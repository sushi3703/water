package net.water.security.entity;
import java.io.Serializable;

public class SecUserResourceEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/* ID */
	private int  id;
			
	/* 用户ID */
	private String  userId;
	
	/*资源ID*/
	private String resId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getResId() {
		return resId;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}

}

