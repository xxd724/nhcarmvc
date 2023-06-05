package com.nh.nhcar.bll;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Users extends BLLBase {
	public Map<String, Object> checkuser(String uname,String upwd){
		String sql="select * from users where uname=? and upwd=sha1(?)";
		List<Object> pares =new ArrayList<Object>();
		pares.add(uname);
		pares.add(upwd);
		return jdbcUtils.queryOneRow(sql, pares);
	}
	public Object check(String uname){
		String sql="select * from users where uname=?";
		List<Object> pares =new ArrayList<Object>();
		pares.add(uname);
		return jdbcUtils.queryOne(sql, pares);
	}
	public int adduser(String uname,String upwd,String uemail){
		String sql="insert into users(uname,upwd,uemail,udate) values(?,sha1(?),?,now())";
		List<Object> pares =new ArrayList<Object>();
		pares.add(uname);
		pares.add(upwd);
		pares.add(uemail);
		return jdbcUtils.update(sql, pares);
	}
}
