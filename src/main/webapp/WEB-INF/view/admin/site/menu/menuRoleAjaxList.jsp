<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table class="table tb2">
  <colgroup>
    <col />
    <col width="50px">
  </colgroup>
    <tbody>
    <c:forEach var="lst" items="${menuList }">
    <c:set var="node1" value="" />	 
    <c:set var="node2" value="" />	 
    <c:set var="node3" value="" />	 
    <c:set var="checked" value="" />	 
    <tr class="<c:if test="${lst.menuLev eq '1'  }">node1</c:if>">
        <td>
        	<c:choose>
        	<c:when test="${lst.menuLev eq '1' }">${lst.menuNm } </c:when>
        	<c:when test="${lst.menuLev eq '2' }"><span class="node2">└ ${lst.menuNm }</span> </c:when>
        	<c:when test="${lst.menuLev eq '3' }"><span class="node3">└ ${lst.menuNm }</span> </c:when>
        	</c:choose>
        	
           	<c:set var="node1" value="group${lst.menuSrl1 }" />	        	
        	<c:if test="${lst.menuLev eq '2' }"><c:set var="node2" value=" group${lst.menuSrl2 }" /></c:if>
        	<c:if test="${lst.menuLev eq '3' }"><c:set var="node3" value=" group${lst.menuSrl2 } group${lst.menuSrl3 }" /></c:if>
        	<c:if test="${lst.useYn eq 'Y' }"><c:set var="checked" value="checked" /></c:if>
        </td>
        <td>
        	<input type="checkbox" class="${node1}${node2}${node3}" onClick="checkMenu(this,'${node1}${node2}${node3}')" name="menuSrl" value="${lst.menuSrl}" ${checked}>
        </td>
    </tr>
    </c:forEach>
    <c:if test="${empty menuList }">
	<tr>
		<td colspan="2" style="text-align:center;height:100px">자료가 없습니다.</td>
	</tr>
	</c:if>
    </tbody>
</table>	
