
package sm.ciscoop.preno.hic.prenota;

import java.util.Enumeration;
import javax.servlet.annotation.WebServlet;

import sm.ciscoop.pdc.*;
import sm.ciscoop.dmc.DMCDB;
import sm.ciscoop.dmc.SimpleList;
import sm.ciscoop.preno.pdc.prenota.*;
import sm.ciscoop.preno.hic.masks.AppSListMask;
import sm.ciscoop.stdlibs.baseutils.exceptions.CISException;
import sm.ciscoop.stdlibs.baseutils.types.Text;


/*-
 * Title:
 * Description:  SimpleList per Ragazzo
 * Copyright:    Copyright (c)
 * Company:      CIScoop
 * @author
 * @version 1.0
 *
 * 
 
	<servlet>
		<servlet-name>HSLstRagazzo</servlet-name>
			<servlet-class>sm.ciscoop.preno.hic.prenota.HSLstRagazzo</servlet-class>
		</servlet>
		<servlet-mapping>
			<servlet-name>HSLstRagazzo</servlet-name>
			<url-pattern>/servlet/sm.ciscoop.preno.hic.prenota.HSLstRagazzo</url-pattern>
		</servlet-mapping>
	</servlet>
	
	DECLARE @idnodo INT = 0;
	SET @idnodo = (select MAX(idnodo) FROM zTrans) + 1;
	if @idnodo IS NULL
	  BEGIN
	    SET @idnodo = 1
	  END
	print @idnodo
	INSERT INTO zTrans ([CdPDC],[DsPDC],[IdNodo],[IdPadre],[Flag],[ultagg],[utente]) VALUES ('HSLstRagazzo' ,'HSLstRagazzo',@idnodo,0,0,GETDATE(),'appl')
	INSERT INTO zPermessi([CdGruppo],[CdPDC], [Descrizione], [Permesso], [ultagg], [utente]) VALUES (1,  'HSLstRagazzo',' ','LAGMIRVC',getdate(),'appl')
	--INSERT INTO zPermessi([CdGruppo],[CdPDC], [Descrizione], [Permesso], [ultagg], [utente]) VALUES (xxx,  'HSLstRagazzo',' ','LAGMIRVC',getdate(),'appl')
	
	//Voce di Menu.xml
	<Voce funzione="HSLstRagazzo" href="/prenota/HSLstRagazzo?MTipo=L&amp;MPasso=2">Lista Semplice HSLstRagazzo</Voce>

*
*/

@WebServlet(value=HSLstRagazzo.SERVLET_URL, name = HSLstRagazzo.SERVLET_NAME) 
public class HSLstRagazzo extends AppSListMask<Ragazzo> {
  
  private static final long  serialVersionUID = 1L;
  
  public static final String SERVLET_NAME     = "HSLstRagazzo";
  public static final String SERVLET_URL      = "/prenota/" + HSLstRagazzo.SERVLET_NAME;
  
	// pdc collegato
	private Ragazzo m_mioPDC;
	private IntAttr   c_IdSoggRagazzo;
 	private CharAttr  c_NomeRagazzo;
 	private CharAttr  c_EmailRagazzo;
 	
	@Override
	public void initMsk() throws Exception {
		m_mioPDC = new Ragazzo(getMsgHandler());
		super.initMsk();

		Attributi attrs = getAttributi();
	c_IdSoggRagazzo = attrs.addIntAttr("IdSoggRagazzo");
		c_NomeRagazzo = attrs.addCharAttr("NomeRagazzo");
		c_EmailRagazzo = attrs.addCharAttr("EmailRagazzo");
		
	}
  


	@Override
	protected Object[] abilitaRelazioniPDC() throws Exception {
		// TODO Auto-generated method stub
		Object[] relaz = new Object[] {
Ragazzo.CSZrel_PrenoRagazzo,10,

			""
		};
		return relaz;
	}


	@Override
	public String getSelect() {
		try {
			String ls = "select top 100 * from "+Ragazzo.CSZ_DBTable;

			ls += " INNER JOIN "+PrenoRagazzo.CSZ_DBTable;
			ls += " ON 1=1  AND "+PrenoRagazzo.CSZ_DBTable+".IdSoggRagazzo = "+Ragazzo.CSZ_DBTable+".IdSoggRagazzo";
			ls += " WHERE 1 = 1 ";
			if ( !((NumericAttr)m_mioPDC.getAttributi().get(Ragazzo.CSZcol_IdSoggRagazzo)).isEmptyNullOrZero()) {
				ls += " AND "+Ragazzo.CSZ_DBTable+".IdSoggRagazzo = "+m_mioPDC.getIdSoggRagazzo();
			}

			//ls += " ORDER BY Cognome, Nome";
			return ls;
		} catch(Exception ee) {
			ee.printStackTrace();
			addMessageError("Errore in :getSelect");
			return "select * from "+Ragazzo.CSZ_DBTable + " where 1 = 0";
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

