package com.nh.nhcar.servlets.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nh.nhcar.servlets.ServletBase;
import com.nh.nhcar.utils.HttpUtil;
import com.nh.nhcar.utils.JsUtils;

public class AdminIndexServlet extends ServletBase {

	/**
	 * Constructor of the object.
	 */
	public AdminIndexServlet() {
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
			response.setContentType("text/html;charset=utf-8");
		    request.setCharacterEncoding("utf-8");
			PrintWriter out=response.getWriter();
			//if(request.getSession().getAttribute("a_aid")==null){
				//JsUtils.JsUtilGo(out, "���ȵ�¼��", "../adminlogin/login");
				//return;
			//}
			String atc=HttpUtil.getStringPara(request,"atc", "index");
			if(atc.equalsIgnoreCase("index")){
				show(request,response,"index.jsp");
			}
			if(atc.equalsIgnoreCase("left")){
				show(request,response,"left.jsp");
			}
			if(atc.equalsIgnoreCase("header")){
				show(request,response,"header.jsp");
			}
			if(atc.equalsIgnoreCase("main")){
				show(request,response,"main.jsp");
			}
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
