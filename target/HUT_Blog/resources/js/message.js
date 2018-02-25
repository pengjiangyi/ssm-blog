$(function() {

	$('a[data-toggle="tab"]').on('shown.bs.tab', function(e) {
		// Get the name of active tab
		var activeTab = $(e.target).text();
		// Get the name of previous tab
		var previousTab = $(e.relatedTarget).text();
		$(".active-tab span").html(activeTab);
		$(".previous-tab span").html(previousTab);
	});

});

function test(obj) {
	if($(obj).text() == '回复') {
		if(user == "") {
			window.location.href = base + '/login.html?from=' + encodeURIComponent(location.href);
		} 
		else{
		var doc = document.createElement('div');
		doc.innerHTML =
			'<textarea id="reply-textarea" class="form-control" rows="3" placeholder="请输入内容">' + '</textarea>' +
			'<button type="button" class="btn btn-success" style="margin-top: 5px;" onclick=sub(this)>' + '提交' + '</button>'
		doc.style = "margin-top:20px";

		$(obj).parent().append(doc);
		$(obj).html("<i class='fa fa-caret-square-o-up' >收起");
		}
	} else {
		$(obj).next().remove();
		$(obj).html("<i class='fa fa-comment' >回复");
	}

}

function sub(obj) {
	var doc = $(obj).parent().parent().parent().parent();
	var parentid = $(obj).parent().prevAll(".parentid").text();
	var nickname = $(obj).parent().prevAll(".nickname").text();
	var reply = $(obj).parent().parent().prev().children(".text-container").html();
	var text = $("#reply-textarea").val();
	var time = getNowFormatDate();
	if(text=="") {
		layer.msg("好歹留几个字啊！")
	} else {
		
		/*if(userid==parentid)
			{
			layer.msg("兄弟自己回复自己,这么厉害的？");
			return;
			}	*/
		$.ajax({
			type:"post",
			url:base+"/addMessage",
			async:false,
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			data:{
				userid:userid,
				parentid:parentid,
				text:text,
				time:time,
				parentname:"@"+nickname
			},
			success:function(){
				$(obj).parent().prev().html("<i class='fa fa-comment' >回复");
				$(obj).parent().remove();
				var html = '<article class="comment" style="margin-top:20px">' +
					'<a class="comment-img" href="#non">' +
					'<img src="resources/img/admin.jpg" alt="" width="50" height="50">' +
					'</a>' +
					'<div class="comment-body">' +
					'<div class="text">' +
					'<p class="text-parentname">@' + nickname+ '</p>' +
					'<p class="text-container">' + text + '</p>' +
					'</div>' +
					'<p class="attribution">' + '<span class="nickname">' + '<i class="fa fa-user"></i>' +username + '</span>' +
					'<span class="time">' + '<i class="fa fa-clock-o"></i>' + time + '</span>' +
					'<span class="userid" style="display:none">'+userid+'</span>' +
					'<span class="parentid" style="display:none">'+parentid+'</span>' +
					'<span class="remark" onclick="test(this)">' + '<i class="fa fa-comment" ></i>' + '回复' + '</span>' +
					'</p>' +
					'</div>' +
					'</article>';
				doc.append(html);
				$(".comments-num").html(parseInt($(".comments-num").html())+1)
				
			},error:function(){
				
				
			}
		});
		
		
		
		
		
	}
}


function html2Escape(sHtml) {
	return sHtml.replace(/[<>&"]/g, function(c) {
		return {
			'<': '&lt;',
			'>': '&gt;',
			'&': '&amp;',
			'"': '&quot;'
		}[c];
	});
}
/*
function getRootPath() {
	//获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
	var curWwwPath = window.document.location.href;
	//获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
	var pathName = window.document.location.pathname;
	var pos = curWwwPath.indexOf(pathName);
	//获取主机地址，如： http://localhost:8083
	var localhostPaht = curWwwPath.substring(0, pos);
	//获取带"/"的项目名，如：/uimcardprj
	var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
	return(localhostPaht + projectName);
}
var path = getRootPath();*/

//根评论
function sub_comments(obj) {
	if(user == "") {
		window.location.href = base + '/login.html?from=' + encodeURIComponent(location.href);
	} else {
		var date = getNowFormatDate();
		var text = UE.getEditor('container').getContentTxt();
		var html = UE.getEditor('container').getPlainTxt();
		var str=UE.getEditor('container').getContent();
		var time = getNowFormatDate();
		if(str==null||str.length==0||str=="") {
			layer.msg("好歹写几个字吧!");
			return;
		} else {
			//parentid=0;
			//parentname=@博主
		$.ajax({
				type: "post",
				url: base+"/addMessage",
				async: false,
				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
				data: {
					userid:userid,
					parentid:0,
					text:html,
					time:time,
					parentname:"@博主"
				},
				success: function() {
					var doc = '<article class="comment" style="margin-top:20px">' +
					'<a class="comment-img" href="#non">' +
					'<img src="resources/img/admin.jpg" alt="" width="50" height="50">' +
					'</a>' +
					'<div class="comment-body">' +
					'<div class="text">' +
					'<p class="text-parentname"></p>' +
					'<p class="text-container">' + html + '</p>' +
					'</div>' +
					'<p class="attribution">' + '<span class="nickname">' + '<i class="fa fa-user"></i>' + username + '</span>' +
					'<span class="time">' + '<i class="fa fa-clock-o"></i>' + date + '</span>' +
					'<span style="display:none" class="userid">'+userid+'</span>'+
					'<span style="display:none" class="parentid">0</span>'+
					'<span class="remark" onclick="test(this)">' + '<i class="fa fa-comment" ></i>' + '回复' + '</span>' +
					'</p>' +
					'</div>' +
					'</article>';
				$('.comments').prepend(doc);	
				$(".comments-num").html(parseInt($(".comments-num").html())+1)
				},
				error: function() {
				}
			});
			
		}
	}

}

function getNowFormatDate() {
	var date = new Date();
	var seperator1 = "-";
	var seperator2 = ":";
	var month = date.getMonth() + 1;
	var strDate = date.getDate();
	if(month >= 1 && month <= 9) {
		month = "0" + month;
	}
	if(strDate >= 0 && strDate <= 9) {
		strDate = "0" + strDate;
	}
	var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate +
		" " + date.getHours() + seperator2 + date.getMinutes() +
		seperator2 + date.getSeconds();
	return currentdate;
}