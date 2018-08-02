//package com.integration.boot.service;
//
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.integration.boot.dao.UserDao;
//import com.integration.boot.domain.Blog;
//import com.integration.boot.domain.User;
//
//@Service
//public class UserService {
//	
//	@Autowired
//	private UserDao userDao;
//
//	public User addUser(User user) {
//		userDao.addUser(user);//该方法后，主键已经设置到user中了
//		return user;
//	}
//	
//	public User getUser(String statement, User user) {
//		return userDao.getUser(statement, user);
//	}
//
//	public int deleteUser(String string, int i) {
//		return userDao.deleteUser(string,i);
//		
//	}
//
//	public List<User> getUserList(String string) {
//		// TODO Auto-generated method stub
//		return userDao.getUserList(string);
//	}
//
//	public void updateUser(String statement,User user) {
//		userDao.updateUser(statement,user);
//	}
//
//	public List<User> getUserByParam(User user) {
//		// TODO Auto-generated method stub
//		return userDao.getUserByParam(user);
//	}
//
//	public void addUserByMapParameter(String string, Map map) {
//		userDao.addUserByMapParameter(string,map);
//		
//	}
//
//	public List selectUserByLike(String string, String name) {
//		// TODO Auto-generated method stub
//		return userDao.selectUserByLike(string,name);
//	}
//
//	public List selectUseWhen(String string, User user) {
//		// TODO Auto-generated method stub
//		return userDao.selectUseWhen(string,user);
//	}
//
//	public List selectDynamicWhere(String string, User user) {
//		// TODO Auto-generated method stub
//		return userDao.selectDynamicWhere(string,user);
//	}
//
//	public void updateDynamicSet(String string, User user) {
//		// TODO Auto-generated method stub
//		userDao.updateDynamicSet(string,user);
//	}
//
//	public List<User> selectUseListIn(String string, List list) {
//		return userDao.selectUseListIn(string,list);
//		// TODO Auto-generated method stub
//		
//	}
//
//	public List<User> selectUseArrayIn(String string, int[] ids) {
//		// TODO Auto-generated method stub
//		return userDao.selectUseArrayIn(string,ids);
//	}
//
//	public List<User> selectUseMapIn(String string, Map map) {
//		// TODO Auto-generated method stub
//		return userDao.selectUseMapIn(string,map);
//	}
//
//	public List<Blog> ListBlog(String string, User user) {
//		// TODO Auto-generated method stub
//		return userDao.userDao(string,user);
//	}
//
//	public void saveOrUpdateUser(User user) {
//		// TODO Auto-generated method stub
//		userDao.saveOrUpdateUser(user);
//	}
//
//}
