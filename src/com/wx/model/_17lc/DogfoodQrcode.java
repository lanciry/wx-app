package com.wx.model._17lc;

import java.io.Serializable;

import com.wx.model.base.BaseModel;

public class DogfoodQrcode extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String openid;
	
	private String ticket;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	
	
}
