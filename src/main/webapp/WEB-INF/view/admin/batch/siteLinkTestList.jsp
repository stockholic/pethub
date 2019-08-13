<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div id="siteLinkData">
	<ul v-for="lst in siteData">
		<li>
			<div v-if="lst.dataTitle"><b>{{ lst.num }}. [{{ lst.dataId }}]  {{ lst.dataTitle }}</b></div>
			<div v-if="lst.dataLink">Link : <a v-bind:href="lst.dataLink" target="_blank">{{ lst.dataLink }}</a></div>
			<div v-if="lst.dataImg">Image : <a v-bind:href="lst.dataImg" target="_blank">{{ lst.dataImg }}</a></div>
			<div v-if="lst.dataContent">{{ lst.dataContent }}</div>
		</li>
	</ul>
</div>
<div class="text-center">
	<button type="button" onclick="regSiteLinkData()" class="btn btn-xm">저장</button>&nbsp;&nbsp;
	<button type="button" onclick="com.popupClose()" class="btn btn-xm">닫기</button>
</div>

<script>
//Site Test Vue 초기화
sObj = new Vue({
		el: "#siteLinkData",
		data : {
			siteData : []
		},
		updated : function(){
			console.log($(".jBox-content")[0].scrollHeight)
			$(".jBox-content").scrollTop($(".jBox-content")[0].scrollHeight);
		}
	});
	
	
//사이트 데이터 등록
function regSiteLinkData(){
	
	if( sObj.siteData.length < 2  ) return;
	
	var regParams = {}
	
	regParams = com.converListToObject("dataList",sObj.siteData);
	regParams.siteSrl = _siteSrl;
	regParams.linkSrl = _linkSrl;
	
	com.confirm({
		content : "등록 하겠습니까 ?",
		confirm : function(){
			
			var obj = com.requestAjax({
				type: "POST",
				url : "/adm/batch/insertSiteLinkData",
				params : regParams
				
			},function(data){
				if (data.result > 0){
					com.notice("등록 되었습니다.")
					com.popupClose();
				}
				com.confirmClose();
			});
			
			
		},
		cancel : function(){
			com.confirmClose();
		}
	});
	

}
</script>