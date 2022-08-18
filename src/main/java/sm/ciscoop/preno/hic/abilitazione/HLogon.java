package sm.ciscoop.preno.hic.abilitazione;


import java.net.URL;

import javax.servlet.annotation.WebServlet;

import sm.ciscoop.preno.hic.masks.IAppMask;
import sm.ciscoop.preno.utils.AppProperties;
import sm.ciscoop.sec.AccessManager;
import sm.ciscoop.sec.pdc.zRuolo;
import sm.ciscoop.servlet.HCommonLogon;
import sm.ciscoop.servlet.sec.ProfiloUtente;
import sm.ciscoop.servlet.sec.ProfiloUtente.ColonneProfiloUtente;
import sm.ciscoop.stdlibs.baseutils.exceptions.CISException;
import sm.ciscoop.stdlibs.baseutils.lang.Classes;
import sm.ciscoop.stdlibs.baseutils.types.Text;
import sm.ciscoop.util.CoreProperties;
import sm.ciscoop.util.CoreUtils;


@WebServlet(value = "/abilitazione/HLogon", name = "HLogon")
public class HLogon extends HCommonLogon implements IAppMask {

	private static final long serialVersionUID = -6744302361897431743L;

	@Override
	protected String getPagUrl() {
		return getAppProperties().getContextPath() + "/abilitazione/HPagUtente";
	}
	
	@Override
	protected void beforeGestTran() throws Exception {
	}

	protected ProfiloUtente caricaProfiloUtente(AccessManager am) throws Exception {
		ProfiloUtente profiloUtente = null;
		if (false) {
			profiloUtente = new ProfiloUtente();
			//profiloUtente.caricaProfiloUtente(c_IdProfiloUtente.getValue(), getJBB());
		} else {

			profiloUtente = new ProfiloUtente();
			/*if ( !getWebProperties().isStandaloneLogon()) {
				profiloUtente.caricaProfiloUtenteServizioRuolo(getCdUtente(), c_IdServizioLogin.getIntValue(), c_IdRuoloLogin.getIntValue(), getJBB());
			}*/

			if (profiloUtente.getCdUtente() <= 0) {
				Integer cdUtente = getCdUtente();
				String idUtente = getIdUtente();
				String dsUtente = "";
				String emailUtente = "";
				if (am != null && am.getUtente() != null) {
					dsUtente = am.getUtente().getDescr();
				} else if (getPDC() != null) {
					dsUtente = getPDC().getDsUtente();
					emailUtente = getPDC().getEmail().getValue();
				}
				if ( !Text.isValue(dsUtente)) {
					dsUtente = getIdUtente();
				}

				profiloUtente.setValue(ColonneProfiloUtente.CDUTENTE.getNome(), cdUtente);
				profiloUtente.setValue(ColonneProfiloUtente.IDUTENTE.getNome(), idUtente);
				profiloUtente.setValue(ColonneProfiloUtente.DSUTENTE.getNome(), dsUtente);
				profiloUtente.setValue(ColonneProfiloUtente.EMAIL_UTENTE.getNome(), emailUtente);

			}
		}
		if (isShibX509Login()) {
			int nCodiss = codissFromSerial(getShibX509Serial());
			if (profiloUtente.getCodiss() != nCodiss) {
				throw new CISException("Errore! Il codice ISS associato al profilo Ã¨ diverso da quello del detentore della smart card");
			}
		} else if (isShibPasswdLogin() && !profiloUtente.getIdUtente().equals(readShibUser())) {
			throw new CISException("Errore! Il nome utente associato al profilo Ã¨ diverso da quello del server di autenticazione!");
		}
		return profiloUtente;
	}

	@Override
	public String getProxMaskName() {
		String url = Classes.getBasePackagePath(HLogon.class) + "/abilitazione/HLogon.xsl";
		//url = AppProperties.getInstance().getContextPath() + "/abilitazione/HLogon.xsl";
		url = "file:////" + getServletContext().getRealPath("/abilitazione/HLogon.xsl");
		return url;
		/*URL resUrl = HLogon.class.getResource("/preno/abilitazione/HLogon.xsl");
		return CoreUtils.getProxMaskName(resUrl);*/
	}
	
}
