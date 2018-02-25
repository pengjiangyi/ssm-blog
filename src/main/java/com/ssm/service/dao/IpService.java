package com.ssm.service.dao;

public interface IpService {

	int addIp(String ip);

	int selectIpTotal();

	int selectIpToDay();

	int selectIpYesterDay();

	int selectIpWeek();

	int selectIpMonth();

	

}
