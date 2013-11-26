package net.water.security.dao;
import net.kuakao.core.exception.DataBaseException;
import net.water.security.entity.SecUserGroupEntity;

/**
 * 用户组
 * @作者 autopub
 * @创建日期 2013-11-26
 * @版本 V 1.0
 *
 */
public interface ISecUserGroupDAO {
	
	/**
	 * 创建用户组
	 * @param SecUserGroupEntity 用户组实体类
	 * @throws DataBaseException
	 */
	public void createSecUserGroup(SecUserGroupEntity SecUserGroupEntity) throws DataBaseException;
	
	
	/**
	 * 删除用户组
	 * @param userId 用户ID
	 * @throws DataBaseException
	 */
	public void destroySecUserGroupByUserId(int userId) throws DataBaseException;

}

