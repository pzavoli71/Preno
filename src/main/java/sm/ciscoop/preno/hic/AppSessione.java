package sm.ciscoop.preno.hic;


import sm.ciscoop.jbb.JBB;
import sm.ciscoop.sec.AccessManager;
import sm.ciscoop.servlet.StdSessione;
import sm.ciscoop.servlet.sec.ProfiloUtente;
import sm.ciscoop.servlet.tabsessione.StdTabSessione;
import sm.ciscoop.stdlibs.baseutils.output.PseudoFragment;
import sm.ciscoop.util.GestoreDB;
import sm.ciscoop.util.PDCProperties;
import sm.ciscoop.util.WebProperties;


/**
 * Classe della sessione dell'applicativo
 *
 */
public class AppSessione extends StdSessione<StdTabSessione> {

  public AppSessione() throws Exception {
    super();
  }

	private String						c_Modificato		 = null;

	public void setModificato(String val) {
		c_Modificato = val;
	}

	public String getModificato() {
		return c_Modificato;
	}
	
	private static final long serialVersionUID = 5904222482370934066L;

	@Override
	protected PseudoFragment getOutputSpec() throws Exception {
		PseudoFragment frg = new PseudoFragment();
		frg.put("MODIFICATO", c_Modificato);
		return frg;
	}

	@Override
	public void applicaLogin(AccessManager am, ProfiloUtente utente, boolean isSimulato) throws Exception {
		super.applicaLogin(am, utente, isSimulato);

		//NOTA!!!: Se vogliamo settare qualcosa che non c'è già nella sessione standard va fatto qui
	}
	
	public void caricaNomeOE(ProfiloUtente profiloUtente, JBB jbb) throws Exception {
	}
	
}
