
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" version="1.0">
<xsl:import href="${AppThemes}/atlanta-1.1.0-SNAPSHOT/xsl/theme.xsl"/>
<xsl:import href="${AppThemes}/atlanta-1.1.0-SNAPSHOT/xsl/liste2.xsl"/>
<xsl:import href="${AppXSL}/app.xsl"/>

<xsl:output method="html" indent="yes"/>

<xsl:decimal-format name="euro" decimal-separator="," grouping-separator="."/>

<xsl:template match="/">
	<xsl:if test="/DOCUMENTO/flSoloCaricaRelaz='true'">
		<xsl:apply-templates select="/DOCUMENTO/*[@tipo = 'PDC']/*[@tipo='LISTA' or @tipo='PDC']" mode="tmlRel"/>
	</xsl:if>

	<xsl:if test="/DOCUMENTO/flSoloCaricaRelaz != 'true' or count(/DOCUMENTO/flSoloCaricaRelaz) = 0">
		<xsl:call-template name="baseListe"/>
	</xsl:if>
</xsl:template>


<xsl:template name="CssSpec">
	<style>
	.cis-field-container label {
		width:150px;
	}
	/*.cis-field-container .ValoreAttributo {
		width:350px;
	}*/
	.screen-s .cis-field-container, .screen-m .cis-field-container {
		display:inline-block;
	}		
	</style>
</xsl:template>

<xsl:variable name="TitoloPagina">Soggetto</xsl:variable>

<xsl:template name="def_javascript">

// Scommentare per richiamare una funzione tooltip specifica
//function tooltipsSpec() {
//    $(":input").tooltip();
//}

function getTitolo(){
	return '  '  + '<xsl:value-of select="$tipo_transaz"/>' + ' <xsl:value-of select="$TitoloPagina"/>' + '   ';
}

function getAutoHref(){
	var href="/preno/soggetti/HLstSoggetto?MTipo=L&amp;MPasso=1";
	return href;
}

$(function() {
	// Registra le funzioni per il menu contestuale e le relazioni
	AppGlob.registraFunzioniPerListe(apriMenu, apriRigaRelazioni);
	//$('input ::first').focus();
	AppGlob.inizializzaLista();
});

// Apre una riga sotto quella corrente, per visualizzare le relazioni aperte con altri pdc
function apriRigaRelazioni(chiave, nomepdc, $riga, $rigarel) {
	// La riga corrispondente all'elemento selezionato e' la seguente
	var spezzanome = nomepdc.split('.');
	var nome = spezzanome[spezzanome.length - 1];
	var $tr = $riga; //$('#Riga'+nome+'_'+id);
	var chiavi = chiave.split('_');
	if ( nome == 'Soggetto') {
		var IdSoggetto = chiavi[0];

	// Scommentare per fare il caricamento manuale ogni volta che si clicca sul + di questa relazione
	//$riga.next().find('.refresh_btn.cis-button').click()	
	}
}
// Apre il menu contestuale in corrispondenza della riga
function apriMenu(chiave, nomepdc, $riga) {
	// La riga corrispondente all'elemento selezionato e' la seguente
	var spezzanome = nomepdc.split('.');
	var nome = spezzanome[spezzanome.length - 1];
	var $tr = $riga; //$('#Riga'+nome+'_'+id);
	var chiavi = chiave.split('_');
	if ( nome == 'Soggetto') {
		var IdSoggetto = chiavi[0];

	}
}


