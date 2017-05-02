package com.wx.service._17lc;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wx.common.constant.Constant;
import com.wx.dao._17lc.DogFoodDao;
import com.wx.model._17lc.DogfoodAssistance;
import com.wx.model._17lc.DogfoodConversion;
import com.wx.model._17lc.DogfoodProducing;
import com.wx.model._17lc.DogfoodQrcode;
import com.wx.model._17lc.DogfoodWithdraw;
import com.wx.util.DogfoodUtil;
import com.wx.util.Encrypt;
import com.wx.util.OrderNumberUtil;
import com.wx.util.WeixinUtil;

/**
 * 刷狗粮活动业务逻辑处理类
 * @author meiiy
 * @version 2017年3月24日
 */
@Service
public class DogFoodService 
{
    private static Logger logger = Logger.getLogger(DogFoodService.class);
    
    @Autowired
    private DogFoodDao dogFoodDao;
    @Autowired
    private DogFoodWithdrowService dogFoodWithdrowService;
    
    /**
     * 兑换狗粮
     * @param openid 用户openid
     * @return 兑换成功返回兑换金额，否则返回0
     * @author meiiy
     * @version 2017年3月25日
     */
    @Transactional
    public BigDecimal converteDogFood(String openid)
    {
        DogfoodProducing dogfoodProducing = new DogfoodProducing();
        dogfoodProducing.setOpenid(openid);
        dogfoodProducing.setIsConvert(Constant.DOGFOOD_NO_CONVERT);//未兑换
        //获取未兑换的狗粮总数
        Integer num = dogFoodDao.sumDodFood(dogfoodProducing);
        if (num == null) {
            return BigDecimal.ZERO;
        }
        
        //修改未兑换状态为已兑换
        dogFoodDao.updateConversionStatus(openid);
        //插入兑换记录
        BigDecimal redAmount = BigDecimal.valueOf(num).divide(BigDecimal.valueOf(20));//每20粒狗粮兑换一元钱
        DogfoodConversion dogfoodConversion = new DogfoodConversion();
        dogfoodConversion.setOpenid(openid);
        dogfoodConversion.setRedAmount(redAmount);
        dogfoodConversion.setIsCash(Constant.DOGFOOD_NO_CASH);
        dogfoodConversion.setCreateTime(new Date());
        dogFoodDao.insertConversionRecord(dogfoodConversion);
        
        return redAmount;
    }
    
    /**
     * 查询未提现的红包金额
     * @param openid 用户openid
     * @return 返回未提现金额
     * @author meiiy
     * @version 2017年3月24日
     */
    public BigDecimal noCashAmount(String openid)
    {
        DogfoodConversion dogfoodConversion = new DogfoodConversion();
        dogfoodConversion.setOpenid(openid);
        dogfoodConversion.setIsCash(Constant.DOGFOOD_NO_CASH);
        BigDecimal amount = dogFoodDao.sumRedAmount(dogfoodConversion);
        
        return amount != null ? amount : BigDecimal.ZERO;
    }
    
