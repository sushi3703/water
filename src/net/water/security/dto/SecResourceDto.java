package net.water.security.dto;


import net.kuakao.core.base.util.SystemUtils;
import net.kuakao.core.dto.PagerDTO;
import net.water.security.entity.SecResourceEntity;
public class SecResourceDto extends PagerDTO {
	
	/* ID */
	private String  resId;
			
			
	/* 资源名称 */
	private String  resName;
			
			
	/* 所属项目 */
	private String  appType;
			
			
	/* 所属菜单 */
	private String  appMenu;
			
			
	/* 资源包含的url */
	private String  urlIds;
			
			
	/* 状态，1有效0删除 */
	private String  status;

	/*依赖的基础资源*/
	private String baseRes;
			
	/*是否需要查询url信息*/
	private boolean needUrlInfos=false;

	public String getBaseRes() {
		return baseRes;
	}

	public void setBaseRes(String baseRes) {
		this.baseRes = baseRes;
	}

	public String  getResId() {
		return resId;
	}
	
	public void setResId(String resId) {
		this.resId = resId;
	}
			
	public String  getResName() {
		return resName;
	}
	
	public void setResName(String resName) {
		this.resName = resName;
	}
			
	public String  getAppType() {
		return appType;
	}
	
	public void setAppType(String appType) {
		this.appType = appType;
	}
			
	public String  getAppMenu() {
		return appMenu;
	}
	
	public void setAppMenu(String appMenu) {
		this.appMenu = appMenu;
	}
			
	public String  getUrlIds() {
		return urlIds;
	}
	
	public void setUrlIds(String urlIds) {
		this.urlIds = urlIds;
	}
			
	public String  getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
			

	public boolean isNeedUrlInfos() {
		return needUrlInfos;
	}

	public void setNeedUrlInfos(boolean needUrlInfos) {
		this.needUrlInfos = needUrlInfos;
	}

	public SecResourceEntity toSecResourceEntity() {
		SecResourceEntity secResourceEntity = new SecResourceEntity();
		secResourceEntity.setResId(this.resId);
		
		secResourceEntity.setResName(this.getResName());
		
		secResourceEntity.setAppType(SystemUtils.strToInt(this.appType));
		
		secResourceEntity.setAppMenu(SystemUtils.strToInt(this.appMenu));
		
		secResourceEntity.setUrlIds(this.getUrlIds());
		
		secResourceEntity.setStatus(SystemUtils.strToInt(this.status));
		
		secResourceEntity.setBaseRes(this.baseRes);
		
		return secResourceEntity;
	}
}

