package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter{

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain)
			throws IOException, ServletException {
		
		boolean flag = false;
		
		if(request instanceof HttpServletRequest) {
			
			HttpServletRequest req = (HttpServletRequest)request;
			
			HttpSession session = req.getSession();
			
			if(session!=null) {
				if(session.getAttribute("customInfo")!=null) {
					flag=true;
				}
				
			}
		}
		
		if(flag) {//flag가 트루이면 
			
			chain.doFilter(request, response);
					
		}else {
			RequestDispatcher rd =
					request.getRequestDispatcher("/member/login.jsp");
			rd.forward(request, response);
			
		}
		
		
		
		
	}
	
	

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

}
