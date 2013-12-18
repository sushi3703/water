package net.water.team.service.impl;

import java.util.Date;
import java.util.List;

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

	public List<TeamEntity> queryTeamByPage(TeamDto teamDto, Model model) throws Exception {
		return teamDAO.queryTeams(teamDto);
	}

	public TeamEntity getTeamById(TeamDto teamDto, Model model) throws Exception {
			TeamEntity teamEntity = null;
			if(StringUtils.isNotBlank(teamDto.getTeamId())) {
				teamEntity = teamDAO.getTeamById(Integer.parseInt(teamDto.getTeamId()));
				if(teamEntity != null) {
					teamEntity.toTeamDto(teamDto);
				}
			}
			return teamEntity;
	}

	public void saveTeam(TeamDto teamDto, Model model) throws Exception {
		TeamEntity teamEntity = teamDto.toTeamEntity();
		if(StringUtils.isNotBlank(teamDto.getTeamId())) {
			teamDAO.updateTeam(teamEntity);
		} else {
			teamEntity.setCreateTime(new Date());
			teamDAO.createTeam(teamEntity);
		}
	}

}

