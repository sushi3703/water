package net.water.user.entity;

import java.io.Serializable;
import java.util.Date;

public class UserSnsEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/* ID */
	private int  userId;
	
	/*qq用户名*/
	private String qqUsername;

	private String qqOpenId;
	
	private String qqAccessToken;
	
	/*最后更新时间*/
	private Date updateTime;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getQqUsername() {
		return qqUsername;
	}

	public void setQqUsername(String qqUsername) {
		this.qqUsername = qqUsername;
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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
