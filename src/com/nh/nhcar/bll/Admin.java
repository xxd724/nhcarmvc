package com.nh.nhcar.bll;

import java.util.ArrayList;
import java.util.List;

public class Admin extends BLLBase{
	public int checkadmin(String aname,String apwd){
		String sql="select aid from admin where aname=? and apwd=?";
		List<Object> pares=new ArrayList<Object>();
		pares.add(0,aname);
		pares.add(1,apwd);
		Object aid=jdbcUtils.queryOne(sql, pares);
		return aid==null?0:Integer.valueOf(aid.toString());
	}
	   
}
