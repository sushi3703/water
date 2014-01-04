package net.water.security.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.kuakao.core.exception.DataBaseException;
import net.kuakao.core.xmlconfig.util.XmlConfigUtil;
import net.water.Constants;
import net.water.security.dao.ISecResourceDAO;
import net.water.security.dao.ISecUrlDAO;
import net.water.security.dto.SecResourceDto;
import net.water.security.dto.SecUrlDto;
import net.water.security.entity.SecResourceEntity;
import net.water.security.entity.SecUrlEntity;
import net.water.security.entity.SecUserResourceEntity;
import net.water.security.service.ISecResourceService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

public class SecResourceService implements ISecResourceService {
	
	@Autowired
	private ISecResourceDAO secResourceDAO;

	@Autowired
	private ISecUrlDAO secUrlDAO;

	public List<SecResourceEntity> querySecResources(SecResourceDto secResourceDto, Model model) throws Exception {
		List<SecResourceEntity> ress = secResourceDAO.querySecResources(secResourceDto);
		if(ress == null || ress.isEmpty()){
			return new ArrayList<SecResourceEntity>();
		}
		if(secResourceDto.isNeedUrlInfos()){
			SecUrlDto urlDto = new SecUrlDto();
			urlDto.setPerPage(-1);
			List<SecUrlEntity> urls;
			for(SecResourceEntity res : ress){
				urlDto.setUrlId(res.getUrlIds());
				urls = secUrlDAO.querySecUrls(urlDto);
				res.setUrls(urls);
			}
		}
		return ress;
	}

	public List<Map<String,Object>> queryAllUrls(String resId){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<Integer,Integer> resUrlMap = new HashMap<Integer, Integer>();
		if(StringUtils.isNotBlank(resId)){
			SecResourceEntity secResourceEntity = secResourceDAO.getSecResourceById(resId);
			if(secResourceEntity != null){
				String urlIds = secResourceEntity.getUrlIds();
				if(StringUtils.isNotBlank(urlIds)){
					for(String resUrl : urlIds.split(",")){
						resUrlMap.put(Integer.parseInt(resUrl), Integer.parseInt(resUrl));
					}
				}
			}
		}
		
		SecUrlDto secUrlDto = new SecUrlDto();
		secUrlDto.setPerPage(-1);
		List<SecUrlEntity> urls = secUrlDAO.querySecUrls(secUrlDto);
		if(urls == null || urls.isEmpty()){
			return list;
		}
		Map<Integer,List<Map<String,Object>>> menuUrlsMap = new HashMap<Integer, List<Map<String,Object>>>();
		List<Map<String,Object>> menuUrls;
		Map<String,Object> urlInfoMap;
		for(SecUrlEntity url : urls){
			menuUrls = menuUrlsMap.get(url.getAppMenu());
			if(menuUrls == null){
				menuUrls = new ArrayList<Map<String,Object>>();
			}
			urlInfoMap = new HashMap<String, Object>();
			urlInfoMap.put("urlId", url.getUrlId());
			urlInfoMap.put("urlName", url.getUrlName());
			urlInfoMap.put("urlMethodShow", url.getUrlMethod()==1?"get":"post");
			urlInfoMap.put("urlPath", url.getUrlPath());
			urlInfoMap.put("urlShow", url.getUrlShow());
			urlInfoMap.put("selected", resUrlMap.containsKey(url.getUrlId()));
			
			menuUrls.add(urlInfoMap);
			menuUrlsMap.put(url.getAppMenu(), menuUrls);
		}
		Map<String,Object> menuInfoMap;
		Map<String,String> menuNameMap = XmlConfigUtil.getMap("security_menu");
		for(int menuId : menuUrlsMap.keySet()){
			menuInfoMap = new HashMap<String, Object>();
			menuInfoMap.put("menuId", menuId);
			menuInfoMap.put("menuName", menuNameMap.get(menuId+""));
			menuInfoMap.put("urlInfoMaps", menuUrlsMap.get(menuId));
			list.add(menuInfoMap);
		}
		return list;
	}

	public SecResourceEntity getSecResourceById(SecResourceDto secResourceDto, Model model) throws Exception {
			SecResourceEntity secResourceEntity = null;
			String resId = secResourceDto.getResId();
			if(StringUtils.isNotBlank(resId)) {
				secResourceEntity = secResourceDAO.getSecResourceById(resId);
				if(secResourceEntity != null) {
					secResourceEntity.toSecResourceDto(secResourceDto);
				}
			}
			return secResourceEntity;
	}

	public void saveSecResource(SecResourceDto secResourceDto, Model model) throws Exception {
		String resId = secResourceDto.getResId();
		SecResourceEntity secResourceEntity = secResourceDto.toSecResourceEntity();
		if(StringUtils.isBlank(resId)){//create
			resId = UUID.randomUUID().toString();
			secResourceEntity.setResId(resId);
			secResourceDAO.createSecResource(secResourceEntity);
		}else{//update
			secResourceDAO.updateSecResource(secResourceEntity);
		}
	}

