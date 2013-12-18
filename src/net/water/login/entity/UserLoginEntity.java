package net.water.login.entity;

import java.io.Serializable;

public class UserLoginEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/* ID */
	private int  userId;
	
	/*用户名*/
	private String uname;
	/*密码*/
	private String upwd;
	/*邮箱*/
	private String email;
	/*状态*/
	private int status;

	/*用户类型,1注册用户,2管理用户*/
	private int type;
	
	private String qqOpenId;
	
	private String qqAccessToken;
	
	/*所属团队ID*/
	private int teamId;
	
	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public String getQqOpenId() {
		return qqOpenId;
	}

	public void setQqOpenId(String qqOpenId) {
		this.qqOpenId = qqOpenId;
	}

	public String getQqAccessToken() {
		return qqAccessToken;
	}

	public void setQqAccessToken(String qqAccessToken) {
		this.qqAccessToken = qqAccessToken;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
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
