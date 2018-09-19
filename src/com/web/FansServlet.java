package com.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Follow;
import com.service.FollowService;

/**
 * Servlet implementation class FansServlet
 */
public class FansServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FansServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type");
        
        FollowService followService = new FollowService();
        String data = followService.getFans(Integer.valueOf(userid));
        PrintWriter pWriter = response.getWriter();
		pWriter.println(data);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String followid = request.getParameter("followid");
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type");
        
        Cookie[] cookies = request.getCookies();
	    String value = null;
	    for(int i=0;cookies!=null && i<cookies.length;i++){
            if(cookies[i].getName().equals("userid")){
                value = cookies[i].getValue();
            }
        }
	    
	    if (value == null) {
			value ="-1";
		}
        FollowService followService = new FollowService();
        Follow follow = new Follow();
        follow.setUserid(Integer.valueOf(followid));
        follow.setFansid(Integer.valueOf(value));
        String data = followService.isFollow(follow);
        PrintWriter pWriter = response.getWriter();
		pWriter.println(data);
	}

}
