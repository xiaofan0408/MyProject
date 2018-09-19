package com.service;

import java.util.ArrayList;

import com.dao.IllustDao;
import com.model.Illust;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class HotService {
	
	public String getPage(int num){
		
		ArrayList<Illust> illusts =null;
		
		if (num == 1) {
			 illusts = IllustDao.getHotPage(0, 15);
		}
		else{
			 illusts = IllustDao.getHotPage((num-1)*15+1, num*15);
		}
		
		JSONArray jsonArray = JSONArray.fromObject(illusts);
		
		int total = IllustDao.getHotNum();
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("total", total);
		jsonObject.put("current", jsonArray);
		
		return jsonObject.toString();
		
	}
	
	public static void main(String[] args) {
		System.out.println(new HotService().getPage(1));
	}

}
