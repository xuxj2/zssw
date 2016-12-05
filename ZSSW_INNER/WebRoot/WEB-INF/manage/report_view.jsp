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
		<title>report_view.jsp</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	</head>

	<body>
	<a style="margin-top: 50px" href="manage/report_execute.action">返回列表</a>
	
		<table border="1px"  cellpadding="2px" cellspacing="0px" align="center">
			<thead align="center" valign="middle" style="font-size: 18px;font-weight: bold">
				<tr>
					<th colspan="8"><s:property value="ssq.substring(0,4)"/>年<s:property value="ssq.substring(4,6)"/>月税收收入预测数</th>
				</tr>
				<tr>
					<td style="width:4em">项目</td>
					<td style="width:6em">当月预计<br/><font style="font-size: 12px;font-weight: normal;">（单位：万元）</font></td>
					<td style="width:6em">上年同期<br/><font style="font-size: 12px;font-weight: normal;">（单位：万元）</font></td>
					<td style="width:6em">同比<br/><font style="font-size: 12px;font-weight: normal;">（单位：百分比）</font></td>
					<td style="width:6em">上月<br/><font style="font-size: 12px;font-weight: normal;">（单位：万元）</font></td>
					<td style="width:6em">本年累计<br/><font style="font-size: 12px;font-weight: normal;">（单位：万元）</font></td>
					<td style="width:6em">上年累计<br/><font style="font-size: 12px;font-weight: normal;">（单位：万元）</font></td>
					<td style="width:6em">累计同比<br/><font style="font-size: 12px;font-weight: normal;">（单位：百分比）</font></td>
				</tr>
			</thead>
			<tbody style="text-align: right">
			<s:iterator value="sssrList" >			
				<tr>
					<td style="text-align: left">${swjgMc}</td>
					<td>${byyj }</td>
					<td>${sntq }</td>
					<td>${bytb }</td>
					<td>${sysz }</td>
					<td>${bnlj }</td>
					<td>${snlj }</td>
					<td>${ljtb } </td>					
				</tr>
			</s:iterator>
			</tbody>
		</table>
		<br>
		<br>
		<br>
		<br>
		<table border="1px"  cellpadding="2px" cellspacing="0px" align="center">
			<thead align="center" valign="middle" style="font-size: 18px;font-weight: bold">
				<tr>
					<th colspan="8"><s:property value="ssq.substring(0,4)"/>年<s:property value="ssq.substring(4,6)"/>月公共财政预算收入预测数</th>
				</tr>
				<tr>
					<td style="width:4em">项目</td>
					<td style="width:6em">当月预计<br/><font style="font-size: 12px;font-weight: normal;">（单位：万元）</font></td>
					<td style="width:6em">上年同期<br/><font style="font-size: 12px;font-weight: normal;">（单位：万元）</font></td>
					<td style="width:6em">同比<br/><font style="font-size: 12px;font-weight: normal;">（单位：百分比）</font></td>
					<td style="width:6em">上月<br/><font style="font-size: 12px;font-weight: normal;">（单位：万元）</font></td>
					<td style="width:6em">本年累计<br/><font style="font-size: 12px;font-weight: normal;">（单位：万元）</font></td>
					<td style="width:6em">上年累计<br/><font style="font-size: 12px;font-weight: normal;">（单位：万元）</font></td>
					<td style="width:6em">累计同比<br/><font style="font-size: 12px;font-weight: normal;">（单位：百分比）</font></td>
				</tr>
			</thead>
			<tbody style="text-align: right">
			<s:iterator value="czysList" >			
				<tr>
					<td style="text-align: left">${swjgMc}</td>
					<td>${byyj }</td>
					<td>${sntq }</td>
					<td>${bytb }</td>
					<td>${sysz }</td>
					<td>${bnlj }</td>
					<td>${snlj }</td>
					<td>${ljtb } </td>					
				</tr>
			</s:iterator>
			</tbody>
		</table>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
	</body>
</html>
