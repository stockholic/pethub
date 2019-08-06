<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script src="/static/assets/fileUpload/js/jquery.uploadfile.min.js"></script>
<link rel="stylesheet" href="/static/assets/fileUpload/css/uploadfile.css">

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

<div class="x_content">

	<div id="fileuploader">Upload</div>
	
	<div style="clear:both">
		<button type="button" id="allCancel" class="btn btn-default">모두취소</button>
		<button type="button" id="send" class="btn btn-default">전송</button>
	</div>
	
	<div style="margin-top:20px" id="result"></div>
 
</div>

</section>

<script>

var uploadObj = null;
var fileData = {
	insertFile : [],
	deleteFile : [] 
}
var maxFileCount = 5;
var currentFileCount = 2;
$(document).ready(function() {
 	uploadObj = com.fileUpload({
 		id : "fileuploader",
 		url:"/adm/board/${flag}/fileUpload",
		fileName:"file",
		maxFileCount : maxFileCount - currentFileCount,
		maxFileSize:1024 * 1024 * 10,		//10M
		allowedTypes : "*"
 	},function(data){
 		$("#result").append("<div>" + JSON.stringify(data) + "</div>");
 		fileData.insertFile.push(data)
 	},function(){
 		$("#result").append("<div>-------------------- Complete --------------------</div>");
 		$("#result").append("<div>" + JSON.stringify(fileData) + "</div>");
 	}) ;
 	
 	$("#send").click(function(){
 		uploadObj.startUpload();
 	});
 	$("#allCancel").click(function(){
 		uploadObj.cancelAll();
 	});
});

</script>

