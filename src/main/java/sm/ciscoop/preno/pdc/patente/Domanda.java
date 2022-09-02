package sm.ciscoop.preno.pdc.patente;

import sm.ciscoop.pdc.*;
import sm.ciscoop.dmc.DMCDB;
import sm.ciscoop.dmc.DMCException;
import sm.ciscoop.stdlibs.baseutils.errors.MessHandler;
import sm.ciscoop.preno.pdc.AppPDC;

import sm.ciscoop.preno.pdc.patente.DomandaQuiz;
import sm.ciscoop.preno.pdc.patente.RispQuiz;
import sm.ciscoop.jbb.IJoinInfo;



/*-
 * Title:
 * Description:  Lista per Domanda
 * Copyright:    Copyright (c)
 * Company:      CIScoop
 * @author
 * @version 1.0
 *
 *

		USE [preno]
		GO
		SET ANSI_NULLS ON
		GO
		SET QUOTED_IDENTIFIER ON
		GO
		SET ANSI_PADDING ON
		GO
		CREATE TABLE [dbo].[ESA_Domanda](
		[IdDomanda] [int] IDENTITY(1,1) ,
		[IdCapitolo] [int] DEFAULT 0 NOT NULL ,
		[IdDom] [int] DEFAULT 0 NOT NULL ,
		[IdProgr] [int] DEFAULT 0 NOT NULL ,
		[Asserzione] [VARCHAR](200) DEFAULT '' NOT NULL ,
		[Valore] [int] DEFAULT 0 NOT NULL ,
		[linkimg] [VARCHAR](100) DEFAULT '' NOT NULL ,
		
		[ultagg] [datetime] DEFAULT (getdate()) NOT NULL,
		[utente] [varchar](20) DEFAULT '' NOT NULL,
		CONSTRAINT [PK_q_quiz_1] PRIMARY KEY CLUSTERED
		(
		
			[IdDomanda] ASC
		) WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]

		) ON [PRIMARY]
		GO

*
*/
public class Domanda extends AppPDC {

  private static final long serialVersionUID = 1L;

  /** Nome predefinito di questo PDC */
  public static final String CSZ_PDCName = "Domanda";

  /** tabella del database associata a questo PDC */
  public static final String CSZ_DBTable = "ESA_Domanda";

  // Campi chiave 
  /**  */
  public static final String CSZcol_IdDomanda = "IdDomanda";
  public static final String CSZds_IdDomanda = "Id Domanda";
  private IntAttr   c_IdDomanda;
 	
  

  // Campi non chiave 
  /**  */
  public static final String CSZcol_IdCapitolo = "IdCapitolo";
  public static final String CSZds_IdCapitolo = "Id Capitolo";
  private IntAttr   c_IdCapitolo;
 	
  
  /**  */
  public static final String CSZcol_IdDom = "IdDom";
  public static final String CSZds_IdDom = "Id Domanda";
  private IntAttr   c_IdDom;
 	
  
  /**  */
  public static final String CSZcol_IdProgr = "IdProgr";
  public static final String CSZds_IdProgr = "Idprogr";
  private IntAttr   c_IdProgr;
 	
  
  /**  */
  public static final String CSZcol_Asserzione = "Asserzione";
  public static final String CSZds_Asserzione = "Asserzione";
  public static final Integer CSZcol_Asserzione_len = 200;
  private CharAttr  c_Asserzione;
 	
  
  /** valore della domana. -1 (vera) 0 falsa */
  public static final String CSZcol_Valore = "Valore";
  public static final String CSZds_Valore = "";
  private BoolAttr  c_Valore;
 	
  
  /** immagine segnale */
  public static final String CSZcol_linkimg = "linkimg";
  public static final String CSZds_linkimg = "linkimg";
  public static final Integer CSZcol_linkimg_len = 100;
  private CharAttr  c_linkimg;
 	
  
  // =============================================================
  // Relazioni
  
  /** rel Rel-Istanza con preno.pdc.patente.DomandaQuiz */
  public static final String CSZrel_DomandaQuiz = "Domanda_DomandaQuiz";
  
