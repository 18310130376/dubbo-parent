package com.integration.boot;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.integration.boot.config.ConfigBean;

@SpringBootApplication
@EnableAsync
@EnableScheduling
@EnableConfigurationProperties({ConfigBean.class})
public class Application{

    private static final Log logger = LogFactory.getLog(Application.class);
    
    @Bean
    public CountDownLatch closeLatch() {
        return new CountDownLatch(1);
    }
    
    public static void main(String[] args) throws InterruptedException {

    	  ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
          logger.info("======dubbo-consumer started successfull ======");
          CountDownLatch closeLatch = context.getBean(CountDownLatch.class);
          closeLatch.await();
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
