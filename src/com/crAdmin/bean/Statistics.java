package com.crAdmin.bean;

import java.math.BigDecimal;

public class Statistics {
	// 统计时间
	private String datatime;
	// 应收费用
	private BigDecimal repairSum;
	// 实收费用
	private BigDecimal repairSctualSum;
	// 库存进价
	private BigDecimal incoming;
	// 应销售总价
	private BigDecimal outsum;
	private String companyId;

	public String getDatatime() {
		return datatime;
	}

	public void setDatatime(String datatime) {
		this.datatime = datatime;
	}

	public BigDecimal getRepairSum() {
		return repairSum;
	}

	public void setRepairSum(BigDecimal repairSum) {
		this.repairSum = repairSum;
	}

	public BigDecimal getRepairSctualSum() {
		return repairSctualSum;
	}

	public void setRepairSctualSum(BigDecimal repairSctualSum) {
		this.repairSctualSum = repairSctualSum;
	}

	public BigDecimal getIncoming() {
		return incoming;
	}

	public void setIncoming(BigDecimal incoming) {
		this.incoming = incoming;
	}

	public BigDecimal getOutsum() {
		return outsum;
	}

	public void setOutsum(BigDecimal outsum) {
		this.outsum = outsum;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

}