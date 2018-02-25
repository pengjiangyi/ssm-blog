package com.ssm.controller;

import java.net.URLDecoder;
import java.util.Random;

import javax.annotation.Resource;
import javax.ejb.LocalHome;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.slf4j.MDC;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ssm.annotation.Log;
import com.ssm.pojo.User;
import com.ssm.service.dao.LoginService;
/**
 * 
	*@class LoginController
	*@description  登录注册管理
 	*@author 彭江毅  
 	*@date  2018年2月10日下午7:04:19
	*@contact 741506070@qq.com
 */
@Controller
public class LoginController {

	@Resource
	private LoginService loginService;
	
	private static Logger log=Logger.getLogger(LoginController.class);
	/**
	 * 登录注册界面
	 * 
	 * @return
	 */
	@RequestMapping("/login.html")
	public ModelAndView loginUI(String from, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		// 注销的直接回首页
		if (from == null) {
			from = request.getContextPath() + "/";
		}

		mv.addObject("backurl", from);
		mv.setViewName("login");
		return mv;
	}

	/**
	 * 登录处理
	 * 
	 * @param email
	 *            邮箱
	 * @param password
	 *            密码
	 * @param request
	 *            http请求
	 * @return
	 */
	@RequestMapping("/login")
	@Log(description="用户登录")
	@ResponseBody
	public String login(String email, String password, HttpServletRequest request) {
	
		
		String msg = "";
		User user = loginService.checkLogin(email, password);
		if (user != null) {
			msg = "登录成功";
			request.getSession().setAttribute("user", user);
			request.getSession().setAttribute("username", user.getUsername());
			request.getSession().setAttribute("userid", user.getId()); 
			request.getSession().setAttribute("url", user.getUrl()); 
		} else {
			msg = "用户名或者密码错误";
		}
		return msg;
	}

	/**
	 * 用户注销
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/logout")
	@Log(description="用户退出登录")
	public ModelAndView logOut(HttpServletRequest request) {
		request.getSession().removeAttribute("user");
		return new ModelAndView("redirect:/login.html");

	}

	/**
	 * 用户注册
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param email
	 *            邮箱
	 * @return
	 */
	@RequestMapping("/register")
	@Log(description="注册")
	@ResponseBody
	public String register(String username, String password, String email) {
		String msg = "";
		User user = loginService.registerCheck(email);
		if (user != null) {
			msg = "邮箱已经被注册";
		} else {
			loginService.register(username,password,email);
			msg = "注册成功";
		}
		return msg;
	}

}
