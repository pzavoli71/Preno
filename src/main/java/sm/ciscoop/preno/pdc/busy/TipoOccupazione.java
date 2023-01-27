package sm.ciscoop.preno.pdc.busy;

import sm.ciscoop.pdc.*;
import sm.ciscoop.dmc.DMCDB;
import sm.ciscoop.dmc.DMCException;
import sm.ciscoop.stdlibs.baseutils.errors.MessHandler;
import sm.ciscoop.preno.pdc.AppPDC;

import sm.ciscoop.preno.pdc.busy.Obiettivo;
import sm.ciscoop.jbb.IJoinInfo;



/*-
 * Title:
 * Description:  Lista per TipoOccupazione
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
		CREATE TABLE [dbo].[TipoOccupazione](
		[TpOccup] [int] DEFAULT 0 NOT NULL ,
		[DsOccup] [VARCHAR](200) DEFAULT '' NOT NULL ,
		
		[ultagg] [datetime] DEFAULT (getdate()) NOT NULL,
		[utente] [varchar](20) DEFAULT '' NOT NULL,
		CONSTRAINT [PK_TipoOccupazione_1] PRIMARY KEY CLUSTERED
		(
		
			[TpOccup] ASC
		) WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]

		) ON [PRIMARY]
		GO

*
*/
public class TipoOccupazione extends AppPDC {

  private static final long serialVersionUID = 1L;

  /** Nome predefinito di questo PDC */
  public static final String CSZ_PDCName = "TipoOccupazione";

  /** tabella del database associata a questo PDC */
  public static final String CSZ_DBTable = "TipoOccupazione";

  // Campi chiave 
  /**  */
  public static final String CSZcol_TpOccup = "TpOccup";
  public static final String CSZds_TpOccup = "Tp Occup";
  private IntAttr   c_TpOccup;
 	
  

  // Campi non chiave 
  /**  */
  public static final String CSZcol_DsOccup = "DsOccup";
  public static final String CSZds_DsOccup = "Ds Occup";
  public static final Integer CSZcol_DsOccup_len = 200;
  private CharAttr  c_DsOccup;
 	
  
  // =============================================================
  // Relazioni
  
  /** rel Rel-Istanza con preno.pdc.busy.Obiettivo */
  public static final String CSZrel_Obiettivo = "TipoOccupazione_Obiettivo";
  
  // =============================================================


  /**
   * Costruttore di default.
   */
  public TipoOccupazione()  throws Exception {
    super(CSZ_PDCName);
  }

  /**
   * Costruttore con message handler.

   * @param msh message handler da usare per la messaggistica del PDC.
   */
  public TipoOccupazione(MessHandler msh) throws Exception {
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
  public TipoOccupazione(String nomeOgg) throws Exception {
    super(nomeOgg);
  }

  /**
   * Costruttore con message handler e nome PDC.
   *
   * @param msh message handler da usare per la messaggistica del PDC.
   * @param nomeOgg nome PDC
   *
   * @see #CSZ_PDCName
   * @see #TipoOccupazione(String)
   * @see #TipoOccupazione(MessHandler)
   */
  public TipoOccupazione(MessHandler msh, String nomeOgg) throws Exception {
    super(msh, nomeOgg);
  }

  /**
   * Costruttore con PDC di appoggio e nome.
   * Il PDC viene creato utilizzando il message handler del PDC passato e utilizzando
   * il database associato al DMC di pdcAppoggio per creare un nuovo DMC da associare a questo PDC.
   *
   * @param pdcAppoggio PDC di appoggio da utilizzare per creare questo PDC
   * @param nomeOgg nome PDC da creare
   * @see #TipoOccupazione(PDC)
   */
  public TipoOccupazione(PDC pdcAppoggio, String nomeOgg) throws Exception {
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
   * @see #TipoOccupazione(PDC,String)
   * @see #CSZ_PDCName
   */
  public TipoOccupazione(PDC pdcAppoggio) throws Exception {
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
    
    // TpOccup
    c_TpOccup = attr.addIntAttr(CSZcol_TpOccup, CSZds_TpOccup, CSZ_DBTable, true);

 	c_TpOccup.setAutoCalcUserIdentity(true);


    
    // DsOccup
    c_DsOccup = attr.addCharAttr(CSZcol_DsOccup, CSZds_DsOccup, CSZ_DBTable, false);
    c_DsOccup.setMaxLen(CSZcol_DsOccup_len); 

    
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

   
      // rel Obiettivo
      rel = addRelazione(CSZrel_Obiettivo, Relazione.RELAZ_N, Obiettivo.class.getName());
      rel.setEnabled(false);
      rel.setJoinType(IJoinInfo.LEFT_OUTER_JOIN);
	  rel.setHasIntegritaReferenziale(true);
      

      
    return true;
  }

  
	public Integer getTpOccup() {
		return c_TpOccup.getValue();
	}
	public void setTpOccup(Integer val) {
		c_TpOccup.setValue(val);
	}
	public IntAttr getTpOccup_attr() {
		return c_TpOccup;
	}

	public String getDsOccup() {
		return c_DsOccup.getValue();
	}
	public void setDsOccup(String val) {
		c_DsOccup.setValue(val);
	}
	public CharAttr getDsOccup_attr() {
		return c_DsOccup;
	}

  /**
   * Ripristina il pdc da DMCDB.

   * @param i campi chiave
   * @return boolean
   * il metodo ritorna true se viene trovato uno ed un singolo record
   */
  public boolean ripristina(IntAttr tpOccup, DMCDB dmco) throws Exception {
   
    c_TpOccup.setValue(tpOccup.getValue());
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
