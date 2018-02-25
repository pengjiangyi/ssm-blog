package com.ssm.service.dao;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import com.ssm.pojo.Article;
import com.ssm.util.LuceneIndex;

/**
 * 
 * @author Administrator
 *
 *  定时任务，用来启动程序一分钟后，初始化lucene索引
 *
 * @since 2018/1/26
 */
@Service
public class TaskTimer implements ApplicationListener<ContextRefreshedEvent> {

	private Timer timer;
	@Resource
	private ArticleService articleservice;

	protected static Logger log = Logger.getLogger(TaskTimer.class);

	@Override
	public void onApplicationEvent(ContextRefreshedEvent e) {
		if (e.getApplicationContext().getParent() == null) {
			createIndex();

		}

	}

	private void createIndex() {
		Timer timer = new Timer("createIndex", true);
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				log.info("创建lucene索引");
				List<Article> list = articleservice.findArticle();
				for (Article article : list) {
					LuceneIndex luceneIndex = new LuceneIndex();
					try {
						luceneIndex.updateIndex(article);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						log.error(e);
					}
				}

			}
		}, 1000 * 60, 1000 * 60 * 60 * 24);

	}

}
