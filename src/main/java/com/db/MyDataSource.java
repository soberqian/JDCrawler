package com.db;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class MyDataSource {
	
	public static DataSource getDataSource(String connectURI){
		
		BasicDataSource ds = new BasicDataSource();
		 //MySQL��jdbc����
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUsername("root");              //��Ҫ���ӵ����ݿ���
		ds.setPassword("112233");                //MySQL�ĵ�½����
		ds.setUrl(connectURI);
		
		return ds;
		
	}

}

