package sm.ciscoop.preno.pdc.prenota;

import sm.ciscoop.pdc.*;
import sm.ciscoop.jbb.IJoinInfo;
import sm.ciscoop.dmc.DMCDB;
import sm.ciscoop.preno.pdc.AppPDC;

import sm.ciscoop.preno.pdc.prenota.PrenoGiorno;
import sm.ciscoop.preno.pdc.prenota.Ragazzo;
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
		CREATE TABLE [dbo].[PrenoRagazzo](
		 		
		  [IdPrenotazione] [int] DEFAULT 0 NOT NULL,
		  		
		  [IdSoggRagazzo] [int] DEFAULT 0 NOT NULL,
		  
			[ultagg] [datetime] DEFAULT (getdate()) NOT NULL,
			[utente] [varchar](20) DEFAULT '' NOT NULL,
		 CONSTRAINT [PK_PrenoRagazzo_1] PRIMARY KEY CLUSTERED
		(
		
			[IdPrenotazione] ASC,
			[IdSoggRagazzo] ASC
		) WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]

		) ON [PRIMARY]
		GO

*
*/
public class PrenoRagazzo extends AppPDC {

  private static final long serialVersionUID = 1L;

  /** Nome predefinito di questo PDC */
  public static final String CSZ_PDCName = "PrenoRagazzo";

  /** tabella del database associata a questo PDC */
  public static final String CSZ_DBTable = "PrenoRagazzo";

  // Campi chiave 
  /**  */
  public static final String CSZcol_IdPrenotazione = "IdPrenotazione";
  public static final String CSZds_IdPrenotazione = "Id Prenotazione";
  private IntAttr c_IdPrenotazione;
  
  /**  */
  public static final String CSZcol_IdSoggRagazzo = "IdSoggRagazzo";
  public static final String CSZds_IdSoggRagazzo = "Id SoggRagazzo";
  private IntAttr c_IdSoggRagazzo;
  

  // Campi non chiave 
  // =============================================================
  // Relazioni
  
  /** rel Rel-TuttoParti con preno.pdc.prenota.PrenoGiorno */
  public static final String CSZrel_PrenoGiorno = "PrenoRagazzo_PrenoGiorno";
  
  /** rel Rel-TuttoParti con preno.pdc.prenota.Ragazzo */
  public static final String CSZrel_Ragazzo = "PrenoRagazzo_Ragazzo";
  
  // =============================================================


  /**
   * Costruttore di default.
   */
  public PrenoRagazzo()  throws Exception {
    super(CSZ_PDCName);
  }

