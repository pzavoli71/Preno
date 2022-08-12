<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="vars.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<link href="<%= app_css_dir %>/cis-static-pages.css" rel="stylesheet" type="text/css"></link>
		<title><%= display_name %> - Errore trasformazione</title>
	</head>
	<body>
		<div class="container">
			<h1 style="text-align: center; font-size: 62px; text-shadow: 0px 0px 8px #aaaaaa;"><%= display_name %></h1>
			
			<h2 class="cis-errpage-centered">
				Oops, si è verificato un errore nella generazione della pagina!
			</h2>
			<div class="cis-errpage-panel">
				<% out.println( request.getParameter(IStdErrorPages.PARAM_ERR) ); %>
				
				<div class="cis-errpage-detail">
					<% out.println( request.getParameter(IStdErrorPages.PARAM_DETAIL) ); %>
				</div>
				
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