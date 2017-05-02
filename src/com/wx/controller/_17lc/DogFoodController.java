package com.wx.controller._17lc;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.wx.common.constant.ActionTypeEnum;
import com.wx.common.constant.ActivityTypeEnum;
import com.wx.common.constant.Constant;
import com.wx.model._17lc.DogfoodAssistance;
import com.wx.model._17lc.DogfoodQrcode;
import com.wx.model._17lc.DogfoodWithdraw;
import com.wx.model.statistics.WechatActionStatistics;
import com.wx.model.statistics.WechatShareStatistics;
import com.wx.service._17lc.DogFoodService;
import com.wx.service.statistics.WechatStatisticsService;
import com.wx.util.DogfoodUtil;
import com.wx.util.Encrypt;
import com.wx.util.WeixinUtil;

/**
 * 刷狗粮活动控制类
 * @author meiiy
 * @version 2017年3月24日
 */
@Controller
@RequestMapping(value="/dog-food")
public class DogFoodController
{
    private static Logger logger = Logger.getLogger(DogFoodController.class);
    
    @Autowired
    private WechatStatisticsService wechatStatisticsService;
    
    @Autowired
    private DogFoodService dogFoodService;
    
    /**
     * 活动主页
     * @param request
     * @param model
     * @return
     * @author meiiy
     * @version 2017年3月24日
     */
    @RequestMapping(value="/index")
    public String index(HttpServletRequest request,Model model)
    {
        String openid = (String)request.getSession().getAttribute("openid");
        //已获得的狗粮总数
        int totalDogFood = dogFoodService.getTotalDogFood(openid);
        //已兑换的狗粮总数
        //int convertedDogFood = dogFoodService.convertedDogFood(openid);
        //已兑换金额
        BigDecimal convertedAmount= dogFoodService.convertedAmount(openid);
        //已提现金额
        BigDecimal cashedAmount = dogFoodService.cashedAmount(openid);
        //助力好友列表
        List<DogfoodAssistance> assistanceList = dogFoodService.getAssistanceList(openid);
        //拥有刷狗粮次数
        int hasNum = dogFoodService.hasNum(openid);
        //用户是否还能刷狗粮
        boolean canProducing = dogFoodService.canProducing(openid);
        //活动是否已结束
        boolean isEnd = DogfoodUtil.isEnd(new Date());
        //获取用户昵称
        //微信昵称
        String nickname = "";
        JSONObject result = WeixinUtil.getUserInfo(openid);
        if (result != null) {
            String subscribe = result.getString("subscribe");
            if (WeixinUtil.SUBSCRIBE.equals(subscribe)) {
                nickname = result.getString("nickname");
            }
        }
        if ("oLEdvwa8RVf6nc1bF7vycy6lSPpY".equals(openid)) {//活动需要，该openid造一些假数据
            model.addAttribute("totalDogFood", totalDogFood+120);
            model.addAttribute("cashedAmount", cashedAmount.add(BigDecimal.valueOf(6)));
            model.addAttribute("assistanceList", assistanceList);
            model.addAttribute("hasNum", hasNum + 5);
            model.addAttribute("isEnd", isEnd);
            model.addAttribute("nickname", nickname);
            model.addAttribute("convertedAmount", convertedAmount.add(BigDecimal.valueOf(6)));
        } else {
            model.addAttribute("totalDogFood", totalDogFood);
            //model.addAttribute("convertedDogFood", convertedDogFood);
            model.addAttribute("cashedAmount", cashedAmount);
            model.addAttribute("assistanceList", assistanceList);
            model.addAttribute("hasNum", (!isEnd && canProducing) ? hasNum : 0);//不能再参与活动是显示为0
            model.addAttribute("isEnd", isEnd);
            model.addAttribute("nickname", nickname);
            model.addAttribute("convertedAmount", convertedAmount);
        }
        
        //以下为页面使用微信JS-SDK而生成必须参数值
        model.addAttribute("appid", Constant.WX_APPID);
        String jsapi_ticket = WeixinUtil.getJsapiTicket();
        String nonceStr = WeixinUtil.create_nonce_str();
        String timestamp = WeixinUtil.create_timestamp();
        String url = Constant.WX_HOST +"/dog-food/index";
        String signature = WeixinUtil.getSign(jsapi_ticket, nonceStr, timestamp, url);
        model.addAttribute("nonceStr", nonceStr);
        model.addAttribute("timestamp", timestamp);
        model.addAttribute("signature", signature);
        
        return "lc/dogfood/index";
    }
    
