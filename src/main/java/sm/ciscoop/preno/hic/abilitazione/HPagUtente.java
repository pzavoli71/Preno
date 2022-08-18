package sm.ciscoop.preno.hic.abilitazione;


import javax.servlet.annotation.WebServlet;

import sm.ciscoop.preno.hic.masks.IAppMask;
import sm.ciscoop.servlet.HCommonPagUtente;
import sm.ciscoop.stdlibs.baseutils.lang.Classes;


@WebServlet(value = "/abilitazione/HPagUtente", name = "HPagUtente")
public class HPagUtente extends HCommonPagUtente implements IAppMask {

	private static final long serialVersionUID = 1L;
	
	@Override
	public String getProxMaskName() {
		String url = Classes.getBasePackagePath(HLogon.class) + "/abilitazione/HLogon.xsl";
		url = "file:////" + getServletContext().getRealPath("/abilitazione/HPagUtente.xsl");
		return url;
	}
		
}
