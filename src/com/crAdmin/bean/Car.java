package com.crAdmin.bean;

import java.util.Date;

public class Car {
	private Integer carId;

	private String carType;

	private String carChassisNum;

	private String licensePlateNum;
	// 车架号
	private String carFrameNum;

	private String engineNum;

	private String serviceAdviser;

	private String ownerName;

	private String ownerSex;

	private String ownerCity;

	private String ownerAddress;
	// 联系人
	private String linker;
	// 联系电话
	private String linkerMobile;

	private String ownerFax;

	private String ownerNum;

	private Date createTime;

	private String isdelete;
	private String companyId;

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType == null ? null : carType.trim();
	}

	public String getCarChassisNum() {
		return carChassisNum;
	}

	public void setCarChassisNum(String carChassisNum) {
		this.carChassisNum = carChassisNum == null ? null : carChassisNum
				.trim();
	}

	public String getLicensePlateNum() {
		return licensePlateNum;
	}

	public void setLicensePlateNum(String licensePlateNum) {
		this.licensePlateNum = licensePlateNum == null ? null : licensePlateNum
				.trim();
	}

	public String getEngineNum() {
		return engineNum;
	}

	public void setEngineNum(String engineNum) {
		this.engineNum = engineNum == null ? null : engineNum.trim();
	}

	public String getServiceAdviser() {
		return serviceAdviser;
	}

	public void setServiceAdviser(String serviceAdviser) {
		this.serviceAdviser = serviceAdviser == null ? null : serviceAdviser
				.trim();
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName == null ? null : ownerName.trim();
	}

	public String getOwnerSex() {
		return ownerSex;
	}

	public void setOwnerSex(String ownerSex) {
		this.ownerSex = ownerSex == null ? null : ownerSex.trim();
	}

	public String getOwnerCity() {
		return ownerCity;
	}

	public void setOwnerCity(String ownerCity) {
		this.ownerCity = ownerCity == null ? null : ownerCity.trim();
	}

	public String getOwnerAddress() {
		return ownerAddress;
	}

	public void setOwnerAddress(String ownerAddress) {
		this.ownerAddress = ownerAddress == null ? null : ownerAddress.trim();
	}

	public String getOwnerFax() {
		return ownerFax;
	}

	public void setOwnerFax(String ownerFax) {
		this.ownerFax = ownerFax == null ? null : ownerFax.trim();
	}

	public String getOwnerNum() {
		return ownerNum;
	}

	public void setOwnerNum(String ownerNum) {
		this.ownerNum = ownerNum == null ? null : ownerNum.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(String isdelete) {
		this.isdelete = isdelete == null ? null : isdelete.trim();
	}

	public String getCarFrameNum() {
		return carFrameNum;
	}

	public void setCarFrameNum(String carFrameNum) {
		this.carFrameNum = carFrameNum;
	}

	public String getLinker() {
		return linker;
	}

	public void setLinker(String linker) {
		this.linker = linker;
	}

	public String getLinkerMobile() {
		return linkerMobile;
	}

	public void setLinkerMobile(String linkerMobile) {
		this.linkerMobile = linkerMobile;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

}