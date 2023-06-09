package com.nh.nhcar.servlets.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nh.nhcar.bll.Category;
import com.nh.nhcar.bll.Product;
import com.nh.nhcar.utils.HttpUtil;
import com.nh.nhcar.utils.JsUtils;
import com.nh.nhcar.utils.UploadUtil;

public class ProductModifyServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ProductModifyServlet() {
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
		Category category=new Category();
		request.setAttribute("product",product.getProduct(pid));
		request.setAttribute("catelist", category.getCartgoryList());
		request.getRequestDispatcher("productmodify.jsp").forward(request, response);
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
		PrintWriter out=response.getWriter();
		UploadUtil uploadUtil=new UploadUtil(getServletContext(),request);
		String pname=uploadUtil.getParameter("pname");
		int pprice=uploadUtil.getParameter("pprice").equals("")?0:Integer.valueOf(uploadUtil.getParameter("pprice"));
		int pcid=Integer.valueOf(uploadUtil.getParameter("pcid"));
		String pcontent=uploadUtil.getParameter("pcontent");
		int pid=Integer.valueOf(uploadUtil.getParameter("pid"));
		int phot=uploadUtil.getParameter("phot")==null?0:1;
		int pnew=uploadUtil.getParameter("pnew")==null?0:1;
		int pcheap=uploadUtil.getParameter("pcheap")==null?0:1;
		if(pname.isEmpty()){
			JsUtils.JsUtilBack(out, "名称不能为空");
			return;
		}
		if(pprice<=0){
			JsUtils.JsUtilBack(out, "价格不对");
			return;
		}
		if(pcid<=0){
			JsUtils.JsUtilBack(out, "请选择品牌");
			return;
		}
		if(pcontent.isEmpty()){
			JsUtils.JsUtilBack(out, "简介不能为空");
			return;
		}
		String ppic=uploadUtil.getParameter("ppic");
		List<String> path=null;
		try{
			path=uploadUtil.upload();
		}catch(Exception e){
			e.printStackTrace();
		}
		if(path.size()>0){
			uploadUtil.deleteFile(ppic);
			ppic=path.get(0);
		}
		Product product=new Product();
		int result=product.modifyProudct(pname, pprice, pcid, pcontent, ppic, pid,phot,pnew,pcheap);
		if(result>0){
			JsUtils.JsUtilGo(out, "修改成功", "productlist");
		}else{
			JsUtils.JsUtilBack(out, "修改失败");
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
