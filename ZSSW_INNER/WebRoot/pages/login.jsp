<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
		<title>掌上税务后台管理登陆</title>
		<meta http-equiv="description" content="掌上税务后台管理登陆">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

		<link rel="stylesheet"
			href="css/bootstrap.min.css" />
		<link rel="stylesheet"
			href="css/bootstrap-responsive.min.css" />
		<link rel="stylesheet"
			href="css/unicorn.login.css" />

		<script src="js/jquery.min.js"></script>

		<script type="text/javascript">	
		$(document).ready(function(){				
        	var n_er=$("#name_er");
        	var p_er=$("#pass_er");
        	n_er.hide();
        	p_er.hide();			
        });
		
	function check_login() {		
		var userName =$("#name").val();
		var userPassword = $("#password").val();
		var checkName = false;
		var checkPassword = false;
		if (!userName && userName == "") {
			$("#name_er").html("用户名不能为空");
			$("#name_er").show();
			checkName = false;
		} else {
			checkName = true;
			$("#name_er").hide();
		}
		if (!userPassword && userPassword == "") {
			$("#pass_er").html("密码不能为空");
			$("#pass_er").show();
			checkPassword = false;
		} else {
			checkPassword = true;
			$("#pass_er").hide();
		}	
		return checkName&&checkPassword;
	}
</script>

	</head>
	<body>
		<div id="logo">
			<div class="logo-content clearfix">				
				<span>
					掌上税务后台登录						
				</span>
			</div>
		</div>		
		<div id="loginbox">
			<form action="<%=response.encodeURL("manage/user_login.action") %>"
				onsubmit="return check_login()" />
				<ul>
					<li>
						<div class="input-prepend">
							<span class="add-on"><i class="icon-user"></i> </span>
							<input id="name" name="name" type="text" placeholder="登录名" />
						</div>
						<div class="error" id="name_er">
							用户名输入错误！
						</div>
					</li>
					<li>
						<div class="input-prepend">
							<span class="add-on"><i class="icon-lock"></i> </span>
							<input id="password" name="password" type="password"
								placeholder="密码" />
						</div>
						<div class="error" id="pass_er">
							密码输入错误！
						</div>
					</li>
					<li>
						<input type="submit" id="login" class="btn btn-block btn-primary"
							value="登录" />
					</li>
				</ul>
			</form>			
		
		</div>
		<div id="login-footer">
			<div class="foot">
				<p>
					<a href="http://sz.jsds.gov.cn/">苏州地方税务局</a>&nbsp;&nbsp;&nbsp;&nbsp;版权所有&nbsp;&nbsp;&nbsp;&nbsp;苏ICP证xxxxxx
				</p>
				<p>
					客户服务热线：12366
				</p>
			</div>
		</div>
	</body>
</html>
