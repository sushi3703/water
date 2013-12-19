package net.water.user.dao;
import net.kuakao.core.exception.DataBaseException;
import net.water.user.entity.UserSnsEntity;

/**
 * 用户sns信息
 * @author SuShimeng
 *
 */
public interface IUserSnsDAO {
	
	/**
	 * 根据qqOpenId查询对应的用户qq账户信息
	 * @param userSnsEntity qqOpenId
	 * @return userId/qqOpenId/qqAccessToken/qqUsername等信息
	 */
	public UserSnsEntity queryUserSnsByQqOpenId(UserSnsEntity userSnsEntity);
	
	/**
	 * 根据userId查询其绑定的sns账号信息
	 * @param userId
	 * @return
	 */
	public UserSnsEntity queryUserSnsByUserId(int userId);
	
	/**
	 * 创建用户sns绑定账号信息
	 * @param userSnsEntity
	 * @throws DataBaseException
	 */
	public void createUserSns(UserSnsEntity userSnsEntity) throws DataBaseException;
	
	/**
	 * 更新用户qq账户信息
	 * @param userSnsEntity userId/qqOpenId/qqAccessToken/qqUsername/updateTime等信息
	 * @throws DataBaseException
	 */
	public void updateUserQqLoginInfo(UserSnsEntity userSnsEntity) throws DataBaseException;
}

