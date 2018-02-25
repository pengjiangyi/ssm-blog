<!DOCTYPE html>

<html lang="en">

	<head>
		<meta charset="UTF-8" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<title>相册</title>

		
<link rel="SHORTCUT ICON" href="${request.contextPath}/resources/img/favicon.ico">
		
		<!--font-awesome-->
		<link href="${request.contextPath}/resources/assets/font-awesome/css/font-awesome.min.css" rel="stylesheet" />

		<!-- bootstrap -->
		<link rel="stylesheet" href="${request.contextPath}/resources/assets/bootstrap-3.3.7-dist/css/bootstrap.min.css" />

		<!-- animate.css -->
		<link rel="stylesheet" href="${request.contextPath}/resources/assets/animate/animate.css" />
		<link rel="stylesheet" href="${request.contextPath}/resources/assets/animate/set.css" />

		<!-- gallery -->
		<link rel="stylesheet" href="${request.contextPath}/resources/assets/gallery/blueimp-gallery.min.css">

		

		<link rel="stylesheet" href="${request.contextPath}/resources/assets/style.css">
		<link rel="stylesheet" href="${request.contextPath}/resources/css/global.css" />
	</head>
	<style>
	.blog-footer{
	position:relative;
	}
	</style>

	<body>
		<div class="topbar animated fadeInLeftBig"></div>

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
						<a href="#" class="active ">相册</a>
					</li>

				</ol>

				<div class="blog-main " style="min-height:500px">
					<!-- works -->
					<div id="works" class=" clearfix grid">
					
					<#list photo.list as item>
					
						<figure class="effect-oscar  wowload fadeIn">
							<img src="${item.url}" alt="${item.name}" />
							<figcaption>
								<h2>${item.name}</h2>
								<p>Lily likes to play with crayons and pencils<br>
									<a href="${item.url}" title="${item.name}" data-gallery>View more</a>
								</p>
							</figcaption>
						</figure>
					</#list>	

					</div>
					<!-- works -->

					<!-- The Bootstrap Image Gallery lightbox, should be a child element of the document body -->
					<div id="blueimp-gallery" class="blueimp-gallery blueimp-gallery-controls">
						<!-- The container for the modal slides -->
						<div class="slides"></div>
						<!-- Controls for the borderless lightbox -->
						<h3 class="title">Title</h3>
						<a class="prev">‹</a>
						<a class="next">›</a>
						<a class="close">×</a>
						<!-- The modal dialog, which will be used to wrap the lightbox content -->
					</div>

				</div>
			</div>
			
			<!-- 底部 -->
	<#include "foot.ftl">

			<!-- jquery -->
			<script src="${request.contextPath}/resources/assets/jquery-3.2.1/jquery-3.2.1.min.js"></script>

			<!-- wow script -->
			<script src="${request.contextPath}/resources/assets/wow/wow.min.js"></script>

			<!-- boostrap -->
			<script src="${request.contextPath}/resources/assets/bootstrap-3.3.7-dist/js/bootstrap.min.js" type="text/javascript"></script>

			<!-- jquery mobile -->
			<script src="${request.contextPath}/resources/assets/mobile/touchSwipe.min.js"></script>
			<script src="${request.contextPath}/resources/assets/respond/respond.js"></script>

			<!-- gallery -->
			<script src="${request.contextPath}/resources/assets/gallery/jquery.blueimp-gallery.min.js"></script>

			<!-- custom script -->
			<script src="${request.contextPath}/resources/assets/script.js"></script>

	</body>

</html>
<script>
$(".photos-active").addClass("active")
var base="${request.contextPath}";
$(".login").attr("href",base+"/login.html?from="+encodeURIComponent(location.href));
</script>
