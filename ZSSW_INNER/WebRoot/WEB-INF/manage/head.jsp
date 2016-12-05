<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">    
    <title>My JSP 'head.jsp' starting page</title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="description" content="This is my page">	
	<link rel="stylesheet" type="text/css" href="css/header.css"/>
  </head>
 <body>
	<div id="header">
	${basePath }
		<div id="logo" class="clearfix">
			<img src="img/logo.png"/>
			<h4>苏州地税掌上税务二期后台系统</h4>
		</div>
		<div class="user-info">
			<a href="#">${user.xm }</a>
			<a href="manage/user_logout.action" target="_top">退出系统</a>
		</div>
	</div>
</body>
</html>
