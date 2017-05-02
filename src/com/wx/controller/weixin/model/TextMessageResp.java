package com.wx.controller.weixin.model;

/**
 * 回复文本类型消息
 * @author meiiy
 * @version Apr 4, 2016
 */
public class TextMessageResp extends BaseMessageResp
{
	// 回复的消息内容
    private String Content;
    public String getContent() {
            return Content;
    }
    public void setContent(String content) {
            Content = content;
    }
}
