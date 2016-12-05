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
	<base href="<%=basePath%>"> 
		<title>掌上税务后台</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet"  href="css/bootstrap.min.css" />
		<link rel="css/bootstrap.min.css" type="text/css"></link>
		<link rel="stylesheet"
			href="css/bootstrap-responsive.min.css" />
		<link rel="stylesheet" href="css/unicorn.main.css" />
		 <link rel="stylesheet" href="css/zTreeStyle/zTreeStyle.css"/>
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/bootstrap-wysiwyg.js"></script>
		<script src="js/jquery.hotkeys.js"></script>
		<script src="js/jquery.ztree.all-3.5.min.js"></script>
		<script src="js/ztree_tzgl.js"></script>
		<script type="text/javascript">
		var zNodes=${txl};
		</script>
	</head>
	<body>
		<div id="content">
			<div id="content-main">
				<div id="content-header">
					<h1>
						通知管理
					</h1>
				</div>
				<div id="breadcrumb">
					<a href="<%=basePath%>manage/welcome.html" title="回到首页" class="tip-bottom"> <i
						class="icon-home"></i> 首页 </a>
					<span class="current">通知管理</span>
				</div>
				<div class="container-fluid">
					<div class="widget-box">
						<div class="widget-title">
							<span class="icon"> <i class="icon-envelope"></i> </span>
							<h5>
								通知管理选项
							</h5>
						</div>
						<div class="widget-content nopadding">
							<form class="row-fluid" action="SystemPush_push.action" enctype="multipart/form-data" method="post">
								<!-- 左边 选项 -->
								<div class="span8">
									<div class="form-horizontal" >
										<div class="control-group">
											<label class="control-label" for="lm">
												文件解析发送
											</label>
											<div class="controls">
												<input type="checkbox" id="lm"  name="jmfs"  >
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="zw">
												正文
											</label>
											<div class="controls">
												<textarea rows="8" id="zw" name="zw"></textarea>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="file">
												添加文件
											</label>
											<div class="controls">
												<input type="file" id="file" name="file"/>
											</div>
										</div>
										<div class="pull-right">
											<input type="submit" value="发送" class="btn btn-primary" />
										</div>
									</div>
								</div>
								<!-- 右边选择 树结构 -->
								<div class="span3">
									<label >选择接收人</label>
									<div class="tz-tree">
										<ul id="tree" class="ztree"></ul>
									</div>
									<input id="ids" name="ids" value=""  type="hidden">
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>