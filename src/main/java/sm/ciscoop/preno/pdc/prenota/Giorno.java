package sm.ciscoop.preno.pdc.prenota;

import sm.ciscoop.pdc.*;
import sm.ciscoop.dmc.DMCDB;
import sm.ciscoop.dmc.DMCException;
import sm.ciscoop.preno.pdc.AppPDC;

import sm.ciscoop.preno.pdc.prenota.SoggAR;
import sm.ciscoop.preno.pdc.prenota.TipoTrasporto;
import sm.ciscoop.stdlibs.baseutils.errors.MessHandler;
import sm.ciscoop.stdlibs.baseutils.types.DtUtil;
import sm.ciscoop.jbb.IJoinInfo;

import java.util.*;


/*-
 * Title:
 * Description:  Lista per Giorno
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
		CREATE TABLE [dbo].[Giorno](
		[IdTpTrasporto] [int] DEFAULT 0 NOT NULL ,
		[IdGiorno] [int] IDENTITY(1,1) ,
		[DtGiorno] [DateTime] ,
		[Destinazione] [VARCHAR](100) DEFAULT '' NOT NULL ,
		[NotaGiorno] [VARCHAR](1000) DEFAULT '' NOT NULL ,
		
		[ultagg] [datetime] DEFAULT (getdate()) NOT NULL,
		[utente] [varchar](20) DEFAULT '' NOT NULL,
		CONSTRAINT [PK_Giorno_1] PRIMARY KEY CLUSTERED
		(
		
			[IdGiorno] ASC
		) WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]

		) ON [PRIMARY]
		GO

*
*/
public class Giorno extends AppPDC {

  private static final long serialVersionUID = 1L;

  /** Nome predefinito di questo PDC */
  public static final String CSZ_PDCName = "Giorno";

  /** tabella del database associata a questo PDC */
  public static final String CSZ_DBTable = "Giorno";

  // Campi chiave 
  /**  */
  public static final String CSZcol_IdGiorno = "IdGiorno";
  public static final String CSZds_IdGiorno = "Id Giorno";
  private IntAttr   c_IdGiorno;
 	
  

  // Campi non chiave 
  /**  */
  public static final String CSZcol_IdTpTrasporto = "IdTpTrasporto";
  public static final String CSZds_IdTpTrasporto = "Id TpTrasporto";
  private IntAttr   c_IdTpTrasporto;
 	
  
  /**  */
  public static final String CSZcol_DtGiorno = "DtGiorno";
  public static final String CSZds_DtGiorno = "Giorno";
  private DateAttr  c_DtGiorno;
 	
  
  /**  */
  public static final String CSZcol_NumGiorno = "NumGiorno";
  public static final String CSZds_NumGiorno = "Num Giorno";
  private IntAttr   c_NumGiorno;
 	
  
  /**  */
  public static final String CSZcol_Destinazione = "Destinazione";
  public static final String CSZds_Destinazione = "Destinazione";
  public static final Integer CSZcol_Destinazione_len = 100;
  private CharAttr  c_Destinazione;
 	
  
  /**  */
  public static final String CSZcol_NotaGiorno = "NotaGiorno";
  public static final String CSZds_NotaGiorno = "Nota Giorno";
  public static final Integer CSZcol_NotaGiorno_len = 1000;
  private CharAttr  c_NotaGiorno;
 	
  
  // =============================================================
  // Relazioni
  
  /** rel Rel-Istanza con preno.pdc.prenota.SoggAR */
  public static final String CSZrel_SoggAR = "Giorno_SoggAR";
  
  /** rel Rel-Istanza con preno.pdc.prenota.TipoTrasporto */
  public static final String CSZrel_TipoTrasporto = "Giorno_TipoTrasporto";
  
  // =============================================================


  /**
   * Costruttore di default.
   */
  public Giorno()  throws Exception {
    super(CSZ_PDCName);
  }

