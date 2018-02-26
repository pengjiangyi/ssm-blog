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
		<title>日志管理</title>
	</head>

	<body>
		<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 日志管理 <span class="c-gray en">&gt;</span> 日志管理
			<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新"><i class="Hui-iconfont">&#xe68f;</i></a>
		</nav>
		<div class="page-container">
			<form  method="post" action="${request.contextPath}/admin/conditionalQueryLog">
			<div class="text-c"> 日期范围：
				<input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}' })" id="datemin" name="datemin" class="input-text Wdate" style="width:120px;"> -
				<input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d' })" id="datemax" name="datemax" class="input-text Wdate" style="width:120px;">
				日志类型:
				<span class="select-box" style="width:100px">
				  <select class="select" size="1" name="type">
				    <option value="" selected>请选择</option>
				    <option value="INFO">INFO</option>
				    <option value="ERROR">ERROR</option>
				  </select>
				</span>
				用户id：
				<input type="text" class="input-text" style="width:100px" placeholder="输入用户id" id="userid" name="userid">
				<button type="submit" class="btn btn-success radius" id="searchBtn" name=""><i class="Hui-iconfont">&#xe665;</i> 搜日志</button>
			</div>
			</form>
			
			
			
			
			<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> </span> <span class="r">共有数据：<strong>${log?size}</strong> 条</span> </div>
			<div class="mt-20">
				<table class="table table-border table-bordered table-hover table-bg table-sort">
					<thead>
						<tr class="text-c">
							<th width="25"><input type="checkbox" name="" value=""></th>
							<th width="100">样本ID</th>
							<th width="100">用户id</th>
							<th width="100">用户名</th>
							<th width="100">样本类型</th>
							<th width="100">日志标题</th>
							<th width="100">请求ip</th>
							<th width="130">请求方法</th>
							<th width="100">请求方法类型</th>
							<th width="200">请求参数</th>
						
							<th width="100">请求异常</th>
							<th width="200">操作时间</th>
							<th width="100">操作耗时</th>
							<th width="100">操作</th>
						</tr>
					</thead>
					<tbody>
					<#list log as item>
						<tr class="text-c">
						
							
							<td><input type="checkbox" value="${item.logid}" name="logbox"></td>
							<td>${item.logid}</td>
							<td>${item.userid!}</td>
							<td>${item.username!}</td>
							<td >${item.type!}</td>
							<td >${item.title!}</td>
							<td >${item.ip!}</td>
							<td >${item.method!}</td>
							<td >${item.methodtype!}</td>
							<td >${item.params!}</td>
			
							<td >${item.exception!}</td>
							<td>${item.starttime?string("yyyy-MM-dd hh:mm:ss")}</td>
							<td >${item.timeout!}ms</td>
							
							
							
							<td class="td-manage">
							
								
								
								
								<a title="删除" href="javascript:;" onclick="syslog_del(this,${item.logid})" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>
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
							"aTargets": [0]
						} // 制定列不参与排序
					]
				});

			});
			var rootPath = '${request.contextPath}'
			
		
		
			/*批量删除*/
			function datadel() {
				var arr = new Array();
				$.each($('input[name=logbox]:checked'), function() {
					arr.push($(this).val());
				});
				if(arr.length == 0) {
					layer.alert("请先选中");
					return;
				}
				
				$.ajax({
					type: "post",
					url: rootPath+"/deleteLog",
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

			/*评论-删除*/
			function syslog_del(obj, id) {
				layer.confirm('确认要删除吗？', function(index) {
					$.ajax({
						type: 'POST',
						url: rootPath+'/deleteLogById',
						data:{
							id:id
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