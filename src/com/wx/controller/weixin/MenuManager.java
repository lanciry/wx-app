package com.wx.controller.weixin;

import java.text.MessageFormat;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wx.common.constant.Constant;
import com.wx.controller.weixin.model.Button;
import com.wx.controller.weixin.model.CommonButton;
import com.wx.controller.weixin.model.ComplexButton;
import com.wx.controller.weixin.model.Menu;
import com.wx.util.WeixinUtil;

/**
 * 创建微信菜单
 * @author meiiy
 * @version Apr 4, 2016
 */
@Controller
public class MenuManager
{
	private static Logger logger = Logger.getLogger(WeixinUtil.class);
	
	//自定义菜单网页授权URL
	public static final String CUS_MENU_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+ Constant.WX_APPID +"&redirect_uri="+
				 Constant.WX_HOST + "/oauth&response_type=code&scope=snsapi_base&state={0}#wechat_redirect";
	
	@RequestMapping(value="/createMenu",method=RequestMethod.GET)
	@ResponseBody
	public String create()
	{
		// 调用接口创建菜单
		int result = WeixinUtil.createMenu(getLCMenu());

		// 判断菜单创建结果
		if (0 == result) {
			logger.info("菜单创建成功！");
		} else {
			logger.info("菜单创建失败，错误码：" + result);
		}
		
		return String.valueOf(result);
	}

	/**
	 * 组装菜单数据
	 * @return
	 */
	private static Menu getMenu()
	{
		CommonButton btn100 = new CommonButton();
		btn100.setName("我的订单");
		btn100.setType("view");
		btn100.setUrl(MessageFormat.format(CUS_MENU_URL, "/order/myOrders"));
		
		CommonButton btn200 = new CommonButton();
		btn200.setName("个人资料");
		btn200.setType("view");
		btn200.setUrl(MessageFormat.format(CUS_MENU_URL, "/customer/userInfo"));
		
		CommonButton mainBtn1 = new CommonButton();
		mainBtn1.setName("提交订单");
		mainBtn1.setType("view");
		mainBtn1.setUrl(MessageFormat.format(CUS_MENU_URL, "/order/applyOrder"));
		
		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("个人中心");
		mainBtn2.setSub_button(new CommonButton[] {btn100, btn200});

		
		CommonButton btn10 = new CommonButton();
		btn10.setName("行业咨询");
		btn10.setType("click");
		btn10.setKey("10");
		CommonButton btn20 = new CommonButton();
		btn20.setName("企业动态");
		btn20.setType("click");
		btn20.setKey("20");
		CommonButton btn30 = new CommonButton();
		btn30.setName("鼎鑫资产");
		btn30.setType("click");
		btn30.setKey("30");
		CommonButton btn40 = new CommonButton();
		btn40.setName("首付贷");
		btn40.setType("click");
		btn40.setKey("40");
		CommonButton btn50 = new CommonButton();
		btn50.setName("企业招聘");
		btn50.setType("view");
		btn50.setUrl("http://eqxiu.com/s/ZFZDsOQw");
		
		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("更多");
		mainBtn3.setSub_button(new CommonButton[] {btn10, btn20,btn30,btn40,btn50});

		Menu menu = new Menu();
		menu.setButton(new Button[] {mainBtn1, mainBtn2, mainBtn3 });

		return menu;
	}
	
	private static Menu getLCMenu()
	{
        CommonButton btn100 = new CommonButton();
        btn100.setName("分享赚");
        btn100.setType("view");
        btn100.setUrl("");
        
        CommonButton btn200 = new CommonButton();
        btn200.setName("收益+");
        btn200.setType("view");
        btn200.setUrl("");
        
        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("★先登录");
        mainBtn1.setSub_button(new CommonButton[] {btn100, btn200});
        
        CommonButton btn700 = new CommonButton();
        btn700.setName("带球过人抢排名");
        btn700.setType("view");
        btn700.setUrl("");
        
        CommonButton btn800 = new CommonButton();
        btn800.setName("刷狗粮赚体验金");
        btn800.setType("view");
        btn800.setUrl(MessageFormat.format(CUS_MENU_URL, "/dog-food/index"));
        
        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("福利游戏");
        mainBtn2.setSub_button(new CommonButton[] {btn700, btn800});
        
        
        CommonButton btn300 = new CommonButton();
        btn300.setName("安全第一");
        btn300.setType("view");
        btn300.setUrl("");
        
        CommonButton btn400 = new CommonButton();
        btn400.setName("关于我们");
        btn400.setType("view");
        btn400.setUrl("");
        
        CommonButton btn500 = new CommonButton();
        btn500.setName("官方公告");
        btn500.setType("view");
        btn500.setUrl("");
        
        CommonButton btn600 = new CommonButton();
        btn600.setName("帮助中心");
        btn600.setType("view");
        btn600.setUrl("");
        
        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName("安全靠谱");
        mainBtn3.setSub_button(new CommonButton[] {btn300,btn400,btn500,btn600});
        
        Menu menu = new Menu();
        menu.setButton(new Button[] {mainBtn1,mainBtn2,mainBtn3});

        return menu;
	}
	
}
