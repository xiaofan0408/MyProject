package com.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Follow;
import com.service.FollowService;

/**
 * Servlet implementation class FollowServlet
 */
public class FollowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowServlet() {
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
        String data = followService.getFollow(Integer.valueOf(userid));
        PrintWriter pWriter = response.getWriter();
		pWriter.println(data);
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userid = request.getParameter("userid");
		String followid = request.getParameter("followid");
        String method = request.getParameter("method");
        
        if (method ==null || method.isEmpty()) {
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json;charset=utf-8");
			response.setHeader("pragma", "no-cache");
			response.setHeader("cache-control", "no-cache");
			response.setHeader("Access-Control-Allow-Origin", "*");
	        response.setHeader("Access-Control-Allow-Methods", "*");
	        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type");
	        
	        FollowService followService = new FollowService();
	        Follow follow = new Follow();
	        follow.setUserid(Integer.valueOf(followid));
	        follow.setFansid(Integer.valueOf(userid));
	        followService.addfollow(follow);
	        String data = followService.getFollow(Integer.valueOf(userid));
	        PrintWriter pWriter = response.getWriter();
			pWriter.println(data);
		}else{
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json;charset=utf-8");
			response.setHeader("pragma", "no-cache");
			response.setHeader("cache-control", "no-cache");
			response.setHeader("Access-Control-Allow-Origin", "*");
	        response.setHeader("Access-Control-Allow-Methods", "*");
	        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type");
	        
	        FollowService followService = new FollowService();
	        Follow follow = new Follow();
	        follow.setUserid(Integer.valueOf(followid));
	        follow.setFansid(Integer.valueOf(userid));
	        followService.deletefollow(follow);
	        String data = followService.getFollow(Integer.valueOf(userid));
	        PrintWriter pWriter = response.getWriter();
			pWriter.println(data);
		}
        
		
		
		
		
		
		
	}
	
	@Override
	protected void doOptions(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		
	}
	
}
