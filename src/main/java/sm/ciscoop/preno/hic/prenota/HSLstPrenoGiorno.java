
package sm.ciscoop.preno.hic.prenota;

import java.util.Enumeration;
import javax.servlet.annotation.WebServlet;

import sm.ciscoop.pdc.*;
import sm.ciscoop.dmc.DMCDB;
import sm.ciscoop.dmc.SimpleList;
import sm.ciscoop.preno.pdc.prenota.*;
import sm.ciscoop.preno.pdc.soggetti.Soggetto;
import sm.ciscoop.preno.hic.masks.AppSListMask;
import sm.ciscoop.stdlibs.baseutils.exceptions.CISException;
import sm.ciscoop.stdlibs.baseutils.types.Text;
import sm.ciscoop.stdlibs.db.output.AnySelect;


/*-
 * Title:
 * Description:  SimpleList per PrenoGiorno
 * Copyright:    Copyright (c)
 * Company:      CIScoop
 * @author
 * @version 1.0
 *
 * 
 
	<servlet>
		<servlet-name>HSLstPrenoGiorno</servlet-name>
			<servlet-class>sm.ciscoop.preno.hic.prenota.HSLstPrenoGiorno</servlet-class>
		</servlet>
		<servlet-mapping>
			<servlet-name>HSLstPrenoGiorno</servlet-name>
			<url-pattern>/servlet/sm.ciscoop.preno.hic.prenota.HSLstPrenoGiorno</url-pattern>
		</servlet-mapping>
	</servlet>
	
	DECLARE @idnodo INT = 0;
	SET @idnodo = (select MAX(idnodo) FROM zTrans) + 1;
	if @idnodo IS NULL
	  BEGIN
	    SET @idnodo = 1
	  END
	print @idnodo
	INSERT INTO zTrans ([CdPDC],[DsPDC],[IdNodo],[IdPadre],[Flag],[ultagg],[utente]) VALUES ('HSLstPrenoGiorno' ,'HSLstPrenoGiorno',@idnodo,0,0,GETDATE(),'appl')
	INSERT INTO zPermessi([CdGruppo],[CdPDC], [Descrizione], [Permesso], [ultagg], [utente]) VALUES (1,  'HSLstPrenoGiorno',' ','LAGMIRVC',getdate(),'appl')
	--INSERT INTO zPermessi([CdGruppo],[CdPDC], [Descrizione], [Permesso], [ultagg], [utente]) VALUES (xxx,  'HSLstPrenoGiorno',' ','LAGMIRVC',getdate(),'appl')
	
	//Voce di Menu.xml
	<Voce funzione="HSLstPrenoGiorno" href="/prenota/HSLstPrenoGiorno?MTipo=L&amp;MPasso=2">Lista Semplice HSLstPrenoGiorno</Voce>

*
*/

@WebServlet(value=HSLstPrenoGiorno.SERVLET_URL, name = HSLstPrenoGiorno.SERVLET_NAME) 
public class HSLstPrenoGiorno extends AppSListMask<PrenoGiorno> {
  
  private static final long  serialVersionUID = 1L;
  
  public static final String SERVLET_NAME     = "HSLstPrenoGiorno";
  public static final String SERVLET_URL      = "/prenota/" + HSLstPrenoGiorno.SERVLET_NAME;
  
	// pdc collegato
	private PrenoGiorno m_mioPDC;
	private IntAttr   c_IdSoggetto;
 	private IntAttr   c_IdPrenotazione;
 	private DateAttr  c_DtGiorno;
 	private IntAttr   c_IdSoggettoAndata;
 	private IntAttr   c_IdSoggettoRitorno;
 	private IntAttr   c_NumMax;
 	
	@Override
	public void initMsk() throws Exception {
		m_mioPDC = new PrenoGiorno(getMsgHandler());
		super.initMsk();

		Attributi attrs = getAttributi();
	c_IdSoggetto = attrs.addIntAttr("IdSoggetto");
		c_IdPrenotazione = attrs.addIntAttr("IdPrenotazione");
		c_DtGiorno = attrs.addDateAttr("DtGiorno");
		c_IdSoggettoAndata = attrs.addIntAttr("IdSoggettoAndata");
		c_IdSoggettoRitorno = attrs.addIntAttr("IdSoggettoRitorno");
		c_NumMax = attrs.addIntAttr("NumMax");
		
	}
  


	@Override
	protected Object[] abilitaRelazioniPDC() throws Exception {
		// TODO Auto-generated method stub
		Object[] relaz = new Object[] {
PrenoGiorno.CSZrel_PrenoRagazzo,10,
PrenoGiorno.CSZrel_Soggettoandat,10,
PrenoGiorno.CSZrel_Soggettoritor,10,

			""
		};
		return relaz;
	}


	@Override
	public String getSelect() {
		try {
			String ls = "select p.*,sa.NomeSoggetto as NomeSoggettoAndata, sa.EmailSogg as EmailSoggAndata, sa.codiss as CodissAndata";
			ls += ",sr.NomeSoggetto as NomeSoggettoRitorno, sr.EmailSogg as EmailSoggRitorno, sr.codiss as CodissRitorno from "+PrenoGiorno.CSZ_DBTable + " p ";

			ls += " LEFT OUTER JOIN "+Soggetto.CSZ_DBTable + " sa ";
			ls += " ON 1=1  AND sa.IdSoggetto = p.IdSoggettoAndata";
			ls += " LEFT OUTER JOIN "+Soggetto.CSZ_DBTable + " sr ";
			ls += " ON 1=1  AND sr.IdSoggetto = p.IdSoggettoRitorno";
			ls += " WHERE 1 = 1 ";
			if ( !m_mioPDC.getAttributi().get(PrenoGiorno.CSZcol_IdPrenotazione).isEmptyOrNull()) {
				ls += " AND p.IdPrenotazione = "+m_mioPDC.getIdPrenotazione();
			}
			if ( !c_DtGiorno.isEmptyNullOrZero()) {
				ls += " AND p.DtGiorno >= "+ getJBB().getFormattedVal(c_DtGiorno.getValue(), true);
			}
			//ls += " ORDER BY Cognome, Nome";
			return ls;
		} catch(Exception ee) {
			ee.printStackTrace();
			addMessageError("Errore in :getSelect");
			return "select * from "+PrenoGiorno.CSZ_DBTable + " where 1 = 0";
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

				if (camporicerca.equalsIgnoreCase("IdSoggetto")) {
					String ls = "select top 100 IdSoggetto, NomeSoggetto as nome FROM "+Soggetto.CSZ_DBTable;
					ls += " WHERE 1 = 1";
					if ( desc != null && desc.length() >= 1) {
						desc = desc.replaceAll("'", "''");
						ls += " AND NomeSoggetto like '"+desc.trim()+"%'";
					} else if ( cd != null && cd.length() > 0) {
						ls += " AND IdSoggetto = "+cd.trim();
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
				if ( nomerelaz.equalsIgnoreCase(PrenoGiorno.CSZrel_PrenoRagazzo)) {
					relazNames = new String [2];
					relazNames[0] = nomerelaz;
					relazNames[1] = PrenoRagazzo.CSZrel_Ragazzo;
				}				
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

				String lsSoggetto = "select IdSoggetto, NomeSoggetto FROM "+Soggetto.CSZ_DBTable;
				lsSoggetto += " WHERE 1 = 1 "; // Ricordarsi di abilitare il combo togliendo questo filtro
				appendToResponse(new AnySelect(lsSoggetto, "Soggetti"));

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

