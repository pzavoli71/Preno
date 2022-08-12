<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="3.0">
    <xsl:import href="${AppThemes}/atlanta-1.1.0-SNAPSHOT/xsl/theme.xsl"/>
	<xsl:import href="${AppThemes}/atlanta-1.1.0-SNAPSHOT/xsl/forms.xsl" />
    <xsl:import href="${AppXSL}/app.xsl"/>
    
    <xsl:output method='html' indent='yes'/>
    
    <xsl:template match="/"><xsl:call-template name="baseForm"/></xsl:template>


    <xsl:variable name="TitoloPagina">Permessi della trans</xsl:variable>
    
    <xsl:template name="def_javascript">
        function getTitolo() {
            return '  '  + '<xsl:value-of select="$tipo_transaz"/>' + ' <xsl:value-of select="$TitoloPagina"/>' + '   ';
        }
    </xsl:template>

 

    <xsl:template match="Descrizione" mode="nodoattributo">
        <xsl:call-template name="CampoTesto">
            <xsl:with-param name="size">20</xsl:with-param>
            <xsl:with-param name="caption">Descrizione</xsl:with-param>
        </xsl:call-template>
    </xsl:template>
    
    
    <xsl:template match="Permesso" mode="nodoattributo">
        <xsl:call-template name="CampoTesto">
            <xsl:with-param name="size">10</xsl:with-param>
            <xsl:with-param name="caption">Permesso</xsl:with-param>
            <xsl:with-param name="obbl">true</xsl:with-param>
        </xsl:call-template>
    </xsl:template>


    <xsl:template match="CdPDC" mode="nodoattributo">
        <!-- xsl:call-template name="CampoEnumeratoX">
            <xsl:with-param name="enumerato" select="../zTrans/Nome"></xsl:with-param>
            <xsl:with-param name="servlet"><xsl:value-of select="$app_context"/>/abilitazione/HzPermessi</xsl:with-param>
            <xsl:with-param name="campocodice">cdpdc</xsl:with-param> 
            <xsl:with-param name="campodesc">nome</xsl:with-param> 
            <xsl:with-param name="size">7</xsl:with-param>
            <xsl:with-param name="widthCampo">270px</xsl:with-param>
            <xsl:with-param name="ParametriCombo">{minwidth:'340px'}</xsl:with-param>
            <xsl:with-param name="chiave">true</xsl:with-param>
            <xsl:with-param name="ParametriServlet">{MTipo:'V',GetXML:'true',MPasso:2,flSoloDatiCombo:'true'}</xsl:with-param>
            <xsl:with-param name="caption">cdPDC</xsl:with-param>
        </xsl:call-template-->
    
        <xsl:call-template name="CampoEnumerato">
            <xsl:with-param name="enumerato" select="/DOCUMENTO/LstTrans/RECORD"></xsl:with-param>
            <xsl:with-param name="chiave">true</xsl:with-param>
            <xsl:with-param name="caption">Nome della funzione</xsl:with-param>
            
        </xsl:call-template>
    </xsl:template>
    
    
    <xsl:template match="CdGruppo" mode="nodoattributo">
        <xsl:call-template name="CampoEnumerato">
            <xsl:with-param name="enumerato" select="/DOCUMENTO/LstGruppo/RECORD"></xsl:with-param>
            <xsl:with-param name="chiave">true</xsl:with-param>
            <xsl:with-param name="caption">Gruppo</xsl:with-param>
            <xsl:with-param name="noVuoto">true</xsl:with-param>
        </xsl:call-template>
    </xsl:template>
</xsl:stylesheet>