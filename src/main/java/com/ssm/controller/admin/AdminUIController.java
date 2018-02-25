package com.ssm.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ssm.service.dao.ArticleService;
import com.ssm.service.dao.CommentsService;
import com.ssm.service.dao.IpService;
import com.ssm.service.dao.ReplyService;
import com.ssm.service.dao.UserService;
/**
 * 
	*@class AdminUIController
	*@description 
 	*@author 彭江毅  
 	*@date  2018年2月10日下午7:42:07
	*@contact 741506070@qq.com
 */
@Controller
public class AdminUIController {
	@Resource
	private ArticleService articeservice;
	@Resource
	private CommentsService commentsservice;
	@Resource
	private ReplyService replyservice;
	
	@Resource
	private IpService ipservice;
	@Resource
	private UserService userservice;
	@RequestMapping("/admin/index")
	public ModelAndView AdminUI() {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("admin/index");
		return mv;
		
		
	}
	@RequestMapping("/admin/welcome.html")
	public ModelAndView welcomeUI() {
		long starttime=System.currentTimeMillis();
		int articleTotal=articeservice.selectArticleTotal();
		int articleToday=articeservice.selectArticleToDay();
		int articleYesterDay=articeservice.selectArticleYesterDay();
		int articleWeek=articeservice.selectArticleWeek();
		int articleMonth=articeservice.selectArticleMonth();
		
		
		int commentsTotal=commentsservice.selectCommentsTotal();
		int commentsToday=commentsservice.selectCommentsToDay();
		int commentsYesterDay=commentsservice.selectCommentsYesterDay();
		int commentsWeek=commentsservice.selectCommentsWeek();
		int commentsMonth=commentsservice.selectCommentsMonth();
		
		
		int ipTotal=ipservice.selectIpTotal();
		int ipToday=ipservice.selectIpToDay();
		int ipYesterDay=ipservice.selectIpYesterDay();
		int ipWeek=ipservice.selectIpWeek();
		int ipMonth=ipservice.selectIpMonth();
		
		
		int userTotal=userservice.selectUserTotal();
		int userToday=userservice.selectUserToDay();
		int userYesterDay=userservice.selectUserYesterDay();
		int userWeek=userservice.selectUserWeek();
		int userMonth=userservice.selectUserMonth();
		
		int messageTotal=replyservice.selectMessageTotal();
		int messageToday=replyservice.selectMessageToDay();
		int messageYesterDay=replyservice.selectMessageYesterDay();
		int messageWeek=replyservice.selectMessageWeek();
		int messageMonth=replyservice.selectMessageMonth();
		
		ModelAndView mv=new ModelAndView();
		mv.setViewName("admin/welcome");
		mv.addObject("articleTotal",articleTotal);
		mv.addObject("articleToDay",articleToday);
		mv.addObject("articleYesterDay",articleYesterDay);
		mv.addObject("articleWeek",articleWeek);
		mv.addObject("articleMonth",articleMonth);
		
		mv.addObject("messageTotal",messageTotal);
		mv.addObject("messageToDay",messageToday);
		mv.addObject("messageYesterDay",messageYesterDay);
		mv.addObject("messageWeek",messageWeek);
		mv.addObject("messageMonth",messageMonth);
		
		mv.addObject("ipTotal",ipTotal);
		mv.addObject("ipToDay",ipToday);
		mv.addObject("ipYesterDay",ipYesterDay);
		mv.addObject("ipWeek",ipWeek);
		mv.addObject("ipMonth",ipMonth);
		
		mv.addObject("userTotal",userTotal);
		mv.addObject("userToDay",userToday);
		mv.addObject("userYesterDay",userYesterDay);
		mv.addObject("userWeek",userWeek);
		mv.addObject("userMonth",userMonth);
		
		
		mv.addObject("commentsTotal",commentsTotal);
		mv.addObject("commentsToDay",commentsToday);
		mv.addObject("commentsYesterDay",commentsYesterDay);
		mv.addObject("commentsWeek",commentsWeek);
		mv.addObject("commentsMonth",commentsMonth);
		long endtime=System.currentTimeMillis();

		return mv;
		
		
	}
	
}
