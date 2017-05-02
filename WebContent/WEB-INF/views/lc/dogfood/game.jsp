<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/views/include_head.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- Mobile Devices Support @begin -->
<meta name="referrer" content="always">
<meta content="application/xhtml+xml;charset=UTF-8" http-equiv="Content-Type">
<meta content="no-cache,must-revalidate" http-equiv="Cache-Control">
<meta content="no-cache" http-equiv="pragma">
<meta content="0" http-equiv="expires">
<meta name="referrer" content="always">
<meta content="telephone=no, address=no" name="format-detection">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
<meta name="apple-mobile-web-app-capable" content="yes" /> <!-- apple devices fullscreen -->
<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent" />
<link href="${ctx}/static/css/base.min.css?v=201703070929" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/css/bhmn.min.css?v=201610271901" rel="stylesheet" type="text/css" />

<link rel="stylesheet" type="text/css" href="${ctx}/static/css/Basc.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/index.css">

<script type='text/javascript'>

    var g_rem = 20;
    (function(win){
        var orientationX = false; //是否横屏进来
        if(win.orientation==90||win.orientation==-90){
            orientationX = true;
        }
        var docEl = win.document.documentElement,tid;
        function refreshRem1(){
            g_rem = docEl.getBoundingClientRect().width/16;
            !g_rem && (g_rem = 20);
            docEl.style.fontSize = g_rem + 'px';
        }
        function refreshRem2(){
            g_rem = docEl.getBoundingClientRect().width/16;
            if(!g_rem){
                return refreshRem1();
            }
            var width = docEl.getBoundingClientRect().width;
            var d = win.document.createElement('div');
            d.style.width = '1rem';
            d.style.display = "none";
            docEl.firstElementChild.appendChild(d);
            var defaultFontSize = parseFloat(window.getComputedStyle(d, null).getPropertyValue('width'));
            docEl.firstElementChild.removeChild(d);
            docEl.style.fontSize = (g_rem/defaultFontSize)*100 + '%';
        }
        var refreshRem = refreshRem2;
        try{
            win.addEventListener("orientationchange", function() {//触发手机横屏竖屏事件
                if(orientationX){
                    location.reload();
                }
            }, false);
        }catch(e){
            
        }
        
        /*win.addEventListener('resize', function() {
            clearTimeout(tid);
            tid = setTimeout(refreshRem, 100);
        }, false);*/
        win.addEventListener('pageshow', function(e) {
            if (e.persisted) {
                clearTimeout(tid);
                tid = setTimeout(refreshRem, 100);
            }
        }, false);
        refreshRem();
    })(window);
    
    function loadGamePreAssets(){}
</script>

</head>
<body class="gameTpl_-1">

<!-- loading页面 -->
    <div id="preloadPage" class="bg">
        
        <div class="spinner">
          <div class="spinner-container container1">
            <div class="circle1"></div>
            <div class="circle2"></div>
            <div class="circle3"></div>
            <div class="circle4"></div>
          </div>
          <div class="spinner-container container2">
            <div class="circle1"></div>
            <div class="circle2"></div>
            <div class="circle3"></div>
            <div class="circle4"></div>
          </div>
          <div class="spinner-container container3">
            <div class="circle1"></div>
            <div class="circle2"></div>
            <div class="circle3"></div>
            <div class="circle4"></div>
          </div>
        </div>
        
        <div class="info"><span id="loadPercent">0</span>%</div>
        <div id="userPreload" data-src="http://7020498.h40.faiusr.com/4/bTc5420m1j6x2m1h9T7x9GMxQTMjZjY.png"></div>
    </div>

    <input id="rightImg" type="hidden" value="http://7020498.h40.faiusr.com/4/164/ACgIABAEGAAg0ajXwwUoiJLHhQIwczh4.png" >
    <div class="ajaxLoadBg">
        <div class="ajaxLoadBar "></div>
    </div>
    <div class="body">      
        <div class="home">
            <div id="homeBgBox">
                <img id="homeBg" src="http://7020498.h40.faiusr.com/2/164/ACgIABACGAAgvoLAxQUo7eGYxgMwgAU4wAw.jpg"/>    
            </div>  
            <div class="gameInfoBox">
                <!-- <div class="titleImg imgContainer absCenter">
                    <img id="titleImg" class="slaveImg abs" src="http://7020498.h40.faiusr.com/4/164/ACgIABAEGAAgzYLAxQUo_IvVpAcw9gQ4rAI.png" style="width:15.9rem;height:7.7rem;top:0.25rem;left:0.1rem;"/>
                </div> -->
            </div>

            <div id="startBtn" class="startBtn imgContainer absCenter" style="top:0rem;">
                <img id="startBtnImg" class="slaveImg abs" src="http://7020498.h40.faiusr.com/4/164/ACgIABAEGAAg1ILAxQUouMyffjCEAjhu.png" style="width:7.45rem;height:3.15rem;top:19.8rem;left:4.15rem;"/>
            </div>
        </div>
        <div class="gameBox hide gameBgBox">
            <div id="gameBgBox">
                <img id="gameBg" src="${ctx}/static/images/ACgIABACGAAg3oLAxQUoz9fKxAEwgAU4wAw.jpg" style="width:100%;height:auto;"/>    
            </div>
            
<div id="gameTopBar" class="gameTopBar" style="display: none">
	<div class="userInfoBox">
		<div  class="userImgBox" style="border-color:rgba(255, 255, 255, 0.298039)"><img src="http://wx.qlogo.cn/mmopen/DfwNel8MSal5gqZM4ZHKiaT1sM6x2XPOpY2D4Tc99ia5LQibwTllK2RbXibibJSVfVEGOSMbV1NVibkQpbCVSt7ibt4TUue3pMibfQfa/0" class="userImg" /></div>
					
			<div id="grade" style="display: none" class="grade"></div>
			
	</div>
	<div class="timeBox">
		时间<br><span class="time">0</span>
	</div>
</div> 
            <div id="gameLayerBox">
                <div id="stayPlace" style="width:100%; height:auto;">
                    <img id="stayPlaceImg" src="${ctx}/static/images/ACgIABAEGAAg8ILAxQUo-oLTlgYwgAU45gE.png"/>
                </div>
                
                <canvas id="canvas" >你的浏览器不支持canvas，请换个牛逼点的浏览器，谢谢</canvas>
                
            </div>
            <div id="hint"></div>
            <div class="timeUpImg hide"></div>
        </div>
        
<div id="spxdPage"></div>
<div id="pageMusic" style="position:absolute;top:0;height:0;"></div>

