<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<div>
	<dl class="dl-horizontal">
		<dt>姓名:</dt>
		<dd>
			<input id="xm" name="xm" value="${swry.xm}" type="text">
		</dd>
		<dt>性别:</dt>
		<dd>${swry.sex}</dd>
		<br />
		<dt>工号:</dt>
		<dd>
			<input type="text" value="${swry.gh}" id="gh" name="gh" />
		</dd>
		<dt>任职机关:</dt>
		<dd>
			<s:select id="rzjgs" class="span6" list="rzjgs" name="rzjgDm"
				listKey="rzjgDm" listValue="rzjg" value="swry.rzjgDm">
			</s:select>
		</dd>
		<dt>职务:</dt>
		<dd>
			<s:select id="zw" class="span6" list="zws" listKey="zwDm"
				listValue="zw" value="swry.zwDm">
			</s:select>
		</dd>
		<dt>设备ID:</dt>
		<dd>
			<input type="text" value="${swry.deviceid}" id="deviceid"
				name="deviceid" />
		</dd>
		<dt>是否激活:</dt>
		<dd>
			<input type="hidden" id="swryDm" name="swryDm"  value="${swryDm}"/>
			<div id="jhbj">
			<s:if test="jhbj==0">
				<button class="btn btn-primary btn-mini rightv"  name="${swry.jhbj}">
					激活</button>
			</s:if>
			<s:else>
				<button class="btn btn-primary btn-mini leftv"  name="${swry.jhbj}">
					未激活</button>
			</s:else>
			</div>
		</dd>
	</dl>
	<div class="submit pull-right">
		<input type="submit" value="提交" class="btn btn btn-primary"
			onclick="updateSwry()" />
	</div>
</div> 

