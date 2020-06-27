package com.crAdmin.module.stock.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crAdmin.bean.Stock;
import com.crAdmin.bean.vo.OutStockVo;
import com.crAdmin.module.stock.dao.StockMapper;

/**
 * 库存相关service
 * 
 * @ClassName: StockService
 * @Description:
 * @author 桑越
 * @date 2015-12-6 下午10:07:01
 * @version V1.0
 */
@Service("stockService")
@Transactional(rollbackFor = Exception.class)
public class StockService {
	@Autowired
	private StockMapper stockMapper;

	/**
	 * 获取库存列表信息
	 * 
	 * @Title: queryStockList
	 * @Description:
	 * @author 桑越
	 * @date 2015-12-6 下午10:07:58
	 * @param @param stock
	 * @param @return 设定文件
	 * @return List<Stock> 返回类型
	 * @throws @version V1.0
	 */
	public List<Stock> queryStockList(Stock stock) {
		return stockMapper.queryStockList(stock);
	}

	/**
	 * 新增库存信息
	 * 
	 * @Title: addStockInfo
	 * @Description:
	 * @author 桑越
	 * @date 2015-12-6 下午11:53:16
	 * @param @param stock
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @throws @version V1.0
	 */
	public int addStockInfo(Stock stock) {
		return stockMapper.addStockInfo(stock);
	}

	/**
	 * 修改库存信息
	 * 
	 * @Title: updateStockInfo
	 * @Description:
	 * @author 桑越
	 * @date 2015-12-18 下午8:13:34
	 * @param @param stock
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @throws @version V1.0
	 */
	public int updateStockInfo(Stock stock) {
		return stockMapper.updateStockInfo(stock);
	}

	/**
	 * 删除库存相关信息
	 * 
	 * @param stock
	 * @return
	 */
	public int delStockInfo(Stock stock) {
		return stockMapper.delStockInfo(stock);
	}

	/**
	 * 查询库存信息
	 * 
	 * @Title: queryStockInfo
	 * @Description:
	 * @author 桑越
	 * @date 2015-12-8 下午9:42:12
	 * @param @param stock
	 * @param @return 设定文件
	 * @return Stock 返回类型
	 * @throws @version V1.0
	 */
	public Stock queryStockInfo(Stock stock) {
		return stockMapper.queryStockInfo(stock);
	}

	/**
	 * 根据使用情况更新库存个数
	 * 
	 * @param stock
	 * @return
	 */
	public int updateStockInfoByUsed(Stock stock) {
		stock.setUpdateTime(new Date());
		return stockMapper.updateStockInfoByUsed(stock);
	}

	/**
	 * 查询销货记录信息
	 * 
	 * @param @param stock
	 * @param @return
	 * @return Stock
	 * @author scn
	 * @time 2017-9-6下午12:07:47
	 * @throws
	 */
	public List<OutStockVo> queryOutStockItemInfo(OutStockVo stock) {
		return stockMapper.queryOutStockItemInfo(stock);
	}

	public List<OutStockVo> getOutStockList(OutStockVo stock) {
		return stockMapper.getOutStockList(stock);
	}

	public List<OutStockVo> getComingStockList(OutStockVo stock) {
		return stockMapper.getComingStockList(stock);
	}

	public List<OutStockVo> getIncomeItemStock(OutStockVo stock) {
		return stockMapper.getIncomeItemStock(stock);
	}

}
