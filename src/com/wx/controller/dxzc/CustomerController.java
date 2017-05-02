package com.wx.controller.dxzc;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wx.model.dxzc.Customer;
import com.wx.service.dxzc.CustomerOrderService;

/**
 * 用户相关信息处理
 * @author meiiy
 * @version Apr 7, 2016
 */
//@Controller
//@RequestMapping("/customer")
public class CustomerController
{
	private static Logger logger = Logger.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerOrderService customerOrderService;
	
	/**
	 * 个人资料
	 * @version Apr 10, 2016
	 * @return
	 */
	@RequestMapping(value="/userInfo")
	public String getCustomerInfo(HttpServletRequest request)
	{
		Customer customer = (Customer)request.getSession().getAttribute("customer");
		//查询我的订单总数
		int orderNum = customerOrderService.getOrderNum(customer.getId());
		logger.info(">>>>>>>>>>>>>>用户：【"+customer.getName()+"】订单总数："+orderNum);
		request.setAttribute("orderNum", orderNum);
		return "dx/customerInfo";
	}
	
	/**
	 * 产品介绍
	 * @return
	 * @author meiiy
	 * @version 2016年5月4日
	 */
	@RequestMapping(value="/product-introduction")
	public String productIntroduction()
	{
		return "dx/productIntroduction";
	}
}
