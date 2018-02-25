<!DOCTYPE HTML>
<html>

	<head>
		<meta charset="utf-8">
		<meta name="renderer" content="webkit|ie-comp|ie-stand">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />
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
		<title>ip管理</title>
	</head>

	<body>
	
		<div id="main" style="width: 90%; height: 400px;margin-left:5%;float:left;margin-top:30px"></div>
	<div id="main2" style="width: 90%;height:400px;margin-left:5%;float:left;margin-top:30px"></div>
	<!--_footer 作为公共模版分离出去-->
<script src="${request.contextPath}/resources/assets/jquery-3.2.1/jquery-3.2.1.min.js "></script>
<script src="${request.contextPath}/resources/js/echarts.js "></script>
<script src="${request.contextPath}/resources/assets/layer-v3.1.1/layer/layer.js"></script>
<script type="text/javascript" src="${request.contextPath}/resources/assets/hui-admin/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/resources/assets/hui-admin/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

		<!--/_footer 作为公共模版分离出去-->

		
	</body>

</html>
<script>

	//基于准备好的dom，初始化echarts实例
	var myChart = echarts.init(document.getElementById('main'));
	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption({
	
		title: {  
                    //主标题文本，'\n'指定换行  
                    text: '本月留言统计',  
                    //水平安放位置，默认为左侧，可选为：'center' | 'left' | 'right' | {number}（x坐标，单位px）  
                    x: 'left',  
                    //垂直安放位置，默认为全图顶端，可选为：'top' | 'bottom' | 'center' | {number}（y坐标，单位px）  
                    y: 'top'  
                }, 
		tooltip : {
			trigger : 'axis'
			
		},
		legend : {
			data : [ '每日留言数' ]
		},
		//工具箱，每个图表最多仅有一个工具箱  
                toolbox: {  
                    //显示策略，可选为：true（显示） | false（隐藏），默认值为false  
                    show: true,  
                    //启用功能，目前支持feature，工具箱自定义功能回调处理  
                    feature: {  
                        //辅助线标志  
                        mark: {show: true},  
                        //dataZoom，框选区域缩放，自动与存在的dataZoom控件同步，分别是启用，缩放后退  
                        dataZoom: {  
                            show: true,  
                             title: {  
                                dataZoom: '区域缩放',  
                                dataZoomReset: '区域缩放后退'  
                            }  
                        },  
                        //数据视图，打开数据视图，可设置更多属性,readOnly 默认数据视图为只读(即值为true)，可指定readOnly为false打开编辑功能  
                        dataView: {show: true, readOnly: true},  
                        //magicType，动态类型切换，支持直角系下的折线图、柱状图、堆积、平铺转换  
                        magicType: {show: true, type: ['line', 'bar']},  
                        //restore，还原，复位原始图表  
                        restore: {show: true},  
                        //saveAsImage，保存图片（IE8-不支持）,图片类型默认为'png'  
                        saveAsImage: {show: true}  
                    }  
                },  
		grid : {
			left : '3%',
			right : '4%',
			bottom : '3%',
			containLabel : true
		},
		xAxis : {
			type : 'category',
			boundaryGap : false,
			
			data : []
		},
		yAxis : {
			type : 'value'
		},
		series : [ {
			name : '每日留言数',
			type : 'line',
			stack : '总量',
			 barWidth: '20%',
			data : []
		} ]
	});

	myChart.showLoading();
	var path = "${request.contextPath}";
	 datetime = [];
	 nums = [];
	
	 
		//格局化日期：yyyy-MM-dd 
			function formatDate(date) {
				var myyear = date.getFullYear();
				var mymonth = date.getMonth() + 1;
				var myweekday = date.getDate();

				if(mymonth < 10) {
					mymonth = "0" + mymonth;
				}
				if(myweekday < 10) {
					myweekday = "0" + myweekday;
				}
				return(myyear + "-" + mymonth + "-" + myweekday);
			}

			var now = new Date();
			var nowDay = now.getDate(); //当前日 
			var nowMonth = now.getMonth(); //当前月 
			var nowYear = now.getYear(); //当前年 
			nowYear += (nowYear < 2000) ? 1900 : 0; //
			console.log(getMonthDays(nowMonth))
			console.log(getMonthStartDate())
			
			var result=new Date(getMonthStartDate());
			var i=0;
			while(i<getMonthDays(nowMonth)){
			
				datetime.push(result.getFullYear()+'-'+(result.getMonth()+1)+'-'+result.getDate());
				result=new Date((result/1000+86400*1)*1000);
				i++;
				
			}
			
			
			//获得某月的天数 
			function getMonthDays(myMonth) {
				var monthStartDate = new Date(nowYear, myMonth, 1);
				var monthEndDate = new Date(nowYear, myMonth + 1, 1);
				var days = (monthEndDate - monthStartDate) / (1000 * 60 * 60 * 24);
				return days;
			}
			
			//获得本月的开端日期 
