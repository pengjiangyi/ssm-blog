package com.ssm.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.w3c.dom.stylesheets.LinkStyle;

import com.alibaba.fastjson.JSON;
import com.ssm.pojo.Article;
import com.ssm.service.dao.IndexService;
import com.ssm.util.ProtostuffUtil;
import com.ssm.util.SerializeUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestMyBatis {
	@Autowired
	private IndexService indexService;
	private static Logger logger = Logger.getLogger(TestMyBatis.class);

	JedisPool pool;
	Jedis jedis;

	@Before
	public void setUp() {
		pool = new JedisPool(new JedisPoolConfig(), "127.0.0.1");
		jedis = pool.getResource();
		// jedis.auth("pjy19960516");
	}

	@Test
	public void test() {

		long startTime = System.currentTimeMillis();
		/*
		 * List<byte []> list=ProtostuffUtil.serializeProtoStuffObjectList(articles,
		 * Article.class); List<Article>
		 * ar=ProtostuffUtil.deserializeProtoStuffDataListToObjectList(list,
		 * Article.class);
		 */
		/*
		 * String json=JSON.toJSONString(articles); jedis.set("xx", json);
		 * 
		 * List<Object> list=JSON.parseArray(jedis.get("xx")); for (Object object :
		 * list) { System.out.println(object); }
		 */

		/*
		 * byte[] bytes=SerializeUtil.serizlize(articles); String key="article";
		 * jedis.setex(key.getBytes(), 60, bytes); List<Article> list=(List<Article>)
		 * SerializeUtil.deserialize(jedis.get(key.getBytes()));
		 * 
		 * for (Article article : list) { System.out.println(article.getId()); }
		 */
		
		long endTime = System.currentTimeMillis();
		System.out.println("程序运行时间：" + (endTime - startTime) + "ms"); // 输出程序运行时间

	}
}