var ajaxrequest = null;
// Apre il menu contestuale in corrispondenza della riga
function caricaRelazione(obj) { //atag,rigapos, nomepdc, nomerelazione) {
	// La riga corrispondente all'elemento selezionato e' la seguente
	/*$('#dialog').dialog({
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
        	},
        	Annulla: function() {
          		$( this ).dialog( "close" );
        	}
      	}
      	}
	*/

	// Devo andare a ritroso nel dom fino a trovare un div con class="divRelazione"
	var $odivRelaz = AppGlob.trovaDivRelazione(obj);
	if ( $odivRelaz == null)
		return;

	var nomepdc = $odivRelaz.attr("nomepdc");
	var nomerelazione = $odivRelaz.attr("nomerelaz");
	var chiave = $odivRelaz.attr("chiave");

	var dati = {};
	var spezzanome = nomepdc.split('.');
	var nome = spezzanome[spezzanome.length - 1];
	var chiavi = chiave.split('_');
	
	var rpp = $odivRelaz.find('.rowsPerPage');
	if(rpp) {
		var page = $odivRelaz.find('.currPage');
		var nrpp = 0;
		var npage = 0;
		nrpp = rpp.val();
		if(page) {
		   npage = page.val();
		}
		if($(obj).hasClass("nextPage")) {
		   npage++;
		}
		else if($(obj).hasClass("prevPage")) {
		   npage--;
		}
		
		dati['rpp'] = nrpp;
		dati['page'] = npage;
	}

	if ( nome == 'Soggetto') {
			dati['IdSoggetto'] = chiavi[0];
	
	}

	dati['MTipo']='L';dati['MPasso']=2;
	AppGlob.reloadRelazione("/preno/soggetti/HLstSoggetto",nomepdc,nomerelazione,dati,$odivRelaz, function(dati, data) { });

}

function richiestaComando(nomecomando, chiave, dati, href, callback) {
	// Qui posso variare il contenuto dell'area dati, aggiungendo attributi con nome e valore
	// che verranno riportati alla servlet come parametri.
	var valore = prompt('inserisci il comando per ',chiave);
	dati.Cognome='xxxxx';
	return true;
}

// Funzione che gestisce il ritorno del comando
function comandoTerminato(nomecomando, chiave, data) {
	var errore = $('DOCUMENTO > ERRORE > USER', data).text();
	AppGlob.emettiErrore(errore);
}
</xsl:template>


<xsl:template name="Titolo" >
	<xsl:value-of select="$TitoloPagina"/>
</xsl:template>


<!-- ============================================ -->
<!--     Filtro                          -->
<!-- ============================================ -->
<xsl:template name="filtro">
	<form method="GET" id="Parametri" name="Parametri">
		<xsl:call-template  name="Comandi"/>
		<fieldset class="filtri">
		<legend>Imposta il filtro</legend>
			<div class="filtrosinistra">
				<!--xsl:apply-templates select="/DOCUMENTO/TipoPratica" mode="combo" >
					<xsl:with-param name="enumerato" select="/DOCUMENTO/LstTpPratica"/>
					<xsl:with-param name="codice"  select="/DOCUMENTO/TipoPratica"/>
				</xsl:apply-templates-->

				<xsl:apply-templates select="/DOCUMENTO/Soggetto/IdSoggetto" mode="nodoattributo" />

			</div>
			<div class="filtrodestra">
				<xsl:call-template name="Ricerca" >
					<!--xsl:with-param name="width">80</xsl:with-param-->
				</xsl:call-template>
			</div>
		</fieldset>
	</form>
</xsl:template>


<!-- ============================================ -->
<!--    Intestazione                             -->
<!-- ============================================ -->
<xsl:template name="IntestazioneLista">
		<div style="display:inline-block; width:300px;" border="0">
		<a href="javascript:void(0)" onclick="document.location.reload()" class="refresh_btn cis-button"><i class="fa fa-refresh"/></a>
			<xsl:call-template name="ButtonHref">
				<xsl:with-param name="href">/preno/soggetti/HSoggetto?MTipo=I&amp;MPasso=1</xsl:with-param>
				<xsl:with-param name="title">Aggiungi un Soggetto</xsl:with-param>
				<xsl:with-param name="fa-class">fa-plus-circle</xsl:with-param>
				<xsl:with-param name="onunload">document.location.reload()</xsl:with-param>
				<xsl:with-param name="caption">Gestione Soggetto</xsl:with-param>
				<!--xsl:with-param name="text">Soggetto</xsl:with-param-->
			</xsl:call-template>
		</div>
