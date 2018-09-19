package com.service;

import java.util.ArrayList;

import com.dao.CollectDao;
import com.dao.FollowDao;
import com.dao.IllustDao;
import com.dao.UserDao;
import com.model.Collect;
import com.model.Follow;
import com.model.Illust;
import com.model.User;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class CollectService {
	
	public String getCollect(int userid){
		ArrayList<Collect> collects = new ArrayList<Collect>();
		ArrayList<Collect> allCollect = CollectDao.getCollect(userid);
		
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		if (allCollect.size()==0) {
			jsonObject.put("empty", "true");
			jsonObject.put("collect", JSONArray.fromObject(collects));
		}
		else{
			jsonObject.put("empty", "false");
			 for(Collect collect :allCollect){
				 JSONObject jsonObject2 = new JSONObject();
				 Illust illust = IllustDao.query(collect.getIllustid());
				 jsonObject2.put("collect", collect);
				 jsonObject2.put("illust",illust);
				 jsonArray.add(jsonObject2);
			 }
		}
		jsonObject.put("collects", jsonArray);
		return jsonObject.toString();
	}
	public int  delCollect(Collect collect){
	   
		return CollectDao.delCollect(collect);
	}
	public int addCollecct(Collect collect){
		return CollectDao.addCollect(collect);
	}
	
	public String isFollow(Collect collect){
    	Collect collect2 = CollectDao.find(collect);
    	JSONObject jsonObject = new JSONObject();
    	if (collect2 == null) {
			jsonObject.put("iscollect", "false");
		}else{
			jsonObject.put("iscollect", "true");
		}
    	return jsonObject.toString();
    }

}
