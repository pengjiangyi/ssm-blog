package com.ssm.service.dao;

import java.util.List;

import com.ssm.pojo.Links;

public interface LinkService {

	int addLinks(String category, String webname, String weburl, String username, String content, String telphone);

	List<Links> selectLinks();

	List<Links> conditionalQuery(String datemin, String datemax, String webname);

	int deleteLinksById(int id);

	int stopLinks(int linkid);

	int startLinks(int linkid);

	int deleteLinks(int[] list);

	Links selectLinksById(int linkid);

	int updateLinkById(int id, String category, String webname, String url, String content, String telphone);

}
