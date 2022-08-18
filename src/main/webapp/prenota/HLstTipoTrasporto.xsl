<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" version="1.0">

<xsl:import href="${AppThemes}/atlanta-1.1.0-SNAPSHOT/xsl/theme.xsl"/>
<xsl:import href="${AppThemes}/atlanta-1.1.0-SNAPSHOT/xsl/liste2.xsl"/>
<xsl:import href="${AppXSL}/app.xsl"/>


<xsl:output method='html' indent='yes'/>
<xsl:decimal-format name="euro" decimal-separator="," grouping-separator="."/>
	


<xsl:template  name="def_javascript">
function getTitolo(){
  return '  '  + '<xsl:value-of select="$tipo_transaz"/>' + ' <xsl:value-of select="$TitoloPagina"/>' + '   ';
}


function getAutoHref() {
	return "/preno/prenota/HLstTipoTrasporto?MPasso=1";
}

<!-- Scommenta se hai importato la libreria Tnew.js e vuoi trasformare la lista in una T -->
<!--		$(function() { -->
<!--			$('.cis-mainlist').cisTTrans(); -->   <!-- Scommenta questo se vuoi solo la lista principale come T -->
<!--			$('.cis-list').cisTTrans(); -->
<!--		}); -->


</xsl:template>

<!-- ============================================ -->
<!--     Titolo            						  -->
<!-- ============================================ -->
<xsl:template name="Titolo" >
	<xsl:value-of select="$TitoloPagina"/>
</xsl:template>
<xsl:variable name="TitoloPagina">TipoTrasporto</xsl:variable>

<!-- ============================================ -->
<!-- Filtro 										 -->
<!-- ============================================ -->
<xsl:template name="LeftFilter">
	
		<xsl:apply-templates select="/DOCUMENTO/TipoTrasporto/IdTpTrasporto" mode="nodoattributo"/>
	
</xsl:template>
	

<!-- ============================================ -->
<!-- Attributi Filtro 							 -->
<!-- ============================================ -->
	
<xsl:template match="IdTpTrasporto" mode="nodoattributo">

	<xsl:apply-templates select="/DOCUMENTO/TipoTrasporto/IdTpTrasporto" mode="CampoIntero">
		<xsl:with-param name="size">10</xsl:with-param>
		<xsl:with-param name="chiave">true</xsl:with-param><xsl:with-param name="serial">true</xsl:with-param>
		<xsl:with-param name="caption">IdTpTrasporto</xsl:with-param>
	</xsl:apply-templates>

