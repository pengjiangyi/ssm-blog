package com.ssm.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ssm.annotation.Log;
import com.ssm.service.dao.LinkService;
/**
 * 
	*@class LinkController
	*@description  前台申请友链控制层
 	*@author 彭江毅  
 	*@date  2018年2月10日下午7:03:56
	*@contact 741506070@qq.com
 */
@Controller
public class LinkController {
	@Resource
	private LinkService linkservice;
	
	/**
	 * 申请友链Action
	 * @param category
	 * @param weburl
	 * @param webname
	 * @param username
	 * @param telphone
	 * @param content
	 * @return
	 */
	@RequestMapping("/addLinks")
	@Log(description="申请友链")
	@ResponseBody
	public String addLinks(String category,String weburl,String webname,String username,String telphone,String content) {
	
		int n=linkservice.addLinks(category,webname,weburl,username,content,telphone);
		
		
		
		
		
		return "ok";
	}
}
