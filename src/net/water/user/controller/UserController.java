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
import net.water.user.service.IUserService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;

	@RequestMapping(value="to_register",method=RequestMethod.GET)
	public String toRegister(HttpServletRequest request, Model model){
		if(request.getSession().getAttribute(Constants.PARAM_USER_BASE_INFO) != null){//已登录，返回首页
			return "redirect:"+Constants.SYS_INDEX;
		}
		model.addAttribute("type", request.getParameter("type"));
		model.addAttribute("teamId", request.getParameter("teamId"));
		return "show/register";
	}
	
	/**
	 * 注册新用户
	 * @param userLoginEntity
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping(value="do_register",method=RequestMethod.POST)
	public void doRegister(UserLoginEntity userLoginEntity,HttpServletRequest request, HttpServletResponse response, Model model){
		PrintWriter writer = null;
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(writer == null){
			return;
		}
		//非空验证
		//验证码验证（采用三部分验证码，1机器干扰的数字，2加或乘图片，3机器干扰的数字）
		//密码确认验证
		//邮箱格式验证
		//
		String res = "suc";
		try {
			userService.createRegisterUser(userLoginEntity, model);
			//登录
			request.getSession().setAttribute(Constants.PARAM_USER_BASE_INFO, userLoginEntity);
		} catch (DataBaseException e) {
			e.printStackTrace();
			res = "系统异常，请稍候再试";
		}
		writer.print(res);
		writer.flush();
	}
	
	/**
	 * 验证邮箱是否可注册(非空、格式、未注册)
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="validate_email",method=RequestMethod.GET)
	public void validateEmail(HttpServletRequest request, HttpServletResponse response){
		PrintWriter writer = null;
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(writer == null){
			return;
		}
		String email = request.getParameter("email");
		//非空验证
		if(StringUtils.isBlank(email)){
			writer.print("请输入邮箱");
			writer.flush();
			return;
		}
		//邮箱格式验证
		String mailCheck = "^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$";
		Pattern regex = Pattern.compile(mailCheck);
		Matcher matcher = regex.matcher(email);
		boolean isMatched = matcher.matches();
		if(!isMatched) {
			writer.print("邮箱格式不正确");
			writer.flush();
			return;
		}
		//未注册验证
		boolean isExit = userService.validateEmailExit(email);
		if(isExit){
			writer.print("此账户已注册");
			writer.flush();
		}else{
			writer.print("suc");
			writer.flush();
		}
	}


}
