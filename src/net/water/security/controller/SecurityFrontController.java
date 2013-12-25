package net.water.security.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/front/security")
public class SecurityFrontController {

	@RequestMapping("security_left")
	public void securityLeft(HttpServletResponse response,HttpServletRequest request, Model model) throws Exception {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		
		StringBuffer leftInfo = new StringBuffer();
		leftInfo.append("document.write(\"");
		//========test begin
		//ttt2
		leftInfo.append("<li>");
		leftInfo.append("<a class='light toggle-collapsed' href='#'><div class='ico'><i class='icon-th-large icon-white'></i></div>");
		leftInfo.append("权限<img alt='' src='http://cachecss.kuakao.com/public/ui/img/toggle-subnav-down.png'>");
		leftInfo.append("</a>");
		leftInfo.append("<ul class='collapsed-nav closed' style='display: none'>");
		leftInfo.append("<li><a style='margin-left: 0px;' href='/admin/secUrl/index.action'>url管理</a></li>");
		leftInfo.append("<li><a style='margin-left: 0px;' href='/admin/secResource/index.action'>资源管理</a></li>");
		leftInfo.append("<li><a style='margin-left: 0px;' href='/admin/secResGroup/index.action'>资源组管理</a></li>");
		leftInfo.append("</ul>");
		leftInfo.append("</li>");
		//
		leftInfo.append("<li class='open'>");
		leftInfo.append("<a class='light toggle-collapsed' href='#'><div class='ico'><i class='icon-th-large icon-white'></i></div>");
		leftInfo.append("用户<img alt='' src='http://cachecss.kuakao.com/public/ui/img/toggle-subnav-down.png'>");
		leftInfo.append("</a>");
		leftInfo.append("<ul class='collapsed-nav closed' style='display: block'>");
		leftInfo.append("<li><a style='margin-left: 0px;' href='/admin/user/index.action'>用户管理</a></li>");
		leftInfo.append("</ul>");
		leftInfo.append("</li>");
		
		//========test end		
		leftInfo.append("\");");
		
		writer.println(leftInfo.toString());
		writer.flush();
	}

}
