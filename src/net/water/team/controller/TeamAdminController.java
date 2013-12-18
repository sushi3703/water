package net.water.team.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.kuakao.core.base.util.FormTokenUtil;
import net.water.team.dto.TeamDto;
import net.water.team.entity.TeamEntity;
import net.water.team.service.ITeamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/team")
public class TeamAdminController {
	
	@Autowired
	private ITeamService teamService;
	
	@RequestMapping("index")
	public String index(HttpServletRequest request,@ModelAttribute("_page") TeamDto teamDto, Model model) throws Exception {
		teamDto.setQueryStr(request.getQueryString() == null ? "" : request.getQueryString());
		List<TeamEntity> teamEntitys = teamService.queryTeamByPage(teamDto ,model);
		model.addAttribute("teamEntitys", teamEntitys);
		return "team/team_index";
	}
	
	@RequestMapping("show")
	public String show(@ModelAttribute("teamDto") TeamDto teamDto, Model model) throws Exception {
		TeamEntity teamEntity = teamService.getTeamById(teamDto ,model);
		model.addAttribute("teamEntity", teamEntity);
		return "team/team_show";
	}
	
	@RequestMapping("edit")
	public String edit(@ModelAttribute("teamDto") TeamDto teamDto, Model model,HttpServletRequest request) throws Exception {
		teamDto.setFormTokenCode(request);
		teamService.getTeamById(teamDto ,model);
		return "team/team_edit";
	}
	
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String save(@ModelAttribute("teamDto") TeamDto teamDto, Model model,HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(FormTokenUtil.validFormToken(request, response, teamDto, true)) {
			return "team/team_edit";
		}
		teamService.saveTeam(teamDto ,model);
		return "redirect:/admin/team/index.action?" + teamDto.getQueryStr();
	}
	
}

