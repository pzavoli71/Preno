package sm.ciscoop.preno.pdc.patente;

import sm.ciscoop.pdc.*;
import sm.ciscoop.dmc.DMCDB;
import sm.ciscoop.dmc.DMCException;
import sm.ciscoop.stdlibs.baseutils.errors.MessHandler;
import sm.ciscoop.preno.pdc.AppPDC;

import sm.ciscoop.preno.pdc.patente.Quiz;
import sm.ciscoop.preno.pdc.patente.Domanda;
import sm.ciscoop.preno.pdc.patente.RispQuiz;
import sm.ciscoop.jbb.IJoinInfo;



/*-
 * Title:
 * Description:  Lista per DomandaQuiz
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
		CREATE TABLE [dbo].[ESA_DomandaQuiz](
		[IdQuiz] [int] DEFAULT 0 NOT NULL ,
		[IdDomanda] [int] DEFAULT 0 NOT NULL ,
		[IdDomandaTest] [int] IDENTITY(1,1) ,
		
		[ultagg] [datetime] DEFAULT (getdate()) NOT NULL,
		[utente] [varchar](20) DEFAULT '' NOT NULL,
		CONSTRAINT [PK_ESA_DomandaQuiz_1] PRIMARY KEY CLUSTERED
		(
		
			[IdDomandaTest] ASC
		) WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]

		) ON [PRIMARY]
		GO

*
*/
public class DomandaQuiz extends AppPDC {

  private static final long serialVersionUID = 1L;

  /** Nome predefinito di questo PDC */
  public static final String CSZ_PDCName = "DomandaQuiz";

  /** tabella del database associata a questo PDC */
  public static final String CSZ_DBTable = "ESA_DomandaQuiz";

  // Campi chiave 
  /**  */
  public static final String CSZcol_IdDomandaTest = "IdDomandaTest";
  public static final String CSZds_IdDomandaTest = "Id DomandaTest";
  private IntAttr   c_IdDomandaTest;
 	
  

  // Campi non chiave 
  /**  */
  public static final String CSZcol_IdQuiz = "IdQuiz";
  public static final String CSZds_IdQuiz = "Id Test";
  private IntAttr   c_IdQuiz;
 	
  
  /**  */
  public static final String CSZcol_IdDomanda = "IdDomanda";
  public static final String CSZds_IdDomanda = "Id Domanda";
  private IntAttr   c_IdDomanda;
 	
  
  // =============================================================
  // Relazioni
  
  /** rel Rel-TuttoParti con preno.pdc.patente.Quiz */
  public static final String CSZrel_Quiz = "DomandaQuiz_Quiz";
  
  /** rel Rel-Istanza con preno.pdc.patente.Domanda */
  public static final String CSZrel_Domanda = "DomandaQuiz_Domanda";
  
  /** rel Rel-Istanza con preno.pdc.patente.RispQuiz */
  public static final String CSZrel_RispQuiz = "DomandaQuiz_RispQuiz";
  
  // =============================================================


  /**
   * Costruttore di default.
   */
  public DomandaQuiz()  throws Exception {
    super(CSZ_PDCName);
  }

