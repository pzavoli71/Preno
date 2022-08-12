<%@page import="sm.ciscoop.servlet.error.IStdErrorPages"%>
<%@page import="sm.ciscoop.commons.utils.CommonProperties"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.io.*"%>
<%
  String app_css_dir = CommonProperties.getInstance().getAppCSSDir();
  String app_js_dir = CommonProperties.getInstance().getAppJSDir();
  String app_images_dir = CommonProperties.getInstance().getAppImagesDir();
  String display_name = CommonProperties.getInstance().getDisplayName();
  String app_description = CommonProperties.getInstance().getAppDescription();
  String support_email = CommonProperties.getInstance().getEMailSupportoTecnico();
%>
