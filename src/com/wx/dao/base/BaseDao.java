package com.wx.dao.base;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

/**
 * DAO基类
 * @author meiiy
 * @version Apr 3, 2016
 */
public class BaseDao 
{
	@Resource(name="sqlSession")
	public SqlSessionTemplate sqlSession;
}