    /**
     * 判断是否能刷狗粮
     * @return
     * @author meiiy
     * @version 2017年3月27日
     */
    @RequestMapping(value="/canProduce")
    @ResponseBody
    public String canProduce(HttpServletRequest request,Model model)
    {
        String openid = (String)request.getSession().getAttribute("openid");
        Map<String,String> map = Maps.newHashMap();
        
        if ("oLEdvwa8RVf6nc1bF7vycy6lSPpY".equals(openid)) {//活动需要，该openid不做任何限制
            map.put("status", "Y");
            return JSONObject.fromObject(map).toString();
        }
        
        try {
            //第一次参与活动可以不关注，后面都需要关注公众号
            //用户是否为第一次刷狗粮
            boolean isFirst = dogFoodService.isFirst(openid);
            if (!isFirst) {
                //用户是否已关注公众号
                JSONObject result = WeixinUtil.getUserInfo(openid);
                if (result != null) {
                    String subscribe = result.getString("subscribe");
                    if (!WeixinUtil.SUBSCRIBE.equals(subscribe)) {
                        map.put("status", "0");
                        return JSONObject.fromObject(map).toString();
                    }
                }
            }
            //判断活动是否已结束
            if (DogfoodUtil.isEnd(new Date())) {
                map.put("status", "1");
                return JSONObject.fromObject(map).toString();
            }
            //是否还能参与活动,最多3天
            if (!dogFoodService.canProducing(openid)) {
                map.put("status", "2");
                return JSONObject.fromObject(map).toString();
            }
            //当天是否还能参与活动,一个用户一天只能刷7次
            if (dogFoodService.countNumToday(openid) > 6) {
                map.put("status", "3");
                return JSONObject.fromObject(map).toString();
            }
            //拥有刷狗粮次数
            if (dogFoodService.hasNum(openid) <= 0) {
                map.put("status", "4");
                return JSONObject.fromObject(map).toString();
            }
            
            map.put("status", "Y");
        } catch (Exception e) {
            logger.error(">>>>>>>>>>判断用户是否能刷狗粮出错",e);
            map.put("status", "-1");
        }
        
        return JSONObject.fromObject(map).toString();
    }
    
    /**
     * 兑换狗粮
     * @return 无可兑换狗粮：status=NONE,兑换成功：status=Y,redAmount=兑换金额，错误：status=N,msg=错误信息
     * @author meiiy
     * @version 2017年3月25日
     */
    @RequestMapping(value="/conversion")
    @ResponseBody
    public String conversion(HttpServletRequest request)
    {
        String openid = (String)request.getSession().getAttribute("openid");
        Map<String,String> map = Maps.newHashMap();
        
        if (DogfoodUtil.isEnd(new Date())) {
            map.put("status", "N");
            map.put("msg", "该活动已结束");
            JSONObject json = JSONObject.fromObject(map);
            return json.toString();
        }
        
        try {
            BigDecimal redAmount = dogFoodService.converteDogFood(openid);
            if (redAmount.compareTo(BigDecimal.ZERO) == 0) {
                map.put("status", "NONE");
            } else {
                map.put("status", "Y");
                map.put("redAmount", redAmount.toString());
            }
        } catch (Exception e) {
            logger.error(">>>>>>>>>>>>兑换狗粮出错openid="+openid,e);
            map.put("status", "N");
            map.put("msg", "兑换狗粮出错，请联系客服");
        }
        
        JSONObject json = JSONObject.fromObject(map);
        return json.toString();
    }
    
    /**
     * 查询未提现金额
     * @return 无可提现金额：status=NONE;有可以提现的金额：status=Y,noCashAmount=待提现金额，
     * 当天已提过现：status=NO,
     * 程序错误：status=N,msg=错误信息
     * @author meiiy
     * @version 2017年3月25日
     */
    @RequestMapping(value="/noCashAmount")
    @ResponseBody
    public String noCashAmount(HttpServletRequest request)
    {
        String openid = (String)request.getSession().getAttribute("openid");
        Map<String,String> map = Maps.newHashMap();
        
        if (DogfoodUtil.isEnd(new Date())) {
            map.put("status", "N");
            map.put("msg", "该活动已结束");
            JSONObject json = JSONObject.fromObject(map);
            return json.toString();
        }
        
        try {
            //获取未提现金额
            BigDecimal noCashAmount = dogFoodService.noCashAmount(openid);
            //获取今天提现次数
            int withdrawNum = dogFoodService.countWithdrawNum(openid);
            
            if (noCashAmount.compareTo(BigDecimal.ZERO) == 0) {
                map.put("status", "NONE");
            } else {
                if (withdrawNum > 0) {//一天只能提现一次
                    map.put("status", "NO");
                } else {
                    map.put("status", "Y");
                }
                map.put("noCashAmount", noCashAmount.toString());
            }
        } catch (Exception e) {
            logger.error(">>>>>>>>>>>>查询待提现金额出错openid="+openid,e);
            map.put("status", "N");
            map.put("msg", "提现出错，请联系客服");
        }
        
        JSONObject json = JSONObject.fromObject(map);
        return json.toString();
    }
    