</xsl:template>

<!-- ============================================ -->
<!--    Intestazione tabella                      -->
<!-- ============================================ -->
<xsl:template name="IntestaTabella">
	<tr >
		<th style="min-width:180px"> </th>

		<th data-nomecol='IdSoggetto'>IdSoggetto</th>

		<th data-nomecol='NomeSoggetto'>NomeSoggetto</th>

		<th data-nomecol='EmailSogg'>EmailSogg</th>


	</tr>
</xsl:template>


<!-- ============================================ -->
<!--    Righe tabella                             -->
<!-- ============================================ -->
<xsl:template match="Soggetto" mode="lista" >

<xsl:variable name="rigapos"><xsl:value-of select="position()"/></xsl:variable>
   <tr id='RigaSoggetto_{position()}' chiave="{IdSoggetto}">
    <xsl:choose>
      <xsl:when test="(position() mod 2) = 1"><xsl:attribute name="class">rigaDispari</xsl:attribute></xsl:when>
      <xsl:otherwise><xsl:attribute name="class">rigaPari</xsl:attribute></xsl:otherwise>
    </xsl:choose>
		<td style="text-align:left">
			<xsl:call-template name="BottoniRiga">
				<xsl:with-param name="nomepdc">Soggetto</xsl:with-param>
				<xsl:with-param name="pos"><xsl:value-of select="position()"/></xsl:with-param>
				<xsl:with-param name="ramochiuso">true</xsl:with-param>
			</xsl:call-template>
			<xsl:call-template name = "ButtonHref" >
				<xsl:with-param name="href">/preno/soggetti/HSoggetto?MTipo=V&amp;MPasso=2&amp;IdSoggetto=<xsl:value-of select="IdSoggetto"/></xsl:with-param>
				<xsl:with-param name="title">Modifica un Soggetto</xsl:with-param>
				<xsl:with-param name="fa-class">fa-edit</xsl:with-param>
				<xsl:with-param name="onunload">document.location.reload()</xsl:with-param>
				<xsl:with-param name="caption">Gestione Soggetto</xsl:with-param>
				<!--xsl:with-param name="text">Gestione Soggetto</xsl:with-param-->
			</xsl:call-template>

		</td>

		<td><xsl:value-of select="IdSoggetto"/></td>

		<td><xsl:value-of select="NomeSoggetto"/></td>

		<td><xsl:value-of select="EmailSogg"/></td>

		<!-- Scommentare queste righe per avere comandi da eseguire su ogni riga -->
		<!-- I comandi devono avere il permesso (su zTrans) del tipo  HLstElabCeliaci:ElaboraMeseSmac-->
		<!--td align="right" class="bottoni_comando">
 		    <xsl:call-template name="linkComando">
		    	<xsl:with-param name="href">/preno/soggetti/HLstSoggetto</xsl:with-param>
		    	<xsl:with-param name="nomecomando">ElaboraMeseSmac</xsl:with-param>
		    	<xsl:with-param name="chiave"><xsl:value-of select='IdSoggetto'/></xsl:with-param>
		    	<xsl:with-param name="ParamServlet">{MTipo:'L',MPasso:'2'}</xsl:with-param>
					<xsl:with-param name="title">Testo per comando</xsl:with-param>
					<xsl:with-param name="fa-class">fa-calculator</xsl:with-param>
		    </xsl:call-template>
			<a href="javascript:void(0)"  style="margin-left:1px; margin-right:5px;font-size:1.5em" onclick="AppGlob.eseguiComando('/preno/soggetti/HLstSoggetto', 'CreaPiattaforma', '{IdSoggetto}', {{MTipo:'L',MPasso:'2'}}, richiestaComando, comandoTerminato)" title="Elabora il comando">
				<i class="fa fa-calculator"></i>
			</a>
			<a href="javascript:void(0)"  style="margin-left:1px; margin-right:5px;font-size:1.5em" onclick="elaboraPiattaforma()" title="Stampa il report">
				<i class="fa fa-print"></i>
			</a>
		</td-->
		</tr >
	<xsl:call-template name="RelazioniSoggetto"/>
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
<xsl:template name="RelazioniSoggetto">
	<xsl:param name="rigapos"><xsl:value-of select="position()"/></xsl:param>
	<tr id="RigaRelSoggetto_{$rigapos}">
		<xsl:choose>
		<xsl:when test="(position() mod 2) = 1"><xsl:attribute name="class">rigaDispari</xsl:attribute></xsl:when>
			<xsl:otherwise><xsl:attribute name="class">rigaPari</xsl:attribute></xsl:otherwise>
		</xsl:choose>
		<td colspan="100" class="closed tdRelazione" >
			<!--xsl:choose>
				<xsl:when test="'1' = '2'"></xsl:when>
			<xsl:when test="count(ListaSoggetto_PrenoGiorno/*[@progr or @result]) &gt; 0"><xsl:attribute name="class">open</xsl:attribute></xsl:when>
			<xsl:when test="count(ListaSoggetto_PrenoGiorno/*[@progr or @result]) &gt; 0"><xsl:attribute name="class">open</xsl:attribute></xsl:when>
		</xsl:choose-->


    <div style="margin-left:20px;" id="divRel_Soggetto_PrenoGiorno_{position()}" class="divRelazione" chiave="{IdSoggetto}" nomepdc="sm.ciscoop.preno.pdc.soggetti.Soggetto" nomerelaz="Soggetto_PrenoGiorno">
		<div class="titolorelaz"><a class="refresh_btn cis-button" href="javascript:void(0)" onclick="caricaRelazione(this)">
		<i class="fa fa-refresh"/>
		</a>
		<xsl:call-template name = "ButtonHref" >
			<xsl:with-param name="href">/preno/prenota/HPrenoGiorno?MTipo=I&amp;MPasso=1&amp;IdSoggetto=<xsl:value-of select="IdSoggetto"/></xsl:with-param>
			<xsl:with-param name="title">Aggiungi un PrenoGiorno</xsl:with-param>
			<xsl:with-param name="fa-class">fa-plus-circle</xsl:with-param>
			<xsl:with-param name="onunload">caricaRelazione(this.atag)</xsl:with-param>
			<xsl:with-param name="caption">Gestione PrenoGiorno</xsl:with-param>
		</xsl:call-template>
		<span class="titolo1">Relazione PrenoGiorno</span>
		<div class="btn_minimax" title="Minimizza"><i class="fa fa-window-minimize"></i></div>		
		</div>
		<xsl:apply-templates select="ListaSoggetto_PrenoGiorno" mode="tmlRel"><xsl:with-param name="pos"><xsl:value-of select="position()"/></xsl:with-param></xsl:apply-templates>
		</div>
		
    <div style="margin-left:20px;" id="divRel_Soggetto_PrenoGiorno_{position()}" class="divRelazione" chiave="{IdSoggetto}" nomepdc="sm.ciscoop.preno.pdc.soggetti.Soggetto" nomerelaz="Soggetto_PrenoGiorno">
		<div class="titolorelaz"><a class="refresh_btn cis-button" href="javascript:void(0)" onclick="caricaRelazione(this)">
		<i class="fa fa-refresh"/>
		</a>
		<xsl:call-template name = "ButtonHref" >
			<xsl:with-param name="href">/preno/prenota/HPrenoGiorno?MTipo=I&amp;MPasso=1&amp;IdSoggetto=<xsl:value-of select="IdSoggetto"/></xsl:with-param>
			<xsl:with-param name="title">Aggiungi un PrenoGiorno</xsl:with-param>
			<xsl:with-param name="fa-class">fa-plus-circle</xsl:with-param>
			<xsl:with-param name="onunload">caricaRelazione(this.atag)</xsl:with-param>
			<xsl:with-param name="caption">Gestione PrenoGiorno</xsl:with-param>
		</xsl:call-template>
		<span class="titolo1">Relazione PrenoGiorno</span>
		<div class="btn_minimax" title="Minimizza"><i class="fa fa-window-minimize"></i></div>		
		</div>
		<xsl:apply-templates select="ListaSoggetto_PrenoGiorno" mode="tmlRel"><xsl:with-param name="pos"><xsl:value-of select="position()"/></xsl:with-param></xsl:apply-templates>
		</div>
		

    </td>
	</tr>
