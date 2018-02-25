package com.ssm.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.ssm.annotation.Log;
import com.ssm.dao.ArticleMapper;
import com.ssm.dao.CategoryMapper;
import com.ssm.dao.CommentsMapper;
import com.ssm.dao.InformationMapper;
import com.ssm.dao.LinksMapper;
import com.ssm.dao.TagMapper;
import com.ssm.pojo.Article;
import com.ssm.pojo.Carousel;
import com.ssm.pojo.Comments;
import com.ssm.pojo.Information;
import com.ssm.pojo.Links;
import com.ssm.service.dao.CarouseService;
import com.ssm.service.dao.IndexService;
import com.ssm.service.dao.IpService;
import com.ssm.service.dao.PhotoService;
import com.ssm.service.dao.RedisService;
import com.ssm.util.IpUtil;
/**
 * 
	*@class IndexController
	*@description  首页控制层
 	*@author 彭江毅  
 	*@date  2018年2月10日下午7:03:38
	*@contact 741506070@qq.com
 */
@Controller
public class IndexController {

	@Resource
	private IndexService indexService;
	@Resource
	private RedisService redisservice;
	@Resource
	private IpService ipservice;
	@Resource
	private CarouseService carouselservice;
	/**
	 * 访问首页数据
	 * 
	 * @return
	 */
	
	@RequestMapping("/")
	@Log(description="访问首页")
	public ModelAndView index(HttpServletRequest req) {
		
		String ip=IpUtil.getIpAddr(req);
		ipservice.addIp(ip);
	
		ModelAndView mv = new ModelAndView();
		
		int pageNo = 1;
		int pageSize = 10;
		PageInfo<Article> article = indexService.selectArticle(pageNo, pageSize);
	
		mv.addObject("article", article);
		
		
		//存入缓存，每半小时更新一次，设置了过期时间，查点击排行
		List<Article> articleRank = redisservice.findArticleRankByRedis();
		mv.addObject("articleRank", articleRank);
		
		//存入缓存，每半小时更新一次，设置了过期时间，查最新的文章
		List<Article> articleNew =redisservice.findArticleNewByRedis();
		mv.addObject("articleNew", articleNew);

		int articleCount = indexService.selectArticleCount();
		mv.addObject("articleCount", articleCount);
		
		
		//存入缓存，每半小时更新一次，设置了过期时间，查最新的评论
		List<Comments> commentsNew=redisservice.findCommentsNewByRedis();
		mv.addObject("commentsNew",commentsNew);
	
		
		int commnetsCount=indexService.selectComments();
		mv.addObject("commentsCount",commnetsCount);
		
		int userCount=indexService.selectUserCount();
		mv.addObject("userCount",userCount);
		
		List<Links> links=indexService.selectLinksStatus();
		mv.addObject("links",links);
		
		
		int ipCount =ipservice.selectIpTotal();
		mv.addObject("ipCount",ipCount);
		
		List<Information> information=indexService.selectInformation();
		mv.addObject("information",information);
		
		
		List<Carousel> carousels=carouselservice.selectCarouselStatus();
		mv.addObject("carousel",carousels);
		
		mv.setViewName("index");
		return mv;

	}

	/**
	 * 访问时间轴页面----静态页面---暂时内容在timeline.js编辑
	 * 
	 * @return
	 */
	
	@RequestMapping("/timeline.html")
	@Log(description="访问博客历程")
	public ModelAndView timelineUI() {
		return new ModelAndView("timeline");

	}
	/**
	 * 404错误页面
	 * @return
	 */
	@RequestMapping("/404.html")
	public ModelAndView error_404(){
		
		return new ModelAndView("404");
		
		
	}
	/**
	 * 500错误页面
	 * @return
	 */
	@RequestMapping("/500.html")
	public ModelAndView error_500(){
		
		return new ModelAndView("500");
		
		
	}
}
