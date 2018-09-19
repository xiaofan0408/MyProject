package com.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CommentDao;
import com.model.Comment;
import com.service.CommentService;

/**
 * Servlet implementation class CommentServlet
 */
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		

		String illustid = request.getParameter("illustid");

		
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "*");
	    response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type");
	
		CommentService commentService = new CommentService();
	    String commmentlist = commentService.getjson(Integer.valueOf(illustid));

		PrintWriter put = response.getWriter();
		put.println(commmentlist);		
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String content = request.getParameter("content");
		String userid = request.getParameter("userid");
		String illustid = request.getParameter("illustid");

		
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "*");
	    response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type");
		
		
		Comment comment = new Comment();
		comment.setContent(content);
		comment.setUserid(Integer.valueOf(userid));
		comment.setIllustid(Integer.valueOf(illustid));

		
		CommentService commentService = new CommentService();
		int result = commentService.addComment(comment);
		
		String commmentlist = commentService.getjson(comment.getIllustid());
		
		PrintWriter put = response.getWriter();
		
		if (result == 1) {
			put.println(commmentlist);
		}else{
			put.println("failed");
		}
		
		
	}
	
	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "*");
	    response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type");
	}

}
