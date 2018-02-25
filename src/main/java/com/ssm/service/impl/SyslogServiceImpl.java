package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.dao.SyslogMapper;
import com.ssm.pojo.Syslog;
import com.ssm.service.dao.SyslogService;

@Service
public class SyslogServiceImpl implements SyslogService {
	@Resource
	private SyslogMapper mapper;
	
	@Override
	public void addLog(Syslog log) {
		// TODO Auto-generated method stub
		mapper.insertSelective(log);
	}

	@Override
	public List<Syslog> selectLog() {
		// TODO Auto-generated method stub
		return mapper.selectLog();
	}

	@Override
	public int deleteLog(int[] list) {
		// TODO Auto-generated method stub
		for (int i = 0; i < list.length; i++) {
			mapper.deleteByPrimaryKey(list[i]);
		}
		return 0;
	}

	@Override
	public int deleteLogById(int id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Syslog> conditionalQueryLog(String datemax, String datemin, String type, Integer userid) {
		// TODO Auto-generated method stub
		return mapper.conditionalQueryLog(datemax,datemin,type,userid);
	}

}
