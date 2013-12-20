package net.water.login.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.water.Constants;
import net.water.login.entity.UserLoginEntity;
import net.water.login.service.IUserLoginService;
import net.water.user.entity.UserSnsEntity;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;

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
	public String doLogin(HttpServletRequest request, Model model){
		String email = request.getParameter("email");
		String upwd = request.getParameter("upwd");
		//log.info(username+","+userpwd);
		UserLoginEntity userLoginEntity = new UserLoginEntity();
		userLoginEntity.setEmail(email);
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
		return "redirect:"+Constants.SYS_INDEX;
	}
	
	@RequestMapping(value = "snsLogin")
	public void snsLogin(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html;charset=utf-8");
		request.getSession().setAttribute("redirectTo",request.getParameter("redirectTo"));
		String redirectUrl = "";
		try {
			redirectUrl = new Oauth().getAuthorizeURL(request);
		} catch (QQConnectException e) {
			e.printStackTrace();
		}
		//跳转
		try {
			response.sendRedirect(redirectUrl);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "after_sns_login")
	public String afterSnsLogin(HttpServletRequest request,HttpServletResponse response) {
		String accessToken = null, openID = null;
		AccessToken accessTokenObj = null;
		try {
			accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
		} catch (QQConnectException e) {
			e.printStackTrace();
		}
		Object sessionRedirectTo = request.getSession().getAttribute("redirectTo");
		
		if (accessTokenObj == null) {
			request.setAttribute("errorMsg", "QQ登录异常，请用其它方式登录");
			request.setAttribute("redirectTo", sessionRedirectTo);
			return "show/login";
		}
		accessToken = accessTokenObj.getAccessToken();
		if ("".equals(accessToken)) {
			request.setAttribute("errorMsg", "QQ登录异常，请用其它方式登录");
			request.setAttribute("redirectTo", sessionRedirectTo);
			return "show/login";
		}
		// 利用获取到的accessToken 去获取当前用的openid
		OpenID openIDObj = new OpenID(accessToken);
		try {
			openID = openIDObj.getUserOpenID();
		} catch (QQConnectException e) {
			e.printStackTrace();
		}
		// System.out.println("欢迎你，代号为 " + openID + " 的用户,accessToken为"+accessToken);
		if(StringUtils.isBlank(openID)){
			request.setAttribute("errorMsg", "QQ登录异常，请用其它方式登录");
			request.setAttribute("redirectTo", sessionRedirectTo);
			return "show/login";
		}
		UserSnsEntity userSnsEntity = new UserSnsEntity();
		userSnsEntity.setQqOpenId(openID);
		userSnsEntity.setQqAccessToken(accessToken);
		String qqUsername = "";
		UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
        try {
			UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
			if(userInfoBean != null){
				qqUsername = userInfoBean.getNickname();
			}
		} catch (QQConnectException e) {
			e.printStackTrace();
		}
		userSnsEntity.setQqUsername(qqUsername);
		UserLoginEntity userLoginEntity = userLoginService.operateQqLoginExist(userSnsEntity);
		if(userLoginEntity == null){// 绑定或注册
			request.setAttribute("qqUsername", qqUsername);
			request.setAttribute("qqOpenId", openID);
    		request.setAttribute("qqAccessToken", accessToken);
    		return "show/bind";
		}else{
			request.getSession().setAttribute(Constants.PARAM_USER_BASE_INFO, userLoginEntity);
			String redirectTo = (String)sessionRedirectTo;
			if(StringUtils.isBlank(redirectTo)){
				redirectTo = Constants.SYS_INDEX;
			}
			return "redirect:"+redirectTo;
		}
	}
	
	@RequestMapping(value = "bind_sns_account")
	public String bindSnsAccount(UserSnsEntity userSnsEntity,HttpServletRequest request,HttpServletResponse response, Model model) {
		UserLoginEntity validateLoginEntity = new UserLoginEntity();
		validateLoginEntity.setEmail(request.getParameter("email"));
		validateLoginEntity.setUpwd(request.getParameter("upwd"));
		UserLoginEntity userLoginInfo = userLoginService.queryUserLogin(validateLoginEntity,model);
		if(userLoginInfo == null){
			request.setAttribute("qqUsername", request.getParameter("qqUsername"));
			request.setAttribute("qqOpenId", request.getParameter("qqOpenId"));
    		request.setAttribute("qqAccessToken", request.getParameter("qqAccessToken"));
    		return "show/bind";
		}
		//login success
		request.getSession().setAttribute(Constants.PARAM_USER_BASE_INFO, userLoginInfo);
		//bind
		userSnsEntity.setUserId(userLoginInfo.getUserId());
		userLoginService.operateBindQqLogin(userSnsEntity);
		
		//跳转
		String redirectUrl = (String)request.getSession().getAttribute("redirectTo");
		if(StringUtils.isBlank(redirectUrl)){
			redirectUrl = Constants.SYS_INDEX;
		}
		return "redirect:"+redirectUrl;
	}
	

}
