package com.nh.nhcar.bll;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Cart extends BLLBase{
	public void add(int cpid,int cnum,int cuid){
		if(checkCart(cpid,cuid)){
			updateNum(cpid,cnum,cuid);
		}else{
			addcart(cpid,cnum,cuid);
		}

	}
	public int addcart(int cpid,int cnum,int cuid){
		String sql="insert into cart(cpid,cnum,cuid) values(?,?,?)";
		List<Object> paras=new ArrayList<Object>();
		paras.add(cpid);
		paras.add(cnum);
		paras.add(cuid);
		return jdbcUtils.update(sql, paras);
	}
	public int updateNum(int cpid,int cnum,int cuid){
		String sql="update cart set cnum=cnum+? where cpid=? and cuid=?";
		List<Object> paras=new ArrayList<Object>();
		paras.add(cpid);
		paras.add(cnum);
		paras.add(cuid);
		return jdbcUtils.update(sql, paras);
	}
	public boolean checkCart(int cpid,int cuid){
		String sql="select count(*) from cart where cpid=? and cuid=?";
		List<Object> paras=new ArrayList<Object>();
		paras.add(cpid);
		paras.add(cuid);
		int num=Integer.valueOf(jdbcUtils.queryOne(sql, paras).toString());
		return num>0;
	}
	public  List<Map<String,Object>> getCartList(int uid){
		String sql="select cid,cnum,pid,pname,ppic from cart inner join product on cpid=pid where cuid=?";
		List<Object> paras=new ArrayList<Object>();
		paras.add(uid);
		return jdbcUtils.query(sql, paras);
	}
	public void update(String[] nums,String[] cids){
		for(int i=0;i<nums.length;i++){
			int num=Integer.valueOf(nums[i]);
			int cid=Integer.valueOf(cids[i]);
			updateNum(num,cid);
		}
	}
	public void updateNum(int num,int cid){
	String sql="update cart set cnum=? where cid=? ";
	List<Object> paras=new ArrayList<Object>();
	paras.add(num);
	paras.add(cid);
	jdbcUtils.update(sql, paras);
	}
	public int delete(int cid){
		String sql="delete from cart where cid=?";
		List<Object> paras=new ArrayList<Object>();
		paras.add(cid);
		return jdbcUtils.update(sql, paras);
		
	}
	public int delete_empty(int cuid){
		String sql="delete from cart where cuid=?";
		List<Object> paras=new ArrayList<Object>();
		paras.add(cuid);
		return jdbcUtils.update(sql, paras);
		
	}
	public Map<String,Object> getCartcuid(int uid){
		String sql="select cuid from cart where cuid=?";
		List<Object> paras=new ArrayList<Object>();
		paras.add(uid);
		return jdbcUtils.queryOneRow(sql, paras);
	}
	public double getTotal(int uid){
		String sql="select sum(pprice*cnum) from cart inner join product on cpid=pid where cuid=?";
		List<Object> paras=new ArrayList<Object>();
		paras.add(uid);
		Object ototal=jdbcUtils.queryOne(sql, paras);
		return Double.valueOf(ototal.toString());
	}

}
