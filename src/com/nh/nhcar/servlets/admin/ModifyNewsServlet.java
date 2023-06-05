package com.nh.nhcar.servlets.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nh.nhcar.bll.News;
import com.nh.nhcar.servlets.ServletBase;
import com.nh.nhcar.utils.HttpUtil;
import com.nh.nhcar.utils.JsUtils;

public class ModifyNewsServlet extends ServletBase {
	
	public ModifyNewsServlet() {
		super();
	}


	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	News news=new News();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			response.setContentType("text/html;charset=utf-8");
		    request.setCharacterEncoding("utf-8");
			PrintWriter out=response.getWriter();
			if(request.getSession().getAttribute("a_aid")==null){
				JsUtils.JsUtilGo(out, "请先登录！", "../adminlogin/login");
				return;
			}
			int nid =HttpUtil.getIntPara(request, "nid",0);
			HttpSession session=request.getSession();
			session.setAttribute("nid", nid);
			Map<String,Object> mod= news.getNews(nid);
			request.setAttribute("modify", mod);
			show(request,response,"modifynews.jsp");
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			response.setContentType("text/html;charset=utf-8");
		    request.setCharacterEncoding("utf-8");
			PrintWriter out=response.getWriter();
			
			request.setCharacterEncoding("utf-8");
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
			HttpSession session=request.getSession();
			int nid =Integer.valueOf(session.getAttribute("nid").toString());
			int result= news.Modifynews(ntitle, nfrom, ncontent, nid);
			if(result>0){
			   	JsUtils.JsUtilGo(out,"修改成功！","newslist");
			}else{
			   	JsUtils.JsUtilBack(out, "修改失败！");
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
