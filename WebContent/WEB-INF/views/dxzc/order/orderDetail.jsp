<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
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
		.weui_cells_title {
			margin-bottom: 20px;
		}
		.red {
			color: red;
		}
		.weui_opr_area {
			margin-bottom: 10px;
		}
		.huanhang {
			width:70%;
		}
	</style>
</head>
<body>
    <c:if test="${empty order}">
    	<div class="weui_cells_title">
	    	<h2 align="center">未查询到订单详细信息</h2>
	    </div>
    </c:if>
    <c:if test="${not empty order}">
    	<div class="weui_cells_title">
	    	<h4 align="center">订单详细</h4>
	    </div>
	    
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
	        <a class="weui_cell" href="javascript:;">
	            <div class="weui_cell_bd weui_cell_primary">
	                <p>贷款金额</p>
	            </div>
	            <div class="weui_cell_ft">${order.loanAmount } 万元</div>
	        </a>
	        <a class="weui_cell" href="javascript:;">
	            <div class="weui_cell_bd weui_cell_primary">
	                <p>贷款期限</p>
	            </div>
	            <div class="weui_cell_ft">${order.loanTerm } 个月</div>
	        </a>
	        <a class="weui_cell" href="javascript:;">
	            <div class="weui_cell_bd weui_cell_primary">
	                <p>抵押顺位</p>
	            </div>
	            <div class="weui_cell_ft">
	            	<c:if test="${order.type eq 1 }">一抵</c:if>
	            	<c:if test="${order.type eq 2 }">二抵</c:if>
	            </div>
	        </a>
	        <a class="weui_cell" href="javascript:;">
	            <div class="weui_cell_bd weui_cell_primary">
	                <p>订单状态</p>
	            </div>
	            <div class="weui_cell_ft">
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
	        <a class="weui_cell" href="javascript:;">
	            <div class="weui_cell_bd weui_cell_primary">
	                <p>初评时间</p>
	            </div>
	            <div class="weui_cell_ft">
	            	<fmt:formatDate value="${order.firstAuditTime }" pattern="yyyy-MM-dd HH:mm"/>
	            </div>
	        </a>
	        <a class="weui_cell" href="javascript:;">
	            <div class="weui_cell_bd weui_cell_primary">
	                <p>初评反馈</p>
	            </div>
	            <div class="weui_cell_ft huanhang">
	            	${order.firstAuditRemarks }
	            </div>
	        </a>
        	<a class="weui_cell" href="javascript:;">
	            <div class="weui_cell_bd weui_cell_primary">
	                <p>下户日期</p>
	            </div>
	            <div class="weui_cell_ft"><fmt:formatDate value="${order.xhDay }" pattern="yyyy-MM-dd"/></div>
	        </a>
	        <a class="weui_cell" href="javascript:;">
	            <div class="weui_cell_bd weui_cell_primary">
	                <p>下户时间段</p>
	            </div>
	            <div class="weui_cell_ft">${order.xhTime }点</div>
	        </a>
	    </div>
	    
	    <div class="weui_opr_area">
            <p class="weui_btn_area">
            	<c:if test="${order.status eq 2 }">
                	<a href="javascript:;" class="weui_btn weui_btn_primary" id="applyXH">申请下户</a>
                </c:if>
                <c:if test="${order.status ne 0  and order.status ne 3  and order.status ne 9 }">
                	<a href="javascript:;" class="weui_btn weui_btn_default" id="cancel">取消订单</a>
                </c:if>
            </p>
        </div>
    </c:if>
    
    <script type="text/javascript" src="${ctx }/static/js/jquery-2.2.3.min.js"></script>
    <script src="${ctx }/static/js/jweixin-1.0.0.js"></script>
    <script type="text/javascript" src="${ctx }/static/js/my-weui.js"></script>
	<script type="text/javascript">
		var hasCancel = false;
		$(document).ready(function(){
			$('#cancel').click(function(){
				weui.prompt("请输入取消原因",function(reason){
					if (reason != '') {
						if (hasCancel) {
							return false;
						}
						hasCancel = true;
						
						$.ajax({
			            	url : '${ctx }/order/cancel',
			            	type : 'post',
			            	data : {
			            		'id' : '${order.id}',
			            		'reason' : reason
			            	},
			            	success : function(data){
			            		var dataJson = $.parseJSON(data);
			            		if (dataJson.status == 'Y') {
			            			weui.toast("取消成功",function(){
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
						        	alert("删除失败,请稍后再试！");
						        }
							}
			            });
					}
				});
			});
			
			$('#applyXH').click(function(){
				window.location.href="${ctx}/order/applyXH?id=${order.id}";
			});
		});
		
		wx.config({
		});
		
		wx.ready(function(){
			wx.hideOptionMenu();
		});
	</script>
</body>
</html>