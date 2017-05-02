package com.wx.service.dxzc;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.wx.common.constant.Constant;
import com.wx.dao.dxzc.CustomerOrderDao;
import com.wx.model.dxzc.CustomerOrder;
import com.wx.model.dxzc.CustomerPhoto;

/**
 * 订单业务逻辑处理
 * @author meiiy
 * @version 2016年4月9日
 */
//@Service
public class CustomerOrderService
{
	@Autowired
	private CustomerOrderDao customerOrderDao;
	
	/**
	 * 保存订单相关信息
	 * @param order 订单信息
	 * @param idCard 身份证号
	 * @param photoNames 房产证照片，身份证照片名称
	 * @author meiiy
	 * @version 2016年4月10日
	 */
	public void saveOrder(CustomerOrder order,String photoNames)
	{
		//保存借款信息
		customerOrderDao.insertOrder(order);
		//更新照片信息订单号
		customerOrderDao.updatePhoto(order.getcId(),photoNames,order.getId());
	}
	/**
	 * 保存图片信息
	 * @param photo 图片对象信息
	 * @param orderNo 订单编号
	 * @author meiiy
	 * @version 2016年4月9日
	 */
	@Transactional
	public void savePhoto(CustomerPhoto photo)
	{
		customerOrderDao.insertPoto(photo);
	}
	
	/**
	 * 删除单张图片
	 * @param photoId 图片表id
	 * @author meiiy
	 * @version 2016年4月10日
	 */
	@Transactional
	public void deletePhoto(String photoId)
	{
		customerOrderDao.deletePhoto(Long.valueOf(photoId));
	}
	
	/**
	 * 查询订单总数
	 * @version Apr 11, 2016
	 * @param cId 用户ID
	 * @return 所有订单总数
	 */
	public int getOrderNum(Long cId)
	{
		return customerOrderDao.getOrderNum(cId);
	}
	
	/**
	 * 查询用户所有订单信息
	 * @version Apr 11, 2016
	 * @param cId 用户ID
	 * @return 所有订单信息
	 */
	public List<CustomerOrder> getMyOrders(Long cId)
	{
		return customerOrderDao.getMyOrders(cId);
	}
	
	/**
	 * 查询用户是否有申请中的订单
	 * @version Apr 11, 2016
	 * @param cId 用户ID
	 * @param 订单状态数组
	 * @return 存在：true,不存在:false
	 */
	public boolean hasApply(Long cId,int arr[])
	{
		int count = customerOrderDao.countOrderStatusNotIn(cId,arr);
		if (count >0) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * 获取单个订单信息
	 * @version Apr 11, 2016
	 * @param id 订单编号
	 * @return
	 */
	public CustomerOrder getOne(Long id)
	{
		return customerOrderDao.getOne(id);
	}
	
	/**
	 * 取消单个订单
	 * @version Apr 12, 2016
	 * @param id 订单ID
	 * @param cId 用户ID
	 * @param reason 取消原因
	 */
	@Transactional
	public void cancel(Long id,Long cId,String reason)
	{
		customerOrderDao.cancel(id,cId,Constant.AUDIT_STATUS_CANCELLED,reason);
	}
	
	/**
	 * 保存下户申请信息
	 * @version Apr 12, 2016
	 * @param id 订单ID
	 * @param xhDate 下户日期
	 * @param xhTime 下户时间
	 * @return 影响的数据条数
	 */
	@Transactional
	public int saveXH(Long id,Date xhDate,String xhTime)
	{
		Map<String,Object> map = Maps.newHashMap();
		map.put("id", id);
		map.put("xhDate", xhDate);
		map.put("xhTime", xhTime);
		map.put("status", Constant.AUDIT_STATUS_XH);
		map.put("priStatus", Constant.AUDIT_STATUS_FIRST_YES);//初审通过才能申请
		return customerOrderDao.saveXH(map);
	}
	
	/**
	 * 获取指定时间最后一个订单的订单号
	 * @version Apr 18, 2016
	 * @param currentDate 时间
	 * @return
	 */
	public String queryLastOrderNumber(Date currentDate)
	{
		return customerOrderDao.queryLastOrderNumber(DateFormatUtils.format(currentDate, "yyyy-MM-dd"));
	}
}
