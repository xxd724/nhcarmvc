package com.nh.nhcar.servlets.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nh.nhcar.bll.PPIC;
import com.nh.nhcar.bll.Product;
import com.nh.nhcar.utils.HttpUtil;
import com.nh.nhcar.utils.JsUtils;
import com.nh.nhcar.utils.UploadUtil;


public class ProductAddppicServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ProductAddppicServlet() {
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
			Product product=new Product();
			request.setAttribute("product", product.getProduct(pid));
			request.getRequestDispatcher("productaddppic.jsp").forward(request, response);
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
		//接收数据，并添加数据库
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		UploadUtil uploadUtil=new UploadUtil(getServletContext(),request);
		int pid=uploadUtil.getParameter("pid")==null?0:Integer.valueOf(uploadUtil.getParameter("pid"));
		String ppic="";
		try{
			List<String> path=uploadUtil.upload();
			ppic=path.get(0);
		}catch(Exception e){
			e.printStackTrace();
		}
		PPIC pic=new PPIC();
		int result=pic.addProductppic(ppic,pid);
		if(result>0){
			JsUtils.JsUtilGo(out, "添加成功", "productlist");
		}else{
			JsUtils.JsUtilBack(out, "添加失败");
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
