package com.nh.nhcar.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nh.nhcar.bll.Users;
import com.nh.nhcar.utils.JsUtils;

public class AddUserServlet extends ServletBase {

	/**
	 * Constructor of the object.
	 */
	public AddUserServlet() {
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
			String uname=request.getParameter("uname");
		    String upwd=request.getParameter("upwd");
		    String urepwd=request.getParameter("urepwd");
		    String uemail=request.getParameter("uemail");
		    if(uname.isEmpty()){
		    	JsUtils.JsUtilBack(out, "�û�����Ϊ�գ�");
		    	return;
		    }
		    if(upwd.isEmpty()){
		    	JsUtils.JsUtilBack(out, "���벻��Ϊ�գ�");
		    	return;
		    }
		    if(!upwd.equals(urepwd)){
		    	JsUtils.JsUtilBack(out, "���ٴ�ȷ�����룡");
		    	return;
		    }
		    String erg="^[a-zA-Z0-9_]+@[a-zA-Z0-9]+.[a-zA-Z]{2,3}$";
		  	if(!uemail.matches(erg)){
		  		JsUtils.JsUtilBack(out, "email��ʽ���ԣ�");
		    	return;
		  	}
		  	Users users=new Users();
		  	Object checkuname= users.check(uname);
		  	if(checkuname!=null){
		  		JsUtils.JsUtilBack(out, "�û�����ռ�ã�");
		    	return;
		  	}else{
		  		int adduser=users.adduser(uname, upwd, uemail);
		  		if(adduser>0){
		  	    	JsUtils.JsUtilGo(out, "ע��ɹ���","login");
		  	   	}else{
		  	   		JsUtils.JsUtilBack(out, "ע��ʧ�ܣ�");
		  	    }
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
