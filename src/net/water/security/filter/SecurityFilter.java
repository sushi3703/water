package net.water.security.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.water.Constants;
import net.water.security.dto.SecUrlDto;
import net.water.security.util.SecurityUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class SecurityFilter implements Filter {
	
	
	protected final Log log = LogFactory.getLog(getClass());
	     
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException    
	{    
		HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String uri = request.getRequestURI();
        //判断后缀
        if(uri.endsWith(".jsp") || uri.endsWith(".action")) {
        	String methodType = request.getMethod();
        	methodType = methodType.toUpperCase();
        	int methodTypeInt = 0;
        	if("GET".equals(methodType)){
        		methodTypeInt = SecUrlDto.URL_METHOD_GET;
        	}
        	if("POST".equals(methodType)){
        		methodTypeInt = SecUrlDto.URL_METHOD_POST;
        	}
        	boolean canDo = SecurityUtil.validateUrlCanDo((String)request.getAttribute(Constants.PARAM_USER_LOGIN_ID), methodTypeInt, request.getServletPath(), request.getSession().getServletContext());
        	if(!canDo){
        		response.sendRedirect("/show/security_error.html");
    			return;
        	}
        }
      
        filterChain.doFilter(request, response);
	}

	public void destroy() {
		
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		
	}    
}    
