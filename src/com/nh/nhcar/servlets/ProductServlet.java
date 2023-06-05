package com.nh.nhcar.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nh.nhcar.bll.Category;
import com.nh.nhcar.bll.Message;
import com.nh.nhcar.bll.PPIC;
import com.nh.nhcar.bll.Product;
import com.nh.nhcar.utils.HttpUtil;
import com.nh.nhcar.utils.PageNaverUtils;
import com.nh.nhcar.utils.PageSet;

public class ProductServlet extends ServletBase {

	/**
	 * Constructor of the object.
	 */
	public ProductServlet() {
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
			int pid=HttpUtil.getIntPara(request, "pid", 0);
			int pno=HttpUtil.getIntPara(request, "pno", 1);
			int ppid=HttpUtil.getIntPara(request, "ppid", 0);
			Message message=new Message();
			PageNaverUtils pNaverUtils=new PageNaverUtils(request);
			Product product=new Product();
			PPIC ppic=new PPIC();
			Category category=new Category();
			request.setAttribute("product", product.getProduct(pid));
			request.setAttribute("productppic", ppic.getProductppic(pid));
			PageSet pSet=message.getPMessageList(pno, 2,pid);
			request.setAttribute("pmessagelist",pSet.getDataResult());
			request.setAttribute("pagenav",pNaverUtils.ShowPager(pSet.getDataCount()));
			request.setAttribute("catelist", category.getCartgoryList());
			request.getRequestDispatcher("product.jsp").forward(request, response);
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
