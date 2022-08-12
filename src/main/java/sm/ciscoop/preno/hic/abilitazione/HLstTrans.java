package sm.ciscoop.preno.hic.abilitazione;


import javax.servlet.annotation.WebServlet;

import sm.ciscoop.preno.hic.masks.AppDynamicListMask;
import sm.ciscoop.pdc.Lista;
import sm.ciscoop.sec.pdc.zPermessi;
import sm.ciscoop.sec.pdc.zTrans;


@WebServlet(value = "/abilitazione/HLstTrans", name = "HLstTrans")
public class HLstTrans extends AppDynamicListMask<zTrans> {

	private static final long serialVersionUID = -2199561924634955110L;

	@Override
	protected Object[] abilitaRelazioniPDC() throws Exception {
		return new Object[] {
				zTrans.CSZrel_Permessi, 0,
				zPermessi.CSZrel_Gruppo, 0
		};
	}

	@Override
	public void impostaValoriFiltro(Lista lista) throws Exception {
		if (c_DIMPAGINA.isEmptyNullOrZero()) {
			c_DIMPAGINA.setValue(10);
		}
		lista.setPageSize(c_DIMPAGINA.getIntValue());
		zTrans m_mioPDC = getPDC();
		m_mioPDC.getAttributi().get(zTrans.CSZcol_CdPDC).setAliasTab(zTrans.CSZ_DBTable);

		m_mioPDC.getAttributi().get(zTrans.CSZcol_IdNodo).setAliasTab(zTrans.CSZ_DBTable);
		if ( !m_mioPDC.getAttributi().get(zTrans.CSZcol_CdPDC).isEmptyOrNull()) {
			String nome = m_mioPDC.getCdPDC();
			nome = nome.replaceAll("'", "\'\'");
			lista.addCondizione("Nome", zTrans.CSZ_DBTable + "." + zTrans.CSZcol_CdPDC + " like '%" + nome + "%'");
		}

		lista.addCampoFiltro(m_mioPDC.getAttributi().get(zTrans.CSZcol_IdNodo), false);
	}
}
