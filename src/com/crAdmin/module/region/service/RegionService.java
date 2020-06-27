package com.crAdmin.module.region.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crAdmin.bean.Region;
import com.crAdmin.module.region.dao.RegionMapper;

@Service("regionService")
@Transactional(rollbackFor = Exception.class)
public class RegionService {
	@Autowired
	private RegionMapper regionMapper;

	/**
	 * 根据父id获取列表
	 * 
	 * @Title: getChildList
	 * @Description:
	 * @author 桑越
	 * @date 2015-10-23 下午3:03:42
	 * @param @param region
	 * @param @return 设定文件
	 * @return List<Region> 返回类型
	 * @throws
	 * @version V1.0
	 */
	public List<Region> getChildList(Region region) {
		return regionMapper.getChildList(region);
	}

	/**
	 * 根据区县id获取省市县三级的详细信息
	 * 
	 * @param region
	 * @return
	 */
	public Map<String, String> getCityInfo(Region region) {
		Map<String, String> map = new HashMap<String, String>();
		region = regionMapper.find(region);
		String ptree = region.getPtree();
		String pname_tree = region.getPnameTree();
		String[] ptreeArr = ptree.split("-");
		String[] pnameTreeArr = pname_tree.split("-");
		map.put("id", region.getRegionId());
		map.put("name", region.getName());
		map.put("pid", ptreeArr[2]);
		map.put("gid", ptreeArr[1]);
		map.put("pname", pnameTreeArr[2]);
		map.put("gname", pnameTreeArr[1]);
		String cname = "";
		if (pnameTreeArr.length > 3) {
			cname = pnameTreeArr[3];
		}
		map.put("cname", cname);
		return map;
	}
}
