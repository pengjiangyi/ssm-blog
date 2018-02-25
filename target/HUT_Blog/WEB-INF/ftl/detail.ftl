<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<meta http-equiv="Content-Language" content="zh-CN">
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<title>文章详情页/${articleInfo.title!}</title>
		<meta name="description" content="${articleInfo.des}" />
		<meta name="keywords" content="${articleInfo.keywords}" />
		<link rel="SHORTCUT ICON" href="${request.contextPath}/resources/img/favicon.ico">
		<!--动画效果css-->
		<link href="${request.contextPath}/resources/css/animate.css" rel="stylesheet" />
		<!--font-awesome-->
		<link href="${request.contextPath}/resources/assets/font-awesome/css/font-awesome.min.css" rel="stylesheet" />

		<!--bootstrap的css-->

		<link rel="stylesheet" href="${request.contextPath}/resources/assets/bootstrap-3.3.7-dist/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${request.contextPath}/resources/css/global.css" />
		<link rel="stylesheet" href="${request.contextPath}/resources/css/detail.css" />
		<link rel="stylesheet" href="${request.contextPath}/resources/css/about.css" />
		<script type="text/javascript" src="${request.contextPath}/resources/utf8-jsp/third-party/SyntaxHighlighter/shCore.js"></script>
		<link rel="stylesheet" href="${request.contextPath}/resources/utf8-jsp/third-party/SyntaxHighlighter/shCoreDefault.css">
		<script type="text/javascript">
		
   		SyntaxHighlighter.all();
		</script>

		<style>
			.blog-main {
				background-color: #eeeeee;
				color: #444;
			}
			.blog-module p {
    line-height: 5px;
    font-size: 13px;
}
			
			.go {
				background: white;
				width: 40px;
				height: 40px;
				text-align: center;
				color: #DAD8D8;
				border: 1px solid #DAD8D8;
			}
			
			.scroll {
				position: fixed;
				left: 95%;
				top: 50%;
				font-size: 23px;
				cursor: pointer;
			}
			
			@media(max-width:768px) {
				.scroll {
					display: none;
				}
			}
	
		.normal-comments-list .comment-item {
			padding: 20px 0 30px;
			border-bottom: 1px solid #f0f0f0;
		}
		
		.normal-comments-list .author {
			margin-bottom: 15px;
		}
		
		.normal-comments-list p {
			margin: 10px 0;
			line-height: 1.5;
			font-size: 16px;
			word-break: break-word!important;
			word-break: break-all;
		}
		
		.v-tooltip-container {
			position: relative;
		}
		
		.v-tooltip-container,
		.v-tooltip-content {
			display: inline-block;
		}
		
		.normal-comments-list .avatar {
			margin-right: 5px;
			width: 38px;
			height: 38px;
			vertical-align: middle;
			display: inline-block;
		}
		
		.avatar img {
			width: 100%;
			height: 100%;
			border: 1px solid #ddd;
			border-radius: 50%;
			width: 38px;
		}
		
		.normal-comments-list .info {
			display: inline-block;
			vertical-align: middle;
		}
		
		.normal-comments-list .name {
			font-size: 15px;
			color: #333;
		}
		
		.normal-comments-list .author-tag {
			margin-left: 2px;
			padding: 0 2px;
			font-size: 12px;
			color: #ea6f5a;
			border: 1px solid #ea6f5a;
			border-radius: 3px;
			vertical-align: middle;
		}
		
		.badge-icon {
			height: 17px;
			width: 17px;
			border-radius: 50%;
			transition: all 1.0s;
			overflow: hidden;
		}
		.badge-icon:hover{
			transform: rotate(360deg);
		}
		
		.normal-comments-list .meta {
			font-size: 12px;
			color: #969696;
		}
		
		.normal-comments-list .meta span {
			margin-right: 6px;
		}
		
		.normal-comments-list p {
			margin: 10px 0;
			line-height: 1.5;
			font-size: 16px;
			word-break: break-word!important;
			word-break: break-all;
		}
		
		.normal-comments-list .tool-group a {
			margin-right: 10px;
			font-size: 10px;
			color: #969696;
			display: inline-block;
		}
		
		.normal-comments-list .tool-group a i {
			margin-right: 5px;
			font-size: 18px;
		}
		
		.normal-comments-list .comment-wrap .report {
			float: right;
			margin: 12px 0 0 10px;
			display: none;
		}
		
		.sub-comment-list {
			margin-top: 20px;
			padding: 5px 0 5px 20px;
			border-left: 2px solid #d9d9d9;
		}
		
		.sub-comment-list .sub-comment {
			margin-bottom: 15px;
			padding-bottom: 15px;
			border-bottom: 1px dashed #f0f0f0;
		}
		
		.sub-comment-list p {
			margin: 0 0 5px;
			font-size: 14px;
			line-height: 1.5;
		}
		
		.sub-comment-list .sub-tool-group {
			font-size: 12px;
			color: #969696;
		}
		
		.sub-comment-list .sub-tool-group a i {
			margin-right: 5px;
			font-size: 14px;
			margin-left: 5px;
		}
		
		.normal-comments-list .more-comment {
			font-size: 14px;
			color: #969696;
			border: none;
		}
		
		.normal-comments-list .sub-comment-list .new-comment {
			margin: 0;
		}
		
		.normal-comments-list .sub-comment-list .new-comment textarea {
			width: 100%;
		}
		
		.normal-comments-list .new-comment {
			position: relative;
			margin-left: 48px;
		}
		
		.normal-comments-list .new-comment textarea {
			padding: 10px 15px;
			width: 100%;
			height: 80px;
			font-size: 13px;
			border: 1px solid #dcdcdc;
			border-radius: 4px;
			background-color: hsla(0, 0%, 71%, .1);
			resize: none;
			display: inline-block;
			vertical-align: top;
			outline-style: none;
		}
		
		.normal-comments-list .new-comment .write-function-block {
			height: 50px;
		}
		
		.normal-comments-list .new-comment .btn-send {
			float: right;
			width: 78px;
			margin: 10px 0;
			padding: 8px 18px;
			font-size: 10px;
			border: none;
			border-radius: 20px;
			color: #fff!important;
			background-color: #42c02e;
			cursor: pointer;
			outline: none;
			display: block;
		}
		
		.normal-comments-list .new-comment .cancel {
			float: right;
			margin: 18px 30px 0 0;
			font-size: 14px;
			color: #969696;
		}
		/*评论效果*/
		
		.reply {
			max-height: 0;
			overflow: hidden;
			transition: .3s ease-out;
		}
		
		.slideUp {
			max-height: 0;
		}
		
		.slideDown {
			max-height: 135px;
		}

			
			
			
			
		</style>
	</head>

	<body>

		
		<#include "head.ftl">

		<!--网站主体-->
		<div class="container blog-main">
			<ol class="breadcrumb ">
				<li>
					<a href="${request.contextPath}/">网站首页</a>
				</li>
				<li>
					<a href="#" class="active ">文章详情</a>
				</li>
				<li>
					<a href="#" >${articleInfo.title!}</a>
				</li>
			</ol>

			<div class="row">
				<!--左边文章列表-->
				<div class="blog-main-left  col-md-8 col-sm-12">
					<div class="article-detail shadow">
						<div class="article-detail-title title">${articleInfo.title!}</div>
						<div class="article-detail-info"><span>编辑时间：${articleInfo.createtime?string("YYYY-MM-dd hh:mm:ss")}</span><span>作者：${articleInfo.author}</span><span>浏览量：${articleInfo.readcount}</span></div>
						<div class="article-detail-content " style="word-break:break-all">${articleInfo.html}</div>
						<div class="article-detail-sign">
							<div class="ds-reward-stl" style="margin-left: 50%">
								<button id="dsRewardBtn" onclick="PaymentUtils.show();">赏</button>
							</div>
							<hr>
							<p>出自：彭江毅博客</p>
							<p>地址：
								<a href="#" target="_blank">pengjiangyi.com</a>
							</p>
							<p style="color: red;">如若转载请注明出处!</p>
						</div>
					</div>

					<!-- 评论区域 -->
					<div class="blog-module shadow article-comment" style="box-shadow: 0 1px 8px #a6a6a6;">
						<fieldset>
							<legend style="text-align: center;">Leave a message</legend>
							
							
							<div style="text-align: left;border-bottom: 1px dashed #DDDDDD;margin-bottom: 20px;">
											<!-- 加载编辑器的容器 -->
											<script id="container" name="content" type="text/plain" class="text-left">

											</script>
											<button type="button" class="btn btn-success" style="margin: 10px 10px;width: 100px;" onclick="rootComment(this)">提交留言</button>

										</div>

										<div style="text-align:left;padding:0px 0px 20px 30px;border-bottom:1px dashed #DDDDDD;font-size:16px"><span class="comments-num">${commentCount}</span>条评论</div>

										<div id="normal-comments-list" class="normal-comments-list" style="text-align:left">
											<#list list as root>
												<div class="comment-item">
													<div class="comment-head">
														<div class="author">
															<div class="v-tooltip-container" style="z-index: 0;">
																<div class="v-tooltip-content">
																	<a href="" class="avatar">
																		<img src="resources/img/user/${root.user.url}" class="badge-icon" />
																	</a>
																</div>
															</div>
															<div class="info">
																<a href="" class="username">${root.user.username}</a>
																<span style="display:none" class="userid">${root.id}</span>
																<span class="author-tag">
									<#if root.user.email=="741506070@qq.com">
									博主
									<#else>用户
									</#if>
									</span>
																<div class="meta">
																	<span><span class="floor">${root_index?if_exists+1}</span>楼:${root.time?string("yyyy-MM-dd HH:mm:ss")}</span>
																</div>
															</div>
														</div>
														<div class="comment-wrap">
															<p>${root.text}</p>
															<div class="tool-group">
																
																<a  href="javascript:void(0)" onclick="firstComment(this)"><i class="fa fa-comment-o"></i><span>回复</span></a>
																<a class="report"><i></i><span>举报</span></a>
															</div>
														</div>
													</div>
													<#if root.children??&&(root.children?size>0)>
														<div class="sub-comment-list ">
															<#list root.children! as child>
																<div class="sub-comment">
																	<p>
																		<div class="v-tooltip-container" style="z-index: 0;">
																			<div class="v-tooltip-content">
																			<a href="" class="avatar">
																		<img src="resources/img/user/${child.user.url}" class="badge-icon" />
																	</a>
																
																				<a class="username">${child.user.username}</a>:
																			</div>
																		</div>
																		<span>
								<a>${child.parentname}</a>
								${child.text}
							</span>
																	</p>
																	<div class="sub-tool-group">
																		<span>${child.time?string("yyyy-MM-dd HH:mm:ss")}</span>
																		<a href="javascript:void(0)" onclick="secondComment(this)">
																			<i class="fa fa-comment-o"></i><span>回复</span>
																		</a>
																		<a class="report">
																			<i></i>
																		</a>
																	</div>
																</div>
															</#list>
														</div>
														<#else>
															<div class="sub-comment-list ">
															</div>
													</#if>
												</div>
											</#list>
										</div>

										<!--PC和WAP自适应版-->
							
							
							
							
							
							
							
							
							
							
							
							
							
							

							<!--PC和WAP自适应版-->
						</fieldset>

					</div>

				</div>

				<!--右边小栏目-->
				<div class="blog-main-right col-md-4 col-sm-12">

					<!-- 搜索 -->
						<div class="input-group search shadow ">
						<input type="text" class="form-control" id="input-keywords" placeholder="文章搜索(回车键可以直接查找)">
						<span class="input-group-btn">
      				  	<button class="btn btn-default " id="input-search" onclick="search()" type="button">Go!</button>
      					</span>
					</div>

					<!--标签-->
					<div class="blog-module shadow ">
						<div class="blog-module-title">
							<i class="fa fa-file-text-o"></i>&nbsp;标签
						</div>
						<div class="tag-container">
							
							<#list tags as item>
							<a href="${request.contextPath}/query/tag/${item.name}/1.html"><span>${item.name}(${item.num})</span></a>
							
							</#list>
							
							
						</div>

					</div>

					
					<!--	点击排行-->
					<div class="blog-module shadow ">
						<div class="blog-module-title">
							<i class="fa fa-file-text-o"></i>&nbsp;点击排行
						</div>
						<div class="click-sort">
							<div class="list-group">
							
									<#list articleRank as item>
										<a href="${request.contextPath}/get-article-detail-${item.id}.html" class="list-group-item">
										<em></em>${item.title}<span class="badge">${item.readcount}</span>
										</a>
									</#list>
								
							</div>

						</div>
					</div>

					<!--最新文章-->
					<div class="blog-module shadow ">
						<div class="blog-module-title">
							<i class="fa fa-file-text-o"></i>&nbsp;最新文章
						</div>
						<div class="new-article">
							<div class="list-group">
							
									<#list articleNew as item>
										<a href="${request.contextPath}/get-article-detail-${item.id}.html" class="list-group-item" data-toggle="tooltip" data-placement="left"
       								 		title="${item.title}">
											${item.title}
										</a>
									</#list>
							
								
							
							</div>

						</div>
					</div>

					<!--最新评论-->
					<div class="blog-module shadow ">
						<div class="blog-module-title">
							<i class="fa fa-file-text-o"></i>&nbsp;最新评论
						</div>
						<div class="side-comments">
							<div class="list-group">
							
							<#list commentsNew as item>
										<a href="${request.contextPath}/get-article-detail-${item.articleid}.html" class="list-group-item"  >
											<img src="resources/img/user/${item.user.url}" class="user-img"> ${item.user.username}:${item.text}
											
										</a>
								</#list>
							
							
							</div>

						</div>
					</div>

				</div>
			</div>
		</div>

		<!-- 底部 -->
		<#include "foot.ftl">

		<div class="scroll">
			<div class="go" id="gotop"><i class="fa fa-chevron-up"></i></div>
			<div class="go" id="gocomments"><i class="fa fa-edit"></i></div>
			<div class="go" id="godown"><i class="fa fa-chevron-down"></i></div>
		</div>

		</div>

		<script src="${request.contextPath}/resources/assets/jquery-3.2.1/jquery-3.2.1.min.js"></script>
		<script src="${request.contextPath}/resources/assets/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
		<!-- 配置文件 -->
		<script type="text/javascript" src="${request.contextPath}/resources/utf8-jsp/ueditor.config.js"></script>
		<!-- 编辑器源码文件 -->
		<script type="text/javascript" src="${request.contextPath}/resources/utf8-jsp/ueditor.all.min.js"></script>
		<!-- 实例化编辑器 -->
		<script type="text/javascript">
			var ue = UE.getEditor('container', {
				toolbars: [
					['bold', 'italic', 'underline', 'forecolor', 'removeformat', 'formatmatch',
						'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|',
						 'emotion'
					]
				],

				autoHeightEnabled: false,
				autoFloatEnabled: false,
				initialFrameHeight: 200,
				initialFrameWidth: null

			});
		</script>
		<script src="${request.contextPath}/resources/assets/layer-v3.1.1/layer/layer.js"></script>
		
		<script src="${request.contextPath}/resources/js/pay.js"></script>
		
	</body>

</html>
<script>

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
</script>

<script>
$(".article-active").addClass("active")
var base="${request.contextPath}";
var user="${Session.user!}";
var userid="${Session.userid!}";
var username="${Session.username!}";
var articleid="${articleInfo.id!}";
var logoUrl='${Session.url!}';
$(".login").attr("href",base+"/login.html?from="+encodeURIComponent(location.href));
</script>




<script>
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
					</script>


<script>
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


</script>













