package net.water.login.controller;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.water.Constants;
import net.water.login.service.IUserLoginService;
import net.water.tool.file.ConfigUtil;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/front/login")
public class LoginFrontController {

	@Autowired
	private IUserLoginService userLoginService;
	

	@RequestMapping("index")
	public String index(HttpServletRequest request, Model model){
		
		return "front/index";
	}
	
	
	@RequestMapping("logout")
	public String logout(HttpServletRequest request, Model model){
		request.getSession().setAttribute(Constants.PARAM_USER_BASE_INFO, null);
		return "show/login";
	}
	
	@RequestMapping("common_top")
	public void commonTop(HttpServletResponse response,HttpServletRequest request, Model model) throws Exception {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		
		String filePath = ConfigUtil.getValue("path_WebRoot")+Constants.PATH_TEMPLATE+"/common/common_top.htm";
		String topInfo = FileUtils.readFileToString(new File(filePath), "utf-8");
		if(StringUtils.isNotBlank(topInfo)){
			topInfo = topInfo.replaceAll("displayName", (String)request.getAttribute(Constants.PARAM_USER_LOGIN_NAME));
		}
		
		writer.println(topInfo);
		writer.flush();
	}

}
