package sm.ciscoop.preno.pdc.busy;

import sm.ciscoop.pdc.*;
import sm.ciscoop.dmc.DMCDB;
import sm.ciscoop.dmc.DMCException;
import sm.ciscoop.stdlibs.baseutils.errors.MessHandler;
import sm.ciscoop.preno.pdc.AppPDC;

import sm.ciscoop.preno.pdc.busy.Obiettivo;
import sm.ciscoop.jbb.IJoinInfo;

import java.util.*;


/*-
 * Title:
 * Description:  Lista per Lavoro
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
		CREATE TABLE [dbo].[Lavoro](
		[IdObiettivo] [int] DEFAULT 0 NOT NULL ,
		[IdLavoro] [int] IDENTITY(1,1) ,
		[DtLavoro] [DateTime] ,
		[OraInizio] [int] DEFAULT 0 NOT NULL ,
		[MinutiInizio] [int] DEFAULT 0 NOT NULL ,
		[NotaLavoro] [VARCHAR](1000) DEFAULT '' NOT NULL ,
		[OraFine] [int] DEFAULT 0 NOT NULL ,
		[MinutiFine] [int] DEFAULT 0 NOT NULL ,
		
		[ultagg] [datetime] DEFAULT (getdate()) NOT NULL,
		[utente] [varchar](20) DEFAULT '' NOT NULL,
		CONSTRAINT [PK_Lavoro_1] PRIMARY KEY CLUSTERED
		(
		
			[IdLavoro] ASC
		) WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]

		) ON [PRIMARY]
		GO

*
*/
public class Lavoro extends AppPDC {

  private static final long serialVersionUID = 1L;

  /** Nome predefinito di questo PDC */
  public static final String CSZ_PDCName = "Lavoro";

  /** tabella del database associata a questo PDC */
  public static final String CSZ_DBTable = "Lavoro";

  // Campi chiave 
  /**  */
  public static final String CSZcol_IdLavoro = "IdLavoro";
  public static final String CSZds_IdLavoro = "Id Lavoro";
  private IntAttr   c_IdLavoro;
 	
  

  // Campi non chiave 
  /**  */
  public static final String CSZcol_IdObiettivo = "IdObiettivo";
  public static final String CSZds_IdObiettivo = "Id Obiettivo";
  private IntAttr   c_IdObiettivo;
 	
  
  /**  */
  public static final String CSZcol_DtLavoro = "DtLavoro";
  public static final String CSZds_DtLavoro = "Dt Lavoro";
  private DateAttr  c_DtLavoro;
 	
  
  /**  */
  public static final String CSZcol_OraInizio = "OraInizio";
  public static final String CSZds_OraInizio = "Ora Inizio";
  private IntAttr   c_OraInizio;
 	
  
  /**  */
  public static final String CSZcol_MinutiInizio = "MinutiInizio";
  public static final String CSZds_MinutiInizio = "Minuti Inizio";
  private IntAttr   c_MinutiInizio;
 	
  
  /**  */
  public static final String CSZcol_NotaLavoro = "NotaLavoro";
  public static final String CSZds_NotaLavoro = "Nota Lavoro";
  public static final Integer CSZcol_NotaLavoro_len = 1000;
  private CharAttr  c_NotaLavoro;
 	
  
  /**  */
  public static final String CSZcol_OraFine = "OraFine";
  public static final String CSZds_OraFine = "Ora Fine";
  private IntAttr   c_OraFine;
 	
  
  /**  */
  public static final String CSZcol_MinutiFine = "MinutiFine";
  public static final String CSZds_MinutiFine = "Minuti Fine";
  private IntAttr   c_MinutiFine;
 	
  
  // =============================================================
  // Relazioni
  
  /** rel Rel-Istanza con preno.pdc.busy.Obiettivo */
  public static final String CSZrel_Obiettivo = "Lavoro_Obiettivo";
  
  // =============================================================


  /**
   * Costruttore di default.
   */
  public Lavoro()  throws Exception {
    super(CSZ_PDCName);
  }

