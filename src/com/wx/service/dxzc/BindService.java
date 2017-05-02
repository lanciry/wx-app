package com.wx.service.dxzc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wx.common.constant.Constant;
import com.wx.dao.dxzc.BindDao;
import com.wx.model.dxzc.Customer;
import com.wx.model.dxzc.InviteCode;


/**
 * 用户绑定业务逻辑处理
 * @author meiiy
 * @version Apr 6, 2016
 */
//@Service
public class BindService 
{
	@Autowired
	private BindDao bindDao;
	@Autowired
	private CustomerService customerService;
	
	/**
	 * 获取用户信息
	 * @version Apr 7, 2016
	 * @param openid 用户微信openid
	 * @return 已注册绑定：Customer;未注册绑定：null
	 */
	public Customer getCustomer(String openid)
	{
		return customerService.getCustomerByopenid(openid);
	}
	
	/**
	 * 判断邀请码是否正确
	 * @version Apr 7, 2016
	 * @param inviteCode 邀请码
	 * @return 正确：true;错误：false
	 */
	public boolean validInviteCode(String code)
	{
		List<InviteCode> list = bindDao.getInviteCode(code);
		if (!list.isEmpty()) {
			InviteCode inviteCode =list.get(0);
			if (inviteCode != null) {
				return inviteCode.getStatus() != null && 
						inviteCode.getStatus() == Constant.INVITE_CODE_UNUSED;
			}
		}
		
		return false;
	}
	
	/**
	 * 保存注册绑定信息
	 * @version Apr 7, 2016
	 * @param cus
	 */
	@Transactional
	public void save(Customer customer)
	{
		//保存用户信息
		customerService.saveCustomer(customer);
		//修改邀请码状态
		InviteCode inviteCode = new InviteCode();
		inviteCode.setcId(customer.getId());
		inviteCode.setStatus(Constant.INVITE_CODE_USED);
		inviteCode.setCode(customer.getInviteCode());
		
		bindDao.updateInviteCode(inviteCode);
	}
	
	/**
	 * 验证是否已存在相同的用户名，手机号码
	 * @version Apr 14, 2016
	 * @param name
	 * @param phone
	 * @return 存在：true;不存在:false
	 */
	public boolean hasNameAndPhone(String name,String phone)
	{
		Customer c = customerService.getCustomer(name, phone);
		if (c != null) {
			return true;
		}
		
		return false;
	}
}
