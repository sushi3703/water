package net.water.security.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.kuakao.core.xmlconfig.util.XmlConfigUtil;
import net.water.Constants;
import net.water.security.entity.SecUrlEntity;
import net.water.security.service.ISecResourceService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/front/security")
public class SecurityFrontController {
	
	@Autowired
	private ISecResourceService resService;

	@RequestMapping("security_left")
	public void securityLeft(HttpServletResponse response,HttpServletRequest request, Model model) throws Exception {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		
		String userId = (String)request.getAttribute(Constants.PARAM_USER_LOGIN_ID);
		if(StringUtils.isBlank(userId)){
			writer.print(Constants.INFO_USERNOLOGIN);
			writer.flush();
			return;
		}
		
		//用户权限菜单
		Map<Integer, List<SecUrlEntity>> menuUrlsMap = resService.getUserSecMenu(userId);
		
		StringBuffer leftInfo = new StringBuffer();
		leftInfo.append("document.write(\"");
		//========make menu begin
		if(menuUrlsMap != null && !menuUrlsMap.isEmpty()){
			//菜单中文名
			Map<String,String> menuNameMap = XmlConfigUtil.getMap("security_menu");
			List<SecUrlEntity> urls;
			for(Integer menuId : menuUrlsMap.keySet()){
				urls = menuUrlsMap.get(menuId);
				if(urls == null || urls.isEmpty()){
					continue;
				}
				leftInfo.append("<li>");
				leftInfo.append("<a class='light toggle-collapsed' href='#'><div class='ico'><i class='icon-th-large icon-white'></i></div>");
				leftInfo.append(menuNameMap.get(menuId+"")+"<img alt='' src='http://cachecss.kuakao.com/public/ui/img/toggle-subnav-down.png'>");
				leftInfo.append("</a>");
				leftInfo.append("<ul id='ul_menu_"+menuId+"' class='collapsed-nav closed' style='display: none'>");
				for(SecUrlEntity url : urls){
					leftInfo.append("<li id='li_url_"+url.getUrlId()+"'><a style='margin-left: 0px;' href='"+url.getUrlPath()
							+ this.getSignAfterUrl(url.getUrlPath())
							+ "selMenu=" + menuId + "&selUrl=" + url.getUrlId()
							+ "'>" + url.getUrlName() + "</a></li>");
				}
				leftInfo.append("</ul>");
				leftInfo.append("</li>");
			}
		}
		//========make menu end		
		leftInfo.append("\");");
		
		writer.println(leftInfo.toString());
		writer.flush();
	}
	
	/**
	 * 取url后的连接符号
	 * @param url 验证的url
	 * @return ?表示url中没有参数，&表示url中已含有参数
	 */
	private String getSignAfterUrl(String url){
		if(StringUtils.isBlank(url)){
			return null;
		}
		return url.indexOf("?") > -1 ? "&" : "?";
	}

}
