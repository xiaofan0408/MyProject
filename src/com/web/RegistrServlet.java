package com.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.lang.model.element.Element;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDao;
import com.model.User;
import com.service.RegistrService;

/**
 * Servlet implementation class RegistrServlet
 */
public class RegistrServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		String txt_password = request.getParameter("txt_password");
		String Email = request.getParameter("Email");
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type");
		
		
		  User user = new User();
		  user.setUserName(username);
		  user.setPassWord(txt_password);
		  user.setEmail(Email);
		  
		  RegistrService registrService = new RegistrService();
		  int result = registrService.register(user);
		  
		  
		  if (result == -1) {
			
			 PrintWriter pWriter = response.getWriter();
			 pWriter.println("failed");
		   
		  }else {
			  request.getSession().setAttribute("user", user);
			  User resultUser = UserDao.find(user);
			   Cookie cookie = new Cookie("userid", Integer.toString(resultUser.getId()));
			   cookie.setMaxAge(60*60*24*30);
			   response.addCookie(cookie);
//			   cookie.setPath(request.getContextPath());
			 //发送自动登陆cookie给客户端浏览器进行存储
			 sendAutoLoginCookie(request,response,user);
			  PrintWriter pWriter = response.getWriter();
			  pWriter.println("success");
			  response.sendRedirect("/MyProject/main.html");
		  }

	}
	
	 private void sendAutoLoginCookie(HttpServletRequest request, HttpServletResponse response, User user) {
			
		 //创建cookie,cookie的名字是autologin，值是用户登录的用户名和密码，用户名和密码之间使用.进行分割，密码经过md5加密处理
		 Cookie cookie = new Cookie("autologin",user.getUserName() + "." + user.getPassWord());
	            //设置cookie的有效期
		 cookie.setMaxAge(60*60*24*30);
		             //设置cookie的有效路径
//		  cookie.setPath(request.getContextPath());
		             //将cookie写入到客户端浏览器
		  response.addCookie(cookie);
	}
		     	
	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
