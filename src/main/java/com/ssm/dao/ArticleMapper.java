package com.ssm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ssm.pojo.Article;

public interface ArticleMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Article record);

	int insertSelective(Article record);

	Article selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Article record);

	int updateByPrimaryKeyWithBLOBs(Article record);

	int updateByPrimaryKey(Article record);

	//查询首页文章
	List<Article> selectArticle();

	//查询点击排行
	List<Article> selectArticleRank();

	//查询最新文章
	List<Article> selectArticleNew();

	//查询文章总数
	int selectArticleCount();
	
	//根据id查文章
	Article selectArticleByID(int id);
	
	/**
	 * 根据标签查文章
	 * @param tagname
	 * @return
	 */
	List<Article> selectArticleByTag(String tagname);
	/**
	 * 查询所有文章
	 * @return
	 */
	List<Article> findArticle();
	/**
	 * 根据标签删除文章
	 * @param id
	 * @return
	 */
	int deleteByTagId(int id);
	
	/**
	 * 查询文章和标签
	 * @param id
	 * @return
	 */
	Article selectArticleAndTag(int id);
	/**
	 * 多条件查询文章
	 * @param datemin
	 * @param datemax
	 * @param title
	 * @return
	 */
	List<Article> conditionalQueryArticle(@Param("datemin")String datemin, @Param("datemax")String datemax, @Param("title")String title);
	
	/**
	 * 查询本月文章统计
	 * @return
	 */
	List<Map> selectArticleEcharts();
	
	/**
	 * 查询今日文章数
	 * @return
	 */
	int selectArticleToDay();
	/**
	 * 查询昨日文章数
	 * @return
	 */
	int selectArticleYesterDay();
	
	/**
	 * 查询本周文章数
	 * @return
	 */
	int selectArticleWeek();
	
	/**
	 * 查询本月文章数
	 * @return
	 */
	int selectArticleMonth();
	
	/**
	 * 查询年度文章统计
	 * @return
	 */
	List<Map> selectArticleEchartsMonthOfYear();
	
	/**
	 * 查询最新的一篇文章
	 * @return
	 */
	Article selectArtilceNewOne();

	
}