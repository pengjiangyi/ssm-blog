package com.ssm.dao;

import java.util.List;
import java.util.Map;

import com.ssm.pojo.Ip;

public interface IpMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Ip record);

    int insertSelective(Ip record);

    Ip selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Ip record);

    int updateByPrimaryKey(Ip record);
    
    /**
     * 查询本月ip统计
     * @return
     */
	List<Map> selectIp();
	
	/**
	 * 查询本月ip数
	 * @return
	 */
	int selectIpMonth();
	
	/**
	 * 查询本周ip数
	 * @return
	 */
	int selectIpWeek();
	
	/*查询昨日ip数
	 *
	 */
	int selectIpYesterDay();
	
	/**
	 * 查询今日ip数
	 * @return
	 */
	int selectIpToDay();
	
	/**
	 * 查询ip总数,首页展示
	 * @return
	 */
	int selectIpTotal();
	
	/**
	 * 查询ip 年份12个月
	 * @return
	 */
	List<Map> selectIpMonthOfYear();
}