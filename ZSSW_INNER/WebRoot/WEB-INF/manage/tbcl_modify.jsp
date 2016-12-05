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
		<link rel="stylesheet"
			href="<%=basePath%>manage/css/bootstrap.min.css" />
		<link rel="stylesheet"
			href="<%=basePath%>manage/css/bootstrap-responsive.min.css" />
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
					<a href="index.html" title="回到首页" class="tip-bottom"> <i
						class="icon-home"></i> 首页 </a>
					<a href="tbcl.html" class="current">同步策略</a>
				</div>
				<div class="container-fluid">
					<div class="widget-box">
						<div class="widget-title">
							<h5>
								修改
							</h5>
						</div>
						<div class="widget-content clearfix">
							<form class="form-horizontal">
								<table class="table">
									<tr>
										<td>
											栏目
										</td>
										<td>
											办税须知-企业纳税人-信用等级
										</td>
									</tr>
									<tr>
										<td>
											发生频率
										</td>
										<td>
											<ul class="tb_md">
												<li>
													<label class="radio">
														<input type="radio" name="md_time" value="">
														每天
													</label>
												</li>
												<li>
													<label class="radio">
														<input type="radio" name="md_time" value="">
														每周：星期
														<select>
															<option>一</option>
														</select>
													</label>
												</li>
												<li>
													<label class="radio">
														<input type="radio" name="md_time" value="">
														每月：
														<select>
															<option>1</option>
														</select>
														号
													</label>
												</li>
											</ul>
										</td>
									</tr>
									<tr>
										<td>
											日频率
										</td>
										<td>
											当天
											<select>
												<option>0</option>
											</select>
											点执行同步任务
										</td>
									</tr>
									<tr>
										<td colspan="3">
											<input type="submit" value="提交" class="btn btn btn-primary" />
											<input type="button" value="取消" class="btn btn btn-primary" />
										</td>
									</tr>
								</table>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>