  /**
   * Costruttore con message handler.

   * @param msh message handler da usare per la messaggistica del PDC.
   */
  public PrenoRagazzo(MessHandler msh) throws Exception {
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
  public PrenoRagazzo(String nomeOgg) throws Exception {
    super(nomeOgg);
  }

  /**
   * Costruttore con message handler e nome PDC.
   *
   * @param msh message handler da usare per la messaggistica del PDC.
   * @param nomeOgg nome PDC
   *
   * @see #CSZ_PDCName
   * @see #PrenoRagazzo(String)
   * @see #PrenoRagazzo(MessHandler)
   */
  public PrenoRagazzo(MessHandler msh, String nomeOgg) throws Exception {
    super(msh, nomeOgg);
  }

  /**
   * Costruttore con PDC di appoggio e nome.
   * Il PDC viene creato utilizzando il message handler del PDC passato e utilizzando
   * il database associato al DMC di pdcAppoggio per creare un nuovo DMC da associare a questo PDC.
   *
   * @param pdcAppoggio PDC di appoggio da utilizzare per creare questo PDC
   * @param nomeOgg nome PDC da creare
   * @see #PrenoRagazzo(PDC)
   */
  public PrenoRagazzo(PDC pdcAppoggio, String nomeOgg) throws Exception {
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
   * @see #PrenoRagazzo(PDC,String)
   * @see #CSZ_PDCName
   */
  public PrenoRagazzo(PDC pdcAppoggio) throws Exception {
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
    
    // IdPrenotazione
    c_IdPrenotazione = attr.addIntAttr(CSZcol_IdPrenotazione, CSZds_IdPrenotazione, CSZ_DBTable, true);


    
    // IdSoggRagazzo
    c_IdSoggRagazzo = attr.addIntAttr(CSZcol_IdSoggRagazzo, CSZds_IdSoggRagazzo, CSZ_DBTable, true);

 	c_IdSoggRagazzo.setAutoCalcUserIdentity(true);


    
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
    
   
      // rel PrenoGiorno
      rel = addRelazione(CSZrel_PrenoGiorno, Relazione.RELAZ_1, PrenoGiorno.class.getName());
      rel.setEnabled(false);
      

      
      // rel Ragazzo
      rel = addRelazione(CSZrel_Ragazzo, Relazione.RELAZ_1, Ragazzo.class.getName());
      rel.setEnabled(false);
      

      
 
    }
    catch (Exception ex) {
	    addMessageError("Errore in :creaRelaz");
      return false;
    }

    return true;
  }

  
  /**
   * Ritorna il PDC PrenoGiorno collegato.
   * Utilizza la relazione per farsi dare il pdc collegato
   * Se e' il caso effettua il ripristino.
   *
   * @return PrenoGiorno il PDC collegato oppure null se non tutti i
   *                campi chiave sono valorizzati
   * @throws Exception in caso di errori in lettura da database
   */
  public PrenoGiorno getPDCPrenoGiorno() throws Exception {
    PrenoGiorno c = (PrenoGiorno) getPDCCollegato(CSZrel_PrenoGiorno, true);
    if (c.getStatus() != PDCStatus.OPENED) {
      if (c.getAttributi().hasKeyNotNull(this)) {
        if (!c.ripristina(this, (DMCDB)this.getDMC())) {
          throw new Exception("ripristina() finito male");
        }
      }
      else {
        //non tutti i campi chiave sono valorizzati: ritorno null
        return null;
      }
    }
    if (c.getStatus() == PDCStatus.OPENED) {
      DMCDB dmc = (DMCDB) getDMC().copy();
      dmc.setTabAggiornamento(c.getTable());
      c.setDMC(dmc);
    }
    return c;
  }
 
  /**
   * Ritorna il PDC Ragazzo collegato.
   * Utilizza la relazione per farsi dare il pdc collegato
   * Se e' il caso effettua il ripristino.
   *
   * @return Ragazzo il PDC collegato oppure null se non tutti i
   *                campi chiave sono valorizzati
   * @throws Exception in caso di errori in lettura da database
   */
  public Ragazzo getPDCRagazzo() throws Exception {
    Ragazzo c = (Ragazzo) getPDCCollegato(CSZrel_Ragazzo, true);
    if (c.getStatus() != PDCStatus.OPENED) {
      if (c.getAttributi().hasKeyNotNull(this)) {
        if (!c.ripristina(this, (DMCDB)this.getDMC())) {
          throw new Exception("ripristina() finito male");
        }
      }
      else {
        //non tutti i campi chiave sono valorizzati: ritorno null
        return null;
      }
    }
    if (c.getStatus() == PDCStatus.OPENED) {
      DMCDB dmc = (DMCDB) getDMC().copy();
      dmc.setTabAggiornamento(c.getTable());
      c.setDMC(dmc);
    }
    return c;
  }
 
  public int getIdPrenotazione() {
    return c_IdPrenotazione.getIntValue();
  }
  public void setIdPrenotazione(int val) {
    c_IdPrenotazione.setValue( val);
  }

  public int getIdSoggRagazzo() {
    return c_IdSoggRagazzo.getIntValue();
  }
  public void setIdSoggRagazzo(int val) {
    c_IdSoggRagazzo.setValue( val);
  }

  /**
   * Ripristina il pdc da DMCDB.

   * @param i campi chiave
   * @return boolean
   * il metodo ritorna true se viene trovato uno ed un singolo record
   */
  public boolean ripristina(IntAttr idPrenotazione,IntAttr idSoggRagazzo, DMCDB dmco) throws Exception {
    if (dmco != null) {
      ((DMCDB) dmco.copy()).resetToPDC(this);
    }
	
    try {
 
      c_IdPrenotazione.setValue(idPrenotazione.getValue());
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
