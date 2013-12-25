package net.water.team.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.water.Constants;
import net.water.login.entity.UserLoginEntity;
import net.water.login.service.IUserLoginService;
import net.water.team.dto.TeamDto;
import net.water.team.entity.TeamEntity;
import net.water.team.service.ITeamService;
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
@RequestMapping("/front/team")
public class TeamFrontController {
	
	@Autowired
	private ITeamService teamService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IUserLoginService userLoginService;
	
	/**
	 * 
	 * @param teamDto
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("my_team")
	public String myTeam(Model model,HttpServletRequest request) {
		//团队信息
		UserLoginEntity userLoginInfo = (UserLoginEntity)request.getSession().getAttribute(Constants.PARAM_USER_BASE_INFO);
		if(userLoginInfo == null){
			return "/login/to_login.action?redirectTo=/front/team/my_team.action";
		}
		String teamId = userLoginInfo.getTeamId();
		TeamDto teamDto = new TeamDto();
		teamDto.setTeamId(teamId);
		TeamEntity teamEntity = null;
		try {
			teamEntity = teamService.getTeamById(teamDto ,model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("teamEntity", teamEntity);
		//团队成员
		if(teamEntity != null){
			UserBaseDto userBaseDto = new UserBaseDto();
			userBaseDto.setTeamId(teamId);
			userBaseDto.setPerPage(-1);
			List<UserBaseEntity> users = userService.queryUsers(userBaseDto, model);
			model.addAttribute("users", users);
		}
		//提示信息
		try {
			request.setCharacterEncoding("gbk");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String msg = request.getParameter(Constants.PARAM_SHOW_MSG);
		if(StringUtils.isNotBlank(msg)){
			model.addAttribute(Constants.PARAM_SHOW_MSG, msg);
		}
		return "front/team/my_team";
	}
	
	@RequestMapping(value="create_team",method=RequestMethod.POST)
	public String createTeam(TeamDto teamDto, Model model,HttpServletRequest request) {
		String userId = (String)request.getAttribute(Constants.PARAM_USER_LOGIN_ID);
		teamDto.setManagerId(userId);
		String res = "suc";
		try {
			teamService.saveTeam(teamDto ,model);
		} catch (Exception e) {
			e.printStackTrace();
			res = Constants.INFO_EXCEPTION;
		}
		if(res.equals("suc")){
			res = "保存成功";
			//更新session中用户的团队信息
			UserLoginEntity userLoginEntity = userLoginService.getUserLoginByUserId(userId);
			request.getSession().setAttribute(Constants.PARAM_USER_BASE_INFO, userLoginEntity);
		}
		model.addAttribute(Constants.PARAM_SHOW_MSG, res);
		return "redirect:/front/team/my_team.action";
	}
	
	@RequestMapping(value="save",method=RequestMethod.POST)
	public void save(TeamDto teamDto, Model model,HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if(writer == null){
			return;
		}
		if(StringUtils.isBlank(teamDto.getTeamName())){
			writer.print("请输入团队名称");
			writer.flush();
			return;
		}
		String res = "保存成功";
		String userId = (String)request.getAttribute(Constants.PARAM_USER_LOGIN_ID);
		teamDto.setManagerId(userId);
		try {
			teamService.saveTeam(teamDto ,model);
		} catch (Exception e) {
			e.printStackTrace();
			res = Constants.INFO_EXCEPTION;
		}
		writer.print(res);
		writer.flush();
	}
	
}

