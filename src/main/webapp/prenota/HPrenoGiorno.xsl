
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"  xmlns:xs="http://www.w3.org/2001/XMLSchema" version="1.0">

<xsl:import href="${AppThemes}/atlanta-1.1.0-SNAPSHOT/xsl/theme.xsl"/>
<xsl:import href="${AppThemes}/atlanta-1.1.0-SNAPSHOT/xsl/forms2.xsl"/>
<xsl:import href="${AppXSL}/app.xsl"/>


<xsl:output method="html" indent="yes"/>

<xsl:template match="/">
<xsl:if test="/DOCUMENTO/flSoloCaricaRelaz = 'true'">
	<xsl:apply-templates select="/DOCUMENTO/*[@tipo = 'PDC']/*[@tipo='LISTA' or @tipo='PDC']" mode="tmlRel"/>
</xsl:if>
<xsl:if test="/DOCUMENTO/flSoloCaricaRelaz != 'true' or count(/DOCUMENTO/flSoloCaricaRelaz) = 0">
	<xsl:apply-templates select="/" mode="noricarica"/>
</xsl:if>
</xsl:template>


<xsl:template match="/" mode="noricarica">
	<xsl:call-template name="baseForm" >
		<xsl:with-param name="AggiungiAncora">true</xsl:with-param>	
		<xsl:with-param name="tooltips">true</xsl:with-param>	
		<xsl:with-param name="contabs">true</xsl:with-param>	
		<!--xsl:with-param name="method">post</xsl:with-param-->
		<!--xsl:with-param name="enctype">multipart/form-data</xsl:with-param-->
	</xsl:call-template>
</xsl:template>


<xsl:template name="CssSpec" >
	<style>
	.cis-field-container .cis-label {
		width:150px;
	}	
	/*.cis-field-container .cis-value {
		width:350px;
	}*/
	
	</style>
</xsl:template>

<!-- ============================================ -->
<!--     Titolo            						  -->
<!-- ============================================ -->
<xsl:template name="Titolo" >
	<xsl:value-of select="$TitoloPagina"/>
</xsl:template>
<xsl:variable name="TitoloPagina">PrenoGiorno</xsl:variable>

<xsl:template  name="def_javascript">
function getTitolo(){
  return '  '  + '<xsl:value-of select="$tipo_transaz"/>' + ' <xsl:value-of select="$TitoloPagina"/>' + '   ';
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
	if ( nome == 'PrenoGiorno') {
		var IdPrenotazione = chiavi[0];
	} else if (nome == 'PrenoRagazzo') {
		var IdPrenotazione = chiavi[0];
		var IdSoggRagazzo = chiavi[1];

	}
}

// Apre una riga sotto quella corrente, per visualizzare le relazioni aperte con altri pdc
function apriRigaRelazioni(chiave, nomepdc, riga, rigarel) {
	// La riga corrispondente all'elemento selezionato e' la seguente
	var spezzanome = nomepdc.split('.');
	var nome = spezzanome[spezzanome.length - 1];
	var tr = riga; //$('#Riga'+nome+'_'+id);
	var chiavi = chiave.split('_');
	if ( nome == 'PrenoGiorno') {
				var IdPrenotazione = chiavi[0];

		// Scommentare per fare il caricamento manuale ogni volta che si clicca sul + di questa relazione,
		// oppure passare true come terzo parametro alla AppGlob.apriRigarelazione
		//riga.next().find('.refresh_btn.cis-button').click()

	} else if (nome == 'PrenoRagazzo') {
		var IdPrenotazione = chiavi[0];
		var IdSoggRagazzo = chiavi[1];

		// Scommentare per fare il caricamento manuale ogni volta che si clicca sul + di questa relazione,
		// oppure passare true come terzo parametro alla AppGlob.apriRigarelazione
		//riga.next().find('.refresh_btn.cis-button').click()
	
	}
}

function richiestaComando(nomecomando, chiave, dati, href, callback) {
	// Qui posso variare il contenuto dell'area dati, aggiungendo attributi con nome e valore
	// che verranno riportati alla servlet come parametri.
	var valore = prompt('inserisci il comando per ',chiave);
	//TODO
	return true;
}

