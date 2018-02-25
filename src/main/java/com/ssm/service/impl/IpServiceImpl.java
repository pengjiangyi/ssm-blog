package com.ssm.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.dao.IpMapper;
import com.ssm.pojo.Ip;
import com.ssm.service.dao.IpService;
@Service
public class IpServiceImpl implements IpService {
	@Resource
	private IpMapper ipMapper;

	@Override
	public int addIp(String ip) {
		// TODO Auto-generated method stub
		Ip item=new Ip();
		item.setIp(ip);
		item.setTime(new Date());
		item.setStatus("");
		
		return ipMapper.insertSelective(item);
	}

	@Override
	public int selectIpTotal() {
		// TODO Auto-generated method stub
		return ipMapper.selectIpTotal();
	}

	@Override
	public int selectIpToDay() {
		// TODO Auto-generated method stub
		return ipMapper.selectIpToDay();
	}

	@Override
	public int selectIpYesterDay() {
		// TODO Auto-generated method stub
		return ipMapper.selectIpYesterDay();
	}

	@Override
	public int selectIpWeek() {
		// TODO Auto-generated method stub
		return ipMapper.selectIpWeek();
	}

	@Override
	public int selectIpMonth() {
		// TODO Auto-generated method stub
		return ipMapper.selectIpMonth();
	}

	
}
