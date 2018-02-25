$(".item").first().addClass("active");
	$(".index").first().addClass("active");
	
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
	