  /**
   * Costruttore con message handler.

   * @param msh message handler da usare per la messaggistica del PDC.
   */
  public Lavoro(MessHandler msh) throws Exception {
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
  public Lavoro(String nomeOgg) throws Exception {
    super(nomeOgg);
  }

  /**
   * Costruttore con message handler e nome PDC.
   *
   * @param msh message handler da usare per la messaggistica del PDC.
   * @param nomeOgg nome PDC
   *
   * @see #CSZ_PDCName
   * @see #Lavoro(String)
   * @see #Lavoro(MessHandler)
   */
  public Lavoro(MessHandler msh, String nomeOgg) throws Exception {
    super(msh, nomeOgg);
  }

  /**
   * Costruttore con PDC di appoggio e nome.
   * Il PDC viene creato utilizzando il message handler del PDC passato e utilizzando
   * il database associato al DMC di pdcAppoggio per creare un nuovo DMC da associare a questo PDC.
   *
   * @param pdcAppoggio PDC di appoggio da utilizzare per creare questo PDC
   * @param nomeOgg nome PDC da creare
   * @see #Lavoro(PDC)
   */
  public Lavoro(PDC pdcAppoggio, String nomeOgg) throws Exception {
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
   * @see #Lavoro(PDC,String)
   * @see #CSZ_PDCName
   */
  public Lavoro(PDC pdcAppoggio) throws Exception {
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
    
    // IdLavoro
    c_IdLavoro = attr.addIntAttr(CSZcol_IdLavoro, CSZds_IdLavoro, CSZ_DBTable, true);
    c_IdLavoro.setSerial(true);

    
    // IdObiettivo
    c_IdObiettivo = attr.addIntAttr(CSZcol_IdObiettivo, CSZds_IdObiettivo, CSZ_DBTable, false);


    
    // DtLavoro
    c_DtLavoro = attr.addDateAttr(CSZcol_DtLavoro, CSZds_DtLavoro, CSZ_DBTable, false);


    
    // OraInizio
    c_OraInizio = attr.addIntAttr(CSZcol_OraInizio, CSZds_OraInizio, CSZ_DBTable, false);


    
    // MinutiInizio
    c_MinutiInizio = attr.addIntAttr(CSZcol_MinutiInizio, CSZds_MinutiInizio, CSZ_DBTable, false);


    
    // NotaLavoro
    c_NotaLavoro = attr.addCharAttr(CSZcol_NotaLavoro, CSZds_NotaLavoro, CSZ_DBTable, false);
    c_NotaLavoro.setMaxLen(CSZcol_NotaLavoro_len); 

    
    // OraFine
    c_OraFine = attr.addIntAttr(CSZcol_OraFine, CSZds_OraFine, CSZ_DBTable, false);


    
    // MinutiFine
    c_MinutiFine = attr.addIntAttr(CSZcol_MinutiFine, CSZds_MinutiFine, CSZ_DBTable, false);


    
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

   
      // rel Obiettivo
      rel = addRelazione(CSZrel_Obiettivo, Relazione.RELAZ_1, Obiettivo.class.getName());
      rel.setEnabled(false);
      

      
    return true;
  }

  
  /**
   * Ritorna il PDC Obiettivo collegato.
   * Utilizza la relazione per farsi dare il pdc collegato
   * Se e' il caso effettua il ripristino.
   *
   * @return Obiettivo il PDC collegato oppure null se non tutti i
   *                campi chiave sono valorizzati
   * @throws Exception in caso di errori in lettura da database
   */
  public Obiettivo getPDCObiettivo() throws Exception {
    return (Obiettivo) loadPDCCollegato(CSZrel_Obiettivo, true);
  }
 
	public Integer getIdLavoro() {
		return c_IdLavoro.getValue();
	}
	public void setIdLavoro(Integer val) {
		c_IdLavoro.setValue(val);
	}
	public IntAttr getIdLavoro_attr() {
		return c_IdLavoro;
	}

	public Integer getIdObiettivo() {
		return c_IdObiettivo.getValue();
	}
	public void setIdObiettivo(Integer val) {
		c_IdObiettivo.setValue(val);
	}
	public IntAttr getIdObiettivo_attr() {
		return c_IdObiettivo;
	}

	public Date getDtLavoro() {
		return c_DtLavoro.getValue();
	}
	public void setDtLavoro(Date val) {
		c_DtLavoro.setValue(val);
	}
	public DateAttr getDtLavoro_attr() {
		return c_DtLavoro;
	}

	public Integer getOraInizio() {
		return c_OraInizio.getValue();
	}
	public void setOraInizio(Integer val) {
		c_OraInizio.setValue(val);
	}
	public IntAttr getOraInizio_attr() {
		return c_OraInizio;
	}

	public Integer getMinutiInizio() {
		return c_MinutiInizio.getValue();
	}
	public void setMinutiInizio(Integer val) {
		c_MinutiInizio.setValue(val);
	}
	public IntAttr getMinutiInizio_attr() {
		return c_MinutiInizio;
	}

	public String getNotaLavoro() {
		return c_NotaLavoro.getValue();
	}
	public void setNotaLavoro(String val) {
		c_NotaLavoro.setValue(val);
	}
	public CharAttr getNotaLavoro_attr() {
		return c_NotaLavoro;
	}

	public Integer getOraFine() {
		return c_OraFine.getValue();
	}
	public void setOraFine(Integer val) {
		c_OraFine.setValue(val);
	}
	public IntAttr getOraFine_attr() {
		return c_OraFine;
	}

	public Integer getMinutiFine() {
		return c_MinutiFine.getValue();
	}
	public void setMinutiFine(Integer val) {
		c_MinutiFine.setValue(val);
	}
	public IntAttr getMinutiFine_attr() {
		return c_MinutiFine;
	}

  /**
   * Ripristina il pdc da DMCDB.

   * @param i campi chiave
   * @return boolean
   * il metodo ritorna true se viene trovato uno ed un singolo record
   */
  public boolean ripristina(IntAttr idLavoro, DMCDB dmco) throws Exception {
   
    c_IdLavoro.setValue(idLavoro.getValue());
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
