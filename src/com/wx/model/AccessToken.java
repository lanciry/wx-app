package com.wx.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 微信全局token
 * @author meiiy
 * @version Apr 4, 2016
 */
public class AccessToken implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	// 获取到的凭证
	private String token;
	// 凭证有效时间，单位：秒
	private Integer expiresIn;
	//凭证获得事件
	private Date date;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}