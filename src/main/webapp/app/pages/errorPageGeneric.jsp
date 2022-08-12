<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="vars.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<link href="<%= app_css_dir %>/cis-static-pages.css" rel="stylesheet" type="text/css"></link>
		<title><%= display_name %> - Errore</title>
	</head>
	<body class="cis-errpage-generic">
		<div class="container">
			<h2 class="cis-errpage-centered">
				<img src="<%= app_images_dir %>/error-cloud.png" alt="error-cloud" />
				<br />
				Si è verificato un errore nell'applicazione <%= display_name %>!
			</h2>
			<div class="cis-errpage-panel">
			    <% out.println( request.getParameter(IStdErrorPages.PARAM_ERR) ); %>
			    <div class="cis-errpage-redirect">
			    	Ci scusiamo per il disagio.
			    </div>
			    <div>
				    <%= support_email %>
			    </div>
			</div>
		</div>
	</body>
</html>