layui.use(['jquery','flow'], function () {
	var $ = layui.jquery;
	// 流加载 图片
    var flow = layui.flow;
    var count = 0 ;
    flow.load({
    	elem: '.mixed-main', //流加载容器
    	isAuto: true,
    	end: '没有更多的图片了~QAQ',
    	done: function(page,next) {
    		$.ajax({
        		type: 'GET',
        		url: 'photo/find.do',
        		dataType:'json',
        		success:function(data) {
        			if(count == 0) count = data.count-1;
        			var lis = [];
            		for (var i=0; i< 8; i++) {
            			if (count < -1) break;
            			if (count==-1) {
            				lis.push('<div class="mixed shadow animated zoomIn">'+
            	                 '<div class="mixed-pic">'+
            	                    '<a href="javascript:"><img src="http://yansuanzhi-1254151862.cossh.myqcloud.com/img/0.jpg" alt="图片还在拍摄中" /></a>'+
        	                    '</div>'+
        	                    '<div class="mixed-info">图片还在拍摄中</div>'+
        	                    '<div class="mixed-footer">'+
        	                        '<a class="layui-btn layui-btn-small layui-btn-primary layui-btn-disabled"><i class="fa fa-eye fa-fw"></i>查看</a>'+
        	                        '<a class="layui-btn layui-btn-small layui-btn-primary layui-btn-disabled"><i class="fa fa-download fa-fw"></i>下载</a>'+
        	                    '</div>',
        	                '</div>');
            			} else {
        	    			lis.push('<div class="mixed shadow animated zoomIn">'+
        	                    '<div class="mixed-pic">'+
        	                        '<a href="javascript:view('+count+')"><img src="'+data.obj[count].img+'" alt="'+data.obj[count].alt+'" /></a>'+
        	                    '</div>'+
        	                    '<div class="mixed-info">'+data.obj[count].alt+'</div>'+
        	                    '<div class="mixed-footer">'+
        	                        '<a class="layui-btn layui-btn-small layui-btn-primary" href="javascript:view('+count+')"><i class="fa fa-eye fa-fw"></i>查看</a>'+
        	                        '<a class="layui-btn layui-btn-small layui-btn-primary" href="pic/down.do?path='+data.obj[count].img+'"><i class="fa fa-download fa-fw"></i>下载</a>'+
        	                    '</div>',
        	                '</div>');
            			}
            			count--;
            		}
            		next(lis.join(''), page < data.obj.length / 8);
        		}
    		})
    	}
    });
});

function view(start) {
	pics.start = start;
	layer.photos({photos: pics });
}
