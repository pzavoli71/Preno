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
	/*.cis-field-container .ValoreAttributo {
		width:350px;
	}*/
	
	</style>
</xsl:template>

<!-- ============================================ -->
<!--     Titolo            						  -->
<!-- ============================================ -->
<xsl:template name="Titolo" >
	<xsl:value-of select="$TitoloPagina"/>
</xsl:template>
<xsl:variable name="TitoloPagina">DomandaQuiz</xsl:variable>

<xsl:template  name="def_javascript">
function getTitolo(){
  return '  '  + '<xsl:value-of select="$tipo_transaz"/>' + ' <xsl:value-of select="$TitoloPagina"/>' + '   ';
}



</xsl:template>



<xsl:template match="DOCUMENTO" >
    <xsl:call-template  name="Comandi" />
    
    <xsl:apply-templates select = "DomandaQuiz"/>
    
</xsl:template>

 
<xsl:template match="DomandaQuiz">

	<xsl:apply-templates select="/DOCUMENTO/DomandaQuiz/IdQuiz" mode="CampoEnumeratoX">
		<!--Se il pdc ha la relazione aperta con il pdc figlio, posso far vedere il dato decodificato prelevandolo direttamente dall'xml-->
		<xsl:with-param name="enumerato" select="DOCUMENTO/LstQuiz"></xsl:with-param>
		<xsl:with-param name="servlet">/preno/patente/HLstDomandaQuiz</xsl:with-param>
		<xsl:with-param name="campocodice">idquiz</xsl:with-param> <!--Nome del campo codice della simplelist dell'enumerato -->
		<xsl:with-param name="campodesc">nome</xsl:with-param> <!--Nome del campo descrizione della simplelist dell'enumerato -->
		<xsl:with-param name="size">7</xsl:with-param>
		<!--xsl:with-param name="widthCampo">270px</xsl:with-param-->
		<xsl:with-param name="ParametriCombo">{minwidth:'340px',NumeroChar:3, SizeCombo:30}</xsl:with-param> <!-- si puo chiamare una funzione prima del richiamo della servlet del combo. Aggiungere: , chiamaPrima:function(dati) {dati.TpSoggetto = $('#TpSoggetto').val()} -->
		<xsl:with-param name="ParametriServlet">{MTipo:'L',GetXML:'true',MPasso:2,flSoloDatiCombo:'true'}</xsl:with-param>
		
		<!--xsl:with-param name="AltriCampi">[['colonnaEstrattaMinuscola', true, 'idCampoDoveSpostare']]</xsl:with-param-->
		<!--xsl:with-param name="altricampi">['nascita_data','idsoggetto']</xsl:with-param--> 		<!-- Versione vecchia -->
		<!--xsl:with-param name="CampiDaSpostare">['nascita_data','DtNascitaFamCar','idsoggetto','IdSoggettoAus']</xsl:with-param-->
		<xsl:with-param name="caption">IdQuiz</xsl:with-param>
	</xsl:apply-templates>

	<!--xsl:apply-templates select="/DOCUMENTO/DomandaQuiz/IdQuiz" mode="CampoEnumerato">
		<xsl:with-param name="enumerato" select="/DOCUMENTO/LstQuiz/RECORD"></xsl:with-param>
		
		<xsl:with-param name="caption">IdQuiz</xsl:with-param>
		<xsl:with-param name="id">IdQuiz</xsl:with-param>
	</xsl:apply-templates-->

	<!--xsl:apply-templates select="/DOCUMENTO/DomandaQuiz/IdQuiz" mode="jqcombo">
		<xsl:with-param name="enumerato" select="/DOCUMENTO/LstQuiz" />
		
		<xsl:with-param name="caption">IdVisita</xsl:with-param-->
		<!--xsl:with-param name="onchange">funzioneDaChiamare</xsl:with-param-->
		<!--xsl:with-param name="showExtra">true</xsl:with-param-->
		<!--xsl:with-param name="campi_lista">['nascita_data','idsoggetto']</xsl:with-param-->
		<!--xsl:with-param name="campi_collegati">['nascita_data','DtNascitaFamCar','idsoggetto','IdSoggettoAus']</xsl:with-param-->
		<!--xsl:with-param name="campi_filtro">null</xsl:with-param> modalita' dinamica ajax: campi da aggiungere all'url, ad esempio {Cognome: '_text_', IdAzienda: 'IdAzienda'} aggiunge "&Cognome=testocombo&IdAzienda=contenuto del campo con id=IdAzienda"-->
		<!--xsl:with-param name="url">/inspect/isp/HAllegati</xsl:with-param-->
		<!--xsl:with-param name="multiselect">true</xsl:with-param-->
	<!--/xsl:apply-templates-->

	<xsl:apply-templates select="/DOCUMENTO/DomandaQuiz/IdDomanda" mode="CampoEnumeratoX">
		<!--Se il pdc ha la relazione aperta con il pdc figlio, posso far vedere il dato decodificato prelevandolo direttamente dall'xml-->
		<xsl:with-param name="enumerato" select="DOCUMENTO/LstDomanda"></xsl:with-param>
		<xsl:with-param name="servlet">/preno/patente/HLstDomandaQuiz</xsl:with-param>
		<xsl:with-param name="campocodice">iddomanda</xsl:with-param> <!--Nome del campo codice della simplelist dell'enumerato -->
		<xsl:with-param name="campodesc">nome</xsl:with-param> <!--Nome del campo descrizione della simplelist dell'enumerato -->
		<xsl:with-param name="size">7</xsl:with-param>
		<!--xsl:with-param name="widthCampo">270px</xsl:with-param-->
		<xsl:with-param name="ParametriCombo">{minwidth:'340px',NumeroChar:3, SizeCombo:30}</xsl:with-param> <!-- si puo chiamare una funzione prima del richiamo della servlet del combo. Aggiungere: , chiamaPrima:function(dati) {dati.TpSoggetto = $('#TpSoggetto').val()} -->
		<xsl:with-param name="ParametriServlet">{MTipo:'L',GetXML:'true',MPasso:2,flSoloDatiCombo:'true'}</xsl:with-param>
		
		<!--xsl:with-param name="AltriCampi">[['colonnaEstrattaMinuscola', true, 'idCampoDoveSpostare']]</xsl:with-param-->
		<!--xsl:with-param name="altricampi">['nascita_data','idsoggetto']</xsl:with-param--> 		<!-- Versione vecchia -->
		<!--xsl:with-param name="CampiDaSpostare">['nascita_data','DtNascitaFamCar','idsoggetto','IdSoggettoAus']</xsl:with-param-->
		<xsl:with-param name="caption">IdDomanda</xsl:with-param>
	</xsl:apply-templates>

	<!--xsl:apply-templates select="/DOCUMENTO/DomandaQuiz/IdDomanda" mode="CampoEnumerato">
		<xsl:with-param name="enumerato" select="/DOCUMENTO/LstDomanda/RECORD"></xsl:with-param>
		
		<xsl:with-param name="caption">IdDomanda</xsl:with-param>
		<xsl:with-param name="id">IdDomanda</xsl:with-param>
	</xsl:apply-templates-->

	<!--xsl:apply-templates select="/DOCUMENTO/DomandaQuiz/IdDomanda" mode="jqcombo">
		<xsl:with-param name="enumerato" select="/DOCUMENTO/LstDomanda" />
		
		<xsl:with-param name="caption">IdVisita</xsl:with-param-->
		<!--xsl:with-param name="onchange">funzioneDaChiamare</xsl:with-param-->
		<!--xsl:with-param name="showExtra">true</xsl:with-param-->
		<!--xsl:with-param name="campi_lista">['nascita_data','idsoggetto']</xsl:with-param-->
		<!--xsl:with-param name="campi_collegati">['nascita_data','DtNascitaFamCar','idsoggetto','IdSoggettoAus']</xsl:with-param-->
		<!--xsl:with-param name="campi_filtro">null</xsl:with-param> modalita' dinamica ajax: campi da aggiungere all'url, ad esempio {Cognome: '_text_', IdAzienda: 'IdAzienda'} aggiunge "&Cognome=testocombo&IdAzienda=contenuto del campo con id=IdAzienda"-->
		<!--xsl:with-param name="url">/inspect/isp/HAllegati</xsl:with-param-->
		<!--xsl:with-param name="multiselect">true</xsl:with-param-->
	<!--/xsl:apply-templates-->

	<xsl:apply-templates select="/DOCUMENTO/DomandaQuiz/IdDomandaTest" mode="CampoIntero">
		<xsl:with-param name="size">10</xsl:with-param>
		<xsl:with-param name="chiave">true</xsl:with-param><xsl:with-param name="serial">true</xsl:with-param>
		<xsl:with-param name="caption">IdDomandaTest</xsl:with-param>
	</xsl:apply-templates>

	<xsl:apply-templates select="/DOCUMENTO/DomandaQuiz/IdCapitolo" mode="CampoIntero">
		<xsl:with-param name="size">10</xsl:with-param>
		
		<xsl:with-param name="caption">IdCapitolo</xsl:with-param>
	</xsl:apply-templates>

	<xsl:apply-templates select="/DOCUMENTO/DomandaQuiz/IdDomanda" mode="CampoIntero">
		<xsl:with-param name="size">10</xsl:with-param>
		
		<xsl:with-param name="caption">IdDomanda</xsl:with-param>
	</xsl:apply-templates>

</xsl:template>
</xsl:stylesheet>
