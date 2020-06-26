package com.crAdmin.module.statistics.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.crAdmin.bean.vo.OutStockVo;
import com.crAdmin.module.statistics.service.CarUseStockService;
import com.crAdmin.util.BaseController;
import com.crAdmin.util.DateUtils;
import com.crAdmin.util.Util;

/**
 * 库存统计controller
 * 
 * @author yht
 * 
 */
@Controller("CarUseStockController")
@RequestMapping("/carUseStock")
public class CarUseStockController extends BaseController {
	@Autowired
	private CarUseStockService service;

	/**
	 * 跳转到汽车维修情况统计界面
	 * 
	 * @param @param model
	 * @param @return
	 * @return String
	 * @author scn
	 * @time 2017-9-8下午5:25:10
	 * @throws
	 */
	@RequestMapping("/toCarUseStock.do")
	public String toSalesMoney(Model model) {
		return "statistics/carUseStock/carUseStockList.ftl";
	}

	@RequestMapping("/getCarUseStockList.do")
	@ResponseBody
	public Object getCarUseStockList(HttpSession session, String endTime,
			String startTime, Model model) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		OutStockVo stock = new OutStockVo();
		stock.setCompanyId(Util.getCompanyId(session));
		stock.setEndTime(DateUtils.formatYs(endTime));
		stock.setStartTime(DateUtils.formatYs(startTime));
		List<OutStockVo> outStock = service.carStockList(stock);
		resultMap.put("data", outStock);

		// 汇总成本利润
		BigDecimal incomePrice = new BigDecimal(0);
		BigDecimal workHourCost = new BigDecimal(0);
		BigDecimal itemSum = new BigDecimal(0);
		for (OutStockVo stockVo : outStock) {
			incomePrice = incomePrice.add(stockVo.getCost());// 进价
			workHourCost = workHourCost.add(stockVo.getWorkHourCost());// 工时费
			itemSum = itemSum.add(stockVo.getItemSum());// 实际收入
		}
		BigDecimal profit = itemSum.subtract(incomePrice);
		OutStockVo stockVo = new OutStockVo();
		stockVo.setStockIncomePrice(incomePrice);
		stockVo.setWorkHourCost(workHourCost);
		stockVo.setItemSum(itemSum);
		stockVo.setProfit(profit);
		resultMap.put("stock", stockVo);
		return resultMap;
	}

}
