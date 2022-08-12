package sm.ciscoop.preno.hic.prenota;

import javax.servlet.annotation.WebServlet;

import enumerati.EnumAR;
import sm.ciscoop.pdc.*;
import sm.ciscoop.preno.pdc.prenota.*;
import sm.ciscoop.preno.pdc.soggetti.Soggetto;
import sm.ciscoop.stdlibs.baseutils.types.DtUtil;
import sm.ciscoop.stdlibs.baseutils.types.Text;
import sm.ciscoop.stdlibs.db.output.AnySelect;
import sm.ciscoop.dmc.DMCDB;
import sm.ciscoop.servlet.OpRes;
import java.util.*;

	
import sm.ciscoop.servlet.Passo;
import sm.ciscoop.preno.hic.masks.AppSingleMask;
		


/*-
 * Title:
 * Description:  Single per SoggAR
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
	INSERT INTO zTrans ([CdPDC],[DsPDC],[IdNodo],[IdPadre],[Flag],[ultagg],[utente]) VALUES ('HSoggAR' ,'HSoggAR',@idnodo,0,0,GETDATE(),'appl')
	INSERT INTO zPermessi([CdGruppo],[CdPDC], [Descrizione], [Permesso], [ultagg], [utente]) VALUES (1, 'HSoggAR',' ','LAGMIRVC',getdate(),'appl')
	--INSERT INTO zPermessi([CdGruppo],[CdPDC], [Descrizione], [Permesso], [ultagg], [utente]) VALUES (xxx, 'HSoggAR',' ','LAGMIRVC',getdate(),'appl')


	// Voce di Menu.xml
	<Voce funzione="HSoggAR" href="/prenota/HSoggAR">Single HSoggAR</Voce>

 *
 */

@WebServlet(value=HSoggAR.SERVLET_URL, name = HSoggAR.SERVLET_NAME) 
public class HSoggAR extends AppSingleMask<SoggAR> {

	
	private static final long  serialVersionUID = 1L;

	public static final String SERVLET_NAME     = "HSoggAR";
	public static final String SERVLET_URL      = "/prenota/" + HSoggAR.SERVLET_NAME;

		
	// UploadAttr
	// UploadAttr	c_FileUpload;
	IntAttr c_Giorno;
	IntAttr c_Mese;
	IntAttr c_Anno;
	IntAttr c_TpSoggetto;
	IntAttr c_IdTpTrasporto;
	
	@Override
	public void initMsk() throws Exception  {
		super.initMsk();
		
		Attributi attrs = getAttributi();
		c_Giorno = (IntAttr) attrs.add("Giorno",PDCType.INTEGER);
		c_Giorno.setValue(0);
		c_Anno = (IntAttr) attrs.add("Anno",PDCType.INTEGER);
		c_Anno.setValue(0);
		c_Mese = (IntAttr) attrs.add("Mese",PDCType.INTEGER);
		c_Mese.setValue(0);

		c_TpSoggetto = (IntAttr) attrs.add("TpSoggetto",PDCType.INTEGER);
		c_TpSoggetto.setValue(0);
		
		c_IdTpTrasporto = (IntAttr) attrs.add("IdTpTrasporto",PDCType.INTEGER);
		
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
			
				String lsSoggetto = "select IdSoggetto, NomeSoggetto FROM "+Soggetto.CSZ_DBTable;
				lsSoggetto += " WHERE 1 = 1 "; // Ricordarsi di abilitare il combo togliendo questo filtro
				if ( c_TpSoggetto.getIntValue() == 1) 
					lsSoggetto += " AND bRagazzo = 0";
				else 
					lsSoggetto += " AND bRagazzo = -1";
				if ( !c_IdTpTrasporto.isEmptyNullOrZero())
					lsSoggetto += " and exists ( select * from SoggTrasporto t where t.IdTpTrasporto = " + c_IdTpTrasporto.getIntValue() + " and t.IdSoggetto = Soggetto.IdSoggetto)";
				appendToResponse(new AnySelect(lsSoggetto, "Soggetto"));

				appendToResponse(EnumAR.class);
				
			
		}		
	}


	
	/* Se si hanno fileitem (file da fare upload, scommentare questa funzione e mettere il path del documento dentro il pdc*/
	@Override
	protected boolean beforeExecIns(Passo nPasso) throws Exception {
		if (!super.beforeExecIns(nPasso)) {
			return false;
		}
		if ( nPasso != Passo.ESECUZIONE)
			return true;
		Date dt = DtUtil.createDate(c_Anno.getIntValue(), c_Mese.getIntValue(), c_Giorno.getIntValue());
		Giorno giorno = new Giorno(getMsgHandler());
		DMCDB dmc = new DMCDB(getMsgHandler(), getDMC());
		String ls = "select * from " + Giorno.CSZ_DBTable + " WHERE DtGiorno = " + getJBB().getFormattedVal(dt, true);
		ls += " and IdTpTrasporto = " + c_IdTpTrasporto.getIntValue();
		dmc.setQuery(ls);
		giorno.setDMC(dmc);
		if ( ! giorno.load(PDC.LOAD_NOWHERECLAUSE)) {
			addMessageError("Errore in lettura giorno.");
			return false;
		}
		if ( giorno.getStatus() == PDCStatus.NO_DATA) {
			giorno.setDtGiorno(dt);
			giorno.setIdTpTrasporto(c_IdTpTrasporto.getIntValue());
			giorno.inserisci();
		}
		SoggAR ar = getPDC();
		ar.setIdGiorno(giorno.getIdGiorno());
		return true;
	}

	
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
				
				
			setRedirect(getServletFullPath() + "?MTipo=V&MPasso=2"+"&IdSoggAndata="+getPDC().getIdSoggAndata()+"&Anno="+c_Anno.getIntValue()+"&Mese="+c_Mese.getIntValue()+"&Giorno="+c_Giorno.getIntValue()+"&TpSoggetto="+c_TpSoggetto.getIntValue()+"&IdTpTrasporto="+c_IdTpTrasporto.getIntValue()+"&AR="+getPDC().getAR());
			addMessageInfo("Inserimento completato correttamente. Chiudere la maschera per vedere il dato aggiornato.");
		}
		return true;
	}

	@Override
	public boolean afterExecMod(Passo nPasso, OpRes opRes) throws Exception {
		if (!super.afterExecMod(nPasso, opRes)) {
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
				
				
			setRedirect(getServletFullPath() + "?MTipo=V&MPasso=2"+"&IdSoggAndata="+getPDC().getIdSoggAndata()+"&Anno="+c_Anno.getIntValue()+"&Mese="+c_Mese.getIntValue()+"&Giorno="+c_Giorno.getIntValue()+"&TpSoggetto="+c_TpSoggetto.getIntValue()+"&IdTpTrasporto="+c_IdTpTrasporto.getIntValue()+"&AR="+getPDC().getAR());
			addMessageInfo("Inserimento completato correttamente. Chiudere la maschera per vedere il dato aggiornato.");
		}
		return true;
	}

		
	@Override
	protected boolean isShowPageAfterModify() {
		if ( getRedirect() == null || getRedirect().length() == 0)
			return true;
		return false;
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
