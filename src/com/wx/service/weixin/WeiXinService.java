package com.wx.service.weixin;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.wx.controller.weixin.model.Article;
import com.wx.controller.weixin.model.NewsMessageResp;
import com.wx.controller.weixin.model.TextMessageResp;
import com.wx.model._17lc.DogfoodAssistance;
import com.wx.model._17lc.DogfoodQrcode;
import com.wx.service._17lc.DogFoodService;
import com.wx.util.MessageUtil;
import com.wx.util.WeixinUtil;

import net.sf.json.JSONObject;

/**
 * 处理微信服务端请求
 * @author meiiy
 * @version Apr 4, 2016
 */
@Service
public class WeiXinService
{
	private static Logger logger = Logger.getLogger(WeiXinService.class);
	
	@Autowired
	private DogFoodService dogFoodService;
	
	public String process(HttpServletRequest request)
	{
		String respMessage = null;
		
		try {
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			//String msgId = requestMap.get("MsgId");
			//消息内容
			String content = requestMap.get("Content");
			//事件KEY
			String eventKey = requestMap.get("EventKey");
			
			logger.info(">>>>>>>>>>>.微信客户端发送请求"+requestMap);
			
			// 默认回复此文本消息
			TextMessageResp textMessage = new TextMessageResp();
			
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {//【微信触发类型】文本消息
				if("520".equals(content.toLowerCase())){
//					textMessage = new TextMessageResp();
//					textMessage.setToUserName(fromUserName);
//					textMessage.setFromUserName(toUserName);
//					textMessage.setCreateTime(new Date().getTime());
//					textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
//					textMessage.setContent("点击以下链接，开启快乐财富之旅吧！[爱心]\n<a href='www.17lc.com/common/member/reg?invite=cmVnX2ludml0ZTEzMzIxMTYyNDQw'>www.17lc.com/common/member/reg?invite=cmVnX2ludml0ZTEzMzIxMTYyNDQw</a>");
				    
				    NewsMessageResp resp = new NewsMessageResp();
                    resp.setToUserName(fromUserName);
                    resp.setFromUserName(toUserName);
                    resp.setCreateTime(new Date().getTime());
                    resp.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                    resp.setArticleCount(1);
                    
                    List<Article> articles= Lists.newArrayList();
                    Article art = new Article();
                    art.setTitle("快乐理财 停不下来！玩游戏赢百万体验金！");
                    art.setDescription("别说我们土豪，我们只是舍得花钱~");
                    art.setPicUrl("http://mmbiz.qpic.cn/mmbiz_png/loPxBHicpov01haMZY55M59F2qpLRERvycoIgQ4M0EHk3mG9ULCue2GJXjlE2L413aH8iblxdJ88lZmuYEIRibLyQ/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1");
                    art.setUrl("http://mp.weixin.qq.com/s/_xySoq9nhluJodKxbWW-RQ");
                    articles.add(art);
                    resp.setArticles(articles);
                    respMessage = MessageUtil.newsMessageToXml(resp);
				} else {
					textMessage = new TextMessageResp();
					textMessage.setToUserName(fromUserName);
					textMessage.setFromUserName(toUserName);
					textMessage.setCreateTime(new Date().getTime());
					textMessage.setMsgType("transfer_customer_service");
					respMessage = MessageUtil.textMessageToXml(textMessage);
				}
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {//【微信触发类型】事件推送
				String eventType = requestMap.get("Event");// 事件类型
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					textMessage = new TextMessageResp();
					textMessage.setToUserName(fromUserName);
					textMessage.setFromUserName(toUserName);
					textMessage.setCreateTime(new Date().getTime());
					textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
					//扫描带参数的二维码，未关注时
					if (StringUtils.isNotEmpty(eventKey) && eventKey.startsWith(MessageUtil.EVENT_KEY_QRSCENE)) {
						// 邀请人的openid
						String inviterOpenid = eventKey.split("_", 2)[1];
						// 根据OpenID判断是否已经助力过,保存第一次分享人永久二维码的ticket
						Long count = dogFoodService.getAssistanceNumByOpenid(fromUserName);
						if(count > 0){
							// 已经助力
							textMessage.setContent("欢迎关注“一起理财”\n央企控股，安全合规！\n点击【登录】，给你稳稳幸福！[爱心]\n\n【福利游戏】，壕礼送不停！\n不要错过哦！[胜利]");
						}else{
							JSONObject jsonObject = WeixinUtil.getUserInfo(fromUserName);
							DogfoodAssistance dogfoodAssistance = new DogfoodAssistance();
							dogfoodAssistance.setInviterOpenid(inviterOpenid);
							dogfoodAssistance.setAssistorOpenid(fromUserName);
							//dogfoodAssistance.setAssistorNickname(jsonObject.getString("nickname"));
							dogfoodAssistance.setAssistorHeadimg(jsonObject.getString("headimgurl"));
							dogFoodService.saveAssistance(dogfoodAssistance);
							textMessage.setContent("感谢您的关注，恭喜您已为好友助力成功！点击【福利游戏】玩游戏，赢红包！");
						}
						// 根据邀请人的OpenID判断是否已保存用于生成二维码的ticket
						DogfoodQrcode dogfoodQrcode = dogFoodService.getQrcodeByOpenid(inviterOpenid);
						if(dogfoodQrcode == null){
							String ticket = requestMap.get("Ticket");
							dogfoodQrcode = new DogfoodQrcode();
							dogfoodQrcode.setOpenid(inviterOpenid);
							dogfoodQrcode.setTicket(ticket);
							dogFoodService.saveQrcode(dogfoodQrcode);
						}
					} else {// 订阅
					    //第三个服务会推送，此处不推送
						//textMessage.setContent("欢迎关注“一起理财”\n央企控股，安全合规！\n点击【登录】，给你稳稳幸福！[爱心]\n\n【福利游戏】，壕礼送不停！\n不要错过哦！[胜利]");
					}
					
					respMessage = MessageUtil.textMessageToXml(textMessage);
				} else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {// 取消订阅
					
				} else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {// 自定义菜单点击事件，发送图文消息
					NewsMessageResp resp = new NewsMessageResp();
					resp.setToUserName(fromUserName);
					resp.setFromUserName(toUserName);
					resp.setCreateTime(new Date().getTime());
					resp.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
					resp.setArticleCount(1);
					
					List<Article> articles= Lists.newArrayList();
					Article art = new Article();
					
//					if("10".equals(eventKey)){//行业咨询
//						art.setTitle("房地产税法正式进入全国人大立法规划");
//						art.setDescription("社会一直热议的房地产税终于有了实质性动向。最新调整过的十二届全国人大常委会立法规划本周向社会公布，包括房地产");
//						art.setPicUrl("http://wx.dxzcgl.com/static/images/hangyezixun_title.jpg");
//						art.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5MzgwMzI5Mw==&mid=211215647&idx=1&sn=fe1d01913094608c21b7b68e7e8ed6d9&scene=18#wechat_redirect");
//					} else if ("20".equals(eventKey)) {//企业动态
//						art.setTitle("鼎鑫资产--葫芦岛分公司培训进行中");
//						art.setDescription("通过此次培训，葫芦岛分公司同事对鼎鑫资产企业文化有了新的认识，同时也加深了对企业产品的了解。");
//						art.setPicUrl("http://wx.dxzcgl.com/static/images/qiyedongtai_title.jpg");
//						art.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5MzgwMzI5Mw==&mid=210708590&idx=1&sn=098c025ebbed94bfdccf1be1419d31b4&scene=18#wechat_redirect");
//					} else if ("30".equals(eventKey)) {//鼎鑫资产
//						art.setTitle("鼎鑫（北京）资产管理有限责任公司");
//						art.setDescription("鼎鑫（北京）资产管理有限责任公司主要服务于金融机构，非金融机构和自然人主体，对于客户的融资需求和理财意愿能够提供快捷便利、资金安全、风险可控、专业、合法的金融服务。");
//						art.setPicUrl("http://wx.dxzcgl.com/static/images/dingxinzichan_title.jpg");
//						art.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5MzgwMzI5Mw==&mid=210706415&idx=1&sn=87e209c3492eb6867c3f3da15e860ebf&scene=18#wechat_redirect");
//					} else if ("40".equals(eventKey)) {//首付贷
//						art.setTitle("鼎鑫资产---消费信用贷");
//						art.setDescription("“首付贷产品”是通过我公司与湖北消费金融公司合作推广的纯信用类贷款产品。现面向华北地区推广，为合作机构提供年化14%的资金成本，与地方资质与信誉较好开发商合作帮助其快速去化房源，更快的完成地方去库存的目标。");
//						art.setPicUrl("http://wx.dxzcgl.com/static/images/shoufudai_title.jpg");
//						art.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5MzgwMzI5Mw==&mid=402384185&idx=1&sn=49f5245b1f3e4234a28faa13db700ce1&scene=18#wechat_redirect");
//					}
					
					articles.add(art);
					resp.setArticles(articles);
					respMessage = MessageUtil.newsMessageToXml(resp);
				} else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {//扫描带参数的二维码，已关注的情况
					textMessage = new TextMessageResp();
					textMessage.setToUserName(fromUserName);
					textMessage.setFromUserName(toUserName);
					textMessage.setCreateTime(new Date().getTime());
					textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
					textMessage.setContent("欢迎关注“一起理财”\n央企控股，安全合规！\n点击【登录】，给你稳稳幸福！[爱心]\n\n【福利游戏】，壕礼送不停！\n不要错过哦！[胜利]");
					
					respMessage = MessageUtil.textMessageToXml(textMessage);
				}
			}else{//【微信触发类型】其他
				textMessage = new TextMessageResp();
				textMessage.setToUserName(fromUserName);
				textMessage.setFromUserName(toUserName);
				textMessage.setCreateTime(new Date().getTime());
				textMessage.setMsgType("transfer_customer_service");
				respMessage = MessageUtil.textMessageToXml(textMessage);
			}
		} catch (Exception e) {
			logger.error(">>>>>>>>>处理微信服务端请求出错："+e.getMessage(),e);
		}
		
		return respMessage;
	}
}
