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
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet"
	href="css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="css/unicorn.main.css" />
<link rel="stylesheet"
	href="css/zTreeStyle/zTreeStyle.css" />
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.ztree.all-3.5.min.js"></script>
<script src="js/ztree_rywh.js"></script>
<script type="text/javascript">
	var zNodes = ${txl};
	function updateSwrys(){
		var select=document.getElementById("zw");
		var index=select.selectedIndex;
		var zwDm=select.options[index].value;
		select=document.getElementById("rzjgs");
		index=select.selectedIndex;
		var rzjgDm=select.options[index].value;
		var ids=$("#goh").text();
		var url="manage/rywh_updateSwrys.action";
		$.post(url,{
			"zwDm":zwDm,
			"rzjgDm":rzjgDm,
			"ids":ids
		},function(data){
			if(data){
				alert("修改成功");
			}
			else{
				alert("修改失败");
			}
		},"json");
	}
	function updateSwry(){
		var select=document.getElementById("zw");
		var index=select.selectedIndex;
		var zwDm=select.options[index].value;
		select=document.getElementById("rzjgs");
		index=select.selectedIndex;
		var rzjgDm=select.options[index].value;
		var gh=$("#gh").val();
		var xm=$("#xm").val();
		var deviceid=$("#deviceid").val();
		var swryDm=$("#swryDm").val();
		var ele=$(".fivth button");
		var jhbj=ele.attr("name");	

		var url="manage/rywh_updateSwry.action"
		$.post(url,{
			"swry.zwDm":zwDm,
			"swry.rzjgDm":rzjgDm,
			"swry.swryDm":swryDm,
			"swry.xm":xm,
			"swry.gh":gh,
			"swry.deviceid":deviceid,
			"swry.jhbj":jhbj
		},function(data){
			if(data){
				alert("修改成功");
			}
			else{
				alert("修改失败");
			}
		},"json");
	}
	$("#jhbj button").click(function (){
		alert("23124234");
		var ele=$(this);
		var status=ele.attr("name");	
		if(status){
			ele.text("激活");
			ele.attr("name","1");
			
		}else{
			ele.text("锁定");
			ele.attr("name","0");
		}
		alert(ele.attr("name"));
	})
</script>
</head>
<body>
	<div id="content">
		<div id="content-main">
			<div id="content-header">
				<h1>人员维护</h1>
			</div>
			<div id="breadcrumb">
				<a href="<%=basePath%>manage/welcome.html" title="回到首页"
					class="tip-bottom"> <i class="icon-home"></i> 首页
				</a> <span class="current">人员维护</span>
			</div>
			<div class="container-fluid">
				<div class="widget-box">
					<div class="widget-title">
						<span class="icon"> <i class="icon-info-sign"></i>
						</span>
						<h5>人员维护</h5>
					</div>
					<div class="widget-content nopadding clearfix">
						<div class="left-tree span4">
							<div class="tz-tree">
								<ul id="tree" class="ztree"></ul>
							</div>
						</div>
						<div class="right-form span9">
							<div id="tabDetail" class="rr-right" style="display: none"></div>
							<div class="form-horizontal" id="form-horizontal">
								<div class="control-group">
									<label class="control-label" for="name"> 姓名 </label>
									<div class="controls">
										<div id="name"></div>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="gh"> 税务管理码 </label>
									<div class="controls">
										<div id="goh"></div>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="jg"> 职务 </label>
									<div class="controls">
										<s:select id="zw" class="span6" list="zws" listKey="zwDm"
											listValue="zw">
										</s:select>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="rzjg"> 任职机关 </label>
									<div class="controls">
										<s:select id="rzjgs" class="span6" list="rzjgs" name="rzjgDm"
											listKey="rzjgDm" listValue="rzjg">
										</s:select>
									</div>
								</div>
								<div class="submit pull-right">
									<input type="submit" value="提交" class="btn btn btn-primary"
										onclick="updateSwrys()" />
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
</body>
</html>