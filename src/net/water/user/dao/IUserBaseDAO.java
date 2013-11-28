package net.water.user.dao;
import java.util.List;

import net.kuakao.core.exception.DataBaseException;
import net.water.user.dto.UserBaseDto;
import net.water.user.entity.UserBaseEntity;

/**
 * 用户基本信息
 * @作者 autopub
 * @创建日期 2013-11-27
 * @版本 V 1.0
 *
 */
public interface IUserBaseDAO {
	
	/**
	 * 用户基本信息(分页)查询
	 * @param userBaseDto 用户基本信息Dto
	 * @return 用户基本信息列表
	 * @throws DataBaseException
	 */
	public List<UserBaseEntity> queryUserBases(UserBaseDto userBaseDto) throws DataBaseException;
	
	/**
	 * 查询单个用户基本信息对象
	 * @param userId 用户ID
	 * @return 用户基本信息
	 * @throws DataBaseException
	 */
	public UserBaseEntity getUserBaseById(int userId) throws DataBaseException;
	
	/**
	 * 创建用户基本信息
	 * @param userBaseEntity 用户基本信息实体类
	 * @throws DataBaseException
	 */
	public void createUserBase(UserBaseEntity userBaseEntity) throws DataBaseException;
	
	/**
	 * 更新用户基本信息
	 * @param userBaseEntity 用户基本信息实体类
	 * @throws DataBaseException
	 */
	public void updateUserBase(UserBaseEntity userBaseEntity) throws DataBaseException;
	
}

