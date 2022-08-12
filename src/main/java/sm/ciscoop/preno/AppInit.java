package sm.ciscoop.preno;


import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;

import sm.ciscoop.preno.utils.AppProperties;
import sm.ciscoop.servlet.listeners.StdContextListener;


/**
 * Classe di inizializzazione dell'applicativo
 */
@WebListener
public class AppInit extends StdContextListener {

	/**
	 * Init standard delle servlet. Attenzione! E' importante che
	 * {@link AppProperties#getInstance()} sia il primo metodo chiamato,
	 * altrimenti si rischia che la classe della configurazione dell'applicativo
	 * venga castata alla classe sbagliata, provocando errori in partenza
	 */
	@Override
	public void init(ServletContext context) throws Exception {
		AppProperties.getInstance();
		super.init(context);
	}
}
