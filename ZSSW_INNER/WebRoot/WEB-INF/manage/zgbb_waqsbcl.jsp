<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<table border="1">
					<tr>
						<td colspan="7">
							2014年4月登记户数统计报表
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
							<span>上上个月未准期申报户</span>
						</td>
						<td>
							<span>已处理户</span>
						</td>
						<td>
							<span>未处理户</span>
						</td>
						<td>
							<span>未按期申报处理率</span>
						</td>
						
					</tr>
					<tr>
						<td>
							<span>1</span>
						</td>
						<td>
							<span>昆山二分局</span>
						</td>
						<td>
							<span>260000</span>
						</td>
						<td>
							<span>330000</span>
						</td>
						<td>
							<span>520000</span>
						</td>
						<td>
							<span>20000</span>
						</td>						
					</tr>
				</table>
