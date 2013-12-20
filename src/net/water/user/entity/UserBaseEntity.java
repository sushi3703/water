package net.water.user.entity;
import java.io.Serializable;
import java.util.Date;

import net.kuakao.core.base.util.SystemUtils;
import net.water.user.dto.UserBaseDto;

public class UserBaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/*  */
	private String  userId;
			
	/* 创建时间 */
	private Date  createTime;
			
	/* 部门 */
	private String  department;
			
	/* 职务 */
	private String  jobTitle;
			
	/* 联系QQ */
	private String  qq;
			
	/* 手机 */
	private String  mobile;
			
	/* 备注 */
	private String  note;
			
	
	/*查询条件并显示信息，用户名*/
	private String uname;
	
	/*查询条件并显示信息，邮箱*/
	private String email;
	
	/*查询条件并显示信息，所属团队*/
	private String teamId;

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}	
	public String getDepartment() {
		return department;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}	
	public String getJobTitle() {
		return jobTitle;
	}
	
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}	
	public String getQq() {
		return qq;
	}
	
	public void setQq(String qq) {
		this.qq = qq;
	}	
	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}	
	public String getNote() {
		return note;
	}
	
	public void setNote(String note) {
		this.note = note;
	}	
	
	public void toUserBaseDto(UserBaseDto userBaseDto) {
		userBaseDto.setUserId(this.userId);
		
		userBaseDto.setCreateTime(SystemUtils.dateToStr(this.createTime,"yyyy-MM-dd HH:mm:ss"));
		
		userBaseDto.setDepartment(this.department);
		
		userBaseDto.setJobTitle(this.jobTitle);
		
		userBaseDto.setQq(this.qq);
		
		userBaseDto.setMobile(this.mobile);
		
		userBaseDto.setNote(this.note);
		
	}

}

