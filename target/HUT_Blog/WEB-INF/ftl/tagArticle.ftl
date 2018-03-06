<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<meta http-equiv="Content-Language" content="zh-CN">
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<title>文章标签分类</title>
		<!--bootstrap的css-->
<link rel="SHORTCUT ICON" href="${request.contextPath}/resources/img/favicon.ico">
		<!--动画效果css-->
		<link href="${request.contextPath}/resources/css/animate.css" rel="stylesheet" />
		<!--font-awesome-->
		<link href="${request.contextPath}/resources/assets/font-awesome/css/font-awesome.min.css" rel="stylesheet" />

		<link rel="stylesheet" href="${request.contextPath}/resources/assets/bootstrap-3.3.7-dist/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${request.contextPath}/resources/css/global.css" />
		<link rel="stylesheet" href="${request.contextPath}/resources/css/index.css" />
		<link rel="stylesheet" href="${request.contextPath}/resources/css/page.css" />
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
					<a href="#" class="active ">文章标签分类</a>
				</li>

			</ol>

			
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
									<i class="fa fa-tag"></i>&nbsp;&nbsp;<a href="${request.contextPath}/query/tag/${article.tag.name!}/1.html">${article.tag.name!} </a> </span>
							<span class="article-viewinfo"><i class="fa fa-eye"></i>&nbsp;${article.readcount!}</span>
							<span class="article-viewinfo"><i class="fa fa-commenting"></i>&nbsp;${article.count!}</span>
						</div>
					</div>	
					
					
			
			</#list>	
			
			<div id="div_pager" style="margin-bottom:60px">
			</div>
						
					

				</div>

					<!--右边小栏目-->
				<div class="blog-main-right col-md-4 col-sm-12">

					<!-- 搜索 -->
				
					
					<div class="input-group search shadow animated fadeInRight">
						<input type="text" class="form-control" id="input-keywords" placeholder="文章搜索(回车键可以直接查找)">
						<span class="input-group-btn">
      				  	<button class="btn btn-default " id="input-search" onclick="search()" type="button">Go!</button>
      					</span>
					</div>
					
					
					
					
					
					

					<!--标签-->
					<div class="blog-module shadow animated fadeInRight">
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
										<a href="${request.contextPath}/get-article-detail-${item.id}.html" class="list-group-item" data-toggle="tooltip" data-placement="left"
       								 		title="${item.title}">
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
											<img src="${request.contextPath}/resources/img/user/${item.user.url}" class="user-img"> ${item.user.username}:${item.text}
											
										</a>
								</#list>
							
							
							</div>

						</div>
					</div>

				</div>
			</div>
		</div>

		<#include "foot.ftl">
		<div style="display:none" id="total">${article.total!}</div>
		<div style="display:none" id="pages">${article.pages!}</div>
		<div style="display:none" id="pageNum">${article.pageNum!}</div>
		<div style="display:none" id="tagname">${tagname!}</div>
		<script src="${request.contextPath}/resources/assets/jquery-3.2.1/jquery-3.2.1.min.js"></script>
		<script src="${request.contextPath}/resources/assets/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
		<script src="${request.contextPath}/resources/js/page.js"></script>
			<script src="${request.contextPath}/resources/assets/layer-v3.1.1/layer/layer.js"></script>
	</body>

</html>
<script>
var base="${request.contextPath}";
$(".article-active").addClass("active")
</script>
<script src="${request.contextPath}/resources/js/tagArticle.js"></script>