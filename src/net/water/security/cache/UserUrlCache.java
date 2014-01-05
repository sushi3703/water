package net.water.security.cache;

import java.util.List;

import net.kuakao.core.base.cache.BaseCache;
import net.water.security.entity.SecUrlEntity;
import net.water.security.service.ISecResourceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("userUrlCache")
public class UserUrlCache extends BaseCache {
	
	@Autowired
	private ISecResourceService resourceService;
	
	@Override
	public String mainKey() {
		return "userUrlCache";
	}

	@Override
	public int timeToLiveSeconds() {
		return 30;
	}
	
	public List<SecUrlEntity> getUserUrls(String userId) {
		Object o = this.get(userId);
		if(o != null){
			return (List<SecUrlEntity>) o;
		}
		List<SecUrlEntity> urls = resourceService.getUserAllUrl(userId);
		/*if(urls ){
			
		}
		if(saUrls == null) {
			List<SaUrlEntity> querySaUrls = this.getUserUrlResourcesSpring(userId, appId);
			if(querySaUrls != null) {
				List<SaUrlEntity> getSaUrls = new ArrayList<SaUrlEntity>();
				List<SaUrlEntity> postSaUrls = new ArrayList<SaUrlEntity>();
				for(SaUrlEntity saUrl : querySaUrls) {
					if("1".equals(saUrl.getUrlMethod())) {
						getSaUrls.add(saUrl);
					} else if("2".equals(saUrl.getUrlMethod())) {
						postSaUrls.add(saUrl);
					}
				}
				this.set(userId + HTTP_GET_TYPE + appId, getSaUrls); //get方式的加载到缓存
				this.set(userId + HTTP_POST_TYPE + appId, postSaUrls); //post方式的加载到缓存
				if("GET".equals(methodType)) {
					saUrls = getSaUrls;
				} else if("POST".equals(methodType)) {
					saUrls = postSaUrls;
				}
			}
		}*/
		return urls;
	}
	/**
	 * 清除缓存
	 * @param userId
	 */
	public void removeCache(String userId){
		super.delete(userId);
	}
	
}
