package com.service;

import java.util.ArrayList;

import com.dao.IllustDao;
import com.model.Illust;

import net.sf.json.JSONArray;

public class PreLoginService {
	
	public String getjson() {
		
		
		ArrayList<Illust> illustlist = IllustDao.getTop(13);
		
		JSONArray jsonArray = JSONArray.fromObject(illustlist);
		
		return jsonArray.toString();

	}
}
