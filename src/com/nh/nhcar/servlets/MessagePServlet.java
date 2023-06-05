package com.nh.nhcar.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nh.nhcar.bll.Message;
import com.nh.nhcar.utils.JsUtils;

public class MessagePServlet extends ServletBase {

	/**
	 * Constructor of the object.
	 */
	public MessagePServlet() {
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
			MessagePServlet.show(request, response, "product.jsp");

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
			String pmname=request.getParameter("pmname");
			int ppid=Integer.valueOf(request.getParameter("ppid"));
			String pmcontent=request.getParameter("pmcontent");
			String pmcheckno=request.getParameter("pmcheckno");
			if(pmname.isEmpty()){
				JsUtils.JsUtilBack(out, "昵称不能为空");
				return;
			}
			if(pmcontent.isEmpty()){
				JsUtils.JsUtilBack(out, "内容不能为空");
				return;
			}
			HttpSession session=request.getSession();
		    String rand=String.valueOf(session.getAttribute("rand"));
		    if (!pmcheckno.equalsIgnoreCase(rand)){
		    	JsUtils.JsUtilBack(out, "验证码错误！");
		    	return;
		    }
			Message message=new Message();
			int result=message.addMessage(pmname, pmcontent, ppid);
			if(result>0){
				JsUtils.JsUtilGo(out, "发布成功","product?pid="+ppid+"");
			}else{
				JsUtils.JsUtilBack(out, "发布失败");
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
