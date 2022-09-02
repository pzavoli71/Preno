package sm.ciscoop.preno.hic.patente;

import javax.servlet.annotation.WebServlet;

import sm.ciscoop.pdc.*;
import sm.ciscoop.preno.pdc.patente.*;
	
import sm.ciscoop.stdlibs.baseutils.types.Text;
import sm.ciscoop.stdlibs.db.output.AnySelect;
import sm.ciscoop.dmc.DMCDB;
import sm.ciscoop.servlet.OpRes;
import java.util.*;

	
import sm.ciscoop.servlet.Passo;
import sm.ciscoop.preno.hic.masks.AppSingleMask;
		


/*-
 * Title:
 * Description:  Single per DomandaQuiz
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
	INSERT INTO zTrans ([CdPDC],[DsPDC],[IdNodo],[IdPadre],[Flag],[ultagg],[utente]) VALUES ('HDomandaQuiz' ,'HDomandaQuiz',@idnodo,0,0,GETDATE(),'appl')
	INSERT INTO zPermessi([CdGruppo],[CdPDC], [Descrizione], [Permesso], [ultagg], [utente]) VALUES (1, 'HDomandaQuiz',' ','LAGMIRVC',getdate(),'appl')
	--INSERT INTO zPermessi([CdGruppo],[CdPDC], [Descrizione], [Permesso], [ultagg], [utente]) VALUES (xxx, 'HDomandaQuiz',' ','LAGMIRVC',getdate(),'appl')


	// Voce di Menu.xml
	<Voce funzione="HDomandaQuiz" href="/patente/HDomandaQuiz">Single HDomandaQuiz</Voce>

 *
 */

@WebServlet(value=HDomandaQuiz.SERVLET_URL, name = HDomandaQuiz.SERVLET_NAME) 
public class HDomandaQuiz extends AppSingleMask<DomandaQuiz> {

	
	private static final long  serialVersionUID = 1L;

	public static final String SERVLET_NAME     = "HDomandaQuiz";
	public static final String SERVLET_URL      = "/patente/" + HDomandaQuiz.SERVLET_NAME;

		
	// UploadAttr
	// UploadAttr	c_FileUpload;
	
	
	@Override
	public void initMsk() throws Exception  {
		super.initMsk();
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

	@Override
	public void loadCombo() throws Exception {
		super.loadCombo();
		if ( isSoloDatiCombo()) {
			String desc = getParRicercaComboTesto();
			String cd = getParRicercaComboCodice();
			String camporicerca = getParRicercaComboCampo();
			String lscombo = "";
			// Todo Aggiungere qui i campi filtro dinamici
			
			if (camporicerca.equalsIgnoreCase("IdQuiz")) {
				String ls = "select top 100 IdQuiz, DtCreazioneTest as nome FROM "+Quiz.CSZ_DBTable;
				ls += " WHERE 1 = 1";
				if ( desc != null && desc.length() >= 1) {
					desc = desc.replaceAll("'", "''");
					ls += " AND DtCreazioneTest like '"+desc.trim()+"%'";
				} else if ( cd != null && cd.length() > 0) {
					ls += " AND IdQuiz = "+cd.trim();
				} else
					ls += " AND 1 = 0";
				appendToResponse(new AnySelect(ls, "Elementi"));
			}
			
			if (camporicerca.equalsIgnoreCase("IdDomanda")) {
				String ls = "select top 100 IdDomanda, IdCapitolo as nome FROM "+Domanda.CSZ_DBTable;
				ls += " WHERE 1 = 1";
				if ( desc != null && desc.length() >= 1) {
					desc = desc.replaceAll("'", "''");
					ls += " AND IdCapitolo like '"+desc.trim()+"%'";
				} else if ( cd != null && cd.length() > 0) {
					ls += " AND IdDomanda = "+cd.trim();
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
			
				String lsQuiz = "select IdQuiz, DtCreazioneTest FROM "+Quiz.CSZ_DBTable;
				lsQuiz += " WHERE 1 = 0 "; // Ricordarsi di abilitare il combo togliendo questo filtro
				appendToResponse(new AnySelect(lsQuiz, "Quiz"));
			
				String lsDomanda = "select IdDomanda, IdCapitolo FROM "+Domanda.CSZ_DBTable;
				lsDomanda += " WHERE 1 = 0 "; // Ricordarsi di abilitare il combo togliendo questo filtro
				appendToResponse(new AnySelect(lsDomanda, "Domanda"));
			
		}		
	}


	
	/* Se si hanno fileitem (file da fare upload, scommentare questa funzione e mettere il path del documento dentro il pdc
	@Override
	public boolean beforeExecIns(Passo nPasso, OpRes opRes) {
		if (!super.beforeExecIns(nPasso, opRes)) {
			return false;
		}

		if ( c_FileUpload.getCISFileItem() != null &amp;&amp; c_FileUpload.getFileName().length() > 0) {
			getPDC().setNomeFile("/"+comu.getIdComunicazionePadre()+"/"+getShibIdUtente()+"/"+c_FileUpload.getFileName());
		}
	}
	*/
	
	@Override
	public boolean afterExecIns(Passo nPasso, OpRes opRes) throws Exception {
		if (!super.afterExecIns(nPasso, opRes)) {
			return false;
		}
		if ( nPasso == Passo.ESECUZIONE && opRes == opRes.OK) {
				/* Se ho dei fileitem da salvare, lo posso fare qui.
    	  /*
			if (c_FileUpload.getItem() != null &amp;&amp;
	            c_FileUpload.getItem().getName().length() > 0) {
			
			String path = sm.ciscoop.util.AppPropertiesBase.getProperties().getProp("REPOS_DOCUM");
			path += "/"+c_FileUpload.getItem().getName();
			java.io.File f = new java.io.File(path);
			f.mkdirs();
			c_FileUpload.saveToAbsolutePathFile(path);
    	  */
				
				
			setRedirect(getServletFullPath() + "?MTipo=V&MPasso=2"+"&IdDomandaTest="+getPDC().getIdDomandaTest());
			addMessageInfo("Inserimento completato correttamente. Chiudere la maschera per vedere il dato aggiornato.");
		}
		return true;
	}

  /*
   * Consente di controllare il permesso per l'esecuzione dell'operazione.
   * Il programmatore dovrebbe controllare se l'utente corrente ha i permessi per operare sul dato presente nel PDC.
   * @see sm.ciscoop.newAppl.hic.SingleMaskNewAppl#checkPermesso(char)
   */
   /*
  @Override
  protected boolean checkPermesso(char modalita) throws Exception {
		SessioneMask sess = getSessionePreno();
		int codiss = sess.getProfiloUtente().getCodiss();
		int codoe = sess.getProfiloUtente().getCodoe();
		int cdruolo = sess.getProfiloUtente().getIdRuolo();
		// Controllare anche le deleghe del portale
		if ( codoe == getPDC().getCodOE())
		 return true;
		String ls = "select CdSoggettoDelegante as CdSoggetto,rtrim(str(CdSoggettoDelegante)) +' ' + NomeSoggettoDelegante as NomeSoggettoDelegante from vista_delegati";
		ls += " WHERE IdAziendaDelegata = " + sess.getProfiloUtente().getIdAziendaLavoro();
		ls += " and siglaoggetto = 'XXXX-SIGLAOGGETTO-XXXXXX' "; // Mettere la propria siglaoggetto
		ls += " and CdSoggettoDelegante = "+codoe;
		getDMC().setQueryDaParsare(false);
		JBB jb = getJBB();
		if ( jb.getCount(ls) <= 0) {
		 return false;
		}
		return true;
  }
  */
}
