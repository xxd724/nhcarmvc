package com.nh.nhcar.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nh.nhcar.bll.Users;
import com.nh.nhcar.utils.HttpUtil;
import com.nh.nhcar.utils.JsUtils;

public class CheckuserServlet extends ServletBase {

	/**
	 * Constructor of the object.
	 */
	public CheckuserServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			response.setContentType("text/html;charset=utf-8");
		    request.setCharacterEncoding("utf-8");
		    PrintWriter out=response.getWriter();
		    String uname=HttpUtil.escapeHtml(request.getParameter("uname"));
		    String upwd=HttpUtil.escapeHtml(request.getParameter("upwd"));
		    Users users=new Users();
		    Map<String,Object> user= users.checkuser(uname, upwd);
		    if(user.size()>0){
		    	request.getSession().setAttribute("sUser", user);
		    	JsUtils.JsUtilGo(out, "µÇÂ¼³É¹¦£¡", "index");
		    }else{
		    	JsUtils.JsUtilBack(out, "ÕËºÅ»òÃÜÂë´íÎó£¬µÇÂ¼Ê§°Ü£¡");
		    }
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
