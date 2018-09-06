package com.integration.boot.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.dubbo.config.annotation.Service;
import com.integration.boot.api.IUserProviderService;


@Service
public class UserProviderServiceImpl implements IUserProviderService{
	
	public List<Map<String, String>> getAllUser(String username) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name","wukang11");
		List<Map<String, String>> list = new ArrayList<>();
		list.add(map);
		return list;
	}

	@Override
	public List<Map<String, String>> getAllUser(String username, String password) {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("name","wukang12");
		map.put("password",password);
		List<Map<String, String>> list = new ArrayList<>();
		list.add(map);
		return list;
	}

	@Override
	public Map<String, String> getData(Map<String, String> map, String password) {
		return map;
	}
}
