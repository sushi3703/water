package net.water.login.controller;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.water.Constants;
import net.water.login.entity.UserLoginEntity;
import net.water.login.service.IUserLoginService;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.water.tool.file.ConfigUtil;


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
	

	@RequestMapping("to_update_pwd")
	public String toUpdatePwd(HttpServletRequest request, Model model){
		String showMsg = request.getParameter(Constants.PARAM_SHOW_MSG);
		if(StringUtils.isNotBlank(showMsg)){
			model.addAttribute(Constants.PARAM_SHOW_MSG, showMsg);
		}
		return "front/update_pwd";
	}
	
	
	@RequestMapping("do_update_pwd")
	public String doUpdatePwd(HttpServletRequest request, Model model){
		Object o = request.getSession().getAttribute(Constants.PARAM_USER_BASE_INFO);
		if(o==null){
			return "show/login";
		}
		String oldPwd = request.getParameter("oldPwd");
		if(StringUtils.isBlank(oldPwd)){
			model.addAttribute(Constants.PARAM_ERROR_MSG, "请输入原密码");
			return "front/update_pwd";
		}
		String newPwd = request.getParameter("newPwd");
		if(StringUtils.isBlank(newPwd)){
			model.addAttribute(Constants.PARAM_ERROR_MSG, "请输入新密码");
			return "front/update_pwd";
		}
		
		UserLoginEntity userLoginEntity = (UserLoginEntity)o;
		userLoginEntity.setUpwd(oldPwd);
		UserLoginEntity oldEntity = userLoginService.queryUserLogin(userLoginEntity,model);
		if(oldEntity == null){
			model.addAttribute(Constants.PARAM_ERROR_MSG, "原密码有误，请重新输入");
			return "front/update_pwd";
		}
		
		userLoginEntity.setUpwd(newPwd);
		userLoginService.updateUserPwd(userLoginEntity);
		
		model.addAttribute(Constants.PARAM_SHOW_MSG, "密码修改成功");
		return "redirect:/front/login/to_update_pwd.action";
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