// Funzione che gestisce il ritorno del comando
function comandoTerminato(nomecomando, chiave, data) {
	var errore = $('DOCUMENTO > ERRORE > USER', data).text();
	//TODO
	AppGlob.emettiErrore(errore);
}

var ajaxrequest = null;
function caricaRelazione(obj) {
	// Devo andare a ritroso nel dom fino a trovare un div con class="divRelazione"
	var conf = {
		domObj : obj,
		relazioni : {
			PrenoGiorno_PrenoRagazzo :['IdPrenotazione'],
		},
		href : "/preno/prenota/HPrenoGiorno",
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


</xsl:template>



<xsl:template match="DOCUMENTO" >
    <xsl:call-template  name="Comandi" />
    
    <xsl:apply-templates select = "PrenoGiorno"/>
    
</xsl:template>

 
<xsl:template match="PrenoGiorno">


	<!--xsl:apply-templates select="IdSoggetto" mode="CampoEnumerato">
		<xsl:with-param name="enumerato" select="/DOCUMENTO/LstSoggetto/RECORD"></xsl:with-param>
		
		<xsl:with-param name="caption">IdSoggetto</xsl:with-param>
		<xsl:with-param name="id">IdSoggetto</xsl:with-param>
	</xsl:apply-templates-->

	<!--xsl:apply-templates select="IdSoggetto" mode="jqcombo">
		<xsl:with-param name="enumerato" select="/DOCUMENTO/LstSoggetto" />
		
		<xsl:with-param name="caption">IdVisita</xsl:with-param-->
		<!--xsl:with-param name="onchange">funzioneDaChiamare</xsl:with-param-->
		<!--xsl:with-param name="showExtra">true</xsl:with-param-->
		<!--xsl:with-param name="campi_lista">['nascita_data','idsoggetto']</xsl:with-param-->
		<!--xsl:with-param name="campi_collegati">['nascita_data','DtNascitaFamCar','idsoggetto','IdSoggettoAus']</xsl:with-param-->
		<!--xsl:with-param name="campi_filtro">null</xsl:with-param> modalita' dinamica ajax: campi da aggiungere all'url, ad esempio {Cognome: '_text_', IdAzienda: 'IdAzienda'} aggiunge "&Cognome=testocombo&IdAzienda=contenuto del campo con id=IdAzienda"-->
		<!--xsl:with-param name="url">/inspect/isp/HAllegati</xsl:with-param-->
		<!--xsl:with-param name="multiselect">true</xsl:with-param-->
	<!--/xsl:apply-templates-->

	<xsl:apply-templates select="IdPrenotazione" mode="CampoIntero">
		<xsl:with-param name="size">10</xsl:with-param>
		<xsl:with-param name="chiave">true</xsl:with-param><xsl:with-param name="serial">true</xsl:with-param>
		<xsl:with-param name="caption">IdPrenotazione</xsl:with-param>
	</xsl:apply-templates>

	<xsl:apply-templates select="DtGiorno" mode="CampoData">
		<xsl:with-param name="size">12</xsl:with-param>
		
		<xsl:with-param name="caption">Data</xsl:with-param>
	</xsl:apply-templates>

	<xsl:apply-templates select="IdSoggettoAndata" mode="CampoEnumerato">
		<xsl:with-param name="enumerato" select="/DOCUMENTO/LstSoggetto/RECORD"></xsl:with-param>
		
		<xsl:with-param name="caption">Soggetto dell'andata</xsl:with-param>
		<xsl:with-param name="id">IdSoggettoAndata</xsl:with-param>
	</xsl:apply-templates>
	
	<xsl:apply-templates select="IdSoggettoRitorno" mode="CampoEnumerato">
		<xsl:with-param name="enumerato" select="/DOCUMENTO/LstSoggetto/RECORD"></xsl:with-param>
		
		<xsl:with-param name="caption">Soggetto del ritorno</xsl:with-param>
		<xsl:with-param name="id">IdSoggettoRitorno</xsl:with-param>
	</xsl:apply-templates>

	<xsl:apply-templates select="NumMax" mode="CampoIntero">
		<xsl:with-param name="size">10</xsl:with-param>
		
		<xsl:with-param name="caption">NumMax</xsl:with-param>
	</xsl:apply-templates>

</xsl:template>

<!-- RELAZIONI IN TAB /-->

<xsl:template name="CreaTabs">
	<xsl:param name="method"/>
	<xsl:param name="action"/>
	<xsl:param name="onsubmit"/>
	<xsl:param name="width"/>
	<xsl:param name="enctype"/>
	<xsl:param name="AggiungiAncora"/>
    <xsl:param name="ParamsAggiungiAncora"></xsl:param>
    <xsl:param name="noAnnulla"></xsl:param>
    <xsl:param name="noModifica"></xsl:param>

<div class="cis-tab-gen" id="cis-tabgen">
<div class="cis-tab-linguette">
        <a class="active cis-tab-a" href="javascript:void(0)" data-name="DatiGenerali">Dati generali</a>
	
        <a class="cis-tab-a" href="javascript:void(0)" data-name="PrenoRagazzo">PrenoRagazzo</a>
       
</div>

<div class="cis-container-tabs">
        <div id="TabDatiGenerali" class="cis-tab-blocco active">
        	<xsl:call-template name="Creaform">
			<xsl:with-param name="method" select="$method"/>
			<xsl:with-param name="action" select="$action"/>
			<xsl:with-param name="onsubmit" select="$onsubmit"/>
			<xsl:with-param name="enctype" select="$enctype"/>
			<xsl:with-param name="width" select="$width"/>
			<xsl:with-param name="AggiungiAncora" select="$AggiungiAncora"/>
		</xsl:call-template>        	        	
        </div>
		
		<xsl:for-each select="/DOCUMENTO/PrenoGiorno">
		
	
        <div id="TabPrenoRagazzo" class="cis-tab-blocco noactive">
        	
    <div style="margin-left:20px;" id="divRel_PrenoGiorno_PrenoRagazzo_{position()}" class="divRelazione" chiave="{IdPrenotazione}" nomepdc="sm.ciscoop.preno.pdc.prenota.PrenoGiorno" nomerelaz="PrenoGiorno_PrenoRagazzo">
		<div class="titolorelaz"><a class="refresh_btn btn_riga cis-button" href="javascript:void(0)" onclick="caricaRelazione(this)">
		<i class="fa fa-refresh"/>
		</a>
		<xsl:call-template name = "ButtonHref" >
			<xsl:with-param name="href">/preno/prenota/HPrenoRagazzo?MTipo=I&amp;MPasso=1&amp;IdPrenotazione=<xsl:value-of select="IdPrenotazione"/></xsl:with-param>
			<xsl:with-param name="title">Aggiungi un PrenoRagazzo</xsl:with-param>
			<xsl:with-param name="width">500</xsl:with-param>
			<xsl:with-param name="height">400</xsl:with-param>
			<xsl:with-param name="fa-class">fa-plus-circle</xsl:with-param>			
			<xsl:with-param name="class">btn_riga</xsl:with-param>			
			<xsl:with-param name="onunload">caricaRelazione(this.atag)</xsl:with-param>
			<xsl:with-param name="caption">Gestione PrenoRagazzo</xsl:with-param>										
			<xsl:with-param name="style">float:left</xsl:with-param>										
		</xsl:call-template>
		<span class="titolo1">Relazione PrenoRagazzo</span>
		<div class="btn_minimax" title="Minimizza"><i class="fa fa-window-minimize"></i></div>		
		</div>
		<xsl:apply-templates select="/DOCUMENTO/PrenoGiorno/ListaPrenoGiorno_PrenoRagazzo" mode="tmlRel"/>
		</div>		
			
			
        </div>

	
		</xsl:for-each>
		
</div>

</div>

</xsl:template>


		
<xsl:template match="ListaPrenoGiorno_PrenoRagazzo" mode="tmlRel">		
		<!--xsl:if test="count(*[@progr or @result]) &gt; 0"-->
		<div class="divLista" data-count="{count(*[@progr or @result])}">		
		<xsl:call-template name="PaginatoreRelazione"><xsl:with-param name="caricaFunction">caricaRelazione(this)</xsl:with-param></xsl:call-template>
		<table border="0" cellpadding="2"  style="width:100%" cellspacing="0" class="tabLista">
			<xsl:call-template name="IntestaTabellaPrenoRagazzo"/>
			<xsl:apply-templates select = "*[@progr or @result]" mode="lista" />    		
		</table>
		</div>
		<!--/xsl:if-->
</xsl:template>

<xsl:template name="IntestaTabellaPrenoRagazzo">
<tr>
<th></th>


     <th >Ragazzo</th>

</tr>
</xsl:template>


<!-- ============================================ -->
<!--    Righe tabella                             -->
<!-- ============================================ -->
<xsl:template match="PrenoRagazzo" mode="lista" >

   <tr id='RigaPrenoRagazzo_{position()}' chiave='{IdPrenotazione}_{IdSoggRagazzo}'>
    <xsl:choose>
      <xsl:when test="(position() mod 2) = 1"><xsl:attribute name="class">rigaDispari</xsl:attribute></xsl:when>
      <xsl:otherwise><xsl:attribute name="class">rigaPari</xsl:attribute></xsl:otherwise>
    </xsl:choose>
		<td>	
			<xsl:call-template name = "ButtonHref" >
				<xsl:with-param name="href">/preno/prenota/HPrenoRagazzo?MTipo=C&amp;MPasso=2&amp;IdPrenotazione=<xsl:value-of select="IdPrenotazione"/>&amp;IdSoggRagazzo=<xsl:value-of select="IdSoggRagazzo"/></xsl:with-param>
				<xsl:with-param name="title">Cancella il ragazzo</xsl:with-param>
				<xsl:with-param name="width">500</xsl:with-param>
				<xsl:with-param name="height">400</xsl:with-param>
				<xsl:with-param name="fa-class">fa-edit</xsl:with-param>			
				<xsl:with-param name="class">btn_riga</xsl:with-param>			
				<xsl:with-param name="onunload">caricaRelazione(this.atag)</xsl:with-param><!--$("#divRel_PrenoGiorno_PrenoRagazzo_<xsl:value-of select='IdPrenotazione'/> > div > a.refresh_btn").click()-->
				<xsl:with-param name="caption">Gestione Ragazzo</xsl:with-param>														
			</xsl:call-template>

		</td>

		<td><xsl:value-of select="Ragazzo/NomeRagazzo"></xsl:value-of></td>

		</tr >

	<!--xsl:call-template name="RelazioniPrenoRagazzo"/-->		
</xsl:template>




<xsl:template name="BottoniRiga">
<xsl:param name="nomepdc"/>
<xsl:param name="pos"/>
<xsl:param name="ramochiuso">false</xsl:param>
<xsl:param name="btn_tree">true</xsl:param>
<xsl:param name="btn_menu">false</xsl:param>
	<xsl:if test="$btn_tree = 'true'">
	<a pos="{$pos}" href="javascript:void(0)" style="margin-left:1px; margin-right:5px" onclick="AppGlob.apriRigaRelazioni(this, 'preno.pdc.prenota.{$nomepdc}')" >
		<i class="fa fa-plus-square-o">
		<xsl:if test="$ramochiuso = 'false' and count(*[@tipo='LISTA']/*[@progr or @result]) > 0"><xsl:attribute name="class">fa fa-minus-square-o</xsl:attribute></xsl:if>
		</i>		
	</a>
	</xsl:if>
	<xsl:if test="$btn_menu = 'true'">
	<a pos="{$pos}" href="javascript:void(0)" onclick="AppGlob.apriMenu(this,'{$nomepdc}')" class="btn_riga">
	<i class="fa fa-bars"></i>		
	</a>
	</xsl:if>
</xsl:template>

</xsl:stylesheet>
