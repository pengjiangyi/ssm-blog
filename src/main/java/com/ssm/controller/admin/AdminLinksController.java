package com.ssm.controller.admin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ssm.pojo.Links;
import com.ssm.service.dao.LinkService;

/**
 * 
	*@class AdminLinksController
	*@description  后台友链管理
 	*@author 彭江毅  
 	*@date  2018年2月10日下午7:22:24
	*@contact 741506070@qq.com
 */
@Controller
public class AdminLinksController {

	@Resource
	private LinkService linksservice;

	/**
	 * 查找出所有的友情链接
	 * 
	 * @return
	 */
	@RequestMapping("/admin/links")
	public ModelAndView linksList() {
		List<Links> links = linksservice.selectLinks();
		ModelAndView mv = new ModelAndView();
		mv.addObject("links", links);
		mv.setViewName("admin/links-list");
		return mv;

	}

	/**
	 * 条件查询友情链接
	 * 
	 * @param datemin
	 *            开始时间
	 * @param datemax
	 *            结束时间
	 * @param webname
	 *            链接网站名称
	 * @return
	 */
	@RequestMapping("/admin/conditionalQueryLinks")
	public ModelAndView conditionalQueryLinks(String datemin, String datemax, String webname) {
		ModelAndView mv = new ModelAndView();
		List<Links> links = linksservice.conditionalQuery(datemin, datemax, webname);
		mv.setViewName("admin/links-list");
		mv.addObject("links", links);
		return mv;
	}

	/**
	 * 根据id删除友情链接
	 * 
	 * @param id
	 *            友链id
	 * @return
	 */
	@RequestMapping("/deleteLinksById")
	@ResponseBody
	public String deleteLinksById(int linksid) {
		int n = linksservice.deleteLinksById(linksid);
		if (n == 1) {
			return "success";
		}
		return "error";

	}

	/**
	 * 停用友链
	 * 
	 * @param linkid
	 * @return
	 */
	@RequestMapping("/stopLinks")
	@ResponseBody
	public String stopLinks(int linkid) {
		int n = linksservice.stopLinks(linkid);
		return "success";
	}

	/**
	 * 启动友链
	 * 
	 * @param linkid
	 * @return
	 */
	@RequestMapping("/startLinks")
	@ResponseBody
	public String startLinks(int linkid) {
		int n = linksservice.startLinks(linkid);
		return "success";
	}

	/**
	 * 批量删除
	 * 
	 * @param list
	 * @return
	 */
	@RequestMapping("/deleteLinks")
	@ResponseBody
	public String deleteLinks(@RequestParam("list") int list[]) {
		int n = linksservice.deleteLinks(list);
		System.out.println(n);
		return "success";
	}

	/**
	 * 返回添加友链的UI界面
	 * 
	 * @return
	 */
	@RequestMapping("/admin/links-add.html")
	public ModelAndView linkAddUI() {

		return new ModelAndView("admin/links-add");
	}

	/**
	 * 返回修改友链的UI界面
	 * 
	 * @return
	 */
	@RequestMapping("/admin/links-update.html/{linkid}")
	public ModelAndView linkUpdateUI(@PathVariable("linkid") int linkid) {

		ModelAndView mv = new ModelAndView();
		Links link = linksservice.selectLinksById(linkid);
		mv.addObject("link", link);
		mv.setViewName("admin/links-update");
		return mv;
	}

	/**
	 * 更新友链
	 * 
	 * @param id
	 *            友链id
	 * @param category
	 *            分类
	 * @param webname
	 *            网站名称
	 * @param url
	 *            网站URL
	 * @param content
	 *            网站简介
	 * @param telphone
	 *            联系人电话
	 * @return
	 */
	@RequestMapping("/admin/updateLink")
	@ResponseBody
	public String linkUpdateAjax(int id, String category, String webname, String url, String content, String telphone) {
		// System.out.println(id+category+webname+url+content+telphone);
		int n = linksservice.updateLinkById(id, category, webname, url, content, telphone);

		return "success";
	}

	/**
	 * 后台添加友链
	 * 
	 * @param category
	 *            网站分类
	 * @param webname
	 *            网站名称
	 * @param url
	 *            网站url
	 * @param content
	 *            网站简介
	 * @param telphone
	 *            联系人电话
	 * @return
	 */
	@RequestMapping("/admin/addLink")
	@ResponseBody
	public String linkAdd(String category, String webname, String url, String content, String telphone) {

		int n = linksservice.addLinks(category, webname, url, "", content, telphone);
		return "success";

	}

}
