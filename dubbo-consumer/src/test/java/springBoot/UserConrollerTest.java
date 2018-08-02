package springBoot;

import java.util.concurrent.ExecutionException;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.integration.boot.Application;
import com.integration.boot.config.ConfigBean;
import com.integration.boot.service.CallGitHubLookupService;
import com.integration.boot.service.GitHubLookupService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
public class UserConrollerTest {
	
	 private static Logger logger=Logger.getLogger(UserConrollerTest.class); // 获取logger实例
	
	@Test
	public void contextLoads() {
		
	}
	private MockMvc mockMvc;
	  
    @Autowired  
    private WebApplicationContext wac;

    @Before
    public void setup() {  
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();  
    }  

    @Test  
    public void getUserInfoTest() throws Exception {  
    	  String uri = "/getUserInfo";
          MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).param("username", "123").content("").contentType(MediaType.APPLICATION_FORM_URLENCODED)
        		  .accept(MediaType.APPLICATION_JSON))
                  .andReturn();
          String content = mvcResult.getResponse().getContentAsString();
          System.out.println(content);
    }  
    
    
    @Test  
    public void getUserByIdTest() throws Exception {  
    	  String uri = "/getUserById";
          MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri).param("username", "123").content("").contentType(MediaType.APPLICATION_FORM_URLENCODED)
        		  .accept(MediaType.APPLICATION_JSON))
                  .andReturn();
          String content = mvcResult.getResponse().getContentAsString();
          System.out.println(content);
    }  
    
    
    
    @Autowired
    private StringEncryptor stringEncryptor;
 
    @Test
    public void encryptValue() {
        System.out.println(stringEncryptor.encrypt("123456"));
    }
    
    @Value("${spring.datasource.password}")
    String value;
    
    @Test
    public void getDecryptValue(){
    	System.out.println("===="+value);
    }
    
    @Autowired
    private CallGitHubLookupService callGitHubLookupService;
    
    @Test
    public void getResult() throws InterruptedException, ExecutionException {
    	callGitHubLookupService.getResult();
    }
    
    @Autowired
    private ConfigBean configBean;
    
    @Test
    public void getProperties() {
    	System.out.println(configBean.getAbc());
    }
    
    public static void main(String[] args) {
        logger.info("普通Info信息");
        logger.debug("调试debug信息");
        logger.error("错误error信息");
        logger.warn("警告warn信息");
        logger.fatal("严重错误fatal信息");
         
        //开发中有可能会遇到一下经典异常
        logger.error("错误了",new IllegalArgumentException("非法参数异常"));
    }
}
