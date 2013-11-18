package net.water.security.service;
import java.util.List;
import java.util.Map;

import net.water.security.dto.SecResGroupDto;
import net.water.security.entity.SecResGroupEntity;
import net.water.security.entity.SecResourceEntity;

import org.springframework.ui.Model;


public interface ISecResGroupService {
	
	/**
	 * 资源组分页查询
	 * @param secResGroupDto 资源组Dto
	 * @return 资源组列表
	 * @throws Exception
	 */
	public List<SecResGroupEntity> querySecResGroupByPage(SecResGroupDto secResGroupDto, Model model) throws Exception;
	
	/**
	 * 用户可以访问的所有资源
	 * @param userId
	 * @return
	 */
	public List<SecResourceEntity> queryAllUserResources(int userId);
	
	/**
	 * 编辑资源组，(用户可访问的)资源列表
	 * @param groupIdStr
	 * @param userId
	 * @return
	 */
	public List<Map<String, Object>> queryAllResourcesGroupByMenu(String groupIdStr, int userId);
	
	/**
	 * 查询单个资源组对象
	 * @param secResGroupDto 资源组Dto
	 * @return 资源组
	 * @throws Exception
	 */
	public SecResGroupEntity getSecResGroupById(SecResGroupDto secResGroupDto, Model model) throws Exception;
	
	
	/**
	 * 保存或更新资源组
	 * @param secResGroupDto
	 * @throws Exception
	 */
	public void saveSecResGroup(SecResGroupDto secResGroupDto, Model model) throws Exception;
	
	/**
	 * 删除资源组
	 * @param secResGroupDto
	 * @param model
	 * @param msgs
	 * @throws Exception
	 */
	public void destroySecResGroup(SecResGroupDto secResGroupDto, Model model,Map<String,String> msgs) throws Exception;

}