  /**
   * Costruttore con message handler.

   * @param msh message handler da usare per la messaggistica del PDC.
   */
  public DomandaQuiz(MessHandler msh) throws Exception {
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
  public DomandaQuiz(String nomeOgg) throws Exception {
    super(nomeOgg);
  }

  /**
   * Costruttore con message handler e nome PDC.
   *
   * @param msh message handler da usare per la messaggistica del PDC.
   * @param nomeOgg nome PDC
   *
   * @see #CSZ_PDCName
   * @see #DomandaQuiz(String)
   * @see #DomandaQuiz(MessHandler)
   */
  public DomandaQuiz(MessHandler msh, String nomeOgg) throws Exception {
    super(msh, nomeOgg);
  }

  /**
   * Costruttore con PDC di appoggio e nome.
   * Il PDC viene creato utilizzando il message handler del PDC passato e utilizzando
   * il database associato al DMC di pdcAppoggio per creare un nuovo DMC da associare a questo PDC.
   *
   * @param pdcAppoggio PDC di appoggio da utilizzare per creare questo PDC
   * @param nomeOgg nome PDC da creare
   * @see #DomandaQuiz(PDC)
   */
  public DomandaQuiz(PDC pdcAppoggio, String nomeOgg) throws Exception {
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
   * @see #DomandaQuiz(PDC,String)
   * @see #CSZ_PDCName
   */
  public DomandaQuiz(PDC pdcAppoggio) throws Exception {
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
    
    // IdDomandaTest
    c_IdDomandaTest = attr.addIntAttr(CSZcol_IdDomandaTest, CSZds_IdDomandaTest, CSZ_DBTable, true);
    c_IdDomandaTest.setSerial(true);

    
    // IdQuiz
    c_IdQuiz = attr.addIntAttr(CSZcol_IdQuiz, CSZds_IdQuiz, CSZ_DBTable, false);


    
    // IdDomanda
    c_IdDomanda = attr.addIntAttr(CSZcol_IdDomanda, CSZds_IdDomanda, CSZ_DBTable, false);


    
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

   
      // rel Quiz
      rel = addRelazione(CSZrel_Quiz, Relazione.RELAZ_1, Quiz.class.getName());
      rel.setEnabled(false);
      

      
      // rel Domanda
      rel = addRelazione(CSZrel_Domanda, Relazione.RELAZ_1, Domanda.class.getName());
      rel.setEnabled(false);
      

      
      // rel RispQuiz
      rel = addRelazione(CSZrel_RispQuiz, Relazione.RELAZ_N, RispQuiz.class.getName());
      rel.setEnabled(false);
      rel.setJoinType(IJoinInfo.LEFT_OUTER_JOIN);
	  rel.addJoinLink(this.CSZcol_IdDomandaTest,RispQuiz.CSZcol_IdDomandaTest);
      rel.setHasIntegritaReferenziale(true);
      

      
    return true;
  }

  
  /**
   * Ritorna il PDC Quiz collegato.
   * Utilizza la relazione per farsi dare il pdc collegato
   * Se e' il caso effettua il ripristino.
   *
   * @return Quiz il PDC collegato oppure null se non tutti i
   *                campi chiave sono valorizzati
   * @throws Exception in caso di errori in lettura da database
   */
  public Quiz getPDCQuiz() throws Exception {
    return (Quiz) loadPDCCollegato(CSZrel_Quiz, true);
  }
 
  /**
   * Ritorna il PDC Domanda collegato.
   * Utilizza la relazione per farsi dare il pdc collegato
   * Se e' il caso effettua il ripristino.
   *
   * @return Domanda il PDC collegato oppure null se non tutti i
   *                campi chiave sono valorizzati
   * @throws Exception in caso di errori in lettura da database
   */
  public Domanda getPDCDomanda() throws Exception {
    return (Domanda) loadPDCCollegato(CSZrel_Domanda, true);
  }
 
	public Integer getIdDomandaTest() {
		return c_IdDomandaTest.getValue();
	}
	public void setIdDomandaTest(Integer val) {
		c_IdDomandaTest.setValue(val);
	}
	public IntAttr getIdDomandaTest_attr() {
		return c_IdDomandaTest;
	}

	public Integer getIdQuiz() {
		return c_IdQuiz.getValue();
	}
	public void setIdQuiz(Integer val) {
		c_IdQuiz.setValue(val);
	}
	public IntAttr getIdQuiz_attr() {
		return c_IdQuiz;
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

  /**
   * Ripristina il pdc da DMCDB.

   * @param i campi chiave
   * @return boolean
   * il metodo ritorna true se viene trovato uno ed un singolo record
   */
  public boolean ripristina(IntAttr idDomandaTest, DMCDB dmco) throws Exception {
   
    c_IdDomandaTest.setValue(idDomandaTest.getValue());
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
