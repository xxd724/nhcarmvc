package com.nh.nhcar.bll;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.nh.nhcar.utils.JdbcUtils;
import com.nh.nhcar.utils.PageSet;

public class News extends BLLBase{
	
	public Map<String, Object> getNews(int nid){
	String sql="select news.*,aname from news inner join admin on naid=aid where nid=?";
	List<Object> pares=new ArrayList<Object>();
	pares.add(nid);
	return jdbcUtils.queryOneRow(sql, pares);
	}
	
	public PageSet getNewslist(int pno,int pageSize,String key){
		String sql="select nid,ndate,ntitle from news where ntitle like ? order by nid desc";
		List<Object> pares=new ArrayList<Object>();
		pares.add("%"+key+"%");
		return jdbcUtils.queryPage(sql, pares, pno, pageSize);
	}
	
	public int insertnews(String ntitle,String ncontent,String nfrom,int naid){
		String sql="insert into news(ntitle,ncontent,nfrom,ndate,naid) values(?,?,?,now(),?)";
		List<Object> pares=new ArrayList<Object>();
		pares.add(ntitle);
		pares.add(ncontent);
		pares.add(nfrom);
		pares.add(naid);
		return jdbcUtils.update(sql, pares);
	}
	
	public PageSet Newslist(int pno,int pageSize){
		String sql="select * from news order by nid desc";
		return jdbcUtils.queryPage(sql, null, pno, pageSize);
	}
	
	public int Deletenews(int nid){
		String sql="delete from news where nid=?";
		List<Object> pares=new ArrayList<Object>();
		pares.add(nid);
		return jdbcUtils.update(sql, pares);
	}
	
	public int Modifynews(String ntitle,String nfrom,String ncontent,int nid){
		String sql="update news set ntitle=?,nfrom=?,ncontent=? where nid=?";
		List<Object> pares=new ArrayList<Object>();
		pares.add(ntitle);
		pares.add(nfrom);
		pares.add(ncontent);
		pares.add(nid);
		return jdbcUtils.update(sql, pares);
	}
}
