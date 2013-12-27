package net.water.security.entity;
import java.io.Serializable;
import java.util.List;

import net.water.security.dto.SecResourceDto;

public class SecResourceEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/* ID */
	private String  resId;
			
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
	
	/*依赖的基础资源*/
	private String baseRes;
			
	/* 资源所包含的url，用于显示 */
	private List<SecUrlEntity> urls;
	
	public String getBaseRes() {
		return baseRes;
	}

	public void setBaseRes(String baseRes) {
		this.baseRes = baseRes;
	}

	public String getResId() {
		return resId;
	}
	
	public void setResId(String resId) {
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
	
	public List<SecUrlEntity> getUrls() {
		return urls;
	}

	public void setUrls(List<SecUrlEntity> urls) {
		this.urls = urls;
	}

	public void toSecResourceDto(SecResourceDto secResourceDto) throws Exception {
		secResourceDto.setResId(this.resId);
		
		secResourceDto.setResName(this.resName);
		
		secResourceDto.setAppType(String.valueOf(this.appType));
		
		secResourceDto.setAppMenu(String.valueOf(this.appMenu));
		
		secResourceDto.setUrlIds(this.urlIds);
		
		secResourceDto.setStatus(String.valueOf(this.status));
		
		secResourceDto.setBaseRes(this.baseRes);
		
	}

}

