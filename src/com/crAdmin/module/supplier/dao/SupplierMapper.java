package com.crAdmin.module.supplier.dao;

import java.util.List;

import com.crAdmin.bean.PersonInfo;

/**
 * 
 * @ClassName: SupplierMapper 
 * @Description: 供应商管理dao
 * @author sun
 * @date 2017-10-27
 */
public interface SupplierMapper {
	/**
	 * 
	 * @Description: 获取供应商列表
	 * @param @param personInfo
	 * @param @return   
	 * @return List<PersonInfo>  
	 * @throws
	 * @author sun
	 * @date 2017-10-27
	 */
	List<PersonInfo> querySupplierList(PersonInfo personInfo);

	/**
	 * 
	 * @param companyId 
	 * @Description: 返回最新供应商编号
	 * @param @return   
	 * @return Integer  
	 * @throws
	 * @author sun
	 * @date 2017-10-27
	 */
	Integer getNewPerNum(String companyId);

	/**
	 * 
	 * @Description: 增加供应商
	 * @param @param personInfo
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author sun
	 * @date 2017-10-27
	 */
	int addPerInfo(PersonInfo personInfo);

	/**
	 * 
	 * @Description: 修改供应商信息
	 * @param @param info
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author sun
	 * @date 2017-10-30
	 */
	int update(PersonInfo info);

	/**
	 * 
	 * @Description: 删除供应商信息
	 * @param @param personInfo
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author sun
	 * @date 2017-11-3
	 */
	int delete(PersonInfo personInfo);

}