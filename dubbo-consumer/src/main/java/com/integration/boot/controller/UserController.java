package com.integration.boot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.integration.boot.provider.service.IUserProviderService;

@RestController
public class UserController {
	
	@Reference(url="dubbo://127.0.0.1:20882/com.integration.boot.provider.service.IUserProviderService")
	private IUserProviderService userProviderService;
	
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value = "/getUserInfo",method = RequestMethod.GET)
	public List getUserInfo(HttpServletRequest request){
		String username = request.getParameter("username");
		return userProviderService.getAllUser(username);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/getUserById",method = RequestMethod.PUT)
	public Map<String,Object> getUserById(HttpServletRequest request,@RequestBody Map<String,Object> map){
		Map<String,Object> resultDate = new HashMap<>();
		resultDate.put("key"," value");
		return resultDate;
	}
}
