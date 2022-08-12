package sm.ciscoop.preno.hic.abilitazione;


import javax.servlet.annotation.WebServlet;

import sm.ciscoop.preno.hic.masks.AppDynamicListMask;
import sm.ciscoop.pdc.Lista;
import sm.ciscoop.sec.pdc.zUtente;


@WebServlet(value = "/abilitazione/HLstUtente", name = "HLstUtente")
public class HLstUtente extends AppDynamicListMask<zUtente> {

	private static final long serialVersionUID = -6945099280123398805L;

	@Override
	public void impostaValoriFiltro(Lista lista) throws Exception {
		if (c_DIMPAGINA.isEmptyNullOrZero()) {
			c_DIMPAGINA.setValue(10);
		}
		zUtente m_mioPDC = getPDC();
		lista.setPageSize(c_DIMPAGINA.getIntValue());

		m_mioPDC.getAttributi().get(zUtente.CSZcol_CdUtente).setAliasTab(zUtente.CSZ_DBTable);
		lista.addCampoFiltro(m_mioPDC.getAttributi().get(zUtente.CSZcol_CdUtente), false);
		if ( !m_mioPDC.getAttributi().get(zUtente.CSZcol_DsUtente).isEmptyOrNull()) {
			String nome = m_mioPDC.getDsUtente();
			nome = nome.replaceAll("'", "\'\'");
			lista.addCondizione("Nome", zUtente.CSZ_DBTable + "." + zUtente.CSZcol_DsUtente + " like '%" + nome + "%'");
		}
		lista.setOrderByList(zUtente.CSZ_DBTable + "." + zUtente.CSZcol_DsUtente);
	}
}
