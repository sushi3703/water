package net.water.user.dto;


import net.kuakao.core.base.util.SystemUtils;
import net.kuakao.core.dto.PagerDTO;
import net.water.user.entity.UserBaseEntity;
public class UserBaseDto extends PagerDTO {
	
	/*  */
	private String  userId;
			
			
	/* 创建时间 */
	private String  createTime;
			
			
	/* 部门 */
	private String  department;
			
			
	/* 头衔 */
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

	public String  getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
			
	public String  getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
			
	public String  getDepartment() {
		return department;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}
			
	public String  getJobTitle() {
		return jobTitle;
	}
	
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
			
	public String  getQq() {
		return qq;
	}
	
	public void setQq(String qq) {
		this.qq = qq;
	}
			
	public String  getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String  getNote() {
		return note;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
			

	public UserBaseEntity toUserBaseEntity() {
		UserBaseEntity userBaseEntity = new UserBaseEntity();
		userBaseEntity.setUserId(this.userId);
		
		
		userBaseEntity.setCreateTime(SystemUtils.strToDate(this.createTime,"yyyy-MM-dd HH:mm:ss"));
		
		userBaseEntity.setDepartment(this.getDepartment());
		
		userBaseEntity.setJobTitle(this.getJobTitle());
		
		userBaseEntity.setQq(this.getQq());
		
		userBaseEntity.setMobile(this.getMobile());
		
		userBaseEntity.setNote(this.getNote());
		
		return userBaseEntity;
	}
}