<div class="ggtc" style="display: none;">
	<div class="ggtc-bg"></div>
	<div class="ggtc-cont clear" style=" width:12rem; height:14rem; margin-left:-6rem; margin-top:-7rem;">
		<div class="ggtc-cont-text">
			<p id="glContent" class="text100 pt280">恭喜您！<br/>获得2粒狗粮！<br/></p>
			<p class="text55 pt80">点击【兑换】按钮可兑换现金红包哦！</p>
		</div>
		<a href="${ctx}/dog-food/index" class="ggtc-btn1"></a>
	</div>
</div>	

    </div>
<script type="text/javascript">
    function loadGamePreAssets(){
        hg.assets.add(["",
		"${ctx}/static/images/heart-h1.png",
		"${ctx}/static/images/heart-h1.png",
		"${ctx}/static/images/icon-dog6.png",
		"${ctx}/static/images/11.png"]);
    }
</script>

<script type="text/javascript">
    var _manage = false,
        m_debug = false, 
        _resRoot = 'https://hdg.faisys.com',
        hg = {};

    (function(hg){
        /*
        hg.assets  : {
          complete
          loadComplete
          onReady
          onload
          loadPage
        }
        */
        var imgPaths = [],
        readyCallBack = [],
        loadCallBack = [];
        hg.assets = {
            complete: false,
            loadComplete: false,
            increment: 10 ,
            loadingStyle: 1 ,
            loadTimeout: 120000,
            add: function(src) {
                var self = this;
                if (self.complete || _manage) {
                    return
                }
                if (isArray(src)) {
                    for (var i = src.length - 1; i >= 0; i--) {
                        self.add(src[i])
                    }
                } else if (typeof src === 'string') {
                    imgPaths.push(src)
                }
                return this
            },
            onReady: function(callBack) {
                var self = this;
                if (self.complete) {
                    callBack()
                } else {
                    readyCallBack.push(callBack)
                }
                return self
            },
            onload: function(callBack) {
                var self = this;
                if (self.loadComplete) {
                    callBack()
                } else {
                    loadCallBack.push(callBack)
                }
                return self
            },
            loadPage: function() {
                if (_manage) return;
                var self = this,
                    percent = 1,
                    numLoaded = 0,
                    $loadPercent = document.getElementById("loadPercent"),
                    loadPallet;
                window.scrollTo(0, 0);
                if (self.loadingStyle === 1) {
                    loadimg(imgPaths)
                } else if (self.loadingStyle === 3) {
                    var logoImg = document.getElementById("preloadImg");
                    if (logoImg.complete) {
                        initLogoImg()
                    } else {
                        logoImg.onload = initLogoImg
                    }
                    function initLogoImg(img) {
                        var img = logoImg;
                        var h = img.height;
                        var w = img.width;
                        var bottomPageInfoDeg = -Math.atan(w / h) - Math.PI / 2;
                        var canvas = document.getElementById("preloadCanvas");
                        var ctx = canvas.getContext("2d");
                        var ratio = (window.devicePixelRatio || 1) / (ctx.backingStorePixelRatio || ctx.webkitBackingStorePixelRatio || ctx.mozBackingStorePixelRatio || ctx.msBackingStorePixelRatio || ctx.oBackingStorePixelRatio || ctx.backingStorePixelRatio || 1);
                        var drawImg = function() {
                                ctx.fillStyle = "#fff";
                                ctx.fillRect(0.2 * g_rem * ratio, 0.2 * g_rem * ratio, w * ratio, h * ratio);
                                ctx.drawImage(img, 0.2 * g_rem * ratio, 0.2 * g_rem * ratio, w * ratio, h * ratio)
                            };
                        if (h === w) {
                            if (h > 3.5 * g_rem) {
                                h = w = 3.5 * g_rem
                            }
                            drawImg = function() {
                                ctx.save();
                                ctx.beginPath();
                                ctx.arc(canvas.width / 2, canvas.height / 2, w / 2 * ratio, 0, 2 * Math.PI);
                                ctx.clip();
                                ctx.fillStyle = "#fff";
                                ctx.fill();
                                ctx.drawImage(img, 0.2 * g_rem * ratio, 0.2 * g_rem * ratio, w * ratio, h * ratio);
                                ctx.restore()
                            }
                        } else {
                            if (w > h && w > 8 * g_rem) {
                                h = 8 * g_rem * h / w;
                                w = 8 * g_rem
                            } else {
                                if (h > w && h > 8 * g_rem) {
                                    w = 8 * g_rem * w / h;
                                    h = 8 * g_rem
                                }
                            }
                        }
                        canvas.width = (w + 0.4 * g_rem) * ratio;
                        canvas.height = (h + 0.4 * g_rem) * ratio;
                        canvas.style.width = w + 0.4 * g_rem + "px";
                        canvas.style.height = h + 0.4 * g_rem + "px";
                        if (h === w) {
                            ctx.beginPath();
                            ctx.arc(canvas.width / 2, canvas.height / 2, canvas.width / 2, 0, 2 * Math.PI);
                            ctx.clip()
                        }
                        ctx.strokeStyle = "#69c5ff";
                        ctx.lineWidth = 8 * g_rem * ratio;
                        loadPallet = function(tmp) {
                            ctx.beginPath();
                            ctx.arc(canvas.width / 2, canvas.height / 2, 4 * g_rem * ratio, bottomPageInfoDeg, bottomPageInfoDeg + tmp * (2 * Math.PI / 100));
                            ctx.stroke();
                            drawImg()
                        };
                        loadimg(imgPaths)
                    }
                }
                function setPercent(percent) {
                    $loadPercent.innerHTML = percent;
                    if (percent === 100) {
                        var index = 0;
                        setTimeout(function() {
                            index++;
                            if (index < 10) {
                                loadPallet && loadPallet(120);
                                setTimeout(arguments.callee, 17)
                            } else {
                                loadPallet = null
                            }
                        }, 17)
                    }
                    loadPallet && loadPallet(percent)
                }
                function loading() {
                    if (numLoaded < imgPaths.length) {
                        if (percent < 100 - imgPaths.length + numLoaded) {
                            percent++;
                            setPercent(percent - 1)
                        }
                    } else {
                        percent += 10;
                        percent > 100 && (percent = 100);
                        setPercent(percent - 1)
                    }
                    if (percent >= 100 && typeof $ != 'undefined') {
                        setPercent(100);
                        var flag = $(window).height() / $(window).width() < 1.575;
                        var bgHeight = flag ? $(window).width() * 1.575 : $(window).height();
                        setTimeout(function() {
                            $("#preloadPage").addClass("leftClose");
                            $("#homeBgBox,.gameBgBox").css("height", bgHeight / g_rem + "rem");
                            //var theObject = g_config.style == 55 ? $(".gameBox .bottomSkill,#bottomSkill") : $(".home .bottomSkill,#bottomSkill");
                            //flag && theObject.css("top", (bgHeight - $(".bottomSkill").outerHeight()) / g_rem + "rem");
                            setTimeout(function() {
                                $loadPercent = null;
                                $("#preloadPage").hide()
                            }, 500);
                            complete();
                            self.loadComplete = true;
                            for (var n = loadCallBack.length, i = 0; i < n; i++) {
                                loadCallBack[i]()
                            }
                            loadCallBack = null;
                            logoImg = null;
                            $loadPercent = null
                        }, 300);
                        return
                    }
                    setTimeout(arguments.callee, 10)
                }
                function loadimg(arr) {
                    loading();
                    for (var i = arr.length - 1; i >= 0; i--) {
                        var img = new Image();
                        img.onload = loadCheckComplete;
                        img.onerror = loadCheckComplete;
                        img.src = arr[i];
                        self[arr[i]] = img
                    }
                    if (self.loadTimeout) {
                        self._loadTimer = setTimeout(loadTimeout, self.loadTimeout)
                    }
                }
                function loadTimeout() {}
                function loadCheckComplete() {
                    numLoaded++;
                    this.assets_complete = true;
                    if (numLoaded === imgPaths.length) {
                        complete()
                    }
                }
                function complete() {
                    if (self.complete) return;
                    clearTimeout(self._loadTimer);
                    numLoaded = imgPaths.length;
                    self.complete = true;
                    for (var n = readyCallBack.length, i = 0; i < n; i++) {
                        readyCallBack[i]()
                    }
                    readyCallBack = null
                }
            }
        };
    })(hg);
    /*----------------------------可编辑元素包装------------------------------------*/
    hg.edit = null;
    
    hg.edit = {};
    //通过conf文件配置可编辑元素的属性
    (function(hg){
        var origin = [{"name":"theGetPricePic","pos":{"left":"1.2rem","top":"1.5rem"},"size":{"width":"13.5rem","height":"16.75rem"},"path":[["https://hdg.faisys.com/image/faiImg.png"]]},{"name":"theGetPricePicTwo","pos":{"left":"1.2rem","top":"1.5rem"},"size":{"width":"13.5rem","height":"16.75rem"},"path":[["https://hdg.faisys.com/image/faiImg2.png"]]},{"name":"theGetPricePicThree","pos":{"left":"NaNrem","top":"NaNrem"},"size":{"width":"4rem","height":"4rem"},"path":[["https://hdg.faisys.com/image/faiImg3.png"]]},{"name":"lotsPot","pos":{"left":"4.9rem","top":"3rem"},"size":{"width":"6.15rem","height":"12.4rem"},"path":[["https://hdg.faisys.com/image/lots1.png"]]},{"name":"lotsShakeHand","pos":{"left":"4.5rem","top":"17.9rem"}}];
        var originDef = [{"name":"playInfo","pos":{"left":"3.5rem","top":"16rem","forParent":"false"},"css":[{"title":"常规文字","opt":0,"from":"#playInfo","css":[{"type":"size","name":"font-size","val":14,"defVal":14,"opt":0},{"type":"color","name":"color","defVal":"#9d3723","val":"","tra":-1,"defTra":-1,"opt":0},{"type":"color","name":"text-shadow","val":"","defVal":"","tra":-1,"defTra":-1,"opt":0}]},{"title":"参与机会","opt":0,"from":"#playInfo .specil","css":[{"type":"size","name":"font-size","val":14,"defVal":14,"opt":0},{"type":"color","name":"color","val":"","defVal":"#ee9c0a","tra":-1,"defTra":-1,"opt":0},{"type":"color","name":"text-shadow","val":"","defVal":"","tra":-1,"defTra":-1,"opt":0}]}],"edit":3},{"name":"theGetPricePic","pos":{"left":"1.2rem","top":"1.5rem"},"size":{"width":"13.5rem","height":"16.75rem"},"path":[["*_resRoot*/image/faiImg.png","图片01","5000k"]],"edit":"theGetPricePic","formDefaultStyle":true},{"name":"theGetPricePicTwo","pos":{"left":"1.2rem","top":"1.5rem"},"size":{"width":"13.5rem","height":"16.75rem"},"path":[["*_resRoot*/image/faiImg2.png","图片01","5000k"]],"edit":"theGetPricePicTwo","formDefaultStyle":true},{"name":"theGetPricePicThree","pos":{"left":"1.2rem","top":"1.5rem"},"size":{"width":"13.5rem","height":"16.75rem"},"path":[["*_resRoot*/image/faiImg3.png","图片01","5000k"]],"edit":"theGetPricePicThree","formDefaultStyle":true},{"name":"lotsPot","pos":{"left":"4.925rem","top":"3rem"},"size":{"width":"6.15rem","height":"12.4rem"},"path":[["*_resRoot*/image/lots1.png","图片01","5000k"]],"edit":"lotsPot","formDefaultStyle":true},{"name":"lotsShakeHand","pos":{"left":"4.5rem","top":"17.9rem","forParent":"false"},"edit":"_none","formDefaultStyle":true}];
        var originMod = null;
        
        function correct(list1,list2){
            return list2.map(function(val2){
                return list1.filter(function(val1){return val1.name === val2.name;})[0] || val2;
            });
        }
        originMod = originMod ? correct(originMod,originDef) : originMod;
        origin = originMod ? correct(origin,originMod) : correct(origin,originDef);
        //将图片放进缓存数组内
        for(var i=0;i < originDef.length;i++){
            var path = origin[i].path;
            var pathDef = originMod ? originMod[i].path : originDef[i].path;
            if(pathDef){
                if(!path){
                    origin[i].path = path = pathDef;
                }else if(!isArray(path[0]) && isArray(pathDef[0])){
                    origin[i].path = path = [].concat(pathDef).splice(0,1,path);
                }
                if(isArray(path[0])){
                    for(var j=0;j < pathDef.length;j++){
                        if(!path[j]) path[j] = pathDef[j];
                        if(!path[j][0]) path[j][0] = pathDef[j][0];
                        hg.assets.add(path[j][0].replace('*_resRoot*',_resRoot));
                    }
                }else{
                    if(!path[0]) path[0] = pathDef[0];
                    hg.assets.add(path[0].replace('*_resRoot*',_resRoot));
                }
            }
        }
        if(false){
            hg.assets.add("");
        }
        if(false && origin[1].path[0] == _resRoot + "/image/tgcm/answer.png"){
            origin[1].path[0] = [_resRoot + "/image/tgcm/drag.png"];
        }
        hg.edit.origin = origin;
        hg.edit.originDef = originDef;
        hg.edit.originMod = originMod;
    })(hg);
    
    /*-----------------------资源loading-----------------------------*/
    //存放所有loading好的image对象,src为key
    hg.assets.add([
        "http://7020498.h40.faiusr.com/2/164/ACgIABACGAAgvoLAxQUo7eGYxgMwgAU4wAw.jpg","https://hdg.faisys.com/image/logo.jpg","https://hdg.faisys.com/image/ruleImg.png","https://hdg.faisys.com/image/success.png","https://hdg.faisys.com/image/light.png","https://hdg.faisys.com/image/bird.png","https://hdg.faisys.com/image/musicOff.png","https://hdg.faisys.com/image/musicOn.png","https://hdg.faisys.com/image/auBg.jpg","http://wx.qlogo.cn/mmopen/DfwNel8MSal5gqZM4ZHKiaT1sM6x2XPOpY2D4Tc99ia5LQibwTllK2RbXibibJSVfVEGOSMbV1NVibkQpbCVSt7ibt4TUue3pMibfQfa/0",
    
        "http://7020498.h40.faiusr.com/4/164/ACgIABAEGAAgzYLAxQUo_IvVpAcw9gQ4rAI.png","http://7020498.h40.faiusr.com/4/164/ACgIABAEGAAg1ILAxQUouMyffjCEAjhu.png","http://7020498.h40.faiusr.com/2/164/ACgIABACGAAg3oLAxQUoz9fKxAEwgAU4wAw.jpg","https://hdg.faisys.com/image/lots1.png","https://hdg.faisys.com/image/lots2.png",
    
        'https://hdg.faisys.com/image/faiImg.png?v=201509171041',
    
    ]);
    loadGamePreAssets();
    hg.assets.loadPage();//开始loading页面
    //全局变量
    var g_config = {"showSkillSup":true,"showSlide":false,"isOpenAreaLimit":false,"isCheckPlayTimes":false,"showMenu":true,"buy":false,"isNewGame":false,"isModel":false,"test":false,"isOem":false,"scoreType":false,"countsTimeType":0,"sortType":0,"drawLimitDef":120,"scoreSet":120,"style":14,"aid":7020498,"gameId":164,"openId":"o1ueSjlWakjL2IjvzYWKQin4aRg8","playerId":0,"userName":"痴人说梦","headImg":"http://wx.qlogo.cn/mmopen/DfwNel8MSal5gqZM4ZHKiaT1sM6x2XPOpY2D4Tc99ia5LQibwTllK2RbXibibJSVfVEGOSMbV1NVibkQpbCVSt7ibt4TUue3pMibfQfa/0","authVer":2,"failSrcId":21,"scoreUnit":"分","testCMD":"","ajaxUrl":"https://hd.faisco.cn/ajax/","startTime":"2017-01-11 14:40","setGameTimeInterval":10,"isDoubleGame":false,"awardList":[],"comfort":{},"bestRankInfo":{},"awardInfoB":"","openAwardLinkNoDraw":false,"awardUsername":"","awardPhone":"","awardAddress":"","status":0,"afterWxCard":true,"haveAward":false,"showHelpGuide":false,"ipInfo":{},"contactNoDraw":"[-1]","isAOpenId":0,"linkInfoType":1,"afterLinkModify":true,"shareDeep":0,"isFromZhuliShare":false,"qrCodeUrl":"http://13179076.h40.faiusr.com/2/ACgIABACGAAgpJKzxgUoqLG1ywEwkAM4kAM.jpg"};
    var isPublish = false;
    var HdVerDef = {
        FREE : 0,  //免费版
        BY : 1,  //白银版
        BJ : 2,  //铂金版
    };
    function isArray(arr){
        return Object.prototype.toString.call(arr) === "[object Array]";
    }
