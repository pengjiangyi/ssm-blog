package com.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssm.pojo.Carousel;

public interface CarouselMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Carousel record);

    int insertSelective(Carousel record);

    Carousel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Carousel record);

    int updateByPrimaryKey(Carousel record);
    
    /**
     * 查询轮播图 状态为发布
     * @return
     */
	List<Carousel> selectCarouselStatus();
	/**
	 * 多条件查询
	 * @param datemax
	 * @param datemin
	 * @param carouselName
	 * @return
	 */
	List<Carousel> conditionalQueryCarousel(@Param("datemax")String datemax, @Param("datemin")String datemin,@Param("name") String carouselName);
	/**
	 * 查询所有轮播图
	 * @return
	 */
	List<Carousel> selectCarousel();


	
}