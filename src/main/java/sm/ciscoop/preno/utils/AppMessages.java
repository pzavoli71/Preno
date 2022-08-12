package sm.ciscoop.preno.utils;


/**
 * Classe dei messaggi di errore dell'applicativo, eventualmente organizzati in sottoenumerati 
 *
 */
public class AppMessages {
  public enum Generici {
    /** 10001: Errore in ripristina: $ */
    RIPRISTINA(10001)
    /** 10002: Errore imprevisto in $. Eccezione: <var>$</var> */
    , ECCEZIONE(10002)
    /** 10003: Errore grave in $: avvisare responsabile. */
    , ERRGRAVE(10003)
    /** 10004: Errore imprevisto: $ */
    , ERRIMP(10004);

    private int code;

    private Generici(int v) {
      code = v;
    }

    /**
     * @return Ritorna il codice numerico dell'errore
     */
    public int code() {
      return code;
    }
  }
//  public enum Lista {
//    ;
//    private int code;
//
//    private Lista(int v) {
//      code = v;
//    }
//
//    /**
//     * @return Ritorna il codice numerico dell'errore
//     */
//    public int code() {
//      return code;
//    }
//
//  }
//
//  public enum Ricerca {
//    ;
//    private int code;
//
//    private Ricerca(int v) {
//      code = v;
//    }
//
//    /**
//     * @return Ritorna il codice numerico dell'errore
//     */
//    public int code() {
//      return code;
//    }
//
//  }
}
