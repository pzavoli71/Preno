package sm.ciscoop.preno.hic.abilitazione;


import javax.servlet.annotation.WebServlet;

import sm.ciscoop.preno.hic.masks.AppDynamicListMask;
import sm.ciscoop.pdc.CharAttr;
import sm.ciscoop.pdc.IntAttr;
import sm.ciscoop.pdc.Lista;
import sm.ciscoop.sec.pdc.zRuoliGruppo;
import sm.ciscoop.sec.pdc.zRuolo;
import sm.ciscoop.stdlibs.baseutils.exceptions.CISException;


@WebServlet(value = "/abilitazione/HLstRuolo", name = "HLstRuolo")
public class HLstRuolo extends AppDynamicListMask<zRuolo> {

	private static final long	serialVersionUID	= 6555085238767861680L;
	CharAttr									c_NomeRuolo;
	IntAttr										c_IdRuolo;

	@Override
	public void initMsk() throws Exception {
		c_NomeRuolo = getAttributi().addCharAttr("NomeRuolo");
		c_IdRuolo = getAttributi().addIntAttr("IdRuolo");
		super.initMsk();
	}

	@Override
	protected Object[] abilitaRelazioniPDC() throws Exception {
		return new String[] { //
				zRuolo.CSZrel_RuoliGruppo, //
				zRuoliGruppo.CSZrel_Gruppo, //
				"" //
		};
	}

	@Override
	public void impostaValoriFiltro(Lista lista) throws CISException {
		if (c_DIMPAGINA.isEmptyNullOrZero()) {
			c_DIMPAGINA.setValue(10);
		}
		zRuolo m_mioPDC = getPDC();
		lista.setPageSize(c_DIMPAGINA.getIntValue());

		lista.addCampoFiltro(m_mioPDC.getAttributi().get(zRuolo.CSZcol_IdRuolo), zRuolo.CSZ_DBTable + "." + zRuolo.CSZcol_IdRuolo,
				false);
		try {
			lista.addCondizione("Nome", zRuolo.CSZ_DBTable + "." + zRuolo.CSZcol_NomeRuolo + " like '%" + c_NomeRuolo + "%'");
		} catch(Exception e) {
			throw new CISException(e.getLocalizedMessage());
		}
	}
}
