package net.water.security.entity;
import java.io.Serializable;

import net.water.security.dto.SecResourceDto;

public class SecResourceEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/* ID */
	private int  resId;
			
	/* 资源名称 */
	private String  resName;
			
	/* 所属项目 */
	private int  appType;
			
	/* 所属菜单 */
	private int  appMenu;
			
	/* 资源包含的url */
	private String  urlIds;
			
	/* 状态，1有效0删除 */
	private int  status;
			
	
	public int getResId() {
		return resId;
	}
	
	public void setResId(int resId) {
		this.resId = resId;
	}	
	public String getResName() {
		return resName;
	}
	
	public void setResName(String resName) {
		this.resName = resName;
	}	
	public int getAppType() {
		return appType;
	}
	
	public void setAppType(int appType) {
		this.appType = appType;
	}	
	public int getAppMenu() {
		return appMenu;
	}
	
	public void setAppMenu(int appMenu) {
		this.appMenu = appMenu;
	}	
	public String getUrlIds() {
		return urlIds;
	}
	
	public void setUrlIds(String urlIds) {
		this.urlIds = urlIds;
	}	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}	
	
	public void toSecResourceDto(SecResourceDto SecResourceDto) throws Exception {
		SecResourceDto.setResId(String.valueOf(this.resId));
		
		SecResourceDto.setResName(this.resName);
		
		SecResourceDto.setAppType(String.valueOf(this.appType));
		
		SecResourceDto.setAppMenu(String.valueOf(this.appMenu));
		
		SecResourceDto.setUrlIds(this.urlIds);
		
		SecResourceDto.setStatus(String.valueOf(this.status));
		
	}

}

