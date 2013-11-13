package net.water.login.service;
import org.springframework.ui.Model;

import net.water.login.entity.UserLoginEntity;


public interface IUserLoginService {
	/**
	 * 用户登录并取得基本信息
	 * @param userLoginEntity
	 * @return
	 */
	public UserLoginEntity queryUserLogin(UserLoginEntity userLoginEntity, Model model);

	/**
	 * 创建用户基本信息
	 * @param userLoginEntity
	 */
	public void createUserLogin(UserLoginEntity userLoginEntity);
	
	/**
	 * 修改用户登录密码
	 * @param userLoginEntity
	 */
	public void updateUserPwd(UserLoginEntity userLoginEntity);
}
