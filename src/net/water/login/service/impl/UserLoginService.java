package net.water.login.service.impl;

import java.util.List;
import java.util.Map;

import net.water.Constants;
import net.water.login.dao.IUserLoginDAO;
import net.water.login.entity.UserLoginEntity;
import net.water.login.service.IUserLoginService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

public class UserLoginService implements IUserLoginService {
	
	@Autowired
	private IUserLoginDAO userLoginDAO;

	@Override
	public UserLoginEntity queryUserLogin(UserLoginEntity userLoginEntity, Model model) {
		if(StringUtils.isBlank(userLoginEntity.getUpwd())){
			model.addAttribute(Constants.PARAM_ERROR_MSG, "请输入密码");
			return null;
		}
		if(StringUtils.isBlank(userLoginEntity.getEmail())){
			model.addAttribute(Constants.PARAM_ERROR_MSG, "请输入邮箱");
			return null;
		}
		//密码加密
		//userLoginEntity.setUpwd(Md5.md5(userLoginEntity.getUpwd()));
		List<Map<String,Object>> users = userLoginDAO.queryUsersByEmail(userLoginEntity.getEmail());
		if(users == null || users.isEmpty()){
			model.addAttribute(Constants.PARAM_ERROR_MSG, "无此账户");
			return null;
		}
		UserLoginEntity user = userLoginDAO.queryUserLoginByEmail(userLoginEntity);
		
		if(user == null){
			model.addAttribute(Constants.PARAM_ERROR_MSG, "密码输入有误");
			return null;
		}
		if(user.getStatus()!=1){
			model.addAttribute(Constants.PARAM_ERROR_MSG, "账户被锁定或已失效");
			return null;
		}
		
		return user;
	}
	
	public boolean operateQqLoginExist(UserLoginEntity userLoginEntity){
		UserLoginEntity dbLoginInfo = userLoginDAO.queryUserLoginByQqOpenId(userLoginEntity);
		if(dbLoginInfo == null){
			return false;
		}
		//更新accessToken
		if(!dbLoginInfo.getQqAccessToken().equals(userLoginEntity.getQqAccessToken())){
			userLoginDAO.updateUserQqLoginInfo(userLoginEntity);
		}
		//设置用户信息
		userLoginEntity.setEmail(dbLoginInfo.getEmail());
		userLoginEntity.setType(dbLoginInfo.getType());
		userLoginEntity.setUname(dbLoginInfo.getUname());
		userLoginEntity.setUserId(dbLoginInfo.getUserId());
		return true;
	}
	
	public UserLoginEntity operateBindQqLogin(UserLoginEntity userLoginEntity){
		if(userLoginEntity.getUserId()==0){
			return null;
		}
		userLoginDAO.updateUserQqLoginInfo(userLoginEntity);
		return userLoginDAO.queryUserLoginByUserId(userLoginEntity);
	}

	@Override
	public void updateUserPwd(UserLoginEntity userLoginEntity) {
		userLoginDAO.updateUserPwd(userLoginEntity);
	}
	

}

