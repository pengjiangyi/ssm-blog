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
		<title>友链管理</title>
	</head>

	<body>
		<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 友链管理 <span class="c-gray en">&gt;</span> 友链管理
			<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新"><i class="Hui-iconfont">&#xe68f;</i></a>
		</nav>
		<div class="page-container">
			<form  method="post" action="${request.contextPath}/admin/conditionalQueryLinks">
			<div class="text-c"> 日期范围：
				<input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}' })" id="datemin" name="datemin" class="input-text Wdate" style="width:120px;"> -
				<input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d' })" id="datemax" name="datemax" class="input-text Wdate" style="width:120px;">
				<input type="text" class="input-text" style="width:250px" placeholder="输入友链网站名称" id="webname" name="webname">
				<button type="submit" class="btn btn-success radius" id="searchBtn" name=""><i class="Hui-iconfont">&#xe665;</i> 搜友链</button>
			</div>
			</form>
			<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a href="javascript:;" onclick="links_add('添加友链','${request.contextPath}/admin/links-add.html','','510')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加友链</a></span> <span class="r">共有数据：<strong>${links?size}</strong> 条</span> </div>
			<div class="mt-20">
				<table class="table table-border table-bordered table-hover table-bg table-sort">
					<thead>
						<tr class="text-c">
							<th width="25"><input type="checkbox" name="" value=""></th>
							<th width="80">ID</th>
							<th width="100">网站类型</th>
							<th width="100">网站名称</th>
							<th width="150">网站地址</th>
							<th width="100">手机号</th>
							<th width="">简介</th>
							<th width="130">申请时间</th>
							<th width="70">状态</th>
							<th width="100">操作</th>
						</tr>
					</thead>
					<tbody>
					<#list links as item>
						<tr class="text-c">
						
							
							<td><input type="checkbox" value="${item.id}" name="linkbox"></td>
							<td>${item.id}</td>
							<td>${item.category}</td>
							<td>${item.webname}</td>
							<td>${item.url}</td>
							<td>${item.telphone}</td>
							<td class="text-l">${item.content}</td>
							<td>${item.time?string("yyyy-MM-dd hh:mm:ss")}</td>
							
							<#if item.status=="启用">
								<td class="td-status"><span class="label label-success radius">${item.status}</span></td>
								<#else>
								<td class="td-status"><span class="label label-defaunt radius">${item.status}</span></td>
								</#if>
							
							
							<td class="td-manage">
							
								<#if item.status=="启用">
								<a style="text-decoration:none" onClick="links_stop(this,${item.id})" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a>
								<#else>
								<a style="text-decoration:none" onClick="links_start(this,${item.id})" href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe604;</i></a>
								</#if>
								
								<a title="编辑" href="javascript:;" onclick="links_edit('编辑','${request.contextPath}/admin/links-update.html/${item.id}','','','510')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>
								</a>
								<a title="删除" href="javascript:;" onclick="links_del(this,${item.id})" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>
							</td>
							
						</tr>
</#list>
					</tbody>
				</table>
			</div>
		</div>
	<!--_footer 作为公共模版分离出去-->
<script src="${request.contextPath}/resources/assets/jquery-3.2.1/jquery-3.2.1.min.js "></script>
	<script src="${request.contextPath}/resources/assets/layer-v3.1.1/layer/layer.js"></script>
<script type="text/javascript" src="${request.contextPath}/resources/assets/hui-admin/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/resources/assets/hui-admin/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

		<!--/_footer 作为公共模版分离出去-->

		<!--请在下方写此页面业务相关的脚本-->
		<script type="text/javascript" src="${request.contextPath}/resources/assets/hui-admin/lib/My97DatePicker/4.8/WdatePicker.js"></script>
		<script type="text/javascript" src="${request.contextPath}/resources/assets/hui-admin/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="${request.contextPath}/resources/assets/hui-admin/lib/laypage/1.2/laypage.js"></script>
		<script type="text/javascript">
			$(function() {
				$('.table-sort').dataTable({
					"aaSorting": [
						[1, "desc"]
					], //默认第几个排序
					"bStateSave": true, //状态保存
					"aoColumnDefs": [
						//{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
						{
							"orderable": false,
							"aTargets": [0, 8, 9]
						} // 制定列不参与排序
					]
				});

			});
			var rootPath = '${request.contextPath}'
			
			
			
			/*批量删除*/
			function datadel() {
				var arr = new Array();
				$.each($('input[name=linkbox]:checked'), function() {
					arr.push($(this).val());
				});
				if(arr.length == 0) {
					layer.alert("请先选中");
					return;
				}
				
				$.ajax({
					type: "post",
					url: rootPath+"/deleteLinks",
					traditional: true,
					data: {
						list:arr
					},
					success: function(data) {
						//刷新页面
						 location.reload();
					},
					error: function() {
					}
				});

			}

			/*友链-添加*/
			function links_add(title, url, w, h) {
				layer_show(title, url, w, h);
			}

			/*友链-停用*/
			function links_stop(obj, id) {
				layer.confirm('确认要停用吗？', function(index) {
					$.ajax({
						type: 'POST',
						url: rootPath+'/stopLinks',
						data:{
						linkid:id	
						},
						
						success: function(data) {
							$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="links_start(this,' + id + ')" href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe604;</i></a>');
							$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">停用</span>');
							$(obj).remove();
							layer.msg('已停用!', {
								icon: 5,
								time: 1000
							});
						},
						error: function(data) {
							console.log(data.msg);
						},
					});
				});
			}

			/*友链-启用*/
			function links_start(obj, id) {
				layer.confirm('确认要启用吗？', function(index) {
					$.ajax({
						type: 'POST',
						url: rootPath+'/startLinks',
						data:{
						linkid:id	
						},
						
						success: function(data) {
							$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="links_stop(this,' + id + ')" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a>');
							$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
							$(obj).remove();
							layer.msg('已启用!', {
								icon: 6,
								time: 1000
							});
						},
						error: function(data) {
							console.log(data.msg);
						},
					});
				});
			}
			/*友链-编辑*/
			function links_edit(title, url, id, w, h) {
				//传id，到后台查出相应的数据后返回

				layer_show(title, url,w, h);
			}

			/*友链-删除*/
			function links_del(obj, id) {
				layer.confirm('确认要删除吗？', function(index) {
					$.ajax({
						type: 'POST',
						url: rootPath+'/deleteLinksById',
						data:{
							linksid:id
						},
						
						success: function(data) {
							$(obj).parents("tr").remove();
							layer.msg('已删除!', {
								icon: 1,
								time: 1000
							});
						},
						error: function(data) {
							console.log(data.msg);
						},
					});
				});
			}
		</script>
	</body>

</html>