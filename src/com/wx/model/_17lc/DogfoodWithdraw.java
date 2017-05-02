package com.wx.model._17lc;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.wx.model.base.BaseModel;

/**
 * 提现记录实体
 * @author meiiy
 * @version 2017年3月25日
 */
public class DogfoodWithdraw extends BaseModel implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    private BigDecimal amount;
    private String mobile;
    private String orderId;
    private Integer status;//提现状态 1-提现成功；0-提现失败（对方处理失败，系统错误等）
    private String message;
    
    
    private Date beginTime;//提现开始时间
    private Date endTime;//提现结束时间
    
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Date getBeginTime() {
        return beginTime;
    }
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }
    public Date getEndTime() {
        return endTime;
    }
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
