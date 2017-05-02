package com.wx.service.dxzc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wx.common.constant.Constant;
import com.wx.dao.dxzc.CustomerDao;
import com.wx.model.dxzc.Customer;
/**
 * 用户相关信息业务逻辑处理
 * @author meiiy
 * @version Apr 7, 2016
 */
//@Service
public class CustomerService 
{
	@Autowired
	private CustomerDao customerDao;
	
	/**
	 * 根据openid获取用户信息
	 * @version Apr 6, 2016
	 * @param openid 用户微信openid
	 * @return 存在用户返回Customer，否则返回null
	 */
	public Customer getCustomerByopenid(String openid)
	{
		List<Customer> list = customerDao.getCustomerByopenid(openid);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 保存用户信息
	 * @version Apr 7, 2016
	 * @return 插入的主键
	 * @param cus 用户信息
	 */
	@Transactional
	public void saveCustomer(Customer cus)
	{
		customerDao.insert(cus);
	}
	
	/**
	 * 更新用户身份证号
	 * @param id 客户ID
	 * @param idCard 身份证号码
	 * @author meiiy
	 * @version 2016年4月10日
	 */
	public void updateCustomerIdCard(Long id,String idCard)
	{
		customerDao.updateCustomerIdCard(id,idCard);
	}
	
	/**
	 * 获取用户的openid
	 * @version Apr 12, 2016
	 * @param id 用户id
	 * @return 用户openid
	 */
	public String selectOpenidBycusId(Long id)
	{
		Customer c = customerDao.selectOne(id);
		if (c != null) {
			return c.getOpenid();
		}
		
		return null;
	}
	
	/**
	 * 根据姓名，手机号获取用户信息
	 * @version Apr 14, 2016
	 * @param openid 用户微信openid
	 * @return 存在用户返回Customer，否则返回null
	 */
	public Customer getCustomer(String name,String phone)
	{
		List<Customer> list = customerDao.getCustomer(name,phone);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 验证用户状态
	 * @param cId 用户ID
	 * @return 可用：true;不可用：false
	 * @author meiiy
	 * @version 2016年4月19日
	 */
	public boolean validCusStatus(Long cId)
	{
		Customer customer = customerDao.selectOne(cId);
		
		if (customer == null || customer.getStatus() == null || customer.getStatus() == Constant.CUS_STATUS_DISABLED) {
			return false;
		}
		
		return true;
	}
}
