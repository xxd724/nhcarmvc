package com.nh.nhcar.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nh.nhcar.bll.Category;
import com.nh.nhcar.bll.News;
import com.nh.nhcar.utils.HttpUtil;
import com.nh.nhcar.utils.PageNaverUtils;
import com.nh.nhcar.utils.PageSet;

public class NewslistServlet extends ServletBase {

	/**
	 * Constructor of the object.
	 */
	public NewslistServlet() {
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
		    int pno=HttpUtil.getIntPara(request, "pno", 1);
		    String key=HttpUtil.getStringPara(request, "key", "");
		    String atc=HttpUtil.getStringPara(request,"atc","");
		    key=new String(key.getBytes("ISO-8859-1"),"utf-8");
			News news=new News();
			Category category=new Category();
			PageNaverUtils pNaverUtils=new PageNaverUtils(request);
			PageSet pSet=news.getNewslist(pno, 2,key);
			if(atc.equals("app")){
				NewslistServlet.showJson(response,pSet.getDataResult());
			}else{
			request.setAttribute("newslist",pSet.getDataResult());
			request.setAttribute("pagenav",pNaverUtils.ShowPager(pSet.getDataCount()));
			request.setAttribute("catelist", category.getCartgoryList());
			//request.getRequestDispatcher("newslist.jsp").forward(request, response);
			NewslistServlet.show(request, response, "newslist.jsp");
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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
