package com.ssm.dao;

import java.util.List;

import javax.ws.rs.core.Link;

import org.apache.ibatis.annotations.Param;

import com.ssm.pojo.Links;

public interface LinksMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Links record);

	int insertSelective(Links record);

	Links selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Links record);

	int updateByPrimaryKey(Links record);
	
	/**
	 * 查询所有友链
	 * @return
	 */
	List<Links> selectLink();
	
	/**
	 * 多条件查询
	 * @param datemin
	 * @param datemax
	 * @param webname
	 * @return
	 */
	List<Links> conditionalQuery(@Param("datemin")String datemin, @Param("datemax")String datemax, @Param("webname")String webname);
	/**
	 * 查询友链  启用状态
	 * @return
	 */
	List<Links> selectLinkStatus();

}