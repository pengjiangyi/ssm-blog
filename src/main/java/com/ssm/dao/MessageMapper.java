package com.ssm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssm.pojo.Message;

public interface MessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);
    	
    /**
     * 查询所有留言
     * @return
     */
    List<Message> selectMessage();
    
    /**
     * 查询留言总数
     * @return
     */
	int selectMessageCount();

	/**
	 * 最新留言id
	 * @return
	 */
	int selectNewId();
	
	/**
	 * 多条件查询
	 * @param datemin
	 * @param datemax
	 * @return
	 */
	List<Message> conditionalQueryMessage(@Param("datemin")String datemin, @Param("datemax")String datemax);
	
	/**
	 * 删除留言以及子留言
	 * @param id
	 * @param parentid
	 * @return
	 */
	int deleteMessageById(@Param("id")int id, @Param("parentid")int parentid);
	
	/**
	 * 本月echarts
	 * @return
	 */
	List<Map> selectMessageEcharts();


	int selectMessageToDay();

	int selectMessageWeek();

	int selectMessageMonth();

	int selectMessageYesterDay();
	/*年度统计
	 *
	 */
	List<Map> selectMessageEchartsMonthOfYear();
}