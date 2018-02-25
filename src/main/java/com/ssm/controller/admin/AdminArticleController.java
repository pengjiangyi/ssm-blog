package com.ssm.controller.admin;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.util.List;

import javax.annotation.Resource;

import org.apache.lucene.codecs.lucene50.Lucene50StoredFieldsFormat.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.ssm.pojo.Article;
import com.ssm.pojo.Tag;
import com.ssm.service.dao.ArticleService;
import com.ssm.service.dao.ReplyService;
import com.ssm.service.dao.TagService;
import com.ssm.util.LuceneIndex;

/**
 * 
 * @class AdminArticleController
 * @description 后台文章管理页面
 * @author 彭江毅
 * @date 2018年2月10日下午7:05:06
 * @contact 741506070@qq.com
 */
@Controller
public class AdminArticleController {
	@Resource
	private ArticleService articleService;
	@Resource
	private TagService tagservice;
	@Resource
	private ReplyService replyservice;

	/**
	 * 查询所有文章
	 * 
	 * @return
	 */
	@RequestMapping("/admin/article")
	public ModelAndView articleList() {

		List<Article> article = articleService.selectArticleTagComments();
		ModelAndView mv = new ModelAndView();
		mv.addObject("article", article);
		mv.setViewName("/admin/article-list");
		return mv;
	}

	/**
	 * 返回添加文章界面，查询所有的标签
	 * 
	 * @return 返回指定视图
	 */
	@RequestMapping("/admin/article-add.html")
	public ModelAndView informationAddUI() {
		List<Tag> tag = tagservice.selectTagAll();
		ModelAndView mv = new ModelAndView();
		mv.addObject("tag", tag);
		mv.setViewName("/admin/article-add");
		return mv;
	}
	/**
	 * 添加文章的Action，执行动作
	 * @param title
	 * @param author
	 * @param content
	 * @param keywords
	 * @param des
	 * @param text
	 * @param html
	 * @param tag
	 * @param imgurl
	 * @return
	 */
	@RequestMapping("/addArticle")
	@ResponseBody
	public String addArticle(String title, String author, String content, String keywords, String des, String text,
			String html, int tag, String imgurl) {

		int n = articleService.addArticle(title, author, content, keywords, des, html, text, tag, imgurl);
		// 查询最新的文章进行添加索引;
		Article article = articleService.selectArtilceNewOne();
		LuceneIndex index = new LuceneIndex();
		try {
			index.addIndex(article);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
	/**
	 * 根据id删除文章
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteArticleById")
	@ResponseBody
	public String deleteArticleById(int id) {
		// 删除文章同时删除文章片的评论

		int n = articleService.deleteArticleById(id);
		int m = replyservice.deleteArticleComments(id);
		return "success";

	}
	/**
	 * 批量删除文章
	 * @param list
	 * @return
	 */
	@RequestMapping("/deleteArticle")
	@ResponseBody
	public String deleteArticleById(@RequestParam("list") int list[]) {

		int n = articleService.deleteArticle(list);
		int m = replyservice.deleteArticleCommentsAll(list);
		return "success";

	}
	/**
	 * 返回更新文章的界面
	 * @param id
	 * @return
	 */
	@RequestMapping("/admin/article-update.html/{id}")
	public ModelAndView updateArticleUI(@PathVariable("id") int id) {
		List<Tag> tag = tagservice.selectTagAll();
		Article article = articleService.selectArticleAndTag(id);
		System.out.println(JSON.toJSONString(article));

		ModelAndView mv = new ModelAndView();
		mv.addObject("article", article);
		mv.addObject("tag", tag);
		mv.setViewName("/admin/article-update");
		return mv;

	}
	/**
	 * 根据id更新文章
	 * @param id
	 * @param title
	 * @param author
	 * @param content
	 * @param keywords
	 * @param des
	 * @param text
	 * @param html
	 * @param tag
	 * @param imgurl
	 * @return
	 */
	@RequestMapping("/updateArticle")
	@ResponseBody
	public String updateArticle(int id, String title, String author, String content, String keywords, String des,
			String text, String html, int tag, String imgurl) {

		int n = articleService.updateArticle(id, title, author, content, keywords, des, html, text, tag, imgurl);

		return "success";
	}
	/**
	 * 多条件查询文章
	 * @param title   文章名
	 * @param datemin 开始时间
	 * @param datemax  结束时间
	 * @return 
	 */
	@RequestMapping("/admin/conditionalQueryArticle")
	public ModelAndView conditionalQueryArticle(String title, String datemin, String datemax) {

		List<Article> article = articleService.conditionalQueryArticle(datemin, datemax, title);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/admin/article-list");
		mv.addObject("article", article);
		return mv;

	}

}
