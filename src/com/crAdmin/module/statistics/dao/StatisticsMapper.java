package com.crAdmin.module.statistics.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.crAdmin.bean.Repair;
import com.crAdmin.bean.Statistics;

public interface StatisticsMapper {
	List<Statistics> queryMoney(Repair repair);

	List<Statistics> queryMoneyByMonth(@Param("month") String month,
			@Param("companyId") String string);

	List<Statistics> queryMoneyByDate(Repair repair);

	List<Statistics> queryMoneyByYear(Repair repair);

	List<Statistics> queryStockInOutSumPrice(Repair repair);
}