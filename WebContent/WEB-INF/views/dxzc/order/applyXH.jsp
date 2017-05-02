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
		.weui_cells_title{
			margin-bottom: 20px;
		}
		.weui_cells_tips{
			margin-bottom: 20px;
			margin-top: 5px;
			color: red;
		}
		.weui_cell_select .weui_select {
			padding-left: 72%;
		}
		.weui_cells_form .weui_input {
			padding-left: 62%;
		}
	</style>
</head>
<body ontouchstart>
	<div class="weui_cells_title">
    	<h4 align="center">申请下户</h4>
    </div>
    
    
    <div class="weui_cells weui_cells_form">
		<div class="weui_cell">
	        <div class="weui_cell_hd"><label for="" class="weui_label">日期</label></div>
	        <div class="weui_cell_bd weui_cell_primary">
	            <input class="weui_input" type="date" value="" name="xhDate" id="xhDate">
	        </div>
	    </div>
	    
	    <div class="weui_cell weui_cell_select weui_select_after">
      	    <div class="weui_cell_hd">时间</div>
            <div class="weui_cell_bd weui_cell_primary">
                <select class="weui_select" name="xhTime" id="xhTime">
               		<option  value="8">8:00</option>
                    <option  value="10">10:00</option>
                    <option  value="12">12:00</option>
                    <option  value="14">14:00</option>
                    <option  value="16">16:00</option>
                    <option  value="18">18:00</option>
                    <option  value="20">20:00</option>
                </select>
            </div>
        </div>
    </div>
    
    <div class="weui_cells_tips">周一至周五18、20点下户需要支付200元下户费，周六、周日全天下户需要支付300元下户费</div>
	
	<a href="javascript:;" class="weui_btn weui_btn_primary" onclick="valid();">确定</a>
	
	<script type="text/javascript" src="${ctx }/static/js/jquery-2.2.3.min.js"></script>
	<script type="text/javascript" src="${ctx }/static/js/my-weui.js"></script>
	<script src="${ctx }/static/js/jweixin-1.0.0.js"></script>
	<script type="text/javascript">
		var hasCommit = false;
		function valid() {
			var xhDate = $("#xhDate").val();
			var xhTime = $("#xhTime").val();
			if (xhDate == '') {
				weui.alert("下户日期必须选择");
				return false;
			} else if (xhTime == '') {
				weui.alert("下户时间必须选择");
				return false;
			}
			
			if (hasCommit) {
				return false;
			}
			hasCommit = true;
			
			$.ajax({
            	url : '${ctx }/order/saveXH',
            	type : 'post',
            	data : {
            		'id' : '${id}',
            		'xhDate' : xhDate,
            		'xhTime' : xhTime
            	},
            	success : function(data){
            		var dataJson = $.parseJSON(data);
            		if (dataJson.status == 'Y') {
            			weui.alert("申请下户成功，如非工作时间，请先支付下户费用。支付宝账号：18911539018“鼎鑫资产管理”",function(){
            				window.location.href="${ctx}/order/myOrders";
            			});
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
				}
            });
		}
		wx.config({
		});
		
		wx.ready(function(){
			wx.hideOptionMenu();
		});
	</script>
</body>
</html>