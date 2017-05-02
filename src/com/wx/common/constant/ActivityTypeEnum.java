package com.wx.common.constant;

/**
 * 微信活动类型
 * @author meiiy
 * @version 2017年3月24日
 */
public enum ActivityTypeEnum 
{
    DOGFOOD("刷狗粮活动");
    
    private final String key;

    private ActivityTypeEnum(String key) {
        this.key = key;
    }
    
    public String getKey() {
        return key;
    }
}
