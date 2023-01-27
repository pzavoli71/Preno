package sm.ciscoop.preno.pdc.busy;

import sm.ciscoop.pdc.*;
import sm.ciscoop.dmc.DMCDB;
import sm.ciscoop.dmc.DMCException;
import sm.ciscoop.stdlibs.baseutils.errors.MessHandler;
import sm.ciscoop.preno.pdc.AppPDC;

import sm.ciscoop.preno.pdc.busy.Obiettivo;
import sm.ciscoop.jbb.IJoinInfo;


import sm.ciscoop.stdlibs.baseutils.format.DateTimeFormatter;
import sm.ciscoop.stdlibs.baseutils.format.IntervalParts;
import java.util.Date;


/*-
 * Title:
 * Description:  Lista per DocObiettivo
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
		CREATE TABLE [dbo].[DocObiettivo](
		[IdObiettivo] [int] DEFAULT 0 NOT NULL ,
		[IdDocObiettivo] [int] IDENTITY(1,1) ,
		[DtDoc] [DateTime] ,
		[PathDoc] [VARCHAR](1000) DEFAULT '' NOT NULL ,
		[NotaDoc] [VARCHAR](2000) DEFAULT '' NOT NULL ,
		
		[ultagg] [datetime] DEFAULT (getdate()) NOT NULL,
		[utente] [varchar](20) DEFAULT '' NOT NULL,
		CONSTRAINT [PK_DocObiettivo_1] PRIMARY KEY CLUSTERED
		(
		
			[IdDocObiettivo] ASC
		) WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]

		) ON [PRIMARY]
		GO

*
*/
public class DocObiettivo extends AppPDC {

  private static final long serialVersionUID = 1L;

  /** Nome predefinito di questo PDC */
  public static final String CSZ_PDCName = "DocObiettivo";

  /** tabella del database associata a questo PDC */
  public static final String CSZ_DBTable = "DocObiettivo";

  // Campi chiave 
  /**  */
  public static final String CSZcol_IdDocObiettivo = "IdDocObiettivo";
  public static final String CSZds_IdDocObiettivo = "Id DocObiettivo";
  private IntAttr   c_IdDocObiettivo;
 	
  

  // Campi non chiave 
  /**  */
  public static final String CSZcol_IdObiettivo = "IdObiettivo";
  public static final String CSZds_IdObiettivo = "Id Obiettivo";
  private IntAttr   c_IdObiettivo;
 	
  
  /**  */
  public static final String CSZcol_DtDoc = "DtDoc";
  public static final String CSZds_DtDoc = "Dt Doc";
  private DateTimeAttr  c_DtDoc;
 	
  
  /**  */
  public static final String CSZcol_PathDoc = "PathDoc";
  public static final String CSZds_PathDoc = "Path Doc";
  public static final Integer CSZcol_PathDoc_len = 1000;
  private CharAttr  c_PathDoc;
 	
  
  /**  */
  public static final String CSZcol_NotaDoc = "NotaDoc";
  public static final String CSZds_NotaDoc = "Nota Doc";
  public static final Integer CSZcol_NotaDoc_len = 2000;
  private CharAttr  c_NotaDoc;
 	
  
  // =============================================================
  // Relazioni
  
  /** rel Rel-Istanza con preno.pdc.busy.Obiettivo */
  public static final String CSZrel_Obiettivo = "DocObiettivo_Obiettivo";
  
  // =============================================================


  /**
   * Costruttore di default.
   */
  public DocObiettivo()  throws Exception {
    super(CSZ_PDCName);
  }

