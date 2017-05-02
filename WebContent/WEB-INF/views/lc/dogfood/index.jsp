<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/views/include_head.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>首页</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
<meta name="format-detection"content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />

<meta name="keywords" content="">
<meta name="description" content="">
<link rel="stylesheet" href="${ctx }/static/css/Basc.css">
<link rel="stylesheet" href="${ctx }/static/css/index.css">

<script src="${ctx }/static/js/remjs.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx }/static/js/jquery-2.2.3.min.js"></script>

</head>
<body>
    <div class="index">
        <div class="index-top">
            <div class="logo"></div>
        </div>
        <div class="index-top-text"></div>
        
        <div class="index-gl">
            <!---数字 --->
            <!--<div class="index-gl-num"><b class="num0"></b><b class="num1"></b></div>-->
            <!------散落的心型------->
            <ul class="index-gl-heart">
                <li class="heart-h1 wz1"></li>
                <li class="heart-h2 wz2"></li>
                <li class="heart-f1 wz3"></li>
                <li class="heart-f2 wz4"></li>
                <li class="heart-b1 wz5"></li>
                <li class="heart-h1 wz6"></li>
                <li class="heart-h2 wz7"></li>
                <li class="heart-f1 wz8"></li>
                <li class="heart-f2 wz9"></li>
                <li class="heart-b1 wz10"></li>
                <li class="heart-h1 wz11"></li>
                <li class="heart-h2 wz12"></li>
                <li class="heart-f1 wz13"></li>
            </ul>
            <div class="index-gl-gp">
                <a href="javascript:;" id="produce"></a>
                <p>获得狗粮：<b>${totalDogFood }</b>粒</p>
            </div>
        </div>
        <div class="index-jiao-icon">
            <b></b>
        </div>
    
        <div class="index-dhtx-btn clear">
            <a href="javascript:;" class="left index-dhtx-btn1" id="conversion"></a>
            <a href="javascript:;" class="right index-dhtx-btn2" id="cash"></a>
        </div>
        
        <ul class="index-numzs-text clear">
            <li class="left hdgz" id="hdgz"><a href="javascript:;">活动规则</a></li>
            <li class="left"><p>已兑换：<b>${convertedAmount }</b>元</p></li>
            <li class="left"><p>已提现：<b>${cashedAmount }</b>元</p></li>
        </ul>
        
        <div class="index-btn-more clear">
            <a href="javascript:;" id="more"></a>
            <p>拥有刷狗粮次数:<b>${hasNum }</b>次</p>
        </div>
        
        <div class="index-peo ">
            <h3>帮你助力的好友：</h3>
            <!------好友头像   放到 b 标签里面------->
            <dl class="clear" id="index-peo-dl">
                <c:forEach items="${assistanceList }" var="dogfoodAssistance">
                    <dd class="left"><b><img src="${dogfoodAssistance.assistorHeadimg }" /></b></dd>
                </c:forEach>
            </dl>
            <p></p>
        </div>
        <div style="margin-top: 0.8rem;">
            <p style="text-align: center;font-size: 0.35rem;color: #333;">
                <c:if test="${fn:length(assistanceList) eq 0}">
                                        暂时还木有好友给你助力哦
                </c:if>
                <c:if test="${fn:length(assistanceList) > 0}">
                                         共有${fn:length(assistanceList)}位好友给你助力哦
                </c:if>
            </p>
        </div>
        <div class="index-foot ">
            <p>一起理财&nbsp;&nbsp;&nbsp;央企控股稳健型金融科技平台</p>
        </div>
    </div>
    
<!------活动规则  弹窗--------->
<div class="ggtc temp" id="hdgz-tc">
    <div class="ggtc-bg"></div>
    <div class="ggtc-hdgzcont clear">
        <div class="ggtc-hdgz-text">
            <h3>活动规则</h3>
            <p>1.点击【开刷】按钮，在15秒倒计时内将漂浮的心拽到狗粮盆中，可获得随机狗粮奖励</p>
            <p>2.每20粒狗粮可兑换1元现金奖励，点击【兑换】按钮即可兑换</p>
            <p>3.现金奖励最终会以体验金的形式发至您一起理财的账户中</p>
            <p>4.每个用户可连续3天参与本活动，首次参与活动，有2次免费刷“狗粮”机会，剩余2天每天可获得1次免费机会</p>
            <p>5.每个用户每天最多有7次开刷机会，可通过分享活动页面，邀请好友为自己助力，获得额外机会</p>
            <p>6.每个好友仅限助力1次</p>
            <p>7.活动截止时间：2017.04.30</p>
            <p>8.客服电话：40010-68178</p>
