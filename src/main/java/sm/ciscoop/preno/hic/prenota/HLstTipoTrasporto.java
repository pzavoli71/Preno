package sm.ciscoop.preno.hic.prenota;

import javax.servlet.annotation.WebServlet;

import sm.ciscoop.pdc.*;
import sm.ciscoop.preno.pdc.prenota.*;
	
import sm.ciscoop.stdlibs.baseutils.types.Text;
import sm.ciscoop.dmc.DMCDB;
import sm.ciscoop.servlet.OpRes;
import java.util.*;

	
import java.util.Enumeration;
import sm.ciscoop.preno.hic.masks.AppDynamicListMask;
import sm.ciscoop.dmc.DMCDB;
		

/*-
 * Title:
 * Description:  Lista dinamica per TipoTrasporto
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
	INSERT INTO zTrans ([CdPDC],[DsPDC],[IdNodo],[IdPadre],[Flag],[ultagg],[utente]) VALUES ('HLstTipoTrasporto' ,'HLstTipoTrasporto',@idnodo,0,0,GETDATE(),'appl')
	INSERT INTO zPermessi([CdGruppo],[CdPDC], [Descrizione], [Permesso], [ultagg], [utente]) VALUES (1, 'HLstTipoTrasporto',' ','LAGMIRVC',getdate(),'appl')
	--INSERT INTO zPermessi([CdGruppo],[CdPDC], [Descrizione], [Permesso], [ultagg], [utente]) VALUES (xxx, 'HLstTipoTrasporto',' ','LAGMIRVC',getdate(),'appl')

	
	//Voce di Menu.xml
	<Voce funzione="HLstTipoTrasporto" href="/prenota/HLstTipoTrasporto">Lista HLstTipoTrasporto</Voce>

 *
 */

@WebServlet(value=HLstTipoTrasporto.SERVLET_URL, name = HLstTipoTrasporto.SERVLET_NAME) 
	public class HLstTipoTrasporto extends AppDynamicListMask<TipoTrasporto> {

	
	private static final long  serialVersionUID = 1L;

	public static final String SERVLET_NAME     = "HLstTipoTrasporto";
	public static final String SERVLET_URL      = "/prenota/" + HLstTipoTrasporto.SERVLET_NAME;

	@Override
	public void initMsk() throws Exception  {
		super.initMsk();
		//Attributi attrs = getAttributi();
	}


	/*-
	
	@Override
	public String getSelect() throws Exception {
		String ls = "select top 100 * from "+TipoTrasporto.CSZ_DBTable;
		
		ls += " INNER JOIN "+Giorno.CSZ_DBTable;
		ls += " ON 1=1  AND "+Giorno.CSZ_DBTable+".IdTpTrasporto = "+TipoTrasporto.CSZ_DBTable+".IdTpTrasporto";
		ls += " WHERE 1 = 1 ";
			if ( !getPDC().getAttributi().get(TipoTrasporto.CSZcol_IdTpTrasporto).isEmptyOrNull()) {
				ls += " AND "+TipoTrasporto.CSZ_DBTable+".IdTpTrasporto = "+getPDC().getIdTpTrasporto();
		}

		//ls += " ORDER BY Cognome, Nome";
		return ls;
	}

	*/

	
  @Override
  public int getMaxNumRecords() throws Exception {
    if ( isToExcel()) {
    	return 10000;
    }
	return DEFAULT_MAX_NUM_RECORDS;
  }

	@Override
	public void impostaValoriFiltro(Lista lista) throws Exception {
		if ( c_DIMPAGINA.isEmptyNullOrZero()) {
			c_DIMPAGINA.setValue(10);
		}
		lista.setPageSize(c_DIMPAGINA.getIntValue());

			getPDC().getAttributi().get("IdTpTrasporto").setAliasTab(TipoTrasporto.CSZ_DBTable);
	  	lista.addCampoFiltro(getPDC().getAttributi().get("IdTpTrasporto"), false);
    
		//lista.setOrderByList(" TipoTrasporto.TipoTrasporto");
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
				ArrayList<Object> lsrelaz =new ArrayList<>();
				lsrelaz.add(nomerelaz);
				lsrelaz.add(Text.toInt(rowsPerPage, 0));

				/* Scommentare per abilitare sottorelazioni a mano quando si richiede di visualizzare una determinata relazione
				if (nomerelaz.equals(TSoggetto.CSZrel_TSoggSezione)) {
					lsrelaz.add(TSoggSezione.CSZrel_TSezione);
				}*/
				Object relazNames[] = lsrelaz.toArray();

				//String relazNames [] = {nomerelaz};
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
				// xml.append(en.getXML(Enumerati.ENUM_...)
			
		}		
	}

  @Override
  public void inspectQuery(StringBuffer sbSelectList, StringBuffer sbFrom,
		StringBuffer szWhere, StringBuffer sbGroupBy,
		StringBuffer sbHaving, StringBuffer sOrderBy) throws Exception {
		/*if ( !bQueryConsumo)
			return;
		sOrderBy.setLength(0);
		sOrderBy.append(" order by DtAcquisto desc"); */
  }

/*-
  Scommentare per eseguire comandi inviati dalla maschera con ajax
  @Override
  protected void eseguiComandoAjax(String nomecomando) throws Exception {
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
  }

*/


}
