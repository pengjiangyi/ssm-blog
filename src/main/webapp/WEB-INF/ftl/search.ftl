<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<meta http-equiv="Content-Language" content="zh-CN">
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<title>博客检索专栏</title>
		<!--bootstrap的css-->
		<link rel="SHORTCUT ICON" href="${request.contextPath}/resources/img/favicon.ico">
		<link rel="stylesheet" href="${request.contextPath}/resources/assets/bootstrap-3.3.7-dist/css/bootstrap.min.css" />
		<!--font-awesome-->
		<link href="${request.contextPath}/resources/assets/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
		<!--全局样式-->
		<link rel="stylesheet" href="${request.contextPath}/resources/css/global.css" />
		<!--动画效果css-->
		<link href="${request.contextPath}/resources/css/animate.css" rel="stylesheet" />
		
			<link rel="stylesheet" href="${request.contextPath}/resources/css/page.css" />

	</head>
	<style>
	.article-item {
			margin-bottom: 20px;
			font-size: 13px;
			font-family: Arial;
			margin-top: 30px;
		}
		
		.article-title a {
			color: rgb(0, 0, 204);
			font-size: 16px;
			text-decoration: underline;
		}
		
		.article-desc {
			margin: 5px 0;
			color: #333;
		}
		
		.article-link {
			color: rgb(0, 128, 0);
		}
		
		.no-result-tip {
			font-size: 18px;
			font-family: microsoft yahei;
			color: #000;
			margin: 100px 0;
		}
		.blog-main{
		min-height:420px
		}
		
	</style>

	<body>
		<#include "head.ftl">

		<!-- 主体（一般只改变这里的内容） -->
		<div class="blog-body ">
			<!-- 这个一般才是真正的主体内容 -->
			<div class="blog-container ">
				<ol class="breadcrumb ">
					<li>
						<a href="${request.contextPath}/">网站首页</a>
					</li>
					<li>
						<a class="active ">博文检索</a>
					</li>
				
				</ol>

				<div class="blog-main ">
					
					<div class="input-group search col-md-8">
						<input type="text" class="form-control" id="input-keywords" placeholder="文章搜索(回车键可以直接查找)">
						<span class="input-group-btn">
      				  	<button class="btn btn-default " id="input-search" onclick="search()" type="button">Go!</button>
      					</span>
					</div>
					<div class="top-counter ">
						为您找到相关结果约${pageBean.totalRecord!}个，服务器一共耗时${time}

					</div>
					<#if pageBean.list??&&(pageBean.list?size>0)>
					<div class="article-list col-md-8">
						<#list pageBean.list! as article>
						<article class="article-item">
							<div class="article-title">
								<a href="${request.contextPath}/get-article-detail-${article.id}.html" target="_blank">${article.title!}</a>
							</div>
							<div class="article-desc">
								${article.text!}
							</div>
							<div class="article-link">
								<a href="${request.contextPath}/get-article-detail-${article.id}.html" target="_blank">${request.contextPath}/get-article-detail-${article.id}.html</a>
								<a>快照</a>

							</div>
						</article>
						
							
					
						</#list>
						<div id="div_pager" style="margin-bottom:20px">
							</div>
					</div>
					
					
					
					<#else>
					<div class="no-result-tip col-md-8">
						对不起，没有找到与<span style="color: red;">${keywords}</span>相关结果，请更换搜索词再试。

					</div>
					</#if>
					
					
				
					
					
					
					
					
					
					
					
					
					
				<div class="clear "></div>
			</div>
		</div>
		<!-- 底部 -->
		<#include "foot.ftl">
		
		<div style="display:none" id="total">${pageBean.totalRecord!}</div>
		<div style="display:none" id="pages">${pageBean.totalPage!}</div>
		<div style="display:none" id="pageNum">${pageBean.pageNum!}</div>
		<div style="display:none" id="keywords">${keywords!}</div>

		<!-- 本页脚本 -->

		<script src="${request.contextPath}/resources/assets/jquery-3.2.1/jquery-3.2.1.min.js "></script>
		<script src="${request.contextPath}/resources/assets/bootstrap-3.3.7-dist/js/bootstrap.min.js "></script>
			<script src="${request.contextPath}/resources/assets/layer-v3.1.1/layer/layer.js"></script>
		<!-- 全局脚本 -->
	
		<script src="${request.contextPath}/resources/js/global.js "></script>
		<script src="${request.contextPath}/resources/js/page.js"></script>
	</body>

</html>

<script>
$(".article-active").addClass("active")
var base="${request.contextPath}";
$(".login").attr("href",base+"/login.html?from="+encodeURIComponent(location.href));
</script>
<script src="${request.contextPath}/resources/js/search.js"></script>
