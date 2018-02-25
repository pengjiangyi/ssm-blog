

/*点击排行颜色js开始*/
	var colors = ["red", "orange", "green", "blue", "cyan"];
	var nums = ["1", "2", "3", "4", "5"];
	$(".list-group-item em").each(function(index, dom) {
		$(this).css("background-color", colors[index]);
		$(this).text(nums[index])
	})
	$(function() {
	$(".login").attr("href",base+"/login.html?from="+encodeURIComponent(location.href));
	
	
	$("[data-toggle='tooltip']").tooltip(); 
		//播放公告
		playAnnouncement(3000);
	});

	function playAnnouncement(interval) {
		var index = 0;
		var $announcement = $('.home-tips-container>span');
		//自动轮换
		setInterval(function() {
			index++; //下标更新
			if(index >= $announcement.length) {
				index = 0;
			}
			$announcement.eq(index).stop(true, true).fadeIn().siblings('span').fadeOut(); //下标对应的图片显示，同辈元素隐藏
		}, interval);
	}
	






var totalPage = $("#pages").html();
var totalRecords = $("#total").html();
var tagname=$("#tagname").html();
var pageNo = $("#pageNum").html(); //这里设置参数名(柯 乐 义 注 释)
if (!pageNo) {
pageNo = 1;
}
//生成分页控件 根据分页的形式在这里设置
kkpager.init({
pno: pageNo,
//总页码
total: totalPage,
//总数据条数
totalRecords: totalRecords,
//链接前部
hrefFormer: base+'/query/tag/'+tagname+"/",
//链接尾部
hrefLatter: '.html',
getLink: function (n) {
return this.hrefFormer +n+ this.hrefLatter; //参数名跟上面相同
}
});
kkpager.generPageHtml();



function search(){
var keywords=$("#input-keywords").val();
if(keywords==null||keywords=="")
{
layer.msg("请输入想要查找的词汇")
return;
}
location.href=base+"/search/"+keywords+"/1.html";
}

$("#input-keywords").keydown(function(){
if(event.keyCode == 13){
search();
}
});
