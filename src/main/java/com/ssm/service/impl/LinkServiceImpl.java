package com.ssm.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.core.Link;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssm.dao.LinksMapper;
import com.ssm.pojo.Links;
import com.ssm.service.dao.LinkService;

@Service
public class LinkServiceImpl implements LinkService {
	@Resource
	private LinksMapper linksMapper;

	@Override
	public int addLinks(String category, String webname, String weburl, String username, String content,
			String telphone) {
		Links links=new Links();
		links.setCategory(category);
		links.setContent(content);
		links.setStatus("停用");
		links.setTelphone(telphone);
		links.setUrl(weburl);
		links.setWebname(webname);
		links.setTime(new Date());
		
		return linksMapper.insertSelective(links);
	}

	@Override
	public List<Links> selectLinks() {
		// TODO Auto-generated method stub
		return linksMapper.selectLink();
	}

	@Override
	public List<Links> conditionalQuery(String datemin, String datemax, String webname) {
		
		return linksMapper.conditionalQuery(datemin,datemax,webname);
	}

	@Override
	public int deleteLinksById(int id) {
		// TODO Auto-generated method stub
		return linksMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int stopLinks(int linkid) {
		// TODO Auto-generated method stub
		Links links=new Links();
		links.setId(linkid);
		links.setStatus("停用");
		return linksMapper.updateByPrimaryKeySelective(links);
	}

	@Override
	public int startLinks(int linkid) {
		Links links=new Links();
		links.setId(linkid);
		links.setStatus("启用");
		return linksMapper.updateByPrimaryKeySelective(links);
	}

	@Override
	public int deleteLinks(int[] list) {
		int n=0;
		for (int i = 0; i < list.length; i++) {
			linksMapper.deleteByPrimaryKey(list[i]);
			n++;
		}
		return n;
	}

	@Override
	public Links selectLinksById(int linkid) {
		// TODO Auto-generated method stub
		return linksMapper.selectByPrimaryKey(linkid);
	}

	@Override
	public int updateLinkById(int id, String category, String webname, String url, String content, String telphone) {
		// TODO Auto-generated method stub
		Links links=new Links();
		links.setId(id);
		links.setCategory(category);
		links.setContent(content);
		links.setTelphone(telphone);
		links.setWebname(webname);
		links.setUrl(url);
		return linksMapper.updateByPrimaryKeySelective(links);
	}

	
}
