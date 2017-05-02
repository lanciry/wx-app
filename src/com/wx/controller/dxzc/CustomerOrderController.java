package com.wx.controller.dxzc;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wx.common.constant.Constant;
import com.wx.service.dxzc.CustomerOrderService;
import com.wx.service.dxzc.CustomerService;
import com.wx.model.dxzc.Customer;
import com.wx.model.dxzc.CustomerOrder;
import com.wx.model.dxzc.CustomerPhoto;
import com.wx.util.OrderNumberUtil;
import com.wx.util.PictureUtil;
import com.wx.util.WeixinUtil;

/**
 * 订单Controller
 * @author wwl
 *
 */
//@Controller
//@RequestMapping("/order")
public class CustomerOrderController {

	private static Logger logger = Logger.getLogger(CustomerOrderController.class);
	
	@Autowired
	private CustomerOrderService customerOrderService;
	@Autowired
	private CustomerService customerService;
	
	/**
	 * 进入产品选择页
	 * @return
	 * @author meiiy
	 * @version 2016年4月9日
	 */
	@RequestMapping(value="/applyOrder")
	public String toSelectProduct()
	{
		logger.info(">>>>>>>>>>>>>>>申请订单第一步，选择产品");
		//此处业务逻辑处理
		
		return "order/product";
	}
	/**
	 * 进入贷款信息填写页
	 * @param productType 产品类型
	 * @param model
	 * @return
	 * @author meiiy
	 * @version 2016年4月9日
	 */
	@RequestMapping(value="/loan")
	public String toLoan(@RequestParam String productType,Model model)
	{
		logger.info(">>>>>>>>>>>>>>>选择的产品类型为：productType="+productType);
		if (StringUtils.isNotEmpty(productType)) {
			model.addAttribute("list", getLoanTerm(productType));
			model.addAttribute("dateDir", DateFormatUtils.format(new Date(), "yyyyMMdd"));//设置保存的文件夹
			model.addAttribute("productType", productType);
			//以下为页面使用微信JS-SDK而生成必须参数值
			model.addAttribute("appid", Constant.WX_APPID);
			String jsapi_ticket = WeixinUtil.getJsapiTicket();
			String nonceStr = WeixinUtil.create_nonce_str();
			String timestamp = WeixinUtil.create_timestamp();
			String url = Constant.WX_HOST +"/order/loan";
			String signature = WeixinUtil.getSign(jsapi_ticket, nonceStr, timestamp, url);
			model.addAttribute("nonceStr", nonceStr);
			model.addAttribute("timestamp", timestamp);
			model.addAttribute("signature", signature);
			
			return "order/loan";
		}
		return "redirect:/error";
	}
	/**
	 * 根据所选择产品生成贷款期限
	 * @param productType 产品类型,1-短期，2-循环
	 * @return
	 * @author meiiy
	 * @version 2016年4月18日
	 */
	private List<String []> getLoanTerm(String productType)
	{
		List<String []> list = Lists.newArrayList();
		String arr[] = null;
		//短期贷是1-12个月，循环贷是36个月，房易贷是7-36个月
		if ("1".equals(productType)) {
			for (int i=1;i<=6;i++) {
				arr = new String [] {String.valueOf(i),String.valueOf(i)+" 个月"};
				list.add(arr);
			}
		} else if ("2".equals(productType)) {
			arr = new String [] {"36","36 个月"};
			list.add(arr);
		} else if ("3".equals(productType)) {
			for (int i=7;i<=36;i++) {
				arr = new String [] {String.valueOf(i),String.valueOf(i)+" 个月"};
				list.add(arr);
			}
		}
		
		return list;
	}
			
	
	/**
	 * 上传房产证，身份证照片
	 * @param request
	 * @return
	 * @author meiiy
	 * @version 2016年4月9日
	 */
	@RequestMapping(value="/uploadPhoto")
	@ResponseBody
	public String uploadPhoto(HttpServletRequest request)
	{
		Map<String,String> map = Maps.newHashMap();
		map.put("status", "Y");
		
		SimpleDateFormat simpleFormat = new SimpleDateFormat("MMddHHmmssSSS");
		//生成随机文件名
		String generationfileName = simpleFormat.format(new Date())+ new Random().nextInt(1000);
		//照片类型
		String type = request.getParameter("type");
		//文件夹名称
		String dateDir = request.getParameter("dateDir");
		//订单编号
		String serverId = request.getParameter("serverId");
		//保存路径
		String savePath=Constant.FILE_BASE_DIR+ dateDir;
		
		File file = new File(savePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		
		String fileNameSuffix = ".jpg";//后缀名
		String fileName = generationfileName+fileNameSuffix;//文件名
		Long photoId = null;
		try {
			InputStream in = WeixinUtil.getMedia(serverId);
			PictureUtil.saveFileFromInputStream(in,savePath,fileName);
			
			//保存图片信息
			CustomerPhoto photo = new CustomerPhoto();
			Customer customer = (Customer)request.getSession().getAttribute("customer");
			photo.setcId(customer.getId());
			photo.setPhotoDirectory(savePath);
			photo.setPhotoName( generationfileName);
			photo.setSuffix(fileNameSuffix);
			photo.setType(Integer.valueOf(type));
			
			customerOrderService.savePhoto(photo);
			photoId = photo.getId();
		} catch (Exception e) {
			logger.error(">>>>>>>>>>>>>>保存图片出错:"+e.getMessage(),e);
			map.put("status", "N");
		}
		
		map.put("photoPath", savePath+File.separator+fileName);
		map.put("photoId", String.valueOf(photoId));
		map.put("photoName", generationfileName);
		
		JSONObject json = JSONObject.fromObject(map);
		return json.toString();
	}
	
	/**
	 * 单张图片删除
	 * @return
	 * @author meiiy
	 * @version 2016年4月10日
	 */
	@RequestMapping(value="/deletePhoto")
	@ResponseBody
	public String deletePhoto(@RequestParam String photoId)
	{
		Map<String,String> map = Maps.newHashMap();
		map.put("status", "Y");
		logger.info(">>>>>>>>>>>>删除图片photoId="+photoId);
		if (StringUtils.isBlank(photoId)) {
			map.put("status", "N");
		} else {
			try {
				customerOrderService.deletePhoto(photoId);
			} catch (Exception e) {
				logger.error(">>>>>>>>>>>>>>删除图片出错:"+e.getMessage(),e);
				map.put("status", "N");
			}
		}
		
		JSONObject json = JSONObject.fromObject(map);
		return json.toString();
	}
	
	/**
	 * 保存订单信息
	 * @return
	 * @author meiiy
	 * @version 2016年4月10日
	 */
	@RequestMapping(value="/save")
	@ResponseBody
	public synchronized String save(HttpServletRequest request)
	{
		Map<String,String> map = Maps.newHashMap();
		map.put("status", "Y");
		
		try {
			Customer customer = (Customer)request.getSession().getAttribute("customer");
			//判断用户是否已被禁用
			if (!customerService.validCusStatus(customer.getId())) {
				map.put("status", "N");
				map.put("msg", "您的账号已被禁用，不能提交订单");
			} else {
				//获取订单号
				Date date = new Date();
				String lastOrderNo = customerOrderService.queryLastOrderNumber(date);
				String orderNo = OrderNumberUtil.getOrderNumber(lastOrderNo,date);
				
				String productType = (String)request.getParameter("productType");
				String borrower = (String)request.getParameter("borrower");
				String loanAmount = (String)request.getParameter("loanAmount");
				String loanTerm = (String)request.getParameter("loanTerm");
				String housePhotoNames = (String)request.getParameter("housePhotoNames");
				String houseType = (String)request.getParameter("houseType");
				//String idcardPhotoNames = (String)request.getParameter("idcardPhotoNames");
				
				CustomerOrder order = new CustomerOrder();
				order.setOrderNo(orderNo);//订单号
				order.setProductType(Integer.valueOf(productType));
				order.setcId(customer.getId());//用户id
				order.setBorrower(borrower);//贷款人姓名
				order.setLoanAmount(Integer.valueOf(loanAmount));//贷款金额
				order.setLoanTerm(Integer.valueOf(loanTerm));//贷款期限
				order.setType(Integer.valueOf(houseType));//抵押顺位
				order.setStatus(Constant.AUDIT_STATUS_NOT);//待审核
				
				//保存
				customerOrderService.saveOrder(order,housePhotoNames);
				
				map.put("orderNo", orderNo);
			}
		} catch (Exception e) {
			map.put("status", "N");
			map.put("msg", "提交订单失败，请联系客服");
			logger.error(">>>>>>>>>>>>>>保存订单信息出错:"+e.getMessage(),e);
		}
		
		JSONObject json = JSONObject.fromObject(map);
		return json.toString();
	}
	
	/**
	 * 保存订单信息成功
	 * @return
	 * @author meiiy
	 * @version 2016年4月10日
	 */
	@RequestMapping(value="/success")
	public String success(HttpServletRequest request,Model model)
	{
		String orderNo = (String)request.getParameter("orderNo");
		model.addAttribute("orderNo", orderNo);
		
		return "order/success";
	}
	/**
	 * 我的订单
	 * @param request
	 * @param model
	 * @return
	 * String
	 */
	@RequestMapping(value="/myOrders")
	public String getMyOrders(HttpServletRequest request,Model model)
	{
		Customer customer = (Customer) request.getSession().getAttribute("customer");
		
		List<CustomerOrder> list = customerOrderService.getMyOrders(customer.getId());
		model.addAttribute("list", list);
		return "dx/myOrders";
	}
	
	/**
	 * 单个订单详情
	 * @version Apr 11, 2016
	 * @return
	 */
	@RequestMapping(value="/detail")
	public String detail(HttpServletRequest request)
	{
		Customer customer = (Customer) request.getSession().getAttribute("customer");
		//订单编号
		String id = (String)request.getParameter("id");
		
		CustomerOrder order = customerOrderService.getOne(Long.valueOf(id));
		if (order != null) {
			if (order.getcId().compareTo(customer.getId()) != 0) {
				order = null;
			}
		}
		request.setAttribute("order", order);
		
		return "order/orderDetail";
	}
	
	/**
	 * 取消订单
	 * @version Apr 11, 2016
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/cancel")
	@ResponseBody
	public String cancel(HttpServletRequest request)
	{
		Customer customer = (Customer) request.getSession().getAttribute("customer");
		//订单编号
		String id = (String)request.getParameter("id");
		//取消原因
		String reason = (String)request.getParameter("reason");
		
		Map<String,String> map = Maps.newHashMap();
		map.put("status", "Y");
		
		try {
			customerOrderService.cancel(Long.valueOf(id),customer.getId(),reason);
		} catch (Exception e) {
			map.put("status", "N");
			map.put("msg", "取消订单失败，请联系客服");
			logger.error(">>>>>>>>>>>>>>取消订单出错:"+e.getMessage(),e);
		}
		
		JSONObject json = JSONObject.fromObject(map);
		return json.toString();
	}
	
	/**
	 * 申请下户
	 * @version Apr 12, 2016
	 * @param id 订单ID
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/applyXH")
	public String applyXH(@RequestParam String id,Model model)
	{
		logger.error(">>>>>>>>>>>>>>申请下户:id="+id);
		model.addAttribute("id", id);
		
		return "order/applyXH";
	}
	
	/**
	 * 保存下户申请信息
	 * @version Apr 12, 2016
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/saveXH")
	@ResponseBody
	public String saveXH(HttpServletRequest request)
	{
		Map<String,String> map = Maps.newHashMap();
		map.put("status", "Y");
		
		Customer customer = (Customer)request.getSession().getAttribute("customer");
		//验证用户账号是否可用
		if (!customerService.validCusStatus(customer.getId())) {
			map.put("status", "N");
			map.put("msg", "您的账号已被禁用，不能申请下户");
		} else {
			//订单编号
			String id = (String)request.getParameter("id");
			//下户日期
			String xhDate = (String)request.getParameter("xhDate");
			//下户时间
			String xhTime = (String)request.getParameter("xhTime");
			
			try {
				int num = customerOrderService.saveXH(Long.valueOf(id),DateUtils.parseDate(xhDate, "yyyy-MM-dd"),xhTime);
				if (num<1) {
					map.put("status", "N");
					map.put("msg", "不满足申请下户的条件");
				}
			} catch (Exception e) {
				map.put("status", "N");
				map.put("msg", "保存下户申请信息失败，请联系客服");
				logger.error(">>>>>>>>>>>>>>保存下户申请信息出错:"+e.getMessage(),e);
			}
		}
		
		JSONObject json = JSONObject.fromObject(map);
		return json.toString();
	}
}
