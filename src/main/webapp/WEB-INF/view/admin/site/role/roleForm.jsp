<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="box box-primary"> 

<div class="box-header with-border">
  <h3 class="box-title">권한등록</h3>
</div>

<form id="frm" name="frm" action="/adm/role/insert" method="POST">
<input type="hidden" name="roleSrl" value="${role.roleSrl }">
<table class="table" style="width:500px;padding:10px">
  <colgroup>
    <col width="30%">
    <col width="70%">
  </colgroup>
<tbody>
<tr>
	<th>권한그룹코드</th>
	<td><input type="text" class="form-control" name="roleCd" id="roleCd" value="${role.roleCd }"></td>
</tr>
<tr>
	<th>권한그룹명</th>
	<td><input type="text" class="form-control" name="roleNm" id="roleNm" value="${role.roleNm }"></td>
</tr>
<tr>
	<th>사용여부</th>
	<td>
		<select name="useYn" id="useYn" class="form-control">
			<option value="Y" ${role.useYn eq "Y" ? "selected" : "" }>사용</option>
			<option value="N" ${role.useYn eq "N" ? "selected" : "" }>미사용</option>
		</select>
	</td>
</tr>
</tbody>
</table> 
</form>

</div>

<div style="text-align:center;padding:10px">
	<button type="button" class="btn btn-primary btn-xm" onClick="save()">저장</button>&nbsp;
	<button type="button" class="btn btn-primary btn-xm" onClick="window.close()">닫기</button>
</div>


<script>


function save(){
	
	if( $("#roleCd").val().trim() == "" ){
		alert("권한코드를 입력해주세요");
		$("#roleCd").focus();
		return;
	}
	if( $("#roleNm").val().trim() == "" ){
		alert("권한그룹을 입력해주세요");
		$("#roleNm").focus();
		return;
	}
	
	$.ajax({      
		type : "POST",  
        url : "/adm/role/insert",
        data :  $("#frm").serialize(),
        success : function(response){   
			window.opener.location.reload();
        	window.close();
        },   
        error : function(xhr) {
            alert("에러 : " + xhr.status);
        }
	});  
	
}

</script>

