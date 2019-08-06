<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="category">
	<table class="table" style="width:350px">
	  <colgroup>
	    <col width="55%">
	    <col width="15%">
	    <col width="15%">
	    <col width="15%">
	  </colgroup>
	  <thead>
    <tr>
        <th>분류</th>
        <th>수정</th>
        <th>순서</th>
        <th>사용</th>
    </tr>
    </thead>
	    <tbody>
	    <c:forEach var="lst" items="${list }">
	    <tr>
	        <td>
	        	<div class="menuNm" onClick="subMenu(this,'${lst.menuSrl1}','${lst.menuSrl2}','${lst.menuSrl}' )">${lst.menuNm }<br>${lst.menuUrl}</div>
	        </td>
	        <td>
	        	<button class="btn btn-primary btn-xs" onClick="save(this,'${menu.menuSrl1 }','${menu.menuSrl2 }','${lst.menuSrl}')" >수정</button>
	        </td>
	        <td><input type="text" value="${lst.menuStp }" maxlength="3" name="menuStp" style="width:30px" onClick="this.select()"></td>
	        <td><input type="checkbox" name="useYn" ${lst.useYn eq "Y" ? "checked" : "" } value="${lst.menuSrl }"></td>
	    </tr>
	    </c:forEach>
	    <c:if test="${empty list }">
		<tr>
			<td colspan="4" style="text-align:center;">자료가 없습니다.</td>
		</tr>
		</c:if>
	    </tbody>
	</table>	
</div>

<div style="text-align:right">
	<button type="button" class="btn btn-primary btn-xm" onClick="save(this,'${menu.menuSrl1 }','${menu.menuSrl2 }','')">등록</button>	
	<button type="button" class="btn btn-primary btn-xm" onClick="saveStp(this,'${menu.menuSrl1 }','${menu.menuSrl2 }')">저장</button>	
	<button type="button" class="btn btn-primary btn-xm" onClick="deleteMenu(this,'${menu.menuSrl1 }','${menu.menuSrl2 }')">삭제</button>	
	<input type="hidden" name="menuSrl">
</div>

