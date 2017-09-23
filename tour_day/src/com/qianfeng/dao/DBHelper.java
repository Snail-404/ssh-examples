package com.qianfeng.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 操作数据库辅工具类
 * 
 * 
 * */
public class DBHelper {
	// jdbc:mysql:localhost:3306/数据库名"+"?useUnicode=true&characterEncoding=utf8";
	public static final String url = "jdbc:mysql://127.0.0.1:3306/dbtour"
			+ "?useUnicode=true&characterEncoding=utf8";
	public static final String user = "root";
	public static final String pwd = "123456";
	public static final String name = "com.mysql.jdbc.Driver";
	private Connection connection;
	public PreparedStatement pst;

	public DBHelper(String sql) {
		// ctrl+1 1.异常快捷键 2.选中变量（第三个变成全局变量）3.;前自动生成返回值
		try {
			Class.forName(name);
			System.out.println("驱动加载成功!");
			connection = DriverManager.getConnection(url, user, pwd);
			System.out.println("获取链接成功!");
			pst = connection.prepareStatement(sql);
		} catch (ClassNotFoundException e) {
			System.out.println("驱动加载失败!");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("获取链接失败!");
			e.printStackTrace();
		}
	}

	/**
	 * 关闭数据库链接
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
