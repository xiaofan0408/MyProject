package com.service;

import java.util.ArrayList;

import com.dao.IllustDao;
import com.model.Illust;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class IllustService {
	
	public String getIllust(int userid){
		ArrayList<Illust> illusts = new ArrayList<Illust>();
		ArrayList<Illust> allIllust = IllustDao.queryAll(userid);
		
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
	public int  delIllust(int id){
		Illust illust = new Illust();
		illust.setId(id);
		return IllustDao.delIllust(illust);
	}
	
	public static void main(String[] args) {
		System.out.println(new  IllustService().getIllust(1));
	}

}
