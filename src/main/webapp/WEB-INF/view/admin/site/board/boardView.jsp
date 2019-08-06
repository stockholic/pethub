<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tx" uri="http://www.pethub.kr/tags"%>

<section class="content-header">
  <h1>
    공지사항
    <small>조회</small>  
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
	    <col width="50%">
	    <col width="50%">
	  </colgroup>
	<tbody>
	<tr>
		<td style="border-top:none">${boardInfo.userNm} <span style="font-size:12px;color:gray;">2017/11/09 12:12</span></td>
		<td style="border-top:none;text-align:right;font-size:12px"> 조회 : ${boardInfo.readCnt}</td>
	</tr>
	<tr>
		<td colspan="2" style="border-top:2px solid #efefef;font-weight: bold;">${boardInfo.title }</td>
	</tr>
	<tr>
		<td colspan="2" style="height:200px;vertical-align: top;">${boardInfo.content }</td>
	</tr>
	<tr>
		<td colspan="2" >
		<c:forEach var="lst" items="${fileList }">
			<p style="font-size: 13px"><a href="javascript:" onClick="fileDownLoad(${lst.fileSrl})"><i class="fa fa-fw fa-floppy-o"></i>  ${lst.fileRealNm }  (<tx:fileSize fileSize="${lst.fileSize }" />)</a></p>
		</c:forEach> 
		</td>
	</tr>
	</tbody>
	</table>
    
    </div>
    
	<div class="box-footer">
		<button type="button" class="btn btn-primary btn-xm" onClick="list()">목록</button>
		<button type="button" class="btn btn-primary btn-xm" onClick="update()">수정</button>
		<button type="button" class="btn btn-primary btn-xm" onClick="reply()">댓글</button>
		<button type="button" class="btn btn-primary btn-xm" onClick="remove()">삭제</button>
	 </div>
	 
	 
</div>



          

            

</section>

<form name="frm" method="POST">
	<input type="hidden" name="page" value="${board.page }">
	<input type="hidden" name="boardSrl" id="boardSrl" value="${board.boardSrl }">
	<input type="hidden" name="searchKey" value="${board.searchKey }">
	<input type="hidden" name="searchValue" value="${board.searchValue }">
</form>

<script>

function list(){
	document.frm.action = "/adm/board/${flag}/list";
	document.frm.submit();
}

function update(){ 
	document.frm.action = "/adm/board/${flag}/update/form"
	document.frm.submit();
}

function reply(){ 
	document.frm.action = "/adm/board/${flag}/reply/form"
	document.frm.submit();
}

function remove(){
	
	if( confirm("삭제하겠습니까?") == false ) return;
	
	$.ajax({      
    	type : "POST",  
        url : "/adm/board/${flag}/delete",
        data : {boardSrl : $("#boardSrl").val()},
	       beforeSend : function(xhr){
			xhr.setRequestHeader("AJAX", "true");
	    },
       success : function(response){
       	document.frm.action = "/adm/board/${flag}/list"
       	document.frm.submit();
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

function fileDownLoad(srl){ 
	document.location.href = "/fileDownLoad?srl=" + srl;
}	


</script>

