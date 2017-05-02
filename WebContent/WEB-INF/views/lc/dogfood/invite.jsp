<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/views/include_head.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>邀请好友助力</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
<meta name="format-detection"content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />

<meta name="keywords" content="">
<meta name="description" content="">

<link rel="stylesheet" type="text/css" href="${ctx}/static/css/Basc.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/index.css">

<script src="${ctx}/static/js/remjs.js" type="text/javascript"></script>
<script src="${ctx}/static/js/jquery-2.2.3.min.js" type="text/javascript"></script>

</head>
<body>

<div class="invite">
	<div class="invite-cont1">
		<div class="logo"></div>
		<b></b>
	</div>
	<div class="invite-cont2">
		<div class="invite-cont2-pic1"></div>
		<div class="invite-cont2-pic2"></div>
		<div class="invite-cont2-pic3"></div>
	</div>
	<div class="invite-cont3">
		<div class="invite-cont3-btn clear">
			<a href="javascript:void(0);" class="left invite-cont3-btn1"></a>
			<a href="javascript:void(0);" class="right invite-cont3-btn2"></a>
		</div>
		<div class="index-foot ">
			<p>一起理财&nbsp;&nbsp;&nbsp;央企控股稳健型金融科技平台</p>
		</div>
	</div>
</div>

<div class="ggtc" style="display: none">
	<div class="ggtc-bg"></div>
	<div class="ggtc-cont clear">
		<div class="ggtc-cont-text">
			<p class="text40 pt20">
			</p>
		</div>
		<a href="javascript:void(0);" class="ggtc-btn1"></a>
	</div>
</div>

<script type="text/javascript">


var inviterOpenid = "${inviterOpenid}";
var openid = "${openid}";

function assistance(){
	if(inviterOpenid == openid){
		notAssistance();
		return false;
	}
	if(inviterOpenid){
		// 判断用户是否关注,关注则保存助力记录，未关注则返回用于生成带有邀请者参数的二维码ticket
		$.ajax({
	    	url : '${ctx }/dog-food/assistance',
	       	type : 'post',
	       	data : {inviterOpenid: inviterOpenid},
	       	success : function(data){
	       		// 0:未关注  1：已经助力  2：助力成功
	       		var status = data.status;
	       		if (status == 0) {
	       			// 未关注
	       			subscribe(data.ticket);
	       		} else if(status == 2) {
	       			// 已关注，助力成功
	       			assistanceSucc();
	       		} else if(status == 1) {
	       			// 已经助力
	       			alreadyAssistance();
	       		}  else if(status == -1) {
                    // 活动已结束
                    gameOver();
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
}

function subscribe(ticket){
	var text = "做个活动不容易！<br />高颜值的您居然还木有关注我们~<br /><br /><p class='text40'  style='color:#F00'>识别二维码，关注成功即为好友助力成功</p>";
	$(".ggtc-cont-text p").removeClass().addClass("text40 pt20");
	$(".ggtc-cont-text p").html(text);
	$(".ggtc-cont-text p:first-child").append("<span></span>");
	var url = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=" + ticket;
	var $img = $("<img src='" + url + "' width=105rem/>")
	$(".ggtc-cont-text span").append($img);
	$(".ggtc-cont clear a").removeClass();
	$(".ggtc").show();
}

function assistanceSucc(){
	var text = "恭喜你成功帮助好友获得一次刷狗粮机会<br /><br />够！朋！友！";
	$(".ggtc-cont-text p").removeClass().addClass("text40 pt180");
	$(".ggtc-cont-text p").html(text);
	$(".ggtc-cont clear a").removeClass().addClass("ggtc-btn4");
	$(".ggtc-cont clear a").attr("href", "${ctx}/dog-food/index");
	$(".ggtc").show();
}

function alreadyAssistance(){
	var text = "您已为好友助力成功，<br />无法重复助力哦！";
	$(".ggtc-cont-text p").removeClass().addClass("text40 pt180");
	$(".ggtc-cont-text p").html(text);
	$(".ggtc-cont clear a").removeClass().addClass("ggtc-btn4");
	$(".ggtc").show();
}

function notAssistance(){
	var text = "sorry，您不能为自己助力哦";
	$(".ggtc-cont-text p").removeClass().addClass("text55 pt180");
	$(".ggtc-cont-text p").html(text);
	$(".ggtc-cont clear a").removeClass().addClass("ggtc-btn4");
	$(".ggtc").show();
}

function gameOver() {
	var text = "该活动已结束";
    $(".ggtc-cont-text p").removeClass().addClass("text55 pt180");
    $(".ggtc-cont-text p").html(text);
    $(".ggtc-cont clear a").removeClass().addClass("ggtc-btn4");
    $(".ggtc").show();
}

function goIndex(){
	if(inviterOpenid != openid) {
		$.ajax({
	        url : '/dog-food/clickBtn',
	        type : 'post',
	        async : false
	    });
	}
	
	window.location.href="/dog-food/index";
}

$(document).ready(function(){
	
	$(".ggtc").click(function(){
		$('.ggtc').hide();
	});
	
	// 助力
	$(".invite-cont3-btn1").click(function(){
		assistance();
	});
	
	// 我也要刷
	$(".invite-cont3-btn2").click(function(){
		goIndex();
	});
	
	$.ajax({
        url : '/dog-food/action',
        type : 'post'
    });
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