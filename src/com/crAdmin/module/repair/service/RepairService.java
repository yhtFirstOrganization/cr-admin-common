package com.crAdmin.module.repair.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crAdmin.bean.Car;
import com.crAdmin.bean.Repair;
import com.crAdmin.bean.RepairItem;
import com.crAdmin.module.car.service.CarService;
import com.crAdmin.module.repair.dao.RepairMapper;
import com.crAdmin.module.repairItem.service.RepairItemService;
import com.crAdmin.util.BaseController;
import com.crAdmin.util.Util;

/**
 * 维修记录相关service
 * 
 * @ClassName: RepairService
 * @Description:
 * @author 桑越
 * @date 2015-12-8 下午9:04:53
 * @version V1.0
 */
@Service("repairService")
@Transactional(rollbackFor = Exception.class)
public class RepairService {
	@Autowired
	private RepairMapper repairMapper;
	@Autowired
	private CarService carService;
	@Autowired
	private RepairItemService repairItemService;

	/**
	 * 获取维修记录列表
	 * 
	 * @Title: queryRepairList
	 * @Description:
	 * @author 桑越
	 * @date 2015-12-8 下午9:05:55
	 * @param @param repair
	 * @param @return 设定文件
	 * @return List<Repair> 返回类型
	 * @throws
	 * @version V1.0
	 */
	public List<Repair> queryRepairList(Repair repair) {
		return repairMapper.queryRepairList(repair);
	}

	/**
	 * 查询维修记录信息
	 * 
	 * @Title: queryRepairInfo
	 * @Description:
	 * @author 桑越
	 * @date 2015-12-18 下午10:34:51
	 * @param @param repair
	 * @param @return 设定文件
	 * @return Repair 返回类型
	 * @throws
	 * @version V1.0
	 */
	public Repair queryRepairInfo(Repair repair) {
		return repairMapper.queryRepairInfo(repair);
	}

	/**
	 * 修改维修记录信息
	 * 
	 * @Title: updateRepairInfo
	 * @Description:
	 * @author 桑越
	 * @date 2015-12-13 下午3:36:14
	 * @param @param repair
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @throws
	 * @version V1.0
	 */
	public int updateRepairInfo(Repair repair) {
		return repairMapper.updateRepairInfo(repair);
	}

	/**
	 * 新增维修记录信息
	 * 
	 * @Title: addRepairInfo
	 * @Description:
	 * @author 桑越
	 * @date 2015-12-8 下午9:06:31
	 * @param @param repair
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @throws
	 * @version V1.0
	 */
	public int addRepairInfo(Repair repair) {
		return repairMapper.addRepairInfo(repair);
	}