    /**
     * 提现
     * @param openid 用户openid
     * @param  mobile 提现手机号码
     * @author meiiy
     * @return 提现成功返回null,失败返回失败原因
     * @version 2017年3月25日
     */
    @Transactional
    public String cash(String openid,String mobile,Map<String,String> map)
    {
        //判断提现手机号
        DogfoodWithdraw dogfoodWithdraw = new DogfoodWithdraw();
        dogfoodWithdraw.setOpenid(openid);
        dogfoodWithdraw.setMobile(mobile);
        
        String rel = this.validMobile(dogfoodWithdraw);
        if (rel != null) {
            return rel;
        }
        //判断可提现金额
        BigDecimal amount = this.noCashAmount(openid);
        if (amount.compareTo(BigDecimal.ZERO) != 1) {
            return "无可提现金额";
        }
        //每天只能提现一次
        int withdrawNum = this.countWithdrawNum(openid);
        if (withdrawNum > 0) {
            return "您今天已经提现啦，剩余"+amount+"元可明天继续提现哦";
        }
        
        //生成订单号
        String orderId = "TX" + OrderNumberUtil.getOrderNumber();
        dogfoodWithdraw.setOrderId(orderId);
        dogfoodWithdraw.setAmount(amount);
        dogfoodWithdraw.setMessage("初始化");
        dogfoodWithdraw.setCreateTime(new Date());
        //保存提现记录
        dogFoodWithdrowService.insertWithdraw(dogfoodWithdraw);
        
        //调用提现接口
        String amontStr = DogfoodUtil.subZeroAndDot(amount.multiply(BigDecimal.valueOf(100)).toString());
        //请求参数
        StringBuilder sb = new StringBuilder();
        sb.append("orderId=").append(orderId).append("&").append("mobile=").append(mobile).append("&").append("amount=").append(amontStr);
        //生成加密串
        StringBuilder sb1 = new StringBuilder();
        sb1.append(orderId).append(mobile).append(amontStr).append(DogfoodUtil.WITHDRAW_SECRET);
        String signature = Encrypt.getMD5(sb1.toString());
        
        sb.append("&").append("signature=").append(signature);
        
        logger.info(">>>>>>>>>提现人openid="+openid+"...发送提现请求="+sb.toString());
        JSONObject jsonObject = WeixinUtil.httpRequest(DogfoodUtil.WITHDRAW_URL, "POST", sb.toString());
        logger.info(">>>>>>>>>>提现返回openid="+openid+"...jsonObject="+jsonObject);
        
        String message = null;
        if (jsonObject != null ) {
            String result = jsonObject.getString("result");
            if ("1".equals(result)) {//提现成功
                //修改未提现数据为已提现
                dogFoodDao.updateCashStatus(openid);
                
                dogfoodWithdraw.setStatus(1);
                dogfoodWithdraw.setMessage("提现成功");
                //转换体验金金额
                map.put("amount", String.valueOf(DogfoodUtil.getExpAmount(amount)));
            } else {
                String reason = jsonObject.getString("reason");
                message = StringUtils.isNotEmpty(reason) ? reason : "提现失败";
                dogfoodWithdraw.setStatus(0);
                dogfoodWithdraw.setMessage(message.length() > 1000 ? message.substring(0, 1000) : message);
            }
        } else {
            logger.info(">>>>>>>>>提现人openid="+openid+"...发送提现请求失败");
            message = "发送提现请求失败";
            dogfoodWithdraw.setStatus(0);
            dogfoodWithdraw.setMessage(message);
        }
        
        //更新提现记录
        dogFoodDao.updateWithdraw(dogfoodWithdraw);
        
        return message;
    }
    
    /**
     * 提现时验证手机号码
     * @author meiiy
     * @return 验证通过返回null,错误返回失败信息
     * @version 2017年4月10日
     */
    public String validMobile(DogfoodWithdraw dogfoodWithdraw) 
    {
        //一个人只能对应一个手机号
        Integer count = dogFoodDao.getWithdrawOfOpenid(dogfoodWithdraw);
        if (count > 0) {
            return "您的微信账号已成功提现过，请输入与上次提现账户一致的手机号";
        }
        //一个手机号只能对应一个人
        count = dogFoodDao.getWithdrawOfMobile(dogfoodWithdraw);
        if (count > 0) {
            return "该手机号码已参与过本活动，请重新输入";
        }
        
        return null;
    }
    
    /**
     * 未有账号用户提现时生成注册数据
     * @param dogfoodWithdraw
     * @author meiiy
     * @version 2017年4月6日
     */
    public void  createRegisterData(DogfoodWithdraw dogfoodWithdraw)
    {
        //生成订单号
        String orderId = "TX" + OrderNumberUtil.getOrderNumber();
        //提现金额
        BigDecimal noCashAmount = this.noCashAmount(dogfoodWithdraw.getOpenid());
        
        dogfoodWithdraw.setOrderId(orderId);
        dogfoodWithdraw.setAmount(noCashAmount);
        dogfoodWithdraw.setMessage("初始化");
        dogfoodWithdraw.setCreateTime(new Date());
        //保存提现记录
        dogFoodWithdrowService.insertWithdraw(dogfoodWithdraw);
    }
    
