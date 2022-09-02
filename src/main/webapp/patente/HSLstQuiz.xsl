<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" version="1.0">

<xsl:import href="${AppThemes}/atlanta-1.1.0-SNAPSHOT/xsl/theme.xsl"/>
<xsl:import href="${AppThemes}/atlanta-1.1.0-SNAPSHOT/xsl/liste2.xsl"/>
<xsl:import href="${AppXSL}/app.xsl"/>


<xsl:output method='html' indent='yes'/>
<xsl:decimal-format name="euro" decimal-separator="," grouping-separator="."/>

<xsl:template match="/">
<xsl:if test="/DOCUMENTO/parametri/flSoloCaricaRelaz = 'true'">
	<xsl:apply-templates select="/DOCUMENTO/*[@tipo = 'PDC']/*[@tipo='LISTA' or @tipo='PDC']" mode="tmlRel"/>
</xsl:if>

<xsl:if test="/DOCUMENTO/parametri/flSoloCaricaRelaz != 'true' or count(/DOCUMENTO/parametri/flSoloCaricaRelaz) = 0">
	<xsl:call-template name="baseListe"/>
</xsl:if>
</xsl:template>



<xsl:template name="CssSpec" >
	<style>
	/*.cis-field-container .ValoreAttributo {
		width:350px;
	}*/
	
	.screen-s .cis-field-container, .screen-m .cis-field-container {
		display:inline-block;
	}
	
	</style>
</xsl:template>

<xsl:template  name="def_javascript">
function getTitolo(){
  return '  '  + '<xsl:value-of select="$tipo_transaz"/>' + ' <xsl:value-of select="$TitoloPagina"/>' + '   ';
}


function getAutoHref(){
	var href="/preno/patente/HSLstQuiz?MTipo=L&amp;MPasso=1";
	return href;
}


$(function() {
	// Registra le funzioni per il menu contestuale e le relazioni
	AppGlob.registraFunzioniPerListe(apriMenu, apriRigaRelazioni);
	AppGlob.inizializzaLista();
});

function apriMenu(chiave, nomepdc, riga) {
	// La riga corrispondente all'elemento selezionato e' la seguente
	var spezzanome = nomepdc.split('.');
	var nome = spezzanome[spezzanome.length - 1];
	var tr = riga; //$('#Riga'+nome+'_'+id);
	var chiavi = chiave.split('_');
	if ( nome == 'Quiz') {
		var IdQuiz = chiavi[0];
	} else if (nome == 'DomandaQuiz') {
		var IdDomandaTest = chiavi[0];
	} else if (nome == 'Test') {
		var IdTest = chiavi[0];

	}
}

// Apre una riga sotto quella corrente, per visualizzare le relazioni aperte con altri pdc
function apriRigaRelazioni(chiave, nomepdc, riga, rigarel) {
	// La riga corrispondente all'elemento selezionato e' la seguente
	var spezzanome = nomepdc.split('.');
	var nome = spezzanome[spezzanome.length - 1];
	var tr = riga; //$('#Riga'+nome+'_'+id);
	var chiavi = chiave.split('_');
	if ( nome == 'Quiz') {
				var IdQuiz = chiavi[0];

		// Scommentare per fare il caricamento manuale ogni volta che si clicca sul + di questa relazione,
		// oppure passare true come terzo parametro alla AppGlob.apriRigarelazione
		//riga.next().find('.refresh_btn.cis-button').click()

	} else if (nome == 'DomandaQuiz') {
		var IdDomandaTest = chiavi[0];

		// Scommentare per fare il caricamento manuale ogni volta che si clicca sul + di questa relazione,
		// oppure passare true come terzo parametro alla AppGlob.apriRigarelazione
		//riga.next().find('.refresh_btn.cis-button').click()
		} else if (nome == 'Test') {
		var IdTest = chiavi[0];

		// Scommentare per fare il caricamento manuale ogni volta che si clicca sul + di questa relazione,
		// oppure passare true come terzo parametro alla AppGlob.apriRigarelazione
		//riga.next().find('.refresh_btn.cis-button').click()
	
	}
}

function richiestaComando(nomecomando, chiave, dati, href, callback) {
	// Qui posso variare il contenuto dell'area dati, aggiungendo attributi con nome e valore
	// che verranno riportati alla servlet come parametri.
	//var valore = prompt('inserisci il comando per ',chiave);
	dati['IdQuiz'] = chiave;
	debugger;
	return true;
}