</script>
<script type="text/javascript" src="${ctx}/static/js/jquery-core.min.js?v=201610271901"></script>

<script type="text/javascript" src="${ctx}/static/js/hdgame.min.js?v=201703141814"></script>
<script type="text/javascript" src="${ctx}/static/js/hdUtil.min.js?v=201610271901"></script>
<script type="text/javascript" src="${ctx}/static/js/lufylegend.min.js?v=201703071826"></script>
<script type="text/javascript">
function $$(selector){
    if(!_manage){
        return $('#__notfound__');
    }
    return parent.$(selector);
}
HdGame.gameDomain = 'hd.faisco.cn';
HdGame.gameDomainIgnoreStyle = 'hd.faisco.cn';
if(false){
    HdGame.gameDomain = 'hd.webportal.cc';
	HdGame.gameDomainIgnoreStyle = 'hd.webportal.cc';
}
HdGame.isReg = false;

/*---------------游戏函数回调--------------------*/
HdGame.initCallBack(hg,['startGame','beforeStartGame','startGamehead','home','again','jsFootEnd','showResult','changeBottomBar','showPoup','hidePoup','timeChange','beforeDraw','updateRankList']); //hdgame
hg.addCallBack(['setGameType','hpInit','hgLoadEnd','save','changeShow']); //hdportal
hg.showGameBox = true; //是否在开始游戏时默认显示游戏页
/*----------------------------可编辑元素包装------------------------------------*/
//通过conf文件配置可编辑元素的属性
if(hg.edit){
    HdGame.initEdit(hg.edit);
}
function parseRemToPx(rem){
    if(rem.indexOf('rem') === -1){
        return parseFloat(rem);
    }
    return parseFloat(rem)*g_rem;
}
function parsePxToRem(px){
    if(px.indexOf('px') === -1){
        return px;
    }
    return parseFloat(px)/g_rem + 'rem';
}
/*----------------------------------计时------------------------------------*/
hg.time = null;

