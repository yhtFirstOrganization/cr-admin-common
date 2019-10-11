package com.crAdmin.module.repair.controller;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.crAdmin.bean.Car;
import com.crAdmin.bean.Message;
import com.crAdmin.bean.Repair;
import com.crAdmin.bean.RepairItem;
import com.crAdmin.module.car.service.CarService;
import com.crAdmin.module.repair.service.RepairService;
import com.crAdmin.module.repairItem.service.RepairItemService;
import com.crAdmin.util.BaseController;
import com.crAdmin.util.DateUtils;
import com.crAdmin.util.NumGenerator;
import com.crAdmin.util.Util;
import com.crAdmin.util.annotation.LogAnnotation;
import com.crAdmin.util.annotation.OptionType;

/**
 * 维修记录相关controller
 * 
 * @ClassName: RepairController
 * @Description:
 * @author 桑越
 * @date 2015-12-8 下午9:08:01
 * @version V1.0
 */
@Controller("repairController")
@RequestMapping("/repair")
public class RepairController extends BaseController {
	@Autowired
	private RepairItemService repairItemService;
	@Autowired
	private RepairService repairService;
	@Autowired
	private CarService carService;

	/**
	 * 进入维修记录列表页面
	 * 
	 * @Title: toRepairList
	 * @Description:
	 * @author 桑越
	 * @date 2015-12-8 下午9:09:08
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 * @version V1.0
	 */
	@RequestMapping("/toRepairList.do")
	// @LogAnnotation(option = "查询维修记录列表信息", optionType = OptionType.query)
	public ModelAndView toRepairList(Integer carid, Integer repairId,
			String startTime, String endTime) {
		ModelAndView mv = new ModelAndView("repair/repairList.ftl");
		if (StringUtils.isNotBlank(startTime)) {
			mv.addObject("startTime", startTime);
		} else {
			mv.addObject("startTime", "");
		}
		if (StringUtils.isNotBlank(endTime)) {
			mv.addObject("endTime", endTime);
		} else {
			mv.addObject("endTime", "");
		}

		// Integer carid = (Integer)session.getValue("carid");
		if (carid != null) {
			Car pcar = new Car();
			pcar.setCarId(carid);
			// 查询汽车基本信息
			pcar.setIsdelete("0");
			Car queryCarInfo = carService.queryCarInfo(pcar);
			mv.addObject("carInfo", queryCarInfo);
		}
		if (repairId != null) {
			Repair repair = new Repair();
			repair.setRepairId(repairId);
			// 查询汽车基本信息
			repair.setIsdelete("0");
			List<Repair> queryRepairInfoList = repairService
					.queryRepairList(repair);
			Repair queryRepairInfo = null;
			if (queryRepairInfoList != null && queryRepairInfoList.size() > 0) {
				queryRepairInfo = queryRepairInfoList.get(0);
			}
			mv.addObject("repairInfo", queryRepairInfo);
		}

		return mv;
	}

	/**
	 * 跳转到新增维修记录和维修项目页面
	 * 
	 * @Title: toAddRepairAndItem
	 * @Description:
	 * @author 桑越
	 * @date 2015-12-8 下午9:12:45
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 * @version V1.0
	 */
	@RequestMapping("/toAddRepairAndItem.do")
	// @LogAnnotation(option = "跳转到新增维修记录和维修项目页面", optionType =
	// OptionType.pageIn)
	public ModelAndView toAddRepairAndItem(Integer carId, HttpSession session) {
		ModelAndView mv = new ModelAndView("repairAndItem/addRepairAndItem.ftl");
		// 查询汽车基本信息
		Car pcar = new Car();
		pcar.setCarId(carId);
		pcar.setIsdelete("0");
		pcar.setCompanyId(Util.getCompanyId(session));
		Car queryCarInfo = carService.queryCarInfo(pcar);
		mv.addObject("carInfo", queryCarInfo);
		mv.addObject("repairNum", NumGenerator.numGenerate());// 初始化显示维修单号
		return mv;
	}

