package com.ssm.service.dao;

import java.util.List;

import com.ssm.pojo.Information;

public interface InformationService {

	List<Information> selectInformation();

	int deleteInformationById(int id);

	int deleteInformation(int[] list);

	int addInformation(String text);

	Information selectInformationById(int id);

	int updateInformation(int id, String text);

}
