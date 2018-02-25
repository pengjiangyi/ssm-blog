package com.ssm.service.dao;

import java.util.List;

import com.ssm.pojo.Syslog;

public interface SyslogService {

	void addLog(Syslog log);

	List<Syslog> selectLog();

	int deleteLog(int[] list);

	int deleteLogById(int id);

	List<Syslog> conditionalQueryLog(String datemax, String datemin, String type, Integer userid);

}
