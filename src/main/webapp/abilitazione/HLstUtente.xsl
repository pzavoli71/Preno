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
            return AppGlob.getBaseContext() + "/abilitazione/HLstUtente?MPasso=1";
        }
    </xsl:template>

    <xsl:variable name="TitoloPagina">Elenco Utenti</xsl:variable>
    <xsl:template name="Titolo"><xsl:value-of select="$TitoloPagina"/></xsl:template>


    <!-- ============================================ -->
    <!-- Filtro -->
    <!-- ============================================ -->
    <xsl:template name="LeftFilter">
        <xsl:apply-templates select="/DOCUMENTO/zUtente/CdUtente" mode="nodoattributo"/>
        <xsl:apply-templates select="/DOCUMENTO/zUtente/DsUtente" mode="nodoattributo"/>
    </xsl:template>


    <!-- ============================================ -->
    <!-- Oggetto principale Lista -->
    <!-- ============================================ -->
    <xsl:template name="contenutoLista">pdc.zUtente</xsl:template>
    
    <xsl:template name="IntestazioneLista">
        <a href="javascript:void(0)" onclick="document.location.reload()" class="refresh_btn cis-button"><i class="fa fa-refresh"/></a>
        <xsl:call-template name="ButtonHref">
            <xsl:with-param name="href"><xsl:value-of select="$app_context"/>/abilitazione/HUtente?MTipo=I&amp;MPasso=1</xsl:with-param>
            <xsl:with-param name="title">Aggiungi un nuovo Utente</xsl:with-param>
            <xsl:with-param name="fa-class">fa-plus-circle</xsl:with-param>
            <xsl:with-param name="onunload">document.location.reload()</xsl:with-param>
            <xsl:with-param name="caption">Gestione Utente</xsl:with-param>
            <xsl:with-param name="text">Utente</xsl:with-param>
        </xsl:call-template>
    </xsl:template>
    
    <xsl:template name="IntestaTabella">
        <tr>
            <th/>
            <th>Codice utente</th>
            <th>Identificativo Utente</th>
            <th>Descrizione Utente</th>
            <th>Utente Internet</th>
        </tr>
    </xsl:template>
    
    <xsl:template match="zUtente" mode="lista">
        <xsl:attribute name="data-key">{"CdUtente" : "<xsl:value-of select="CdUtente"/>"}</xsl:attribute>
        
        <td>
            <xsl:call-template name="BottoneRelazioni"/>
            <xsl:call-template name="ButtonHref">
                <xsl:with-param name="href"><xsl:value-of select="$app_context"/>/abilitazione/HUtente?MTipo=V&amp;MPasso=2&amp;CdUtente=<xsl:value-of select="CdUtente"/></xsl:with-param>
                <xsl:with-param name="title">Modifica un Utente</xsl:with-param>
                <xsl:with-param name="fa-class">fa-edit</xsl:with-param>                                                     
                <xsl:with-param name="onunload">CISLoader.loadEntity({CdUtente: "<xsl:value-of select="CdUtente"/>"})</xsl:with-param>         
                <xsl:with-param name="caption">Gestione Utente</xsl:with-param>      
            </xsl:call-template>
        </td>
        
        <td><xsl:value-of select="CdUtente"/></td>
        <td><xsl:value-of select="IdUtente"/></td>
        <td><xsl:value-of select="DsUtente"/></td>
        <td><xsl:value-of select="UtenteInternet"/></td>
    </xsl:template>


    <!-- ============================================ -->
    <!-- Relazione UtGr -->
    <!-- ============================================ -->
    <xsl:template match="ListazUtente_UtGr" mode="contenutoLista">pdc.zUtGr</xsl:template>
    <xsl:template match="ListazUtente_UtGr" mode="relazioniToEnable">["UtGr_Gruppo"]</xsl:template>
    
    <xsl:template match="ListazUtente_UtGr" mode="titoloRelazione">
        <a class="refresh_btn cis-button" href="javascript:void(0)">
            <xsl:attribute name="onClick">CISLoader.loadEntity({CdUtente: "<xsl:value-of select="../CdUtente"/>"})</xsl:attribute>
            <i class="fa fa-refresh"/>
        </a>
        <xsl:call-template name="ButtonHref">
            <xsl:with-param name="href"><xsl:value-of select="$app_context"/>/abilitazione/HUtGr?MTipo=I&amp;MPasso=1&amp;CdUtente=<xsl:value-of select="CdUtente"/></xsl:with-param>
            <xsl:with-param name="title">Aggiungi un UtGr</xsl:with-param>
            <xsl:with-param name="fa-class">fa-plus-circle</xsl:with-param>                                                     
            <xsl:with-param name="onunload">CISLoader.loadEntity({CdUtente: "<xsl:value-of select="../CdUtente"/>"})</xsl:with-param>         
            <xsl:with-param name="caption">Gestione UtGr</xsl:with-param>      
        </xsl:call-template>
        <span class="cis-titolo-rel">Relazione Utente-Gruppo</span>
    </xsl:template>

    <xsl:template match="ListazUtente_UtGr" mode="intestaTabella">
        <tr>
            <th/>
            <th>Codice Gruppo</th>
        </tr>
    </xsl:template>

    <xsl:template match="UtGr" mode="lista">
        <xsl:attribute name="data-key">{"CdGruppo" : "<xsl:value-of select="CdGruppo"/>", "CdUtente" : "<xsl:value-of select="CdUtente"/>"}</xsl:attribute>
        
        <td>
            <xsl:call-template name="BottoneRelazioni"/>
            <xsl:call-template name="ButtonHref">
                <xsl:with-param name="href"><xsl:value-of select="$app_context"/>/abilitazione/HUtGr?MTipo=V&amp;MPasso=2&amp;CdGruppo=<xsl:value-of select="CdGruppo"/>&amp;CdUtente=<xsl:value-of select="CdUtente"/></xsl:with-param>
                <xsl:with-param name="title">Modifica un UtGr</xsl:with-param>
                <xsl:with-param name="fa-class">fa-edit</xsl:with-param>                                                     
                <xsl:with-param name="onunload">CISLoader.loadEntity({CdGruppo: "<xsl:value-of select="CdGruppo"/>", CdUtente: "<xsl:value-of select="CdUtente"/>"})</xsl:with-param>         
                <xsl:with-param name="caption">Gestione UtGr</xsl:with-param>      
            </xsl:call-template>
        </td>
        <td><xsl:value-of select="CdGruppo"/> - <xsl:value-of select="Gruppo/DsGruppo"/></td>
    </xsl:template>
    <!-- ============================================ -->
    <!-- Attributi Filtro -->
    <!-- ============================================ -->
    <xsl:template match="CdUtente" mode="nodoattributo">
        <xsl:apply-templates select="." mode="CampoIntero">
            <xsl:with-param name="size">15</xsl:with-param>
            <xsl:with-param name="caption">Codice Utente</xsl:with-param>
            <xsl:with-param name="kill0">true</xsl:with-param>
        </xsl:apply-templates>
    </xsl:template>

    <xsl:template match="DsUtente" mode="nodoattributo">
        <xsl:apply-templates select="." mode="CampoTesto">
            <xsl:with-param name="size">40</xsl:with-param>
            <xsl:with-param name="caption">Nome Utente</xsl:with-param>
        </xsl:apply-templates>
    </xsl:template>
</xsl:stylesheet>