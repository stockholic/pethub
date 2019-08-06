<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>

.login-box-body {
  border-radius: 8px;
  -moz-border-radius: 8px;
  -webkit-border-radius: 8px;
}
</style>

<body  class="hold-transition login-page">
<div class="login-box">
  <div class="login-logo">
    <a href="/adm/main"><b>PetHub</b></a>
  </div>

  <div class="login-box-body">
    <p class="login-box-msg">
    <c:if test="${!empty param.err}">
		아이디와 비밀번호를 확인해 주세요
	</c:if> 
    </p>

   	<form method="post" role="form" id="form_login" action='/loginProc' method='POST'>
      
      <div class="form-group has-feedback">
        <input type="text" name="userNm" id="userNm" value="" class="form-control" placeholder="아이디" autofocus autocomplete="off">
        
        
        <span class="glyphicon glyphicon-user form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <input type="password" class="form-control" name="password" id="password" value="" placeholder="Password"  autocomplete="off">
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
      </div>
      
      <div class="row">
        <div class="col-xs-8">
          <div class="checkbox">
            <label>
              <input type="checkbox" name="remember-me"> 로그인 유지
            </label>
          </div>
        </div>
        <div class="col-xs-4">
          <button type="submit" class="btn btn-primary btn-block btn-flat">전송</button>
        </div>
      </div>
    </form>
  </div>
</div>
</body>
