<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="3.0">
    <xsl:import href="${AppThemes}/atlanta-1.1.0-SNAPSHOT/xsl/theme.xsl"/>
	<xsl:import href="${AppThemes}/atlanta-1.1.0-SNAPSHOT/xsl/forms.xsl" />
    <xsl:import href="${AppXSL}/app.xsl"/>
    <xsl:output method='html' indent='yes'/>

    <xsl:template match="/"><xsl:call-template name="baseForm"/></xsl:template>
    
    
    <xsl:variable name="TitoloPagina">Titolo della Pagina</xsl:variable>

    <xsl:template name="def_javascript">
        function getTitolo() {
            return '  '  + '<xsl:value-of select="$tipo_transaz"/>' + ' <xsl:value-of select="$TitoloPagina"/>' + '   ';
        }
    </xsl:template>

 

    <xsl:template match="CdUtente" mode="nodoattributo">
        <xsl:call-template name="CampoIntero">
            <xsl:with-param name="size">10</xsl:with-param>
            <xsl:with-param name="chiave">true</xsl:with-param>
            <xsl:with-param name="caption">Codice Utente</xsl:with-param>
        </xsl:call-template>
    </xsl:template>
    
    <xsl:template match="IdUtente" mode="nodoattributo">
        <xsl:call-template name="CampoTesto">
            <xsl:with-param name="size">20</xsl:with-param>
            <xsl:with-param name="caption">Identificativo</xsl:with-param>
        </xsl:call-template>
    </xsl:template>
    
    <xsl:template match="DsUtente" mode="nodoattributo">
        <xsl:call-template name="CampoTesto">
            <xsl:with-param name="size">30</xsl:with-param>
            <xsl:with-param name="caption">Descrizione Utente</xsl:with-param>
        </xsl:call-template>
    </xsl:template>
    
    <xsl:template match="UtenteInternet" mode="nodoattributo">
        <xsl:call-template name="CampoCheckbox">
            <xsl:with-param name="caption">É un utente internet?</xsl:with-param>
            <xsl:with-param name="readonly">true</xsl:with-param>
        </xsl:call-template>
    </xsl:template>
    
    <xsl:template match="Disabled" mode="nodoattributo">
        <xsl:call-template name="CampoCheckbox">
            <xsl:with-param name="caption">É disabilitato?</xsl:with-param>
            <xsl:with-param name="readonly">true</xsl:with-param>
        </xsl:call-template>
    </xsl:template>
    
    <xsl:template match="ScadPasswd" mode="nodoattributo">
        <xsl:call-template name="CampoData">
            <xsl:with-param name="caption">Scadenza Password</xsl:with-param>
            <xsl:with-param name="readonly">true</xsl:with-param>
        </xsl:call-template>
    </xsl:template>
    
    <xsl:template match="DurataPasswd" mode="nodoattributo">
        <xsl:call-template name="CampoTesto">
            <xsl:with-param name="size">30</xsl:with-param>
            <xsl:with-param name="caption">Durata Password</xsl:with-param>
            <xsl:with-param name="readonly">true</xsl:with-param>
        </xsl:call-template>
    </xsl:template>
    
    <xsl:template match="bEntraSenzaSmartCard" mode="nodoattributo">
        <xsl:call-template name="CampoCheckbox">
            <xsl:with-param name="caption">Entra senza Smart Card?</xsl:with-param>
            <xsl:with-param name="readonly">true</xsl:with-param>
        </xsl:call-template>
    </xsl:template>
    
    <xsl:template match="Email" mode="nodoattributo">
        <xsl:call-template name="CampoTesto">
            <xsl:with-param name="size">30</xsl:with-param>
            <xsl:with-param name="caption">Email</xsl:with-param>
        </xsl:call-template>
    </xsl:template>
</xsl:stylesheet>