package sm.ciscoop.preno.hic.abilitazione;


import javax.servlet.annotation.WebServlet;

import sm.ciscoop.preno.hic.masks.IAppMask;
import sm.ciscoop.servlet.HCommonLogonPA;


@WebServlet(value = "/abilitazione/HLogonPA", name = "HLogonPA")
public class HLogonPA extends HCommonLogonPA implements IAppMask {

	private static final long serialVersionUID = -5955749703833134420L;

	@Override
	protected String getPagUrl() {
		/*-
		  Attenzione serve solo se si fa la redirect tra portale internet e
		  intranet altrimenti scrivere nella getPagUrl() l'url diretto della pagina
		  principale
		
		    if(getAppSessione().getProfiloUtente().isUtenteInternet()) {
		      return getAppProperties().getContextPath() + "/<PAGINA_INTERNET>";
		    } else {
		      return getAppProperties().getContextPath() + "/<PAGINA_INTRANET>";
		  }
		 */
		return getAppProperties().getContextPath() + "/HPagUtente";
	}
}
