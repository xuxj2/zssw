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
		<div class="widget-content nopadding clearfix">
			<dl class="dl-horizontal">
				<dt>
					姓名
				</dt>
				<dd>
					黄广杰
				</dd>
				<dt>
					性别
				</dt>
				<dd>
					<select>
						<option>
							男
						</option>
						<option>
							女
						</option>
					</select>
				</dd>
				<dt>
					工号
				</dt>
				<dd>
					<input type="text" placeholder="工号">
				</dd>
				<dt>
					任职机关
				</dt>
				<dd>
					<select>
						<option>
							任职机关
						</option>
						<option>
							任职机关
						</option>
					</select>
				</dd>
				<dt>
					职务
				</dt>
				<dd>
					<select>
						<option>
							职务
						</option>
						<option>
							职务
						</option>
					</select>
				</dd>
			</dl>
		</div>
	</body>
</html>