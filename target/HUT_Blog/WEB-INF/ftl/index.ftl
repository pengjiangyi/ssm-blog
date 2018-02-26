<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<meta http-equiv="Content-Language" content="zh-CN">
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<title>彭江毅的个人博客,java攻城狮的聚集地</title>
		<meta name="description" content="彭江毅的个人博客" />
		<meta name="keywords" content="个人博客,个人博客网站,个人网站制作,个人博客网站制作,个人网站欣赏,个人博客模板,java面试题,java教程,彭江毅个人博客,彭江毅,SSM框架，SSH框架，java基础" />
		<!--bootstrap的css-->
		<link rel="SHORTCUT ICON" href="${request.contextPath}/resources/img/favicon.ico">
		<!--动画效果css-->
		<link href="${request.contextPath}/resources/css/animate.css" rel="stylesheet" />
		<!--font-awesome-->
		<link href="${request.contextPath}/resources/assets/font-awesome/css/font-awesome.min.css" rel="stylesheet" />

		<link rel="stylesheet" href="${request.contextPath}/resources/assets/bootstrap-3.3.7-dist/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${request.contextPath}/resources/css/global.css" />
		<link rel="stylesheet" href="${request.contextPath}/resources/css/index.css" />
	</head>

	<body>
		<#include "head.ftl">
		<!--轮播图开始-->
		<div class="container">

			<div id="blog-carousel" class="carousel slide" data-ride="carousel">
				<!-- 轮播（Carousel）指标 -->
				<ol class="carousel-indicators">
				
					<#list carousel as item>
				
					<li data-target="#blog-carousel" data-slide-to="${item_index}" class="index"></li>
				
				
				</#list>
					
					
				</ol>
				<!-- 轮播（Carousel）项目 -->
				<div class="carousel-inner">
				
				<#list carousel as item>
				
					<div class="item">
						<img src="${item.url}" alt="${item.content}">
						<div class="carousel-caption">${item.name}</div>
					</div>
				
				
				
				</#list>
					
					
				</div>

			</div>

		</div>

		<!--轮播图结束-->
		<!--网站主体-->
		<div class="container blog-main">

			<div class="home-tips shadow">
				<i style="float:left;line-height:17px;" class="fa fa-volume-up"></i>
				<div class="home-tips-container">
						
					<#list information as item>
						<span>${item.text}</span>
					</#list>	
					
					

				</div>
			</div>
			<div class="row">
				<!--左边文章列表-->
				<div class="blog-main-left animated slideInLeft col-md-8 col-sm-12">
						
			<#list article.list as article>
				<div class="article shadow animated fadeInLeft">
						<div class="article-left ">
							<img src="${article.imgurl}">
						</div>
						<div class="article-right">
							<div class="article-title">
								<a href="${request.contextPath}/get-article-detail-${article.id}.html">${article.title!}</a>
							</div>
							<div class="article-abstract">
								${article.content!}
							</div>
						</div>
						<div class="clear"></div>
						<div class="article-footer">
							<span><i class="fa fa-clock-o"></i>&nbsp;&nbsp;${article.createtime?string("yyyy-MM-dd HH:mm:ss")}</span><span class="article-author" data-toggle="tooltip" title="作者">
									<i class="fa fa-user"></i>&nbsp;&nbsp; ${article.author!}</span> <span data-toggle="tooltip" title="标签">
									<i class="fa fa-tag"></i>&nbsp;&nbsp;<a href="${request.contextPath}/query/tag/${article.tag.name}/1.html">${article.tag.name!} </a> </span>
							<span class="article-viewinfo"><i class="fa fa-eye"></i>&nbsp;${article.readcount!}</span>
							<span class="article-viewinfo"><i class="fa fa-commenting"></i>&nbsp;${article.count!}</span>
						</div>
					</div>	
					
					
			
			</#list>	
						
					

				</div>

				<!--右边小栏目-->
				<div class="blog-main-right col-md-4 col-sm-12">

					<!-- 小编信息 -->
					<div class="blogerinfo shadow animated fadeInRight">
						<div class="blogerinfo-figure">
							<img src="${request.contextPath}/resources/img/weixin.png" alt="" />
						</div>
						<p class="blogerinfo-nickname">彭江毅</p>
						<p class="blogerinfo-introduce">一枚95后程序员，java开发工程师</p>
						<p class="blogerinfo-location">
							<i class="fa fa-location-arrow"></i>&nbsp;中国 - 深圳
						</p>
						<hr />
						<div class="blogerinfo-contact">
							<a target="_blank" title="741506070" href="#" data-toggle="tooltip" data-placement="top"
       								 		><i class="fa fa-qq fa-2x"></i>
							</a>
							<a target="_blank" title="java@pengjiangyi.com" href="#"  data-toggle="tooltip" data-placement="top"><i class="fa fa-envelope fa-2x"></i>
							</a>
							<a target="_blank" title="新浪微博" href="#" data-toggle="tooltip" data-placement="top"><i class="fa fa-weibo fa-2x"></i>
							</a>
							<a target="_blank" title="GitHub" href="#" data-toggle="tooltip" data-placement="top"><i class="fa fa-git fa-2x"></i>
							</a>
						</div>
					</div>

					<!--网站数据-->
					<div class="blog-module shadow animated fadeInRight">
						<div class="blog-module-title">
							<i class="fa fa-file-text-o"></i>&nbsp;网站统计
						</div>

						<div class="data-container">
							<ul class="list-group">
								<li class="list-group-item">文章总数<span class="badge">${articleCount}</span></li>
								<li class="list-group-item">评论总数<span class="badge">${commentsCount}</span></li>
								<li class="list-group-item">IP总数<span class="badge">${ipCount}</span></li>
								<li class="list-group-item">建站时间<span class="badge">2018-1-13</span></li>
								<li class="list-group-item">用户人数<span class="badge">${userCount}</span></li>
							</ul>

						</div>

					</div>

					<!--	点击排行-->
					<div class="blog-module shadow animated fadeInRight">
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
					<div class="blog-module shadow animated fadeInRight">
						<div class="blog-module-title">
							<i class="fa fa-file-text-o"></i>&nbsp;最新文章
						</div>
						<div class="new-article">
							<div class="list-group">
							
									<#list articleNew as item>
										<a href="${request.contextPath}/get-article-detail-${item.id}.html" class="list-group-item" 
       								 		>
											${item.title}
										</a>
									</#list>
							
								
							
							</div>

						</div>
					</div>

					<!--最新评论-->
					<div class="blog-module shadow animated fadeInRight">
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

					<!-- 个性签名 -->
					<div class="blog-module shadow animated fadeInRight">
						<div class="blog-module-title">
							<i class="fa fa-file-text-o"></i>&nbsp;个性签名
						</div>
						<p class="contentMood">
							<!-- 个人签名。&nbsp;&nbsp;&nbsp;--Paul
							Graham -->
							成功＝艰苦的劳动＋正确的方法＋少说空话
							<p align="right">---爱因斯坦</p>
							<p>
					</div>

					<!-- 广告 -->
					<div></div>

					<!-- 友情链接 -->
					<div class="blog-module shadow animated fadeInRight">
						<div class="blog-module-title">
							<i class="fa fa-link"></i>&nbsp;友情链接
						</div>
						<ul class="blogroll">
						
						<#list links as item>
							<li>
								<a href="${item.url}" target="_blank" data-toggle="tooltip" data-placement="top"
       								 		title="${item.url}">${item.webname}</a>
							</li>
						</#list>
				
						</ul>
					</div>

				</div>
			</div>
		</div>

		<#include "foot.ftl">
		
		

		<script src="${request.contextPath}/resources/assets/jquery-3.2.1/jquery-3.2.1.min.js"></script>
		<script src="${request.contextPath}/resources/assets/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

	</body>

</html>
<script>
var base="${request.contextPath}";
</script>
<script src="${request.contextPath}/resources/js/index.js"></script>