package com.wx.model.dxzc;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户订单
 * @author wwl
 * @version Apr 7, 2016
 */
public class CustomerOrder implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id; /* 订单Id */
	private String orderNo; /* 订单编号 */
	private Long cId; /* 客户Id */
	private Integer loanAmount; /* 贷款金额 */
	private Integer loanRate; /* 贷款利率 */
	private Integer loanTerm; /* 贷款期限 */
	private Integer auditAmount; /* 审核金额 */
	private Integer type; /* 抵押类型：1-一抵，2-二抵 */
	private Integer status; /* 审核类型：1-初审，2 -终审 */
	private Date xhDay; /* 下户日期 */
	private String xhTime; /* 下户时间段 */
	private Integer relationShip; /* 抵押人与贷款人关系：0-本人，1-非本人 */
	private Date createTime; /* 创建时间 */
	private String remarks; /* 备注 */
	
	private Long xhId;
	private Integer xhAmount;
	private Long firstAuditId;
	private String firstAuditRemarks;
	private Date firstAuditTime;
	private Long finalAuditId;
	private String finalAuditRemarks;
	private Date finalAuditTime;
	private Integer productType;
	private String borrower;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Long getcId() {
		return cId;
	}

	public void setcId(Long cId) {
		this.cId = cId;
	}

	public Integer getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Integer loanAmount) {
		this.loanAmount = loanAmount;
	}

	public Integer getLoanRate() {
		return loanRate;
	}

	public void setLoanRate(Integer loanRate) {
		this.loanRate = loanRate;
	}

	public Integer getLoanTerm() {
		return loanTerm;
	}

	public void setLoanTerm(Integer loanTerm) {
		this.loanTerm = loanTerm;
	}

	public Integer getAuditAmount() {
		return auditAmount;
	}

	public void setAuditAmount(Integer auditAmount) {
		this.auditAmount = auditAmount;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getXhDay() {
		return xhDay;
	}

	public void setXhDay(Date xhDay) {
		this.xhDay = xhDay;
	}

	public String getXhTime() {
		return xhTime;
	}

	public void setXhTime(String xhTime) {
		this.xhTime = xhTime;
	}

	public Integer getRelationShip() {
		return relationShip;
	}

	public void setRelationShip(Integer relationShip) {
		this.relationShip = relationShip;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getXhId() {
		return xhId;
	}

	public void setXhId(Long xhId) {
		this.xhId = xhId;
	}

	public Integer getXhAmount() {
		return xhAmount;
	}

	public void setXhAmount(Integer xhAmount) {
		this.xhAmount = xhAmount;
	}

	public Long getFirstAuditId() {
		return firstAuditId;
	}

	public void setFirstAuditId(Long firstAuditId) {
		this.firstAuditId = firstAuditId;
	}

	public String getFirstAuditRemarks() {
		return firstAuditRemarks;
	}

	public void setFirstAuditRemarks(String firstAuditRemarks) {
		this.firstAuditRemarks = firstAuditRemarks;
	}

	public Date getFirstAuditTime() {
		return firstAuditTime;
	}

	public void setFirstAuditTime(Date firstAuditTime) {
		this.firstAuditTime = firstAuditTime;
	}

	public Long getFinalAuditId() {
		return finalAuditId;
	}

	public void setFinalAuditId(Long finalAuditId) {
		this.finalAuditId = finalAuditId;
	}

	public String getFinalAuditRemarks() {
		return finalAuditRemarks;
	}

	public void setFinalAuditRemarks(String finalAuditRemarks) {
		this.finalAuditRemarks = finalAuditRemarks;
	}

	public Date getFinalAuditTime() {
		return finalAuditTime;
	}

	public void setFinalAuditTime(Date finalAuditTime) {
		this.finalAuditTime = finalAuditTime;
	}

	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	public String getBorrower() {
		return borrower;
	}

	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}
}
