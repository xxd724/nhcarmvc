package com.nh.nhcar.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nh.nhcar.bll.Message;
import com.nh.nhcar.utils.HttpUtil;
import com.nh.nhcar.utils.JsUtils;
import com.nh.nhcar.utils.PageNaverUtils;
import com.nh.nhcar.utils.PageSet;

public class MessageServlet extends ServletBase{

	/**
	 * Constructor of the object.
	 */
	public MessageServlet() {
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
			if(request.getSession().getAttribute("sUser")==null){
				JsUtils.JsUtilGo(out, "请先登录！", "login");
				return;
			}
			int pno=HttpUtil.getIntPara(request, "pno", 1);
			String atc=HttpUtil.getStringPara(request,"atc","");
			Message message=new Message();
			PageNaverUtils pageNaverUtils=new PageNaverUtils(request);
			PageSet pageSet=message.getMessagelist(pno,2);
			if(atc.equals("app")){
				MessageServlet.showJson(response,pageSet.getDataResult());
			}else{
			request.setAttribute("messagelist", pageSet.getDataResult());
			request.setAttribute("pagenav",pageNaverUtils.ShowPager(pageSet.getDataCount()));
			//request.getRequestDispatcher("message.jsp").forward(request, response);
			MessageServlet.show(request, response, "message.jsp");
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
		    response.setContentType("text/html;charset=utf-8");
		    request.setCharacterEncoding("utf-8");
		    PrintWriter out=response.getWriter();
		    String mname=HttpUtil.escapeHtml(request.getParameter("mname"));
		    String mtitle=HttpUtil.escapeHtml(request.getParameter("mtitle"));
		    String mcontent=HttpUtil.escapeHtml(request.getParameter("mcontent"));
		    String mcheckno=request.getParameter("mcheckno");
		     if(mname.isEmpty()){
		   		JsUtils.JsUtilBack(out, "昵称不能为空！");
		   		return;
		    }
		    if(mtitle.isEmpty()){
		   		JsUtils.JsUtilBack(out, "标题不能为空！");
		   		return;
		    }
		    if(mcontent.isEmpty()){
		   		JsUtils.JsUtilBack(out, "内容不能为空！");
		   		return;
		    }
		    HttpSession session=request.getSession();
		    String rand=String.valueOf(session.getAttribute("rand"));
		    if(!mcheckno.equalsIgnoreCase(rand)){
		   		JsUtils.JsUtilBack(out, "验证码不正确！");
		   		return;
		    }
		    Message message=new Message();
		    int result=message.addmessage(mname, mtitle, mcontent);
		    if(result>0){
		    	JsUtils.JsUtilGo(out, "发布成功！", "message");
		    }else{
		    	JsUtils.JsUtilBack(out, "发布失败！");
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
