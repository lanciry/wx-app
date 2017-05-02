package com.wx.model.dxzc;

import java.io.Serializable;
import java.util.Date;

/**
 * 邀请码
 * @author wwl
 * @version Apr 7, 2016
 */
public class InviteCode implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id; /* 邀请码ID */
	private String code; /* 邀请码 */
	private Long cId; /* 客户ID */
	private Integer status; /* 状态：0-未使用，1-已使用 */
	private Date createTime; /* 创建时间 */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getcId() {
		return cId;
	}

	public void setcId(Long cId) {
		this.cId = cId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
