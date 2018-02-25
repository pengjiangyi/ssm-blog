package com.ssm.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ssm.pojo.User;
import com.ssm.service.dao.UserService;
/**
 * 
	*@class AdminLoginController
	*@description  后台登录
 	*@author 彭江毅   
 	*@date  2018年2月10日下午7:23:51
	*@contact 741506070@qq.com
 */
@Controller
public class AdminLoginController {
	@Resource
	private UserService userservice;
	/**
	 * 返回登录ui
	 * @return
	 */
	@RequestMapping("admin/login.html")
	public ModelAndView adminLoginUI()
	{
	return new ModelAndView("/admin/login");
		
		
	}
	/**
	 * 后台管理员登录
	 * @param email
	 * @param password
	 * @param req
	 * @return
	 */
	@RequestMapping("/admin/login")
	public ModelAndView adminLogin(String email,String password,HttpServletRequest req) {
		
		User admin=userservice.checkAdminLogin(email,password);
		if(admin!=null){
			req.getSession().setAttribute("admin", admin);
			return new ModelAndView("/admin/index");
		}
		return new ModelAndView("/admin/login");
	}
	
	/**
	 * 注销登录
	 * @param req
	 * @return
	 */
	@RequestMapping("/admin/logout")
	public ModelAndView adminLogout(HttpServletRequest req) {
		
		req.getSession().removeAttribute("admin");
		
		
		
		return new ModelAndView("/admin/login");
	}
	
}