    /**
     * 提现
     * @return 提现成功：status=Y,amount=体验金金额；提现失败：status=N,msg=错误信息
     * @author meiiy
     * @version 2017年3月25日
     */
    @RequestMapping(value="/cash")
    @ResponseBody
    public String cash(HttpServletRequest request)
    {
        String openid = (String)request.getSession().getAttribute("openid");
        Map<String,String> map = Maps.newHashMap();
        map.put("status", "N");
        
        if (DogfoodUtil.isEnd(new Date())) {
            map.put("msg", "该活动已结束");
            JSONObject json = JSONObject.fromObject(map);
            return json.toString();
        }
        
        String mobile = request.getParameter("mobile");
        if (StringUtils.isBlank(mobile)) {
            map.put("msg", "请输入提现手机号");
            JSONObject json = JSONObject.fromObject(map);
            return json.toString();
        }
        
        try {
            String msg = dogFoodService.cash(openid,mobile,map);
            
            if (StringUtils.isNotEmpty(msg)) {
                map.put("msg", msg);
            } else {
                map.put("status", "Y");
            }
        } catch (Exception e) {
            logger.error(">>>>>>>>>>>>提现出错openid="+openid,e);
            map.put("msg", "提现出错，请联系客服");
        }
        
        JSONObject json = JSONObject.fromObject(map);
        return json.toString();
    }
    
    /**
     * 获取刷狗粮后获得的狗粮数
     * @return 成功：status=Y，num=狗粮数 ，失败：status=N,msg=错误信息
     * @author meeiy
     * @version 2017年3月26日
     */
    @RequestMapping(value="/produce")
    @ResponseBody
    public String produce(HttpServletRequest request)
    {
        String openid = (String)request.getSession().getAttribute("openid");
        Map<String,String> map = Maps.newHashMap();
        
        if (!"oLEdvwa8RVf6nc1bF7vycy6lSPpY".equals(openid)) {//活动需要，该openid不做任何限制
            //活动是否已结束
            if (DogfoodUtil.isEnd(new Date())) {
                map.put("status", "N");
                map.put("msg", "该活动已结束！");
                JSONObject json = JSONObject.fromObject(map);
                return json.toString();
            }
            //是否还能参与活动
            if (!dogFoodService.canProducing(openid)) {
                map.put("status", "N");
                map.put("msg", "该活动只能连续参与3天，您已达到活动参与时长！");
                JSONObject json = JSONObject.fromObject(map);
                return json.toString();
            }
            //当天是否还能参与活动,一个用户一天只能刷7次
            if (dogFoodService.countNumToday(openid) > 6) {
                map.put("status", "N");
                map.put("msg", "您已经达到今日游戏次数上限，记得明日继续来刷哦！");
                return JSONObject.fromObject(map).toString();
            }
            //拥有刷狗粮次数
            if (dogFoodService.hasNum(openid) <= 0) {
                map.put("status", "N");
                map.put("msg", "抱歉哦~您的游戏次数已用光！");
                return JSONObject.fromObject(map).toString();
            }
        }
        
        try {
            Integer num = dogFoodService.produce(openid);
            map.put("status", "Y");
            map.put("num", num.toString());
        } catch (Exception e) {
            logger.error(">>>>>>>>>>>>生成狗粮数据出错openid="+openid,e);
            map.put("status", "N");
            map.put("msg", "获取狗粮出错，请联系客服");
        }
        
        JSONObject json = JSONObject.fromObject(map);
        return json.toString();
    }
    
    /**
     * 分享成功后保存分享记录
     * @author meiiy
     * @version 2017年3月24日
     */
    @RequestMapping(value="/share")
    public void share(HttpSession httpSession)
    {
        String openid = (String)httpSession.getAttribute("openid");
        logger.info(">>>>>>>>>>>>用户分享成功openid="+openid);
        try {
            WechatShareStatistics shareStatistics = 
                    new WechatShareStatistics(openid,ActivityTypeEnum.DOGFOOD,new Date());
            wechatStatisticsService.insertShare(shareStatistics);
        } catch (Exception e) {
            logger.error(">>>>>>>>>>>>保存用户分享信息出错openid="+openid,e);
        }
    }
    
