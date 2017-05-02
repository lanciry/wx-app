package com.wx.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.wx.dao.base.BaseDao;
import com.wx.model.AccessToken;

/**
 * 全局accessToken数据库操作
 * @author meiiy
 * @version Apr 4, 2016
 */
@Repository
public class AccessTokenDao extends BaseDao
{
	/**
	 * 获取token
	 * @return
	 */
	public AccessToken getAccessToken()
	{
		List<AccessToken> list =  sqlSession.selectList("accessToken.select");
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 保存token
	 * @param token 凭证字符串
	 * @param expiresIn 有效时长
	 * @param date 获取token的时间
	 * @return
	 */
	public int insertAccessToken(String token,int expiresIn,Date date)
	{
		Map<String,Object> map = Maps.newHashMap();
		map.put("token", token);
		map.put("expiresIn", expiresIn);
		map.put("date", date);
		return sqlSession.insert("accessToken.insert", map);
	}
	
	/**
	 * 更新token
	 * @param id
	 * @return
	 */
	public int updateAccessToken(Integer id,String token,int expiresIn,Date date)
	{
		Map<String,Object> map = Maps.newHashMap();
		map.put("id", id);
		map.put("token", token);
		map.put("expiresIn", expiresIn);
		map.put("date", date);
		return sqlSession.update("accessToken.update", map);
	}
}
