
	$(".article-detail-content img").addClass("img-responsive");

	/*点击排行颜色js开始*/
	var colors = ["red", "orange", "green", "blue", "cyan"];
	var nums = ["1", "2", "3", "4", "5"];
	$(".list-group-item em").each(function(index, dom) {
		$(this).css("background-color", colors[index]);
		$(this).text(nums[index])
	})

	$('#gotop').click(function() {
		$('html,body').animate({
			scrollTop: '0px'
		}, 300);
	});
	$('#gocomments').click(function() {
		$('html,body').animate({
			scrollTop: $('.article-comment').offset().top
		}, 300);
	});
	$('#godown').click(function() {
		$('html,body').animate({
			scrollTop: $('.blog-footer').offset().top
		}, 300);
	});

	$(".go").hover(function() {
		var id = $(this).attr('id');
		if(id == 'gotop') {
			$(this).html("返回顶部");
		} else if(id == "gocomments") {
			$(this).html("欢迎评论");
		} else if(id == "godown") {
			$(this).html("返回底部");
		}
		$(this).css({
			"color": "black",
			"font-size": "14px"
		})
	}, function() {
		var id = $(this).attr('id');
		if(id == 'gotop') {
			var doc = '<i class="fa fa-chevron-up"></i>';
			$(this).html(doc);

		} else if(id == "gocomments") {
			var doc = '<i class="fa fa-edit"></i>';
			$(this).html(doc);
		} else if(id == "godown") {
			var doc = '<i class="fa fa-chevron-down"></i>';
			$(this).html(doc);

		}
		$(this).css({
			"color": "#DAD8D8",
			"font-size": "20px"
		})
	});
	/*点击排行颜色js结束*/
	
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
		var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate +
			" " + date.getHours() + seperator2 + date.getMinutes() +
			seperator2 + date.getSeconds();
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
		var text = UE.getEditor('container').getContentTxt();
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
			floor = parseInt($("#normal-comments-list .comment-item").last().find(".floor").html()) + 1;
			flag = true;
		} else {
			floor = parseInt($("#normal-comments-list .comment-item").first().find(".floor").html()) + 1;
		}
		var author;
		if(username == "彭江毅")
			author = "管理员"
		else {
			author = "用户"
		}
		//根回复
		if(user == "") {
			window.location.href = base + '/login.html?from=' + encodeURIComponent(location.href);
		} else {
			if(str == null || str.length == 0 || str == "") {
				layer.msg("好歹写几个字吧!");
				return;
			} else {

				$.ajax({
					type: "post",
					url: base + "/addComments",
					async: false,
					contentType: "application/x-www-form-urlencoded; charset=UTF-8",
					data: {
						userid: userid,
						parentid: parentid,
						text: html,
						time: date,
						parentname: parentname,
						articleid:articleid
					},
					success: function(data) {

						var doc =
							'<div class="comment-item">' +
							'<div class="comment-head">' +
							'<div class="author">' +
							'<div class="v-tooltip-container" style="z-index: 0;">' +
							'<div class="v-tooltip-content">' +
							'<a href="" class="avatar">' +
							'<img src="resources/img/user/'+logoUrl+'" class="badge-icon" />' +
							'</a>' +
							'</div>' +
							'</div>' +
							'<div class="info">' +
							'<a href="" class="username">' + username + '</a>' +
							'<span style="display:none" class="userid">' + data + '</span>' +
							'<span class="author-tag">' +
							author +
							'</span>' +
							'<div class="meta">' +
							'<span>' + '<span class="floor">' + floor + '<span>' + '楼:' + date + '</span>' +
							'</div>' +
							'</div>' +
							'</div>' +
							'<div class="comment-wrap">' +
							'<p>' + html + '</p>' +
							'<div class="tool-group">' +
					
							'<a href="javascript:void(0)" onclick="firstComment(this)"><i class="fa fa-comment-o"></i><span>回复</span></a>' +
							'<a class="report"><i></i><span>举报</span></a>' +
							'</div>' +
							'</div>' +
							'</div>' +

							'<div class="sub-comment-list ">' +

							'</div>' +

							'</div>';
						$("#normal-comments-list").prepend(doc);
						UE.getEditor('container').setContent('', false);
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
			window.location.href = base + '/login.html?from=' + encodeURIComponent(location.href);

		}
		var username = $(obj).parents(".comment-item").find(".username").html();
		var userid = $(obj).parents(".comment-item").find(".userid").html();
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
			$(obj).parents(".comment-item").find(".comment-head").append(doc);
		} else {
			$(obj).children("span").html("回复")
			$(obj).parents(".comment-head").find(".new-comment-container").remove();
		}
	}
	//第一级回复的提交按钮
	function send(obj) {

		var parentid = $(obj).parents(".comment-head").find(".userid").html();
		var text = $(obj).parents(".comment-head").find("textarea").val();
		var date = getNowFormatDate();
		var parentname = "";
		var date = getNowFormatDate();
		if(text == "") {
			layer.msg("空白是不行的的兄弟")
			return;
		}

		$.ajax({
			type: "post",
			url: base + "/addComments",
			async: false,
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			data: {
				userid: userid,
				parentid: parentid,
				text: html2Escape(text),
				time: date,
				parentname: parentname,
				articleid:articleid
			},
			success: function() {

				var doc = '<div class="sub-comment">' +
					'<p>' +
					'<div class="v-tooltip-container" style="z-index: 0;">' +
					'<div class="v-tooltip-content">' +
						
					'<a href="" class="avatar">'+
					'<img src="resources/img/user/'+logoUrl+'" class="badge-icon" />'+
				'</a>'+
					'<a class="username">' + username + '</a>:' +
					'</div>' +
					'</div>' +
					'<span>' +
					'<a class="parentname"></a>' +
					html2Escape(text) +
					'</span>' +
					'</p>' +
					'<div class="sub-tool-group">' +
					'<span>' + date + '</span>' +
					'<a href="javascript:void(0)" onclick=secondComment(this) >' +
					'<i class="fa fa-comment-o"></i><span>回复</span>' +
					'</a>' +
					'<a class="report">' +
					'<i></i>' +
					'</a>' +
					'</div>' +
					'</div>';

				$(obj).parents(".comment-head").next().append(doc);
				$(".comments-num").html(parseInt($(".comments-num").html())+1);
					$(obj).parents(".comment-head").find(".comment-wrap a").find("span").html("回复")
				$(obj).parents(".comment-head").find(".new-comment-container").remove();

			},
			error: function() {

			}
		});

	}

	//下级回复的提交事件
	function secondComment(obj) {
		if(user == "") {
			window.location.href = base + '/login.html?from=' + encodeURIComponent(location.href);

		}
		var username = $(obj).parents(".sub-comment").find(".username").html();
		if($(obj).children("span").html() == "回复") {
			$(obj).children("span").html("收起")
			var doc = '<div class="new-comment-container" style="margin-top:20px">' +
				'<form class="new-comment">' +
				'<textarea placeholder="请写下你的评论"></textarea>' +
				'<div class="write-function-block">' +
				'<a class="btn btn-send" href="javascript:void(0)"  onclick=Send(this)>发送</a>' +
				'<a class="cancel">取消</a>' +
				'</div>' +
				'</form>' +
				'</div>';
			$(obj).parents(".sub-comment").append(doc);
		} else {
			$(obj).children("span").html("回复")
			$(obj).parents(".sub-comment").find(".new-comment-container").remove();
		}
	}
	//下级回复的回复事件
	function Send(obj) {
		var date = getNowFormatDate();
		var parentid = $(obj).parents(".sub-comment-list").prev().find(".userid").html();
		var parentname = $(obj).parents(".sub-comment").find(".username").html();
		var text = $(obj).parents(".new-comment").find("textarea").val();

		if(text == "") {
			layer.msg("空白是不行的的兄弟")
			return;
		}

		$.ajax({
			type: "post",
			url: base + "/addComments",
			async: false,
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			data: {
				userid: userid,
				parentid: parentid,
				text: html2Escape(text),
				time: date,
				parentname: "@" + parentname,
				articleid:articleid
			},
			success: function(data) {

				var doc = '<div class="sub-comment">' +
					'<p>' +
					'<div class="v-tooltip-container" style="z-index: 0;">' +
					'<div class="v-tooltip-content">' +
						'<a href="" class="avatar">'+
					'<img src="resources/img/user/'+logoUrl+'" class="badge-icon" />'+
				'</a>'+
					'<a class="username">' + username + '</a>:' +
					'</div>' +
					'</div>' +
					'<span>' +
					'<a class="parentname" style="margin-right:20px">' + '@' + parentname + '</a>' +
					html2Escape(text) +
					'</span>' +
					'</p>' +
					'<div class="sub-tool-group">' +
					'<span>' + date + '</span>' +
					'<a href="javascript:void(0)" onclick=secondComment(this)  >' +
					'<i class="fa fa-comment-o"></i><span>回复</span>' +
					'</a>' +
					'<a class="report">' +
					'<i></i>' +
					'</a>' +
					'</div>' +
					'</div>';

				$(obj).parents(".sub-comment-list").append(doc);
				$(".comments-num").html(parseInt($(".comments-num").html())+1);
				$(obj).parents(".sub-comment").find(".sub-tool-group a").find("span").html("回复");
				$(obj).parents(".sub-comment").find(".new-comment-container").remove();

			},
			error: function() {

			}
		});

	}

function search(){
var keywords=$("#input-keywords").val();
if(keywords==null||keywords=="")
{
layer.msg("请输入想要查找的词汇")
return;
}
location.href=base+"/search/"+keywords+"/1.html";
}

$("#input-keywords").keydown(function(){
if(event.keyCode == 13){
search();
}
});
	
	