</xsl:template>


<xsl:template match="ListaSoggetto_PrenoGiorno" mode="tmlRel">
<xsl:param name="pos"/>
		<xsl:if test="count(*[@progr or @result]) &gt; 0">
		<div class="divLista">
		<xsl:call-template name="PaginatoreRelazione"><xsl:with-param name="caricaFunction">caricaRelazione(this)</xsl:with-param></xsl:call-template>
		<table border="0" cellpadding="2" cellspacing="0" class="tabLista" id="tabListaSoggetto_PrenoGiorno_{$pos}" nomepdc="Soggetto">
			<xsl:call-template name="IntestaTabellaPrenoGiorno"/>
			<xsl:apply-templates select = "*[@progr or @result]" mode="lista" />
		</table>
		<!-- Scommentare per fare una transazione T con uesta tabella <script language="javascript">
				$('#tabListaSoggetto_PrenoGiorno_<xsl:value-of select="$pos"/>').cisTTrans();
		</script-->		
		</div>
		</xsl:if>
</xsl:template>

<xsl:template match="ListaSoggetto_PrenoGiorno" mode="tmlRel">
<xsl:param name="pos"/>
		<xsl:if test="count(*[@progr or @result]) &gt; 0">
		<div class="divLista">
		<xsl:call-template name="PaginatoreRelazione"><xsl:with-param name="caricaFunction">caricaRelazione(this)</xsl:with-param></xsl:call-template>
		<table border="0" cellpadding="2" cellspacing="0" class="tabLista" id="tabListaSoggetto_PrenoGiorno_{$pos}" nomepdc="Soggetto">
			<xsl:call-template name="IntestaTabellaPrenoGiorno"/>
			<xsl:apply-templates select = "*[@progr or @result]" mode="lista" />
		</table>
		<!-- Scommentare per fare una transazione T con uesta tabella <script language="javascript">
				$('#tabListaSoggetto_PrenoGiorno_<xsl:value-of select="$pos"/>').cisTTrans();
		</script-->		
		</div>
		</xsl:if>
