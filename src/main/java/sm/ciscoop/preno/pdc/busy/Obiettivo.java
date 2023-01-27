package sm.ciscoop.preno.pdc.busy;

import sm.ciscoop.pdc.*;
import sm.ciscoop.dmc.DMCDB;
import sm.ciscoop.dmc.DMCException;
import sm.ciscoop.stdlibs.baseutils.errors.MessHandler;
import sm.ciscoop.preno.pdc.AppPDC;

import sm.ciscoop.preno.pdc.soggetti.Soggetto;
import sm.ciscoop.preno.pdc.busy.TipoOccupazione;
import sm.ciscoop.preno.pdc.busy.Lavoro;
import sm.ciscoop.preno.pdc.busy.DocObiettivo;
import sm.ciscoop.jbb.IJoinInfo;

import java.math.BigDecimal;
import sm.ciscoop.stdlibs.baseutils.format.DateTimeFormatter;
import sm.ciscoop.stdlibs.baseutils.format.IntervalParts;
import java.util.Date;


/*-
 * Title:
 * Description:  Lista per Obiettivo
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
		CREATE TABLE [dbo].[Obiettivo](
		[IdSoggetto] [int] DEFAULT 0 NOT NULL ,
		[TpOccup] [int] DEFAULT 0 NOT NULL ,
		[IdObiettivo] [int] IDENTITY(1,1) ,
		[DtInizioObiettivo] [DateTime] ,
		[DescObiettivo] [VARCHAR](1000) DEFAULT '' NOT NULL ,
		[DtScadenzaObiettivo] [DateTime] ,
		[MinPrevisti] [int] DEFAULT 0 NOT NULL ,
		[DtFineObiettivo] [DateTime] ,
		[NotaObiettivo] [VARCHAR](2000) DEFAULT '' NOT NULL ,
		[PercCompletamento] [DECIMAL](18,2) DEFAULT 0 NOT NULL ,
		
		[ultagg] [datetime] DEFAULT (getdate()) NOT NULL,
		[utente] [varchar](20) DEFAULT '' NOT NULL,
		CONSTRAINT [PK_Obiettivo_1] PRIMARY KEY CLUSTERED
		(
		
			[IdObiettivo] ASC
		) WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]

		) ON [PRIMARY]
		GO

*
*/
public class Obiettivo extends AppPDC {

  private static final long serialVersionUID = 1L;

  /** Nome predefinito di questo PDC */
  public static final String CSZ_PDCName = "Obiettivo";

  /** tabella del database associata a questo PDC */
  public static final String CSZ_DBTable = "Obiettivo";

  // Campi chiave 
  /**  */
  public static final String CSZcol_IdObiettivo = "IdObiettivo";
  public static final String CSZds_IdObiettivo = "Id Obiettivo";
  private IntAttr   c_IdObiettivo;
 	
  

  // Campi non chiave 
  /**  */
  public static final String CSZcol_IdSoggetto = "IdSoggetto";
  public static final String CSZds_IdSoggetto = "Id Soggetto";
  private IntAttr   c_IdSoggetto;
 	
  
  /**  */
  public static final String CSZcol_TpOccup = "TpOccup";
  public static final String CSZds_TpOccup = "Tp Occup";
  private IntAttr   c_TpOccup;
 	
  
  /**  */
  public static final String CSZcol_DtInizioObiettivo = "DtInizioObiettivo";
  public static final String CSZds_DtInizioObiettivo = "Dt InizioObiettivo";
  private DateTimeAttr  c_DtInizioObiettivo;
 	
  
  /**  */
  public static final String CSZcol_DescObiettivo = "DescObiettivo";
  public static final String CSZds_DescObiettivo = "Desc Obiettivo";
  public static final Integer CSZcol_DescObiettivo_len = 1000;
  private CharAttr  c_DescObiettivo;
 	
  
  /**  */
  public static final String CSZcol_DtScadenzaObiettivo = "DtScadenzaObiettivo";
  public static final String CSZds_DtScadenzaObiettivo = "Dt FineObiettivo";
  private DateTimeAttr  c_DtScadenzaObiettivo;
 	
  
  /** Minuti previsti per questo lavoro */
  public static final String CSZcol_MinPrevisti = "MinPrevisti";
  public static final String CSZds_MinPrevisti = "Min Previsti";
  private IntAttr   c_MinPrevisti;
 	
  
  /**  */
  public static final String CSZcol_DtFineObiettivo = "DtFineObiettivo";
  public static final String CSZds_DtFineObiettivo = "Dt FineObiettivo";
  private DateTimeAttr  c_DtFineObiettivo;
 	
  
  /**  */
  public static final String CSZcol_NotaObiettivo = "NotaObiettivo";
  public static final String CSZds_NotaObiettivo = "Nota Obiettivo";
  public static final Integer CSZcol_NotaObiettivo_len = 2000;
  private CharAttr  c_NotaObiettivo;
 	
  
  /**  */
  public static final String CSZcol_PercCompletamento = "PercCompletamento";
  public static final String CSZds_PercCompletamento = "Perc Completamento";
  private BDAttr  c_PercCompletamento;
 	
  
  // =============================================================
  // Relazioni
  
