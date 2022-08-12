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
	return "/preno/prenota/HLstGiorno?MPasso=1";
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
<xsl:variable name="TitoloPagina">Mese</xsl:variable>

<!-- ============================================ -->
<!-- Filtro 										 -->
<!-- ============================================ -->
<xsl:template name="LeftFilter">
		<xsl:apply-templates select="/DOCUMENTO/IdTpTrasporto" mode="nodoattributo"/>
		<xsl:apply-templates select="/DOCUMENTO/Anno" mode="nodoattributo"/>
		<xsl:apply-templates select="/DOCUMENTO/Mese" mode="nodoattributo"/>
	
</xsl:template>
	

<!-- ============================================ -->
<!-- Attributi Filtro 							 -->
<!-- ============================================ -->
	
<xsl:template match="Anno" mode="nodoattributo">

	<xsl:apply-templates select="." mode="CampoIntero">
		<xsl:with-param name="size">5</xsl:with-param>
		<xsl:with-param name="caption">Anno</xsl:with-param>
		<xsl:with-param name="onchange">$('form').submit();</xsl:with-param>
	</xsl:apply-templates>

</xsl:template>

<xsl:template match="Mese" mode="nodoattributo">

	<xsl:apply-templates select="." mode="CampoIntero">
		<xsl:with-param name="size">5</xsl:with-param>
		<xsl:with-param name="caption">Mese</xsl:with-param>
		<xsl:with-param name="onchange">$('form').submit();</xsl:with-param>
	</xsl:apply-templates>

</xsl:template>

<xsl:template match="IdTpTrasporto" mode="nodoattributo">

	<xsl:apply-templates select="." mode="CampoEnumerato">
	<xsl:with-param name="enumerato" select="/DOCUMENTO/LstTipoTrasporto/RECORD"></xsl:with-param>
	<xsl:with-param name="caption">Trasporto</xsl:with-param>
	<xsl:with-param name="anche_codice">true</xsl:with-param>
	<xsl:with-param name="noVuoto">true</xsl:with-param>
	<xsl:with-param name="onchange">$('form').submit();</xsl:with-param>
	</xsl:apply-templates>

