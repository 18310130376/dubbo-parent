//package com.integration.boot.mapper;
//
//
//import java.util.List;
//
//import org.apache.ibatis.annotations.Mapper;
//
//import com.integration.boot.domain.User;
//
//public interface UserMapper {
//	 
//	    /**
//	     * 插入用户，并将主键设置到user中
//	     * 注意：返回的是数据库影响条数，即1
//	     */
//	    public int addUser(User user);
//	    
//	    public void saveOrUpdateUser(User user);
//	    
//	    public List getUserList();
//}
