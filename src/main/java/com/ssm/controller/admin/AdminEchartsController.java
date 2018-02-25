package com.ssm.controller.admin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.swing.plaf.synth.SynthSpinnerUI;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ssm.pojo.Search;
import com.ssm.service.dao.EchartsService;
/**
 * 
	*@class AdminEchartsController
	*@description  报表统计,采用echarts
 	*@author 彭江毅  
 	*@date  2018年2月10日下午7:13:49
	*@contact 741506070@qq.com
 */
@Controller
public class AdminEchartsController {
	@Resource
	private EchartsService echartsService;
	/**
	 * 返回IP记录的视图
	 * @return
	 */
	@RequestMapping("/admin/ip-echarts")
	public ModelAndView ipUI() {

		return new ModelAndView("/admin/echarts-ip");
	}
	/**
	 * 返回搜索记录的视图
	 * @return
	 */
	@RequestMapping("/admin/search-echarts")
	public ModelAndView searchUI() {

		return new ModelAndView("/admin/echarts-search");
	}
	/**
	 * 返回文章记录的视图
	 * @return
	 */
	@RequestMapping("/admin/article-echarts")
	public ModelAndView articleUI() {
		return new ModelAndView("/admin/echarts-article");
	}
	/**
	 * 返回评论数的视图
	 * @return
	 */
	@RequestMapping("/admin/comments-echarts")
	public ModelAndView commentsUI() {
		return new ModelAndView("/admin/echarts-comments");
	}
	/**
	 * 返回留言数的视图
	 * @return
	 */
	@RequestMapping("/admin/message-echarts")
	public ModelAndView messageUI() {
		return new ModelAndView("/admin/echarts-message");
	}

	/**
	 * 
	 * @param data
	 *            数据库查询的数据,返回格式是time,num
	 * @return
	 */
	public static int[] transto(List<Map> data) {
		List<Map> list = new ArrayList<>();
		// 转换数据格式为key为日期,value为数量
		for (Map map : data) {
			Map map2 = new HashMap<>();
			map2.put(map.get("time"), map.get("num"));
			list.add(map2);
		}
		// 获取本月的全部日期
		List list3 = getDayListOfMonth();
		int[] nums = new int[list3.size()];
		// 将所有日期的数量清0
		for (int i = 0; i < nums.length; i++) {
			nums[i] = 0;
		}
		// 遍历转换的Map,匹配本月的全部日期,有匹配的获取日期和数量，同时设置数组
		for (Map<String, Object> map : list) {
			for (Object key : map.keySet()) {
				for (int i = 0; i < list3.size(); i++) {
					// key是数据库的日期 list3是生成的本月日期 进行匹配
					if (key.equals(list3.get(i))) {
						// 进行截取获取日期最后两位，0开头截取掉
						String day = (list3.get(i).toString().substring(8, 10));
						if (day.startsWith("0")) {
							day = day.substring(1, 2);
						}
						// 设置对应日期的数组数量
						nums[Integer.parseInt(day) - 1] = Integer.parseInt(map.get(key).toString());

					}
				}

			}
		}
		return nums;

	}
	/**
	 * 转换格式数据,将为0的数据置零
	 * 数据库的格式为2018-01-01 截取月份  和当年生成的数组2018-01匹配
	 * 存在取值,不存在为0
	 * 
	 * @param data
	 * @return
	 */
	public static int[] transToMonth(List<Map> data) {
		List<Map> list = new ArrayList<>();
		// 转换数据格式为key为日期,value为数量
		for (Map map : data) {
			Map map2 = new HashMap<>();
			map2.put(map.get("time"), map.get("num"));
			list.add(map2);
		}
		// 获取本年的全部月份
		List list3 = getMonthListOfYear();
		
		int[] nums = new int[list3.size()];
		// 将所有月份的数据清0
		for (int i = 0; i < nums.length; i++) {
			nums[i] = 0;
		}
		// 遍历转换的Map,匹配本月的全部日期,有匹配的获取日期和数量，同时设置数组
		for (Map<String, Object> map : list) {
			for (Object key : map.keySet()) {
				
				
				for (int i = 0; i < list3.size(); i++) {
					// key是数据库的日期 list3是生成的本月日期 进行匹配
					if (key.equals(list3.get(i))) {
						// 进行截取获取日期最后两位，0开头截取掉
						String day = (list3.get(i).toString().substring(5, 7));
						if (day.startsWith("0")) {
							day = day.substring(1, 2);
						}
						
						// 设置对应日期的数组数量
						nums[Integer.parseInt(day) - 1] = Integer.parseInt(map.get(key).toString());

					}
				}

			}
		}
		
		return  nums;
		
	}
	/**
	 * 获取一个月的ip记录
	 * @return
	 */
	@RequestMapping("/ip-echarts")
	@ResponseBody
	public String getIp() {
		String json = "";
		List<Map> ip = echartsService.selectIp();
		int[] nums = transto(ip);
		json = JSONObject.toJSONString(nums);
		return json;
	}
	/**
	 * 获取一年的记录,月份划分1-12月
	 * @return
	 */
	@RequestMapping("/ipMonthOfYear-echarts")
	@ResponseBody
	public String getIpMonthOfYear() {
		String json = "";
		List<Map> ip = echartsService.selectIpMonthOfYear();

		int[] nums = transToMonth(ip);

		json = JSONObject.toJSONString(nums);
		return json;
	}
	
	
	/**
	 * 获取一个月的文章数记录
	 * @return
	 */
	@RequestMapping("/article-echarts")
	@ResponseBody
	public String getArticle() {
		String json = "";
		List<Map> article = echartsService.selectArticleEcharts();
		int[] nums = transto(article);
		return JSON.toJSONString(nums);
	}
	/**
	 * 获取一年12个月的文章数  
	 * @return
	 */
	@RequestMapping("/articleMonthOfYear-echarts")
	@ResponseBody
	public String getArticleMonthOfYear() {
		String json = "";
		List<Map> article = echartsService.selectArticleEchartsMonthOfYear();
		int[] nums = transToMonth(article);
		return JSON.toJSONString(nums);
	}
	/**
	 * 获取一个月的评论数据
	 * @return
	 */
	@RequestMapping("/comments-echarts")
	@ResponseBody
	public String getComments() {
		String json = "";
		List<Map> comments = echartsService.selectCommentsEcharts();
		int[] nums = transto(comments);
		return JSON.toJSONString(nums);
	}
	/**
	 * 获取一年的12个月的数据
	 * @return
	 */
	@RequestMapping("/commentsMonthOfYear-echarts")
	@ResponseBody
	public String getCommentsMonthOfYear() {
		String json = "";
		List<Map> comments = echartsService.selectCommentsEchartsMonthOfYear();
		int[] nums = transToMonth(comments);
		return JSON.toJSONString(nums);
	}
	
