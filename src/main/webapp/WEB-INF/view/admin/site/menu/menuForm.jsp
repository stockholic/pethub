<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<form id="regFrm" name="regFrm">
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
	<th class="required">메뉴명</th>
	<td><input type="text" class="form-control" name="menuNm" id="menuNm" value="${menuData.menuNm }"></td>
</tr>
<tr>
	<th>경로</th>
	<td><input type="text" class="form-control" name="menuUrl" id="menuUrl" value="${menuData.menuUrl }"></td>
</tr>
<tr>
	<th class="required">순서</th>
	<td><input type="text" class="form-control" name="menuStp" id="menuStp" maxlength="3" value="${menuData.menuStp }" onKeydown="com.numberInput(event)"></td>
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


<div style="text-align:center;padding:10px">
	<button type="button" class="btn btn-default" onClick="save()">저장</button>&nbsp;
	<button type="button" class="btn btn-default" onClick="com.popupClose()">닫기</button>
</div> 
<script>


function save(){
	
	if( com.validation("#regFrm") == false ) return;
	
	com.requestAjax({
		type: "POST",
		url : "/adm/menu/insert",
		params : $("#regFrm").serializeObject(),
	},function(data){
		com.notice("저장 되었습니다.")
		
		<c:if test="${empty menu.menuSrl1 && empty menu.menuSrl2}">
        	reloadMenu('menu1','','');
       	</c:if>
       	<c:if test="${!empty menu.menuSrl1 && empty menu.menuSrl2}">
        	reloadMenu('menu2','${menu.menuSrl1}','');
       	</c:if>
       	<c:if test="${!empty menu.menuSrl1 && !empty menu.menuSrl2}">
        	reloadMenu('menu3','${menu.menuSrl1}','${menu.menuSrl2}');
       	</c:if>
		
		com.popupClose();
	});
	
}

</script>

