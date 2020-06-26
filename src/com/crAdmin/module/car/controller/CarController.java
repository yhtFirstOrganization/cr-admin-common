package com.crAdmin.module.car.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.crAdmin.bean.Car;
import com.crAdmin.bean.Message;
import com.crAdmin.bean.Region;
import com.crAdmin.bean.Repair;
import com.crAdmin.module.car.service.CarService;
import com.crAdmin.module.region.service.RegionService;
import com.crAdmin.module.repair.service.RepairService;
import com.crAdmin.util.BaseController;
import com.crAdmin.util.DateUtils;
import com.crAdmin.util.Util;
import com.crAdmin.util.annotation.LogAnnotation;
import com.crAdmin.util.annotation.OptionType;

/**
 * 汽车相关controller
 * 
 * @ClassName: CarController
 * @Description:
 * @author 桑越
 * @date 2015-12-7 下午11:14:56
 * @version V1.0
 */
@Controller("carController")
@RequestMapping("/car")
public class CarController extends BaseController {
	@Autowired
	private CarService carService;
	@Autowired
	private RegionService regionService;
	@Autowired
	private RepairService repairService;

	/**
	 * 跳转到汽车列表页面
	 * 
	 * @Title: toCarList
	 * @Description:
	 * @author 桑越
	 * @date 2015-12-6 下午4:07:24
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws @version V1.0
	 */
	@RequestMapping("/toCarList.do")
	public ModelAndView toCarList() {
		ModelAndView mv = new ModelAndView("car/carList.ftl");
		return mv;
	}

	/**
	 * 跳转到新增汽车页面
	 * 
	 * @Title: toAddCar
	 * @Description:
	 * @author 桑越
	 * @date 2015-12-6 下午4:08:44
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws @version V1.0
	 */
	@RequestMapping("/toAddCar.do")
//	@LogAnnotation(option = "进入新增汽车页面", optionType = OptionType.pageIn)
	public ModelAndView toAddCar() {
		ModelAndView mv = new ModelAndView("car/addCar.ftl");

		// 初始化查询省
		Region country = new Region();
		country.setRegionId(country_code);
		List<Region> provs = regionService.getChildList(country);
		mv.addObject("provs", provs);
		return mv;
	}

	/**
	 * 跳转到修改汽车页面
	 * 
	 * @Title: toEditCar
	 * @Description:
	 * @author 桑越
	 * @date 2015-12-17 下午10:21:37
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws @version V1.0
	 */
	@RequestMapping("/toEditCar.do")
	public ModelAndView toEditCar(Integer carId, HttpSession session) {
		Car pcar = new Car();
		pcar.setCarId(carId);
		pcar.setIsdelete("0");// 有效的
		pcar.setCompanyId(Util.getCompanyId(session));
		Car queryCar = carService.queryCarInfo(pcar);

		ModelAndView mv = new ModelAndView("car/editCar.ftl");
		String ownerCity = queryCar.getOwnerCity();// 车主所在城市
		String ownerAddress = queryCar.getOwnerAddress();// 车主详细位置
		// 处理地址
		String qxid = "";
		String gid = "";
		String pid = "";
		if (ownerCity != null && !"".equals(ownerCity)) {
			Region region = new Region();
			region.setRegionId(ownerCity);
			Map<String, String> cityMap = regionService.getCityInfo(region);

			if (cityMap != null && cityMap.size() > 0) {
				qxid = ownerCity;
				gid = cityMap.get("gid");
				pid = cityMap.get("pid");
				String pname = cityMap.get("pname");
				String gname = cityMap.get("gname");
				String cname = cityMap.get("cname");
				if (ownerAddress.length() > (pname.length() + gname.length() + cname
						.length())) {
					ownerAddress = ownerAddress.substring(pname.length()
							+ gname.length() + cname.length());
					queryCar.setOwnerAddress(ownerAddress);
				}
			}
		}
		mv.addObject("carInfo", queryCar);
		mv.addObject("gid", gid);
		mv.addObject("pid", pid);
		mv.addObject("qxid", qxid);

		// 初始化查询省
		Region country = new Region();
		country.setRegionId(country_code);
		List<Region> provs = regionService.getChildList(country);
		mv.addObject("provs", provs);
		return mv;
	}