    /**
     * 进入分享页保存数据
     * @author meiiy
     * @version 2017年3月24日
     */
    @RequestMapping(value="/action")
    public void action(HttpSession httpSession)
    {
        String openid = (String)httpSession.getAttribute("openid");
        logger.info(">>>>>>>>>>>>用户进入分享页openid="+openid);
        
        try {
            WechatActionStatistics actionStatistics = 
                    new WechatActionStatistics(openid,ActivityTypeEnum.DOGFOOD,ActionTypeEnum.DOGFOOD_INTO_SHAREPAGE,new Date());
            wechatStatisticsService.insertAction(actionStatistics);
        } catch (Exception e) {
            logger.error(">>>>>>>>>>>>保存用户响应信息出错openid="+openid,e);
        }
    }
    
    /**
     * 点击分享页“我也要参与”按钮保存数据
     * @author meiiy
     * @version 2017年3月24日
     */
    @RequestMapping(value="/clickBtn")
    public void clickBtn(HttpSession httpSession)
    {
        String openid = (String)httpSession.getAttribute("openid");
        logger.info(">>>>>>>>>>>>用户点击分享页按钮openid="+openid);
        
        try {
            WechatActionStatistics actionStatistics = 
                    new WechatActionStatistics(openid,ActivityTypeEnum.DOGFOOD,ActionTypeEnum.DOGFOOD_CLICK_SHAREPAGE_BTN,new Date());
            wechatStatisticsService.insertAction(actionStatistics);
        } catch (Exception e) {
            logger.error(">>>>>>>>>>>>保存用户点击分享页按钮出错openid="+openid,e);
        }
    }
    