(function(hg){
    var initTime = 15;
    if(!g_config.countsTimeType && initTime === 0){
        if(!_manage){
            initTime = 99999;
        }else{
            if(false){
                initTime = 15;
            }else{
                initTime = 15; 
            }
        }
        $(function(){$('.timeBox').hide();});
    }
    hg.time = HdGame.initTime(initTime);
})(hg);

/*----------------------------------计分------------------------------------*/

hg.grade = HdGame.initGrade();

/*----------------------------------声音-----------------------------------*/
hg.sound = HdGame.initSound([
	{"path":"${ctx}/static/voice/yinyue.mp3","fileName":"背景音乐.mp3","optFlag":0},
	{"path":"https://hdg.faisys.com/image/bhmn/bonus.mp3","fileName":"得分音效.mp3","optFlag":2}],
	[{"path":"${ctx}/static/voice/yinyue.mp3","fileName":"背景音乐.mp3","optFlag":0},
	 {"path":"*_resRoot*/image/bhmn/bonus.mp3","fileName":"得分音效.mp3","optFlag":2}],null);

/*----------------------------------获取ip信息-----------------------------------*/
//HdGame.initAreaLimit(false);
//
HdGame.tlog('openId',HdGame.getCookie('oppenId_20160112wx25367438c8ce0799'));
</script>