  /** rel Rel-Istanza con preno.pdc.patente.RispQuiz */
  public static final String CSZrel_RispQuiz = "Domanda_RispQuiz";
  
  // =============================================================


  /**
   * Costruttore di default.
   */
  public Domanda()  throws Exception {
    super(CSZ_PDCName);
  }

  /**
   * Costruttore con message handler.

   * @param msh message handler da usare per la messaggistica del PDC.
   */
  public Domanda(MessHandler msh) throws Exception {
    super(msh, CSZ_PDCName);
  }

  /**
   * Costruttore con nome PDC.
   * Normalmente si usa come nome la costante CSZ_PDCName, ma
   * puo' essere specificato qui un nome diverso.
   *
   * @param nomeOgg nome PDC
   * @see #CSZ_PDCName
   */
  public Domanda(String nomeOgg) throws Exception {
    super(nomeOgg);
  }

  /**
   * Costruttore con message handler e nome PDC.
   *
   * @param msh message handler da usare per la messaggistica del PDC.
   * @param nomeOgg nome PDC
   *
   * @see #CSZ_PDCName
   * @see #Domanda(String)
   * @see #Domanda(MessHandler)
   */
  public Domanda(MessHandler msh, String nomeOgg) throws Exception {
    super(msh, nomeOgg);
  }

  /**
   * Costruttore con PDC di appoggio e nome.
   * Il PDC viene creato utilizzando il message handler del PDC passato e utilizzando
   * il database associato al DMC di pdcAppoggio per creare un nuovo DMC da associare a questo PDC.
   *
   * @param pdcAppoggio PDC di appoggio da utilizzare per creare questo PDC
   * @param nomeOgg nome PDC da creare
   * @see #Domanda(PDC)
   */
  public Domanda(PDC pdcAppoggio, String nomeOgg) throws Exception {
    super(pdcAppoggio, nomeOgg);
  }

  /**
   * Costruttore con PDC di appoggio.
   * Il PDC viene creato utilizzando il message handler del PDC passato e utilizzando
   * il database associato al DMC di pdcAppoggio per creare un nuovo DMC da associare a questo PDC.
   * Il nome del PDC utilizzato è quello di default (CSZ_PDCName).
   *
   * @param pdcAppoggio PDC di appoggio da utilizzare per creare questo PDC
   * @param nomeOgg nome PDC da creare
   * @see #Domanda(PDC,String)
   * @see #CSZ_PDCName
   */
  public Domanda(PDC pdcAppoggio) throws Exception {
    super(pdcAppoggio, CSZ_PDCName);
  }

  /**
   * Nome tabella di appartenenza.
   *
   * Serve normalmente nei PDC in join multi-tabella. Nel caso che il DMC non
   * sia su database (come di solito) ma su file o altro supporto di
   * memorizzazione, tale stringa identificherà la risorsa che il DMC deve usare
   * per salvare il PDC.
   *
   * @return String tabella di appartenenza
   */
  @Override
  public String getTable() {
    return CSZ_DBTable;
  }

  /**
   * Crea e Inizializza gli attributi di questo PDC.
   */
  @Override
  public boolean initAttrs() throws Exception {
    if (!super.initAttrs()) {
      return false;
    }

    Attributi attr = getAttributi();
    
    // IdDomanda
    c_IdDomanda = attr.addIntAttr(CSZcol_IdDomanda, CSZds_IdDomanda, CSZ_DBTable, true);
    c_IdDomanda.setSerial(true);

    
    // IdCapitolo
    c_IdCapitolo = attr.addIntAttr(CSZcol_IdCapitolo, CSZds_IdCapitolo, CSZ_DBTable, false);


    
    // IdDom
    c_IdDom = attr.addIntAttr(CSZcol_IdDom, CSZds_IdDom, CSZ_DBTable, false);


    
    // IdProgr
    c_IdProgr = attr.addIntAttr(CSZcol_IdProgr, CSZds_IdProgr, CSZ_DBTable, false);


    
    // Asserzione
    c_Asserzione = attr.addCharAttr(CSZcol_Asserzione, CSZds_Asserzione, CSZ_DBTable, false);
    c_Asserzione.setMaxLen(CSZcol_Asserzione_len); 

    
    // Valore
    c_Valore = attr.addBoolAttr(CSZcol_Valore, CSZds_Valore, CSZ_DBTable, false);
   c_Valore.setUseDefaultIfNull(true);

    
    // linkimg
    c_linkimg = attr.addCharAttr(CSZcol_linkimg, CSZds_linkimg, CSZ_DBTable, false);
    c_linkimg.setMaxLen(CSZcol_linkimg_len); 

    
    return true;
  }

  


