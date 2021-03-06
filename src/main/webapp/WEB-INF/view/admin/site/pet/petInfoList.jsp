<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<section class="content-header">
  <h1>
    품종관리
    <small>목록</small>  
  </h1>
  <ol class="breadcrumb">
    <li><a href="#">컨텐츠관리</a></li>
    <li class="active">품종관리</li>
  </ol>
</section>


<section class="content container-fluid">

<div class="box box-default">

    <div class="box-body" id="dataWrap">
     	
     	<form name="frm" id="frm" class="form-inline" onsubmit="return false">
		   <select name="petSize" id="petSize" class="form-control" onchange="search()">
				<option value="">사이즈</option>
				<option value="S" ${petInfo.petSize eq 'S' ? "selected" : "" }>소형</option>
				<option value="M" ${petInfo.petSize eq 'M' ? "selected" : "" }>중형</option>
				<option value="L" ${petInfo.petSize eq 'L' ? "selected" : "" }>대형</option>
			</select>
		
			<div class="input-group input-group-sm" style="width: 250px;">
	             <input type="text" name="searchString" id="searchString" value="${petInfo.searchString }" class="form-control pull-right" placeholder="Search">
	             <div class="input-group-btn">
	               <button type="button" onClick="search()" class="btn btn-default"><i class="fa fa-search"></i></button>
	             </div>
	         </div>
	         
	        <input type="hidden" name="page" id="page"  value="">
			<input type="hidden" name="petSrl" id="petSrl" value="">
	         
		</form>
		
	  	
		<div class="pull-right" v-cloak>Total : {{ vData.totalRow | addComma }} [ {{ vData.page }} / {{ vData.totalPage }} ]</div> 
	    <table class="table table-hover table-top">
		  <colgroup>
		    <col style="width:50px">
		    <col style="width:80px">
		    <col />
		    <col style="width:60px">
		    <col style="width:60px">
		    <col style="width:60px">
		    <col style="width:60px">
		    <col style="width:60px">
		    <col style="width:60px">
		     <col style="width:100px">
		  </colgroup>
		<thead>
		<tr>
			<th class="text-center">번호</th>
			<th class="text-center">종류</th>
			<th>이름</th>
			<th class="text-center">사이즈</th>
			<th class="text-center">적응</th>
			<th class="text-center">친밀</th>
			<th class="text-center">훈련</th>
			<th class="text-center">지능</th>
			<th class="text-center">활동</th>
			<th class="text-center">등록일</th>
		</tr>
		</thead>
		<tbody>
		
		<tr v-for="lst in vData.dataList" v-cloak>
			<td class="text-center">{{ lst.num  }}</td>
			<td class="text-center" v-bind:style="{'color': ( lst.petCd == 'dog' ? '' : 'blue' )}">{{ lst.petCd  =='dog' ? '강아지' : '고양이' }}</td>
			<td class="truncate-ellipsis"><a href="javascript:;" v-on:click="view(lst.petSrl)">{{ lst.petNm }}</a></td>
			<td class="text-center">{{ lst.petSize }}</a></td>
			<td class="text-center">{{ lst.spec1 }}</a></td>
			<td class="text-center">{{ lst.spec2 }}</a></td>
			<td class="text-center">{{ lst.spec3 }}</a></td>
			<td class="text-center">{{ lst.spec5 }}</a></td>
			<td class="text-center">{{ lst.spec5 }}</a></td>
			<td class="text-center">{{ lst.regDt | timestampToDate }}</td>
		</tr> 
		
		<tr v-if="vData.totalPage == 0" v-cloak>
			<td class="text-center" colspan="10" style="height:150px;vertical-align: middle;">자료가 없습니다.</td>
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
	
	var filter = {
		searchString : $("#searchString").val()	
		,petSize : $("#petSize").val()
	}
	$.extend(params, filter);
	
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
				itemsOnPage : rowSize,
				currentPage : ${petInfo.page }
			});
			
		}else if($("#paging > ul").length > 0 ){
			com.updatePageItems("#paging", obj.totalRow)
		}
		
	});
	
	return obj;
}

// 검색
function search(){
	
	if( $("#searchString").val().trim().length !=0 && $("#searchString").val().trim().length < 2 ) return;
	
	document.frm.action = "/adm/board/petInfoList";
	document.frm.submit();
	
}

//페이지 이동, 함수명 고정
function goPage(pageNumber){
	
	var page = pageNumber;
	
	if(page == undefined ? 1 : page);
	
	getVdata({
		page : page,
		rowSize : rowSize
	});
	
}

function view(petSrl){
	document.frm.action = "/adm/board/petInfoView";
	$("#petSrl").val(petSrl)
	$("#page").val (com.getCurrentPage("#paging") )
	document.frm.submit();
}


function regForm(){
	document.frm.action = "/adm/board/petInfoForm";
	document.frm.submit();
}



</script>