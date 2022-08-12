<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="3.0">
    <xsl:import href="${AppThemes}/atlanta-1.1.0-SNAPSHOT/xsl/theme.xsl"/>
	<xsl:import href="${AppThemes}/atlanta-1.1.0-SNAPSHOT/xsl/forms.xsl" />
    <xsl:import href="${AppXSL}/app.xsl"/>

<xsl:output method='html' indent='yes'/>

<xsl:template match="/">
	<xsl:call-template name="baseForm">		
	</xsl:call-template>
</xsl:template>

<xsl:template name="CssSpec" >
	<style>
	.cis-field-container label {
		width:150px;
	}
	/*.cis-field-container .ValoreAttributo {
		width:350px;
	}*/
	</style>
</xsl:template>

<xsl:variable name="TitoloPagina">Titolo della Pagina</xsl:variable>

<xsl:template  name="def_javascript">
function getTitolo() {
  return '  '  + '<xsl:value-of select="$tipo_transaz"/>' + ' <xsl:value-of select="$TitoloPagina"/>' + '   ';
}

$(function() {
	//$('#CampoNomeFamCar').hide();
	//$('input ::first').focus();
	$('input[tipo="m"]').off().removeAttr('onKeypress').keypress(function(evento) {		
		AppGlob.replacePunto(evento);
	});		
});

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

 

<xsl:template match="CdGruppo" mode="nodoattributo">

	<!-- xsl:call-template name="CampoEnumeratoX">
		<xsl:with-param name="enumerato" select="../Gruppo/Nome"></xsl:with-param>
		<xsl:with-param name="servlet"><xsl:value-of select="$app_context"/>/abilitazione/HzRuoliGruppo</xsl:with-param>
		<xsl:with-param name="campocodice">cdgruppo</xsl:with-param> 
		<xsl:with-param name="campodesc">nome</xsl:with-param> 
		<xsl:with-param name="size">7</xsl:with-param>
		<xsl:with-param name="widthCampo">270px</xsl:with-param>
		<xsl:with-param name="ParametriCombo">{minwidth:'340px'}</xsl:with-param>
		<xsl:with-param name="chiave">true</xsl:with-param>
		<xsl:with-param name="ParametriServlet">{MTipo:'V',GetXML:'true',MPasso:2,flSoloDatiCombo:'true'}</xsl:with-param>
		<xsl:with-param name="caption">cdGruppo</xsl:with-param>
	</xsl:call-template-->

	<xsl:call-template name="CampoEnumerato">
		<xsl:with-param name="enumerato" select="/DOCUMENTO/LstGruppo/RECORD"></xsl:with-param>
		<xsl:with-param name="chiave">true</xsl:with-param>
		<xsl:with-param name="caption">cdGruppo</xsl:with-param>
	</xsl:call-template>


</xsl:template>



<xsl:template match="IdRuolo" mode="nodoattributo">

	<!-- xsl:call-template name="CampoEnumeratoX">
		<xsl:with-param name="enumerato" select="../zRuolo/Nome"></xsl:with-param>
		<xsl:with-param name="servlet"><xsl:value-of select="$app_context"/>/abilitazione/HzRuoliGruppo</xsl:with-param>
		<xsl:with-param name="campocodice">idruolo</xsl:with-param> 
		<xsl:with-param name="campodesc">nome</xsl:with-param> 
		<xsl:with-param name="size">7</xsl:with-param>
		<xsl:with-param name="widthCampo">270px</xsl:with-param>
		<xsl:with-param name="ParametriCombo">{minwidth:'340px',NumeroChar:1}</xsl:with-param>
		<xsl:with-param name="chiave">true</xsl:with-param>
		<xsl:with-param name="ParametriServlet">{MTipo:'V',GetXML:'true',MPasso:2,flSoloDatiCombo:'true'}</xsl:with-param>
		<xsl:with-param name="caption">IdRuolo</xsl:with-param>
	</xsl:call-template-->

	<xsl:call-template name="CampoEnumerato">
		<xsl:with-param name="enumerato" select="/DOCUMENTO/LstRuolo/RECORD"></xsl:with-param>
		<xsl:with-param name="chiave">true</xsl:with-param>
		<xsl:with-param name="caption">IdRuolo</xsl:with-param>
	</xsl:call-template>


</xsl:template>



</xsl:stylesheet>
