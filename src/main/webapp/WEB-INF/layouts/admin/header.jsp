<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication var="info" property="principal" />

<%--  <sec:authorize access="hasAnyRole('ROLE_ADM', 'ROLE_USR')">
	<div>
		<span class="glyphicon glyphicon-off"></span> <a href="/logout"><span> 로그아웃</span></a>
		${info.user.userNm}(${info.user.userId})
	</div>
</sec:authorize>
<sec:authorize access="!hasRole('ROLE_USR')">
	<div><span class="fa fa-sign-out fa-fw"></span> <a href="#"><span> 로그인</span></a></div>
</sec:authorize> --%>


<div>
	<span class="glyphicon glyphicon-off"></span> <a href="/logout"><span> 로그아웃</span></a>
	${info.user.userNm}(${info.user.userId})
</div>

