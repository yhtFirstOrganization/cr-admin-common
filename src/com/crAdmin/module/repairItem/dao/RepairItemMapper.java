package com.crAdmin.module.repairItem.dao;

import java.util.List;

import com.crAdmin.bean.RepairItem;

public interface RepairItemMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(RepairItem record);

	int insertSelective(RepairItem record);

	RepairItem selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(RepairItem record);

	int updateByPrimaryKeyWithBLOBs(RepairItem record);

	int updateByPrimaryKey(RepairItem record);
	
	public List<RepairItem> queryRepairItemList(RepairItem repairItem);
	
	public int addRepairItemInfo(RepairItem repairItem);

	int delRepairItemInfo(RepairItem repairItem);
}