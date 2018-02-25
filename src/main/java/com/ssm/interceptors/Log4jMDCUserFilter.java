package com.ssm.interceptors;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.MDC;

import com.ssm.pojo.User;
import com.ssm.service.dao.IpService;
import com.ssm.util.IpUtil;

public class Log4jMDCUserFilter implements Filter {
	private final static String DEFAULT_USER_NAME = "游客";

	private final static double DEFAULT_USERID= Math.random()*100000.0; 
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req=(HttpServletRequest) request;
		
		
		
		 HttpSession session= req.getSession();  
	        if (session==null){  
	            MDC.put("userId",DEFAULT_USERID);    
	        }  
	        else{  
	            User user=(User)session.getAttribute("user");  
	            if (user==null){  
	                MDC.put("userId",DEFAULT_USERID);  
	                MDC.put("userName",DEFAULT_USER_NAME);  
	            }  
	            else  
	            {  
	                MDC.put("userId",user.getId());  
	                MDC.put("userName",user.getUsername());  
	            }  
	        }  
        chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
