package sm.ciscoop.preno.pdc.patente;

import sm.ciscoop.pdc.*;
import sm.ciscoop.dmc.DMCDB;
import sm.ciscoop.dmc.DMCException;
import sm.ciscoop.stdlibs.baseutils.errors.MessHandler;
import sm.ciscoop.preno.pdc.AppPDC;

import sm.ciscoop.preno.pdc.patente.Domanda;
import sm.ciscoop.preno.pdc.patente.DomandaQuiz;
import sm.ciscoop.jbb.IJoinInfo;
import sm.ciscoop.jbb.JBB;



/*-
 * Title:
 * Description:  Lista per RispQuiz
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
		CREATE TABLE [dbo].[ESA_RispQuiz](
		[IdDomanda] [int] DEFAULT 0 NOT NULL ,
		[IdDomandaTest] [int] DEFAULT 0 NOT NULL ,
		[IdRispTest] [int] IDENTITY(1,1) ,
		[RespVero] [int] DEFAULT 0 NOT NULL ,
		[RespFalso] [int] DEFAULT 0 NOT NULL ,
		[bControllata] [int] DEFAULT 0 NOT NULL ,
		[EsitoRisp] [int] DEFAULT 0 NOT NULL ,
		
		[ultagg] [datetime] DEFAULT (getdate()) NOT NULL,
		[utente] [varchar](20) DEFAULT '' NOT NULL,
		CONSTRAINT [PK_ESA_RispQuiz_1] PRIMARY KEY CLUSTERED
		(
		
			[IdRispTest] ASC
		) WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]

		) ON [PRIMARY]
		GO

*
*/
public class RispQuiz extends AppPDC {

  private static final long serialVersionUID = 1L;

  /** Nome predefinito di questo PDC */
  public static final String CSZ_PDCName = "RispQuiz";

  /** tabella del database associata a questo PDC */
  public static final String CSZ_DBTable = "ESA_RispQuiz";

  // Campi chiave 
  /**  */
  public static final String CSZcol_IdRispTest = "IdRispTest";
  public static final String CSZds_IdRispTest = "Id RespTest";
  private IntAttr   c_IdRispTest;
 	
  

  // Campi non chiave 
  /**  */
  public static final String CSZcol_IdDomanda = "IdDomanda";
  public static final String CSZds_IdDomanda = "Id Domanda";
  private IntAttr   c_IdDomanda;
 	
  
  /**  */
  public static final String CSZcol_IdDomandaTest = "IdDomandaTest";
  public static final String CSZds_IdDomandaTest = "Id DomandaTest";
  private IntAttr   c_IdDomandaTest;
 	
  
  /**  */
  public static final String CSZcol_RespVero = "RespVero";
  public static final String CSZds_RespVero = "Resp Vero";
  private BoolAttr  c_RespVero;
 	
  
  /**  */
  public static final String CSZcol_RespFalso = "RespFalso";
  public static final String CSZds_RespFalso = "Resp Falso";
  private BoolAttr  c_RespFalso;
 	
  
  /**  */
  public static final String CSZcol_bControllata = "bControllata";
  public static final String CSZds_bControllata = "bControllata";
  private BoolAttr  c_bControllata;
 	
  
  /**  */
  public static final String CSZcol_EsitoRisp = "EsitoRisp";
  public static final String CSZds_EsitoRisp = "Esito Risp";
  private BoolAttr  c_EsitoRisp;
 	
  
  // =============================================================
  // Relazioni
  
  /** rel Rel-Istanza con preno.pdc.patente.Domanda */
  public static final String CSZrel_Domanda = "RispQuiz_Domanda";
  
  /** rel Rel-Istanza con preno.pdc.patente.DomandaQuiz */
  public static final String CSZrel_DomandaQuiz = "RispQuiz_DomandaQuiz";
  
  // =============================================================


  /**
   * Costruttore di default.
   */
  public RispQuiz()  throws Exception {
    super(CSZ_PDCName);
  }

