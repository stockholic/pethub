<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<form id="regFrm" id="regFrm">

<input type="hidden"name="siteSrl" id="siteSrl" value="${siteInfo.siteSrl}">

<table class="table">
  <colgroup>
    <col width="20%">
    <col width="80%">
  </colgroup>
<tbody>
<tr>
	<th class="required">사이트명</th>
	<td><input type="text" class="form-control" name="siteNm" id="siteNm" value="${siteInfo.siteNm }" style="width:200px"></td>
</tr>
<tr>
	<th>사이트 URL</th>
	<td><input type="text" class="form-control" name="siteUrl" id="siteUrl" value="${siteInfo.siteUrl}"></td>
</tr>
<tr>
	<th>기타</th>
	<td><input type="text" class="form-control" name="siteEtc" id="siteEtc" value="${siteInfo.siteEtc}"></td>
</tr>
</tbody>
</table>
</form> 

<div style="text-align:center;">
	<c:if test="${ empty siteInfo.siteSrl  }">
		<button type="button" class="btn btn-default" onClick="save()">저장</button>&nbsp;
		<button type="button" class="btn btn-default" onClick="com.popupClose()">닫기</button>
	</c:if>
	<c:if test="${ !empty siteInfo.siteSrl  }">
		<button type="button" class="btn btn-default" onClick="update()">수정</button>&nbsp;
		<button type="button" class="btn btn-default" onClick="remove()">삭제</button>&nbsp;
		<button type="button" class="btn btn-default" onClick="com.popupClose()">닫기</button>
	</c:if>
</div> 


<script>

// 등록
function save(){
	
	if( com.validation("#regFrm") == false ) return;
	
	com.requestAjax({
		type: "POST",
		async : false, 
		url : "/adm/batch/insertSiteInfo",
		params : $("#regFrm").serializeObject(),
	},function(data){
		if (data.result > 0){
			com.notice("저장 되었습니다.")
			com.popupClose();
			goPage(1);
		}
	});
}

// 수정
function update(){
	
	if( com.validation("#regFrm") == false ) return;
	
	com.requestAjax({
		type: "POST",
		async : false, 
		url : "/adm/batch/updateSiteInfo",
		params : $("#regFrm").serializeObject(),
	},function(data){
		if (data.result > 0){
			com.notice("수정 되었습니다.")
			com.popupClose();
			goPage();
		}
	});
}

// 삭제
function remove(){
	
	com.confirm({
		content : "삭제 하겠습니까 ?",
		confirm : function(){
			var obj = com.requestAjax({
				type: "POST",
				async : false, 
				url : "/adm/batch/deleteSiteInfo",
				params : { siteSrl : $("#siteSrl").val() },
			},function(data){
				if (data.result > 0){
					com.notice("삭제 되었습니다.")
					com.popupClose();
					goPage();
				}
			});
		},
		cancel : function(){
		}
	});
	
}

</script>

