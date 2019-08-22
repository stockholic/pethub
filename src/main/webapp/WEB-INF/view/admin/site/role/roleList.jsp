<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<section class="content-header">
  <h1>
    권한관리
    <small>그룹관리</small>  
  </h1>
  <ol class="breadcrumb">
    <li><a href="#"> 권한관리</a></li>
    <li class="active">그룹관리</li>
  </ol>
</section>

<section class="content container-fluid">

<div class="box box-default">
    <div class="box-body" id="dataWrap">
    
    	<div class="box-600">
	  	<div class="pull-left">
       	<form name="frm" id="frm" class="form-inline">
		    <label>사용여부</label>
			 <select name="useYn" id="useYn" class="form-control" onChange="search()">
				<option value="">전체</option>
				<option value="Y">사용</option>
				<option value="N">미사용</option>
			</select>
		</form>
	  	</div>
		<div class="pull-right" v-cloak>Total : {{ vData.totalRow | addComma }} [ {{ vData.page }} / {{ vData.totalPage }} ]</div> 
		</div>

		<table class="table table-hover table-top box-600">
		  <colgroup>
		    <col width="5%">
		    <col width="40%">
		    <col width="40%">
		    <col width="15%">
		  </colgroup>
		<thead>
		<tr>
			<th><input type="checkbox" onClick="checkAll(this)"></th>
			<th>권한그룹코드</th>
			<th>권한그룹명</th>
			<th style="text-align: center">사용</th>
		</tr>
		</thead>
		<tbody>
		
		<tr v-for="lst in vData.dataList" v-cloak>
			<td><input type="checkbox" name="roleSrl" v-bind:value="lst.roleSrl"></td>
			<td><a href="javascript:;" v-on:click="openRegForm(lst.roleSrl)">{{ lst.roleCd}}</a></td>
			<td>{{ lst.roleNm}}</td>
			<td style="text-align: center">{{ lst.useYn }}</td>
		</tr>
		
		<tr v-if="vData.totalPage == 0" v-cloak>
			<td class="text-center" colspan="4" style="height:150px;vertical-align: middle;">자료가 없습니다.</td>
		</tr>
		
		</tbody>
		</table> 

	</div>

    <div class="box-footer box-600">
		<button type="button" class="btn btn-primary btn-xm" onClick="openRegForm()">등록</button>

		<button type="button" class="btn btn-primary btn-xm pull-right pull-margin" onClick="remove()">삭제</button>
		<button type="button" class="btn btn-primary btn-xm pull-right pull-margin" onClick="updateStatus('N')">미사용</button>
		<button type="button" class="btn btn-primary btn-xm pull-right pull-margin" onClick="updateStatus('Y')">사용</button>
    </div>
    
</div>

</section>


<script>

var vObj = null;			//Vue 객제
var rowSize = 15;		//페이지당 보여줄 로우 수

$(document).ready(function() {

	//Vue 초기화
	vObj = com.initVue("#dataWrap");
	
});


//Ajax 데이터 추출,  Vue 에 정의된 함수명 
function getVdata(params){	
	
	var obj = {};
 	com.requestAjax({
		type: "POST",
		url : "/adm/role/listJson", 
		params : params,
		
	//call back	
	},function(data){
		obj = data;
		console.log(obj);
		vObj.vData = obj;
	});
	
	return obj;
}


function search(){
	getVdata({
		rowSize : rowSize,
		useYn : $("#useYn").val()
	});
	
}


function openRegForm(roleSrl){
	com.popup({
		title : "권한 등록",
		width : 520,
		height : 250,
		url : "/adm/role/form"+( roleSrl != undefined ? "?roleSrl="+roleSrl : "")
	})
}

function checkAll(obj){
	$("input[name=roleSrl]").each(function(){
		$(this).prop("checked",  $(obj).is(":checked") )
	});
}

function updateStatus(useYn){
	var data = {
		arrRoleSrl : [],
		useYn : useYn
	}
	$("input[name=roleSrl]").each(function(){
		if( $(this).is(":checked") == true ) {
			data.arrRoleSrl.push( $(this).val() )
		}
	});
	
	if(data.arrRoleSrl.length == 0) return;
	
	com.confirm({
		content : "수정 하겠습니까 ?",
		confirm : function(){
			var obj = com.requestAjax({
				type: "POST",
				url : "/adm/role/updateStatus",
				params : $.param(data,true)
			},function(data){
				com.notice("수정 되었습니다.")
				com.confirmClose();
				search();
			});
		},
		cancel : function(){
		}
	});
	
}

function remove(){
	
	var data = {
		arrRoleSrl : []
	}
	$("input[name=roleSrl]").each(function(){
		if( $(this).is(":checked") == true ) {
			data.arrRoleSrl.push( $(this).val() )
		}
	});
	
	if(data.arrRoleSrl.length == 0) return;
	
	com.confirm({
		content : "삭제 하겠습니까 ?",
		confirm : function(){
			var obj = com.requestAjax({
				type: "POST",
				url : "/adm/role/delete",
				params : $.param(data,true)
			},function(data){
				com.notice("삭제 되었습니다.")
				com.confirmClose();
				search();
			});
		},
		cancel : function(){
		}
	});
	
}

</script>
