package com.service;

import com.dao.IllustDao;
import com.dao.UserDao;
import com.model.Illust;

public class UpdateService {
	
	public int save(Illust illust){
		UserDao.addillust(illust.getAuthorid());
		return IllustDao.save(illust);
	}

}
