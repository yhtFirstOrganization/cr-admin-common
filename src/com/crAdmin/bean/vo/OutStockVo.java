package com.crAdmin.bean.vo;

import java.math.BigDecimal;
import java.util.Date;

public class OutStockVo {
	private Integer repairItemId;// 维修详情id

	private Integer repairId;// 维修id

	private Integer stockId;// 库存id

	private String stockNo;// 库存编号
	private String stockName;// 库存名称
	private String companyId;// 单位id
	private BigDecimal userdStockQuantity;// 使用库存数量
	private BigDecimal stockQuantity;// 剩余库存数量
	private BigDecimal workHourCost;// 工时费

	private BigDecimal stockIncomePrice;// 进价
	private BigDecimal stockCost;// 剩余库存成本

	private BigDecimal itemPrice;// 单价

	private BigDecimal itemQuantity;// 数量

	private BigDecimal itemSum;// 总额
	private BigDecimal cost;// 成本
	private BigDecimal profit;// 总利润
	private BigDecimal repairCount;// 维修次数
	private Integer carId;// 车辆id
	private String carType;// 车型
	private String license_plate_num;//车牌号
	// 新增供应商信息
	private String supplier;
	private String supplierTel;
	
	private Date incomingTime;//维修时间
	private Date createTime;//维修时间
	private Date startTime;//维修时间
	private Date endTime;//维修时间
	private BigDecimal stockCount;//进货次数
	private String incomingTimeStr;

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

	public BigDecimal getItemSum() {
		return itemSum;
	}

	public void setItemSum(BigDecimal itemSum) {
		this.itemSum = itemSum;
	}

	public Integer getStockId() {
		return stockId;
	}

	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}

	public BigDecimal getStockIncomePrice() {
		return stockIncomePrice;
	}

	public void setStockIncomePrice(BigDecimal stockIncomePrice) {
		this.stockIncomePrice = stockIncomePrice;
	}

	public String getStockNo() {
		return stockNo;
	}

	public void setStockNo(String stockNo) {
		this.stockNo = stockNo;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public BigDecimal getUserdStockQuantity() {
		return userdStockQuantity;
	}

	public void setUserdStockQuantity(BigDecimal userdStockQuantity) {
		this.userdStockQuantity = userdStockQuantity;
	}

	public BigDecimal getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(BigDecimal stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public BigDecimal getWorkHourCost() {
		return workHourCost;
	}

	public void setWorkHourCost(BigDecimal workHourCost) {
		this.workHourCost = workHourCost;
	}

	public BigDecimal getRepairCount() {
		return repairCount;
	}

	public void setRepairCount(BigDecimal repairCount) {
		this.repairCount = repairCount;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public String getLicense_plate_num() {
		return license_plate_num;
	}

	public void setLicense_plate_num(String license_plate_num) {
		this.license_plate_num = license_plate_num;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public BigDecimal getStockCost() {
		return stockCost;
	}

	public void setStockCost(BigDecimal stockCost) {
		this.stockCost = stockCost;
	}

	public BigDecimal getStockCount() {
		return stockCount;
	}

	public void setStockCount(BigDecimal stockCount) {
		this.stockCount = stockCount;
	}

	public String getSupplierTel() {
		return supplierTel;
	}

	public void setSupplierTel(String supplierTel) {
		this.supplierTel = supplierTel;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public Date getIncomingTime() {
		return incomingTime;
	}

	public void setIncomingTime(Date incomingTime) {
		this.incomingTime = incomingTime;
	}

	public String getIncomingTimeStr() {
		return incomingTimeStr;
	}

	public void setIncomingTimeStr(String incomingTimeStr) {
		this.incomingTimeStr = incomingTimeStr;
	}
	
}