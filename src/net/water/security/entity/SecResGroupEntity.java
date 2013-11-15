package net.water.security.entity;
import java.io.Serializable;

import net.water.security.dto.SecResGroupDto;

public class SecResGroupEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/* ID */
	private int  groupId;
			
	/* 组名 */
	private String  groupName;
			
	/* 所有者ID */
	private int  ownerId;
			
	/* 包含的资源ID */
	private String  resIds;
			
	/* 状态，1有效0删除 */
	private int  status;
			
	
	public int getGroupId() {
		return groupId;
	}
	
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}	
	public String getGroupName() {
		return groupName;
	}
	
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}	
	public int getOwnerId() {
		return ownerId;
	}
	
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}	
	public String getResIds() {
		return resIds;
	}
	
	public void setResIds(String resIds) {
		this.resIds = resIds;
	}	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}	
	
	public void toSecResGroupDto(SecResGroupDto secResGroupDto) throws Exception {
		secResGroupDto.setGroupId(String.valueOf(this.groupId));
		
		secResGroupDto.setGroupName(this.groupName);
		
		secResGroupDto.setOwnerId(String.valueOf(this.ownerId));
		
		secResGroupDto.setResIds(this.resIds);
		
		secResGroupDto.setStatus(String.valueOf(this.status));
		
	}

}

