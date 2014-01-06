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
		return 300;
	}
	
	public List<SecUrlEntity> getUserUrls(String userId) {
		Object o = this.get(userId);
		if(o != null){
			return (List<SecUrlEntity>)o;
		}
		List<SecUrlEntity> urls = resourceService.getUserAllUrl(userId);
		this.set(userId, urls);
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
