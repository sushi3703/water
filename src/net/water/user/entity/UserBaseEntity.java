package net.water.user.entity;
import java.io.Serializable;
import java.util.Date;

import net.kuakao.core.base.util.SystemUtils;
import net.water.user.dto.UserBaseDto;

public class UserBaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/*  */
	private int  userId;
			
	/* 创建者 */
	private int  createUserId;
			
	/* 创建时间 */
	private Date  createTime;
			
	/* 创建或注册时的ip */
	private String  createIp;
			
	/* 公司 */
	private String  company;
			
	/* 部门 */
	private String  department;
			
	/* 职务 */
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

	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
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
	public String getCreateIp() {
		return createIp;
	}
	
	public void setCreateIp(String createIp) {
		this.createIp = createIp;
	}	
	public String getCompany() {
		return company;
	}
	
	public void setCompany(String company) {
		this.company = company;
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
	public String getTel() {
		return tel;
	}
	
	public void setTel(String tel) {
		this.tel = tel;
	}	
	public String getNote() {
		return note;
	}
	
	public void setNote(String note) {
		this.note = note;
	}	
	
	public void toUserBaseDto(UserBaseDto userBaseDto) {
		userBaseDto.setUserId(String.valueOf(this.userId));
		
		userBaseDto.setCreateUserId(String.valueOf(this.createUserId));
		
		userBaseDto.setCreateTime(SystemUtils.dateToStr(this.createTime,"yyyy-MM-dd HH:mm:ss"));
		
		userBaseDto.setCreateIp(this.createIp);
		
		userBaseDto.setCompany(this.company);
		
		userBaseDto.setDepartment(this.department);
		
		userBaseDto.setJobTitle(this.jobTitle);
		
		userBaseDto.setQq(this.qq);
		
		userBaseDto.setMobile(this.mobile);
		
		userBaseDto.setTel(this.tel);
		
		userBaseDto.setNote(this.note);
		
	}

}

