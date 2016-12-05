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
			${year}年${month }月准期申报率报表
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
			<span>应申报户数</span>
		</td>
		<td>
			<span>准期申报户数</span>
		</td>
		<td>
			<span>逾期申报户数</span>
		</td>
		<td>
			<span>准期申报率</span>
		</td>
		<td>
			<span>逾期申报率</span>
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
			<td>
				<s:property value="ysbhs" />
			</td>
			<td>
				<s:property value="zqsbhs" />
			</td>
			<td>
				<s:property value="yisbhs" />
			</td>
			<td>
				<s:property value="zqsbl" />
			</td>
			<td>
				<s:property value="sbl" />
			</td>
		</tr>

	</s:iterator>


</table>
