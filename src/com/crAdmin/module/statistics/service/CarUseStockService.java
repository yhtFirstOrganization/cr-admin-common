package com.crAdmin.module.statistics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crAdmin.bean.vo.OutStockVo;
import com.crAdmin.module.statistics.dao.CarUseStockMapper;

/**
 * 汽车维修记录相关service
* @ClassName: CarUserStockService 
* @Description: TODO
* @author scn 
* @date 2017-9-8 下午4:59:33
 */

@Service("CarUseStockService")
@Transactional(rollbackFor = Exception.class)
public class CarUseStockService {
	@Autowired
	private CarUseStockMapper carUseStockMapper;

	public List<OutStockVo> carStockList(OutStockVo stockVo){
		return carUseStockMapper.carStockList(stockVo);
	}
}
