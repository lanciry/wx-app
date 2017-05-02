package com.wx.dao.dxzc;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wx.dao.base.BaseDao;
import com.wx.model.dxzc.InviteCode;

/**
 * 用户绑定信息数据库操作
 * @author meiiy
 * @version Apr 6, 2016
 */
//@Repository
public class BindDao extends BaseDao
{
	/**
	 * 根据邀请码获取该邀请码相关信息
	 * @version Apr 7, 2016
	 * @param inviteCode 邀请码
	 * @return
	 */
	public List<InviteCode> getInviteCode(String code)
	{
		return sqlSession.selectList("inviteCode.select",code);
	}
	
	/**
	 * 更新邀请码表信息
	 * @version Apr 7, 2016
	 * @param inviteCode
	 */
	public void updateInviteCode(InviteCode inviteCode)
	{
		sqlSession.update("inviteCode.update", inviteCode);
	}
}
