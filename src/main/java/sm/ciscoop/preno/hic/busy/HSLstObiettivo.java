
package sm.ciscoop.preno.hic.busy;

import java.util.Enumeration;
import javax.servlet.annotation.WebServlet;

import sm.ciscoop.pdc.*;
import sm.ciscoop.dmc.DMCDB;
import sm.ciscoop.stdlibs.baseutils.exceptions.CISException;
import sm.ciscoop.dmc.SimpleList;
import sm.ciscoop.preno.pdc.busy.*;
import sm.ciscoop.preno.hic.masks.AppSListMask;
import sm.ciscoop.stdlibs.baseutils.types.Text;
import sm.ciscoop.stdlibs.db.output.AnySelect;


/*-
 * Title:
 * Description:  SimpleList per Obiettivo
 * Copyright:    Copyright (c)
 * Company:      CIScoop
 * @author
 * @version 1.0
 *
 * 
 
	<servlet>
		<servlet-name>HSLstObiettivo</servlet-name>
			<servlet-class>sm.ciscoop.preno.hic.busy.HSLstObiettivo</servlet-class>
		</servlet>
		<servlet-mapping>
			<servlet-name>HSLstObiettivo</servlet-name>
			<url-pattern>/servlet/sm.ciscoop.preno.hic.busy.HSLstObiettivo</url-pattern>
		</servlet-mapping>
	</servlet>
	
	DECLARE @idnodo INT = 0;
	SET @idnodo = (select MAX(idnodo) FROM zTrans) + 1;
	if @idnodo IS NULL
	  BEGIN
	    SET @idnodo = 1
	  END
	print @idnodo
	INSERT INTO zTrans ([CdPDC],[DsPDC],[IdNodo],[IdPadre],[Flag],[ultagg],[utente]) VALUES ('HSLstObiettivo' ,'HSLstObiettivo',@idnodo,0,0,GETDATE(),'appl')
	INSERT INTO zPermessi([CdGruppo],[CdPDC], [Descrizione], [Permesso], [ultagg], [utente]) VALUES (1,  'HSLstObiettivo',' ','LAGMIRVC',getdate(),'appl')
	--INSERT INTO zPermessi([CdGruppo],[CdPDC], [Descrizione], [Permesso], [ultagg], [utente]) VALUES (xxx,  'HSLstObiettivo',' ','LAGMIRVC',getdate(),'appl')
	
	//Voce di Menu.xml
	<Voce funzione="HSLstObiettivo" href="/busy/HSLstObiettivo?MTipo=L&amp;MPasso=2">Lista Semplice HSLstObiettivo</Voce>

*
*/

@WebServlet(value=HSLstObiettivo.SERVLET_URL, name = HSLstObiettivo.SERVLET_NAME) 
public class HSLstObiettivo extends AppSListMask<Obiettivo> {
  
  private static final long  serialVersionUID = 1L;
  
  public static final String SERVLET_NAME     = "HSLstObiettivo";
  public static final String SERVLET_URL      = "/busy/" + HSLstObiettivo.SERVLET_NAME;
  
	// pdc collegato
	private Obiettivo m_mioPDC;
	private IntAttr   c_TpOccup;
 	private IntAttr   c_IdObiettivo;
 	private DateAttr  c_DtInizioObiettivo;
 	private CharAttr  c_DescObiettivo;
 	private DateAttr  c_DtScadenzaObiettivo;
 	private IntAttr   c_MinPrevisti;
 	private DateAttr  c_DtFineObiettivo;
 	private CharAttr  c_NotaObiettivo;
 	private DoubleAttr  c_PercCompletamento;
 	
	@Override
	public void initMsk() throws Exception {
		m_mioPDC = new Obiettivo(getMsgHandler());
		super.initMsk();

		Attributi attrs = getAttributi();
	c_TpOccup = attrs.addIntAttr("TpOccup");
		c_IdObiettivo = attrs.addIntAttr("IdObiettivo");
		c_DtInizioObiettivo = attrs.addDateAttr("DtInizioObiettivo");
		c_DescObiettivo = attrs.addCharAttr("DescObiettivo");
		c_DtScadenzaObiettivo = attrs.addDateAttr("DtScadenzaObiettivo");
		c_MinPrevisti = attrs.addIntAttr("MinPrevisti");
		c_DtFineObiettivo = attrs.addDateAttr("DtFineObiettivo");
		c_NotaObiettivo = attrs.addCharAttr("NotaObiettivo");
		c_PercCompletamento = (DoubleAttr) attrs.add("PercCompletamento", PDCType.DOUBLE);
		
	}
  


	@Override
	protected Object[] abilitaRelazioniPDC() throws Exception {
		// TODO Auto-generated method stub
		Object[] relaz = new Object[] {
Obiettivo.CSZrel_TipoOccupazione,10,
Obiettivo.CSZrel_Lavoro,10,
Obiettivo.CSZrel_DocObiettivo,10,

			""
		};
		return relaz;
	}


