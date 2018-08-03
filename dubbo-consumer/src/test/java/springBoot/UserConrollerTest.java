package springBoot;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.integration.boot.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
public class UserConrollerTest {
	
	 private static Logger logger=Logger.getLogger(UserConrollerTest.class);
	
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
          logger.info(content);
    }  
    
    
    @Test  
    public void getUserByIdTest() throws Exception {  
    	  String uri = "/getUserById";
          MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri).param("username", "123").content("").contentType(MediaType.APPLICATION_JSON)
        		  .accept(MediaType.APPLICATION_JSON))
                  .andReturn();
          String content = mvcResult.getResponse().getContentAsString();
          logger.debug(content);
    }  
 
}
