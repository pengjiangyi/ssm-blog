<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<meta http-equiv="Content-Language" content="zh-CN">
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<title>关于我</title>
		<!--bootstrap的css-->
		<link rel="stylesheet" href="${request.contextPath}/resources/assets/bootstrap-3.3.7-dist/css/bootstrap.min.css" />
		<link rel="SHORTCUT ICON" href="${request.contextPath}/resources/img/favicon.ico">
		<link href="${request.contextPath}/resources/css/bootstrapValidator.css" rel="stylesheet">

		<!--font-awesome-->
		<link href="${request.contextPath}/resources/assets/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
		<!--全局样式-->
		<link rel="stylesheet" href="${request.contextPath}/resources/css/global.css" />
		<!--动画效果css-->
		<link href="${request.contextPath}/resources/css/animate.css" rel="stylesheet" />
		<!--本页样式-->
		<link rel="stylesheet" href="${request.contextPath}/resources/css/about.css" />
		<link rel="stylesheet" href="${request.contextPath}/resources/css/comment.css" />
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
							<a href="#" class="active ">关于</a>
						</li>

					</ol>

					<div class="blog-main">
						<ul id="Tab" class="nav nav-tabs">

							<li class="active">
								<a href="#blog" data-toggle="tab"> 关于博客</a>
							</li>
							<li>
								<a href="#author" data-toggle="tab">关于作者</a>
							</li>
							<li>
								<a href="#link" data-toggle="tab">友情链接</a>
							</li>

							<li>
								<a href="#message" data-toggle="tab">留言墙</a>
							</li>
						</ul>

						<div id="TabContent" class="tab-content">
							<div class="tab-pane fade in active" id="blog">
								<div class="aboutinfo">

									<div class="aboutinfo-figure">
										<img src="${request.contextPath}/resources/img/weixin.png" alt="博客" width="120px" height="120px" />
									</div>
									<p class="aboutinfo-introduce">一个JAVA程序员的个人博客，记录博主学习和成长之路，分享生活点点滴滴</p>
									<p class="aboutinfo-location">
										<i class="fa fa-link"></i>&nbsp;&nbsp;
										<a target="_blank" href="#">www.pengjiangyi.com</a>
									</p>
									<hr />
									<div class="aboutinfo-contact">
										<a title="网站首页" href="index.html"><i class="fa fa-home fa-2x" style="font-size: 2.5em; position: relative; top: 3px"></i> </a>
										<a title="文章专栏" href="article.html"><i class="fa fa-file-text fa-2x"></i> </a>
										<a title="杂七杂八" href="pic.html"><i class="fa fa-paper-plane-o fa-2x"></i> </a>
										<a title="点点滴滴" href="timeline.html"><i class="fa fa-hourglass-half fa-2x"></i> </a>
									</div>

									<fieldset class="">
										<legend>简介</legend>
										<div class="aboutinfo-abstract">
											<p style="text-align: center;">由JAVA开发的个人博客网站，诞生于2017年9月16日，迄今为止经历了一次大改，暂且称为博客2.0。</p>
											<h1>第一个版本</h1>
											<p>诞生的版本，采用Java mvc作为后台框架，前端几乎自己手写，用了纯DIV布局！起初并没有注意美工，只打算完成基本的功能，故视觉体验是比较差的。
											</p>
											<h1>第二个版本</h1>
											<p>bootstrap简洁的风格让我很喜欢,于是决定给我的网站改版!此次改版从里到外几乎全部更新。后台所有的设计,全部重新推翻重新,才有了现在这个版本。样式显然比之前高出很多</p>
											<h1>当前版本</h1>
											<p>发布了这个版本，首先得感谢bootstrap作者贤心,不管是文笔,还是代码水平,都让我很佩服,也感谢,个人在bootstrap中提交了案例的小伙伴,才让我有了,开发这个版本的想法</p>
											<h1 style="text-align: center;">The End</h1>
										</div>
									</fieldset>
								</div>
							</div>
							<div class="tab-pane fade" id="author">
								<div class="aboutinfo">
									<div class="aboutinfo-figure">
										<img src="${request.contextPath}/resources/img/admin.jpg" alt="Absolutely" width="100px" height="100px" />
									</div>
									<p class="aboutinfo-nickname">彭江毅</p>
									<p class="aboutinfo-introduce">一枚95后程序员，Java开发工程师，主攻后端，略懂Web前端</p>
									<p class="aboutinfo-location">
										<i class="fa fa-location-arrow"></i>&nbsp;深圳
									</p>
									<hr />
									<div class="aboutinfo-contact">
										<a target="_blank" title="QQ交流" href="#"><i class="fa fa-qq fa-2x"></i> </a>
										<a target="_blank" title="给我写信" href="#"><i class="fa fa-envelope fa-2x"></i> </a>
										<a target="_blank" title="新浪微博" href="#"><i class="fa fa-weibo fa-2x"></i> </a>
										<a target="_blank" title="GitHub" href="#"><i class="fa fa-git fa-2x"></i> </a>
									</div>
									<fieldset class="layui-elem-field layui-field-title">
										<legend>简介</legend>
										<div class="layui-field-box aboutinfo-abstract abstract-bloger">
											<p style="text-align: center;">彭江毅，博客创始人,湖南常德人,目前是一个码农，从事Java开发。</p>
											<h1>个人信息</h1>
											<p>暂无</p>
											<h1>个人介绍</h1>
											<p>暂无</p>
											<h1>未来</h1>
											<p>还没想好</p>
											<h1 style="text-align: center;">The End</h1>
										</div>
									</fieldset>
								</div>
							</div>
							<div class="tab-pane fade" id="link">
								<div class="aboutinfo">
									<div class="aboutinfo-figure">
										<img src="${request.contextPath}/resources/img/handshake.png" alt="友情链接" />
									</div>
									<p class="aboutinfo-nickname">友情链接</p>
									<p class="aboutinfo-introduce">Name：彭江毅的博客&nbsp;&nbsp;&nbsp;&nbsp;Site：pengjiangyi.com</p>
									<p class="aboutinfo-location">
										<i class="fa fa-close"></i>经常宕机&nbsp; <i class="fa fa-close"></i>不合法规&nbsp;
										<i class="fa fa-close"></i>插边球站&nbsp; <i class="fa fa-close"></i>红标报毒&nbsp;
										<i class="fa fa-check"></i>原创优先&nbsp; <i class="fa fa-check"></i>技术优先
									</p>
									<hr />
									<div class="aboutinfo-contact">
										<p style="font-size: 2em;">互换友链，携手并进！</p>
									</div>
									<fieldset>
										<legend>Friend Link</legend>
										<div>
											<!-- class都是bootstrap定义好的样式，验证是根据input中的name值 -->
											<form id="defaultForm" class="form-horizontal">
												<!-- 下面这个div必须要有，插件根据这个进行添加提示 -->
												<div class="form-group">
													<label class="col-lg-3 control-label">网站名称</label>
													<div class="col-lg-8">
														<select class="form-control" name="category" id="category">
															<option value="0">请选择</option>
															<option value="论坛博客">论坛博客</option>
															<option value="休闲娱乐">休闲娱乐</option>
															<option value="生活服务">生活服务</option>
															<option value="教育文化">教育文化</option>
															<option value="网上商城">网上商城</option>
															<option value="综合其它">综合其它</option>
														</select>
													</div>
												</div>

												<div class="form-group">
													<label class="col-lg-3 control-label">网站名称</label>
													<div class="col-lg-8">
														<input type="text" class="form-control" name="webname" id="webname" />
													</div>
												</div>

												<div class="form-group">
													<label class="col-lg-3 control-label">网站地址</label>
													<div class="col-lg-8">
														<input type="text" class="form-control" name="weburl" id="weburl" />
													</div>
												</div>

												<div class="form-group">
													<label class="col-lg-3 control-label">联系人</label>
													<div class="col-lg-8">
														<input type="text" class="form-control" name="username" id="username" />
													</div>
												</div>

												<div class="form-group">
													<label class="col-lg-3 control-label">联系人电话</label>
													<div class="col-lg-8">
														<input type="text" class="form-control" name="telphone" id="telphone" />
													</div>
												</div>

												<div class="form-group">
													<label class="col-lg-3 control-label">简介</label>
													<div class="col-lg-8">
														<textarea class="form-control" name="content" id="content"></textarea>
													</div>
												</div>

												<div class="form-group">
													<div class="col-lg-9 col-lg-offset-4">
														<button type="button" id="subLink" class="btn btn-primary">提交申请</button>
													</div>
												</div>
											</form>

										</div>
									</fieldset>
								</div>
							</div>

							<div class="tab-pane fade" id="message">
								<div class="aboutinfo">
									<div class="aboutinfo-head">
										<div class="aboutinfo-figure">
											<img src="${request.contextPath}/resources/img/messagewall.png" alt="留言墙" />
										</div>
										<p class="aboutinfo-nickname">留言墙</p>
										<p class="aboutinfo-introduce">本页面可留言、吐槽、提问。欢迎灌水，杜绝广告！</p>
										<p class="aboutinfo-location">
											<i class="fa fa-clock-o"></i>&nbsp;<span id="time"></span>
										</p>
										<hr />
										<div class="aboutinfo-contact">
											<p style="font-size: 2em;">沟通交流，拉近你我！</p>
										</div>
									</div>
									<fieldset>
										<legend style="text-align: center;">Leave a message</legend>
										<div style="text-align: left; border-bottom: 1px dashed #DDDDDD; margin-bottom: 20px;">
											<!-- 加载编辑器的容器 -->
											<script id="container" name="content" type="text/plain" class="text-left">

											</script>
											<button type="button" class="btn btn-success" style="margin: 10px 10px; width: 100px;" onclick="rootComment(this)">提交留言</button>

										</div>

										<div style="text-align: left; padding: 0px 0px 20px 30px; border-bottom: 1px dashed #DDDDDD; font-size: 16px">
											<span class="comments-num">${messageCount}</span>条留言
										</div>

										<div id="normal-comments-list" class="normal-comments-list" style="text-align: left">
											<#list list as root>
												<div class="comment-item">
													<div class="comment-head">
														<div class="author">
															<div class="v-tooltip-container" style="z-index: 0;">
																<div class="v-tooltip-content">
																	<a href="" class="avatar"> <img src="resources/img/user/${root.user.url}" class="badge-icon" />
																	</a>
																</div>
															</div>
															<div class="info">
																<a href="" class="username">${root.user.username}</a> <span style="display: none" class="userid">${root.id}</span> <span class="author-tag"> ${root.user.level} </span>
																<div class="meta">
																	<span><span class="floor">${root_index?if_exists+1}</span>楼:${root.time?string("yyyy-MM-dd HH:mm:ss")}
																	</span>
																</div>
															</div>
														</div>
														<div class="comment-wrap">
															<p>${root.text}</p>
															<div class="tool-group">

																<a href="javascript:void(0)" onclick="firstComment(this)"><i class="fa fa-comment-o"></i><span>回复</span></a>
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

																				<a href="" class="avatar"> <img src="resources/img/user/${child.user.url}" class="badge-icon" />
																				</a>
																				<a class="username"> ${child.user.username}</a>:
																			</div>
																		</div>
																		<span> <a>${child.parentname}</a> ${child.text}
												</span>
																	</p>
																	<div class="sub-tool-group">
																		<span>${child.time?string("yyyy-MM-dd HH:mm:ss")}</span>
																		<a href="javascript:void(0)" onclick="secondComment(this)">
																			<i class="fa fa-comment-o"></i><span>回复</span>
																		</a>
																		<a class="report"> <i></i>
																		</a>
																	</div>
																</div>
															</#list>
														</div>
														<#else>
															<div class="sub-comment-list "></div>
													</#if>
												</div>
											</#list>
										</div>

										<!--PC和WAP自适应版-->
									</fieldset>
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
					<script src="${request.contextPath}/resources/js/bootstrapValidator.js"></script>
					<!-- 全局脚本 -->
					<script src="${request.contextPath}/resources/js/global.js "></script>
					<script src="${request.contextPath}/resources/assets/layer-v3.1.1/layer/layer.js"></script>
					<script>
						$(".about-active").addClass("active")
						var user = "${Session.user!}";
						var userid = "${Session.userid!}";
						var username = "${Session.username!}";
						var base = "${request.contextPath}"
						$(".login").attr(
							"href",
							base + "/login.html?from=" +
							encodeURIComponent(location.href));
						var logoUrl = '${Session.url!}';
					</script>
					<!-- 配置文件 -->
					<script type="text/javascript" src="${request.contextPath}/resources/utf8-jsp/ueditor.config.js"></script>
					<!-- 编辑器源码文件 -->
					<script type="text/javascript" src="${request.contextPath}/resources/utf8-jsp/ueditor.all.min.js"></script>
					<!-- 实例化编辑器 -->
					<script type="text/javascript">
						//编辑器资源文件根路径 最好在ueditor.config.js中配置
						//window.UEDITOR_HOME_URL = "/ueditor/";

						var ue = UE.getEditor('container', {
							toolbars: [
								['emotion', 'bold', 'italic', 'underline',
									'forecolor', 'removeformat', 'formatmatch',
									'justifyleft', 'justifycenter', 'justifyright',
									'justifyjustify', '|'

								]
							],

							autoHeightEnabled: false,
							autoFloatEnabled: false,
							initialFrameHeight: 200,
							initialFrameWidth: null

						});
					</script>
					<script src="${request.contextPath}/resources/js/about.js"></script>
					
	</body>

</html>