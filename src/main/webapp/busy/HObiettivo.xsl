
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"  xmlns:xs="http://www.w3.org/2001/XMLSchema" version="1.0">
<xsl:import href="${AppThemes}/atlanta-1.1.0-SNAPSHOT/xsl/theme.xsl"/>
<xsl:import href="${AppThemes}/atlanta-1.1.0-SNAPSHOT/xsl/forms2.xsl"/>
<xsl:import href="${AppXSL}/app.xsl"/>

<xsl:output method="html" indent="yes"/>

<xsl:template match="/">
	<xsl:call-template name="baseForm" >
		<xsl:with-param name="AggiungiAncora">true</xsl:with-param>	
		<xsl:with-param name="tooltips">true</xsl:with-param>		
	</xsl:call-template>
</xsl:template>

<xsl:template name="CssSpec" >
	<style>
	.cis-field-container .cis-label {
		width:150px;
	}
	/*.cis-field-container .cis-value {
		width:350px;
	}*/
	</style>
</xsl:template>

<xsl:variable name="TitoloPagina"><xsl:value-of select="$tipo_transaz"/>Obiettivo</xsl:variable>

<xsl:template  name="def_javascript">
function getTitolo(){
  return '  '  + '<xsl:value-of select="$tipo_transaz"/>' + ' <xsl:value-of select="$TitoloPagina"/>' + '   ';
}	

</xsl:template>

<xsl:template name="Titolo" >
	<xsl:value-of select="$TitoloPagina"/>
</xsl:template>

<xsl:template match="DOCUMENTO" >
    <xsl:call-template  name="Comandi" />
    
    <xsl:apply-templates select = "Obiettivo"/>
    
    <xsl:call-template name = "Errori"/>