<!--             <p>8.本活动最终解释权归一起理财所有</p> -->
        </div>
        <a href="javascript:;" id="ruleOK" class="ggtc-btn1"></a>
    </div>
</div>  
<!-- 共用弹窗 -->
<div class="ggtc" style="display: none" id="ggtc">
    <div class="ggtc-bg temp"></div>
    <div class="ggtc-cont clear">
        <div class="ggtc-cont-text temp">
            <p></p>
        </div>
        <a href="javascript:;"  class="ggtc-btn1 temp" style="display: none;"></a>
        <a href="javascript:;"  class="ggtc-btn4" style="display: none;"></a>
    </div>
</div>

<!-- 确认是否提现 -->
<div class="ggtc" id="whetherWithdraw">
    <div class="ggtc-bg temp"></div>
    <div class="ggtc-cont clear">
        <div class="ggtc-cont-text temp">
            <p class="text50 pt150">每天只有一次提现的机会哦~ <br />你确定要提现了么？</p>
        </div>
        <a href="javascript:;"  class="ggtc-btn5" ></a>
    </div>
</div>

<!-- 关注 -->
<div class="ggtc" id="erweima">
    <div class="ggtc-bg"></div>
    <div class="ggtc-cont clear">
        <div class="ggtc-cont-text">
            <p class="text40 pt50">
                                还没有关注我们哦~<br />
                <img style="width:3rem;height:2.8rem;margin:10px 0;" src='${ctx }/static/images/ewm.png' /><br />
                                识别关注，还有大批红包等你来拿
            </p>
        </div>
        <a href="javascript:;"  class="ggtc-btn1 temp"></a>
    </div>
</div>

<!-- 兑换，提现成功弹窗 -->
<div class="ggtc" style="display: none" id="ggtc_2">
    <div class="ggtc-bg"></div>
    <div class="ggtc-cont clear">
        <div class="ggtc-cont-text">
            <p></p>
        </div>
        <a href="javascript:;" onclick="javascript:window.location.reload();" class="ggtc-btn1"></a>
    </div>
</div>
<!-- 提现弹窗 -->
<div class="ggtc" id="cashDiv">
    <div class="ggtc-bg cashTemp"></div>
    <div class="ggtc-fxhy-pic cashTemp"></div>
    <div class="ggtc-cont clear" style="width: 8.7rem;height: 11rem;margin-top: -5.5rem;margin-left: -4.35rem;">
        <div class="ggtc-cont-text">
            <p class="text46 pt20 cashTemp">恭喜您！</p>
            <p class="text50 pt20 cashTemp">获得<label id="cashAmount"></label>元现金红包</p>
            <p class="text40 pt20 cashTemp">此红包由“一起理财”帅帅的BOSS暖心送出</p>
            <p class="text40 pt20 cashTemp">红包将以体验金的形式发送至您的账户</p>
            <div class="ggtc-account-btn clear">
                <b class="left n_no"  id="noAccount"></b>
                <b class="right y_no"  id="hasAccount"></b>
            </div>
            <div class="ggtc-inp" style="margin-top: 0.1rem;">
                <input type="tel" id="mobile" maxlength="11" name="mobile" placeholder="手机号" />
            </div>
        </div>
        <a href="javascript:;" id="cashing" class="ggtc-btn2" style="bottom: 0.9rem;"></a>
    </div>
</div>  

<!-- 分享遮罩 -->
<div class="mask"></div>

