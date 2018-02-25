<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="/favicon.ico" >
<link rel="Shortcut Icon" href="/favicon.ico" />
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
<!--/meta 作为公共模版分离出去-->

</head>
<body>
<article class="page-container">
			<form action="" method="get" class="form form-horizontal" id="form-carousel-add">
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>图片名称：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" class="input-text" value="" placeholder="" id="name" name="name">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>图片地址：</label>
					<div class="formControls col-xs-8 col-sm-9 skin-minimal">
						<script type="text/plain" id="upload_ue" style="display:none"></script>
						<input style="width: 90%;" class="input-text" type="text" id="picture" name="cover" /><input type="button" onclick="upImage();" class="btn btn-primary radius" value="上传"></input>
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3">图片简介：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<textarea name="abstract" id="abstract" cols="" rows="" class="textarea" placeholder="说点什么...最少输入10个字符"></textarea>
						<p class="textarea-numberbar"><em class="textarea-length">0</em>/100</p>
					</div>
				</div>
				<div class="row cl">
					<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
						<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
					</div>
				</div>
			</form>
		</article>


		<!--_footer 作为公共模版分离出去-->
<script src="${request.contextPath}/resources/assets/jquery-3.2.1/jquery-3.2.1.min.js "></script>
	<script src="${request.contextPath}/resources/assets/layer-v3.1.1/layer/layer.js"></script>
<script type="text/javascript" src="${request.contextPath}/resources/assets/hui-admin/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/resources/assets/hui-admin/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

		<!--/_footer 作为公共模版分离出去-->
		<!--请在下方写此页面业务相关的脚本-->
		<script type="text/javascript" src="${request.contextPath}/resources/assets/hui-admin/lib/My97DatePicker/4.8/WdatePicker.js"></script>
		<script type="text/javascript" src="${request.contextPath}/resources/assets/hui-admin/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
		<script type="text/javascript" src="${request.contextPath}/resources/assets/hui-admin/lib/jquery.validation/1.14.0/validate-methods.js"></script>
		<script type="text/javascript" src="${request.contextPath}/resources/assets/hui-admin/lib/jquery.validation/1.14.0/messages_zh.js"></script>
			<!-- 配置文件 -->
					<script type="text/javascript" src="${request.contextPath}/resources/utf8-jsp/ueditor.config.js"></script>
					<!-- 编辑器源码文件 -->
					<script type="text/javascript" src="${request.contextPath}/resources/utf8-jsp/ueditor.all.min.js"></script>





<script type="text/javascript">
var base='${request.contextPath}'
var _editor;
$(function() {

//重新实例化一个编辑器，防止在上面的editor编辑器中显示上传的图片或者文件
_editor = UE.getEditor('upload_ue');
UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
UE.Editor.prototype.getActionUrl = function(action) {
    if (action == 'uploadimage' || action == 'uploadfile') {
        	var id = $('#carInfoId').val();
    	   return base+'/ueditor/fileupload/upload';
    } else if(action=='listimage'){
    	return base+'/ueditor/fileupload/download';
    }
    else{
        return this._bkGetActionUrl.call(this, action);
    }
};
// 复写UEDITOR的getContentLength方法 解决富文本编辑器中一张图片或者一个文件只能算一个字符的问题,可跟数据库字符的长度配合使用
UE.Editor.prototype._bkGetContentLength = UE.Editor.prototype.getContentLength;
UE.Editor.prototype.getContentLength = function(){
    return this.getContent().length;
}



_editor.ready(function () {
//设置编辑器不可用
_editor.setDisabled();
//隐藏编辑器，因为不会用到这个编辑器实例，所以要隐藏
_editor.hide();
//侦听图片上传
_editor.addListener('beforeInsertImage', function (t, arg) {
//将地址赋值给相应的input,只去第一张图片的路径
$("#picture").attr("value", arg[0].src);
})

});
}); 
//弹出图片上传的对话框
function upImage() {
var myImage = _editor.getDialog("insertimage");
myImage.open();
}

</script>


<script type="text/javascript">
			var rootPath = '${request.contextPath}'

			$(function() {
				$('.skin-minimal input').iCheck({
					checkboxClass: 'icheckbox-blue',
					radioClass: 'iradio-blue',
					increaseArea: '20%'
				});

				$("#form-carousel-add").validate({
					rules: {
						name: {
							required: true,
							minlength: 2,
							maxlength: 16
						},
						abstract: {
							required: true,
						},
						cover: {
							required: true,
						}

					},
					onkeyup: false,
					focusCleanup: true,
					success: "valid",
					submitHandler: function(form) {

						//ajax上传
						$.ajax({
							type: "post",
							url: rootPath + "/addPhoto",
							async: false,
							data: {
								name:$("#name").val(),
								url:$("#picture").val(),
								content:$("#abstract").val()
							},
							success: function(data) {
							window.parent.location.reload();
								var index = parent.layer.getFrameIndex(window.name);
							
								parent.layer.close(index);
							},
							error: function() {

							}

						});

					}
				});
			});
		</script>







</body>
</html>