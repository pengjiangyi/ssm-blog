package com.ssm.controller.admin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ssm.pojo.Carousel;
import com.ssm.service.dao.CarouseService;

/**
 * 
	*@class AdminCarouselController
	*@description  后台轮播管理
 	*@author 彭江毅  
 	*@date  2018年2月10日下午7:13:20
	*@contact 741506070@qq.com
 */
@Controller
public class AdminCarouselController {
	@Resource
	private CarouseService carouselservice;

	/**
	 * 返回轮播图的UI界面
	 * 
	 * @return
	 */
	@RequestMapping("/admin/carousel")
	public ModelAndView findCarousel() {
		ModelAndView mv = new ModelAndView();
		List<Carousel> list = carouselservice.selectCarousel();
		mv.addObject("carousel", list);
		mv.setViewName("admin/carousel-list");
		return mv;
	}

	/**
	 * 返回轮播添加的页面
	 * 
	 * @return
	 */
	@RequestMapping("/carousel-add.html")
	public ModelAndView CarouselAddUI() {

		return new ModelAndView("admin/carousel-add");
	}

	/**
	 * 修改轮播图的状态为发布
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/startCarousel")
	@ResponseBody
	public String startCarousel(int id) {
		int n = carouselservice.startCarousel(id);

		return "success";
	}

	/**
	 * 修改轮播图的状态为停用
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/stopCarousel")
	@ResponseBody
	public String stopCarousel(int id) {
		int n = carouselservice.stopCarousel(id);

		return "success";
	}

	/**
	 * 执行根据id删除轮播图
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteCarouselById")
	@ResponseBody
	public String deleteCarouselById(int id) {
		int n = carouselservice.deleteCarouselById(id);

		return "success";

	}

	/**
	 * 前台传来id数组,批量删除
	 * 
	 * @param list
	 * @return
	 */
	@RequestMapping("/deleteCarousel")
	@ResponseBody
	public String deleteCarousel(@RequestParam("list") int list[]) {
		int n = carouselservice.deleteCarousel(list);

		return "success";
	}

	/**
	 * 轮播图页面的多条件查询
	 * 
	 * @param datemin
	 * @param datemax
	 * @param carouselName
	 * @return
	 */
	@RequestMapping("/admin/conditionalQueryCarousel")
	public ModelAndView conditionalQueryCarousel(String datemin, String datemax, String carouselName) {
		ModelAndView mv = new ModelAndView();
		List<Carousel> list = carouselservice.conditionalQueryCarousel(datemin, datemax, carouselName);
		mv.addObject("carousel", list);
		mv.setViewName("admin/carousel-list");
		return mv;

	}

	/**
	 * 执行添加轮播图的业务
	 * 
	 * @param name
	 * @param url
	 * @param content
	 * @return
	 */
	@RequestMapping("/addCarousel")
	@ResponseBody
	public String addCarousel(String name, String url, String content) {
		int n = carouselservice.addCarousel(name, url, content);
		return "success";
	}

	/**
	 * 执行更新轮播图
	 * 
	 * @param id
	 * @param name
	 * @param url
	 * @param content
	 * @return
	 */
	@RequestMapping("/updateCarousel")
	@ResponseBody
	public String updateCarousel(int id, String name, String url, String content) {
		int n = carouselservice.updateCarouselById(id, name, content, url);
		return "success";
	}

	/**
	 * 查询数据返回到更新轮播图页面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/updateCarousel/{id}.html")
	public ModelAndView updateCarousel(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView();
		Carousel carousel = carouselservice.selectCarouselById(id);
		mv.setViewName("admin/carousel-update");
		mv.addObject("carousel", carousel);
		return mv;
	}

}
