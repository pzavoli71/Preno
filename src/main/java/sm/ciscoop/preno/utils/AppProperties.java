package sm.ciscoop.preno.utils;


import sm.ciscoop.stdlibs.baseutils.BaseProperties;
import sm.ciscoop.stdlibs.baseutils.ConfigurationManagerEntry;
import sm.ciscoop.util.WebProperties;


/**
 * Classe singleton che rappresenta le properties standard dell'applicativo.
 * Fare override dei metodi qui presenti per modificare le proprietà standard
 * dell'applicativo, preferibilmente mantenendo l'annotation
 * {@link ConfigurationManagerEntry} per ottenere una gestione grafica tramite
 * la servlet HGConfigurationManager
 */
public class AppProperties extends WebProperties {

	private static final long serialVersionUID = 1L;

	/** costruttore privato per prevenire nuove istanze di classe */
	private AppProperties() {
		super();
	}

	public AppProperties(BaseProperties properties) {
		super(properties);
	}

	/**
	 * Ritorna l'istanza del singleton
	 *
	 * @return
	 */
	public static synchronized AppProperties getInstance() {
		if (BaseProperties.m_ConfigurationManager == null) {
			synchronized (BaseProperties.class) {
				if (BaseProperties.m_ConfigurationManager == null) {
					BaseProperties.m_ConfigurationManager = new AppProperties();
				}
			}
		}
		if ( ! (BaseProperties.m_ConfigurationManager instanceof AppProperties)) {
			synchronized (BaseProperties.class) {
				AppProperties prop = new AppProperties(BaseProperties.m_ConfigurationManager);
				BaseProperties.m_ConfigurationManager = prop;
			}
		}
		return (AppProperties) BaseProperties.m_ConfigurationManager;
	}

	@Override
	public boolean isOutputSchemaEnabled() {
		return true;
	}

	@Override
	public int getSessionTimeout() {
		return 86400;
	}
}
