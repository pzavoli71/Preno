
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"  xmlns:xs="http://www.w3.org/2001/XMLSchema" version="1.0">
<xsl:import href="${AppThemes}/atlanta-1.1.0-SNAPSHOT/xsl/theme.xsl"/>
<xsl:import href="${AppThemes}/atlanta-1.1.0-SNAPSHOT/xsl/forms2.xsl"/>
<xsl:import href="${AppXSL}/app.xsl"/>

<xsl:output method="html" indent="yes"/>

<xsl:template match="/">
	<xsl:call-template name="baseForm" >
		<xsl:with-param name="AggiungiAncora">true</xsl:with-param>	
		<xsl:with-param name="tooltips">true</xsl:with-param>		
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

<xsl:variable name="TitoloPagina"><xsl:value-of select="$tipo_transaz"/>PrenoRagazzo</xsl:variable>

<xsl:template  name="def_javascript">
function getTitolo(){
  return '  '  + '<xsl:value-of select="$tipo_transaz"/>' + ' <xsl:value-of select="$TitoloPagina"/>' + '   ';
}	

</xsl:template>

<xsl:template name="Titolo" >
	<xsl:value-of select="$TitoloPagina"/>
</xsl:template>

<xsl:template match="DOCUMENTO" >
    <xsl:call-template  name="Comandi" />
    
    <xsl:apply-templates select = "PrenoRagazzo"/>
    
    <!-- xsl:call-template name = "Errori"/-->
</xsl:template>

 
<xsl:template match="PrenoRagazzo">


	<xsl:apply-templates select="/DOCUMENTO/IdPrenotazione" mode="hidden">
	</xsl:apply-templates>

	<!--xsl:apply-templates select="/DOCUMENTO/IdPrenotazione" mode="jqcombo">
		<xsl:with-param name="enumerato" select="/DOCUMENTO/LstPrenoGiorno" />
		<xsl:with-param name="chiave">true</xsl:with-param>
		<xsl:with-param name="caption">IdVisita</xsl:with-param-->
		<!--xsl:with-param name="onchange">funzioneDaChiamare</xsl:with-param-->
		<!--xsl:with-param name="showExtra">true</xsl:with-param-->
		<!--xsl:with-param name="campi_lista">['nascita_data','idsoggetto']</xsl:with-param-->
		<!--xsl:with-param name="campi_collegati">['nascita_data','DtNascitaFamCar','idsoggetto','IdSoggettoAus']</xsl:with-param-->
		<!--xsl:with-param name="campi_filtro">null</xsl:with-param> modalita' dinamica ajax: campi da aggiungere all'url, ad esempio {Cognome: '_text_', IdAzienda: 'IdAzienda'} aggiunge "&Cognome=testocombo&IdAzienda=contenuto del campo con id=IdAzienda"-->
		<!--xsl:with-param name="url">/inspect/isp/HAllegati</xsl:with-param-->
		<!--xsl:with-param name="multiselect">true</xsl:with-param-->
	<!--/xsl:apply-templates-->


	<xsl:apply-templates select="IdSoggRagazzo" mode="CampoEnumerato">
		<xsl:with-param name="enumerato" select="/DOCUMENTO/LstRagazzo/RECORD"></xsl:with-param>
		<xsl:with-param name="chiave">true</xsl:with-param>
		<xsl:with-param name="caption">Ragazzo</xsl:with-param>
		<xsl:with-param name="id">IdSoggRagazzo</xsl:with-param>
	</xsl:apply-templates>

	<!--xsl:apply-templates select="/DOCUMENTO/IdSoggRagazzo" mode="jqcombo">
		<xsl:with-param name="enumerato" select="/DOCUMENTO/LstRagazzo" />
		<xsl:with-param name="chiave">true</xsl:with-param>
		<xsl:with-param name="caption">IdVisita</xsl:with-param-->
		<!--xsl:with-param name="onchange">funzioneDaChiamare</xsl:with-param-->
		<!--xsl:with-param name="showExtra">true</xsl:with-param-->
		<!--xsl:with-param name="campi_lista">['nascita_data','idsoggetto']</xsl:with-param-->
		<!--xsl:with-param name="campi_collegati">['nascita_data','DtNascitaFamCar','idsoggetto','IdSoggettoAus']</xsl:with-param-->
		<!--xsl:with-param name="campi_filtro">null</xsl:with-param> modalita' dinamica ajax: campi da aggiungere all'url, ad esempio {Cognome: '_text_', IdAzienda: 'IdAzienda'} aggiunge "&Cognome=testocombo&IdAzienda=contenuto del campo con id=IdAzienda"-->
		<!--xsl:with-param name="url">/inspect/isp/HAllegati</xsl:with-param-->
		<!--xsl:with-param name="multiselect">true</xsl:with-param-->
	<!--/xsl:apply-templates-->

</xsl:template>

<xsl:template name="ParametriPerAggiungiAncora">&amp;IdPrenotazione=<xsl:value-of select="/DOCUMENTO/PrenoRagazzo/IdPrenotazione"/></xsl:template>
</xsl:stylesheet>
