<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tx" uri="http://www.pethub.kr/tags"%>


<div class="top-menu">
	<div>
		<a href= "/">분양</a> | 
		<span style="font-size: 14px;font-weight: bold;">품종</span>
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


<div id="list_wrap">

	<ul class="list-unstyled">
	  
	  <c:forEach var="lst" items="${list }" varStatus="status">
	  <li class="media my-4" v-for="lst in dataList">
	    <img class="mr-3" src="https://cdn3-www.dogtime.com/assets/uploads/2011/01/file_22944_saint-bernard-460x290.jpg" style="max-width: 120px; min-width: 120px" onerror="$(this).hide()">
	    
	    <div class="media-body">
	      <h5 class="mt-0 mb-1">
	      	<a href="#">${lst.petNm}</a>
	      </h5>
	     ${lst.intro}
	    </div>
	  </li>
	  <hr/>
	  </c:forEach>
	  

	</ul>
	
	
</div>



<script>

$(document).ready(function() {
	
	//검색
	$("#searchString").keyup(function(event) {
		if (event.keyCode == 13) {
	    	if( $(this).val().trim().length  == 0){
	    		document.location.href = "/breed/list"
	    	}else if( $(this).val().trim().length  > 1){
	         document.location.href = "/breed/list/" + $(this).val();
	    	}
	    }
	});

	
});

</script>