</xsl:template>

		
	<!-- ============================================ -->
	<!-- Oggetto principale Lista 					 -->
	<!-- ============================================ -->
	<xsl:template name="contenutoLista">prenota.Giorno</xsl:template>
	
	<xsl:template name="IntestazioneLista">
		<a href="javascript:void(0)" onclick="document.location.reload()" class="refresh_btn cis-button"><i class="fa fa-refresh"/></a>
		<xsl:call-template name="ButtonHref">
			<xsl:with-param name="href">/preno/prenota/HGiorno?MTipo=I&amp;MPasso=1</xsl:with-param>
			<xsl:with-param name="title">Aggiungi un Giorno</xsl:with-param>
			<xsl:with-param name="fa-class">fa-plus-circle</xsl:with-param>
			<xsl:with-param name="onunload">document.location.reload()</xsl:with-param>
			<xsl:with-param name="caption">Gestione Giorno</xsl:with-param>
			<xsl:with-param name="text">Giorno</xsl:with-param>
		</xsl:call-template>
	</xsl:template>


	<xsl:template name="IntestaTabella">
		<tr>
			<th/>
			<th>IdTpTrasporto</th>
			<th>IdGiorno</th>
			<th>Giorno</th>
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
				<xsl:with-param name="onunload">CISLoader.loadEntity({IdGiorno: "<xsl:value-of select="IdGiorno"/>"})</xsl:with-param>
				<xsl:with-param name="caption">Gestione Giorno</xsl:with-param>
			</xsl:call-template>
		</td>
		<td><xsl:value-of select="IdTpTrasporto"/><xsl:value-of select="TipoTrasporto/Nome"/></td>
		<td><xsl:value-of select="IdGiorno"/></td>
		<td><xsl:value-of select="Giorno"/></td>
	</xsl:template>
	
	
	
	
	<!-- ============================================ -->
	<!-- Relazione SoggAR 	 -->
	<!-- ============================================ -->
	<xsl:template match="ListaGiorno_SoggAR" mode="contenutoLista">prenota.SoggAR</xsl:template>
    <!--xsl:template match="ListaGiorno_SoggAR" mode="relazioniToEnable">["RelazioneExtraDaCaricare", "Altra relazione"]</xsl:template-->		<!-- leggi documentazione in liste2.xsl -->
	<!--xsl:template match="ListaGiorno_SoggAR" mode="showRelation"><xsl:if test="../CampoDiMioPadre != '9'">true</xsl:if></xsl:template-->	<!-- leggi documentazione in liste2.xsl -->
	
	<xsl:template match="ListaGiorno_SoggAR" mode="titoloRelazione">
		<a class="refresh_btn cis-button" href="javascript:void(0)">
			<xsl:attribute name="onClick">CISLoader.loadEntity({IdGiorno: "<xsl:value-of select="../IdGiorno"/>"}, this)</xsl:attribute>
			<i class="fa fa-refresh"/>
		</a>
		<xsl:call-template name="ButtonHref">
			<xsl:with-param name="href">/preno/prenota/HSoggAR?MTipo=I&amp;MPasso=1&amp;IdGiorno=<xsl:value-of select="IdGiorno"/></xsl:with-param>
			<xsl:with-param name="title">Aggiungi un SoggAR</xsl:with-param>
			<xsl:with-param name="fa-class">fa-plus-circle</xsl:with-param>
			<xsl:with-param name="onunload">CISLoader.loadEntity({IdGiorno: "<xsl:value-of select="../IdGiorno"/>"}, this.atag)</xsl:with-param>
			<xsl:with-param name="caption">Gestione SoggAR</xsl:with-param>
		</xsl:call-template>
		<span class="cis-titolo-rel">Relazione SoggAR</span>
		<!--xsl:call-template name="Minimizzatore"/-->
	</xsl:template>
	
	
	<xsl:template match="ListaGiorno_SoggAR" mode="intestaTabella">
		<tr>
      <th/>
			<th>IdSoggAndata</th>
			<th>AR</th>
			<th>IdSoggetto</th>
			<th>IdGiorno</th>
		</tr>
	</xsl:template>
	
	
	<xsl:template match="SoggAR" mode="lista">
		<xsl:attribute name="data-key">{"IdSoggAndata" : "<xsl:value-of select="IdSoggAndata"/>"}</xsl:attribute>
		
		<td>
			<xsl:call-template name="BottoneRelazioni"/>
			<xsl:call-template name="ButtonHref">
				<xsl:with-param name="href">/preno/prenota/HSoggAR?MTipo=V&amp;MPasso=2&amp;IdSoggAndata=<xsl:value-of select="IdSoggAndata"/></xsl:with-param>
				<xsl:with-param name="title">Modifica un SoggAR</xsl:with-param>
				<xsl:with-param name="fa-class">fa-edit</xsl:with-param>
				<!--xsl:with-param name="target">fmSoggAR</xsl:with-param-->
				<xsl:with-param name="onunload">CISLoader.loadEntity({IdSoggAndata: "<xsl:value-of select="IdSoggAndata"/>"})</xsl:with-param>
				<xsl:with-param name="caption">Gestione SoggAR</xsl:with-param>
			</xsl:call-template>
		</td>
			
		<td><xsl:value-of select="IdSoggAndata"/></td>
		<td><xsl:value-of select="AR"/></td>
		<td><xsl:value-of select="IdSoggetto"/><xsl:value-of select="Soggetto/Nome"></xsl:value-of></td>
		<td><xsl:value-of select="IdGiorno"/><xsl:value-of select="Giorno/Nome"></xsl:value-of></td>
	</xsl:template>
	

