package com.integration.boot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.integration.boot.api.IUserProviderService;

@Controller
public class UserController {
	
	private static Logger logger=Logger.getLogger(UserController.class);
	
	@Reference
	private IUserProviderService userProviderService;
	
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value = "/getUserInfo",method = RequestMethod.GET)
	public List getUserInfo(HttpServletRequest request){
		String username = request.getParameter("username");
		logger.debug("==================");
		return userProviderService.getAllUser(username,null);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/getUserById",method = RequestMethod.PUT)
	public Map<String,Object> getUserById(HttpServletRequest request,@RequestBody Map<String,Object> map){
		Map<String,Object> resultDate = new HashMap<>();
		resultDate.put("key"," value");
		logger.debug("==================");
		return resultDate;
	}

}
