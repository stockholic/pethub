<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<form id="regFrm" id="regFrm">

<input type="hidden"name="linkSrl" id="linkSrl" value="${siteLink.linkSrl}">

<table class="table">
  <colgroup>
    <col width="20%">
    <col width="80%">
  </colgroup>
<tbody>
<tr>
	<th>구분</th>
	<td>
	<select name="linkCd" id="linkCd" class="form-control" style="width:150px">
		<option value="dog" ${siteLink.linkCd eq "dog" ? "selected" : "" }>강아지</option>
		<option value="cat" ${siteLink.linkCd eq "cat" ? "selected" : "" }>고양이</option>
	</select>
	</td>
</tr>
<tr>
	<th class="required">사이트</th>
	<td>
	<select name="siteSrl" id="siteSrl" class="form-control" style="width:300px">
		<option value="">선택</option>
		<c:forEach var="lst" items="${siteInfoLIst }" varStatus="status">
		<option value="${lst.siteSrl }" ${lst.siteSrl eq siteLink.siteSrl ? "selected" : "" }>${lst.siteNm }</option>
		</c:forEach>
	</select>
	</td>
</tr>
<tr>
	<th>링크</th>
	<td><input type="text" class="form-control" name="linkUrl" id="linkUrl" value="${siteLink.linkUrl}"></td>
</tr>
<tr>
	<th class="required">클래스</th>
	<td><input type="text" class="form-control" name="linkCls" id="linkCls" value="${siteLink.linkCls}"></td>
</tr>
<tr>
	<th class="required">제목메소드</th>
	<td><input type="text" class="form-control" name="linkMtdLst" id="linkMtdLst" value="${siteLink.linkMtdLst}" style="width:150px"></td>
</tr>
<tr>
	<th>내용메소드</th>
	<td><input type="text" class="form-control" name="linkMtdCts" id="linkMtdCts" value="${siteLink.linkMtdCts}" style="width:150px"></td>
</tr>
<tr>
	<th class="required">배치간격</th>
	<td class="form-inline">
		<input type="text" class="form-control" name="batchItvTime" id="batchItvTime" maxlength="2" style="width:80px" value="${siteLink.batchItv.substring(0,fn:length(siteLink.batchItv)-1 ) }" style=":80px" onKeydown="com.numberInput(event)">
		&nbsp;<input type="radio" name="batchItvType" value="H" ${empty siteLink.batchItv || siteLink.batchItv.substring(fn:length(siteLink.batchItv)-1) eq 'H' ? 'checked' : '' }>시
		&nbsp;<input type="radio" name="batchItvType" value="M" ${siteLink.batchItv.substring(fn:length(siteLink.batchItv)-1) eq 'M' ? 'checked' : '' }>분
		<input type="hidden" name="batchItv" id="batchItv" value="${siteLink.batchItv}">
	</td>
</tr>
<tr>
	<th>사용여부</th>
	<td>
	<select name="useYn" id="useYn" class="form-control" style="width:150px">
		<option value="Y" ${siteLink.useYn eq "Y" ? "selected" : "" }>사용</option>
		<option value="N" ${siteLink.useYn eq "N" ? "selected" : "" }>미사용</option>
	</select>
	</td>
</tr>
</tbody>
</table>
</form> 

<div style="text-align:center;">
	<c:if test="${ empty siteLink.linkSrl  }">
		<button type="button" class="btn btn-default" onClick="save()">저장</button>&nbsp;
		<button type="button" class="btn btn-default" onClick="com.popupClose()">닫기</button>
	</c:if>
	<c:if test="${ !empty siteLink.linkSrl  }">
		<button type="button" class="btn btn-default" onClick="update()">수정</button>&nbsp;
		<button type="button" class="btn btn-default" onClick="remove()">삭제</button>&nbsp;
		<button type="button" class="btn btn-default" onClick="com.popupClose()">닫기</button>
	</c:if>
</div> 


<script>

// 등록
function save(){
	
	if( com.validation("#regFrm") == false ) return;
	
	$("#batchItv").val( $("#batchItvTime").val() + $("input:radio[name=batchItvType]:checked").val());
	
	com.requestAjax({
		type: "POST",
		url : "/adm/batch/insertSiteLink",
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
	
	$("#batchItv").val( $("#batchItvTime").val() + $("input:radio[name=batchItvType]:checked").val());
	
	var obj = com.requestAjax({
		type: "POST",
		url : "/adm/batch/updateSiteLink",
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
				url : "/adm/batch/deleteSiteLink",
				params : { linkSrl : $("#linkSrl").val() },
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

