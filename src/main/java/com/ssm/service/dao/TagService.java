package com.ssm.service.dao;

import java.util.List;

import com.ssm.pojo.Tag;


public interface TagService {

	List<Tag> selectTagAll();

	int addTag(String name);

	Tag selectTagById(int id);

	int updateTag(int id, String name);

	int deleteTagById(int id);

	int deleteArticle(int id);

	int deleteTag(int[] list);

	int deleteArticleAll(int[] list);

}