	/**
	 * 新增维修记录和项目信息
	 * 
	 * @Title: addRepairAndItem
	 * @Description:
	 * @author 桑越
	 * @date 2015-12-13 下午12:00:47
	 * @param @param repair
	 * @param @return 设定文件
	 * @return Map<String,Object> 返回类型
	 * @throws
	 * @version V1.0
	 */
	public Map<String, Object> addRepairAndItem(Repair repair) {
		Integer carId = repair.getCarId();// 获取汽车id
		String repairNum = repair.getRepairNum();// 获取维修单号
		String serviceAdviser = repair.getServiceAdviser();// 维修顾问
		String repairMan = repair.getRepairMan();// 维修工
		String repairTimeStr = repair.getRepairTimeStr();// 维修时间string
		Date repairTime = repair.getRepairTime();// 维修时间
		String companyId = repair.getCompanyId();
		String otherBz = repair.getOtherBz();
		BigDecimal repairActualSum = repair.getRepairActualSum();// 实收总额
		BigDecimal warrantyStartMiles = repair.getWarrantyStartMiles();// 行驶里程
		String repairPayment = repair.getRepairPayment();// 付款方式
		List<RepairItem> itemList = repair.getItemList();// 维修项目List

		Map<String, Object> returnMap = new HashMap<String, Object>();
		// 查询汽车信息
		Car pcar = new Car();
		pcar.setCarId(carId);
		pcar.setIsdelete("0");
		Car queryCar = carService.queryCarInfo(pcar);

		if (queryCar == null) {
			returnMap.put("code", BaseController.code0012);
			returnMap.put("msg", "汽车信息不存在，请刷新重试");
			return returnMap;
		}

		// 新增维修记录信息
		Repair prepair = new Repair();
		prepair.setCarId(carId);
		prepair.setRepairNum(repairNum);
		prepair.setRepairName(repairTimeStr + "维修记录");
		prepair.setServiceAdviser(serviceAdviser);
		prepair.setRepairMan(repairMan);
		prepair.setRepairTime(repairTime);
		prepair.setWarrantyStartMiles(warrantyStartMiles);
		prepair.setRepairPayment(repairPayment);
		prepair.setRepairActualSum(repairActualSum);
		prepair.setIsdelete("0");
		prepair.setCompanyId(companyId);
		prepair.setOtherBz(otherBz);
		int i = addRepairInfo(prepair);
		if (i == 0) {
			returnMap.put("code", BaseController.code0013);
			returnMap.put("msg", "新增维修记录失败，请刷新重试");
			return returnMap;
		}
		Integer repairId = prepair.getRepairId();// 返回自增主键

		double repairSum = addRepairItemInfo(repairId, itemList);// 维修总额

		// 修改维修记录信息（维修总额）
		Repair prepair1 = new Repair();
		prepair1.setRepairId(repairId);
		prepair1.setRepairSum(BigDecimal.valueOf(Util.round(repairSum, 2)));
		int k = updateRepairInfo(prepair1);
		if (k == 0) {
			returnMap.put("code", BaseController.code0013);
			returnMap.put("msg", "新增维修记录失败，请刷新重试");
			return returnMap;
		}

		returnMap.put("code", BaseController.successCode);
		returnMap.put("msg", "新增维修记录和项目信息成功");
		return returnMap;
	}

	/**
	 * 修改维修记录和项目信息
	 * 
	 * @param repair
	 * @return
	 */
	public Map<String, Object> updateRepairAndItem(Repair repair) {
		Integer carId = repair.getCarId();// 获取汽车id
		Integer repairId = repair.getRepairId();// 维修记录id
		String repairNum = repair.getRepairNum();// 获取维修单号
		String serviceAdviser = repair.getServiceAdviser();// 维修顾问
		String repairMan = repair.getRepairMan();// 维修工
		String repairTimeStr = repair.getRepairTimeStr();// 维修时间string
		Date repairTime = repair.getRepairTime();// 维修时间
		BigDecimal repairActualSum = repair.getRepairActualSum();// 实收金额
		BigDecimal warrantyStartMiles = repair.getWarrantyStartMiles();// 行驶里程
		String repairPayment = repair.getRepairPayment();// 付款方式
		String otherBz = repair.getOtherBz();
		List<RepairItem> itemList = repair.getItemList();// 维修项目List

		Map<String, Object> returnMap = new HashMap<String, Object>();
		// 查询汽车信息
		Car pcar = new Car();
		pcar.setCarId(carId);
		pcar.setIsdelete("0");
		Car queryCar = carService.queryCarInfo(pcar);

		if (queryCar == null) {
			returnMap.put("code", BaseController.code0012);
			returnMap.put("msg", "汽车信息不存在，请刷新重试");
			return returnMap;
		}

		// 修改维修记录信息
		Repair prepair = new Repair();
		prepair.setCarId(carId);
		prepair.setRepairNum(repairNum);
		prepair.setRepairName(repairTimeStr + "维修记录");
		prepair.setServiceAdviser(serviceAdviser);
		prepair.setRepairMan(repairMan);
		prepair.setRepairTime(repairTime);
		prepair.setWarrantyStartMiles(warrantyStartMiles);
		prepair.setRepairPayment(repairPayment);
		prepair.setRepairActualSum(repairActualSum);
		prepair.setIsdelete("0");
		prepair.setRepairId(repairId);
		prepair.setOtherBz(otherBz);
		int i = updateRepairInfo(prepair);
		if (i == 0) {
			returnMap.put("code", BaseController.code0017);
			returnMap.put("msg", "修改维修记录失败，请刷新重试");
			return returnMap;
		}

		// 新增维修项目信息并将项目总额返回
		double repairSum = addRepairItemInfo(repairId, itemList);// 维修总额

		// 修改维修记录信息（维修总额）
		Repair prepair1 = new Repair();
		prepair1.setRepairId(repairId);
		prepair1.setRepairSum(BigDecimal.valueOf(Util.round(repairSum, 2)));
		int k = updateRepairInfo(prepair1);
		if (k == 0) {
			returnMap.put("code", BaseController.code0017);
			returnMap.put("msg", "修改维修记录失败，请刷新重试");
			return returnMap;
		}

		returnMap.put("code", BaseController.successCode);
		returnMap.put("msg", "新增维修记录和项目信息成功");
		return returnMap;
	}

