package sm.ciscoop.preno.pdc.patente;

import sm.ciscoop.pdc.*;
import sm.ciscoop.dmc.DMCDB;
import sm.ciscoop.dmc.DMCException;
import sm.ciscoop.stdlibs.baseutils.errors.MessHandler;
import sm.ciscoop.preno.pdc.AppPDC;

import sm.ciscoop.preno.pdc.patente.Quiz;
import sm.ciscoop.jbb.IJoinInfo;


import java.util.Date;


/*-
 * Title:
 * Description:  Lista per Test
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
		CREATE TABLE [dbo].[ESA_Test](
		[IdQuiz] [int] DEFAULT 0 NOT NULL ,
		[IdTest] [int] IDENTITY(1,1) ,
		[EsitoTest] [int] DEFAULT 0 NOT NULL ,
		[DtInizioTest] [DateTime] ,
		[DtFineTest] [DateTime] ,
		
		[ultagg] [datetime] DEFAULT (getdate()) NOT NULL,
		[utente] [varchar](20) DEFAULT '' NOT NULL,
		CONSTRAINT [PK_ESA_Test_1] PRIMARY KEY CLUSTERED
		(
		
			[IdTest] ASC
		) WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]

		) ON [PRIMARY]
		GO

*
*/
public class Test extends AppPDC {

  private static final long serialVersionUID = 1L;

  /** Nome predefinito di questo PDC */
  public static final String CSZ_PDCName = "Test";

  /** tabella del database associata a questo PDC */
  public static final String CSZ_DBTable = "ESA_Test";

  // Campi chiave 
  /**  */
  public static final String CSZcol_IdTest = "IdTest";
  public static final String CSZds_IdTest = "Id Test";
  private IntAttr   c_IdTest;
 	
  

  // Campi non chiave 
  /**  */
  public static final String CSZcol_IdQuiz = "IdQuiz";
  public static final String CSZds_IdQuiz = "Id Test";
  private IntAttr   c_IdQuiz;
 	
  
  /** Risposte Esatte */
  public static final String CSZcol_EsitoTest = "EsitoTest";
  public static final String CSZds_EsitoTest = "Esito Test";
  private IntAttr   c_EsitoTest;
 	
  
  /**  */
  public static final String CSZcol_DtInizioTest = "DtInizioTest";
  public static final String CSZds_DtInizioTest = "Dt InizioTest";
  private DateTimeAttr  c_DtInizioTest;
 	
  
  /**  */
  public static final String CSZcol_DtFineTest = "DtFineTest";
  public static final String CSZds_DtFineTest = "Dt FineTest";
  private DateTimeAttr  c_DtFineTest;
 	
  
  // =============================================================
  // Relazioni
  
  /** rel Rel-TuttoParti con preno.pdc.patente.Quiz */
  public static final String CSZrel_Quiz = "Test_Quiz";
  
  // =============================================================


  /**
   * Costruttore di default.
   */
  public Test()  throws Exception {
    super(CSZ_PDCName);
  }

  /**
   * Costruttore con message handler.

   * @param msh message handler da usare per la messaggistica del PDC.
   */
  public Test(MessHandler msh) throws Exception {
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
  public Test(String nomeOgg) throws Exception {
    super(nomeOgg);
  }

  /**
   * Costruttore con message handler e nome PDC.
   *
   * @param msh message handler da usare per la messaggistica del PDC.
   * @param nomeOgg nome PDC
   *
   * @see #CSZ_PDCName
   * @see #Test(String)
   * @see #Test(MessHandler)
   */
  public Test(MessHandler msh, String nomeOgg) throws Exception {
    super(msh, nomeOgg);
  }

  /**
   * Costruttore con PDC di appoggio e nome.
   * Il PDC viene creato utilizzando il message handler del PDC passato e utilizzando
   * il database associato al DMC di pdcAppoggio per creare un nuovo DMC da associare a questo PDC.
   *
   * @param pdcAppoggio PDC di appoggio da utilizzare per creare questo PDC
   * @param nomeOgg nome PDC da creare
   * @see #Test(PDC)
   */
  public Test(PDC pdcAppoggio, String nomeOgg) throws Exception {
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
   * @see #Test(PDC,String)
   * @see #CSZ_PDCName
   */
  public Test(PDC pdcAppoggio) throws Exception {
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
    
    // IdTest
    c_IdTest = attr.addIntAttr(CSZcol_IdTest, CSZds_IdTest, CSZ_DBTable, true);
    c_IdTest.setSerial(true);

    
    // IdQuiz
    c_IdQuiz = attr.addIntAttr(CSZcol_IdQuiz, CSZds_IdQuiz, CSZ_DBTable, false);


    
    // EsitoTest
    c_EsitoTest = attr.addIntAttr(CSZcol_EsitoTest, CSZds_EsitoTest, CSZ_DBTable, false);


    
    // DtInizioTest
    c_DtInizioTest = attr.addDateTimeAttr(CSZcol_DtInizioTest, CSZds_DtInizioTest, CSZ_DBTable, false);


    
    // DtFineTest
    c_DtFineTest = attr.addDateTimeAttr(CSZcol_DtFineTest, CSZds_DtFineTest, CSZ_DBTable, false);


    
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
 
	public Integer getIdTest() {
		return c_IdTest.getValue();
	}
	public void setIdTest(Integer val) {
		c_IdTest.setValue(val);
	}
	public IntAttr getIdTest_attr() {
		return c_IdTest;
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

	public Integer getEsitoTest() {
		return c_EsitoTest.getValue();
	}
	public void setEsitoTest(Integer val) {
		c_EsitoTest.setValue(val);
	}
	public IntAttr getEsitoTest_attr() {
		return c_EsitoTest;
	}

	public Date getDtInizioTest() {
		return c_DtInizioTest.getValue();
	}
	public void setDtInizioTest(Date val) {
		c_DtInizioTest.setValue(val);
	}
	public DateTimeAttr getDtInizioTest_attr() {
		return c_DtInizioTest;
	}

	public Date getDtFineTest() {
		return c_DtFineTest.getValue();
	}
	public void setDtFineTest(Date val) {
		c_DtFineTest.setValue(val);
	}
	public DateTimeAttr getDtFineTest_attr() {
		return c_DtFineTest;
	}

  /**
   * Ripristina il pdc da DMCDB.

   * @param i campi chiave
   * @return boolean
   * il metodo ritorna true se viene trovato uno ed un singolo record
   */
  public boolean ripristina(IntAttr idTest, DMCDB dmco) throws Exception {
   
    c_IdTest.setValue(idTest.getValue());
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
