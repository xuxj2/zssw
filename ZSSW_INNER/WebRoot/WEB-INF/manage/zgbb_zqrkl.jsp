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
			${year}年${month }月准期入库率报表
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
			<span>应入库户数</span>
		</td>
		<td>
			<span>准期入库户数</span>
		</td>
		<td>
			<span>准期入库率</span>
		</td>
		<td>
			<span>应入库金额</span>
		</td>
		<td>
			<span>已入库金额</span>
		</td>
		<td>
			<span>入库率</span>
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
				<s:property value="yrkhs" />
			</td>
			<td  align="right">
				<s:property value="zqrkhs" />
			</td>
			<td  align="right">
				<s:property value="zqrkl" />
			</td>
			<td  align="right">
				<s:property value="yrkje" />
			</td>
			<td  align="right">
				<s:property value="yirkje" />
			</td>
			<td  align="right">
				<s:property value="rkl" />
			</td>
		</tr>
	</s:iterator>

</table>