	/**
	 * 新增维修项目列表信息
	 * 
	 * @param repair
	 * @return
	 */
	public double addRepairItemInfo(Integer repairId, List<RepairItem> itemList) {
		double repairSum = 0;// 维修总额
		// 新增维修项目列表信息
		for (RepairItem queryItem : itemList) {
			String itemNum = queryItem.getItemNum();// 项目编号
			String itemDes = queryItem.getItemDes();// 项目描述
			double itemPrice = queryItem.getItemPrice() == null ? 0 : queryItem
					.getItemPrice().doubleValue();// 项目单价
			double stockPriceIncome = queryItem.getStockPriceIncome() == null ? 0
					: queryItem.getStockPriceIncome().doubleValue();// 项目单价
			double itemQuantity = queryItem.getItemQuantity() == null ? 0
					: queryItem.getItemQuantity().doubleValue();// 项目数量
			double workHoursCost = queryItem.getWorkHoursCost() == null ? 0
					: queryItem.getWorkHoursCost().doubleValue();// 工时费
			double itemSum = Util.round(itemPrice * itemQuantity
					+ workHoursCost, 2);// 项目总额（四舍五入）
			String itemUnit = queryItem.getItemUnit();// 项目单位
			String replaceReason = queryItem.getReplaceReason();// 更换原因 字段显示改为
																// 配件编号
			Integer stockId = queryItem.getStockId();

			// 新增维修项目
			RepairItem pitem = new RepairItem();
			pitem.setItemNum(itemNum);
			pitem.setRepairId(repairId);
			pitem.setItemDes(itemDes);
			pitem.setItemPrice(BigDecimal.valueOf(itemPrice));
			pitem.setStockPriceIncome(BigDecimal.valueOf(stockPriceIncome));
			pitem.setItemQuantity(BigDecimal.valueOf(itemQuantity));
			pitem.setItemUnit(itemUnit);
			pitem.setItemSum(BigDecimal.valueOf(itemSum));
			pitem.setReplaceReason(replaceReason);
			pitem.setWorkHoursCost(BigDecimal.valueOf(workHoursCost));
			pitem.setStockId(stockId);
			pitem.setIsdelete("0");
			repairItemService.addRepairItemInfo(pitem);
			repairSum += itemSum;// 累加项目总额
		}

		return repairSum;
	}

	/**
	 * 删除维修记录
	 * 
	 * @param repair
	 * @return
	 * @version V1.1桑越2015年12月19日 修改删除维修记录的相关
	 */
	public Map<String, Object> delRepairInfoAndItem(Repair repair) {
		Integer repairId = repair.getRepairId();// 维修记录id

		Map<String, Object> returnMap = new HashMap<String, Object>();
		// 删除维修记录
		int i = repairMapper.delRepairInfo(repair);

		if (i <= 0) {
			returnMap.put("code", BaseController.code0015);
			returnMap.put("msg", "删除维修记录失败，请刷新重试");
			return returnMap;
		}
		// 删除维修项目信息
		RepairItem pitem = new RepairItem();
		pitem.setRepairId(repairId);
		int j = repairItemService.delRepairItemInfo(pitem);

		if (j <= 0) {
			returnMap.put("code", BaseController.code0016);
			returnMap.put("msg", "删除维修项目信息失败，请刷新重试");
			return returnMap;
		}

		returnMap.put("code", BaseController.successCode);
		returnMap.put("msg", "");
		return returnMap;
	}

}
