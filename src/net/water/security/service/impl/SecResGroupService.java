package net.water.security.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.kuakao.core.xmlconfig.util.XmlConfigUtil;
import net.water.security.dao.ISecResGroupDAO;
import net.water.security.dao.ISecResourceDAO;
import net.water.security.dto.SecResGroupDto;
import net.water.security.dto.SecResourceDto;
import net.water.security.entity.SecResGroupEntity;
import net.water.security.entity.SecResourceEntity;
import net.water.security.service.ISecResGroupService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

public class SecResGroupService implements ISecResGroupService {

	@Autowired
	private ISecResGroupDAO secResGroupDAO;

	@Autowired
	private ISecResourceDAO secResourceDAO;

	public List<SecResGroupEntity> querySecResGroupByPage(
			SecResGroupDto secResGroupDto, Model model) throws Exception {
		List<SecResGroupEntity> groups = secResGroupDAO.querySecResGroups(secResGroupDto);
		if(groups == null || groups.isEmpty()){
			return groups;
		}
		if(secResGroupDto.isNeedResInfos()){
			SecResourceDto resourceDto = new SecResourceDto();
			resourceDto.setPerPage(-1);
			List<SecResourceEntity> ress;
			for(SecResGroupEntity group : groups){
				if(StringUtils.isBlank(group.getResIds()))continue;
				resourceDto.setResId(group.getResIds());
				ress = secResourceDAO.querySecResources(resourceDto);
				group.setRess(ress);
			}
		}
		return groups;
	}

	public SecResGroupEntity getSecResGroupById(SecResGroupDto secResGroupDto,
			Model model) throws Exception {
		SecResGroupEntity secResGroupEntity = null;
		if (StringUtils.isNotBlank(secResGroupDto.getGroupId())) {
			secResGroupEntity = secResGroupDAO.getSecResGroupById(Integer
					.parseInt(secResGroupDto.getGroupId()));
			if (secResGroupEntity != null) {
				secResGroupEntity.toSecResGroupDto(secResGroupDto);
			}
		}
		return secResGroupEntity;
	}

	public List<SecResourceEntity> queryAllUserResources(int userId){
		List<SecResourceEntity> ress = new ArrayList<SecResourceEntity>();
		if(userId == 0)return ress;
		StringBuffer resIds = new StringBuffer(",");
		SecResourceDto resourceDto = new SecResourceDto();
		resourceDto.setPerPage(-1);
		List<SecResourceEntity> resourceEntities;
		if(1==1){//super admin has all
			resourceEntities = secResourceDAO.querySecResources(resourceDto);
			return resourceEntities;
		}
		//resGroup
		SecResGroupDto secResGroupDto = new SecResGroupDto();
		secResGroupDto.setPerPage(-1);
		secResGroupDto.setOwnerId(userId+"");
		List<SecResGroupEntity> groups = secResGroupDAO.querySecResGroups(secResGroupDto);
		if(groups != null && !groups.isEmpty()){
			for(SecResGroupEntity group : groups){
				if(StringUtils.isNotBlank(group.getResIds()))
					resIds.append(group.getResIds()+",");
			}
		}
		//userRes
		//...
		//
		String resIdStr = resIds.toString();
		if(",".equals(resIdStr)){
			return ress;
		}
		resIdStr = resIdStr.substring(1, resIdStr.length()-1);
		if(StringUtils.isBlank(resIdStr)){
			return ress;
		}
		SecResourceDto secResourceDto = new SecResourceDto();
		secResourceDto.setPerPage(-1);
		secResourceDto.setResId(resIdStr);
		
		return secResourceDAO.querySecResources(secResourceDto);
	}

	public List<Map<String, Object>> queryAllResourcesGroupByMenu(String groupIdStr, int userId) {
		List<Map<String, Object>> menuResources = new ArrayList<Map<String,Object>>();
		List<SecResourceEntity> ress = this.queryAllUserResources(userId);
		if(ress == null || ress.isEmpty())return menuResources;
		//groupInfo
		String groupResIds = "";
		if(StringUtils.isNotBlank(groupIdStr)){
			SecResGroupEntity groupEntity = secResGroupDAO.getSecResGroupById(Integer.parseInt(groupIdStr));
			if(groupEntity != null){
				groupResIds = groupEntity.getResIds();
			}
			if(StringUtils.isBlank(groupResIds)){
				groupResIds = "";
			}
		}
		groupResIds = ","+groupResIds+",";
		//menuMap
		Map<Integer,List<Map<String,Object>>> menuResourceInfoMap = new HashMap<Integer, List<Map<String,Object>>>();
		List<Map<String,Object>> resourceInfos;
		Map<String,Object> resourceInfoMap;
		for(SecResourceEntity res : ress){
			resourceInfos = menuResourceInfoMap.get(res.getAppMenu());
			if(resourceInfos == null){
				resourceInfos = new ArrayList<Map<String,Object>>();
			}
			resourceInfoMap = new HashMap<String, Object>();
			resourceInfoMap.put("resName", res.getResName());
			resourceInfoMap.put("resId", res.getResId());
			resourceInfoMap.put("selected", groupResIds.indexOf(","+res.getResId()+",")>-1);
			
			resourceInfos.add(resourceInfoMap);
			menuResourceInfoMap.put(res.getAppMenu(), resourceInfos);
		}
		//list
		Map<String,String> menuNameMap = XmlConfigUtil.getMap("security_menu");
		if(!menuResourceInfoMap.isEmpty()){
			Map<String, Object> menuInfoMap;
			for(int menuId : menuResourceInfoMap.keySet()){
				menuInfoMap = new HashMap<String, Object>();
				menuInfoMap.put("menuId", menuId);
				menuInfoMap.put("menuName", menuNameMap.get(menuId+""));
				menuInfoMap.put("resourceInfoMaps", menuResourceInfoMap.get(menuId));
				
				menuResources.add(menuInfoMap);
			}
		}
		
		return menuResources;
	}

	public void saveSecResGroup(SecResGroupDto secResGroupDto, Model model)
			throws Exception {
		SecResGroupEntity secResGroupEntity = secResGroupDto
				.toSecResGroupEntity();
		if (StringUtils.isNotBlank(secResGroupDto.getGroupId())) {
			secResGroupDAO.updateSecResGroup(secResGroupEntity);
		} else {
			secResGroupDAO.createSecResGroup(secResGroupEntity);
		}
	}

	public void destroySecResGroup(SecResGroupDto secResGroupDto, Model model,
			Map<String, String> msgs) throws Exception {
		String groupId = secResGroupDto.getGroupId();
		if (StringUtils.isNotBlank(groupId)) {
			secResGroupDAO.destroySecResGroup(Integer.parseInt(groupId));
			msgs.put("success", "删除成功!");
		} else {
			msgs.put("error", "请选择要删除的Id");
		}
	}

}