  /** rel Rel-Istanza con preno.pdc.soggetti.Soggetto */
  public static final String CSZrel_Soggetto = "Obiettivo_Soggetto";
  
  /** rel Rel-Istanza con preno.pdc.busy.TipoOccupazione */
  public static final String CSZrel_TipoOccupazione = "Obiettivo_TipoOccupazione";
  
  /** rel Rel-Istanza con preno.pdc.busy.Lavoro */
  public static final String CSZrel_Lavoro = "Obiettivo_Lavoro";
  
  /** rel Rel-Istanza con preno.pdc.busy.DocObiettivo */
  public static final String CSZrel_DocObiettivo = "Obiettivo_DocObiettivo";
  
  // =============================================================


  /**
   * Costruttore di default.
   */
  public Obiettivo()  throws Exception {
    super(CSZ_PDCName);
  }

  /**
   * Costruttore con message handler.

   * @param msh message handler da usare per la messaggistica del PDC.
   */
  public Obiettivo(MessHandler msh) throws Exception {
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
  public Obiettivo(String nomeOgg) throws Exception {
    super(nomeOgg);
  }

  /**
   * Costruttore con message handler e nome PDC.
   *
   * @param msh message handler da usare per la messaggistica del PDC.
   * @param nomeOgg nome PDC
   *
   * @see #CSZ_PDCName
   * @see #Obiettivo(String)
   * @see #Obiettivo(MessHandler)
   */
  public Obiettivo(MessHandler msh, String nomeOgg) throws Exception {
    super(msh, nomeOgg);
  }

  /**
   * Costruttore con PDC di appoggio e nome.
   * Il PDC viene creato utilizzando il message handler del PDC passato e utilizzando
   * il database associato al DMC di pdcAppoggio per creare un nuovo DMC da associare a questo PDC.
   *
   * @param pdcAppoggio PDC di appoggio da utilizzare per creare questo PDC
   * @param nomeOgg nome PDC da creare
   * @see #Obiettivo(PDC)
   */
  public Obiettivo(PDC pdcAppoggio, String nomeOgg) throws Exception {
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
   * @see #Obiettivo(PDC,String)
   * @see #CSZ_PDCName
   */
  public Obiettivo(PDC pdcAppoggio) throws Exception {
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
    
    // IdObiettivo
    c_IdObiettivo = attr.addIntAttr(CSZcol_IdObiettivo, CSZds_IdObiettivo, CSZ_DBTable, true);
    c_IdObiettivo.setSerial(true);

    
    // IdSoggetto
    c_IdSoggetto = attr.addIntAttr(CSZcol_IdSoggetto, CSZds_IdSoggetto, CSZ_DBTable, false);


    
    // TpOccup
    c_TpOccup = attr.addIntAttr(CSZcol_TpOccup, CSZds_TpOccup, CSZ_DBTable, false);


    
    // DtInizioObiettivo
    c_DtInizioObiettivo = attr.addDateTimeAttr(CSZcol_DtInizioObiettivo, CSZds_DtInizioObiettivo, CSZ_DBTable, false);
    ((DateTimeFormatter) (c_DtInizioObiettivo.getFormatter())).setFrom("year");
    ((DateTimeFormatter) (c_DtInizioObiettivo.getFormatter())).setTo("minute"); 

    
    // DescObiettivo
    c_DescObiettivo = attr.addCharAttr(CSZcol_DescObiettivo, CSZds_DescObiettivo, CSZ_DBTable, false);
    c_DescObiettivo.setMaxLen(CSZcol_DescObiettivo_len); 

    
    // DtScadenzaObiettivo
    c_DtScadenzaObiettivo = attr.addDateTimeAttr(CSZcol_DtScadenzaObiettivo, CSZds_DtScadenzaObiettivo, CSZ_DBTable, false);
    ((DateTimeFormatter) (c_DtScadenzaObiettivo.getFormatter())).setFrom("year");
    ((DateTimeFormatter) (c_DtScadenzaObiettivo.getFormatter())).setTo("minute"); 

    
    // MinPrevisti
    c_MinPrevisti = attr.addIntAttr(CSZcol_MinPrevisti, CSZds_MinPrevisti, CSZ_DBTable, false);


    
    // DtFineObiettivo
    c_DtFineObiettivo = attr.addDateTimeAttr(CSZcol_DtFineObiettivo, CSZds_DtFineObiettivo, CSZ_DBTable, false);
    ((DateTimeFormatter) (c_DtFineObiettivo.getFormatter())).setFrom("year");
    ((DateTimeFormatter) (c_DtFineObiettivo.getFormatter())).setTo("minute"); 

    
    // NotaObiettivo
    c_NotaObiettivo = attr.addCharAttr(CSZcol_NotaObiettivo, CSZds_NotaObiettivo, CSZ_DBTable, false);
    c_NotaObiettivo.setMaxLen(CSZcol_NotaObiettivo_len); 

    
    // PercCompletamento
    c_PercCompletamento = attr.addBDAttr(CSZcol_PercCompletamento, CSZds_PercCompletamento, CSZ_DBTable, false);


    
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
      

      
      // rel TipoOccupazione
      rel = addRelazione(CSZrel_TipoOccupazione, Relazione.RELAZ_1, TipoOccupazione.class.getName());
      rel.setEnabled(false);
      

      
      // rel Lavoro
      rel = addRelazione(CSZrel_Lavoro, Relazione.RELAZ_N, Lavoro.class.getName());
      rel.setEnabled(false);
      rel.setJoinType(IJoinInfo.LEFT_OUTER_JOIN);
	  rel.addJoinLink(this.CSZcol_IdObiettivo,Lavoro.CSZcol_IdObiettivo);
      rel.setHasIntegritaReferenziale(true);
      

      
      // rel DocObiettivo
      rel = addRelazione(CSZrel_DocObiettivo, Relazione.RELAZ_N, DocObiettivo.class.getName());
      rel.setEnabled(false);
      rel.setJoinType(IJoinInfo.LEFT_OUTER_JOIN);
	  rel.addJoinLink(this.CSZcol_IdObiettivo,DocObiettivo.CSZcol_IdObiettivo);
      rel.setHasIntegritaReferenziale(true);
      

      
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
   * Ritorna il PDC TipoOccupazione collegato.
   * Utilizza la relazione per farsi dare il pdc collegato
   * Se e' il caso effettua il ripristino.
   *
   * @return TipoOccupazione il PDC collegato oppure null se non tutti i
   *                campi chiave sono valorizzati
   * @throws Exception in caso di errori in lettura da database
   */
  public TipoOccupazione getPDCTipoOccupazione() throws Exception {
    return (TipoOccupazione) loadPDCCollegato(CSZrel_TipoOccupazione, true);
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

	public Integer getIdSoggetto() {
		return c_IdSoggetto.getValue();
	}
	public void setIdSoggetto(Integer val) {
		c_IdSoggetto.setValue(val);
	}
	public IntAttr getIdSoggetto_attr() {
		return c_IdSoggetto;
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

	public Date getDtInizioObiettivo() {
		return c_DtInizioObiettivo.getValue();
	}
	public void setDtInizioObiettivo(Date val) {
		c_DtInizioObiettivo.setValue(val);
	}
	public DateTimeAttr getDtInizioObiettivo_attr() {
		return c_DtInizioObiettivo;
	}

	public String getDescObiettivo() {
		return c_DescObiettivo.getValue();
	}
	public void setDescObiettivo(String val) {
		c_DescObiettivo.setValue(val);
	}
	public CharAttr getDescObiettivo_attr() {
		return c_DescObiettivo;
	}

	public Date getDtScadenzaObiettivo() {
		return c_DtScadenzaObiettivo.getValue();
	}
	public void setDtScadenzaObiettivo(Date val) {
		c_DtScadenzaObiettivo.setValue(val);
	}
	public DateTimeAttr getDtScadenzaObiettivo_attr() {
		return c_DtScadenzaObiettivo;
	}

	public Integer getMinPrevisti() {
		return c_MinPrevisti.getValue();
	}
	public void setMinPrevisti(Integer val) {
		c_MinPrevisti.setValue(val);
	}
	public IntAttr getMinPrevisti_attr() {
		return c_MinPrevisti;
	}

	public Date getDtFineObiettivo() {
		return c_DtFineObiettivo.getValue();
	}
	public void setDtFineObiettivo(Date val) {
		c_DtFineObiettivo.setValue(val);
	}
	public DateTimeAttr getDtFineObiettivo_attr() {
		return c_DtFineObiettivo;
	}

	public String getNotaObiettivo() {
		return c_NotaObiettivo.getValue();
	}
	public void setNotaObiettivo(String val) {
		c_NotaObiettivo.setValue(val);
	}
	public CharAttr getNotaObiettivo_attr() {
		return c_NotaObiettivo;
	}

	public BigDecimal getPercCompletamento() {
		return c_PercCompletamento.getValue();
	}
	public void setPercCompletamento(BigDecimal val) {
		c_PercCompletamento.setValue(val);
	}
	public void setPercCompletamento(String val) {
		c_PercCompletamento.setValue(val);
	}
	public BDAttr getPercCompletamento_attr() {
		return c_PercCompletamento;
	}

  /**
   * Ripristina il pdc da DMCDB.

   * @param i campi chiave
   * @return boolean
   * il metodo ritorna true se viene trovato uno ed un singolo record
   */
  public boolean ripristina(IntAttr idObiettivo, DMCDB dmco) throws Exception {
   
    c_IdObiettivo.setValue(idObiettivo.getValue());
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
