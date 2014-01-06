package net.water.security.util;

import java.util.List;

import javax.servlet.ServletContext;

import net.water.security.cache.UserUrlCache;
import net.water.security.entity.SecUrlEntity;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SecurityUtil {
	/**
	 * 验证用户是否可以访问该url
	 * @param userId 待验证用户id
	 * @param methodTypeInt url访问方式1get2post
	 * @param urlPath url路径
	 * @param sc 用于查找spring资源
	 * @return true可以false不可以
	 */
	public static boolean validateUrlCanDo(String userId,int methodTypeInt,String urlPath,ServletContext sc){
		if(StringUtils.isBlank(userId) || methodTypeInt == 0 || StringUtils.isBlank(urlPath) || sc == null){
			return false;
		}
		//用户可以访问的所有url
		ApplicationContext application = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
		if(application==null)return false;
		UserUrlCache userUrlCache = (UserUrlCache) application.getBean("userUrlCache");
		List<SecUrlEntity> urls = userUrlCache.getUserUrls(userId);
		//验证
		if(urls == null || urls.isEmpty()){
			return false;
		}
		for(SecUrlEntity url : urls){
			if(methodTypeInt == url.getUrlMethod() && urlPath.equals(url.getUrlPath())){
				return true;
			}
		}
		return false;
	}
}
