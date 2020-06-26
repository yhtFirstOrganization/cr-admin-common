package com.crAdmin.module.repairItem.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crAdmin.bean.RepairItem;
import com.crAdmin.bean.Stock;
import com.crAdmin.module.repairItem.dao.RepairItemMapper;
import com.crAdmin.module.stock.service.StockService;

/**
 * 维修项目相关service
 * 
 * @ClassName: RepairItemService
 * @Description:
 * @author 桑越
 * @date 2015-12-7 下午11:14:14
 * @version V1.0
 */
@Service("repairItemService")
@Transactional(rollbackFor = Exception.class)
public class RepairItemService {
	@Autowired
	private RepairItemMapper repairItemMapper;
	@Autowired
	private StockService stockService;

	/**
	 * 获取维修项目列表
	 * 
	 * @Title: queryRepairItemList
	 * @Description:
	 * @author 桑越
	 * @date 2015-12-8 下午8:57:21
	 * @param @param
	 *            repairItem
	 * @param @return
	 *            设定文件
	 * @return List<RepairItem> 返回类型
	 * @throws @version
	 *             V1.0
	 */
	public List<RepairItem> queryRepairItemList(RepairItem repairItem) {
		return repairItemMapper.queryRepairItemList(repairItem);
	}

	/**
	 * 新增维修项目信息
	 * 
	 * @Title: addRepairItemInfo
	 * @Description:
	 * @author 桑越
	 * @date 2015-12-8 下午8:58:13
	 * @param @param
	 *            repairItem
	 * @param @return
	 *            设定文件
	 * @return int 返回类型
	 * @throws @version
	 *             V1.0
	 */
	public int addRepairItemInfo(RepairItem repairItem) {
		// 修改库存信息
		Integer stockId = repairItem.getStockId();
		if (stockId != null && stockId > 0) {
			BigDecimal itemQuantity = repairItem.getItemQuantity();
			Stock stock = new Stock();
			stock.setStockId(stockId);
			stock.setStockQuantity(itemQuantity);
			stockService.updateStockInfoByUsed(stock);
		}
		return repairItemMapper.addRepairItemInfo(repairItem);
	}

	/**
	 * 根据维修记录删除维修项目信息
	 * 
	 * @Title: delRepairItemInfo
	 * @Description:
	 * @author 桑越
	 * @date 2015-12-19 下午10:05:17
	 * @param @param
	 *            particle
	 * @param @return
	 *            设定文件
	 * @return int 返回类型
	 * @throws @version
	 *             V1.0
	 */
	public int delRepairItemInfo(RepairItem repairItem) {
		List<RepairItem> repairItemList = repairItemMapper.queryRepairItemList(repairItem);
		if (repairItemList != null) {
			for (int i = 0; i < repairItemList.size(); i++) {
				RepairItem repairItemResult = repairItemList.get(i);
				if (repairItemResult != null) {
					Integer stockId = repairItemResult.getStockId();
					if (stockId != null && stockId > 0) {
						BigDecimal itemQuantity = repairItemResult.getItemQuantity();
						Stock stock = new Stock();
						stock.setStockId(stockId);
						stock.setStockQuantity(new BigDecimal(0).subtract(itemQuantity));
						stockService.updateStockInfoByUsed(stock);
					}
				}

			}
		}
		return repairItemMapper.delRepairItemInfo(repairItem);
	}

}
