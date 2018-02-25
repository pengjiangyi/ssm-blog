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
import com.ssm.pojo.Links;
import com.ssm.service.dao.InformationService;
/**
 * 
	*@class AdminInformationController
	*@description  消息推送管理
 	*@author 彭江毅  
 	*@date  2018年2月10日下午7:20:17
	*@contact 741506070@qq.com
 */
@Controller
public class AdminInformationController {
	@Resource
	private InformationService informationService;
	/**
	 * 返回消息推送视图
	 * @return
	 */
	@RequestMapping("/admin/information")
	public ModelAndView informationList() {

		List<Information> informations = informationService.selectInformation();
		ModelAndView mv = new ModelAndView();
		mv.addObject("information", informations);
		mv.setViewName("/admin/information-list");
		return mv;

	}
	/**
	 * 添加消息视图
	 * @return
	 */
	@RequestMapping("/admin/information-add.html")
	public ModelAndView informationAddUI() {

		return new ModelAndView("admin/information-add");
	}
	/**
	 * 添加消息
	 * @param text
	 * @return
	 */
	@RequestMapping("/admin/addInformation")
	@ResponseBody
	public String informationAdd(String text) {
		int n=informationService.addInformation(text);
		return "success";

	}
	/**
	 * 根据消息id,获取消息,返回到消息更新界面
	 * @param id
	 * @return
	 */
	@RequestMapping("/admin/information-update.html/{id}")
	public ModelAndView informationUpdateUI(@PathVariable("id") int id) {

		ModelAndView mv = new ModelAndView();
		Information information=informationService.selectInformationById(id);
		mv.addObject("information", information);
		mv.setViewName("admin/information-update");
		return mv;
	}
	/**
	 * 获取消息,更新消息
	 * @param id
	 * @param text
	 * @return
	 */
	@RequestMapping("/admin/updateInformation")
	@ResponseBody
	public String linkUpdateAjax(int id, String text) {
		// System.out.println(id+category+webname+url+content+telphone);
		int n=informationService.updateInformation(id,text);

		return "success";
	}

	
	
	
	/**
	 * 删除消息根据id
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteInformationById")
	@ResponseBody
	public String deleteInformationById(int id) {
		int n = informationService.deleteInformationById(id);

		return "success";
	}
	/**
	 * 批量删除消息
	 * @param list
	 * @return
	 */
	@RequestMapping("/deleteInformation")
	@ResponseBody
	public String deleteInformation(@RequestParam("list") int list[]) {
		int n = informationService.deleteInformation(list);

		return "success";
	}

}
