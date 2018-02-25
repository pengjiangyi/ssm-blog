<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<meta http-equiv="Content-Language" content="zh-CN">
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<title>博客历程</title>
		<link rel="SHORTCUT ICON" href="${request.contextPath}/resources/img/favicon.ico">
		<!--bootstrap的css-->
		<link rel="stylesheet" href="${request.contextPath}/resources/assets/bootstrap-3.3.7-dist/css/bootstrap.min.css" />
		<!--font-awesome-->
		<link href="${request.contextPath}/resources/assets/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
		<!--全局样式-->
		<link rel="stylesheet" href="${request.contextPath}/resources/css/global.css" />
		<!--动画效果css-->
		<link href="${request.contextPath}/resources/css/animate.css" rel="stylesheet" />
		<!--本页样式-->
		<link rel="stylesheet" href="${request.contextPath}/resources/css/timeline.css" />

	</head>

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
						<a href="timeline.html " class="active ">博客历程</a>
					</li>
				
				</ol>

				<div class="blog-main ">
					<!--  <div class="child-nav shadow ">
	                <span class="child-nav-btn child-nav-btn-this ">时光轴</span>
	                <span class="child-nav-btn " onclick="location.href='#' ">待开发</span>
                </div> -->
					<div class="timeline-box shadow ">
						<div class="timeline-main ">
							<h1><i class="fa fa-clock-o "></i>时光轴<span> —— 记录生活点点滴滴</span></h1>
							<div class="timeline-line "></div>
							<div class="timeline-data ">

							</div>
							<h1 style="padding-top:4px;padding-bottom:2px;margin-top:40px; "><i class="fa fa-hourglass-end "></i>THE END</h1>
						</div>
					</div>
				</div>
				<div class="clear "></div>
			</div>
		</div>
		<!-- 底部 -->
		<#include "foot.ftl">

		<!-- 本页脚本 -->

		<script src="${request.contextPath}/resources/assets/jquery-3.2.1/jquery-3.2.1.min.js "></script>
		<script src="${request.contextPath}/resources/assets/bootstrap-3.3.7-dist/js/bootstrap.min.js "></script>
		<!-- 全局脚本 -->
	
		<script src="${request.contextPath}/resources/js/global.js "></script>
		<script src="${request.contextPath}/resources/js/timeline.js "></script>
	</body>

</html>

<script>
$(".timeline-active").addClass("active")
var base="${request.contextPath}";
$(".login").attr("href",base+"/login.html?from="+encodeURIComponent(location.href));
</script>
