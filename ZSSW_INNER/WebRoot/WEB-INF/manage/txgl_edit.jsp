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
		 <link rel="stylesheet" href="<%=basePath%>manage/js/zTreeStyle/zTreeStyle.css"/>
		<script src="<%=basePath%>manage/js/jquery.min.js"></script>
		<script src="<%=basePath%>manage/js/bootstrap.min.js"></script>
		<script src="<%=basePath%>manage/js/jquery.ztree.all-3.5.min.js"></script>
		<script src="<%=basePath%>manage/js/ztree_txedit.js"></script>
		<script type="text/javascript">
		var zNodes=${txlxr};
		</script>


</head>
<body>
	<div id="content">
		<div id="content-main">
			<div id="content-header">
				<h1>条线管理</h1>
			</div>
			<div id="breadcrumb">
				<a href="<%=basePath%>manage/welcome.html" title="回到首页"
					class="tip-bottom"> <i class="icon-home"></i> 首页
				</a> <span class="current">条线管理</span>
			</div>
			<div class="container-fluid">
				<div class="widget-box">
					<div class="widget-title">
						<span class="icon"> <i class="icon-pencil"></i>
						</span>
						<h5>编辑条线</h5>
					</div>
					<div class="widget-content nopadding">
						<div class="widget-content clearfix">
							<form class="form-horizontal"  action="TxAction_update.action" method="post">
								<div class="control-group">
									<label class="control-label" for="lm"> 条线名称 </label>
									<div class="controls">
										<input type="text" id="lm" name="txmc" value="${txmc}">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="ry"> 人员 </label>
									<div class="controls">
											<div class="tz-tree" >
												<ul id="txtree" class="ztree"></ul>
										</div>
									</div>
								</div>
									<input id="txid" name="txid" value="${txid}" type="hidden"/>
									<input id="ids" name="ids" value=""  type="hidden"/>
								<div class="pull-right">
									<input type="submit" value="提交" class="btn btn btn-primary" />
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>