package sm.ciscoop.preno.hic.abilitazione;


import javax.servlet.annotation.WebServlet;

import sm.ciscoop.preno.hic.masks.AppSingleMask;
import sm.ciscoop.sec.pdc.zGruppo;
import sm.ciscoop.sec.pdc.zRuoliGruppo;
import sm.ciscoop.sec.pdc.zRuolo;
import sm.ciscoop.servlet.OpRes;
import sm.ciscoop.servlet.Passo;
import sm.ciscoop.stdlibs.db.output.AnySelect;


@WebServlet(value = "/abilitazione/HRuoliGruppo", name = "HRuoliGruppo")
public class HRuoliGruppo extends AppSingleMask<zRuoliGruppo> {

	private static final long serialVersionUID = -7216243083474511378L;

	@Override
	protected Object[] abilitaRelazioniPDC() throws Exception {
		return new String[] { //
				zRuoliGruppo.CSZrel_Gruppo, //
				zRuoliGruppo.CSZrel_Ruolo, //
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

			if (camporicerca.equalsIgnoreCase("IdRuolo")) {
				String ls = "select IdRuolo, NomeRuolo as nome FROM " + zRuolo.CSZ_DBTable;
				ls += " WHERE 1 = 1";
				if (desc != null && desc.length() >= 1) {
					desc = desc.replaceAll("'", "''");
					ls += " AND NomeRuolo like '" + desc.trim() + "%'";
				} else if (cd != null && cd.length() > 0) {
					ls += " AND IdRuolo = " + cd.trim();
				} else {
					ls += " AND 1 = 0";
				}
				appendToResponse(new AnySelect(ls, "Elementi"));
			}
		} else {
			String lszGruppo = "select CdGruppo, IdGruppo FROM " + zGruppo.CSZ_DBTable;
			lszGruppo += " WHERE 1 = 1 ";
			appendToResponse(new AnySelect(lszGruppo, "Gruppo"));

			String lszRuolo = "select IdRuolo, NomeRuolo FROM " + zRuolo.CSZ_DBTable;
			lszRuolo += " WHERE 1 = 1 ";
			appendToResponse(new AnySelect(lszRuolo, "Ruolo"));
		}
	}

	@Override
	public boolean afterExecIns(Passo nPasso, OpRes nOpRes) throws Exception {
		if ( !super.afterExecIns(nPasso, nOpRes)) {
			return false;
		}
		if (nPasso == Passo.ESECUZIONE && nOpRes == OpRes.OK) {
			zRuoliGruppo m_mioPDC = getPDC();
			setRedirect(getServletFullPath() + "?MTipo=V&MPasso=2" //
					+ "&" + zRuoliGruppo.CSZcol_CdGruppo + "=" + m_mioPDC.getCdGruppo() //
					+ "&" + zRuoliGruppo.CSZcol_IdRuolo + "=" + m_mioPDC.getIdRuolo());
			addMessageInfo("Inserimento completato correttamente. Chiudere la maschera per vedere il dato aggiornato.");
		}
		return true;
	}
}
