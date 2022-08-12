<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="3.0">
    <xsl:import href="${AppThemes}/atlanta-1.1.0-SNAPSHOT/xsl/theme.xsl"/>
	<xsl:import href="${AppThemes}/atlanta-1.1.0-SNAPSHOT/xsl/forms.xsl" />
    <xsl:import href="${AppXSL}/app.xsl"/>
    
    <xsl:output method='html' indent='yes'/>
    
    <xsl:template match="/">
        <xsl:call-template name="baseForm">
            <xsl:with-param name="AggiungiAncora">true</xsl:with-param>
        </xsl:call-template>
    </xsl:template>
    
    <xsl:variable name="TitoloPagina">Titolo della Pagina</xsl:variable>
    
    <xsl:template  name="def_javascript">
        function getTitolo() {
            return '  '  + '<xsl:value-of select="$tipo_transaz"/>' + ' <xsl:value-of select="$TitoloPagina"/>' + '   ';
        }
    </xsl:template>

    <xsl:template match="CdPDC" mode="nodoattributo">
        <xsl:call-template name="CampoTesto">
            <xsl:with-param name="size">30</xsl:with-param>
            <xsl:with-param name="chiave">true</xsl:with-param>
            <xsl:with-param name="caption">Nome Transazione</xsl:with-param>
            <xsl:with-param name="obbl">true</xsl:with-param>
        </xsl:call-template>
    </xsl:template>
    
    
    <xsl:template match="DsPDC" mode="nodoattributo">
        <xsl:call-template name="CampoTesto">
            <xsl:with-param name="size">40</xsl:with-param> 
            <xsl:with-param name="caption">descrizione</xsl:with-param>
        </xsl:call-template>
    </xsl:template>
    
    
    <xsl:template match="IdNodo" mode="nodoattributo">
        <xsl:if test="/DOCUMENTO/MTIPO != 'I'">
            <xsl:call-template name="CampoIntero">
                <xsl:with-param name="size">10</xsl:with-param>
                <xsl:with-param name="caption">Id Nodo</xsl:with-param>
                <xsl:with-param name="readonly">true</xsl:with-param>
            </xsl:call-template>
        </xsl:if>
    </xsl:template>
    
    
    <xsl:template match="IdPadre" mode="nodoattributo">
        <xsl:call-template name="CampoIntero">
            <xsl:with-param name="size">10</xsl:with-param>
            <xsl:with-param name="caption">idPadre</xsl:with-param>
        </xsl:call-template>
    </xsl:template>
    
    
    <xsl:template match="Flag" mode="nodoattributo">
        <xsl:call-template name="CampoIntero">
            <xsl:with-param name="size">10</xsl:with-param>
            <xsl:with-param name="caption">flag</xsl:with-param>
        </xsl:call-template>
    </xsl:template>
</xsl:stylesheet>