package com.crAdmin.module.stockIncome.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crAdmin.bean.StockIncome;
import com.crAdmin.module.stockIncome.dao.StockIncomeMapper;

/**
 * 库存相关service
 * 
 * @ClassName: StockService
 * @Description:
 * @author 桑越
 * @date 2015-12-6 下午10:07:01
 * @version V1.0
 */
@Service("StockIncomingService")
@Transactional(rollbackFor = Exception.class)
public class StockIncomingService {
	@Autowired
	private StockIncomeMapper stockIncomeMapper;

	public int insertIncomeInfo(StockIncome stockIncome) {
		return stockIncomeMapper.insertSelective(stockIncome);
	}
	public int delStockIncomeInfo(StockIncome stockIncome) {
		return stockIncomeMapper.delStockIncomeInfo(stockIncome);
	}


}
