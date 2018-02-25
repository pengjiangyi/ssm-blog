package com.ssm.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.ssm.annotation.Log;
import com.ssm.pojo.Photo;
import com.ssm.service.dao.PhotoService;
/**
 * 
	*@class PhotoController
	*@description  相册管理
 	*@author 彭江毅  
 	*@date  2018年2月10日下午7:04:37
	*@contact 741506070@qq.com
 */
@Controller
public class PhotoController {
	@Resource
	private PhotoService photoservice;

	@RequestMapping("/query/photo/{page}.html")
	@Log(description="访问相册")
	public ModelAndView queryPhoto(@PathVariable("page") int pageNo) {
		int pagesize=12;
		PageInfo<Photo> photo=photoservice.selectPhotoStatus(pageNo,pagesize);
		ModelAndView mv = new ModelAndView();
		mv.addObject("photo",photo);
		mv.setViewName("pic");
		return mv;
                                               
	}

}