<script type="text/javascript">
HdGame.initWxConfig({"isHideShareBtn":false,"isHideMenuItems":false,"fullUrl":"https://hd.faisco.cn/7020498/164/load.html?style=14","nameList":["一等奖","二等奖","三等奖","四等奖","五等奖","六等奖","七等奖","八等奖","安慰奖"],"jsSdkAppid":"wx25367438c8ce0799","timestamp":"1490075801","nonce_str":"b5032c43-1839-4db9-bfae-a7f69f03f68c","signature":"d6b68ad47ee3aa0c76a15bd97e476a672ab17b40","cardTicket":"","dynamicShareUrlRootEmpty":false,"dynamicShareUrlRoot":"http://7020498-164.hdpyqd.com/","fsl":"b","shareDeep":0,"openStrongAttention":false,"shareImg":"http://7020498.h40.faiusr.com/2/164/ACgIABACGAAg5KrXwwUoh--awgMwyAE4yAE!160x160.jpg","activeName":"元宵快到碗里来","wxShareText_def":"轻轻松松就能抽到大奖，积攒多年的人品终于有用了，你也赶紧来抽奖吧！！","wxShareText_award":"<span class=\"tag\" contenteditable=\"false\">玩家名称<\/span>已经在活动里赢得<span class=\"tag\" contenteditable=\"false\">奖品名称<\/span>，你也快来玩游戏赢大奖吧！","wxShareText_rank":"<span class=\"tag\" contenteditable=\"false\">玩家名称<\/span>以<span class=\"tag\" contenteditable=\"false\">游戏成绩<\/span>的成绩傲视群雄，你敢来挑战Ta赢取丰厚奖品吗？","addDrawTime":1})

