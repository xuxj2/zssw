<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>zgbb_upload</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script src="<%=basePath%>js/jquery.min.js"></script>
		<script type="text/javascript">	
		$(document).ready(function(){
			/*
			*查询按钮事件
			*/
				
			$("#queryb").click(function(){				
				var reportId=$("#queryrp").val();
				var year=$("#queryy").val();
				var month=$("#querym").val();				
				loadReport(reportId, year, month);
			});		
			
			/*
			 *删除按钮事件
			 */
			$("#delbut").click(function(){				
				var reportId=$("#queryrp").val();
				var year=$("#queryy").val();
				var month=$("#querym").val();				
				deleteReport(reportId, year, month);
			});	
			
		});
			

/*
 *加载报表
 */
	function loadReport(reportId, year, month) {
		var uri = "manage/zgReport_view.action";
		$.post(uri, {
			"reportId" : reportId,
			"year" : year,
			"month" : month
		}, function(data) {
			$("#report_content").html(data);
		}, "text");
	}
	
	
/*
 *删除报表
 */
	function deleteReport(reportId, year, month) {
		var uri = "manage/zgReport_delete.action";
		$.post(uri, {
			"reportId" : reportId,
			"year" : year,
			"month" : month,
		}, function(data) {		
		if("1"==data){
			$("#report_content").html("<p>"+"删除成功"+"</p>");
		}else if("0"==data){
			$("#report_content").html("<p>"+"系统没有这写数据"+"</p>");
		}else{
			$("#report_content").html("<p>"+"删除出错"+"</p>");
		}			
		}, "json");
	}
	
		</script>
	</head>

	<body>
		<div align="center">
			<h3>
				征管报表管理
			</h3>
		</div>


		<div align="center">
			<div align="left">
				<form action="manage/zgReport_upload.action"
					enctype="multipart/form-data" method="post">
					<u><label> 
						上传： 
					</label><input type="file" name="uploads"><input type="file" name="uploads" style="margin-left: 14px;"><input type="submit" title="上传" value="上传"></u>
					
					
					
				</form>
			</div>
			<div align="left">
				<div>
					<label>
						报表查询：
					</label>
					<select name="reportId" id="queryrp">
						<option value="B04001">
							登记户数统计
						</option>
						<option value="B04002">
							登记户数变动统计
						</option>
						<option value="B04003">
							准期申报率
						</option>
						<option value="B04004">
							准期入库率
						</option>
						<option value="B04005">
							欠税增减率
						</option>
						<option value="B04007">
							任务准期完成率
						</option>
						<option value="B04006">
							逾期申报处罚率
						</option>
						<!--  
						<option value="B04008">
							未按期申报处理率
						</option>-->
					</select>
					<select name="year" id="queryy">
						<option value="2013">
							2013
						</option>
						<option value="2014">
							2014
						</option>
						<option value="2015">
							2015
						</option>
					</select>
					<select name="month" id="querym">
						<option value="1">
							1
						</option>
						<option value="2">
							2
						</option>
						<option value="3">
							3
						</option>
						<option value="4">
							4
						</option>
						<option value="5">
							5
						</option>
						<option value="6">
							6
						</option>
						<option value="7">
							7
						</option>
						<option value="8">
							8
						</option>
						<option value="9">
							9
						</option>
						<option value="10">
							10
						</option>
						<option value="11">
							11
						</option>
						<option value="12">
							12
						</option>
					</select>
					<input id="queryb" type="button" title="查询" value="查询" />
					<input id="delbut" type="button" title="删除" value="删除报表" />
				</div>
			</div>

			<div id="report_content" align="center">

			</div>
		</div>
	</body>
</html>
