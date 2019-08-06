<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
  	<title><tiles:getAsString name="title"/></title>
  	<!-- Tell the browser to be responsive to screen width -->
  	<meta http-equiv="X-UA-Compatible" content="IE=edge">
  	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  	
	<tiles:insertDefinition name="adminCss" />
	<script src="/static/assets/jquery/jquery-3.4.1.min.js"></script>
</head>

<body>

  

<tiles:insertAttribute name="body" />


<tiles:insertDefinition name="adminScript"/>


</body>
</html>


