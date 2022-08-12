package sm.ciscoop.preno.hic.abilitazione;


import javax.servlet.annotation.WebServlet;

import sm.ciscoop.preno.hic.masks.AppSingleMask;
import sm.ciscoop.sec.pdc.zRuolo;
import sm.ciscoop.servlet.OpRes;
import sm.ciscoop.servlet.Passo;


@WebServlet(value = "/abilitazione/HRuolo", name = "HRuolo")
public class HRuolo extends AppSingleMask<zRuolo> {

	private static final long serialVersionUID = 8242430139767781074L;

	@Override
	protected Object[] abilitaRelazioniPDC() throws Exception {
		return new Object[] {
				zRuolo.CSZrel_RuoliGruppo, 5,
				""
		};
	}

	@Override
	public boolean afterExecIns(Passo nPasso, OpRes nOpRes) throws Exception {
		if ( !super.afterExecIns(nPasso, nOpRes)) {
			return false;
		}
		if (nPasso == Passo.ESECUZIONE && nOpRes == OpRes.OK) {
			zRuolo m_mioPDC = getPDC();
			setRedirect(getServletFullPath() + "?MTipo=V&MPasso=2" + "&IdRuolo=" + m_mioPDC.getIdRuolo());
			addMessageInfo("Inserimento completato correttamente. Chiudere la maschera per vedere il dato aggiornato.");
		}
		return true;
	}
}