// Funzione che gestisce il ritorno del comando
function comandoTerminato(nomecomando, chiave, data) {
	var errore = $('DOCUMENTO > ERRORE > USER', data).text();
	debugger;
	AppGlob.emettiErrore(errore);
}

var ajaxrequest = null;
function caricaRelazione(obj) {
	// Devo andare a ritroso nel dom fino a trovare un div con class="divRelazione"
	var conf = {
		domObj : obj,
		relazioni : {
			Quiz_DomandaQuiz :['IdQuiz'],Quiz_Test :['IdQuiz'], DomandaQuiz_RispQuiz: ['IdDomandaTest']
		},
		href : "/preno/patente/HSLstQuiz",
		params : {
			MTipo : "L",
			MPasso : 2
		},
		//scommenta questo metodo per aprire un dialog quando viene caricata una relazione
		//beforecaricarelaz : creaDialogRelaz,
		aftercaricarelaz : function(dati,data) {
			//TODO
		}
	}
	AppGlob.stdCaricaRelaz(conf);
}

//scommenta il metodo beforecaricarelaz per aprire un dialog quando viene caricata una relazione
function creaDialogRelaz(dati,afterclose){
	$('#dialog').dialog({
		autoOpen: true,
		modal:true,
		title:'Inserisci i parametri',
		position:{ my: 'top', at: 'top+150' },
		show: {
			effect: "blind",
			duration: 100
		},
		hide: {
			effect: "explode",
			duration: 400
		},
		buttons: {
			Ok: function() {
				var dtvaluta = $( this ).find('#DtValuta').val();
				var annoliq = $( this ).find('#AnnoLiq').val();
				var numliq = $( this ).find('#NumLiq').val();
				$( this ).dialog( "close" );
				afterclose(dati);
			},
			Annulla: function() {
				$( this ).dialog( "close" );
			} 
		}
	});
}

function rispondi(idrisptest, valore, tag) {
	// Devo andare a ritroso nel dom fino a trovare un div con class="divRelazione"
	var href = "/preno/patente/HRispQuiz";
	var dati = {};
	dati['MTipo'] = 'R';
	dati['MPasso'] = 3;
	dati['GetXML'] = true;
	dati['_bt_Modifica'] = 'Conferma';
	dati['IdRispTest'] = idrisptest;
	if ( valore == true) {
		dati['RespVero'] = true;
		dati['RespFalso'] = false;
	} else {
		dati['RespVero'] = false;
		dati['RespFalso'] = true;	
	}
	//dati['_jsonData'] = 1;
    if (ajaxrequest)
    	ajaxrequest.abort();
    AppGlob.showLoading();
    ajaxrequest = $.ajax({
		url: href,
		cache: false,
		type:'post',
		data: dati,
		dataType: 'xml'
    }).done(function(data) {
    	AppGlob.hideLoading();	
		caricaRelazione(tag);
	}).always(function(data) {
    	AppGlob.hideLoading();	
		caricaRelazione(tag);
	});
}
</xsl:template>

<!-- ============================================ -->
<!--     Titolo            						  -->
<!-- ============================================ -->
<xsl:template name="Titolo" >
	<xsl:value-of select="$TitoloPagina"/>
</xsl:template>
<xsl:variable name="TitoloPagina">Quiz</xsl:variable>

<!-- ============================================ -->
<!--    Intestazione                             -->
<!-- ============================================ -->
<xsl:template name="IntestazioneLista">
		<div style="display:inline-block; width:300px;" border="0">
		<a href="javascript:void(0)" onclick="document.location.reload()" class="refresh_btn cis-button"><i class="fa fa-refresh"/></a>
			<xsl:call-template name="ButtonHref">
				<xsl:with-param name="href">/preno/patente/HQuiz?MTipo=I&amp;MPasso=1</xsl:with-param>
				<xsl:with-param name="title">Aggiungi un Quiz</xsl:with-param>
				<xsl:with-param name="fa-class">fa-plus-circle</xsl:with-param>
				<xsl:with-param name="onunload">document.location.reload()</xsl:with-param>
				<xsl:with-param name="caption">Gestione Quiz</xsl:with-param>
			</xsl:call-template>
		</div>