<xsl:template name="tmlDocumentoListe">
    <style>
		.cis-filter .cis-fields-inline > .cis-field-container {
			display:inline-block;
		}    
    
    	.tabcalendario  tr  td{
    		padding:5px;
    		border:2px solid black;
    		position:relative;
    		vertical-align:top;
    	}
    	.tabcalendario div.tabcella {
    		min-height:3em;
    		/*border:1px solid #dbdbdb;
    		border-radius:2px;*/
    		font-size:0.7em;
			/*box-shadow: 2px 2px 5px #726262;*/
			position:relative;
			height:100%;
			top:0px;
    	}
    	.cis-button {
    		padding-left: 2px;
    		padding-right:2px;
    		margin:1px;
    	}
    	.numgiorno {
    		font-size:10px;
    		font-weight:bold;
    		background-color:red;
    		color:white;
    		padding:3px;
    		position:relative;
    		border-radius:2px;
    		display:block;
    		text-align:center;
    	}
    	b {
    		margin-left:5px;
    	}
    	.persona {
    		padding:2px;
    		border:1px solid grey;
    		border-radius:2px;
    		display:inline-block;
    		font-weight:bold;
    	}
    	p {
    		text-align:center;
    		margin-bottom:0px;
    		margin-top:0px;
    	}
    	.bordato {
    		padding:3px;
    		border:1px solid black;
    		position:relative;  
    		min-height:50px;  
    		background-color: #dddddd;	
    	}
    	.noback {
    		background-color:#ffffff00;
    		color:black;
    		text-decoration:underline !important;
    	}
    	.noback:hover {
    		background-color:#ffffff;
    	}
    </style>
    <xsl:call-template name="Errori"/>
    <div class="TitoloForm"><xsl:call-template name="Titolo"/></div>
    <xsl:call-template name="Filtro"/>
    <div class="{$body-list-class}">
    	<table class="tabcalendario">
    	<tr>
    		<th>Lun</th>
    		<th>Mar</th>
    		<th>Mer</th>
    		<th>Gio</th>
    		<th>Ven</th>
    		<th>Sab</th>
    		<th>Dom</th>
    	</tr>
    	<xsl:call-template name="Settimana">
    		<xsl:with-param name="NumSett">1</xsl:with-param>
    	</xsl:call-template>
    	</table>
    </div>
</xsl:template>
	
<xsl:template name="Settimana">
	<xsl:param name="NumSett"/>
    	<tr>
    		<td><xsl:apply-templates select="/DOCUMENTO/Giorni/G[@sett = $NumSett][@gset = 1]"/></td>
    		<td><xsl:apply-templates select="/DOCUMENTO/Giorni/G[@sett = $NumSett][@gset = 2]"/></td>
    		<td><xsl:apply-templates select="/DOCUMENTO/Giorni/G[@sett = $NumSett][@gset = 3]"/></td>
    		<td><xsl:apply-templates select="/DOCUMENTO/Giorni/G[@sett = $NumSett][@gset = 4]"/></td>
    		<td><xsl:apply-templates select="/DOCUMENTO/Giorni/G[@sett = $NumSett][@gset = 5]"/></td>
    		<td><xsl:apply-templates select="/DOCUMENTO/Giorni/G[@sett = $NumSett][@gset = 6]"/></td>
    		<td><xsl:apply-templates select="/DOCUMENTO/Giorni/G[@sett = $NumSett][@gset = 7]"/></td>
    	</tr>

	<xsl:if test="count(/DOCUMENTO/Giorni/G[@sett = $NumSett + 1]) &gt; 0">
    	<xsl:call-template name="Settimana">
    		<xsl:with-param name="NumSett"><xsl:value-of select="$NumSett + 1"/></xsl:with-param>
    	</xsl:call-template>
	</xsl:if>
</xsl:template>

<xsl:template match="G">
<xsl:variable name="num"><xsl:value-of select="@num"/></xsl:variable>
	<div class="numgiorno"><xsl:value-of select="@num"/></div>
