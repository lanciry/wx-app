package com.wx.model._17lc;

import java.io.Serializable;
import java.util.Date;

import com.wx.model.base.BaseModel;

/**
 * 狗粮数据实体
 * @author meiiy
 * @version 2017年3月24日
 */
public class DogfoodProducing extends BaseModel implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    private Integer number;//狗粮数量
    private Integer isConvert;//是否兑换。0-未兑换，1-已兑换
    
    private Date beginTime;//刷狗粮开始时间
    private Date endTime;//刷狗粮结束时间
    private Integer countForDay;//刷狗粮的天数
    private Date lastDate;//最后一次刷狗粮的日期（年月日）
    
    public Integer getNumber() {
        return number;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }
    public Integer getIsConvert() {
        return isConvert;
    }
    public void setIsConvert(Integer isConvert) {
        this.isConvert = isConvert;
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
    public Integer getCountForDay() {
        return countForDay;
    }
    public void setCountForDay(Integer countForDay) {
        this.countForDay = countForDay;
    }
    public Date getLastDate() {
        return lastDate;
    }
    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }
}
