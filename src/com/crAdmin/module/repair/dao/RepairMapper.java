package com.crAdmin.module.repair.dao;

import java.util.List;

import com.crAdmin.bean.Repair;
public interface RepairMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Repair record);

	int insertSelective(Repair record);

	Repair selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Repair record);

	int updateByPrimaryKey(Repair record);

	public List<Repair> queryRepairList(Repair repair);

	public int addRepairInfo(Repair repair);

	public int delRepairInfo(Repair repair);

	public int updateRepairInfo(Repair repair);
	
	public Repair queryRepairInfo(Repair repair);

}