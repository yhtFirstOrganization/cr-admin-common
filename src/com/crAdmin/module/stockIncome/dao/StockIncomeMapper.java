package com.crAdmin.module.stockIncome.dao;

import com.crAdmin.bean.StockIncome;

public interface StockIncomeMapper {
    int deleteByPrimaryKey(Integer id);
    int delStockIncomeInfo(StockIncome stockIncome);

    int insert(StockIncome record);

    int insertSelective(StockIncome record);

    StockIncome selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StockIncome record);

    int updateByPrimaryKey(StockIncome record);
}