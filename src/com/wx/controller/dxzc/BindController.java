package com.wx.controller.dxzc;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wx.common.constant.Constant;
import com.wx.model.dxzc.Customer;
import com.wx.service.dxzc.BindService;

/**
 * 微信用户绑定
 * @author meiiy
 * @version Apr 5, 2016
 */
//@Controller
//@RequestMapping("bind")
public class BindController
{
	private static Logger logger = Logger.getLogger(BindController.class);
	
	@Autowired
	private BindService bindService;
	
	/**
	 * 验证用户是否已注册绑定
	 * @version Apr 6, 2016
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/validation")
	public String validation(HttpServletRequest request,Model model,RedirectAttributes attr)
	{
		Object openid = request.getSession().getAttribute("openid");
		if (openid != null && !"".equals((String)openid)) {
			Customer customer = bindService.getCustomer((String)openid);
			if (customer != null) {
				logger.info(">>>>>>>>>>>>>validation获取到用户绑定信息");
				//验证用户是否已被禁用
				if (customer.getStatus() == null || customer.getStatus() == Constant.CUS_STATUS_DISABLED) {
					logger.info(">>>>>>>>>>>>>用户："+customer.getName()+" 已被禁用");
					attr.addFlashAttribute("msg", "错误：你的账号已被禁用");
					return "redirect:/error";
				} else {
					//保存用户信息到session
					request.getSession().setAttribute("customer", customer);
					//请求的URL
					String reqUrl = (String)request.getSession().getAttribute("reqUrl");
					return "redirect:"+reqUrl;
				}
			} else {
				logger.info(">>>>>>>>>>>>>validation未获取到用户绑定信息，forward至注册绑定页面");
				model.addAttribute("customer", new Customer());
				return "bind/binding";
			}
		} else {//不是通过微信端访问
			logger.info(">>>>>>>>>bind-validation，未获取到openid");
			attr.addFlashAttribute("msg", "错误：请在微信客户端打开");
			return "redirect:/error";
		}
	}
	
	/**
	 * 保存注册绑定信息
	 * @version Apr 7, 2016
	 * @return
	 */
	@RequestMapping(value="/save")
	public synchronized String save(@ModelAttribute Customer customer,Model model,HttpServletRequest request)
	{
		String errorMsg = validCustomer(customer);
		if (errorMsg == null){
			try {
				customer.setOpenid((String)request.getSession().getAttribute("openid"));//设置用户微信openid
				bindService.save(customer);
				//保存用户信息到session
				request.getSession().setAttribute("customer", customer);
			} catch (Exception e) {
				logger.info(">>>>>>>>>>>"+customer.getName()+" 注册绑定失败："+e.getMessage(),e);
				model.addAttribute("errorMsg", "注册绑定失败,请联系客服");
				return "bind/binding";
			}
			logger.info(">>>>>>>>>>>"+customer.getName()+" 注册绑定成功");
			
			//请求的URL
			String reqUrl = (String)request.getSession().getAttribute("reqUrl");
			return "redirect:"+reqUrl;
		} else {//返回注册绑定页
			logger.info(">>>>>>>>>>>注册绑定验证失败："+errorMsg);
			model.addAttribute("errorMsg", errorMsg);
			return "bind/binding";
		}
	}
	
	/**
	 * 验证用户注册绑定信息是否正确
	 * @version Apr 7, 2016
	 * @param cus 注册信息
	 * @return 正确返回null,错误返回错误信息
	 */
	private String validCustomer(Customer cus)
	{
		String name = cus.getName();
		String mobilePhone = cus.getMobilePhone();
		String inviteCode = cus.getInviteCode();
		if (StringUtils.isBlank(name)) {
			return "姓名不能为空";
		} else if (StringUtils.isBlank(mobilePhone)) {
			return "手机号不能为空";
		} else if (!mobilePhone.matches("^1[0-9]{10}$")) {
			return "手机号码格式不正确";
		}  else if (StringUtils.isBlank(inviteCode)) {
			return "邀请码不能为空";
		} else if (!bindService.validInviteCode(inviteCode)) {//邀请码是否正确
			return "邀请码不正确";
		} else if (bindService.hasNameAndPhone(name, mobilePhone)) {
			return "用户信息已被注册";
		}
		return null;
	}
}
