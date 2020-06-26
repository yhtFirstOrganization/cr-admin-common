package com.crAdmin.bean;

import java.util.Date;

public class PersonInfo {
	private Integer perId;

	private Integer perNum;

	private String perName;

	private String perTel;

	private String perCompanyName;

	private String perCity;
	private String perAddress;

	private String perEmail;
	private String elseTel;
	// 维修时间string
	private String perLevel;

	private String perType;

	private Date createTime;

	private Date updateTime;

	private String isdelete;
	private String companyId;
	private String bz;
	public Integer getPerId() {
		return perId;
	}
	public void setPerId(Integer perId) {
		this.perId = perId;
	}
	public Integer getPerNum() {
		return perNum;
	}
	public void setPerNum(Integer perNum) {
		this.perNum = perNum;
	}
	public String getPerName() {
		return perName;
	}
	public void setPerName(String perName) {
		this.perName = perName;
	}
	public String getPerTel() {
		return perTel;
	}
	public void setPerTel(String perTel) {
		this.perTel = perTel;
	}
	public String getPerCompanyName() {
		return perCompanyName;
	}
	public void setPerCompanyName(String perCompanyName) {
		this.perCompanyName = perCompanyName;
	}
	public String getPerAddress() {
		return perAddress;
	}
	public void setPerAddress(String perAddress) {
		this.perAddress = perAddress;
	}
	public String getPerEmail() {
		return perEmail;
	}
	public void setPerEmail(String perEmail) {
		this.perEmail = perEmail;
	}
	public String getElseTel() {
		return elseTel;
	}
	public void setElseTel(String elseTel) {
		this.elseTel = elseTel;
	}
	public String getPerLevel() {
		return perLevel;
	}
	public void setPerLevel(String perLevel) {
		this.perLevel = perLevel;
	}
	public String getPerType() {
		return perType;
	}
	public void setPerType(String perType) {
		this.perType = perType;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(String isdelete) {
		this.isdelete = isdelete;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getPerCity() {
		return perCity;
	}
	public void setPerCity(String perCity) {
		this.perCity = perCity;
	}


}