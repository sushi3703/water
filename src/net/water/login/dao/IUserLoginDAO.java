package net.water.login.dao;
import java.util.List;
import java.util.Map;

import net.kuakao.core.exception.DataBaseException;
import net.water.login.entity.UserLoginEntity;

/**
 * SuShimeng
 * @创建日期 2013-10-12
 * @版本 V 1.0
 *
 */
public interface IUserLoginDAO {
	/**
	 * 用户(uname)登录并查询其基本信息
	 * @param userLoginEntity
	 * @return
	 */
	public UserLoginEntity queryUserLoginByUname(UserLoginEntity userLoginEntity);
	
	/**
	 * 用户(email)登录并查询其基本信息
	 * @param userLoginEntity
	 * @return
	 */
	public UserLoginEntity queryUserLoginByEmail(UserLoginEntity userLoginEntity);
	
	/**
	 * 创建用户(登录信息)
	 * @param userLoginEntity
	 * @throws DataBaseException
	 */
	public void createUserLoginInfo(UserLoginEntity userLoginEntity) throws DataBaseException;

	/**
	 * 修改用户登录密码
	 * @param userLoginEntity
	 * @throws DataBaseException
	 */
	public void updateUserPwd(UserLoginEntity userLoginEntity) throws DataBaseException;
	
	/**
	 * 根据用户名查看是否存在此用户
	 * @param uname
	 * @return
	 */
	public List<Map<String,Object>> queryUsersByName(String uname);
	
	/**
	 * 根据邮箱查看是否存在此用户
	 * @param email
	 * @return
	 */
	public List<Map<String,Object>> queryUsersByEmail(String email);
}

