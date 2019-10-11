package com.crAdmin.module.index.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crAdmin.bean.User;
import com.crAdmin.module.index.dao.UserMapper;

@Service("indexService")
@Transactional(rollbackFor = Exception.class)
public class IndexService {
	@Autowired
	private UserMapper userMapper;

	public User checkUser(User user) {
		User userResult = userMapper.checkUser(user);
		return userResult;
	}

	public int updateUser(User user) {
		int i = userMapper.updateUser(user);
		return i;
	}

}
