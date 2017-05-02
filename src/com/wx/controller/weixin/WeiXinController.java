package com.wx.controller.weixin;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wx.common.constant.Constant;
import com.wx.service.dxzc.CustomerService;
import com.wx.service.weixin.WeiXinService;
import com.wx.util.WeixinSignUtil;
import com.wx.util.WeixinUtil;

/**
 * 处理微服务端的请求
 * @author meiiy
 * @version Apr 4, 2016
 */
@Controller
public class WeiXinController
{
	private static Logger logger = Logger.getLogger(WeiXinController.class);
	
	@Autowired
	private WeiXinService weiXinService;
//	@Autowired
//	private CustomerService customerService;
	
	@RequestMapping(value="/weixin",method=RequestMethod.GET)
	@ResponseBody
	public String weixinGet(HttpServletRequest request)
	{
		logger.info(">>>>>>>>>>微信服务端GET请求");
		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");
		// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		if (WeixinSignUtil.checkSignature(Constant.SIGN_TOKEN, signature, timestamp, nonce)) {
			return echostr;
		} else {
			return "error";
		}
	}
	
	/**
	 * 接收微信公众号接收的消息，处理后再做相应的回复
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/weixin",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String weixinPost(HttpServletRequest request)
	{
		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		if (WeixinSignUtil.checkSignature(Constant.SIGN_TOKEN, signature, timestamp, nonce)) {
			return weiXinService.process(request);
		} else {
			return "error";
		}
	}
	
	/**
	 * Oauth2.0校验回调
	 * @param request
	 */
	@RequestMapping("/oauth")
	public String oauth(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		//微信返回的授权码
		String code = request.getParameter("code");
		
		if(StringUtils.isNotBlank(code)){
			JSONObject result = WeixinUtil.oauth2(code);
			if(result != null){
				String openid = result.getString("openid");
				if(StringUtils.isNotEmpty(openid)){
					String url = request.getParameter("state");
					
					request.getSession().setAttribute("openid", openid);
					request.getSession().setAttribute("reqUrl", url);
					
					return "redirect:"+url;
				}
			}
		}
		
		return "redirect:/error";
	}
	
	@RequestMapping("/error")
    public String errorPage(HttpServletRequest request)
	{
		request.setAttribute("msg", request.getParameter("msg"));
		return "errors/error";
	}
	
	/**
	 * 给指定公众号用户发送消息
	 * @return
	 * @author meiiy
	 * @version 2016年4月12日
	 */
//	@RequestMapping(value="/sendTempleteMsg")
//	public void sendTempleteMsg(HttpServletRequest request)
//	{
//	    String data = request.getParameter("data");
//		logger.error(">>>>>>>>>>>>进入发送模板消息data="+data);
//		if (StringUtils.isNotEmpty(data)) {
//			JSONObject jsonObject = JSONObject.fromObject(data);
//			try {
//				String openid = customerService.selectOpenidBycusId(jsonObject.getLong("id"));
//				logger.error(">>>>>>>>>>>>获取openid="+openid);
//				if (StringUtils.isNotEmpty(openid)) {
//					String json = "{\"touser\":\""+openid+"\",\"template_id\":\""
//							+ Constant.TEMPLETE_ID+"\",\"data\":{\"first\":{\"value\":\"" 
//							+ jsonObject.getString("first") + "\"},\"keyword1\":{\"value\":\""
//							+ jsonObject.getString("orderNo") + "\"},\"keyword2\":{\"value\":\""
//							+ jsonObject.getString("auditDate") + "\"},\"keyword3\":{\"value\":\""
//							+ jsonObject.getString("result") + "\"},\"remark\":{\"value\":\"" + jsonObject.getString("remark") + "\"}}}";
//					
//					WeixinUtil.sendTempleteMsg(json);
//				}
//			} catch (Exception e) {
//				logger.error(">>>>>>>>>>>>发送模板消息出错:"+e.getMessage(),e);
//			}
//		}
//	}
}
