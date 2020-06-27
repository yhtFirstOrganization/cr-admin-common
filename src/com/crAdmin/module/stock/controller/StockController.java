package com.crAdmin.module.stock.controller;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.crAdmin.bean.PersonInfo;
import com.crAdmin.bean.Stock;
import com.crAdmin.bean.StockIncome;
import com.crAdmin.bean.User;
import com.alibaba.fastjson.JSONObject;
import com.crAdmin.bean.Message;
import com.crAdmin.bean.vo.OutStockVo;
import com.crAdmin.module.stock.service.StockService;
import com.crAdmin.module.stockIncome.service.StockIncomingService;
import com.crAdmin.module.supplier.service.SupplierService;
import com.crAdmin.util.BaseController;
import com.crAdmin.util.DateUtils;
import com.crAdmin.util.ExcelException;
import com.crAdmin.util.ExcelUtil;
import com.crAdmin.util.Util;
import com.crAdmin.util.annotation.LogAnnotation;
import com.crAdmin.util.annotation.OptionType;

/**
 * 库存相关controller
 * 
 * @author yht
 * 
 */
@Controller("stockController")
@RequestMapping("/stock")
public class StockController extends BaseController {
	@Autowired
	private StockService stockService;
	@Autowired
	private StockIncomingService stockIncomingService;
	@Autowired
	private SupplierService supplierService;

	/**
	 * 跳转到库存列表页面
	 * 
	 * @Title: toStockList
	 * @Description:
	 * @author 桑越
	 * @date 2015-12-6 下午4:07:24
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws @version V1.0
	 */
	@RequestMapping("/toStockList.do")
	// @LogAnnotation(option = "跳转到库存页面", optionType = OptionType.pageIn)
	public ModelAndView toStockList() {
		ModelAndView mv = new ModelAndView("stock/stockList.ftl");
		return mv;
	}

	/**
	 * 跳转到新增库存页面
	 * 
	 * @Title: toAddStock
	 * @Description:
	 * @author 桑越
	 * @date 2015-12-6 下午4:08:44
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws @version V1.0
	 */
	@RequestMapping("/toAddStock.do")
	// @LogAnnotation(option = "跳转到新增库存页面", optionType = OptionType.pageIn)
	public ModelAndView toAddStock(HttpSession session) {
		ModelAndView mv = new ModelAndView("stock/addStock.ftl");
		// 获取供应商列表
		PersonInfo personInfo = new PersonInfo();
		personInfo.setCompanyId(Util.getCompanyId(session));
		personInfo.setIsdelete("0");
		personInfo.setPerType("1");
		List<PersonInfo> supplierList = supplierService
				.querySupplierList(personInfo);
		// 返回页面赋值
		// 处理返回值
		Stock queryStock = new Stock();
		mv.addObject("stockInfo", queryStock);
		mv.addObject("supplierList", supplierList == null ? "" : supplierList);
		return mv;
	}

	/**
	 * 跳转到查看进价确认界面
	 * 
	 * @return
	 */
	@RequestMapping("/toShowStockIncomePricePage.do")
	// @LogAnnotation(option = "跳转到查看进价确认页面", optionType = OptionType.pageIn)
	public ModelAndView toShowStockIncomePricePage() {
		ModelAndView mv = new ModelAndView(
				"stock/validateIncomePricePassword.ftl");
		return mv;
	}

