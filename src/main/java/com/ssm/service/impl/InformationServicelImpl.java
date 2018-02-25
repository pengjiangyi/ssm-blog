package com.ssm.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.dao.InformationMapper;
import com.ssm.pojo.Information;
import com.ssm.service.dao.InformationService;
@Service
public class InformationServicelImpl implements InformationService {
	@Resource
	private InformationMapper informationMapper;

	@Override
	public List<Information> selectInformation() {
		// TODO Auto-generated method stub
		return informationMapper.selectInformation();
	}

	@Override
	public int deleteInformationById(int id) {
		// TODO Auto-generated method stub
		return informationMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteInformation(int[] list) {
		int n=0;
		for (int i = 0; i < list.length; i++) {
			informationMapper.deleteByPrimaryKey(list[0]);
			n++;
		}
		return n;
	}

	@Override
	public int addInformation(String text) {
		// TODO Auto-generated method stub
		Information information=new Information();
		information.setText(text);
		information.setTime(new Date());
		return informationMapper.insertSelective(information);
	}

	@Override
	public Information selectInformationById(int id) {
		// TODO Auto-generated method stub
		return informationMapper.selectByPrimaryKey(id);
	}


	@Override
	public int updateInformation(int id, String text) {
		// TODO Auto-generated method stub
		Information  information=new Information();
		information.setId(id);
		information.setText(text);
		return informationMapper.updateByPrimaryKeySelective(information);
	}
}
