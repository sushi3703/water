package net.water.team.entity;
import java.io.Serializable;
import java.util.Date;

import net.water.team.dto.TeamDto;
import net.water.tool.math.SuDateUtils;

public class TeamEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/* 团队ID */
	private int  teamId;
			
	/* 团队名称 */
	private String  teamName;
			
	/* 创建者ID */
	private int  createUserId;
			
	/* 创建时间 */
	private Date  createTime;
			
	/* 当前管理员ID */
	private int  managerId;
			
	/*状态，1有效，0删除*/
	private int status;
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getTeamId() {
		return teamId;
	}
	
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}	
	public String getTeamName() {
		return teamName;
	}
	
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}	
	public int getCreateUserId() {
		return createUserId;
	}
	
	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
	}	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}	
	public int getManagerId() {
		return managerId;
	}
	
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}	
	
	public void toTeamDto(TeamDto teamDto) throws Exception {
		teamDto.setTeamId(String.valueOf(this.teamId));
		
		teamDto.setTeamName(this.teamName);
		
		teamDto.setCreateUserId(String.valueOf(this.createUserId));
		
		teamDto.setCreateTime(SuDateUtils.getFormatString(this.createTime,"yyyy-MM-dd HH:mm:ss"));
		
		teamDto.setManagerId(String.valueOf(this.managerId));
		
		teamDto.setStatus(String.valueOf(this.status));
		
	}

}

