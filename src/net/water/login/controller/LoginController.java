package net.water.login.controller;

import javax.servlet.http.HttpServletRequest;

import net.water.Constants;
import net.water.login.entity.UserLoginEntity;
import net.water.login.service.IUserLoginService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private IUserLoginService userLoginService;

	@RequestMapping("to_login")
	public String toLogin(HttpServletRequest request, Model model){
		model.addAttribute("redirectTo", request.getParameter("redirectTo"));
		return "show/login";
	}

	@RequestMapping("do_login")
	public String login(HttpServletRequest request, Model model){
		String uname = request.getParameter("uname");
		String upwd = request.getParameter("upwd");
		//log.info(username+","+userpwd);
		UserLoginEntity userLoginEntity = new UserLoginEntity();
		userLoginEntity.setUname(uname);
		userLoginEntity.setUpwd(upwd);
		UserLoginEntity userBaseInfo = userLoginService.queryUserLogin(userLoginEntity,model);
		if(userBaseInfo == null){
			return "show/login";
		}
		request.getSession().setAttribute(Constants.PARAM_USER_BASE_INFO, userBaseInfo);
		String redirectTo = request.getParameter("redirectTo");
		if(StringUtils.isNotBlank(redirectTo)){
			return "redirect:"+redirectTo;
		}
		return "redirect:/admin/security/index.action";
	}

}
