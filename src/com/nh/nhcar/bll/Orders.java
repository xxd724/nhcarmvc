package com.nh.nhcar.bll;

import java.util.ArrayList;
import java.util.List;

public class Orders extends BLLBase{
	//在orders表中添加一条订单记录，同时返回订单id
	//同时根据订单id，往items表中插入多条记录
	public int add(String oname,String otel,String oaddress,int ouid){
		double ototal=getTotal(ouid);
		int oid=addOrders(oname,otel,oaddress,ouid,ototal);
		addItems(oid,ouid);
		return oid;
	}
	public int addOrders(String oname,String otel,String oaddress,int ouid,double ototal){
		String sql ="insert into orders(oname,otel,oaddress,ouid,ototal) values(?,?,?,?,?)";
		List<Object> paras=new ArrayList<Object>();
		paras.add(oname);
		paras.add(otel);
		paras.add(oaddress);
		paras.add(ouid);
		paras.add(ototal);
		return jdbcUtils.insertreturnID(sql, paras);
	}
	public void addItems(int oid,int uid){
		String sql="insert items(ipid,inum,ipprice,ioid) select cpid,cnum,pprice,? from cart inner join product on cpid=pid where cuid=?";
		List<Object> paras=new ArrayList<Object>();
		paras.add(oid);
		paras.add(uid);
		jdbcUtils.update(sql, paras);
	}
	
	
	
	public double getTotal(int uid){
		String sql="select sum(pprice*cnum) from cart inner join product on cpid=pid where cuid=?";
		List<Object> paras=new ArrayList<Object>();	
		paras.add(uid);
		Object ototal=jdbcUtils.queryOne(sql, paras);
		return Double.valueOf(ototal.toString());
	}
}
