package com.crAdmin.module.index.dao;

import com.crAdmin.bean.User;


public interface UserMapper {
	User checkUser(User user);

	int updateUser(User user);

}