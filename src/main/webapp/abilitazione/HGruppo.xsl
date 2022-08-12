<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="3.0">
    <xsl:import href="${AppThemes}/atlanta-1.1.0-SNAPSHOT/xsl/theme.xsl"/>
	<xsl:import href="${AppThemes}/atlanta-1.1.0-SNAPSHOT/xsl/forms.xsl" />
    <xsl:import href="${AppXSL}/app.xsl"/>
    
    <xsl:output method='html' indent='yes' />
    
    <xsl:template match="/">
        <xsl:call-template name="baseForm">
            <xsl:with-param name="AggiungiAncora">true</xsl:with-param>
        </xsl:call-template>
    </xsl:template>
    
    <xsl:variable name="TitoloPagina">Titolo della Pagina</xsl:variable>
    
    <xsl:template name="def_javascript">
        function getTitolo() {
            return '  '  + '<xsl:value-of select="$tipo_transaz"/>' + ' <xsl:value-of select="$TitoloPagina"/>' + '   ';
        }
    </xsl:template>


    <xsl:template match="CdGruppo" mode="nodoattributo">
        <xsl:call-template name="CampoIntero">
            <xsl:with-param name="size">10</xsl:with-param>
            <xsl:with-param name="chiave">true</xsl:with-param>
            <xsl:with-param name="readonly">true</xsl:with-param>
            <xsl:with-param name="caption">cd. Gruppo</xsl:with-param>
        </xsl:call-template>
    </xsl:template>
    
    
    <xsl:template match="IdGruppo" mode="nodoattributo">
        <xsl:call-template name="CampoTesto">
            <xsl:with-param name="size">30</xsl:with-param>
            <xsl:with-param name="caption">Nome Gruppo</xsl:with-param>
            <xsl:with-param name="obbl">true</xsl:with-param>
        </xsl:call-template>
    </xsl:template>
    
    
    <xsl:template match="DsGruppo" mode="nodoattributo">
        <xsl:call-template name="CampoTesto">
            <xsl:with-param name="size">30</xsl:with-param>
            <xsl:with-param name="caption">Descrizione</xsl:with-param>
            <xsl:with-param name="obbl">true</xsl:with-param>
        </xsl:call-template>
    </xsl:template>
    
    
    <xsl:template match="Flags" mode="nodoattributo">
        <xsl:call-template name="CampoIntero">
            <xsl:with-param name="size">10</xsl:with-param>
            <xsl:with-param name="caption">flags</xsl:with-param>
        </xsl:call-template>
    </xsl:template>
</xsl:stylesheet>