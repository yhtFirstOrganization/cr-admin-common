package com.crAdmin.bean;

import java.math.BigDecimal;
import java.util.Date;

public class StockIncome {
	private Integer id;

	private Integer stockId;

	private BigDecimal incomeStockQuantity;

	private Date incomeTime;

	private Date updateTime;
	// 新增供应商信息
	private Integer supplierId;
	private String supplier;
	private String supplierTel;
	private String isdelete;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStockId() {
		return stockId;
	}

	public void setStockId(Integer stockId) {
		this.stockId = stockId == null ? null : stockId;
	}

	public BigDecimal getIncomeStockQuantity() {
		return incomeStockQuantity;
	}

	public void setIncomeStockQuantity(BigDecimal incomeStockQuantity) {
		this.incomeStockQuantity = incomeStockQuantity;
	}

	public Date getIncomeTime() {
		return incomeTime;
	}

	public void setIncomeTime(Date incomeTime) {
		this.incomeTime = incomeTime;
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

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getSupplierTel() {
		return supplierTel;
	}

	public void setSupplierTel(String supplierTel) {
		this.supplierTel = supplierTel;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
}