package com.ssm.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebParam.Mode;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ssm.pojo.Syslog;
import com.ssm.service.dao.SyslogService;
/**
 * 
	*@class AdminLogController
	*@description  系统日志管理
 	*@author 彭江毅  
 	*@date  2018年2月10日下午7:22:44
	*@contact 741506070@qq.com
 */
@Controller
public class AdminLogController {
	@Resource
	private SyslogService logservice;
	/**
	 * 查询所有的日志返回
	 * @return
	 */
	@RequestMapping("/admin/syslog")
	public ModelAndView syslogList() {
		List<Syslog> log=logservice.selectLog();
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/admin/syslog-list");
		mv.addObject("log",log);
		return mv;
		
		
	}
	/**
	 * 批量删除日志
	 * @param list
	 * @return
	 */
	@RequestMapping("/deleteLog")
	@ResponseBody
	public String deleteLog(@RequestParam("list")int list[]) {
		int n=logservice.deleteLog(list);
		
		return "success";
	}
	/**
	 * 删除日志单个
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteLogById")
	@ResponseBody
	public String deleteLogById(int id) {
		int n=logservice.deleteLogById(id);
		
		return "success";
	}
	/**
	 * 多条件查询日志
	 * @param datemin
	 * @param datemax
	 * @param type  日志类型 INFO ERROR
	 * @param userid	根据用户查询
	 * @return
	 */
	@RequestMapping("/admin/conditionalQueryLog")
	public ModelAndView conditionalQueryLog(String datemin,String datemax,String type,Integer userid) {
		List<Syslog> log=logservice.conditionalQueryLog(datemax,datemin,type,userid);
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/admin/syslog-list");
		mv.addObject("log",log);
		return mv;
	}
}
