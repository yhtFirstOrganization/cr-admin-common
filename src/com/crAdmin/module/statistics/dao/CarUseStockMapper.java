package com.crAdmin.module.statistics.dao;

import java.util.List;

import com.crAdmin.bean.vo.OutStockVo;

public interface CarUseStockMapper {
	List<OutStockVo> carStockList(OutStockVo stockVo);
}