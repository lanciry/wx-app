package com.wx.model.statistics;

import java.io.Serializable;
import java.util.Date;

import com.wx.common.constant.ActivityTypeEnum;
import com.wx.model.base.BaseModel;

/**
 * 微信活动分享统计
 * @author meiiy
 * @version 2017年3月24日
 */
public class WechatShareStatistics extends BaseModel implements Serializable 
{
    private static final long serialVersionUID = 1L;
    
    private ActivityTypeEnum activityType;
    
    public WechatShareStatistics() {
        
    }
    
    public WechatShareStatistics (String openid,ActivityTypeEnum activityType,Date createTime) {
        this.openid = openid;
        this.activityType = activityType;
        this.createTime = createTime;
    }

    public ActivityTypeEnum getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityTypeEnum activityType) {
        this.activityType = activityType;
    }
}
