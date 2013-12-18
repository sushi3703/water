package net.water.login.service;
import org.springframework.ui.Model;

import net.water.login.entity.UserLoginEntity;


public interface IUserLoginService {
	/**
	 * 用户登录并取得基本信息
	 * @param userLoginEntity email和upwd信息
	 * @return 用户登录基本信息
	 */
	public UserLoginEntity queryUserLogin(UserLoginEntity userLoginEntity, Model model);

	/**
	 * 修改用户登录密码
	 * @param userLoginEntity
	 */
	public void updateUserPwd(UserLoginEntity userLoginEntity);
	
	/**
	 * qq登录是否已绑定系统账号(并更新accessToken，设置userId/uname/email/type等信息)
	 * @param userLoginEntity
	 * @return true是 false否
	 */
	public boolean operateQqLoginExist(UserLoginEntity userLoginEntity);
	
	/**
	 * 绑定QQ登录(并返回登录基本信息)
	 * @param userLoginEntity
	 * @return
	 */
	public UserLoginEntity operateBindQqLogin(UserLoginEntity userLoginEntity);
}
