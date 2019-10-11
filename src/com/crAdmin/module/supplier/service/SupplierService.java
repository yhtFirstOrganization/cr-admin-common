package com.crAdmin.module.supplier.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crAdmin.bean.PersonInfo;
import com.crAdmin.module.supplier.dao.SupplierMapper;

/**
 * 
 * @ClassName: SupplierService
 * @Description: 供应商相关service
 * @author sun
 * @date 2017-10-27
 */
@Service("supplierService")
@Transactional(rollbackFor = Exception.class)
public class SupplierService {
	@Autowired
	private SupplierMapper supplierMapper;

	/**
	 * 
	 * @Description: 查询供应商列表
	 * @param @param personInfo
	 * @param @return
	 * @return List<PersonInfo>
	 * @throws
	 * @author sun
	 * @date 2017-10-27
	 */
	public List<PersonInfo> querySupplierList(PersonInfo personInfo) {
		return supplierMapper.querySupplierList(personInfo);
	}

	/**
	 * 
	 * @Description: 查询本企业最大供应商编号
	 * @param @param companyId
	 * @param @return
	 * @return Integer
	 * @throws
	 * @author sun
	 * @date 2017-10-27
	 */
	public Integer getNewPerNum(String companyId) {
		return supplierMapper.getNewPerNum(companyId);
	}

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
	public int addPerInfo(PersonInfo personInfo) {
		return supplierMapper.addPerInfo(personInfo);
	}

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
	public int update(PersonInfo info) {
		return supplierMapper.update(info);
	}

	public int delete(PersonInfo personInfo) {
		return supplierMapper.delete(personInfo);
	}

}
