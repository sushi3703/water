package net.water.login.dao;
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
	 * 根据用户email查询登录信息
	 * @param email
	 * @return
	 */
	public UserLoginEntity queryUserLoginByEmail(String email);
	
	/**
	 * 根据userId取用户登录信息
	 * @param userId
	 * @return
	 */
	public UserLoginEntity queryUserLoginByUserId(String userId);
	
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
	 * 修改用户状态
	 * @param userId 用户ID
	 * @param status 状态
	 * @throws DataBaseException
	 */
	public void updateUserStatus(String userId,int status) throws DataBaseException;
	
	/**
	 * 修改用户名
	 * @param userId
	 * @param uname
	 * @throws DataBaseException
	 */
	public void updateUserName(String userId,String uname) throws DataBaseException;
	
	/**
	 * 修改用户所在团队
	 * @param userId
	 * @param teamId
	 * @throws DataBaseException
	 */
	public void updateUserTeam(String userId,String teamId) throws DataBaseException;
	
}

