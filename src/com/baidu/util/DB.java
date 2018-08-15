package com.baidu.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 工具类-连接数据库
 * 
 * @author Administrator
 * 
 */
public class DB {
	private static final String driver = "com.mysql.jdbc.Driver"; // 数据库驱动
	// 连接数据库的URL地址
	private static final String url = "jdbc:mysql://localhost:3306/blog?useUnicode=true&characterEncoding=UTF-8";
	private static final String username = "root";// 数据库的用户名
	private static final String password = "root";// 数据库的密码
	
	private static Connection conn = null;
	
	static {
		try {
			Class.forName(driver);//加载驱动
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//返回一个数据库连接对象
	public static Connection getConnection() throws Exception{
		if(conn == null){
			conn = DriverManager.getConnection(url, username, password);
		}
		return conn;
	}
	
	//测试
	public static void main(String[] args) {
		try {
			Connection conn = DB.getConnection();
			if(conn != null){
				System.out.println("连接数据库正常");
			}else{
				System.out.println("连接数据库失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
