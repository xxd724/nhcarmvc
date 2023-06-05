package com.nh.nhcar.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nh.nhcar.bll.Cart;
import com.nh.nhcar.bll.Orders;
import com.nh.nhcar.utils.JsUtils;

public class OrderServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public OrderServlet() {
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
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out=response.getWriter();
			if(request.getSession().getAttribute("sUser")==null){
	    	 
				JsUtils.JsUtilGo(out, "���ȵ�¼", "login");
				return;
			}
			Map<String,Object> sUser=(Map<String,Object>)request.getSession().getAttribute("sUser");
			int uid=Integer.valueOf(sUser.get("uid").toString());
			Orders orders=new Orders();
			Cart cart=new Cart();
			double sum_total=orders.getTotal(uid);
			request.setAttribute("total", sum_total);
			request.setAttribute("cartlist", cart.getCartList(uid));
			request.getRequestDispatcher("order.jsp").forward(request, response);
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
			request.setCharacterEncoding("utf-8");
		     response.setContentType("text/html;charset=utf-8");
		     PrintWriter out=response.getWriter();
		     String oname=request.getParameter("oname");
		     String otel=request.getParameter("otel");
		     String oaddress=request.getParameter("oaddress");
		     if(request.getSession().getAttribute("sUser")==null){   
		    	 JsUtils.JsUtilGo(out, "���ȵ�¼", "login");
		    	 return;
		     }
		     Map<String,Object> sUser=(Map<String,Object>)request.getSession().getAttribute("sUser");
		     int uid=Integer.valueOf(sUser.get("uid").toString());
		     //������֤
		     if(oname.isEmpty()){
		     JsUtils.JsUtilBack(out,"�û�������Ϊ�գ�");
		   	  return;
		     }
		     if(otel.isEmpty()){
		   	  JsUtils.JsUtilBack(out,"���벻��Ϊ�գ�");
		   	  return;
		     }
		     if(oaddress.isEmpty()){
			   	  JsUtils.JsUtilBack(out,"���벻��Ϊ�գ�");
			   	  return;
			 }
		     Orders orders=new Orders();
		     int oid=orders.add(oname, otel, oaddress, uid);	  
		     if(oid>0){
		    	 JsUtils.JsUtilGo(out, "��ɶ�����������:"+oid, "index");
		     }else{
		    	 JsUtils.JsUtilBack(out, "����ʧ��");
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
