package net.water.user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.kuakao.core.base.util.FormTokenUtil;
import net.kuakao.core.exception.DataBaseException;
import net.sf.json.JSONObject;
import net.water.Constants;
import net.water.login.entity.UserLoginEntity;
import net.water.user.dto.UserBaseDto;
import net.water.user.entity.UserBaseEntity;
import net.water.user.service.IUserService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/user")
public class UserAdminController {
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping("index")
	public String index(HttpServletRequest request,@ModelAttribute("_page") UserBaseDto userBaseDto, Model model) throws Exception {
		userBaseDto.setQueryStr(request.getQueryString() == null ? "" : request.getQueryString());
		List<UserBaseEntity> userBaseEntitys = userService.queryUserBaseByPage(userBaseDto ,model);
		model.addAttribute("userEntitys", userBaseEntitys);
		return "admin/user/user_index";
	}
	
	@RequestMapping("edit")
	public String edit(@ModelAttribute("userBaseDto") UserBaseDto userBaseDto, Model model,HttpServletRequest request) throws Exception {
		userBaseDto.setFormTokenCode(request);
		userService.getUserBaseById(userBaseDto ,model);
		return "admin/user/user_edit";
	}
	
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String save(@ModelAttribute("userBaseDto") UserBaseDto userBaseDto, Model model,HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(FormTokenUtil.validFormToken(request, response, userBaseDto, true)) {
			return "userBase/userBase_edit";
		}
		userService.saveUserBase(userBaseDto ,model);
		return "redirect:/admin/userBase/index.action?" + userBaseDto.getQueryStr();
	}
	
	
	@RequestMapping("destroy")
	public void destroy(UserBaseDto userBaseDto, Model model, HttpServletResponse response) {
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
		Map<String,String> msgs = new HashMap<String,String>();
		try {
			userService.destroyUser(userBaseDto ,model);
			msgs.put("success","删除成功!");
		} catch (DataBaseException e) {
			e.printStackTrace();
			msgs.put("error","系统异常，请稍候再试");
		}
		
		writer.print(JSONObject.fromObject(msgs));
		writer.flush();
	}

	/**
	 * 管理员重置用户密码
	 * @param userLoginEntity userId/email
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("reset_pwd")
	public void resetPwd(UserLoginEntity userLoginEntity,HttpServletRequest request,HttpServletResponse response, Model model){
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
		if(StringUtils.isBlank(userLoginEntity.getUserId()) || StringUtils.isBlank(userLoginEntity.getEmail())){
			writer.print("请选择要重置密码的用户");
			writer.flush();
			return;
		}
		//默认重置密码
		String defaultResetPwd = "010";
		userLoginEntity.setUpwd(defaultResetPwd);
		String res = "aa";
		try {
			userService.updateUserPwd(userLoginEntity);
			res = "密码重置成功，新密码为"+defaultResetPwd;
		} catch (Exception e) {
			e.printStackTrace();
			res = "密码重置失败，请联系管理员";
		}
		writer.print(res);
		writer.flush();
	}
	
}

