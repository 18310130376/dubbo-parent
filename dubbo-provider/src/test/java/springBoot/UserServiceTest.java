//package springBoot;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
// 
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import com.integration.boot.application.Application;
//import com.integration.boot.domain.Blog;
//import com.integration.boot.domain.User;
//import com.integration.boot.mapper.IUserMapperOperation;
//import com.integration.boot.service.UserService;
//
//import junit.framework.TestCase;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = {Application.class})
//@WebAppConfiguration
//public class UserServiceTest extends TestCase{
//
//	    @Autowired
//	    private UserService userService;
//	    
//	    @Autowired
//	    private SqlSessionFactory sqlSessionFactory;
//	   
//	    @Test
//	    public void addUserTest(){
//	    	User user = new User("王海艳", 26);
//	    	userService.addUser(user);
//	    	System.out.println(user);
//	    }
//	    
//	    public void getUser(){
//	    	User user = new User();
//	    	user.setId(1);
//	    	user =  userService.getUser("com.integration.boot.mapper.UserMapper.selectUserById", user);
//	    	System.out.println(user);
//	    }
//	    
//	    public void deleteUser(){
//	    	int a = userService.deleteUser("com.integration.boot.mapper.UserMapper.deleteUserById",5);
//	    	System.out.println(a);
//	    }
//	    
//	    public void getUserList(){
//	    	List<User> list = userService.getUserList("getUserList");
//	    	System.out.println(list);
//	    }
//	    
//	    
//	    public void updateUser(){
//	    	User user = new User();
//	    	user.setId(6);
//	    	user.setName("wukang475402366");
//	    	user.setAge(28);
//	    	userService.updateUser("updateUser",user);
//	    }
//	    
//	    public void getUserByParam(){
//	    	
//	    	User user = new User();
//	    	user.setId(6);
//	    	user.setName("WU");
//	    	List<User> list = userService.getUserByParam(user);
//	    	System.out.println(list);
//	    }
//   
//	    public void testOp(){
//	    	SqlSession session = sqlSessionFactory.openSession();
//	        try {
//	            IUserMapperOperation userOperation=session.getMapper(IUserMapperOperation.class);
//	            User user = new User("王海艳", 26);
//	            userOperation.addUser(user);
//	            System.out.println(user);
//	        } finally {
//	            session.close();
//	        }
//	    }
//	    
//	    
//	    @SuppressWarnings({ "unchecked", "rawtypes" })
//	    public void addUserByMapParameter(){
//	    	Map map = new HashMap();
//	    	map.put("name", "wnnziyu4754");
//	    	map.put("age", 1000);
//	    	userService.addUserByMapParameter("addUserByMapParameter",map);
//	    }
//	    
//	    @SuppressWarnings("rawtypes")
//		public void selectUserByLike(){
//	    	List list = userService.selectUserByLike("selectUserByLike","");
//	    	System.out.println(list);
//	    }
//	    
//	    @SuppressWarnings("rawtypes")
//		@Test
//	    public void selectUseWhen(){
//	    	User user = new User();
//	    	user.setAge(26);
//	    	List list = userService.selectUseWhen("selectUseWhen",user);
//	    	System.out.println(list);
//	    }
//	    
//	    
//	    
//	    @SuppressWarnings("rawtypes")
//		public void selectDynamicWhere(){
//	    	User user = new User();
//	    	user.setAge(26);
//	    	user.setId(7);
//	    	user.setName("王海艳");
//	    	List list = userService.selectDynamicWhere("selectDynamicWhere",user);
//	    	System.out.println(list);
//	    }
//	    
//	    
//	    @Test
//	    public void updateDynamicSet(){
//	    	User user = new User();
//	    	user.setId(7);
//	    	user.setAge(22);
//	    	user.setName("王海艳love you forever");
//	    	userService.updateDynamicSet("updateDynamicSet",user);
//	    }
//	    
//	    
//	    @SuppressWarnings({ "unchecked", "rawtypes" })
//		@Test
//	    public void selectUseListIn(){
//	    	
//	    	List list = new ArrayList<>();
//	    	list.add(7);
//	    	list.add(9);
//	    	List<User> userList  = userService.selectUseListIn("selectUseListIn",list);
//	    	System.out.println(userList);
//	    }
//	    
//	    @Test
//	    public void selectUseArrayIn(){
//	    	
//	    	int [] ids =new int[]{6,10};
//	    	List<User> userList  = userService.selectUseArrayIn("selectUseArrayIn",ids);
//	    	System.out.println(userList);
//	    }
//	    
//	    
//	    @SuppressWarnings({ "unchecked", "rawtypes" })
//		@Test
//	    public void selectUseMapIn(){
//	    	
//	    	Map map = new HashMap();
//	        List list = new ArrayList<>();
//	        list.add(8);
//	        list.add(7);
//	        map.put("idList", list);
//	        map.put("name", "王海艳");
//	    	List<User> userList  = userService.selectUseMapIn("selectUseMapIn",map);
//	    	System.out.println(userList);
//	    }
//	    
//	    @Test
//	    public void ListBlog(){
//	        
//	    	User user = new User();
//	    	user.setId(8);
//	    	List<Blog> blogList = userService.ListBlog("getUserBlog",user);
//	    	if(blogList != null && blogList.size()>0){
//	    		for(Blog blog : blogList){
//	    			System.out.println(blog);
//	    		}
//	    	}
//	    }
//	    
//	    
//	    /**
//	     * 分页查询
//	     * */
//	    @Test
//	    public void getUserByPage(){
//	    	  PageHelper.startPage(1, 3);
//	    	  List<User> list = userService.getUserList("getUserList");
//	    	  PageInfo<User> page = new PageInfo<User>(list);
//	    	    System.out.println(page.getPageNum());
//	    	    System.out.println(page.getPageSize());
//	    	    System.out.println(page.getStartRow());
//	    	    System.out.println(page.getEndRow());
//	    	    System.out.println(page.getTotal());
//	    	    System.out.println(page.getPages());
//	    	    System.out.println(page.getFirstPage());
//	    	    System.out.println(page.getLastPage());
//	    	    System.out.println(page.isHasPreviousPage());
//	    	    System.out.println(page.isHasNextPage());
//	    	    System.out.println(page.getList());
//	    	    System.out.println(page.getNavigatePages());
//	    	    System.out.println(page.getNavigatepageNums());
//	    	    List<User> listUser = page.getList();
//	    	    for(User user : listUser){
//	    	    	System.out.println(user);
//	    	    }
//	    }
//
//	    
//	    @Test
//	    public void saveOrUpdateUser(){
//	    	User user = new User("王海艳updatemmm", 20);
//	    	user.setId(13);
//	    	try {
//				userService.saveOrUpdateUser(user);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//	    	System.out.println(user);
//	    }
//}
