package springBoot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.integration.boot.Application;
import com.integration.boot.config.StaticProperties;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
public class TestSpringBoot {

	@Test
	public void Test() {
		
		String applicationName = StaticProperties.SPRING_APPLICATION_NAME;
		System.out.println(applicationName);
		
	}
}
