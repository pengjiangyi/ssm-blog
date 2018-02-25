package com.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssm.pojo.Syslog;

public interface SyslogMapper {
    int deleteByPrimaryKey(Integer logid);

    int insert(Syslog record);

    int insertSelective(Syslog record);

    Syslog selectByPrimaryKey(Integer logid);

    int updateByPrimaryKeySelective(Syslog record);

    int updateByPrimaryKey(Syslog record);

	List<Syslog> selectLog();

	List<Syslog> conditionalQueryLog(@Param("datemax")String datemax, @Param("datemin")String datemin, @Param("type")String type,@Param("userid") Integer userid);
}