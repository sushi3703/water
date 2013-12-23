package net.water.user.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.water.Constants;
import net.water.login.entity.UserLoginEntity;
import net.water.login.service.IUserLoginService;
import net.water.user.dto.UserBaseDto;
import net.water.user.entity.UserBaseEntity;
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

	@RequestMapping(value="to_update_user_base",method=RequestMethod.GET)
	public String toUpdateUserBase(HttpServletRequest request, Model model){
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String showMsg = request.getParameter(Constants.PARAM_ERROR_MSG);
		if(StringUtils.isNotBlank(showMsg)){
			model.addAttribute(Constants.PARAM_ERROR_MSG, showMsg);
		}
		UserBaseDto userBaseDto = new UserBaseDto();
		userBaseDto.setUserId((String)request.getAttribute(Constants.PARAM_USER_LOGIN_ID));
		UserBaseEntity userBaseInfo = userService.getUserBaseById(userBaseDto, model);
		model.addAttribute("userBaseInfo", userBaseInfo);
		return "front/user/update_user_base";
	}
	

	@RequestMapping(value="do_update_user_base",method=RequestMethod.POST)
	public String doUpdateUserBase(UserBaseEntity userBaseEntity,HttpServletRequest request, Model model){
		if(StringUtils.isBlank(userBaseEntity.getUserId())){
			model.addAttribute(Constants.PARAM_ERROR_MSG, "请重新登录后再试");
			return "redirect:/front/user/to_update_user_base.action";
		}
		String res = "修改成功";
		HttpSession session = request.getSession();
		UserLoginEntity loginEntity = (UserLoginEntity)session.getAttribute(Constants.PARAM_USER_BASE_INFO);
		try {
			userService.updateUserBase(userBaseEntity, model);
			loginEntity.setUname(userBaseEntity.getUname());
			session.setAttribute(Constants.PARAM_USER_BASE_INFO, loginEntity);
		} catch (Exception e) {
			e.printStackTrace();
			res = "请重新登录后再试";
		}
		model.addAttribute(Constants.PARAM_ERROR_MSG, res);
		return "redirect:/front/user/to_update_user_base.action";
	}
	

	@RequestMapping("to_update_pwd")
	public String toUpdatePwd(HttpServletRequest request, Model model){
		String showMsg = request.getParameter(Constants.PARAM_ERROR_MSG);
		if(StringUtils.isNotBlank(showMsg)){
			model.addAttribute(Constants.PARAM_ERROR_MSG, showMsg);
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
