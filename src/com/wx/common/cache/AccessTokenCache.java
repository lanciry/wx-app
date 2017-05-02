package com.wx.common.cache;

import java.util.Date;

import com.wx.model.AccessToken;
import com.wx.util.CacheUtils;

/**
 * 全局accessToken缓存
 * @author meiiy
 * @version Apr 4, 2016
 */
public class AccessTokenCache
{
	private static final String TOKEN_NAME = "access_token";
	private static final String TOKEN_KEY = "access_token_key";
	
	//private static AccessTokenDao accessTokenDao = SpringContextHolder.getBean(AccessTokenDao.class);
	
	/**
	 * 放入access_token
	 * @param accessToken
	 */
	public static synchronized void putAccessToken(AccessToken accessToken)
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
		
		return obj != null ? (AccessToken)obj : null;
		
		/*暂时不保存入库
		if (obj == null) {
			//从数据库中读取
			AccessToken token = accessTokenDao.getAccessToken();
			if (token != null) {
				putAccessToken(token);
			}
			return token;
		} else {
			return (AccessToken)obj;
		}*/
	}

	/**
	 * 删除access_token
	 */
	public static synchronized void removeCache()
	{
		CacheUtils.remove(TOKEN_NAME, TOKEN_KEY);
	}
	
	/**
	 * 更新access_token至数据库
	 */
	public static void updateTokenToDB(Integer id,String token,int expiresIn,Date date)
	{
		//accessTokenDao.updateAccessToken(id, token, expiresIn, date);
	}
	
	/**
	 * 更新access_token至数据库
	 */
	public static void insertTokenToDB(String token,int expiresIn,Date date)
	{
		//accessTokenDao.insertAccessToken(token, expiresIn, date);
	}
}