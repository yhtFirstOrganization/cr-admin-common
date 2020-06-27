package com.crAdmin.module.region.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crAdmin.bean.Region;
import com.crAdmin.module.region.service.RegionService;

@Controller("regionController")
@RequestMapping("/region")
public class RegionController {
	@Resource(name="regionService")
	RegionService regionService;
	/**
	 * 根据父节点获取列表
	 * @param provId
	 * @return
	 */
	@RequestMapping("/getCitys.do")
	public @ResponseBody List<Region>getCitys(String fatherid){
		List<Region> list = null;
		Region father = new Region();
		father.setRegionId(fatherid);
		list = regionService.getChildList(father);
		return list;
	}
}
