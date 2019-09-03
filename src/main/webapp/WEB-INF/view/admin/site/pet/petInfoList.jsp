<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<section class="content-header">
  <h1>
    펫정보
    <small>목록</small>  
  </h1>
  <ol class="breadcrumb">
    <li><a href="#"> 컨텐츠</a></li>
    <li class="active">펫정보</li>
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
		    <col style="width:4%">
		    <col style="width:10%">
		    <col style="width:16%">
		    <col style="width:10%">
		    <col style="width:8%">
		    <col style="width:8%">
		    <col style="width:8%">
		    <col style="width:8%">
		    <col style="width:8%">
		    <col style="width:8%">
		    <col style="width:10%">
		  </colgroup>
		<thead>
		<tr>
			<th class="text-center">번호</th>
			<th>종류</th>
			<th>이름</th>
			<th class="text-center">사이즈</th>
			<th class="text-center">적응</th>
			<th class="text-center">친근</th>
			<th class="text-center">미용</th>
			<th class="text-center">훈련</th>
			<th class="text-center">지능</th>
			<th class="text-center">활동</th>
			<th class="text-center">등록일</th>
		</tr>
		</thead>
		<tbody>
		
		<tr v-for="lst in vData.dataList" v-cloak>
			<td class="text-center">{{ lst.num  }}</td>
			<td>{{ lst.petCd }}</td>
			<td><a href="javascript:;" v-on:click="view(lst.petSrl)">{{ lst.petNm }}</a></td>
			<td class="text-center">{{ lst.petSize }}</a></td>
			<td class="text-center">{{ lst.spec1 }}</a></td>
			<td class="text-center">{{ lst.spec2 }}</a></td>
			<td class="text-center">{{ lst.spec3 }}</a></td>
			<td class="text-center">{{ lst.spec5 }}</a></td>
			<td class="text-center">{{ lst.spec5 }}</a></td>
			<td class="text-center">{{ lst.spec6 }}</a></td>
			<td class="text-center">{{ lst.regDt | timestampToDate }}</td>
		</tr> 
		
		<tr v-if="vData.totalPage == 0" v-cloak>
			<td class="text-center" colspan="11" style="height:150px;vertical-align: middle;">자료가 없습니다.</td>
		</tr>
		
		</tbody>
		</table> 
		
	</div>
	
	<div id="paging"></div>
	
	<div class="box-footer">
		<button type="button" onclick="regForm()" class="btn btn-primary btn-xm">등록</button>
	</div>
	
</div>	

</section>

<form name="urlFrm" method="GET">
	<input type="hidden" name="page" id="page"  value="">
	<input type="hidden" name="petSrl" id="petSrl" value="">
	<input type="hidden" name="searchString" value="">
</form>

<script>


var vObj = null;			//Vue 객체
var rowSize = 15;		//페이지당 보여줄 로우 수

$(document).ready(function() {

	//Vue 초기화
	vObj = com.initVue("#dataWrap",${petInfo.page });
	
	//검색
	$("#searchString").keyup(function(event) {
		if( event.keyCode == 13){
			search();
    	}
	});
	
	
});

//Ajax 데이터 추출,  Vue 에 정의된 함수명  
function getVdata(params){	
	
	//loading open
	//com.loading(".content-wrapper");
	
	var obj = {};
 	com.requestAjax({
		type: "POST",
		url : "/adm/board/petInfoJson", 
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
			
			com.selectPage("#paging",${petInfo.page });
			
		}else if($("#paging > ul").length > 0 ){
			com.updatePageItems("#paging", obj.totalRow)
		}
		
		//loading close 
		/* 
		setTimeout(function() {
			com.loadingClose(); 
		}, 200);
 		*/
		
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

function view(petSrl){
	document.urlFrm.action = "/adm/board/petInfoView";
	$("#petSrl").val(petSrl)
	$("#page").val (com.getCurrentPage("#paging") )
	document.urlFrm.submit();
}


function regForm(){
	document.urlFrm.action = "/adm/board/petInfoForm";
	document.urlFrm.submit();
}



</script>