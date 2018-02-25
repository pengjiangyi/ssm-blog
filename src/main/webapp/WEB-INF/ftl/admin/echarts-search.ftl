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
		<title>搜索词汇统计</title>
	</head>

	<body>
	
		<div id="main3" style="width: 90%; height: 400px;margin-left:5%;float:left;margin-top:30px"></div>
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

	var mychart3=echarts.init(document.getElementById('main3'));
	var path = "${request.contextPath}";
	
	var legendData=[]
	var  seriesData=[]
	
	
	mychart3.setOption({
		title : {
	        text: '搜索词汇统计',
	        subtext: '按照搜索次数排名',
	        x:'center'
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    legend: {
	        type: 'scroll',
	        orient: 'vertical',
	        right: 10,
	        top: 20,
	        bottom: 20,
	        data:[]
	    },
	    series : [
	        {
	            name: '词汇',
	            type: 'pie',
	            radius : '55%',
	            center: ['40%', '50%'],
	            data: [],
	            itemStyle: {
	                emphasis: {
	                    shadowBlur: 10,
	                    shadowOffsetX: 0,
	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	                }
	            }
	        }
	    ]
	});
	
	
	
	  seriesData.push({
          name: 'java',
          value: 1,
   
          
      });
	
	 seriesData.push({
         name: 'linux',
         value: 2,
  
         
     });
	
	
	
	$.ajax({
		
		type : "post",
		async : false, //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
		url : path + "/search-echarts", //请求发送到TestServlet处
		data : {},
		dataType : "json",
		success : function(result) {
			
			
			//请求成功时执行该函数内容，result即为服务器返回的json对象
			if (result) {
				legendData=result.legendData
				 seriesData=result.seriesData
		
				
				 mychart3.setOption({
	
	    legend: {
	 
	        data:legendData
	    },
	    series : [
	        {
	           
	            data: seriesData,
	            
	        }
	    ]
	});

			}

		},
		error : function(errorMsg) {
			//请求失败时执行该函数
			alert("图表请求数据失败!");
			myChart3.hideLoading();
		}

	})
	

	
	
	
	
	
	

     
   

</script>