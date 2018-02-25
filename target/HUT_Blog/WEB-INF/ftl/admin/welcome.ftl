<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="${request.contextPath}/resources/assets/hui-admin/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="${request.contextPath}/resources/assets/hui-admin/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="${request.contextPath}/resources/assets/hui-admin/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="${request.contextPath}/resources/assets/hui-admin/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="${request.contextPath}/resources/assets/hui-admin/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>我的桌面</title>
</head>
<body>
<div class="page-container">
	<p class="f-20 text-success">欢迎使用admin博客后台</p>
	<table class="table table-border table-bordered table-bg">
		<thead>
			<tr>
				<th colspan="7" scope="col">信息统计</th>
			</tr>
			<tr class="text-c">
				<th>统计</th>
				<th>发表文章</th>
				<th>网站评论</th>
				<th>网站留言</th>
				<th>ip访问数</th>
				<th>用户</th>
				
			</tr>
		</thead>
		<tbody>
			<tr class="text-c">
				<td>总数</td>
				<td>${articleTotal}</td>
				<td>${commentsTotal}</td>
				<td>${messageTotal}</td>
				<td>${ipTotal}</td>
				<td>${userTotal}</td>
			</tr>
			<tr class="text-c">
				<td>今日</td>
				<td>${articleToDay}</td>
				<td>${commentsToDay}</td>
				<td>${messageToDay}</td>
				<td>${ipToDay}</td>
				<td>${userToDay}</td>
			</tr>
			<tr class="text-c">
				<td>昨日</td>
				<td>${articleYesterDay}</td>
				<td>${commentsYesterDay}</td>
				<td>${messageYesterDay}</td>
				<td>${ipYesterDay}</td>
				<td>${userYesterDay}</td>
			</tr>
			<tr class="text-c">
				<td>本周</td>
				<td>${articleWeek}</td>
				<td>${commentsWeek}</td>
				<td>${messageWeek}</td>
				<td>${ipWeek}</td>
				<td>${userWeek}</td>
			</tr>
			<tr class="text-c">
				<td>本月</td>
				<td>${articleMonth}</td>
				<td>${commentsMonth}</td>
				<td>${messageMonth}</td>
				<td>${ipMonth}</td>
				<td>${userMonth}</td>
			</tr>
		</tbody>
	</table>
	<table class="table table-border table-bordered table-bg mt-20">
		<thead>
			<tr>
				<th colspan="2" scope="col">服务器信息</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th width="30%">服务器计算机名</th>
				<td><span id="lbServerName">pjy</span></td>
			</tr>
			<tr>
				<td>服务器IP地址</td>
				<td>192.168.1.96</td>
			</tr>
			<tr>
				<td>服务器域名</td>
				<td>www.pengjiangyi.com</td>
			</tr>
			<tr>
				<td>服务器端口 </td>
				<td>80</td>
			</tr>
			<tr>
				<td>服务器操作系统 </td>
				<td>centos 7</td>
			</tr>
			<tr>
				<td>服务器的语言种类 </td>
				<td>Chinese (People's Republic of China)</td>
			</tr>
			<tr>
				<td>服务器IE版本 </td>
				<td>6.0000</td>
			</tr>
			<tr>
				<td>服务器上次启动到现在已运行 </td>
				<td>7210分钟</td>
			</tr>
			
			
			
			
		
		</tbody>
	</table>
</div>
<footer class="footer mt-20">
	<div class="container">
		<p>感谢jQuery、layer、laypage、Validform、UEditor、My97DatePicker、iconfont、Datatables、WebUploaded、icheck、highcharts、bootstrap-Switch<br>
			Copyright &copy;2015-2017 H-ui.admin v3.1 All Rights Reserved.<br>
			本后台系统由<a href="http://www.h-ui.net/" target="_blank" title="H-ui前端框架">H-ui前端框架</a>提供前端技术支持</p>
	</div>
</footer>

<!--_footer 作为公共模版分离出去-->
<script src="${request.contextPath}/resources/assets/jquery-3.2.1/jquery-3.2.1.min.js "></script>
	<script src="${request.contextPath}/resources/assets/layer-v3.1.1/layer/layer.js"></script>
<script type="text/javascript" src="${request.contextPath}/resources/assets/hui-admin/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/resources/assets/hui-admin/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${request.contextPath}/resources/assets/hui-admin/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>


</body>
</html>