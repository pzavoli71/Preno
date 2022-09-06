package sm.ciscoop.preno.hic.patente;

import javax.servlet.annotation.WebServlet;

import sm.ciscoop.pdc.*;
import sm.ciscoop.preno.pdc.patente.*;
import sm.ciscoop.sec.pdc.zUtente;
import sm.ciscoop.stdlibs.baseutils.exceptions.CISException;
import sm.ciscoop.stdlibs.baseutils.types.Text;
import sm.ciscoop.stdlibs.db.output.AnySelect;
import sm.ciscoop.util.RequestParameterMap;
import sm.ciscoop.dmc.DMCDB;
import sm.ciscoop.dmc.IspettoreDelleQuery;
import sm.ciscoop.servlet.OpRes;
import java.util.*;

	
import java.util.Enumeration;
import sm.ciscoop.dmc.SimpleList;
import sm.ciscoop.jbb.JBB;
import sm.ciscoop.dmc.DMCDB;
import sm.ciscoop.preno.hic.masks.AppSListMask;

/*-
 * Title:
 * Description:  SimpleList per Quiz
 * Copyright:    Copyright (c)
 * Company:      CIScoop
 * @author
 * @version 1.0
 *
 * 

	
	
	DECLARE @idnodo INT = 0;
	SET @idnodo = (select MAX(idnodo) FROM zTrans) + 1;
	if @idnodo IS NULL
	  BEGIN
	    SET @idnodo = 1
	  END
	print @idnodo
	INSERT INTO zTrans ([CdPDC],[DsPDC],[IdNodo],[IdPadre],[Flag],[ultagg],[utente]) VALUES ('HSLstQuiz' ,'HSLstQuiz',@idnodo,0,0,GETDATE(),'appl')
	INSERT INTO zPermessi([CdGruppo],[CdPDC], [Descrizione], [Permesso], [ultagg], [utente]) VALUES (1, 'HSLstQuiz',' ','LAGMIRVC',getdate(),'appl')
	--INSERT INTO zPermessi([CdGruppo],[CdPDC], [Descrizione], [Permesso], [ultagg], [utente]) VALUES (xxx, 'HSLstQuiz',' ','LAGMIRVC',getdate(),'appl')

	
	
	//Voce di Menu.xml
	<Voce funzione="HSLstQuiz" href="/patente/HSLstQuiz">Lista Semplice HSLstQuiz</Voce>

*
*/

@WebServlet(value=HSLstQuiz.SERVLET_URL, name = HSLstQuiz.SERVLET_NAME) 
public class HSLstQuiz extends AppSListMask<Quiz> implements IspettoreDelleQuery {
  
	
	private static final long  serialVersionUID = 1L;

	public static final String SERVLET_NAME     = "HSLstQuiz";
	public static final String SERVLET_URL      = "/patente/" + HSLstQuiz.SERVLET_NAME;

	BoolAttr c_bRisposteSbagliate;
	boolean bQueryRisposte = false;
	@Override
	public void initMsk() throws Exception  {
		super.initMsk();
		Attributi attrs = getAttributi();
		c_bRisposteSbagliate = (BoolAttr ) attrs.add("bRisposteSbagliate", PDCType.BOOLEAN);
		c_bRisposteSbagliate.setValue(false);
		
	}

	@Override
	public String getSelect() throws Exception {
		String ls = "select top 200 * from "+Quiz.CSZ_DBTable + " left outer join zUtente on zutente.cdutente = " + Quiz.CSZ_DBTable + ".cdUtente ";
		
		ls += " WHERE 1 = 1 ";
			if ( !getPDC().getAttributi().get(Quiz.CSZcol_IdQuiz).isEmptyOrNull()) {
				if ( getPDC().getIdQuiz() > 0)
					ls += " AND "+Quiz.CSZ_DBTable+".IdQuiz = "+getPDC().getIdQuiz();
		}
		
		ls += " ORDER BY " + Quiz.CSZ_DBTable + ".IdQuiz desc";
		return ls;
	}

  @Override
  public int getMaxNumRecords() throws Exception {
    if ( isToExcel()) {
    	return 10000;
    }
	return DEFAULT_MAX_NUM_RECORDS;
  }

