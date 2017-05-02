package com.wx.model._17lc;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.wx.model.base.BaseModel;

/**
 * 狗粮兑换，提现数据实体
 * @author meiiy
 * @version 2017年3月24日
 */
public class DogfoodConversion extends BaseModel implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    private BigDecimal redAmount;
    private Integer isCash;
    private Date cashTime;
    
    public BigDecimal getRedAmount() {
        return redAmount;
    }
    public void setRedAmount(BigDecimal redAmount) {
        this.redAmount = redAmount;
    }
    public Integer getIsCash() {
        return isCash;
    }
    public void setIsCash(Integer isCash) {
        this.isCash = isCash;
    }
    public Date getCashTime() {
        return cashTime;
    }
    public void setCashTime(Date cashTime) {
        this.cashTime = cashTime;
    }
}
