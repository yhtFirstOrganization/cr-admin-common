package com.crAdmin.module.car.dao;

import java.util.List;

import com.crAdmin.bean.Car;

public interface CarMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Car record);

	int insertSelective(Car record);

	Car selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Car record);

	int updateByPrimaryKey(Car record);
	
	public List<Car> queryCarList(Car car);
	
	public int addCarInfo(Car car);
	
	public int delCarInfo(Car car);
	
	public Car queryCarInfo(Car car);
	
	public int updateCarInfo(Car car);
	
	public List<Car> queryOtherCarList(Car car);
}