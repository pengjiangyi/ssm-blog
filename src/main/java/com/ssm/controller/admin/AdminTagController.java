package com.ssm.controller.admin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ssm.pojo.Information;
import com.ssm.pojo.Tag;
import com.ssm.service.dao.TagService;
/**
 * 
	*@class AdminTagController
	*@description  标签管理层
 	*@author 彭江毅  
 	*@date  2018年2月10日下午7:38:31
	*@contact 741506070@qq.com
 */
@Controller
public class AdminTagController {
	@Resource
	private TagService tagservice;
	/**
	 * 查询所有标签返回
	 * @return
	 */
	@RequestMapping("/admin/tag")
	public ModelAndView TagList() {
		List<Tag> tag=tagservice.selectTagAll();
		ModelAndView mv=new ModelAndView();
		mv.addObject("tag",tag);
		mv.setViewName("/admin/tag-list");
		return mv;
	}
	/**
	 * 添加标签UI
	 * @return
	 */
	@RequestMapping("/admin/tag-add.html")
	public ModelAndView tagAddUI() {

		return new ModelAndView("admin/tag-add");
	}
	/**
	 * 添加标签
	 * @param name
	 * @return
	 */
	@RequestMapping("/admin/addTag")
	@ResponseBody
	public String informationAdd(String name) {
		int n=tagservice.addTag(name);
		return "success";

	}
	/**
	 * 查询标签信息，返回更新UI
	 * @param id
	 * @return
	 */
	@RequestMapping("/admin/tag-update.html/{id}")
	public ModelAndView informationUpdateUI(@PathVariable("id") int id) {

		ModelAndView mv = new ModelAndView();
		Tag tag=tagservice.selectTagById(id);
		mv.addObject("tag", tag);
		mv.setViewName("admin/tag-update");
		return mv;
	}
	/**
	 * 更新标签
	 * @param id
	 * @param name
	 * @return
	 */
	@RequestMapping("/admin/updateTag")
	@ResponseBody
	public String linkUpdateAjax(int id, String name) {
		// System.out.println(id+category+webname+url+content+telphone);
		int n=tagservice.updateTag(id,name);

		return "success";
	}
	
	/**
	 * 删除标签
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteTagById")
	@ResponseBody
	public String deleteInformationById(int id) {
		//删除标签，将会删除标签下的文章
		
		int n = tagservice.deleteTagById(id);
		int y=tagservice.deleteArticle(id);

		return "success";
	}
	
	/**
	 * 批量删除标签
	 * @param list
	 * @return
	 */
	@RequestMapping("/deleteTag")
	@ResponseBody
	public String deleteTag(@RequestParam("list") int list[]) {
		//删除标签，将会删除标签下的文章
		
		int n = tagservice.deleteTag(list);
		int y=tagservice.deleteArticleAll(list);

		return "success";
	}


	
}
