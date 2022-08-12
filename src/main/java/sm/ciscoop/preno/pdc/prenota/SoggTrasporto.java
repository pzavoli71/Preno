package sm.ciscoop.preno.pdc.prenota;

import sm.ciscoop.pdc.*;
import sm.ciscoop.dmc.DMCDB;
import sm.ciscoop.dmc.DMCException;
import sm.ciscoop.stdlibs.baseutils.errors.MessHandler;
import sm.ciscoop.preno.pdc.AppPDC;

import sm.ciscoop.preno.pdc.soggetti.Soggetto;
import sm.ciscoop.preno.pdc.prenota.TipoTrasporto;
import sm.ciscoop.jbb.IJoinInfo;



/*-
 * Title:
 * Description:  Lista per SoggTrasporto
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
		CREATE TABLE [dbo].[SoggTrasporto](
		[IdSoggetto] [int] DEFAULT 0 NOT NULL ,
		[IdTpTrasporto] [int] DEFAULT 0 NOT NULL ,
		[IdSoggTrasporto] [int] IDENTITY(1,1) ,
		
		[ultagg] [datetime] DEFAULT (getdate()) NOT NULL,
		[utente] [varchar](20) DEFAULT '' NOT NULL,
		CONSTRAINT [PK_SoggTrasporto_1] PRIMARY KEY CLUSTERED
		(
		
			[IdSoggTrasporto] ASC
		) WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]

		) ON [PRIMARY]
		GO

*
*/
public class SoggTrasporto extends AppPDC {

  private static final long serialVersionUID = 1L;

  /** Nome predefinito di questo PDC */
  public static final String CSZ_PDCName = "SoggTrasporto";

  /** tabella del database associata a questo PDC */
  public static final String CSZ_DBTable = "SoggTrasporto";

  // Campi chiave 
  /**  */
  public static final String CSZcol_IdSoggTrasporto = "IdSoggTrasporto";
  public static final String CSZds_IdSoggTrasporto = "Id SoggTrasporto";
  private IntAttr   c_IdSoggTrasporto;
 	
  

  // Campi non chiave 
  /**  */
  public static final String CSZcol_IdSoggetto = "IdSoggetto";
  public static final String CSZds_IdSoggetto = "Id Soggetto";
  private IntAttr   c_IdSoggetto;
 	
  
  /**  */
  public static final String CSZcol_IdTpTrasporto = "IdTpTrasporto";
  public static final String CSZds_IdTpTrasporto = "Id TpTrasporto";
  private IntAttr   c_IdTpTrasporto;
 	
  
  // =============================================================
  // Relazioni
  
  /** rel Rel-Istanza con preno.pdc.soggetti.Soggetto */
  public static final String CSZrel_Soggetto = "SoggTrasporto_Soggetto";
  
  /** rel Rel-Istanza con preno.pdc.prenota.TipoTrasporto */
  public static final String CSZrel_TipoTrasporto = "SoggTrasporto_TipoTrasporto";
  
  // =============================================================


  /**
   * Costruttore di default.
   */
  public SoggTrasporto()  throws Exception {
    super(CSZ_PDCName);
  }

  /**
   * Costruttore con message handler.

   * @param msh message handler da usare per la messaggistica del PDC.
   */
  public SoggTrasporto(MessHandler msh) throws Exception {
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
  public SoggTrasporto(String nomeOgg) throws Exception {
    super(nomeOgg);
  }

  /**
   * Costruttore con message handler e nome PDC.
   *
   * @param msh message handler da usare per la messaggistica del PDC.
   * @param nomeOgg nome PDC
   *
   * @see #CSZ_PDCName
   * @see #SoggTrasporto(String)
   * @see #SoggTrasporto(MessHandler)
   */
  public SoggTrasporto(MessHandler msh, String nomeOgg) throws Exception {
    super(msh, nomeOgg);
  }

  /**
   * Costruttore con PDC di appoggio e nome.
   * Il PDC viene creato utilizzando il message handler del PDC passato e utilizzando
   * il database associato al DMC di pdcAppoggio per creare un nuovo DMC da associare a questo PDC.
   *
   * @param pdcAppoggio PDC di appoggio da utilizzare per creare questo PDC
   * @param nomeOgg nome PDC da creare
   * @see #SoggTrasporto(PDC)
   */
  public SoggTrasporto(PDC pdcAppoggio, String nomeOgg) throws Exception {
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
   * @see #SoggTrasporto(PDC,String)
   * @see #CSZ_PDCName
   */
  public SoggTrasporto(PDC pdcAppoggio) throws Exception {
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
    
    // IdSoggTrasporto
    c_IdSoggTrasporto = attr.addIntAttr(CSZcol_IdSoggTrasporto, CSZds_IdSoggTrasporto, CSZ_DBTable, true);
    c_IdSoggTrasporto.setSerial(true);

    
    // IdSoggetto
    c_IdSoggetto = attr.addIntAttr(CSZcol_IdSoggetto, CSZds_IdSoggetto, CSZ_DBTable, false);


    
    // IdTpTrasporto
    c_IdTpTrasporto = attr.addIntAttr(CSZcol_IdTpTrasporto, CSZds_IdTpTrasporto, CSZ_DBTable, false);


    
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

   
      // rel Soggetto
      rel = addRelazione(CSZrel_Soggetto, Relazione.RELAZ_1, Soggetto.class.getName());
      rel.setEnabled(false);
      

      
      // rel TipoTrasporto
      rel = addRelazione(CSZrel_TipoTrasporto, Relazione.RELAZ_1, TipoTrasporto.class.getName());
      rel.setEnabled(false);
      

      
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
  public Soggetto getPDCSoggetto() throws Exception {
    return (Soggetto) loadPDCCollegato(CSZrel_Soggetto, true);
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
 
	public Integer getIdSoggTrasporto() {
		return c_IdSoggTrasporto.getValue();
	}
	public void setIdSoggTrasporto(Integer val) {
		c_IdSoggTrasporto.setValue(val);
	}
	public IntAttr getIdSoggTrasporto_attr() {
		return c_IdSoggTrasporto;
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

	public Integer getIdTpTrasporto() {
		return c_IdTpTrasporto.getValue();
	}
	public void setIdTpTrasporto(Integer val) {
		c_IdTpTrasporto.setValue(val);
	}
	public IntAttr getIdTpTrasporto_attr() {
		return c_IdTpTrasporto;
	}

  /**
   * Ripristina il pdc da DMCDB.

   * @param i campi chiave
   * @return boolean
   * il metodo ritorna true se viene trovato uno ed un singolo record
   */
  public boolean ripristina(IntAttr idSoggTrasporto, DMCDB dmco) throws Exception {
   
    c_IdSoggTrasporto.setValue(idSoggTrasporto.getValue());
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
