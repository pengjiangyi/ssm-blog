package com.ssm.service.dao;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.ssm.pojo.Article;
import com.ssm.pojo.Comments;
import com.ssm.pojo.Information;
import com.ssm.pojo.Links;

public interface IndexService {
	PageInfo<Article> selectArticle(int pageNo,int pageSize);
	
	List<Article> findArticle();

	List<Article> selectArticleRank();

	List<Article> selectArticleNew();

	int selectArticleCount();

	List<Comments> selectCommentsNew();

	int selectCommentsCount();

	int selectUserCount();

	List<Links> selectLinks();

	List<Information> selectInformation();

	List<Links> selectLinksStatus();
	
	
	//查询评论的数量
	int selectComments();

	

	

}
