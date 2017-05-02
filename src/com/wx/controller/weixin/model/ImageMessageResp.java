package com.wx.controller.weixin.model;
/**
 * 回复图片类型消息
 * @author meiiy
 * @version Apr 4, 2016
 */
public class ImageMessageResp extends BaseMessageResp
{
	
	private Image Image;

	public Image getImage() {
		return Image;
	}

	public void setImage(Image image) {
		Image = image;
	}

}
