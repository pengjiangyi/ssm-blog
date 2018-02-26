
function getNowFormatDate() {
	var date = new Date();
	var seperator1 = "-";
	var seperator2 = ":";
	var month = date.getMonth() + 1;
	var strDate = date.getDate();
	if(month >= 1 && month <= 9) {
		month = "0" + month;
	}
	if(strDate >= 0 && strDate <= 9) {
		strDate = "0" + strDate;
	}
	var currentdate = date.getFullYear() + seperator1 +
		month + seperator1 + strDate + " " +
		date.getHours() + seperator2 +
		date.getMinutes() + seperator2 +
		date.getSeconds();
	return currentdate;
}

function html2Escape(sHtml) {
	return sHtml.replace(/[<>&"]/g, function(c) {
		return {
			'<': '&lt;',
			'>': '&gt;',
			'&': '&amp;',
			'"': '&quot;'
		}[c];
	});
}
var flag = false;

function rootComment(obj) {
	var text = UE.getEditor('container')
		.getContentTxt();
	var html = UE.getEditor('container').getPlainTxt();
	var str = UE.getEditor('container').getContent();
	var date = getNowFormatDate();
	var parentid = 0;
	var parentname = "";
	//计算出楼层
	var floor;
	if(flag == false) {
		if($(".floor").length ==0)
		floor=1;
		else
		floor = parseInt($(
				"#normal-comments-list .comment-item")
			.last().find(".floor").html()) + 1;
		
		flag = true;
	} else {
		floor = parseInt($(
				"#normal-comments-list .comment-item")
			.first().find(".floor").html()) + 1;
	}
	var author;
	if(username == "彭江毅")
		author = "管理员"
	else {
		author = "用户"
	}
	//根回复
	if(user == "") {
		window.location.href = base +
			'/login.html?from=' +
			encodeURIComponent(location.href);
	} else {
		if(str == null || str.length == 0 || str == "") {
			layer.msg("好歹写几个字吧!");
			return;
		} else {

			$
				.ajax({
					type: "post",
					url: base + "/addMessage",
					async: false,
					contentType: "application/x-www-form-urlencoded; charset=UTF-8",
					data: {
						userid: userid,
						parentid: parentid,
						text: html,
						time: date,
						parentname: parentname
					},
					success: function(data) {

						var doc = '<div class="comment-item">' +
							'<div class="comment-head">' +
							'<div class="author">' +
							'<div class="v-tooltip-container" style="z-index: 0;">' +
							'<div class="v-tooltip-content">' +
							'<a href="" class="avatar">' +
							'<img src="resources/img/user/' + logoUrl + '" class="badge-icon" />' +
							'</a>' +
							'</div>' +
							'</div>' +
							'<div class="info">' +
							'<a href="" class="username">' +
							username +
							'</a>' +
							'<span style="display:none" class="userid">' +
							data +
							'</span>' +
							'<span class="author-tag">' +
							author +
							'</span>' +
							'<div class="meta">' +
							'<span>' +
							'<span class="floor">' +
							floor +
							'<span>' +
							'楼:' +
							date +
							'</span>' +
							'</div>' +
							'</div>' +
							'</div>' +
							'<div class="comment-wrap">' +
							'<p>' +
							html +
							'</p>' +
							'<div class="tool-group">' +

							'<a href="javascript:void(0)" onclick="firstComment(this)"><i class="fa fa-comment-o"></i><span>回复</span></a>' +
							'<a class="report"><i></i><span>举报</span></a>' +
							'</div>' +
							'</div>' +
							'</div>' +

							'<div class="sub-comment-list ">' +

							'</div>' +

							'</div>';
						$("#normal-comments-list")
							.prepend(doc);
						UE.getEditor('container')
							.setContent('',
								false);
						$(".comments-num").html(parseInt($(".comments-num").html())+1);

					},
					error: function() {

					}
				});

		}

	}
}

