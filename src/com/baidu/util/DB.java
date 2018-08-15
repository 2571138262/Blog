package com.baidu.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * ������-�������ݿ�
 * 
 * @author Administrator
 * 
 */
public class DB {
	private static final String driver = "com.mysql.jdbc.Driver"; // ���ݿ�����
	// �������ݿ��URL��ַ
	private static final String url = "jdbc:mysql://localhost:3306/blog?useUnicode=true&characterEncoding=UTF-8";
	private static final String username = "root";// ���ݿ���û���
	private static final String password = "root";// ���ݿ������
	
	private static Connection conn = null;
	
	static {
		try {
			Class.forName(driver);//��������
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//����һ�����ݿ����Ӷ���
	public static Connection getConnection() throws Exception{
		if(conn == null){
			conn = DriverManager.getConnection(url, username, password);
		}
		return conn;
	}
	
	//����
	public static void main(String[] args) {
		try {
			Connection conn = DB.getConnection();
			if(conn != null){
				System.out.println("�������ݿ�����");
			}else{
				System.out.println("�������ݿ�ʧ��");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
