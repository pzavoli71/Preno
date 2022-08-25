<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="3.0">
    <xsl:import href="${AppThemes}/atlanta-1.1.0-SNAPSHOT/xsl/pagutente.xsl"/>
    <xsl:import href="${AppXSL}/app.xsl"/>
    
	<xsl:output method="html" encoding="utf-8" indent="yes" />
	
	<xsl:variable name="lstMenu"><xsl:value-of select="$app_context"></xsl:value-of>/abilitazione/Menu.xml</xsl:variable>

	<xsl:template match="/">
		<xsl:call-template name="PagUtente" />
	</xsl:template>
	
	<xsl:template name="PannelloFooter" ></xsl:template>	
	<xsl:template name="PannelloCss">
		<meta name="mobile-web-app-capable" content="yes"/>
		<meta name="apple-mobile-web-app-capable" content="yes"/>
	</xsl:template>
	
	<!-- Gestione base della visualizzazione responsive -->
	<xsl:template name="PagUtente">
		<xsl:text disable-output-escaping='yes'>&lt;!DOCTYPE html&gt;</xsl:text>
		<html>
			<head>
				<title><xsl:value-of select="$app_display_name" /></title>
			  	<link rel="shortcut icon" href="{$app_images}/app_icon.png" type="image/png" />
	      		<link rel="icon" href="{$app_images}/app_icon.png" type="image/png" />
				<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
				<meta name="viewport" content="width=device-width, initial-scale=1.0" />
				<!-- 
					ATTENZIONE: l'ordine di importazione dei CSS e' fondamentale!
					Dal piu' generico (in alto) 
					al piu' specifico (in basso).
				 -->
				<xsl:call-template name="MenuCss" />
				<xsl:call-template name="CssStd" />
				<xsl:call-template name="PannelloCss" />
	            <xsl:call-template name="JSStd" />
				<xsl:call-template name="PannelloScript" />
			</head>
	 
			<body id="sfondo" class="screen">
				<div class="header">
			      	<div class="intest">
						<img class="stemma" src="{$app_images}/stemma2.png"/>
				    </div>
				    <a title="Torna alla Home Page" href="{$app_context}"><h1><xsl:call-template name="Titolo"/></h1></a>
				    <div class="NomeUtente">
				    	<h2 style="display:inline-block">Benvenuto</h2>&#xA0;<span>
					    	<xsl:if test="/DOCUMENTO/SESSIONE/ProfiloUtente/NomePF != ''">
					    		<xsl:value-of select="/DOCUMENTO/SESSIONE/ProfiloUtente/NomePF"/>
					    	</xsl:if>
					    	<xsl:if test="count(/DOCUMENTO/SESSIONE/ProfiloUtente/NomePF) = 0">
					    		<xsl:value-of select="/DOCUMENTO/SESSIONE/ProfiloUtente/DsUtente"/>
					    	</xsl:if>
				    	</span>
				    	<div class="NomeUtenteDati">
					    	<xsl:if test="/DOCUMENTO/SESSIONE/ProfiloUtente/NomeRuolo != ''">
					    		<strong>
					    			<br />
					    			<xsl:value-of select="/DOCUMENTO/SESSIONE/ProfiloUtente/NomeRuolo"/>
					    		</strong>
					    	</xsl:if>
					    	<xsl:if test="/DOCUMENTO/SESSIONE/ProfiloUtente/Codoe &gt; '0'">
					    		<br />
					    		<xsl:value-of select="/DOCUMENTO/SESSIONE/ProfiloUtente/NomeSoggettoOE"/>
					    		<xsl:value-of select="/DOCUMENTO/SESSIONE/ProfiloUtente/NomeOE"/>
					    	</xsl:if>
				    	</div>
				    	<div class="NomeUtenteApri" onclick="fnNomeUtenteApri()">
				    		<i class="fa fa-arrow-circle-down"></i>
				    	</div>			    				    				    	
				    </div>
				    <div class="hamburger" onclick="fnBarraMenuToggle()">
				    	<i class="fa fa-bars"/>
				    </div>
				</div>
				
				<xsl:call-template name="PannelloMenu" />
				
				<div class="centrale" id="divcentrale">
					<div id="tabmenucentrale"></div>
					<xsl:call-template name="PannelloCentrale" />
				</div>
	
				<div class="footer">
					<xsl:call-template name="PannelloFooter" />
				</div>
			</body>
		</html>
	
	</xsl:template>
	
</xsl:stylesheet>