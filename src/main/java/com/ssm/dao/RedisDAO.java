package com.ssm.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.ssm.pojo.Article;
import com.ssm.pojo.Comments;
import com.ssm.service.dao.ArticleService;
import com.ssm.service.dao.IndexService;
import com.ssm.util.SerializeUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.util.Pool;
/**
 * 
	*@class RedisDAO
	*@description  redis缓存数据
 	*@author 彭江毅  
 	*@date  2018年2月10日下午8:13:51
	*@contact 741506070@qq.com
 */
public class RedisDAO {
	@Resource
	private JedisPool jedisPool;
	@Resource
	private IndexService indexService;
	@Resource
	private ArticleService articleService;

	public RedisDAO(String ip, int port) {
		jedisPool = new JedisPool(ip, port);
	}

	public List<Article> getArticelNew() {
		String key = "articleNew";
		List<Article> articleNew = new ArrayList<>();
		try {
			Jedis jedis = jedisPool.getResource();
			try {

				if (jedis.get(key.getBytes()) != null) {
					articleNew = (List<Article>) SerializeUtil.getObject(key, jedis);
					System.out.println("缓存");
				} else {
					articleNew = indexService.selectArticleNew();
					SerializeUtil.setObjectAndTime(key, articleNew, jedis, 30 * 60);
					System.out.println("数据库");
				}
			} finally {
				jedis.close();
			}

		} catch (Exception e) {
		}

		return articleNew;
	}

	public List<Article> getArticleRank() {

		String key = "articleRank";
		List<Article> articleRank = new ArrayList<>();
		try {
			Jedis jedis = jedisPool.getResource();
			try {

				if (jedis.get(key.getBytes()) != null) {
					articleRank = (List<Article>) SerializeUtil.getObject(key, jedis);
					System.out.println("缓存");
				} else {
					articleRank = indexService.selectArticleRank();
					SerializeUtil.setObjectAndTime(key, articleRank, jedis, 30 * 60);
					System.out.println("数据库");
				}
			} finally {
				jedis.close();
			}

		} catch (Exception e) {
		}

		return articleRank;
	}

	public List<Comments> getCommentsNew() {
		String key = "commentsNew";
		List<Comments> commentsNew = new ArrayList<>();
		try {
			Jedis jedis = jedisPool.getResource();
			try {
				if (jedis.get(key.getBytes()) != null) {
					commentsNew = (List<Comments>) SerializeUtil.getObject(key, jedis);
					System.out.println("缓存");
				} else {
					commentsNew = indexService.selectCommentsNew();
					SerializeUtil.setObjectAndTime(key, commentsNew, jedis, 30 * 60);
					System.out.println("数据库");
				}
			} finally {
				jedis.close();
			}
		} catch (Exception e) {
		}

		return commentsNew;
	}

	public List<Map> getArticleTag() {
		
		List<Map> articleTag = new ArrayList<>();
		try {
			Jedis jedis = jedisPool.getResource();
			try {
				if (jedis.get("tag") != null) {
					articleTag = JSON.parseArray(jedis.get("tag"),Map.class);
					System.out.println("缓存");
				} else {
					articleTag = articleService.selectArticleTag();
					String tag=JSON.toJSONString(articleTag);
					jedis.setex("tag",60*30, tag);
					System.out.println("数据库");
				}
			} finally {
				jedis.close();
			}
		} catch (Exception e) {
		}

		return articleTag;
	}

}
