package com.integration.boot;

import java.io.File;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
public class Application{

    private static final Log logger = LogFactory.getLog(Application.class);
    
    @Bean
    public CountDownLatch closeLatch() {
        return new CountDownLatch(1);
    }
    
    public static void main(String[] args) throws InterruptedException {
    	  
    	  loadLogConfig();
    	   SpringApplication.run(Application.class, args);
          logger.info("======dubbo-consumer started successfull ======");
    }
    
    public static void loadLogConfig(){
    	
		try {
			PropertyConfigurator.configure(Application.class.getClassLoader().getResource("").getPath() + File.separator+"config"+File.separator+"log4j.properties");
		} catch (Exception e) {
			logger.error("=========config not found，try to load from user.dir =======");
			PropertyConfigurator.configure(System.getProperty("user.dir") + File.separator+"config"+ File.separator+"log4j.properties");
		}
    }
    
    /**
     *判断是否是linux系统
     * */
    public static boolean isLinux() { 
    	System.out.println(System.getProperty("os.name"));
    	return System.getProperty("os.name").toLowerCase().indexOf("linux") >= 0; 
    }
    
    
    @Bean
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3);
        executor.setMaxPoolSize(3);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("GithubLookup-");
        executor.initialize();
        return executor;
    }

}
