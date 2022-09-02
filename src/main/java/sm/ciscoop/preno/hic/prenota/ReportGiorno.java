package sm.ciscoop.preno.hic.prenota;

import java.io.File;
import java.util.Date;

import sm.ciscoop.jbb.JBB;
import sm.ciscoop.preno.utils.AppReportConfigurator;
import sm.ciscoop.stdlibs.baseutils.types.DtUtil;
import sm.ciscoop.stdlibs.db.GenericDBConn;
import sm.ciscoop.util.CoreProperties;

public class ReportGiorno extends AppReportConfigurator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ReportGiorno(GenericDBConn db) throws Exception {
		super(db);
	}

	public ReportGiorno(JBB db) throws Exception {
		super(db);
	}
	
	@Override
	public String getSourceName() {
		return "HStGiorno";
	}
	
	@Override
	public String getDisplayName() {
		String szDesc = "";
		szDesc += "StGiorno_"; // + _annoprot + "_" + _nrprot;
		return szDesc;
	}

	@Override
	public String getFileName() {
		String szDesc = "";
		szDesc = "StRiepilogoMensile_"; // + _annoprot + "_" + _nrprot;
		return szDesc + ".pdf";
	}	
	@Override
	public void configureReport() throws Exception {
		boolean bHandleTrans = false;
		boolean bOK = true;
		/*try {
			getJBB().setUseTransactions(true);
			bHandleTrans = getJBB().openTrans("Calcolo protocollo");
			bOK = updateProtocollo(elementi);
		} catch (Exception e) {
			bOK = false;
		} finally {
			getJBB().closeTrans(bHandleTrans, bOK);
		}
		 */
		addParameter("NomeUfficio", "ISTITUTO PER LA SICUREZZA SOCIALE");
		addParameter("DescDipartimento", "Assegni Familiari");
		addParameter("Email", "assegni.familiari@iss.sm");
		addParameter("Telefono", "+378(0549)994401");
		addParameter("Fax", "+378(0549)994252");
		setQuery(getQuery());

		Date dt = getParamAsDate("DtGiorno");
		Date dtinizio = DtUtil.getPrimoDelMese(dt);
		Date dtfine = DtUtil.getUltimoDelMese(dt);
		
		addParameter("DtInizio", getJBB().getFormattedVal(dtinizio));
		addParameter("DtFine", getJBB().getFormattedVal(dtfine));
		addParameter("IdTpTrasporto", getParamAsInt("IdTpTrasporto"));
		
		/*String pathStpPDF = CoreProperties.getInstance().getProp("PDF_OUTPATH");
		if (pathStpPDF == null) {
			throw new Exception(" Errore in lettura PDF_OUTPATH");
		}
		pathStpPDF += "/arpa/assfam/";
		File outDir = new File(pathStpPDF);
		outDir.mkdirs();
		setOutputDir(pathStpPDF);
*/
	}
	
}
