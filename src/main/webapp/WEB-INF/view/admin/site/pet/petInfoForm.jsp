<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tx" uri="http://www.pethub.kr/tags"%>

<link rel="stylesheet" href="/static/assets/fileUpload/css/uploadfile.css">
<link type="text/css" rel="stylesheet" href="/static/css/starrr.css">
<script src="/static/assets/fileUpload/js/jquery.form.js"></script>
<script src="/static/assets/fileUpload/js/jquery.uploadfile.min.js"></script>
<script src="/static/js/starrr.js"></script>


<section class="content-header">
  <h1>
    펫정보
    <small>작성</small>  
  </h1>
  <ol class="breadcrumb">
    <li><a href="#"> 컨텐츠</a></li>
    <li class="active">펫정보</li>
  </ol>
</section>


<section class="content container-fluid">

<div class="box box-default">
    <div class="box-body">
    
    
	<form id="regFrm" name="regFrm">
	<input type="hidden" name="petSrl" value="${petInfo.petSrl }">
	<input type="hidden" name="fileInfo" id="fileInfo" value="">
	
    <table class="table">
	  <colgroup>
	    <col width="10%">
	    <col width="90%">
	  </colgroup>
	<tbody>
	
	<tr>
		<th class="required">펫이름</th>
		<td><input type="text" class="form-control" name="petNm" id="petNm" value="${petInfoData.petNm }"></td>
	</tr>
	<tr>
		<th class="required">종류</th>
		<td>
			<select name="petCd" id="petCd" class="form-control" style="width:150px">
				<option value="">선택</option>
				<option value="dog" ${petInfoData.petCd eq "dog" ? "selected" : "" }>강아지</option>
				<option value="cat" ${petInfoData.petCd eq "cat" ? "selected" : "" }>고양이</option>
			</select>
		</td>
	</tr>
	<tr>
		<th class="required">사이즈</th>
		<td>
			<select name="petSize" id="petSize" class="form-control" style="width:150px">
				<option value="">선택</option>
				<option value="S" ${petInfoData.petSize eq "S" ? "selected" : "" }>소형</option>
				<option value="M" ${petInfoData.petSize eq "M" ? "selected" : "" }>중형</option>
				<option value="L" ${petInfoData.petSize eq "L" ? "selected" : "" }>대형</option>
			</select>
		</td>
	</tr>
	<tr>
		<th class="required">능력</th>
		<td>
		
			적응 : <div class='starrr' id="starrr1"></div> <input type="text" name="spec1" id="spec1" style="width:20px;margin:0px 10px;border:0"value="${petInfoData.spec1 }" readOnly>
			친근 : <div class='starrr' id="starrr2"></div> <input type="text" name="spec2" id="spec2" style="width:20px;margin:0px 10px;border:0" value="${petInfoData.spec2 }" readOnly>
			청결 : <div class='starrr' id="starrr3"></div> <input type="text" name="spec3" id="spec3" style="width:20px;margin:0px 10px;border:0" value="${petInfoData.spec3 }" readOnly><br>
			훈련 : <div class='starrr' id="starrr4"></div> <input type="text" name="spec4" id="spec4" style="width:20px;margin:0px 10px;border:0" value="${petInfoData.spec4 }" readOnly>
			지능 : <div class='starrr' id="starrr5"></div> <input type="text" name="spec5" id="spec5" style="width:20px;margin:0px 10px;border:0" value="${petInfoData.spec5 }" readOnly>
			활동 : <div class='starrr' id="starrr6"></div> <input type="text" name="spec6" id="spec6" style="width:20px;margin:0px 10px;border:0" value="${petInfoData.spec6 }" readOnly>
			
		</td>
	</tr>
	<tr>
		<th class="required">소개</th>
		<td><textarea id="intro" name="intro" class="form-control" style="height:130px">${petInfoData.intro }</textarea></td>
	</tr>
	<tr>
		<th class="required">특징</th>
		<td><textarea id="feature" name="feature" class="form-control"style="height:130px">${petInfoData.feature }</textarea></td>
	</tr>
	<tr>
		<th class="required">보살핌</th>
		<td><textarea id="care" name="care" class="form-control" style="height:130px">${petInfoData.care }</textarea></td>
	</tr>
	<tr>
		<th class="required">먹이</th>
		<td><input type="text" class="form-control" name="feed" id="feed" value="${petInfoData.feed }"></td> 
	</tr>
	<tr>
		<th class="required">기타</th>
		<td><input type="text" class="form-control" name="etc" id="etc" value="${petInfoData.etc }"></td> 
	</tr>
	
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
	</form>
    </div>
	
	<div class="box-footer">
		<button type="button" class="btn btn-primary btn-xm" onClick="list()">목록</button>
		<button type="button" class="btn btn-primary btn-xm" id="save">저장</button>
	 </div>
	    
