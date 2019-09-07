<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tx" uri="http://www.pethub.kr/tags"%>


<div class="top-menu">
	<div>
		<a href= "/">분양</a> | 
		<a href= "/breed/list" class="menu-selected">품종</a>
	</div>
</div>

<div class="row search-wrap">

	<div class="col-3 logo">
		<a href= "/">Pethub.kr</a>
	</div>
	
  	<div class="col-6 text-center" id="searchInput">
  	<div class="search-input">
		<div class="form-group has-search">
		<span class="fa fa-search form-control-feedback"></span>
    	<input type="text" id="searchString" value="${petInfo.searchString }" maxlength="20" class="form-control" placeholder="Search">
  		</div>
  	</div>
  	</div>
  	
	<div class="col-3 text-right last-update">
	</div>
</div>

<div class="breed-menu">
	<a href="/breed/list?petSize=S" class="${petInfo.petSize eq 'S' ? 'menu-selected' : '' }">소형견</a> | 
	<a href="/breed/list?petSize=M" class="${petInfo.petSize eq 'M' ? 'menu-selected' : '' }">중형견</a> | 
	<a href="/breed/list?petSize=L" class="${petInfo.petSize eq 'L' ? 'menu-selected' : '' }">대형견</a> 
</div>

<div class="row">
<c:set var="cnt" value="1" />
<c:forEach var="lst" items="${list }" varStatus="status">
  	<div class="col-sm text-center" style="margin-bottom: 20px">
  		<div><a href="/breed/view/${lst.petSrl}"><img class="list breed-img" src="${lst.petImg}" onerror="$(this).hide()"></a></div>
  		<div class="list breed-title"><a href="/breed/view/${lst.petSrl}">${lst.petNm}</a></div>
 	</div>
<c:if test="${cnt % 3 == 0}">
</div>
<div class="row">
</c:if>

<c:set var="cnt" value="${cnt+1}" />
</c:forEach>
</div>


<script>

$(document).ready(function() {
	
	//검색
	$("#searchString").keyup(function(event) {
		if (event.keyCode == 13) {
	    	if( $(this).val().trim().length  == 0){
	    		document.location.href = "/breed/list?petSize=${petInfo.petSize}"
	    	}else if( $(this).val().trim().length  > 1){
	         document.location.href = "/breed/list/" + $(this).val() + "?petSize=${petInfo.petSize}";
	    	}
	    }
	});
	
});

</script>

