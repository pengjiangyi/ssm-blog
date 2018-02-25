package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.dao.ArticleMapper;
import com.ssm.dao.TagMapper;
import com.ssm.pojo.Tag;
import com.ssm.service.dao.TagService;
@Service
public class TagServiceImpl implements TagService {
	@Resource
	private TagMapper tagMapper;
	@Resource
	private ArticleMapper articlemapper;
	

	@Override
	public List<Tag> selectTagAll() {
		// TODO Auto-generated method stub
		return tagMapper.selectTagAll();
	}

	@Override
	public int addTag(String name) {
		// TODO Auto-generated method stub
		Tag tag=new Tag();
		tag.setName(name);
		return tagMapper.insertSelective(tag);
	}

	@Override
	public Tag selectTagById(int id) {
		// TODO Auto-generated method stub
		return tagMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateTag(int id, String name) {
		// TODO Auto-generated method stub
		Tag tag=new Tag();
		tag.setId(id);
		tag.setName(name);
		return tagMapper.updateByPrimaryKeySelective(tag);
	}

	@Override
	public int deleteTagById(int id) {
		// TODO Auto-generated method stub
		return tagMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteArticle(int id) {
		// TODO Auto-generated method stub
		return articlemapper.deleteByTagId(id);
	}

	@Override
	public int deleteTag(int[] list) {
		// TODO Auto-generated method stub
		int n=0;
		for (int i = 0; i < list.length; i++) {
			tagMapper.deleteByPrimaryKey(list[i]);
			n++;
		}
		return n;
	}

	@Override
	public int deleteArticleAll(int[] list) {
		// TODO Auto-generated method stub
		int n=0;
		for (int i = 0; i < list.length; i++) {
			articlemapper.deleteByTagId(list[i]);
			n++;
		}
		return n;
	}



	
}
