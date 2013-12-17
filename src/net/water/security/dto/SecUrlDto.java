package net.water.security.dto;


import net.kuakao.core.base.util.SystemUtils;
import net.kuakao.core.dto.PagerDTO;
import net.water.security.entity.SecUrlEntity;
import net.water.tool.math.SuIntUtils;
public class SecUrlDto extends PagerDTO {
	/** url显示 */
	public final static int URL_SHOW_TRUE = 1;
	/** url不显示 */
	public final static int URL_SHOW_FALSE = 2;
	/** GET请求*/
	public final static int URL_METHOD_GET = 1;
	/** POST请求*/
	public final static int URL_METHOD_POST = 2;
	
	/* ID */
	private String  urlId;
			
			
	/* URL名称 */
	private String  urlName;
			
			
	/* 提交方式 */
	private String  urlMethod;
			
			
	/* 地址 */
	private String  urlPath;
			
			
	/* 项目 */
	private String  appType;

	
	/* 所属菜单 */
	private String  appMenu;
			
			
	/* 是否显示 */
	private String  urlShow;
			
	/*显示顺序*/
	private String urlOrder;

	public String getUrlId() {
		return urlId;
	}

	public void setUrlId(String urlId) {
		this.urlId = urlId;
	}

	public String getUrlName() {
		return urlName;
	}

	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}

	public String getUrlMethod() {
		return urlMethod;
	}

	public void setUrlMethod(String urlMethod) {
		this.urlMethod = urlMethod;
	}

	public String getUrlPath() {
		return urlPath;
	}

	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getAppMenu() {
		return appMenu;
	}

	public void setAppMenu(String appMenu) {
		this.appMenu = appMenu;
	}

	public String getUrlShow() {
		return urlShow;
	}

	public void setUrlShow(String urlShow) {
		this.urlShow = urlShow;
	}

	public String getUrlOrder() {
		return urlOrder;
	}

	public void setUrlOrder(String urlOrder) {
		this.urlOrder = urlOrder;
	}

	public SecUrlEntity toSecUrlEntity() {
		SecUrlEntity secUrlEntity = new SecUrlEntity();
		secUrlEntity.setUrlId(SystemUtils.strToInt(this.urlId));
		
		secUrlEntity.setUrlName(this.getUrlName());
		
		secUrlEntity.setUrlMethod(SuIntUtils.getInt(this.getUrlMethod()));
		
		secUrlEntity.setUrlPath(this.getUrlPath());
		
		secUrlEntity.setAppType(SuIntUtils.getInt(this.getAppType()));
		
		secUrlEntity.setAppMenu(SuIntUtils.getInt(this.getAppMenu()));
		
		secUrlEntity.setUrlShow(SuIntUtils.getInt(this.getUrlShow()));
		
		secUrlEntity.setUrlOrder(SuIntUtils.getInt(this.getUrlOrder()));
		
		return secUrlEntity;
	}
}

