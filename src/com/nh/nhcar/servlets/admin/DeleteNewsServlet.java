package com.nh.nhcar.servlets.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nh.nhcar.bll.News;
import com.nh.nhcar.servlets.ServletBase;
import com.nh.nhcar.utils.HttpUtil;
import com.nh.nhcar.utils.JsUtils;

public class DeleteNewsServlet extends ServletBase {

	/**
	 * Constructor of the object.
	 */
	public DeleteNewsServlet() {
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
			int nid =HttpUtil.getIntPara(request,"nid", 0);
			News news=new News();
			int result=news.Deletenews(nid);
			//int nid = Integer.valueOf(request.getParameter("nid"));
			//int result = news.admindeletenews(nid);
		    if(result>0){
		   		JsUtils.JsUtilGo(out,"ɾ���ɹ���","newslist");
		    }else{
		   		JsUtils.JsUtilBack(out, "ɾ��ʧ�ܣ�");
		   		return;
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
