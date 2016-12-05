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
		<td colspan="7" align="center">
			${year}年${month }月欠税增加率报表
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
			<span>截至上年底欠税余额(万元)</span>
		</td>
		<td>
			<span>本年累计清理陈欠(万元)</span>
		</td>
		<td>
			<span>本期新增欠税(万元) </span>
		</td>
		<td>
			<span>期末欠税余额(万元) </span>
		</td>
		<td>
			<span>欠税增长率</span>
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
				<s:property value="qcqsje" />
			</td>
			<td align="right">
				<s:property value="qqskje" />
			</td>
			<td align="right">
				<s:property value="xzqsje" />
			</td>
			<td align="right">
				<s:property value="qmqsje" />
			</td>
			<td align="right">
				<s:property value="qszjl" />
			</td>
		</tr>
		</s:iterator>
</table>
