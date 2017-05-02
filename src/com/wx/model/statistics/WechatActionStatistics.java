package com.wx.model.statistics;

import java.io.Serializable;
import java.util.Date;

import com.wx.common.constant.ActionTypeEnum;
import com.wx.common.constant.ActivityTypeEnum;
import com.wx.model.base.BaseModel;

/**
 * 微信活动响应统计
 * @author meiiy
 * @version 2017年3月24日
 */
public class WechatActionStatistics extends BaseModel implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    private ActivityTypeEnum activityType;
    private ActionTypeEnum eventType;
    
    public WechatActionStatistics() {
        
    }
    
    public WechatActionStatistics (String openid,ActivityTypeEnum activityType,ActionTypeEnum eventType,Date createTime) {
        this.openid = openid;
        this.activityType = activityType;
        this.eventType = eventType;
        this.createTime = createTime;
    }

    public ActivityTypeEnum getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityTypeEnum activityType) {
        this.activityType = activityType;
    }

    public ActionTypeEnum getEventType() {
        return eventType;
    }

    public void setEventType(ActionTypeEnum eventType) {
        this.eventType = eventType;
    }
}
