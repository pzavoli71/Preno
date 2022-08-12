
package sm.ciscoop.preno.batch.giorno;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import sm.ciscoop.dmc.SimpleList;
import sm.ciscoop.preno.batch.AppBatch;
import sm.ciscoop.stdlibs.baseutils.types.DtUtil;
import sm.ciscoop.stdlibs.baseutils.types.Text;


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
			List<Map<String, Object>> righe = jb.getRowsAsMap(ls);
			/*
			for (Map<String, Object> riga : righe) {
				SimpleList sl = new SimpleList(getMsgHandler(), "ListaTrovati");
				dmc.setQuery("select TpSoggetto, CdSoggetto, NomeSoggetto, WholeName, BirthDate, TokenFound, referencenumber, regulationprogramme from " + RisultatoEsec.CSZ_DBTable + " where idesecue = " + esec.getIdEsecUE());
				sl.setDMC(dmc);
				sl.load();
				if ( sl.getCount() > 0) {
					ExcelReport exc = new ExcelReport(getMsgHandler());
					SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
					String nomefile = "RisultatoEsec_" + esec.getIdRicerca() + "_" + sf.format(new Date()) + ".xls";
					exc.stampaExcel(sl, null, nomefile, "Ricerca Soggetti sottoposti a sanzioni UE per " + NomeRicerca, "Ricerca Soggetti sottoposti a sanzioni UE per " + NomeRicerca, null);
					inviaEmail("La ricerca ha estratto " + sl.getCount() + " soggetti", emailUfficio, nomefile, NomeRicerca);
				}
			}
*/
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
}