//回复第一级评论，不用@
function firstComment(obj) {

	if(user == "") {
		window.location.href = base +
			'/login.html?from=' +
			encodeURIComponent(location.href);

	}
	var username = $(obj).parents(".comment-item")
		.find(".username").html();
	var userid = $(obj).parents(".comment-item").find(
		".userid").html();
	if($(obj).children("span").html() == "回复") {
		$(obj).children("span").html("收起")
		var doc = '<div class="new-comment-container" style="margin-top:10px;margin-left:-40px">' +
			'<form class="new-comment">' +
			'<textarea placeholder="请写下你的评论" class="xx"></textarea>' +
			'<div class="write-function-block">' +
			'<a class="btn btn-send" href="javascript:void(0)"  onclick=send(this)>发送</a>' +
			'<a class="cancel">取消</a>' +
			'</div>' +
			'</form>' +

			'</div>';
		$(obj).parents(".comment-item").find(
			".comment-head").append(doc);
	} else {
		$(obj).children("span").html("回复")
		$(obj).parents(".comment-head").find(
			".new-comment-container").remove();
	}
}
//第一级回复的提交按钮
function send(obj) {

	var parentid = $(obj).parents(".comment-head")
		.find(".userid").html();
	var text = $(obj).parents(".comment-head").find(
		"textarea").val();
	var date = getNowFormatDate();
	var parentname = "";
	var date = getNowFormatDate();
	if(text == "") {
		layer.msg("空白是不行的的兄弟")
		return;
	}

	$
		.ajax({
			type: "post",
			url: base + "/addMessage",
			async: false,
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			data: {
				userid: userid,
				parentid: parentid,
				text: html2Escape(text),
				time: date,
				parentname: parentname
			},
			success: function() {

				var doc = '<div class="sub-comment">' +
					'<p>' +
					'<div class="v-tooltip-container" style="z-index: 0;">' +
					'<div class="v-tooltip-content">' +
					'<a href="" class="avatar">' +
					'<img src="resources/img/user/' + logoUrl + '" class="badge-icon" />' +
					'</a>' +
					'<a class="username">' +
					username +
					'</a>:' +
					'</div>' +
					'</div>' +
					'<span>' +
					'<a class="parentname"></a>' +
					html2Escape(text) +
					'</span>' +
					'</p>' +
					'<div class="sub-tool-group">' +
					'<span>' +
					date +
					'</span>' +
					'<a href="javascript:void(0)" onclick=secondComment(this) >' +
					'<i class="fa fa-comment-o"></i><span>回复</span>' +
					'</a>' +
					'<a class="report">' +
					'<i></i>' +
					'</a>' +
					'</div>' + '</div>';

				$(obj).parents(".comment-head")
					.next().append(doc);
				$(obj).parents(".comment-head")
					.find(".comment-wrap a")
					.find("span").html("回复")
					$(".comments-num").html(parseInt($(".comments-num").html())+1);
				$(obj)
					.parents(".comment-head")
					.find(
						".new-comment-container")
					.remove();

			},
			error: function() {

			}
		});

}

//下级回复的提交事件
function secondComment(obj) {
	if(user == "") {
		window.location.href = base +
			'/login.html?from=' +
			encodeURIComponent(location.href);

	}
	var username = $(obj).parents(".sub-comment").find(
		".username").html();
	if($(obj).children("span").html() == "回复") {
		$(obj).children("span").html("收起")
		var doc = '<div class="new-comment-container" style="margin-top:20px">' +
			'<form class="new-comment">' +
			'<textarea placeholder="请写下你的评论"></textarea>' +
			'<div class="write-function-block">' +
			'<a class="btn btn-send" href="javascript:void(0)"  onclick=Send(this)>发送</a>' +
			'<a class="cancel">取消</a>' +
			'</div>' +
			'</form>' + '</div>';
		$(obj).parents(".sub-comment").append(doc);
	} else {
		$(obj).children("span").html("回复")
		$(obj).parents(".sub-comment").find(
			".new-comment-container").remove();
	}
}
//下级回复的回复事件
function Send(obj) {
	var date = getNowFormatDate();
	var parentid = $(obj).parents(".sub-comment-list")
		.prev().find(".userid").html();
	var parentname = $(obj).parents(".sub-comment")
		.find(".username").html();
	var text = $(obj).parents(".new-comment").find(
		"textarea").val();

	if(text == "") {
		layer.msg("空白是不行的的兄弟")
		return;
	}

	$
		.ajax({
			type: "post",
			url: base + "/addMessage",
			async: false,
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			data: {
				userid: userid,
				parentid: parentid,
				text: html2Escape(text),
				time: date,
				parentname: "@" + parentname
			},
			success: function(data) {

				var doc = '<div class="sub-comment">' +
					'<p>' +
					'<div class="v-tooltip-container" style="z-index: 0;">' +
					'<div class="v-tooltip-content">' +
					'<a href="" class="avatar">' +
					'<img src="resources/img/user/' + logoUrl + '" class="badge-icon" />' +
					'</a>' +
					'<a class="username">' +
					username +
					'</a>:' +
					'</div>' +
					'</div>' +
					'<span>' +
					'<a class="parentname" style="margin-right:20px">' +
					'@' +
					parentname +
					'</a>' +
					html2Escape(text) +
					'</span>' +
					'</p>' +
					'<div class="sub-tool-group">' +
					'<span>' +
					date +
					'</span>' +
					'<a href="javascript:void(0)" onclick=secondComment(this)  >' +
					'<i class="fa fa-comment-o"></i><span>回复</span>' +
					'</a>' +
					'<a class="report">' +
					'<i></i>' +
					'</a>' +
					'</div>' + '</div>';

				$(obj).parents(".sub-comment-list")
					.append(doc);
				$(".comments-num").html(parseInt($(".comments-num").html())+1);
				$(obj).parents(".sub-comment")
					.find(".sub-tool-group a")
					.find("span").html("回复");
				$(obj)
					.parents(".sub-comment")
					.find(
						".new-comment-container")
					.remove();

			},
			error: function() {

			}
		});

}

