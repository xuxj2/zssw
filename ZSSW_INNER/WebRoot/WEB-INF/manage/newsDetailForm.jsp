<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<style>
table {
	text-align: left;
	padding: 2px;
	margin: 5px;
}

table input {
	height: 30px;
	vertical-align: middle;
}

table input[type='text'] {
	width: 600px;
	height: 30px;
	vertical-align: middle;
}

table textarea {
	width: 600px;
}

table td {
	vertical-align: top;
}
</style>

<form id="deform1" action="<%=basePath%>manage/news_modify.action"
	method="post">
	<input type="hidden" name="news.columnId" value="${news.columnId}" />
	<input type="hidden" name="news.id" value="${news.id}" />
	<table>
		<tr>
			<td>
				标题:
			</td>
			<td>
				<input type="text" name="news.title" value="${news.title }" />
			</td>
		</tr>
		<tr>
			<td>
				原文地址:
			</td>
			<td>
				<input type="text" name="news.sourceurl" value="${news.sourceurl}" />
			</td>
		</tr>
		<tr>
			<td>
				发布日期:
			</td>
			<td>
				<input type="text" name="news.time" value="${news.time }" />
			</td>
		</tr>
		<tr>
			<td>
				消息来源:
			</td>
			<td>
				<input type="text" name="news.channel" value="${news.channel }" />
			</td>
		</tr>	
		<s:if test="news.columnId.substring(0,3)=='zsk'||news.columnId.substring(0,3)=='fgk'">
			<tr>
				<td>
					发文字号:
				</td>
				<td>
					<input type="text" name="news.issueNumber"
						value="${news.issueNumber }" />
				</td>
			</tr>
			<tr>
				<td>
					有效性:
				</td>
				<td>
					<span style="margin-right: 15px"> 全文有效<input type="radio"
							name="news.effective" value="全文有效" checked="checked" /> </span>
					<span> 部分有效<input type="radio" name="news.effective"
							value="部分有效" /> </span>
				</td>
			</tr>
		</s:if>
		<s:else>
			<tr>
				<td>
					客户端可见:
				</td>
				<td>
					<span style="margin-right: 15px">显示<input type="radio"
							name="news.visibility" alt="显示" value="1" checked="checked" /> </span>
					<span>不显示<input type="radio" name="news.visibility"
							alt="不显示" value="0" /> </span>
				</td>
			</tr>
		</s:else>

		<tr>
			<td>
				正文:
			</td>
			<td>
				<textarea name="news.textContent" rows="30">${news.textContent }</textarea>
			</td>
		</tr>
		<tr>
			<td>
				附件:
			</td>
			<td>
				<textarea name="news.fileurl">${news.fileurl }</textarea>
			</td>
		</tr>
		<tr>
			<td>
				链接（含图片）:
			</td>
			<td>
				<textarea name="news.imageurl">${news.imageurl}</textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input id="upsub" type="button" value="提交修改" />
				<input id="upretn" type="button" value="返回" />
			</td>

		</tr>
	</table>
</form>