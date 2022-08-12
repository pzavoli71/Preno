package sm.ciscoop.preno.hic.prenota;

import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;

import enumerati.EnumAR;
import sm.ciscoop.pdc.*;
import sm.ciscoop.preno.pdc.prenota.*;
import sm.ciscoop.preno.pdc.soggetti.Soggetto;
import sm.ciscoop.stdlibs.baseutils.output.PseudoFragment;
import sm.ciscoop.stdlibs.baseutils.types.DtUtil;
import sm.ciscoop.stdlibs.baseutils.types.Text;
import sm.ciscoop.stdlibs.db.output.AnySelect;
import sm.ciscoop.dmc.DMCDB;
import sm.ciscoop.servlet.OpRes;
import sm.ciscoop.servlet.Passo;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

	
import java.util.Enumeration;
import sm.ciscoop.preno.hic.masks.AppDynamicListMask;
import sm.ciscoop.preno.hic.masks.AppListMask;
import sm.ciscoop.dmc.DMCDB;
		

/*-
 * Title:
 * Description:  Lista dinamica per Giorno
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
	INSERT INTO zTrans ([CdPDC],[DsPDC],[IdNodo],[IdPadre],[Flag],[ultagg],[utente]) VALUES ('HLstGiorno' ,'HLstGiorno',@idnodo,0,0,GETDATE(),'appl')
	INSERT INTO zPermessi([CdGruppo],[CdPDC], [Descrizione], [Permesso], [ultagg], [utente]) VALUES (1, 'HLstGiorno',' ','LAGMIRVC',getdate(),'appl')
	--INSERT INTO zPermessi([CdGruppo],[CdPDC], [Descrizione], [Permesso], [ultagg], [utente]) VALUES (xxx, 'HLstGiorno',' ','LAGMIRVC',getdate(),'appl')

	
	//Voce di Menu.xml
	<Voce funzione="HLstGiorno" href="/prenota/HLstGiorno">Lista HLstGiorno</Voce>

 *
 */

