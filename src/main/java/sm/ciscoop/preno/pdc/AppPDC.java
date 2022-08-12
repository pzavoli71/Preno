package sm.ciscoop.preno.pdc;


import sm.ciscoop.stdlibs.baseutils.errors.MessHandler;
import sm.ciscoop.pdc.PDC;
import sm.ciscoop.pdc.PDCStatus;


/**
 * Classe PDC dell'applicativo. Raccoglie i metodi comuni dei PDC
 * dell'applicativo
 *
 */
public class AppPDC extends PDC {

  private static final long  serialVersionUID = 1L;

  /** Nome predefinito di questo PDC */
  public static final String CSZ_PDCName      = "AppPDC";

  /**
   * Costruttore di default.
   */
  public AppPDC() throws Exception {
    super();
  }

  /**
   * Costruttore con nome PDC. Normalmente si usa come nome la costante
   * CSZ_PDCName, ma può essere specificato qui un nome diverso.
   *
   * @param nomeOgg
   *          nome PDC
   * @see #CSZ_PDCName
   */
  public AppPDC(String nome) throws Exception {
    super(nome);
  }

  /**
   * Costruttore con message handler.
   *
   * @param msh
   *          message handler da usare per la messaggistica del PDC.
   */
  public AppPDC(MessHandler msg) throws Exception {
    super(msg);
  }

  /**
   * Costruttore con message handler e nome PDC.
   *
   * @param msh
   *          message handler da usare per la messaggistica del PDC.
   * @param nomeOgg
   *          nome PDC
   *
   * @see #CSZ_PDCName
   * @see #Gruppo(String)
   * @see #Gruppo(MessHandler)
   */
  public AppPDC(MessHandler msg, String nome) throws Exception {
    super(msg, nome);
  }

  /**
   * Costruttore con PDC di appoggio e nome. Il PDC viene creato utilizzando il
   * message handler del PDC passato e utilizzando il database associato al DMC
   * di pdcAppoggio per creare un nuovo DMC da associare a questo PDC.
   *
   * @param pdcAppoggio
   *          PDC di appoggio da utilizzare per creare questo PDC
   * @param nomeOgg
   *          nome PDC da creare
   * @see #Gruppo(PDC)
   */
  public AppPDC(PDC pdcAppoggio) throws Exception {
    super(pdcAppoggio.getMsgHandler());
  }

  /**
   * Costruttore con PDC di appoggio. Il PDC viene creato utilizzando il message
   * handler del PDC passato e utilizzando il database associato al DMC di
   * pdcAppoggio per creare un nuovo DMC da associare a questo PDC. Il nome del
   * PDC utilizzato è quello di default (CSZ_PDCName).
   *
   * @param pdcAppoggio
   *          PDC di appoggio da utilizzare per creare questo PDC
   * @param nomeOgg
   *          nome PDC da creare
   * @see #Gruppo(PDC,String)
   * @see #CSZ_PDCName
   */
  public AppPDC(PDC pdcAppoggio, String nomeOgg) throws Exception {
    super(pdcAppoggio.getMsgHandler(), nomeOgg);
  }

	public boolean isInInserimento() {
		return getStatus() == PDCStatus.INSERT || getStatus() == PDCStatus.INSERTED;
	}
  
}
