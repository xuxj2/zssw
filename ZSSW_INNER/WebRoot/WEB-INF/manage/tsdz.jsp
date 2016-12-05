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
<link rel="stylesheet" href="css/skins/all.css" />
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/icheck.min.js"></script>
<link rel="stylesheet" href="css/zTreeStyle/zTreeStyle.css" />
<script src="js/jquery.ztree.all-3.5.min.js"></script>
<script src="js/ztree_tsdz.js"></script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						//点击选项卡，切换到不同栏目订阅人员的信息
						$('#tabs a').click(function() {

							var isActive = $(this).parent().hasClass('active');
							if (!isActive) {
								var href = $(this).attr("href");
								var columnId = $(this).attr("id");
							
								loadList(href, columnId);
							}
						});
						//页面初始化后加载涉税通告栏目订阅人员信息
						window.onload = loadList("#tab1", "hswz000");
						//加载指定栏目订阅人员信息
						function loadList(eleId, columnId) {
							var cityObj = $("#lmid");
							cityObj.attr("value", columnId);
							var uri = "manage/tsgl_selectRy.action";
							$.post(uri, {
								"lmid" : columnId
							}, function(data) {
								var zNodes = data;
								$.fn.zTree.init($("#tree"), setting, zNodes);
							}, "json");
						}

					});
	function update()
	{
		var url="manage/tsgl_updateRy.action";
		var lmid=$("#lmid").attr("value");
		var ryids=$("#ids").attr("value");
		$.post(url,{
			"lmid":lmid,
			"ids":ryids
		},function(data){
			if(data){
				alert("修改成功");
			}
			else{
				alert("修改失败");
			}
			
		},"json");
	}
</script>
</head>
<body>
	<div id="content">
		<div id="breadcrumb">
			<a href="<%=basePath%>manage/welcome.html" title="回到首页"
				class="tip-bottom"> <i class="icon-home"></i> 首页
			</a> <span class="current">推送定制</span>
		</div>
		<div class="container-fluid">
			<div class="widget-box">
				<div class="widget-title">
					<!-- 标签卡 -->
					<ul class="nav nav-tabs" id="tabs">
						<li class="active"><a data-toggle="tab" id="hswz000"
							href="#tab1">寒山闻钟</a></li>
						<li><a data-toggle="tab" id="jzxx000" href="#tab1">局长信箱</a></li>
						<li><a data-toggle="tab" id="zgbb000" href="#tab1">征管报表</a></li>
						<li><a data-toggle="tab" id="gcbb000" href="#tab1">税收统计</a></li>
					</ul>
				</div>
				<div class="widget-content">
					<div class="ts-tree">
						<ul id="tree" class="ztree"></ul> 
					</div>
				</div>
					<input id="ids" name="ids" value="'" type="hidden" />
					 <input id="lmid" name="lmid" value="" type="hidden" />
					<div class="pull-right">
						<input  type="submit" value="提交" class="btn btn-primary"  onclick="update()"/>
					</div>
			</div>
		</div>
	</div>

</body>
</html>
