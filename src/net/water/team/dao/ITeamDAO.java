package net.water.team.dao;
import java.util.List;

import net.kuakao.core.exception.DataBaseException;
import net.water.team.dto.TeamDto;
import net.water.team.entity.TeamEntity;

/**
 * 团队
 * @作者 autopub
 * @创建日期 2013-12-18
 * @版本 V 1.0
 *
 */
public interface ITeamDAO {
	
	/**
	 * 团队(分页)查询
	 * @param teamDto 团队Dto
	 * @return 团队列表
	 * @throws DataBaseException
	 */
	public List<TeamEntity> queryTeams(TeamDto teamDto) throws DataBaseException;
	
	/**
	 * 查询单个团队对象
	 * @param teamDto 团队Dto
	 * @return 团队
	 * @throws DataBaseException
	 */
	public TeamEntity getTeamById(int teamId) throws DataBaseException;
	
	/**
	 * 创建团队
	 * @param teamEntity 团队实体类
	 * @throws DataBaseException
	 */
	public void createTeam(TeamEntity teamEntity) throws DataBaseException;
	
	/**
	 * 更新团队
	 * @param teamEntity 团队实体类
	 * @throws DataBaseException
	 */
	public void updateTeam(TeamEntity teamEntity) throws DataBaseException;
	
	/**
	 * 修改团队状态
	 * @param teamId 团队ID
	 * @param status 状态
	 * @throws DataBaseException
	 */
	public void updateTeamStatus(int teamId,int status) throws DataBaseException;

}

