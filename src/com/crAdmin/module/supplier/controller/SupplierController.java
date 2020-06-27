package com.crAdmin.module.supplier.controller;

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
import com.crAdmin.bean.Message;
import com.crAdmin.bean.PersonInfo;
import com.crAdmin.bean.Region;
import com.crAdmin.module.region.service.RegionService;
import com.crAdmin.module.supplier.service.SupplierService;
import com.crAdmin.util.BaseController;
import com.crAdmin.util.DateUtils;
import com.crAdmin.util.Util;

/**
 * 供应商相关controller
 * 
 * @author sun
 * 
 */
@Controller("supplierController")
@RequestMapping("/supplier")
public class SupplierController extends BaseController {
	@Autowired
	private SupplierService supplierService;
	@Autowired
	private RegionService regionService;

	/**
	 * 
	 * @Description: 跳转到供应商列表页面
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 * @author sun
	 * @date 2017-10-27
	 */
	@RequestMapping("/toSupplierList.do")
	public String toSupplierList(Model model) {
		return "supplier/supplierList.ftl";
	}

	/**
	 * 
	 * @Description: 查询供应商信息
	 * @param @param session
	 * @param @return
	 * @return Object
	 * @throws
	 * @author sun
	 * @date 2017-10-27
	 */
	@RequestMapping("/querySupplierList.do")
	@ResponseBody
	// @LogAnnotation(option = "查询供应商列表信息", optionType = OptionType.query)
	public Object queryCarList(HttpSession session) {
		PersonInfo personInfo = new PersonInfo();
		personInfo.setCompanyId(Util.getCompanyId(session));
		personInfo.setIsdelete("0");
		personInfo.setPerType("1");// 1为供应商，2为客户
		List<PersonInfo> list = supplierService.querySupplierList(personInfo);

		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("data", list);

		return returnMap;
	}

	/**
	 * 
	 * @Description: 跳转到供应商新增页面
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 * @author sun
	 * @date 2017-10-27
	 */
	@RequestMapping("/toAddSupplier.do")
	// @LogAnnotation(option = "进入新增汽车页面", optionType = OptionType.pageIn)
	public String toAddCar(Model model, PersonInfo info, HttpSession session) {
		PersonInfo personInfo = new PersonInfo();
		if (info != null && info.getPerId() != null) {
			// 获取供应商信息
			List<PersonInfo> supplierInfo = supplierService
					.querySupplierList(info);
			if (supplierInfo != null && supplierInfo.size() > 0
					&& supplierInfo.get(0) != null) {
				personInfo = supplierInfo.get(0);
			}

		} else {
			// 获取最大供应商编号
			Integer perNum = supplierService.getNewPerNum(Util
					.getCompanyId(session));
			personInfo.setPerNum(perNum == null ? 1 : perNum + 1);
		}

		String ownerCity = personInfo.getPerCity();// 供应的所在城市
		String ownerAddress = personInfo.getPerAddress();// 供应商详细位置
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
					personInfo.setPerAddress(ownerAddress);
				}
			}
		}
		model.addAttribute("supplierInfo", personInfo);

		model.addAttribute("gid", gid);
		model.addAttribute("pid", pid);
		model.addAttribute("qxid", qxid);

		// 初始化查询省
		Region country = new Region();
		country.setRegionId(country_code);
		List<Region> provs = regionService.getChildList(country);
		model.addAttribute("provs", provs);
		return "supplier/addSupplier.ftl";
	}

	/**
	 * 
	 * @Description: 保存供应商信息
	 * @param @param personInfo
	 * @param @param session
	 * @param @return
	 * @return Object
	 * @throws
	 * @author sun
	 * @date 2017-10-27
	 */
	@RequestMapping("/addSupplier.do")
	@ResponseBody
	// @LogAnnotation(option = "新增供应商信息", optionType = OptionType.add)
	public Object addCarInfo(PersonInfo personInfo, HttpSession session) {
		Message message = new Message();
		if (personInfo == null || StringUtils.isBlank(personInfo.getPerName())
				|| StringUtils.isBlank(personInfo.getPerTel())) {
			message.setCode(code0021);
			message.setMsg("输入参数有误，请检查！");
			return message;
		}
		int i = 0;
		if (personInfo.getPerId() == null) {
			personInfo.setCompanyId(Util.getCompanyId(session));
			// 新增汽车相关信息
			personInfo.setCreateTime(DateUtils.getCurrDate());
			personInfo.setPerType("1");
			i = supplierService.addPerInfo(personInfo);
			message.setMsg("新增供应商成功！");
		} else {
			personInfo.setUpdateTime(DateUtils.getCurrDate());
			i = supplierService.update(personInfo);
			message.setMsg("编辑供应商成功！");
		}
		if (i < 1) {// 新增失败
			message.setCode(code0021);
			message.setMsg("新增供应商信息失败，请刷新重试！");
			return message;
		}

		message.setCode(successCode);
		return message;
	}

	/**
	 * 
	 * @Description: 编辑供应商
	 * @param @param model
	 * @param @param info
	 * @param @return
	 * @return String
	 * @throws
	 * @author sun
	 * @date 2017-10-27
	 */
	@RequestMapping("/delSupplierInfo.do")
	@ResponseBody
	public Object delSupplierInfo(Model model, PersonInfo info) {
		// 获取供应商信息
		List<PersonInfo> supplierInfo = supplierService.querySupplierList(info);
		PersonInfo personInfo = new PersonInfo();
		if (supplierInfo != null && supplierInfo.size() > 0
				&& supplierInfo.get(0) != null) {
			personInfo = supplierInfo.get(0);
		}
		Message message = new Message();
		int i = supplierService.delete(personInfo);
		if (i < 1) {
			message.setCode(code0023);
			message.setMsg("删除供应商信息失败，请刷新重试！");
		}
		message.setCode(successCode);
		message.setMsg("删除供应商信息成功！");
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("message", message);
		return returnMap;
	}

	/**
	 * 
	 * @Description: 联动获取企业电话
	 * @param @param model
	 * @param @param info
	 * @param @return
	 * @return String
	 * @throws
	 * @author sun
	 * @date 2017-10-30
	 */
	@RequestMapping("/getSupplierTel.do")
	@ResponseBody
	public Map<String, Object> getSupplierTel(Model model, PersonInfo info) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if (info == null || info.getPerId() == null) {
			returnMap.put("supplierTel", "");
		} else {
			List<PersonInfo> perInfo = supplierService.querySupplierList(info);
			String Tel = "";
			if (perInfo != null && perInfo.get(0) != null) {
				Tel = perInfo.get(0).getPerTel();
			}
			returnMap.put("supplierTel", Tel);
		}

		return returnMap;
	}

}
