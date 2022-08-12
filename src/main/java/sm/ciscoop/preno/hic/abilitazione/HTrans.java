package sm.ciscoop.preno.hic.abilitazione;


import javax.servlet.annotation.WebServlet;

import sm.ciscoop.preno.hic.masks.AppSingleMask;
import sm.ciscoop.sec.pdc.zTrans;
import sm.ciscoop.servlet.OpRes;
import sm.ciscoop.servlet.Passo;


@WebServlet(value = "/abilitazione/HTrans", name = "HTrans")
public class HTrans extends AppSingleMask<zTrans> {

	private static final long serialVersionUID = -7274460639216287170L;

	@Override
	protected Object[] abilitaRelazioniPDC() throws Exception {
		return new String[] { //
				zTrans.CSZrel_Permessi, //
				"" //
		};
	}

	@Override
	public boolean afterExecIns(Passo nPasso, OpRes nOpRes) throws Exception {
		if ( !super.afterExecIns(nPasso, nOpRes)) {
			return false;
		}
		if (nPasso == Passo.ESECUZIONE && nOpRes == OpRes.OK) {
			zTrans m_mioPDC = getPDC();
			setRedirect(getServletFullPath() + "?MTipo=V&MPasso=2&" + zTrans.CSZcol_CdPDC + "=" + m_mioPDC.getCdPDC());
			addMessageInfo("Inserimento completato correttamente. Chiudere la maschera per vedere il dato aggiornato.");
		}
		return true;
	}
}
