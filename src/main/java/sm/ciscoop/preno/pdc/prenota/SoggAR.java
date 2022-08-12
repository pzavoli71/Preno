package sm.ciscoop.preno.pdc.prenota;

import sm.ciscoop.pdc.*;
import sm.ciscoop.dmc.DMCDB;
import sm.ciscoop.dmc.DMCException;
import sm.ciscoop.preno.pdc.AppPDC;

import sm.ciscoop.preno.pdc.soggetti.Soggetto;
import sm.ciscoop.stdlibs.baseutils.errors.MessHandler;
import sm.ciscoop.preno.pdc.prenota.Giorno;
import sm.ciscoop.jbb.IJoinInfo;



/*-
 * Title:
 * Description:  Lista per SoggAR
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
		CREATE TABLE [dbo].[SoggAR](
		[IdSoggetto] [int] DEFAULT 0 NOT NULL ,
		[IdGiorno] [int] DEFAULT 0 NOT NULL ,
		[IdSoggAndata] [int] IDENTITY(1,1) ,
		[AR] [int] DEFAULT 0 NOT NULL ,
		
		[ultagg] [datetime] DEFAULT (getdate()) NOT NULL,
		[utente] [varchar](20) DEFAULT '' NOT NULL,
		CONSTRAINT [PK_SoggAR_1] PRIMARY KEY CLUSTERED
		(
		
			[IdSoggAndata] ASC
		) WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]

		) ON [PRIMARY]
		GO

*
*/
public class SoggAR extends AppPDC {

  private static final long serialVersionUID = 1L;

  /** Nome predefinito di questo PDC */
  public static final String CSZ_PDCName = "SoggAR";

  /** tabella del database associata a questo PDC */
  public static final String CSZ_DBTable = "SoggAR";

  // Campi chiave 
  /**  */
  public static final String CSZcol_IdSoggAndata = "IdSoggAndata";
  public static final String CSZds_IdSoggAndata = "Id SoggAndata";
  private IntAttr   c_IdSoggAndata;
 	
  

  // Campi non chiave 
  /**  */
  public static final String CSZcol_IdSoggetto = "IdSoggetto";
  public static final String CSZds_IdSoggetto = "Id Soggetto";
  private IntAttr   c_IdSoggetto;
 	
  
  /**  */
  public static final String CSZcol_IdGiorno = "IdGiorno";
  public static final String CSZds_IdGiorno = "Id Giorno";
  private IntAttr   c_IdGiorno;
 	
  
  /** 1 solo andata

2 solo ritorno

3 Tutte e due */
  public static final String CSZcol_AR = "AR";
  public static final String CSZds_AR = "AR";
  private IntAttr   c_AR;
 	
  
  // =============================================================
  // Relazioni
  
  /** rel Rel-Istanza con preno.pdc.soggetti.Soggetto */
  public static final String CSZrel_Soggetto = "SoggAR_Soggetto";
  
  /** rel Rel-Istanza con preno.pdc.prenota.Giorno */
  public static final String CSZrel_Giorno = "SoggAR_Giorno";
  
  // =============================================================


  /**
   * Costruttore di default.
   */
  public SoggAR()  throws Exception {
    super(CSZ_PDCName);
  }

