package com.service;

import java.util.ArrayList;
import java.util.Collections;

import org.apache.catalina.tribes.util.Arrays;

import com.dao.FollowDao;
import com.dao.IllustDao;
import com.dao.UserDao;
import com.model.Follow;
import com.model.Illust;
import com.model.User;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class AfterLoginService {
	
	public String getjson(int userid){
		
		JSONObject jsonObject = new JSONObject();

		ArrayList<Follow> follows = FollowDao.getFollow(userid);
		ArrayList<Illust> illusts = new ArrayList<Illust>();
		for(Follow follow:follows){
			ArrayList<Illust> followIllust = IllustDao.queryAll(follow.getUserid());
			illusts.addAll(followIllust);
		}
		Collections.sort(illusts,(a,b)->{
			return -a.getTime().compareTo(b.getTime());
		});
		
		ArrayList<Illust> resultIllust = new ArrayList<Illust>();
		
		int num = 8;
		if (illusts.size() <8) {
			num = illusts.size();
		}
		
		for(int i =0;i<num;i++){
			resultIllust.add(illusts.get(i));
		}
		if (resultIllust.size() ==0) {
			jsonObject.put("empty", "true");
		}
		else{
			jsonObject.put("empty", "false");
		}
		JSONArray jsonArray = new JSONArray();
		for(Illust illust: resultIllust){
			JSONObject jsonObject2 = new JSONObject();
			User user = UserDao.query(illust.getAuthorid());
			jsonObject2.put("illust", illust);
			jsonObject2.put("user", user);
			jsonArray.add(jsonObject2);
		}
		jsonObject.put("illusts", jsonArray);
		
		ArrayList<Illust> allIllusts = IllustDao.queryAllIllust();
		JSONArray  jsonArray2 = new JSONArray();
		for(int i=0;i<4;i++){
			jsonArray2.add(JSONObject.fromObject(allIllusts.get(i)));
		}
		jsonObject.put("hot", jsonArray2);
		
		ArrayList<User> users = UserDao.queryAll();
		JSONArray jsonArray3 = new JSONArray();
		for(int i=0;i<6;i++){
			jsonArray3.add(JSONObject.fromObject(users.get(i)));
		}
		jsonObject.put("HotUser", jsonArray3);
		
		jsonObject.put("mainuserid", userid);
		
		return jsonObject.toString();
		
	}
	public static void main(String[] args) {
		System.out.println(new AfterLoginService().getjson(11));
	}

}
