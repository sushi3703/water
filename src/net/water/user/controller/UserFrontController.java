package net.water.user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.kuakao.core.exception.DataBaseException;
import net.water.Constants;
import net.water.login.entity.UserLoginEntity;
import net.water.login.service.IUserLoginService;
import net.water.user.service.IUserService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/front/user")
public class UserFrontController {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IUserLoginService userLoginService;

	@RequestMapping(value="to_modify_base_info",method=RequestMethod.GET)
	public String toModifyBaseInfo(HttpServletRequest request, Model model){
		
		return "front/user/modify_user_base_info";
	}
	

	@RequestMapping("to_update_pwd")
	public String toUpdatePwd(HttpServletRequest request, Model model){
		String showMsg = request.getParameter(Constants.PARAM_SHOW_MSG);
		if(StringUtils.isNotBlank(showMsg)){
			model.addAttribute(Constants.PARAM_SHOW_MSG, showMsg);
		}
		return "front/user/update_pwd";
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
			return "front/user/update_pwd";
		}
		String newPwd = request.getParameter("newPwd");
		if(StringUtils.isBlank(newPwd)){
			model.addAttribute(Constants.PARAM_ERROR_MSG, "请输入新密码");
			return "front/user/update_pwd";
		}
		
		UserLoginEntity userLoginEntity = (UserLoginEntity)o;
		userLoginEntity.setUpwd(oldPwd);
		UserLoginEntity oldEntity = userLoginService.queryUserLogin(userLoginEntity,model);
		if(oldEntity == null){
			model.addAttribute(Constants.PARAM_ERROR_MSG, "原密码有误，请重新输入");
			return "front/user/update_pwd";
		}
		
		userLoginEntity.setUpwd(newPwd);
		userService.updateUserPwd(userLoginEntity);
		
		model.addAttribute(Constants.PARAM_SHOW_MSG, "密码修改成功");
		return "redirect:/front/user/to_update_pwd.action";
	}
	

}
