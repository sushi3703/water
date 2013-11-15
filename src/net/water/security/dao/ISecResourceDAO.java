package net.water.security.dao;
import java.util.List;

import net.kuakao.core.exception.DataBaseException;
import net.water.security.dto.SecResourceDto;
import net.water.security.entity.SecResourceEntity;

/**
 * 权限资源
 * @作者 autopub
 * @创建日期 2013-11-13
 * @版本 V 1.0
 *
 */
public interface ISecResourceDAO {
	
	/**
	 * 权限资源(分页)查询
	 * @param secResourceDto 权限资源Dto
	 * @return 权限资源列表
	 * @throws DataBaseException
	 */
	public List<SecResourceEntity> querySecResources(SecResourceDto secResourceDto) throws DataBaseException;
	
	/**
	 * 查询单个权限资源对象
	 * @param resId 权限资源id
	 * @return 权限资源
	 * @throws DataBaseException
	 */
	public SecResourceEntity getSecResourceById(int resId) throws DataBaseException;
	
	/**
	 * 创建权限资源
	 * @param secResourceEntity 权限资源实体类
	 * @throws DataBaseException
	 */
	public void createSecResource(SecResourceEntity secResourceEntity) throws DataBaseException;
	
	/**
	 * 更新权限资源
	 * @param secResourceEntity 权限资源实体类
	 * @throws DataBaseException
	 */
	public void updateSecResource(SecResourceEntity secResourceEntity) throws DataBaseException;
	
	/**
	 * 删除权限资源
	 * @param resId 权限资源ID
	 * @throws DataBaseException
	 */
	public void destroySecResource(int resId) throws DataBaseException;

}

