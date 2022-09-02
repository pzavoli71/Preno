<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"  xmlns:xs="http://www.w3.org/2001/XMLSchema" version="1.0">

<xsl:import href="${AppThemes}/atlanta-1.0.0-SNAPSHOT/xsl/theme.xsl"/>
<xsl:import href="${AppThemes}/atlanta-1.0.0-SNAPSHOT/xsl/forms2.xsl"/>
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
<xsl:variable name="TitoloPagina">Domanda</xsl:variable>

<xsl:template  name="def_javascript">
function getTitolo(){
  return '  '  + '<xsl:value-of select="$tipo_transaz"/>' + ' <xsl:value-of select="$TitoloPagina"/>' + '   ';
}



</xsl:template>



<xsl:template match="DOCUMENTO" >
    <xsl:call-template  name="Comandi" />
    
    <xsl:apply-templates select = "Domanda"/>
    
</xsl:template>

 
<xsl:template match="Domanda">

	<xsl:apply-templates select="/DOCUMENTO/Domanda/IdDomanda" mode="CampoIntero">
		<xsl:with-param name="size">10</xsl:with-param>
		<xsl:with-param name="chiave">true</xsl:with-param><xsl:with-param name="serial">true</xsl:with-param>
		<xsl:with-param name="caption">IdDomanda</xsl:with-param>
	</xsl:apply-templates>

	<xsl:apply-templates select="/DOCUMENTO/Domanda/IdCapitolo" mode="CampoIntero">
		<xsl:with-param name="size">10</xsl:with-param>
		
		<xsl:with-param name="caption">IdCapitolo</xsl:with-param>
	</xsl:apply-templates>

	<xsl:apply-templates select="/DOCUMENTO/Domanda/IdDom" mode="CampoIntero">
		<xsl:with-param name="size">10</xsl:with-param>
		
		<xsl:with-param name="caption">IdDom</xsl:with-param>
	</xsl:apply-templates>

	<xsl:apply-templates select="/DOCUMENTO/Domanda/IdProgr" mode="CampoIntero">
		<xsl:with-param name="size">10</xsl:with-param>
		
		<xsl:with-param name="caption">IdProgr</xsl:with-param>
	</xsl:apply-templates>

	<xsl:apply-templates select="/DOCUMENTO/Domanda/Asserzione" mode="CampoTesto">
		<xsl:with-param name="size">10</xsl:with-param>
		
		<xsl:with-param name="caption">Asserzione</xsl:with-param>
	</xsl:apply-templates>

	<xsl:apply-templates select="/DOCUMENTO/Domanda/Valore" mode="CampoCheckbox">
		<xsl:with-param name="size">10</xsl:with-param>
		
		<xsl:with-param name="caption">Valore</xsl:with-param>
	</xsl:apply-templates>

	<xsl:apply-templates select="/DOCUMENTO/Domanda/linkimg" mode="CampoTesto">
		<xsl:with-param name="size">10</xsl:with-param>
		
		<xsl:with-param name="caption">linkimg</xsl:with-param>
	</xsl:apply-templates>

</xsl:template>
</xsl:stylesheet>
