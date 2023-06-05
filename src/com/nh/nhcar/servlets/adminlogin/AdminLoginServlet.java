package com.nh.nhcar.servlets.adminlogin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nh.nhcar.bll.Admin;
import com.nh.nhcar.servlets.ServletBase;
import com.nh.nhcar.utils.JsUtils;

public class AdminLoginServlet extends ServletBase {

	/**
	 * Constructor of the object.
	 */
	public AdminLoginServlet() {
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
			AdminLoginServlet.show(request, response, "login.jsp");
		
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
	
		  	String aname=request.getParameter("aname");
		  	String apwd=request.getParameter("apwd");
		  	String acheckno=request.getParameter("acheckno");
		    if(aname.isEmpty()){
		   		JsUtils.JsUtilBack(out, "账号不能为空！");
		   		return;
		    }
		    if(apwd.isEmpty()){
		   		JsUtils.JsUtilBack(out, "密码不能为空！");
		   		return;
		    }
		    HttpSession session=request.getSession();
		    String rand=String.valueOf(session.getAttribute("rand"));
		    if(!acheckno.equalsIgnoreCase(rand)){
		   		JsUtils.JsUtilBack(out, "验证码不正确！");
		   		return;
		    }
		   Admin admin=new Admin();
		   int aid=admin.checkadmin(aname, apwd);
		   if(aid>0){
			   	session.setAttribute("a_aid", aid);
		   		JsUtils.JsUtilGo(out,"登录成功！","../admin/index");
		   }else{
		   		JsUtils.JsUtilBack(out, "账号或密码不正确，登录失败！");
		   return;
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