</div>
 

</section>

<form name="urlFrm" method="POST">
	<input type="hidden" name="page" id="page" value="${petInfo.page }">
	<input type="hidden" name="petSrl" id="petSrl" value="${petInfo.petSrl }">
	<input type="hidden" name="searchString" value="${petInfo.searchString }">
</form>

<script>

//--------------------------------- file Upload start
var uploadObj = null;
var fileInfo = {
	insertFile : [],
	deleteFile : [] 
}
var maxFileCount = 5;
var fileCount = ${empty petInfoData.fileCnt ? 0 : petInfoData.fileCnt};
var allowFileCount = maxFileCount - fileCount;
$(document).ready(function() {
	
 	uploadObj = com.fileUpload({
 		id : "fileuploader",
 		url:"/fileUpload/petInfo",
		maxFileCount : allowFileCount,
		maxFileSize:1024 * 1024 * 10,		//10M
		allowedTypes : "*"
		
	//업로드 파일정보 처리
 	},function(data){
 		
 		console.log( data )
 		
 		fileInfo.insertFile.push(data)
 		
 	//모든 파일 업로드 후 처리
 	},function(){
 		send();
 	}) ;
 	
 	$("#save").click(function(){
 		save();
 	});
 	
 	
 	//Star ....
 	$('#starrr1').starrr({
 		rating: ${empty petInfoData.spec1 ? 0 : petInfoData.spec1},
		change: function(e, value){
			$("#spec1").val(value)
		}
	});
 	$('#starrr2').starrr({
 		rating: ${empty petInfoData.spec2 ? 0 : petInfoData.spec2},
		change: function(e, value){
			$("#spec2").val(value)
		}
	});
 	$('#starrr3').starrr({
 		rating: ${empty petInfoData.spec3 ? 0 : petInfoData.spec3},
		change: function(e, value){
			$("#spec3").val(value)
		}
	});
 	$('#starrr4').starrr({
 		rating: ${empty petInfoData.spec4 ? 0 : petInfoData.spec4},
		change: function(e, value){
			$("#spec4").val(value)
		}
	});
 	$('#starrr5').starrr({
 		rating: ${empty petInfoData.spec5 ? 0 : petInfoData.spec5},
		change: function(e, value){
			$("#spec5").val(value)
		}
	});
 	$('#starrr6').starrr({
 		rating: ${empty petInfoData.spec6 ? 0 : petInfoData.spec6},
		change: function(e, value){
			$("#spec6").val(value)
		}
	});
 	
});
 	
//--------------------------------- file Upload end 	

function list(){
	document.urlFrm.action = "/adm/board/petInfoList";
	document.urlFrm.submit();
}
function view(){
	document.urlFrm.action = "/adm/board/petInfoView";
	document.urlFrm.submit();
}

function save(){
	
	if( com.validation("#regFrm") == false ) return;
	
	
	if( uploadObj.getFileCount() > 0)
		uploadObj.startUpload();
	else
		send();		
	
}

function send(){
	
	$("#fileInfo").val(JSON.stringify(fileInfo));
	
	var url = $("#petSrl").val() != "" ? "/adm/board/updatePetInfo" : "/adm/board/insertPetInfo";
	
	com.requestAjax({
		type: "POST",
		url : url,
		params : $("#regFrm").serializeObject(),
	},function(data){
		$("#petSrl").val(data.petSrl)
       	view();
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