</xsl:template>

<!-- ============================================ -->
<!--    Intestazione tabella                      -->
<!-- ============================================ -->
<xsl:template name="IntestaTabella">
	<tr>
		<th ></th>
		<th data-nomecol='IdQuiz'>Id</th>

		<th data-nomecol='CdUtente'>CdUtente</th>


		<th data-nomecol='DtCreazioneTest'>Data creazione</th>

		<th data-nomecol='DtCreazioneTest'></th>

	</tr>
</xsl:template>



<!-- ============================================ -->
<!--    Righe tabella                             -->
<!-- ============================================ -->
<xsl:template match="RECORD" mode="lista" >

<xsl:variable name="rigapos"><xsl:value-of select="@progr"/></xsl:variable>
   <tr id='RigaQuiz_{@progr}' chiave="{idquiz}">
    <xsl:choose>
      <xsl:when test="(@progr mod 2) = 1"><xsl:attribute name="class">rigaDispari</xsl:attribute></xsl:when>
      <xsl:otherwise><xsl:attribute name="class">rigaPari</xsl:attribute></xsl:otherwise>
    </xsl:choose>
		<td>
			<xsl:call-template name="BottoniRiga">
				<xsl:with-param name="nomepdc">Quiz</xsl:with-param>
				<xsl:with-param name="pos"><xsl:value-of select="$rigapos"/></xsl:with-param>
				<xsl:with-param name="ramochiuso">true</xsl:with-param>
			</xsl:call-template>
			<xsl:call-template name="ButtonHref" >
				<xsl:with-param name="href">/preno/patente/HQuiz?MTipo=V&amp;MPasso=2&amp;IdQuiz=<xsl:value-of select="idquiz"/></xsl:with-param>
				<xsl:with-param name="title">Modifica un Quiz</xsl:with-param>
				<xsl:with-param name="fa-class">fa-edit</xsl:with-param>
				<xsl:with-param name="onunload">document.location.reload()</xsl:with-param>
				<xsl:with-param name="caption">Gestione Quiz</xsl:with-param>
			</xsl:call-template>

		</td>
		<td><xsl:value-of select="idquiz"/></td>

		<td><xsl:value-of select="idutente"/></td>


		<td><xsl:value-of select="dtcreazionetest"/></td>

		<!-- Scommentare queste righe per avere comandi da eseguire su ogni riga -->
		<!-- I comandi devono avere il permesso (su zTrans) del tipo  HLstElabCeliaci:ElaboraMeseSmac-->
		<td align="right" class="bottoni_comando">
 		    <xsl:call-template name="linkComando">
		    	<xsl:with-param name="href">/preno/patente/HSLstQuiz</xsl:with-param>
		    	<xsl:with-param name="nomecomando">ConfermaTest</xsl:with-param>
		    	<xsl:with-param name="chiave"><xsl:value-of select='idquiz'/></xsl:with-param>
		    	<xsl:with-param name="ParamServlet">{MTipo:'L',MPasso:'2'}</xsl:with-param>
				<xsl:with-param name="title">Conferma il test</xsl:with-param>
				<xsl:with-param name="fa-class">fa-calculator</xsl:with-param>
		    </xsl:call-template>

	<!-- 
			<a href="javascript:void(0)"  style="margin-left:1px; margin-right:5px;font-size:1.5em" onclick="AppGlob.eseguiComando('/preno/patente/HSLstQuiz', 'CreaPiattaforma', '{idquiz}', {{MTipo:'L',MPasso:'2'}}, richiestaComando, comandoTerminato)" title="Elabora il comando">
				<i class="fa fa-calculator"></i>
			</a>
			<a href="javascript:void(0)"  style="margin-left:1px; margin-right:5px;font-size:1.5em" onclick="elaboraPiattaforma()" title="Stampa il report">
				<i class="fa fa-print"></i>
			</a>
	-->
		</td>
		</tr >

	<xsl:call-template name="RelazioniQuiz">
		<xsl:with-param name="rigapos"><xsl:value-of select="$rigapos"/></xsl:with-param>
	</xsl:call-template>		
</xsl:template>


<!-- Scommentare se si vuole abilitare la modalita T sulla tabella principale
<xsl:template name="BaseListFinePagina" >
<script language="javascript">
		$('#tabPrinc').cisTTrans();