	/**
	 * 跳转到修改维修记录和项目信息页面
	 * 
	 * @Title: toUpdateRepairAndItem
	 * @Description:
	 * @author 桑越
	 * @date 2015-12-18 下午10:10:27
	 * @param @param repairId
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 * @version V1.0
	 */
	@RequestMapping("/toUpdateRepairAndItem.do")
	// @LogAnnotation(option = "跳转到修改维修记录和项目信息页面", optionType =
	// OptionType.pageIn)
	public ModelAndView toUpdateRepairAndItem(Integer repairId) {
		ModelAndView mv = new ModelAndView(
				"repairAndItem/editRepairAndItem.ftl");

		// 查询维修基本信息
		Repair prepair = new Repair();
		prepair.setRepairId(repairId);
		prepair.setIsdelete("0");
		Repair queryRepair = repairService.queryRepairInfo(prepair);

		Integer carId = null;
		if (queryRepair != null) {
			carId = queryRepair.getCarId();
		}
		// 查询汽车基本信息
		Car pcar = new Car();
		pcar.setCarId(carId);
		pcar.setIsdelete("0");
		Car queryCarInfo = carService.queryCarInfo(pcar);

		mv.addObject("carInfo", queryCarInfo);
		mv.addObject("repairInfo", queryRepair);
		return mv;
	}

	/**
	 * 新增维修记录和项目信息
	 * 
	 * @Title: addRepairAndItem
	 * @Description:
	 * @author 桑越
	 * @date 2015-12-13 上午11:46:10
	 * @param @param repair
	 * @param @return 设定文件
	 * @return Object 返回类型
	 * @throws
	 * @version V1.0
	 */
	@RequestMapping("/addRepairAndItem.do")
	@ResponseBody
	@LogAnnotation(option = "新增维修记录和项目信息", optionType = OptionType.add)
	public Object addRepairAndItem(String conditionPara, HttpSession session) {
		JSONObject conditionParaJson = JSONObject.fromObject(conditionPara);// 接收的全部参数json对象

		Integer carId = conditionParaJson.getInt("carId");// 获取汽车id
		String repairNum = conditionParaJson.getString("repairNum");// 获取维修单号
		String serviceAdviser = conditionParaJson.getString("serviceAdviser");// 维修顾问
		String repairMan = conditionParaJson.getString("repairMan");// 维修工
		String repairTimeStr = conditionParaJson.getString("repairTime");// 维修时间
		String otherBz = conditionParaJson.getString("otherBz");// 维修时间
		Double warrantyStartMiles = conditionParaJson
				.getDouble("warrantyStartMiles");// 行驶里程
		String repairPayment = conditionParaJson.getString("repairPayment");// 付款方式
		double repairActualSum = 0;// 实收总额
		String repairActualSumStr = conditionParaJson
				.getString("repairActualSum");
		if (!StringUtils.isEmpty(repairActualSumStr)) {
			repairActualSum = Double.parseDouble(repairActualSumStr);
		}

		JSONArray itemArrayJson = conditionParaJson.getJSONArray("itemArray");// 维修项目json数组
		@SuppressWarnings({ "deprecation", "unchecked" })
		List<RepairItem> itemList = JSONArray.toList(itemArrayJson,
				RepairItem.class);// 维修项目List

		Message message = new Message();
		if (carId == null) {
			message.setCode(errorCode);
			message.setMsg("新增维修记录和项目信息时，汽车id不可为空");
			return message;
		}
		if (repairTimeStr == null || "".equals(repairTimeStr)) {
			message.setCode(errorCode);
			message.setMsg("新增维修记录和项目信息时，维修时间不可为空");
			return message;
		}
		if (repairPayment == null || "".equals(repairPayment)) {
			message.setCode(errorCode);
			message.setMsg("新增维修记录和项目信息时，付款方式不可为空");
			return message;
		}
		if (itemList == null || itemList.size() == 0) {
			message.setCode(errorCode);
			message.setMsg("新增维修记录和项目信息时，维修项目列表不可为空");
			return message;
		}
		try {
			// 新增维修记录和项目信息
			Repair prepair = new Repair();
			prepair.setCompanyId(Util.getCompanyId(session));
			prepair.setCarId(carId);
			prepair.setRepairNum(repairNum);
			prepair.setServiceAdviser(serviceAdviser);
			prepair.setRepairMan(repairMan);
			prepair.setWarrantyStartMiles(BigDecimal
					.valueOf(warrantyStartMiles));// 行驶里程
			prepair.setRepairPayment(repairPayment);
			prepair.setRepairTimeStr(repairTimeStr);
			prepair.setRepairTime(DateUtils.format(repairTimeStr,
					DateUtils.formatStr_yyyyMMddHHmmss));
			prepair.setItemList(itemList);
			prepair.setOtherBz(otherBz);
			prepair.setRepairActualSum(BigDecimal.valueOf(repairActualSum));
			Map<String, Object> resultMap = repairService
					.addRepairAndItem(prepair);

			String code = (String) resultMap.get("code");
			String msg = (String) resultMap.get("msg");

			if (!successCode.equals(code)) {
				message.setCode(code);
				message.setMsg(msg);
				return message;
			} else {
				message.setCode(successCode);
				message.setMsg("");
				return message;
			}

		} catch (Exception e) {
			message.setCode(errorCode);
			message.setMsg("新增维修记录和项目信息失败，系统错误，请刷新重试！");
			return message;
		}

	}

