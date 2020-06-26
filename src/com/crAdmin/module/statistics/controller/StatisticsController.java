package com.crAdmin.module.statistics.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.crAdmin.bean.Repair;
import com.crAdmin.bean.Statistics;
import com.crAdmin.module.statistics.service.StatisticsService;
import com.crAdmin.util.BaseController;
import com.crAdmin.util.DateUtils;
import com.crAdmin.util.Util;
import com.crAdmin.util.annotation.LogAnnotation;
import com.crAdmin.util.annotation.OptionType;

/**
* @ClassName: StatisticsController 
* @Description: 统计信息相关controller
* @author scn 
* @date 2017-8-8 下午2:15:00
 */
@Controller("StatisticsController")
@RequestMapping("/statistic")
public class StatisticsController extends BaseController {
	@Autowired
	private StatisticsService service;

	/**
	 * 跳转到查询统计页面
	 * 
	 * @param @return
	 * @return ModelAndView
	 * @author scn
	 * @time 2017-6-21下午5:59:11
	 * @throws
	 */
	@RequestMapping("/toSalesMoneyList.do")
	// @LogAnnotation(option = "跳转到查询统计页面", optionType = OptionType.pageIn)
	public ModelAndView toSalesMoney() {
		ModelAndView mv = new ModelAndView(
				"statistics/moneyStatistics/salesStatistics.ftl");
		return mv;
	}

	/**
	 * 跳转到echart图形展示页面
	 * 
	 * @param @return
	 * @return ModelAndView
	 * @author scn
	 * @time 2017-6-21下午5:59:11
	 * @throws
	 */
	@RequestMapping("/toSalesEchart.do")
	@LogAnnotation(option = "跳转到图形展示页面", optionType = OptionType.pageIn)
	public String toSalesEchart(String dateType, String startTime,
			String endTime, Model model) {
		if (StringUtils.isBlank(dateType)) {
			dateType = "date";
		}
		model.addAttribute("dateType", dateType);
		model.addAttribute("endTime", endTime);
		model.addAttribute("startTime", startTime);
		return "statistics/moneyStatistics/salesEchartStatistics.ftl";
	}

	/**
	 * 获取一段时间内实际应有收支记录
	 * 
	 * @param @param startTime
	 * @param @param endTime
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @author scn
	 * @time 2017-6-21下午5:59:27
	 * @throws
	 */
	@RequestMapping("/getMonery.do")
	@ResponseBody
	@LogAnnotation(option = "查询统计信息", optionType = OptionType.query)
	public Map<String, Object> countByTime(String startTime, String endTime,
			String dateType,HttpSession session) throws Exception {
		List<Statistics> statisticsList = new ArrayList<Statistics>();
		Repair repair = new Repair();
		// 判断传入时间为空则默认查全部，否则根据实际传入时间计算
		// --------------------关于echart请求的判断-------------
		// 如果为空，设置结束时间为当前
		// if (StringUtils.isBlank(endTime) && StringUtils.isBlank(startTime)
		// && StringUtils.isNotBlank(time)) {
		// repair.setEndTime(new Date());
		// }
		// // 最近一月，起始时间设置为一个月前
		// if ("1".equals(time)) {
		// repair.setStartTime(DateUtils.getCurrDate(-1));
		// }
		// // 最近三个月，起始时间设置为三个月前
		// else if ("3".equals(time)) {
		// repair.setStartTime(DateUtils.getCurrDate(-3));
		// }// 最近半年，起始时间设置为六个月前
		// else if ("6".equals(time)) {
		// repair.setStartTime(DateUtils.getCurrDate(-6));
		// } // 最近一年，起始时间设置为六个月前
		// else if ("12".equals(time)) {
		// repair.setStartTime(DateUtils.getCurrDate(-12));
		// }
		// ---------echart页面时间判断结束-------------------
		// ---------按月按季度判断--------------------------
		// 判断dateType类型 date:日期;month:月份;quarter:季度;year:年
		repair.setCompanyId(Util.getCompanyId(session));
		if (StringUtils.isNotBlank(startTime)) {
			repair.setStartTime(DateUtils.format(startTime,
					DateUtils.formatStr_yyyyMMddHHmmss));
		}
		if (StringUtils.isNotBlank(endTime)) {
			repair.setEndTime(DateUtils.format(endTime,
					DateUtils.formatStr_yyyyMMddHHmmss));
		}
		// 按天查询
		if ("date".equals(dateType)) {
			statisticsList = service.queryMoneyByDate(repair);
		}// 按月查询
		else if ("month".equals(dateType)) {
			statisticsList = service.queryMoney(repair);
		}// 按季度查询（未做）
		else if ("quarter".equals(dateType)) {
		}// 按年查询
		else if ("year".equals(dateType)) {
			statisticsList = service.queryMoneyByYear(repair);

		} else {
			statisticsList = service.queryMoney(repair);

		}
		Map<String, Object> result = new HashMap<String, Object>();
		// 获取应收、实收总额
		BigDecimal repairSum = new BigDecimal(0);
		BigDecimal repairSctualSum = new BigDecimal(0);
		for (Statistics statistics : statisticsList) {
			repairSum = repairSum.add(statistics.getRepairSum());
			repairSctualSum = repairSctualSum.add(statistics
					.getRepairSctualSum());
		}

		List<Statistics> stockInOutSum = service
				.queryStockInOutSumPrice(repair);
		if (null != stockInOutSum && stockInOutSum.size() > 0) {
			Statistics statistics = stockInOutSum.get(0);
			if (statistics != null) {
				result.put("incoming", statistics.getIncoming());
				result.put("outsum", statistics.getOutsum());
			}
		}

		result.put("repairSum", repairSum);
		result.put("repairSctualSum", repairSctualSum);
		result.put("data", statisticsList);
		return result;
	}

	/**
	 * 按月查询销售金额
	 * 
	 * @param @param datatime
	 * @param @param model
	 * @param @return
	 * @return String
	 * @author scn
	 * @time 2017-6-28上午10:32:42
	 * @throws
	 */
	@RequestMapping("/toSalesMonth.do")
	public String toSaleMonthList(String datatime, Model model) {
		model.addAttribute("datatime", datatime);
		return "statistics/moneyStatistics/salesMonthStatistics.ftl";
	}

	/**
	 * 双击某月展示当月每天销售金额
	 * 
	 * @param @param month
	 * @param @return
	 * @param @throws Exception
	 * @return Object
	 * @author scn
	 * @time 2017-6-28上午10:32:57
	 * @throws
	 */
	@RequestMapping("/getDateMoneyByMonth.do")
	@ResponseBody
//	@LogAnnotation(option = "跳转到查询统计页面", optionType = OptionType.pageIn)
	public Object getMoneryItem(String month,HttpSession session) throws Exception {
		// 判断传入时间为空则默认为最近7天，否则根据实际传入时间计算
		// if (StringUtils.isBlank(month)) {
		// return null;
		// }
		List<Statistics> statisticsList = service
				.queryMoneyByMonth(month + "%",Util.getCompanyId(session));
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", statisticsList);
		return result;
	}

}
