
package sm.ciscoop.preno.hic.busy;

import java.util.Enumeration;
import javax.servlet.annotation.WebServlet;

import sm.ciscoop.preno.hic.masks.AppListMask;
import sm.ciscoop.pdc.*;
import sm.ciscoop.dmc.DMCDB;
import sm.ciscoop.stdlibs.baseutils.exceptions.CISException;
import sm.ciscoop.preno.pdc.busy.*;
import sm.ciscoop.stdlibs.baseutils.types.Text;
import sm.ciscoop.stdlibs.db.output.AnySelect;


/*-
 * Title:
 * Description:  Lista per TipoOccupazione
 * Copyright:    Copyright (c)
 * Company:      CIScoop
 * @author
 * @version 1.0
 *
 *
 
	<servlet>
		<servlet-name>HLstTipoOccupazione</servlet-name>
			<servlet-class>sm.ciscoop.preno.hic.busy.HLstTipoOccupazione</servlet-class>
		</servlet>
		<servlet-mapping>
			<servlet-name>HLstTipoOccupazione</servlet-name>
			<url-pattern>/servlet/sm.ciscoop.preno.hic.busy.HLstTipoOccupazione</url-pattern>
		</servlet-mapping>
	</servlet>
	
	DECLARE @idnodo INT = 0;
	SET @idnodo = (select MAX(idnodo) FROM zTrans) + 1;
	if @idnodo IS NULL
	  BEGIN
	    SET @idnodo = 1
	  END
	print @idnodo
	INSERT INTO zTrans ([CdPDC],[DsPDC],[IdNodo],[IdPadre],[Flag],[ultagg],[utente]) VALUES ('HLstTipoOccupazione' ,'HLstTipoOccupazione',@idnodo,0,0,GETDATE(),'appl')
	INSERT INTO zPermessi([CdGruppo],[CdPDC], [Descrizione], [Permesso], [ultagg], [utente]) VALUES (1, 'HLstTipoOccupazione',' ','LAGMIRVC',getdate(),'appl')
	--INSERT INTO zPermessi([CdGruppo],[CdPDC], [Descrizione], [Permesso], [ultagg], [utente]) VALUES (xxx, 'HLstTipoOccupazione',' ','LAGMIRVC',getdate(),'appl')
	
	//Voce di Menu.xml
	<Voce funzione="HLstTipoOccupazione" href="/busy/HLstTipoOccupazione?MTipo=L&amp;MPasso=2">Lista HLstTipoOccupazione</Voce>

 *
 */

@WebServlet(value=HLstTipoOccupazione.SERVLET_URL, name = HLstTipoOccupazione.SERVLET_NAME) 
public class HLstTipoOccupazione extends AppListMask<TipoOccupazione> {

	private static final long  serialVersionUID = 1L;
	
	public static final String SERVLET_NAME     = "HLstTipoOccupazione";
	public static final String SERVLET_URL      = "/busy/" + HLstTipoOccupazione.SERVLET_NAME;
	
	// pdc collegato
	private TipoOccupazione m_mioPDC;
	private IntAttr   c_TpOccup;
 	private CharAttr  c_DsOccup;
 	
	@Override
	public void initMsk() throws Exception  {
		m_mioPDC = new TipoOccupazione(getMsgHandler());
		super.initMsk();

		Attributi attrs = getAttributi();
	c_TpOccup = attrs.addIntAttr("TpOccup");
		c_DsOccup = attrs.addCharAttr("DsOccup");
		
		//abilitaRelaz();
	}


	@Override
	protected Object[] abilitaRelazioniPDC() throws Exception {
		// TODO Auto-generated method stub
		Object[] relaz = new Object[] {
TipoOccupazione.CSZrel_Obiettivo,10,

			""
		};
		return relaz;
	}


	/*-
	@Override
	public String getSelect() {
		try {
			String ls = "select top 100 * from "+TipoOccupazione.CSZ_DBTable;

			ls += " INNER JOIN "+Obiettivo.CSZ_DBTable;
			ls += " ON 1=1  AND "+Obiettivo.CSZ_DBTable+".TpOccup = "+TipoOccupazione.CSZ_DBTable+".TpOccup";
			ls += " WHERE 1 = 1 ";
			if ( !((NumericAttr)m_mioPDC.getAttributi().get(TipoOccupazione.CSZcol_TpOccup)).isEmptyNullOrZero()) {
				ls += " AND "+TipoOccupazione.CSZ_DBTable+".TpOccup = "+m_mioPDC.getTpOccup();
			}

			//ls += " ORDER BY Cognome, Nome";
			return ls;
		} catch(Exception ee) {
			addMessage(ee);
			return "select * from "+TipoOccupazione.CSZ_DBTable + " where 1 = 0";
		}
	}
	*
	*/


  @Override
  public int getMaxNumRecords() {
	  if ( isToExcel())
		  return 10000;
	  return DEFAULT_MAX_NUM_RECORDS;
  }
  

	@Override
	public void impostaValoriFiltro(Lista lista){
		try {
			if ( c_DIMPAGINA.isEmptyNullOrZero()) {
				c_DIMPAGINA.setValue(10);
			}
			lista.setPageSize(c_DIMPAGINA.getIntValue());
 
			m_mioPDC.getAttributi().get("TpOccup").setAliasTab(TipoOccupazione.CSZ_DBTable);
			lista.addCampoFiltro(m_mioPDC.getAttributi().get("TpOccup"), false);
    
			//lista.setOrderByList(" TipoOccupazione.TipoOccupazione");
		} catch (Exception ex) {
			addMessage(ex);
		}
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
		sOrderBy.append(" order by DtAcquisto desc"); */
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
