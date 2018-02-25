package com.ssm.service.dao;

import java.util.List;
import java.util.Map;

import com.ssm.pojo.Article;
import com.ssm.pojo.Comments;

public interface RedisService {
	public List<Article> findArticleNewByRedis();

	public List<Article> findArticleRankByRedis();

	public List<Comments> findCommentsNewByRedis();

	public List<Map> findArticleTagByRedis();

}