</xsl:template>


	
	<!-- ============================================ -->
	<!-- Oggetto principale Lista 					 -->
	<!-- ============================================ -->
	<xsl:template name="contenutoLista">prenota.TipoTrasporto</xsl:template>
	
	<xsl:template name="IntestazioneLista">
		<a href="javascript:void(0)" onclick="document.location.reload()" class="refresh_btn cis-button"><i class="fa fa-refresh"/></a>
		<xsl:call-template name="ButtonHref">
			<xsl:with-param name="href">/preno/prenota/HTipoTrasporto?MTipo=I&amp;MPasso=1</xsl:with-param>
			<xsl:with-param name="title">Aggiungi un TipoTrasporto</xsl:with-param>
			<xsl:with-param name="fa-class">fa-plus-circle</xsl:with-param>
			<xsl:with-param name="onunload">document.location.reload()</xsl:with-param>
			<xsl:with-param name="caption">Gestione TipoTrasporto</xsl:with-param>
			<xsl:with-param name="text">TipoTrasporto</xsl:with-param>
		</xsl:call-template>
	</xsl:template>


	<xsl:template name="IntestaTabella">
		<tr>
			<th/>
			<th>IdTpTrasporto</th>
			<th>NomeTpTrasporto</th>
		</tr>
	</xsl:template>


	<xsl:template match="TipoTrasporto" mode="lista">
		<xsl:attribute name="data-key">{"IdTpTrasporto" : "<xsl:value-of select="IdTpTrasporto"/>"}</xsl:attribute>

		<td>
			<xsl:call-template name="BottoneRelazioni"/>
			<xsl:call-template name="ButtonHref">
				<xsl:with-param name="href">/preno/prenota/HTipoTrasporto?MTipo=V&amp;MPasso=2&amp;IdTpTrasporto=<xsl:value-of select="IdTpTrasporto"/></xsl:with-param>
				<xsl:with-param name="title">Modifica un TipoTrasporto</xsl:with-param>
				<xsl:with-param name="fa-class">fa-edit</xsl:with-param>
				<xsl:with-param name="onunload">document.location.reload();</xsl:with-param>
				<xsl:with-param name="caption">Gestione TipoTrasporto</xsl:with-param>
			</xsl:call-template>
		</td>
		<td><xsl:value-of select="IdTpTrasporto"/></td>
		<td><xsl:value-of select="NomeTpTrasporto"/></td>
	</xsl:template>
	
	
	
	
	<!-- ============================================ -->
	<!-- Relazione Giorno 	 -->
	<!-- ============================================ -->
	<xsl:template match="ListaTipoTrasporto_Giorno" mode="contenutoLista">prenota.Giorno</xsl:template>
    <!--xsl:template match="ListaTipoTrasporto_Giorno" mode="relazioniToEnable">["RelazioneExtraDaCaricare", "Altra relazione"]</xsl:template-->		<!-- leggi documentazione in liste2.xsl -->
	<!--xsl:template match="ListaTipoTrasporto_Giorno" mode="showRelation"><xsl:if test="../CampoDiMioPadre != '9'">true</xsl:if></xsl:template-->	<!-- leggi documentazione in liste2.xsl -->
	
	<xsl:template match="ListaTipoTrasporto_Giorno" mode="titoloRelazione">
		<a class="refresh_btn cis-button" href="javascript:void(0)">
			<xsl:attribute name="onClick">CISLoader.loadEntity({IdTpTrasporto: "<xsl:value-of select="../IdTpTrasporto"/>"}, this)</xsl:attribute>
			<i class="fa fa-refresh"/>
		</a>
		<xsl:call-template name="ButtonHref">
			<xsl:with-param name="href">/preno/prenota/HGiorno?MTipo=I&amp;MPasso=1&amp;IdTpTrasporto=<xsl:value-of select="IdTpTrasporto"/></xsl:with-param>
			<xsl:with-param name="title">Aggiungi un Giorno</xsl:with-param>
			<xsl:with-param name="fa-class">fa-plus-circle</xsl:with-param>
			<xsl:with-param name="onunload">CISLoader.loadEntity({IdTpTrasporto: "<xsl:value-of select="../IdTpTrasporto"/>"}, this.atag)</xsl:with-param>
			<xsl:with-param name="caption">Gestione Giorno</xsl:with-param>
		</xsl:call-template>
		<span class="cis-titolo-rel">Relazione Giorno</span>
		<!--xsl:call-template name="Minimizzatore"/-->
	</xsl:template>
	
	
	<xsl:template match="ListaTipoTrasporto_Giorno" mode="intestaTabella">
		<tr>
      <th/>
			<th>IdGiorno</th>
			<th>Giorno</th>
			<th>IdTpTrasporto</th>
		</tr>
	</xsl:template>
	
	
	<xsl:template match="Giorno" mode="lista">
		<xsl:attribute name="data-key">{"IdGiorno" : "<xsl:value-of select="IdGiorno"/>"}</xsl:attribute>
		
		<td>
			<xsl:call-template name="BottoneRelazioni"/>
			<xsl:call-template name="ButtonHref">
				<xsl:with-param name="href">/preno/prenota/HGiorno?MTipo=V&amp;MPasso=2&amp;IdGiorno=<xsl:value-of select="IdGiorno"/></xsl:with-param>
				<xsl:with-param name="title">Modifica un Giorno</xsl:with-param>
				<xsl:with-param name="fa-class">fa-edit</xsl:with-param>
				<!--xsl:with-param name="target">fmGiorno</xsl:with-param-->
				<xsl:with-param name="onunload">CISLoader.loadEntity({IdGiorno: "<xsl:value-of select="IdGiorno"/>"})</xsl:with-param>
				<xsl:with-param name="caption">Gestione Giorno</xsl:with-param>
			</xsl:call-template>
		</td>
			
		<td><xsl:value-of select="IdGiorno"/></td>
		<td><xsl:value-of select="Giorno"/></td>
		<td><xsl:value-of select="IdTpTrasporto"/><xsl:value-of select="TipoTrasporto/Nome"></xsl:value-of></td>
	</xsl:template>
	
</xsl:stylesheet>