</script>
</xsl:template>
-->



<!-- ============================================ -->
<!--    Relazioni tra i pdc                       -->
<!-- ============================================ -->
<xsl:template name="RelazioniQuiz">
<xsl:param name="rigapos"><xsl:value-of select="position()"/></xsl:param>
	<tr id="RigaRelQuiz_{$rigapos}">
    <xsl:choose>
      <xsl:when test="(position() mod 2) = 1"><xsl:attribute name="class">rigaDispari</xsl:attribute></xsl:when>
      <xsl:otherwise><xsl:attribute name="class">rigaPari</xsl:attribute></xsl:otherwise>
    </xsl:choose>
    <td colspan="100" class="closed tdRelazione" >
		<!--xsl:choose>
		<xsl:when test="'1' = '2'"></xsl:when>
			<xsl:when test="count(ListaQuiz_DomandaQuiz/*[@progr or @result]) &gt; 0"><xsl:attribute name="class">open</xsl:attribute></xsl:when>
			<xsl:when test="count(ListaQuiz_Test/*[@progr or @result]) &gt; 0"><xsl:attribute name="class">open</xsl:attribute></xsl:when>
		</xsl:choose-->


    <div style="margin-left:20px;" id="divRel_Quiz_DomandaQuiz_{$rigapos}" class="divRelazione" chiave="{idquiz}" nomepdc="sm.ciscoop.preno.pdc.patente.Quiz" nomerelaz="Quiz_DomandaQuiz">
		<div class="titolorelaz"><a class="refresh_btn cis-button" href="javascript:void(0)" onclick="caricaRelazione(this)">
		<i class="fa fa-refresh"/>
		</a>
		<xsl:call-template name = "ButtonHref" >
			<xsl:with-param name="href">/preno/patente/HDomandaQuiz?MTipo=I&amp;MPasso=1&amp;IdQuiz=<xsl:value-of select="IdQuiz"/></xsl:with-param>
			<xsl:with-param name="title">Aggiungi un DomandaQuiz</xsl:with-param>
			<xsl:with-param name="fa-class">fa-plus-circle</xsl:with-param>
			<xsl:with-param name="onunload">caricaRelazione(this.atag)</xsl:with-param>
			<xsl:with-param name="caption">Gestione DomandaQuiz</xsl:with-param>
		</xsl:call-template>		
		&#xA0;
		<span class="titolo1">Relazione DomandaQuiz</span>
		<div class="btn_minimax" title="Minimizza"><i class="fa fa-window-minimize"></i></div>
		</div>
		<xsl:apply-templates select="ListaQuiz_DomandaQuiz" mode="tmlRel"><xsl:with-param name="pos"><xsl:value-of select="position()"/></xsl:with-param></xsl:apply-templates>
		</div>
		
    <div style="margin-left:20px;" id="divRel_Quiz_Test_{$rigapos}" class="divRelazione" chiave="{idquiz}" nomepdc="sm.ciscoop.preno.pdc.patente.Quiz" nomerelaz="Quiz_Test">
		<div class="titolorelaz"><a class="refresh_btn cis-button" href="javascript:void(0)" onclick="caricaRelazione(this)">
		<i class="fa fa-refresh"/>
		</a>
		<xsl:call-template name = "ButtonHref" >
			<xsl:with-param name="href">/preno/patente/HTest?MTipo=I&amp;MPasso=1&amp;IdQuiz=<xsl:value-of select="IdQuiz"/></xsl:with-param>
			<xsl:with-param name="title">Aggiungi un Test</xsl:with-param>
			<xsl:with-param name="fa-class">fa-plus-circle</xsl:with-param>
			<xsl:with-param name="onunload">caricaRelazione(this.atag)</xsl:with-param>
			<xsl:with-param name="caption">Gestione Test</xsl:with-param>
		</xsl:call-template>		
		&#xA0;
		<span class="titolo1">Relazione Test</span>
		<div class="btn_minimax" title="Minimizza"><i class="fa fa-window-minimize"></i></div>
		</div>
		<xsl:apply-templates select="ListaQuiz_Test" mode="tmlRel"><xsl:with-param name="pos"><xsl:value-of select="position()"/></xsl:with-param></xsl:apply-templates>
		</div>
		

    </td>
	</tr>
