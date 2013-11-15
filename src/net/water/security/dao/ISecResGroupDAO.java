package net.water.security.dao;
import java.util.List;

import net.kuakao.core.exception.DataBaseException;
import net.water.security.dto.SecResGroupDto;
import net.water.security.entity.SecResGroupEntity;

/**
 * 资源组
 * @作者 autopub
 * @创建日期 2013-11-15
 * @版本 V 1.0
 *
 */
public interface ISecResGroupDAO {
	
	/**
	 * 资源组(分页)查询
	 * @param secResGroupDto 资源组Dto
	 * @return 资源组列表
	 * @throws DataBaseException
	 */
	public List<SecResGroupEntity> querySecResGroups(SecResGroupDto secResGroupDto) throws DataBaseException;
	
	/**
	 * 查询单个资源组对象
	 * @param groupId 资源组ID
	 * @return 资源组
	 * @throws DataBaseException
	 */
	public SecResGroupEntity getSecResGroupById(int groupId) throws DataBaseException;
	
	/**
	 * 创建资源组
	 * @param secResGroupEntity 资源组实体类
	 * @throws DataBaseException
	 */
	public void createSecResGroup(SecResGroupEntity secResGroupEntity) throws DataBaseException;
	
	/**
	 * 更新资源组
	 * @param secResGroupEntity 资源组实体类
	 * @throws DataBaseException
	 */
	public void updateSecResGroup(SecResGroupEntity secResGroupEntity) throws DataBaseException;
	
	/**
	 * 删除资源组
	 * @param groupId 资源组ID
	 * @throws DataBaseException
	 */
	public void destroySecResGroup(int groupId) throws DataBaseException;

}

