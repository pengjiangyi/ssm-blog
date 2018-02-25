package com.ssm.dao;

import java.util.List;
import java.util.Map;

import com.ssm.pojo.Search;

public interface SearchMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Search record);

    int insertSelective(Search record);

    Search selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Search record);

    int updateByPrimaryKey(Search record);
    /**
     * 根据关键字统计数量 去重查询
     * @return
     */
	List selectSearchKeywords();
	/**
	 * 查询关键字以及对应的数量 
	 * @return
	 */
	List<Map> selectSearchKeywordsAndNum();
}