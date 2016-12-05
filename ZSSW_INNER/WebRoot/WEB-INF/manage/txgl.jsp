<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
		<link rel="stylesheet"
			href="css/bootstrap.min.css" />
		<link rel="stylesheet"
			href="css/bootstrap-responsive.min.css" />
		<link rel="stylesheet" href="css/unicorn.main.css" />
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script type="text/javascript">
		function selectTx(){
			var txmc=document.getElementById("txmc").value;
			var url="<%=basePath%>manage/TxAction_selectTx.action?page.pageNo=1&txmc=";
			window.location.href=url+txmc; 
		}
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
							<span class="icon"> <i class="icon-random"></i> </span>
							<div class="form-search search">
							<!--  
								<input type="text" class="input-large search-query" id="txmc" 
									placeholder="请输入条线名称">							
								<button id="select" class="btn btn-primary btn-mini"  onclick="selectTx()">
									<i class="icon-search icon-white"></i> 查询
								</button>
								-->
								<button id="add" class="btn btn-primary btn-mini"  onclick="window.location='<%=basePath%>manage/TxAction_allTxlxr.action?type=add'">
									<i class="icon-plus icon-white"></i> 新增
								</button>
							</div>
						</div>
						<s:iterator value="page.list"  status="status">
							<ul  class="site-stats" >
								<li >
									<a href="<%=basePath%>manage/TxAction_allTxlxr.action?type=txlxr&txid=${txDm}">${txMc}</a>
									<div class="pull-right">
										<button id="add" class="btn btn-primary btn-mini" onclick="window.location.href='<%=basePath%>manage/TxAction_delete.action?txid=${txDm}'" >
											<i class="icon-remove icon-white"></i> 删除
										</button>
									</div>
								</li>
							</ul>
						</s:iterator>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>