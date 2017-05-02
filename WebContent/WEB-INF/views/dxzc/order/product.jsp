<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/views/include_head.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>选择产品类型</title>
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
	.next {
		position: fixed;
		bottom: 5px;
		left: 0px;
		width: 100%;
	}
	.product {
		width:90%;
		height: auto;
		margin:10px auto;
		border-radius: 5px;
	}
	.product img {
	    width: 100%;
	    transition: all 0.10s ease-in-out;
		-webkit-transition: all 0.10s ease-in-out;
		-moz-transition: all 0.10s ease-in-out;
		border-radius: 6px;
		outline: none;
	}
	
	.b-border{
		border-radius: 6px;
		-webkit-transition:border linear .1s;
		transition:border linear .1s;
		border-color: blue;
		-webkit-box-shadow:0 0 18px blue;
		box-shadow:0 0 20px blue;
	}
	</style>
</head>
<body ontouchstart>
	<form action="/order/loan" method="post" id="form">
		<input type="hidden" name="productType" id ="productType" /> 
	</form>
	<div>
		<div class="product">
			<img alt="" src="${ctx }/static/images/short.png" id="short" data-value="1">
		</div>
		<div class="product">
			<img alt="" src="${ctx }/static/images/house.png" id="house" data-value="3">
		</div>
		<div class="product">
			<img alt="" src="${ctx }/static/images/cycle.png" id="cycle" data-value="2">
		</div>
	</div>
	<a href="javascript:;" class="weui_btn weui_btn_disabled weui_btn_default next" onclick="valid();" id="next">下一步</a>
	
	<script src="${ctx }/static/js/jquery-2.2.3.min.js"></script>
	<script type="text/javascript" src="${ctx }/static/js/my-weui.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
		    $('#short,#cycle,#house').click(function(){
		    	$(this).addClass("b-border");
		    	$(this).parent().siblings().find("img").removeClass("b-border");
		        $("#next").addClass("weui_btn_primary");
		    });
		});
		function valid()
		{
			var productType = $("body").find(".b-border");
			if (productType && productType.length>0) {
				$("#productType").val(productType.attr("data-value"));
				$("#form").submit();
			} else {
				weui.alert("请选择产品类型");
			}
		}
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