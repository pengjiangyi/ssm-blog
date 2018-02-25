package com.ssm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.dao.CommentsMapper;
import com.ssm.pojo.Comments;
import com.ssm.service.dao.CommentsService;

@Service
public class CommentsServiceImpl implements CommentsService {
  @Resource
  private CommentsMapper commentsMapper;

@Override
public List<Comments> selectArticleComments() {
	// TODO Auto-generated method stub
	return commentsMapper.selectAll();
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
public int deleteCommentsById(int id,int parentid) {
	// TODO Auto-generated method stub
	return commentsMapper.deleteCommentsAndChild(id,parentid);
}

@Override
public int deleteComments(int id[], int parentid[]) {
	for (int i = 0; i < id.length; i++) {
		commentsMapper.deleteCommentsAndChild(id[i], parentid[i]);
	}
	return 0;
}

@Override
public List<Comments> conditionalQueryComments(String datemin, String datemax, Integer articleid) {
	// TODO Auto-generated method stub
	return commentsMapper.conditionalQueryComments(datemin,datemax,articleid);
}

@Override
public int selectCommentsTotal() {
	// TODO Auto-generated method stub
	return commentsMapper.selectCommentsTotal();
}

@Override
public int selectCommentsToDay() {
	// TODO Auto-generated method stub
	return commentsMapper.selectCommentsToDay();
}

@Override
public int selectCommentsWeek() {
	// TODO Auto-generated method stub
	return commentsMapper.selectCommentsWeek();
}

@Override
public int selectCommentsMonth() {
	// TODO Auto-generated method stub
	return commentsMapper.selectCommentsMonth();
}

@Override
public int selectCommentsYesterDay() {
	// TODO Auto-generated method stub
	return commentsMapper.selectCommentsYesterDay();
}

}
