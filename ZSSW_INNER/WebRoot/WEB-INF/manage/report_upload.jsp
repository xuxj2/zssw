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
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<form action="manage/report_upload.action" method="post"
			enctype="multipart/form-data" title="上传报表">
			<b>选择所属期</b>
			<select name="ssq">
				<option value="201401">
					201401
				</option>
				<option value="201402">
					201402
				</option>
				<option value="201403">
					201403
				</option>
				<option value="201404" selected="selected">
					201404
				</option>
				<option value="201405">
					201405
				</option>
				<option value="201406">
					201406
				</option>
				<option value="201407">
					201407
				</option>
				<option value="201408">
					201408
				</option>
				<option value="201409">
					201409
				</option>
				<option value="201410">
					201410
				</option>
				<option value="201411">
					201411
				</option>
				<option value="201412">
					201412
				</option>
			</select>
			<br />
			<input type="file" name="file" />
			<input type="submit" name="submit" value="上传" />
		</form>

		<table border="1px" cellpadding="2px" cellspacing="0px" align="center">
			<thead align="center" valign="middle"
				style="font-size: 18px; font-weight: bold">
				<tr>
					<th colspan="2">
						报表查看
					</th>
				</tr>
				<tr>
					<td style="width: 4em">
						序号
					</td>
					<td style="width: 11em">
						所属期
					</td>
				</tr>
			</thead>			
			<tbody style="text-align: center">
				<s:iterator value="sssrTitleList" status="staus" var="ssq">
					<tr>
						<td style="text-align: right">
							${staus.count}
						</td>
						<td>
							<a href="manage/report_query.action?ssq=${ssq}">${ssq}</a>
						</td>
					</tr>
				</s:iterator>
			</tbody>		
		</table>
		
	</body>
</html>