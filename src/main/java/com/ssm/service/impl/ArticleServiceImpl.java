package com.ssm.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.dao.ArticleMapper;
import com.ssm.dao.CommentsMapper;
import com.ssm.dao.SearchMapper;
import com.ssm.dao.TagMapper;
import com.ssm.pojo.Article;
import com.ssm.pojo.Comments;
import com.ssm.pojo.Message;
import com.ssm.pojo.Search;
import com.ssm.pojo.Tag;
import com.ssm.service.dao.ArticleService;
import com.ssm.util.LuceneIndex;

@Service
public class ArticleServiceImpl implements ArticleService {
	@Resource
	private ArticleMapper articleMapper;
	@Resource
	private TagMapper tagmapper;
	@Resource
	private CommentsMapper commentsmapper;
	@Resource
	private SearchMapper searchmapper;

	@Override
	public Article selectArticleByID(int id) {
		// TODO Auto-generated method stub

		return articleMapper.selectArticleByID(id);
	}

	@Override
	public List<Map> selectArticleTag() {
		// TODO Auto-generated method stub
		return tagmapper.selectArticleTag();
	}

	@Override
	public List<Comments> selectArticleComments(int id) {

		return commentsmapper.selectCommentsByArticleId(id);
	}

	@Override
	public List<Comments> selectArticleCommentsAndChild(List<Comments> rootmenu) {

		List<Comments> menuList = new ArrayList<>();
		// 查出所有根数据
		for (int i = 0; i < rootmenu.size(); i++) {
			if (rootmenu.get(i).getParentid() == 0) {
				menuList.add(rootmenu.get(i));
			}

		}
		// 查找子节点
		for (Comments menu : menuList) {
			menu.setChildren(getChild(menu.getId(), rootmenu));
		}

		return menuList;
	}

	private List<Comments> getChild(Integer id, List<Comments> rootmenu) {
		List<Comments> childList = new ArrayList<>();
		for (Comments menu : rootmenu) {
			if (menu.getParentid() == id) {
				childList.add(menu);
			}
		}
		for (Comments menu : childList) {
			menu.setChildren(getChild(menu.getId(), rootmenu));
		}
		if (childList.size() == 0)
			return null;
		return childList;
	}

	@Override
	public int addComments(int userid, int parentid, int articleid, String text, String time, String parentname,
			String ip) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = null;
		try {
			date = format.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Comments comments = new Comments();
		comments.setArticleid(articleid);
		comments.setUserid(userid);
		comments.setIp(ip);
		comments.setParentname(parentname);
		comments.setTime(date);
		comments.setText(text);
		comments.setParentid(parentid);
		return commentsmapper.insertSelective(comments);

	}

	@Override
	public PageInfo<Article> selectArticleByTag(int pageNo, int pageSize, String tagname) {
		PageHelper.startPage(pageNo, pageSize);
		List<Article> list = articleMapper.selectArticleByTag(tagname);
		PageInfo<Article> pageInfo = new PageInfo<Article>(list);
		return pageInfo;
	}

	

	@Override
	public int selectCommentsIDNew() {
		// TODO Auto-generated method stub
		return commentsmapper.selectCommentsIDNew();
	}

	@Override
	public int selectCommentsCountByArticleID(int id) {
		// TODO Auto-generated method stub
		return commentsmapper.selectCommentsCount(id);
	}

	@Override
	public List<Article> findArticle() {
		// TODO Auto-generated method stub
		return articleMapper.findArticle();
	}

	@Override
	public List<Article> selectArticleTagComments() {
		// TODO Auto-generated method stub
		return articleMapper.selectArticle();
	}

	@Override
	public int addArticle(String title, String author, String content, String keywords, String des, String html,
			String text, int tag, String imgurl) {
		// TODO Auto-generated method stub
		Article article = new Article();
		article.setAuthor(author);
		article.setCategoryid(1);
		article.setContent(content);
		article.setTitle(title);
		article.setDes(des);
		article.setHtml(html);
		article.setText(text);
		article.setImgurl(imgurl);
		article.setTagid(tag);
		article.setCreatetime(new Date());
		article.setReadcount(0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		
		String year_moth=sdf.format(new Date());
		article.setYm(year_moth);
		article.setKeywords(keywords);
		
		

		return articleMapper.insertSelective(article);
	}

	@Override
	public int deleteArticleById(int id) {
		// TODO Auto-generated method stub
		
		LuceneIndex luceneIndex=new LuceneIndex();
		

		
		try {
		luceneIndex.deleteIndex(String.valueOf(id));
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return articleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteArticle(int[] list) {
		// TODO Auto-generated method stub
		int n=0;
		for (int i = 0; i < list.length; i++) {
			LuceneIndex luceneIndex=new LuceneIndex();
			
			try {
				luceneIndex.deleteIndex(String.valueOf(list[i]));
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
			
			
			articleMapper.deleteByPrimaryKey(list[i]);
			n++;
		}
		return n;
	}

	@Override
	public Article selectArticleAndTag(int id) {
		// TODO Auto-generated method stub
		return articleMapper.selectArticleAndTag(id);
	}

	@Override
	public int updateArticle(int id,String title, String author, String content, String keywords, String des, String html,
			String text, int tag, String imgurl) {
		Article article = new Article();
		article.setId(id);
		article.setAuthor(author);
		article.setContent(content);
		article.setTitle(title);
		article.setDes(des);
		article.setHtml(html);
		article.setText(text);
		article.setImgurl(imgurl);
		article.setTagid(tag);
		article.setKeywords(keywords);
	LuceneIndex luceneIndex=new LuceneIndex();
		

		
		try {
		luceneIndex.updateIndex(article);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return articleMapper.updateByPrimaryKeySelective(article);
	}

	@Override
	public int AddReadCount(int id) {
		// TODO Auto-generated method stub
		Article article=articleMapper.selectArticleByID(id);
		article.setReadcount(article.getReadcount()+1);
		articleMapper.updateByPrimaryKeySelective(article);
		return 0;
	}

	@Override
	public List<Article> conditionalQueryArticle(String datemin, String datemax, String title) {
		// TODO Auto-generated method stub
		return articleMapper.conditionalQueryArticle(datemin,datemax,title);
	}

	@Override
	public int addSearch(String ip, String keywords) {
		// TODO Auto-generated method stub
		Search search=new Search();
		search.setIp(ip);
		search.setKeywords(keywords);
		search.setTime(new Date());
		return searchmapper.insertSelective(search);
	}

	@Override
	public int selectArticleTotal() {
		// TODO Auto-generated method stub
		return articleMapper.selectArticleCount();
	}

	@Override
	public int selectArticleToDay() {
		// TODO Auto-generated method stub
		return articleMapper.selectArticleToDay();
	}

	@Override
	public int selectArticleYesterDay() {
		// TODO Auto-generated method stub
		return articleMapper.selectArticleYesterDay();
	}

	@Override
	public int selectArticleWeek() {
		// TODO Auto-generated method stub
		return articleMapper. selectArticleWeek();
	}

	@Override
	public int selectArticleMonth() {
		// TODO Auto-generated method stub
		return articleMapper.selectArticleMonth();
	}

	@Override
	public Article selectArtilceNewOne() {
		// TODO Auto-generated method stub
		return articleMapper.selectArtilceNewOne();
	}

}
