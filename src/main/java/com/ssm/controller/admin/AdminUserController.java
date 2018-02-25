package com.ssm.controller.admin;

import java.util.List;

import javax.annotation.Resource;

import org.apache.lucene.codecs.lucene50.Lucene50StoredFieldsFormat.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ssm.pojo.User;
import com.ssm.service.dao.UserService;
/**
 * 
	*@class AdminUserController
	*@description  管理用户
 	*@author 彭江毅  
 	*@date  2018年2月10日下午7:42:16
	*@contact 741506070@qq.com
 */
@Controller
public class AdminUserController {
	@Resource
	private UserService userService;
	/**
	 * 查询所有用户
	 * @return
	 */
	@RequestMapping("/admin/user")
	public ModelAndView userList() {
		List<User> userlist = userService.selectUser();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/admin/user-list");
		mv.addObject("userlist", userlist);
		return mv;
	}
	/**
	 * 删除用户
	 * @param list
	 * @return
	 */
	@RequestMapping("/deleteUser")
	@ResponseBody
	public String deleteUser(@RequestParam("list") int list[]) {
		int n = userService.deleteUser(list);

		return "success";
	}
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteUserById")
	@ResponseBody
	public String deleteUserById(int id) {
		int n = userService.deleteUserById(id);

		return "success";
	}
	/**
	 * 停用用户
	 * @param id
	 * @return
	 */
	@RequestMapping("/stopUser")
	@ResponseBody
	public String stopUser(int id) {
		int n = userService.stopUser(id);

		return "success";
	}
	/*启用用户
	 * 
	 */
	@RequestMapping("/startUser")
	@ResponseBody
	public String startUser(int id) {
		int n = userService.startUser(id);

		return "success";
	}
	/**
	 * 多条件查询用户
	 * @param datemin
	 * @param datemax
	 * @param username
	 * @return
	 */
	@RequestMapping("/admin/conditionalQueryUser")
	public ModelAndView conditionalQueryUser(String datemin,String datemax,String username) {
		
		List<User> userlist=userService.conditionalQueryUser(datemin,datemax,username);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/admin/user-list");
		mv.addObject("userlist", userlist);
		return mv;
		
		
	}
	/**
	 * 添加用户UI
	 * @return
	 */
	@RequestMapping("/admin/user-add.html")
	public ModelAndView userAddUI() {
		return new ModelAndView("/admin/user-add");
		
	}
	/**
	 * 查询用户信息,返回更新视图
	 * @param id
	 * @return
	 */
	@RequestMapping("/admin/user-update.html/{id}")
	public ModelAndView userUpdateUI(@PathVariable("id")int id) {
		User user=userService.selectUserById(id);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/admin/user-update");
		mv.addObject("user", user);
		return mv;
	}
	/**
	 * 更新用户
	 * @param id
	 * @param username
	 * @param password
	 * @param url
	 * @return
	 */
	@RequestMapping("/admin/updateUser")
	public String updateUser(Integer id,String username,String password,String url)
	{
		int n=userService.updateUser(id,username,password,url);
		return "success";
		
		
	}
	/**
	 * 添加用户
	 * @param username
	 * @param password
	 * @param email
	 * @return
	 */
	@RequestMapping("/admin/addUser")
	public String addUser(String username,String password,String email)
	{
		int n=userService.addUser(username,password,email);
		return "success";
		
		
	}
}
