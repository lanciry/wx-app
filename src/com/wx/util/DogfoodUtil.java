package com.wx.util;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.time.DateUtils;


public class DogfoodUtil 
{
    //活动结束时间
    private static Date END_TIME;
    //提现接口地址
    public static String WITHDRAW_URL = ResourceUtil.getProPerties("dogfood_withdraw_url");
    //注册提现接口地址
    public static String REG_WITHDRAW_URL = ResourceUtil.getProPerties("dogfood_reg_withdraw_url");
    //提现密钥
    public static String WITHDRAW_SECRET = "yiqilicai_wx_cash_2017!@#";
    //生成狗粮的规则
    private static List<Integer []> ruleList = new LinkedList<>();
    
    static {
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(DateUtils.parseDate(ResourceUtil.getProPerties("dogfood_end_time"), "yyyy-MM-dd HH:mm:ss"));
            END_TIME = c.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        Integer arr[] = new Integer[]{35,45};
        ruleList.add(arr);
        
        arr = new Integer[]{15,20};
        ruleList.add(arr);
        
        arr = new Integer[]{4,5};
        ruleList.add(arr);
        
        arr = new Integer[]{10,15};
        ruleList.add(arr);
        
        arr = new Integer[]{12,18};
        ruleList.add(arr);
        
        arr = new Integer[]{8,10};
        ruleList.add(arr);
        
        arr = new Integer[]{6,7};
        ruleList.add(arr);
    }
    
    /**
     * 活动是否已结束
     * @param current 当前时间
     * @return true-结束，false-未结束
     * @author meiiy
     * @version 2017年3月26日
     */
    public static boolean isEnd(Date current)
    {
        return current.after(END_TIME);
    }
    
    /**
     * 生成狗粮
     * @param num 用户当天已刷狗粮的次数,一天最多7次，最多120粒
     * @return 生成的狗粮数量
     * @author meiiy
     * @version 2017年3月26日
     */
    public static Integer gainDogFoodNum(int num)
    {
        if (num > 6) {
            return 0;
        }
        
        Integer arr[] = ruleList.get(num);
        
        return RandomUtils.nextInt(arr[0], arr[1]);
    }
    
    /** 
     * 去掉数字末尾多余的0
     * @param s 
     * @return  
     */  
    public static String subZeroAndDot(String str)
    {  
        if (str == null) return null;
        
        if (str.indexOf(".") > 0) {
            str = str.replaceAll("0+?$", "");//去掉末尾多余的0  
            str = str.replaceAll("[.]$", "");//如最后一位是.则去掉  
        }  
        return str;  
    }
    
    /**
     * 获取体验金大小
     * @param amount 现金金额
     * @return 体验金金额
     * @author meiiy
     * @version 2017年4月11日
     */
    public static int getExpAmount(BigDecimal amount)
    {
        //0-2元1万，2-4元2万，4-6元3万， 6-8元4万， 8-10元5万，10-12 6万 ， 12-14 7万 ，14-16 8万， 16-18 9万
        if (amount == null || amount.compareTo(BigDecimal.ZERO) != 1) {
            return 0;
        }
        
        Double expAmount = Math.ceil(amount.doubleValue()/2) * 10000;
        
        return expAmount.intValue();
    }
    
}
