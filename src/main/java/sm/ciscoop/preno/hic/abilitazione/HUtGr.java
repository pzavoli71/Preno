package sm.ciscoop.preno.hic.abilitazione;


import javax.servlet.annotation.WebServlet;

import sm.ciscoop.preno.hic.masks.AppSingleMask;
import sm.ciscoop.sec.pdc.zGruppo;
import sm.ciscoop.sec.pdc.zUtGr;
import sm.ciscoop.sec.pdc.zUtente;
import sm.ciscoop.servlet.OpRes;
import sm.ciscoop.servlet.Passo;
import sm.ciscoop.stdlibs.db.output.AnySelect;


@WebServlet(value = "/abilitazione/HUtGr", name = "HUtGr")
public class HUtGr extends AppSingleMask<zUtGr> {

	private static final long serialVersionUID = -1515382824716615373L;

	@Override
	protected Object[] abilitaRelazioniPDC() throws Exception {
		return new String[] { //
				zUtGr.CSZrel_Gruppo, //
				zUtGr.CSZrel_zUtente, //
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

			if (camporicerca.equalsIgnoreCase("CdUtente")) {
				String ls = "select CdUtente, DsUtente as nome, case when UtenteInternet = -1 THEN 'Internet' ELSE 'PA' END as tipo FROM "
						+ zUtente.CSZ_DBTable;
				ls += " WHERE 1 = 1";
				if (desc != null && desc.length() >= 3) {
					desc = desc.replaceAll("'", "''");
					ls += " AND DsUtente like '%" + desc.trim() + "%'";
				} else if (cd != null && cd.length() > 0) {
					ls += " AND CdUtente = " + cd.trim();
				} else {
					ls += " AND 1 = 0";
				}
				appendToResponse(new AnySelect(ls, "Elementi"));
			}
		} else {
			String lszGruppo = "select CdGruppo, IdGruppo FROM " + zGruppo.CSZ_DBTable;
			lszGruppo += " WHERE 1 = 1 ";
			appendToResponse(new AnySelect(lszGruppo, "Gruppo"));

			String lszUtente = "select CdUtente, IdUtente FROM " + zUtente.CSZ_DBTable;
			lszUtente += " WHERE 1 = 0 ";
			appendToResponse(new AnySelect(lszUtente, "zUtente"));
		}
	}

	@Override
	public boolean afterExecIns(Passo nPasso, OpRes nOpRes) throws Exception {
		if ( !super.afterExecIns(nPasso, nOpRes)) {
			return false;
		}
		if (nPasso == Passo.ESECUZIONE && nOpRes == OpRes.OK) {
			zUtGr m_mioPDC = getPDC();
			setRedirect(getServletFullPath() + "?MTipo=V&MPasso=2&" + zUtGr.CSZcol_CdGruppo + "=" + m_mioPDC.getCdGruppo() + "&"
					+ zUtGr.CSZcol_CdUtente + "=" + m_mioPDC.getCdUtente());
			addMessageInfo("Inserimento completato correttamente. Chiudere la maschera per vedere il dato aggiornato.");
		}
		return true;
	}
}
