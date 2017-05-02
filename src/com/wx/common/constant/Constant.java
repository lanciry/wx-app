package com.wx.common.constant;

import com.wx.util.ResourceUtil;

/**
 * 静态常量类
 * @author meiiy
 * @version Apr 3, 2016
 */
public class Constant
{
	//微信请求签名token
	public static final String SIGN_TOKEN = "xxxxxx";
	//微信访问地址
	public static final String WX_HOST = ResourceUtil.getProPerties("wx_host");
	//微信应用ID
	public static final String WX_APPID = ResourceUtil.getProPerties("wx_appid");
	//微信应用密钥
	public static final String WX_APP_SECRET = ResourceUtil.getProPerties("wx_appSecret");
	//邀请码未使用标志
	public static final int INVITE_CODE_UNUSED = 0;
	//邀请码已使用标志
	public static final int INVITE_CODE_USED = 1;
	//房产证，身份证照片保存路径
	public static final String FILE_BASE_DIR = ResourceUtil.getProPerties("file_base_dir");
	
	//审核状态，取消
	public static final int AUDIT_STATUS_CANCELLED = 0;
	//审核状态，待初评
	public static final int AUDIT_STATUS_NOT = 1;
	//审核状态，已初评,通过
	public static final int AUDIT_STATUS_FIRST_YES = 2;
	//审核状态，已初评,未通过
	public static final int AUDIT_STATUS_FIRST_NO = 3;
	//已下户申请
	public static final int AUDIT_STATUS_XH = 4;
	//已分配下户人员
	public static final int AUDIT_STATUS_XH_FENPEI = 5;
	//用户放弃
	public static final int AUDIT_STATUS_QUIT = 9;
	//已提交综合结论
	public static final int AUDIT_STATUS_SECOND = 6;
	//待初审
	public static final int AUDIT_STATUS_SECOND_YES = 7;
	//待终审
	public static final int AUDIT_STATUS_SECOND_NO = 8;
	
	//模板消息编号
	public static final String TEMPLETE_ID = ResourceUtil.getProPerties("template_id");
	
	//用户状态 正常
	public static final int CUS_STATUS_NORMAL = 1;
	//用户状态 禁用
	public static final int CUS_STATUS_DISABLED = 0;
	
	//狗粮未兑换
	public static final int DOGFOOD_NO_CONVERT = 0;
	//狗粮已兑换
	public static final int DOGFOOD_CONVERTED = 1;
	//狗粮红包未提现
	public static final int DOGFOOD_NO_CASH = 0;
	//狗粮红包已提现
    public static final int DOGFOOD_CASHED = 1;
	
}
