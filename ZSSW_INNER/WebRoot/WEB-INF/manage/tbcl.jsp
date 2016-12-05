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
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="<%=basePath%>manage/css/bootstrap.min.css" />
		<link rel="stylesheet" href="<%=basePath%>manage/css/bootstrap-responsive.min.css" />
		<link rel="stylesheet" href="<%=basePath%>manage/css/unicorn.main.css" />
		<script src="<%=basePath%>manage/js/jquery.min.js"></script>
		<script src="<%=basePath%>manage/js/bootstrap.min.js"></script>
	</head>
	<body>
		<div id="content">
			<div id="content-main">
				<div id="content-header">
					<h1>
						同步策略
					</h1>
				</div>
				<div id="breadcrumb">
					<a href="<%=basePath%>manage/welcome.html" title="回到首页" class="tip-bottom"> <i
						class="icon-home"></i> 首页 </a>
					<span class="current">同步策略</span>
				</div>
				<div class="container-fluid">
					<div class="widget-box">
						<div class="widget-title">
							<span class="icon"> <i class="icon-refresh"></i> </span>
							<h5>
								同步策略栏目列表
							</h5>
						</div>
						<div class="widget-content nopadding">
							<table class="table table_tb table-bordered table-hover">
								<thead>
									<tr>
										<th>
											栏目
										</th>
										<th>
											同步策略
										</th>
										<th>
											修改策略
										</th>
										<th>
											上次发生时间
										</th>
										<th>
											手动同步
										</th>
									</tr>
								</thead>
								<tbody class="tb-btn">
									<tr>
										<td class="first">
											涉税通告
										</td>
										<td class="second">
											每天凌晨2点
										</td>
										<td class="third">
											<button class="btn btn-primary btn-mini">
												<i class="icon-pencil icon-white"></i> 编辑修改
											</button>
										</td>
										<td class="forth">
											2013年12月15日 15:30:18
										</td>
										<td class="fivth">
											<button type="button" class="btn btn-primary btn-mini"
												data-dismiss="modal" class="close">
												<i class="icon-hand-up icon-white" data-toggle="modal"></i>
												手动同步
											</button>
										</td>
									</tr>
									<tr>
										<td class="first">
											12366知识库
										</td>
										<td class="second">
											每天凌晨2点
										</td>
										<td class="third">
											<button class="btn btn-primary btn-mini">
												<i class="icon-pencil icon-white"></i> 编辑修改
											</button>
										</td>
										<td class="forth">
											2013年12月15日 15:30:18
										</td>
										<td class="fivth">
											<button class="btn btn-primary btn-mini">
												<i class="icon-hand-up icon-white" data-toggle="modal"></i>
												手动同步
											</button>
										</td>
									</tr>
									<tr>
										<td class="first">
											总局法规库
										</td>
										<td class="second">
											每天凌晨2点
										</td>
										<td class="third">
											<button class="btn btn-primary btn-mini">
												<i class="icon-pencil icon-white"></i> 编辑修改
											</button>
										</td>
										<td class="forth">
											2013年12月15日 15:30:18
										</td>
										<td class="fivth">
											<button class="btn btn-primary btn-mini">
												<i class="icon-hand-up icon-white" data-toggle="modal"></i>
												手动同步
											</button>
										</td>
									</tr>
									<tr>
										<td class="first">
											手机报
										</td>
										<td class="second">
											每天凌晨2点
										</td>
										<td class="third">
											<button class="btn btn-primary btn-mini">
												<i class="icon-pencil icon-white"></i> 编辑修改
											</button>
										</td>
										<td class="forth">
											2013年12月15日 15:30:18
										</td>
										<td class="fivth">
											<button class="btn btn-primary btn-mini">
												<i class="icon-hand-up icon-white" data-toggle="modal"></i>
												手动同步
											</button>
										</td>
									</tr>
									<tr>
										<td class="first">
											地税新闻
										</td>
										<td class="second">
											每天凌晨2点
										</td>
										<td class="third">
											<button class="btn btn-primary btn-mini">
												<i class="icon-pencil icon-white"></i> 编辑修改
											</button>
										</td>
										<td class="forth">
											2013年12月15日 15:30:18
										</td>
										<td class="fivth">
											<button class="btn btn-primary btn-mini">
												<i class="icon-hand-up icon-white" data-toggle="modal"></i>
												手动同步
											</button>
										</td>
									</tr>
									<tr>
										<td class="first">
											全省动态
										</td>
										<td class="second">
											每天凌晨2点
										</td>
										<td class="third">
											<button class="btn btn-primary btn-mini">
												<i class="icon-pencil icon-white"></i> 编辑修改
											</button>
										</td>
										<td class="forth">
											2013年12月15日 15:30:18
										</td>
										<td class="fivth">
											<!-- <button class="btn btn-primary btn-mini" data-toggle="modal" data-target="#myModal" data-backdrop="static">
										-->
											<button class="btn btn-primary btn-mini" data-toggle="modal">
												<i class="icon-hand-up icon-white"></i> 手动同步
											</button>
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