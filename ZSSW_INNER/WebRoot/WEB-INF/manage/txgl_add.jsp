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
		<link rel="stylesheet" href="css/bootstrap.min.css" />
		<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
		<link rel="stylesheet" href="css/unicorn.main.css" />
		<link rel="stylesheet" href="css/zTreeStyle/zTreeStyle.css"/>
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/jquery.ztree.all-3.5.min.js"></script>
		<script src="js/ztree_txedit.js"></script>	
		<script type="text/javascript">
		var zNodes=${txlxr};
		</script>
	</head>
	<body>
		<div id="content">
			<div id="content-main">
				<div id="content-header">
					<h1>
						条线管理
					</h1>
				</div>
				<div id="breadcrumb">
					<a href="manage/manage_main.action" title="回到首页" class="tip-bottom"> <i
						class="icon-home"></i> 首页 </a>
					<span class="current">条线管理</span>
				</div>
				<div class="container-fluid">
					<div class="widget-box">
						<div class="widget-title">
							<span class="icon"> <i class="icon-plus"></i> </span>
							<h5>
								添加条线
							</h5>
						</div>
						<div class="widget-content nopadding">
							<div class="widget-content clearfix">
								<form class="form-horizontal" action="TxAction_add.action" method="post">
									<div class="control-group">
										<label class="control-label" for="lm">
											条线名称
										</label>
										<div class="controls">
											<input type="text" id="lm" name="txmc"  value="">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="ry">
											人员
										</label>
									<div class="controls">
										<div id="ry" class="tx_ed">
											<div class="tz-tree">
												<ul id="txtree" class="ztree"></ul>
											</div>
										</div>
									</div>
								</div>
								<input id="ids" name="ids" value=""  type="hidden" />
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