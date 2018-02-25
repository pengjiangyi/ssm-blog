package com.ssm.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.dao.UserMapper;
import com.ssm.pojo.User;
import com.ssm.service.dao.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Resource
	private UserMapper userMapper;

	@Override
	public List<User> selectUser() {
		// TODO Auto-generated method stub
		return userMapper.selectUser();
	}

	@Override
	public int deleteUser(int[] list) {
		// TODO Auto-generated method stub
		for (int i = 0; i < list.length; i++) {
			userMapper.deleteByPrimaryKey(list[i]);
		}
		return 0;
	}

	@Override
	public int deleteUserById(int id) {
		// TODO Auto-generated method stub
		return userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int stopUser(int id) {
		// TODO Auto-generated method stub
		User user=new User();
		user.setId(id);
		user.setStatus("停用");
		return userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public int startUser(int id) {
		// TODO Auto-generated method stub
		User user=new User();
		user.setId(id);
		user.setStatus("启用");
		return userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public List<User> conditionalQueryUser(String datemin, String datemax, String username) {
		// TODO Auto-generated method stub
		return userMapper.conditionalQueryUser(datemax,datemin,username);
	}

	@Override
	public User selectUserById(int id) {
		// TODO Auto-generated method stub
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateUser(Integer id, String username, String password, String url) {
		// TODO Auto-generated method stub
		User user=new User();
		user.setId(id);
		user.setPassword(password);
		user.setUrl(url);
		user.setUsername(username);
		return userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public int addUser(String username, String password, String email) {
		// TODO Auto-generated method stub
		User u = new User();
		u.setEmail(email);
		u.setPassword(password);
		u.setUsername(username);
		Random random = new Random();
		int x = random.nextInt(360);
		String url = x + ".png";
		u.setUrl(url);
		u.setLevel("用户");
		u.setStatus("启用");
		u.setRegistertime(new Date());
		return userMapper.insertSelective(u);
	}

	@Override
	public int selectUserTotal() {
		// TODO Auto-generated method stub
		return userMapper.selectUserTotal();
	}

	@Override
	public int selectUserToDay() {
		// TODO Auto-generated method stub
		return userMapper.selectUserToDay();
	}

	@Override
	public int selectUserWeek() {
		// TODO Auto-generated method stub
		return userMapper.selectUserWeek();
	}

	@Override
	public int selectUserYesterDay() {
		// TODO Auto-generated method stub
		return userMapper.selectUserYesterDay();
	}

	@Override
	public int selectUserMonth() {
		// TODO Auto-generated method stub
		return userMapper.selectUserMonth();
	}

	@Override
	public User checkAdminLogin(String email, String password) {
		// TODO Auto-generated method stub
		return userMapper.checkAdminLogin(email,password);
	}
}
