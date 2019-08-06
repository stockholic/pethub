var com = {

	/**
	 * 숫자 체크
	 */
	isNumber : function(str){
		var reg =  /^[0-9]+$/ ;
		return reg.test(str);
	},
	
	/**
	 * 메일 체크
	 */
	isEmail : function(str){
		var reg = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/ ;
		return reg.test(str);
	},
	
	//달력
	calendar : function(id,obj){
		
		var options = {
			    format: "yyyy-mm-dd",
			    todayBtn: "linked",
			    language: "kr",
			    keyboardNavigation: false,
			    forceParse: false,
			    todayHighlight: true,
			    autoclose : true
		};
		$.extend(options, obj);
		
		 $('#' + id).datepicker(options);
	},
	
	/**
	 * Ajax 요청
	 */
	requestAjax : function(obj, callbackFnc){
		
		var options = {
				type : "GET", 
				async : true, 
				params : {}
			};
		$.extend(options, obj);
			
		 $.ajax({      
	    	type : options.type,  
	    	async : options.async,
	    	url : options.url,
	        data : options.params,
	        beforeSend : function(xhr){
				xhr.setRequestHeader("AJAX", "true");
		    },
	        success : function(response){   
	        	data = response;
	        	if( typeof callbackFnc ===  "function") callbackFnc(data);
	        },   
	        error : function(xhr) {
	        	if(xhr.status == "403"){
	        		document.location.href = "/adm/main";
	        	}else{
	    	        alert("에러 : " + xhr.status);
		       	}
	        }
	    });  
	 
	},
	
	/**
	 * 윈도우 팝업창
	 */
	wPopup : function(params){
		
		var options = {
			title : "관리도구",
			width : 600,
			height : 400,
			scrollbars : "no"
		};
		
		$.extend( options, params );
		
		var left = (screen.width/2)-(options.width /2);
		var top = (screen.height/2)-( options.height /2);
		if( options.left != undefined ) left = left +  options.left;
		if( options.top != undefined ) top = top +  options.top; 
		
		var winObj = window.open(options.url, options.title.replace(/ /g,'_'), 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=' + options.scroll + ', width='+options.width+', height='+options.height+', top='+top+', left='+left);
		winObj.document.title = options.title
	},
	
	 formatBytes : function(a,b){
		if(0==a)return"0 Bytes";
		var c=1024,
		d=b||2,e=["Bytes","KB","MB","GB","TB","PB","EB","ZB","YB"],
		f=Math.floor(Math.log(a)/Math.log(c));
		return parseFloat((a/Math.pow(c,f)).toFixed(d))+" "+e[f]
	},
	
	formatNumber : function(x){
	    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	},
	
	/**
	 * 파일업로드
	 */
	fileUpload : function(params, onSuccessCallback, afterUploadAllCallback){
	
		//http://hayageek.com/docs/jquery-upload-file.php#local
		
		var options = {
			url:"",
			fileName:"file",
			maxFileCount : 5,
			maxFileSize:1024 * 1024 * 10,	//10M
			allowedTypes : "*",
			//allowedTypes : "jpg,png,gif",
			autoSubmit : false,
			allowDuplicates : false,
			showFileCounter:false,
			showAbort : false,
			//showProgress : true,
			dragdropWidth : 450,
			cancelStr : "삭제",
			dragDropStr : "",
			uploadStr : "클릭 또는 드래그 <span class='ajax-file-upload-fileSize'>최대 : <span id='maxFileCount'>" + params.maxFileCount + "</span>개 , : " + this.formatBytes(params.maxFileSize,2) + "이하</span>",
			sizeErrorStr : " 파일은 제외 되었습니다. 파일 최대 용량 : ",
			maxFileCountErrorStr:" 파일은 제외 되었습니다. 파일 최대  허용 개수 :  ",
			duplicateErrorStr : "이미 존재하는 파일입니다.",
			extErrorStr : " 파일은 제외 되었습니다. - 허용되는 확장자 : ",
			uploadQueueOrder:'bottom',
			
			customProgressBar: function(obj,s){
				this.statusbar = $("<div class='ajax-file-upload-statusbar'></div>");
			//	this.preview = $("<img class='ajax-file-upload-preview' />").width(s.previewWidth).height(s.previewHeight).appendTo(this.statusbar).hide();
				this.filename = $("<div class='ajax-file-upload-filename'></div>").appendTo(this.statusbar);
				this.progressDiv = $("<div class='ajax-file-upload-progress'>").appendTo(this.statusbar);
				this.progressbar = $("<div class='ajax-file-upload-bar'></div>").appendTo(this.progressDiv);
				this.abort = $("<div>" + s.abortStr + "</div>").appendTo(this.statusbar).hide();
				this.cancel = $("<div>" + s.cancelStr + "</div>").appendTo(this.statusbar).hide();
				this.done = $("<div>" + s.doneStr + "</div>").appendTo(this.statusbar).hide();
				this.download = $("<div>" + s.downloadStr + "</div>").appendTo(this.statusbar).hide();
				this.del = $("<div>" + s.deletelStr + "</div>").appendTo(this.statusbar).hide();
				
				this.cancel.addClass("ajax-file-upload-red");

			},onSuccess:function(files,data,xhr,pd){
				onSuccessCallback(data)
			},afterUploadAll:function(obj)	{
				afterUploadAllCallback();
			}
		};
		
		$.extend( options, params );
		
		return $("#" + params.id).uploadFile(options);
	},
	
	/**
	 * 에디터
	 */
	editor : function(id){
		//http://summernote.org/
		$('#' + id).summernote({
	 		toolbar: [
	 		    ['style', ['bold', 'italic', 'underline', 'clear']], 
	 		    ['fontname', ['fontname']], 
	 		    ['fontsize', ['fontsize']],
	 		    ['color', ['color']],
	 		    ['table', ['table']],
	 		    ['para', ['ul', 'ol', 'paragraph']],
	 		    ['height', ['height']],
	 		    ['codeview',['codeview']], 
	 		  ],
			height: 400,                
			lang: 'ko-KR',
			fontNames: ['맑은 고딕', '굴림체','돋움체','바탕체','궁서체','Arial Black', 'Comic Sans MS', 'Courier New', 'Merriweather']
		});
	 
	 	$('#' + id).summernote('fontName', '맑은 고딕');
	},
	
	/**
		Vue 초기화
		beforeCreate 	Called synchronously after the instance has just been initialized, before data observation and event/watcher setup.
		created 			Called synchronously after the instance is created. At this stage, the instance has finished processing the options which means the following have been set up: 
							data observation, computed properties, methods, watch/event callbacks. However, the mounting phase has not been started, and the $el property will not be available yet.
		beforeMount 	Called right before the mounting begins: the render function is about to be called for the first time.
		mounted 		Called after the instance has just been mounted where el is replaced by the newly created vm.$el.
		beforeUpdate 	Called when the data changes, before the virtual DOM is re-rendered and patched.
		updated 		Called after a data change causes the virtual DOM to be re-rendered and patched.
	*/
	initVue : function( selector ){
		
		var vObj = new Vue({
				el: selector,
				data : {
					vData : {}
				},
				methods : {
				},
				mounted: function () {
					var _rowSize = (rowSize == undefined) ? 15 : rowSize;
					this.vData = getVdata({rowSize : rowSize});
				}
			});
		
		return vObj;
	},
	
	
	/**
	 * 페이징 초기화
	 *  http://flaviusmatis.github.io/simplePagination.js/
	 */
	initPaging : function( obj ){
		
		var options = {
				selector : "#paging",
				items : 0,						// 전체 레코드 수
				itemsOnPage : 15,			// 페이지당 목록 수
				currentPage : 1				// 현재페이지
			};
	
		$.extend( options, obj );
		
		$(options.selector).pagination({
			items: options.items,
		 	itemsOnPage: options.itemsOnPage,
			currentPage : options.currentPage,
			prevText : "〈 이전",
			nextText : "다음 〉",
			onPageClick : function(pageNumber,event){
				goPage(pageNumber)
			}
		});
	},
	/**
	 * 페이지 선택
	 */
	selectPage  : function( selector, pageNumber ){
		$(selector).pagination('selectPage', pageNumber);
	},
	/**
	 * 전체 페이지 수
	 */
	getPagesCount  : function( selector ){
		return $(selector).pagination('getPagesCount');
	},
	/**
	 * 현재 페이지
	 */
	getCurrentPage   : function( selector ){
		return $(selector).pagination('getCurrentPage');
	},
	/**
	 * 전체 레코드 수 변경
	 */
	updatePageItems  : function( selector, items ){
		$(selector).pagination('updateItems', items);
	},
	/**
	 * 목록에 보여줄 페이지 수 변경
	 */
	updatePageItemsOnPage   : function( selector, itemsOnPage ){
		$(selector).pagination('updateItemsOnPage', itemsOnPage);
	},
	/**
	 * 페이징 redraw
	 */
	pageRedraw   : function( selector ){
		 $(selector).pagination('redraw');
	},
	/**
	 * 페이징 destroy
	 */
	pageDestroy   : function( selector ){
		$(selector).pagination('destroy');
	},
	
	/**
	 * 레이어 팝업
	 * https://stephanwagner.me/jBox
	 */
	jboxPopup : null,
	popup : function(obj){
		
		if( this.jboxPopup != null ) this.jboxPopup.destroy();
		
		var options = {
				title : '&nbsp;',			// 제목
				width : 600,			// 너비
				height : 400,			// 높이
				zIndex : 9999,			
				content : "",
			};
		
		$.extend( options, obj );
	
		this.jboxPopup = new jBox('Modal', {
			draggable: 'title',
			width: options.width,
			height: options.height,
			animation: false,
			closeButton: 'title',
			title: options.title,
			zIndex : options.zIndex,
			closeOnClick: false,			//클릭시 안 닫힘
		});
		
		if(obj.url != undefined){
			this.requestAjax({
				type : options.type,
				url : options.url,
				async : false,
				params : options.params
			},function(data){
				options.content =data;
			});
		}
		
		this.jboxPopup.setContent(options.content);
		this.jboxPopup.open();
	},
	popupClose : function(){
		this.jboxPopup.close();
	},
	
	
	/**
	 * 알림 레이어
	 */
	notice : function(msg){
		new jBox('Notice', {
			  content: msg,
			  color: 'black',
			  autoClose: 2000,
			  position: {
			      x: 'center',
			      y: 'top'
			  },
			  offset: {
			      y: 20
			  },
		});
	},
	
	/**
	 * 확인 창 레이어
	 */
	jboxConfirm : null,
	confirm : function(obj) {
		
		if(this.jboxConfirm != null) this.jboxConfirm.destroy();
			
		this.jboxConfirm = new jBox('Confirm', {
			  confirmButton: '확인',
			  cancelButton: '취소',
			  content : obj.content,
			  confirm : obj.confirm,
			  cancel : obj.cancel,
		});
		
		this.jboxConfirm.open();
	},
	confirmClose : function(){
		this.jboxConfirm.destroy();
	},
	
	/**
	 * 폼 Validation 체크
	 */
	validation : function(selector) {
		var result = true;
		
		$(selector).find(".required").each(function(idx){
			
			var value = "";
			
			var $this = $(this);
			var $tdObj = $this.next();
			var $obj = $tdObj.find('input, select, textarea');
				
			if($obj.length < 1) return result;
			
			if( $obj.attr("type") == "radio" ){
				$obj = $tdObj.find( 'input[id=' + $obj.attr("id") + ']' );
				for( var i = 0; i < $obj.length; i++){
					if( $obj[i].checked ) value = "checked";				
				}
			}else{
				if( typeof $obj[0].getValue == 'function' ){
					value = $obj[0].getValue();
				}else{
					value = $obj.val();
				}
			}
			
			if( value == undefined || value == "" ){
				com.notice($this.text() +  " 값은 필수입니다.");
				$obj.focus();
				result = false;
				return false;
			}
			
		});
		
		return result;
	},
	
	/**
	 * 숫자만 입력
	 */
	numberInput : function(evt){
		
	   if (window.event) {								// IE코드
	        var code = window.event.keyCode;
	   }else{ 													// 타브라우저
	        var code = evt.which;
	   }
	 
	    if ((code > 34 && code < 41) || (code > 47 && code < 58) || (code > 95 && code < 106) || code == 8 || code == 9 || code == 13 || code == 46){
	        window.event.returnValue = true;
	        return;
	    }
	    if (window.event){
	        window.event.returnValue = false;
	    }else{
	    	evt.preventDefault();
	    }
	},

	loading : function(selector) {
		
		if( $("#loading").length > 0 ) this.loadingClose();
		
		var height = $(selector).height();
		var width = $(selector).width();
		var top = $(selector).offset().top;
		
		var html = "<div id='loading' style='z-index:10000;top:" + top + "px;height:" + height + "px;'>" 
					+ "	<div class='loading-cell'>"
					+ "	<div class='loading-box'>"
					+	"		<p><img src='/static/image/loading.svg' width='30' height='30'></p><p>loading ...<p>"
					+ "	</div>"
					+ "	</div>"
					+ "</div>"
		
		$("body").append(html); 
	},
	
	
	
	loadingClose : function() {
		$("#loading").remove();
	},
	
	/**
	 *  리스트 데이터 Object  MVC 에서 받을 수 있는 Object  로 변환
	 *  dataList[0].dataTitle : 'abcd'
	 *  dataList[1].dataTitle : 'efgh'
	 *  ...
	 */
	converListToObject : function(name, objList){
		
		var obj = {};
		$(objList).each(function(i,val){
		    $.each(val,function(k,v){
		        obj[name + "[" + i + "]." + k] = v;
			});
		    
		});
		//console.log( obj )
		return obj
		
	}
	
};

//--------------------------------------------------------------------  Jquery plugin
/**
 * 함수 토글
 */
$.fn.clickToggle = function(func1, func2) {
    var funcs = [func1, func2];
    this.data('toggleclicked', 0);
    this.click(function() {
        var data = $(this).data();
        var tc = data.toggleclicked;
        $.proxy(funcs[tc], this)();
        data.toggleclicked = (tc + 1) % 2;
    });
    return this;
};

/**
 * form data To Object
 */
jQuery.fn.serializeObject = function() {
    var obj = null;
    try {
        if (this[0].tagName && this[0].tagName.toUpperCase() == "FORM") {
            var arr = this.serializeArray();
            if (arr) {
                obj = {};
                jQuery.each(arr, function() {
                    obj[this.name] = this.value;
                });
            }
        }
    } catch (e) {
        alert(e.message);
    }
 
    return obj;
};



//--------------------------------------------------------------------  Vue Filter
// 천단위 콤마
Vue.filter('addComma', function (num) {
	if(num == undefined) return "";
	return com.formatNumber(num)
});

Vue.filter('timestampToDate', function (timestamp) {
	var d = new Date(timestamp);
    year = d.getFullYear(),
    month = '' + (d.getMonth() + 1),
    day = '' + d.getDate();

	if (month.length < 2) month = '0' + month;
	if (day.length < 2) day = '0' + day;
	
	return [year, month, day].join('.');
});


