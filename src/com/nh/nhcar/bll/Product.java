package com.nh.nhcar.bll;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.nh.nhcar.utils.PageSet;

public class Product extends BLLBase{
	public int addProduct(String pname,float pprice,int pcid,String pcontent,String ppic ,int phot,int pnew,int pcheap){
		String sql="insert into product(pname,pprice,pcid,pcontent,ppic,phot,pnew,pcheap) values(?,?,?,?,?,?,?,?)";
		List<Object> paras=new ArrayList<Object>();
		paras.add(pname);
		paras.add(pprice);
		paras.add(pcid);
		paras.add(pcontent);
		paras.add(ppic);
		paras.add(phot);
		paras.add(pnew);
		paras.add(pcheap);
		return jdbcUtils.update(sql, paras);
	}
	
	public PageSet getProductlist(int pno,int pageSize,String key){
		String sql="select pid,pname,ppic,pprice,cname from product inner join category on pcid=cid where pname like ? or cname like ? order by pdate desc";
		List<Object> paras=new ArrayList<Object>();
		paras.add("%"+key+"%");
		paras.add("%"+key+"%");
		return jdbcUtils.queryPage(sql, paras, pno, pageSize);
	}
	
	public PageSet getProductlist(int pno,int pageSize,int cid){
		String sql="select pid,pname,ppic,pprice,cname from product inner join category on pcid=cid where cid=? order by pdate desc";
		List<Object> paras=new ArrayList<Object>();
		paras.add(cid);
		return jdbcUtils.queryPage(sql,paras, pno, pageSize);
	}
	
	public int deleterProduct(int pid){
		String sql="delete from product where pid=?";
		List<Object> paras=new ArrayList<Object>();
		paras.add(pid);
		return jdbcUtils.update(sql, paras);
	}
	
	public Map<String,Object> getProduct(int pid){
		String sql="select * from product inner join category on pcid=cid where pid=?";
		List<Object> paras=new ArrayList<Object>();
		paras.add(pid);
		return jdbcUtils.queryOneRow(sql, paras);
	}
	
	public int modifyProudct(String pname,int pprice,int pcid,String pcontent,String ppic,int pid,int phot,int pnew,int pcheap){
		String sql="update product set pname=?,pprice=?,pcid=?,pcontent=?,ppic=?,phot=?,pnew=?,pcheap=? where pid=?";
		List<Object> paras=new ArrayList<Object>();
		paras.add(pname);
		paras.add(pprice);
		paras.add(pcid);
		paras.add(pcontent);
		paras.add(ppic);
		paras.add(phot);
		paras.add(pnew);
		paras.add(pcheap);
		paras.add(pid);
		return jdbcUtils.update(sql, paras);
	}
	
	public List<Map<String,Object>> getHomeProductList(String pType){
		String fileName="";
		if(pType.equalsIgnoreCase("hot")){
			fileName="phot";
		}else if(pType.equalsIgnoreCase("new")){
			fileName="pnew";
		}else if(pType.equalsIgnoreCase("cheap")){
			fileName="pcheap";
		}
		String sql="select pid,ppic,pname,pprice from product where "+fileName+"=1 order by pdate desc limit 0,4";
		return jdbcUtils.query(sql);
	}
}