<script type="text/javascript">
var isCashing = false;
var cashType = -1;
$(document).ready(function(){
    $(".index-peo p").click(function(){
        if($('#index-peo-dl').hasClass('heightAuto')){
            $('#index-peo-dl').removeClass('heightAuto');
        }else {
            $('#index-peo-dl').addClass("heightAuto");
        }
    });
    
    $('.temp,.mask,.cashTemp').click(function(){
        $('.ggtc').hide();
    });
    $('.mask').click(function(){
        $(this).hide();
    });
    $('#hdgz').click(function(){
        $('#hdgz-tc').show();
    });
    $('#ruleOK').click(function(){
        $('#hdgz-tc').hide();
    });
    
    //弹窗分享遮罩
    $(".ggtc-btn4").click(function(){
        $('.ggtc').hide();
        $(".mask").show();
    });
    
    $("#more").click(function(){
        //判断活动是否已结束
        if (${isEnd}) {
            $("#ggtc").find("p").removeClass().addClass("text55 pt100").html("该活动已结束").end()
                .find("a").hide().end().find(".ggtc-btn1").show().end().show();
            return false;
        }
        
        $(".mask").show();
    });
    
    $("#noAccount").click(function(){
        $("#hasAccount").removeClass("y_yes");
        $(this).addClass("n_yes");
        cashType = 0;
    });
    $("#hasAccount").click(function(){
        $("#noAccount").removeClass("n_yes");
        $(this).addClass("y_yes");
        cashType = 1
    });
    
    //开刷
    $("#produce").click(function(){
        $.ajax({
            url: "/dog-food/canProduce",
            type: "get",
            dataType : "json",
        }).done(function( data ) {
            var status = data.status;
            if (status == 'Y') {
                window.location.href="/dog-food/game";
            } else if (status == '0') {
                $("#erweima").show();
                return false;
            } else if (status == '1') {
                $("#ggtc").find("p").removeClass().addClass("text55 pt100").html("该活动已结束").end()
                   .find("a").hide().end().find(".ggtc-btn1").show().end().show();
                return false;
            } else if (status == '2') {
                $("#ggtc").find("p").removeClass().addClass("text55 pt100").html("该活动只能连续参与3天，您已达到活动参与时长").end()
                   .find("a").hide().end().find(".ggtc-btn1").show().end().show();
                return false;
            } else if (status == '3') {
                $("#ggtc").find("p").html("您已经达到今日游戏次数上限<br />记得明日继续来刷哦！<br /><br />记得点击【兑换】按钮可兑换现金红包呦~")
                    .removeClass().addClass("text50 pt100").end().find("a").hide().end().find(".ggtc-btn1").show().end().show();
                return false;
            } else if (status == '4') {
                $("#ggtc").find("p").html("抱歉哦~您的游戏次数已用光<br />点击【获得更多次数】按钮<br />让好友助你一臂之力<br />获得更多现金红包")
                    .removeClass().addClass("text50 pt100").end().find("a").hide().end().find(".ggtc-btn4").show().end().show();
                return false;
            } else {
                $("#ggtc").find("p").removeClass().addClass("text55 pt100").html("系统错误，请联系客服").end()
                   .find("a").hide().end().find(".ggtc-btn1").show().end().show();
                return false;
            }
        }).fail(function( xhr, status, errorThrown ) {
            alert( "网络出现错误" );
        }).always(function( xhr, status ) {
        });
    });
    
    var isConvert = false;
    //兑换
    $("#conversion").click(function(){
        if (isConvert) {
            return false;
        }
        
        isConvert = true;
        
        $.ajax({
            url: "/dog-food/conversion",
            type: "get",
            dataType : "json",
        }).done(function( data ) {
            var status = data.status;
            if (status == 'NONE') {
                $("#ggtc").find("p").removeClass().addClass("text50 pt100").html("您还无可兑换的狗粮哦<br /><br />快去点击【开刷】按钮参与活动").end()
                   .find("a").hide().end().find(".ggtc-btn1").show().end().show();
            } else if (status == 'Y') {
                var amount = data.redAmount; 
                var num = parseInt(amount*20);
                $("#ggtc_2").find("p").html("恭喜您用"+num+"粒狗粮<br />成功兑换"+amount+"元现金红包<br /><br />想要红包入钱包，点击【提现】按钮即可")
                   .removeClass().addClass("text50 pt100").end().show();
            } else {
                $("#ggtc").find("p").removeClass().addClass("text55 pt100").html(data.msg).end()
                   .find("a").hide().end().find(".ggtc-btn1").show().end().show();
            }
        }).fail(function( xhr, status, errorThrown ) {
            alert( "网络出现错误" );
        }).always(function( xhr, status ) {
            isConvert = false;
        });
    });
    
    var isCash = false;
    //提现查询可提现金额
    $("#cash").click(function(){
        if (isCash) {
            return false;
        }
        
        isCash = true;
        
        $.ajax({
            url: "/dog-food/noCashAmount",
            type: "get",
            dataType : "json",
        }).done(function( data ) {
            var status = data.status;
            if (status == 'NONE') {
                $("#ggtc").find("p").removeClass().addClass("text50 pt150").html("您还没有可提现的红包<br /><br />快去点击【兑换】按钮兑换红包").end()
                    .find("a").hide().end().find(".ggtc-btn1").show().end().show();
            } else if (status == 'NO') {
            	$("#ggtc").find("p").removeClass().addClass("text50 pt150").html("您今天已经提现啦，剩余"+data.noCashAmount+"元可明天继续提现哦").end()
                    .find("a").hide().end().find(".ggtc-btn1").show().end().show();
            } else if (status == 'Y') {
            	//先弹出一天只能提现一次的弹窗
            	$("#whetherWithdraw").show();
                $("#cashAmount").text(data.noCashAmount);
            } else {
                $("#ggtc").find("p").removeClass().addClass("text50 pt100").html(data.msg).end()
                    .find("a").hide().end().find(".ggtc-btn1").show().end().show();
            }
        }).fail(function( xhr, status, errorThrown ) {
            alert( "网络出现错误" );
        }).always(function( xhr, status ) {
            isCash = false;
        });
    });
    
    $(".ggtc-btn5").click(function(){
        $(".ggtc").hide();
        $("#cashDiv").show();
    });
    
    //提现
    $("#cashing").click(function(){
        if (isCashing) {
            return false;
        }
        
        var mobile = $("#mobile").val();
        if(!(/1[0-9]{10}/.test(mobile))){
            alert("手机号码格式有误！");
            $("#mobile").focus();
            return false;
        }
        
        isCashing = true;
        
        if (cashType == 0) {
            noAccount(mobile);
        } else if (cashType == 1) {
            hasAccount(mobile);
        } else {
            alert("请选择是否有账号");
            isCashing = false;
            return false;
        }
    });
});
//已有账号
function hasAccount(mobile) {
    $.ajax({
        url: "/dog-food/cash",
        type: "post",
        data : {'mobile':mobile},
        dataType : "json",
    }).done(function( data ) {
        var status = data.status;
        if (status == 'Y') {
            $("#ggtc_2").find("p").html("恭喜您成功兑换"+data.amount+"元体验金<br />可登陆一起理财官网进行查看").removeClass().addClass("text50 pt150").end().show();
        } else {
            $("#ggtc").find("p").removeClass().addClass("text50 pt150").html(data.msg).end()
                .find("a").hide().end().find(".ggtc-btn1").show().end().show();
        }
    }).fail(function( xhr, status, errorThrown ) {
        alert( "网络出现错误" );
    }).always(function( xhr, status ) {
        $("#cashDiv").hide();
        isCashing = false;
    });
}
//未有账号
function noAccount(mobile) {
    $.ajax({
        url: "/dog-food/toRegister",
        type: "post",
        dataType : "json",
        data : {
            'mobile':mobile
        }
    }).done(function( data ) {
        var status = data.status;
        if (status == 'Y') {
            window.location.href=data.url+"?orderId="+data.orderId+"&amount="+data.amount+"&mobile="+mobile+"&signature="+data.signature;
        } else {
            $("#ggtc").find("p").removeClass().addClass("text50 pt150").html(data.msg).end()
                .find("a").hide().end().find(".ggtc-btn1").show().end().show();
        }
    }).fail(function( xhr, status, errorThrown ) {
        alert( "网络出现错误" );
    }).always(function( xhr, status ) {
        $("#cashDiv").hide();
        isCashing = false;
    });
}
</script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script type="text/javascript">
    wx.config({
        debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
        appId: '${appid}', // 必填，公众号的唯一标识
        timestamp: '${timestamp}', // 必填，生成签名的时间戳
        nonceStr: '${nonceStr}', // 必填，生成签名的随机串
        signature: '${signature}',// 必填，签名，见附录1
        jsApiList: ["hideOptionMenu", "showOptionMenu", "onMenuShareTimeline", "onMenuShareAppMessage","hideMenuItems"] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
    });
    //https://open.weixin.qq.com/connect/oauth2/authorize?appid=${appid}&redirect_uri=${ctx }/oauth&response_type=code&scope=snsapi_base&state=/dog-food/invite%3finviterOpenid=${openid}#wechat_redirect
    wx.ready(function(){
        wx.showOptionMenu();
        //分享到朋友圈
        wx.onMenuShareTimeline({
            title: '您的好友${nickname}正在赚红包，红包到手即可提现！', // 分享标题
            desc: '一个用人品换现金红包的游戏！身边的朋友都开始玩了！', // 分享描述
            link: '${ctx }/dog-food/invite?inviterOpenid=${openid}', // 分享链接
            imgUrl: '${ctx }/static/images/share.jpg', // 分享图标
            success: function () {
                // 用户确认分享后执行的回调函数
                $.ajax({
                    url : '/dog-food/share',
                    type : 'post'
                });
            },
            cancel: function () {
                // 用户取消分享后执行的回调函数
            }
        });
        //分享给朋友
        wx.onMenuShareAppMessage({
            title: '您的好友${nickname}正在赚红包，红包到手即可提现！', // 分享标题
            desc: '一个用人品换现金红包的游戏！身边的朋友都开始玩了！', // 分享描述
            link: '${ctx }/dog-food/invite?inviterOpenid=${openid}', // 分享链接
            imgUrl: '${ctx }/static/images/share.jpg', // 分享图标
            success: function () {
                // 用户确认分享后执行的回调函数
                $.ajax({
                    url : '/dog-food/share',
                    type : 'post'
                });
            },
            cancel: function () {
                // 用户取消分享后执行的回调函数
            }
        });
    });
</script>
</body>
</html>



