package com.ssm.service.dao;

import java.util.List;

import com.ssm.pojo.Carousel;

public interface CarouseService {

	List<Carousel> selectCarouselStatus();

	int startCarousel(int id);

	int stopCarousel(int id);

	int deleteCarouselById(int id);

	int deleteCarousel(int[] list);

	List<Carousel> conditionalQueryCarousel(String datemin, String datemax, String carouselName);

	int addCarousel(String name, String url, String content);

	int updateCarouselById(int id, String name, String content, String url);

	Carousel selectCarouselById(int id);

	List<Carousel> selectCarousel();

	

}
