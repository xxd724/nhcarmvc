package com.nh.nhcar.bll;

import com.nh.nhcar.utils.JdbcUtils;

public class BLLBase {
		protected JdbcUtils jdbcUtils;
		public BLLBase(){
			jdbcUtils=new JdbcUtils();
		}
	

}