</xsl:template>

<xsl:template name="IntestaTabellaPrenoGiorno">
	<tr>
		<th style="min-width:180px"></th>

		<th data-nomecol='IdPrenotazione' >IdPrenotazione</th>

		<th data-nomecol='DtGiorno' >DtGiorno</th>

		<th data-nomecol='IdSoggettoAndata' >IdSoggettoAndata</th>

		<th data-nomecol='IdSoggettoRitorno' >IdSoggettoRitorno</th>

		<th data-nomecol='IdSoggetto' >IdSoggetto</th>

	</tr>
</xsl:template>


<!-- ============================================ -->
<!--    Righe tabella                             -->
<!-- ============================================ -->
<xsl:template match="PrenoGiorno" mode="lista" >

	<tr id='RigaPrenoGiorno_{position()}' chiave='{IdPrenotazione}'>
		<xsl:choose>
			<xsl:when test="(position() mod 2) = 1"><xsl:attribute name="class">rigaDispari</xsl:attribute></xsl:when>
			<xsl:otherwise><xsl:attribute name="class">rigaPari</xsl:attribute></xsl:otherwise>
		</xsl:choose>
		<td style="text-align:left">
			<xsl:call-template name="BottoniRiga">
				<xsl:with-param name="nomepdc">PrenoGiorno</xsl:with-param>
				<xsl:with-param name="pos"><xsl:value-of select="position()"/></xsl:with-param>
				<xsl:with-param name="ramochiuso">true</xsl:with-param>
			</xsl:call-template>
			<xsl:call-template name="ButtonHref">
				<xsl:with-param name="href">/preno/prenota/HPrenoGiorno?MTipo=V&amp;MPasso=2&amp;IdPrenotazione=<xsl:value-of select="IdPrenotazione"/></xsl:with-param>
				<xsl:with-param name="title">Modifica un PrenoGiorno</xsl:with-param>
				<xsl:with-param name="fa-class">fa-edit</xsl:with-param>
				<xsl:with-param name="onunload">caricaRelazione(this.atag)</xsl:with-param><!--$("#divRel_Soggetto_PrenoGiorno_<xsl:value-of select='IdSoggetto'/> > div > a.refresh_btn").click()-->
				<xsl:with-param name="caption">Gestione PrenoGiorno</xsl:with-param>
			</xsl:call-template>
		</td>

		<td><xsl:value-of select="IdPrenotazione"/></td>

		<td><xsl:value-of select="DtGiorno"/></td>

		<td><xsl:value-of select="IdSoggettoAndata"/></td>

		<td><xsl:value-of select="IdSoggettoRitorno"/></td>

		<td><xsl:value-of select="IdSoggetto"/><xsl:value-of select="Soggetto/Nome"></xsl:value-of></td>

	</tr>

	<!--xsl:call-template name="RelazioniPrenoGiorno"/-->
