package com.ssm.service.impl;

import java.util.Date;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.annotation.Log;
import com.ssm.dao.UserMapper;
import com.ssm.pojo.User;
import com.ssm.service.dao.LoginService;
@Service
public class LoginServiceImpl implements LoginService {
	@Resource
	private UserMapper usermapper;
	

	@Override
	public User registerCheck(String email) {
		// TODO Auto-generated method stub
		return usermapper.registerCheck(email);
	}

	@Override
	
	public User checkLogin(String email, String password) {
		// TODO Auto-generated method stub
		return usermapper.loginCheck(email, password);
	}

	@Override
	public int register(String username, String password, String email) {
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
		return usermapper.insertSelective(u);
	}

}
