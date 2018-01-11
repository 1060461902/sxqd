package edu.zjgsu.ito.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import edu.zjgsu.ito.utils.RequestWrapper;

/**
 * 获取json数据过滤器
 * @author Jenson_Zhou
 *
 */
public class GetJsonFilter implements Filter{

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			RequestWrapper requestWrapper = new RequestWrapper((HttpServletRequest) request);
			chain.doFilter(requestWrapper, response);
		} catch (Exception e) {
			chain.doFilter(request, response);
		}
		
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
