<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% pageContext.setAttribute("LF", "\n"); %>

<link type="text/css" rel="stylesheet" href="/static/css/starrr.css">
<script src="/static/js/starrr.js"></script>

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


<div class="row" style="margin-top: 30px">
	<div class="col-sm text-center"><img class="mr-3 view breed-img" id="breed-img" src="${petInfoData.petImg }" onerror="$(this).hide()"></div>
	<div class="col-sm"> 
		<div class="view breed-title">${petInfoData.petNm}</div>
	   <div class="view spec">
			적응 : <div class='starrr' id="starrr1"></div><br>
			친밀 : <div class='starrr' id="starrr2"></div><br>
			훈련 : <div class='starrr' id="starrr3"></div><br>
			지능 : <div class='starrr' id="starrr4"></div><br>
			활동 : <div class='starrr' id="starrr5"></div>
		</div>	
		
		 출처 : <a href="${petInfoData.link }" target="_blank">${petInfoData.link }</a>
	</div>
</div>

<div class="view intro">
 	<h5>소개</h5>
 	<div>${fn:replace(petInfoData.intro , LF, '<br>')}</div>
 </div>

 <div class="view intro">
 	<h5>특징</h5>
 	<div>${fn:replace(petInfoData.feature , LF, '<br>')}</div>
 </div>
 
 <div class="view intro">
 	<h5>보살핌</h5>
 	<div>${fn:replace(petInfoData.care , LF, '<br>')}</div>
 </div>
 
 <div class="view intro">
 	<h5>먹이</h5>
 	<div>${fn:replace(petInfoData.feed , LF, '<br>')}</div>
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
	
	$('#starrr1').starrr({
	  rating: ${empty petInfoData.spec1 ? 0 : petInfoData.spec1},readOnly: true
	});
	$('#starrr2').starrr({
	  rating: ${empty petInfoData.spec2 ? 0 : petInfoData.spec2},readOnly: true
	});
	$('#starrr3').starrr({
	  rating: ${empty petInfoData.spec3 ? 0 : petInfoData.spec3},readOnly: true
	});
	$('#starrr4').starrr({
	  rating: ${empty petInfoData.spec4 ? 0 : petInfoData.spec4},readOnly: true
	});
	$('#starrr5').starrr({
	  rating: ${empty petInfoData.spec5 ? 0 : petInfoData.spec5},readOnly: true
	});
	
});

</script>

