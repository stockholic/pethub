<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<section class="content-header">
  <h1>
    권한관리
    <small>그룹/권한관리</small>  
  </h1>
  <ol class="breadcrumb">
    <li><a href="#"> Level</a></li>
    <li class="active">Here</li>
  </ol>
</section>

<section class="content container-fluid">

<div class="box box-default">
    <div class="box-body">
    
    	<div class="box-600">
	  	<div class="form-group-sm pull-left">
       	<form name="frm" id="frm" action="/adm/role/list" method="POST" class="form-inline">
		    <label>사용여부</label>
			 <select name="useYn" id="useYn" class="form-control" onChange="search()">
				<option value="">전체</option>
				<option value="Y" ${role.useYn eq "Y" ? "selected" : "" }>사용</option>
				<option value="N" ${role.useYn eq "N" ? "selected" : "" }>미사용</option>
			</select>
		</form>
	  	</div>
		<div class="pull-right">Total :  ${fn:length(list)}</div> 
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
		
		<c:forEach var="lst" items="${list }">
		<tr>
			<td><input type="checkbox" name="roleSrl" value="${lst.roleSrl }"></td>
			<td><a href="javascript:save(${lst.roleSrl})">${lst.roleCd }</a></td>
			<td>${lst.roleNm }</td>
			<td style="text-align: center">${lst.useYn }</td>
		</tr>
		</c:forEach>
		<c:if test="${empty list }">
		<tr>
			<td colspan="4" style="height:100px;text-align:center;vertical-align: middle">자료가 없습니다.</td>
		</tr>
		</c:if>
		
		</tbody>
		</table> 

	</div>

    <div class="box-footer box-600">
		<button type="button" class="btn btn-primary btn-xm" onClick="save()">등록</button>

		<button type="button" class="btn btn-primary btn-xm pull-right pull-margin" onClick="remove()">삭제</button>
		<button type="button" class="btn btn-primary btn-xm pull-right pull-margin" onClick="updateStatus('N')">미사용</button>
		<button type="button" class="btn btn-primary btn-xm pull-right pull-margin" onClick="updateStatus('Y')">사용</button>
    </div>
    
</div>

</section>


<script>

function search(){
	document.frm.submit();
}

function goPage(p){
	document.frm.submit();
}

function save(roleSrl){
	com.popup({
		width:520,
		height:300,
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
	if(confirm("변경하겠습니까?") == false) return;
	
	 $.ajax({      
    	type : "POST",  
        url : "/adm/role/updateStatus",
        data : $.param(data,true),
        beforeSend : function(xhr){
			xhr.setRequestHeader("AJAX", "true");
	    },
        success : function(response){   
        	search();
        },   
        error : function(xhr) {
        	if(xhr.status == "403"){
        		document.location.href = "/login";
        	}else{
    	        alert("에러 : " + xhr.status);
	       	}
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
	if(confirm("삭제하겠습니까?") == false) return;
	
	 $.ajax({      
    	type : "POST",  
        url : "/adm/role/delete",
        data : $.param(data,true),
	    beforeSend : function(xhr){
			xhr.setRequestHeader("AJAX", "true");
	    },
        success : function(response){   
        	search();
        },   
        error : function(xhr) {
        	if(xhr.status == "403"){
        		document.location.href = "/login";
        	}else{
    	        alert("에러 : " + xhr.status);
	       	}
        }
    });  
}

</script>
