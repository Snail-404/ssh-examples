package com.qianfeng.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * �������ݿ⸨������
 * 
 * 
 * */
public class DBHelper {
	// jdbc:mysql:localhost:3306/���ݿ���"+"?useUnicode=true&characterEncoding=utf8";
	public static final String url = "jdbc:mysql://127.0.0.1:3306/dbtour"
			+ "?useUnicode=true&characterEncoding=utf8";
	public static final String user = "root";
	public static final String pwd = "123456";
	public static final String name = "com.mysql.jdbc.Driver";
	private Connection connection;
	public PreparedStatement pst;

	public DBHelper(String sql) {
		// ctrl+1 1.�쳣��ݼ� 2.ѡ�б��������������ȫ�ֱ�����3.;ǰ�Զ����ɷ���ֵ
		try {
			Class.forName(name);
			System.out.println("�������سɹ�!");
			connection = DriverManager.getConnection(url, user, pwd);
			System.out.println("��ȡ���ӳɹ�!");
			pst = connection.prepareStatement(sql);
		} catch (ClassNotFoundException e) {
			System.out.println("��������ʧ��!");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("��ȡ����ʧ��!");
			e.printStackTrace();
		}
	}

	/**
	 * �ر����ݿ�����
	 * */
	public void close() {
		if (connection != null) {
			try {
				connection.close();
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
