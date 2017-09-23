package com.qianfeng.dao;

import java.io.Writer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.qianfeng.bean.TourBean;
import com.qianfeng.bean.UserBean;
import com.sun.faces.config.DbfFactory;

public class TourDao {
	/**
	 * ≤Â»Î ˝æ›ø‚*/
	public static boolean setTour(Map<String, String> map){
		String sql="insert into tourinfo(userName,descript,musicName,musicUrl,photoName,photoUrl,date,sign) values(?,?,?,?,?,?,?,?)";
		DBHelper db=new DBHelper(sql);
		try {
			db.pst.setString(1, map.get("userName"));
			db.pst.setString(2, map.get("descript"));
			db.pst.setString(3, map.get("musicName"));
			db.pst.setString(4, map.get("musicUrl"));
			db.pst.setString(5, map.get("photoName"));
			db.pst.setString(6, map.get("photoUrl"));
			db.pst.setString(7, map.get("date"));
			db.pst.setString(8, map.get("sign"));
			int update=db.pst.executeUpdate();
			if(update>0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public static List<TourBean> getDiary(){
		String sql="select * from tourinfo order by id desc";
		DBHelper db=new DBHelper(sql);
		List<TourBean> list = null;
		try {
			ResultSet query = db.pst.executeQuery();
			 list=new ArrayList<TourBean>();
			while(query.next()){
				TourBean bean=new TourBean();
				bean.setDate(query.getString("date"));
				bean.setDescript(query.getString("descript"));
				bean.setMusicName(query.getString("musicName"));
				bean.setMusicUrl(query.getString("musicUrl"));
				bean.setPhotoName(query.getString("photoName"));
				bean.setPhotoUrl(query.getString("photoUrl"));
				bean.setUserName(query.getString("userName"));
				bean.setSign(query.getString("sign"));
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
	public static boolean delDiary(String sign){
		String sql="delete from tourinfo where sign=?";
		DBHelper db=new DBHelper(sql);
		try {
			db.pst.setString(1, sign);
			int update = db.pst.executeUpdate();
			if(update>0){
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
	
	public static boolean regis(UserBean user){
		
		
		String sql="insert into users(userName,passWord,sex,phone,age,address) values(?,?,?,?,?,?)";
		DBHelper db=new DBHelper(sql);
		
		try {
			db.pst.setString(1, user.getUserName());
			db.pst.setString(2, user.getPassWord());
			db.pst.setString(3, user.getSex());
			db.pst.setString(4, user.getPhone());
			db.pst.setString(5, user.getAge());
			db.pst.setString(6, user.getAddress());
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
	public static boolean checkLog(String userName,String passWord){
		String sql="select * from users where userName=? and passWord=?";
		DBHelper db=new DBHelper(sql);
		System.out.println(userName);
		//List<UserBean> list=null;
		try {
			db.pst.setString(1, userName);
			db.pst.setString(2, passWord);
			java.sql.ResultSet rs=db.pst.executeQuery();
			if (rs.next()) {
				System.out.println("trues");
				return true;
				
			}
			else {
				System.out.println("fas");
				return false;
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	public static List<UserBean> getMess(String userName){
		String sql="select * from users where userName=?";
		DBHelper db=new DBHelper(sql);
		List<UserBean> list=null;
		try {
			db.pst.setString(1, userName);
			java.sql.ResultSet rs=db.pst.executeQuery();
			list=new ArrayList<UserBean>();
			while (rs.next()) {
				UserBean bean=new UserBean();
				bean.setUserName(rs.getString("userName"));
				bean.setSex(rs.getString("sex"));
				bean.setPhone(rs.getString("phone"));
				bean.setId(rs.getInt("id"));
				bean.setAge(rs.getString("age"));
				bean.setAddress(rs.getString("address"));
				
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
}
