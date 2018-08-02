package com.integration.boot.provider.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.dubbo.config.annotation.Service;


@SuppressWarnings("all")
@Service
public class UserProviderService implements IUserProviderService{
	
	public List getAllUser(String username) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name","wukang11");
		List list = new ArrayList<>();
		list.add(map);
		return list;
	}
}
