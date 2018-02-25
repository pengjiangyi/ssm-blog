package com.ssm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ssm.pojo.Comments;

public interface CommentsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Comments record);

    int insertSelective(Comments record);

    Comments selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comments record);

    int updateByPrimaryKey(Comments record);
    
    List<Comments> selectCommentsNew();
    
    int selectCommentsCount();
    /**
     * 根据文章查评论
     * @param id
     * @return
     */
	List<Comments> selectCommentsByArticleId(int id);
	/**
	 * 查询最新评论
	 * @return
	 */
	int selectCommentsIDNew();
	/**
	 * 查询评论总数 parentid=0
	 * @param id
	 * @return
	 */
	int selectCommentsCount(int id);
	/**
	 * 删除评论
	 * @param id
	 * @return
	 */
	int deleteArticleComments(int id);
	
	/**
	 * 查询所有的评论
	 * @return
	 */
	List<Comments> selectAll();
	/**
	 * 查询评论以及字评论
	 * @param id
	 * @param parentid
	 * @return
	 */
	int deleteCommentsAndChild(@Param("id")int id,@Param("parentid") int parentid);
	
	/**
	 * 多条件查询
	 * @param datemin
	 * @param datemax
	 * @param articleid
	 * @return
	 */
	List<Comments> conditionalQueryComments(@Param("datemin")String datemin,@Param("datemax") String datemax, @Param("articleid")Integer articleid);
	
	/**
	 * 查询评论统计echarts
	 * @return
	 */
	List<Map> selectCommentsEcharts();
	
	/**
	 * 查询今日文章发布数
	 * @return
	 */
	int selectCommentsToDay();
	
	/**
	 * 查询本周文章发布数
	 * @return
	 */
	int selectCommentsWeek();
	/**
	 * 查询本月文章发布数
	 * @return
	 */
	int selectCommentsMonth();
	/**
	 * 查询文章总数
	 * @return
	 */
	int selectCommentsTotal();
	
	/**
	 * 查询昨日文章发布数
	 * @return
	 */
	int selectCommentsYesterDay();
	/**
	 * 查询一年12个月的文章统计
	 * @return
	 */
	List<Map> selectCommentsEchartsMonthOfYear();
	
	/**
	 * 查询所有评论数量
	 * @return
	 */
	int selectComments();
}