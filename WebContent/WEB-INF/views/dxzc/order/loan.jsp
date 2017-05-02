<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/views/include_head.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>鼎鑫资产管理</title>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
    <meta http-equiv="Cache-Control" content="no-siteapp">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta content="telephone=no" name="format-detection">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta name="keywords" content="">
    <meta name="description" content="">
    
    <link rel="stylesheet" type="text/css" href="${ctx }/static/weui/css/weui.min.css" />
	
	<style type="text/css">
		.weui_cell label {
			width: 7em;
		}
		input {
			text-align: right;
		}
		.wanyuan {
			margin-left: 5px;
		    height: 44px;
		    line-height: 44px;
		    font-size: 14px;
		}
		.weui_icon_clear {
			position: absolute;
			top: 0px;
			right: 0px;
		}
		.weui_icon_clear:before{
			font-size: 26px;
		}
		.weui_uploader_file{
			position: relative;
		}
		.weui_uploader_file img{
			width: 79px;
			height: 79px;
		}
		.form {
			margin-bottom: 20px;
		}
		.weui_cell_select .weui_select {
			padding-left: 65%;
		}
	</style>
</head>
<body ontouchstart>
	<div class="weui_cells_title">
    	<h4 align="center">请填写贷款信息</h4>
    </div>
	<div class="form">
		<div class="weui_cells weui_cells_form">
	        <div class="weui_cell">
	            <div class="weui_cell_hd"><label class="weui_label">贷款人姓名</label></div>
	            <div class="weui_cell_bd weui_cell_primary">
	                <input type="text" name="borrower" id="borrower" class="weui_input" />
	            </div>
	        </div>
	        <%--暂时注释 <div class="weui_cell">
	           <div class="weui_cell_hd"><label class="weui_label">贷款人身份证号</label></div>
	           <div class="weui_cell_bd weui_cell_primary">
	                <input type="text" name="idCard" id="idCard" value="${sessionScope.customer.idCard }"
	                	<c:if test="${not empty sessionScope.customer.idCard }">readonly="readonly"</c:if> maxlength="18" class="weui_input"/>
	           </div>
	       </div> --%>
	       <div class="weui_cell weui_vcode">
	           <div class="weui_cell_hd"><label class="weui_label">贷款金额</label></div>
		           <div class="weui_cell_bd weui_cell_primary">
		                <input type="text" name="loanAmount" id="loanAmount" class="weui_input" />
		           </div>
		           <div class="weui_cell_ft">
		               <span class="wanyuan">万元</span>
		           </div>
	        </div>
	        
	        <div class="weui_cell weui_cell_select weui_select_after">
	      	    <div class="weui_cell_hd">贷款期限</div>
	            <div class="weui_cell_bd weui_cell_primary">
	                <select class="weui_select" name="loanTerm" id="loanTerm">
                		<c:forEach items="${list }" var="arr">
                			<option value="${arr[0] }">${arr[1] }</option>
                		</c:forEach>
	                </select>
	            </div>
	        </div>
	    </div>
	    
	    <div class="weui_cells weui_cells_form">
	        <div class="weui_cell">
	            <div class="weui_cell_bd weui_cell_primary">
	                <div class="weui_uploader">
	                    <div class="weui_uploader_hd weui_cell">
	                        <div class="weui_cell_bd weui_cell_primary">上传房产证照片</div>
	                    </div>
	                    <div class="weui_uploader_bd">
	                        <ul class="weui_uploader_files" id="housePhotoImg">
	                        </ul>
	                        <div class="weui_uploader_input_wrp">
	                            <input class="weui_uploader_input" id="housePhoto" data-id="2"/>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	    
	   <%-- <div class="weui_cell weui_cell_select weui_select_after">
      	    <div class="weui_cell_hd">房产证类型</div>
            <div class="weui_cell_bd weui_cell_primary">
                <select class="weui_select" name="houseType" id="houseType">
                    <option value="1">旧版</option>
                    <option value="2">新版</option>
                </select>
            </div>
        </div>
        
        <div class="weui_cell" id="oldType">
        	<div class="weui_cell_hd"><label class="weui_label">房产证号</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input type="text" name="name" id="name" class="weui_input"/>
                京房权证
                <input type="text" name="name" id="name" class="weui_input"/>
                字第
                <input type="text" name="name" id="name" class="weui_input"/>
                号
           </div>
        </div>
        
        <div class="weui_cell hide" id="newType">
        	<div class="weui_cell_hd"><label class="weui_label">房产证号</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input type="text" name="name" id="name" class="weui_input"/>
                （
                <input type="text" name="name" id="name" class="weui_input"/>
                ）
                <input type="text" name="name" id="name" class="weui_input"/>
                不动产权第
                <input type="text" name="name" id="name" class="weui_input"/>
                号
           </div>
        </div> --%>
        
        <div class="weui_cells">
	        <div class="weui_cell weui_cell_select weui_select_after">
	      	    <div class="weui_cell_hd">抵押顺位</div>
	            <div class="weui_cell_bd weui_cell_primary">
	                <select class="weui_select" name="houseType" id="houseType">
	                    <option value="1">一抵</option>
	                    <option value="2">二抵</option>
	                </select>
	            </div>
	        </div>
        </div>
        
        <%-- <div class="weui_cells weui_cells_form">
	        <div class="weui_cell">
	            <div class="weui_cell_bd weui_cell_primary">
	                <div class="weui_uploader">
	                    <div class="weui_uploader_hd weui_cell">
	                        <div class="weui_cell_bd weui_cell_primary">上传身份证照片</div>
	                    </div>
	                    <div class="weui_uploader_bd">
	                    	<ul class="weui_uploader_files" id="idcardPhotoImg">
	                        </ul>
	                        <div class="weui_uploader_input_wrp">
	                            <input class="weui_uploader_input" id="idcardPhoto" data-id="1"/>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	        <input type="hidden" id="idcardPhotoCount" value="0"/>
	    </div> --%>
	</div>
	
	<a href="javascript:;" class="weui_btn weui_btn_primary" onclick="valid();">提交贷款</a>
	
	<script type="text/javascript" src="${ctx }/static/js/jquery-2.2.3.min.js"></script>
	<script type="text/javascript" src="${ctx }/static/js/my-weui.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			//上传图片
			$('#housePhoto').click(function(){
				uploadImage($(this));
			});
		});
		//上传图片
		function uploadImage(obj){
			$('#housePhoto').attr("disabled","disabled");
			wx.chooseImage({
			    success: function (res) {
			        var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
			        for(var i in localIds){
				        wx.uploadImage({
				            localId: localIds[i], // 需要上传的图片的本地ID，由chooseImage接口获得
				            isShowProgressTips: 1, // 默认为1，显示进度提示
				            success: function (res) {
				                var serverId = res.serverId; // 返回图片的服务器端ID
				                $.ajax({
				                	url : '${ctx }/order/uploadPhoto',
				                	type : 'post',
				                	data : {
				                		'serverId' : serverId,
				                		'type' : obj.attr("data-id"),
				                		'dateDir':'${dateDir}'
				                	},
				                	success : function(data){
				                		var dataJson = $.parseJSON(data);
				                		if (dataJson.status == 'Y') {
				                			var li='<li class="weui_uploader_file"><img id="preview_'+dataJson.photoId+'" photo-name="'+dataJson.photoName+'" src="${ctx}'+dataJson.photoPath+'" onclick="preview('+dataJson.photoId+');" /><i id="p_'+dataJson.photoId+'" onclick="deletePhoto('+dataJson.photoId+');" class="weui_icon_clear"></i></li>';
				                			obj.parent().parent().find("ul").append($(li));
				                		} else {
				                			weui.alert("上传图片失败，请联系客服");
				                		}
				                	},
				                	complete : function () {
				                		$('#housePhoto').removeAttr("disabled");
				                	},
				                	error : function(XMLHttpRequest, textStatus, errorThrown) {
										if(textStatus == "timeout"){
								        	alert("网络连接超时,请稍后再试！");
								        }else if(textStatus == "error"){
								        	alert("上传图片失败,请稍后再试！");
								        }
									}
				                });
				            }
				        });
			        }
			    }
			});
		}
		//点击图片预览
		function preview(photoId)
		{
			var obj = $("#preview_"+photoId);
			var imgArray = [];
	        var curImageSrc = obj.attr('src');
	        var oParent = obj.parent().parent();
            $(oParent).find("img").each(function(index, el) {
                var itemSrc = $(this).attr('src');
                imgArray.push(itemSrc);
            });
            wx.previewImage({
                current: curImageSrc,
                urls: imgArray
            });
		}
		//删除图片
		function deletePhoto(photoId)
		{
			weui.confirm("确认删除该照片吗",function(r){
				if (r == true) {
					$.ajax({
		            	url : '${ctx }/order/deletePhoto',
		            	type : 'post',
		            	data : {
		            		'photoId' : photoId
		            	},
		            	success : function(data){
		            		var dataJson = $.parseJSON(data);
		            		if (dataJson.status == 'Y') {
		            			var r = $("#p_"+photoId).attr("r");
		            			$("#p_"+photoId).parent().remove();
		            			weui.toast("删除成功");
		            		} else {
		            			weui.alert(dataJson.msg);
		            		}
		            	},
		            	error : function(XMLHttpRequest, textStatus, errorThrown) {
							if(textStatus == "timeout"){
					        	alert("网络连接超时,请稍后再试！");
					        }else if(textStatus == "error"){
					        	alert("删除失败,请稍后再试！");
					        }
						}
		            });
				}
			});
		}
		var hasCommit = false;
		function valid()
		{
			
			var borrower = $("#borrower").val();
			var loanAmount = $("#loanAmount").val();
			var loanTerm = $("#loanTerm").val();
			var houseType = $("#houseType").val();
			
			var housePhotoNames = [];
			$("#housePhotoImg").find("img").each(function(){
				var housePhotoName ="'"+$(this).attr("photo-name")+"'";
				housePhotoNames.push(housePhotoName);
			});
			
			/* var idCard = $("#idCard").val();
			var idcardPhotoCount = $("#idcardPhotoCount").val();
			if (idCard == '') {
				weui.alert("身份证号码必须填写");
				$("#idCard").focus();
				return false;
			} else if (!/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(idCard)) {
				weui.alert("身份证号码不符合规格");
				$("#idCard").focus();
				return false;
			} else */ 
			if (borrower == '') {
				weui.alert("贷款人姓名必须填写");
				$("#borrower").focus();
				return false;
			}else if (loanAmount == '') {
				weui.alert("贷款金额必须填写");
				$("#loanAmount").focus();
				return false;
			} else if (!/^\+?[1-9][0-9]*$/.test(loanAmount)) {
				weui.alert("贷款金额不符合规格");
				$("#loanAmount").focus();
				return false;
			} else if (loanTerm == '') {
				weui.alert("贷款期限必须选择");
				return false;
			} else if (housePhotoNames.length == 0) {
				weui.alert("必须上传房产证照片");
				return false;
			} else if (houseType == '') {
				weui.alert("抵押顺位必须选择");
				return false;
			}
			/* else if (idcardPhotoCount == 0) {
				weui.alert("必须上传身份证照片");
				return false;
			}
			var idcardPhotoNames = [];
			$("#idcardPhotoImg").find("img").each(function(){
				var idcardPhotoName ="'"+$(this).attr("photo-name")+"'";
				idcardPhotoNames.push(idcardPhotoName);
			});*/
			
			if (hasCommit) {
				return false;
			}
			hasCommit = true;
			
			$.ajax({
            	url : '${ctx }/order/save',
            	type : 'post',
            	data : {
            		'productType' : '${productType}',
            		'dateDir' : '${dateDir}',
            		'borrower' : borrower,
            		'loanAmount' : loanAmount,
            		'loanTerm' : loanTerm,
            		'housePhotoNames' : housePhotoNames.join(","),
            		'houseType' : houseType
            	},
            	success : function(data){
            		var dataJson = $.parseJSON(data);
            		if (dataJson.status == 'Y') {
            			window.location.href="${ctx}/order/success?orderNo="+dataJson.orderNo;
            		} else {
            			weui.alert(dataJson.msg);
            		}
            	},
            	error : function(XMLHttpRequest, textStatus, errorThrown) {
					if(textStatus == "timeout"){
			        	alert("网络连接超时,请稍后再试！");
			        }else if(textStatus == "error"){
			        	alert("保存失败,请稍后再试！");
			        }
				},
				complete : function() {
					hasCommit = false;
				}
            });
		}
		
		document.ondblclick = function () { 
			window.event.returnValue = false; 
		};
	</script>
	<script src="${ctx }/static/js/jweixin-1.0.0.js"></script>
	<script type="text/javascript">
		wx.config({
		    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
		    appId: '${appid}', // 必填，公众号的唯一标识
		    timestamp: '${timestamp}', // 必填，生成签名的时间戳
		    nonceStr: '${nonceStr}', // 必填，生成签名的随机串
		    signature: '${signature}',// 必填，签名，见附录1
		    jsApiList: ["hideOptionMenu", "showOptionMenu", "onMenuShareTimeline", "onMenuShareAppMessage","hideMenuItems",'chooseImage', 'previewImage', 'uploadImage', 'downloadImage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
		});
		
		wx.ready(function(){
			wx.showOptionMenu();
			//分享到朋友圈
			wx.onMenuShareTimeline({
				title: '', // 分享标题
				desc: '', // 分享描述
			    link: '', // 分享链接
			    imgUrl: '', // 分享图标
			    success: function () {
			        // 用户确认分享后执行的回调函数
			    },
			    cancel: function () {
			        // 用户取消分享后执行的回调函数
			    }
			});
			//分享给朋友
			wx.onMenuShareAppMessage({
			    title: '', // 分享标题
			    desc: '', // 分享描述
			    link: '', // 分享链接
			    imgUrl: '', // 分享图标
			    success: function () {
			        // 用户确认分享后执行的回调函数
			    },
			    cancel: function () {
			        // 用户取消分享后执行的回调函数
			    }
			});
		});
	</script>
</body>
</html>