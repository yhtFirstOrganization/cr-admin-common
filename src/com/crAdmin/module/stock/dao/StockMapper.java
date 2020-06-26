package com.crAdmin.module.stock.dao;

import java.util.List;

import com.crAdmin.bean.Stock;
import com.crAdmin.bean.vo.OutStockVo;

public interface StockMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Stock record);

	int insertSelective(Stock record);

	Stock selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Stock record);

	int updateByPrimaryKeyWithBLOBs(Stock record);

	int updateByPrimaryKey(Stock record);

	Stock queryStockInfo(Stock stock);

	List<Stock> queryStockList(Stock stock);

	List<Stock> queryOtherStockList(Stock stock);

	int addStockInfo(Stock stock);

	int updateStockInfo(Stock stock);

	int delStockInfo(Stock stock);

	int updateStockInfoByUsed(Stock stock);

	List<OutStockVo> queryOutStockItemInfo(OutStockVo stock);

	List<OutStockVo> getOutStockList(OutStockVo stock);

	List<OutStockVo> getComingStockList(OutStockVo stock);

	List<OutStockVo> getIncomeItemStock(OutStockVo stock);
}