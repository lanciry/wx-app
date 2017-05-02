package com.wx.model.dxzc;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户图片附件
 * @author wwl
 * @version Apr 7, 2016
 */
public class CustomerPhoto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id; /* 图片Id */
	private Long cId; /* 客户Id */
	private Long oId; /* 订单Id */
	private String photoName; /* 图片名称 uuid */
	private String suffix; /* 图片后缀 */
	private String photoDirectory; /* 图片路径 */
	private Integer type; /* 图片类型：1-身份证照片，2-房产证照片 */
	private Date createTime; /* 上传时间 */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getcId() {
		return cId;
	}

	public void setcId(Long cId) {
		this.cId = cId;
	}

	public Long getoId() {
		return oId;
	}

	public void setoId(Long oId) {
		this.oId = oId;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getPhotoDirectory() {
		return photoDirectory;
	}

	public void setPhotoDirectory(String photoDirectory) {
		this.photoDirectory = photoDirectory;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
