package sm.ciscoop.preno.utils;


import java.util.Formatter;
import java.util.Vector;

import sm.ciscoop.stdlibs.baseutils.exceptions.CISException;


/**
 * Classe delle eccezioni dell'applicativo
 */
public class AppException extends CISException {

  private static final long serialVersionUID = 9160616576149582980L;

	public AppException(Throwable ex, String msg, Integer errCode, String internalMsg, Object... params) {
		super(ex, msg, errCode, internalMsg, params);
	}

	/**
	 * Costruttore. Aggiunge un'exception fornendone il testo.
	 *
	 * @param msg
	 *          messaggio errore
	 */
	public AppException(String msg) {
		super(msg);
	}

	/**
	 * Costruttore. Aggiunge un'exception fornendone il testo e un testo interno
	 *
	 * @param msg
	 *          messaggio errore
	 * @param internalMessage
	 *          messaggio dettagliato, non visibile in output
	 */
	public AppException(String msg, String internalMessage) {
		super(msg, internalMessage);
	}

	/**
	 * Costruttore che specifica exception causata da altra exception.
	 *
	 * @param cause
	 *          eccezzione che ha causato questa
	 * @param msg
	 *          messaggio errore
	 */
	public AppException(Throwable cause, String msg) {
		super(cause, msg);
	}

	/**
	 * Costruttore che specifica exception causata da altra exception.
	 *
	 * @param cause
	 *          eccezzione che ha causato questa
	 * @param msg
	 *          messaggio errore
	 */
	public AppException(String msg, Throwable cause) {
		super(msg, cause);
	}

	/**
	 * Aggiunge un'exception fornendone il codice numerico senza di parametri.
	 *
	 * @param errCode
	 *          codice errore
	 */
	public AppException(Integer errCode) {
		super(errCode);
	}

	/**
	 * Aggiunge un'exception fornendone il codice numerico e un numero variabile
	 * di parametri.
	 *
	 * @param errCode
	 *          codice errore
	 * @param params
	 *          parametri di sostituzione per generare il testo del messaggio
	 *          finale
	 */
	public AppException(Integer errCode, Object... params) {
		super(errCode, params);
	}
}
