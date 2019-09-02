<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<%@ taglib prefix="tx" uri="http://www.pethub.kr/tags"%>
<% pageContext.setAttribute("LF", "\n"); %>

<link type="text/css" rel="stylesheet" href="/static/css/starrr.css">
<script src="/static/js/starrr.js"></script>


<style>
.spec{margin: 20px 0px;line-height: 24px}
.title{height:30px; font-weight: bold;}
.cts{padding-left: 12px}
</style>


<section class="content-header">
  <h1>
     펫정보
    <small>조회</small>  
  </h1>
  <ol class="breadcrumb">
    <li><a href="#"> 컨텐츠</a></li>
    <li class="active">펫정보</li>
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
		<td style="border-top:none">${petInfoData.petNm} - ${petInfoData.petSize}</td>
		<td style="border-top:none;text-align:right"><span style="font-size:12px;color:gray;"><fmt:formatDate value="${petInfoData.regDt}" pattern="yyyy/MM/dd HH:mm:ss" /></span></td>
	</tr>

	<tr>
		<td><img  alt="보더콜리"> ${petInfoFileUrl}  aaaa </td> 
		<td>
			<div class="spec">
			적응 : <div class='starrr' id="starrr1"></div><br>
			친밀 : <div class='starrr' id="starrr2"></div><br>
			청결 : <div class='starrr' id="starrr3"></div><br>
			훈련 : <div class='starrr' id="starrr4"></div><br>
			지능 : <div class='starrr' id="starrr5"></div><br>
			활동 : <div class='starrr' id="starrr6"></div>
			</div>
			
			출처 : <a href="${petInfoData.etc }" target="_blank">${petInfoData.etc }</a>
			
		</td>	
	</tr>
	<tr>	
		<td colspan="2">
			<div class="title">소개</div>
			<div class="cts">${fn:replace(petInfoData.intro , LF, '<br>')}</div>
			<hr>
			
			<div class="title">특징</div>
			<div class="cts">${fn:replace(petInfoData.feature , LF, '<br>')}</div>
			<hr>
			
			<div class="title">보살핌</div>
			<div class="cts">${fn:replace(petInfoData.care , LF, '<br>')}</div>
			<hr>
			
			<div class="title">먹이</div>
			<div class="cts">${fn:replace(petInfoData.feed , LF, '<br>')}</div>
			<hr>
		</td> 
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
		<button type="button" class="btn btn-primary btn-xm" onClick="remove()">삭제</button>
	 </div>
	 
	 
</div>

            

</section>

<form name="frm" method="POST">
	<input type="hidden" name="page" id="page" value="${petInfo.page }">
	<input type="hidden" name="petSrl" id="petSrl" value="${petInfo.petSrl }">
	<input type="hidden" name="searchString" value="${petInfo.searchString }">
</form>

<script>

$(document).ready(function() {

	//https://github.com/dobtco/starrr
	
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
	$('#starrr6').starrr({
	  rating: ${empty petInfoData.spec6 ? 0 : petInfoData.spec6},readOnly: true
	});
	
});

function list(){
	document.frm.action = "/adm/board/petInfoList";
	document.frm.submit();
}

function update(){ 
	document.frm.action = "/adm/board/petInfoForm"
	document.frm.submit();
}


function remove(){
	
	com.confirm({
		content : "삭제 하겠습니까 ?",
		confirm : function(){
			var obj = com.requestAjax({
				type: "POST",
				url : "/adm/board/deletePetInfo",
				params : { petSrl : $("#petSrl").val() },
			},function(data){
				if (data.result > 0){
					com.notice("삭제 되었습니다.")
					com.confirmClose();
					goPage();
				}
				
				document.frm.action = "/adm/board/petInfoList"
		       	document.frm.submit();
				
			});
		},
		cancel : function(){
		}
	});
	
}

function fileDownLoad(srl){ 
	document.location.href = "/fileDownLoad?srl=" + srl;
}	


</script>

