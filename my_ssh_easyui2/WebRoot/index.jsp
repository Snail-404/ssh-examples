<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<style type="text/css">
	#tab2,tr,th,td{
		border:1px solid blue;
		border-collapse: collapse;
	}
</style>

<script type="text/javascript">
	function btnOnclick() {
		$.ajax({
			type : "post",
			url : "user_look",
			async : false,
			dataTyoe : "json",
			success : function(data) {
			
				//alert(typeof data);
				//alert(data);

				/* alert(typeof data.json1);
				alert(data.json1);
				alert(typeof data.json1[0]);
				alert(data.json1[0]);
				
				var testjson=eval("("+data.json1+")");
				alert(typeof testjson);
				alert(testjson[0].id); */

				//var rootjson = eval("(" + data + ")");
				//alert(typeof rootjson);
				//alert(rootjson[0].id);

				//alert(data[0].userName);

				$.each(data, function(i, n) {
					var otd=$("<tr><td>"+n.id+"</td><td>"+n.userName+"</td><td>"+n.passWord+"</td><tr>");
					$("#tab2").append(otd);

				});
				

			},
			error : function(xhr, textState) {
				alert("数据请求失败");
			}
		});

	};
</script>
<body>
	<input type="button" name="btn" onclick="btnOnclick()" id="btn"
		value="查看" />

	<table id="tab2">
		<tr id="tr1">
			<th>id</th>
			<th>姓名</th>
			<th>密码</th>
		</tr>

	</table>
</body>
</html>
