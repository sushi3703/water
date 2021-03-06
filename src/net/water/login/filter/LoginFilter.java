package net.water.login.filter;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.water.Constants;
import net.water.login.entity.UserLoginEntity;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class LoginFilter implements Filter {
	
	
	protected final Log log = LogFactory.getLog(getClass());
	     
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException    
	{    
		HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String uri = request.getRequestURI();
        //判断后缀
        if(uri.endsWith(".jsp") || uri.endsWith(".action")) {
        	String redirectTo = "";
        	Map params = request.getParameterMap();
        	StringBuffer urlParam = new StringBuffer();
        	if(params!=null && !params.isEmpty()){
        		for(Object key : params.keySet()){
        			if(urlParam.length()>0)urlParam.append("&");
        			urlParam.append(key+"="+request.getParameter((String)key));
        		}
        		redirectTo = URLEncoder.encode(request.getRequestURL() + "?" + urlParam.toString(),"ISO-8859-1");
        	}
        	HttpSession session = request.getSession();
        	if(session == null){
        		response.sendRedirect(request.getContextPath()+"/login/to_login.action?redirectTo="+redirectTo);
        		return;
        	}
        	Object userBaseInfoObj = session.getAttribute(Constants.PARAM_USER_BASE_INFO);
        	if(userBaseInfoObj == null){ 
        		response.sendRedirect(request.getContextPath()+"/login/to_login.action?redirectTo="+redirectTo);
        		return;
	        }
        	UserLoginEntity userLoginEntity = (UserLoginEntity)userBaseInfoObj;
        	request.setAttribute(Constants.PARAM_USER_LOGIN_ID, userLoginEntity.getUserId());
        	request.setAttribute(Constants.PARAM_USER_LOGIN_NAME, userLoginEntity.getUname());
        }
      
        filterChain.doFilter(request, response);
	}

	public void destroy() {
		
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		
	}    
}    