    /**
     * 提现用户注册成功后修改提现状态
     * @param orderId 订单号
     * @param mobile 注册手机号
     * @param signature 签名
     * @return 成功返回null,失败返回错误信息
     * @author meiiy
     * @version 2017年4月7日
     */
    public String updateForRegister(String orderId,String mobile,String signature)
    {
        //验证签名
        StringBuilder sb = new StringBuilder();
        sb.append(orderId).append(mobile).append(DogfoodUtil.WITHDRAW_SECRET);
        String sign = Encrypt.getMD5(sb.toString());
        logger.info(">>>>>>>>>>>通知验证签名=signature"+signature+"...sign="+sign);
        if (signature.equals(sign)) {
            //查询订单
            DogfoodWithdraw dogfoodWithdraw = dogFoodDao.findWithdrawByOrderId(orderId);
            logger.info(">>>>>>>>>>>通知根据订单号orderId="+orderId+"...查询订单是否存在dogfoodWithdraw="+dogfoodWithdraw);
            if (dogfoodWithdraw != null) {
                //订单处理状态
                Integer status = dogfoodWithdraw.getStatus();
                logger.info(">>>>>>>>>>>通知根据订单号orderId="+orderId+"...订单状态="+status);
                if (status == null) {
                    //修改未提现数据为已提现
                    dogFoodDao.updateCashStatus(dogfoodWithdraw.getOpenid());
                    //更新提现记录
                    dogfoodWithdraw.setMobile(mobile);
                    dogfoodWithdraw.setStatus(1);
                    dogfoodWithdraw.setMessage("提现成功"+DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
                    dogFoodDao.updateWithdrawForNotify(dogfoodWithdraw);
                    
                    return null;
                } else {
                    return "订单已处理";
                }
            } else {
                return "订单号不存在";
            }
        } else {
            return "签名信息错误";
        }
    }
    
    /**
     * 生成狗粮数据
     * @param openid  用户openid
     * @return 本次获取的狗粮数量
     * @author meiiy
     * @version 2017年3月26日
     */
    @Transactional
    public Integer produce(String openid)
    {
        //获取当天已刷狗粮次数
        Integer count = this.countNumToday(openid);
        //获取狗粮数量
        Integer num = DogfoodUtil.gainDogFoodNum(count);
        //保存数据
        DogfoodProducing dogfoodProducing = new DogfoodProducing();
        dogfoodProducing.setOpenid(openid);
        dogfoodProducing.setNumber(num);
        dogfoodProducing.setIsConvert(Constant.DOGFOOD_NO_CONVERT);
        dogfoodProducing.setCreateTime(new Date());
        
        dogFoodDao.insertProducing(dogfoodProducing);
        
        return num;
    }
    
    /**
     * 查询获得的所有狗粮数
     * @param openid 用户openid
     * @return 狗粮数目，如果未参与活动返回0
     * @author meiiy
     * @version 2017年3月24日
     */
    public int getTotalDogFood(String openid)
    {
        DogfoodProducing dogfoodProducing = new DogfoodProducing();
        dogfoodProducing.setOpenid(openid);
        Integer num = dogFoodDao.sumDodFood(dogfoodProducing);
        
        return num != null ? num : 0;
    }
    
    /**
     * 查询已兑换的狗粮数
     * @param openid 用户openid
     * @return 狗粮数目，如果未兑换过返回0
     * @author meiiy
     * @version 2017年3月24日
     */
    public int convertedDogFood(String openid)
    {
        DogfoodProducing dogfoodProducing = new DogfoodProducing();
        dogfoodProducing.setOpenid(openid);
        dogfoodProducing.setIsConvert(Constant.DOGFOOD_CONVERTED);//已兑换
        Integer num = dogFoodDao.sumDodFood(dogfoodProducing);
        
        return num != null ? num : 0;
    }
    
    /**
     * 查询已提现的红包金额
     * @param openid 用户openid
     * @return 已提现金额，如果未提现过返回0
     * @author meiiy
     * @version 2017年3月24日
     */
    public BigDecimal convertedAmount(String openid)
    {
        DogfoodConversion dogfoodConversion = new DogfoodConversion();
        dogfoodConversion.setOpenid(openid);
        BigDecimal amount = dogFoodDao.sumRedAmount(dogfoodConversion);
        
        return amount != null ? amount : BigDecimal.ZERO;
    }
    
    /**
     * 查询已兑换的红包金额
     * @param openid 用户openid
     * @return 已兑换金额，如果未兑换过返回0
     * @author meiiy
     * @version 2017年3月24日
     */
    public BigDecimal cashedAmount(String openid)
    {
        DogfoodConversion dogfoodConversion = new DogfoodConversion();
        dogfoodConversion.setOpenid(openid);
        dogfoodConversion.setIsCash(Constant.DOGFOOD_CASHED);//已提现
        BigDecimal amount = dogFoodDao.sumRedAmount(dogfoodConversion);
        
        return amount != null ? amount : BigDecimal.ZERO;
    }
    
    /**
     * 获取分享人的所有助力好友列表
     * @param openid 分享人openid
     * @return
     * @author meiiy
     * @version 2017年3月24日
     */
    public List<DogfoodAssistance> getAssistanceList(String openid)
    {
        return dogFoodDao.getAssistanceList(openid);
    }
    
    /**
     * 获取用户拥有的刷狗粮次数
     * @param openid 用户openid
     * @return
     * @author meiiy
     * @version 2017年3月24日
     */
    public int hasNum(String openid)
    {
        int hasNum = 0;
        //用户好友助力的次数
        int assistanceNum = 0;
        synchronized (this) {
            assistanceNum = this.getAssistanceList(openid).size();
        }
        
        List<DogfoodProducing> list = dogFoodDao.countNumEveryDay(openid);
        int size = list.size();
        if (size == 0) {//未刷过狗粮，第一天免费两次
            hasNum = 2 + assistanceNum;
        } else {
            int freeNum = 0;//当天免费的次数
            int usedAssistanceNum = 0;//当天使用助力的次数
            int usedNum = 0; //当天已刷的次数
            //获取当前查询日期
            Date nowDate = new Date();
            try {
                nowDate = DateUtils.parseDate(DateFormatUtils.format(nowDate, "yyyy-MM-dd"), "yyyy-MM-dd");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            
            for (int i = 0;i<list.size();i++) {
                DogfoodProducing obj = list.get(i);
                Date createDate = obj.getCreateTime();//刷狗粮的日期
                int countForDay = obj.getCountForDay();//当天刷狗粮次数
                if (i == 0) {
                    if (nowDate.compareTo(createDate) != 0) {//第二天查询
                        usedAssistanceNum += countForDay > 2 ? countForDay - 2 : 0;
                        freeNum = 1;
                    } else {//第一天查询当天
                        freeNum = 2;
                        usedNum = countForDay;
                    }
                } else {
                    freeNum = 1;
                    if (nowDate.compareTo(createDate) != 0) {
                        usedAssistanceNum += countForDay > 1 ? countForDay - 1 : 0;
                    } else {//第二天查当天，第三天查当天
                        usedNum = countForDay;
                    }
                }
                
//                if (nowDate.compareTo(createDate) == 0) {
//                    usedNum = countForDay;
//                }
            }
            
            hasNum = assistanceNum - usedAssistanceNum + freeNum - usedNum;
        }
        
        //最多显示21次
        return hasNum > 21 ? 21 : hasNum;
    }
    
    /**
     * 判断用户是否还能参与活动（一个用户只能参与活动3天）
     * @param openid 用户openid
     * @return true-可以参与；false-不可以再参与
     * @author meiiy
     * @version 2017年3月24日
     */
    public boolean canProducing(String openid)
    {
        List<DogfoodProducing> list = dogFoodDao.countNumForDay(openid);
        DogfoodProducing dogfoodProducing = list.get(0);
        
        if (dogfoodProducing.getCountForDay() < 3) {
            return true;
        } else if (dogfoodProducing.getCountForDay() == 3) {
            Date nowDate = new Date();
            try {
                nowDate = DateUtils.parseDate(DateFormatUtils.format(nowDate, "yyyy-MM-dd"), "yyyy-MM-dd");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //如果已参与3天，且当前日期大于最后一天刷狗粮的日期就不能再参与
            if (nowDate.after(dogfoodProducing.getLastDate())) {
                return false;
            }
            
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * 判断用户是否为第一次刷狗粮
     * @param openid
     * @return 是-true,不是-false
     * @author meiiy
     * @version 2017年3月25日
     */
    public Boolean isFirst(String openid)
    {
        DogfoodProducing dogfoodProducing = new DogfoodProducing();
        dogfoodProducing.setOpenid(openid);
        
        Integer count = dogFoodDao.countNum(dogfoodProducing);
        
        return count == 0;
    }
    
    /**
     * 获取用户当天已刷狗粮的次数
     * @param openid 用户openid
     * @return
     * @author meiiy
     * @version 2017年3月24日
     */
    public int countNumToday(String openid)
    {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        Date beginTime = c.getTime();
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        Date endTime = c.getTime();
        DogfoodProducing dogfoodProducing = new DogfoodProducing();
        dogfoodProducing.setOpenid(openid);
        dogfoodProducing.setBeginTime(beginTime);
        dogfoodProducing.setEndTime(endTime);
        
        return dogFoodDao.countNum(dogfoodProducing);
    }
    
    /**
     * 查询当天提现次数
     * @param openid 用户openid
     * @return
     * @author meiiy
     * @version 2017年4月11日
     */
    public int countWithdrawNum(String openid)
    {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        Date beginTime = c.getTime();
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        Date endTime = c.getTime();
        DogfoodWithdraw dogfoodWithdraw = new DogfoodWithdraw();
        dogfoodWithdraw.setOpenid(openid);
        dogfoodWithdraw.setBeginTime(beginTime);
        dogfoodWithdraw.setEndTime(endTime);
        
        return dogFoodDao.countWithdrawNum(dogfoodWithdraw);
    }
    
    public Long getAssistanceNumByOpenid(String openid){
        return dogFoodDao.getAssistanceNumByOpenid(openid);
    }
    
    /**
     * 保存助力记录
     * @param dogfoodAssistance
     * @return
     */
    public synchronized Long saveAssistance(DogfoodAssistance dogfoodAssistance){
        return dogFoodDao.saveAssistance(dogfoodAssistance);
    }
    
    /**
     * 保存带有OpenID参数的的ticket
     * @param dogfoodQrcode
     * @return
     */
    public Long saveQrcode(DogfoodQrcode dogfoodQrcode){
        return dogFoodDao.saveQrcode(dogfoodQrcode);
    }
    
    /**
     *  根据openid获取生成二维码的ticket
     * @param openid
     * @return
     */
    public DogfoodQrcode getQrcodeByOpenid(String openid){
        return dogFoodDao.getQrcodeByOpenid(openid);
    }
}
