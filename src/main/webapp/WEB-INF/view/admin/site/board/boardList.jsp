<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tx" uri="http://www.pethub.kr/tags"%>


<section class="content-header">
  <h1>
    공지사항
    <small>목록</small>  
  </h1>
  <ol class="breadcrumb">
    <li><a href="#"> Level</a></li>
    <li class="active">Here</li>
  </ol>
</section>


<section class="content container-fluid">

<div class="box box-default">
    <div class="box-body">
    
    	<div class="form-group-sm pull-left">
     	<form name="frm" id="frm" action="" method="POST" class="form-inline">
		  <div class="form-group-sm">
			   <select name="searchKey" id="searchKey" class="form-control">
				<option value="title" ${board.searchKey eq 'title' ? "selected" : "" }>제목</option>
				<option value="content" ${board.searchKey eq 'content' ? "selected" : "" }>내용</option>
			</select>
			
			<div class="input-group input-group-sm" style="width: 200px;">
	             <input type="text" name="searchValue" id="searchValue" value="${board.searchValue }" class="form-control pull-right" placeholder="Search">
	             <div class="input-group-btn">
	               <button type="button" onClick="search()" class="btn btn-default"><i class="fa fa-search"></i></button>
	             </div>
           	</div>
			
		  </div>
		  
		  <input type="hidden" name="page" id="page" value="${board.page }">
		  <input type="hidden" name="boardSrl" id="boardSrl" value="">
		</form>
	  	</div>
		<div class="pull-right">Total : ${board.totalRow} [ ${board.page} / ${board.totalPage} ]</div> 
    
	    <table class="table table-hover table-top">
		  <colgroup>
		    <col style="width:60px">
		    <col />
		     <col style="width:50px">
		     <col style="width:80px">
		     <col style="width:80px">
		  </colgroup>
		<thead>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>파일</th>
			<th>등록자</th>
			<th>등록일</th>
		</tr>
		</thead>
		<tbody>
		
		<c:forEach var="lst" items="${list }" varStatus="status">
		<tr>
			<td>${board.totalRow - ((board.page-1) * board.rowSize + status.index) }</td>
			<td style="text-align:left;padding-left:${lst.lev*12}px">
				<c:if test="${lst.lev > 0}">└</c:if>
				<a href="javascript:view(${lst.boardSrl })">${lst.title }</a>
			</td>
			<td>${lst.fileCnt }</td>
			<td>${lst.userNm } </td>
			<%-- <td><fmt:formatDate value="${lst.regDate}" pattern="yyyy/MM/dd HH:mm:ss" /></td> --%>
			<td><fmt:formatDate value="${lst.regDate}" pattern="yyyy.MM.dd" /></td>
		</tr>
		</c:forEach>
		<c:if test="${empty list }">
		<tr>
			<td colspan="5" style="height:100px;text-align:center;vertical-align: middle">자료가 없습니다.</td>
		</tr>
		</c:if>
		
		</tbody>
		</table> 
		
	</div>
		
	<div class="box-footer">
		<button type="button" class="btn btn-primary btn-xm" onClick="save()">등록</button>
	</div>
		
	<c:if test="${board.totalRow > 0}">
	<div style="text-align:center">
	<nav aria-label="Page navigation">
	  <ul class="pagination">
	 	 <tx:nav totalPage="${board.totalPage }" page="${board.page }"/>
	  </ul>
	</nav>
	</div>
	</c:if>
	
</div>	

</section>

<script>

function search(){
	$("#page").val(1)
	document.frm.action = "/adm/board/${flag}/list";
	document.frm.submit();
}

function goPage(p){
	$("#page").val(p)
	document.frm.action = "/adm/board/${flag}/list";
	document.frm.submit();
}

function save(){
	document.location.href = "/adm/board/${flag}/insert/form";
}


function view(srl){
	document.frm.action = "/adm/board/${flag}/view"
	$("#boardSrl").val(srl);
	document.frm.submit();
}

</script>