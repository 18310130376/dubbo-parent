package com.integration.boot.service;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CallGitHubLookupService {

  @Autowired
  private GitHubLookupService gitHubLookupService;
  
  private static final Logger logger = LoggerFactory.getLogger(CallGitHubLookupService.class);
    
    public void getResult() throws InterruptedException, ExecutionException {
    	 Future<Map<String,Object>> page1 = gitHubLookupService.findUser("PivotalSoftware");
         Future<Map<String,Object>> page2 = gitHubLookupService.findUser("CloudFoundry");
         Future<Map<String,Object>> page3 = gitHubLookupService.findUser("Spring-Projects");

         while (!(page1.isDone() && page2.isDone() && page3.isDone())) {
             Thread.sleep(10);
         }
         logger.info("--> " + page1.get());
         logger.info("--> " + page2.get());
         logger.info("--> " + page3.get());
    }
    
}
