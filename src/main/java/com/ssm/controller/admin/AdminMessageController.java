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
import com.ssm.pojo.Message;
import com.ssm.service.dao.ArticleService;
import com.ssm.service.dao.CommentsService;
import com.ssm.service.dao.ReplyService;
/**
 * 
	*@class AdminMessageController
	*@description  管理留言
 	*@author 彭江毅  
 	*@date  2018年2月10日下午7:24:06
	*@contact 741506070@qq.com
 */
@Controller
public class AdminMessageController {
	//直接调用前台写的service
	@Resource
	private ReplyService replyService;
	/**
	 * 查询所有留言记录返回
	 * @return
	 */
	@RequestMapping("/admin/message")
	public ModelAndView messageList() {
		ModelAndView mv = new ModelAndView();
		List<Message> rootmenu = replyService.selectMessage();
		List<Message> menulist = replyService.selectMessageAndChild(rootmenu);

		mv.addObject("message", menulist);
		mv.setViewName("/admin/message-list");
		return mv;
	}
	/**
	 * 多条件查询留言
	 * @param datemin
	 * @param datemax
	 * @return
	 */
	@RequestMapping("/admin/conditionalQueryMessage")
	public ModelAndView conditionalQueryMessage(String datemin, String datemax) {
		ModelAndView mv = new ModelAndView();
		List<Message> rootmenu = replyService.conditionalQueryMessage(datemin, datemax);
		List<Message> menulist = replyService.selectMessageAndChild(rootmenu);

		mv.addObject("message", menulist);
		mv.setViewName("/admin/message-list");
		return mv;
	}
	/**
	 * 删除留言根据id
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteMessageById")
	@ResponseBody
	public String deleteMessageById(int id) {
		// 删除评论同时需要删除子评论
		int n = replyService.deleteMessageById(id, id);

		return "success";
	}
	/**
	 * 批量删除
	 * @param list
	 * @return
	 */
	@RequestMapping("/deleteMessage")
	@ResponseBody
	public String deleteMessage(@RequestParam("list") int list[]) {
		// 删除评论同时需要删除子评论
		int n = replyService.deleteMessage(list, list);

		return "success";
	}

}
