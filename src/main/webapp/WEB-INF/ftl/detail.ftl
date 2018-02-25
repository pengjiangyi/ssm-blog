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

		<link rel="stylesheet" href="${request.contextPath}/resources/css/about.css" />
				<link rel="stylesheet" href="${request.contextPath}/resources/css/detail.css" />
		<link rel="stylesheet" href="${request.contextPath}/resources/css/comment.css" />
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
$(".article-active").addClass("active")
var base="${request.contextPath}";
var user="${Session.user!}";
var userid="${Session.userid!}";
var username="${Session.username!}";
var articleid="${articleInfo.id!}";
var logoUrl='${Session.url!}';
$(".login").attr("href",base+"/login.html?from="+encodeURIComponent(location.href));
</script>
<script src="${request.contextPath}/resources/js/detail.js"></script>