<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<base href="<%=basePath%>">
		<title>掌上税务后台</title>
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
						信息维护
					</h1>
				</div>
				<div id="breadcrumb">
					<a href="<%=basePath%>manage/welcome.html" title="回到首页" class="tip-bottom"> <i
						class="icon-home"></i> 首页 </a>
					<span class="current">信息维护</span>
				</div>
				<div class="container-fluid">
					<div class="widget-box">
						<div class="widget-title">
							<span class="icon"> <i class="icon-pencil"></i> </span>
							<h5>
								编辑修改
							</h5>
						</div>
						<div class="widget-content clearfix">
							<form class="form-horizontal">
								<div class="control-group">
									<label class="control-label" for="lm">
										所属栏目
									</label>
									<div class="controls">
										<input type="text" id="lm" value="涉税通知">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="bt">
										标题
									</label>
									<div class="controls">
										<input type="text" id="bt" value="">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="ny">
										内容
									</label>
									<div class="controls">
										<textarea rows="8" id="ny">abc</textarea>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="xgr">
										修改人
									</label>
									<div class="controls">
										<input type="text" id="xgr" value="">
									</div>
								</div>
								<div class="pull-right">
									<input type="submit" value="提交" class="btn btn btn-primary" />
									<input type="button" value="取消" class="btn btn btn-primary" />
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
