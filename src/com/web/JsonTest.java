package com.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Illust;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonTest extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Illust[] imgs = getImage();
		JSONObject jsonObject = null;
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < imgs.length; i++) {
			jsonObject = JSONObject.fromObject(imgs[i]);
			jsonArray.add(jsonObject);
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setHeader("Access-Control-Allow-Origin", "*");
//		response.setHeader("Content-Security-Policy", "default-src 'self'");
		
		
		PrintWriter put = response.getWriter();
		put.println(jsonArray.toString());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	public Illust[] getImage() {
		Illust[] imgs = new Illust[13];
		String bg = "bg0";
		for (int i = 1; i <= imgs.length; i++) {
			if (i >= 10) {
				bg = "bg";
			}
			imgs[i - 1] = new Illust();
			imgs[i - 1].setThumbURL("img1/thumbs/" + bg + i + ".jpg");
			imgs[i - 1].setRealURL("img1/" + bg + i + ".jpg");
		}
		return imgs;
	}

}
