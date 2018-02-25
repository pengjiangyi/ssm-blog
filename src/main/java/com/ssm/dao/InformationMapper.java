package com.ssm.dao;

import java.util.List;

import com.ssm.pojo.Information;

public interface InformationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Information record);

    int insertSelective(Information record);

    Information selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Information record);

    int updateByPrimaryKey(Information record);
    
    //查询所有消息
    List<Information> selectInformation();
}