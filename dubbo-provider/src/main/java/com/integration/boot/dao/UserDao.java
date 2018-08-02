//package com.integration.boot.dao;
//
//import java.util.List;
//import java.util.Map; 
//
//import javax.annotation.Resource;
//
//import org.mybatis.spring.SqlSessionTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import com.integration.boot.domain.Blog;
//import com.integration.boot.domain.User;
//import com.integration.boot.mapper.UserMapper;
//
//@SuppressWarnings("all")
//@Repository
//public class UserDao {
//	
//	@Autowired
//	private UserMapper userMapper;
//
//	@Resource
//	public SqlSessionTemplate sqlSession;
//
//	public int addUser(User user) {
//		return userMapper.addUser(user);
//	}
//
//	public User getUser(String statement, User user) {
//		return sqlSession.selectOne(statement, user);
//	}
//
//	public int deleteUser(String statement, int parameter) {
//		return sqlSession.delete(statement, parameter);
//	}
//
//	public List<User> getUserList(String string) {
//		
//		return userMapper.getUserList();
//	}
//
//	public void updateUser(String statement,User user) {
//		sqlSession.update(statement, user);
//	}
//
//	public List<User> getUserByParam(User user) {
//		return sqlSession.selectList("getUserByParam", user);
//	}
//
//	public void addUserByMapParameter(String string, Map map) {
//		sqlSession.insert(string, map);
//	}
//
//	public List selectUserByLike(String string, String name) {
//		return sqlSession.selectList(string, name);
//	}
//
//	public List selectUseWhen(String string, User user) {
//		return sqlSession.selectList(string, user);
//	}
//
//	public List selectDynamicWhere(String string, User user) {
//		return sqlSession.selectList(string, user);
//	}
//
//	public void updateDynamicSet(String string, User user) {
//		sqlSession.update(string, user);
//	}
//
//	public List<User> selectUseListIn(String string, List list) {
//		return sqlSession.selectList(string,list);
//	}
//
//	public List<User> selectUseArrayIn(String string, int[] ids) {
//		return sqlSession.selectList(string, ids);
//	}
//
//	public List<User> selectUseMapIn(String string, Map map) {
//		return sqlSession.selectList(string,map);
//	}
//
//	public List<Blog> userDao(String string, User user) {
//		return sqlSession.selectList(string, user);
//	}
//
//
//	
//	public void saveOrUpdateUser(User user) {
//		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//		userMapper.saveOrUpdateUser(user);
////		sqlSession.insert("saveOrUpdateUser", user);
//	}
//}