	/**
	 * 校验查看进价秘钥
	 * 
	 * @return
	 */
	@RequestMapping("/validateIncomePricePassword.do")
	@ResponseBody
	@LogAnnotation(option = "校验查看进价秘钥", optionType = OptionType.passwordCheck)
	public String validateIncomePricePassword(User user, HttpSession session)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		JSONObject json = new JSONObject();
		String otherPasswordPara = user.getOtherPassword();
		if (StringUtils.isBlank(otherPasswordPara)) {
			json.put("code", "401");
			json.put("msg", "请输入正确的密码！");
			return json.toString();
		}
		User sessionUser = (User) session.getAttribute("user");
		String otherPassword = sessionUser.getOtherPassword();
		if (!StringUtils.equals(otherPassword, otherPasswordPara)) {
			json.put("code", "402");
			json.put("msg", "密码错误！");
			return json.toString();
		}
		json.put("code", "200");
		json.put("msg", "验证通过！");
		return json.toString();
	}

	/**
	 * 跳转到修改库存页面
	 * 
	 * @Title: toEditStock
	 * @Description:
	 * @author 桑越
	 * @date 2015-12-17 下午10:21:37
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws @version V1.0
	 */
	@RequestMapping("/toEditStock.do")
	// @LogAnnotation(option = "跳转到修改库存页面", optionType = OptionType.pageIn)
	public ModelAndView toEditStock(Integer stockId, HttpSession session) {
		Stock pstock = new Stock();
		pstock.setStockId(stockId);
		pstock.setCompanyId(Util.getCompanyId(session));
		pstock.setIsdelete("0");// 有效的
		Stock queryStock = stockService.queryStockInfo(pstock);
		// 获取供应商列表
		PersonInfo personInfo = new PersonInfo();
		personInfo.setCompanyId(Util.getCompanyId(session));
		personInfo.setIsdelete("0");
		personInfo.setPerType("1");
		List<PersonInfo> supplierList = supplierService
				.querySupplierList(personInfo);

		ModelAndView mv = new ModelAndView("stock/addStock.ftl");
		mv.addObject("supplierList", supplierList == null ? "" : supplierList);
		// 处理返回值
		mv.addObject("stockInfo", queryStock);

		return mv;
	}

	/**
	 * 跳转到库存进货页面
	 * 
	 * @param stockId
	 * @return
	 */
	@RequestMapping("/toInComeStock.do")
	// @LogAnnotation(option = "跳转到修改库存页面", optionType = OptionType.pageIn)
	public String toInComeStock(Integer stockId, HttpSession session,
			Model model) {
		Stock pstock = new Stock();
		pstock.setStockId(stockId);
		pstock.setCompanyId(Util.getCompanyId(session));
		pstock.setIsdelete("0");// 有效的
		Stock queryStock = stockService.queryStockInfo(pstock);
		// 获取供应商列表
		PersonInfo personInfo = new PersonInfo();
		personInfo.setCompanyId(Util.getCompanyId(session));
		personInfo.setIsdelete("0");
		personInfo.setPerType("1");
		List<PersonInfo> supplierList = supplierService
				.querySupplierList(personInfo);
		// 返回页面赋值
		model.addAttribute("stockInfo", queryStock);
		model.addAttribute("supplierList", supplierList == null ? ""
				: supplierList);

		return "stock/inComeStock.ftl";
	}

	/**
	 * 跳转到库存lov窗口
	 * 
	 * @param stockId
	 * @return
	 */
	@RequestMapping("/toStockLOV.do")
	public ModelAndView toStockLOV() {
		Stock pstock = new Stock();
		pstock.setIsdelete("0");// 有效的
		ModelAndView mv = new ModelAndView("stock/stockLOV.ftl");

		return mv;
	}

	/**
	 * ajax校验库存编号等信息
	 * 
	 * @Title: checkLicensePlateNum
	 * @Description:
	 * @author 桑越
	 * @date 2015-12-6 下午9:55:52
	 * @param @param article
	 * @param @return 设定文件
	 * @return Object 返回类型
	 * @throws @version V1.0
	 * @version V1.1桑越2016年1月3日 增加对修改库存时库存编号的校验
	 */
	@RequestMapping("/checkStockNo.do")
	@ResponseBody
	public Object checkStockNo(Stock stock, String editType, HttpSession session) {
		Integer stockId = stock.getStockId();// 库存id
		String stockNo = stock.getStockNo();// 库存编号

		Map<String, Object> returnMap = new HashMap<String, Object>();
		Stock pstock = new Stock();
		pstock.setStockId(stockId);
		pstock.setStockNo(stockNo);
		pstock.setCompanyId(Util.getCompanyId(session));
		pstock.setIsdelete("0");// 设置查询有效的库存信息
		List<Stock> otherStockList = stockService.queryStockList(pstock);
		if (otherStockList == null || otherStockList.size() == 0) {// 没有相关库存信息，可以修改
			returnMap.put("status", "y");
			returnMap.put("info", "");
			return returnMap;
		} else {
			returnMap.put("status", "n");
			returnMap.put("info", "已存在该库存编号相关库存信息！");
			return returnMap;
		}
	}

	/**
	 * 新增库存信息
	 * 
	 * @Title: addArticleInfo
	 * @Description:
	 * @author 桑越
	 * @date 2015-12-6 下午11:51:51
	 * @param @param stock
	 * @param @return 设定文件
	 * @return Object 返回类型
	 * @throws @version V1.0
	 */
	@RequestMapping("/addStock.do")
	@ResponseBody
	@LogAnnotation(option = "编辑库存或进货信息", optionType = OptionType.edit)
	public Object addStockInfo(Stock stock, HttpSession session) {

		stock.setCompanyId(Util.getCompanyId(session));
		StockIncome stockIncome = new StockIncome();
		if (stock != null && StringUtils.isNotBlank(stock.getSupplier())) {
			Integer supplierId = doSupplier(stock);
			stockIncome.setSupplierId(supplierId);
			stock.setSupplierId(supplierId);
		}
		stockIncome.setSupplier(stock.getSupplier());
		stockIncome.setSupplierTel(stock.getSupplierTel());

		Integer stockId = stock.getStockId();
		String pageType = stock.getPageType();
		stock.setUpdateTime(DateUtils.getCurrDate());
		stockIncome.setIsdelete("0");
		int i;
		String msg = "";
		if (stockId != null && stockId > 0) {// 修改
			msg = "修改成功！";
			if (StringUtils.isNotBlank(pageType)
					&& StringUtils.equalsIgnoreCase(pageType, "inCome")) {// 进货
				BigDecimal inComeStockQuantity = stock.getInComeStockQuantity();
				BigDecimal stockQuantity = stock.getStockQuantity();
				stock.setStockQuantity(inComeStockQuantity.add(stockQuantity));
				// 插入进货信息
				stockIncome.setIncomeStockQuantity(inComeStockQuantity);
				stockIncome.setIncomeTime(DateUtils.getCurrDate());
				stockIncome.setStockId(stockId);
				stockIncomingService.insertIncomeInfo(stockIncome);
				msg = "添加进货成功！";
			}

			i = stockService.updateStockInfo(stock);
		} else {
			// 新增库存相关信息
			stock.setCreateTime(DateUtils.getCurrDate());
			i = stockService.addStockInfo(stock);
			List<Stock> stockInfo = stockService.queryStockList(stock);
			stockIncome.setIncomeStockQuantity(stockInfo.get(0)
					.getStockQuantity());
			stockIncome.setIncomeTime(DateUtils.getCurrDate());
			stockIncome.setStockId(stockInfo.get(0).getStockId());
			stockIncomingService.insertIncomeInfo(stockIncome);
			msg = "新增成功！";
		}
		Message message = new Message();

		if (i == 0) {// 新增失败
			message.setCode(code0020);
			message.setMsg("新增库存信息失败，请刷新重试！");
			return message;
		}

		message.setCode(successCode);
		message.setMsg(msg);
		return message;
	}

	/**
	 * 删除库存信息
	 * 
	 * @param stock
	 * @return
	 * @version V1.1 桑越2015年12月19日 优化代码
	 */
	@RequestMapping("/delStockInfo.do")
	@ResponseBody
	@LogAnnotation(option = "删除库存信息", optionType = OptionType.delete)
	public Object delStockInfo(Stock stock) {
		Integer stockId = stock.getStockId();// 文章id

		// 删除库存信息
		Stock particle = new Stock();
		particle.setStockId(stockId);
		int i = stockService.delStockInfo(particle);
		StockIncome stockIncome = new StockIncome();
		stockIncome.setStockId(stockId);
		int income = stockIncomingService.delStockIncomeInfo(stockIncome);
		Message message = new Message();

		Map<String, Object> returnMap = new HashMap<String, Object>();
		if (i + income == 0) {
			message.setCode(code0018);
			message.setMsg("删除库存信息失败，请刷新重试！");
			returnMap.put("message", message);
			return returnMap;
		}
		message.setCode(successCode);
		message.setMsg("");
		returnMap.put("message", message);
		return returnMap;
	}

	/**
	 * 查询库存列表
	 * 
	 * @param provId
	 * @return
	 */
	@RequestMapping("/queryStockList.do")
	@ResponseBody
	// @LogAnnotation(option = "查询库存列表", optionType = OptionType.query)
	public Object queryStockList(Stock stock, HttpSession session) {
		stock.setCompanyId(Util.getCompanyId(session));
		stock.setIsdelete("0");
		List<Stock> list = stockService.queryStockList(stock);

		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("data", list);

		return returnMap;
	}

	@RequestMapping("/queryStockInfo.do")
	@ResponseBody
	// @LogAnnotation(option = "查询库存信息", optionType = OptionType.query)
	public Object queryStockInfo(Integer stockId) {
		Stock pstock = new Stock();
		pstock.setStockId(stockId);
		pstock.setIsdelete("0");
		Stock queryStockInfo = stockService.queryStockInfo(pstock);
		return queryStockInfo;
	}

	/**
	 * 跳转到库存列表页面
	 * 
	 * @Title: toStockList
	 * @Description:
	 * @author 桑越
	 * @date 2015-12-6 下午4:07:24
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws @version V1.0
	 */
	@RequestMapping("/toStockStatisticList.do")
	public ModelAndView toStockStatisticList() {
		ModelAndView mv = new ModelAndView(
				"statistics/stock/stockStatisticsList.ftl");
		return mv;
	}

	/**
	 * 查询库存统计信息列表
	 * 
	 * @param provId
	 * @return
	 */
	@RequestMapping("/outStockList.do")
	@ResponseBody
	// @LogAnnotation(option = "查询库存列表", optionType = OptionType.query)
	public Object outStockList(HttpSession session, String endTime,
			String startTime, String dateType) {
		OutStockVo stock = new OutStockVo();
		stock.setCompanyId(Util.getCompanyId(session));
		stock.setEndTime(DateUtils.formatYs(endTime));
		stock.setStartTime(DateUtils.formatYs(startTime));
		// 获取库存出库信息的列表
		List<OutStockVo> list = stockService.getOutStockList(stock);

		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("data", list);

		// 汇总成本利润
		BigDecimal incomePrice = new BigDecimal(0);
		BigDecimal workHourCost = new BigDecimal(0);
		BigDecimal itemSum = new BigDecimal(0);
		BigDecimal stockCost = new BigDecimal(0);
		for (OutStockVo stockVo : list) {
			incomePrice = incomePrice.add(stockVo.getCost());// 进价
			workHourCost = workHourCost.add(stockVo.getWorkHourCost());// 工时费
			itemSum = itemSum.add(stockVo.getItemSum());// 实际收入
			stockCost = stockCost.add(stockVo.getStockCost());// 实际收入
		}
		BigDecimal profit = itemSum.subtract(incomePrice);
		OutStockVo stockVo = new OutStockVo();
		stockVo.setStockIncomePrice(incomePrice);
		stockVo.setWorkHourCost(workHourCost);
		stockVo.setItemSum(itemSum);
		stockVo.setProfit(profit);
		stockVo.setStockCost(stockCost);
		returnMap.put("stock", stockVo);
		return returnMap;
	}

	/**
	 * 7 跳转到销货记录页面
	 * 
	 * @param stockId
	 * @return
	 */
	@RequestMapping("/toOutItemStock.do")
	// @LogAnnotation(option = "跳转到修改库存页面", optionType = OptionType.pageIn)
	public String toOutStock(Integer stockId, String startTime, String endTime,
			String dateType, HttpSession session, Model model) {
		model.addAttribute("stockId", stockId);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("dateType", dateType == null ? "" : dateType);
		return "statistics/stock/outStock.ftl";
	}

	/**
	 * 获取销货记录列表
	 * 
	 * @param @param stockId
	 * @param @param session
	 * @param @param model
	 * @param @return
	 * @return Object
	 * @author scn
	 * @time 2017-9-7下午3:23:52
	 * @throws
	 */
	@RequestMapping("/toOutStockItemList.do")
	@ResponseBody
	public Object getOutStockList(Integer stockId, String startTime,
			String endTime, String dateType, HttpSession session) {
		OutStockVo pstock = new OutStockVo();
		pstock.setStockId(stockId);
		pstock.setEndTime(DateUtils.formatYs(endTime));
		pstock.setStartTime(DateUtils.formatYs(startTime));
		pstock.setCompanyId(Util.getCompanyId(session));
		// 根据库存id查询每辆车的使用情况
		List<OutStockVo> queryStock = stockService
				.queryOutStockItemInfo(pstock);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("data", queryStock);

		// 获取库存信息
		Stock stock = new Stock();
		stock.setStockId(stockId);
		Stock stockInfo = stockService.queryStockInfo(stock);
		// 汇总成本利润
		BigDecimal incomePrice = new BigDecimal(0);
		BigDecimal workHourCost = new BigDecimal(0);
		BigDecimal itemSum = new BigDecimal(0);
		for (OutStockVo stockVo : queryStock) {
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
		// returnMap.put("stock", stockVo);
		returnMap.put("stock", stockInfo);
		return returnMap;
	}

	/**
	 * 跳转到库存进货统计页面
	 * 
	 * @param @return
	 * @return ModelAndView
	 * @author scn
	 * @time 2017-9-9下午11:00:55
	 * @throws
	 */
	@RequestMapping("/toComingStock.do")
	public String toComingStock(Model model) {
		return "statistics/stock/incomingstock.ftl";
	}

	/**
	 * 查询库存统计信息列表
	 * 
	 * @param provId
	 * @return
	 */
	@RequestMapping("/getComingStockList.do")
	@ResponseBody
	// @LogAnnotation(option = "查询库存列表", optionType = OptionType.query)
	public Object getComingStockList(HttpSession session, String endTime,
			String startTime, String dateType) {
		OutStockVo stock = new OutStockVo();
		stock.setCompanyId(Util.getCompanyId(session));
		stock.setEndTime(DateUtils.format(endTime));
		stock.setStartTime(DateUtils.format(startTime));
		// 获取库存出库信息的列表
		List<OutStockVo> list = stockService.getComingStockList(stock);

		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("data", list);

		// 汇总成本利润
		BigDecimal incomePrice = new BigDecimal(0);
		BigDecimal workHourCost = new BigDecimal(0);
		BigDecimal itemSum = new BigDecimal(0);
		for (OutStockVo stockVo : list) {
			incomePrice = incomePrice.add(stockVo.getStockCount());// 进价
			workHourCost = workHourCost.add(stockVo.getStockCount());// 工时费
			itemSum = itemSum.add(stockVo.getStockQuantity());// 实际收入
		}
		BigDecimal profit = itemSum.subtract(incomePrice);
		OutStockVo stockVo = new OutStockVo();
		stockVo.setStockIncomePrice(incomePrice);
		stockVo.setWorkHourCost(workHourCost);
		stockVo.setItemSum(itemSum);
		stockVo.setProfit(profit);
		returnMap.put("stock", stockVo);
		return returnMap;
	}

	/**
	 * 7 跳转到进货记录页面
	 * 
	 * @param stockId
	 * @return
	 */
	@RequestMapping("/toIncomeItemStockList.do")
	// @LogAnnotation(option = "跳转到修改库存页面", optionType = OptionType.pageIn)
	public String toIncomeItemStockList(Integer stockId, String startTime,
			String endTime, HttpSession session, Model model) {
		model.addAttribute("stockId", stockId);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		return "statistics/stock/incomeStockItemList.ftl";
	}

	/**
	 * 查询库存统计信息列表
	 * 
	 * @param provId
	 * @return
	 */
	@RequestMapping("/getIncomeItemStock.do")
	@ResponseBody
	// @LogAnnotation(option = "查询库存列表", optionType = OptionType.query)
	public Object getIncomeItemStock(HttpSession session, Integer stockId,
			String endTime, String startTime) {
		OutStockVo stock = new OutStockVo();
		stock.setCompanyId(Util.getCompanyId(session));
		stock.setEndTime(DateUtils.format(endTime));
		stock.setStartTime(DateUtils.format(startTime));
		stock.setStockId(stockId);
		// 获取库存出库信息的列表
		List<OutStockVo> list = stockService.getIncomeItemStock(stock);

		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("data", list);

		// 汇总成本利润
		// BigDecimal incomePrice = new BigDecimal(0);
		// BigDecimal workHourCost = new BigDecimal(0);
		// BigDecimal itemSum = new BigDecimal(0);
		// for (OutStockVo stockVo : list) {
		// incomePrice = incomePrice.add(stockVo.getStockCount());// 进价
		// workHourCost = workHourCost.add(stockVo.getStockCount());// 工时费
		// itemSum = itemSum.add(stockVo.getStockQuantity());// 实际收入
		// }
		// BigDecimal profit = itemSum.subtract(incomePrice);
		// OutStockVo stockVo = new OutStockVo();
		// stockVo.setStockIncomePrice(incomePrice);
		// stockVo.setWorkHourCost(workHourCost);
		// stockVo.setItemSum(itemSum);
		// stockVo.setProfit(profit);
		// returnMap.put("stock", stockVo);
		return returnMap;
	}

	@RequestMapping("/expStockComeList.do")
	@ResponseBody
	// @LogAnnotation(option = "跳转到修改库存页面", optionType = OptionType.pageIn)
	public void expStockComeList(HttpSession session, String endTime,
			String startTime, String dateType, OutputStream out,
			HttpServletResponse response) {
		try {
			OutStockVo stock = new OutStockVo();
			stock.setCompanyId(Util.getCompanyId(session));
			stock.setEndTime(DateUtils.format(endTime));
			stock.setStartTime(DateUtils.format(startTime));
			// 获取库存出库信息的列表
			List<OutStockVo> list = stockService.getComingStockList(stock);

			LinkedHashMap<String, String> fieldMap = new LinkedHashMap<String, String>();
			fieldMap.put("stockNo", "库存编号");
			fieldMap.put("stockName", "库存名称");
			fieldMap.put("stockQuantity", "进货总数量");
			fieldMap.put("stockCount", "进货次数");
			fieldMap.put("cost", "进货成本");
			// ExcelUtil.listToExcel(list, fieldMap, "库存进货记录", out);
			String fileName = null;
			if (StringUtils.isBlank(startTime) && StringUtils.isBlank(endTime)) {
				fileName = "全部库存记录";
			} else {
				fileName = startTime == null ? ""
						: startTime + "-" + endTime == null ? "" : endTime
								+ "库存进货记录";
			}
			ExcelUtil.listToExcel(list, fieldMap, fileName, 65525, response,
					fileName);
		} catch (ExcelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping("/expStockComeItemList.do")
	@ResponseBody
	// @LogAnnotation(option = "跳转到修改库存页面", optionType = OptionType.pageIn)
	public void expStockComeItemList(HttpSession session, Integer stockId,
			String endTime, String startTime, HttpServletResponse response) {
		try {
			OutStockVo stock = new OutStockVo();
			stock.setCompanyId(Util.getCompanyId(session));
			stock.setEndTime(DateUtils.format(endTime));
			stock.setStartTime(DateUtils.format(startTime));
			stock.setStockId(stockId);
			// 获取库存出库信息的列表
			List<OutStockVo> list = stockService.getIncomeItemStock(stock);

			LinkedHashMap<String, String> fieldMap = new LinkedHashMap<String, String>();
			fieldMap.put("stockNo", "库存编号");
			fieldMap.put("stockName", "库存名称");
			fieldMap.put("supplier", "供应商");
			fieldMap.put("supplierTel", "供应商联系方式");
			fieldMap.put("stockQuantity", "进货数量");
			fieldMap.put("stockIncomePrice", "进货单价");
			fieldMap.put("incomingTimeStr", "进货时间");
			// ExcelUtil.listToExcel(list, fieldMap, "库存进货记录", out);
			String fileName = null;
			if (StringUtils.isBlank(startTime) && StringUtils.isBlank(endTime)) {
				fileName = "全部进货记录";
			} else {
				fileName = startTime == null ? ""
						: startTime + "-" + endTime == null ? "" : endTime
								+ stockId + "进货记录";
			}
			ExcelUtil.listToExcel(list, fieldMap, fileName, 65525, response,
					fileName);
		} catch (ExcelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Description: 1.保存或更新或选择供应商信息 2.返回供应商id（简单处理，数据库中存在则只返回ID，不做更新）
	 * @param @param stock
	 * @param @return
	 * @return
	 * @return Object
	 * @throws
	 * @author sun
	 * @date 2017-11-3
	 */
	private Integer doSupplier(Stock stock) {
		// 定义id，用于返回
		Integer id = null;
		PersonInfo info = new PersonInfo();
		// 查询名称相同的供应商
		info.setPerCompanyName(stock.getSupplier());
		info.setCompanyId(stock.getCompanyId());
		info.setPerType("1");
		info.setPerName(stock.getSupplier());
		List<PersonInfo> oldSupplierList = supplierService
				.querySupplierList(info);
		info.setPerTel(stock.getSupplierTel());

		if (stock.getSupplierId() != null) {
			id = stock.getSupplierId();
		} else if (oldSupplierList != null && oldSupplierList.size() > 0
				&& oldSupplierList.get(0) != null) {
			id = oldSupplierList.get(0).getPerId();
		} else {
			info.setCreateTime(DateUtils.getCurrDate());
			info.setIsdelete("0");
			info.setPerNum(supplierService.getNewPerNum(stock.getCompanyId()) + 1);
			supplierService.addPerInfo(info);
			id = info.getPerId();
		}
		return id;
	}

	/**
	 * 
	 * @Description: 1.保存或更新或选择供应商信息 2.返回供应商id（复杂处理，每次操作都更新）
	 * @param @param stock
	 * @param @return
	 * @return Object
	 * @throws
	 * @author sun
	 * @date 2017-11-3
	 */
	@SuppressWarnings("unused")
	private Object doSupplierMore(Stock stock) {

		PersonInfo info = new PersonInfo();
		// 查询名称相同的供应商
		info.setPerCompanyName(stock.getSupplier());
		info.setCompanyId(stock.getCompanyId());
		info.setPerType("1");
		info.setPerName(stock.getSupplier());

		info.setPerTel(stock.getSupplierTel());
		// 保存供应商信息
		if (stock.getSupplierId() != null) {
			// 传入的信息包含供应商id，直接编辑供应商信息
			info.setPerId(stock.getSupplierId());
			info.setUpdateTime(DateUtils.getCurrDate());
			supplierService.update(info);
		} else {
			// 查询是否有同名供应商，有则更新
			List<PersonInfo> oldSupplierList = supplierService
					.querySupplierList(info);
			if (oldSupplierList != null && oldSupplierList.size() > 0

			&& oldSupplierList.get(0) != null) {
				info.setPerId(oldSupplierList.get(0).getPerId());
				info.setUpdateTime(DateUtils.getCurrDate());
				supplierService.update(info);
			} else {// 新增供应商信息
				info.setCreateTime(DateUtils.getCurrDate());
				info.setIsdelete("0");
				info.setPerNum(supplierService.getNewPerNum(stock
						.getCompanyId()) + 1);
				supplierService.addPerInfo(info);
			}
		}
		return null;
	}
}