<div class="tabcella">
	<p>Andata</p>	
	<div style="text-align:center;position:relative">
		<xsl:call-template name="ButtonHref">
			<xsl:with-param name="href">/preno/prenota/HSoggAR?MTipo=I&amp;MPasso=1&amp;Anno=<xsl:value-of select="/DOCUMENTO/Anno"/>&amp;Mese=<xsl:value-of select="/DOCUMENTO/Mese"/>&amp;IdTpTrasporto=<xsl:value-of select="/DOCUMENTO/IdTpTrasporto"/>&amp;Giorno=<xsl:value-of select="$num"/>&amp;TpSoggetto=1&amp;AR=1</xsl:with-param>
			<xsl:with-param name="title">Vado io</xsl:with-param>
			<xsl:with-param name="fa-class">fa-plus-circle</xsl:with-param>
			<xsl:with-param name="onunload">document.location.reload()</xsl:with-param>
			<xsl:with-param name="caption">Gestione Autista</xsl:with-param>
			<xsl:with-param name="text">Autista</xsl:with-param>
		</xsl:call-template>		
		<xsl:call-template name="ButtonHref">
			<xsl:with-param name="href">/preno/prenota/HSoggAR?MTipo=I&amp;MPasso=1&amp;Anno=<xsl:value-of select="/DOCUMENTO/Anno"/>&amp;Mese=<xsl:value-of select="/DOCUMENTO/Mese"/>&amp;IdTpTrasporto=<xsl:value-of select="/DOCUMENTO/IdTpTrasporto"/>&amp;Giorno=<xsl:value-of select="$num"/>&amp;TpSoggetto=2&amp;AR=1</xsl:with-param>
			<xsl:with-param name="title">Segna un ragazzo</xsl:with-param>
			<xsl:with-param name="fa-class">fa-plus-circle</xsl:with-param>
			<xsl:with-param name="onunload">document.location.reload()</xsl:with-param>
			<xsl:with-param name="caption">Gestione Ragazzo</xsl:with-param>
			<xsl:with-param name="text">Ragazzo</xsl:with-param>
		</xsl:call-template>
	</div>
	<xsl:call-template name="Giorno">
		<xsl:with-param name="num"><xsl:value-of select="$num"/></xsl:with-param>
		<xsl:with-param name="ar">1</xsl:with-param>
	</xsl:call-template>	
	<p>Ritorno</p>	
	<div style="text-align:center;position:relative">
		<xsl:call-template name="ButtonHref">
			<xsl:with-param name="href">/preno/prenota/HSoggAR?MTipo=I&amp;MPasso=1&amp;Anno=<xsl:value-of select="/DOCUMENTO/Anno"/>&amp;Mese=<xsl:value-of select="/DOCUMENTO/Mese"/>&amp;IdTpTrasporto=<xsl:value-of select="/DOCUMENTO/IdTpTrasporto"/>&amp;Giorno=<xsl:value-of select="$num"/>&amp;TpSoggetto=1&amp;AR=2</xsl:with-param>
			<xsl:with-param name="title">Vado io</xsl:with-param>
			<xsl:with-param name="fa-class">fa-plus-circle</xsl:with-param>
			<xsl:with-param name="onunload">document.location.reload()</xsl:with-param>
			<xsl:with-param name="caption">Gestione Autista</xsl:with-param>
			<xsl:with-param name="text">Autista</xsl:with-param>
		</xsl:call-template>		
		<xsl:call-template name="ButtonHref">
			<xsl:with-param name="href">/preno/prenota/HSoggAR?MTipo=I&amp;MPasso=1&amp;Anno=<xsl:value-of select="/DOCUMENTO/Anno"/>&amp;Mese=<xsl:value-of select="/DOCUMENTO/Mese"/>&amp;IdTpTrasporto=<xsl:value-of select="/DOCUMENTO/IdTpTrasporto"/>&amp;Giorno=<xsl:value-of select="$num"/>&amp;TpSoggetto=2&amp;AR=2</xsl:with-param>
			<xsl:with-param name="title">Segna un ragazzo</xsl:with-param>
			<xsl:with-param name="fa-class">fa-plus-circle</xsl:with-param>
			<xsl:with-param name="onunload">document.location.reload()</xsl:with-param>
			<xsl:with-param name="caption">Gestione Ragazzo</xsl:with-param>
			<xsl:with-param name="text">Ragazzo</xsl:with-param>
		</xsl:call-template>
	</div>
	<xsl:call-template name="Giorno">
		<xsl:with-param name="num"><xsl:value-of select="$num"/></xsl:with-param>
		<xsl:with-param name="ar">2</xsl:with-param>
	</xsl:call-template>
