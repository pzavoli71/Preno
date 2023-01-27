package sm.ciscoop.preno.pdc.soggetti;

import sm.ciscoop.pdc.*;
import sm.ciscoop.dmc.DMCDB;
import sm.ciscoop.dmc.DMCException;
import sm.ciscoop.stdlibs.baseutils.errors.MessHandler;
import sm.ciscoop.preno.pdc.AppPDC;

import sm.ciscoop.preno.pdc.prenota.SoggAR;
import sm.ciscoop.preno.pdc.prenota.SoggTrasporto;
import sm.ciscoop.preno.pdc.busy.Obiettivo;
import sm.ciscoop.jbb.IJoinInfo;



/*-
 * Title:
 * Description:  Lista per Soggetto
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
		CREATE TABLE [dbo].[Soggetto](
		[CdUtente] [int] DEFAULT 0 NOT NULL ,
		[IdSoggetto] [int] DEFAULT 0 NOT NULL ,
		[NomeSoggetto] [VARCHAR](50) DEFAULT '' NOT NULL ,
		[EmailSogg] [VARCHAR](200) DEFAULT '' NOT NULL ,
		[bRagazzo] [int] DEFAULT 0 NOT NULL ,
		[CodISS] [int] DEFAULT 0 NOT NULL ,
		
		[ultagg] [datetime] DEFAULT (getdate()) NOT NULL,
		[utente] [varchar](20) DEFAULT '' NOT NULL,
		CONSTRAINT [PK_Soggetto_1] PRIMARY KEY CLUSTERED
		(
		
			[IdSoggetto] ASC
		) WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]

		) ON [PRIMARY]
		GO

*
*/
public class Soggetto extends AppPDC {

  private static final long serialVersionUID = 1L;

  /** Nome predefinito di questo PDC */
  public static final String CSZ_PDCName = "Soggetto";

  /** tabella del database associata a questo PDC */
  public static final String CSZ_DBTable = "Soggetto";

  // Campi chiave 
  /**  */
  public static final String CSZcol_IdSoggetto = "IdSoggetto";
  public static final String CSZds_IdSoggetto = "Id Soggetto";
  private IntAttr   c_IdSoggetto;
 	
  

  // Campi non chiave 
  /**  */
  public static final String CSZcol_CdUtente = "CdUtente";
  public static final String CSZds_CdUtente = "CdUtente";
  private IntAttr   c_CdUtente;
 	
  
  /**  */
  public static final String CSZcol_NomeSoggetto = "NomeSoggetto";
  public static final String CSZds_NomeSoggetto = "Nome Soggetto";
  public static final Integer CSZcol_NomeSoggetto_len = 50;
  private CharAttr  c_NomeSoggetto;
 	
  
  /**  */
  public static final String CSZcol_EmailSogg = "EmailSogg";
  public static final String CSZds_EmailSogg = "Email Sogg";
  public static final Integer CSZcol_EmailSogg_len = 200;
  private CharAttr  c_EmailSogg;
 	
  
  /**  */
  public static final String CSZcol_bRagazzo = "bRagazzo";
  public static final String CSZds_bRagazzo = "bRagazzo";
  private BoolAttr  c_bRagazzo;
 	
  
  /**  */
  public static final String CSZcol_CodISS = "CodISS";
  public static final String CSZds_CodISS = "Cod ISS";
  private IntAttr   c_CodISS;
 	
  
  // =============================================================
  // Relazioni
  
  /** rel Rel-Istanza con preno.pdc.abilitazione.zUtente */
  public static final String CSZrel_zUtente = "Soggetto_zUtente";
  
  /** rel Rel-Istanza con preno.pdc.prenota.SoggAR */
  public static final String CSZrel_SoggAR = "Soggetto_SoggAR";
  
  /** rel Rel-Istanza con preno.pdc.prenota.SoggTrasporto */
  public static final String CSZrel_SoggTrasporto = "Soggetto_SoggTrasporto";
  
  /** rel Rel-Istanza con preno.pdc.busy.Obiettivo */
  public static final String CSZrel_Obiettivo = "Soggetto_Obiettivo";
  
  // =============================================================


  /**
   * Costruttore di default.
   */
  public Soggetto()  throws Exception {
    super(CSZ_PDCName);
  }

