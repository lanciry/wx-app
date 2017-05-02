package com.wx.dao.dxzc;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.wx.dao.base.BaseDao;
import com.wx.model.dxzc.CustomerOrder;
import com.wx.model.dxzc.CustomerPhoto;

/**
 * 订单数据库操作
 * @author meiiy
 * @version 2016年4月9日
 */
//@Repository
public class CustomerOrderDao extends BaseDao
{
	/**
	 * 插入订单信息
	 * @param order 订单对象
	 * @author meiiy
	 * @version 2016年4月9日
	 */
	public void insertOrder(CustomerOrder order)
	{
		sqlSession.insert("customerOrder.insert", order);
	}
	
	/**
	 * 插入订单照片信息
	 * @param order 照片对象
	 * @author meiiy
	 * @version 2016年4月9日
	 */
	public void insertPoto(CustomerPhoto photo)
	{
		sqlSession.insert("customerPhoto.insert", photo);
	}
	
	/**
	 * 删除订单单张照片
	 * @param photoId
	 * @author meiiy
	 * @version 2016年4月10日
	 */
	public void deletePhoto(Long photoId)
	{
		sqlSession.delete("customerPhoto.delete",photoId);
	}
	
	/**
	 * 更新照片信息中的订单号
	 * @param cId 客户id
	 * @param photoNames 照片名称
	 * @param oId 订单号
	 * @author meiiy
	 * @version 2016年4月10日
	 */
	public void updatePhoto(Long cId,String photoNames,Long oId)
	{
		Map<String,Object> map = Maps.newHashMap();
		map.put("cId", cId);
		map.put("photoNames", photoNames);
		map.put("oId", oId);
		
		sqlSession.update("customerPhoto.update", map);
	}
	
	/**
	 * 查询不在指定的状态的订单总数
	 * @version Apr 11, 2016
	 * @param cId 用户ID
	 * @param arr 状态数组
	 * @return
	 */
	public int countOrderStatusNotIn(Long cId,int arr[])
	{
		Map<String,Object> map = Maps.newHashMap();
		map.put("cId", cId);
		String s = Arrays.toString(arr);
		map.put("status", s.substring(1, s.length()-1));
		
		return sqlSession.selectOne("customerOrder.countOrderStatusNotIn",map);
	}
	
	/**
	 * 查询用户的订单总数
	 * @version Apr 11, 2016
	 * @param cId 用户ID
	 * @return
	 */
	public int getOrderNum(Long cId)
	{
		return sqlSession.selectOne("customerOrder.countOrders",cId);
	}
	
	/**
	 * 查询用户的所有订单信息
	 * @version Apr 11, 2016
	 * @param cId 用户ID
	 * @return
	 */
	public List<CustomerOrder> getMyOrders(Long cId)
	{
		return sqlSession.selectList("customerOrder.select",cId);
	}
	
	/**
	 * 获取单个订单信息
	 * @version Apr 11, 2016
	 * @param id 订单编号
	 * @return
	 */
	public CustomerOrder getOne(Long id)
	{
		return sqlSession.selectOne("customerOrder.selectOne",id);
	}
	
	/**
	 * 取消单个订单
	 * @version Apr 12, 2016
	 * @param id 订单ID
	 * @param cId 用户ID
	 */
	public void cancel(Long id,Long cId,Integer status,String reason)
	{
		Map<String,Object> map = Maps.newHashMap();
		map.put("cId", cId);
		map.put("id", id);
		map.put("status", status);
		map.put("reason", reason);
		
		sqlSession.update("customerOrder.cancel", map);
	}
	
	/**
	 * 保存下户信息
	 * @version Apr 12, 2016
	 * @param map
	 */
	public int saveXH(Map<String,Object> map)
	{
		return sqlSession.update("customerOrder.saveXH", map);
	}
	
	/**
	 * 获取指定时间最后一个订单的订单号
	 * @version Apr 18, 2016
	 * @param currentDate 时间
	 * @return
	 */
	public String queryLastOrderNumber(String currentDate)
	{
		return sqlSession.selectOne("customerOrder.queryLastOrderNumber",currentDate);
	}
	
}
