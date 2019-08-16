<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<section class="content-header">
  <h1>
    사이트 링크관리
    <small>목록</small>  
  </h1>
  <ol class="breadcrumb">
    <li><a href="#"> 배치관리</a></li>
    <li class="active">사이트 링크관리</li>
  </ol>
</section>


<section class="content container-fluid">

<div class="box box-default">

    <div class="box-body" id="dataWrap">
    
    	<div class="form-group-sm pull-left">
		  <div class="form-group-sm">
			
			<div class="input-group input-group-sm" style="width: 250px;">
	             <input type="text" name="searchString" id="searchString" value="" class="form-control pull-right" placeholder="Search">
	             <div class="input-group-btn">
	               <button type="button" onClick="search()" class="btn btn-default"><i class="fa fa-search"></i></button>
	             </div>
           	</div>
			
		  </div>
	  	</div>
	  	
		<div class="pull-right" v-cloak>Total : {{ vData.totalRow | addComma }} [ {{ vData.page }} / {{ vData.totalPage }} ]</div> 
    
	    <table class="table table-hover table-top">
		  <colgroup>
		    <col style="width:60px">
		     <col style="width:60px">
		     <col style="width:150px">
		     <col style="width:120px">
		     <col />
		     <col style="width:80px">
		     <col style="width:80px">
		     <col style="width:80px">
		     <col style="width:80px">
		     <col style="width:100px">
		     <col style="width:100px">
		     <col style="width:120px">
		  </colgroup>
		<thead>
		<tr>
			<th class="text-center">번호</th>
			<th>구분</th>
			<th>사이트 명</th>
			<th>대상</th>
			<th>링크</th>
			<th class="text-center">링크수</th>
			<th class="text-center">데이터수</th>
			<th class="text-center">배치간격</th>
			<th class="text-center">사용여부</th>
			<th class="text-center">실행일</th>
			<th class="text-center">등록일</th>
			<th class="text-center"></th>
		</tr>
		</thead>
		<tbody>
		
		<tr v-for="lst in vData.dataList" v-cloak>
			<td class="text-center">{{ lst.num | addComma }}</td>
			<td v-bind:style="{'color': ( lst.linkCd == 'dog' ? '' : 'blue' )}">{{ lst.linkCd  =='dog' ? '강아지' : '고양이' }}</td>
			<td>{{ lst.siteNm }}</td>
			<td>{{ lst.linkNm }}</td>
			<td class="truncate-ellipsis"><a href="javascript:;" v-on:click="openUptForm(lst.linkSrl)">{{ lst.linkUrl }}</a></td>
			<td class="text-center">{{ lst.linkCnt}}</td>
			<td class="text-center">{{ lst.siteDataCnt | addComma}}</td>
			<td class="text-center">{{ lst.batchItv}}</td>
			<td class="text-center" v-bind:style="{'text-decoration': ( lst.useYn == 'Y' ? 'none' : 'line-through' )}">{{ lst.useYn == 'Y' ? '사용' : '미사용' }}</td>
			<td class="text-center">{{ lst.excDt | timestampToDate }}</td>
			<td class="text-center">{{ lst.regDt | timestampToDate }}</td>
			<td class="text-center">
				<a href="javascript:;" v-bind:href="lst.linkUrl" target="_blank">[보기]</a>&nbsp;&nbsp;
				<a href="javascript:;" v-on:click="siteLinkTest(lst.siteSrl, lst.linkSrl, lst.linkUrl, lst.linkCls, lst.linkMtdLst)">[테스트]</a>
			</td> 
		</tr>
		
		<tr v-if="vData.totalPage == 0" v-cloak>
			<td class="text-center" colspan="11" style="height:150px;vertical-align: middle;">자료가 없습니다.</td>
		</tr>
		
		</tbody>
		</table> 
		
	</div>
	
	<div id="paging"></div>
	
	<div class="box-footer">
		<button type="button" onclick="openRegForm()" class="btn btn-primary btn-xm">등록</button>
	</div>
	
	
</div>	

</section>


<script>


var vObj = null;			//Vue 객제
var rowSize = 15;		//페이지당 보여줄 로우 수

var sObj = null;			//Site Test Vue

