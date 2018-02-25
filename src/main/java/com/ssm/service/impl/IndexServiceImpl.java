package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.dao.ArticleMapper;
import com.ssm.dao.CommentsMapper;
import com.ssm.dao.InformationMapper;
import com.ssm.dao.LinksMapper;
import com.ssm.dao.UserMapper;
import com.ssm.pojo.Article;
import com.ssm.pojo.Comments;
import com.ssm.pojo.Information;
import com.ssm.pojo.Links;
import com.ssm.service.dao.IndexService;
@Service
public class IndexServiceImpl implements IndexService {
	@Resource
	private ArticleMapper articlemapper;
	@Resource
	private CommentsMapper commentsmapper;
	@Resource
	private UserMapper usermapper;
	@Resource
	private LinksMapper linksmapper;
	@Resource
	private InformationMapper infomapper;
	
	@Override
	public PageInfo<Article> selectArticle(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<Article> list=articlemapper.selectArticle();
		PageInfo<Article> pageInfo=new PageInfo<Article>(list);
		return pageInfo;
	}

	@Override
	public List<Article> findArticle() {
		// TODO Auto-generated method stub
		return articlemapper.selectArticle();
	}

	@Override
	public List<Article> selectArticleRank() {
		// TODO Auto-generated method stub
		return articlemapper.selectArticleRank();
	}

	@Override
	public List<Article> selectArticleNew() {
		// TODO Auto-generated method stub
		return articlemapper.selectArticleNew();
	}

	@Override
	public int selectArticleCount() {
		// TODO Auto-generated method stub
		return articlemapper.selectArticleCount();
	}

	@Override
	public List<Comments> selectCommentsNew() {
		// TODO Auto-generated method stub
		return commentsmapper.selectCommentsNew();
	}

	@Override
	public int selectCommentsCount() {
		// TODO Auto-generated method stub
		return commentsmapper.selectCommentsCount();
	}

	@Override
	public int selectUserCount() {
		// TODO Auto-generated method stub
		return usermapper.userCount();
	}

	@Override
	public List<Links> selectLinks() {
		// TODO Auto-generated method stub
		return linksmapper.selectLink();
	}
	
	@Override
	public List<Links> selectLinksStatus() {
		// TODO Auto-generated method stub
		return linksmapper.selectLinkStatus();
	}

	@Override
	public List<Information> selectInformation() {
		// TODO Auto-generated method stub
		return infomapper.selectInformation();
	}

	@Override
	public int selectComments() {
		// TODO Auto-generated method stub
		return commentsmapper.selectComments();
	}

}
