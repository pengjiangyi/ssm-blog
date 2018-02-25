package com.ssm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ssm.dao.ArticleMapper;
import com.ssm.dao.CommentsMapper;
import com.ssm.dao.IpMapper;
import com.ssm.dao.MessageMapper;
import com.ssm.dao.SearchMapper;
import com.ssm.service.dao.EchartsService;
@Service
public class EchartsServiceImpl implements EchartsService {
	@Resource
	private IpMapper ipMapper;
	@Resource
	private SearchMapper searchmapper;
	@Resource
	private ArticleMapper articlemapper;
	@Resource
	private CommentsMapper commentsmapper;
	@Resource
	private MessageMapper messagemapper;
	@Override
	public List<Map> selectIp() {
		// TODO Auto-generated method stub
		return ipMapper.selectIp();
	}

	@Override
	public List selectSearchKeywords() {
		// TODO Auto-generated method stub
		return searchmapper.selectSearchKeywords();
	}

	@Override
	public List<Map> selectSearchKeywordsAndNum() {
		// TODO Auto-generated method stub
		return  searchmapper.selectSearchKeywordsAndNum();
	}

	@Override
	public List<Map> selectArticleEcharts() {
		// TODO Auto-generated method stub
		return articlemapper.selectArticleEcharts();
	}

	@Override
	public List<Map> selectCommentsEcharts() {
		// TODO Auto-generated method stub
		return commentsmapper.selectCommentsEcharts();
	}

	@Override
	public List<Map> selectMessageEcharts() {
		// TODO Auto-generated method stub
		return messagemapper.selectMessageEcharts();
	}

	@Override
	public List<Map> selectIpMonthOfYear() {
		// TODO Auto-generated method stub
		return ipMapper.selectIpMonthOfYear();
	}

	@Override
	public List<Map> selectArticleEchartsMonthOfYear() {
		// TODO Auto-generated method stub
		return articlemapper.selectArticleEchartsMonthOfYear();
	}

	@Override
	public List<Map> selectCommentsEchartsMonthOfYear() {
		// TODO Auto-generated method stub
		return commentsmapper.selectCommentsEchartsMonthOfYear();
	}

	@Override
	public List<Map> selectMessageEchartsMonthOfYear() {
		// TODO Auto-generated method stub
		return messagemapper.selectMessageEchartsMonthOfYear();
	}
	
	
	
	
}
