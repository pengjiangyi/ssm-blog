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

	</head>
	<style>
			
		.login-page {
			padding: 2% 0 0;
			margin: auto;
		}
		
		.form {
			position: relative;
			z-index: 1;
			background: #FFFFFF;
			max-width: 360px;
			margin: 0 auto 100px;
			padding: 45px;
			text-align: center;
			box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
		}
		
		.form input {
			font-family: "Roboto", sans-serif;
			outline: 0;
			background: #f2f2f2;
			width: 100%;
			border: 0;
			margin: 0 0 15px;
			padding: 15px;
			box-sizing: border-box;
			font-size: 14px;
		}
		
		.form button {
			font-family: "Microsoft YaHei", "Roboto", sans-serif;
			text-transform: uppercase;
			outline: 0;
			background: #4CAF50;
			width: 100%;
			border: 0;
			padding: 15px;
			color: #FFFFFF;
			font-size: 14px;
			-webkit-transition: all 0.3 ease;
			transition: all 0.3 ease;
			cursor: pointer;
		}
		
		.form button:hover,
		.form button:active,
		.form button:focus {
			background: #43A047;
		}
		
		.form .message {
			margin: 15px 0 0;
			color: #b3b3b3;
			font-size: 12px;
		}
		
		.form .message a {
			color: #4CAF50;
			text-decoration: none;
		}
		
		.form .register-form {
			display: none;
		}
		
		.shake_effect {
			-webkit-animation-name: shake;
			animation-name: shake;
			-webkit-animation-duration: 1s;
			animation-duration: 1s;
		}
		
		@-webkit-keyframes shake {
			from,
			to {
				-webkit-transform: translate3d(0, 0, 0);
				transform: translate3d(0, 0, 0);
			}
			10%,
			30%,
			50%,
			70%,
			90% {
				-webkit-transform: translate3d(-10px, 0, 0);
				transform: translate3d(-10px, 0, 0);
			}
			20%,
			40%,
			60%,
			80% {
				-webkit-transform: translate3d(10px, 0, 0);
				transform: translate3d(10px, 0, 0);
			}
		}
		
		@keyframes shake {
			from,
			to {
				-webkit-transform: translate3d(0, 0, 0);
				transform: translate3d(0, 0, 0);
			}
			10%,
			30%,
			50%,
			70%,
			90% {
				-webkit-transform: translate3d(-10px, 0, 0);
				transform: translate3d(-10px, 0, 0);
			}
			20%,
			40%,
			60%,
			80% {
				-webkit-transform: translate3d(10px, 0, 0);
				transform: translate3d(10px, 0, 0);
			}
		}
	</style>

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
</script>

<script>

	var base='${request.contextPath}';
	function check_login() {
		var name = $("#user_name").val();
		var pass = $("#password").val();
		if($.trim(name) == "" && $.trim(name) == 0) {
			layer.msg('用户名不能为空', {
				icon: 5,
				time: 1000
			});
			$("#login_form").removeClass('shake_effect');
			setTimeout(function() {
				$("#login_form").addClass('shake_effect')
			}, 1);
		} else if($.trim(pass) == "" && $.trim(pass) == 0) {
			layer.msg('密码不能为空', {
				icon: 5,
				time: 1000
			});
			$("#login_form").removeClass('shake_effect');
			setTimeout(function() {
				$("#login_form").addClass('shake_effect')
			}, 1);
		} else {
			$.ajax({
				type: "post",
				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
				url: base+"/login",
				async: false,
				data:{
				email:name,
				password:pass
				},
				success: function(data) {
				if(data=="登录成功")
				window.location.href=backUrl;
				else
				layer.msg('用户名或密码错误', {
				icon: 5,
				time: 1000
			});
				
				
				
				},
				error: function() {

				}

			});

		}

	}

	function check_register() {
		var name = $("#r_user_name").val();
		var pass = $("#r_password").val();
		var checkpass = $("#r_password2").val();
		var email = $("#r_email").val();

		//验证邮箱格式的js正则表达式  
		var isEmail = /^[0-9A-Za-z][\.-_0-9A-Za-z]*@[0-9A-Za-z]+(\.[0-9A-Za-z]+)+$/;

		if($.trim(email) == "" && $.trim(email).length == 0) {
			layer.msg('邮箱不能为空', {
				icon: 5,
				time: 1000
			});
			$("#login_form").removeClass('shake_effect');
			setTimeout(function() {
				$("#login_form").addClass('shake_effect')
			}, 1);
		} else if(!isEmail.test(email)) {
			layer.msg('邮箱格式不正确', {
				icon: 5,
				time: 1000
			});
			$("#login_form").removeClass('shake_effect');
			setTimeout(function() {
				$("#login_form").addClass('shake_effect')
			}, 1);
		} else if($.trim(name) == "" && $.trim(name).length == 0 || $.trim(name).length > 6) {
			layer.msg('用户名格式不正确', {
				icon: 5,
				time: 1000
			});
			$("#login_form").removeClass('shake_effect');
			setTimeout(function() {
				$("#login_form").addClass('shake_effect')
			}, 1);
		} else if($.trim(pass) == "" && $.trim(pass).length == 0 || $.trim(pass).length > 10) {
			layer.msg('密码格式不对', {
				icon: 5,
				time: 1000
			});
			$("#login_form").removeClass('shake_effect');
			setTimeout(function() {
				$("#login_form").addClass('shake_effect')
			}, 1);
		} else if($.trim(checkpass) == "" && $.trim(checkpass).length == 0 || $.trim(checkpass).length > 10) {
			layer.msg('密码格式不对', {
				icon: 5,
				time: 1000
			});
			$("#login_form").removeClass('shake_effect');
			setTimeout(function() {
				$("#login_form").addClass('shake_effect')
			}, 1);
		} else if(pass != checkpass) {
			layer.msg('两次密码不一致', {
				icon: 5,
				time: 1000
			});
			$("#login_form").removeClass('shake_effect');
			setTimeout(function() {
				$("#login_form").addClass('shake_effect')
			}, 1);
		} else {
		var index = layer.load(1);	
			//全部条件满足，进行注册
			$.ajax({
				type: "post",
				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
				data:{
				username:name,
				password:pass,
				email:email
				},
				url: base+"/register.html",
				async: false,
				success: function(data) {
					layer.close(index);
					
					if(data=='注册成功')
					{
					layer.msg(data,{time:1000});
					$('form').animate({
					height: 'toggle',
					opacity: 'toggle'
					}, 'slow');
					}
					else{
					layer.msg(data,{time:1000});
					}
					
					
				},
				error: function() {

				}
			});
		}

	}

	$("#create").click(function() {  
		check_register();  
		return false;
	})
	$("#login").click(function() {  
		check_login();  
		return false;
	})

	$('.message a').click(function() {
		$('form').animate({
			height: 'toggle',
			opacity: 'toggle'
		}, 'slow');
	});
</script>

<script>
var base="${request.contextPath}";
$(".login").attr("href",base+"/login.html?from="+base+"/");
</script>
