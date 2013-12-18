package net.water.login.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.water.Constants;
import net.water.login.entity.UserLoginEntity;
import net.water.login.service.IUserLoginService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.javabeans.AccessToken;
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
		
		if (accessTokenObj == null) {
			request.setAttribute("errorMsg", "QQ登录异常，请用其它方式登录");
			request.setAttribute("redirectTo", request.getSession().getAttribute("redirectTo"));
			return "show/login";
		}
		accessToken = accessTokenObj.getAccessToken();
		if ("".equals(accessToken)) {
			request.setAttribute("errorMsg", "QQ登录异常，请用其它方式登录");
			request.setAttribute("redirectTo", request.getSession().getAttribute("redirectTo"));
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
			request.setAttribute("redirectTo", request.getSession().getAttribute("redirectTo"));
			return "show/login";
		}
		UserLoginEntity userLoginEntity = new UserLoginEntity();
		userLoginEntity.setQqAccessToken(accessToken);
		userLoginEntity.setQqOpenId(openID);
		boolean qqLoginExist = userLoginService.operateQqLoginExist(userLoginEntity);
		if(qqLoginExist){
			request.getSession().setAttribute(Constants.PARAM_USER_BASE_INFO, userLoginEntity);
			String redirectTo = request.getParameter("redirectTo");
			if(StringUtils.isNotBlank(redirectTo)){
				return "redirect:"+redirectTo;
			}
			return "redirect:"+Constants.SYS_INDEX;
		}else{// 绑定或注册
			request.setAttribute("qqOpenId", openID);
    		request.setAttribute("qqAccessToken", accessToken);
    		return "show/bind";
		}
	}
	/**
	 * 验证用户输入的用户名密码
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "validate_user_pwd")
	@Deprecated
	public void validateUserPwd(HttpServletRequest request, HttpServletResponse response, Model model){
		PrintWriter writer = null;
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		Map<String,String> msgs = new HashMap<String,String>();
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(writer == null){
			return;
		}
		String uname = request.getParameter("uname");
		if(uname == null){
			msgs.put("error", "请输入用户名");
			writer.print(JSONObject.fromObject(msgs).toString());
			writer.flush();
			return;
		}
		uname = uname.trim();
		if("".equals(uname)){
			msgs.put("error", "请输入用户名");
			writer.print(JSONObject.fromObject(msgs).toString());
			writer.flush();
			return;
		}
		String pwd = request.getParameter("upwd");
		if(pwd == null){
			msgs.put("error", "请输入密码");
			writer.print(JSONObject.fromObject(msgs).toString());
			writer.flush();
			return;
		}
		pwd = pwd.trim();
		if("".equals(pwd)){
			msgs.put("error", "请输入密码");
			writer.print(JSONObject.fromObject(msgs).toString());
			writer.flush();
			return;
		}
		UserLoginEntity userLoginEntity = new UserLoginEntity();
		userLoginEntity.setUname(uname);
		userLoginEntity.setUpwd(pwd);
		UserLoginEntity userBaseInfo = userLoginService.queryUserLogin(userLoginEntity,model);
		if(userBaseInfo == null){
			msgs.put("error", "用户名或密码错误");
			writer.print(JSONObject.fromObject(msgs).toString());
			writer.flush();
		}else{
			msgs.put("suc", "suc");
			msgs.put("userId", userBaseInfo.getUserId()+"");
			writer.print(JSONObject.fromObject(msgs).toString());
			writer.flush();
		}
	}
	
	@RequestMapping(value = "bind_sns_account")
	public String bindSnsAccount(UserLoginEntity userLoginEntity,HttpServletRequest request,HttpServletResponse response, Model model) {
		UserLoginEntity userBaseInfo = userLoginService.queryUserLogin(userLoginEntity,model);
		if(userBaseInfo == null){
			request.setAttribute("qqOpenId", request.getParameter("qqOpenId"));
    		request.setAttribute("qqAccessToken", request.getParameter("qqAccessToken"));
    		return "show/bind";
		}
		//bind
		userLoginEntity.setUserId(userBaseInfo.getUserId());
		UserLoginEntity baseInfo = userLoginService.operateBindQqLogin(userLoginEntity);
		if(baseInfo == null){
			request.setAttribute("errorMsg", "QQ登录异常，请用其它方式登录");
			request.setAttribute("redirectTo", request.getSession().getAttribute("redirectTo"));
			return "show/login";
		}else{
			request.getSession().setAttribute(Constants.PARAM_USER_BASE_INFO, baseInfo);
		}
		//跳转
		String redirectUrl = request.getParameter("redirectUrl");
		if(StringUtils.isBlank(redirectUrl)){
			redirectUrl = (String)request.getSession().getAttribute("redirectTo");
		}
		if(StringUtils.isBlank(redirectUrl)){
			redirectUrl = Constants.SYS_INDEX;
		}
		return "redirect:"+redirectUrl;
	}
}
