
package sm.ciscoop.preno.batch.giorno;


import java.io.File;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import sm.ciscoop.dmc.SimpleList;
import sm.ciscoop.preno.batch.AppBatch;
import sm.ciscoop.preno.hic.prenota.ReportGiorno;
import sm.ciscoop.stdlibs.baseutils.transform.TextTransformer;
import sm.ciscoop.stdlibs.baseutils.types.DtUtil;
import sm.ciscoop.stdlibs.baseutils.types.Text;
import sm.ciscoop.stdlibs.mail.SendMail;
import sm.ciscoop.stdlibs.mail.sender.CISEmailMessage;
import sm.ciscoop.stdlibs.mail.sender.SingleSender;
import sm.ciscoop.stdreport.main.StdReport;
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
			/*jb.setUseTransactions(true);
			jb.beginTrans();
*/
			Date dt = new Date();
			int nriga = 0;
			
			ReportGiorno rep2008 = new ReportGiorno(jb);
			StdReport std = rep2008.getStdReport();
			//std.addResourceLocation("sm.ciscoop.preno.stampe.includes");
			rep2008.addParameter("DtGiorno", new Date());
			rep2008.addParameter("IdTpTrasporto", 1);
			
			rep2008.createReport();
			String filename = rep2008.getFileName();
			String nomefile = rep2008.getOutputDir() + "/" + filename;
			File f = new File(nomefile);
			if ( f.exists()) {
				System.out.println("esiste");
				sendToTelegram(nomefile);
			}
			System.out.println(filename);
			/*
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
				inviaEmail("La ricerca ha estratto soggetti", email, null, "");
			}*/
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

	  
	  public static void sendToTelegram(String nomefile) {
		  
		  try {
	      String urlString = "https://api.telegram.org/bot%s/sendDocument";

	      //Add Telegram token (given Token is fake)
	      String apiToken = "5317668012:AAEWtqw2GS2ELcqz3cuNT2LFrkZQovqpM7E";
	    
	      //Add chatId (given chatId is fake)
	      String chatId = "@TrasportiCalcio";
	      Date dt = new Date();
	      String text = "Hello world 4 " + dt;

	      urlString = String.format(urlString, apiToken);
		  
		  String url = urlString; //"https://api.telegram.org/bot%s/sendDocument";	  
		  String charset = "UTF-8";
		  String param = "@TrasportiCalcio";
		  File binaryFile = new File(nomefile);
		  String boundary = Long.toHexString(System.currentTimeMillis()); // Just generate some unique random value.
		  String CRLF = "\r\n"; // Line separator required by multipart/form-data.

		  URLConnection connection = new URL(url).openConnection();
		  connection.setDoOutput(true);
		  connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

		  try (
		      OutputStream output = connection.getOutputStream();
		      PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, charset), true);
		  ) {
		      // Send normal param.
		      writer.append("--" + boundary).append(CRLF);
		      writer.append("Content-Disposition: form-data; name=\"chat_id\"").append(CRLF);
		      writer.append("Content-Type: text/plain; charset=" + charset).append(CRLF);
		      writer.append(CRLF).append(param).append(CRLF).flush();

		      writer.append("--" + boundary).append(CRLF);
		      writer.append("Content-Disposition: form-data; name=\"text\"").append(CRLF);
		      writer.append("Content-Type: text/plain; charset=" + charset).append(CRLF);
		      writer.append(CRLF).append("Ti invio il documento dei 2008").append(CRLF).flush();
		      
		      // Send text file.
		      /*writer.append("--" + boundary).append(CRLF);
		      writer.append("Content-Disposition: form-data; name=\"textFile\"; filename=\"" + textFile.getName() + "\"").append(CRLF);
		      writer.append("Content-Type: text/plain; charset=" + charset).append(CRLF); // Text file itself must be saved in this charset!
		      writer.append(CRLF).flush();
		      Files.copy(textFile.toPath(), output);
		      output.flush(); // Important before continuing with writer!
		      writer.append(CRLF).flush(); // CRLF is important! It indicates end of boundary.
		*/
		      // Send binary file.
		      writer.append("--" + boundary).append(CRLF);
		      writer.append("Content-Disposition: form-data; name=\"document\"; filename=\"" + binaryFile.getName() + "\"").append(CRLF);
		      writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(binaryFile.getName())).append(CRLF);
		      writer.append("Content-Transfer-Encoding: binary").append(CRLF);
		      writer.append(CRLF).flush();
		      Files.copy(binaryFile.toPath(), output);
		      output.flush(); // Important before continuing with writer!
		      writer.append(CRLF).flush(); // CRLF is important! It indicates end of boundary.

		      // End of multipart/form-data.
		      writer.append("--" + boundary + "--").append(CRLF).flush();
		  }

		  // Request is lazily fired whenever you need to obtain information about response.
		  int responseCode = ((HttpURLConnection) connection).getResponseCode();
		  System.out.println(responseCode); // Should be 200	      
		  } catch(Exception eee ) {
			  eee.printStackTrace();
		  }
		  /*
	      String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";

	      //Add Telegram token (given Token is fake)
	      String apiToken = "5317668012:AAEWtqw2GS2ELcqz3cuNT2LFrkZQovqpM7E";
	    
	      //Add chatId (given chatId is fake)
	      String chatId = "@TrasportiCalcio";
	      Date dt = new Date();
	      String text = "Hello world 4 " + dt;

	      urlString = String.format(urlString, apiToken, chatId, text);

	      try {
	          URL url = new URL(urlString);
	          URLConnection conn = url.openConnection();
	          InputStream is = new BufferedInputStream(conn.getInputStream());
	          is.close();
	      } catch (IOException e) {
	          e.printStackTrace();
	      }*/
	  }  
	
}
