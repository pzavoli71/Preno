package sm.ciscoop.preno.pdc.patente;

import sm.ciscoop.pdc.*;
import sm.ciscoop.dmc.DMCDB;
import sm.ciscoop.dmc.DMCException;
import sm.ciscoop.stdlibs.baseutils.errors.MessHandler;
import sm.ciscoop.preno.pdc.AppPDC;

import sm.ciscoop.preno.pdc.patente.DomandaQuiz;
import sm.ciscoop.preno.pdc.patente.Test;
import sm.ciscoop.sec.pdc.zUtente;
import sm.ciscoop.jbb.IJoinInfo;
import sm.ciscoop.jbb.JBB;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;


/*-
 * Title:
 * Description:  Lista per Quiz
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
		CREATE TABLE [dbo].[ESA_Quiz](
		[CdUtente] [int] DEFAULT 0 NOT NULL ,
		[IdQuiz] [int] IDENTITY(1,1) ,
		[DtCreazioneTest] [DateTime] ,
		[DtInizioTest] [DateTime] ,
		[EsitoTest] [int] DEFAULT 0 NOT NULL ,
		[DtFineTest] [DateTime] ,
		[bRispSbagliate] [int] DEFAULT 0 NOT NULL ,
		
		[ultagg] [datetime] DEFAULT (getdate()) NOT NULL,
		[utente] [varchar](20) DEFAULT '' NOT NULL,
		CONSTRAINT [PK_ESA_Quiz_1] PRIMARY KEY CLUSTERED
		(
		
			[IdQuiz] ASC
		) WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]

		) ON [PRIMARY]
		GO

*
*/
public class Quiz extends AppPDC {

  private static final long serialVersionUID = 1L;

  /** Nome predefinito di questo PDC */
  public static final String CSZ_PDCName = "Quiz";

  /** tabella del database associata a questo PDC */
  public static final String CSZ_DBTable = "ESA_Quiz";

  // Campi chiave 
  /**  */
  public static final String CSZcol_IdQuiz = "IdQuiz";
  public static final String CSZds_IdQuiz = "Id Test";
  private IntAttr   c_IdQuiz;
 	
  

  // Campi non chiave 
  /**  */
  public static final String CSZcol_CdUtente = "CdUtente";
  public static final String CSZds_CdUtente = "CdUtente";
  private IntAttr   c_CdUtente;
 	
  
  /**  */
  public static final String CSZcol_DtCreazioneTest = "DtCreazioneTest";
  public static final String CSZds_DtCreazioneTest = "Dt CreazioneTest";
  private DateTimeAttr  c_DtCreazioneTest;
 	
  
  /**  */
  public static final String CSZcol_DtInizioTest = "DtInizioTest";
  public static final String CSZds_DtInizioTest = "Dt InizioTest";
  private DateTimeAttr  c_DtInizioTest;
 	
  
  /** Risposte Esatte */
  public static final String CSZcol_EsitoTest = "EsitoTest";
  public static final String CSZds_EsitoTest = "Esito Test";
  private IntAttr   c_EsitoTest;
 	
  
  /**  */
  public static final String CSZcol_DtFineTest = "DtFineTest";
  public static final String CSZds_DtFineTest = "Dt FineTest";
  private DateTimeAttr  c_DtFineTest;
 	
  
  /** Il quiz recupera dal db principalmente le domande che hanno avuto una prevalenza di risposte sbagliate. */
  public static final String CSZcol_bRispSbagliate = "bRispSbagliate";
  public static final String CSZds_bRispSbagliate = "bRispSbagliate";
  private BoolAttr  c_bRispSbagliate;
 	
  
  // =============================================================
  // Relazioni
  
  /** rel Rel-TuttoParti con preno.pdc.patente.DomandaQuiz */
  public static final String CSZrel_DomandaQuiz = "Quiz_DomandaQuiz";
  
  /** rel Rel-TuttoParti con preno.pdc.patente.Test */
  public static final String CSZrel_Test = "Quiz_Test";
  
  /** rel Rel-Istanza con preno.pdc.abilitazione.zUtente */
  public static final String CSZrel_zUtente = "Quiz_zUtente";
  
  // =============================================================


  /**
   * Costruttore di default.
   */
  public Quiz()  throws Exception {
    super(CSZ_PDCName);
  }

