package com.ssm.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.ssm.annotation.Log;
import com.ssm.pojo.Message;
import com.ssm.service.dao.ReplyService;
import com.ssm.util.IpUtil;
/**
 * 
	*@class ReplyController
	*@description  关于我页面控制层
 	*@author 彭江毅  
 	*@date  2018年2月10日下午7:04:48
	*@contact 741506070@qq.com
 */
@Controller
public class ReplyController {

	@Resource
	private ReplyService replyService;
	/**
	 * 返回关于我页面并且查询数据
	 * @return
	 */
	@RequestMapping("/about.html")
	public ModelAndView aboutUI() {
		ModelAndView mv = new ModelAndView();
		//查询所有的数据
		List<Message> rootmenu = replyService.selectMessage();
		//递归查找，封装json
		List<Message> menulist = replyService.selectMessageAndChild(rootmenu);
		
		mv.addObject("list", menulist);
		mv.setViewName("about");
		
		int messageCount=replyService.selectMessageCount();
		mv.addObject("messageCount", messageCount);
		return mv;
	}

	@RequestMapping("/addMessage")
	@Log(description="留言")
	@ResponseBody
	public String addMessage(HttpServletRequest request, int userid, int parentid, String text, String time,
			String parentname) {
	
		String commentid;
		String ip = IpUtil.getIpAddr(request);
		int n = replyService.addMessage(userid, parentid, ip, text, time, parentname);
		if (n == 1)
		{
			//查询最新的留言ID,返回到前端
			commentid=String.valueOf(replyService.selectNewId());
			
		}
		else {
			commentid="";
		}

		return commentid;

	}

}
