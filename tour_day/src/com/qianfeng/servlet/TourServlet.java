package com.qianfeng.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.opensymphony.xwork2.ActionContext;
import com.qianfeng.bean.TourBean;
import com.qianfeng.bean.UserBean;
import com.qianfeng.dao.DBHelper;
import com.qianfeng.dao.TourDao;
import com.qianfeng.tools.Config;
import com.qianfeng.tools.ServletManager;

public class TourServlet extends HttpServlet {
	private static final long serialVerionID = 1L;
	private HttpServletRequest request;// 请求码
	private HttpServletResponse response;// 响应码
	private PrintWriter writer;
	
	ActionContext ac=ActionContext.getContext();
	Map<String, Object> session;
	public TourServlet() {
		super();
	}

	
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.request=request;
		this.response=response;
		this.request.setCharacterEncoding("utf-8");
		this.response.setCharacterEncoding("utf-8");
		this.response.setContentType("text/html;charset=utf-8");
		// 跨域处理
		this.response.setHeader("Access-Control-Allow-Origin", "*");
		// 获取输出流
		writer = this.response.getWriter();
		String method=this.request.getParameter("method");
		
		if (method.equals("login")) {
			
			//writer.print("login success");
			loginCheck();
		}		
		writer.flush();
		if (method.equals("lookMess")) {
			
			JsonArray array=lookMess();
			writer.println(array);
			//loginCheck();
		}		
		writer.flush();
		
		if (method.equals("register")) {
			register();
			
		}
		writer.flush();
		if(method.equals("sendData")){
			//获取到的音乐 图片文件写入服务器，存储服务器上的路径
			//获取存放图片的绝对路径
			String path = getServletContext().getRealPath("/upload");
			System.out.println(path);
			Map<String, String> map = new ServletManager().upload(request, path);
			System.out.println(map);
			boolean success = TourDao.setTour(map);
			if(success){
				String json=new Gson().toJson(map);
				writer.print(json);
			}
		}
		writer.flush();
		if(method.equals("getData")){
			List<TourBean> list=TourDao.getDiary();
			String json2=new Gson().toJson(list);
			System.out.println(json2);
			writer.print(json2);
		}
		writer.flush();
		if(method.equals("downloadData")){
			String musicName = this.request.getParameter("music");
			boolean success = ServletManager.download(musicName);
			if(success){
				writer.print(Config.DOWNLOADPATH+"/"+musicName);
			}else{
				writer.print("下载失败");
			}
		}
		writer.flush();
		if(method.equals("delData")){
			String sign = this.request.getParameter("sign");
			System.out.println(sign);
			boolean success = TourDao.delDiary(sign);
			System.out.println(success);
			if(success){
				writer.print(1);
			}else{
				writer.print(0);
			}
		}
		writer.flush();
		
	}

	private JsonArray lookMess() {
		String userName=this.request.getParameter("userName");
		List<UserBean> list=TourDao.getMess(userName);
		Gson gson=new Gson();
		//使用Gson吧list转换为string类型
		String json=gson.toJson(list);
		System.out.println(json);
		//把string转换为jsonarray
		JsonArray array=new JsonParser().parse(json).getAsJsonArray();
		
		return array;
		
	}
	
	
	public void loginCheck(){
		
		String userName=this.request.getParameter("userName");
		String passWord=this.request.getParameter("passWord");
		
		boolean success=TourDao.checkLog(userName,passWord);
		if (success) {
			writer.print(1);	
			
		}
		else {
			writer.print(0);
		}
	}
	
	public Map<String, Object> getSession() {
		return session;
	}


	public void setSession(Map<String, Object> session) {
		this.session = session;
	}


	public void register(){
		String userName=this.request.getParameter("userName");
		String passWord=this.request.getParameter("passWord");
		String sex=this.request.getParameter("sex");
		String phone=this.request.getParameter("phone");
		String age=this.request.getParameter("age");
		String address=this.request.getParameter("address");
		System.out.println(sex);
		UserBean user=new UserBean();
		user.setUserName(userName);
		user.setPassWord(passWord);
		user.setSex(sex);
		user.setPhone(phone);
		user.setAge(age);
		user.setAddress(address);
		boolean success=TourDao.regis(user);
		if (success) {
			writer.print(1);			
		}
		else {
			writer.print(0);
		}
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
