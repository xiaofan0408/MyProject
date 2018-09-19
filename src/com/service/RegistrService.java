package com.service;

import com.dao.UserDao;
import com.model.User;

public class RegistrService {
	
	public int register(User user){
		int result = UserDao.save(user);
		return result;
	}

}
