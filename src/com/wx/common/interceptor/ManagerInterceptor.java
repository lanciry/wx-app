package com.wx.common.interceptor;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.wx.controller.weixin.MenuManager;

/**
 * 用于过滤未登陆用户访问管理内容的拦截器
 * @author meiiy
 * @version Apr 3, 2016
 */
public class ManagerInterceptor implements HandlerInterceptor
{
	private static Logger logger = Logger.getLogger(ManagerInterceptor.class);
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object obj, Exception e)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj, ModelAndView mav) throws Exception {
	}
	
	/**
	 * 未绑定的用户跳转至绑定页
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception
	{
		String ua = request.getHeader("user-agent").toLowerCase();
		//logger.info(">>>>>>>>>>浏览器信息:"+ua);
		if (ua.indexOf("micromessenger") ==-1) {
		    logger.info(">>>>>>>>>>非微信浏览器打开");
			response.sendRedirect(MessageFormat.format(MenuManager.CUS_MENU_URL, request.getRequestURI()));
			return false;
		} else {
//			if (request.getSession().getAttribute("customer") != null) {
//				logger.info(">>>>>>>>>>请求URI="+request.getRequestURI()+",已注册绑定");
//				return true;
//			} else {
//				logger.info(">>>>>>>>>>请求URI="+request.getRequestURI()+",未注册绑定，跳转至/bind/validation");
//				response.sendRedirect(request.getContextPath()+"/bind/validation");
//				return false;
//			}
		    
		    if (request.getSession().getAttribute("openid") != null) {
                logger.info(">>>>>>>>>>请求URI="+request.getRequestURI()+",已微信登录");
                return true;
            } else {
                logger.info(">>>>>>>>>>请求URI="+request.getRequestURI()+",未获取到openid，重新授权登录");
                if ("/dog-food/invite".equals(request.getRequestURI())) {
                    logger.info(">>>>>>>>>>>进入分享页授权"+"/dog-food/invite?"+request.getQueryString());
                    response.sendRedirect(MessageFormat.format(MenuManager.CUS_MENU_URL, "/dog-food/invite?"+request.getQueryString()));
                } else {
                    response.sendRedirect(MessageFormat.format(MenuManager.CUS_MENU_URL, "/dog-food/index"));
                }
                
                return false;
            }
		}
	}
}
