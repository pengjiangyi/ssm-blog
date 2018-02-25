
/*百度统计*/
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "https://hm.baidu.com/hm.js?4c379efff1c0d273173ba7daf8448789";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();

/*转换时间戳函数*/
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

//定时 缓存  
var MyLocalStorage = {                  
	/** 
	 * 总容量5M 
	 * 存入缓存，支持字符串类型、json对象的存储 
	 * 页面关闭后依然有效 ie7+都有效 
	 * @param key 缓存key 
	 * @param stringVal 
	 * @time 数字 缓存有效时间（秒） 默认60s  
	 * 注：localStorage 方法存储的数据没有时间限制。第二天、第二周或下一年之后，数据依然可用。不能控制缓存时间，故此扩展 
	 * */  
    put : function(key,stringVal,time){  
        try{  
            if(!localStorage){return false;}  
            if(!time || isNaN(time)){time=60;}  
            var cacheExpireDate = (new Date()-1)+time*1000;  
            var cacheVal = {val:stringVal,exp:cacheExpireDate};  
            localStorage.setItem(key,JSON.stringify(cacheVal));//存入缓存值  
        }catch(e){}   
    }, /**获取缓存*/  
    get : function (key){  
        try{  
            if(!localStorage){return false;}  
            var cacheVal = localStorage.getItem(key);  
            var result = JSON.parse(cacheVal);  
            var now = new Date()-1;  
            if(!result){return null;}//缓存不存在  
            if(now>result.exp){//缓存过期  
                this.remove(key);                     
                return "";  
            }  
            return result.val;  
        }catch(e){  
            this.remove(key);
            return null;  
        }  
    },/**移除缓存，一般情况不手动调用，缓存过期自动调用*/  
    remove : function(key){  
        if(!localStorage){return false;}  
        localStorage.removeItem(key);  
    },/**清空所有缓存*/  
    clear : function(){  
        if(!localStorage){return false;}  
        localStorage.clear();
    }
}