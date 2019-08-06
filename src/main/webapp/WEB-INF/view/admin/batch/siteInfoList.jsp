<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<section class="content-header">
  <h1>
    사이트관리
    <small>목록</small>  
  </h1>
  <ol class="breadcrumb">
    <li><a href="#"> 배치관리</a></li>
    <li class="active">사이트관리</li>
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
		    <col style="width:80px">
		     <col style="width:250px">
		     <col style="width:250px">
		     <col />
		     <col style="width:80px">
		  </colgroup>
		<thead>
		<tr>
			<th class="text-center">번호</th>
			<th>사이트 명</th>
			<th>사이트 URL</th>
			<th>기타</th>
			<th class="text-center">등록일</th>
		</tr>
		</thead>
		<tbody>
		
		<tr v-for="lst in vData.dataList" v-cloak>
			<td class="text-center">{{ lst.num | addComma }}</td>
			<td><a href="javascript:;" v-on:click="openUptForm(lst.siteSrl)">{{ lst.siteNm }}</a></td>
			<td><a v-bind:href="lst.siteUrl" target="_blank">{{ lst.siteUrl }}</a></td>
			<td>{{ lst.siteEtc }}</a></td>
			<td class="text-center">{{ lst.regDt | timestampToDate }}</td>
		</tr>
		
		<tr v-if="vData.totalPage == 0" v-cloak>
			<td class="text-center" colspan="5" style="height:150px;vertical-align: middle;">자료가 없습니다.</td>
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


//Ajax 데이터 추출,  Vue 에 정의된 함수명 
function getVdata(params){	
	
	var obj = {};
 	com.requestAjax({
		type: "POST",
		url : "/adm/batch/siteInfoJson", 
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
		title : "사이트 등록",
		width : 600,
		height : 250,
		url : "/adm/batch/siteInfoForm"
	})
}

//수정폼 호출
function openUptForm(siteSrl){
	com.popup({
		title : "사이트 수정",
		width : 600,
		height : 250,
		url : "/adm/batch/siteInfoForm",
		params : {siteSrl : siteSrl}
	})
}



</script>