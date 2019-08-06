<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<table style="font-size: 12px;margin-bottom:10px">
<tr>
	<td><label>${cacheNm }</label> - Total :  ${fn:length(cacheElementList)}</td>
</tr>
</table>
<table class="table table-hover" style="font-size: 12px">
  <colgroup>
    <col width="12%">
    <col />
    <col width="12%">
    <col width="12%">
    <col width="12%">
    <col width="12%">
    <col width="5%">
  </colgroup>
<thead>
<tr>
	<th>CacheName</th>
	<th>CacheKey</th>
	<th>HitCount</th>
	<th>CreationTime</th>
	<th>ExpirationTime</th>
	<th>LastAccessTime</th>
	<th>IsExpired</th>
	<th></th>
</tr>
</thead>
<tbody>
<c:forEach var="lst" items="${cacheElementList }" varStatus="status">
<tr>
	<td>${lst.cacheNm }</td>
	<td>${lst.cacheKey }</td>
	<td>${lst.hitCount }</td>
	<td><fmt:formatDate value="${lst.creationTime}" pattern="MM/dd HH:mm:ss" /></td>
	<td><fmt:formatDate value="${lst.expirationTime}" pattern="MM/dd HH:mm:ss" /></td>
	<td><fmt:formatDate value="${lst.lastAccessTime}" pattern="MM/dd HH:mm:ss" /></td>
	<td>${lst.expired }</td>
	<td><a href="javascipt:" onClick="removeCache('${lst.cacheNm }','${lst.cacheKey }', this)">delete</a></td>
</tr>
</c:forEach>
<c:if test="${empty cacheElementList }">
<tr>
	<td colspan="8" style="height:100px;text-align:center;vertical-align: middle">No Cache</td>
</tr>
</c:if>

</tbody>
</table>
