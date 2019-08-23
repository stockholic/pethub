<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
  	<title>펫허브 : PetHub ${petTitle}</title>
  
  	<meta http-equiv="X-UA-Compatible" content="IE=edge">
  	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  	<link rel="canonical" href="http://www.pethub.kr">
	<meta name="description" content="강아지, 고양이 종합 정보">
	<meta name="keywords" content="애견,애완견,강아지,고양이,분양,애견분양,무료분양"> 
	<meta name="classification" content="애견,애완견,강아지,고양이,분양,애견분양,무료분양">  	
	
	<meta property="og:type" content="website">
	<meta property="og:title" content="강아지, 고양이 종합 정보">
	<meta property="og:description" content="애견,애완견,강아지,고양이,분양,애견분양,무료분양">
	<meta property="og:image" content="http://www.pethub.kr/static/image/pethub.png">
	<meta property="og:url" content="http://www.pethub.kr">
	
	<!-- Global site tag (gtag.js) - Google Analytics -->
	<script async src="https://www.googletagmanager.com/gtag/js?id=UA-146217264-1"></script>
	<script>
	  window.dataLayer = window.dataLayer || [];
	  function gtag(){dataLayer.push(arguments);}
	  gtag('js', new Date());
	
	  gtag('config', 'UA-146217264-1');
	</script>
	
	
  	<link rel="shortcut icon" href="/static/favicon.ico" type="image/x-icon" />
	<tiles:insertDefinition name="frontCss" />
	<tiles:insertDefinition name="frontScript"/>
	
</head>

<body>

<div class="container ">
    
   <div>
     <tiles:insertAttribute name="body" />
   </div>
    
</div>

 <hr />
    
  <div class="footer">
 	 <tiles:insertAttribute name="footer" />
  </div>
</body>
</html>


