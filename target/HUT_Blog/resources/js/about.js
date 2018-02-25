systemTime();
function systemTime() {
    //获取系统时间。
    var dateTime = new Date();
    var year = dateTime.getFullYear();
    var month = dateTime.getMonth() + 1;
    var day = dateTime.getDate();
    var hh = dateTime.getHours();
    var mm = dateTime.getMinutes();
    var ss = dateTime.getSeconds();

    //分秒时间是一位数字，在数字前补0。
    mm = extra(mm);
    ss = extra(ss);

    //将时间显示到ID为time的位置，时间格式形如：19:18:02
    document.getElementById("time").innerHTML = year + "-" + month + "-" + day + " " + hh + ":" + mm + ":" + ss;
    //每隔1000ms执行方法systemTime()。
    setTimeout("systemTime()", 1000);
}

//补位函数。
function extra(x) {
    //如果传入数字小于10，数字前补一位0。
    if (x < 10) {
        return "0" + x;
    }
    else {
        return x;
    }
}




//监听留言提交
layui.use(['jquery', 'form', 'layedit', 'laydate'], function(){
	var form = layui.form;
	var $ = layui.jquery;
	var layedit = layui.layedit;
    var flow = layui.flow;
    
    
  //自定义验证规则
    form.verify({
      title: function(value){
        if(value.length < 2){
          return '网站名称至少两个字符';
        }
      }
      ,remark: function(value){
        if(value.length < 10) {
        	return '简介不能低于10个字';
        }
      },personName :function(value) {
    	  if(value.length < 2) {
    		  return '姓名至少两个字符';
    	  }
      },yzm : function(value) {
    	  if(value.length != 5) {
    		  return '验证码是五个字符';
    	  }
      }
    });
    
    
    //监听留言提交
    form.on('submit(insertLink)', function (data) {
        var index = layer.load(1);
        //模拟留言提交
        setTimeout(function () {
        	layer.close(index);
        	var json = {typeId:data.field.typeId,name:data.field.name,url:data.field.url,contact:data.field.contact,phone:data.field.phone,code:data.field.code,remark:data.field.remark};
        	$.ajax({
        		type: 'POST',
        		data: json,
        		url:'insertLink.do',
        		dataType:'json',
        		success:function(result) {
        			layer.open({
        				  content:result.msg,
        				  scrollbar: false
        			});
        		}
        	});
        }, 500);
        return false;
    });
    
	
});