function getMonthStartDate(){ 
var monthStartDate = new Date(nowYear, nowMonth, 1); 
return formatDate(monthStartDate); 
} 
	
	$.ajax({
		
		type : "post",
		async : false, //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
		url : path + "/message-echarts", //请求发送到TestServlet处
		data : {},
		dataType : "json",
		success : function(result) {
			
			
			//请求成功时执行该函数内容，result即为服务器返回的json对象
			if (result) {
				nums=result
		
				
				myChart.hideLoading(); //隐藏加载动画
				myChart.setOption({ //加载数据图表
					xAxis : {
						data : datetime
					},
					series : [ {
						// 根据名字对应到相应的系列
						name : '每日留言数',
						type : 'line',
						stack : '总量',
						data : nums
					} ]
				});

			}

		},
		error : function(errorMsg) {
			//请求失败时执行该函数
			alert("图表请求数据失败!");
			myChart.hideLoading();
		}

	})
	

	
	
	
	var mychart2=echarts.init(document.getElementById('main2'));
	var nums2=[]
	
	mychart2.setOption({
		title: {  
                    //主标题文本，'\n'指定换行  
                    text: '本年度每月留言数统计',  
                    //水平安放位置，默认为左侧，可选为：'center' | 'left' | 'right' | {number}（x坐标，单位px）  
                    x: 'left',  
                    //垂直安放位置，默认为全图顶端，可选为：'top' | 'bottom' | 'center' | {number}（y坐标，单位px）  
                    y: 'top'  
                }, 
		
		tooltip : {
			trigger : 'axis'
		},
		legend : {
			data : [ '每月留言数' ]
		},
		//工具箱，每个图表最多仅有一个工具箱  
                toolbox: {  
                    //显示策略，可选为：true（显示） | false（隐藏），默认值为false  
                    show: true,  
                    //启用功能，目前支持feature，工具箱自定义功能回调处理  
                    feature: {  
                        //辅助线标志  
                        mark: {show: true},  
                        //dataZoom，框选区域缩放，自动与存在的dataZoom控件同步，分别是启用，缩放后退  
                        dataZoom: {  
                            show: true,  
                             title: {  
                                dataZoom: '区域缩放',  
                                dataZoomReset: '区域缩放后退'  
                            }  
                        },  
                        //数据视图，打开数据视图，可设置更多属性,readOnly 默认数据视图为只读(即值为true)，可指定readOnly为false打开编辑功能  
                        dataView: {show: true, readOnly: true},  
                        //magicType，动态类型切换，支持直角系下的折线图、柱状图、堆积、平铺转换  
                        magicType: {show: true, type: ['line', 'bar']},  
                        //restore，还原，复位原始图表  
                        restore: {show: true},  
                        //saveAsImage，保存图片（IE8-不支持）,图片类型默认为'png'  
                        saveAsImage: {show: true}  
                    }  
                },  
		grid : {
			left : '3%',
			right : '4%',
			bottom : '3%',
			containLabel : true
		},
		xAxis : {
			type : 'category',
			boundaryGap : false,
			data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
		},
		yAxis : {
			type : 'value'
		},
		series : [ {
			name : '每月留言数',
			type : 'line',
			stack : '总量',
			barWidth: '20%',
			data : []
		} ]
	});
	
	
	
	$.ajax({
		
		type : "post",
		async : false, //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
		url : path + "/messageMonthOfYear-echarts", //请求发送到TestServlet处
		data : {},
		dataType : "json",
		success : function(result) {
			
			
			//请求成功时执行该函数内容，result即为服务器返回的json对象
			if (result) {
				
				nums2=result;
				
		
				
				mychart2.hideLoading(); //隐藏加载动画
				mychart2.setOption({ //加载数据图表
					series : [ {
						// 根据名字对应到相应的系列
						name : '每月留言数',
						type : 'line',
						stack : '总量',
						data : nums2
					} ]
				});

			}

		},
		error : function(errorMsg) {
			//请求失败时执行该函数
			alert("图表请求数据失败!");
			mychart2.hideLoading();
		}

	})
	

</script>