	/**
	 * 获取一个月的消息数
	 * @return
	 */
	@RequestMapping("/message-echarts")
	@ResponseBody
	public String getMessage() {
		String json = "";
		List<Map> message = echartsService.selectMessageEcharts();
		int[] nums = transto(message);
		return JSON.toJSONString(nums);
	}
	
	/**
	 * 获取一年12个月的消息数
	 * @return
	 */
	@RequestMapping("/messageMonthOfYear-echarts")
	@ResponseBody
	public String getMessageMonthOfYear() {
		String json = "";
		List<Map> message = echartsService.selectMessageEchartsMonthOfYear();
		int[] nums = transToMonth(message);
		return JSON.toJSONString(nums);
	}

	/**
	 * 获取
	 * @return
	 */
	@RequestMapping("/search-echarts")
	@ResponseBody
	public String getSearch() {
		// 获取所有词汇

		List<Search> list = echartsService.selectSearchKeywords();

		List legendData = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			legendData.add(list.get(i).getKeywords());
		}

		List<Map> seriesData = echartsService.selectSearchKeywordsAndNum();
		// 获取所有词汇加次数
		Map map = new HashMap<>();
		map.put("legendData", legendData);
		map.put("seriesData", seriesData);
		return JSONObject.toJSONString(map);

	}

	/**
	 * 获取本月的全部日期 返回格式为2018-01-01
	 * 
	 * @return
	 */
	public static List getDayListOfMonth() {
		List list = new ArrayList();
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);// 年份
		int month = cal.get(Calendar.MONTH) + 1;// 月份
		int maxday = cal.getActualMaximum(Calendar.DATE);// 获取一个月有多少天
		int day=cal.get(Calendar.DATE);
			for (int i = 1; i <= day; i++) {
			String m = String.valueOf(month);
			String j = String.valueOf(i);
			if (month < 10) {
				m = "0" + String.valueOf(month);
			}
			if (i < 10) {
				j = "0" + String.valueOf(i);
			}
			String aDate = String.valueOf(year) + "-" + (m) + "-" + j;
			list.add(aDate);
		}
		return list;
	}
	/**
	 * 得到一年的12个月份  返回格式为2018-01
	 * @return
	 */
	public static List getMonthListOfYear() {
		List list = new ArrayList();
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);// 年份
		int month = cal.get(Calendar.MONTH) + 1;// 月份
		for(int i=1;i<=month;i++) {
			String m=String.valueOf(i);
			if(i<10) {
				m="0"+String.valueOf(i);
			}
			String aDate = String.valueOf(year) + "-" + (m) ;
			list.add(aDate);
			
		}
		return list;
	}

}
