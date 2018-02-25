package com.ssm.service.dao;

import java.util.List;

import com.ssm.pojo.Message;


public interface ReplyService {

	List<Message> selectMessage();

	List<Message> selectMessageAndChild(List<Message> rootmenu);

	int addMessage(int userid, int parentid, String ip, String text, String time, String parentname);

	int selectMessageCount();

	int selectNewId();

	int deleteArticleComments(int id);

	int deleteArticleCommentsAll(int[] list);

	List<Message> conditionalQueryMessage(String datemin, String datemax);

	int deleteMessageById(int id, int id2);

	int deleteMessage(int[] list, int[] list2);

	int selectMessageTotal();

	int selectMessageToDay();

	int selectMessageWeek();

	int selectMessageMonth();

	int selectMessageYesterDay();

}
