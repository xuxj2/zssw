<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>掌上税务后台</title>
		<link rel="stylesheet"
			href="<%=basePath%>manage/css/bootstrap.min.css" />
		<link rel="stylesheet"
			href="<%=basePath%>manage/css/bootstrap-responsive.min.css" />
		<link rel="stylesheet" href="<%=basePath%>manage/css/unicorn.main.css" />
		<script src="<%=basePath%>manage/js/jquery.min.js"></script>
		<script src="<%=basePath%>manage/js/bootstrap.min.js"></script>
		<script src="<%=basePath%>manage/js/bootstrap-datepicker.js"></script>
		<script src="<%=basePath%>manage/My97DatePicker/WdatePicker.js"></script>
	</head>
	<body>
		<div id="content">
			<div id="content-main">
				<div id="content-header">
					<h1>
						日志管理
					</h1>
				</div>
				<div id="breadcrumb">
					<a href="<%=basePath%>manage/welcome.html" title="回到首页" class="tip-bottom"> <i
						class="icon-home"></i> 首页 </a>
					<span class="current">日志管理</span>
				</div>
				<div class="container-fluid">
					<div class="widget-box">
						<div class="widget-title">
							<span class="icon"> <i class="icon-calendar"></i> </span>
							<h5>
								日志管理选项
							</h5>
						</div>
						<div class="widget-content">
							<form class="form-search search">
								<input class="Wdate" type="text" onClick="WdatePicker({dateFmt:'yyyy年MM月dd日 HH时mm分ss秒'})" placeholder="起始时间">
								<input class="Wdate" type="text" onClick="WdatePicker({dateFmt:'yyyy年MM月dd日 HH时mm分ss秒'})" placeholder="终止时间">
								<input type="text" class="input-large search-query"
									placeholder="请输入ID号">
								<button class="btn btn-primary btn-mini">
									<i class="icon-search icon-white"></i> 查询
								</button>
								<button class="btn btn-primary btn-mini">
									<i class="icon-download icon-white"></i> 导出
								</button>
							</form>
							<table class="table table-bordered table-hover">
								<thead>
									<tr>
										<th>
											登录ID
										</th>
										<th>
											登录时间
										</th>
										<th>
											功能
										</th>
										<th>
											操作类型
										</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>
											row1
										</td>
										<td>
											row2
										</td>
										<td>
											row3
										</td>
										<td>
											row4
										</td>
									</tr>
									<tr>
										<td>
											row1
										</td>
										<td>
											row2
										</td>
										<td>
											row3
										</td>
										<td>
											row4
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
