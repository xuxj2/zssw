<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
 
    <base href="<%=basePath%>"> 
    <title></title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">	
	<script src="js/jquery.min.js"></script>
  </head> 
  

<frameset rows="80,*" cols="*" frameborder="NO" border="0" framespacing="0">
	<frame src="manage/manage_head.action" name="topFrame" scrolling="NO" noresize />
	<frameset cols="200,*" frameborder="NO" border="0" framespacing="0">
		<frame src="manage/manage_left.action" name="leftFrame" scrolling="NO" noresize>
		<frame src="manage/manage_main.action" name="mainFrame">
	</frameset>
</frameset>
 
</html>
