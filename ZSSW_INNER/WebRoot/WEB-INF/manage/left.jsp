<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String outerPath = "http://61.177.61.227:7001/zssw_public/manage/";	
	//String outerPath = "http://127.0.0.1:8080/zssw_public/manage/";
	String sessionId=request.getSession().getId();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'left.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css"
			href="css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="css/left.css" />
		<script src="js/jquery.min.js"></script>
		<script type="text/javascript">
	$(document).ready(function() {
		$("li").bind("click", function() {
			$(this).addClass("active");
			$(this).siblings().removeClass("active");
		});
	});
</script>

	</head>

	<body>
		<!-- 左边菜单栏 -->
		<div id="sidebar">
			<ul>
				<li>
					<!-- class="active"-->
					<a href="<%=outerPath %>news_execute.action?sessionId=<%=sessionId %>" target="mainFrame"> <i
						class="icon icon-th-list"></i> <span>信息维护</span> </a>
				</li>
				<li>
					<!-- class="active"-->
					<a href="<%=outerPath %>calendar_list.action?sessionId=<%=sessionId %>" target="mainFrame"> <i
						class="icon icon-th-list"></i> <span>办税日历</span> </a>
				</li>
				<!-- 			
				<li>
					<a href="manage/tbcl.jsp" target="mainFrame"> <i
						class="icon icon-refresh"></i> <span>同步策略</span> </a>
				</li>
				 -->
				<li>
					<a href="manage/TxAction_allTx.action?type=txlxr&page.pageNo=1"
						target="mainFrame"> <i class="icon icon-random"></i> <span>条线管理</span>
					</a>
				</li>

				<li>
					<a href="manage/tsgl_execute.action" target="mainFrame"> <i
						class="icon icon-star"></i> <span>订阅统计</span> </a>
				</li>
				<li>
					<a href="manage/tsgl_tsdz.action" target="mainFrame"> <i
						class="icon icon-share"></i> <span>推送定制</span> </a>
				</li>

<!--  
				<li>
					<a href="manage/rzgl.jsp" target="mainFrame"> <i
						class="icon icon-calendar"></i> <span>日志管理</span> </a>
				</li>
				-->
				<li>
					<a href="manage/user_list.action" target="mainFrame"> <i
						class="icon icon-user"></i> <span>用户管理</span> </a>
				</li>
				<li>
					<a href="manage/rywh_txl.action" target="mainFrame"> <i
						class="icon icon-info-sign"></i> <span>人员维护</span> </a>
				</li>
				<li>
					<a href="manage/SystemPush_alllxr.action" target="mainFrame">
						<i class="icon icon-envelope"></i> <span>通知管理</span> </a>
				</li>
				<li>
					<a href="manage/report_execute.action" target="mainFrame">
						<i class="icon icon-envelope"></i> <span>税收统计报表</span> </a>
				</li>
				<li>
					<a href="manage/zgReport_execute.action" target="mainFrame">
						<i class="icon icon-envelope"></i> <span>征管报表管理</span> </a>
				</li>
				<li>
					<a href="manage/taxIncome_execute.action" target="mainFrame">
						<i class="icon icon-envelope"></i> <span>规财非税收入报表上传</span> </a>
				</li>
			</ul>
		</div>
	</body>
</html>
