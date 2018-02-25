package com.ssm.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.dao.CarouselMapper;
import com.ssm.pojo.Carousel;
import com.ssm.service.dao.CarouseService;
@Service
public class CarouselServiceImpl implements CarouseService {
	@Resource
	private CarouselMapper carouselMapper;

	@Override
	public List<Carousel> selectCarouselStatus() {
		// TODO Auto-generated method stub
		return carouselMapper.selectCarouselStatus();
	}

	
	@Override
	public List<Carousel> selectCarousel() {
		// TODO Auto-generated method stub
		return carouselMapper.selectCarousel();
	}
	@Override
	public int startCarousel(int id) {
		Carousel carousel=new Carousel();
		carousel.setId(id);
		carousel.setStatus("发布");
		// TODO Auto-generated method stub
		return carouselMapper.updateByPrimaryKeySelective(carousel);
	}

	@Override
	public int stopCarousel(int id) {
		// TODO Auto-generated method stub
		Carousel carousel=new Carousel();
		carousel.setId(id);
		carousel.setStatus("下架");
		return carouselMapper.updateByPrimaryKeySelective(carousel);
	}

	@Override
	public int deleteCarouselById(int id) {
		// TODO Auto-generated method stub
		return carouselMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteCarousel(int[] list) {
		int n=0;
		for (int i = 0; i < list.length; i++) {
			carouselMapper.deleteByPrimaryKey(list[i]);
			n++;
		}
		return n;
	}

	@Override
	public List<Carousel> conditionalQueryCarousel(String datemin, String datemax, String carouselName) {
		// TODO Auto-generated method stub
		return carouselMapper.conditionalQueryCarousel(datemax,datemin,carouselName);
	}

	@Override
	public int addCarousel(String name, String url, String content) {
		// TODO Auto-generated method stub
		Carousel carousel=new Carousel();
		carousel.setContent(content);
		carousel.setName(name);
		carousel.setStatus("发布");
		carousel.setUrl(url);
		carousel.setTime(new Date());
		return carouselMapper.insertSelective(carousel);
	}

	@Override
	public int updateCarouselById(int id, String name, String content, String url) {
		Carousel carousel=new Carousel();
		carousel.setId(id);
		carousel.setContent(content);
		carousel.setName(name);
		carousel.setUrl(url);
		carousel.setTime(new Date());
		return carouselMapper.updateByPrimaryKeySelective(carousel);
	}

	@Override
	public Carousel selectCarouselById(int id) {
		// TODO Auto-generated method stub
		return carouselMapper.selectByPrimaryKey(id);
	}
}
