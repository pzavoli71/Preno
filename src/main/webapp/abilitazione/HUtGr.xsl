<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="3.0">
    <xsl:import href="${AppThemes}/atlanta-1.1.0-SNAPSHOT/xsl/theme.xsl"/>
	<xsl:import href="${AppThemes}/atlanta-1.1.0-SNAPSHOT/xsl/forms.xsl" />
    <xsl:import href="${AppXSL}/app.xsl"/>
    
    <xsl:output method='html' indent='yes'/>
    
    <xsl:template match="/">
        <xsl:call-template name="baseForm"/>
    </xsl:template>
    
    
    <xsl:variable name="TitoloPagina">Titolo della Pagina</xsl:variable>
    
    <xsl:template  name="def_javascript">
    function getTitolo() {
        return '  '  + '<xsl:value-of select="$tipo_transaz"/>' + ' <xsl:value-of select="$TitoloPagina"/>' + '   ';
    }
    </xsl:template>

 
    <xsl:template match="CdGruppo" mode="nodoattributo">
        <!-- xsl:call-template name="CampoEnumeratoX">
            <xsl:with-param name="enumerato" select="../Gruppo/Nome"></xsl:with-param>
            <xsl:with-param name="servlet"><xsl:value-of select="$app_context"/>/abilitazione/HUtGr</xsl:with-param>
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
            <xsl:with-param name="caption">Codice Gruppo</xsl:with-param>
        </xsl:call-template>
    </xsl:template>
    
    
    
    <xsl:template match="CdUtente" mode="nodoattributo">
        <xsl:call-template name="CampoEnumeratoX">
<!--             <xsl:with-param name="enumerato" select="../zUtente/DsUtente"></xsl:with-param> -->
            <xsl:with-param name="servlet"><xsl:value-of select="$app_context"/>/abilitazione/HUtGr</xsl:with-param>
            <xsl:with-param name="campocodice">cdutente</xsl:with-param> 
            <xsl:with-param name="campodesc">nome</xsl:with-param> 
            <xsl:with-param name="size">7</xsl:with-param>
            <xsl:with-param name="ParametriCombo">{minwidth:'340px'}</xsl:with-param>
            <xsl:with-param name="chiave">true</xsl:with-param>
            <xsl:with-param name="ParametriServlet">{MTipo:'V',GetXML:'true',MPasso:2,flSoloDatiCombo:'true'}</xsl:with-param>
<!--            <xsl:with-param name="altricampi">['cdutente','tipo']</xsl:with-param> -->
            <xsl:with-param name="caption">Codice Utente</xsl:with-param>
        </xsl:call-template>
    </xsl:template>
</xsl:stylesheet>