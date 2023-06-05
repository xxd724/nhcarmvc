package com.nh.nhcar.utils;

import java.io.IOException;
import java.io.PrintWriter;

public class JsUtils {
	public static void JsUtilBack(PrintWriter out,String msg) throws IOException{
		out.println("<script language='Javascript'>alert('"+msg+"');history.back();</script>");
	}
	public static void JsUtilGo(PrintWriter out,String msg,String url) throws IOException{
		out.println("<script language='Javascript'>alert('"+msg+"');location.href='"+url+"';</script>");
	}
}