	/**
	 * ajax校验车牌号等信息
	 * 
	 * @Title: checkLicensePlateNum
	 * @Description:
	 * @author 桑越
	 * @date 2015-12-6 下午9:55:52
	 * @param @param article
	 * @param @return 设定文件
	 * @return Object 返回类型
	 * @throws @version V1.0
	 * @version V1.1桑越2016年1月3日 增加对修改汽车时车牌号的校验
	 */
	@RequestMapping("/checkLicensePlateNum.do")
	@ResponseBody
	public Object checkLicensePlateNum(Car car, String editType,
			HttpSession session) {
		Integer carId = car.getCarId();// 汽车id
		String licensePlateNum = car.getLicensePlateNum();// 车牌号

		Map<String, Object> returnMap = new HashMap<String, Object>();
		if ("1".equals(editType)) {// 修改汽车
			Car pcar = new Car();
			pcar.setCarId(carId);
			pcar.setLicensePlateNum(licensePlateNum);
			pcar.setCompanyId(Util.getCompanyId(session));
			pcar.setIsdelete("0");// 设置查询有效的汽车信息
			List<Car> otherCarList = carService.queryOtherCarList(pcar);
			if (otherCarList == null || otherCarList.size() == 0) {// 没有相关汽车信息，可以修改
				returnMap.put("status", "y");
				returnMap.put("info", "");
				return returnMap;
			} else {
				returnMap.put("status", "n");
				returnMap.put("info", "已存在该车牌号相关汽车信息，不可修改！");
				return returnMap;
			}
		} else {// 新增汽车
			Car pcar = new Car();
			pcar.setLicensePlateNum(licensePlateNum);
			pcar.setCompanyId(Util.getCompanyId(session));
			pcar.setIsdelete("0");// 设置查询有效的汽车信息
			List<Car> carList = carService.queryCarList(pcar);
			if (carList == null || carList.size() == 0) {// 没有相关汽车信息，可以新增
				returnMap.put("status", "y");
				returnMap.put("info", "");
				return returnMap;
			} else {
				returnMap.put("status", "n");
				returnMap.put("info", "已存在该车牌号相关汽车信息，不可新增！");
				return returnMap;
			}
		}
	}

	/**
	 * 新增汽车信息
	 * 
	 * @Title: addArticleInfo
	 * @Description:
	 * @author 桑越
	 * @date 2015-12-6 下午11:51:51
	 * @param @param car
	 * @param @return 设定文件
	 * @return Object 返回类型
	 * @throws @version V1.0
	 */
	@RequestMapping("/addCar.do")
	@ResponseBody
	@LogAnnotation(option = "新增汽车信息", optionType = OptionType.add)
	public Object addCarInfo(Car car, HttpSession session) {
		/*
		 * String carType = car.getCarType(); String licensePlateNum =
		 * car.getLicensePlateNum(); String carChassisNum =
		 * car.getCarChassisNum(); String engineNum = car.getEngineNum();
		 * BigDecimal warrantyStartMiles = car.getWarrantyStartMiles(); String
		 * ownerName = car.getOwnerName(); String ownerSex = car.getOwnerSex();
		 * String ownerCity = car.getOwnerCity(); String ownerAddress =
		 * car.getOwnerAddress(); String ownerMobile = car.getOwnerMobile();
		 * String ownerFax = car.getOwnerFax(); String ownerNum =
		 * car.getOwnerNum(); String isdelete = car.getIsdelete();
		 */

		// 新增汽车校验车牌号
		Message message = new Message();
		@SuppressWarnings("unchecked")
		Map<String, Object> checkResult = (Map<String, Object>) checkLicensePlateNum(
				car, "0", session);
		String status = (String) checkResult.get("status");
		String info = (String) checkResult.get("info");

		if ("n".equals(status)) {
			message.setCode(errorCode);
			message.setMsg(info);
			return message;
		}

		car.setCompanyId(Util.getCompanyId(session));
		// 新增汽车相关信息
		car.setCreateTime(DateUtils.getCurrDate());
		int i = carService.addCarInfo(car);

		if (i == 0) {// 新增失败
			message.setCode(code0020);
			message.setMsg("新增汽车信息失败，请刷新重试！");
			return message;
		}

		message.setCode(successCode);
		message.setMsg("");
		return message;
	}

