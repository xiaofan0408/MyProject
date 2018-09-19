package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dao.UserDao;
import com.model.User;
import com.util.DbUtil;



public class LoginService {
	
	public  User login(User user) {
			
			return UserDao.login(user);
	}

}
