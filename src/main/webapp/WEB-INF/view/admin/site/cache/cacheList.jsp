<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tx" uri="http://www.pethub.kr/tags"%>

<section class="content-header">
  <h1>
    캐쉬관리
    <small>EHCache 관리</small>  
  </h1>
  <ol class="breadcrumb">
    <li><a href="#"> Level</a></li>
    <li class="active">Here</li>
  </ol>
</section>

<section class="content container-fluid">
<div class="box box-default">
    <div class="box-body">
    
    <div class="col-md-3">
		<c:forEach var="lst" items="${cacheList }">
		<div class="box box-primary">
		    <div class="box-header with-border">
		      <h3 class="box-title" style="font-size: 13px;font-weight: bold;"> <a href="javascript:" onClick="getElement('${lst.cacheNm }')">${lst.cacheNm }</a></h3>
		      <div class="box-tools pull-right">
		     <!--  <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button> -->
		      </div>
		    </div>
		    <div class="box-body" style="font-size: 12px;line-height: 24px">
		      CacheSize : ${lst.cacheSize }<br>
		      CacheHitCount : ${lst.cacheHitCount }<br>
		      CacheExpiredCount : ${lst.cacheExpiredCount }<br>
		      CacheRemoveCount : ${lst.cacheRemoveCount }<br>
		      MemorySize : <tx:fileSize fileSize="${lst.memorySize }" />
		    </div>
		</div>
		</c:forEach>
	
	</div>
	<div class="col-md-9" id="element"></div>
    
    </div>
 </div>


</section>

<script>

function getElement(cacheNm){
	
   $.ajax({      
    	type : "POST",  
        url : "/adm/cache/elementList",
        data : {cacheNm : cacheNm},
        beforeSend : function(xhr){
			xhr.setRequestHeader("AJAX", "true");
	    },
        success : function(response){   
        	$("#element").html(response)
        },   
        error : function(xhr) {
        	if(xhr.status == "403"){
        		document.location.href = "/login";
        	}else{
    	        alert("에러 : " + xhr.status);
	       	}
        }
    });  
}

function removeCache(cacheNm, cacheKey, obj){
	
	if(confirm("삭제하겠습니까?") == false) return;
	
 	$.ajax({      
    	type : "POST",  
        url : "/adm/cache/delete",
        data : {cacheNm : cacheNm, cacheKey : cacheKey},
        beforeSend : function(xhr){
			xhr.setRequestHeader("AJAX", "true");
	    },
        success : function(response){   
        	if(response.result == true){
        		 $(obj).closest("tr").remove();
        	}
        },   
        error : function(xhr) {
        	if(xhr.status == "403"){
        		document.location.href = "/login";
        	}else{
    	        alert("에러 : " + xhr.status);
	       	}
        }
    });  
}

$(document).ready(function(){
	getElement("menuCache")
});

</script>