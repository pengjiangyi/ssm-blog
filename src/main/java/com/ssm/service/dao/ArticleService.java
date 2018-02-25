package com.ssm.service.dao;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.ssm.pojo.Article;
import com.ssm.pojo.Comments;
import com.ssm.pojo.Tag;

public interface ArticleService {

	Article selectArticleByID(int id);

	List<Map> selectArticleTag();

	List<Comments> selectArticleComments(int id);

	List<Comments> selectArticleCommentsAndChild(List<Comments> rootmenu);

	int addComments(int userid, int parentid, int articleid, String text, String time, String parentname, String ip);

	

	PageInfo<Article> selectArticleByTag(int pageNo, int pageSize, String tagname);

	int selectCommentsIDNew();

	int selectCommentsCountByArticleID(int id);

	List<Article> findArticle();

	List<Article> selectArticleTagComments();

	int addArticle(String title, String author, String content, String keywords, String des, String html, String text,
			int tag, String imgurl);

	int deleteArticleById(int id);

	int deleteArticle(int[] list);

	Article selectArticleAndTag(int id);

	int updateArticle(int id, String title, String author, String content, String keywords, String des, String html,
			String text, int tag, String imgurl);

	int AddReadCount(int id);

	List<Article> conditionalQueryArticle(String datemin, String datemax, String title);

	int  addSearch(String ip, String keywords);

	int selectArticleTotal();

	int selectArticleToDay();

	int selectArticleYesterDay();

	int selectArticleWeek();

	int selectArticleMonth();

	Article selectArtilceNewOne();
	

}
