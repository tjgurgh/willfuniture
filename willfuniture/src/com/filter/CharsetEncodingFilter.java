package com.filter;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class CharsetEncodingFilter implements Filter{
	
	
	private String charset;
	

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain)
			throws IOException, ServletException {
		
		
		String uri;
		
		if(request instanceof HttpServletRequest) {
			
			HttpServletRequest req = (HttpServletRequest)request;
			
			uri = req.getRequestURI();
			
			if(uri.indexOf("abc.do")!=-1) {
				
				req.setCharacterEncoding("euc-kr");
				
			}else {
				req.setCharacterEncoding(charset);
			}
		}
		
		chain.doFilter(request, response);
		//두번째 필터로 데이터넘기기 필터가 없으면 서버로 넘김
			
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	
		charset = filterConfig.getInitParameter(charset);
		//web.xml한테 위의값 받기
		
		if(charset==null) {
			charset = "utf-8";	
		}
	
	
	
	
	
	}
	
	
	
	

}