</xsl:template>

<xsl:template match="ListaQuiz_DomandaQuiz" mode="tmlRel">
<xsl:param name="pos"/>
		<xsl:if test="count(*[@progr or @result]) &gt; 0">
		<div class="divLista">
		<xsl:call-template name="PaginatoreRelazione"><xsl:with-param name="caricaFunction">caricaRelazione(this)</xsl:with-param></xsl:call-template>
		<table border="0" cellpadding="2" cellspacing="0" class="tabLista" id="tabListaQuiz_DomandaQuiz_{$pos}" nomepdc="Quiz">
			<xsl:call-template name="IntestaTabellaDomandaQuiz"/>
			<xsl:apply-templates select = "*[@progr or @result]" mode="lista" />
		</table>
		<!-- Scommentare per fare una transazione T con uesta tabella <script language="javascript">
				$('#tabListaQuiz_DomandaQuiz_<xsl:value-of select="$pos"/>').cisTTrans();
		</script-->
		</div>
		</xsl:if>
</xsl:template>

<xsl:template match="ListaQuiz_Test" mode="tmlRel">
<xsl:param name="pos"/>
		<xsl:if test="count(*[@progr or @result]) &gt; 0">
		<div class="divLista">
		<xsl:call-template name="PaginatoreRelazione"><xsl:with-param name="caricaFunction">caricaRelazione(this)</xsl:with-param></xsl:call-template>
		<table border="0" cellpadding="2" cellspacing="0" class="tabLista" id="tabListaQuiz_Test_{$pos}" nomepdc="Quiz">
			<xsl:call-template name="IntestaTabellaTest"/>
			<xsl:apply-templates select = "*[@progr or @result]" mode="lista" />
		</table>
		<!-- Scommentare per fare una transazione T con uesta tabella <script language="javascript">
				$('#tabListaQuiz_Test_<xsl:value-of select="$pos"/>').cisTTrans();
		</script-->
		</div>
		</xsl:if>
</xsl:template>

<xsl:template name="IntestaTabellaDomandaQuiz">
<tr>
<th ></th>

     <th data-nomecol="IdCapitolo" >Cap. / Dom.</th>

     <th data-nomecol="Asserzione" >Testo</th>
</tr>
</xsl:template>


<!-- ============================================ -->
<!--    Righe tabella                             -->
<!-- ============================================ -->
<xsl:template match="DomandaQuiz" mode="lista" >

   <tr id='RigaDomandaQuiz_{position()}' chiave='{IdDomandaTest}'>
    <xsl:choose>
      <xsl:when test="(position() mod 2) = 1"><xsl:attribute name="class">rigaDispari</xsl:attribute></xsl:when>
      <xsl:otherwise><xsl:attribute name="class">rigaPari</xsl:attribute></xsl:otherwise>
    </xsl:choose>
		<td style="text-align:left">
			<xsl:call-template name="BottoniRiga">
				<xsl:with-param name="nomepdc">DomandaQuiz</xsl:with-param>
				<xsl:with-param name="pos"><xsl:value-of select="position()"/></xsl:with-param>
				<xsl:with-param name="ramochiuso">true</xsl:with-param>
			</xsl:call-template>
			<xsl:call-template name="ButtonHref">
				<xsl:with-param name="href">/preno/patente/HDomandaQuiz?MTipo=V&amp;MPasso=2&amp;IdDomandaTest=<xsl:value-of select="IdDomandaTest"/></xsl:with-param>
				<xsl:with-param name="title">Modifica un DomandaQuiz</xsl:with-param>
				<xsl:with-param name="fa-class">fa-edit</xsl:with-param>
				<xsl:with-param name="onunload">caricaRelazione(this.atag)</xsl:with-param><!--$("#divRel_Quiz_DomandaQuiz_<xsl:value-of select='IdQuiz'/> > div > a.refresh_btn").click()-->
				<xsl:with-param name="caption">Gestione DomandaQuiz</xsl:with-param>
			</xsl:call-template>
		</td>

		<td><xsl:value-of select="Domanda/IdCapitolo"/>/<xsl:value-of select="Domanda/IdDom"/></td>

		<td><xsl:if test="Domanda/linkimg != ''"><img border="1" src="/preno/quiz/immagini/{Domanda/linkimg}" height="70" style="margin-right:10px"></img></xsl:if><xsl:value-of select="Domanda/Asserzione"></xsl:value-of></td>

		</tr >

	<xsl:call-template name="RelazioniDomandaQuiz"/>