	/**
	 * 修改维修记录和项目信息
	 * 
	 * @param conditionPara
	 * @return
	 * @version V1.1 桑越2015年12月19日 修改注释
	 */
	@SuppressWarnings({ "unchecked" })
	@RequestMapping("/updateRepairAndItem.do")
	@ResponseBody
	@LogAnnotation(option = "修改维修记录和项目信息", optionType = OptionType.edit)
	public Object updateRepairAndItem(String conditionPara) {
		JSONObject conditionParaJson = JSONObject.fromObject(conditionPara);// 接收的全部参数json对象

		Integer carId = conditionParaJson.getInt("carId");// 获取汽车id
		Integer repairId = conditionParaJson.getInt("repairId");// 获取维修id
		String repairNum = conditionParaJson.getString("repairNum");// 获取维修单号
		String serviceAdviser = conditionParaJson.getString("serviceAdviser");// 维修顾问
		String repairMan = conditionParaJson.getString("repairMan");// 维修工
		String repairTimeStr = conditionParaJson.getString("repairTime");// 维修时间
		Double warrantyStartMiles = conditionParaJson
				.getDouble("warrantyStartMiles");// 行驶里程
		String repairPayment = conditionParaJson.getString("repairPayment");// 付款方式
		String otherBz = conditionParaJson.getString("otherBz");
		double repairActualSum = 0;// 实收总额
		String repairActualSumStr = conditionParaJson
				.getString("repairActualSum");
		if (!StringUtils.isEmpty(repairActualSumStr)) {
			repairActualSum = Double.parseDouble(repairActualSumStr);
		}

		JSONArray itemArrayJson = conditionParaJson.getJSONArray("itemArray");// 维修项目json数组
		@SuppressWarnings("deprecation")
		List<RepairItem> itemList = JSONArray.toList(itemArrayJson,
				RepairItem.class);// 维修项目List

		Message message = new Message();
		if (carId == null) {
			message.setCode(errorCode);
			message.setMsg("修改维修记录和项目信息时，汽车id不可为空");
			return message;
		}
		if (repairId == null) {
			message.setCode(errorCode);
			message.setMsg("修改维修记录和项目信息时，维修id不可为空");
			return message;
		}
		if (repairTimeStr == null || "".equals(repairTimeStr)) {
			message.setCode(errorCode);
			message.setMsg("修改维修记录和项目信息时，维修时间不可为空");
			return message;
		}
		if (repairPayment == null || "".equals(repairPayment)) {
			message.setCode(errorCode);
			message.setMsg("修改维修记录和项目信息时，付款方式不可为空");
			return message;
		}
		if (itemList == null || itemList.size() == 0) {
			message.setCode(errorCode);
			message.setMsg("修改维修记录和项目信息时，维修项目列表不可为空");
			return message;
		}
		try {
			// 删除维修详细记录
			RepairItem pitem = new RepairItem();
			pitem.setRepairId(repairId);
			repairItemService.delRepairItemInfo(pitem);

			// 修改维修记录，重新新增详细记录
			Repair prepair = new Repair();
			prepair.setCarId(carId);
			prepair.setRepairId(repairId);
			prepair.setRepairNum(repairNum);
			prepair.setServiceAdviser(serviceAdviser);
			prepair.setRepairMan(repairMan);
			prepair.setWarrantyStartMiles(BigDecimal
					.valueOf(warrantyStartMiles));// 行驶里程
			prepair.setRepairPayment(repairPayment);
			prepair.setRepairTimeStr(repairTimeStr);
			prepair.setRepairActualSum(BigDecimal.valueOf(repairActualSum));
			prepair.setRepairTime(DateUtils.format(repairTimeStr,
					DateUtils.formatStr_yyyyMMddHHmmss));
			prepair.setItemList(itemList);
			prepair.setOtherBz(otherBz);
			Map<String, Object> resultMap = repairService
					.updateRepairAndItem(prepair);

			String code = (String) resultMap.get("code");
			String msg = (String) resultMap.get("msg");

			if (!successCode.equals(code)) {
				message.setCode(code);
				message.setMsg(msg);
				return message;
			} else {
				message.setCode(successCode);
				message.setMsg("");
				return message;
			}

		} catch (Exception e) {
			message.setCode(errorCode);
			message.setMsg("修改维修记录和项目信息失败，系统错误，请刷新重试！");
			return message;
		}

	}