  /**
   * Trigger chiamato DOPO il caricamento della lista.
   *
   * <br>Passo la lista gia' caricata al trigger.
   * Questi puo' cambiarla completamente prima di restituirla.
   * Se torna null, mantengo qualla che ho caricato qui
   *
   * @param lista Lista
   * @return Lista
   * @throws Exception
   */  
	@Override
	public SimpleList afterLoadLista(SimpleList lista) throws CISException {
		return null;
	}

	/**
   * Trigger chiamato PRIMA del caricamento della lista. La lista che si restituisce 
	 * viene considerata la nuova lista da utilizzare, altrimenti restituire null.
   *
   * @param lista Lista
   * @return Lista
   * @throws Exception
   */
	@Override
	public SimpleList beforeLoadLista(SimpleList lista) throws CISException {
		if ( c_DIMPAGINA.isEmptyNullOrZero()) {
			c_DIMPAGINA.setValue(20);
	  }
		lista.setPageSize(c_DIMPAGINA.getIntValue());
		return null;
	}

	@Override
	public void loadCombo() throws Exception {
		super.loadCombo();
		if ( isSoloDatiCombo()) {
			String desc = getParRicercaComboTesto();
			String cd = getParRicercaComboCodice();
			String camporicerca = getParRicercaComboCampo();
			String lscombo = "";
			// Todo Aggiungere qui i campi filtro dinamici
			
			} else if (isCaricaSoloRelaz()) {
				// Mi è stato richiesto di aprire una relazione tra un pdc e un altro, pertanto la devo caricare e visualizzare (richiesta con ajax)
    			String nomepdc = getParNomePDC();
    			String nomerelaz = getParNomeRelaz();
    			Integer numPagina = getParNumPagina();
    			Integer rowsPerPage = getParRighePerPagina();
    			if (numPagina == null) {
    				numPagina = 0;
    			}
    			if (rowsPerPage == null) {
    				rowsPerPage = 0;
    			}
				Class<?> clazz = Class.forName(nomepdc);
				PDC pdc = (PDC) clazz.newInstance();
				Enumeration<?> en = getRequestParameterMap().getParameterNames();
				while (en.hasMoreElements()) {
					String s = (String) en.nextElement();
					Attributo attr = pdc.getAttributi().get(s);
					if ( attr != null) {
						String v = getRequestParameterMap().getParameter(s);
						attr.setValue(v);
					}
				}
				Object relazNames [] = {nomerelaz, rowsPerPage};
				if ( nomerelaz.equalsIgnoreCase(Quiz.CSZrel_DomandaQuiz)) {
					relazNames = new Object[8];
					relazNames[0] = (String) nomerelaz; relazNames[1] = (Integer) rowsPerPage;
					relazNames[2] = DomandaQuiz.CSZrel_Domanda; relazNames[3] = (Integer) 1;
					relazNames[4] = DomandaQuiz.CSZrel_RispQuiz; relazNames[5] = (Integer) 3;
					relazNames[6] = RispQuiz.CSZrel_Domanda; relazNames[7] = (Integer) 1;
					bQueryRisposte = true;
				}
				if ( nomerelaz.equalsIgnoreCase(DomandaQuiz.CSZrel_RispQuiz)) {
					relazNames = new Object[4];
					relazNames[0] = (String) nomerelaz; relazNames[1] = (Integer) rowsPerPage;
					relazNames[2] = RispQuiz.CSZrel_Domanda; relazNames[3] = (Integer) 1;
					bQueryRisposte = true;
				}
				pdc.enableStruct(relazNames, true);
				DMCDB dmc = new DMCDB(getMsgHandler(),getDMC());
				dmc.setQueryInspector(this);
				pdc.setDMC(dmc);
				if ( !pdc.load()) {
					addMessageInfo("Errore in lettura dati Quiz:getxmlCombo:relaz");
					return;
				}
				if ( rowsPerPage > 0)
					pdc.getRelazioneAnnidata(nomerelaz).getLista().setCurrPage(numPagina);
				appendToResponse(pdc);


			} else {
				// AppEnumeratiFactory en = (AppEnumeratiFactory) AppEnumeratiFactory.getInstance();
				// Todo Aggiungere qui i campi filtro statici
				// xml.append(en.getXML(Enumerati.ENUM_...)
			
				String lszUtente = "select CdUtente, IdUtente FROM "+zUtente.CSZ_DBTable;
				lszUtente += " WHERE 1 = 0 "; // Ricordarsi di abilitare il combo togliendo questo filtro
				appendToResponse(new AnySelect(lszUtente, "zUtente"));
			
		}		
	}


