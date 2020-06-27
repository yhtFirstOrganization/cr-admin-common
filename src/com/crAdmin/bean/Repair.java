package com.crAdmin.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Repair {
	private Integer repairId;

	private Integer carId;

	private String repairNum;

	private String repairName;

	private String serviceAdviser;

	private String repairMan;

	private Date repairTime;
	private Date updateTime;
	// 维修时间string
	private String repairTimeStr;

	private String repairPayment;

	private BigDecimal repairSum;

	private BigDecimal repairActualSum;

	private String isdelete;
	private String companyId;
	private String otherBz;

	private Date startTime;

	private Date endTime;

	private List<RepairItem> itemList;

	private BigDecimal warrantyStartMiles;

	public Integer getRepairId() {
		return repairId;
	}

	public void setRepairId(Integer repairId) {
		this.repairId = repairId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public String getRepairNum() {
		return repairNum;
	}

	public void setRepairNum(String repairNum) {
		this.repairNum = repairNum == null ? null : repairNum.trim();
	}

	public String getRepairName() {
		return repairName;
	}

	public void setRepairName(String repairName) {
		this.repairName = repairName == null ? null : repairName.trim();
	}

	public String getServiceAdviser() {
		return serviceAdviser;
	}

	public void setServiceAdviser(String serviceAdviser) {
		this.serviceAdviser = serviceAdviser == null ? null : serviceAdviser.trim();
	}

	public String getRepairMan() {
		return repairMan;
	}

	public void setRepairMan(String repairMan) {
		this.repairMan = repairMan == null ? null : repairMan.trim();
	}

	public Date getRepairTime() {
		return repairTime;
	}

	public void setRepairTime(Date repairTime) {
		this.repairTime = repairTime;
	}

	public String getRepairPayment() {
		return repairPayment;
	}

	public void setRepairPayment(String repairPayment) {
		this.repairPayment = repairPayment == null ? null : repairPayment.trim();
	}

	public BigDecimal getRepairSum() {
		return repairSum;
	}

	public void setRepairSum(BigDecimal repairSum) {
		this.repairSum = repairSum;
	}

	public String getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(String isdelete) {
		this.isdelete = isdelete == null ? null : isdelete.trim();
	}

	public List<RepairItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<RepairItem> itemList) {
		this.itemList = itemList;
	}

	public String getRepairTimeStr() {
		return repairTimeStr;
	}

	public void setRepairTimeStr(String repairTimeStr) {
		this.repairTimeStr = repairTimeStr;
	}

	public BigDecimal getRepairActualSum() {
		return repairActualSum;
	}

	public void setRepairActualSum(BigDecimal repairActualSum) {
		this.repairActualSum = repairActualSum;
	}

	public BigDecimal getWarrantyStartMiles() {
		return warrantyStartMiles;
	}

	public void setWarrantyStartMiles(BigDecimal warrantyStartMiles) {
		this.warrantyStartMiles = warrantyStartMiles;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setStockId(Integer stockId) {
		// TODO Auto-generated method stub
		
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getOtherBz() {
		return otherBz;
	}

	public void setOtherBz(String otherBz) {
		this.otherBz = otherBz;
	}

}