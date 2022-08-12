package sm.ciscoop.preno.hic.abilitazione;


import javax.servlet.annotation.WebServlet;

import sm.ciscoop.preno.hic.masks.AppDynamicListMask;
import sm.ciscoop.pdc.Lista;
import sm.ciscoop.sec.pdc.zGruppo;
import sm.ciscoop.sec.pdc.zRuoliGruppo;
import sm.ciscoop.sec.pdc.zUtGr;
import sm.ciscoop.servlet.Passo;


@WebServlet(value = "/abilitazione/HLstGruppo", name = "HLstGruppo")
public class HLstGruppo extends AppDynamicListMask<zGruppo> {

	private static final long serialVersionUID = -3555583721440225118L;

	@Override
	protected Object[] abilitaRelazioniPDC() throws Exception {
		return new Object[] { //
				zGruppo.CSZrel_Permessi, 0,  //
				zGruppo.CSZrel_UtGr, 0, //
				zUtGr.CSZrel_zUtente, 0, //
				zGruppo.CSZrel_RuoliGruppo, 0, //
				zRuoliGruppo.CSZrel_Ruolo, 0,
				"" };
	}

	@Override
	public void impostaValoriFiltro(Lista lista) throws Exception {
		if (c_DIMPAGINA.isEmptyNullOrZero()) {
			c_DIMPAGINA.setValue(10);
		}
		lista.setPageSize(c_DIMPAGINA.getIntValue());

		zGruppo m_mioPDC = getPDC();
		m_mioPDC.getAttributi().get(zGruppo.CSZcol_CdGruppo).setAliasTab(zGruppo.CSZ_DBTable);
		lista.addCampoFiltro(m_mioPDC.getAttributi().get(zGruppo.CSZcol_CdGruppo), false);

		if ( !m_mioPDC.getAttributi().get(zGruppo.CSZcol_IdGruppo).isEmptyOrNull()) {
			String nome = m_mioPDC.getIdGruppo();
			nome = nome.replaceAll("'", "\'\'");
			lista.addCondizione("Nome", zGruppo.CSZ_DBTable + "." + zGruppo.CSZcol_IdGruppo + " like '%" + nome + "%'");
		}

		lista.setOrderByList(" " + zGruppo.CSZ_DBTable + "." + zGruppo.CSZcol_IdGruppo);
	}

	@Override
	protected Passo getPassoDefault() {
		return Passo.PRESENTAZIONE;
	}
}
