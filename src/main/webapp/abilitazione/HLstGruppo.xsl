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
            return AppGlob.getBaseContext() + "/abilitazione/HLstGruppo?MPasso=1";
        }
    </xsl:template>

    <xsl:variable name="TitoloPagina">Lista gruppi di funzioni</xsl:variable>
    <xsl:template name="Titolo"><xsl:value-of select="$TitoloPagina"/></xsl:template>

    <!-- ============================================ -->
    <!-- Filtro -->
    <!-- ============================================ -->
    <xsl:template name="LeftFilter">
        <xsl:apply-templates select="/DOCUMENTO/zGruppo/CdGruppo" mode="nodoattributo"/>
        <xsl:apply-templates select="/DOCUMENTO/zGruppo/IdGruppo" mode="nodoattributo"/>
    </xsl:template>


    <!-- ============================================ -->
    <!-- Oggetto principale Lista -->
    <!-- ============================================ -->
    <xsl:template name="contenutoLista">pdc.zGruppo</xsl:template>
    
    <xsl:template name="IntestazioneLista">
        <a href="javascript:void(0)" onclick="document.location.reload()" class="refresh_btn cis-button"><i class="fa fa-refresh"/></a>
        <xsl:call-template name="ButtonHref">
            <xsl:with-param name="href"><xsl:value-of select="$app_context"/>/abilitazione/HGruppo?MTipo=I&amp;MPasso=1</xsl:with-param>
            <xsl:with-param name="title">Aggiungi un Gruppo</xsl:with-param>
            <xsl:with-param name="fa-class">fa-plus-circle</xsl:with-param>
            <xsl:with-param name="onunload">document.location.reload()</xsl:with-param>
            <xsl:with-param name="caption">Gestione Gruppo</xsl:with-param>
            <xsl:with-param name="text">Gruppo</xsl:with-param>
        </xsl:call-template>
    </xsl:template>
    
    <xsl:template name="IntestaTabella">
        <tr>
            <th/>
            <th>Codice Gruppo</th>
            <th>Descrizione breve</th>
            <th>Descrizione</th>
            <th>Flags</th>
        </tr>
    </xsl:template>
    
    <xsl:template match="zGruppo" mode="lista">
        <xsl:attribute name="data-key">{"CdGruppo" : "<xsl:value-of select="CdGruppo"/>"}</xsl:attribute>
        
        <td>
            <xsl:call-template name="BottoneRelazioni"/>
            <xsl:call-template name="ButtonHref">
                <xsl:with-param name="href"><xsl:value-of select="$app_context"/>/abilitazione/HGruppo?MTipo=V&amp;MPasso=2&amp;CdGruppo=<xsl:value-of select="CdGruppo"/></xsl:with-param>
                <xsl:with-param name="title">Modifica un gruppo</xsl:with-param>
                <xsl:with-param name="fa-class">fa-edit</xsl:with-param>                                                     
                <xsl:with-param name="onunload">CISLoader.loadEntity({CdGruppo: "<xsl:value-of select="CdGruppo"/>"})</xsl:with-param>         
                <xsl:with-param name="caption">Gestione Gruppo</xsl:with-param>      
            </xsl:call-template>
        </td>
    
        <td><xsl:value-of select="CdGruppo"/></td>
        <td><xsl:value-of select="IdGruppo"/></td>
        <td><xsl:value-of select="DsGruppo"/></td>
        <td><xsl:value-of select="Flags"/></td>
    </xsl:template>




    <!-- ============================================ -->
    <!-- Relazione Permessi -->
    <!-- ============================================ -->
    <xsl:template match="ListaGruppo_Permessi" mode="contenutoLista">pdc.zPermessi</xsl:template>
    <xsl:template match="ListaGruppo_Permessi" mode="relazioniToEnable">["Gruppo_Permessi"]</xsl:template><!-- leggi documentazione in liste2.xsl -->
	
    <xsl:template match="ListaGruppo_Permessi" mode="titoloRelazione">
        <a class="refresh_btn cis-button" href="javascript:void(0)">
            <xsl:attribute name="onClick">CISLoader.loadEntity({CdGruppo: "<xsl:value-of select="../CdGruppo"/>"}, this.atag)</xsl:attribute>
            <i class="fa fa-refresh"/>
        </a>
        <xsl:call-template name="ButtonHref">
            <xsl:with-param name="href"><xsl:value-of select="$app_context"/>/abilitazione/HPermessi?MTipo=I&amp;MPasso=1&amp;CdGruppo=<xsl:value-of select="../CdGruppo"/></xsl:with-param>
            <xsl:with-param name="title">Aggiungi un permesso</xsl:with-param>
            <xsl:with-param name="fa-class">fa-plus-circle</xsl:with-param>
            <xsl:with-param name="onunload">CISLoader.loadEntity({CdGruppo: "<xsl:value-of select="../CdGruppo"/>"}, this.atag)</xsl:with-param>
            <xsl:with-param name="caption">Gestione Permesso</xsl:with-param>
        </xsl:call-template>
        <span class="cis-titolo-rel">Permessi</span>
        <xsl:call-template name="Minimizzatore"/>
    </xsl:template>
    
    <xsl:template match="ListaGruppo_Permessi" mode="intestaTabella">
        <tr>
            <th/>
            <th>Descrizione</th>
            <th>Permesso</th>
            <th>Transazione</th>
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
                <xsl:with-param name="caption">Gestione Permesso</xsl:with-param>
            </xsl:call-template>
        </td>
        <td><xsl:value-of select="Descrizione"/></td>
        <td><xsl:value-of select="Permesso"/></td>
        <td><xsl:value-of select="CdPDC"/></td>
    </xsl:template>



    <!-- ============================================ -->
    <!-- Relazione UtGr -->
    <!-- ============================================ -->
    <xsl:template match="ListaGruppo_UtGr" mode="contenutoLista">'pdc.zUtGr</xsl:template>
    <xsl:template match="ListaGruppo_UtGr" mode="relazioniToEnable">["UtGr_zUtente"]</xsl:template>
    
    <xsl:template match="ListaGruppo_UtGr" mode="titoloRelazione">
        <a class="refresh_btn cis-button" href="javascript:void(0)">
            <xsl:attribute name="onClick">CISLoader.loadEntity({CdGruppo: "<xsl:value-of select="../CdGruppo"/>"},this)</xsl:attribute>
            <i class="fa fa-refresh"/>
        </a>
        <xsl:call-template name="ButtonHref">
            <xsl:with-param name="href"><xsl:value-of select="$app_context"/>/abilitazione/HUtGr?MTipo=I&amp;MPasso=1&amp;CdGruppo=<xsl:value-of select="CdGruppo"/></xsl:with-param>
            <xsl:with-param name="title">Aggiungi un UtGr</xsl:with-param>
            <xsl:with-param name="fa-class">fa-plus-circle</xsl:with-param>
            <xsl:with-param name="onunload">CISLoader.loadEntity({CdPDC: "<xsl:value-of select="../CdPDC"/>"})</xsl:with-param>
            <xsl:with-param name="caption">Gestione UtGr</xsl:with-param>
        </xsl:call-template>
        <span class="cis-titolo-rel">Utenti</span>
        <xsl:call-template name="Minimizzatore"/>
    </xsl:template>
    
    <xsl:template match="ListaGruppo_UtGr" mode="intestaTabella">
        <tr>
            <th/>
            <th>Utente</th>
        </tr>
    </xsl:template>
    
    <xsl:template match="zUtGr" mode="lista">
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
    
        <td><xsl:value-of select="CdUtente"/>&#xA0;<xsl:value-of select="zUtente/DsUtente"/></td>
    </xsl:template>



    <!-- ============================================ -->
    <!-- Relazione RuoliGruppo -->
    <!-- ============================================ -->
    <xsl:template match="ListaGruppo_RuoliGruppo" mode="contenutoLista">pdc.zPermessi</xsl:template>
    <xsl:template match="ListaGruppo_RuoliGruppo" mode="relazioniToEnable">["RuoliGruppo_Ruolo"]</xsl:template>
    
    
    <xsl:template match="ListaGruppo_RuoliGruppo" mode="titoloRelazione">
        <a class="refresh_btn cis-button" href="javascript:void(0)">
            <xsl:attribute name="onClick">CISLoader.loadEntity({CdGruppo: "<xsl:value-of select="../CdGruppo"/>"},this)</xsl:attribute>
            <i class="fa fa-refresh"/>
        </a>
        <xsl:call-template name="ButtonHref">
            <xsl:with-param name="href"><xsl:value-of select="$app_context"/>/abilitazione/HUtGr?MTipo=I&amp;MPasso=1&amp;CdGruppo=<xsl:value-of select="CdGruppo"/></xsl:with-param>
            <xsl:with-param name="title">Aggiungi un UtGr</xsl:with-param>
            <xsl:with-param name="fa-class">fa-plus-circle</xsl:with-param>                                                     
            <xsl:with-param name="onunload">CISLoader.loadEntity({CdGruppo: "<xsl:value-of select="../CdGruppo"/>"})</xsl:with-param>         
            <xsl:with-param name="caption">Gestione UtGr</xsl:with-param>      
        </xsl:call-template>
        <span class="cis-titolo-rel">Ruoli del gruppo</span>
        <xsl:call-template name="Minimizzatore"/>
    </xsl:template>
    
    <xsl:template match="ListaGruppo_RuoliGruppo" mode="intestaTabella">
        <tr>
            <th/>
            <th>Ruolo</th>
        </tr>
    </xsl:template>
    
    <xsl:template match="zRuoliGruppo" mode="lista">
        <xsl:attribute name="data-key">{"CdGruppo" : "<xsl:value-of select="CdGruppo"/>", "IdRuolo" : "<xsl:value-of select="IdRuolo"/>"}</xsl:attribute>
        
        <td>
            <xsl:call-template name="BottoneRelazioni"/>
            <xsl:call-template name="ButtonHref">
                <xsl:with-param name="href"><xsl:value-of select="$app_context"/>/abilitazione/HRuoliGruppo?MTipo=V&amp;MPasso=2&amp;CdGruppo=<xsl:value-of select="CdGruppo"/>&amp;IdRuolo=<xsl:value-of select="IdRuolo"/></xsl:with-param>
                <xsl:with-param name="title">Modifica un RuoliGruppo</xsl:with-param>
                <xsl:with-param name="fa-class">fa-edit</xsl:with-param>
                <xsl:with-param name="onunload">CISLoader.loadEntity({CdGruppo: "<xsl:value-of select="CdGruppo"/>", IdRuolo: "<xsl:value-of select="IdRuolo"/>"})</xsl:with-param>
                <xsl:with-param name="caption">Gestione RuoliGruppo</xsl:with-param>
            </xsl:call-template>
        </td>
    
        <td><xsl:value-of select="IdRuolo"/>&#xA0;<xsl:value-of select="zRuolo/NomeRuolo"/></td>
    </xsl:template>


    <!-- ============================================ -->
    <!--    Attributi filtro                          -->
    <!-- ============================================ -->
    <xsl:template match="CdGruppo" mode="nodoattributo">
        <xsl:apply-templates select="." mode="CampoIntero">
            <xsl:with-param name="size">10</xsl:with-param>
            <xsl:with-param name="caption">Codice gruppo</xsl:with-param>   
            <xsl:with-param name="kill0">true</xsl:with-param>
        </xsl:apply-templates>
    </xsl:template>
    
    <xsl:template match="IdGruppo" mode="nodoattributo">
        <xsl:apply-templates select="." mode="CampoIntero">
            <xsl:with-param name="size">50</xsl:with-param>
            <xsl:with-param name="caption">Nome del gruppo</xsl:with-param> 
        </xsl:apply-templates>
    </xsl:template>
</xsl:stylesheet>