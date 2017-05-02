<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/views/include_head.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>注册绑定</title>
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
			width: 4em;
		}
		.error {
			margin-top: 10px;
			color: red;
			font-size: 14px;
		}
	</style>
</head>
<body ontouchstart>
    <div class="weui_cells_title">
    	<h5 align="center">请先注册成为经纪人再提交订单</h5>
    </div>
	<s:form action="/bind/save" method="post" modelAttribute="customer" id="form">
		<div class="weui_cells weui_cells_form">
	        <div class="weui_cell">
	            <div class="weui_cell_hd"><label class="weui_label">姓&nbsp;&nbsp;&nbsp;名：</label></div>
	            <div class="weui_cell_bd weui_cell_primary">
	                <s:input path="name" cssClass="weui_input" placeholder="请输真实姓名"/>
	            </div>
	        </div>
	        <div class="weui_cell">
	           <div class="weui_cell_hd"><label class="weui_label">手机号：</label></div>
	           <div class="weui_cell_bd weui_cell_primary">
	               <s:input path="mobilePhone" maxlength="11" cssClass="weui_input" placeholder="请输入手机号"/>
	           </div>
	       </div>
	       <div class="weui_cell">
	           <div class="weui_cell_hd"><label class="weui_label">邀请码：</label></div>
	           <div class="weui_cell_bd weui_cell_primary">
	               <s:input path="inviteCode" cssClass="weui_input" placeholder="请输入邀请码"/>
	           </div>
	       </div>
	    </div>
	</s:form>
	
	<h4 id="error" align="center" class="error">${errorMsg }</h4>
	
	<div class="weui_btn_area">
        <a class="weui_btn weui_btn_primary" href="javascript:" onclick="valid();">确定</a>
    </div>

	<script type="text/javascript" src="${ctx }/static/js/jquery-2.2.3.min.js"></script>
	<script type="text/javascript">
		var hasCommit = false;
		
		function valid()
		{
			var name = $("#name").val();
			var mobilePhone = $("#mobilePhone").val();
			var inviteCode = $("#inviteCode").val();
			if (name == '') {
				$("#error").text("姓名不能为空");
				$("#name").focus();
				return false;
			} else if (mobilePhone == '') {
				$("#error").text("手机号不能为空");
				$("#mobilePhone").focus();
				return false;
			} else if (!(/^1[0-9]{10}$/.test(mobilePhone))) {
				$("#error").text("手机号码格式不正确");
				$("#mobilePhone").focus();
				return false;
			} else if (inviteCode == '') {
				$("#error").text("邀请码不能为空");
				$("#inviteCode").focus();
				return false;
			}
			
			if (hasCommit) {
				return false;
			}
			hasCommit = true;
			
			$("#form").submit();
		}
	</script>
	<script src="${ctx }/static/js/jweixin-1.0.0.js"></script>
	<script type="text/javascript">
		wx.config({
		    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
		    appId: '${appid}', // 必填，公众号的唯一标识
		    timestamp: '${timestamp}', // 必填，生成签名的时间戳
		    nonceStr: '${nonceStr}', // 必填，生成签名的随机串
		    signature: '${signature}',// 必填，签名，见附录1
		    jsApiList: ["hideOptionMenu", "showOptionMenu", "onMenuShareTimeline", "onMenuShareAppMessage","hideMenuItems"] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
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