</script>
<script type="text/javascript">
    var _gameOver = false;
    var GameArg = {
        createTime : 0.8,
        first : true,
    };
    
    window.requestAnimFrame = function() {
        return window.requestAnimationFrame || window.webkitRequestAnimationFrame || window.mozRequestAnimationFrame || window.oRequestAnimationFrame || window.msRequestAnimationFrame || 
        function(a) {
            window.setTimeout(a, 1e3 / 60, (new Date).getTime())
        }
    }();
    var Elements;
    (function(Elements) {
        function Gift(imgData,offsetX,offsetY,fps,speed) {
            this.imgData = imgData,
            this.offsetY = offsetY || 0;
            this.offsetX = offsetX || 0;
            this.speed = speed;
            this.walkFps = fps;
            this.frameInc = 0;
            this.walk();
        }
        Gift.prototype.walk = function(){
            this.status = 'walk';
            this.width = this.imgData.walk.spriteWidth;
            this.height = this.imgData.walk.spriteHeight;
        };
        Gift.prototype.drag = function(){
            this.status = 'drag';
            this.width = this.imgData.drag.spriteWidth;
            this.height = this.imgData.drag.spriteHeight;
        };
        Gift.prototype.stay = function(){
            this.status = 'stay';
            this.width = this.imgData.stay.spriteWidth;
            this.height = this.imgData.stay.spriteHeight;
        };
        Gift.prototype.render = function(ctx){
            var img;
            var statusData = this.imgData[this.status].file;
            switch(this.status){
                case 'walk':
                    this.frameInc += this.walkFps*GameArg.delta;
                    var frameInc = Math.floor(this.frameInc);
                    img = statusData[frameInc % statusData.length];
                    this.offsetX += this.speed*GameArg.delta;
                    break;
                case 'drag':
                case 'stay':
                    img = statusData;
                    break;
            }
            ctx.drawImage(img,GameArg.canvas.width - this.offsetX,this.offsetY,this.width,this.height);
        };
        Elements.Gift = Gift;
    })(Elements || (Elements = {}));
    (function(Elements) {
        function Man(imgData,offsetX,offsetY,speed) {
            this.imgData = imgData,
            this.offsetY = offsetY || 0;
            this.offsetX = offsetX || 0;
            this.speed = speed;
            this.width = this.imgData.man.spriteWidth;
            this.height = this.imgData.man.spriteHeight;
        }
        Man.prototype.render = function(ctx){
            var img = this.imgData.man.file;
            this.offsetX += this.speed*GameArg.delta;
            ctx.drawImage(img,GameArg.canvas.width - this.offsetX,this.offsetY,this.width,this.height);
        };
        Elements.Man = Man;
    })(Elements || (Elements = {}));
    
    function initTapEvent(){
        GameArg.canvas.addEventListener("touchstart", function(event) {
            event.preventDefault();
            event.stopPropagation();
            if(_gameOver || GameArg.dragGirl){
                return;
            }
            var pageX = GameArg.canvas.width - (event.changedTouches[0].pageX - GameArg.canvasX)*GameArg.ratio;
            var pageY = (event.changedTouches[0].pageY - GameArg.canvasY)*GameArg.ratio;
            for (var f = GameArg.walkList.length-1;f >= 0; f--){
                var girl = GameArg.walkList[f];
                if(pageX < girl.offsetX && pageX > girl.offsetX - girl.width && pageY > girl.offsetY && pageY < girl.offsetY + girl.height){
                    GameArg.walkList.splice(f,1);
                    GameArg.dragGirl = girl;
                    GameArg.dragGirl.lastPageX = pageX;
                    GameArg.dragGirl.lastPageY = pageY;
                    girl.drag();
                    return;
                }
            } 
        },false);
        GameArg.canvas.addEventListener("touchmove",function(a) {
            event.preventDefault();
            event.stopPropagation();
            if(_gameOver || !GameArg.dragGirl){
                return;
            }
            var pageX = GameArg.canvas.width - (event.changedTouches[0].pageX - GameArg.canvasX)*GameArg.ratio;
            var pageY = (event.changedTouches[0].pageY - GameArg.canvasY)*GameArg.ratio;
            var x = GameArg.dragGirl.offsetX + (pageX - GameArg.dragGirl.lastPageX);
            var y = GameArg.dragGirl.offsetY + (pageY - GameArg.dragGirl.lastPageY);
            if(x > 0 && x < GameArg.canvas.width){
                GameArg.dragGirl.offsetX = x;
            }
            if(y > 0 && y + GameArg.dragGirl.height < GameArg.canvas.height){
                GameArg.dragGirl.offsetY = y;
            }
            GameArg.dragGirl.lastPageX = pageX;
            GameArg.dragGirl.lastPageY = pageY;
        },false);
        GameArg.canvas.addEventListener("touchend", function(a) {
            event.preventDefault();
            event.stopPropagation();
            if(_gameOver || !GameArg.dragGirl){
                return;
            }
            if(GameArg.dragGirl.offsetY + GameArg.dragGirl.height < GameArg.successY){
                GameArg.dragGirl.walk();
                addGirl(GameArg.walkList,GameArg.dragGirl);
            }else{
                if(GameArg.dragGirl.offsetY < GameArg.canvas.height - GameArg.stayCanvas.height){
                    GameArg.dragGirl.offsetY = GameArg.canvas.height - GameArg.stayCanvas.height;
                }
                GameArg.dragGirl.stay();
                GameArg.scoreList.push(new Elements.Score(GameArg.dragGirl.offsetX - GameArg.dragGirl.width/2 + GameArg.scoreCanvas.width/2,GameArg.dragGirl.offsetY - GameArg.scoreCanvas.height));
                GameArg.dragGirl.offsetY -= (GameArg.canvas.height - GameArg.stayCanvas.height);
                GameArg.dragGirl.render(GameArg.stayCtx);
                //hg.sound.play(1);
				//总分每次递增的值
                hg.grade(0);
            }
            GameArg.dragGirl = null;
        },false);
    }
    function parseRatio(rem){
        return parseRemToPx(rem)*GameArg.ratio;
    }
    function resizeCanvas() {
        GameArg.canvas = document.getElementById("canvas"),
        GameArg.ctx = GameArg.canvas.getContext("2d");
        var $gameLayerBox = $("#gameLayerBox");
        GameArg.canvasX = $gameLayerBox.offset().left;
        GameArg.canvasY = $gameLayerBox.offset().top;
        var backingStore = GameArg.ctx.backingStorePixelRatio ||
                        GameArg.ctx.webkitBackingStorePixelRatio ||
                        GameArg.ctx.mozBackingStorePixelRatio ||
                        GameArg.ctx.msBackingStorePixelRatio ||
                        GameArg.ctx.oBackingStorePixelRatio ||
                        GameArg.ctx.backingStorePixelRatio || 1;
        GameArg.ratio = (window.devicePixelRatio || 1) / backingStore;
        //GameArg.ratio = 1;
        var width = $gameLayerBox.innerWidth();
        var height = $gameLayerBox.innerHeight();
        GameArg.canvas.width = width*GameArg.ratio;
        GameArg.canvas.height = height*GameArg.ratio;
        GameArg.canvas.style.width = width + 'px';
        GameArg.canvas.style.height = height + 'px';
        GameArg.faiX = GameArg.canvas.width ;//- parseRatio('3.6rem')*0.8;
        GameArg.maxH = Math.max(parseRatio('2.75rem'),parseRatio('2.75rem'),parseRatio('2.75rem'),parseRatio('5.1rem'));
        GameArg.stayCanvas = $('<canvas width='+GameArg.canvas.width+' height='+($('#stayPlace').height()*GameArg.ratio + parseRatio('2.75rem')/3)+' ></canvas>')[0];
        GameArg.stayCtx = GameArg.stayCanvas.getContext("2d");

        var scoreCanvasW = 2*g_rem*GameArg.ratio;
        var scoreCanvasH = 1*g_rem*GameArg.ratio;
        GameArg.scoreCanvas = $('<canvas width='+scoreCanvasW+' height='+scoreCanvasH+' ></canvas>')[0];
        GameArg.scoreCtx = GameArg.scoreCanvas.getContext("2d");
        GameArg.scoreCtx.font = "bold " + GameArg.ratio*g_rem + "px Arial";
        GameArg.scoreCtx.textAlign = "center";
        GameArg.scoreCtx.baseBaseLine = "middle";
        GameArg.scoreCtx.fillStyle = "#f30";
		// 拖到盆里+10的红色文字
        //GameArg.scoreCtx.fillText("+10",scoreCanvasW/2,scoreCanvasH);

        (function(Elements) {
            function Score(offsetX,offsetY,speed) {
                this.offsetY = offsetY || 0;
                this.offsetX = offsetX || 0;
                this.speed = speed || 1.2*g_rem/1000;
                this.y = 0;
            }
            Score.prototype.width = GameArg.scoreCanvas.width;
            Score.prototype.height = GameArg.scoreCanvas.height;
            Score.prototype.render = function(ctx){
                this.offsetY -= this.speed*GameArg.delta;
                this.y += this.speed*GameArg.delta;
                ctx.drawImage(GameArg.scoreCanvas,GameArg.canvas.width - this.offsetX,this.offsetY,this.width,this.height);
            };
            Elements.Score = Score;
        })(Elements || (Elements = {}));
		
		//游戏图片设置，跑着的，拽住的，盆里的
        GameArg.assetLib = {
            girl:{
                walk :{
                    file : [hg.assets['${ctx}/static/images/heart-h1.png']],
                    spriteWidth: parseRatio('2.7rem'),
                    spriteHeight: parseRatio('2.75rem')
                },
                drag :{
                    file : hg.assets['${ctx}/static/images/heart-h1.png'],
                    spriteWidth: parseRatio('2.7rem'),
                    spriteHeight: parseRatio('2.75rem')
                },
                stay :{
                    file : hg.assets['${ctx}/static/images/icon-dog6.png'],
                    spriteWidth: parseRatio('2.7rem'),
                    spriteHeight: parseRatio('2.75rem')
                }
            },
            man:{
                man :{
                    file : hg.assets['${ctx}/static/images/11.png'],
                    spriteWidth: parseRatio('3.6rem'),
                    spriteHeight: parseRatio('5.1rem')
                }
            }
        };
        GameArg.canvasTop = 4*g_rem*GameArg.ratio;
        GameArg.successY = GameArg.canvas.height - $('#stayPlace').height()*GameArg.ratio;
        GameArg.first = false;
        initTapEvent();
        LF.setTopBarNotLF(GameArg.canvas,GameArg.ratio);
        startGame();
    }
    function startGame(){
        GameArg.createTime = 0.8,
        GameArg.speedRatio = 1,
        _gameOver = false,
        GameArg.walkList = [],
        GameArg.stayList = [],
        GameArg.scoreList = [],
        GameArg.dragGirl = null,
        GameArg.manList = [],
        GameArg.faiGirl = null,
        GameArg._endGame = false,
        GameArg.currentTime = 0;
        GameArg.delta = 0;
        GameArg.ctx.clearRect(0,0,GameArg.canvas.width,GameArg.canvas.height);
        GameArg.stayCtx.clearRect(0,0,GameArg.stayCanvas.width,GameArg.stayCanvas.height);
        addGirl(GameArg.walkList,new Elements.Gift(GameArg.assetLib.girl,parseRatio('2.7rem') + 1*g_rem,GameArg.canvasTop + 4.4*g_rem,0,0));
        updateGame();
        if(!HdGame.isplaySucess){
            $('#hint').show();
			//点击按钮，弹出遮罩层，点击遮罩层开始游戏
        }else{
            var speed = GameArg.canvas.width/4000;
            GameArg.walkList[0].speed = speed;
            GameArg.walkList[0].walkFps = 3*speed/parseRatio('2.7rem');
            gamePlay();
            hg.time.start();
        }
        
    }
    function gamePlay(){
        if(_gameOver){
            return;
        }
        var g = GameArg;
        if((isIPhone4()) && g.walkList.length >= 7){
            setTimeout(arguments.callee,1000);
        }else if(isAndroid() && g.walkList.length >= 12){
            setTimeout(arguments.callee,1000);
        }else if(g.walkList.length >= 14){
            setTimeout(arguments.callee,1000);
        }else{
            var create_time = GetRandom(g.createTime/2,g.createTime) * 1000;
            var offsetY = GetRandomNum(g.canvasTop+g.maxH-parseRatio('2.75rem'),g.successY-parseRatio('2.75rem'));
            var speed = GetRandom(g.canvas.width/2800,g.canvas.width/4000);
            speed = speed*1.5;
            addGirl(g.walkList,new Elements.Gift(g.assetLib.girl,0,offsetY,3*speed/parseRatio('2.7rem'),speed));
            g.gamePlayTimer = setTimeout(arguments.callee,create_time);
        }   
    }
    function updateGame(){
        if(GameArg._endGame){
            return;
        }
        var currentTime = (new Date).getTime();
        GameArg.delta = currentTime - GameArg.currentTime;
        GameArg.currentTime = currentTime;
        GameArg.delta > 500 && (GameArg.delta = 0);
        GameArg.ctx.clearRect(0,0,GameArg.canvas.width,GameArg.canvas.height);
        for(var i = 0;i < GameArg.walkList.length;i++){
            if(GameArg.walkList[i].offsetX > GameArg.faiX){
                addGirl(GameArg.manList,new Elements.Man(GameArg.assetLib.man,GameArg.canvas.width,GameArg.walkList[i].offsetY - (parseRatio('5.1rem') - parseRatio('2.75rem')),GameArg.canvas.width/2000));
                GameArg.walkList.splice(i,1);
                if(!GameArg.walkList[i]){
                    continue;
                }
            }
            GameArg.walkList[i].render(GameArg.ctx);
        }
        GameArg.ctx.drawImage(GameArg.stayCanvas,0,GameArg.canvas.height - GameArg.stayCanvas.height,GameArg.stayCanvas.width,GameArg.stayCanvas.height);
        for(var i = 0;i < GameArg.scoreList.length;i++){
            if(GameArg.scoreList[i].y > 1.2*g_rem){
                GameArg.scoreList.splice(i,1);
                if(!GameArg.scoreList[i]){
                    continue;
                }
            }
            GameArg.scoreList[i].render(GameArg.ctx);
        }
        GameArg.dragGirl && (GameArg.dragGirl.render(GameArg.ctx));
        for(var i = 0;i < GameArg.manList.length;i++){
            if(GameArg.manList[i].offsetX > GameArg.canvas.width + GameArg.manList[i].width){
                GameArg.manList.splice(i,1);
                if(!GameArg.manList[i]){
                    continue;
                }
            }
            GameArg.manList[i].render(GameArg.ctx);
        }
        hg.time.updateInFrame(GameArg.delta);
        LF.showTopBar();
        requestAnimFrame(updateGame);
    }
    function addGirl(list,girl){
        var length = list.length;
        if(length === 0){
            list.push(girl);
            return;
        }
        for(var i = 0;i < length;i++){
            if(girl.offsetY <= list[i].offsetY){
                list.splice(i,0,girl);
                return;
            }
        }
        list.push(girl);
    }
    function isIPhone6(){
        return window.navigator.userAgent.indexOf('iPhone') !== -1&&(window.screen.availWidth === 375 || window.screen.availWidth === 414);
    }
    function isIPhone4(){
        return window.navigator.userAgent.indexOf('iPhone') !== -1&&(window.screen.availHeight === 460);
    }
    function isAndroid(){
        return window.navigator.userAgent.indexOf('Android') !== -1;
    }
    function GetRandom(a,b){
        return a+Math.random()*(b-a);
    }
    function GetRandomNum(a,b){
        return a+Math.round(Math.random()*(b-a));
    }
    
    $(function(){ 
        gameInit();
    
		//点击遮罩层开始执行
		$('#hint').on('touchstart',function(){
			$(this).hide();
			var speed = GameArg.canvas.width/4000;
			GameArg.walkList[0].speed = speed;
			GameArg.walkList[0].walkFps = 3*speed/parseRatio('2.7rem');
			gamePlay();
			hg.time.start();
			//alert(9999)
			if(typeof hg.sound.cache[0] !== 'undefined' && typeof hg.sound.cache[0].playing !== 'undefined' && !hg.sound.cache[0].playing && g_config.style != 48){
                hg.sound.readyPlay(0,0,'loop');
            }
		});
		//点击开始按钮执行
		hg.on('startGame',function(){
			if(GameArg.first){
				resizeCanvas();
			}else{
				startGame();
			}
			//alert(1111111)
		});
		hg.time.setAcceList(4);
		hg.time.updateFlag = false;
		hg.time.on('acce',function(index){
			GameArg.createTime = GameArg.createTime*0.8;
			GameArg.speedRatio = 1 + 0.5*index;
		}).on('end',function(){
			_gameOver = true;
			GameArg._endGame = true;
			setTimeout(function(){
				gameOver(hg.grade.val);
			},1000);
		});
		$('#gameLayerBox').css('top',0);

        $('.gameBox').on('touchmove',function(event){
            event.preventDefault();
            event.stopPropagation();
        });
    });

    function gameInit() {
        hg.time.init();
        $("#grade").hide();
        //hg.grade.set(0);
        $('.timeUpImg').hide();
    }
    function home(){
        $('#ruleImg').show();
        $('.homeBtnBox').show();
        $('.footerBox').show();
        $('.gameBox').hide();
        startBtnDelay();
        $('.home').show();
        $('#poupInfoBox').hide();
        $('.resuleBox').hide();
        gameInit();
        hg.fire('home');
    }   
    function gameRestart() {
        $('.gameBox').show();
        gameInit();
        if(!_manage){
            startGame();
        }
        hg.fire('again');
    }
    function showGamePage(){
        $('#ruleImg').hide();
        $('.homeBtnBox').hide();
        $('.footerBox').hide(); 
        $('.home').hide();
        $('#poupInfoBox').hide();
        $('.resuleBox').hide();
        $('.gameBox').show();
    }
