package sm.ciscoop.preno.hic.abilitazione;


import javax.servlet.annotation.WebServlet;

import sm.ciscoop.preno.hic.masks.IAppMask;
import sm.ciscoop.servlet.HCommonPagUtente;


@WebServlet(value = "/abilitazione/HPagUtente", name = "HPagUtente")
public class HPagUtente extends HCommonPagUtente implements IAppMask {

	private static final long serialVersionUID = 1L;
}
