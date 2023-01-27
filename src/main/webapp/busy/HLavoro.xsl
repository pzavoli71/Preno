
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

<xsl:variable name="TitoloPagina"><xsl:value-of select="$tipo_transaz"/>Lavoro</xsl:variable>

<xsl:template  name="def_javascript">
function getTitolo(){
  return '  '  + '<xsl:value-of select="$tipo_transaz"/>' + ' <xsl:value-of select="$TitoloPagina"/>' + '   ';
}	

</xsl:template>

<xsl:template name="Titolo" >
	<xsl:value-of select="$TitoloPagina"/>
</xsl:template>

<xsl:template match="DOCUMENTO" >
    <!-- xsl:call-template  name="Comandi" /-->
    
    <xsl:apply-templates select = "Lavoro"/>
    
    <xsl:call-template name = "Errori"/>
</xsl:template>

 
<xsl:template match="Lavoro">
	<xsl:apply-templates select="IdObiettivo" mode="hidden"/>

	<!--xsl:apply-templates select="IdObiettivo" mode="CampoEnumerato">
		<xsl:with-param name="enumerato" select="/DOCUMENTO/LstObiettivo/RECORD"></xsl:with-param>
		
		<xsl:with-param name="caption">IdObiettivo</xsl:with-param>
		<xsl:with-param name="id">IdObiettivo</xsl:with-param>
	</xsl:apply-templates-->

	<!--xsl:apply-templates select="IdObiettivo" mode="jqcombo">
		<xsl:with-param name="enumerato" select="/DOCUMENTO/LstObiettivo" />
		
		<xsl:with-param name="caption">IdVisita</xsl:with-param-->
		<!--xsl:with-param name="onchange">funzioneDaChiamare</xsl:with-param-->
		<!--xsl:with-param name="showExtra">true</xsl:with-param-->
		<!--xsl:with-param name="campi_lista">['nascita_data','idsoggetto']</xsl:with-param-->
		<!--xsl:with-param name="campi_collegati">['nascita_data','DtNascitaFamCar','idsoggetto','IdSoggettoAus']</xsl:with-param-->
		<!--xsl:with-param name="campi_filtro">null</xsl:with-param> modalita' dinamica ajax: campi da aggiungere all'url, ad esempio {Cognome: '_text_', IdAzienda: 'IdAzienda'} aggiunge "&Cognome=testocombo&IdAzienda=contenuto del campo con id=IdAzienda"-->
		<!--xsl:with-param name="url">/inspect/isp/HAllegati</xsl:with-param-->
		<!--xsl:with-param name="multiselect">true</xsl:with-param-->
	<!--/xsl:apply-templates-->

	<xsl:apply-templates select="IdLavoro" mode="CampoIntero">
		<xsl:with-param name="size">10</xsl:with-param>
		<xsl:with-param name="chiave">true</xsl:with-param><xsl:with-param name="serial">true</xsl:with-param>
		<xsl:with-param name="caption">IdLavoro</xsl:with-param>
	</xsl:apply-templates>

	<xsl:apply-templates select="DtLavoro" mode="CampoData">
		<xsl:with-param name="size">10</xsl:with-param>
		
		<xsl:with-param name="caption">DtLavoro</xsl:with-param>
	</xsl:apply-templates>

	<xsl:apply-templates select="OraInizio" mode="CampoIntero">
		<xsl:with-param name="size">10</xsl:with-param>
		
		<xsl:with-param name="caption">OraInizio</xsl:with-param>
	</xsl:apply-templates>

	<xsl:apply-templates select="MinutiInizio" mode="CampoIntero">
		<xsl:with-param name="size">10</xsl:with-param>
		
		<xsl:with-param name="caption">MinutiInizio</xsl:with-param>
	</xsl:apply-templates>

	<xsl:apply-templates select="NotaLavoro" mode="CampoTesto">
		<xsl:with-param name="size">10</xsl:with-param>
		
		<xsl:with-param name="caption">NotaLavoro</xsl:with-param>
	</xsl:apply-templates>

	<xsl:apply-templates select="OraFine" mode="CampoIntero">
		<xsl:with-param name="size">10</xsl:with-param>
		
		<xsl:with-param name="caption">OraFine</xsl:with-param>
	</xsl:apply-templates>

	<xsl:apply-templates select="MinutiFine" mode="CampoIntero">
		<xsl:with-param name="size">10</xsl:with-param>
		
		<xsl:with-param name="caption">MinutiFine</xsl:with-param>
	</xsl:apply-templates>

</xsl:template>
</xsl:stylesheet>
