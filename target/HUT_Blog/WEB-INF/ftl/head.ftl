
		<!--导航栏开始-->
		<nav class="navbar navbar-default  navbar-fixed-top navbar-inverse">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#blog-collapse">
						<span class="sr-only"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					
					<a class="navbar-brand blog-logo" href="#"><img src="${request.contextPath}/resources/img/userlogo.png" width="80px"></a>
				</div>
				<div class="collapse navbar-collapse" id="blog-collapse">
					<ul class="nav navbar-nav" >
						<li class="blog-collapse-li index-active">
							<a href="${request.contextPath}/"><span class="glyphicon glyphicon-home">博客首页</span></a>
						</li>
						<li class="blog-collapse-li article-active">
							<a href="${request.contextPath}/query/article/1.html"><span class="glyphicon glyphicon-asterisk">文章首页</span></a>
						</li>

						<li class="blog-collapse-li timeline-active">
							<a href="${request.contextPath}/timeline.html"><span class="glyphicon glyphicon-signal"></span>博客历程</a>
						</li>
						<li class="blog-collapse-li photos-active">
							<a href="${request.contextPath}/query/photo/1.html"><span class="glyphicon glyphicon-picture"></span>相册</a>
						</li>
						<li class="blog-collapse-li about-active">
							<a href="${request.contextPath}/about.html"><span class="glyphicon glyphicon-user"></span>关于</a>
						</li>

					</ul>

					<ul class="nav navbar-nav navbar-right nav-avatar" >
					
						<#if Session.user?exists>
						<li class="blog-collapse-li">
							<a href="${request.contextPath}/logout" class="logout"><img src="${request.contextPath}/resources/img/user/${Session.user.url!}" class="user-img"/>注销</a>
						
						</li>
						
						<#else>
						<li class="blog-collapse-li">
							<a href="#" class="login"><i class="fa fa-user-circle-o"></i>帐号登录</a>
						</li>
						</#if>
					
						
					</ul>

				</div>
			</div>
		</nav>
		
		
		<!--导航栏结束-->