  /**
   * Costruttore con message handler.

   * @param msh message handler da usare per la messaggistica del PDC.
   */
  public Giorno(MessHandler msh) throws Exception {
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
  public Giorno(String nomeOgg) throws Exception {
    super(nomeOgg);
  }

  /**
   * Costruttore con message handler e nome PDC.
   *
   * @param msh message handler da usare per la messaggistica del PDC.
   * @param nomeOgg nome PDC
   *
   * @see #CSZ_PDCName
   * @see #Giorno(String)
   * @see #Giorno(MessHandler)
   */
  public Giorno(MessHandler msh, String nomeOgg) throws Exception {
    super(msh, nomeOgg);
  }

  /**
   * Costruttore con PDC di appoggio e nome.
   * Il PDC viene creato utilizzando il message handler del PDC passato e utilizzando
   * il database associato al DMC di pdcAppoggio per creare un nuovo DMC da associare a questo PDC.
   *
   * @param pdcAppoggio PDC di appoggio da utilizzare per creare questo PDC
   * @param nomeOgg nome PDC da creare
   * @see #Giorno(PDC)
   */
  public Giorno(PDC pdcAppoggio, String nomeOgg) throws Exception {
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
   * @see #Giorno(PDC,String)
   * @see #CSZ_PDCName
   */
  public Giorno(PDC pdcAppoggio) throws Exception {
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
    
    // IdGiorno
    c_IdGiorno = attr.addIntAttr(CSZcol_IdGiorno, CSZds_IdGiorno, CSZ_DBTable, true);
    c_IdGiorno.setSerial(true);

    
    // IdTpTrasporto
    c_IdTpTrasporto = attr.addIntAttr(CSZcol_IdTpTrasporto, CSZds_IdTpTrasporto, CSZ_DBTable, false);


    
    // DtGiorno
    c_DtGiorno = attr.addDateAttr(CSZcol_DtGiorno, CSZds_DtGiorno, CSZ_DBTable, false);


    
    // NumGiorno
    c_NumGiorno = attr.addIntAttr(CSZcol_NumGiorno, CSZds_NumGiorno, CSZ_DBTable, false);

    c_NumGiorno.setCalcolato(true);
	//c_NumGiorno.setWriteable(false);


    
    // Destinazione
    c_Destinazione = attr.addCharAttr(CSZcol_Destinazione, CSZds_Destinazione, CSZ_DBTable, false);
    c_Destinazione.setMaxLen(CSZcol_Destinazione_len); 

    
    // NotaGiorno
    c_NotaGiorno = attr.addCharAttr(CSZcol_NotaGiorno, CSZds_NotaGiorno, CSZ_DBTable, false);
    c_NotaGiorno.setMaxLen(CSZcol_NotaGiorno_len); 

    
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

   
      // rel SoggAR
      rel = addRelazione(CSZrel_SoggAR, Relazione.RELAZ_N, SoggAR.class.getName());
      rel.setEnabled(false);
      rel.setJoinType(IJoinInfo.LEFT_OUTER_JOIN);
	  rel.addJoinLink(this.CSZcol_IdGiorno,SoggAR.CSZcol_IdGiorno);
      rel.setHasIntegritaReferenziale(true);
      

      
      // rel TipoTrasporto
      rel = addRelazione(CSZrel_TipoTrasporto, Relazione.RELAZ_1, TipoTrasporto.class.getName());
      rel.setEnabled(false);
      

      
    return true;
  }

  
  /**
   * Ritorna il PDC TipoTrasporto collegato.
   * Utilizza la relazione per farsi dare il pdc collegato
   * Se e' il caso effettua il ripristino.
   *
   * @return TipoTrasporto il PDC collegato oppure null se non tutti i
   *                campi chiave sono valorizzati
   * @throws Exception in caso di errori in lettura da database
   */
  public TipoTrasporto getPDCTipoTrasporto() throws Exception {
    return (TipoTrasporto) loadPDCCollegato(CSZrel_TipoTrasporto, true);
  }
 
	public Integer getIdGiorno() {
		return c_IdGiorno.getValue();
	}
	public void setIdGiorno(Integer val) {
		c_IdGiorno.setValue(val);
	}
	public IntAttr getIdGiorno_attr() {
		return c_IdGiorno;
	}

	public Integer getIdTpTrasporto() {
		return c_IdTpTrasporto.getValue();
	}
	public void setIdTpTrasporto(Integer val) {
		c_IdTpTrasporto.setValue(val);
	}
	public IntAttr getIdTpTrasporto_attr() {
		return c_IdTpTrasporto;
	}

	public Date getDtGiorno() {
		return c_DtGiorno.getValue();
	}
	public void setDtGiorno(Date val) {
		c_DtGiorno.setValue(val);
	}
	public DateAttr getDtGiorno_attr() {
		return c_DtGiorno;
	}

	public Integer getNumGiorno() {
		return c_NumGiorno.getValue();
	}
	public void setNumGiorno(Integer val) {
		c_NumGiorno.setValue(val);
	}
	public IntAttr getNumGiorno_attr() {
		return c_NumGiorno;
	}

	public String getDestinazione() {
		return c_Destinazione.getValue();
	}
	public void setDestinazione(String val) {
		c_Destinazione.setValue(val);
	}
	public CharAttr getDestinazione_attr() {
		return c_Destinazione;
	}

	public String getNotaGiorno() {
		return c_NotaGiorno.getValue();
	}
	public void setNotaGiorno(String val) {
		c_NotaGiorno.setValue(val);
	}
	public CharAttr getNotaGiorno_attr() {
		return c_NotaGiorno;
	}

  /**
   * Ripristina il pdc da DMCDB.

   * @param i campi chiave
   * @return boolean
   * il metodo ritorna true se viene trovato uno ed un singolo record
   */
  public boolean ripristina(IntAttr idGiorno, DMCDB dmco) throws Exception {
   
    c_IdGiorno.setValue(idGiorno.getValue());
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
   
   @Override
	protected boolean calcAttributo() throws Exception {
		if ( !super.calcAttributo())
			return false;
		if ( !c_DtGiorno.isEmptyNullOrZero()) 
			setNumGiorno(DtUtil.getDay(getDtGiorno()));
		return true;
	}
}