  /**
   * Costruttore con message handler.

   * @param msh message handler da usare per la messaggistica del PDC.
   */
  public Quiz(MessHandler msh) throws Exception {
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
  public Quiz(String nomeOgg) throws Exception {
    super(nomeOgg);
  }

  /**
   * Costruttore con message handler e nome PDC.
   *
   * @param msh message handler da usare per la messaggistica del PDC.
   * @param nomeOgg nome PDC
   *
   * @see #CSZ_PDCName
   * @see #Quiz(String)
   * @see #Quiz(MessHandler)
   */
  public Quiz(MessHandler msh, String nomeOgg) throws Exception {
    super(msh, nomeOgg);
  }

  /**
   * Costruttore con PDC di appoggio e nome.
   * Il PDC viene creato utilizzando il message handler del PDC passato e utilizzando
   * il database associato al DMC di pdcAppoggio per creare un nuovo DMC da associare a questo PDC.
   *
   * @param pdcAppoggio PDC di appoggio da utilizzare per creare questo PDC
   * @param nomeOgg nome PDC da creare
   * @see #Quiz(PDC)
   */
  public Quiz(PDC pdcAppoggio, String nomeOgg) throws Exception {
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
   * @see #Quiz(PDC,String)
   * @see #CSZ_PDCName
   */
  public Quiz(PDC pdcAppoggio) throws Exception {
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
    
    // IdQuiz
    c_IdQuiz = attr.addIntAttr(CSZcol_IdQuiz, CSZds_IdQuiz, CSZ_DBTable, true);
    c_IdQuiz.setSerial(true);

    
    // CdUtente
    c_CdUtente = attr.addIntAttr(CSZcol_CdUtente, CSZds_CdUtente, CSZ_DBTable, false);


    
    // DtCreazioneTest
    c_DtCreazioneTest = attr.addDateTimeAttr(CSZcol_DtCreazioneTest, CSZds_DtCreazioneTest, CSZ_DBTable, false);


    
    // DtInizioTest
    c_DtInizioTest = attr.addDateTimeAttr(CSZcol_DtInizioTest, CSZds_DtInizioTest, CSZ_DBTable, false);


    
    // EsitoTest
    c_EsitoTest = attr.addIntAttr(CSZcol_EsitoTest, CSZds_EsitoTest, CSZ_DBTable, false);


    
    // DtFineTest
    c_DtFineTest = attr.addDateTimeAttr(CSZcol_DtFineTest, CSZds_DtFineTest, CSZ_DBTable, false);


    
    // bRispSbagliate
    c_bRispSbagliate = attr.addBoolAttr(CSZcol_bRispSbagliate, CSZds_bRispSbagliate, CSZ_DBTable, false);
   c_bRispSbagliate.setUseDefaultIfNull(true);

    
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

   
      // rel DomandaQuiz
      rel = addRelazione(CSZrel_DomandaQuiz, Relazione.RELAZ_N, DomandaQuiz.class.getName());
      rel.setEnabled(false);
      rel.setJoinType(IJoinInfo.LEFT_OUTER_JOIN);
	  rel.addJoinLink(this.CSZcol_IdQuiz,DomandaQuiz.CSZcol_IdQuiz);
      rel.setHasIntegritaReferenziale(true);
      

      
      // rel Test
      rel = addRelazione(CSZrel_Test, Relazione.RELAZ_N, Test.class.getName());
      rel.setEnabled(false);
      rel.setJoinType(IJoinInfo.LEFT_OUTER_JOIN);
	  rel.addJoinLink(this.CSZcol_IdQuiz,Test.CSZcol_IdQuiz);
      rel.setHasIntegritaReferenziale(true);
      

      
      // rel zUtente
      rel = addRelazione(CSZrel_zUtente, Relazione.RELAZ_1, zUtente.class.getName());
      rel.setEnabled(false);
      

      
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
  public zUtente getPDCzUtente() throws Exception {
    return (zUtente) loadPDCCollegato(CSZrel_zUtente, true);
  }
 
	public Integer getIdQuiz() {
		return c_IdQuiz.getValue();
	}
	public void setIdQuiz(Integer val) {
		c_IdQuiz.setValue(val);
	}
	public IntAttr getIdQuiz_attr() {
		return c_IdQuiz;
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

	public Date getDtCreazioneTest() {
		return c_DtCreazioneTest.getValue();
	}
	public void setDtCreazioneTest(Date val) {
		c_DtCreazioneTest.setValue(val);
	}
	public DateTimeAttr getDtCreazioneTest_attr() {
		return c_DtCreazioneTest;
	}

	public Date getDtInizioTest() {
		return c_DtInizioTest.getValue();
	}
	public void setDtInizioTest(Date val) {
		c_DtInizioTest.setValue(val);
	}
	public DateTimeAttr getDtInizioTest_attr() {
		return c_DtInizioTest;
	}

	public Integer getEsitoTest() {
		return c_EsitoTest.getValue();
	}
	public void setEsitoTest(Integer val) {
		c_EsitoTest.setValue(val);
	}
	public IntAttr getEsitoTest_attr() {
		return c_EsitoTest;
	}

	public Date getDtFineTest() {
		return c_DtFineTest.getValue();
	}
	public void setDtFineTest(Date val) {
		c_DtFineTest.setValue(val);
	}
	public DateTimeAttr getDtFineTest_attr() {
		return c_DtFineTest;
	}

	public Boolean getbRispSbagliate() {
		return c_bRispSbagliate.getValue();
	}
	public void setbRispSbagliate(Boolean val) {
		c_bRispSbagliate.setValue(val);
	}
	public BoolAttr getbRispSbagliate_attr() {
		return c_bRispSbagliate;
	}

  /**
   * Ripristina il pdc da DMCDB.

   * @param i campi chiave
   * @return boolean
   * il metodo ritorna true se viene trovato uno ed un singolo record
   */
  public boolean ripristina(IntAttr idQuiz, DMCDB dmco) throws Exception {
   
    c_IdQuiz.setValue(idQuiz.getValue());
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
	protected boolean afterSave() throws Exception {
		if ( !super.afterSave())
			return false;
		if ( isInInserimento()) {
			JBB jb = getJBB();
			Hashtable<Integer, Hashtable<Integer, BigDecimal>> capitoli = new Hashtable<>(); 
			if ( getbRispSbagliate()) {
				// Carico l'array delle domande da fare in maniera inversa rispetto a quante risposte sbagliate sono state 
				// date nel passato
				String ls = "select d.IdCapitolo, d.IdDomanda, d.Asserzione, (";
				ls += " select count(*) from ESA_DomandaQuiz dq where dq.IdDomanda = d.IdDomanda";
				ls += " ) as ConteggioDomandeFatte,";
				ls += " IsNull(t.RisposteSbagliate,0) as RisposteSbagliate";
				ls += " from ESA_Domanda d left outer join"; 
				ls += " (";
				ls += " select dd.IdDomanda, count(rq.IdDomandaTest) as RisposteSbagliate";
				ls += " from esa_rispquiz rq inner join ESA_Domanda d1 on d1.IdDomanda = rq.IdDomanda";
				ls += " inner join ESA_Domanda dd on dd.IdCapitolo = d1.IdCapitolo and dd.IdDom = d1.IdDom and dd.IdProgr = 0";
				ls += " inner join ESA_DomandaQuiz dq on dq.IdDomandaTest = rq.IdDomandaTest";
				ls += " where rq.EsitoRisp = -1 and rq.bControllata = -1";
				ls += " and"; 
				ls += " ((d1.valore != 0 and RespVero = 0) or (d1.valore = 0 and RespFalso = 0))";
				ls += " group by dd.IdDomanda";
				ls += " ) t";
				ls += " on t.IdDomanda = d.IdDomanda";
				ls += " where d.idprogr = 0";
				ls += " ORDER BY D.iDcAPITOLO, D.iDdOMANDA";
				List<Map<String, Object>> righed = jb.getRowsAsMap(ls);
				if ( righed == null) 
					throw new Exception("Non trovo l'elenco delle domande sbagliate");
				int somma = 0, numdomande = 0, oldidcap = 0;;
				for (Map<String, Object> riga: righed) {
					Integer idcapitolo = (Integer) riga.get("idcapitolo");
					Hashtable<Integer, BigDecimal> htdomande = capitoli.get(idcapitolo);
					if ( htdomande == null) {
						// Ho trovato un nuovo capitolo. Aggiorno i pesi delle domande del capitolo precedente, rispetto a 1.0
						if ( oldidcap > 0) {
							htdomande = capitoli.get(oldidcap);
							Enumeration<Integer> en = htdomande.keys();
							while ( en.hasMoreElements()) {
								Integer iddom = en.nextElement();
								BigDecimal bd = htdomande.get(iddom);
								bd = new BigDecimal( bd.doubleValue() / somma);
								htdomande.put(iddom, bd);
							}
						}
						htdomande = new Hashtable<Integer, BigDecimal>();
						capitoli.put(idcapitolo, htdomande);
						somma = 0; numdomande = 0;
					}
					BigDecimal bdValore;
					Integer iddomanda = (Integer) riga.get("iddomanda");
					int conteggiodomandefatte = (Integer) riga.get("conteggiodomandefatte");
					int rispsbagliate = (Integer) riga.get("rispostesbagliate");
					if ( conteggiodomandefatte == 0)
						bdValore = new BigDecimal(1000.0);
					else
						bdValore = new BigDecimal(rispsbagliate);
					htdomande.put(iddomanda, bdValore);
					somma += bdValore.doubleValue();
					numdomande++;
					oldidcap = idcapitolo;
				}				
				if ( oldidcap > 0) {
					Hashtable<Integer, BigDecimal> htdomande = capitoli.get(oldidcap);
					Enumeration<Integer> en = htdomande.keys();
					while ( en.hasMoreElements()) {
						Integer iddom = en.nextElement();
						BigDecimal bd = htdomande.get(iddom);
						bd = new BigDecimal( bd.doubleValue() / somma);
						htdomande.put(iddom, bd);
					}
				}
			}
			String ls = "select distinct IdCapitolo from " + Domanda.CSZ_DBTable + " order by 1";
			List<Map<String, Object>> righecapitoli = jb.getRowsAsMap(ls);
			if ( righecapitoli == null) 
				throw new Exception("Non trovo i capitoli dentro le domande.");
			for (Map<String, Object> riga: righecapitoli) {
				int idcapitolo = (Integer) riga.get("idcapitolo");
				ls = "select IdDomanda, IdDom from " + Domanda.CSZ_DBTable + " where idprogr = 0 and idcapitolo = " + idcapitolo + " order by 2";
				List<Map<String, Object>> righedomande = jb.getRowsAsMap(ls);
				if ( righedomande == null) 
					throw new Exception("Non trovo le domande dentro il capitolo " + idcapitolo);
				int elemrandomtrovato[] = new int[3];
				elemrandomtrovato[0] = -1; double rnd0 = -1.0;
				for ( int i = 0; i < righedomande.size(); i++) {
					double rnd2 = Math.random();
					if ( capitoli.size() > 0) {
						Hashtable<Integer, BigDecimal> htdomande = capitoli.get(idcapitolo);
						BigDecimal bd = htdomande.get((Integer) righedomande.get(i).get("iddomanda"));
						rnd2 = bd.doubleValue();
					}
					if ( rnd2 > rnd0) {
						elemrandomtrovato[0] = i;
						rnd0 = rnd2;
					}
				}
				Map<String, Object> rigatrovata = righedomande.get(elemrandomtrovato[0]);
				int iddomanda = (Integer) rigatrovata.get("iddomanda");
				int iddom = (Integer) rigatrovata.get("iddom");
				DMCDB dmc = new DMCDB(getMsgHandler(), getDMCDB());
				DomandaQuiz domanda = new DomandaQuiz(getMsgHandler());
				domanda.setDMC(dmc);
				domanda.setIdQuiz(getIdQuiz());
				domanda.setIdDomanda(iddomanda);
				domanda.inserisci();
				
				// Cerco tre quesiti a caso della domanda prescelta.
				elemrandomtrovato[0] = -1; elemrandomtrovato[1]  = -1; elemrandomtrovato[2]  = -1;
				double rnd1 = -1.0, rnd2 = -2.0, rnd3 = -3.0;
				ls = "select IdDomanda, IdProgr from " + Domanda.CSZ_DBTable + " where idprogr > 0 and idcapitolo = " + idcapitolo + " and iddom = " + iddom + " order by 2";
				righedomande = jb.getRowsAsMap(ls);
				if ( righedomande == null) 
					throw new Exception("Non trovo le domande del capitolo " + idcapitolo + " della domanda " + iddom);
				double v[] = new double[righedomande.size()];
				for ( int i = 0; i < righedomande.size(); i++) {
					double rnd4 = Math.random();
					v[i] = rnd4;
				}
				rnd0 = -1;
				for ( int i = 0; i < righedomande.size(); i++) {
					if ( v[i] > rnd0) {
						elemrandomtrovato[0] = i;
						rnd0 = v[i];
					}
				}
				v[elemrandomtrovato[0]] = -1.0; rnd0 = -1;
				for ( int i = 0; i < righedomande.size(); i++) {
					if ( v[i] > rnd0) {
						elemrandomtrovato[1] = i;
						rnd0 = v[i];
					}
				}
				v[elemrandomtrovato[1]] = -1.0; rnd0 = -1;
				for ( int i = 0; i < righedomande.size(); i++) {
					if ( v[i] > rnd0) {
						elemrandomtrovato[2] = i;
						rnd0 = v[i];
					}
				}
				for ( int i = 0; i < 3; i++) {
					rigatrovata = righedomande.get(elemrandomtrovato[i]);
					iddomanda = (Integer) rigatrovata.get("iddomanda");
					dmc = new DMCDB(getMsgHandler(), getDMCDB());
					RispQuiz risp = new RispQuiz(getMsgHandler());
					risp.setDMC(dmc);
					risp.setIdDomandaTest(domanda.getIdDomandaTest());
					risp.setIdDomanda(iddomanda);
					risp.inserisci();
				}
				
			}
		}
		return true;
	}
}
