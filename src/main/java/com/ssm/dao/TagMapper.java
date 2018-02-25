package com.ssm.dao;

import java.util.List;
import java.util.Map;

import com.ssm.pojo.Tag;

public interface TagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tag record);

    int insertSelective(Tag record);

    Tag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);
    
    /**
     * 侧栏标签查询 没有文章不显示标签
     * @return
     */
	List<Map> selectArticleTag();
	
	/**
	 * 没有文章 显示标签下数量为0
	 * @return
	 */
	List<Tag> selectTagAll();
}