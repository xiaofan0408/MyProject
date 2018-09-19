package com.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.interfaces.RSAKey;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.AfterLoginService;

/**
 * Servlet implementation class AfterLoginServlet
 */
public class AfterLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AfterLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
	    AfterLoginService afterLoginService = new AfterLoginService();
	    String data =afterLoginService.getjson(Integer.valueOf(value));
	    PrintWriter pWriter = response.getWriter();
	    pWriter.println(data);
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	@Override
	protected void doOptions(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		
		super.doOptions(arg0, arg1);
	}

}
