package com.wx.common.constant;

/**
 * 微信互动点击事件类型
 * @author meiiy
 * @version 2017年3月24日
 */
public enum ActionTypeEnum
{
    DOGFOOD_INTO_SHAREPAGE("刷狗粮活动,进入分享页"),
    DOGFOOD_CLICK_SHAREPAGE_BTN("刷狗粮活动,点击分享页开刷按钮");
    
    private final String key;

    private ActionTypeEnum(String key) {
        this.key = key;
    }
    
    public String getKey() {
        return key;
    }
}
