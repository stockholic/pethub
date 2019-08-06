<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication var="info" property="principal" />

<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
	<meta charset="utf-8">
  	<title><tiles:getAsString name="title"/></title>
  
  	<!-- Tell the browser to be responsive to screen width -->
  	<meta http-equiv="X-UA-Compatible" content="IE=edge">
  	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  
  	<link rel="shortcut icon" href="/static/favicon.ico" type="image/x-icon" />
  	
	<tiles:insertDefinition name="adminCss" />
	<tiles:insertDefinition name="adminScript"/>
	
</head>

<body class="hold-transition skin-blue sidebar-mini fixed">
<div class="wrapper">

  <!-- Main Header -->
  <header class="main-header">

    <!-- Logo start -->
    <a href="/adm/main" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>PH</b></span>
      <span class="logo-lg"><b>PetHub</b></span>
    </a>
     <!-- Logo end-->

    <!-- Header Navbar -->
    <nav class="navbar navbar-static-top" role="navigation">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>
      
      
      <!-- Navbar Right Menu -->
      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <!-- Messages: style can be found in dropdown.less-->
          
          <li class="dropdown messages-menu">
            <!-- Menu toggle button -->
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-envelope-o"></i>
              <span class="label label-success">4</span>
            </a>
            <ul class="dropdown-menu">
              <li class="header">You have 4 messages</li>
              <li>
                <!-- inner menu: contains the messages -->
                <ul class="menu">
                  <li><!-- start message -->
                    <a href="#">
                      <!-- Message title and timestamp -->
                      <h4>
                        Support Team
                        <small><i class="fa fa-clock-o"></i> 5 mins</small>
                      </h4>
                      <!-- The message -->
                      <p>Why not buy a new awesome theme?</p>
                    </a>
                  </li>
                  <!-- end message -->
                </ul>
                <!-- /.menu -->
              </li>
              <li class="footer"><a href="#">See All Messages</a></li>
            </ul>
          </li>
          
          <li>
            <a href="/logout" > <i class="fa fa-fw fa-power-off"></i>  로그아웃 </a>
          </li>
          
          
          <!-- /.messages-menu -->
        </ul>
      </div>
      
    </nav>
  </header>
  
  
  
  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">

    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">

	<div class="user-panel">
        <div class="info"> 
          <p>${info.user.userNm}(${info.user.userId})</p> 
        </div> 
      </div>
      
      <!-- Sidebar Menu -->
      <ul class="sidebar-menu" data-widget="tree">
      <tiles:insertAttribute name="menu" />
      </ul>
	      
    </section>
    <!-- /.sidebar -->
    
  </aside>
  
  

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
   <tiles:insertAttribute name="body" />
  </div>

</div>

 <!-- Main Footer -->
<!--   <footer class="main-footer">
    To the right
    <div class="pull-right hidden-xs">
      Anything you want
    </div>
    Default to the left
    <strong>Copyright &copy; 2016 <a href="#">Company</a>.</strong> All rights reserved.
  </footer> -->



<script>

//https://github.com/tefra/navgoco

$('.sidebar-menu').navgoco({
//	caretHtml: '<i class="some-random-icon-class"></i>',
	accordion: true,			
	openClass: 'active menu-open',
	save: true,					//메뉴위치 유지
	cookie: {
		name: 'navgoco',
	  	expires: false,
	  	path: '/'
	 },
	 slide: {
	 	duration: 400,
	 	easing: 'swing'
	 },
	onClickAfter : function(e){
	 }
});

</script>
</body>
</html>


