package net.water.security.util;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;

public class SecurityUtil {

	public static boolean validateUrlCanDo(String userId,String methodType,String urlPath,ServletContext sc){
		if(StringUtils.isBlank(userId) || StringUtils.isBlank(methodType) || StringUtils.isBlank(urlPath) || sc == null){
			return false;
		}
		//用户可以访问的所有url
		
		return false;
	}
}
