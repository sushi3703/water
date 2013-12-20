package net.water.team.dto;


import net.kuakao.core.dto.PagerDTO;
import net.water.team.entity.TeamEntity;
import net.water.tool.math.SuDateUtils;
import net.water.tool.math.SuIntUtils;
public class TeamDto extends PagerDTO {
	
	/* 团队ID */
	private String  teamId;
			
			
	/* 团队名称 */
	private String  teamName;
			
			
	/* 创建者ID */
	private String  createUserId;
			
			
	/* 创建时间 */
	private String  createTime;
			
			
	/* 当前管理员ID */
	private String  managerId;
			
			
	/*状态，1有效，0删除*/
	private String status;
	
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String  getTeamId() {
		return teamId;
	}
	
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
			
	public String  getTeamName() {
		return teamName;
	}
	
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
			
	public String  getCreateUserId() {
		return createUserId;
	}
	
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
			
	public String  getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
			
	public String  getManagerId() {
		return managerId;
	}
	
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
			

	public TeamEntity toTeamEntity() {
		TeamEntity teamEntity = new TeamEntity();
		teamEntity.setTeamId(this.teamId);
		
		teamEntity.setTeamName(this.getTeamName());
		
		teamEntity.setCreateUserId(this.createUserId);
		
		teamEntity.setCreateTime(SuDateUtils.getDateByFormatString(this.createTime,"yyyy-MM-dd HH:mm:ss"));
		
		teamEntity.setManagerId(this.managerId);
		
		teamEntity.setStatus(SuIntUtils.getInt(this.status));
		
		return teamEntity;
	}
}

