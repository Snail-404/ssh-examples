package com.ldu.first.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ldu.first.bean.BmiBean;
import com.ldu.first.bean.UserBean;
import com.mysql.jdbc.ResultSet;

/**
 * 操作数据库的工具类
 * @author dai
 *
 */

public class DBHelper {
	public static final String url="jdbc:mysql://127.0.0.1:3306/db_bmi"
			+"?useUnicode=true&characterEncoding=utf-8";
	public static final String user="root";
	public static final String password="123456";
	public static final String name="com.mysql.jdbc.Driver";
	private Connection connection;//连接
	public PreparedStatement pst;
	
	public DBHelper(String sql){
		//快捷键 try catch
		try {
			Class.forName(name);
			System.out.println("驱动加载成功");
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("成功获取连接");
			pst = connection.prepareStatement(sql);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("驱动加载失败");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("获取连接失败");
		}
	}
	
	public void close(){
		try {
			connection.close();
			pst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			connection=null;
			pst=null;
		}
	}
	
	public static boolean addBmi(BmiBean bean){
		String sql="insert into bmiinfo(date,height,weight,bmi)"
				+"values(?,?,?,?)";
		DBHelper db=new DBHelper(sql);
		try {
			db.pst.setString(1, bean.getDate());
			db.pst.setString(2, bean.getHeight());
			db.pst.setString(3, bean.getWeight());
			db.pst.setString(4, bean.getBmi());
			
			int update=db.pst.executeUpdate();
			if (update>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close();			
		}
		return false;
	}
	
	public static List<BmiBean> getBmi(){
		String sql="select * from bmiinfo";
		DBHelper db=new DBHelper(sql);
		List<BmiBean> list=null;
		try {
			java.sql.ResultSet rs=db.pst.executeQuery();
			list=new ArrayList<BmiBean>();
			while (rs.next()) {
				BmiBean bean=new BmiBean();
				bean.setBmi(rs.getString("bmi"));
				bean.setDate(rs.getString("date"));
				bean.setHeight(rs.getString("height"));
				bean.setWeight(rs.getString("weight"));
				bean.setId(rs.getInt("id"));
				list.add(bean);
								
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close();
		}
		return list;
		
	}
	
	public static boolean deleteBmi(String sign){
		int id=Integer.valueOf(sign);
		String sql="delete from bmiinfo where id=?";
		DBHelper db=new DBHelper(sql);
		try {
			db.pst.setInt(1, id);
			int update=db.pst.executeUpdate();
			if (update>0) {
				return true;
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;		
	}
	
	
}
