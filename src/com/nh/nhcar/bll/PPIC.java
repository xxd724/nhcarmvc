package com.nh.nhcar.bll;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PPIC extends BLLBase{
	public List<Map<String,Object>> getProductppic(int pid){
		String sql="select ppic from ppic where pid=? limit 0,4";
		List<Object> paras=new ArrayList<Object>();
		paras.add(pid);
		return jdbcUtils.query(sql,paras);
	}
	public int addProductppic(String ppic,int pid){
		String sql="insert into ppic(ppic,pid) values(?,?)";
		List<Object> paras=new ArrayList<Object>();
		paras.add(ppic);
		paras.add(pid);
		return jdbcUtils.update(sql, paras);
	}
}