</xsl:template>

 
<xsl:template match="Obiettivo">

	<xsl:apply-templates select="IdSoggetto" mode="CampoEnumeratoX">
		<!--Se il pdc ha la relazione aperta con il pdc figlio, posso far vedere il dato decodificato prelevandolo direttamente dall'xml-->
		<xsl:with-param name="enumerato" select="DOCUMENTO/LstSoggetto"></xsl:with-param>
		<xsl:with-param name="servlet">/preno/busy/HObiettivo</xsl:with-param>
		<xsl:with-param name="campocodice">idsoggetto</xsl:with-param> <!--Nome del campo codice della simplelist dell'enumerato -->
		<xsl:with-param name="campodesc">nome</xsl:with-param> <!--Nome del campo descrizione della simplelist dell'enumerato -->
		<xsl:with-param name="size">7</xsl:with-param>
		<!--xsl:with-param name="widthCampo">270px</xsl:with-param-->
		<xsl:with-param name="ParametriCombo">{minwidth:'340px',NumeroChar:3, AttributiInput: {size: '30',style:"min-width:200px"}, AttributiResultsTable: {style:"position:absolute; top:30px;min-width:340px"}}</xsl:with-param> <!-- si puo chiamare una funzione prima del richiamo della servlet del combo. Aggiungere: , chiamaPrima:function(dati) {dati.TpSoggetto = $('#TpSoggetto').val()} -->
		<xsl:with-param name="ParametriServlet">{MTipo:'L',GetXML:'true',MPasso:2,flSoloDatiCombo:'true'}</xsl:with-param>
		
		<xsl:with-param name="AltriCampi">[['idsoggetto', true, 'idCampoDoveSpostare']]</xsl:with-param>
		<xsl:with-param name="altricampi">['idsoggetto']</xsl:with-param> 		<!-- Versione vecchia -->
		<!--xsl:with-param name="CampiDaSpostare">['nascita_data','DtNascitaFamCar','idsoggetto','IdSoggettoAus']</xsl:with-param-->
		<xsl:with-param name="caption">IdSoggetto</xsl:with-param>
	</xsl:apply-templates>

	<!--xsl:apply-templates select="IdSoggetto" mode="CampoEnumerato">
		<xsl:with-param name="enumerato" select="/DOCUMENTO/LstSoggetto/RECORD"></xsl:with-param>
		
		<xsl:with-param name="caption">IdSoggetto</xsl:with-param>
		<xsl:with-param name="id">IdSoggetto</xsl:with-param>
	</xsl:apply-templates-->

	<!--xsl:apply-templates select="IdSoggetto" mode="jqcombo">
		<xsl:with-param name="enumerato" select="/DOCUMENTO/LstSoggetto" />
		
		<xsl:with-param name="caption">IdVisita</xsl:with-param-->
		<!--xsl:with-param name="onchange">funzioneDaChiamare</xsl:with-param-->
		<!--xsl:with-param name="showExtra">true</xsl:with-param-->
		<!--xsl:with-param name="campi_lista">['nascita_data','idsoggetto']</xsl:with-param-->
		<!--xsl:with-param name="campi_collegati">['nascita_data','DtNascitaFamCar','idsoggetto','IdSoggettoAus']</xsl:with-param-->
		<!--xsl:with-param name="campi_filtro">null</xsl:with-param> modalita' dinamica ajax: campi da aggiungere all'url, ad esempio {Cognome: '_text_', IdAzienda: 'IdAzienda'} aggiunge "&Cognome=testocombo&IdAzienda=contenuto del campo con id=IdAzienda"-->
		<!--xsl:with-param name="url">/inspect/isp/HAllegati</xsl:with-param-->
		<!--xsl:with-param name="multiselect">true</xsl:with-param-->
	<!--/xsl:apply-templates-->


	<xsl:apply-templates select="TpOccup" mode="CampoEnumerato">
		<xsl:with-param name="enumerato" select="/DOCUMENTO/LstTipoOccupazione/RECORD"></xsl:with-param>		
		<xsl:with-param name="caption">Tipo</xsl:with-param>
		<xsl:with-param name="noVuoto">true</xsl:with-param>
		<xsl:with-param name="id">TpOccup</xsl:with-param>
	</xsl:apply-templates>

	<!--xsl:apply-templates select="TpOccup" mode="jqcombo">
		<xsl:with-param name="enumerato" select="/DOCUMENTO/LstTipoOccupazione" />
		
		<xsl:with-param name="caption">IdVisita</xsl:with-param-->
		<!--xsl:with-param name="onchange">funzioneDaChiamare</xsl:with-param-->
		<!--xsl:with-param name="showExtra">true</xsl:with-param-->
		<!--xsl:with-param name="campi_lista">['nascita_data','idsoggetto']</xsl:with-param-->
		<!--xsl:with-param name="campi_collegati">['nascita_data','DtNascitaFamCar','idsoggetto','IdSoggettoAus']</xsl:with-param-->
		<!--xsl:with-param name="campi_filtro">null</xsl:with-param> modalita' dinamica ajax: campi da aggiungere all'url, ad esempio {Cognome: '_text_', IdAzienda: 'IdAzienda'} aggiunge "&Cognome=testocombo&IdAzienda=contenuto del campo con id=IdAzienda"-->
		<!--xsl:with-param name="url">/inspect/isp/HAllegati</xsl:with-param-->
		<!--xsl:with-param name="multiselect">true</xsl:with-param-->
	<!--/xsl:apply-templates-->

	<xsl:apply-templates select="IdObiettivo" mode="CampoIntero">
		<xsl:with-param name="size">10</xsl:with-param>
		<xsl:with-param name="chiave">true</xsl:with-param><xsl:with-param name="serial">true</xsl:with-param>
		<xsl:with-param name="caption">IdObiettivo</xsl:with-param>
	</xsl:apply-templates>

	<xsl:apply-templates select="DtInizioObiettivo" mode="CampoData">
		<xsl:with-param name="size">15</xsl:with-param>
		<xsl:with-param name="bOra">true</xsl:with-param>		
		<xsl:with-param name="caption">DtInizioObiettivo</xsl:with-param>
	</xsl:apply-templates>

	<xsl:apply-templates select="DescObiettivo" mode="CampoTestoArea">
		<xsl:with-param name="cols">45</xsl:with-param>
		<xsl:with-param name="rows">3</xsl:with-param>		
		<xsl:with-param name="caption">DescObiettivo</xsl:with-param>
	</xsl:apply-templates>

	<xsl:apply-templates select="DtScadenzaObiettivo" mode="CampoData">
		<xsl:with-param name="size">15</xsl:with-param>
		
		<xsl:with-param name="caption">DtScadenzaObiettivo</xsl:with-param>
	</xsl:apply-templates>

	<xsl:apply-templates select="MinPrevisti" mode="CampoIntero">
		<xsl:with-param name="size">10</xsl:with-param>
		
		<xsl:with-param name="caption">MinPrevisti</xsl:with-param>
	</xsl:apply-templates>

	<xsl:apply-templates select="DtFineObiettivo" mode="CampoData">
		<xsl:with-param name="size">15</xsl:with-param>
		<xsl:with-param name="bOra">true</xsl:with-param>		
		<xsl:with-param name="caption">DtFineObiettivo</xsl:with-param>
	</xsl:apply-templates>

	<xsl:apply-templates select="NotaObiettivo" mode="CampoTestoArea">
		<xsl:with-param name="cols">45</xsl:with-param>
		<xsl:with-param name="rows">5</xsl:with-param>
		
		<xsl:with-param name="caption">NotaObiettivo</xsl:with-param>
	</xsl:apply-templates>

	<xsl:apply-templates select="PercCompletamento" mode="CampoMoney">
		<xsl:with-param name="size">10</xsl:with-param>
		
		<xsl:with-param name="caption">PercCompletamento</xsl:with-param>
	</xsl:apply-templates>

</xsl:template>
</xsl:stylesheet>
