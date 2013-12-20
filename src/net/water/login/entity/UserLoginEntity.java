package net.water.login.entity;

import java.io.Serializable;

public class UserLoginEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/* ID */
	private String  userId;
	
	/*用户名*/
	private String uname;
	/*密码*/
	private String upwd;
	/*邮箱*/
	private String email;
	/*状态*/
	private int status;

	/*用户类型,1根用户,2普通用户*/
	private int type;
	
	/*所属团队ID*/
	private String teamId;
	
	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUpwd() {
		return upwd;
	}

	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	

}
