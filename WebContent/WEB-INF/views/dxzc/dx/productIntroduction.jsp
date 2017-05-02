<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/views/include_head.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>产品介绍</title>
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
	.product {
		width:100%;
		height: auto;
		margin: 0;
	}
	.product img {
		width: 100%;
		border: none;
	}
	</style>
</head>
<body ontouchstart>
	<div>
		<div class="product">
			<img alt="" src="${ctx }/static/images/introduction1.jpg" />
			<img alt="" src="${ctx }/static/images/introduction2.jpg" />
		</div>
	</div>
	
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