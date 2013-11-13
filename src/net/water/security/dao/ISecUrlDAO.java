package net.water.security.dao;
import java.util.List;

import net.kuakao.core.exception.DataBaseException;
import net.water.security.dto.SecUrlDto;
import net.water.security.entity.SecUrlEntity;

/**
 * 资源
 * @作者 
 * @创建日期 2013-10-21
 * @版本 V 1.0
 *
 */
public interface ISecUrlDAO {
	
	/**
	 * 资源分页查询
	 * @param secUrlDto 资源Dto
	 * @return 资源列表
	 * @throws DataBaseException
	 */
	public List<SecUrlEntity> querySecUrlByPage(SecUrlDto secUrlDto) throws DataBaseException;
	
	/**
	 * 资源查询
	 * @param secUrlDto 资源Dto
	 * @return 资源列表
	 * @throws DataBaseException
	 */
	public List<SecUrlEntity> querySecUrls(SecUrlDto secUrlDto) throws DataBaseException;
	
	/**
	 * 查询单个资源对象
	 * @param urlId
	 * @return 资源
	 * @throws DataBaseException
	 */
	public SecUrlEntity getSecUrlById(int urlId) throws DataBaseException;
	
	/**
	 * 创建资源
	 * @param secUrlEntity 资源实体类
	 * @throws DataBaseException
	 */
	public void createSecUrl(SecUrlEntity secUrlEntity) throws DataBaseException;
	
	/**
	 * 更新资源
	 * @param secUrlEntity 资源实体类
	 * @throws DataBaseException
	 */
	public void updateSecUrl(SecUrlDto secUrlDto) throws DataBaseException;
	
	/**
	 * 删除资源
	 * @param urlId 资源ID
	 * @throws DataBaseException
	 */
	public void destroySecUrl(int urlId) throws DataBaseException;

}

