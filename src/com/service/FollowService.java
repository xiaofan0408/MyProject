package com.service;

import java.util.ArrayList;

import com.dao.FollowDao;
import com.dao.UserDao;
import com.model.Follow;
import com.model.User;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class FollowService {
	
	public String getFollow(int userid){
		
		ArrayList<Follow> follows = FollowDao.getFollow(userid);
		ArrayList<User> users = new ArrayList<User>();
		for(Follow follow: follows){
			User user = UserDao.query(follow.getUserid());
			users.add(user);
		}
		JSONObject jsonObject = new JSONObject();
		if (follows.size() == 0) {
			jsonObject.put("empty", "true");
		}
		else{
			jsonObject.put("empty", "false");
		}
		jsonObject.put("follow", JSONArray.fromObject(users));
		return jsonObject.toString();
	
		
	}
	public String getFans(int userid){
		ArrayList<Follow> follows = FollowDao.getFans(userid);
		ArrayList<User> users = new ArrayList<User>();
		for(Follow follow: follows){
			User user = UserDao.query(follow.getFansid());
			users.add(user);
		}
		JSONObject jsonObject = new JSONObject();
		if (follows.size() == 0) {
			jsonObject.put("empty", "true");
		}
		else{
			jsonObject.put("empty", "false");
		}
		jsonObject.put("fans", JSONArray.fromObject(users));
		return jsonObject.toString();
	}
	public int addfollow(Follow follow){
		       
	   UserDao.follow(follow.getFansid(),follow.getUserid());
		return FollowDao.addFollow(follow);
	}
    public int deletefollow(Follow follow){
    	UserDao.unfollow(follow.getFansid(),follow.getUserid());
		return FollowDao.deleteFollow(follow);
	}
    
    public static void main(String[] args) {
		System.out.println(new  FollowService().getFans(1));
		System.out.println(new FollowService().getFollow(1));
	}
    public String isFollow(Follow follow){
    	Follow follow2 = FollowDao.find(follow);
    	JSONObject jsonObject = new JSONObject();
    	if (follow2 == null) {
			jsonObject.put("isfollow", "false");
		}else{
			jsonObject.put("isfollow", "true");
		}
    	return jsonObject.toString();
    }

}
