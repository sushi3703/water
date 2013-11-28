package net.water.user.dto;


import net.kuakao.core.base.util.SystemUtils;
import net.kuakao.core.dto.PagerDTO;
import net.water.user.entity.UserBaseEntity;
public class UserBaseDto extends PagerDTO {
	
	/*  */
	private String  userId;
			
			
	/* 创建者 */
	private String  createUserId;
			
			
	/* 创建时间 */
	private String  createTime;
			
			
	/* 创建或注册时的ip */
	private String  createIp;
			
			
	/* 公司 */
	private String  company;
			
			
	/* 部门 */
	private String  department;
			
			
	/* 头衔 */
	private String  jobTitle;
			
			
	/* 联系QQ */
	private String  qq;
			
			
	/* 手机 */
	private String  mobile;
			
			
	/* 固定电话 */
	private String  tel;
			
			
	/* 备注 */
	private String  note;
			
			
	/*查询条件，用户名*/
	private String uname;
	
	/*查询条件，邮箱*/
	private String email;

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
			
	public String  getCreateIp() {
		return createIp;
	}
	
	public void setCreateIp(String createIp) {
		this.createIp = createIp;
	}
			
	public String  getCompany() {
		return company;
	}
	
	public void setCompany(String company) {
		this.company = company;
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
			
	public String  getTel() {
		return tel;
	}
	
	public void setTel(String tel) {
		this.tel = tel;
	}
			
	public String  getNote() {
		return note;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
			

	public UserBaseEntity toUserBaseEntity() {
		UserBaseEntity userBaseEntity = new UserBaseEntity();
		userBaseEntity.setUserId(SystemUtils.strToInt(this.userId));
		
		userBaseEntity.setCreateUserId(SystemUtils.strToInt(this.createUserId));
		
		userBaseEntity.setCreateTime(SystemUtils.strToDate(this.createTime,"yyyy-MM-dd HH:mm:ss"));
		
		userBaseEntity.setCreateIp(this.getCreateIp());
		
		userBaseEntity.setCompany(this.getCompany());
		
		userBaseEntity.setDepartment(this.getDepartment());
		
		userBaseEntity.setJobTitle(this.getJobTitle());
		
		userBaseEntity.setQq(this.getQq());
		
		userBaseEntity.setMobile(this.getMobile());
		
		userBaseEntity.setTel(this.getTel());
		
		userBaseEntity.setNote(this.getNote());
		
		return userBaseEntity;
	}
}

