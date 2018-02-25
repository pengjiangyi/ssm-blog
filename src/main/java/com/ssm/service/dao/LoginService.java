package com.ssm.service.dao;

import com.ssm.pojo.User;

public interface LoginService {
	
	
	public User registerCheck(String email);

	public User checkLogin(String email, String password);

	public int register(String username, String password, String email);
}