</xsl:template>

<!-- ============================================ -->
<!--    Relazioni tra i pdc                       -->
<!-- ============================================ -->
<xsl:template name="RelazioniDomandaQuiz">
<xsl:param name="rigapos"><xsl:value-of select="position()"/></xsl:param>
	<tr id="RigaRelDomandaQuiz_{$rigapos}">
    <xsl:choose>
      <xsl:when test="($rigapos mod 2) = 1"><xsl:attribute name="class">rigaDispari</xsl:attribute></xsl:when>
      <xsl:otherwise><xsl:attribute name="class">rigaPari</xsl:attribute></xsl:otherwise>
    </xsl:choose>
    <td colspan="100" class="closed tdRelazione" >
		<!--xsl:choose>
		<xsl:when test="'1' = '2'"></xsl:when>
			<xsl:when test="count(ListaQuiz_DomandaQuiz/*[@progr or @result]) &gt; 0"><xsl:attribute name="class">open</xsl:attribute></xsl:when>
			<xsl:when test="count(ListaQuiz_Test/*[@progr or @result]) &gt; 0"><xsl:attribute name="class">open</xsl:attribute></xsl:when>
		</xsl:choose-->


    <div style="" id="divRel_DomandaQuiz_RispQuiz_{$rigapos}" class="divRelazione" chiave="{IdDomandaTest}" nomepdc="sm.ciscoop.preno.pdc.patente.DomandaQuiz" nomerelaz="DomandaQuiz_RispQuiz">
		<div class="titolorelaz"><a class="refresh_btn cis-button" href="javascript:void(0)" onclick="caricaRelazione(this)">
		<i class="fa fa-refresh"/>
		</a>
		<xsl:call-template name = "ButtonHref" >
			<xsl:with-param name="href">/preno/patente/HRispQuiz?MTipo=I&amp;MPasso=1&amp;IdDomandaTest=<xsl:value-of select="IdDomandaTest"/></xsl:with-param>
			<xsl:with-param name="title">Aggiungi una Risposta</xsl:with-param>
			<xsl:with-param name="fa-class">fa-plus-circle</xsl:with-param>
			<xsl:with-param name="onunload">caricaRelazione(this.atag)</xsl:with-param>
			<xsl:with-param name="caption">Gestione Rispostaz</xsl:with-param>
		</xsl:call-template>		
		&#xA0;
		<span class="titolo1">Relazione Risposta</span>
		<div class="btn_minimax" title="Minimizza"><i class="fa fa-window-minimize"></i></div>
		</div>
		<xsl:apply-templates select="ListaDomandaQuiz_RispQuiz" mode="tmlRel"><xsl:with-param name="pos"><xsl:value-of select="position()"/></xsl:with-param></xsl:apply-templates>
		</div>
		
    </td>
	</tr>
</xsl:template>


<xsl:template match="ListaDomandaQuiz_RispQuiz" mode="tmlRel">
<xsl:param name="pos"/>
		<xsl:if test="count(*[@progr or @result]) &gt; 0">
		<div class="divLista">
		<!-- xsl:call-template name="PaginatoreRelazione"><xsl:with-param name="caricaFunction">caricaRelazione(this)</xsl:with-param></xsl:call-template-->
		<table border="0" cellpadding="2" cellspacing="0" class="tabLista" id="tabListaDomandaQuiz_RispQuiz_{$pos}" nomepdc="Quiz">
			<xsl:call-template name="IntestaTabellaRispQuiz"/>
			<xsl:apply-templates select = "*[@progr or @result]" mode="lista" />
		</table>
		</div>
		</xsl:if>
</xsl:template>

<xsl:template name="IntestaTabellaRispQuiz">
<tr>
<th ></th>

     <th data-nomecol="Progr" >Progr</th>

     <th data-nomecol="Asserzione" >Testo</th>
     
     <th data-nomecol="Risposta" >Risposta</th>

     <th data-nomecol="Esito" >Esito</th>
     
</tr>
</xsl:template>


