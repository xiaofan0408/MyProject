package com.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.User;
import com.service.LoginService;

public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           
		   String username = request.getParameter("username");
		   
		   String password = request.getParameter("password");
		   
		   LoginService loginService = new LoginService();
		   
		   User user = new User();
		   user.setUserName(username);
		   user.setPassWord(password);
		   
		   User resultUser = loginService.login(user);
		   
		   if (resultUser != null) {
			   request.getSession().setAttribute("user", user);
			   Cookie cookie = new Cookie("userid", Integer.toString(resultUser.getId()));
			   cookie.setMaxAge(60*60*24*30);
			   response.addCookie(cookie);
//			   cookie.setPath(request.getContextPath());
			 //发送自动登陆cookie给客户端浏览器进行存储
			 sendAutoLoginCookie(request,response,user);
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

}