	/**
	 * 删除维修记录
	 * 
	 * @param repair
	 * @return
	 * @version V1.1 桑越2015年12月19日 修改为对维修记录和维修项目的同时删除
	 */
	@RequestMapping("/delRepairInfoAndItem.do")
	@ResponseBody
	@LogAnnotation(option = "删除维修记录", optionType = OptionType.delete)
	public Object delRepairInfoAndItem(Repair repair) {
		Integer repairId = repair.getRepairId();// 维修id

		Message message = new Message();
		Map<String, Object> returnMap = new HashMap<String, Object>();

		// 删除维修记录和维修项目
		Repair particle = new Repair();
		particle.setRepairId(repairId);
		Map<String, Object> resultMap = repairService
				.delRepairInfoAndItem(particle);

		String code = (String) resultMap.get("code");
		String msg = (String) resultMap.get("msg");
		if (!successCode.equals(code)) {// 成功
			message.setCode(code);
			message.setMsg(msg);
			returnMap.put("message", message);
			return returnMap;
		}
		message.setCode(successCode);
		message.setMsg("");
		returnMap.put("message", message);
		return returnMap;
	}

	/**
	 * 将特殊类型转为springmvc可以识别的对象类型（比如int、日期、double、long类型）
	 * 
	 * @Title: initBinder
	 * @Description:
	 * @author 桑越
	 * @date 2015-11-10 上午11:15:26
	 * @param @param binder 设定文件
	 * @return void 返回类型
	 * @throws
	 * @version V1.0
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// 日期处理
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(df, true));
	}

	/**
	 * 查询维修记录列表
	 * 
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/queryRepairList.do")
	@ResponseBody
	// @LogAnnotation(option = "查询维修记录和项目信息", optionType = OptionType.query)
	public List<Repair> queryRepairList(Integer carid, String startTime,
			String endTime, HttpSession session) throws ParseException {
		// Integer carid = (Integer) session.getAttribute("carId");
		// 查询汽车基本信息
		Repair repair = new Repair();
		repair.setCarId(carid);
		repair.setCompanyId(Util.getCompanyId(session));
		repair.setIsdelete("0");
		// 处理时间
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if (!StringUtils.isEmpty(startTime)) {
			Date startTimeDate = df.parse(startTime);
			repair.setStartTime(startTimeDate);
		}
		if (!StringUtils.isEmpty(endTime)) {
			Date endTimeDate = df.parse(endTime);
			repair.setEndTime(endTimeDate);
		}
		List<Repair> repairList = repairService.queryRepairList(repair);

		return repairList;
	}

}
