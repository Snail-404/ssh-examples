<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    <form action="user_login" method="post">
    	<input type="text" name="user.userName">
    	<input type="password" name="user.passWord">
    	<input type="submit" value="提交">
    </form>
    <a href="user_look">查看信息</a>
    <a href="user_delete?id=26">删除信息</a>
    <br>
    
    <form action="user_update">
    	<input type="text" name="user2.id">
    	<input type="text" name="user2.userName">
    	<input type="password" name="user2.passWord">
    	<input type="submit" value="提交">
    </form>
  </body>
</html>
