package net.water.team.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import net.water.login.dao.IUserLoginDAO;
import net.water.team.dao.ITeamDAO;
import net.water.team.dto.TeamDto;
import net.water.team.entity.TeamEntity;
import net.water.team.service.ITeamService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

public class TeamService implements ITeamService {
	
	@Autowired
	private ITeamDAO teamDAO;
	
	@Autowired
	private IUserLoginDAO userLoginDAO;

	public List<TeamEntity> queryTeamByPage(TeamDto teamDto, Model model) throws Exception {
		return teamDAO.queryTeams(teamDto);
	}
	
	public TeamEntity getTeamById(TeamDto teamDto, Model model) throws Exception {
		String teamId = teamDto.getTeamId();
		if(StringUtils.isBlank(teamId)){
			return null;
		}
		TeamEntity teamEntity = teamDAO.getTeamById(teamId);
		if(teamEntity != null) {
			teamEntity.toTeamDto(teamDto);
		}
		return teamEntity;
	}

	public void saveTeam(TeamDto teamDto, Model model) throws Exception {
		TeamEntity teamEntity = teamDto.toTeamEntity();
		if(StringUtils.isNotBlank(teamDto.getTeamId())) {
			teamDAO.updateTeam(teamEntity);
		} else {
			//创建团队
			String teamId = UUID.randomUUID().toString();
			teamEntity.setTeamId(teamId);
			teamEntity.setCreateUserId(teamEntity.getManagerId());
			teamEntity.setCreateTime(new Date());
			teamDAO.createTeam(teamEntity);
			teamDto.setTeamId(teamId);
			//修改当前用户团队信息
			String userId = teamDto.getManagerId();
			userLoginDAO.updateUserTeam(userId, teamId);
		}
	}

}