</div>
</xsl:template>

<xsl:template name="Giorno">
<xsl:param name="num"/>
<xsl:param name="ar"/>
	<xsl:variable name="tptrasporto"><xsl:value-of select="IdTpTrasporto"/></xsl:variable>
	<div class="bordato">
	<xsl:for-each select="/DOCUMENTO/LstElementi/Giorno[NumGiorno = $num]/ListaGiorno_SoggAR/SoggAR[AR = $ar or AR = 3]/Soggetto[bRagazzo = 'false']">
		<xsl:call-template name="ButtonHref">
			<xsl:with-param name="href">/preno/prenota/HSoggAR?MTipo=V&amp;MPasso=2&amp;Anno=<xsl:value-of select="/DOCUMENTO/Anno"/>&amp;Mese=<xsl:value-of select="/DOCUMENTO/Mese"/>&amp;IdTpTrasporto=<xsl:value-of select="/DOCUMENTO/IdTpTrasporto"/>&amp;Giorno=<xsl:value-of select="$num"/>&amp;TpSoggetto=1&amp;IdSoggAndata=<xsl:value-of select="../IdSoggAndata"/></xsl:with-param>
			<!-- xsl:with-param name="title">Autista</xsl:with-param-->
			<xsl:with-param name="fa-class">fa-automobile</xsl:with-param>
			<xsl:with-param name="onunload">document.location.reload()</xsl:with-param>
			<xsl:with-param name="caption">Gestione Autista</xsl:with-param>
			<xsl:with-param name="text"><xsl:value-of select="NomeSoggetto"/></xsl:with-param>
			<xsl:with-param name="extraClass">noback</xsl:with-param>
		</xsl:call-template>			
	</xsl:for-each><br/>
	<!-- /div>
	<div class="bordato"-->
	<xsl:for-each select="/DOCUMENTO/LstElementi/Giorno[NumGiorno = $num]/ListaGiorno_SoggAR/SoggAR[AR = $ar or AR = 3]/Soggetto[bRagazzo = 'true']">
		<xsl:call-template name="ButtonHref">
			<xsl:with-param name="href">/preno/prenota/HSoggAR?MTipo=V&amp;MPasso=2&amp;Anno=<xsl:value-of select="/DOCUMENTO/Anno"/>&amp;Mese=<xsl:value-of select="/DOCUMENTO/Mese"/>&amp;IdTpTrasporto=<xsl:value-of select="/DOCUMENTO/IdTpTrasporto"/>&amp;Giorno=<xsl:value-of select="$num"/>&amp;TpSoggetto=2&amp;IdSoggAndata=<xsl:value-of select="../IdSoggAndata"/></xsl:with-param>
			<!-- xsl:with-param name="title">Ragazzo</xsl:with-param-->
			<xsl:with-param name="fa-class">fa-soccer-ball-o</xsl:with-param>
			<xsl:with-param name="onunload">document.location.reload()</xsl:with-param>
			<xsl:with-param name="caption">Gestione Ragazzo</xsl:with-param>
			<xsl:with-param name="text"><xsl:value-of select="NomeSoggetto"/></xsl:with-param>
			<xsl:with-param name="extraClass">noback</xsl:with-param>
		</xsl:call-template>			
	</xsl:for-each>
	</div>	
</xsl:template>
</xsl:stylesheet>
