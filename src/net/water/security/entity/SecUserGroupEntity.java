package net.water.security.entity;
import java.io.Serializable;

public class SecUserGroupEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/*  */
	private int  id;
			
	/*  */
	private int  userId;
			
	/*  */
	private int  groupId;
			
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}	
	public int getGroupId() {
		return groupId;
	}
	
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}	
	
}
