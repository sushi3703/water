package net.water.security.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/security")
public class SecurityAdminController {


	@RequestMapping("index")
	public String index(HttpServletRequest request, Model model) throws Exception {
		
		return "";
	}
	
	@RequestMapping("security_left")
	public void securityLeft(HttpServletResponse response,HttpServletRequest request, Model model) throws Exception {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		
		StringBuffer leftInfo = new StringBuffer();
		leftInfo.append("document.write(\"");
		leftInfo.append("<div class='navi'><ul class='main-nav'>");
		//========test begin
		//ttt1
		leftInfo.append("<li>");
		leftInfo.append("<a class='light toggle-collapsed' href='#'><div class='ico'><i class='icon-th-large icon-white'></i></div>");
		leftInfo.append("ttt1<img alt='' src='http://cachecss.kuakao.com/public/ui/img/toggle-subnav-down.png'>");
		leftInfo.append("</a>");
		leftInfo.append("<ul class='collapsed-nav closed' style='display: none'>");
		leftInfo.append("<li><a style='margin-left: 0px;' href='/'>t11</a></li>");
		leftInfo.append("<li><a style='margin-left: 0px;' href='/'>t12</a></li>");
		leftInfo.append("</ul>");
		leftInfo.append("</li>");
		//ttt2
		leftInfo.append("<li class='open'>");
		leftInfo.append("<a class='light toggle-collapsed' href='#'><div class='ico'><i class='icon-th-large icon-white'></i></div>");
		leftInfo.append("ttt2<img alt='' src='http://cachecss.kuakao.com/public/ui/img/toggle-subnav-down.png'>");
		leftInfo.append("</a>");
		leftInfo.append("<ul class='collapsed-nav closed' style='display: block'>");
		leftInfo.append("<li><a style='margin-left: 0px;' href='/admin/secUrl/index.action'>t21</a></li>");
		leftInfo.append("<li><a style='margin-left: 0px;' href='/'><b>t22</b></a></li>");
		leftInfo.append("</ul>");
		leftInfo.append("</li>");
		
		//========test end		
		leftInfo.append("</ul></div>");
		leftInfo.append("\");");
		
		writer.println(leftInfo.toString());
		writer.flush();
	}

}
