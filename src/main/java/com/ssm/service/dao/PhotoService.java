package com.ssm.service.dao;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.ssm.pojo.Photo;

public interface PhotoService {



	List<Photo> selectPhotoAll();

	int startPhoto(int id);

	int stopPhoto(int id);

	int deletePhotoById(int id);

	int deletePhoto(int[] list);

	List<Photo> conditionalQueryPhoto(String datemin, String datemax, String photoName);

	Photo selectPhotoById(int id);

	int updatePhoto(int id, String url, String content, String name);

	int addPhoto(String url, String content, String name);

	PageInfo<Photo> selectPhotoStatus(int pageNo, int pagesize);


}
