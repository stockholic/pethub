<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>

.menu-wrap {
	float:left;
	margin-right:15px;
}
.menu-wrap .tb1{
	width:350px;
	border-bottom:1px solid #ddd;
}
.menu-wrap .tb2{
	width:500px;
	border-bottom:1px solid #ddd;
}
.menu-wrap .tb1 th,.menu-wrap .tb1 td{
	text-align:center;
	cursor: pointer;
}

#menu2{
	height:600px;
	border-bottom:1px solid #ddd;
	overflow: auto; 
}

.node1{
	background-color: #efefef;
}
.node2{
	padding-left:20px;
}
.node3{
	padding-left:40px;
}

</style>

<section class="content-header">
  <h1>
    메뉴설정
    <small>권한별 메뉴설정을 합니다.</small> 
  </h1>
  <ol class="breadcrumb">
    <li><a href="#"> Level</a></li>
    <li class="active">Here</li>
  </ol>
</section>

<section class="content container-fluid">

<div class="box box-default">
    <div class="box-body">
    
    <div id="menu1" class="menu-wrap">
	<table class="table table-hover tb1">
	  <colgroup>
	    <col width="50%">
	    <col width="50%">
	  </colgroup>
	  <thead>
	    <tr>
	        <th>권한그룹코드</th>
	        <th>권한그룹명</th>
	    </tr>
	    </thead>
	    <tbody>
	    <c:forEach var="lst" items="${authList }">
	    <tr onClick="getMenu(this,'${lst.roleCd }')">
	        <td>${lst.roleCd }</td>
	        <td>${lst.roleNm }</td>
	    </tr>
	    </c:forEach>
	    <c:if test="${empty authList }">
		<tr>
			<td colspan="2" style="text-align:center;height:100px">자료가 없습니다.</td>
		</tr>
		</c:if>
	    </tbody>
	</table>	
	<button type="button" class="btn btn-primary btn-xm" onClick="save()">저장</button>	
	</div>
	
	
	<div id="menu2" class="menu-wrap"></div>
    
    </div>
</div>





</section>



<script>

var authCd = "";

function checkMenu(obj,clazz){
	var target = clazz.trim().replace(/ /g, '.');
	$(obj).closest("tbody").find("."+target).prop('checked',$(obj).is(":checked")); 
}

function save(){
	
	var data = {
		authCd : "",
		arrMenuSrl : []
	}
	
	$(".tb2").find("[name=menuSrl]").each(function(){
		
		if($(this).is(":checked") == true){
			data.arrMenuSrl.push($(this).val());
			data.authCd = authCd;
		}
		
	});
	
	if(authCd == "" || data.arrMenuSrl.length == 0) return;
	
	 $.ajax({      
    	type : "POST",  
        url : "/adm/menu/role/insert",
        data : $.param(data,true),
        beforeSend : function(xhr){
			xhr.setRequestHeader("AJAX", "true");
	    },
        success : function(response){   
        	alert("저장되었습니다.") 
        	//getMenu(null,authCd)
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

function getMenu(obj,roleCd){
	
	authCd = roleCd;
	
   $.ajax({      
    	type : "POST",  
        url : "/adm/menu/role/ajaxList?roleCd=" + authCd,
        success : function(response){   
        	$("#menu2").html(response)
        },   
        beforeSend : function(xhr){
			xhr.setRequestHeader("AJAX", "true");
	    },
        error : function(xhr) {
        	if(xhr.status == "403"){
        		document.location.href = "/login";
        	}else{
    	        alert("에러 : " + xhr.status);
	       	}
        }
    });  
   
   if(obj != null){
	 	$(obj).closest("tbody").find("tr").css("background","");
		$(obj).closest("tr").css("background","#f5f5f5");
   }
}


</script>

	        	
	        	

	