<!-- ============================================ -->
<!--    Righe tabella                             -->
<!-- ============================================ -->
<xsl:template match="RispQuiz" mode="lista" >

   <tr id='RigaRispQuiz_{position()}' chiave='{IdRispTest}'>
    <xsl:choose>
      <xsl:when test="(position() mod 2) = 1"><xsl:attribute name="class">rigaDispari</xsl:attribute></xsl:when>
      <xsl:otherwise><xsl:attribute name="class">rigaPari</xsl:attribute></xsl:otherwise>
    </xsl:choose>
		<td style="text-align:left">
			<xsl:call-template name="ButtonHref">
				<xsl:with-param name="href">/preno/patente/HRispQuiz?MTipo=R&amp;MPasso=2&amp;IdRispTest=<xsl:value-of select="IdRispTest"/></xsl:with-param>
				<xsl:with-param name="title">Modifica una Risposta</xsl:with-param>
				<xsl:with-param name="fa-class">fa-edit</xsl:with-param>
				<xsl:with-param name="onunload">caricaRelazione(this.atag)</xsl:with-param><!--$("#divRel_Quiz_DomandaQuiz_<xsl:value-of select='IdQuiz'/> > div > a.refresh_btn").click()-->
				<xsl:with-param name="caption">Gestione Risposta</xsl:with-param>
			</xsl:call-template>
		</td>

		<td><xsl:value-of select="Domanda/IdProgr"/></td>

		<td><xsl:value-of select="Domanda/Asserzione"/></td>

		<td>
			Vero<br/>
			<input type="checkbox" name="chTrue{IdRispTest}" id="chTrue{IdRispTest}" onclick="rispondi({IdRispTest}, true, this)">
			<xsl:if test="RespVero = 'true'"><xsl:attribute name="checked">true</xsl:attribute></xsl:if>
			</input><br/>
			Falso<br/>
			<input type="checkbox" name="chFalse{IdRispTest}" id="chFalse{IdRispTest}" onclick="rispondi({IdRispTest}, false, this)">
			<xsl:if test="RespFalso = 'true'"><xsl:attribute name="checked">true</xsl:attribute></xsl:if>
			</input>			
		</td>

		<td><xsl:if test="EsitoRisp = 'true'">Corretta</xsl:if>
		<xsl:if test="EsitoRisp = 'false' and (RespVero = 'true' or RespFalso = 'true')">Sbagliata</xsl:if>
		</td>

		</tr >


</xsl:template>


<xsl:template name="IntestaTabellaTest">
<tr>
<th style="min-width:180px"></th>

     <th data-nomecol="IdTest" >IdTest</th>

     <th data-nomecol="EsitoTest" >EsitoTest</th>

     <th data-nomecol="DtInizioTest" >DtInizioTest</th>

     <th data-nomecol="DtFineTest" >DtFineTest</th>

     <th data-nomecol="IdQuiz" >IdQuiz</th>

</tr>
</xsl:template>


<!-- ============================================ -->
<!--    Righe tabella                             -->
<!-- ============================================ -->
<xsl:template match="Test" mode="lista" >

   <tr id='RigaTest_{position()}' chiave='{IdTest}'>
    <xsl:choose>
      <xsl:when test="(position() mod 2) = 1"><xsl:attribute name="class">rigaDispari</xsl:attribute></xsl:when>
      <xsl:otherwise><xsl:attribute name="class">rigaPari</xsl:attribute></xsl:otherwise>
    </xsl:choose>
		<td style="text-align:left">
			<xsl:call-template name="BottoniRiga">
				<xsl:with-param name="nomepdc">Test</xsl:with-param>
				<xsl:with-param name="pos"><xsl:value-of select="position()"/></xsl:with-param>
				<xsl:with-param name="ramochiuso">true</xsl:with-param>
			</xsl:call-template>
			<xsl:call-template name="ButtonHref">
				<xsl:with-param name="href">/preno/patente/HTest?MTipo=V&amp;MPasso=2&amp;IdTest=<xsl:value-of select="IdTest"/></xsl:with-param>
				<xsl:with-param name="title">Modifica un Test</xsl:with-param>
				<xsl:with-param name="fa-class">fa-edit</xsl:with-param>
				<xsl:with-param name="onunload">caricaRelazione(this.atag)</xsl:with-param><!--$("#divRel_Quiz_Test_<xsl:value-of select='IdQuiz'/> > div > a.refresh_btn").click()-->
				<xsl:with-param name="caption">Gestione Test</xsl:with-param>
			</xsl:call-template>
		</td>

		<td><xsl:value-of select="IdTest"/></td>

		<td><xsl:value-of select="EsitoTest"/></td>

		<td><xsl:value-of select="DtInizioTest"/></td>

		<td><xsl:value-of select="DtFineTest"/></td>

		<td><xsl:value-of select="IdQuiz"/><xsl:value-of select="Quiz/nome"></xsl:value-of></td>

		</tr >

	<!--xsl:call-template name="RelazioniTest"/-->
