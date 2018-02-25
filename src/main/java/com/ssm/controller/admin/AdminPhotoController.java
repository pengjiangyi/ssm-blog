package com.ssm.controller.admin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ssm.pojo.Photo;
import com.ssm.service.dao.PhotoService;
/**
 * 
	*@class AdminPhotoController
	*@description  管理相册
 	*@author 彭江毅  
 	*@date  2018年2月10日下午7:24:58
	*@contact 741506070@qq.com
 */
@Controller
public class AdminPhotoController {
	@Resource
	private PhotoService photoService;
	/**
	 * 查询所有相册图片
	 * @return
	 */
	@RequestMapping("/admin/photo")
	public ModelAndView photoList() {
		ModelAndView mv = new ModelAndView();
		List<Photo> photo = photoService.selectPhotoAll();
		mv.addObject("photo", photo);

		mv.setViewName("/admin/photo-list");
		return mv;

	}
	
	/**
	 * 返回添加视图
	 * @return
	 */
	@RequestMapping("/photo-add.html")
	public ModelAndView PhotoAddUI() {
		return new ModelAndView("/admin/photo-add");

	}
	/**
	 * 多条件查询相册
	 * @param datemin
	 * @param datemax
	 * @param photoName
	 * @return
	 */
	@RequestMapping("/admin/conditionalQueryPhoto")
	public ModelAndView conditionalQueryPhoto(String datemin,String datemax,String photoName) {
		ModelAndView modelAndView=new ModelAndView();
		List<Photo> list=photoService.conditionalQueryPhoto(datemin,datemax,photoName);
		modelAndView.addObject("photo",list);
		
		modelAndView.setViewName("/admin/photo-list");
		return modelAndView;
		
	}
	/**
	 * 查询图片信息，返回到更新界面
	 * @param id
	 * @return
	 */
	@RequestMapping("/updatePhoto/{id}.html")
	public ModelAndView updatePhotoUI(@PathVariable("id")int id) {
		ModelAndView mv=new ModelAndView();
		Photo photo=photoService.selectPhotoById(id);
		mv.setViewName("/admin/photo-update");
		mv.addObject("photo",photo);
		
		return mv;
		
	}
	/**
	 * 更新相册
	 * @param id
	 * @param url
	 * @param content
	 * @param name
	 * @return
	 */
	@RequestMapping("/updatePhoto")
	@ResponseBody
	public  String updatePhoto(int id,String url,String content,String name) {
		int n=photoService.updatePhoto(id,url,content,name);
		
		return "success";
	}
	/**
	 * 添加图片
	 * @param url
	 * @param content
	 * @param name
	 * @return
	 */
	@RequestMapping("/addPhoto")
	@ResponseBody
	public String addPhoto(String url,String content,String name) {
		
		int n=photoService.addPhoto(url,content,name);
		return "success";
		
	}
	
	/**
	 * 发布图片
	 * @param id
	 * @return
	 */
	@RequestMapping("/startPhoto")
	@ResponseBody
	public String startPhoto(int id) {
		int n = photoService.startPhoto(id);
		return "success";
	}
	/**
	 * 下架图片
	 * @param id
	 * @return
	 */
	@RequestMapping("/stopPhoto")
	@ResponseBody
	public String stopPhoto(int id) {
		int n = photoService.stopPhoto(id);
		return "success";
	}
	/**
	 * 删除图片
	 * @param id
	 * @return
	 */
	@RequestMapping("/deletePhotoById")
	@ResponseBody
	public String deletePhotoById(int id) {
		int n = photoService.deletePhotoById(id);
		return "success";

	}
	/**
	 * 批量删除
	 * @param list
	 * @return
	 */
	@RequestMapping("/deletePhoto")
	@ResponseBody
	public String deletePhoto(@RequestParam("list") int list[]) {
		int n = photoService.deletePhoto(list);
		return "success";
	}

}