$(document)
	.ready(
		function() {
			$('#defaultForm')
				.bootstrapValidator({
					message: 'This value is not valid',
					feedbackIcons: { /*输入框不同状态，显示图片的样式*/
						valid: 'glyphicon glyphicon-ok',
						invalid: 'glyphicon glyphicon-remove',
						validating: 'glyphicon glyphicon-refresh'
					},
					fields: { /*验证*/
						'category': {
							message: '类型验证失败!',
							validators: {
								notEmpty: {
									message: '类型不能为空'
								},
								callback: {
									message: '请选择一个类别',
									callback: function(
										value,
										validator) {
										if(value == 0) {
											return false;
										} else {
											return true;
										}
									}
								}
							}
						},
						webname: { /*键名username和input name值对应*/
							message: '请输入网站名称',
							validators: {
								notEmpty: { /*非空提示*/
									message: '网站名称非空'
								}
							}
						},
						weburl: { /*键名username和input name值对应*/
							message: '请输入网站地址',
							validators: {
								notEmpty: { /*非空提示*/
									message: '网站地址非空'
								},
								uri: { //正则验证  
									message: '允许http,https,ftp开头的网址'
								}

							}
						},
						username: {
							message: '用户名无效',
							validators: {
								notEmpty: {
									message: '用户名不能为空'
								},
								stringLength: {
									min: 6,
									max: 15,
									message: '用户名长度必须在6到30之间'
								}
							}
						},
						telphone: {
							validators: {
								notEmpty: {
									message: '手机号码不能为空'
								},
								regexp: { //正则验证  
									regexp: /^[1][3,4,5,7,8][0-9]{9}$/,
									message: '手机号码格式不对'
								}
							}
						},
						content: {
							validators: {
								notEmpty: {
									message: '请输入简介'
								},
								stringLength: {
									min: 1,
									max: 100,
									message: '长度在1-100'
								}
							}
						}
					}
				});
		});
$("#subLink")
	.click(
		function() {
			var data = $("#defaultForm").data(
				'bootstrapValidator');
			$("#defaultForm").data(
					'bootstrapValidator')
				.validate();
			if(data.isValid() == true) {
				$
					.ajax({
						type: "post",
						url: base +
							"/addLinks",
						async: false,
						contentType: "application/x-www-form-urlencoded; charset=UTF-8",
						data: {
							webname: $(
									"#webname")
								.val(),
							weburl: $(
									"#weburl")
								.val(),
							category: $(
									"#category")
								.val(),
							username: $(
									"#username")
								.val(),
							telphone: $(
									"#telphone")
								.val(),
							content: $(
									"#content")
								.val()
						},
						success: function(
							data) {
							layer
								.alert("请将下列网址添加到你的友链名称-----------------(http://www.pengjiangyi.com)合作愉快");
							$(
									'#defaultForm')
								.bootstrapValidator(
									'resetForm',
									true);
						},
						error: function() {}
					});
			} else {
				layer.msg("验证没通过")
			}
		})








