
package sm.ciscoop.preno.hic.busy;

import javax.servlet.annotation.WebServlet;
import sm.ciscoop.pdc.*;
import sm.ciscoop.stdlibs.baseutils.exceptions.CISException;
import sm.ciscoop.servlet.Passo;
import sm.ciscoop.preno.pdc.busy.*;
import sm.ciscoop.preno.hic.masks.AppSingleMask;
import sm.ciscoop.stdlibs.baseutils.types.Text;
import sm.ciscoop.stdlibs.db.output.AnySelect;
import sm.ciscoop.dmc.DMCDB;
import java.util.Enumeration;
import sm.ciscoop.servlet.OpRes;



/*-
 * Title:
 * Description:  Single per Lavoro
 * Copyright:    Copyright (c) 
 * Company:      CIScoop
 * @author
 * @version 1.0
 *
 *
 
		<servlet>
			<servlet-name>HLavoro</servlet-name>
				<servlet-class>sm.ciscoop.preno.hic.busy.HLavoro</servlet-class>
			</servlet>
			<servlet-mapping>
				<servlet-name>HLavoro</servlet-name>
				<url-pattern>/servlet/sm.ciscoop.preno.hic.busy.HLavoro</url-pattern>
			</servlet-mapping>
		</servlet>	
		 
		DECLARE @idnodo INT = 0;
		SET @idnodo = (select MAX(idnodo) FROM zTrans) + 1;
		if @idnodo IS NULL
		  BEGIN
		    SET @idnodo = 1
		  END
		print @idnodo
		INSERT INTO zTrans ([CdPDC],[DsPDC],[IdNodo],[IdPadre],[Flag],[ultagg],[utente]) VALUES ('HLavoro' ,'HLavoro',@idnodo,0,0,GETDATE(),'appl')
		INSERT INTO zPermessi([CdGruppo],[CdPDC], [Descrizione], [Permesso], [ultagg], [utente]) VALUES (1,  'HLavoro',' ','AGMIRVC',getdate(),'appl')
		--INSERT INTO zPermessi([CdGruppo],[CdPDC], [Descrizione], [Permesso], [ultagg], [utente]) VALUES (xxx,  'HLavoro',' ','AGMIRVC',getdate(),'appl')
		
		// Voce di Menu.xml
		<Voce funzione="HLavoro" href="/busy/HLavoro?MTipo=I&amp;MPasso=1">Single HLavoro</Voce>

 *
 */

@WebServlet(value=HLavoro.SERVLET_URL, name = HLavoro.SERVLET_NAME) 
public class HLavoro extends AppSingleMask<Lavoro> {
	
	private static final long  serialVersionUID = 1L;
	
	public static final String SERVLET_NAME     = "HLavoro";
	public static final String SERVLET_URL      = "/busy/" + HLavoro.SERVLET_NAME;

	private IntAttr   c_IdObiettivo;
 	private IntAttr   c_IdLavoro;
 	private DateAttr  c_DtLavoro;
 	private IntAttr   c_OraInizio;
 	private IntAttr   c_MinutiInizio;
 	private CharAttr  c_NotaLavoro;
 	private IntAttr   c_OraFine;
 	private IntAttr   c_MinutiFine;
 	
	
	// UploadAttr
	// UploadAttr	c_FileUpload;
	@Override
	public void initAttrs() throws Exception  {
		super.initAttrs();

		Attributi attrs = getAttributi();
	c_IdObiettivo = attrs.addIntAttr("IdObiettivo");
		c_IdLavoro = attrs.addIntAttr("IdLavoro");
		c_DtLavoro = attrs.addDateAttr("DtLavoro");
		c_OraInizio = attrs.addIntAttr("OraInizio");
		c_MinutiInizio = attrs.addIntAttr("MinutiInizio");
		c_NotaLavoro = attrs.addCharAttr("NotaLavoro");
		c_OraFine = attrs.addIntAttr("OraFine");
		c_MinutiFine = attrs.addIntAttr("MinutiFine");
		
	}



	@Override
	protected Object[] abilitaRelazioniPDC() throws Exception {
		// TODO Auto-generated method stub
		Object[] relaz = new Object[] {
Lavoro.CSZrel_Obiettivo,10,

			""
		};
		return relaz;
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

				if (camporicerca.equalsIgnoreCase("IdObiettivo")) {
					String ls = "select top 100 IdObiettivo, DtInizioObiettivo as nome FROM "+Obiettivo.CSZ_DBTable;
					ls += " WHERE 1 = 1";
					if ( desc != null && desc.length() >= 1) {
						desc = desc.replaceAll("'", "''");
						ls += " AND DtInizioObiettivo like '"+desc.trim()+"%'";
					} else if ( cd != null && cd.length() > 0) {
						ls += " AND IdObiettivo = "+cd.trim();
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
    			if (numPagina == null ) {
    				numPagina = 0;
    			}
    			if (rowsPerPage == null ) {
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

				String lsObiettivo = "select IdObiettivo, DtInizioObiettivo FROM "+Obiettivo.CSZ_DBTable;
				lsObiettivo += " WHERE 1 = 1 "; // Ricordarsi di abilitare il combo togliendo questo filtro
				appendToResponse(new AnySelect(lsObiettivo, "Obiettivo"));				

			}
		}	catch (Exception ex) {
			addMessage(ex);
		}
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

	
	/* Se si hanno fileitem (file da fare upload, scommentare questa funzione e mettere il path del documento dentro il pdc
	@Override
	public boolean beforeExecIns(int nPasso, int nOpRes) {
		if (!super.beforeExecIns(nPasso, nOpRes)) {
			return false;
		}

		if ( c_FileUpload.getCISFileItem() != null &amp;&amp; c_FileUpload.getFileName().length() > 0) {
			m_mioPDC.setNomeFile("/"+comu.getIdComunicazionePadre()+"/"+getShibIdUtente()+"/"+c_FileUpload.getFileName());
		}
	}
	*/
	
	@Override
	protected boolean afterExecIns(Passo nPasso, OpRes opRes) throws Exception {
		if (!super.afterExecIns(nPasso, opRes)) {
			return false;
		}
		if ( nPasso == Passo.ESECUZIONE && opRes == OpRes.OK) {
			try {
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
				
				
				setRedirect(getServletFullPath() + "?MTipo=V&MPasso=2"+"&IdLavoro="+getPDC().getIdLavoro());
				addMessageInfo("Inserimento completato correttamente. Chiudere la maschera per vedere il dato aggiornato.");
			} catch(Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}	

  /*
   * Consente di controllare il permesso per l'esecuzione dell'operazione.
   * Il programmatore dovrebbe controllare se l'utente corrente ha i permessi per operare sul dato presente nel m_mioPDC.
   * @see sm.ciscoop.newAppl.hic.SingleMaskNewAppl#checkPermesso(char)
   */
   /*
  @Override
  protected boolean checkPermesso(char modalita) {
	  try {
		  SessioneMask sess = getSessionePreno();
		  int codiss = sess.getProfiloUtente().getCodiss();
		  int codoe = sess.getProfiloUtente().getCodoe();
		  int cdruolo = sess.getProfiloUtente().getIdRuolo();
		  // Controllare anche le deleghe del portale
		  if ( codoe == m_mioPDC.getCodOE())
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
	  } catch(Exception e) {
		  e.printStackTrace();
		  addMessage("Errore grave in "+getClass().getSimpleName()+"::checkPermesso", 4);
		  return false;
	  }
	  return true;
  }	
  */
}