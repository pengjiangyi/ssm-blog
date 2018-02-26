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
<title>图片列表</title>
<link href="${request.contextPath}/resources/assets/hui-admin/lib/lightbox2/2.8.1/css/lightbox.css" rel="stylesheet" type="text/css" >
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 图片管理 <span class="c-gray en">&gt;</span> 相册管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<form  method="post" action="${request.contextPath}/admin/conditionalQueryPhoto">
	<div class="text-c"> 日期范围：
		<input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'logmax\')||\'%y-%M-%d\'}' })" id="logmin" name="datemin" class="input-text Wdate" style="width:120px;">
		-
		<input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'logmin\')}',maxDate:'%y-%M-%d' })" id="logmax" name="datemax" class="input-text Wdate" style="width:120px;">
		<input type="text" name="carouselName" id="" placeholder=" 图片名称" style="width:250px" class="input-text">
		<button name="photoName" id="" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 搜图片</button>
	</div>
	</form>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a class="btn btn-primary radius" onclick="photo_add('添加图片','${request.contextPath}/photo-add.html')" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加图片</a></span> <span class="r">共有数据：<strong>${photo?size}</strong> 条</span> </div>
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					<th width="40"><input name="" type="checkbox" value=""></th>
					<th width="80">ID</th>
					<th width="100">图片名称</th>
					<th width="100">图片描述</th>
					<th width="150">图片展示</th>
					<th>图片地址</th>
					
					<th width="150">更新时间</th>
					<th width="60">发布状态</th>
					<th width="100">操作</th>
				</tr>
			</thead>
			<tbody>
			
				<#list photo as item>
				<tr class="text-c">
					<td><input name="photobox" type="checkbox" value="${item.id}"></td>
					<td>${item.id}</td>
					<td>${item.name}</td>
					<td >${item.content}</td>
					
					<td><a href="${item.url}" data-lightbox="gallery" data-title="${item.name}"><img width="130" height="80" class="picture-thumb" src="${item.url}"></a></td>
					<td>${item.url}</td>
					<td>${item.time?string("yyyy-MM-dd hh:mm:ss")}</td>
					<#if item.status=="发布">
								<td class="td-status"><span class="label label-success radius">${item.status}</span></td>
								<#else>
								<td class="td-status"><span class="label label-defaunt radius">${item.status}</span></td>
								</#if>
					
					
					<td class="td-manage">
					<#if item.status=="发布">
					<a style="text-decoration:none" onClick="photo_stop(this,${item.id})" href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a> <a style="text-decoration:none" class="ml-5" onClick="photo_edit('图库编辑','${request.contextPath}/updatePhoto/${item.id}.html','10001')" href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a> <a style="text-decoration:none" class="ml-5" onClick="photo_del(this,${item.id})" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a>
					<#else>
					<a style="text-decoration:none" onClick="photo_start(this,${item.id})" href="javascript:;" title="发布"><i class="Hui-iconfont">&#xe603;</i></a> <a style="text-decoration:none" class="ml-5" onClick="photo_edit('图库编辑','${request.contextPath}/updatePhoto/${item.id}.html','10001')" href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a> <a style="text-decoration:none" class="ml-5" onClick="photo_del(this,${item.id})" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a>
					</#if>
					
					
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
<script type="text/javascript" src="${request.contextPath}/resources/assets/hui-admin/lib/lightbox2/2.8.1/js/lightbox.min.js"></script> 

<script type="text/javascript">
$('.table-sort').dataTable({
	"aaSorting": [[ 1, "desc" ]],//默认第几个排序
	"bStateSave": true,//状态保存
	"aoColumnDefs": [
	  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
	  {"orderable":false,"aTargets":[0,8]}// 制定列不参与排序
	]
});
		var rootPath = '${request.contextPath}'

/*批量删除*/
			function datadel() {
				var arr = new Array();
				$.each($('input[name=photobox]:checked'), function() {
					arr.push($(this).val());
				});
				if(arr.length == 0) {
					layer.alert("请先选中");
					return;
				}
				
				$.ajax({
					type: "post",
					url: rootPath+"/deletePhoto",
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



/*图片-添加*/
function photo_add(title,url){
	
	
	layer_show(title,url,"","500")
}



/*图片-下架*/
function photo_stop(obj,id){
	layer.confirm('确认要下架吗？', function(index) {
					$.ajax({
						type: "post",
						url: rootPath + "/stopPhoto",
						async: false,
						data: {
							id: id
						},
						success: function(data) {
							$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="photo_start(this,'+id+')" href="javascript:;" title="发布"><i class="Hui-iconfont">&#xe603;</i></a>');
							$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">下架</span>');
							$(obj).remove();
							layer.msg('已下架!', {
								icon: 5,
								time: 1000
							});
						},
						error: function() {

						}
					});

				});
	
	
	
	
}

/*图片-发布*/
function photo_start(obj,id){
	layer.confirm('确认要发布吗？', function(index) {
					$.ajax({
						type: "post",
						url: rootPath + "/startPhoto",
						async: false,
						data: {
							id: id
						},
						success: function(data) {
							$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="photo_stop(this,'+id+')" href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a>');
							$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">发布</span>');
							$(obj).remove();
							layer.msg('已发布!', {
								icon: 6,
								time: 1000
							});
						},
						error: function() {

						}
					});

				});
}



/*图片-编辑*/
function photo_edit(title,url,id){
	layer_show(title,url,"","500")
}

/*图片-删除*/
function photo_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			url: rootPath+'/deletePhotoById',
			data:{
			id:id
			},
			success: function(data){
				$(obj).parents("tr").remove();
				layer.msg('已删除!',{icon:1,time:1000});
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
	});
}
</script>
</body>
</html>