  /**
   * Costruttore con message handler.

   * @param msh message handler da usare per la messaggistica del PDC.
   */
  public SoggAR(MessHandler msh) throws Exception {
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
  public SoggAR(String nomeOgg) throws Exception {
    super(nomeOgg);
  }

  /**
   * Costruttore con message handler e nome PDC.
   *
   * @param msh message handler da usare per la messaggistica del PDC.
   * @param nomeOgg nome PDC
   *
   * @see #CSZ_PDCName
   * @see #SoggAR(String)
   * @see #SoggAR(MessHandler)
   */
  public SoggAR(MessHandler msh, String nomeOgg) throws Exception {
    super(msh, nomeOgg);
  }

  /**
   * Costruttore con PDC di appoggio e nome.
   * Il PDC viene creato utilizzando il message handler del PDC passato e utilizzando
   * il database associato al DMC di pdcAppoggio per creare un nuovo DMC da associare a questo PDC.
   *
   * @param pdcAppoggio PDC di appoggio da utilizzare per creare questo PDC
   * @param nomeOgg nome PDC da creare
   * @see #SoggAR(PDC)
   */
  public SoggAR(PDC pdcAppoggio, String nomeOgg) throws Exception {
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
   * @see #SoggAR(PDC,String)
   * @see #CSZ_PDCName
   */
  public SoggAR(PDC pdcAppoggio) throws Exception {
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
    
    // IdSoggAndata
    c_IdSoggAndata = attr.addIntAttr(CSZcol_IdSoggAndata, CSZds_IdSoggAndata, CSZ_DBTable, true);
    c_IdSoggAndata.setSerial(true);

    
    // IdSoggetto
    c_IdSoggetto = attr.addIntAttr(CSZcol_IdSoggetto, CSZds_IdSoggetto, CSZ_DBTable, false);


    
    // IdGiorno
    c_IdGiorno = attr.addIntAttr(CSZcol_IdGiorno, CSZds_IdGiorno, CSZ_DBTable, false);


    
    // AR
    c_AR = attr.addIntAttr(CSZcol_AR, CSZds_AR, CSZ_DBTable, false);


    
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
      

      
      // rel Giorno
      rel = addRelazione(CSZrel_Giorno, Relazione.RELAZ_1, Giorno.class.getName());
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
   * Ritorna il PDC Giorno collegato.
   * Utilizza la relazione per farsi dare il pdc collegato
   * Se e' il caso effettua il ripristino.
   *
   * @return Giorno il PDC collegato oppure null se non tutti i
   *                campi chiave sono valorizzati
   * @throws Exception in caso di errori in lettura da database
   */
  public Giorno getPDCGiorno() throws Exception {
    return (Giorno) loadPDCCollegato(CSZrel_Giorno, true);
  }
 
	public Integer getIdSoggAndata() {
		return c_IdSoggAndata.getValue();
	}
	public void setIdSoggAndata(Integer val) {
		c_IdSoggAndata.setValue(val);
	}
	public IntAttr getIdSoggAndata_attr() {
		return c_IdSoggAndata;
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

	public Integer getIdGiorno() {
		return c_IdGiorno.getValue();
	}
	public void setIdGiorno(Integer val) {
		c_IdGiorno.setValue(val);
	}
	public IntAttr getIdGiorno_attr() {
		return c_IdGiorno;
	}

	public Integer getAR() {
		return c_AR.getValue();
	}
	public void setAR(Integer val) {
		c_AR.setValue(val);
	}
	public IntAttr getAR_attr() {
		return c_AR;
	}

  /**
   * Ripristina il pdc da DMCDB.

   * @param i campi chiave
   * @return boolean
   * il metodo ritorna true se viene trovato uno ed un singolo record
   */
  public boolean ripristina(IntAttr idSoggAndata, DMCDB dmco) throws Exception {
   
    c_IdSoggAndata.setValue(idSoggAndata.getValue());
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
	protected boolean beforeSave() throws Exception {
		if ( !super.beforeSave())
			return false;
		if ( isInInserimento() || isInEditMode()) {
			Soggetto s = getPDCSoggetto();
			String ls = "";
			if ( s.getbRagazzo()) {
				// Controllo solo se questo ragazzo è già stato inserito
				ls = "select * from " + CSZ_DBTable +  " where idSoggAndata != " + getIdSoggAndata();	
				ls += " AND IdSoggetto = " + getIdSoggetto();
			} else {
				// Controllo se è già stato inserito un autista per questa tratta
				ls = "select * from " + CSZ_DBTable +  " a inner join " + Soggetto.CSZ_DBTable + " s on s.IdSoggetto = a.IdSoggetto and s.bragazzo = 0";
				ls += " where idSoggAndata != " + getIdSoggAndata();	
			}
			ls += " AND IdGiorno = " + getIdGiorno();
			if ( getAR() == 1) {
				ls += " AND AR in (1,3)";
			} else if (getAR() == 2) {
				ls += " AND AR in (2,3)";
			}								
			int conta = getJBB().getCount(ls);
			if ( conta > 0) {
				addMessageError("Il soggetto è già presente");
				return false;
			}
			if ( conta < 0) {
				addMessageError("Errore in verifica presenza del soggetto");
				return false;
			}
		}
		return true;
	}
}
