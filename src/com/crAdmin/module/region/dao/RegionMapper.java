package com.crAdmin.module.region.dao;

import java.util.List;

import com.crAdmin.bean.Region;

public interface RegionMapper {
	int deleteByPrimaryKey(Region record);

	int insert(Region record);

	int insertSelective(Region record);

	Region find(Region record);

	int updateByPrimaryKeySelective(Region record);

	int updateByPrimaryKeyWithBLOBs(Region record);

	int updateByPrimaryKey(Region record);

	/**
	 * 根据父id获取列表
	 * 
	 * @param pid
	 * @return
	 */
	List<Region> getChildList(Region region);
}