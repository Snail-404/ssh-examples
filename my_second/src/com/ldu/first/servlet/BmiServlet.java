package com.ldu.first.servlet;

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
import com.ldu.first.bean.BmiBean;
import com.ldu.first.dao.DBHelper;


public class BmiServlet extends HttpServlet {
	private static final long seriaVersionUID=1L;
	private HttpServletRequest request;//������
	private HttpServletResponse response;
	private PrintWriter writer;
	
	
	public BmiServlet() {
		super();
	}

	
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.request=request;
		this.response=response;
		//�����������ñ����ʽ
		this.request.setCharacterEncoding("utf-8");
		this.response.setCharacterEncoding("utf-8");
		//����Ӧ�������±���
		this.response.setContentType("text/html;charset=utf-8");
		
		//������
		this.response.setHeader("Access-Control-Allow-Origin", "*");
		//��ȡ�����
		writer=this.response.getWriter();
		
		//���������ȡ����
		String method=this.request.getParameter("method");
		if (method.equals("add_bmi")) {
			addBmi();
		}else if (method.equals("start_bmi")) {
			/*JsonArray array=getBmiData();
			writer.println(array);*/
			
			JsonArray array=getBmiData();
			writer.println(array);
		}else if (method.equals("delete_bmi")) {
			
			deleteBmi();
		}
		writer.flush();
		
	}

	private void deleteBmi(){
		String sign=this.request.getParameter("sign");
		boolean success=DBHelper.deleteBmi(sign);
		if (success) {
			writer.println(1);
		}else
			writer.println(0);
		System.out.println(sign);
		
	}
	
	
	private JsonArray getBmiData() {
		List<BmiBean> list=DBHelper.getBmi();
		Gson gson=new Gson();
		//ʹ��Gson��listת��Ϊstring����
		String json=gson.toJson(list);
		System.out.println("----"+json);
		//��stringת��Ϊjsonarray
		//return json;
		JsonArray array=new JsonParser().parse(json).getAsJsonArray();
		System.out.println("++++++++++++++"+array);
		return array;
		
	}


	private void addBmi() {
		// TODO Auto-generated method stub
		String height=this.request.getParameter("height");
		String weight=this.request.getParameter("weight");
		String bmi=this.request.getParameter("bmi");
		String date=this.request.getParameter("dateTime");
		BmiBean bean=new BmiBean();
		bean.setBmi(bmi);
		bean.setDate(date);
		bean.setHeight(height);
		bean.setWeight(weight);
		boolean success=DBHelper.addBmi(bean);
		if (success) {
			JsonArray array=getBmiData();
			writer.println(array);		
		}
		else {
			writer.println(0);
		}
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