  /**
   * Crea e Inizializza le relazioni di questo PDC.
   */
  @Override
  public boolean initRelazione() throws Exception {
    if (!super.initRelazione()) {
      return false;
    }
    
    Relazione rel;

   
      // rel DomandaQuiz
      rel = addRelazione(CSZrel_DomandaQuiz, Relazione.RELAZ_N, DomandaQuiz.class.getName());
      rel.setEnabled(false);
      rel.setJoinType(IJoinInfo.LEFT_OUTER_JOIN);
	  rel.addJoinLink(this.CSZcol_IdDomanda,DomandaQuiz.CSZcol_IdDomanda);
      rel.setHasIntegritaReferenziale(true);
      

      
      // rel RispQuiz
      rel = addRelazione(CSZrel_RispQuiz, Relazione.RELAZ_N, RispQuiz.class.getName());
      rel.setEnabled(false);
      rel.setJoinType(IJoinInfo.LEFT_OUTER_JOIN);
	  rel.addJoinLink(this.CSZcol_IdDomanda,RispQuiz.CSZcol_IdDomanda);
      rel.setHasIntegritaReferenziale(true);
      

      
    return true;
  }

  
	public Integer getIdDomanda() {
		return c_IdDomanda.getValue();
	}
	public void setIdDomanda(Integer val) {
		c_IdDomanda.setValue(val);
	}
	public IntAttr getIdDomanda_attr() {
		return c_IdDomanda;
	}

	public Integer getIdCapitolo() {
		return c_IdCapitolo.getValue();
	}
	public void setIdCapitolo(Integer val) {
		c_IdCapitolo.setValue(val);
	}
	public IntAttr getIdCapitolo_attr() {
		return c_IdCapitolo;
	}

	public Integer getIdDom() {
		return c_IdDom.getValue();
	}
	public void setIdDom(Integer val) {
		c_IdDom.setValue(val);
	}
	public IntAttr getIdDom_attr() {
		return c_IdDom;
	}

	public Integer getIdProgr() {
		return c_IdProgr.getValue();
	}
	public void setIdProgr(Integer val) {
		c_IdProgr.setValue(val);
	}
	public IntAttr getIdProgr_attr() {
		return c_IdProgr;
	}

	public String getAsserzione() {
		return c_Asserzione.getValue();
	}
	public void setAsserzione(String val) {
		c_Asserzione.setValue(val);
	}
	public CharAttr getAsserzione_attr() {
		return c_Asserzione;
	}

	public Boolean getValore() {
		return c_Valore.getValue();
	}
	public void setValore(Boolean val) {
		c_Valore.setValue(val);
	}
	public BoolAttr getValore_attr() {
		return c_Valore;
	}

	public String getlinkimg() {
		return c_linkimg.getValue();
	}
	public void setlinkimg(String val) {
		c_linkimg.setValue(val);
	}
	public CharAttr getlinkimg_attr() {
		return c_linkimg;
	}

  /**
   * Ripristina il pdc da DMCDB.

   * @param i campi chiave
   * @return boolean
   * il metodo ritorna true se viene trovato uno ed un singolo record
   */
  public boolean ripristina(IntAttr idDomanda, DMCDB dmco) throws Exception {
   
    c_IdDomanda.setValue(idDomanda.getValue());
    return ripristina(dmco);
  }

  /**
   * Metodo per la validazione di questo PDC.
   *  
   *
   * @return true se operazione effettuata con successo, false altrimenti
   */
   @Override
  public boolean validatePDCSpec() throws Exception {
    boolean bOK = super.validatePDCSpec();
    if (bOK) {
      // logica  validatePDCSpec
    }
    return bOK;
  }
}
