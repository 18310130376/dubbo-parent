package com.integration.boot.api;

import java.util.List;
import java.util.Map;

public interface IUserProviderService {
	
	public List<Map<String, String>> getAllUser(String username);
	
	
	public List<Map<String, String>> getAllUser(String username,String password);
}
