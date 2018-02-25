var totalPage = $("#pages").html();
var totalRecords = $("#total").html();
var keywords=$("#keywords").html();
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
hrefFormer: base+'/search/'+keywords+"/",
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


