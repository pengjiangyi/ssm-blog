package com.ssm.service.dao;

import java.util.List;

import com.ssm.pojo.Comments;

public interface CommentsService {

	List<Comments> selectArticleComments();

	List<Comments> selectArticleCommentsAndChild(List<Comments> rootmenu);

	int deleteCommentsById(int id, int id2);

	int deleteComments(int[] list, int[] list2);

	List<Comments> conditionalQueryComments(String datemin, String datemax, Integer articleid);

	int selectCommentsTotal();

	int selectCommentsToDay();

	int selectCommentsWeek();

	int selectCommentsMonth();

	int selectCommentsYesterDay();

	

}
