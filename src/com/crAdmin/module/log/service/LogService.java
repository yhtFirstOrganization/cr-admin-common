package com.crAdmin.module.log.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.crAdmin.bean.OperLog;
import com.crAdmin.module.log.dao.LogMapper;

/**
 * 日志相关service
 * 
 * @ClassName: LogService
 * @author scn
 * @date 2017-8-8 上午10:57:50
 */
@Service("logService")
@Transactional(rollbackFor = Exception.class)
public class LogService {
	@Autowired
	LogMapper logMapper;

	/**
	 * 插入操作日志
	 * 
	 * @param @param operLog
	 * @return void
	 * @author scn
	 * @time 2017-8-8上午10:59:03
	 * @throws
	 */
	public void insertLog(OperLog operLog) {
		logMapper.insertLog(operLog);
	}
}
