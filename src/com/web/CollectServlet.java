package com.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Collect;
import com.model.Follow;
import com.service.CollectService;
import com.service.FollowService;



/**
 * Servlet implementation class CollectServlet
 */
public class CollectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CollectServlet() {
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
		
		CollectService collectService = new CollectService();
		String data = collectService.getCollect(Integer.valueOf(userid));
		PrintWriter pWriter = response.getWriter();
		pWriter.println(data);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userid = request.getParameter("userid");
		String illustid = request.getParameter("illustid");
        String method = request.getParameter("method");
        
        if (method ==null || method.isEmpty()) {
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json;charset=utf-8");
			response.setHeader("pragma", "no-cache");
			response.setHeader("cache-control", "no-cache");
			response.setHeader("Access-Control-Allow-Origin", "*");
	        response.setHeader("Access-Control-Allow-Methods", "*");
	        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type");
	        
	        CollectService collectService = new CollectService();
	        Collect collect = new Collect();
	        collect.setUserid(Integer.valueOf(userid));
	        collect.setIllustid(Integer.valueOf(illustid));
	        collectService.addCollecct(collect);
	        PrintWriter pWriter = response.getWriter();
	        String data = collectService.getCollect(collect.getUserid());
			pWriter.println(data);
	     
		}else if(method.equals("delete")){
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json;charset=utf-8");
			response.setHeader("pragma", "no-cache");
			response.setHeader("cache-control", "no-cache");
			response.setHeader("Access-Control-Allow-Origin", "*");
	        response.setHeader("Access-Control-Allow-Methods", "*");
	        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type");
	        
	        CollectService collectService = new CollectService();
	        Collect collect = new Collect();
	        collect.setUserid(Integer.valueOf(userid));
	        collect.setIllustid(Integer.valueOf(illustid));
	        collectService.delCollect(collect);
	        PrintWriter pWriter = response.getWriter();
	        String data = collectService.getCollect(collect.getUserid());
			pWriter.println(data);
		}else if (method.equals("find")) {
		
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
		    CollectService collectService = new CollectService();
	        Collect collect = new Collect();
	        collect.setUserid(Integer.valueOf(value));
	        collect.setIllustid(Integer.valueOf(illustid));
	        String data = collectService.isFollow(collect);
	        PrintWriter pWriter = response.getWriter();
			pWriter.println(data);
		}
	}

}
