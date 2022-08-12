package sm.ciscoop.preno.pdc.prenota;

import sm.ciscoop.pdc.*;
import sm.ciscoop.jbb.IJoinInfo;
import sm.ciscoop.dmc.DMCDB;
import sm.ciscoop.preno.pdc.AppPDC;

import sm.ciscoop.preno.pdc.prenota.PrenoRagazzo;
import sm.ciscoop.preno.pdc.soggetti.Soggetto;
import sm.ciscoop.stdlibs.baseutils.errors.MessHandler;
import sm.ciscoop.preno.pdc.soggetti.Soggetto;
import java.util.*;


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
		CREATE TABLE [dbo].[PrenoGiorno](
		 		
		  [IdSoggetto] [int] DEFAULT 0 NOT NULL,
		  		
		  [IdPrenotazione] [int] IDENTITY(1,1),
		  		
		  [DtGiorno] [DateTime],
		  		
		  [IdSoggettoAndata] [int] DEFAULT 0 NOT NULL,
		  		
		  [IdSoggettoRitorno] [int] DEFAULT 0 NOT NULL,
		  		
		  [NumMax] [int] DEFAULT 0 NOT NULL,
		  
			[ultagg] [datetime] DEFAULT (getdate()) NOT NULL,
			[utente] [varchar](20) DEFAULT '' NOT NULL,
		 CONSTRAINT [PK_PrenoGiorno_1] PRIMARY KEY CLUSTERED
		(
		
			[IdPrenotazione] ASC
		) WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]

		) ON [PRIMARY]
		GO

*
*/
public class PrenoGiorno extends AppPDC {

  private static final long serialVersionUID = 1L;

  /** Nome predefinito di questo PDC */
  public static final String CSZ_PDCName = "PrenoGiorno";

  /** tabella del database associata a questo PDC */
  public static final String CSZ_DBTable = "PrenoGiorno";

  // Campi chiave 
  /**  */
  public static final String CSZcol_IdPrenotazione = "IdPrenotazione";
  public static final String CSZds_IdPrenotazione = "Id Prenotazione";
  private IntAttr c_IdPrenotazione;
  

  // Campi non chiave 
  /**  */
  public static final String CSZcol_IdSoggetto = "IdSoggetto";
  public static final String CSZds_IdSoggetto = "Id Soggetto";
  private IntAttr c_IdSoggetto;
  
  /**  */
  public static final String CSZcol_DtGiorno = "DtGiorno";
  public static final String CSZds_DtGiorno = "Dt Giorno";
  private DateAttr c_DtGiorno;
  
  /**  */
  public static final String CSZcol_IdSoggettoAndata = "IdSoggettoAndata";
  public static final String CSZds_IdSoggettoAndata = "Id SoggettoAndata";
  private IntAttr c_IdSoggettoAndata;
  
  /**  */
  public static final String CSZcol_IdSoggettoRitorno = "IdSoggettoRitorno";
  public static final String CSZds_IdSoggettoRitorno = "Id SoggettoRitorno";
  private IntAttr c_IdSoggettoRitorno;
  
  /**  */
  public static final String CSZcol_NumMax = "NumMax";
  public static final String CSZds_NumMax = "Num Max";
  private IntAttr c_NumMax;
  
  // =============================================================
  // Relazioni
  
  /** rel Rel-TuttoParti con preno.pdc.prenota.PrenoRagazzo */
  public static final String CSZrel_PrenoRagazzo = "PrenoGiorno_PrenoRagazzo";
  
  /** rel Rel-Istanza con preno.pdc.soggetti.Soggetto */
  public static final String CSZrel_Soggettoandat = "PrenoGiorno_Soggettoandat";
  
  /** rel Rel-Istanza con preno.pdc.soggetti.Soggetto */
  public static final String CSZrel_Soggettoritor = "PrenoGiorno_Soggettoritor";
  
  // =============================================================


  /**
   * Costruttore di default.
   */
  public PrenoGiorno()  throws Exception {
    super(CSZ_PDCName);
  }

