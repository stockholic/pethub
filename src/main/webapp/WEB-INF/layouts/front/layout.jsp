<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
  	<title>펫허브 ::: PetHub</title>
  
  	<meta http-equiv="X-UA-Compatible" content="IE=edge">
  	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	<meta name="description" content="강아지, 고양이 종합 정보">
	<meta name="keywords" content="애견,애완견,강아지,고양이,분양,애견분양,무료분양"> 
	<meta name="classification" content="애견,애완견,강아지,고양이,분양,애견분양,무료분양">  	
  	
  	<link rel="shortcut icon" href="/static/favicon.ico" type="image/x-icon" />
	<tiles:insertDefinition name="frontCss" />
	<tiles:insertDefinition name="frontScript"/>
	
</head>

<body>

<div class="container ">
    
   <div>
     <tiles:insertAttribute name="body" />
   </div>
    
</div>

 <hr />
    
  <div class="footer">
 	 <tiles:insertAttribute name="footer" />
  </div>

  <div style="display: none;">
  페키니즈, 요크셔테리어, 그레이하운드, 콜리, 슈나우저, 푸들, 웰시 코기, 비글, 마스티프, 라사압소, 셰틀랜드쉽독, 바이마리너, 진돗개, 포인터, 아키타, 차우차우, 브리타니, 아프간하운드, 뉴펀들랜드, 
  아이리쉬세타, 보더콜리, 베들링턴 테리어, 킹찰스스파니엘, 잭 러셀 테리어, 그레이트덴, 와이어 폭스테리어, 토이푸들, 불개, 시츄, 저패니즈스피츠, 라이카, 치와와, 스탠다드 푸들, 비숑프리제, 올드 잉글리쉬 쉽독, 
  프랜치불독, 브러쉘그리폰, 바셋하운드, 도베르만, 웰쉬코기 카디건, 화이트테리어, 세인트버나드, 세퍼트, 도고아르젠티노, 비어디드콜리, 빠삐용, 시바견, 미니핀, 친, 오브차카, 포메라니안, 삽살이, 케인코르소, 불독, 
  로트와일러, 볼조이, 퍼그, 잉글리쉬코카스파니엘, 닥스훈트, 핏불테리어, 라브라도 리트리버, 비즐라, 마리노이즈, 복서, 에어데일 테리어, 달마시안, 사모예드, 알래스카 말라뮤트, 아메리카코커스파니엘, 불테리어, 
  샤페이, 그레이트피레니즈, 보스톤테리어, 풍산개, 말티즈, 시베리안허스키, 버니즈마운틴독, 골든리트리버, 바센지
  </div>

</body>
</html>


