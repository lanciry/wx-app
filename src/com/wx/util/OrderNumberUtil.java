package com.wx.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 订单编号生成类
 * @author meiiy
 * @version 2016年4月9日
 */
public class OrderNumberUtil
{
	private static int ORDER_NUMBER = 0;
	private static final String PATTERN = "yyyyMMdd";
	
	private static final Set<String> orderIds = new ConcurrentSkipListSet<>();
	
	/**
	 * 获取订单编号
	 * @deprecated
	 * @return
	 * @author meiiy
	 * @version 2016年4月9日
	 */
	public static synchronized String getNumber()
	{
		String orderNumber = "";
		ORDER_NUMBER += 1;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		orderNumber = sdf.format(new Date());
		for (int i = 4 - String.valueOf(ORDER_NUMBER).length(); i > 0; i--) {
			orderNumber = orderNumber + "0";
		}
		return orderNumber + String.valueOf(ORDER_NUMBER);
	}
	
	/**
	 * 获取订单编号
	 * @return
	 * @author meiiy
	 * @version 2016年4月18日
	 */
	public static String getOrderNumber(String lastOrderNo,Date date)
	{
		if (StringUtils.isEmpty(lastOrderNo)) {
			return DateFormatUtils.format(date, PATTERN) + "0001";
		} else {
			String suffix = lastOrderNo.substring(PATTERN.length());
			int no = Integer.parseInt(suffix) + 1;
			return DateFormatUtils.format(date, PATTERN) + String.format("%04d", no);
		}
	}
	
	/**
	 * 刷狗粮活动生成16位数字提现订单号
	 * @return 
	 * @author meiiy
	 * @version 2017年3月27日
	 */
	public synchronized static String getOrderNumber()
	{
	    String orderId = RandomStringUtils.randomNumeric(16);
	    
	    if (!orderIds.add(orderId)) {
            return getOrderNumber();
        } else {
            return orderId;
        }
	}
}
