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
            var href = AppGlob.getBaseContext() + "/abilitazione/HLstTrans?MPasso=1";
            return href;
        }
        
<!--         $(function() { -->
<!--             $('.cis-list').cisTTrans(); -->
<!--         }); -->
    </xsl:template>


    <xsl:variable name="TitoloPagina">Elenco delle Funzioni</xsl:variable>
    <xsl:template name="Titolo"><xsl:value-of select="$TitoloPagina"/></xsl:template>

<!--     <xsl:template name="enableTTrans">true</xsl:template> -->

    <!-- ============================================ -->
    <!-- Filtro -->
    <!-- ============================================ -->
    <xsl:template name="LeftFilter">
        <xsl:apply-templates select="/DOCUMENTO/zTrans/CdPDC" mode="nodoattributo"/>
    </xsl:template>


    <!-- ============================================ -->
    <!-- Oggetto principale Lista -->
    <!-- ============================================ -->
    <xsl:template name="contenutoLista">pdc.zTrans</xsl:template>
    
    <xsl:template name="IntestazioneLista">
        <a href="javascript:void(0)" onclick="document.location.reload()" class="refresh_btn cis-button"><i class="fa fa-refresh"/></a>
        <xsl:call-template name="ButtonHref">
            <xsl:with-param name="href"><xsl:value-of select="$app_context"/>/abilitazione/HTrans?MTipo=I&amp;MPasso=1</xsl:with-param>
            <xsl:with-param name="title">Aggiungi una nuova Transazione</xsl:with-param>
            <xsl:with-param name="fa-class">fa-plus-circle</xsl:with-param>                                                     
            <xsl:with-param name="onunload">document.location.reload()</xsl:with-param>         
            <xsl:with-param name="caption">Gestione Trans</xsl:with-param>      
            <xsl:with-param name="text">Transazione</xsl:with-param>                                                            
        </xsl:call-template>
    </xsl:template>
    
    <xsl:template name="IntestaTabella">
        <tr>
            <th/>
            <th data-nomecol="CdPDC">Transazione</th>
            <th data-nomecol="DsPDC">Descrizione</th>
            <th data-nomecol="IdNodo">Id Nodo</th>
            <th data-nomecol="IdPadre">Id Padre</th>
            <th data-nomecol="Flag">Flag</th>
        </tr>
    </xsl:template>

    <xsl:template match="zTrans" mode="lista">
        <xsl:attribute name="data-key">{"CdPDC" : "<xsl:value-of select="CdPDC"/>"}</xsl:attribute>
        
        <td>
            <xsl:call-template name="BottoneRelazioni"/>
            <xsl:call-template name="ButtonHref">
                <xsl:with-param name="href"><xsl:value-of select="$app_context"/>/abilitazione/HTrans?MTipo=V&amp;MPasso=2&amp;CdPDC=<xsl:value-of select="CdPDC"/></xsl:with-param>
                <xsl:with-param name="title">Modifica una transazione</xsl:with-param>
                <xsl:with-param name="fa-class">fa-edit</xsl:with-param>
                <xsl:with-param name="onunload">CISLoader.loadEntity({CdPDC: "<xsl:value-of select="CdPDC"/>"})</xsl:with-param>
                <xsl:with-param name="caption">Gestione transazione</xsl:with-param>
            </xsl:call-template>
        </td>
        
        <td><xsl:value-of select="CdPDC"/></td>
        <td><xsl:value-of select="DsPDC"/></td>
        <td><xsl:value-of select="IdNodo"/></td>
        <td><xsl:value-of select="IdPadre"/></td>
        <td><xsl:value-of select="Flag"/></td>
    </xsl:template>


    <!-- ============================================ -->
    <!-- Relazione Permessi -->
    <!-- ============================================ -->
    <xsl:template match="ListaTrans_Permessi" mode="contenutoLista">pdc.zPermessi</xsl:template>
    <xsl:template match="ListaTrans_Permessi" mode="relazioniToEnable">["Permessi_Gruppo"]</xsl:template>
    
    
    <xsl:template match="ListaTrans_Permessi" mode="titoloRelazione">
        <a class="refresh_btn cis-button" href="javascript:void(0)">
            <xsl:attribute name="onClick">CISLoader.loadEntity({CdPDC: "<xsl:value-of select="../CdPDC"/>"})</xsl:attribute>
            <i class="fa fa-refresh"/>
        </a>
        <xsl:call-template name="ButtonHref">
            <xsl:with-param name="href"><xsl:value-of select="$app_context"/>/abilitazione/HPermessi?MTipo=I&amp;MPasso=1&amp;CdPDC=<xsl:value-of select="../CdPDC"/></xsl:with-param>
            <xsl:with-param name="title">Aggiungi un permesso</xsl:with-param>
            <xsl:with-param name="fa-class">fa-plus-circle</xsl:with-param>
            <xsl:with-param name="onunload">CISLoader.loadEntity({CdPDC: "<xsl:value-of select="../CdPDC"/>"})</xsl:with-param>
            <xsl:with-param name="caption">Gestione permesso</xsl:with-param>
        </xsl:call-template>
        <span class="cis-titolo-rel">zPermessi</span>
    </xsl:template>

    <xsl:template match="ListaTrans_Permessi" mode="intestaTabella">
        <tr>
            <th/>
            <th data-nomecol="Descrizione">Descrizione</th>
            <th data-nomecol="Permesso">Permesso</th>
            <th data-nomecol="CdGruppo">Gruppo</th>
        </tr>
    </xsl:template>

    <xsl:template match="zPermessi" mode="lista">
        <xsl:attribute name="data-key">{"CdGruppo" : "<xsl:value-of select="CdGruppo"/>", "CdPDC" : "<xsl:value-of select="CdPDC"/>"}</xsl:attribute>
        
        <td>
            <xsl:call-template name="BottoneRelazioni"/>
            <xsl:call-template name="ButtonHref">
                <xsl:with-param name="href"><xsl:value-of select="$app_context"/>/abilitazione/HPermessi?MTipo=V&amp;MPasso=2&amp;CdPDC=<xsl:value-of select="CdPDC"/>&amp;CdGruppo=<xsl:value-of select="CdGruppo"/></xsl:with-param>
                <xsl:with-param name="title">Modifica un permesso</xsl:with-param>
                <xsl:with-param name="fa-class">fa-edit</xsl:with-param>                                                     
                <xsl:with-param name="onunload">CISLoader.loadEntity({CdGruppo: "<xsl:value-of select="CdGruppo"/>", CdPDC: "<xsl:value-of select="CdPDC"/>"})</xsl:with-param>         
                <xsl:with-param name="caption">Gestione permesso</xsl:with-param>      
            </xsl:call-template>
        </td>
        <td><xsl:value-of select="Descrizione"/></td>
        <td><xsl:value-of select="Permesso"/></td>
        <td><xsl:value-of select="CdGruppo"/> - <xsl:value-of select="zGruppo/DsGruppo"/></td>
    </xsl:template>


    <!-- ============================================ -->
    <!-- Campi Filtro -->
    <!-- ============================================ -->
    <xsl:template match="CdPDC" mode="nodoattributo">
        <xsl:apply-templates select="." mode="CampoTesto">
            <xsl:with-param name="size">50</xsl:with-param>
            <xsl:with-param name="caption">Nome Transazione</xsl:with-param>    
        </xsl:apply-templates>
    </xsl:template>
</xsl:stylesheet>