	@Override
	public String getSelect() {
		try {
			String ls = "select top 100 * from "+Obiettivo.CSZ_DBTable;

			ls += " INNER JOIN "+TipoOccupazione.CSZ_DBTable;
			ls += " ON 1=1  AND "+TipoOccupazione.CSZ_DBTable+".TpOccup = "+Obiettivo.CSZ_DBTable+".TpOccup";
			ls += " LEFT JOIN "+Lavoro.CSZ_DBTable;
			ls += " ON 1=1  AND "+Lavoro.CSZ_DBTable+".IdObiettivo = "+Obiettivo.CSZ_DBTable+".IdObiettivo";
			ls += " LEFT JOIN "+DocObiettivo.CSZ_DBTable;
			ls += " ON 1=1  AND "+DocObiettivo.CSZ_DBTable+".IdObiettivo = "+Obiettivo.CSZ_DBTable+".IdObiettivo";
			ls += " WHERE 1 = 1 ";
			if ( !m_mioPDC.getAttributi().get(Obiettivo.CSZcol_IdObiettivo).isEmptyOrNull()) {
				ls += " AND "+Obiettivo.CSZ_DBTable+".IdObiettivo = "+m_mioPDC.getIdObiettivo();
			}

			//ls += " ORDER BY Cognome, Nome";
			return ls;
		} catch(Exception ee) {
			ee.printStackTrace();
			addMessageError("Errore in :getSelect" + ee.getMessage());
			return "select * from "+Obiettivo.CSZ_DBTable + " where 1 = 0";
		}
	}


  @Override
  public int getMaxNumRecords() {
	  if ( isToExcel())
		  return 10000;
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
			c_DIMPAGINA.setValue(10);
	  }
		lista.setPageSize(c_DIMPAGINA.getIntValue());
		return null;
	}

	@Override
	public void loadCombo() {
		StringBuilder xml = new StringBuilder(200);
		try {
			super.loadCombo();
			if ( isSoloDatiCombo()) {
    			String desc = getParRicercaComboTesto();
		    	String cd = getParRicercaComboCodice();
    			String camporicerca = getParRicercaComboCampo();
				String lscombo = "";
				// Todo Aggiungere qui i campi filtro dinamici

				if (camporicerca.equalsIgnoreCase("TpOccup")) {
					String ls = "select top 100 TpOccup, DsOccup as nome FROM "+TipoOccupazione.CSZ_DBTable;
					ls += " WHERE 1 = 1";
					if ( desc != null && desc.length() >= 1) {
						desc = desc.replaceAll("'", "''");
						ls += " AND DsOccup like '"+desc.trim()+"%'";
					} else if ( cd != null && cd.length() > 0) {
						ls += " AND TpOccup = "+cd.trim();
					} else
						ls += " AND 1 = 0";
					appendToResponse(new AnySelect(ls, "Elementi"));
				}

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
				String relazNames [] = {nomerelaz};
				pdc.enableStruct(relazNames, true);
				DMCDB dmc = new DMCDB(getMsgHandler(),getDMC());
				pdc.setDMC(dmc);
				if ( !pdc.load()) {
					addMessageInfo("Errore in lettura dati :getXMLCombo:relazioni");
					return;
				}
				Lista l = pdc.getRelazioneAnnidata(nomerelaz).getLista();
				if (Text.toInt(rowsPerPage, 0) > 0) {
					l.setPageSize(Text.toInt(rowsPerPage, 10));
					int npages = l.getPageCount();
					int npage = Math.max(1, Text.toInt(numPagina, 1));
					if (npage > npages) {
						npage = npages;
					}
					l.setCurrPage(npage);
				}
				appendToResponse(pdc);				
			} else {
				// AppEnumeratiFactory en = (AppEnumeratiFactory) AppEnumeratiFactory.getInstance();
				// Todo Aggiungere qui i campi filtro statici
				//xml.append(en.getXML(Enumerati.ENUM_...)

				String lsTipoOccupazione = "select TpOccup, DsOccup FROM "+TipoOccupazione.CSZ_DBTable;
				lsTipoOccupazione += " WHERE 1 = 1 "; // Ricordarsi di abilitare il combo togliendo questo filtro
				appendToResponse(new AnySelect(lsTipoOccupazione, "TipoOccupazione"));

			}
		}	catch (Exception ex) {
			addMessage(ex);
		}
	}


  @Override
  public void inspectQuery(StringBuffer sbSelectList, StringBuffer sbFrom,
      StringBuffer szWhere, StringBuffer sbGroupBy,
      StringBuffer sbHaving, StringBuffer sOrderBy) {
    /*if ( !bQueryConsumo)
      return;
    sOrderBy.setLength(0);
    sOrderBy.append(" order by DtAcquisto desc");*/
  }



/*-
  Scommentare per eseguire comandi inviati dalla maschera con ajax
  @Override
  protected void eseguiComandoAjax(String nomecomando) throws Exception {
    try {
		RequestParameterMap param = getRequestParameterMap();
		switch (nomecomando) {
		  case "LanciaElaborazione":
			String strIdElab = param.getParameter("idelab");
			if (strIdElab == null || strIdElab.trim().length() == 0) {
			  getMsgHandler().addMsg(new Message("Manca l'identificativo dell'elaborazione"));
			  return ;
			}
			int idElab = Integer.parseInt(strIdElab);
			if (idElab <= 0) {
			  getMsgHandler().addMsg(new Message("L'identificativo dell'elaborazione non è valido"));
			  return;
			}
			
			JBB jbb = getJBB();
			jbb.setUseTransactions(true);
			if ( !jbb.inTransaction())
				jbb.beginTrans();

			// Todo: Aggiungere qui l'elaborazione
			
			addMessageInfo("Comando Terminato correttamente");
			jbb.commitTrans();
		}      
		addMessageInfo("Nessun Comando da eseguire!");
    } catch(Exception e ) {
		if ( getJBB().inTransaction())
			getJBB().rollbackTrans();			
      addMessage(e);
    }
  }
  
*/
}

