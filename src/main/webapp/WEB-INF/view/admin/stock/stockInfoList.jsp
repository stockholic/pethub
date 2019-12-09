<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<section class="content container-fluid">




    <div class="box-body" id="dataWrap">
    
  
	  <div class="form-row">
		<div class="form-group col-sm-2">
		  <label for="price">매입단가</label>
		  <input type="text" class="form-control" id="buyPrice" style="text-align: right;height:35px; font-size: 18px" onKeydown="com.numberInput(event);" onKeyup="com.setFormatNumber(this)" maxlength="8" onclick="select()">
		</div>
		<div class="form-group col-sm-2">
		  <label for="ea">수량</label>
		  <input type="text" class="form-control" id="ea" style="text-align: right;height:35px; font-size: 18px"  onKeydown="com.numberInput(event)" onKeyup="com.setFormatNumber(this)" maxlength="7" onclick="select()">
		</div>
		<div class="form-group col-sm-2">
		  <label for="totalPrice">총매입가</label>
		  <input type="text" class="form-control" id="totalPrice"style="text-align: right;height:35px; font-size: 18px" readOnly>
	  </div>

		<button type="button" id="sendBtn" onclick="setVdata()" class="btn btn-primary btn-xm" style="margin-top:28px;margin-left:15px">전송</button>
  	 

		<table class="table table-hover table-top">
		  <thead>
		    <tr>
		      <th scope="col" class="text-center">매입가</th>
		      <th scope="col" class="text-center">매도가</th>
		      <th scope="col" class="text-center">수량</th>
		      <th scope="col" class="text-center">손익</th>
		      <th scope="col" class="text-center">손익율</th>
		    </tr>
		  </thead>
		  <tbody>
		    <tr v-for="lst in vData.dataList" v-cloak>
		      <td class="text-center" v-bind:style="{'background': ( lst.profitRate == '0.010' || lst.profitRate == '0.020' ? '#dedede' : '' )}">{{ lst.buyPrice | addComma }}</td>
		      <td class="text-center" v-bind:style="{'background': ( lst.profitRate == '0.010' || lst.profitRate == '0.020' ? '#dedede' : '' )}">{{ lst.sellPrice | addComma }}</td>
		      <td class="text-center" v-bind:style="{'background': ( lst.profitRate == '0.010' || lst.profitRate == '0.020' ? '#dedede' : '' )}">{{ lst.ea | addComma }}</td>
		      <td class="text-center" v-bind:style="{'background': ( lst.profitRate == '0.010' || lst.profitRate == '0.020' ? '#dedede' : '' )}">{{ lst.profit | addComma }}</td>
		      <td class="text-center" v-bind:style="{'background': ( lst.profitRate == '0.010' || lst.profitRate == '0.020' ? '#dedede' : '' )}">{{ lst.profitRate }}</td>
		    </tr>
		  </tbody>
		</table>
		
	</div>
	

</section>


<script>

var vObj = null;			//Vue 객제
$(document).ready(function() {

	 vObj = new Vue({
		el: "#dataWrap",
		data : {
			vData : {}
		}
	});
	
	 
	$("#ea, #buyPrice").keyup(function(event) {
		if( event.keyCode == 13){
			setVdata();
    	}
	});
});

function setVdata(){	
	
	var _buyPrice = $("#buyPrice").val().replace(/,/g,'');
	var _ea = $("#ea").val().replace(/,/g,'');
	
	if( _buyPrice == "" || _ea == "" ) return;
	
	
	$("#totalPrice").val(getTotal());
	com.setFormatNumber($("#totalPrice"));
	
	
	var obj = {
		dataList : []
	};
	
	for(i = 30; i > 0; i--){
		

		var _profitRate = (i /1000).toFixed(3);
		var _profit = Math.round(eval(_buyPrice) * eval(_profitRate) * eval(_ea))
		//console.log( eval(_buyPrice) +":"+ eval(_profitRate) +":"+  eval(_ea) )
		
		
		
		obj.dataList.push({
			buyPrice : _buyPrice
			, sellPrice : Math.round( eval(_buyPrice) + ( _profit / eval(_ea) ) )
			, ea : _ea
			, profit : _profit
			, profitRate : _profitRate
		}); 
	}
	
	vObj.vData = obj
}

function getTotal(){
	
	var buyPrice = $("#buyPrice").val().replace(/,/g,'');
	var ea = $("#ea").val().replace(/,/g,'');
	var totalPrice = buyPrice * ea;
	
	return totalPrice;
	
}



</script>