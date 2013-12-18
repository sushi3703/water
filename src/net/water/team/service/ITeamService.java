package net.water.team.service;
import java.util.List;

import net.water.team.dto.TeamDto;
import net.water.team.entity.TeamEntity;

import org.springframework.ui.Model;


public interface ITeamService {
	
	/**
	 * 团队查询
	 * @param teamDto 团队Dto
	 * @return 团队列表
	 * @throws Exception
	 */
	public List<TeamEntity> queryTeamByPage(TeamDto teamDto, Model model) throws Exception;
	
	/**
	 * 查询单个团队对象
	 * @param teamDto 团队Dto
	 * @return 团队
	 * @throws Exception
	 */
	public TeamEntity getTeamById(TeamDto teamDto, Model model) throws Exception;
	
	
	/**
	 * 保存或更新团队
	 * @param teamDto
	 * @throws Exception
	 */
	public void saveTeam(TeamDto teamDto, Model model) throws Exception;
	
}
