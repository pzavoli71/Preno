<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"  xmlns:xs="http://www.w3.org/2001/XMLSchema" version="1.0">

<xsl:import href="${AppThemes}/atlanta-1.1.0-SNAPSHOT/xsl/theme.xsl"/>
<xsl:import href="${AppThemes}/atlanta-1.1.0-SNAPSHOT/xsl/forms2.xsl"/>
<xsl:import href="${AppXSL}/app.xsl"/>


<xsl:output method="html" indent="yes"/>

<xsl:template match="/">
	<xsl:call-template name="baseForm" >
		<!-- xsl:with-param name="AggiungiAncora">true</xsl:with-param-->	
		<xsl:with-param name="tooltips">true</xsl:with-param>		
	</xsl:call-template>
</xsl:template>



<xsl:template name="CssSpec" >
	<style>
	/*.cis-field-container .ValoreAttributo {
		width:350px;
	}*/
	
	.cis-field-container .cis-label {
    	width:150px;
	}	
	</style>
</xsl:template>

<!-- ============================================ -->
<!--     Titolo            						  -->
<!-- ============================================ -->
<xsl:template name="Titolo" >
	<xsl:value-of select="$TitoloPagina"/>
</xsl:template>
<xsl:variable name="TitoloPagina">Risposta Quiz</xsl:variable>

<xsl:template  name="def_javascript">
function getTitolo(){
  return '  '  + '<xsl:value-of select="$tipo_transaz"/>' + ' <xsl:value-of select="$TitoloPagina"/>' + '   ';
}



</xsl:template>



<xsl:template match="DOCUMENTO" >
    <xsl:call-template  name="Comandi" />
    
    <xsl:apply-templates select = "RispQuiz"/>
    
</xsl:template>

 
<xsl:template match="RispQuiz">

	<xsl:apply-templates select="IdDomanda" mode="hidden"/>

	<xsl:apply-templates select="IdDomandaTest" mode="hidden"/>


	<xsl:apply-templates select="IdRispTest" mode="hidden"/>


	<xsl:apply-templates select="RespVero" mode="CampoCheckbox">
		<xsl:with-param name="size">10</xsl:with-param>
		
		<xsl:with-param name="caption">Vero</xsl:with-param>
	</xsl:apply-templates>

	<xsl:apply-templates select="RespFalso" mode="CampoCheckbox">
		<xsl:with-param name="size">10</xsl:with-param>
		
		<xsl:with-param name="caption">Falso</xsl:with-param>
	</xsl:apply-templates>

	<xsl:apply-templates select="EsitoRisp" mode="CampoCheckbox">
		<xsl:with-param name="size">10</xsl:with-param>
		
		<xsl:with-param name="caption">EsitoRisp</xsl:with-param>
		<xsl:with-param name="readonly">true</xsl:with-param>
	</xsl:apply-templates>

</xsl:template>
</xsl:stylesheet>
