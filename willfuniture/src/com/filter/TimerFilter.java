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

//����: �����ø����̼ǿ��� ����ϴ� �����̳� JSP�� �����ϱ� ����
//request�� ������ ������ �� �����̳� JSP���� �����ϴ� ���
//�ַ� ������ ���� ���� ��� �α����� �ۼ�, ���ڵ��۾� ��...



public class TimerFilter  implements Filter{
	
	private FilterConfig config;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain)
			throws IOException, ServletException {
	
		
		long before = System.currentTimeMillis();//��ž��ġ��ŸƮ
		
		chain.doFilter(request, response);
		//���Ϳ��� ���ͷ� ��������ִ� �۾� �긴������
		//������ �����͸� �Ѱ��ٶ����� �ǰ��� �־������. 
		
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
		//web.xml�κ��� �ʱⰪ�� �޾ƿ��� ����. 
		//������ �ʱⰪ�� ���� �ǹ� ����
		this.config = filterConfig;
	
	}

}