  /**
   * Costruttore con message handler.

   * @param msh message handler da usare per la messaggistica del PDC.
   */
  public Soggetto(MessHandler msh) throws Exception {
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
  public Soggetto(String nomeOgg) throws Exception {
    super(nomeOgg);
  }

  /**
   * Costruttore con message handler e nome PDC.
   *
   * @param msh message handler da usare per la messaggistica del PDC.
   * @param nomeOgg nome PDC
   *
   * @see #CSZ_PDCName
   * @see #Soggetto(String)
   * @see #Soggetto(MessHandler)
   */
  public Soggetto(MessHandler msh, String nomeOgg) throws Exception {
    super(msh, nomeOgg);
  }

  /**
   * Costruttore con PDC di appoggio e nome.
   * Il PDC viene creato utilizzando il message handler del PDC passato e utilizzando
   * il database associato al DMC di pdcAppoggio per creare un nuovo DMC da associare a questo PDC.
   *
   * @param pdcAppoggio PDC di appoggio da utilizzare per creare questo PDC
   * @param nomeOgg nome PDC da creare
   * @see #Soggetto(PDC)
   */
  public Soggetto(PDC pdcAppoggio, String nomeOgg) throws Exception {
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
   * @see #Soggetto(PDC,String)
   * @see #CSZ_PDCName
   */
  public Soggetto(PDC pdcAppoggio) throws Exception {
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
    
    // IdSoggetto
    c_IdSoggetto = attr.addIntAttr(CSZcol_IdSoggetto, CSZds_IdSoggetto, CSZ_DBTable, true);

 	c_IdSoggetto.setAutoCalcUserIdentity(true);


    
    // CdUtente
    c_CdUtente = attr.addIntAttr(CSZcol_CdUtente, CSZds_CdUtente, CSZ_DBTable, false);


    
    // NomeSoggetto
    c_NomeSoggetto = attr.addCharAttr(CSZcol_NomeSoggetto, CSZds_NomeSoggetto, CSZ_DBTable, false);
    c_NomeSoggetto.setMaxLen(CSZcol_NomeSoggetto_len); 

    
    // EmailSogg
    c_EmailSogg = attr.addCharAttr(CSZcol_EmailSogg, CSZds_EmailSogg, CSZ_DBTable, false);
    c_EmailSogg.setMaxLen(CSZcol_EmailSogg_len); 

    
    // bRagazzo
    c_bRagazzo = attr.addBoolAttr(CSZcol_bRagazzo, CSZds_bRagazzo, CSZ_DBTable, false);
   c_bRagazzo.setUseDefaultIfNull(true);

    
    // CodISS
    c_CodISS = attr.addIntAttr(CSZcol_CodISS, CSZds_CodISS, CSZ_DBTable, false);


    
    return true;
  }

  
  /* Scommentare per effettuare il calcolo manualmente della chiave del PDC.
  Altrimenti il calcolo verra' fatto automaticamente dal PDC.
  @Override
  public boolean calcolaChiavePrimaria() {

  }
  */
  


  /**
   * Crea e Inizializza le relazioni di questo PDC.
   */
  @Override
  public boolean initRelazione() throws Exception {
    if (!super.initRelazione()) {
      return false;
    }
    
    Relazione rel;

   
      // rel zUtente
      rel = addRelazione(CSZrel_zUtente, Relazione.RELAZ_1, sm.ciscoop.sec.pdc.zUtente.class.getName());
      rel.setEnabled(false);
      

      
      // rel SoggAR
      rel = addRelazione(CSZrel_SoggAR, Relazione.RELAZ_N, SoggAR.class.getName());
      rel.setEnabled(false);
      rel.setJoinType(IJoinInfo.LEFT_OUTER_JOIN);
	  rel.setHasIntegritaReferenziale(true);
      

      
      // rel SoggTrasporto
      rel = addRelazione(CSZrel_SoggTrasporto, Relazione.RELAZ_N, SoggTrasporto.class.getName());
      rel.setEnabled(false);
      rel.setJoinType(IJoinInfo.LEFT_OUTER_JOIN);
	  rel.setHasIntegritaReferenziale(true);
      

      
      // rel Obiettivo
      rel = addRelazione(CSZrel_Obiettivo, Relazione.RELAZ_N, Obiettivo.class.getName());
      rel.setEnabled(false);
      rel.setJoinType(IJoinInfo.LEFT_OUTER_JOIN);
	  rel.setHasIntegritaReferenziale(true);
      

      
    return true;
  }

  
  /**
   * Ritorna il PDC zUtente collegato.
   * Utilizza la relazione per farsi dare il pdc collegato
   * Se e' il caso effettua il ripristino.
   *
   * @return zUtente il PDC collegato oppure null se non tutti i
   *                campi chiave sono valorizzati
   * @throws Exception in caso di errori in lettura da database
   */
  public sm.ciscoop.sec.pdc.zUtente getPDCzUtente() throws Exception {
    return (sm.ciscoop.sec.pdc.zUtente) loadPDCCollegato(CSZrel_zUtente, true);
  }
  
	public Integer getIdSoggetto() {
		return c_IdSoggetto.getValue();
	}
	public void setIdSoggetto(Integer val) {
		c_IdSoggetto.setValue(val);
	}
	public IntAttr getIdSoggetto_attr() {
		return c_IdSoggetto;
	}

	public Integer getCdUtente() {
		return c_CdUtente.getValue();
	}
	public void setCdUtente(Integer val) {
		c_CdUtente.setValue(val);
	}
	public IntAttr getCdUtente_attr() {
		return c_CdUtente;
	}

	public String getNomeSoggetto() {
		return c_NomeSoggetto.getValue();
	}
	public void setNomeSoggetto(String val) {
		c_NomeSoggetto.setValue(val);
	}
	public CharAttr getNomeSoggetto_attr() {
		return c_NomeSoggetto;
	}

	public String getEmailSogg() {
		return c_EmailSogg.getValue();
	}
	public void setEmailSogg(String val) {
		c_EmailSogg.setValue(val);
	}
	public CharAttr getEmailSogg_attr() {
		return c_EmailSogg;
	}

	public Boolean getbRagazzo() {
		return c_bRagazzo.getValue();
	}
	public void setbRagazzo(Boolean val) {
		c_bRagazzo.setValue(val);
	}
	public BoolAttr getbRagazzo_attr() {
		return c_bRagazzo;
	}

	public Integer getCodISS() {
		return c_CodISS.getValue();
	}
	public void setCodISS(Integer val) {
		c_CodISS.setValue(val);
	}
	public IntAttr getCodISS_attr() {
		return c_CodISS;
	}

  /**
   * Ripristina il pdc da DMCDB.

   * @param i campi chiave
   * @return boolean
   * il metodo ritorna true se viene trovato uno ed un singolo record
   */
  public boolean ripristina(IntAttr idSoggetto, DMCDB dmco) throws Exception {
   
    c_IdSoggetto.setValue(idSoggetto.getValue());
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
