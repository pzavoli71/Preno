package sm.ciscoop.preno.hic.prenota;

import javax.servlet.annotation.WebServlet;

import sm.ciscoop.pdc.*;
import sm.ciscoop.preno.hic.masks.AppStampeMask;
import sm.ciscoop.preno.pdc.prenota.*;
	
import sm.ciscoop.stdlibs.baseutils.types.Text;
import sm.ciscoop.stdlibs.db.output.AnySelect;
import sm.ciscoop.transportreport.configurator.StdReportConfigurator;
import sm.ciscoop.dmc.DMCDB;
import sm.ciscoop.servlet.OpRes;
import java.util.*;

	
/*-
 * Title:
 * Description:  Stampa per Giorno
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
	INSERT INTO zTrans ([CdPDC],[DsPDC],[IdNodo],[IdPadre],[Flag],[ultagg],[utente]) VALUES ('HStGiorno' ,'HStGiorno',@idnodo,0,0,GETDATE(),'appl')
	INSERT INTO zPermessi([CdGruppo],[CdPDC], [Descrizione], [Permesso], [ultagg], [utente]) VALUES (1, 'HStGiorno',' ','LAGMIRVC',getdate(),'appl')
	--INSERT INTO zPermessi([CdGruppo],[CdPDC], [Descrizione], [Permesso], [ultagg], [utente]) VALUES (xxx, 'HStGiorno',' ','LAGMIRVC',getdate(),'appl')

		
		// Voce di Menu.xml
		<Voce target="Principale" funzione="HStGiorno" href="prenota/HStGiorno">Stampa HStGiorno</Voce>

 *
 */

@WebServlet(value=HStGiorno.SERVLET_URL, name = HStGiorno.SERVLET_NAME) 
public class HStGiorno extends AppStampeMask<Giorno, ReportGiorno> {
	
	
	private static final long  serialVersionUID = 1L;

	public static final String SERVLET_NAME     = "HStGiorno";
	public static final String SERVLET_URL      = "/prenota/" + HStGiorno.SERVLET_NAME;


	//IntAttr c_CdSoggetto;
	
	
	CharAttr c_Protocollo;


	
	@Override
	public void initMsk() throws Exception  {
		super.initMsk();
		//Attributi attrs = getAttributi();
		//c_Protocollo = attrs.addCharAttr("Protocollo");
	}

	@Override
	public Giorno initPDC() throws Exception {
		Giorno m_mioPDC = new Giorno(getMsgHandler());
		Object[] struct = new Object[] {
			Giorno.CSZrel_SoggAR,5,
			Giorno.CSZrel_TipoTrasporto,5,
			
			""
		};
		m_mioPDC.enableStruct(struct , true);
		return m_mioPDC;
	}

	
	
   /* 
	Scommentare per eseguire controlli sugli attributi di maschera. Altrimenti la stampa viene eseguita subito
	  forzando l'attributo cisStampaDiretta
	@Override
	public boolean devoControllareParametri() {
		return true;
	}
	
	@Override
	public void controllaParametri() throws CISException {
		if (getPDC().getCdGruppo() == 10) {
			return;
		}
		addMessage("Devi impostare il valore a 10!", 2);
	}
	*
	*/
  
	@Override
	protected void beforeCreateReport() throws Exception {
		getReportConfigurator().addParameter("IdGiorno", getPDC().getIdGiorno());
		getReportConfigurator().setQuery(getQuery());
	}
	

  private String getQuery() {
    //String query = "";
    return null;
  }

  /* Il file di stampa viene generato nel direttorio che viene indicato da REPOS_DOCUM nel properties.*/
  @Override
  protected String getFileName() {
    return "NomeFileDiStampa.pdf";
  }
  
  /**
   * Indica se salvare su filesystem il file generato. Se è true il path dove
   * verrà salvato il file verrà preso dalla proprietà REPOS_DOCUM del
   * .properties.
   * 
   * @return
   */
  public boolean isFileDaSalvare() {
    return false;
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
