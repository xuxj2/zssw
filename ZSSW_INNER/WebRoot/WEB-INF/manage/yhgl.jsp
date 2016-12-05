<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		<link rel="stylesheet" href="css/bootstrap.min.css" />
		<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
		<link rel="stylesheet" href="css/unicorn.main.css" />
		<link rel="stylesheet" href="css/zTreeStyle/zTreeStyle.css" />
		<script src="js/jquery.min.js"></script>
		<script src="js/icheck.min.js"></script>
		<script src="js/jquery.ztree.all-3.5.min.js"></script>
		<script src="js/ztree_yhgl.js"></script>
		<script src="js/bootstrap.min.js"></script>

		<script type="text/javascript">
		
/**
 * 用户管理
 */
var ids;
var setting = {
	check : {
		enable : true
	},
	view : {
		dblClickExpand : true
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	callback : {		
		onCheck : onCheck
	}
};



function onCheck(e, treeId, treeNode) {
	var checked=treeNode.checked;	
	if(treeNode.isParent){
		for(var i=0;i<treeNode.children.length;i++){		
			onCheck(e, treeId, treeNode.children[i]);			
		}
	}else{
		if(checked){
			var row=$("<tr id=row"+treeNode.id+"><td>"+treeNode.name+"</td><td>"+treeNode.id+"</td><td>"+treeNode.getParentNode().name+"</td></tr>");
			$("#add_selector tbody").append(row);
		}else{
			var row=$("#row"+treeNode.id);
			row.remove();
		}
	}
}
		
		
		
		
	$(document).ready(function() {
		$(".widget-title ul li").click(function(){
			if(!$(this).hasClass("active")){
				$("#add_selector").css("display","none");
			}		
		});
	
		/*
		 *点击删除，移除人员超级管理员权限
		 */
		$("#tab1 .yh-third button").click(function() {
			var text = $(this).parent().prev().prev().text().trim();
			var suc = removePer(text, 4);
			if (suc) {
				$(this).parent().parent().remove();
			}
		});

		/*
		 *点击删除，移除人员管理员权限
		 */
		$("#tab2 .yh-third button").click(function() {
			var text = $(this).parent().prev().prev().text().trim();
			var suc = removePer(text, 2);
			if (suc) {
				$(this).parent().parent().remove();
			}
		});

		/*
		 *点击增加按钮
		 */
		 
		$("#tab1 .row button:eq(1)").click(function() {	
			$("#add_selector tbody").empty();
			$("#add_perm_value").val(4);		
			loadSelectable(4);
			$("#add_selector").css("display","block");		
		});
		$("#tab2 .row button:eq(1)").click(function() {	
			$("#add_selector tbody").empty();	
			$("#add_perm_value").val(2);			
			loadSelectable(2);
			$("#add_selector").css("display","block");				
		});
/*
 * 确定增加
 */
 $("#confirm_add").click(function(){
 	var trs=$("#add_selector tbody tr");
 	var perm=$("#add_perm_value").val();
 	var ids="";
 	if(trs.length>0){
 		for(var k=0;k<trs.length;k++){ 			
 		ids=ids+trs.eq(k).attr("id").substr(3)+","; 	
 	} 
 	window.location.href="manage/user_add.action?permission="+perm+"&swryGhs="+ids; 	
 	}
 	 
 });


		function removePer(user, perm) {
			var suc = false;
			var uri = "manage/user_remove.action";
			$.ajax({
				type : "POST",
				url : uri,
				async : false,
				data : "swryGh=" + user + "&permission=" + perm,
				dataType : "json",
				success : function(data) {
					suc = data;
				}
			});
			return suc;
		}

		function loadSelectable(perm) {
			var uri = "manage/user_selectable.action";
			$.post(uri, {
				"permission" : perm
			}, function(data) {
				$.fn.zTree.init($("#tree"), setting, data);			
			}, "json");
		}
	});
</script>
	</head>
	<body>
		<div id="content">
			<div id="content-main">
				<div id="content-header">
					<h1>
						用户管理
					</h1>
				</div>
				<div id="breadcrumb">
					<a href="<%=basePath%>manage/welcome.html" title="回到首页"
						class="tip-bottom"> <i class="icon-home"></i> 首页 </a>
					<span class="current">用户管理</span>
				</div>
				<div class="container-fluid">
					<div class="widget-box">  
						<div class="widget-title">
							<ul class="nav nav-tabs">
								<li class="active">
									<a data-toggle="tab" href="#tab1">系统管理员</a>
								</li>
								<li>
									<a data-toggle="tab" href="#tab2">用户</a>
								</li>
							</ul>
						</div>
						<div class="widget-content tab-content">
							<div id="tab1" class="tab-pane active">
								<div class="row">
									<div class="form-search search">
										<input type="text" class="input-large search-query"
											placeholder="请输入系统管理员登录名">
										<button class="btn btn-primary btn-mini" disabled="disabled">
											<i class="icon-search icon-white"></i> 查询
										</button>
										<button class="btn btn-primary btn-mini">
											<i class="icon-plus icon-white"></i> 新增
										</button>
									</div>
								</div>
								<table class="table table-bordered table-hover">
									<thead>
										<tr>
											<th>
												序号
											</th>
											<th>
												姓名
											</th>
											<th>
												工号
											</th>
											<th>
												任职机关
											</th>
											<th>
											</th>
										</tr>
									</thead>
									<tbody>
										<s:iterator value="#request.owners" status="status">
											<tr>
												<td>
													${status.count}
												</td>
												<td>
													<s:property value="xm" />
												</td>
												<td>
													<s:property value="swryGh" />
												</td>
												<td>
													<s:property value="rzjgMc" />
													<p style="display: none">
														<s:property value="rzjgDm" />
													</p>
												</td>
												<td class="yh-third">
													<button class="btn btn-primary btn-mini">
														<i class="icon-pencil icon-white"></i> 删除
													</button>
												</td>
											</tr>

										</s:iterator>
									</tbody>
								</table>
							</div>
							<div id="tab2" class="tab-pane">
								<div class="row">
									<div class="form-search search">
										<input type="text" class="input-large search-query"
											placeholder="请输入用户登录名">
										<button class="btn btn-primary btn-mini" disabled="disabled">
											<i class="icon-search icon-white"></i> 查询
										</button>
										<button class="btn btn-primary btn-mini">
											<i class="icon-plus icon-white"></i> 新增
										</button>
									</div>
								</div>
								<table class="table table-bordered">
									<thead>
										<tr>
											<th>
												序号
											</th>
											<th>
												姓名
											</th>
											<th>
												工号
											</th>
											<th>
												任职机关
											</th>
											<th>
											</th>
										</tr>
									</thead>
									<tbody>
										<s:iterator value="#request.managers" status="status">
											<tr>
												<td>
													${status.count}
												</td>
												<td>
													<s:property value="xm" />
												</td>
												<td>
													<s:property value="swryGh" />
												</td>
												<td>
													<s:property value="rzjgMc" />
													<p style="display: none">
														<s:property value="rzjgDm" />
													</p>
												</td>
												<td class="yh-third">
													<button class="btn btn-primary btn-mini">
														<i class="icon-pencil icon-white"></i> 删除
													</button>
												</td>
											</tr>

										</s:iterator>
									</tbody>
								</table>
							</div>
						</div>

						<div id="add_selector" 	style="width: 95%; height: 400px; margin: 20px auto 10px;display:none">
							<input id="add_perm_value" type="hidden" value="2"/>
							<div class="ts-tree" style="float: left; width: 300px;">
								<button class="btn btn-primary btn-mini" id="confirm_add">
									<i class="icon-check icon-white"></i>确定
								</button>
								<div style="height: 380px;overflow: auto">
									<ul id="tree" class="ztree"></ul>
								</div>								
							</div>
							<div style="float: left; margin-left: 5px;height: 376px;overflow: auto ">
								<table class="table table-bordered">
									<thead>
										<tr>
											<th style="width: 6em">
												姓名
											</th>
											<th style="width: 14em">
												工号
											</th>
											<th style="width: 26em">
												任职机关
											</th>
										</tr>
									</thead>
									<tbody >
									</tbody>
								</table>
							</div>

						</div>

					</div>
				</div>
			</div>
		</div>
	</body>
</html>
