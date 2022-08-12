package sm.ciscoop.preno.pdc.prenota;

import sm.ciscoop.pdc.*;
import sm.ciscoop.jbb.IJoinInfo;
import sm.ciscoop.dmc.DMCDB;
import sm.ciscoop.preno.pdc.AppPDC;

import sm.ciscoop.preno.pdc.prenota.PrenoRagazzo;
import sm.ciscoop.stdlibs.baseutils.errors.MessHandler;


/*-
 * 
 * Copyright:    Copyright (c)
 * Company:      CIScoop
 * @author  $Author: paride $
 * @version $Revision: 1.4 $  $Date: 2018/03/29 10:39:31 $
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
		CREATE TABLE [dbo].[Ragazzo](
		 		
		  [IdSoggRagazzo] [int] DEFAULT 0 NOT NULL,
		  		
		  [NomeRagazzo] [VARCHAR](200) DEFAULT '' NOT NULL,
		  		
		  [EmailRagazzo] [VARCHAR](200) DEFAULT '' NOT NULL,
		  
			[ultagg] [datetime] DEFAULT (getdate()) NOT NULL,
			[utente] [varchar](20) DEFAULT '' NOT NULL,
		 CONSTRAINT [PK_Ragazzo_1] PRIMARY KEY CLUSTERED
		(
		
			[IdSoggRagazzo] ASC
		) WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]

		) ON [PRIMARY]
		GO

*
*/
public class Ragazzo extends AppPDC {

  private static final long serialVersionUID = 1L;

  /** Nome predefinito di questo PDC */
  public static final String CSZ_PDCName = "Ragazzo";

  /** tabella del database associata a questo PDC */
  public static final String CSZ_DBTable = "Ragazzo";

  // Campi chiave 
  /**  */
  public static final String CSZcol_IdSoggRagazzo = "IdSoggRagazzo";
  public static final String CSZds_IdSoggRagazzo = "Id SoggRagazzo";
  private IntAttr c_IdSoggRagazzo;
  

  // Campi non chiave 
  /**  */
  public static final String CSZcol_NomeRagazzo = "NomeRagazzo";
  public static final String CSZds_NomeRagazzo = "Nome Ragazzo";
  private CharAttr c_NomeRagazzo;
  
  /**  */
  public static final String CSZcol_EmailRagazzo = "EmailRagazzo";
  public static final String CSZds_EmailRagazzo = "Email Ragazzo";
  private CharAttr c_EmailRagazzo;
  
  // =============================================================
  // Relazioni
  
  /** rel Rel-TuttoParti con preno.pdc.prenota.PrenoRagazzo */
  public static final String CSZrel_PrenoRagazzo = "Ragazzo_PrenoRagazzo";
  
  // =============================================================


  /**
   * Costruttore di default.
   */
  public Ragazzo()  throws Exception {
    super(CSZ_PDCName);
  }

  /**
   * Costruttore con message handler.

   * @param msh message handler da usare per la messaggistica del PDC.
   */
  public Ragazzo(MessHandler msh) throws Exception {
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
  public Ragazzo(String nomeOgg) throws Exception {
    super(nomeOgg);
  }

  /**
   * Costruttore con message handler e nome PDC.
   *
   * @param msh message handler da usare per la messaggistica del PDC.
   * @param nomeOgg nome PDC
   *
   * @see #CSZ_PDCName
   * @see #Ragazzo(String)
   * @see #Ragazzo(MessHandler)
   */
  public Ragazzo(MessHandler msh, String nomeOgg) throws Exception {
    super(msh, nomeOgg);
  }

  /**
   * Costruttore con PDC di appoggio e nome.
   * Il PDC viene creato utilizzando il message handler del PDC passato e utilizzando
   * il database associato al DMC di pdcAppoggio per creare un nuovo DMC da associare a questo PDC.
   *
   * @param pdcAppoggio PDC di appoggio da utilizzare per creare questo PDC
   * @param nomeOgg nome PDC da creare
   * @see #Ragazzo(PDC)
   */
  public Ragazzo(PDC pdcAppoggio, String nomeOgg) throws Exception {
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
   * @see #Ragazzo(PDC,String)
   * @see #CSZ_PDCName
   */
  public Ragazzo(PDC pdcAppoggio) throws Exception {
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
    
    // IdSoggRagazzo
    c_IdSoggRagazzo = attr.addIntAttr(CSZcol_IdSoggRagazzo, CSZds_IdSoggRagazzo, CSZ_DBTable, true);

 	c_IdSoggRagazzo.setAutoCalcUserIdentity(true);


    
    // NomeRagazzo
    c_NomeRagazzo = attr.addCharAttr(CSZcol_NomeRagazzo, CSZds_NomeRagazzo, CSZ_DBTable, false);
    c_NomeRagazzo.setMaxLen(200); 

    
    // EmailRagazzo
    c_EmailRagazzo = attr.addCharAttr(CSZcol_EmailRagazzo, CSZds_EmailRagazzo, CSZ_DBTable, false);
    c_EmailRagazzo.setMaxLen(200); 

    
    return true;
  }

  
  /* Scommentare per effettuare il calcolo manualmente della chiave del PDC.
  Altrimenti il calcolo verra' fatto automaticamente dal PDC.
  @Override
  public void calcolaChiavePrimaria() {
  
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
    try {
    
   
      // rel PrenoRagazzo
      rel = addRelazione(CSZrel_PrenoRagazzo, Relazione.RELAZ_N, PrenoRagazzo.class.getName());
      rel.setEnabled(false);
      rel.setJoinType(IJoinInfo.LEFT_OUTER_JOIN);rel.setHasIntegritaReferenziale(true); 
      

      
 
    }
    catch (Exception ex) {
	    addMessageError("Errore in :creaRelaz");
      return false;
    }

    return true;
  }

  
  public int getIdSoggRagazzo() {
    return c_IdSoggRagazzo.getIntValue();
  }
  public void setIdSoggRagazzo(int val) {
    c_IdSoggRagazzo.setValue( val);
  }

  public String getNomeRagazzo() {
    return c_NomeRagazzo.getValue();
  }
  public void setNomeRagazzo(String val) {
    c_NomeRagazzo.setValue(val);
  }

  public String getEmailRagazzo() {
    return c_EmailRagazzo.getValue();
  }
  public void setEmailRagazzo(String val) {
    c_EmailRagazzo.setValue(val);
  }

  /**
   * Ripristina il pdc da DMCDB.

   * @param i campi chiave
   * @return boolean
   * il metodo ritorna true se viene trovato uno ed un singolo record
   */
  public boolean ripristina(IntAttr idSoggRagazzo, DMCDB dmco) throws Exception {
    if (dmco != null) {
      ((DMCDB) dmco.copy()).resetToPDC(this);
    }
	
    try {
 
      c_IdSoggRagazzo.setValue(idSoggRagazzo.getValue());
      if ( !load() || (getStatus() == PDCStatus.NO_DATA)) {
        return false;
      }
    } catch (Exception e) {
      addMessageError("Errore in :Ripristina");
      return false;
    }
    return true;
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
