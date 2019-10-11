package com.crAdmin.module.car.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crAdmin.bean.Car;
import com.crAdmin.module.car.dao.CarMapper;

/**
 * 汽车相关service
 * 
 * @ClassName: CarService
 * @Description:
 * @author 桑越
 * @date 2015-12-6 下午10:07:01
 * @version V1.0
 */
@Service("carService")
@Transactional(rollbackFor = Exception.class)
public class CarService {
	@Autowired
	private CarMapper carMapper;

	/**
	 * 获取汽车列表信息
	 * 
	 * @Title: queryCarList
	 * @Description:
	 * @author 桑越
	 * @date 2015-12-6 下午10:07:58
	 * @param @param car
	 * @param @return 设定文件
	 * @return List<Car> 返回类型
	 * @throws
	 * @version V1.0
	 */
	public List<Car> queryCarList(Car car) {
		return carMapper.queryCarList(car);
	}
	
	/**
	 * 查询其他汽车列表
	 * 
	 * @Title: queryOtherCarList
	 * @Description:
	 * @author 桑越
	 * @date 2016年1月3日 下午2:41:20
	 * @param @param car
	 * @param @return 设定文件
	 * @return List<Car> 返回类型
	 * @throws @version V1.0
	 */
	public List<Car> queryOtherCarList(Car car) {
		return carMapper.queryOtherCarList(car);
	}

	/**
	 * 新增汽车信息
	 * 
	 * @Title: addCarInfo
	 * @Description:
	 * @author 桑越
	 * @date 2015-12-6 下午11:53:16
	 * @param @param car
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @throws
	 * @version V1.0
	 */
	public int addCarInfo(Car car) {
		return carMapper.addCarInfo(car);
	}

	/**
	 * 修改汽车信息
	 * 
	 * @Title: updateCarInfo
	 * @Description:
	 * @author 桑越
	 * @date 2015-12-18 下午8:13:34
	 * @param @param car
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @throws
	 * @version V1.0
	 */
	public int updateCarInfo(Car car) {
		return carMapper.updateCarInfo(car);
	}

	/**
	 * 删除汽车相关信息
	 * 
	 * @param car
	 * @return
	 */
	public int delCarInfo(Car car) {
		return carMapper.delCarInfo(car);
	}

	/**
	 * 查询汽车信息
	 * 
	 * @Title: queryCarInfo
	 * @Description:
	 * @author 桑越
	 * @date 2015-12-8 下午9:42:12
	 * @param @param car
	 * @param @return 设定文件
	 * @return Car 返回类型
	 * @throws
	 * @version V1.0
	 */
	public Car queryCarInfo(Car car) {
		return carMapper.queryCarInfo(car);
	}

}
