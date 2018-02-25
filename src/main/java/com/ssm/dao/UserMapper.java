package com.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssm.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
  /**
   * 注册查重
   * @param email
   * @return
   */
    User registerCheck(String email);
    
    /**
     * 用户注册
     * @param user
     * @return
     */
    int register(User user);
    
    /**
     * 用户总数
     * @return
     */
    int userCount();
    
    /**
     * 登录验证
     * @param email
     * @param password
     * @return
     */
    User loginCheck(@Param("email")String email,@Param("password")String password);
    
    /**
     * 查找所有用户
     * @return
     */
	List<User> selectUser();

	List<User> conditionalQueryUser(@Param("datemax")String datemax, @Param("datemin")String datemin,@Param("username") String username);

	int selectUserMonth();

	int selectUserYesterDay();

	int selectUserWeek();

	int selectUserToDay();

	int selectUserTotal();
	
	/**
	 * 管理员登录验证
	 * @param email
	 * @param password
	 * @return
	 */
	User checkAdminLogin(@Param("email")String email, @Param("password")String password);
}