  /**
   * Costruttore con message handler.

   * @param msh message handler da usare per la messaggistica del PDC.
   */
  public RispQuiz(MessHandler msh) throws Exception {
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
  public RispQuiz(String nomeOgg) throws Exception {
    super(nomeOgg);
  }

  /**
   * Costruttore con message handler e nome PDC.
   *
   * @param msh message handler da usare per la messaggistica del PDC.
   * @param nomeOgg nome PDC
   *
   * @see #CSZ_PDCName
   * @see #RispQuiz(String)
   * @see #RispQuiz(MessHandler)
   */
  public RispQuiz(MessHandler msh, String nomeOgg) throws Exception {
    super(msh, nomeOgg);
  }

  /**
   * Costruttore con PDC di appoggio e nome.
   * Il PDC viene creato utilizzando il message handler del PDC passato e utilizzando
   * il database associato al DMC di pdcAppoggio per creare un nuovo DMC da associare a questo PDC.
   *
   * @param pdcAppoggio PDC di appoggio da utilizzare per creare questo PDC
   * @param nomeOgg nome PDC da creare
   * @see #RispQuiz(PDC)
   */
  public RispQuiz(PDC pdcAppoggio, String nomeOgg) throws Exception {
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
   * @see #RispQuiz(PDC,String)
   * @see #CSZ_PDCName
   */
  public RispQuiz(PDC pdcAppoggio) throws Exception {
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
    
    // IdRispTest
    c_IdRispTest = attr.addIntAttr(CSZcol_IdRispTest, CSZds_IdRispTest, CSZ_DBTable, true);
    c_IdRispTest.setSerial(true);

    
    // IdDomanda
    c_IdDomanda = attr.addIntAttr(CSZcol_IdDomanda, CSZds_IdDomanda, CSZ_DBTable, false);


    
    // IdDomandaTest
    c_IdDomandaTest = attr.addIntAttr(CSZcol_IdDomandaTest, CSZds_IdDomandaTest, CSZ_DBTable, false);


    
    // RespVero
    c_RespVero = attr.addBoolAttr(CSZcol_RespVero, CSZds_RespVero, CSZ_DBTable, false);
   c_RespVero.setUseDefaultIfNull(true);

    
    // RespFalso
    c_RespFalso = attr.addBoolAttr(CSZcol_RespFalso, CSZds_RespFalso, CSZ_DBTable, false);
   c_RespFalso.setUseDefaultIfNull(true);

    
    // bControllata
    c_bControllata = attr.addBoolAttr(CSZcol_bControllata, CSZds_bControllata, CSZ_DBTable, false);
   c_bControllata.setUseDefaultIfNull(true);

    
    // EsitoRisp
    c_EsitoRisp = attr.addBoolAttr(CSZcol_EsitoRisp, CSZds_EsitoRisp, CSZ_DBTable, false);
   c_EsitoRisp.setUseDefaultIfNull(true);

    
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

   
      // rel Domanda
      rel = addRelazione(CSZrel_Domanda, Relazione.RELAZ_1, Domanda.class.getName());
      rel.addAlias("IdDomanda", "IdDomandaRisp");
      rel.setDxTableAlias("DomandaRisp");
      rel.setEnabled(false);
      

      
      // rel DomandaQuiz
      rel = addRelazione(CSZrel_DomandaQuiz, Relazione.RELAZ_1, DomandaQuiz.class.getName());
      rel.setEnabled(false);
      

      
    return true;
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
 
  /**
   * Ritorna il PDC DomandaQuiz collegato.
   * Utilizza la relazione per farsi dare il pdc collegato
   * Se e' il caso effettua il ripristino.
   *
   * @return DomandaQuiz il PDC collegato oppure null se non tutti i
   *                campi chiave sono valorizzati
   * @throws Exception in caso di errori in lettura da database
   */
  public DomandaQuiz getPDCDomandaQuiz() throws Exception {
    return (DomandaQuiz) loadPDCCollegato(CSZrel_DomandaQuiz, true);
  }
 
	public Integer getIdRispTest() {
		return c_IdRispTest.getValue();
	}
	public void setIdRispTest(Integer val) {
		c_IdRispTest.setValue(val);
	}
	public IntAttr getIdRispTest_attr() {
		return c_IdRispTest;
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

	public Integer getIdDomandaTest() {
		return c_IdDomandaTest.getValue();
	}
	public void setIdDomandaTest(Integer val) {
		c_IdDomandaTest.setValue(val);
	}
	public IntAttr getIdDomandaTest_attr() {
		return c_IdDomandaTest;
	}

	public Boolean getRespVero() {
		return c_RespVero.getValue();
	}
	public void setRespVero(Boolean val) {
		c_RespVero.setValue(val);
	}
	public BoolAttr getRespVero_attr() {
		return c_RespVero;
	}

	public Boolean getRespFalso() {
		return c_RespFalso.getValue();
	}
	public void setRespFalso(Boolean val) {
		c_RespFalso.setValue(val);
	}
	public BoolAttr getRespFalso_attr() {
		return c_RespFalso;
	}

	public Boolean getbControllata() {
		return c_bControllata.getValue();
	}
	public void setbControllata(Boolean val) {
		c_bControllata.setValue(val);
	}
	public BoolAttr getbControllata_attr() {
		return c_bControllata;
	}

	public Boolean getEsitoRisp() {
		return c_EsitoRisp.getValue();
	}
	public void setEsitoRisp(Boolean val) {
		c_EsitoRisp.setValue(val);
	}
	public BoolAttr getEsitoRisp_attr() {
		return c_EsitoRisp;
	}

  /**
   * Ripristina il pdc da DMCDB.

   * @param i campi chiave
   * @return boolean
   * il metodo ritorna true se viene trovato uno ed un singolo record
   */
  public boolean ripristina(IntAttr idRispTest, DMCDB dmco) throws Exception {
   
    c_IdRispTest.setValue(idRispTest.getValue());
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
      if ( getRespFalso() && getRespVero()) {
    	  addMessageError("Devi scegliere solo una risposta");
    	  return false;
      }
      if ( isInInserimento())
    	  return true;
      JBB jbb = getJBB();
      String ls = "select count(*) FROM " + Quiz.CSZ_DBTable + " q INNER JOIN " + DomandaQuiz.CSZ_DBTable + " d ON ";
      ls += " d.IdQuiz = q.IdQuiz WHERE d.IdDomandaTest = " + getIdDomandaTest();
      ls += " AND (q.DtFineTest IS NOT NULL or q.dtiniziotest is null)";
      if ( jbb.getCount(ls) > 0 ) {
    	  addMessageError("Il test è già terminato. Impossibile aggiornare la risposta.");
    	  return false;
      }
    }
    return bOK;
  }
}
