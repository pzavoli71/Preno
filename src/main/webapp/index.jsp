<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/app/pages/vars.jsp" %>
<html>

<head>
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="Refresh" content="1; URL=${cis.index.logon.url}" />
	<link href="<%= app_css_dir %>/cis-static-pages.css" rel="stylesheet" type="text/css"></link>
	<title>avvio di <%= display_name %> ...</title>
	
	<style type="text/css">
		@keyframes ldio-yi9ymgni6d9 {
			0% {
				opacity: 1;
			}
			50% {
				opacity: .5;
			}
			100% {
				opacity: 1;
			}
		}
		
		.ldio-yi9ymgni6d9 div {
			position: absolute;
			width: 30px;
			height: 50px;
			top: 0px;
			animation: ldio-yi9ymgni6d9 1s cubic-bezier(0.5, 0, 0.5, 1) infinite;
		}
		
		.ldio-yi9ymgni6d9 div:nth-child(1) {
			transform: translate(25px, 0);
			background: #353535;
			animation-delay: -0.6s;
		}
		
		.ldio-yi9ymgni6d9 div:nth-child(2) {
			transform: translate(65px, 0);
			background: #666666;
			animation-delay: -0.4s;
		}
		
		.ldio-yi9ymgni6d9 div:nth-child(3) {
			transform: translate(105px, 0);
			background: #9b9b9b;
			animation-delay: -0.2s;
		}
		
		.ldio-yi9ymgni6d9 div:nth-child(4) {
			transform: translate(145px, 0);
			background: #d4d4d4;
			animation-delay: -1s;
		}
		
		.loadingio-spinner-bars-3cumfxdvroh {
			width: 200px;
			height: 50px;
			display: inline-block;
			overflow: hidden;
			background: none;
		}
		
		.ldio-yi9ymgni6d9 {
			width: 100%;
			height: 100%;
			position: relative;
			transform: translateZ(0) scale(1);
			backface-visibility: hidden;
			transform-origin: 0 0;
			/* see note above */
		}
		
		.ldio-yi9ymgni6d9 div {
			box-sizing: content-box;
		}
	</style>
</head>

<body>
	<div class="container">
		<div class="appName">
			<%= display_name %>
		</div>
		<div class="appDesc">
			<%= app_description %>
		</div>

		<center>
			<div class="loadingio-spinner-bars-3cumfxdvroh">
				<div class="ldio-yi9ymgni6d9">
					<div></div>
					<div></div>
					<div></div>
					<div></div>
				</div>
			</div>
		</center>

		<h2 style="text-align: center;">Attendere la partenza dell'applicativo... ${cis.debug.project}</h2>
	</div>
</body>

</html>