  /**
   * Costruttore con message handler.

   * @param msh message handler da usare per la messaggistica del PDC.
   */
  public DocObiettivo(MessHandler msh) throws Exception {
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
  public DocObiettivo(String nomeOgg) throws Exception {
    super(nomeOgg);
  }

  /**
   * Costruttore con message handler e nome PDC.
   *
   * @param msh message handler da usare per la messaggistica del PDC.
   * @param nomeOgg nome PDC
   *
   * @see #CSZ_PDCName
   * @see #DocObiettivo(String)
   * @see #DocObiettivo(MessHandler)
   */
  public DocObiettivo(MessHandler msh, String nomeOgg) throws Exception {
    super(msh, nomeOgg);
  }

  /**
   * Costruttore con PDC di appoggio e nome.
   * Il PDC viene creato utilizzando il message handler del PDC passato e utilizzando
   * il database associato al DMC di pdcAppoggio per creare un nuovo DMC da associare a questo PDC.
   *
   * @param pdcAppoggio PDC di appoggio da utilizzare per creare questo PDC
   * @param nomeOgg nome PDC da creare
   * @see #DocObiettivo(PDC)
   */
  public DocObiettivo(PDC pdcAppoggio, String nomeOgg) throws Exception {
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
   * @see #DocObiettivo(PDC,String)
   * @see #CSZ_PDCName
   */
  public DocObiettivo(PDC pdcAppoggio) throws Exception {
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
    
    // IdDocObiettivo
    c_IdDocObiettivo = attr.addIntAttr(CSZcol_IdDocObiettivo, CSZds_IdDocObiettivo, CSZ_DBTable, true);
    c_IdDocObiettivo.setSerial(true);

    
    // IdObiettivo
    c_IdObiettivo = attr.addIntAttr(CSZcol_IdObiettivo, CSZds_IdObiettivo, CSZ_DBTable, false);


    
    // DtDoc
    c_DtDoc = attr.addDateTimeAttr(CSZcol_DtDoc, CSZds_DtDoc, CSZ_DBTable, false);
    ((DateTimeFormatter) (c_DtDoc.getFormatter())).setFrom("year");
    ((DateTimeFormatter) (c_DtDoc.getFormatter())).setTo("minute"); 

    
    // PathDoc
    c_PathDoc = attr.addCharAttr(CSZcol_PathDoc, CSZds_PathDoc, CSZ_DBTable, false);
    c_PathDoc.setMaxLen(CSZcol_PathDoc_len); 

    
    // NotaDoc
    c_NotaDoc = attr.addCharAttr(CSZcol_NotaDoc, CSZds_NotaDoc, CSZ_DBTable, false);
    c_NotaDoc.setMaxLen(CSZcol_NotaDoc_len); 

    
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
 
	public Integer getIdDocObiettivo() {
		return c_IdDocObiettivo.getValue();
	}
	public void setIdDocObiettivo(Integer val) {
		c_IdDocObiettivo.setValue(val);
	}
	public IntAttr getIdDocObiettivo_attr() {
		return c_IdDocObiettivo;
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

	public Date getDtDoc() {
		return c_DtDoc.getValue();
	}
	public void setDtDoc(Date val) {
		c_DtDoc.setValue(val);
	}
	public DateTimeAttr getDtDoc_attr() {
		return c_DtDoc;
	}

	public String getPathDoc() {
		return c_PathDoc.getValue();
	}
	public void setPathDoc(String val) {
		c_PathDoc.setValue(val);
	}
	public CharAttr getPathDoc_attr() {
		return c_PathDoc;
	}

	public String getNotaDoc() {
		return c_NotaDoc.getValue();
	}
	public void setNotaDoc(String val) {
		c_NotaDoc.setValue(val);
	}
	public CharAttr getNotaDoc_attr() {
		return c_NotaDoc;
	}

  /**
   * Ripristina il pdc da DMCDB.

   * @param i campi chiave
   * @return boolean
   * il metodo ritorna true se viene trovato uno ed un singolo record
   */
  public boolean ripristina(IntAttr idDocObiettivo, DMCDB dmco) throws Exception {
   
    c_IdDocObiettivo.setValue(idDocObiettivo.getValue());
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
