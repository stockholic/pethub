<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>

.menu-wrap {
	float:left;
	margin-right:15px;
}

.menu-wrap .category{
	height:500px;
	border-bottom:1px solid #ddd;
	margin-bottom:20px;
	overflow: auto; 
}

.menu-wrap table td:first-child{
	text-align:left;
}

.menu-wrap table th,.menu-wrap table td{
	text-align:center;
}

.menu-wrap table td .menuNm{
	text-overflow:ellipsis;
	white-space:nowrap;
	word-wrap:normal;
	width:180px;
	overflow:hidden;
	cursor: pointer;
}

.menu-wrap [name=menuStp] {
    width:30px;text-align:right;
}


</style>

<section class="content-header">
  <h1>
    메뉴등록
    <small>3단계 메뉴등록</small>  
  </h1>
  <ol class="breadcrumb">
    <li><a href="#"> Level</a></li>
    <li class="active">Here</li>
  </ol>
</section>

<section class="content container-fluid">

<div class="box box-default">
    <div class="box-body">

	<div id="menu1" class="menu-wrap"></div>
	<div id="menu2" class="menu-wrap"></div>
	<div id="menu3" class="menu-wrap"></div>

	</div>
</div>
</section>

<script>

function getMenu(params){
	
   $.ajax({      
    	type : "POST",  
        url : "/adm/menu/ajaxList",
        data : params.data,
        beforeSend : function(xhr){
			xhr.setRequestHeader("AJAX", "true");
	    },
        success : function(response){   
        	$("#" + params.id).html(response)
        	$("#" + params.id).find(".tb1 tr th:eq(0)").text(params.name);
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

function subMenu(obj,srl1,srl2, srl){
	var menu = $(obj).closest(".menu-wrap").attr("id");
	
	if(menu == "menu1"){
		getMenu( {name:"중분류", id : "menu2",data : {menuSrl1 : srl1}} );
		$("#menu3").empty();
		$("input[name=menuSrl").eq(0).val(srl)
	}else if(menu == "menu2"){
		getMenu( {name:"소분류", id : "menu3",data : {menuSrl1 : srl1, menuSrl2 : srl2}} );
		$("input[name=menuSrl").eq(1).val(srl)
	}else if(menu == "menu3"){
		$("input[name=menuSrl").eq(2).val(srl)
	}
	
	$(obj).closest("tbody").find("tr").css("background","");
	$(obj).closest("tr").css("background","#f5f5f5");
	
}

function reloadMenu(menu,srl1,srl2){
	
 	if(menu == "menu1"){
		getMenu( {name:"대분류",id : "menu1"} );
	}else if(menu == "menu2"){
		getMenu( {name:"중분류",id : "menu2",data : {menuSrl1 : srl1}} );
	}else if(menu == "menu3"){
		getMenu( {name:"소분류",id : "menu3",data : {menuSrl1 : srl1, menuSrl2 : srl2}} );
	} 
	
}

function saveStp(obj, srl1, srl2){
	
	var menu = $(obj).closest(".menu-wrap").attr("id");
	if($(obj).closest(".menu-wrap").find("[name=useYn]").length == 0) return;
	
	var data = {
		arrMenuSrl : [],
		arrMenuUseYn : [],
		arrMenuStp : []
	}
	
	$(obj).closest(".menu-wrap").find("[name=useYn]").each(function(){
		data.arrMenuSrl.push($(this).val());
		data.arrMenuUseYn.push($(this).is(":checked") == true ? "Y":"N");
	});
	$(obj).closest(".menu-wrap").find("[name=menuStp]").each(function(){
		data.arrMenuStp.push($(this).val());
	});

	
	 $.ajax({      
    	type : "POST",  
        url : "/adm/menu/updateStatus",
        data : $.param(data,true),
        beforeSend : function(xhr){
			xhr.setRequestHeader("AJAX", "true");
	    },
        success : function(response){   
        	alert("저장되었습니다.");
        	reloadMenu(menu, srl1, srl2)
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

function save(obj,srl1,srl2,srl){
	
	var menu = $(obj).closest(".menu-wrap").attr("id");
	
	com.popup({
		width:520,
		height:350,
		url : "/adm/menu/form?menuSrl=" + srl + "&menuSrl1=" + srl1 + "&menuSrl2=" + srl2
	})
}

function deleteMenu(obj, srl1, srl2){
	
	var menu = $(obj).closest(".menu-wrap").attr("id");
	
	if($(obj).closest("div").find("input[name=menuSrl]").val() == "") return;
	if( (menu=="menu1" || menu=="menu2") && confirm("하위메뉴도 모두 삭제됩니다.\n\n계속하시겠습니까?") == false) return;
	
	var data = {
		menuNm : menu,
		menuSrl : $(obj).closest("div").find("input[name=menuSrl]").val()
	}
	
	$.ajax({      
    	type : "POST",  
        url : "/adm/menu/delete",
        data : data,
        beforeSend : function(xhr){
			xhr.setRequestHeader("AJAX", "true");
	    },
        success : function(response){   
        	reloadMenu(menu, srl1, srl2);
        	
        	if("menu1" == menu){
	        	$("#menu2").empty();
        	}else if("menu2" == menu){
	        	$("#menu3").empty();
        	}
        	
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

$(document).ready(function(){
	getMenu({
		name : "대분류",
		id : "menu1"
	});
	
	 $("input[name=menuStp]").mask("000");
});




</script>



	