</xsl:template>


<xsl:template name="IntestaTabellaPrenoGiorno">
	<tr>
		<th style="min-width:180px"></th>

		<th data-nomecol='IdPrenotazione' >IdPrenotazione</th>

		<th data-nomecol='DtGiorno' >DtGiorno</th>

		<th data-nomecol='IdSoggettoAndata' >IdSoggettoAndata</th>

		<th data-nomecol='IdSoggettoRitorno' >IdSoggettoRitorno</th>

		<th data-nomecol='IdSoggetto' >IdSoggetto</th>

	</tr>
</xsl:template>


<!-- ============================================ -->
<!--    Righe tabella                             -->
<!-- ============================================ -->
<xsl:template match="PrenoGiorno" mode="lista" >

	<tr id='RigaPrenoGiorno_{position()}' chiave='{IdPrenotazione}'>
		<xsl:choose>
			<xsl:when test="(position() mod 2) = 1"><xsl:attribute name="class">rigaDispari</xsl:attribute></xsl:when>
			<xsl:otherwise><xsl:attribute name="class">rigaPari</xsl:attribute></xsl:otherwise>
		</xsl:choose>
		<td style="text-align:left">
			<xsl:call-template name="BottoniRiga">
				<xsl:with-param name="nomepdc">PrenoGiorno</xsl:with-param>
				<xsl:with-param name="pos"><xsl:value-of select="position()"/></xsl:with-param>
				<xsl:with-param name="ramochiuso">true</xsl:with-param>
			</xsl:call-template>
			<xsl:call-template name="ButtonHref">
				<xsl:with-param name="href">/preno/prenota/HPrenoGiorno?MTipo=V&amp;MPasso=2&amp;IdPrenotazione=<xsl:value-of select="IdPrenotazione"/></xsl:with-param>
				<xsl:with-param name="title">Modifica un PrenoGiorno</xsl:with-param>
				<xsl:with-param name="fa-class">fa-edit</xsl:with-param>
				<xsl:with-param name="onunload">caricaRelazione(this.atag)</xsl:with-param><!--$("#divRel_Soggetto_PrenoGiorno_<xsl:value-of select='IdSoggetto'/> > div > a.refresh_btn").click()-->
				<xsl:with-param name="caption">Gestione PrenoGiorno</xsl:with-param>
			</xsl:call-template>
		</td>

		<td><xsl:value-of select="IdPrenotazione"/></td>

		<td><xsl:value-of select="DtGiorno"/></td>

		<td><xsl:value-of select="IdSoggettoAndata"/></td>

		<td><xsl:value-of select="IdSoggettoRitorno"/></td>

		<td><xsl:value-of select="IdSoggetto"/><xsl:value-of select="Soggetto/Nome"></xsl:value-of></td>

	</tr>

	<!--xsl:call-template name="RelazioniPrenoGiorno"/-->