  @Override
  protected void eseguiComandoAjax(String nomecomando) throws Exception {
	RequestParameterMap param = getRequestParameterMap();
	switch (nomecomando) {
	  case "ConfermaTest":
		String strIdQuiz = param.getParameter("IdQuiz");
		if (strIdQuiz == null || strIdQuiz.trim().length() == 0) {
		  addMessageError("Manca l'identificativo del test");
		  return ;
		}
		int idQuiz = Integer.parseInt(strIdQuiz);
		if (idQuiz <= 0) {
			addMessageError("L'identificativo del test non è valido");
			return;
		}

		JBB jbb = getJBB();
		try {
		jbb.setUseTransactions(true);
		if ( !jbb.inTransaction())
			jbb.beginTrans();

		String ls = "select count(IdQuiz) from " + Quiz.CSZ_DBTable ;
		ls += " where IdQuiz = " + idQuiz;
		ls = " and dtiniziotest is not null and dtfinetest is null";
		int contarighe = jbb.getCount(ls);
		if (contarighe == 0) {
			throw new Exception("Il quiz non esiste oppure non è ancora iniziato.");			
		}
		
		int Errori = 0;
		ls = "select rq.IdRispTest, rq.IdDomandaTest, d.IdDomanda, d.Valore, RespVero, RespFalso, asserzione from " + DomandaQuiz.CSZ_DBTable + " dq INNER JOIN " + RispQuiz.CSZ_DBTable + " rq ";
		ls += " on rq.IdDomandaTest = dq.IdDomandaTest INNER JOIN " + Domanda.CSZ_DBTable + " d ON d.IdDomanda = rq.IdDomanda ";
		ls += " where dq.IdQuiz = " + idQuiz;		
		List<Map<String, Object>> righe = jbb.getRowsAsMap(ls);
		for ( Map<String, Object> riga : righe) {
			int idrisptest = (Integer) riga.get("idrisptest");
			int valore = (Integer) riga.get("valore");
			int respvero = (Integer) riga.get("respvero");
			int respfalso = (Integer) riga.get("respfalso");
			String risposta = (String) riga.get("asserzione");
			if ( respvero == 0 && respfalso == 0) {
				throw new Exception("La domanda alla risposta " + risposta + " non è stata data.");
			}
			if (valore == 0 && respvero != 0 || valore != 0 && respfalso != 0) {
				ls = "UPDATE " + RispQuiz.CSZ_DBTable + " SET EsitoRisp = -1, bControllata = -1 WHERE IdRispTest = " + idrisptest;
				Errori++;
			}
			else
				ls = "UPDATE " + RispQuiz.CSZ_DBTable + " SET EsitoRisp = 0 , bControllata = -1 WHERE IdRispTest = " + idrisptest;
			if ( jbb.execute(ls) != 1) {
				throw new Exception("Errore in registrazione esito risposta " + risposta + ".");
			}			
		}
		ls = "UPDATE " + Quiz.CSZ_DBTable + " SET DtFineTest = getdate(), EsitoTest = " + (Errori > 0 ? "-" + Errori:"1") + " WHERE IdQuiz = " + idQuiz;
		if ( jbb.execute(ls) != 1) {
			throw new Exception("Errore in registrazione esito quiz.");
		}			
		
		addMessageInfo("Comando Terminato correttamente");
		return;
		} catch(Exception e) {
			if ( jbb.inTransaction())
				jbb.rollbackTrans();	
			addMessageError("Errore in esecuzione." + e.getMessage());
			return;
		} finally {
			if ( jbb.inTransaction())
				jbb.commitTrans();
		}
	  case "IniziaTest":
		strIdQuiz = param.getParameter("IdQuiz");
		if (strIdQuiz == null || strIdQuiz.trim().length() == 0) {
			addMessageError("Manca l'identificativo del test");
			return ;
		}
		idQuiz = Integer.parseInt(strIdQuiz);
		if (idQuiz <= 0) {
			addMessageError("L'identificativo del test non è valido");
			return;
		}

		jbb = getJBB();
		try {
		jbb.setUseTransactions(true);
		if ( !jbb.inTransaction())
			jbb.beginTrans();
		
		String ls = "select DtInizioTest, DtFineTest, EsitoTest from " + Quiz.CSZ_DBTable ;
		ls += " where IdQuiz = " + idQuiz;		
		List<Map<String, Object>> righe = jbb.getRowsAsMap(ls);
		for ( Map<String, Object> riga : righe) {
			Date dtinizio = (Date) riga.get("dtiniziotest");
			Date dtfine = (Date) riga.get("dtinetest");
			if ( dtinizio != null && dtfine == null) {
				throw new Exception("Il quiz è ancora in corso. Devi terminarlo. ");
			}
			ls = "UPDATE " + Quiz.CSZ_DBTable + " SET DtInizioTest = getdate(), DtFineTest = NULL, EsitoTest = 0 WHERE IdQuiz = " + idQuiz;
			if ( jbb.execute(ls) != 1) {
				throw new Exception("Errore in registrazione inizio test.");
			}			
		}
		
		addMessageInfo("Il test è incominciato. Puoi rispondere alle domande.");
		return;
		} catch(Exception e) {
			if ( jbb.inTransaction())
				jbb.rollbackTrans();	
			addMessageError("Errore in esecuzione." + e.getMessage());
			return;
		} finally {
			if ( jbb.inTransaction())
				jbb.commitTrans();
		}
	  case "FineTest":
		strIdQuiz = param.getParameter("IdQuiz");
		if (strIdQuiz == null || strIdQuiz.trim().length() == 0) {
			addMessageError("Manca l'identificativo del test");
			return ;
		}
		idQuiz = Integer.parseInt(strIdQuiz);
		if (idQuiz <= 0) {
			addMessageError("L'identificativo del test non è valido");
			return;
		}

		jbb = getJBB();
		try {
		jbb.setUseTransactions(true);
		if ( !jbb.inTransaction())
			jbb.beginTrans();
		
		String ls = "select DtInizioTest, DtFineTest, EsitoTest from " + Quiz.CSZ_DBTable ;
		ls += " where IdQuiz = " + idQuiz;		
		List<Map<String, Object>> righe = jbb.getRowsAsMap(ls);
		for ( Map<String, Object> riga : righe) {
			Date dtinizio = (Date) riga.get("dtiniziotest");
			Date dtfine = (Date) riga.get("dtinetest");
			if ( dtfine != null || dtinizio == null) {
				throw new Exception("Il quiz è già terminato oppure non è mai iniziato.");
			}
			ls = "UPDATE " + Quiz.CSZ_DBTable + " SET DtFineTest = getdate() , EsitoTest = 0 WHERE IdQuiz = " + idQuiz;
			if ( jbb.execute(ls) != 1) {
				throw new Exception("Errore in registrazione inizio test.");
			}			
		}
		
		addMessageInfo("Comando Terminato correttamente");
		return;
		} catch(Exception e) {
			if ( jbb.inTransaction())
				jbb.rollbackTrans();	
			addMessageError("Errore in esecuzione." + e.getMessage());
			return;
		} finally {
			if ( jbb.inTransaction())
				jbb.commitTrans();
		}

	}
	addMessageInfo("Nessun Comando da eseguire!");
  }

  @Override
  public void inspectQuery(StringBuffer sbSelectList, StringBuffer sbFrom, StringBuffer szWhere,
		  StringBuffer sbGroupBy, StringBuffer sbHaving, StringBuffer sOrderBy) throws Exception {
	    if ( !bQueryRisposte)
	    	return;
	    sOrderBy.setLength(0);
	    sOrderBy.append(" order by IdDomandaTest");	  
  }

}


