package com.service;

import java.awt.Point;

import com.dao.IllustDao;
import com.dao.UserDao;
import com.model.Illust;
import com.model.User;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

public class ShowService {
	
	private Illust illust;
	private User user;
	
	public String getJson(int id){
		
		JSONArray jsonArray = new JSONArray();
		JSONArray jsonArray2 = new JSONArray();
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		
		
		
		illust = IllustDao.query(id);
		user = UserDao.query(illust.getAuthorid());
		
		
		JSONObject illustObject = JSONObject.fromObject(illust);
		JSONObject userOBJect = JSONObject.fromObject(user);
		
		jsonArray.add(illustObject);
		jsonArray.add(userOBJect);
		

       return jsonArray.toString();		
		
		
	}
	
	public static void main(String[] args) {
		System.out.println(new ShowService().getJson(1));
	}

}
