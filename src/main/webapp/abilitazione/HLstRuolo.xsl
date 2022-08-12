<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="3.0">
    <xsl:import href="${AppThemes}/atlanta-1.1.0-SNAPSHOT/xsl/theme.xsl"/>
	<xsl:import href="${AppThemes}/atlanta-1.1.0-SNAPSHOT/xsl/liste2.xsl" />
    <xsl:import href="${AppXSL}/app.xsl"/>
    <xsl:output method='html' indent='yes'/>
    <xsl:decimal-format name="euro" decimal-separator="," grouping-separator="."/>


    <xsl:template name="def_javascript">
        function getTitolo() {
            return '  '  + '<xsl:value-of select="$tipo_transaz"/>' + ' <xsl:value-of select="$TitoloPagina"/>' + '   ';
        }
        
        function getAutoHref() {
            return AppGlob.getBaseContext() + "/abilitazione/HLstRuolo?MPasso=1";
        }
    </xsl:template>

    <xsl:variable name="TitoloPagina">Ruoli del Portale</xsl:variable>
    <xsl:template name="Titolo"><xsl:value-of select="$TitoloPagina"/></xsl:template>

    <!-- ============================================ -->
    <!-- Filtro -->
    <!-- ============================================ -->
    <xsl:template name="LeftFilter">
        <xsl:apply-templates select="/DOCUMENTO/zRuolo/IdRuolo" mode="nodoattributo"/>
    </xsl:template>

    <!-- ============================================ -->
    <!-- Oggetto principale Lista -->
    <!-- ============================================ -->
    <xsl:template name="contenutoLista">pdc.zRuolo</xsl:template>
    
    <xsl:template name="IntestazioneLista">
        <a href="javascript:void(0)" onclick="document.location.reload()" class="refresh_btn cis-button"><i class="fa fa-refresh"/></a>
        <xsl:call-template name="ButtonHref">
            <xsl:with-param name="href"><xsl:value-of select="$app_context"/>/abilitazione/HRuolo?MTipo=I&amp;MPasso=1</xsl:with-param>
            <xsl:with-param name="title">Aggiungi un Ruolo</xsl:with-param>
            <xsl:with-param name="fa-class">fa-plus-circle</xsl:with-param>                                                     
            <xsl:with-param name="onunload">document.location.reload()</xsl:with-param>         
            <xsl:with-param name="caption">Gestione Ruolo</xsl:with-param>
            <xsl:with-param name="text">Ruolo</xsl:with-param>
        </xsl:call-template>
    </xsl:template>
    
    <xsl:template name="IntestaTabella">
        <tr>
            <th/>
            <th>IdRuolo</th>
            <th>NomeRuolo</th>
        </tr>
    </xsl:template>
    
    <xsl:template match="zRuolo" mode="lista">
        <xsl:attribute name="data-key">{"IdRuolo" : "<xsl:value-of select="IdRuolo"/>"}</xsl:attribute>
        
        <td>
            <xsl:call-template name="BottoneRelazioni"/>
            <xsl:call-template name="ButtonHref">
                <xsl:with-param name="href"><xsl:value-of select="$app_context"/>/abilitazione/HRuolo?MTipo=V&amp;MPasso=2&amp;IdRuolo=<xsl:value-of select="IdRuolo"/></xsl:with-param>
                <xsl:with-param name="title">Modifica un ruolo</xsl:with-param>
                <xsl:with-param name="fa-class">fa-edit</xsl:with-param>
                <xsl:with-param name="onunload">CISLoader.loadEntity({IdRuolo: "<xsl:value-of select="IdRuolo"/>"})</xsl:with-param>
                <xsl:with-param name="caption">Gestione ruolo</xsl:with-param>
            </xsl:call-template>
        </td>
        
        <td><xsl:value-of select="IdRuolo"/></td>
        <td><xsl:value-of select="NomeRuolo"/></td>
    </xsl:template>
    
    
    <!-- ============================================ -->
    <!-- Relazione RuoliGruppo -->
    <!-- ============================================ -->
    <xsl:template match="ListaRuolo_RuoliGruppo" mode="contenutoLista">pdc.zRuoliGruppo</xsl:template>
    <xsl:template match="ListaRuolo_RuoliGruppo" mode="relazioniToEnable">["RuoliGruppo_Gruppo"]</xsl:template>
    
    
    <xsl:template match="ListaRuolo_RuoliGruppo" mode="titoloRelazione">
        <a class="refresh_btn cis-button" href="javascript:void(0)">
            <xsl:attribute name="onClick">CISLoader.loadEntity({IdRuolo: "<xsl:value-of select="../IdRuolo"/>"}, this.atag)</xsl:attribute>
            <i class="fa fa-refresh"/>
        </a>
        <xsl:call-template name="ButtonHref">
                <xsl:with-param name="href"><xsl:value-of select="$app_context"/>/abilitazione/HRuoliGruppo?MTipo=I&amp;MPasso=1&amp;IdRuolo=<xsl:value-of select="../IdRuolo"/></xsl:with-param>
                <xsl:with-param name="title">Aggiungi un RuoliGruppo</xsl:with-param>
                <xsl:with-param name="fa-class">fa-plus-circle</xsl:with-param>
            	<xsl:with-param name="onunload">CISLoader.loadEntity({CdGruppo: "<xsl:value-of select="../IdRuolo"/>"}, this.atag)</xsl:with-param>
            	<xsl:with-param name="caption">Gestione RuoliGruppo</xsl:with-param>
            </xsl:call-template>
        <span class="cis-titolo-rel">RuoliGruppo</span>
    </xsl:template>

    <xsl:template match="ListaRuolo_RuoliGruppo" mode="intestaTabella">
        <tr>
            <th/>
            <th>Codice Gruppo</th>
        </tr>
    </xsl:template>

    <xsl:template match="zRuoliGruppo" mode="lista">
        <xsl:attribute name="data-key">{"IdRuolo" : "<xsl:value-of select="IdRuolo"/>", "CdGruppo" : "<xsl:value-of select="CdGruppo"/>"}</xsl:attribute>
        
        <td>
            <xsl:call-template name="BottoneRelazioni"/>
            <xsl:call-template name="ButtonHref">
                <xsl:with-param name="href"><xsl:value-of select="$app_context"/>/abilitazione/HRuoliGruppo?MTipo=V&amp;MPasso=2&amp;CdGruppo=<xsl:value-of select="CdGruppo"/>&amp;IdRuolo=<xsl:value-of select="IdRuolo"/></xsl:with-param>
                <xsl:with-param name="title">Modifica un RuoliGruppo</xsl:with-param>
                <xsl:with-param name="fa-class">fa-edit</xsl:with-param>
            	<xsl:with-param name="onunload">CISLoader.loadEntity({IdRuolo: "<xsl:value-of select="IdRuolo"/>", CdGruppo: "<xsl:value-of select="CdGruppo"/>"})</xsl:with-param>
                <xsl:with-param name="caption">Gestione RuoliGruppo</xsl:with-param>
            </xsl:call-template>
        </td>
        
        <td><xsl:value-of select="CdGruppo"/> - <xsl:value-of select="zGruppo/DsGruppo"/></td>
    </xsl:template>


    <!-- ============================================ -->
    <!-- Attributi Filtro -->
    <!-- ============================================ -->
    <xsl:template match="IdRuolo" mode="nodoattributo">
        <xsl:apply-templates select="." mode="CampoIntero">
            <xsl:with-param name="size">50</xsl:with-param>
            <xsl:with-param name="caption">IdRuolo</xsl:with-param>
            <xsl:with-param name="kill0">true</xsl:with-param>
        </xsl:apply-templates>
    </xsl:template>
</xsl:stylesheet>