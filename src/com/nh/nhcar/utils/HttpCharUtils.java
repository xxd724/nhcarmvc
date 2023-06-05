package com.nh.nhcar.utils;

public class HttpCharUtils {
	public static String doHTML(String val){
		val=val.replaceAll("<", "&lt");
		val=val.replaceAll(">", "&gt");
		val=val.replaceAll("\"", "&quot");
		val=val.replaceAll("&", "&amp");
		return val;
	}

}