</script>

<script type="text/javascript">
    var count = 0;
    var drawTimesLimit = 3;
    var drawTotalLimit = 6;
    var totalCount = 0;
    var isLimitDraw = false;
    var otherPlayerInfo = {};
    var showAwardList = false;
    var informNum = 1;
    var playerAwardList = [];
    var skillSupportType = 2;
    var skillName = HdGame.decodeHtml('凡科互动');
    var skillLink = HdGame.decodeUrl(HdGame.decodeHtml('http%3A%2F%2Fmp.weixin.qq.com%2Fs%3F__biz%3DMjM5MTk5MjI3OA%3D%3D%26mid%3D209854000%26idx%3D1%26sn%3D82241d924839270d3ea820ad2d56c01b%23wechat_redirect'));
	var gameType = 0;
    var rankShowNum = 100;
	PlayInfo.initData(0,0,5,10,false);

    //改版前的联系兑奖提示语
    HdGame._awardLinkMsg = '获奖者需填写联系电话方可查看兑奖码兑奖';

    //初始化弹出层参数
	HdGame.initChangePoup({
		rankUrl        : '',
		awardUrl       : '',
		getRegAwardUrl : '',
		openId         : 'o1ueSjlWakjL2IjvzYWKQin4aRg8',
		gameId         : 164,
		mhaveScore     : (true && gameType != 3),
		informUrl      : '',
		hasReport      : false,
		afterWxCard    : true
	});
    HdGame.resulePoup.init({
        drawType  : 1,
        checkRegUrl:'',  
        regUrl    : '',
        home      : typeof home !== 'undefined' ? home : null,
        again     : typeof gameRestart !== 'undefined' ? gameRestart : null,
        giftInit  : _manage? luckDraw : (false ? luckDrawZhuli : luckDraw),
    });
   HdGame.initJsFoot({"rulesortstr":"abcde","drawType":1,"hostName":"fkhdjr","hostLink":"http://","menuLink":"","fromFav":"","openAwardExp":true,"isHideFxts":true,"supportUrl":"http://mp.weixin.qq.com/s?__biz=MjM5MTk5MjI3OA==&mid=209854000&idx=1&sn=82241d924839270d3ea820ad2d56c01b#rd","skillSupport":"凡科互动","showJoinNum":true,"showRedDot":false,"showMDRedDot":false,"showAwardBtn":false,"isSelAwardLine":false,"showSkillSup":true,"menuStyle":3,"soundIcon_l":"0.75rem","soundIcon_t":"1rem","awardImage":"http://hdg.faisys.com/image/gift.png?v=201504091029","titleImg_path_def":"http://hdg.faisys.com/image/bhmn/title.png?v=201509051300","startImg_path_def":"http://hdg.faisys.com/image/bhmn/startBtn.png?v=201509051300","awardIconImgPath_def":"http://hdg.faisys.com/image/ruleImg_red.png","titleImg_w_def":"12rem","titleImg_h_def":"8rem","startBtn_w_def":"7.5rem","startBtn_h_def":"2rem","logoImg_w_def":"2rem","logoImg_h_def":"2rem","awardImg_w_def":"6rem","awardImg_h_def":"6rem","homeBgPath_def":"http://hdg.faisys.com/image/bhmn/homeBg.jpg?v=201509051300","gameBg2Path_def":"","gameBgPath_def":"http://hdg.faisys.com/image/bhmn/gameBg.png?v=201509081300","headImg2":"","headImg3":"","headImg4":"","dangerType":1})

    //标题与开始按钮动画
    function startBtnDelay(){
        //$('.titleImg').removeClass('titleDown').addClass('titleDown');
        $('#startBtnImg').removeClass('startTada');
        
        hg.sound.pauseAll();
        
        setTimeout(function(){
            $('#startBtnImg').addClass('startTada');
        },1000); 
        
    }
    //抽奖结果页或收藏卡劵的显示兑奖详细页
    function showAwardDetail4Draw(fromFav){
        home('showAward',fromFav);
        HdGame.changePoup(3);
    }
    function setAwardImgHeight(){
        $('#resule-gift-foot').css("margin-top",($('#resule-gift-sucImg').height()+35)/g_rem + 'rem');
    }
	function showRank(){
		HdGame.changePoup(1);
        $('#awardDetailBox').hide();
	}
	function showRule(){
        HdGame.changePoup(2);
        $('#awardDetailBox').hide();
	}
    function activateSound(){ //兼容ios下 WebAudio类型的对象无法自动播放，必须在点击事件中播放过一次，才允许播放
        try{
            if(HdGame.isIPhone() && hg.sound.list && hg.sound.list.length > 0 && !hg.sound._activate){ 
                $.each(hg.sound.list, function(i, val) {
                    var data = hg.sound.cache[i];
                    if(i > 0 && data && data.soundType == "LWebAudio"){
                        data.play();
                        data.stop();
                    }
                });
                hg.sound._activate = true;
            }
        }catch(e){
            HdGame.logStd("activateSoundErr",e);
        }
    }
	function startBtnAjax(event,data,pass){
		//alert(222);
        var that = this;
        if(hg.showGameBox){
            //$('.homeBtnBox,.bottomSkill').hide();
            $('.footerBox').hide(); 
            $('.home,#ruleImg').hide();
            $('.gameBox').show();
            //if(typeof hg.sound.cache[0] !== 'undefined' && typeof hg.sound.cache[0].playing !== 'undefined' && //!hg.sound.cache[0].playing && g_config.style != 48){
            //    hg.sound.readyPlay(0,0,'loop');
            //}
        }
        HdGame.logDog(1000002, 21);
        HdGame.logObjDog(1000092, 1, 164);
        
            HdGame.addJoinGameBehavior();
        
        hg.fireWith('startGame',that,[false,event,data]);
        
        return true;
    }
		
    function gameOver(_gameScore,callBack,option,showAjaxBar){
		// 游戏结束
    	$.ajax({
	    	url : '${ctx }/dog-food/produce',
	       	type : 'post',
	       	data : {},
	       	success : function(data){
	       		var dataJson = $.parseJSON(data);
	       		var status = dataJson.status;
	       		if(status == 'Y'){
	       			var num = dataJson.num;
	       			$("#glContent").html("恭喜您！<br/>获得" + num + "粒狗粮！<br/>");
	       			$(".ggtc").show();
	       		}else{
	       			alert(dataJson.msg);
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

var drawStatusLuckDraw = true;
function luckDraw(fn,option,pass){
    
	if(hg.fireWith('beforeDraw',this,[arguments]) === false){
		return false;
	}
	
    if(!drawStatusLuckDraw){
        return;
    }

    drawStatusLuckDraw=false;
    $('.ajaxLoadBg').show();
    $('.ajaxLoadBar').addClass('ajaxLoad'); 

    var params = {};
    params.gameId = 164;
    params.style = 14;
    
    params.userName = g_config.userName;
    params.isPub = $.trim('1');
    params.headImg = g_config.headImg;
    $.extend(params,option);
      

}
$(function(){
	var headimgurl = "${headimgurl}";
	var openid = "${openid}";
	$(".userImg").attr("src", headimgurl);
    hg.fire('jsFootEnd');
	//showGamePage();
	$("#startBtnImg").trigger("touchend")
	//$(".ggtc").show();
	//home();
})
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