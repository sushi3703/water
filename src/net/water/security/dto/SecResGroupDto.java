package net.water.security.dto;


import net.kuakao.core.base.util.SystemUtils;
import net.kuakao.core.dto.PagerDTO;
import net.water.security.entity.SecResGroupEntity;
public class SecResGroupDto extends PagerDTO {
	
	/* ID */
	private String  groupId;
			
			
	/* 组名 */
	private String  groupName;
			
			
	/* 所有者ID */
	private String  ownerId;
			
			
	/* 包含的资源ID */
	private String  resIds;
			
			
	/* 状态，1有效0删除 */
	private String  status;
			
			

	public String  getGroupId() {
		return groupId;
	}
	
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
			
	public String  getGroupName() {
		return groupName;
	}
	
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
			
	public String  getOwnerId() {
		return ownerId;
	}
	
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
			
	public String  getResIds() {
		return resIds;
	}
	
	public void setResIds(String resIds) {
		this.resIds = resIds;
	}
			
	public String  getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
			

	public SecResGroupEntity toSecResGroupEntity() {
		SecResGroupEntity secResGroupEntity = new SecResGroupEntity();
		secResGroupEntity.setGroupId(SystemUtils.strToInt(this.groupId));
		
		secResGroupEntity.setGroupName(this.getGroupName());
		
		secResGroupEntity.setOwnerId(SystemUtils.strToInt(this.ownerId));
		
		secResGroupEntity.setResIds(this.getResIds());
		
		secResGroupEntity.setStatus(SystemUtils.strToInt(this.status));
		
		return secResGroupEntity;
	}
}

