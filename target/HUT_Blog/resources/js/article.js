
function getTimeByTimeStamp(Timestamp) {
	var newDate = new Date();
	newDate.setTime(Timestamp);
	Date.prototype.format = function(format) {
		var date = {
			"M+" : this.getMonth() + 1,
			"d+" : this.getDate(),
			"h+" : this.getHours(),
			"m+" : this.getMinutes(),
			"s+" : this.getSeconds(),
			"q+" : Math.floor((this.getMonth() + 3) / 3),
			"S+" : this.getMilliseconds()
		};
		if (/(y+)/i.test(format)) {
			format = format.replace(RegExp.$1, (this.getFullYear() + '')
					.substr(4 - RegExp.$1.length));
		}
		for ( var k in date) {
			if (new RegExp("(" + k + ")").test(format)) {
				format = format.replace(RegExp.$1,
						RegExp.$1.length == 1 ? date[k] : ("00" + date[k])
								.substr(("" + date[k]).length));
			}
		}
		return format;
	}
	return newDate.format('yyyy-MM-dd hh:mm:ss');
} 

layui.use(['jquery','flow','form'], function () {
	var $ = layui.jquery;
	var id = "";
	var url = window.location.href.split("?")[1];
	if (url!=null && url!='') {
		 var args = url.split("&");
		 id = args[0].split("=")[1];
	}
    // 流加载
    var flow = layui.flow;
    flow.load({
    	elem: '.blog-main-left', //流加载容器
    	isAuto: true,
    	end: '没有更多的文章了~QAQ',
    	done: function(page,next) {
    		$.ajax({
        		type: 'GET',
        		data: {page:page,id:id},
        		url: 'Article/findPaging.do',
        		dataType:'json',
        		success:function(data) {
        			if (data.status==0) {
        				var arts = data.obj;
        				var lis = [];
        				for (var i=0; i<arts.length; i++) {
        					var time = getTimeByTimeStamp(arts[i].createTime);
        					var head='';
        					if (page==1) {
        						head='<div class="article shadow animated fadeInLeft">';
        					} else {
            	        		head='<div class="article shadow">';
            	        	}
        					lis.push(head+
        	                          '<div class="article-left ">'+
        	                          '<img src="'+arts[i].img+'" alt=\''+arts[i].title+'\'/>'+
        	                      '</div>'+
        	                      '<div class="article-right">'+
        	                          '<div class="article-title">'+
        	                              '<a href="findArticleDetails?type='+arts[i].id+'&details='+arts[i].articleNumber+'&similarity='+arts[i].articleSort.id+'">'+arts[i].title+'</a>'+
        	                          '</div>'+
        	                          '<div class="article-abstract">'+
        	                              arts[i].content+
        	                          '</div>'+
        	                      '</div>'+
        	                      '<div class="clear"></div>'+
        	                      '<div class="article-footer">'+
        	                          '<span><i class="fa fa-clock-o"></i>&nbsp;&nbsp;'+time+'</span>'+
        	                          '<span class="article-author"><i class="fa fa-user"></i>&nbsp;&nbsp;验算纸</span>'+
        	                          '<span><i class="fa fa-tag"></i>&nbsp;&nbsp;<a href="#">'+arts[i].articleSort.name+'</a></span>'+
        	                          '<span class="article-viewinfo"><i class="fa fa-eye"></i>&nbsp;'+arts[i].hits+'</span>'+
        	                          '<span class="article-viewinfo"><i class="fa fa-commenting"></i>&nbsp;'+arts[i].articleSort.count+'</span>'+
        	                      '</div>'+
        	                  '</div>');
        				}	
        				if (page==1) {
        					next(lis.join(''), page < data.count);
        				} else {
        					setTimeout(function(){next(lis.join(''), page < data.count);},1000);
        				}
        			} else {
        				layer.msg(data.msg,{anim:6});
        			}
        		}
        	});	
    	}
    });
});

function go(type) {
	window.location.href = _contextPath+'/article.html?id='+id;
}