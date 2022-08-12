<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="3.0">	
    <xsl:variable name="app_name"><xsl:value-of select="/DOCUMENTO/APP_CONFIG/APP_NAME"/></xsl:variable>
    <xsl:variable name="app_description"><xsl:value-of select="/DOCUMENTO/APP_CONFIG/APP_DESCRIPTION"/></xsl:variable>
    <xsl:variable name="app_context"><xsl:value-of select="/DOCUMENTO/APP_CONFIG/APP_CONTEXT"/></xsl:variable>
    <xsl:variable name="app_display_name"><xsl:value-of select="/DOCUMENTO/APP_CONFIG/APP_DISPLAY_NAME"/></xsl:variable>
    <xsl:variable name="app_mail"><xsl:value-of select="/DOCUMENTO/APP_CONFIG/APP_MAIL"/></xsl:variable>
    <xsl:variable name="app_home"><xsl:value-of select="/DOCUMENTO/APP_CONFIG/APP_HOME"/></xsl:variable>
    <xsl:variable name="app_css"><xsl:value-of select="/DOCUMENTO/APP_CONFIG/APP_CSS"/></xsl:variable>
    <xsl:variable name="app_images"><xsl:value-of select="/DOCUMENTO/APP_CONFIG/APP_IMAGES"/></xsl:variable>
    <xsl:variable name="app_js"><xsl:value-of select="/DOCUMENTO/APP_CONFIG/APP_JS"/></xsl:variable>
    <xsl:variable name="app_libs"><xsl:value-of select="/DOCUMENTO/APP_CONFIG/APP_JS"/>/extlib</xsl:variable>
    <xsl:variable name="app_xsl"><xsl:value-of select="/DOCUMENTO/APP_CONFIG/APP_XSL"/></xsl:variable>
    <xsl:variable name="app_themes"><xsl:value-of select="/DOCUMENTO/APP_CONFIG/APP_THEMES"/></xsl:variable>
  	<xsl:variable name="current_skin"><xsl:value-of select="/DOCUMENTO/SESSIONE/skin" /></xsl:variable>
    
    <!-- Per la gestione della versione dei file Javascript e CSS da importare -->
    <xsl:variable name="ver" visibility="public">1</xsl:variable>
        
	<xsl:template name="initApp">
		<link rel="stylesheet" href="{$app_css}/app.css?v={$ver}" type="text/css" data-cssvars="" />
		<link rel="stylesheet" href="{$app_css}/atlanta.css?v={$ver}" type="text/css" data-cssvars="" />
		<script type="text/javascript" src="{$app_js}/app.js?v={$ver}"></script>
		<script>
			var AppGlob = AppGlob || {};
			AppGlob.name='<xsl:value-of select="$app_name" />';
			AppGlob.appContext='<xsl:value-of select="$app_context" />';
			AppGlob.displayName='<xsl:value-of select="$app_display_name" />';
			AppGlob.appHome='<xsl:value-of select="$app_home" />';
			AppGlob.appCss='<xsl:value-of select="$app_css" />';
			AppGlob.appImages='<xsl:value-of select="$app_images" />';
			AppGlob.appJs='<xsl:value-of select="$app_js" />';
			AppGlob.appLibs='<xsl:value-of select="$app_libs" />';
			AppGlob.appXsl='<xsl:value-of select="$app_xsl" />';
			AppGlob.appThemes='<xsl:value-of select="$app_themes" />';
		</script>
	</xsl:template>
</xsl:stylesheet>