$(document).ready(function() {

	//Vue 초기화
	vObj = com.initVue("#dataWrap");
	
	//검색
	$("#searchString").keyup(function(event) {
		if( event.keyCode == 13){
			search();
    	}
	});
	
	

});

// Ajax 데이터 추출,  Vue 에 정의된 함수명 
function getVdata(params){	
	
	//loading open
	//com.loading(".content-wrapper");
	
	var obj = {};
 	com.requestAjax({
		type: "POST",
		url : "/adm/batch/siteLinkJson", 
		params : params,
		
	//call back	
	},function(data){
		obj = data;
		//console.log(obj);
		
		vObj.vData = obj;
		
		//페이징 표시
		if( $("#paging > ul").length == 0  && data.totalRow > 0 ){
			com.initPaging({
				selector : "#paging",
				items : obj.totalRow,
				itemsOnPage : rowSize
			});
		}else if($("#paging > ul").length > 0 ){
			com.updatePageItems("#paging", obj.totalRow)
		}
		
		//loading close
		//com.loadingClose();
		
	});
	
	return obj;
}

// 검색
function search(){
	
	if( $("#searchString").val().trim().length  !=0 && $("#searchString").val().trim().length < 2 ) return;
	
	//페이징 새로 그리기 위해 제거
	com.pageDestroy("#paging");
	
	getVdata({
		rowSize : rowSize,
		searchString : $("#searchString").val().trim().length  > 1 ? $("#searchString").val() : ""
	});
	
}

//페이지 이동, 함수명 고정
function goPage(pageNumber){
	
	var page = pageNumber;
	
	if(page == undefined ? 1 : page);
	
	getVdata({
		page : page,
		rowSize : rowSize,
		searchString : $("#searchString").val().trim().length  > 1 ? $("#searchString").val() : ""
	});
	
}

//등록폼 호출
function openRegForm(){
	com.popup({
		title : "링크 등록",
		width : 700,
		height : 540,
		url : "/adm/batch/siteLinkForm"
	})
}

//수정폼 호출
function openUptForm(linkSrl){
	com.popup({
		title : "링크 수정",
		width : 700,
		height : 540,
		url : "/adm/batch/siteLinkForm",
		params : {linkSrl : linkSrl}
	})
}


//----------------------------------------------------------- WebSocket Start
//사이트 테스트 WebSocket 연결
//nginx 연결시 소켓 타임아웃 설정해 줘야 한다.
function wsConnect() {
	var host = "ws://"+window.location.hostname;
	var port = window.location.port
	var ws = new WebSocket(host + ":" + port+ "/console"); 
	
	ws.onmessage = function(message){
		console.log(JSON.parse(message.data));
		sObj.siteData.push( JSON.parse(message.data) );
	}
	
	
	//  서버에서 보냄 consoleLog.getConsole().send(mapper.writeValueAsString(cli) );
}
//----------------------------------------------------------- WebSocket End


//사이트 테스트 호출	Server-Sent Events
var _siteSrl = "";
var _linkSrl = "";
function siteLinkTest(siteSrl, linkSrl, linkUrl, linkCls, linkMtdLst){
	
	_siteSrl = siteSrl;
	_linkSrl = linkSrl;
	
	com.popup({
		title : "링크 작업결과",
		width : 1200,
		height : 700,
		zIndex : 9999,
		url : "/adm/batch/siteLinkTestList"
	});
	
	var params = {
		linkUrl : linkUrl,
		linkCls : linkCls,
		linkMtdLst : linkMtdLst
	}
	
	//console.log( encodeURI($.param(params, true)) );
			
	var url = "/adm/batch/siteLinkSseTest?" + encodeURI($.param(params, true));
	var es = new EventSource(url);
	
	es.onopen = function (message) {
		console.log("SSE open");
		com.loading("body");
	};

	//메세지 도착
	es.onmessage = function (message) {
		console.log(JSON.parse(message.data));
		sObj.siteData.push( JSON.parse(message.data) );
	};

	//연결종료나 에러발생시 호출
	es.onerror = function (message) {
		es.close();
		com.loadingClose();
		console.log("SSE error OR close");
	};
	
}


</script>