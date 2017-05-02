package com.wx.common.cache;

import com.wx.model.AccessToken;
import com.wx.util.CacheUtils;

/**
 * 网页JS API ticket缓存管理
 * @author meiiy
 * @version Apr 4, 2016
 */
public class JsapiTicketCache
{
	private static final String TOKEN_NAME = "jsapi_ticket";
	private static final String TOKEN_KEY = "jsapi_ticket_key";
	
	/**
	 * 放入access_token
	 * @param accessToken
	 */
	public static synchronized void putTicket(AccessToken accessToken)
	{
		if (accessToken != null){
			CacheUtils.put(TOKEN_NAME, TOKEN_KEY, accessToken);
		}
	}
	
	/**
	 * 获取access_token
	 * @return
	 */
	public static synchronized AccessToken getCache() 
	{
		Object obj = CacheUtils.get(TOKEN_NAME, TOKEN_KEY);
		if (obj != null) {
			return (AccessToken)obj;
		}
		return null;
	}
	
	/**
	 * 删除access_token
	 */
	public static synchronized void removeCache()
	{
		CacheUtils.remove(TOKEN_NAME, TOKEN_KEY);
	}
}
