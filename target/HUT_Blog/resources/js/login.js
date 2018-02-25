layui.use(['jquery','form'], function () {
    var $ = layui.jquery;
    var form = layui.form;
    //监听登陆
    form.on('submit(loginForm)', function(data){
    	data = data.field;
    	$.ajax({
    		type: 'post',
    		data: data,
    		async:true,
    		url: 'logins',
    		dataType:'json',
    		success:function(result) {
    			if (result.status==6666) {
    				MyLocalStorage.put("user", JSON.stringify(result.obj), 360*24*3);
    				if (document.referrer.split('?')[0].lastIndexOf('resetPwd.html')>0) {
    					location.href = 'index.html';
    				} else {
    					window.history.back(-1);
    				}
    			} else {
    				layer.msg(result.msg,{anim:6});
    			}
    		}
    	});
    	return false;
    });
});