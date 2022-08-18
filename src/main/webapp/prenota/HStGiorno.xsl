
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" version="1.0">

<xsl:import href="${AppThemes}/atlanta-1.0.0-SNAPSHOT/xsl/theme.xsl"/>
<xsl:import href="${AppThemes}/atlanta-1.0.0-SNAPSHOT/xsl/forms2.xsl"/>
<xsl:import href="${AppXSL}/app.xsl"/>


<xsl:output method='html' indent='yes'/>

<xsl:template match="/">
	<xsl:call-template  name = "baseForm" >
		<xsl:with-param name="method">get</xsl:with-param>
		<xsl:with-param name="noform">true</xsl:with-param>
	</xsl:call-template  >
</xsl:template>



<xsl:template name="CssSpec" >
	<style>
	/*.cis-field-container .ValoreAttributo {
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
<xsl:variable name="TitoloPagina">Giorno</xsl:variable>



<xsl:template  name="def_javascript">
function getTitolo(){
  return '  '  + '<xsl:value-of select="$tipo_transaz"/>' + ' <xsl:value-of select="$TitoloPagina"/>' + '   ';
}

function initspec() {

};

function richiestaComando(nomecomando, chiave, dati) {
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


function attivaFinestraPerStampa() {		
	var iframe = window.frameElement;
	if ( iframe) {
		var idframe = $(iframe).attr('id');
		var wtif = $(iframe).width();
		var htif = $(iframe).height();
		var x = $(iframe).css('left');
		var y = $(iframe).css('top');
		var position = $(iframe).css('position');
		var topw = window.top;
		var wt = $(topw).width();
		var ht = $(topw).height();		
		var $docframe = $(document); 				
		$(docframe).find('body').empty();
		$(docframe).find('body').append('&lt;div style="width:50%;margin:auto;font-size:30px;text-align:center" &gt;Attendere:Sto caricando il file...&lt;/div&gt;');
		$(iframe).css('position','absolute').css('border','1px solid black').css('box-shadow', '-1px 2px 15px 1px #000000').css('top','25px').css('left','10px').css('height',(ht - 40) +'px').css('width',(wt - 20)+'px');
		var docpadre = window.parent.document;
		$(docpadre).find('body').append('&lt;div id="idChiudiFrame" idframe="'+idframe+'" title="Chiudi la stampa" style="cursor:pointer;font-size:14px;font-weight:bold;position:absolute;right:10px;top:1px;border:2px solid black;border-radius:11px;width:19px;height:19px;line-height:19px;padding:0px;text-align:center" onclick="AppGlob.ripristinaFrameDopoStampa(this,\''+idframe+'\',\''+x+'\',\''+y+'\','+wtif+','+htif+',\''+position+'\')"&gt;X&lt;/div&gt;');
	}
}

function inviaAjax(frm) {
	var dati = {};
	var $form = $('form#fmStampa');
	var hrefdati = '/preno/prenota/HStGiorno?MTipo=G&amp;MPasso=3&amp;cisCheckValidita=true&amp;';
	hrefdati += $form.serialize();
	AppGlob.showLoading();
	$('body').load(hrefdati, function() {
		if ( $('form#fmStampa').hasClass('attivaDiretta')) {
			var hrefdati = '/preno/prenota/HStGiorno?MTipo=G&amp;MPasso=3&amp;cisStampaDiretta=true&amp;';
			hrefdati += $form.serialize();
			var iframe = window.frameElement;
			if ( iframe)
				iframe.src = hrefdati;
			else
				document.location.src = hrefdati; 
			attivaFinestraPerStampa();
		}	
	}); 
	return false;
}

</xsl:template>


<!-- TEMPLATE BASE DEL DOCUMENTO -->
<xsl:template match="DOCUMENTO">
<xsl:if test="/DOCUMENTO/cisStampaDiretta != 'true'">
	<form id="fmStampa" name="fmStampa" target="_blank">
	<!--xsl:if test="/DOCUMENTO/cisValidazioneOK = 'true' or /DOCUMENTO/cisStampaDiretta = 'true'" >
		<xsl:attribute name="class">attivaDiretta</xsl:attribute>
	</xsl:if-->

	<input type="hidden" name="cisStampaDiretta" value="true"/>
	
	<xsl:call-template name="Comandi"/>

	<xsl:apply-templates select="/DOCUMENTO/Giorno/IdGiorno" mode="nodoattributo" />
	
	<xsl:call-template name="Esegui">	 
	</xsl:call-template>
	
	</form>	
</xsl:if>		
</xsl:template>




<xsl:template match="IdGiorno" mode="nodoattributo">

	<xsl:apply-templates select="/DOCUMENTO/Giorno/IdGiorno" mode="CampoIntero">
		<xsl:with-param name="size">10</xsl:with-param>
		<xsl:with-param name="chiave">true</xsl:with-param><xsl:with-param name="serial">true</xsl:with-param>
		<xsl:with-param name="caption">IdGiorno</xsl:with-param>
	</xsl:apply-templates>

</xsl:template>



</xsl:stylesheet>
