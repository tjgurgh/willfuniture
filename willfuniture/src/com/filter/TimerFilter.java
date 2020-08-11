package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//필터: 웹어플리케이션에서 사용하는 서블릿이나 JSP에 도착하기 전에
//request의 내용을 조작한 후 서블릿이나 JSP에게 전송하는 기능
//주로 보안을 위해 많이 사용 로그파일 작성, 인코딩작업 등...



public class TimerFilter  implements Filter{
	
	private FilterConfig config;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain)
			throws IOException, ServletException {
	
		
		long before = System.currentTimeMillis();//스탑워치스타트
		
		chain.doFilter(request, response);
		//필터에서 필터로 연결시켜주는 작업 브릿지같음
		//서버에 데이터를 넘겨줄때까지 건건이 넣어줘야함. 
		
		long after = System.currentTimeMillis();
	
		String uri;
		
		if(request instanceof HttpServletRequest) {
			
			HttpServletRequest req = (HttpServletRequest)request;
			
			 uri = req.getRequestURI();
			 
			config.getServletContext().log(uri + ":" + (after-before) + 
					"ms");
			
		}
	
	
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//web.xml로부터 초기값을 받아오는 역할. 
		//지금은 초기값이 없어 의미 없음
		this.config = filterConfig;
	
	}

}