</xsl:template>




<xsl:template name="BottoniRiga">
	<xsl:param name="nomepdc"/>
	<xsl:param name="pos"/>
	<xsl:param name="ramochiuso">false</xsl:param>
	<xsl:param name="btn_tree">true</xsl:param>
	<xsl:param name="btn_menu">false</xsl:param>
	<xsl:if test="$btn_tree = 'true'">
		<a pos="{$pos}" href="javascript:void(0)" style="margin-left:1px; margin-right:5px" onclick="AppGlob.apriRigaRelazioni(this, 'sm.ciscoop.preno.pdc.soggetti.{$nomepdc}')" class="btn_espandi">
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
	<li style="margin-left:12px">Visualizza la relazione Sindacato</li>
	<li style="margin-left:12px">Visualizza la relazione PiattaformaSindacato</li>
	</ul>
	</div>
</xsl:template>


<!-- TEMPLATE BASE DEL DOCUMENTO -->
<!-- Scommentare solo se serve specializzare il comportamento -->
<!--xsl:template match="DOCUMENTO">
	<xsl:apply-templates select="*[@tipo='PDC']" mode ="nodopdc"/>
</xsl:template-->

<!-- TEMPLATE BASE DEL PDC -->
<!-- Scommentare solo se serve specializzare il comportamento -->
<!--xsl:template match="*" mode="nodopdc"-->
	<!--xsl:apply-templates select="IdPratica" mode ="hidden"/-->
	<!--xsl:apply-templates select="*[name() != 'IdPratica' and name() != 'ProgrFam']" mode ="nodoattributo"/-->
	<!--xsl:apply-templates select="*" mode ="nodoattributo"/>
</xsl:template-->

<xsl:template name="LeftFilter">

<!-- ============================================ -->
<!--    Attributi filtro                          -->
<!-- ============================================ -->

	<xsl:apply-templates select="/DOCUMENTO/Soggetto/IdSoggetto" mode="CampoIntero">
		<xsl:with-param name="size">10</xsl:with-param>
		<xsl:with-param name="chiave">true</xsl:with-param>
		<xsl:with-param name="caption">IdSoggetto</xsl:with-param>
	</xsl:apply-templates>

</xsl:template>

<xsl:template name="mieIframe" >
<div id="dialog" title="Basic dialog">
  <div id="divAnnoLiq" style="margin-bottom:5px"><label for="AnnoLiq" style="display:inline-block;min-width:85px">Anno Liq.</label><input class="testo" type="text" id="AnnoLiq" name="AnnoLiq" size="5"/></div>
  <div id="divNumLiq" style="margin-bottom:5px"><label for="NumLiq" style="display:inline-block;min-width:85px">Numero Liq.</label><input class="testo" type="text" id="NumLiq" name="NumLiq" size="5"/></div>
  <div id="divDtValuta" style="margin-bottom:5px"><label for="DtValuta" style="display:inline-block;min-width:85px">Data Valuta</label><input class="testo" type="text" id="DtValuta" name="DtValuta" size="12"/></div>
</div>

<!--xsl:call-template name="crea_iframe" >
   <xsl:with-param name="id" >fmSoggetto</xsl:with-param>
   <xsl:with-param name="name" >fmSoggetto</xsl:with-param>
   <xsl:with-param name="titolo">Soggetto</xsl:with-param>
   <xsl:with-param name="height">350px</xsl:with-param>
   <xsl:with-param name="width">400px</xsl:with-param>
   <xsl:with-param name="top">50px</xsl:with-param>
   <xsl:with-param name="left">auto</xsl:with-param>
</xsl:call-template-->
</xsl:template>

</xsl:stylesheet>
