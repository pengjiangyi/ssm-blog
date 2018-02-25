package com.ssm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.dao.RedisDAO;
import com.ssm.pojo.Article;
import com.ssm.pojo.Comments;
import com.ssm.service.dao.RedisService;
@Service
public class RedisServiceImpl implements RedisService {
	@Resource
	private RedisDAO redisdao;

	@Override
	public List<Article> findArticleNewByRedis() {
		// TODO Auto-generated method stub
		return redisdao.getArticelNew();
	}

	@Override
	public List<Article> findArticleRankByRedis() {
		// TODO Auto-generated method stub
		
		return redisdao.getArticleRank();
	}

	@Override
	public List<Comments> findCommentsNewByRedis() {
		// TODO Auto-generated method stub
		
		return redisdao.getCommentsNew();
	}

	@Override
	public List<Map> findArticleTagByRedis() {
		// TODO Auto-generated method stub
		return redisdao.getArticleTag();
	}

}
