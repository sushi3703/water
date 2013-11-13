package net.water.security.entity;
import java.io.Serializable;

import net.water.security.dto.SecUrlDto;

public class SecUrlEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/* ID */
	private int  urlId;
			
	/* URL名称 */
	private String  urlName;
			
	/* 提交方式 */
	private int  urlMethod;
			
	/* 地址 */
	private String  urlPath;
			
	/* 所属项目 */
	private int  appType;
	
	/* 所属菜单 */
	private int  appMenu;
			
	/* 是否显示 */
	private int urlShow;
	
	/*显示顺序*/
	private int urlOrder;
		
	
	public int getUrlId() {
		return urlId;
	}


	public void setUrlId(int urlId) {
		this.urlId = urlId;
	}


	public String getUrlName() {
		return urlName;
	}


	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}


	public int getUrlMethod() {
		return urlMethod;
	}


	public void setUrlMethod(int urlMethod) {
		this.urlMethod = urlMethod;
	}


	public String getUrlPath() {
		return urlPath;
	}


	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
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


	public int getUrlShow() {
		return urlShow;
	}


	public void setUrlShow(int urlShow) {
		this.urlShow = urlShow;
	}


	public int getUrlOrder() {
		return urlOrder;
	}


	public void setUrlOrder(int urlOrder) {
		this.urlOrder = urlOrder;
	}


	public void toSecUrlDto(SecUrlDto secUrlDto) throws Exception {
		secUrlDto.setUrlId(String.valueOf(this.urlId));
		
		secUrlDto.setUrlName(this.urlName);
		
		secUrlDto.setUrlMethod(String.valueOf(this.urlMethod));
		
		secUrlDto.setUrlPath(this.urlPath);
		
		secUrlDto.setAppType(String.valueOf(this.appType));
		
		secUrlDto.setAppMenu(String.valueOf(this.appMenu));
		
		secUrlDto.setUrlShow(String.valueOf(this.urlShow));
		
		secUrlDto.setUrlOrder(String.valueOf(this.urlOrder));
		
	}

}

