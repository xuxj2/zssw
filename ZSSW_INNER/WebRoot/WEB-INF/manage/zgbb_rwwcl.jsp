<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<table border="1">
	<tr>
		<td colspan="5" align="center">
			${year}年${month }月任务准期完成率报表
			<p style="display: none">
				<span>	<s:property value="reportId" /></span>
				<span>	<s:property value="year" /></span>
				<span>	<s:property value="month" /></span>
			</p>
		</td>
	</tr>
	<tr>
		<td>
			<span>序号</span>
		</td>
		<td>
			<span>税务机关</span>
		</td>
		<td>
			<span>及时完成任务数</span>
		</td>
		<td>
			<span>任务总数</span>
		</td>
		<td>
			<span>基础任务完成及时率</span>
		</td>
	</tr>
	<s:iterator value="reports" status="status">
		<tr>
			<td>
				<span>${status.count} </span>
			</td>
			<td>
				<s:property value="swjgmc" />
				<p style="display: none">
					<s:property value="swjgdm" />
				</p>
			</td>
			<td align="right">
				<s:property value="zqwcrws" />
			</td>
			<td  align="right">
				<s:property value="ywcrws" />
			</td>
			<td  align="right">
				<s:property value="rwzqwcl" />
			</td>
		</tr>
	</s:iterator>
</table>
