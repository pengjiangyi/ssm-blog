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