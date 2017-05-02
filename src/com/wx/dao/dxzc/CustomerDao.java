package com.wx.dao.dxzc;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.wx.dao.base.BaseDao;
import com.wx.model.dxzc.Customer;

/**
 * 用户相关信息数据库操作
 * @author meiiy
 * @version Apr 7, 2016
 */
//@Repository
public class CustomerDao extends BaseDao
{
	/**
	 * 根据openid获取用户信息
	 * @version Apr 6, 2016
	 * @param openid 用户微信openid
	 * @return
	 */
	public List<Customer> getCustomerByopenid(String openid)
	{
		return sqlSession.selectList("customer.select",openid);
	}
	
	/**
	 * 保存用户信息
	 * @version Apr 7, 2016
	 * @param cus 用户信息
	 */
	public void insert(Customer cus)
	{
		sqlSession.insert("customer.insert", cus);
	}
	
	/**
	 * 如果用户身份证号码为空，更新用户身份证号
	 * @param id 用户ID
	 * @param idCard 身份证号
	 * @author meiiy
	 * @version 2016年4月10日
	 */
	public void updateCustomerIdCard(Long id,String idCard)
	{
		Map<String,Object> map = Maps.newHashMap();
		map.put("id", id);
		map.put("idCard", idCard);
		
		sqlSession.update("customer.updateIdCard", map);
	}
	
	/**
	 * 获取单个用户信息
	 * @version Apr 12, 2016
	 * @param id 用户id
	 * @return
	 */
	public Customer selectOne(Long id)
	{
		return sqlSession.selectOne("customer.selectOne",id);
	}
	
	/**
	 * 根据openid获取用户信息
	 * @version Apr 6, 2016
	 * @param openid 用户微信openid
	 * @return
	 */
	public List<Customer> getCustomer(String name,String phone)
	{
		Map<String,Object> map = Maps.newHashMap();
		map.put("name", name);
		map.put("phone", phone);
		
		return sqlSession.selectList("customer.selectByNameAndPhone",map);
	}
}
