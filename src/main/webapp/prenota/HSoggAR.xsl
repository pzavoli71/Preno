<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"  xmlns:xs="http://www.w3.org/2001/XMLSchema" version="1.0">

<xsl:import href="${AppThemes}/atlanta-1.1.0-SNAPSHOT/xsl/theme.xsl"/>
<xsl:import href="${AppThemes}/atlanta-1.1.0-SNAPSHOT/xsl/forms2.xsl"/>
<xsl:import href="${AppXSL}/app.xsl"/>


<xsl:output method="html" indent="yes"/>

<xsl:template match="/">
	<xsl:call-template name="baseForm" >
		<xsl:with-param name="AggiungiAncora">true</xsl:with-param>	
		<xsl:with-param name="tooltips">true</xsl:with-param>		
		<!-- xsl:with-param name="ParamsAggiungiAncora"><xsl:call-template name="ParametriPerAggiungiAncora"></xsl:call-template></xsl:with-param-->		
	</xsl:call-template>
</xsl:template>



<xsl:template name="CssSpec" >
	<style>
	/*.cis-field-container .ValoreAttributo {
		width:350px;
	}*/
	.cis-field-container .cis-label {
    	width:120px;
	}	
	</style>
</xsl:template>

<!-- ============================================ -->
<!--     Titolo            						  -->
<!-- ============================================ -->
<xsl:template name="Titolo" >
	<xsl:value-of select="$TitoloPagina"/>
</xsl:template>
<xsl:variable name="TitoloPagina"><xsl:if test="/DOCUMENTO/parametri/TpSoggetto = 1">Autista</xsl:if><xsl:if test="/DOCUMENTO/parametri/TpSoggetto = 2">Ragazzo</xsl:if></xsl:variable>

<xsl:template  name="def_javascript">
function getTitolo(){
  return '  '  + '<xsl:value-of select="$tipo_transaz"/>' + ' <xsl:value-of select="$TitoloPagina"/>' + '   ';
}



</xsl:template>



<xsl:template match="DOCUMENTO" >
<p style="text-align:center;margin:0px;">(<xsl:value-of select="/DOCUMENTO/Giorno"/>/<xsl:value-of select="/DOCUMENTO/Mese"/>/<xsl:value-of select="/DOCUMENTO/Anno"/>)</p>
    <xsl:call-template  name="Comandi" />
	
    <xsl:apply-templates select = "SoggAR"/>
    
</xsl:template>

 
<xsl:template match="SoggAR">


	<xsl:apply-templates select="/DOCUMENTO/SoggAR/IdSoggetto" mode="CampoEnumerato">
		<xsl:with-param name="enumerato" select="/DOCUMENTO/LstSoggetto/RECORD"></xsl:with-param>		
		<xsl:with-param name="caption">Soggetto</xsl:with-param>
		<xsl:with-param name="id">IdSoggetto</xsl:with-param>
		<xsl:with-param name="noVuoto">true</xsl:with-param>
	</xsl:apply-templates>

	<!--xsl:apply-templates select="/DOCUMENTO/SoggAR/IdSoggetto" mode="jqcombo">
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

	<xsl:apply-templates select="/DOCUMENTO/SoggAR/IdGiorno" mode="hidden"/>

	<!--xsl:apply-templates select="/DOCUMENTO/SoggAR/IdGiorno" mode="CampoEnumerato">
		<xsl:with-param name="enumerato" select="/DOCUMENTO/LstGiorno/RECORD"></xsl:with-param>
		
		<xsl:with-param name="caption">IdGiorno</xsl:with-param>
		<xsl:with-param name="id">IdGiorno</xsl:with-param>
	</xsl:apply-templates-->

	<!--xsl:apply-templates select="/DOCUMENTO/SoggAR/IdGiorno" mode="jqcombo">
		<xsl:with-param name="enumerato" select="/DOCUMENTO/LstGiorno" />
		
		<xsl:with-param name="caption">IdVisita</xsl:with-param-->
		<!--xsl:with-param name="onchange">funzioneDaChiamare</xsl:with-param-->
		<!--xsl:with-param name="showExtra">true</xsl:with-param-->
		<!--xsl:with-param name="campi_lista">['nascita_data','idsoggetto']</xsl:with-param-->
		<!--xsl:with-param name="campi_collegati">['nascita_data','DtNascitaFamCar','idsoggetto','IdSoggettoAus']</xsl:with-param-->
		<!--xsl:with-param name="campi_filtro">null</xsl:with-param> modalita' dinamica ajax: campi da aggiungere all'url, ad esempio {Cognome: '_text_', IdAzienda: 'IdAzienda'} aggiunge "&Cognome=testocombo&IdAzienda=contenuto del campo con id=IdAzienda"-->
		<!--xsl:with-param name="url">/inspect/isp/HAllegati</xsl:with-param-->
		<!--xsl:with-param name="multiselect">true</xsl:with-param-->
	<!--/xsl:apply-templates-->

	<xsl:apply-templates select="/DOCUMENTO/SoggAR/IdSoggAndata" mode="hidden"/>

	<xsl:apply-templates select="/DOCUMENTO/SoggAR/AR" mode="CampoEnumerato">
		<xsl:with-param name="enumerato" select="/DOCUMENTO/LstAR/RECORD"></xsl:with-param>		
		<xsl:with-param name="caption">A/R</xsl:with-param>
		<xsl:with-param name="id">AR</xsl:with-param>
		<xsl:with-param name="noVuoto">true</xsl:with-param>
	</xsl:apply-templates>

	<xsl:apply-templates select="/DOCUMENTO/Giorno" mode="hidden"/>
	<xsl:apply-templates select="/DOCUMENTO/Anno" mode="hidden"/>
	<xsl:apply-templates select="/DOCUMENTO/Mese" mode="hidden"/>
	<xsl:apply-templates select="/DOCUMENTO/parametri/TpSoggetto" mode="hidden"/>
	<xsl:apply-templates select="/DOCUMENTO/IdTpTrasporto" mode="hidden"/>
	

</xsl:template>

<!-- xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx -->
<!-- Personalizzare per aggiungi ancora      -->
<!-- xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx -->

<xsl:template name="ParametriPerAggiungiAncora">&amp;Anno=<xsl:value-of select="/DOCUMENTO/Anno"/>&amp;Mese=<xsl:value-of select="/DOCUMENTO/Mese"/>&amp;Giorno=<xsl:value-of select="/DOCUMENTO/Giorno"/>&amp;TpSoggetto=<xsl:value-of select="/DOCUMENTO/TpSoggetto"/>&amp;IdTpTrasporto=<xsl:value-of select="/DOCUMENTO/IdTpTrasporto"/>&amp;AR=<xsl:value-of select="/DOCUMENTO/parametri/AR"/></xsl:template>

</xsl:stylesheet>
