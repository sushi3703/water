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
import net.water.user.dto.UserBaseDto;
import net.water.user.entity.UserBaseEntity;
import net.water.user.service.IUserBaseService;

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
	private IUserBaseService userBaseService;
	
	@RequestMapping("index")
	public String index(HttpServletRequest request,@ModelAttribute("_page") UserBaseDto userBaseDto, Model model) throws Exception {
		userBaseDto.setQueryStr(request.getQueryString() == null ? "" : request.getQueryString());
		List<UserBaseEntity> userBaseEntitys = userBaseService.queryUserBaseByPage(userBaseDto ,model);
		model.addAttribute("userEntitys", userBaseEntitys);
		return "admin/user/user_index";
	}
	
	@RequestMapping("edit")
	public String edit(@ModelAttribute("userBaseDto") UserBaseDto userBaseDto, Model model,HttpServletRequest request) throws Exception {
		userBaseDto.setFormTokenCode(request);
		userBaseService.getUserBaseById(userBaseDto ,model);
		return "admin/user/user_edit";
	}
	
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String save(@ModelAttribute("userBaseDto") UserBaseDto userBaseDto, Model model,HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(FormTokenUtil.validFormToken(request, response, userBaseDto, true)) {
			return "userBase/userBase_edit";
		}
		userBaseService.saveUserBase(userBaseDto ,model);
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
			userBaseService.destroyUser(userBaseDto ,model);
			msgs.put("success","删除成功!");
		} catch (DataBaseException e) {
			e.printStackTrace();
			msgs.put("error","系统异常，请稍候再试");
		}
		
		writer.print(JSONObject.fromObject(msgs));
		writer.flush();
	}

}

