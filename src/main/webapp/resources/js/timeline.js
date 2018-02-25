var data = [
	{'year':'2018','month':[{
		'title':'1月','day':[{
			'time':'2018年1月13日 ','content':'&nbsp;博客第二版正式启动，全新布局以及采用SSM+MAVEN框架'
			
		}]
		
	}]},
	{'year':'2017','month':[
		{'title':'10月','day':[
		    {'time':'2017年10月26日 ','content':'准备重新改版一个版本,心疼1.0版3秒钟,周末还是一如既往的折腾&nbsp;'},
		    {'time':'2017年10月25日 ','content':'感觉自己写的样式太丑了,兼容性不好,发现各种问题&nbsp;'},
		    {'time':'2017年10月24日 ','content':'博客v.01基本功能全部完善!关于QQ登陆，和微博登陆还在研究中'}
		]},
		{'title':'9月','day':[
  		    {'time':'2017年010月1日 ','content':'&nbsp;博客v1.0发布!'}
  		]},
		{'title':'8月','day':[
   		    {'time':'2017年09月16日 ','content':'博客项目启动!'}
   		]}
	]}
];



	for (var y=0; y<data.length; y++) {
		var html = '<div class="timeline-year">';
		
		var month = data[y].month;
		for (var m=0; m<month.length; m++) {
			html += '<div class="timeline-month"><ul>';
			var day = month[m].day;
			for (var d=0; d<day.length; d++) {
				html += '<li>'+
                		'<div class="h4  animated fadeInLeft"><p class="date">'+day[d].time+'</p></div>'+
                		'<p class="dot-circle animated "><i class="fa fa-dot-circle-o"></i></p>'+
                		'<div class="content animated fadeInRight">'+day[d].content+'</div><div class="clear"></div>'+
                		'</li>';
			}
			html += '</ul></div>';
		}
		html +='</div>';
		$(".timeline-data").append(html);
	}
	
	
$(function () {
	
	    $('.monthToggle').click(function () {
	        $(this).parent('h3').siblings('ul').slideToggle('slow');
	        $(this).children('i').toggleClass('fa-caret-down fa-caret-right');
	    });
	    $('.yearToggle').click(function () {
	        $(this).parent('h2').siblings('.timeline-month').slideToggle('slow');
	        $(this).children('i').toggleClass('fa-caret-down fa-caret-right');
	    });
	});
