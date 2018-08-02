package com.integration.boot.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SessionFilter implements Filter {

	private String[] excludedUris;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain filterChain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		System.out.println("requestUtl:" + request.getRequestURI());
		String uri = request.getServletPath();
		if (isExcludedUri(uri)) {
			filterChain.doFilter(request, response);
		} else if (request.getSession().getAttribute("user") != null) {
			filterChain.doFilter(request, response);
		}else {
			if (isAjaxRequest(request)) {
				 response.setContentType("application/json; charset=utf-8");  
				 Map<String,Object> resultMap = new HashMap<>();
				 resultMap.put("success", false);
				 PrintWriter out = response.getWriter();
	             out.print(new ObjectMapper().writeValueAsString(resultMap));
	             out.flush();
	             out.close();
			}else {
				  filterChain.doFilter(request, response);
				//response.sendRedirect(request.getContextPath() + "/login/toLogin");
			}
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		if (StringUtils.isNotBlank(filterConfig.getInitParameter("excludedUri"))) {
			excludedUris = filterConfig.getInitParameter("excludedUri").split(",");
		}
	}

	private boolean isExcludedUri(String uri) {
		if (excludedUris == null || excludedUris.length <= 0) {
			return false;
		}
		for (String ex : excludedUris) {
			uri = uri.trim();
			ex = ex.trim();
			if (uri.toLowerCase().matches(ex.toLowerCase().replace("*", ".*")))
				return true;
		}
		return false;
	}
	
	public boolean isAjaxRequest(HttpServletRequest request) {
		String requestType = request.getHeader("X-Requested-With");
		return "XMLHttpRequest".equalsIgnoreCase(requestType);
	}
}
