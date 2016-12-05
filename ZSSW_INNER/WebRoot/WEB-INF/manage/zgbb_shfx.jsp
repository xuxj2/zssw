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
		<td colspan="8" align="center">
			${year}年${month }月登记户数统计报表
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
			<span>合计户数</span>
		</td>
		<td>
			<span>企业纳税人</span>
		</td>
		<td>
			<span>个体纳税人</span>
		</td>
		<td>
			<span>正常户</span>
		</td>
		<td>
			<span>非正常户</span>
		</td>
		<td>
			<span>停业户</span>
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
				<s:property value="hjhs" />
			</td>
			<td align="right">
				<s:property value="qyhs" />
			</td>
			<td align="right">
				<s:property value="gths" />
			</td>
			<td align="right">
				<s:property value="zchs" />
			</td>
			<td align="right">
				<s:property value="fzchs" />
			</td>
			<td align="right">
				<s:property value="tyhs" />
			</td>			
		</tr>
	</s:iterator>

</table>

