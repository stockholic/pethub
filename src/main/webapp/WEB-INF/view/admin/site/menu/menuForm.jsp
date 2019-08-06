<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="box box-primary"> 

<div class="box-header with-border">
  <h3 class="box-title">메뉴등록</h3>
</div>

<form id="frm" name="frm" action="/adm/menu/insert" method="POST">
<input type="hidden" name="menuSrl" value="${menu.menuSrl }">
<input type="hidden" name="menuSrl1" value="${menu.menuSrl1 }">
<input type="hidden" name="menuSrl2" value="${menu.menuSrl2 }">
<table class="table" style="width:500px;padding:10px">
  <colgroup>
    <col width="25%">
    <col width="75%">
  </colgroup>
<tbody>
<tr>
	<th>메뉴명</th>
	<td><input type="text" class="form-control" name="menuNm" id="menuNm" value="${menuData.menuNm }"></td>
</tr>
<tr>
	<th>경로</th>
	<td><input type="text" class="form-control" name="menuUrl" id="menuUrl" value="${menuData.menuUrl }"></td>
</tr>
<tr>
	<th>순서</th>
	<td><input type="text" class="form-control" name="menuStp" id="menuStp" maxlength="3" value="${menuData.menuStp }"></td>
</tr>
<tr>
	<th>사용여부</th>
	<td>
		<select name="useYn" id="useYn" class="form-control">
			<option value="Y" ${menuData.useYn eq "Y" ? "selected" : "" }>사용</option>
			<option value="N" ${menuData.useYn eq "N" ? "selected" : "" }>미사용</option>
		</select>
	</td>
</tr>
</tbody>
</table> 
</form>

</div>

<div style="text-align:center;padding:10px">
	<button type="button" class="btn btn-default" onClick="save()">저장</button>&nbsp;
	<button type="button" class="btn btn-default" onClick="window.close()">닫기</button>
</div> 
<script>


function save(){
	
	if( $("#menuNm").val().trim() == "" ){
		alert("메뉴명을 입력해주세요");
		$("#menuNm").focus();
		return;
	}
	if( $("#menuStp").val().trim() == "" ){
		alert("순서를 입력해주세요");
		$("#menuStp").focus();
		return;
	}
	$.ajax({      
		type : "POST",  
        url : "/adm/menu/insert",
        data :  $("#frm").serialize(),
        success : function(response){   
        	alert("저장되었습니다.")
			
        	<c:if test="${empty menu.menuSrl1 && empty menu.menuSrl2}">
        	window.opener.reloadMenu('menu1','','');
        	</c:if>
        	<c:if test="${!empty menu.menuSrl1 && empty menu.menuSrl2}">
        	window.opener.reloadMenu('menu2','${menu.menuSrl1}','');
        	</c:if>
        	<c:if test="${!empty menu.menuSrl1 && !empty menu.menuSrl2}">
        	window.opener.reloadMenu('menu3','${menu.menuSrl1}','${menu.menuSrl2}');
        	</c:if>
        	
        	window.close();
        },   
        error : function(xhr) {
            alert("에러 : " + xhr.status);
        }
	});  
	
}

</script>

