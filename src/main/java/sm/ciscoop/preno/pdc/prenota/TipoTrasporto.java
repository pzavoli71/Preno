package sm.ciscoop.preno.pdc.prenota;

import sm.ciscoop.pdc.*;
import sm.ciscoop.dmc.DMCDB;
import sm.ciscoop.dmc.DMCException;
import sm.ciscoop.preno.pdc.AppPDC;

import sm.ciscoop.preno.pdc.prenota.Giorno;
import sm.ciscoop.stdlibs.baseutils.errors.MessHandler;
import sm.ciscoop.jbb.IJoinInfo;



/*-
 * Title:
 * Description:  Lista per TipoTrasporto
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
		CREATE TABLE [dbo].[TipoTrasporto](
		[IdTpTrasporto] [int] IDENTITY(1,1) ,
		[NomeTpTrasporto] [VARCHAR](100) DEFAULT '' NOT NULL ,
		
		[ultagg] [datetime] DEFAULT (getdate()) NOT NULL,
		[utente] [varchar](20) DEFAULT '' NOT NULL,
		CONSTRAINT [PK_TipoTrasporto_1] PRIMARY KEY CLUSTERED
		(
		
			[IdTpTrasporto] ASC
		) WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]

		) ON [PRIMARY]
		GO

*
*/
public class TipoTrasporto extends AppPDC {

  private static final long serialVersionUID = 1L;

  /** Nome predefinito di questo PDC */
  public static final String CSZ_PDCName = "TipoTrasporto";

  /** tabella del database associata a questo PDC */
  public static final String CSZ_DBTable = "TipoTrasporto";

  // Campi chiave 
  /**  */
  public static final String CSZcol_IdTpTrasporto = "IdTpTrasporto";
  public static final String CSZds_IdTpTrasporto = "Id TpTrasporto";
  private IntAttr   c_IdTpTrasporto;
 	
  

  // Campi non chiave 
  /**  */
  public static final String CSZcol_NomeTpTrasporto = "NomeTpTrasporto";
  public static final String CSZds_NomeTpTrasporto = "Nome TpTrasporto";
  public static final Integer CSZcol_NomeTpTrasporto_len = 100;
  private CharAttr  c_NomeTpTrasporto;
 	
  
  // =============================================================
  // Relazioni
  
  /** rel Rel-Istanza con preno.pdc.prenota.Giorno */
  public static final String CSZrel_Giorno = "TipoTrasporto_Giorno";
  
  // =============================================================


  /**
   * Costruttore di default.
   */
  public TipoTrasporto()  throws Exception {
    super(CSZ_PDCName);
  }

  /**
   * Costruttore con message handler.

   * @param msh message handler da usare per la messaggistica del PDC.
   */
  public TipoTrasporto(MessHandler msh) throws Exception {
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
  public TipoTrasporto(String nomeOgg) throws Exception {
    super(nomeOgg);
  }

  /**
   * Costruttore con message handler e nome PDC.
   *
   * @param msh message handler da usare per la messaggistica del PDC.
   * @param nomeOgg nome PDC
   *
   * @see #CSZ_PDCName
   * @see #TipoTrasporto(String)
   * @see #TipoTrasporto(MessHandler)
   */
  public TipoTrasporto(MessHandler msh, String nomeOgg) throws Exception {
    super(msh, nomeOgg);
  }

  /**
   * Costruttore con PDC di appoggio e nome.
   * Il PDC viene creato utilizzando il message handler del PDC passato e utilizzando
   * il database associato al DMC di pdcAppoggio per creare un nuovo DMC da associare a questo PDC.
   *
   * @param pdcAppoggio PDC di appoggio da utilizzare per creare questo PDC
   * @param nomeOgg nome PDC da creare
   * @see #TipoTrasporto(PDC)
   */
  public TipoTrasporto(PDC pdcAppoggio, String nomeOgg) throws Exception {
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
   * @see #TipoTrasporto(PDC,String)
   * @see #CSZ_PDCName
   */
  public TipoTrasporto(PDC pdcAppoggio) throws Exception {
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
    
    // IdTpTrasporto
    c_IdTpTrasporto = attr.addIntAttr(CSZcol_IdTpTrasporto, CSZds_IdTpTrasporto, CSZ_DBTable, true);
    c_IdTpTrasporto.setSerial(true);

    
    // NomeTpTrasporto
    c_NomeTpTrasporto = attr.addCharAttr(CSZcol_NomeTpTrasporto, CSZds_NomeTpTrasporto, CSZ_DBTable, false);
    c_NomeTpTrasporto.setMaxLen(CSZcol_NomeTpTrasporto_len); 

    
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

   
      // rel Giorno
      rel = addRelazione(CSZrel_Giorno, Relazione.RELAZ_N, Giorno.class.getName());
      rel.setEnabled(false);
      rel.setJoinType(IJoinInfo.LEFT_OUTER_JOIN);
	  rel.addJoinLink(this.CSZcol_IdTpTrasporto,Giorno.CSZcol_IdTpTrasporto);
      rel.setHasIntegritaReferenziale(true);
      

      
    return true;
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

	public String getNomeTpTrasporto() {
		return c_NomeTpTrasporto.getValue();
	}
	public void setNomeTpTrasporto(String val) {
		c_NomeTpTrasporto.setValue(val);
	}
	public CharAttr getNomeTpTrasporto_attr() {
		return c_NomeTpTrasporto;
	}

  /**
   * Ripristina il pdc da DMCDB.

   * @param i campi chiave
   * @return boolean
   * il metodo ritorna true se viene trovato uno ed un singolo record
   */
  public boolean ripristina(IntAttr idTpTrasporto, DMCDB dmco) throws Exception {
   
    c_IdTpTrasporto.setValue(idTpTrasporto.getValue());
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
