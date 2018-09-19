package com.service;

import java.util.ArrayList;


import com.dao.CommentDao;
import com.dao.UserDao;
import com.model.Comment;
import com.model.User;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class CommentService {
	
	public String getjson(int illustid){
		
		ArrayList<Comment> commlist = CommentDao.query(illustid);
		JSONObject jsonObject = new JSONObject();
		if (commlist == null) {
			jsonObject.put("hascomment", "0");
		 }
		 else {
			jsonObject.put("hascomment", commlist.size());
			JSONArray jsonArray = new JSONArray();
			for(Comment comment:commlist){
				JSONObject jsonObject2 = new JSONObject();
				User user = UserDao.query(comment.getUserid());
				jsonObject2.put("comment", comment);
				jsonObject2.put("user", user);
				jsonArray.add(jsonObject2);
			}
			jsonObject.put("comments",jsonArray);
		}
		return jsonObject.toString();
		
	}
	
	public static int addComment(Comment comment){
		int result = CommentDao.save(comment);
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(new CommentService().getjson(1));
	}

}
