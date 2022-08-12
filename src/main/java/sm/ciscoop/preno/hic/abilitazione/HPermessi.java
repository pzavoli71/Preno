package sm.ciscoop.preno.hic.abilitazione;


import javax.servlet.annotation.WebServlet;

import sm.ciscoop.preno.hic.masks.AppSingleMask;
import sm.ciscoop.sec.pdc.zGruppo;
import sm.ciscoop.sec.pdc.zPermessi;
import sm.ciscoop.sec.pdc.zTrans;
import sm.ciscoop.servlet.OpRes;
import sm.ciscoop.servlet.Passo;
import sm.ciscoop.stdlibs.db.output.AnySelect;


@WebServlet(value = "/abilitazione/HPermessi", name = "HPermessi")
public class HPermessi extends AppSingleMask<zPermessi> {

	private static final long serialVersionUID = 454223830944962565L;

	@Override
	protected Object[] abilitaRelazioniPDC() throws Exception {
		return new String[] { //
				zPermessi.CSZrel_Trans, //
				zPermessi.CSZrel_Gruppo, //
				"" //
		};
	}

	@Override
	public void loadCombo() throws Exception {
		super.loadCombo();
		if (isSoloDatiCombo()) {
			String desc = getParRicercaComboTesto();
			String cd = getParRicercaComboCodice();
			String camporicerca = getParRicercaComboCampo();

			if (camporicerca.equalsIgnoreCase("CdPDC")) {
				String ls = "select CdPDC, DsPDC as nome FROM " + zTrans.CSZ_DBTable;
				ls += " WHERE 1 = 1";
				if (desc != null && desc.length() >= 3) {
					desc = desc.replaceAll("'", "''");
					ls += " AND DsPDC like '" + desc.trim() + "%'";
				} else if (cd != null && cd.length() > 0) {
					ls += " AND CdPDC = " + cd.trim();
				} else {
					ls += " AND 1 = 0";
				}
				appendToResponse(new AnySelect(ls, "Elementi"));
			}

			if (camporicerca.equalsIgnoreCase("CdGruppo")) {
				String ls = "select CdGruppo, IdGruppo as nome FROM " + zGruppo.CSZ_DBTable;
				ls += " WHERE 1 = 1";
				if (desc != null && desc.length() >= 3) {
					desc = desc.replaceAll("'", "''");
					ls += " AND IdGruppo like '" + desc.trim() + "%'";
				} else if (cd != null && cd.length() > 0) {
					ls += " AND CdGruppo = " + cd.trim();
				} else {
					ls += " AND 1 = 0";
				}
				appendToResponse(new AnySelect(ls, "Elementi"));
			}
		} else {
			String lszTrans = "select CdPDC, DsPDC FROM " + zTrans.CSZ_DBTable;
			lszTrans += " WHERE 1 = 1 ";
			appendToResponse(new AnySelect(lszTrans, "Trans"));

			String lszGruppo = "select CdGruppo, IdGruppo FROM " + zGruppo.CSZ_DBTable;
			lszGruppo += " WHERE 1 = 1 ";
			appendToResponse(new AnySelect(lszGruppo, "Gruppo"));
		}
	}

	@Override
	public boolean afterExecIns(Passo nPasso, OpRes nOpRes) throws Exception {
		if ( !super.afterExecIns(nPasso, nOpRes)) {
			return false;
		}
		zPermessi m_mioPDC = getPDC();
		if (nPasso == Passo.ESECUZIONE && nOpRes == OpRes.OK) {
			setRedirect(getServletFullPath() + "?MTipo=V&MPasso=2" //
					+ "&" + zPermessi.CSZcol_CdPDC + "=" + m_mioPDC.getCdPDC() //
					+ "&" + zPermessi.CSZcol_CdGruppo + "=" + m_mioPDC.getCdGruppo());
			addMessageInfo("Inserimento completato correttamente. Chiudere la maschera per vedere il dato aggiornato.");
		}
		return true;
	}
}
