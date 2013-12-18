package net.water.team.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.kuakao.core.base.dao.BaseDAO;
import net.kuakao.core.exception.DataBaseException;
import net.water.team.dao.ITeamDAO;
import net.water.team.dto.TeamDto;
import net.water.team.entity.TeamEntity;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository
public class TeamDAO extends BaseDAO implements ITeamDAO {
	
	public List<TeamEntity> queryTeams(TeamDto teamDto) throws DataBaseException {
		String sql = "select team_id,team_name,create_user_id,create_time,manager_id,status from w_team";
		StringBuffer where = new StringBuffer();
		List<Object> args = new ArrayList<Object>();
		TeamEntity teamEntity = teamDto.toTeamEntity();
		
		if(StringUtils.isNotBlank(teamDto.getTeamId())) {
			args.add(teamEntity.getTeamId());
			where.append(" and team_id=?");
		}
		if(StringUtils.isNotBlank(teamDto.getTeamName())) {
			where.append(" and team_name like '%"+teamEntity.getTeamName()+"%'");
		}
		if(StringUtils.isNotBlank(teamDto.getCreateUserId())) {
			args.add(teamEntity.getCreateUserId());
			where.append(" and create_user_id=?");
		}
		if(StringUtils.isNotBlank(teamDto.getManagerId())) {
			args.add(teamEntity.getManagerId());
			where.append(" and manager_id=?");
		}
		if(StringUtils.isNotBlank(teamDto.getStatus())) {
			args.add(teamEntity.getStatus());
			where.append(" and status=?");
		}
		
		String sqlSel = sql + where.toString().replaceFirst("and","where") + " order by team_id desc";
		if(teamDto.getPerPage() == -1){
			return super.query(sqlSel, args.toArray(), new RowMapper<TeamEntity>() {

				public TeamEntity mapRow(ResultSet rs, int arg1) throws SQLException {
					TeamEntity teamEntity = new TeamEntity();
					teamEntity.setTeamId(rs.getInt("team_id"));
					teamEntity.setTeamName(rs.getString("team_name"));
					teamEntity.setCreateUserId(rs.getInt("create_user_id"));
					teamEntity.setCreateTime(rs.getTimestamp("create_time"));
					teamEntity.setManagerId(rs.getInt("manager_id"));
					teamEntity.setStatus(rs.getInt("status"));
					return teamEntity;
				}
				
			});
		}else{
			return super.queryByPage(sql + where.toString().replaceFirst("and","where"), args.toArray(), new RowMapper<TeamEntity>() {

				public TeamEntity mapRow(ResultSet rs, int arg1) throws SQLException {
					TeamEntity teamEntity = new TeamEntity();
					teamEntity.setTeamId(rs.getInt("team_id"));
					teamEntity.setTeamName(rs.getString("team_name"));
					teamEntity.setCreateUserId(rs.getInt("create_user_id"));
					teamEntity.setCreateTime(rs.getTimestamp("create_time"));
					teamEntity.setManagerId(rs.getInt("manager_id"));
					teamEntity.setStatus(rs.getInt("status"));
					return teamEntity;
				}
				
			}, teamDto);
		}
	}

	
	
	public TeamEntity getTeamById(int teamId) throws DataBaseException {
		String sql = "select team_id,team_name,create_user_id,create_time,manager_id,status from w_team where team_id = ?  limit 1";
		return super.queryForObject(sql, new Object[]{teamId }, new RowMapper<TeamEntity>() {
			public TeamEntity mapRow(ResultSet rs, int value) throws SQLException {
				TeamEntity teamEntity = new TeamEntity();
				teamEntity.setTeamId(rs.getInt("team_id"));
				teamEntity.setTeamName(rs.getString("team_name"));
				teamEntity.setCreateUserId(rs.getInt("create_user_id"));
				teamEntity.setCreateTime(rs.getTimestamp("create_time"));
				teamEntity.setManagerId(rs.getInt("manager_id"));
				teamEntity.setStatus(rs.getInt("status"));
				return teamEntity;
			}
		});
	}

	
	public void updateTeamStatus(int teamId,int status) throws DataBaseException {
		String sql = "update w_team set status=? where team_id = ? ";
		super.update(sql, new Object[]{status,teamId});
	}
	
	
	
	public void createTeam(TeamEntity teamEntity) throws DataBaseException {
		String sql = "insert into w_team(team_name ,create_user_id ,create_time ,manager_id,status ) values(? ,? ,? ,?,? )";
		super.update(sql, new Object[]{teamEntity.getTeamName() ,teamEntity.getCreateUserId() ,teamEntity.getCreateTime() ,teamEntity.getManagerId(),teamEntity.getStatus() });
	}
	
	
	
	public void updateTeam(TeamEntity teamEntity) throws DataBaseException {
		if(teamEntity.getTeamId()==0)return;
		
		StringBuffer updateSql = new StringBuffer("update w_team set ");
		List<Object> args = new ArrayList<Object>();
		if(StringUtils.isNotBlank(teamEntity.getTeamName())){
			args.add(teamEntity.getTeamName());
			updateSql.append("team_name=?").append(",");	
		}
		if(teamEntity.getManagerId() != 0){
			args.add(teamEntity.getManagerId());
			updateSql.append("manager_id=?").append(",");	
		}
		
		args.add(teamEntity.getTeamId());
		
		String sql = updateSql.substring(0, updateSql.length()-1) + " where team_id = ? "; 
		super.update(sql, args.toArray());
	}

}

