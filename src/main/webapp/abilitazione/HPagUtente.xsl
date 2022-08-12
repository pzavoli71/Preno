<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="3.0">
    <xsl:import href="${AppThemes}/atlanta-1.1.0-SNAPSHOT/xsl/pagutente.xsl"/>
    <xsl:import href="${AppXSL}/app.xsl"/>
    
	<xsl:output method="html" encoding="utf-8" indent="yes" />
	
	<xsl:variable name="lstMenu"><xsl:value-of select="$app_context"></xsl:value-of>/abilitazione/Menu.xml</xsl:variable>

	<xsl:template match="/">
		<xsl:call-template name="PagUtente" />
	</xsl:template>
	
	<xsl:template name="PannelloFooter" ></xsl:template>	
	<xsl:template name="PannelloCss">
		<meta name="mobile-web-app-capable" content="yes"/>
		<meta name="apple-mobile-web-app-capable" content="yes"/>
	</xsl:template>
</xsl:stylesheet>