<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style>
	#fileWrap{
		width:600px;
	}
	#fileWrap div{
		margin-bottom:5px;
	}
</style>

<script src="/static/assets/bootstrap/dist/js/bootstrap-filestyle.min.js"></script>

<section class="content-header">
  <h1>
    파일업로드
    <small>다중업로드</small>  
  </h1>
  <ol class="breadcrumb">
    <li><a href="#"> Level</a></li>
    <li class="active">Here</li>
  </ol>
</section>


<section class="content container-fluid">

<form name="uploadFrm" id="uploadFrm"  method="post" enctype="multipart/form-data"> 

<div id="fileWrap">
	<div><input type="file" name="file" class="filestyle" data-buttonText="파일선택" data-icon="false" data-size="sm"/></div>
	<div><input type="file" name="file" class="filestyle" data-buttonText="파일선택" data-icon="false" data-size="sm"/></div>
	<div><input type="file" name="file" class="filestyle" data-buttonText="파일선택" data-icon="false" data-size="sm"/></div>
	<div><input type="file" name="file" class="filestyle" data-buttonText="파일선택" data-icon="false" data-size="sm"/></div>
	<div><input type="file" name="file" class="filestyle" data-buttonText="파일선택" data-icon="false" data-size="sm"/></div>
</div>
</form>

<button class="btn btn-default" onClick="upload()">업로드</button>
</div>
</section>


<script>

function upload(){
	
	var fileCnt = 0;
	$("input[name=file]").each(function(){
		if( $(this).val() != "" ) fileCnt++;
	});
		
	if(fileCnt > 0) {
	
		$.ajax({      
			type : "POST",  
		    url : "/adm/board/${flag}/fileUpload",
		    processData: false,
	        contentType: false,
		    data : $('#uploadFrm'),
		    beforeSend : function(xhr){
				xhr.setRequestHeader("AJAX", "true");
		    },
		    success : function(response){   
		    	console.log( response )
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
}




</script>

