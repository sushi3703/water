package net.water.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.water.user.dto.UserBaseDto;
import net.water.user.entity.UserBaseEntity;
import net.water.user.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/user")
public class UserAdminController {
	
	@Autowired
	private IUserService userService;
	
	/**
	 * 所有根用户
	 * @param request
	 * @param userBaseDto
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("index")
	public String index(HttpServletRequest request,@ModelAttribute("_page") UserBaseDto userBaseDto, Model model) throws Exception {
		userBaseDto.setQueryStr(request.getQueryString() == null ? "" : request.getQueryString());
		
		List<UserBaseEntity> userBaseEntitys = userService.getAllBaseUser(userBaseDto ,model);
		model.addAttribute("userEntitys", userBaseEntitys);
		return "admin/user/user_index";
	}

}