	/**
	 * 修改汽车信息
	 * 
	 * @Title: updateCarInfo
	 * @Description:
	 * @author 桑越
	 * @date 2015-12-18 下午8:11:11
	 * @param @param car
	 * @param @return 设定文件
	 * @return Object 返回类型
	 * @throws @version V1.0
	 */
	@RequestMapping("/updateCarInfo.do")
	@ResponseBody
	@LogAnnotation(option = "修改汽车信息", optionType = OptionType.edit)
	public Object updateCarInfo(Car car) {
		Message message = new Message();

		// 修改汽车相关信息
		int i = carService.updateCarInfo(car);

		if (i == 0) {// 修改失败
			message.setCode(code0019);
			message.setMsg("修改汽车信息失败，请刷新重试！");
			return message;
		}

		message.setCode(successCode);
		message.setMsg("");
		return message;
	}

	/**
	 * 删除汽车信息
	 * 
	 * @param car
	 * @return
	 * @version V1.1 桑越2015年12月19日 优化代码
	 */
	@RequestMapping("/delCarInfo.do")
	@ResponseBody
	@LogAnnotation(option = "删除汽车信息", optionType = OptionType.delete)
	public Object delCarInfo(Car car) {
		Integer carId = car.getCarId();// 文章id
		// 查询维修记录列表
		Repair repair = new Repair();
		repair.setCarId(carId);
		repair.setIsdelete("0");
		List<Repair> repairList = repairService.queryRepairList(repair);

		// 循环记录列表并删除维修记录和维修项目信息
		for (Repair perRepair : repairList) {
			repairService.delRepairInfoAndItem(perRepair);
		}

		// 删除汽车信息
		Car particle = new Car();
		particle.setCarId(carId);
		int i = carService.delCarInfo(particle);

		Message message = new Message();

		Map<String, Object> returnMap = new HashMap<String, Object>();
		if (i == 0) {
			message.setCode(code0018);
			message.setMsg("删除汽车信息失败，请刷新重试！");
			returnMap.put("message", message);
			return returnMap;
		}
		message.setCode(successCode);
		message.setMsg("");
		returnMap.put("message", message);
		return returnMap;
	}

	/**
	 * 查询汽车列表
	 * 
	 * @param provId
	 * @return
	 */
	@RequestMapping("/queryCarList.do")
	@ResponseBody
//	@LogAnnotation(option = "查询汽车列表信息", optionType = OptionType.query)
	public Object queryCarList(HttpSession session) {
		Car car = new Car();
		car.setCompanyId(Util.getCompanyId(session));
		car.setIsdelete("0");
		List<Car> list = carService.queryCarList(car);

		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("data", list);

		return returnMap;
	}

	@RequestMapping("/queryCarInfo.do")
	@ResponseBody
//	@LogAnnotation(option = "查询汽车信息", optionType = OptionType.query)
	public Object queryCarInfo(Integer carId, HttpSession session) {
		Car pcar = new Car();
		pcar.setCompanyId(Util.getCompanyId(session));
		pcar.setCarId(carId);
		pcar.setIsdelete("0");
		Car queryCarInfo = carService.queryCarInfo(pcar);
		return queryCarInfo;
	}

}
