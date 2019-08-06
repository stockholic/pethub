<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<section class="content-header">
  <h1>
    사용자관리
    <small>등록/수정/권한부여</small>  
  </h1>
  <ol class="breadcrumb">
    <li><a href="#"> Level</a></li>
    <li class="active">Here</li>
  </ol>
</section>


<section class="content container-fluid">

<div class="box box-default">
    <div class="box-body">
    
    <form id="frm" name="frm" action="/adm/user/insert" method="POST">
	<input type="hidden" name="userSrl" id="userSrl" value="${user.userSrl }">
	<table class="table box-600">
	  <colgroup>
	    <col width="30%">
	    <col width="70%">
	  </colgroup>
	<tbody>
	<tr>
		<th>아이디</th>
		<td><input type="text" class="form-control" name="userId" id="userId" value="${userInfo.userId }" ${!empty userInfo.userSrl ? "readOnly" : ""}></td>
	</tr>
	<tr>
		<th>패스워드</th>
		<td><input type="password" class="form-control" name="password" id="password" value=""></td>
	</tr>
	<tr>
		<th>패스워드<br>(확인)</th>
		<td><input type="password" class="form-control" name="password2" id="password2" value=""></td>
	</tr>
	<tr>
		<th>성명</th>
		<td><input type="text" class="form-control" name="userNm" id="userNm" value="${userInfo.userNm }"></td>
	</tr>
	<tr>
		<th>E-Mail</th>
		<td><input type="text" class="form-control" name="email" id="email" value="${userInfo.email }"></td>
	</tr>
	<tr>
		<th>권한그룹</th>
		<td>
			<select name="roleCd" id="roleCd"  class="form-control">
				<option value="">선택</option>
				<c:forEach var="lst" items="${roleList }">
					<option value="${lst.roleCd }" ${lst.roleCd eq userInfo.roleCd ? "selected" : ""} >${lst.roleNm }</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
	<tr>
		<th>사용여부</th>
		<td>
			<select name="useYn" id="useYn" class="form-control">
				<option value="Y" ${userInfo.useYn eq "Y" ? "selected" : "" }>사용</option>
				<option value="N" ${userInfo.useYn eq "N" ? "selected" : "" }>미사용</option>
			</select>
		</td>
	</tr>
	</tbody>
	</table> 
	</form>
	
	</div>
	
	<div class="box-footer box-600">
		<button type="button" class="btn btn-primary btn-xm" onClick="list()">목록</button>
		<button type="button" class="btn btn-primary btn-xm" onClick="save()">저장</button>
    </div>
	
</div>
	
</section>
	

<form name="listFrm" method="POST">
	<input type="hidden" name="page" value="${user.page }">
	<input type="hidden" name="useYn" value="${user.useYn }">
</form>
    

<script>

function list(){
	document.listFrm.action = "/adm/user/list";
	document.listFrm.submit();
}

function save(){
	
	if( $("#userId").val().trim() == "" ){
		alert("아이디를 입력해주세요");
		$("#userId").focus();
		return;
	}
	
	<c:if test="${empty user.userSrl }">
	if( $("#password").val().trim() == "" ){
		alert("패스워드를 입력해주세요");
		$("#password").focus();
		return;
	}
	if( $("#password2").val().trim() == "" ){
		alert("확인 패스워드를 입력해주세요");
		$("#password2").focus();
		return;
	}
	</c:if>
	
	if( $("#password").val() != $("#password2").val() ){
		alert("패스워드가 일치하지 않습니다.");
		$("#password").focus();
		return;
	}
	if( $("#userNm").val().trim() == "" ){
		alert("이름을 입력해주세요");
		$("#userNm").focus();
		return;
	}
	if( $("#roleCd").val() == "" ){
		alert("권한그룹을 선택해주세요");
		$("#roleCd").focus();
		return;
	}
	
	$.ajax({      
		type : "POST",  
        url : "/adm/user/insert",
        data :  $("#frm").serialize(),
        success : function(response){   
        	if($("#userSrl"))
	        	list();
        	else
				document.location.href = "/adm/user/list";        		
        },   
        error : function(xhr) {
            alert("에러 : " + xhr.status);
        }
	});  
	
}

</script>

