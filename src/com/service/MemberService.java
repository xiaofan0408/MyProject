package com.service;

import java.util.ArrayList;

import org.apache.tomcat.util.descriptor.tld.TldRuleSet.Variable;

import com.dao.CollectDao;
import com.dao.IllustDao;
import com.dao.UserDao;
import com.model.Collect;
import com.model.Illust;
import com.model.User;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MemberService {
	
	public String getjson(int userid) {
		User user = UserDao.query(userid);
		ArrayList<Illust> illusts = new ArrayList<Illust>();
		ArrayList<Illust> queryillusts = IllustDao.queryAll(userid);
		int num = 6;
		if (queryillusts.size()<6) {
			num =queryillusts.size();
		}
		for(int i=0;i<num;i++){
			illusts.add(queryillusts.get(i));
		}
		ArrayList<Collect> collects = new ArrayList<Collect>();
		ArrayList<Collect> collects2 = CollectDao.getCollect(userid);
		int num2 = 6;
		if (collects2.size()<6) {
			num =collects2.size();
		}
		for(int i=0;i<num;i++){
			collects.add(collects2.get(i));
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("user", user);
		jsonObject.put("illust", JSONArray.fromObject(illusts));
		jsonObject.put("collect", JSONArray.fromObject(collects));
		
		return jsonObject.toString();
	}

}
