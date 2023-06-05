package com.nh.nhcar.servlets.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nh.nhcar.bll.Category;
import com.nh.nhcar.bll.Product;
import com.nh.nhcar.utils.HttpUtil;
import com.nh.nhcar.utils.PageNaverUtils;
import com.nh.nhcar.utils.PageSet;

public class ProductListServler extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ProductListServler() {
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
			//int cid=HttpUtil.getIntPara(request, "cid", 0 );
			int pno=HttpUtil.getIntPara(request, "pno", 1);
			Product product=new Product();
			Category category=new Category();
			//PageSet pSet=product.getProductlist(pno,2,cid);
			PageSet pSet=product.getProductlist(pno,2,"");
			request.setAttribute("productlist", pSet.getDataResult());
			PageNaverUtils pageNaverUtils=new PageNaverUtils(request);
			request.setAttribute("pagenav", pageNaverUtils.ShowPager(pSet.getDataCount()));
			request.setAttribute("catelist", category.getCartgoryList());
			request.getRequestDispatcher("productlist.jsp").forward(request, response);
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
