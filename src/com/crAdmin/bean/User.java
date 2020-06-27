package com.crAdmin.bean;

import java.util.Date;

public class User {
	private Integer userId;// 自增id
	private String loginName;// 登录名
	private String userName;// 用户名称

	private String password;// 密码

	private String otherPassword;// 其他密码

	private String newPassword;// 密码

	private String newOtherPassword;// 其他密码

	private Date createTime;// 创建用户时间

	private Date updateTime;// 修改时间

	private String isDelete;// 是否删除 0：正常；1：删除
	private String remark;// 备注
	private String companyId;// 备注
	private String addr;// 地址
	private String tel;// 电话

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getOtherPassword() {
		return otherPassword;
	}

	public void setOtherPassword(String otherPassword) {
		this.otherPassword = otherPassword;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewOtherPassword() {
		return newOtherPassword;
	}

	public void setNewOtherPassword(String newOtherPassword) {
		this.newOtherPassword = newOtherPassword;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}