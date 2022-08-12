package sm.ciscoop.preno.hic.abilitazione;


import javax.servlet.annotation.WebServlet;

import sm.ciscoop.preno.hic.masks.AppSingleMask;
import sm.ciscoop.sec.pdc.zUtente;
import sm.ciscoop.servlet.OpRes;
import sm.ciscoop.servlet.Passo;


@WebServlet(value = "/abilitazione/HUtente", name = "HUtente")
public class HUtente extends AppSingleMask<zUtente> {

	private static final long serialVersionUID = -1515382824716615373L;

	@Override
	protected Object[] abilitaRelazioniPDC() throws Exception {
		return new String[] { //
				zUtente.CSZrel_UtGr, //
				""
		};
	}

	@Override
	public zUtente initPDC() throws Exception {
		zUtente m_mioPDC = super.initPDC();
		m_mioPDC.getAttributi().remove(zUtente.CSZcol_HPwd);
		m_mioPDC.getAttributi().remove(zUtente.CSZcol_XPwd);
		m_mioPDC.getAttributi().remove(zUtente.CSZcol_DbUser);
		return m_mioPDC;
	}

	@Override
	public boolean afterExecIns(Passo nPasso, OpRes nOpRes) throws Exception {
		if ( !super.afterExecIns(nPasso, nOpRes)) {
			return false;
		}
		if (nPasso == Passo.ESECUZIONE && nOpRes == OpRes.OK) {
			zUtente m_mioPDC = getPDC();
			setRedirect(getServletFullPath() + "?MTipo=V&MPasso=2&" + zUtente.CSZcol_CdUtente + "=" + m_mioPDC.getCdUtente());
			addMessageInfo("Inserimento completato correttamente. Chiudere la maschera per vedere il dato aggiornato.");
		}
		return true;
	}
}
