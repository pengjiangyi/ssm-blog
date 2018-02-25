package com.ssm.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssm.dao.CommentsMapper;
import com.ssm.dao.MessageMapper;
import com.ssm.pojo.Message;
import com.ssm.service.dao.ReplyService;

@Service
public class ReplyServiceImpl implements ReplyService {
	@Resource
	private MessageMapper messageMapper;
	@Resource
	private CommentsMapper commentsmapper;

	@Override
	public List<Message> selectMessage() {

		return messageMapper.selectMessage();
	}

	@Override
	public List<Message> selectMessageAndChild(List<Message> rootmenu) {

		List<Message> menuList = new ArrayList<>();
		// 查出所有根数据
		for (int i = 0; i < rootmenu.size(); i++) {
			if (rootmenu.get(i).getParentid() == 0) {
				menuList.add(rootmenu.get(i));
			}

		}
		// 查找子节点
		for (Message menu : menuList) {
			menu.setChildren(getChild(menu.getId(), rootmenu));
		}

		return menuList;
	}
	private List<Message> getChild(Integer id, List<Message> rootmenu) {
		List<Message> childList = new ArrayList<>();
		for (Message menu : rootmenu) {
			if (menu.getParentid() == id) {
				childList.add(menu);
			}
		}
		for (Message menu : childList) {
			menu.setChildren(getChild(menu.getId(), rootmenu));
		}
		if (childList.size() == 0)
			return null;
		return childList;

	}

	@Override
	public int addMessage(int userid, int parentid, String ip, String text, String time, String parentname) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = null;
		try {
			date = format.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Message message=new Message();
		message.setUserid(userid);
		message.setParentid(parentid);
		message.setIp(ip);
		message.setText(text);
		message.setTime(date);
		message.setParentname(parentname);
		return messageMapper.insertSelective(message);


	}

	@Override
	public int selectMessageCount() {
		// TODO Auto-generated method stub
		return messageMapper.selectMessageCount();
	}

	@Override
	public int selectNewId() {
		// TODO Auto-generated method stub
		return messageMapper.selectNewId();
	}

	@Override
	public int deleteArticleComments(int id) {
		// TODO Auto-generated method stub
		return commentsmapper.deleteArticleComments(id);
	}

	@Override
	public int deleteArticleCommentsAll(int[] list) {
		// TODO Auto-generated method stub
		int n=0;
		for (int i = 0; i < list.length; i++) {
			commentsmapper.deleteArticleComments(list[i]);
			n++;
		}
		return n;
	}

	@Override
	public List<Message> conditionalQueryMessage(String datemin, String datemax) {
		// TODO Auto-generated method stub
		return messageMapper.conditionalQueryMessage(datemin,datemax);
	}

	@Override
	public int deleteMessageById(int id, int parentid) {
		// TODO Auto-generated method stub
		return messageMapper.deleteMessageById(id,parentid);
	}

	@Override
	public int deleteMessage(int[] list, int[] list2) {
		// TODO Auto-generated method stub
		for (int i = 0; i < list.length; i++) {
			messageMapper.deleteMessageById(list[i], list2[i]);
		}
		return 0;
	}

	@Override
	public int selectMessageTotal() {
		// TODO Auto-generated method stub
		return messageMapper.selectMessageCount();
	}

	@Override
	public int selectMessageToDay() {
		// TODO Auto-generated method stub
		return messageMapper.selectMessageToDay();
	}

	@Override
	public int selectMessageWeek() {
		// TODO Auto-generated method stub
		return messageMapper.selectMessageWeek();
	}

	@Override
	public int selectMessageMonth() {
		// TODO Auto-generated method stub
		return messageMapper.selectMessageMonth();
	}

	@Override
	public int selectMessageYesterDay() {
		// TODO Auto-generated method stub
		return messageMapper.selectMessageYesterDay();
	}
}