    /**
     * 进入到邀请助力页面
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value="/invite")
    public String invite(HttpServletRequest request, Model model){
        String inviterOpenid = request.getParameter("inviterOpenid");
        String openid = (String)request.getSession().getAttribute("openid");
        model.addAttribute("inviterOpenid", inviterOpenid);
        model.addAttribute("openid", openid);
        return "lc/dogfood/invite";
    }
     
     /**
      * 判断用户是否关注,关注则保存助力记录，未关注则返回用于生成带有邀请者参数的二维码ticket
     * @param httpSession
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/assistance")
    public Map<String, Object> assistance(HttpServletRequest request){
        Map<String, Object> data = new HashMap<>();
        //活动是否已结束
        if (DogfoodUtil.isEnd(new Date())) {
            data.put("status", -1);
            return data;
        }
        
        int status = 0;
        String inviterOpenid = request.getParameter("inviterOpenid");
        String openid = (String)request.getSession().getAttribute("openid");
        JSONObject jsonObject = WeixinUtil.getUserInfo(openid);
        // 0:未关注  1:已关注
        int isSubscribe = jsonObject.getInt("subscribe");
        if(isSubscribe == 0){
            String ticket = "";
            // 根据邀请人的OpenID判断是否已保存用于生成二维码的ticket
            DogfoodQrcode dogfoodQrcode = dogFoodService.getQrcodeByOpenid(inviterOpenid);
            if(dogfoodQrcode == null){
                // 返回用于生成带有邀请者参数的二维码ticket
                ticket = WeixinUtil.getQrcodeTicket(inviterOpenid);
                dogfoodQrcode = new DogfoodQrcode();
                dogfoodQrcode.setOpenid(inviterOpenid);
                dogfoodQrcode.setTicket(ticket);
                dogFoodService.saveQrcode(dogfoodQrcode);
            }else{
                ticket = dogfoodQrcode.getTicket();
            }
            status = 0;
            data.put("ticket", ticket);
        }else{
            // 根据OpenID判断是否已经助力过,保存第一次分享人永久二维码的ticket
            Long count = dogFoodService.getAssistanceNumByOpenid(openid);
            if(count > 0){
                // 已经助力
                status = 1;
            }else{
                // 已关注，保存助力记录
                DogfoodAssistance dogfoodAssistance = new DogfoodAssistance();
                dogfoodAssistance.setInviterOpenid(inviterOpenid);
                dogfoodAssistance.setAssistorOpenid(openid);
                //dogfoodAssistance.setAssistorNickname(jsonObject.getString("nickname"));
                dogfoodAssistance.setAssistorHeadimg(jsonObject.getString("headimgurl"));
                dogFoodService.saveAssistance(dogfoodAssistance);
                status = 2;
            }
        }
        data.put("status", status);
        return data;
     }
    
    @RequestMapping(value="/game")
    public String game(HttpServletRequest request, Model model){
    	 String openid = (String)request.getSession().getAttribute("openid");
    	 JSONObject jsonObject = WeixinUtil.getUserInfo(openid);
         // 0:未关注  1:已关注
         int isSubscribe = jsonObject.getInt("subscribe");
         if(isSubscribe == 1){
        	 String headimgurl = jsonObject.getString("headimgurl");
        	 model.addAttribute("headimgurl", headimgurl);
         }
		model.addAttribute("openid", openid);
    	return "lc/dogfood/game";
    } 
    
    /**
     * 未有账号用户提现时生成注册数据
     * @param request
     * @return
     * @author meiiy
     * @version 2017年4月6日
     */
    @RequestMapping(value="/toRegister")
    @ResponseBody
    public String toRegister(HttpServletRequest request)
    {
        String openid = (String)request.getSession().getAttribute("openid");
        Map<String,String> map = Maps.newHashMap();
        
        String mobile = request.getParameter("mobile");
        if (StringUtils.isBlank(mobile)) {
            map.put("status", "N");
            map.put("msg", "请输入正确的手机号");
            JSONObject json = JSONObject.fromObject(map);
            return json.toString();
        }
        
        try {
            //验证手机号
            DogfoodWithdraw dogfoodWithdraw = new DogfoodWithdraw();
            dogfoodWithdraw.setOpenid(openid);
            dogfoodWithdraw.setMobile(mobile);
            String result = dogFoodService.validMobile(dogfoodWithdraw);
            if (result != null) {
                map.put("status", "N");
                map.put("msg", result);
                JSONObject json = JSONObject.fromObject(map);
                return json.toString();
            }
            
            //验证提现次数
            int withdrawNum = dogFoodService.countWithdrawNum(openid);
            if (withdrawNum > 0) {
                map.put("status", "N");
                map.put("msg", "您今天已经提现啦，剩余金额可明天继续提现哦");
                JSONObject json = JSONObject.fromObject(map);
                return json.toString();
            }
            
            //生成数据
            dogFoodService.createRegisterData(dogfoodWithdraw);
            //提现金额转换为分
            String amontStr = DogfoodUtil.subZeroAndDot(dogfoodWithdraw.getAmount().multiply(BigDecimal.valueOf(100)).toString());
            //生成加密串
            StringBuilder sb = new StringBuilder();
            sb.append(dogfoodWithdraw.getOrderId()).append(amontStr).append(mobile)
                .append(DogfoodUtil.WITHDRAW_SECRET);
            String signature = Encrypt.getMD5(sb.toString());
            
            map.put("status", "Y");
            map.put("orderId", dogfoodWithdraw.getOrderId());
            map.put("amount", amontStr);
            map.put("url", DogfoodUtil.REG_WITHDRAW_URL);
            map.put("signature", signature);
            
            logger.info(">>>>>>>>>>>>>注册生成请求数据openid="+openid+"map="+map);
        } catch (Exception e) {
            logger.error(">>>>>>>>>>>>生成注册数据出错openid="+openid,e);
            map.put("status", "N");
            map.put("msg", "提现出错，请联系客服");
        }
        
        JSONObject json = JSONObject.fromObject(map);
        return json.toString();
    }
    
    /**
     * 注册成功通知
     * @param request
     * @return
     * @author meiiy
     * @version 2017年4月6日
     */
    @RequestMapping(value="/notify")
    @ResponseBody
    public Map<String, String> notify(HttpServletRequest request)
    {
        String orderId = request.getParameter("orderId");
        String mobile = request.getParameter("mobile");
        String signature = request.getParameter("signature");
        logger.info(">>>>>>>>>>>>>注册成功通知请求参数orderId="+orderId+"...mobile="+mobile+"...signature="+signature);
        
        Map<String, String> map = Maps.newHashMap();
        map.put("result", "0");
        
        if (StringUtils.isBlank(orderId) || StringUtils.isBlank(mobile) || StringUtils.isBlank(signature)) {
            map.put("msg", "参数错误");
            return map;
        }
        
        try {
            String msg = dogFoodService.updateForRegister(orderId,mobile,signature);
            if (msg == null) {
                map.put("result", "1");
            } else {
                map.put("msg", msg);
            }
        } catch (Exception e) {
            logger.error(">>>>>>>>>>>>注册成功通知出错",e);
            map.put("msg", "系统出错");
        }
        
        return map;
    }
}
