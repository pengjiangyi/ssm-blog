package com.ssm.controller.admin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.ssm.pojo.Article;
import com.ssm.pojo.Comments;
import com.ssm.service.dao.ArticleService;
import com.ssm.service.dao.CommentsService;
/**
 * 
	*@class AdminCommentsController
	*@description  后台评论管理
 	*@author 彭江毅  
 	*@date  2018年2月10日下午7:13:37
	*@contact 741506070@qq.com
 */
@Controller
public class AdminCommentsController {

	@Resource
	private CommentsService commentsService;
	/**
	 * 查询所有评论返回ui视图
	 * @return
	 */
	@RequestMapping("/admin/comments")
	public ModelAndView articleList() {
		ModelAndView mv = new ModelAndView();
		List<Comments> rootmenu = commentsService.selectArticleComments();
		List<Comments> menulist = commentsService.selectArticleCommentsAndChild(rootmenu);

		mv.addObject("comments", menulist);
		mv.setViewName("/admin/comments-list");
		return mv;
	}
	/**
	 * 多条件查询评论
	 * @param datemin  开始时间
	 * @param datemax  结束时间
	 * @param articleid 文章id
	 * @return
	 */
	@RequestMapping("/admin/conditionalQueryComments")
	public ModelAndView conditionalQueryComments(String datemin, String datemax, Integer articleid) {
		ModelAndView mv = new ModelAndView();
		List<Comments> rootmenu = commentsService.conditionalQueryComments(datemin, datemax, articleid);
		List<Comments> menulist = commentsService.selectArticleCommentsAndChild(rootmenu);

		mv.addObject("comments", menulist);
		mv.setViewName("/admin/comments-list");
		return mv;
	}
	/**
	 * 根据id删除评论
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteCommentsById")
	@ResponseBody
	public String deleteCommentsById(int id) {
		// 删除评论同时需要删除子评论
		int n = commentsService.deleteCommentsById(id, id);

		return "success";
	}
	/**
	 * 批量删除评论
	 * @param list  评论id数组
	 * @return
	 */
	@RequestMapping("/deleteComments")
	@ResponseBody
	public String deleteComments(@RequestParam("list") int list[]) {
		// 删除评论同时需要删除子评论
		int n = commentsService.deleteComments(list, list);

		return "success";
	}

}
