package com.ssm.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.dao.PhotoMapper;
import com.ssm.pojo.Photo;
import com.ssm.service.dao.PhotoService;

@Service
public class PhotoServiceImpl implements PhotoService {
	@Resource
	private PhotoMapper photoMapper;

	@Override
	public PageInfo<Photo> selectPhotoStatus(int pageNo, int pagesize) {
		PageHelper.startPage(pageNo, pagesize);
		List<Photo> list = photoMapper.selectPhotoStatus();
		PageInfo<Photo> pageInfo = new PageInfo<Photo>(list);
		return pageInfo;
	}

	@Override
	public List<Photo> selectPhotoAll() {
		// TODO Auto-generated method stub
		return photoMapper.selectPhoto();
	}

	@Override
	public int startPhoto(int id) {
		Photo photo = new Photo();
		photo.setId(id);
		photo.setStatus("发布");
		return photoMapper.updateByPrimaryKeySelective(photo);
	}

	@Override
	public int stopPhoto(int id) {
		Photo photo = new Photo();
		photo.setId(id);
		photo.setStatus("下架");
		return photoMapper.updateByPrimaryKeySelective(photo);
	}

	@Override
	public int deletePhotoById(int id) {
		// TODO Auto-generated method stub
		return photoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deletePhoto(int[] list) {
		int n = 0;
		for (int i = 0; i < list.length; i++) {
			photoMapper.deleteByPrimaryKey(list[i]);
			n++;
		}
		return n;
	}

	@Override
	public List<Photo> conditionalQueryPhoto(String datemin, String datemax, String photoName) {
		// TODO Auto-generated method stub
		return photoMapper.conditionalQueryPhoto(datemax, datemin, photoName);
	}

	@Override
	public Photo selectPhotoById(int id) {
		// TODO Auto-generated method stub
		return photoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updatePhoto(int id, String url, String content, String name) {
		// TODO Auto-generated method stub
		Photo photo = new Photo();
		photo.setId(id);
		photo.setContent(content);
		photo.setName(name);
		photo.setUrl(url);
		photo.setTime(new Date());

		return photoMapper.updateByPrimaryKeySelective(photo);
	}

	@Override
	public int addPhoto(String url, String content, String name) {
		// TODO Auto-generated method stub
		Photo photo=new Photo();
		photo.setContent(content);
		photo.setName(name);
		photo.setStatus("下架");
		photo.setTime(new Date());
		photo.setUrl(url);
		return photoMapper.insertSelective(photo);
	}

}
