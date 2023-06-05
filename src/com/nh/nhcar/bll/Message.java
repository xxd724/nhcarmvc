package com.nh.nhcar.bll;

import java.util.ArrayList;
import java.util.List;

import com.nh.nhcar.utils.PageSet;

public class Message extends BLLBase{
	public int addmessage(String mname,String mtitle,String mcontent){
		String sql="insert into message(mname,mtitle,mcontent,mdate) values(?,?,?,now())";
		List<Object> pares=new ArrayList<Object>();
		pares.add(mname);
		pares.add(mtitle);
		pares.add(mcontent);
		return jdbcUtils.update(sql, pares);
	}
	public int addMessage(String pmname,String pmcontent,int ppid){
		String sql="insert into pmessage(pmname,pmcontent,ppid,pmdate) values (?,?,?,now())";
	    List<Object> paras=new ArrayList<Object>();
	    paras.add(pmname);	
	    paras.add(pmcontent);
	    paras.add(ppid);
	    return jdbcUtils.update(sql,paras);
	}
	public PageSet getMessagelist(int pno,int pageSize){
		String sql="select * from message order by mid desc";
		return jdbcUtils.queryPage(sql, null, pno, pageSize);
	}
	public PageSet getPMessageList(int pno,int pageSize,int pid){
		String sql="select * from pmessage where ppid=? order by pmid desc";
		List<Object> paras=new ArrayList<Object>();
		paras.add(pid);
		return jdbcUtils.queryPage(sql, paras, pno, pageSize);
	}

}
