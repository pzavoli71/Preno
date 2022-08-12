package sm.ciscoop.preno.hic.prenota;

import javax.servlet.annotation.WebServlet;

import sm.ciscoop.pdc.*;
import sm.ciscoop.preno.pdc.prenota.*;
import sm.ciscoop.stdlibs.baseutils.exceptions.CISException;
import sm.ciscoop.stdlibs.baseutils.types.Text;
import sm.ciscoop.stdlibs.db.output.AnySelect;
import sm.ciscoop.dmc.DMCDB;
import sm.ciscoop.servlet.OpRes;
import java.util.*;

	
import java.util.Enumeration;
import sm.ciscoop.dmc.SimpleList;
import sm.ciscoop.dmc.DMCDB;
import sm.ciscoop.preno.hic.masks.AppSListMask;
		


/*-
 * Title:
 * Description:  SimpleList per Giorno
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
	INSERT INTO zTrans ([CdPDC],[DsPDC],[IdNodo],[IdPadre],[Flag],[ultagg],[utente]) VALUES ('HSLstGiorno' ,'HSLstGiorno',@idnodo,0,0,GETDATE(),'appl')
	INSERT INTO zPermessi([CdGruppo],[CdPDC], [Descrizione], [Permesso], [ultagg], [utente]) VALUES (1, 'HSLstGiorno',' ','LAGMIRVC',getdate(),'appl')
	--INSERT INTO zPermessi([CdGruppo],[CdPDC], [Descrizione], [Permesso], [ultagg], [utente]) VALUES (xxx, 'HSLstGiorno',' ','LAGMIRVC',getdate(),'appl')

	
	
	//Voce di Menu.xml
	<Voce funzione="HSLstGiorno" href="/prenota/HSLstGiorno">Lista Semplice HSLstGiorno</Voce>

*
*/

@WebServlet(value=HSLstGiorno.SERVLET_URL, name = HSLstGiorno.SERVLET_NAME) 
public class HSLstGiorno extends AppSListMask<Giorno> {
  
	
	private static final long  serialVersionUID = 1L;

	public static final String SERVLET_NAME     = "HSLstGiorno";
	public static final String SERVLET_URL      = "/prenota/" + HSLstGiorno.SERVLET_NAME;

	@Override
	public void initMsk() throws Exception  {
		super.initMsk();
		//Attributi attrs = getAttributi();
	}


	@Override
	public String getSelect() throws Exception {
		String ls = "select top 100 * from "+Giorno.CSZ_DBTable;
		
		ls += " WHERE 1 = 1 ";
			if ( !getPDC().getAttributi().get(Giorno.CSZcol_IdGiorno).isEmptyOrNull()) {
				ls += " AND "+Giorno.CSZ_DBTable+".IdGiorno = "+getPDC().getIdGiorno();
		}

		//ls += " ORDER BY Cognome, Nome";
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
			c_DIMPAGINA.setValue(10);
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
			
			if (camporicerca.equalsIgnoreCase("IdTpTrasporto")) {
				String ls = "select top 100 IdTpTrasporto, NomeTpTrasporto as nome FROM "+TipoTrasporto.CSZ_DBTable;
				ls += " WHERE 1 = 1";
				if ( desc != null && desc.length() >= 1) {
					desc = desc.replaceAll("'", "''");
					ls += " AND NomeTpTrasporto like '"+desc.trim()+"%'";
				} else if ( cd != null && cd.length() > 0) {
					ls += " AND IdTpTrasporto = "+cd.trim();
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
				Object relazNames [] = {nomerelaz, rowsPerPage};
				pdc.enableStruct(relazNames, true);
				DMCDB dmc = new DMCDB(getMsgHandler(),getDMC());
				pdc.setDMC(dmc);
				if ( !pdc.load()) {
					addMessageInfo("Errore in lettura dati Giorno:getxmlCombo:relaz");
					return;
				}
				if ( rowsPerPage > 0)
					pdc.getRelazioneAnnidata(nomerelaz).getLista().setCurrPage(numPagina);
				appendToResponse(pdc);


			} else {
				// AppEnumeratiFactory en = (AppEnumeratiFactory) AppEnumeratiFactory.getInstance();
				// Todo Aggiungere qui i campi filtro statici
				// xml.append(en.getXML(Enumerati.ENUM_...)
			
				String lsTipoTrasporto = "select IdTpTrasporto, NomeTpTrasporto FROM "+TipoTrasporto.CSZ_DBTable;
				lsTipoTrasporto += " WHERE 1 = 0 "; // Ricordarsi di abilitare il combo togliendo questo filtro
				appendToResponse(new AnySelect(lsTipoTrasporto, "TipoTrasporto"));
			
		}		
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


