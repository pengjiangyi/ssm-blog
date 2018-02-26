package com.ssm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.ssm.annotation.Log;
import com.ssm.pojo.Article;
import com.ssm.pojo.Comments;
import com.ssm.pojo.PageBean;
import com.ssm.pojo.Tag;
import com.ssm.service.dao.ArticleService;
import com.ssm.service.dao.IndexService;
import com.ssm.service.dao.RedisService;
import com.ssm.service.dao.TaskTimer;
import com.ssm.util.IpUtil;
import com.ssm.util.LuceneIndex;
/**
 * 
	*@class ArticleController
	*@description  前台文章控制层
 	*@author 彭江毅  
 	*@date  2018年2月10日下午7:02:49
	*@contact 741506070@qq.com
 */
@Controller
public class ArticleController {
	@Resource
	private ArticleService articleservice;
	@Resource
	private IndexService indexService;
	@Resource
	private RedisService redisService;

	protected static Logger log = Logger.getLogger(ArticleController.class);
	/**
	 * 获取文章详情的actions
	 * @param id
	 * @return 返回文章详情
	 */
	@RequestMapping("/get-article-detail-{id}.html")
	@Log(description="访问文章详情")
	public ModelAndView getArticleDetail(@PathVariable("id") int id) {
		
	
		ModelAndView mv = new ModelAndView();
		Article articleInfo = articleservice.selectArticleByID(id);
		if(articleInfo==null) {
			return new ModelAndView("404");
		}
		mv.setViewName("detail");
		mv.addObject("articleInfo", articleInfo);
		
		if(articleInfo!=null)
		{
		//点击一次数量加1
		int n=articleservice.AddReadCount(id);
		}
		
		// 查询标签
		List<Map> tags = redisService.findArticleTagByRedis();
		mv.addObject("tags", tags);

		int commentCount = articleservice.selectCommentsCountByArticleID(id);
		mv.addObject("commentCount", commentCount);

		// 根据id查文章的评论
		List<Comments> rootmenu = articleservice.selectArticleComments(id);
		List<Comments> menulist = articleservice.selectArticleCommentsAndChild(rootmenu);
		mv.addObject("list", menulist);
		System.out.println(JSON.toJSONString(menulist));

		// 查询排行
		List<Article> articleRank = redisService.findArticleRankByRedis();
		mv.addObject("articleRank", articleRank);

		// 最新的
		List<Article> articleNew = redisService.findArticleRankByRedis();
		mv.addObject("articleNew", articleNew);

		List<Comments> commentsNew = redisService.findCommentsNewByRedis();
		mv.addObject("commentsNew", commentsNew);

		return mv;
	}
	/**
	 * 发表评论Action
	 * @param request  http请求
	 * @param userid	用户id
	 * @param articleid 文章id
	 * @param parentid  评论的父类id
	 * @param text     评论内容
	 * @param time     评论时间
	 * @param parentname  回复谁
	 * @return
	 */
	@RequestMapping("/addComments")
	@Log(description="发表文章评论")
	@ResponseBody
	public String addComments(HttpServletRequest request, int userid, int articleid, int parentid, String text,
			String time, String parentname) {
		String commentid;
		String ip = IpUtil.getIpAddr(request);
		int n = articleservice.addComments(userid, parentid, articleid, text, time, parentname, ip);
		if (n == 1) {
			commentid = String.valueOf(articleservice.selectCommentsIDNew());
		} else {
			commentid = "";
		}
		return commentid;
	}
	/**
	 * 访问文章专栏第一页
	 * @param pageNo
	 * @return
	 */
	@RequestMapping("/query/article/{page}.html")
	@Log(description="访问文章首页")
	public ModelAndView articleUI(@PathVariable("page") int pageNo) {

		ModelAndView mv = new ModelAndView();
		int pageSize = 10;
		// 分页查询文章
		PageInfo<Article> article = indexService.selectArticle(pageNo, pageSize);
		mv.addObject("article", article);

		// 查询标签

		List<Map> tags = redisService.findArticleTagByRedis();
		mv.addObject("tags", tags);

		// 查询排行
		List<Article> articleRank = redisService.findArticleRankByRedis();
		mv.addObject("articleRank", articleRank);

		// 最新的
		List<Article> articleNew = redisService.findArticleRankByRedis();
		mv.addObject("articleNew", articleNew);

		List<Comments> commentsNew = redisService.findCommentsNewByRedis();
		mv.addObject("commentsNew", commentsNew);

		mv.setViewName("article");
		return mv;
	}
	/**
	 * 根据标签查询文章的action
	 * @param tagname 标签名称
	 * @param pageNo  页码
	 * @return
	 */
	@RequestMapping("/query/tag/{tagname}/{page}.html")
	@Log(description="访问标签分类文章")
	public ModelAndView tagArtilceUI(@PathVariable("tagname") String tagname, @PathVariable("page") int pageNo) {
		ModelAndView mv = new ModelAndView();
		int pageSize = 10;
		// 分页查询文章通过tagname
		PageInfo<Article> article = articleservice.selectArticleByTag(pageNo, pageSize, tagname);
		mv.addObject("article", article);
		System.out.println(JSON.toJSONString(article));
		mv.addObject("tagname", tagname);
		// 查询标签

		List<Map> tags = redisService.findArticleTagByRedis();
		mv.addObject("tags", tags);
		// 查询排行
		List<Article> articleRank = redisService.findArticleRankByRedis();
		mv.addObject("articleRank", articleRank);

		// 最新的
		List<Article> articleNew = redisService.findArticleRankByRedis();
		mv.addObject("articleNew", articleNew);

		List<Comments> commentsNew = redisService.findCommentsNewByRedis();
		mv.addObject("commentsNew", commentsNew);

		mv.setViewName("tagArticle");
		return mv;
	}
	/**
	 * 博文检索
	 * @param keywords 关键字
	 * @param page    页码
	 * @param request  用于记录ip
	 * @return
	 */
	@RequestMapping("search/{keywords}/{page}.html")
	@Log(description="博文检索")
	public ModelAndView queryByLucene(@PathVariable("keywords") String keywords, @PathVariable("page") int page,HttpServletRequest request) {
		//记录用户的搜索记录以及ip
		String ip=IpUtil.getIpAddr(request);
		articleservice.addSearch(ip,keywords);
		
		ModelAndView mv = new ModelAndView();
		long startTime = System.currentTimeMillis(); // 获取开始时间
		// 确保索引已经建立
		LuceneIndex index = new LuceneIndex();
		List<Article> article = new ArrayList<>();
		try {
			article = index.searchBlog(keywords);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int pageSize = 10;
		int totalRecord = article.size();

		PageBean pb = new PageBean(page, pageSize, totalRecord);
		// 截取数据
		Integer toIndex = article.size() >= page * 5 ? page * 5 : article.size();
		List<Article> newList = article.subList((page - 1) * 5, toIndex);
		pb.setList(newList);
		

		long endTime = System.currentTimeMillis();
		double time=(double)(endTime-startTime)/1000;
		
		
		mv.addObject("time", (time + "秒"));// 获取结束时间
		mv.addObject("pageBean", pb);
		mv.addObject("article", newList);
		mv.addObject("keywords", keywords);
		mv.addObject("total", article.size());

		mv.setViewName("search");
		return mv;

	}
	/**
	 * 定时器调用的启动服务建立lucene索引
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateIndex")
	@Log(description="建立Lucene索引")
	public ModelAndView updateIndex() throws Exception {
		ModelAndView mv = new ModelAndView();
		List<Article> list = articleservice.findArticle();
		for (Article article : list) {
			LuceneIndex luceneIndex = new LuceneIndex();
			luceneIndex.updateIndex(article);
		}
		mv.setViewName("redirect:/");
		return mv;

	}

}
