package com.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssm.pojo.Photo;

public interface PhotoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Photo record);

    int insertSelective(Photo record);

    Photo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Photo record);

    int updateByPrimaryKey(Photo record);

	List<Photo> selectPhoto();

	List<Photo> conditionalQueryPhoto(@Param("datemax")String datemax, @Param("datemin")String datemin,@Param("name") String photoName);
	
	/**
	 * 查询图片  发布状态
	 * @return
	 */
	List<Photo> selectPhotoStatus();
	
}