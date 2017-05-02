<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/views/include_head.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <title>个人资料</title>
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
<body>
	<div class="weui_cells">
        <div class="weui_cell">
            <div class="weui_cell_bd weui_cell_primary">
                <p>姓&nbsp;&nbsp;名</p>
            </div>
            <div class="weui_cell_ft">${sessionScope.customer.name}</div>
        </div>
    </div>
    
    <div class="weui_cells">
        <div class="weui_cell">
            <div class="weui_cell_bd weui_cell_primary">
                <p>手机号</p>
            </div>
            <div class="weui_cell_ft">${sessionScope.customer.mobilePhone}</div>
        </div>
    </div>
    
<%--     <div class="weui_cells">
        <div class="weui_cell">
            <div class="weui_cell_bd weui_cell_primary">
                <p>身份证</p>
            </div>
            <div class="weui_cell_ft">${sessionScope.customer.idCard}</div>
        </div>
    </div>
 --%>
	<div class="weui_cells weui_cells_access">
        <a class="weui_cell" href="javascript:;">
            <div class="weui_cell_bd weui_cell_primary">
                <p>总订单数</p>
            </div>
            <div class="weui_cell_ft" onclick="window.location.href='${ctx}/order/myOrders'">${orderNum}</div>
        </a>
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