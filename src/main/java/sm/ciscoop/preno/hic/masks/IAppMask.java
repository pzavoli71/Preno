package sm.ciscoop.preno.hic.masks;

import sm.ciscoop.preno.hic.AppSessione;
import sm.ciscoop.preno.hic.abilitazione.HLogonPA;
import sm.ciscoop.preno.utils.AppProperties;
import sm.ciscoop.servlet.IBaseMask;
import sm.ciscoop.servlet.StdSessione;
import sm.ciscoop.servlet.sec.HStdLogon;

/**
 * Interfaccia che raccoglie tutte le utility comuni a tutte le maschere
 * dell'applicativo
 *
 * @author marco
 *
 */
public interface IAppMask extends IBaseMask {


	/**
	 * Ritorna le properties dell'applicativo
	 *
	 * @return
	 */
	default AppProperties getAppProperties() {
		return AppProperties.getInstance();
	}

	/**
	 * Restituisce la classe di sessione dell'applicativo
	 *
	 * @return
	 */
	default AppSessione getAppSessione() {
		return (AppSessione) getStdSessione();
	}

	/**
	 * Ritorna la classe della sessione dell'applicativo tramite generics
	 *
	 * @param <T>
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	default <T extends StdSessione> Class<T> getClassSessione() {
		return (Class<T>) AppSessione.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public default <H extends HStdLogon> Class<H> getExternalLoginServletClass() {
		return (Class<H>) HLogonPA.class;
	}

}
