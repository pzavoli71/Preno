<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="vars.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<link href="<%= app_css_dir %>/cis-static-pages.css" rel="stylesheet" type="text/css"></link>
		<title><%= display_name %> - Impossibile stampare</title>
	</head>
	<body>
		<div class="container">
			<div class="cis-errpage-centered">
		    	<img src="<%= app_images_dir %>/error-cloud.png" alt="error-cloud" />
		      
		    	<h1>Errore in creazione report</h1>
		    	Si è verificato un errore imprevisto nella generazione del report 
		    	<strong><% out.println(request.getParameter(IStdErrorPages.PARAM_REPORTTITLE)); %></strong>.
	    	</div>
	    
	    	Dettagli del problema:
			<table class="cis-errpage-tabreport">
				<tr>
			 		<td>
			 			Codice report
			 		</td>
			 		<td class="cis-errpage-bold">
			 			<% out.println(request.getParameter(IStdErrorPages.PARAM_REPORTNAME)); %>
			 		</td>
				</tr>
				<tr>
			 		<td>
			 			Origine errore
			 		</td>
			 		<td class="cis-errpage-bold">
			 			<% out.println(request.getParameter(IStdErrorPages.PARAM_ORIGIN)); %>
			 		</td>
				</tr>
				<tr>
			 		<td>
			 			Sorgente errore
			 		</td>
			 		<td class="cis-errpage-bold">
			 			<% out.println(request.getParameter(IStdErrorPages.PARAM_REPORTSOURCE)); %>
			 		</td>
				</tr>
				<tr>
			  	<td>
			  		Descrizione errore
			  	</td>
			  	<td class="cis-errpage-bold cis-errpage-stperr">
			  		<% out.println(request.getParameter(IStdErrorPages.PARAM_ERR)); %>
			  		<br />
			  		<% out.println(request.getParameter(IStdErrorPages.PARAM_DETAIL)); %>
			  	</td>
		  	</tr>
		</table>
		    <div>
				La preghiamo di chiudere questa finestra e riprovare a generare il report.
				<br />
				Se il problema persiste, contattare l'assistenza tecnica.
		    </div>
		</div>
	</body>
</html>