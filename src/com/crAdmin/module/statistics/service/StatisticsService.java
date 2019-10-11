package com.crAdmin.module.statistics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crAdmin.bean.Repair;
import com.crAdmin.bean.Statistics;
import com.crAdmin.module.statistics.dao.StatisticsMapper;

/**
 * 汽车相关service
 * 
 * @ClassName: CarService
 * @Description:
 * @author 桑越
 * @date 2015-12-6 下午10:07:01
 * @version V1.0
 */
@Service("StatisticsService")
@Transactional(rollbackFor = Exception.class)
public class StatisticsService {
	@Autowired
	private StatisticsMapper statisticsMapper;

	/**
	 * 获取每月应实收总金额
	* @param @param repair
	* @param @return 
	* @return List<Statistics>  
	* @author scn
	* @time 2017-6-22下午2:49:00
	* @throws
	 */
	public List<Statistics> queryMoney(Repair repair) {
		return statisticsMapper.queryMoney(repair);
	}
	//按日期查询
	public List<Statistics> queryMoneyByDate(Repair repair) {
		return statisticsMapper.queryMoneyByDate(repair);
	}
	//按年查询
	public List<Statistics> queryMoneyByYear(Repair repair) {
		return statisticsMapper.queryMoneyByYear(repair);
	}
	/**
	 * @param string 
	 * 根据月数获取每天应实收总金额
	 * @param @param repair
	 * @param @return 
	 * @return List<Statistics>  
	 * @author scn
	 * @time 2017-6-22下午2:49:00
	 * @throws
	 */
	public List<Statistics> queryMoneyByMonth(String month, String string) {
		return statisticsMapper.queryMoneyByMonth(month,string);
	}
	public List<Statistics> queryStockInOutSumPrice(Repair repair) {
		return statisticsMapper.queryStockInOutSumPrice(repair);
	}
}
