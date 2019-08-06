<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tx" uri="http://www.pethub.kr/tags"%>

<tx:selectMenuList />

<%-- 
<c:forEach var="lst1" items="${menuList }">
<div style="font-weight: bold;height:24px">
	<c:choose>
	<c:when test="${!empty lst1.menuUrl}"><a href="${lst1.menuUrl }">${lst1.menuNm }</a></c:when>
	<c:otherwise>${lst1.menuNm }</c:otherwise>
	</c:choose>
</div>
	
	<c:forEach var="lst2" items="${lst1.menuLev2 }">
	<div style="height:24px;padding-left:15px;">└ 
		<c:choose>
		<c:when test="${!empty lst2.menuUrl}"><a href="${lst2.menuUrl }">${lst2.menuNm }</a></c:when>
		<c:otherwise>${lst2.menuNm }</c:otherwise>
		</c:choose>
	</div>
	
		<c:forEach var="lst3" items="${lst2.menuLev3 }">
		<div style="height:24px;padding-left:30px;">└ 
			<c:choose>
			<c:when test="${!empty lst3.menuUrl}"><a href="${lst3.menuUrl }">${lst3.menuNm }</a></c:when>
			<c:otherwise>${lst3.menuNm }</c:otherwise>
			</c:choose>
		</div>
		</c:forEach>
		
	</c:forEach>
	
</c:forEach> 
--%>
 <c:forEach var="lst1" items="${menuList }">
<li class="treeview">
	<c:choose>
	<c:when test="${!empty lst1.menuUrl}"><a href="${lst1.menuUrl }" ><i class="fa fa-folder"></i> <span>${lst1.menuNm }</span></a></c:when>
	<c:otherwise>
		<a href="#">
			<i class="${lst1.menuSrl1 eq '1' ? 'fa fa-fw fa-cog':'fa fa-folder' }"></i> <span>${lst1.menuNm }</span>
			<span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
		</a>
		</c:otherwise>
	</c:choose>
	
	<c:if test="${fn:length(lst1.menuLev2) > 0}"><ul class="treeview-menu"></c:if>
	<c:forEach var="lst2" items="${lst1.menuLev2 }">
		<li>
		<c:choose>
		<c:when test="${!empty lst2.menuUrl}"><a href="${lst2.menuUrl }">${lst2.menuNm }</a></c:when>
		<c:otherwise><a href="#">${lst2.menuNm }<span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span></a></c:otherwise>
		</c:choose>
		
			<c:if test="${fn:length(lst2.menuLev3) > 0}"><ul class="treeview-menu"></c:if>
			<c:forEach var="lst3" items="${lst2.menuLev3 }">
				<li>
				<c:choose>
				<c:when test="${!empty lst3.menuUrl}"><a href="${lst3.menuUrl }">${lst3.menuNm }</a></c:when>
				<c:otherwise><a href="#">${lst3.menuNm }</a></c:otherwise>
				</c:choose>
				</li>
			</c:forEach>				
			<c:if test="${fn:length(lst2.menuLev3) > 0}"></ul></c:if>
		</li>
	</c:forEach>
	<c:if test="${fn:length(lst1.menuLev2) > 0}"></ul></c:if> 
</li> 
</c:forEach>
 
