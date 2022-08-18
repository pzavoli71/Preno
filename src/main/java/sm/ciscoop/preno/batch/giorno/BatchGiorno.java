
package sm.ciscoop.preno.batch.giorno;


import java.io.File;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import sm.ciscoop.dmc.SimpleList;
import sm.ciscoop.preno.batch.AppBatch;
import sm.ciscoop.stdlibs.baseutils.transform.TextTransformer;
import sm.ciscoop.stdlibs.baseutils.types.DtUtil;
import sm.ciscoop.stdlibs.baseutils.types.Text;
import sm.ciscoop.stdlibs.mail.SendMail;
import sm.ciscoop.stdlibs.mail.sender.CISEmailMessage;
import sm.ciscoop.stdlibs.mail.sender.SingleSender;
import sm.ciscoop.util.CoreProperties;


public class BatchGiorno extends AppBatch {
	@Override
	protected void initBatch() throws Exception {
		addArgument("-d", "--deceduti", "esegue solo il check dei deceduti");
		sm.ciscoop.util.batch.BatchArgument arg = addArgument("-dt", "--datacontrollo", "imposta una specifica data in cui effettuare il controllo (il default e' oggi)");
		arg.setRequireDate(true);

		super.initBatch();
	}
	
	@Override
	protected void lanciaBatch(String[] args) throws Exception {
		sm.ciscoop.jbb.JBB jb = null;
		try {
			logInfo("HOST del db " + getJBB().getDatabase() + ": " + getJBB().getHost());
			jb = getJBB();
			jb.setUseTransactions(true);
			jb.beginTrans();

			Date dt = new Date();
			int nriga = 0;
			String ls = "select * from Giorno g INNER JOIN SoggAR a on a.idgiorno = g.idgiorno ";
			ls += " INNER JOIN Soggetto s ON s.IdSoggetto = a.IdSoggetto ";
			ls += " INNER JOIN TipoTrasporto t on t.IdTpTrasporto = g.IdTpTrasporto ";
			ls += " WHERE g.DtGiorno = " + getJBB().getFormattedVal(dt, true);
			ls += " ORDER BY bRagazzo";
			List<Map<String, Object>> righe = jb.getRowsAsMap(ls);
			for (Map<String, Object> riga : righe) {
				Date DtGiorno = (Date) riga.get("dtgiorno");
				String destinazione = (String) riga.get("destinazione");
				String email = (String) riga.get("emailsogg");
					/*ExcelReport exc = new ExcelReport(getMsgHandler());
					SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
					String nomefile = "RisultatoEsec_" + esec.getIdRicerca() + "_" + sf.format(new Date()) + ".xls";
					exc.stampaExcel(sl, null, nomefile, "Ricerca Soggetti sottoposti a sanzioni UE per " + NomeRicerca, "Ricerca Soggetti sottoposti a sanzioni UE per " + NomeRicerca, null);
					*/
				inviaEmail("La ricerca ha estratto soggetti", email, null, "");
			}
		} catch (Exception e) {
			if (jb != null && jb.inTransaction()) {
				try {
					jb.rollbackTrans();
				} catch (Exception eee) {
					e.printStackTrace();
				}
			}
			logInfo("Errore in esecuzione." + e.getMessage());
			e.printStackTrace();	
		} finally {
			if (jb != null && jb.inTransaction()) {
				try {
					jb.commitTrans();
				} catch (Exception eee) {

				}
			}
		}
	}

	public static void main(String[] args) {
		BatchGiorno batch = new BatchGiorno();
		batch.esegui(args);
	}
	

	private void inviaEmail(String messaggio, String emails, String nomefile, String nomericerca) throws Exception {
		List<CISEmailMessage> listaEmail = new ArrayList<CISEmailMessage>();
		emails = emails.replace(";", ",");
		String mails[] = emails.split(",");
		for (String mail : mails) {
			CISEmailMessage message = new CISEmailMessage(mail);
			message.addAttachment(new File(nomefile));
			message.addFilteringParameter("esito", messaggio);
			listaEmail.add(message);
		}
		/*message = new CISEmailMessage("mfedele.cis@pa.sm");
		message.addFilteringEntry("esito", messaggio);
		listaEmail.add(message);
		*/
		SingleSender singleSender = new SingleSender(creaMail(nomefile, nomericerca));
		singleSender.setListaMessaggi(listaEmail);
		singleSender.spedisci();

		if (singleSender.getTotaleInviiErrati() > 0) {
			logError("Ci sono stati errori. Non è stato possibile inviare " + singleSender.getTotaleInviiErrati() + " messaggi");
		}
	}

	private SendMail creaMail(String nomefile, String nomericerca) throws Exception {
		SendMail mail = new SendMail();
		mail.setEncoding(StandardCharsets.UTF_8.name());
		mail.clearAttachments();
		mail.addAttachedFile(nomefile);
		mail.applyConfiguration(CoreProperties.getInstance());
		mail.clearAddressList();
		mail.setForceSender(true); //necessario per usare questo mittente e non quello definito nel server
		mail.setSender("servizi@pa.sm");
		mail.setSenderName("Elaborazione ricerca Sanzioni UE");
		mail.setSubject(nomericerca);
		mail.setDebugMode(true);
		TextTransformer tt = new TextTransformer();
		tt.setLoaderClass(getClass());
		//tt.set("mail"); //non è il context è la cartella non modificare
		//    tt.addKey("nomecontratto", nomeContratto);
		//    tt.addKey("dtfinepwd", DtUtil.format(dtFinePWD));
		//tt.setTemplate(template);
		String testoEmail = tt.transform();
		//Messaggio mail
		mail.setMessage(testoEmail);
		mail.setHTMLFormat(true);

		return mail;
	}
	
}
