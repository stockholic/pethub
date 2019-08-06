<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tx" uri="http://www.pethub.kr/tags"%>

<link rel="stylesheet" href="/static/assets/fileUpload/css/uploadfile.css">
<link rel="stylesheet" href="/static/assets/summernote/summernote.css">

<script src="/static/assets/summernote/summernote.min.js"></script>
<script src="/static/assets/summernote/lang/summernote-ko-KR.js"></script>

<script src="/static/assets/fileUpload/js/jquery.form.js"></script>
<script src="/static/assets/fileUpload/js/jquery.uploadfile.min.js"></script>


<section class="content-header">
  <h1>
    공지사항
    <small>작성</small>  
  </h1>
  <ol class="breadcrumb">
    <li><a href="#"> Level</a></li>
    <li class="active">Here</li>
  </ol>
</section>


<section class="content container-fluid">

<div class="box box-default">
    <div class="box-body">
    
    
    <table class="table">
	  <colgroup>
	    <col width="15%">
	    <col width="85%">
	  </colgroup>
	<tbody>
	<form id="frm" name="frm">
	<input type="hidden" name="boardSrl" value="${board.boardSrl }">
	<input type="hidden" name="fileInfo" id="fileInfo" value="">
	<tr>
		<th>제목</th>
		<td><input type="text" class="form-control" name="title" id="title" value="${boardInfo.title }"></td>
	</tr>
	<tr>
		<td colspan="2">
		<textarea id="content" name="content" style="display: none;" class="form-control">${boardInfo.content }</textarea>
		</td>
	</tr>
	</form>
	<tr>
		<td colspan="2">
		<c:forEach var="lst" items="${fileList }">
			<p style="font-size: 13px">
				<a href="javascript:" onClick="fileDownLoad(${lst.fileSrl})"><i class="fa fa-fw fa-floppy-o"></i>  ${lst.fileRealNm }  (<tx:fileSize fileSize="${lst.fileSize }" />)</a>
				&nbsp;&nbsp;<a href="javascript:" onClick="removeFile(this,${lst.fileSrl })"><span style="color:#000;text-decoration: underline;">삭제</span></a>
			</p>
		</c:forEach>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<div id="fileuploader"></div>
		</td>
	</tr>
	</tbody>
	</table> 
    </div>
	
	<div class="box-footer">
		<button type="button" class="btn btn-primary btn-xm" onClick="list()">목록</button>
		<button type="button" class="btn btn-primary btn-xm" id="save">저장</button>
		<!-- <button type="button" class="btn btn-primary btn-xm" id="allCancel" >파일모두최소</button> -->
	 </div>
	    
</div>
 

</section>

<form name="urlFrm" method="POST">
	<input type="hidden" name="page" id="page" value="${board.page }">
	<input type="hidden" name="boardSrl" id="boardSrl" value="${board.boardSrl }">
	<input type="hidden" name="searchKey" value="${board.searchKey }">
	<input type="hidden" name="searchValue" value="${board.searchValue }">
</form>

<script>

//--------------------------------- file Upload start
var uploadObj = null;
var fileInfo = {
	insertFile : [],
	deleteFile : [] 
}
var maxFileCount = 5;
var fileCount = ${empty boardInfo.fileCnt ? 0 : boardInfo.fileCnt};
var allowFileCount = maxFileCount - fileCount;
$(document).ready(function() {
	
 	uploadObj = com.fileUpload({
 		id : "fileuploader",
 		url:"/fileUpload/board/${flag}",
		maxFileCount : allowFileCount,
		maxFileSize:1024 * 1024 * 10,		//10M
		allowedTypes : "*"
		
	//업로드 파일정보 처리
 	},function(data){
 		fileInfo.insertFile.push(data)
 		
 	//모든 파일 업로드 후 처리
 	},function(){
 		send();
 	}) ;
 	
 	$("#save").click(function(){
 		save();
 	});
/*
 	$("#allCancel").click(function(){
 		uploadObj.cancelAll();
 	});
 */	
 	com.editor("content");
});
 	
//--------------------------------- file Upload end 	

function list(){
	document.urlFrm.action = "/adm/board/${flag}/list";
	document.urlFrm.submit();
}
function view(){
	document.urlFrm.action = "/adm/board/${flag}/view";
	document.urlFrm.submit();
}

function save(){
	
	if( $("#title").val().trim() == "" ){
		alert("제목을 입력해주세요");
		$("#title").focus();
		return;
	}
	if( $("#content").val().trim() == "" ){
		alert("내용을 입력해주세요");
		return;
	}
	
	
	if( uploadObj.getFileCount() > 0)
		uploadObj.startUpload();
	else
		send();		
	
}

function send(){
	$("#fileInfo").val(JSON.stringify(fileInfo));
	
	$.ajax({      
		type : "POST",  
        url : "/adm/board/${flag}/${action}",
        data :  $("#frm").serialize(),
        beforeSend : function(xhr){
			xhr.setRequestHeader("AJAX", "true");
	    },
        success : function(response){
        	$("#boardSrl").val(response.boardSrl)
        	view();
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

function removeFile(obj,srl){
	allowFileCount++;
	$("#maxFileCount").text(allowFileCount)
	uploadObj.update({maxFileCount : allowFileCount});
	$(obj).closest("p").remove();
	
	fileInfo.deleteFile.push(srl);
	
}

</script>