@WebServlet(value=HLstGiorno.SERVLET_URL, name = HLstGiorno.SERVLET_NAME) 
	public class HLstGiorno extends AppListMask<Giorno> {

	
	private static final long  serialVersionUID = 1L;
	
	
	public static final String SERVLET_NAME     = "HLstGiorno";
	public static final String SERVLET_URL      = "/prenota/" + HLstGiorno.SERVLET_NAME;

	IntAttr c_Anno;
	IntAttr c_Mese;
	IntAttr c_IdTpTrasporto;
	
	StringBuffer stGiorni;
	@Override
	public void initMsk() throws Exception  {
		super.initMsk();
		Attributi attrs = getAttributi();
		c_Anno = (IntAttr) attrs.add("Anno", PDCType.INTEGER);
		c_Mese = (IntAttr) attrs.add("Mese", PDCType.INTEGER);
		c_IdTpTrasporto = (IntAttr) attrs.add("IdTpTrasporto", PDCType.INTEGER);
		c_IdTpTrasporto.setValue(1);
	}

	public boolean beforeExecList(Passo nPasso) throws Exception {
		String relaz[] = {Giorno.CSZrel_SoggAR, Giorno.CSZrel_TipoTrasporto, SoggAR.CSZrel_Soggetto};
		getPDC().enableStruct(relaz, true);
		
		sendToTelegram();
		
		return true;
	}

	@Override
	public String getSelect() throws Exception {
		String ls = "select top 1000 * from "+Giorno.CSZ_DBTable;
		
		ls += " INNER JOIN "+SoggAR.CSZ_DBTable;
		ls += " ON 1=1  AND "+SoggAR.CSZ_DBTable+".IdGiorno = "+Giorno.CSZ_DBTable+".IdGiorno";
		ls += " INNER JOIN "+Soggetto.CSZ_DBTable;
		ls += " ON 1=1  AND "+Soggetto.CSZ_DBTable+".IdSoggetto = "+SoggAR.CSZ_DBTable+".IdSoggetto";
		ls += " INNER JOIN "+TipoTrasporto.CSZ_DBTable;
		ls += " ON 1=1  AND "+TipoTrasporto.CSZ_DBTable+".IdTpTrasporto = "+Giorno.CSZ_DBTable+".IdTpTrasporto";
		ls += " WHERE 1 = 1 ";
		if ( c_Anno.isEmptyNullOrZero()) {
			c_Anno.setValue(DtUtil.getCurrentYear());
		}
		if ( c_Mese.isEmptyNullOrZero()) {
			c_Mese.setValue(DtUtil.getCurrentMonth() + 1);
		}
		Date dtinizio = DtUtil.createDate(c_Anno.getIntValue(), c_Mese.getIntValue(), 1);
		Date dtfine = DtUtil.getUltimoDelMese(dtinizio);
		if ( dtinizio != null) {
			ls += " AND "+Giorno.CSZ_DBTable+".DtGiorno >= "+getJBB().getFormattedVal(dtinizio, true);
		}
		if ( dtfine != null) {
			ls += " AND "+Giorno.CSZ_DBTable+".DtGiorno <= "+getJBB().getFormattedVal(dtfine, true);
		}
		if ( !c_IdTpTrasporto.isEmptyNullOrZero()) {
			ls += " AND "+Giorno.CSZ_DBTable+".IdTpTrasporto = "+c_IdTpTrasporto.getIntValue();
		}
		ls += " ORDER BY DtGiorno";
		return ls;
	}

	
  @Override
  public int getMaxNumRecords() throws Exception {
    if ( isToExcel()) {
    	return 10000;
    }
	return DEFAULT_MAX_NUM_RECORDS;
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
			
				String lsTipoTrasporto = "select IdTpTrasporto, NomeTpTrasporto FROM "+TipoTrasporto.CSZ_DBTable;
				lsTipoTrasporto += " WHERE 1 = 1 "; // Ricordarsi di abilitare il combo togliendo questo filtro
				appendToResponse(new AnySelect(lsTipoTrasporto, "TipoTrasporto"));
								
				// Creo l'array dei giorni del mese
				stGiorni = new StringBuffer(1000);
				if ( c_Anno.isEmptyNullOrZero())
					c_Anno.setValue(DtUtil.getCurrentYear());
				if ( c_Mese.isEmptyNullOrZero())
					c_Mese.setValue(DtUtil.getCurrentMonth() + 1);
				Date dtinizio = DtUtil.createDate(c_Anno.getIntValue(), c_Mese.getIntValue(), 1);
				Date dtfine = DtUtil.getUltimoDelMese(dtinizio);
				Date dt = dtinizio;
				Calendar cal = Calendar.getInstance();
				cal.setTime(dt);
				int settinizio = cal.get(Calendar.WEEK_OF_YEAR);
				stGiorni.append("<Giorni>\n");
				while ( DtUtil.compare(dt, dtfine) <= 0) {
					int gset = DtUtil.getWeekNumber(dt);
					String nomegiorno = DtUtil.getDayName(dt);
					cal.setTime(dt);
					int sett = cal.get(Calendar.WEEK_OF_YEAR);
					sett = sett - settinizio + 1;
					stGiorni.append("<G num='" + DtUtil.getDay(dt) + "' gset='" + gset + "' sett='" + sett + "'/>");
					dt = DtUtil.addGiorni(dt, 1);
				}
				stGiorni.append("</Giorni>\n");
				PseudoFragment pf = PseudoFragment.fromXML(stGiorni.toString());
				appendToResponse("Giorni",pf);
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
  
  public static void sendToTelegram() {
      String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";

      //Add Telegram token (given Token is fake)
      String apiToken = "5317668012:AAEWtqw2GS2ELcqz3cuNT2LFrkZQovqpM7E";
    
      //Add chatId (given chatId is fake)
      //String chatId = "728936845";
      String chatId = "@TrasportiCalcio";
      Date dt = new Date();
      String text = "Hello world 4 " + dt;

      urlString = String.format(urlString, apiToken, chatId, text);
/*
      try {
          URL url = new URL(urlString);
          URLConnection conn = url.openConnection();
          InputStream is = new BufferedInputStream(conn.getInputStream());
          is.close();
      } catch (IOException e) {
          e.printStackTrace();
      }
      */
  }  
}
