package net.water.login.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.water.Constants;
import net.water.login.dao.IUserLoginDAO;
import net.water.login.entity.UserLoginEntity;
import net.water.login.service.IUserLoginService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import su.tool.Md5;

public class UserLoginService implements IUserLoginService {
	
	@Autowired
	private IUserLoginDAO userLoginDAO;

	@Override
	public UserLoginEntity queryUserLogin(UserLoginEntity userLoginEntity, Model model) {
		if(StringUtils.isBlank(userLoginEntity.getUname()) || StringUtils.isBlank(userLoginEntity.getUpwd())){
			return null;
		}
		List<Map<String,Object>> users = userLoginDAO.queryUsersByName(userLoginEntity.getUname());
		if(users == null || users.isEmpty()){
			model.addAttribute(Constants.PARAM_ERROR_MSG, "无此账户");
			return null;
		}
		//密码加密
		//userLoginEntity.setUpwd(Md5.md5(userLoginEntity.getUpwd()));
		UserLoginEntity user = userLoginDAO.queryUserLogin(userLoginEntity);
		if(user == null){
			model.addAttribute(Constants.PARAM_ERROR_MSG, "密码输入有误");
			return null;
		}
		return user;
	}

	@Override
	public void createUserLogin(UserLoginEntity userLoginEntity) {
		//密码加密
		userLoginEntity.setUpwd(Md5.md5(userLoginEntity.getUpwd()));
		userLoginEntity.setCreateTime(new Date());
		userLoginEntity.setStatus(Constants.STATUS_ENABLE);
		userLoginDAO.createUserLoginInfo(userLoginEntity);
	}

	@Override
	public void updateUserPwd(UserLoginEntity userLoginEntity) {
		userLoginDAO.updateUserPwd(userLoginEntity);
	}
	

}

