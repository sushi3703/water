package net.water.login.service;
import net.kuakao.core.exception.DataBaseException;
import net.water.login.entity.UserLoginEntity;
import net.water.user.entity.UserSnsEntity;

import org.springframework.ui.Model;


public interface IUserLoginService {
	/**
	 * 根据用户ID取得其登录基本信息
	 * @param userId
	 * @return
	 */
	public UserLoginEntity getUserLoginByUserId(String userId);
	/**
	 * 用户登录并取得用户登录信息
	 * @param userLoginEntity email和upwd信息
	 * @return 用户登录基本信息
	 */
	public UserLoginEntity queryUserLogin(UserLoginEntity userLoginEntity, Model model);

	/**
	 * qq登录是否已绑定系统账号
	 * <br/>如已绑定，检查更新accessToken/qqUsername
	 * <br/>返回对应用户的登录信息
	 * @param userSnsEntity qqOpenId
	 * @return null或对应用户的登录信息
	 */
	public UserLoginEntity operateQqLoginExist(UserSnsEntity userSnsEntity);
	
	/**
	 * 绑定QQ账号登录
	 * @param userSnsEntity
	 * @throws DataBaseException
	 */
	public void operateBindQqLogin(UserSnsEntity userSnsEntity)throws DataBaseException;
	
}