</xsl:template>


<xsl:template name="BottoniRiga">
	<xsl:param name="nomepdc"/>
	<xsl:param name="pos"/>
	<xsl:param name="ramochiuso">false</xsl:param>
	<xsl:param name="btn_tree">true</xsl:param>
	<xsl:param name="btn_menu">false</xsl:param>
	<xsl:if test="$btn_tree = 'true'">
		<a pos="{$pos}" href="javascript:void(0)" style="margin-left:1px; margin-right:5px" onclick="AppGlob.apriRigaRelazioni(this, 'sm.ciscoop.preno.pdc.patente.{$nomepdc}',true)" class="btn_espandi">
			<i class="fa fa-plus-square-o">
			<xsl:if test="$ramochiuso = 'false' and count(*[@tipo='LISTA']/*[@progr or @result]) > 0"><xsl:attribute name="class">fa fa-minus-square-o</xsl:attribute></xsl:if>
			</i>
		</a>
	</xsl:if>
	<xsl:if test="$btn_menu = 'true'">
		<a pos="{$pos}" href="javascript:void(0)" onclick="AppGlob.apriMenu(this,'{$nomepdc}')" class="cis-button">
		<i class="fa fa-bars"></i>
		</a>
	</xsl:if>
</xsl:template>

<xsl:template name="MenuContestuale">
	<div class="MenuContestuale closed" id="divMenu">
	<ul style="padding-left:4px">
	<li style="margin-left:12px">Visualizza la relazione</li>
	</ul>
	</div>
</xsl:template>

<!-- ============================================ -->
<!--    Attributi filtro                          -->
<!-- ============================================ -->
<xsl:template name="LeftFilter">

	<xsl:apply-templates select="/DOCUMENTO/Quiz/IdQuiz" mode="CampoIntero">
		<xsl:with-param name="size">10</xsl:with-param>
		<xsl:with-param name="chiave">true</xsl:with-param><xsl:with-param name="serial">true</xsl:with-param>
		<xsl:with-param name="caption">IdQuiz</xsl:with-param>
	</xsl:apply-templates>

</xsl:template>

<xsl:template name="mieIframe" >
	<div id="dialog" title="Basic dialog">
	  <div id="divAnnoLiq" style="margin-bottom:5px"><label for="AnnoLiq" style="display:inline-block;min-width:85px">Anno Liq.</label><input class="testo" type="text" id="AnnoLiq" name="AnnoLiq" size="5"/></div>
	  <div id="divNumLiq" style="margin-bottom:5px"><label for="NumLiq" style="display:inline-block;min-width:85px">Numero Liq.</label><input class="testo" type="text" id="NumLiq" name="NumLiq" size="5"/></div>
	  <div id="divDtValuta" style="margin-bottom:5px"><label for="DtValuta" style="display:inline-block;min-width:85px">Data Valuta</label><input class="testo" type="text" id="DtValuta" name="DtValuta" size="12"/></div>
	</div>

	<!--xsl:call-template name="crea_iframe" >
	   <xsl:with-param name="id" >fmQuiz</xsl:with-param>
	   <xsl:with-param name="name" >fmQuiz</xsl:with-param>
	   <xsl:with-param name="titolo">Quiz</xsl:with-param>
	   <xsl:with-param name="height">350px</xsl:with-param>
	   <xsl:with-param name="width">400px</xsl:with-param>
	   <xsl:with-param name="top">50px</xsl:with-param>
	   <xsl:with-param name="left">auto</xsl:with-param>
	</xsl:call-template-->
</xsl:template>



</xsl:stylesheet>
