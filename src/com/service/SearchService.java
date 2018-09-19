package com.service;

import java.util.ArrayList;

import com.dao.IllustDao;
import com.model.Illust;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SearchService {
	
	
	public static String getjson(String word){
		
		ArrayList<Illust> illusts = new ArrayList<Illust>();
		ArrayList<Illust> allIllust = IllustDao.search(word);
		
		JSONObject jsonObject = new JSONObject();
		
		if (allIllust.size()==0) {
			jsonObject.put("empty", "true");
			jsonObject.put("illust", JSONArray.fromObject(illusts));
		}
		else{
			illusts.addAll(allIllust);
			jsonObject.put("empty", "false");
			jsonObject.put("illust", JSONArray.fromObject(illusts));
		}
		return jsonObject.toString();
		
		
	}

}
