package com.crAdmin.bean;

import java.util.Date;

public class OperLog {
	private Integer id;

	private String type;

	private Integer operUserId;

	private Date operTime;
	// 车架号
	private String operContent;

	private Date createTime;

	private String isDelete;

	private String remark;
	
	private String params;
	
	private String returnVal;


	public Date getCreateTime() {
		return createTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getOperUserId() {
		return operUserId;
	}

	public void setOperUserId(Integer integer) {
		this.operUserId = integer;
	}

	public Date getOperTime() {
		return operTime;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

	public String getOperContent() {
		return operContent;
	}

	public void setOperContent(String operContent) {
		this.operContent = operContent;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getReturnVal() {
		return returnVal;
	}

	public void setReturnVal(String returnVal) {
		this.returnVal = returnVal;
	}

}