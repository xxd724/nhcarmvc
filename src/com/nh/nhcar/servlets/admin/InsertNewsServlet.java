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
import com.nh.nhcar.utils.JsUtils;

public class InsertNewsServlet extends ServletBase {

	/**
	 * Constructor of the object.
	 */
	public InsertNewsServlet() {
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
			//response.setContentType("text/html;charset=utf-8");
		    //request.setCharacterEncoding("utf-8");
			//PrintWriter out=response.getWriter();
			//if(request.getSession().getAttribute("a_aid")==null){
				//JsUtils.JsUtilGo(out, "请先登录！", "../adminlogin/login");
				//return;
			//}
			show(request,response,"addnews.jsp");
		
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
		    String ntitle=request.getParameter("ntitle");
		    String nfrom=request.getParameter("nfrom");
		    String ncontent=request.getParameter("ncontent");
		    if(ntitle.isEmpty()){
		     	JsUtils.JsUtilBack(out, "标题不能为空！");
		     	return;
		    }
		    if(nfrom.isEmpty()){
		     	JsUtils.JsUtilBack(out, "来源不能为空！");
		     	return;
		    }
		    if(ncontent.isEmpty()){
		     	JsUtils.JsUtilBack(out, "内容不能为空！");
		     	return;
		    }
		    News news=new News();
		    HttpSession session=request.getSession();
		    int naid=Integer.valueOf(session.getAttribute("a_aid").toString());
		    int result=news.insertnews(ntitle, ncontent, nfrom, naid);
		    if(result>0){
		     	JsUtils.JsUtilGo(out,"发布成功！","insertnews");
		    }else{
		     	JsUtils.JsUtilBack(out, "发布失败！");
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
