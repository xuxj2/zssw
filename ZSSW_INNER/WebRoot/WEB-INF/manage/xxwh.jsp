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
		<link rel="stylesheet" href="<%=basePath%>manage/css/bootstrap.min.css" />
		<link rel="stylesheet" href="<%=basePath%>manage/css/bootstrap-responsive.min.css" />
		<link rel="stylesheet" href="<%=basePath%>manage/css/unicorn.main.css" />
		<script src="<%=basePath%>manage/js/jquery.min.js"></script>
		<script src="<%=basePath%>manage/js/bootstrap.min.js"></script>
		<script type="text/javascript">
		$(document).ready(function() {
			//点击选项卡，切换到不同栏目的信息
	 			$('#tabs a').click(function() {
							var isActive = $(this).parent().hasClass('active');														
							if (!isActive) {
								var href = $(this).attr("href");
								var columnId = $(this).attr("id");
								loadList(href, columnId, 1);
							}
						});
						
			//点击 “编辑修改” 展开某条信息的具体内容
				$(".third button").live("click",function() {
					var uri = "manage/news_detail.action";
					var id = $(this).parent().parent().attr("id");
					var cid=$("#columnId").val();					
					$.post(uri,{"columnId" : cid,"news.id" : id},function(data){
														$("#tabDetail").html(data);														
														$("#tab1").css("display","none");
														$("#tabs li:not(.active)").css("display","none");																																										
														$("#tabDetail").css("display","block");
														}, "text");
	 			});
	 		/*
	 		 *点击“提交修改”,成功后返回列表页，隐藏详情页
	 		 */	
	 			$("#upsub").live("click",function(){
	 				var modUri=	"manage/news_modify.action";
					var newsColumnId=$("#deform1 input:eq(0)").val();					
	 				var newsId= $("#deform1 input:eq(1)").val();
	 				var newsTitle=$("#deform1 input:eq(2)").val();
	 				var newsSourceurl=$("#deform1 input:eq(3)").val();
	 				var newsTime=$("#deform1 input:eq(4)").val();	 				
	 				var newsChannel=$("#deform1 input:eq(5)").val();
	 				var newsIssueNumber=$("#deform1 input:eq(6)").val();	 				
	 				var newsEffective=$("#deform1 [name='news.effective']:checked").val();	 				
	 				var newsVisibility=$("#deform1 [name='news.visibility']:checked").val();	 				
	 				var newsTextContent=$("#deform1 textarea:eq(0)").val();
	 				var newsFileurl=$("#deform1 textarea:eq(1)").val();
	 				var newsImageurl=$("#deform1 textarea:eq(2)").val();
	 				$.post(modUri,
	 					   {
	 						"news.columnId" :newsColumnId,
	 						"news.id" :newsId,
	 						"news.title":newsTitle,
	 						"news.sourceurl":newsSourceurl,
	 						"news.time":newsTime,
	 						"news.channel":newsChannel,
	 						"news.issueNumber":newsIssueNumber,
	 						"news.effective":newsEffective,
	 						"news.visibility":newsVisibility,
	 						"news.textContent":newsTextContent,
	 						"news.fileurl":newsFileurl,
	 						"news.imageurl":newsImageurl
	 					   },
	 					   function(data){
										if(data){
											$("#tabDetail").css("display","none");
											$("#tab1").css("display","block");
											$("#tabs li").css("display","block");
										}else{
											alert("修改不成功");											
										}
									}, 
							"json");
	 			});
	 			
	 			//修改页返回
	 			$("#upretn").live("click",function(){
	 				$("#tabDetail").css("display","none");
	 				$("#tab1").css("display","block");
	 				$("#tabs li").css("display","block");	 			
	 			});

		});
	//页面初始化后加载涉税通告栏目第一页信息
	window.onload = loadList("#tab1", "dsxw300", 1);
	
	//加载指定栏目的指定页数内容
	function loadList(eleId, columnId, page) {
		var uri = "manage/news_list.action";
		$.post(uri, {
			"columnId" : columnId,
			"page.pageNo" : page
		}, function(data) {
			$(eleId).html(data);
		}, "text");
	}
</script>
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
							<!-- 标签卡 -->
							<ul class="nav nav-tabs" id="tabs">
								<li class="active">
									<a data-toggle="tab" id="dsxw300" href="#tab1">涉税通告</a>
								</li>
								<li>
									<a data-toggle="tab" id="dsxw400" href="#tab1">手机报</a>
								</li>
								<li>
									<a data-toggle="tab" id="dsxw100" href="#tab1">全省动态</a>
								</li>
								<li>
									<a data-toggle="tab" id="dsxw200" href="#tab1">地税新闻</a>
								</li>
								<li>
									<a data-toggle="tab" id="zsk1000" href="#tab1">12366知识库</a>
								</li>
								<li>
									<a data-toggle="tab" id="fgk2000" href="#tab1">总局法规库</a>
								</li>
							</ul>
						</div>
						<div class="tab-content">
							<div id="tab1" class="tab-pane active">
								<img alt="waiting for onload"
									src="<%=basePath%>/manage/loading.gif" align="middle"
									title="loading">
							</div>
							<div id="tabDetail" style="display: none">

							</div>							
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