	public void destroySecResource(SecResourceDto secResourceDto, Model model, Map<String,String> msgs) throws Exception {
		String resId = secResourceDto.getResId();
		if(StringUtils.isNotBlank(resId)) {
			secResourceDAO.updateResourceStatus(resId,Constants.STATUS_DISABLE);
			msgs.put("success","删除成功!");
		}else{
			msgs.put("error","请选择要删除的Id");
		}
	}
	
	public Map<Integer, List<SecUrlEntity>> getUserSecMenu(String userId){
		if(StringUtils.isBlank(userId)){
			return null;
		}
		//用户所有res
		List<SecResourceEntity> ress = secResourceDAO.getResByUserId(userId);
		if(ress == null || ress.isEmpty()){
			return null;
		}
		StringBuffer allUrlIds = new StringBuffer("");
		String urlIds;
		for(SecResourceEntity res : ress){
			urlIds = res.getUrlIds();
			if(StringUtils.isBlank(urlIds)){
				continue;
			}
			allUrlIds.append(","+urlIds);
		}
		String allUrlIdStr = allUrlIds.toString();
		if(StringUtils.isBlank(allUrlIdStr)){
			return null;
		}
		allUrlIdStr = allUrlIdStr.substring(1);
		SecUrlDto urlDto = new SecUrlDto();
		urlDto.setUrlId(allUrlIdStr);
		urlDto.setUrlShow(SecUrlDto.URL_SHOW_TRUE+"");
		List<SecUrlEntity> urls = secUrlDAO.querySecUrls(urlDto);
		if(urls == null || urls.isEmpty()){
			return null;
		}
		//按菜单分组
		Map<Integer,List<SecUrlEntity>> menuUrlsMap = new HashMap<Integer, List<SecUrlEntity>>();
		List<SecUrlEntity> menuUrls;
		for(SecUrlEntity url : urls){
			menuUrls = menuUrlsMap.get(url.getAppMenu());
			if(menuUrls == null){
				menuUrls = new ArrayList<SecUrlEntity>();
			}
			menuUrls.add(url);
			menuUrlsMap.put(url.getAppMenu(), menuUrls);
		}
		
		return menuUrlsMap;
	}
	
	public List<Map<String,Object>> getUserResourceOfTeam(String adminId,String userId){
		//管理员所有可分配的资源
		List<SecResourceEntity> adminRess = secResourceDAO.getResByUserId(adminId);
		if(adminRess == null || adminRess.isEmpty()){
			return null;
		}
		//用户当前拥有的资源
		List<SecResourceEntity> userRess = secResourceDAO.getResByUserId(userId);
		Map<String,String> userResMap = new HashMap<String, String>();
		if(userRess != null && !userRess.isEmpty()){
			for(SecResourceEntity userRes : userRess){
				userResMap.put(userRes.getResId(), userRes.getResId());
			}
		}
		//
		Map<Integer,List<Map<String,String>>> menuIdResMap = new HashMap<Integer, List<Map<String,String>>>();
		List<Map<String,String>> resList;
		Map<String,String> resMap;
		for(SecResourceEntity res : adminRess){
			resMap = new HashMap<String, String>();
			resMap.put("resId", res.getResId());
			resMap.put("resName", res.getResName());
			resMap.put("chk", StringUtils.isNotBlank(userResMap.get(res.getResId()))?"true":"false");
			
			resList = menuIdResMap.get(res.getAppMenu());
			if(resList == null){
				resList = new ArrayList<Map<String,String>>();
			}
			resList.add(resMap);
			menuIdResMap.put(res.getAppMenu(), resList);
		}
		if(menuIdResMap.isEmpty()){
			return null;
		}
		//
		List<Map<String,Object>> menuResList = new ArrayList<Map<String,Object>>();
		//菜单中文名
		Map<String,String> menuNameMap = XmlConfigUtil.getMap("security_menu");
		Map<String,Object> menuResMap;
		for(int menuId : menuIdResMap.keySet()){
			menuResMap = new HashMap<String, Object>();
			menuResMap.put("menuName", menuNameMap.get(menuId+""));
			menuResMap.put("resList", menuIdResMap.get(menuId));
			menuResList.add(menuResMap);
		}
		return menuResList;
	}
	
	public void updateUserRes(String userId,String selRes) throws DataBaseException{
		//删除原用户资源数据
		secResourceDAO.destroyUserResByUserId(userId);
		if(StringUtils.isBlank(selRes)){
			return;
		}
		SecUserResourceEntity userRes;
		for(String resId : selRes.split(",")){
			userRes = new SecUserResourceEntity();
			userRes.setResId(resId);
			userRes.setUserId(userId);
			secResourceDAO.createUserRes(userRes);
		}
	}

}

