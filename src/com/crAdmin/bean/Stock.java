package com.crAdmin.bean;

import java.math.BigDecimal;
import java.util.Date;

public class Stock {
	// 库存id
	private Integer stockId;
	// 库存编号
	private String stockNo;
	// 库存价格
	private BigDecimal stockPrice;
	// 库存进价
	private BigDecimal stockPriceIncome;
	// 库存个数
	private BigDecimal stockQuantity;
	// 库存单位
	private String stockUnit;
	// 库存安装工时费
	private BigDecimal workHoursCost;
	// 库存创建时间
	private Date createTime;
	// 库存更新时间
	private Date updateTime;
	// 库存是否删除
	private String isdelete;
	// 库存描述
	private String stockDes;
	// 库存进货数量
	private BigDecimal inComeStockQuantity;
	// 界面类型
	private String pageType;

	private String companyId;
	// 新增供应商信息
	private String supplier;
	private String supplierTel;
	private Integer supplierId;

	public Integer getStockId() {
		return stockId;
	}

	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}

	public String getStockNo() {
		return stockNo;
	}

	public void setStockNo(String stockNo) {
		this.stockNo = stockNo == null ? null : stockNo.trim();
	}

	public BigDecimal getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(BigDecimal stockPrice) {
		this.stockPrice = stockPrice;
	}

	public BigDecimal getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(BigDecimal stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public String getStockUnit() {
		return stockUnit;
	}

	public void setStockUnit(String stockUnit) {
		this.stockUnit = stockUnit == null ? null : stockUnit.trim();
	}

	public BigDecimal getWorkHoursCost() {
		return workHoursCost;
	}

	public void setWorkHoursCost(BigDecimal workHoursCost) {
		this.workHoursCost = workHoursCost;
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
		this.isdelete = isdelete == null ? null : isdelete.trim();
	}

	public String getStockDes() {
		return stockDes;
	}

	public void setStockDes(String stockDes) {
		this.stockDes = stockDes == null ? null : stockDes.trim();
	}

	public void setLicensePlateNum(Object licensePlateNum) {
		// TODO Auto-generated method stub

	}

	public BigDecimal getInComeStockQuantity() {
		return inComeStockQuantity;
	}

	public void setInComeStockQuantity(BigDecimal inComeStockQuantity) {
		this.inComeStockQuantity = inComeStockQuantity;
	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	public BigDecimal getStockPriceIncome() {
		return stockPriceIncome;
	}

	public void setStockPriceIncome(BigDecimal stockPriceIncome) {
		this.stockPriceIncome = stockPriceIncome;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
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

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

}