<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="3.0">
	<xsl:output method="html" indent="yes"/>

	<xsl:template name="user_css_js" />
	<xsl:template name="std_css_js">
		<style>
			* {
				box-sizing:border-box;
				user-select:none;
				-ms-user-select: none;
				-moz-user-select: none;
				-webkit-user-select: none;
			}
			html {
				height:100%;
			}
			body {
				font-family: opensans, Verdana, Helvetica, sans-serif;
				margin:0;
				color:#333;
				font-size:14px;
				display:-webkit-flex;
				-webkit-align-items:center;
				display:flex;
				align-items:center;
				height:100%;
				background: radial-gradient(circle, rgba(255,255,255,1) 0%, #ccc 150%);
				overflow-x:hidden;
			}
			input, textarea, select, label { 
				font-family:opensans, Verdana, Helvetica, sans-serif!important; 
			}
			input[type='radio'] {
				outline:0;
			}
			::placeholder {
				color:#AAAAAA;
			 	opacity: 1;
			}
			:-ms-input-placeholder {
				color:#AAAAAA;
			}
			::-ms-input-placeholder {
				display:none;
			}
			::-ms-clear {
				display: none;
			}
			.login-form {
				min-width: 350px;
				margin:auto;
			}
			.appName {
				font-size:3em;
				font-style:normal;
				font-weight:bold;
				text-shadow:0px 0px 8px #aaaaaa;
				text-align:center;
			}
			.appDesc {
				font-style:italic;
				font-weight:bold;
				text-align:center;
			}
			.appName,
			.appDesc,
			fieldset .element:not(:last-child),
			fieldset,
			form>.element {
				margin-bottom:8px;
				outline:none;
				width:100%;
			}
			
			.element {
				display:-webkit-flex;
				-webkit-align-items:stretch;
				display:flex;
				align-items:stretch;
				font-size: 14px;
				color: #555;
				height:42px;
			}
			#labSimula {
			    align-items: center;
				display: flex;
				justify-content: center;
				flex: 1 1 auto;
				cursor:pointer;
			}
			.ico,
			.icoT{
				fill: #fff;
				background:#337ab7;
				height:100%;
				min-width:42px;
				max-width:42px;
				/*border-radius: 10px 0 0 10px;*/
				padding:8px;
			}
			.icoT {
				display: -webkit-flex;
				-webkit-align-content: center;
				-webkit-align-items: center;
				-webkit-justify-content: center;
				display: flex;
				align-content: center;
				align-items: center;
				justify-content: center;
				color: white;
				font-size: 1.6em;
			}
			.form-control-base,
			.form-control {
				-webkit-flex:1 1 auto;
				flex:1 1 auto;
				padding:0 8px;
			}
			.ruolo,
			.servizio {
				text-overflow:ellipsis;
				padding:0 5px;
				cursor:pointer;
				width:100%;
				-webkit-appearance: menulist-button; /*safari...*/
			}
			.profilo {
				display: -webkit-flex;
				-webkit-flex-direction:column;
				display: flex;
				flex-direction:columnn;
				position:relative;
				width:100%;
				text-overflow:ellipsis;
			}
			#cmbProfilo {
				-webkit-flex:1 1 auto;
				flex:1 1 auto;
			}
			#lstProfili {
				position: absolute;
				top: 41px;
				left:0;
				padding: 5px;
				min-width:100%;
				overflow:hidden;
			}
			#lstProfili[halfsize] {
				height: calc(50% + 8px);
			}
			#lstProfili option {
				padding:2px;
				font-size: 80%;
				width:100%;
			}
			#lstProfili option:nth-child(odd) {
				background:#ececec;
			}
			#lstProfili option:hover {
				background: #337ab7;
				color: white;
			}
			.btn {
				color: #fff;
				background-color: #337ab7;
				border-color: #2e6da4;
				outline:none;
				font-weight: bold;
				white-space: nowrap;
				-ms-touch-action: manipulation;
				touch-action: manipulation;
				cursor: pointer;
				background-image: none;
				border: 1px solid transparent;
				/*border-radius:10px;*/
			}
			.btn[state=over] {
				background-color: #286090;
			}
			.btn[state=clicked] {
				background-color: #204d74;
			}
			.btnAccedi {
				width:100%;
				font-size:1.2em;
			}
			.err {
				color: #B50F03;
				font-size:0.8em;
				text-align:center;
				font-weight:bold;
			}
			.err > * {
				width:100%;
				max-width: 400px;
				margin: auto;
			}
			.myOpt {
				font-weight:bold;
			}
			.err,
			.err *,
			input[type=text],
			input[type=password] {
				user-select:text;
				-ms-user-select: text;
				-moz-user-select: text;
				-webkit-user-select: text;
			}
			.chkbox {
				cursor:pointer;
				display:-webkit-inline-flex;
				display:inline-flex;
			}
			.chkbox:hover,
			.chkbox input:checked+span {
				font-weight:bold;
			}
			.authenticatemodes,
			.simulamodes {
				display:-webkit-flex;
				display:flex;
				-webkit-flex:1 1 auto;
				flex:1 1 auto;
			}
			.authenticatemodes .authenticatemode,
			.simulamodes .simulamode {
			border:1px solid #A9A9A9;
				width:50%;
			display:-webkit-flex;
			display:flex;
			-webkit-flex-direction:column;
			flex-direction:column;
			cursor: pointer;
			}
			.authenticatemodes .authenticatemode:not(:first-child),
			.simulamodes .simulamode:not(:first-child) {
				border-left:none
			}
			.authenticatemode[state=over],
			.simulamode[state=over] {
				background-color: #286090;
				color:white;
			}
			.authenticatemode[selected],
			.simulamode[selected] {
				background-color:#337ab7;
				color:white;
			}
			.authenticatemode[state=clicked],
			.simulamode[state=clicked] {
				background-color: #204d74;
				color:white;
			}
			.authenticatemode .authenticatemodetitle,
			.simulamode .simulamodetitle {
				margin:auto;
				white-space:nowrap;
				padding:8px;
			}
			
			fieldset {
				margin-top: 0.5rem;
				margin-bottom: 0.5rem;
				border: none;
				box-shadow: 0px 0px 2px rgba(0,0,0,1);
				background-color: rgb(240,240,240);
				border-radius:5px;
				position:relative;
				padding-top:1.6rem;
				margin-top:2rem;
			}
			
			legend {
				position:absolute;
				background-color: #337ab7;
				padding: 0.75rem;
				font-size: 1.1em;
				font-weight: bold;
				color: white;
				border:1px solid #acacac;
				border-radius:5px;
				top:-1.6rem;
				left:-0.8rem;
			}
			#profiloElement {
				position:relative;
			}
			.loading {
				display: inline-block;
				border: 3px solid rgba(255,255,255,.3);
				border-radius: 50%;
				border-top-color: #fff;
				animation: spin 1s ease-in-out infinite;
				-webkit-animation: spin 1s ease-in-out infinite;
				position: absolute;
				height: 42px;
				width: 42px;
			}
						
			@keyframes spin {
				to { -webkit-transform: rotate(360deg); }
			}
			@-webkit-keyframes spin {
				to { -webkit-transform: rotate(360deg); }
			}
			[disattivo],
			[disattivo] > * {
				cursor: default !important;
				opacity: 0.4;
				transition: none !important;
				pointer-events: none !important;
				font-style: italic;
			}
		</style>
		<script>
			var xhr;
			var timeoutIncr = 0;
			function fixupMouse(event) {
				event = event || window.event;
			
				var e = { event: event,
					target: event.target ? event.target : event.srcElement,
					which: event.which ? event.which :
						event.button === 1 ? 1 :
						event.button === 2 ? 3 : 
						event.button === 4 ? 2 : 1,
					x: event.x ? event.x : event.clientX,
					y: event.y ? event.y : event.clientY
				};
				return e;
			}
			window.addEventListener('DOMContentLoaded', function() {
				document.getElementById('IdUtente').focus();
				var cmbRuolo = document.getElementById('cmbRuolo');
				var cmbServizio = document.getElementById('cmbServizio');
				var cmbRuoloSim = document.getElementById('cmbRuoloSim');
				var cmbServizioSim = document.getElementById('cmbServizioSim');
				var cmbProfilo = document.getElementById('cmbProfilo');
				var lstProfili = document.getElementById('lstProfili');
				var IdSoggettoSim = document.getElementById('IdSoggettoSim');
				var Realm = document.getElementById('Realm');
				var chkSimula=document.getElementById('chkSimula');
				var pnlSimula = document.getElementById('pnlSimula');
				var authenticatemode = document.getElementById('authenticatemode');
				
				Array.prototype.slice.call(document.querySelectorAll('.btn')).forEach(function(elm) {
					elm.addEventListener("mouseover",function() {
						elm.setAttribute("state","over");
					});
					elm.addEventListener("mouseleave",function() {
						elm.removeAttribute("state");
					});
					elm.addEventListener("mousedown",function(event) {
						if(fixupMouse(event).which!=1) {
							return;
						}
						elm.setAttribute("state","clicked");
					});
					elm.addEventListener("mouseup",function(event) {
						if(fixupMouse(event).which!=1) {
							return;
						}
						elm.setAttribute("state","over");
					});
				});
				
				Array.prototype.slice.call(document.querySelectorAll('input[type=text],input[type=password]')).forEach(function(elm) {
					elm.addEventListener('keypress', function(e) {
						if (e.key === 'Enter') {
							e.preventDefault();
							document.getElementById('btnLogin').click();
						}
					});
				});
			});
		</script>
	</xsl:template>
	<xsl:template match="filtraUtenti" mode="generate">
		<xsl:for-each select="/DOCUMENTO/LstProfili/RECORD">
			<option>
				<xsl:attribute name="key"><xsl:value-of select="idsoggetto" /></xsl:attribute>
				<xsl:attribute name="realm"><xsl:value-of select="realm" /></xsl:attribute>
				<xsl:choose>
					<xsl:when test="realm=1">locale</xsl:when>
					<xsl:when test="realm=2">pa.sm</xsl:when>
					<xsl:when test="realm=3">intra</xsl:when>
				</xsl:choose> - <xsl:value-of select="codiss" /> - <xsl:value-of select="pf"/><xsl:if test="string-length(oe)>0"> (OE <xsl:value-of select="codoe" /> - <xsl:value-of select="oe"/>)</xsl:if>
			</option>
		</xsl:for-each>
	</xsl:template>
	<xsl:template match="filtraRuoliServizi" mode="generate">
		<ruoli>
			<xsl:for-each select="/DOCUMENTO/LstRuoli/RECORD">
				<option>
					<xsl:attribute name="value"><xsl:value-of select="idruolo" /></xsl:attribute>
					<xsl:attribute name="idserv"><xsl:value-of select="idservizio" /></xsl:attribute>
					<xsl:value-of select="ruolo" />
				</option>
			</xsl:for-each>
		</ruoli>
		<servizi>
			<xsl:for-each select="/DOCUMENTO/LstServizi/RECORD">
				<option>
					<xsl:attribute name="value"><xsl:value-of select="idservizio" /></xsl:attribute>
					<xsl:value-of select="servizio" />
				</option>
			</xsl:for-each>
		</servizi>
	</xsl:template>
	<xsl:variable name="TitoloPagina">Pagina di Logon</xsl:variable>
	
	<xsl:template name="Titolo">
		<xsl:value-of select="$TitoloPagina"/>
	</xsl:template>
	<xsl:template name="TitoloForm">
		<xsl:call-template name="Titolo" />
	</xsl:template>

	<xsl:variable name="favicon">
		<link rel="icon" href="{/DOCUMENTO/APP_CONFIG/APP_IMAGES}/app_icon.png" type="image/png" />
	</xsl:variable>
	<xsl:variable name="shortcuticon">
		<link rel="shortcut icon" href="{/DOCUMENTO/APP_CONFIG/APP_IMAGES}/app_icon.png" type="image/png" />
	</xsl:variable>

	<xsl:template match="/">
		<xsl:choose>
			<xsl:when test="count(/DOCUMENTO/TEMPLATE/*)>=1">
				<xsl:apply-templates select="/DOCUMENTO/TEMPLATE/*" mode="generate"/>
			</xsl:when>
			<xsl:otherwise>
				<xsl:text disable-output-escaping='yes'>&lt;!DOCTYPE html&gt;</xsl:text>
				<html>
					<head>
						<title><xsl:value-of select="/DOCUMENTO/APP_CONFIG/APP_DISPLAY_NAME" /> - Logon<xsl:if 
							test="/DOCUMENTO/SESSIONE/DBHost='localhost'"> TEST</xsl:if><xsl:if 
							test="/DOCUMENTO/SESSIONE/DBHost='elrond.ciscoop.cis'"> TEST (ELROND)</xsl:if>
						</title>
						<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
						<xsl:copy-of select="$favicon" />
						<xsl:copy-of select="$shortcuticon" />
						<meta name="viewport" content="width=device-width, initial-scale=1" />
						<xsl:call-template name="std_css_js" />
						<xsl:call-template name="user_css_js" />
					</head>
					<body>
						<xsl:call-template name="paginaNormale" />
					</body>
				</html>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>

	<xsl:template name="paginaNormale">
		<div class="login-form">
			<form method="post">
				<input type="hidden" name="MTipo" value="G"/>
				<input type="hidden" name="MPasso" value="1"/>
				<div class="appName">
					<xsl:value-of select="/DOCUMENTO/APP_CONFIG/APP_DISPLAY_NAME" />
				</div>
				<div class="appDesc">
					<xsl:value-of select="/DOCUMENTO/APP_CONFIG/APP_DESCRIPTION" />
				</div>
				<fieldset>
					<legend>Login</legend>
					<input type="hidden" id="authenticatemode" name="authenticatemode" value="{DOCUMENTO/authenticatemode}" />
					<div class="element">
						<svg class="ico" viewBox="0 0 1000 1000" focusable="false">
							<g>
								<path d="M500,59c127.8,0,231.5,103.6,231.5,231.5c0,82.4-43.1,185.1-107.8,247.7c-15.3,14.8-11.4,39.8,7.5,49.3l165.2,82.6C871.6,707.8,919,784.5,919,868.6V941H81v-72.4c0-84.1,47.5-160.8,122.6-198.5l165.2-82.6c19-9.5,22.8-34.5,7.5-49.3c-64.8-62.6-107.8-165.3-107.8-247.7C268.5,162.6,372.2,59,500,59 M500,10c-154.6,0-280.5,125.8-280.5,280.5c0,90.1,41.5,193.2,105,264.5l-142.9,71.4C89.3,672.5,32,765.3,32,868.6V941c0,27,22,49,49,49H919c27.1,0,49-22,49-49v-72.4c0-103.2-57.4-196-149.7-242.3l-142.9-71.4c63.5-71.3,105.1-174.4,105.1-264.5C780.5,135.8,654.6,10,500,10"/>
							</g>
						</svg>
						<input>
							<xsl:attribute name="type">text</xsl:attribute>
							<xsl:attribute name="name">IdUtente</xsl:attribute>
							<xsl:attribute name="id">IdUtente</xsl:attribute>
							<xsl:attribute name="class">form-control</xsl:attribute>
							<xsl:attribute name="value"><xsl:value-of select="/DOCUMENTO/IdUtente" /></xsl:attribute>
							<xsl:attribute name="title">Inserisci il nome Utente</xsl:attribute>
							<xsl:attribute name="placeholder">User Name</xsl:attribute>
							<xsl:attribute name="required">required</xsl:attribute>
							<xsl:attribute name="autocomplete">off</xsl:attribute>
							<xsl:attribute name="autocorrect">off</xsl:attribute>
							<xsl:attribute name="autocapitalize">off</xsl:attribute>
							<xsl:attribute name="spellcheck">false</xsl:attribute>
						</input>
					</div>
					<div class="element">
						<svg class="ico" viewBox="0 0 1000 1000" focusable="false">
							<g>
								<path d="M752,326.2L752,326.2l0-31.1c0-173.2-98.9-285-252-285c-153.1,0-252,111.9-252,285v31.1C164.2,326.2,96,394.3,96,478.1v360.1C96,921.9,164.2,990,248,990H752c83.8,0,151.9-68.1,151.9-151.9V478.1C904,394.3,835.8,326.2,752,326.2z M500,118c125.3,0,144,110.9,144,177v31.1H356V295C356,241.8,370,118,500,118z M816.2,838.1c0,35.4-28.8,64.1-64.2,64.1H248c-35.4,0-64.2-28.7-64.2-64.1V478.1c0-35.4,28.8-64.1,64.2-64.1H752c35.4,0,64.2,28.7,64.2,64.1L816.2,838.1L816.2,838.1z"/>
								<path d="M536,684.1v82c0,19.8-16.2,36-36,36c-19.8,0-36-16.2-36-36v-82c-21.4-12.5-36-35.4-36-62c0-39.8,32.2-72,72-72c39.8,0,72,32.2,72,72C572,648.7,557.4,671.6,536,684.1L536,684.1z"/>
							</g>
						</svg>
						<input>
							<xsl:attribute name="type">password</xsl:attribute>
							<xsl:attribute name="name">Password</xsl:attribute>
							<xsl:attribute name="id">Password</xsl:attribute>
							<xsl:attribute name="class">form-control</xsl:attribute>
							<xsl:attribute name="value"><xsl:value-of select="/DOCUMENTO/Password" /></xsl:attribute>
							<xsl:attribute name="title">Inserisci la password</xsl:attribute>
							<xsl:attribute name="placeholder">Password</xsl:attribute>
							<xsl:attribute name="autocomplete">off</xsl:attribute>
							<xsl:attribute name="autocorrect">off</xsl:attribute>
							<xsl:attribute name="autocapitalize">off</xsl:attribute>
							<xsl:attribute name="spellcheck">false</xsl:attribute>
						</input>
					</div>
				</fieldset>
				<div class="element">
					<input>
						<xsl:attribute name="id">btnLogin</xsl:attribute>
						<xsl:attribute name="type">submit</xsl:attribute>
						<xsl:attribute name="name">Esegui</xsl:attribute>
						<xsl:attribute name="value">Accedi</xsl:attribute>
						<xsl:attribute name="class">btn btnAccedi</xsl:attribute>
					</input>
				</div>
				<div class="err">
					<xsl:for-each select="/DOCUMENTO/ERRORE/MESSAGE">
						<div>
							<xsl:if test="TYPE/@cod = '8' and string-length(EXCEPTION) > 0">
								<xsl:attribute name="title"><xsl:value-of select="normalize-space(substring(EXCEPTION,1,300))"/></xsl:attribute>
							</xsl:if>
							<xsl:value-of select="TEXT" disable-output-escaping="yes"/>
						</div>
					</xsl:for-each>
				</div>
			</form>
		</div>
	</xsl:template>
</xsl:stylesheet>