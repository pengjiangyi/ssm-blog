<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<meta http-equiv="Content-Language" content="zh-CN">
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<title>博客登录页面</title>
		<link rel="SHORTCUT ICON" href="${request.contextPath}/resources/img/favicon.ico">
		<!--bootstrap的css-->
		<link rel="stylesheet" href="${request.contextPath}/resources/assets/bootstrap-3.3.7-dist/css/bootstrap.min.css" />
		<!--font-awesome-->
		<link href="${request.contextPath}/resources/assets/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
		<!--全局样式-->
		<link rel="stylesheet" href="${request.contextPath}/resources/css/global.css" />
		<!--动画效果css-->
		<link href="${request.contextPath}/resources/css/animate.css" rel="stylesheet" />
		<link href="${request.contextPath}/resources/css/login.css" rel="stylesheet" />

	</head>
	
	<body>
		<#include "head.ftl">

		<!-- 主体（一般只改变这里的内容） -->
		<div class="blog-body container">
			<!-- 这个一般才是真正的主体内容 -->
			<div class="blog-container ">
				<ol class="breadcrumb ">
					<li>
						<a href="/">网站首页</a>
					</li>
					<li>
						<a href="/login.html" class="active ">用户登录</a>
					</li>

				</ol>

				<div class="blog-main animated zoomIn">
					<div class="container">
						<div id="wrapper" class="login-page ">
							<div id="login_form" class="form">
								<form class="register-form">
									<input type="text" placeholder="电子邮件(将作为你的登录名)" id="r_email" />
									<input type="text" placeholder="昵称" id="r_user_name" />
									<input type="password" placeholder="密码" id="r_password" />
									<input type="password" placeholder="密码确认" id="r_password2" />

									<button id="create">创建账户</button>
									<p class="message">已经有了一个账户?
										<a href="#">立刻登录</a>
									</p>
								</form>
								<form class="login-form">
									<input type="text" placeholder="用户名(邮箱)" id="user_name" />
									<input type="password" placeholder="密码" id="password" />
									<button id="login">登　录</button>
									<p class="message">还没有账户?
										<a href="#">立刻创建</a>
									</p>
								</form>
							</div>
						</div>
					</div>
					<div class="clear "></div>
				</div>
			</div>
		</div>
			<!-- 底部 -->
			<#include "foot.ftl">

			<!-- 本页脚本 -->

			<script src="${request.contextPath}/resources/assets/jquery-3.2.1/jquery-3.2.1.min.js "></script>
			<script src="${request.contextPath}/resources/assets/layer-v3.1.1/layer/layer.js"></script>
			<script src="${request.contextPath}/resources/assets/bootstrap-3.3.7-dist/js/bootstrap.min.js "></script>
			<!-- 全局脚本 -->

	</body>

</html>
<script>
var backUrl='${backurl!}';
var base='${request.contextPath}';
$(".login").attr("href",base+"/login.html?from="+base+"/");
</script>

<script src="${request.contextPath}/resources/js/login.js"></script>
