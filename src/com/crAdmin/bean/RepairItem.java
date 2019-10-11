package com.crAdmin.bean;

import java.math.BigDecimal;

public class RepairItem {
	private Integer repairItemId;

	private Integer repairId;

	private Integer stockId;

	private String itemNum;

	private BigDecimal stockPriceIncome;
	
	private BigDecimal itemPrice;

	private BigDecimal itemQuantity;

	private String itemUnit;

	private BigDecimal itemSum;

	private String replaceReason;

	private BigDecimal workHoursCost;

	private String isdelete;

	private String itemDes;

	public Integer getRepairItemId() {
		return repairItemId;
	}

	public void setRepairItemId(Integer repairItemId) {
		this.repairItemId = repairItemId;
	}

	public Integer getRepairId() {
		return repairId;
	}

	public void setRepairId(Integer repairId) {
		this.repairId = repairId;
	}

	public String getItemNum() {
		return itemNum;
	}

	public void setItemNum(String itemNum) {
		this.itemNum = itemNum == null ? null : itemNum.trim();
	}

	public BigDecimal getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}

	public BigDecimal getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(BigDecimal itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public String getItemUnit() {
		return itemUnit;
	}

	public void setItemUnit(String itemUnit) {
		this.itemUnit = itemUnit == null ? null : itemUnit.trim();
	}

	public BigDecimal getItemSum() {
		return itemSum;
	}

	public void setItemSum(BigDecimal itemSum) {
		this.itemSum = itemSum;
	}

	public String getReplaceReason() {
		return replaceReason;
	}

	public void setReplaceReason(String replaceReason) {
		this.replaceReason = replaceReason == null ? null : replaceReason
				.trim();
	}

	public BigDecimal getWorkHoursCost() {
		return workHoursCost;
	}

	public void setWorkHoursCost(BigDecimal workHoursCost) {
		this.workHoursCost = workHoursCost;
	}

	public String getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(String isdelete) {
		this.isdelete = isdelete == null ? null : isdelete.trim();
	}

	public String getItemDes() {
		return itemDes;
	}

	public void setItemDes(String itemDes) {
		this.itemDes = itemDes == null ? null : itemDes.trim();
	}

	public Integer getStockId() {
		return stockId;
	}

	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}

	public BigDecimal getStockPriceIncome() {
		return stockPriceIncome;
	}

	public void setStockPriceIncome(BigDecimal stockPriceIncome) {
		this.stockPriceIncome = stockPriceIncome;
	}
}