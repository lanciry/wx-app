<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/views/include_head.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>我的订单</title>
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
		body {
			background-color: #C9C9C9;
		}
		.weui_cells_title {
			margin-bottom: 20px;
		}
		.weui_cells{
			margin: 10px;
			font-size: 14px;
			background-color: #FFFFFF;
		}
		.red {
			color: red;
		}
	</style>
</head>
<body>
	<div class="weui_cells_title">
    	<h4 align="center">我的订单</h4>
    </div>
    
    <c:if test="${empty list }">
    	<h4 align="center">未查询到订单信息</h4>
    </c:if>
    
    <c:forEach items="${list }" var="order">
    	<div class="weui_cells">
	        <a class="weui_cell" href="javascript:;">
	            <div class="weui_cell_bd weui_cell_primary">
	                <p>提交订单时间</p>
	            </div>
	            <div class="weui_cell_ft">
	            	<fmt:formatDate value="${order.createTime }" pattern="yyyy-MM-dd HH:mm"/>
	            </div>
	        </a>
	        <a class="weui_cell" href="javascript:;">
	            <div class="weui_cell_bd weui_cell_primary">
	                <p>订单号</p>
	            </div>
	            <div class="weui_cell_ft">${order.orderNo }</div>
	        </a>
	        <a class="weui_cell" href="javascript:;">
	            <div class="weui_cell_bd weui_cell_primary">
	                <p>贷款人</p>
	            </div>
	            <div class="weui_cell_ft">${order.borrower }</div>
	        </a>
	        <a class="weui_cell weui_cells_access" href="javascript:;">
	            <div class="weui_cell_bd weui_cell_primary">
	                <p>订单状态</p>
	            </div>
	            <div class="weui_cell_ft" onclick="orderDetail('${order.id }');">
	            	<span class="red">
		            	<c:if test="${order.status eq 0 }">已取消</c:if>
		            	<c:if test="${order.status eq 1 }">待初评</c:if>
		            	<c:if test="${order.status eq 2 }">初评通过</c:if>
		            	<c:if test="${order.status eq 3 }">初评拒绝</c:if>
		            	<c:if test="${order.status eq 4 }">申请下户</c:if>
		            	<c:if test="${order.status eq 5 }">分配下户</c:if>
		            	<c:if test="${order.status eq 9 }">放弃</c:if>
		            	<c:if test="${order.status eq 6 }">综合结论</c:if>
		            	<c:if test="${order.status eq 7 }">待初审</c:if>
		            	<c:if test="${order.status eq 8 }">待终审</c:if>
	            	</span>
	            </div>
	        </a>
	    </div>
    </c:forEach>
    
    <script src="${ctx }/static/js/jweixin-1.0.0.js"></script>
	<script type="text/javascript">
		function orderDetail(orderId)
		{
			window.location.href="${base}/order/detail?id="+orderId;
		}
		
		wx.config({
		});
		
		wx.ready(function(){
			wx.hideOptionMenu();
		});
	</script>
</body>
</html>