  /**
   * Costruttore con message handler.

   * @param msh message handler da usare per la messaggistica del PDC.
   */
  public PrenoGiorno(MessHandler msh) throws Exception {
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
  public PrenoGiorno(String nomeOgg) throws Exception {
    super(nomeOgg);
  }

  /**
   * Costruttore con message handler e nome PDC.
   *
   * @param msh message handler da usare per la messaggistica del PDC.
   * @param nomeOgg nome PDC
   *
   * @see #CSZ_PDCName
   * @see #PrenoGiorno(String)
   * @see #PrenoGiorno(MessHandler)
   */
  public PrenoGiorno(MessHandler msh, String nomeOgg) throws Exception {
    super(msh, nomeOgg);
  }

  /**
   * Costruttore con PDC di appoggio e nome.
   * Il PDC viene creato utilizzando il message handler del PDC passato e utilizzando
   * il database associato al DMC di pdcAppoggio per creare un nuovo DMC da associare a questo PDC.
   *
   * @param pdcAppoggio PDC di appoggio da utilizzare per creare questo PDC
   * @param nomeOgg nome PDC da creare
   * @see #PrenoGiorno(PDC)
   */
  public PrenoGiorno(PDC pdcAppoggio, String nomeOgg) throws Exception {
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
   * @see #PrenoGiorno(PDC,String)
   * @see #CSZ_PDCName
   */
  public PrenoGiorno(PDC pdcAppoggio) throws Exception {
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
    c_IdPrenotazione.setSerial(true);

    
    // IdSoggetto
    c_IdSoggetto = attr.addIntAttr(CSZcol_IdSoggetto, CSZds_IdSoggetto, CSZ_DBTable, false);


    
    // DtGiorno
    c_DtGiorno = attr.addDateAttr(CSZcol_DtGiorno, CSZds_DtGiorno, CSZ_DBTable, false);


    
    // IdSoggettoAndata
    c_IdSoggettoAndata = attr.addIntAttr(CSZcol_IdSoggettoAndata, CSZds_IdSoggettoAndata, CSZ_DBTable, false);


    
    // IdSoggettoRitorno
    c_IdSoggettoRitorno = attr.addIntAttr(CSZcol_IdSoggettoRitorno, CSZds_IdSoggettoRitorno, CSZ_DBTable, false);


    
    // NumMax
    c_NumMax = attr.addSmallintAttr(CSZcol_NumMax, CSZds_NumMax, CSZ_DBTable, false);


    
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
    try {
    
   
      // rel PrenoRagazzo
      rel = addRelazione(CSZrel_PrenoRagazzo, Relazione.RELAZ_N, PrenoRagazzo.class.getName());
      rel.setEnabled(false);
      rel.setJoinType(IJoinInfo.LEFT_OUTER_JOIN);rel.addJoinLink("IdPrenotazione","IdPrenotazione"); 
      rel.setHasIntegritaReferenziale(true); 
      

      
      // rel Soggettoandat
      rel = addRelazione(CSZrel_Soggettoandat, Relazione.RELAZ_1, Soggetto.class.getName());
      rel.addAlias("IdSoggetto", "IdSoggettoAndata","SoggettoAndata");
      rel.addAlias("NomeSoggetto", "NomSoggettoAndata","SoggettoAndata");
      rel.addAlias("EmailSogg", "EmailSoggAndata","SoggettoAndata");
      rel.addAlias("CodISS", "CodISSAndata","SoggettoAndata");
      rel.setDxTableAlias("SoggettoAndata");
      rel.setJoinType(IJoinInfo.LEFT_OUTER_JOIN);
      rel.setEnabled(false);
      

      
      // rel Soggettoritor
      rel = addRelazione(CSZrel_Soggettoritor, Relazione.RELAZ_1, Soggetto.class.getName());
      rel.addAlias("IdSoggetto", "IdSoggettoRitorno","SoggettoRitorno");
      rel.addAlias("NomeSoggetto", "NomSoggettoRitorno","SoggettoRitorno");
      rel.addAlias("EmailSogg", "EmailSoggRitorno","SoggettoRitorno");
      rel.addAlias("CodISS", "CodISSRitorno","SoggettoRitorno");
      rel.setDxTableAlias("SoggettoRitorno");
      rel.setJoinType(IJoinInfo.LEFT_OUTER_JOIN);
      rel.setEnabled(false);
      

      
 
    }
    catch (Exception ex) {
	    addMessageError("Errore in :creaRelaz");
      return false;
    }

    return true;
  }

  
  /**
   * Ritorna il PDC Soggetto collegato.
   * Utilizza la relazione per farsi dare il pdc collegato
   * Se e' il caso effettua il ripristino.
   *
   * @return Soggetto il PDC collegato oppure null se non tutti i
   *                campi chiave sono valorizzati
   * @throws Exception in caso di errori in lettura da database
   */
  public Soggetto getPDCSoggettoAndataq() throws Exception {
    Soggetto c = (Soggetto) getPDCCollegato(CSZrel_Soggettoandat, true);
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
   * Ritorna il PDC Soggetto collegato.
   * Utilizza la relazione per farsi dare il pdc collegato
   * Se e' il caso effettua il ripristino.
   *
   * @return Soggetto il PDC collegato oppure null se non tutti i
   *                campi chiave sono valorizzati
   * @throws Exception in caso di errori in lettura da database
   */
  public Soggetto getPDCSoggettoRitorno() throws Exception {
    Soggetto c = (Soggetto) getPDCCollegato(CSZrel_Soggettoritor, true);
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

  public int getIdSoggetto() {
    return c_IdSoggetto.getIntValue();
  }
  public void setIdSoggetto(int val) {
    c_IdSoggetto.setValue( val);
  }

  public Date getDtGiorno() {
    return c_DtGiorno.getValue();
  }
  public void setDtGiorno(Date val) {
    c_DtGiorno.setValue(val);
  }

  public int getIdSoggettoAndata() {
    return c_IdSoggettoAndata.getIntValue();
  }
  public void setIdSoggettoAndata(int val) {
    c_IdSoggettoAndata.setValue( val);
  }

  public int getIdSoggettoRitorno() {
    return c_IdSoggettoRitorno.getIntValue();
  }
  public void setIdSoggettoRitorno(int val) {
    c_IdSoggettoRitorno.setValue( val);
  }

  public int getNumMax() {
    return c_NumMax.getIntValue();
  }
  public void setNumMax(int val) {
    c_NumMax.setValue( val);
  }

  /**
   * Ripristina il pdc da DMCDB.

   * @param i campi chiave
   * @return boolean
   * il metodo ritorna true se viene trovato uno ed un singolo record
   */
  public boolean ripristina(IntAttr idPrenotazione, DMCDB dmco) throws Exception {
    if (dmco != null) {
      ((DMCDB) dmco.copy()).resetToPDC(this);
    }
	
    try {
 
      c_IdPrenotazione.setValue(idPrenotazione.getValue());
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
