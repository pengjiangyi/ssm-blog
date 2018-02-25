package com.ssm.service.dao;

import java.util.List;

import com.ssm.pojo.User;

public interface UserService {

	List<User> selectUser();

	int deleteUser(int[] list);

	int deleteUserById(int id);

	int stopUser(int id);

	int startUser(int id);

	List<User> conditionalQueryUser(String datemin, String datemax, String username);

	User selectUserById(int id);

	int updateUser(Integer id, String username, String password, String url);

	int addUser(String username, String password, String url);

	int selectUserTotal();

	int selectUserToDay();

	int selectUserWeek();

	int selectUserYesterDay();

	int selectUserMonth();

	User checkAdminLogin(String email, String password);

}
