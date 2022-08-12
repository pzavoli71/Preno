package sm.ciscoop.preno.hic.abilitazione;


import javax.servlet.annotation.WebServlet;

import sm.ciscoop.preno.hic.masks.AppSingleMask;
import sm.ciscoop.sec.pdc.zGruppo;
import sm.ciscoop.servlet.OpRes;
import sm.ciscoop.servlet.Passo;


@WebServlet(value = "/abilitazione/HGruppo", name = "HGruppo")
public class HGruppo extends AppSingleMask<zGruppo> {

	private static final long serialVersionUID = -1515382824716615373L;

	@Override
	protected Object[] abilitaRelazioniPDC() throws Exception {
		return new String[] { //
				zGruppo.CSZrel_Permessi, //
				zGruppo.CSZrel_UtGr, //
				zGruppo.CSZrel_RuoliGruppo, //
				"" //
		};
	}

	@Override
	public boolean afterExecIns(Passo nPasso, OpRes nOpRes) throws Exception {
		if ( !super.afterExecIns(nPasso, nOpRes)) {
			return false;
		}
		if (nPasso == Passo.ESECUZIONE && nOpRes == OpRes.OK) {
			zGruppo m_mioPDC = getPDC();
			setRedirect(getServletFullPath() + "?MTipo=V&MPasso=2" //
					+ "&" + zGruppo.CSZcol_CdGruppo + "=" + m_mioPDC.getCdGruppo() //
					+ "&" + zGruppo.CSZcol_IdGruppo + "=" + m_mioPDC.getIdGruppo());

			addMessageInfo("Inserimento completato correttamente. Chiudere la maschera per vedere il dato aggiornato.");
		}
		return true;
	}
}
