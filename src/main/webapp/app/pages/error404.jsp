<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="vars.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<link href="<%=app_css_dir%>/cis-static-pages.css" rel="stylesheet" type="text/css"></link>
		<title><%=display_name%> - Pagina non trovata!</title>
	</head>
	<body class="cis-errpage-404">
		<div class="container">
			<h2 class="cis-errpage-centered">ERRORE!</h2>
			<div class="cis-errpage-main">
				<img src="<%=app_images_dir%>/404.png" alt="404" class="cis-error-img404" />
			</div>
			<h2 class="cis-errpage-centered">
				Non ho trovato la pagina <span class="pageuri"> <%=request.getAttribute("javax.servlet.error.request_uri")%></span>
				<!-- ${condition ? "some text when true" : "some text when false"}  -->
			</h2>
		</div>
	</body>
</html>