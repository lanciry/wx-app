<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/views/include_head.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>提交订单成功</title>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
    <meta http-equiv="Cache-Control" content="no-siteapp">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta content="telephone=no" name="format-detection">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta name="keywords" content="">
    <meta name="description" content="">
    
    <link rel="stylesheet" type="text/css" href="${ctx }/static/weui/css/weui.min.css" />
</head>
<body ontouchstart>
	<div class="weui_cells_title">
    	<h4 align="center">提交成功</h4>
    </div>
	
	<div class="weui_msg">
        <div class="weui_icon_area"><i class="weui_icon_success weui_icon_msg"></i></div>
        <div class="weui_text_area">
            <h2 class="weui_msg_title">您的订单已提交成功</h2>
            <br/>
            <p class="weui_msg_desc">订单号${orderNo }</p>
            <p class="weui_msg_desc">2小时给你反馈</p>
        </div>
        <div class="weui_opr_area">
            <p class="weui_btn_area">
                <a href="javascript:;" class="weui_btn weui_btn_primary" id="myOrder">查看我的订单</a>
                <a href="javascript:;" class="weui_btn weui_btn_default" id="back">返回</a>
            </p>
        </div>
    </div>
	
	<script src="${ctx }/static/js/jquery-2.2.3.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#back").click(function(){
				wx.closeWindow();
			});
			$("#myOrder").click(function(){
				window.location.href="${ctx }/order/myOrders";
			})
		});
	</script>
	<script src="${ctx }/static/js/jweixin-1.0.0.js"></script>
	<script type="text/javascript">
		wx.config({
		});
		
		wx.ready(function(){
			wx.hideOptionMenu();
		});
	</script>
</body>
</html>