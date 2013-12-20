package net.water.login.service.impl;

import java.util.Date;

import net.kuakao.core.exception.DataBaseException;
import net.water.Constants;
import net.water.login.dao.IUserLoginDAO;
import net.water.login.entity.UserLoginEntity;
import net.water.login.service.IUserLoginService;
import net.water.tool.Md5;
import net.water.user.dao.IUserSnsDAO;
import net.water.user.entity.UserSnsEntity;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

public class UserLoginService implements IUserLoginService {
	
	@Autowired
	private IUserLoginDAO userLoginDAO;
	
	@Autowired
	private IUserSnsDAO userSnsDAO;

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
		UserLoginEntity user = userLoginDAO.queryUserLoginByEmail(userLoginEntity.getEmail());
		if(user == null){
			model.addAttribute(Constants.PARAM_ERROR_MSG, "无此账户");
			return null;
		}
		
		if(!user.getUpwd().equals(Md5.md5(userLoginEntity.getEmail()+userLoginEntity.getUpwd()))){
			model.addAttribute(Constants.PARAM_ERROR_MSG, "密码输入有误");
			return null;
		}
		if(user.getStatus()!=1){
			model.addAttribute(Constants.PARAM_ERROR_MSG, "账户被锁定或已失效");
			return null;
		}
		user.setUpwd(null);
		
		return user;
	}
	
	public UserLoginEntity operateQqLoginExist(UserSnsEntity userSnsEntity){
		//qqOpenId是否已绑定本站账户
		UserSnsEntity dbSnsInfo = userSnsDAO.queryUserSnsByQqOpenId(userSnsEntity);
		if(dbSnsInfo == null){
			return null;
		}
		//已绑定，更新accessToken/qqUsername
		if(!dbSnsInfo.getQqAccessToken().equals(userSnsEntity.getQqAccessToken()) || !dbSnsInfo.getQqUsername().equals(userSnsEntity.getQqUsername())){
			dbSnsInfo.setQqAccessToken(userSnsEntity.getQqAccessToken());
			dbSnsInfo.setQqUsername(userSnsEntity.getQqUsername());
			dbSnsInfo.setUpdateTime(new Date());
			userSnsDAO.updateUserQqLoginInfo(dbSnsInfo);
		}
		//查询对应用户的登录信息
		return userLoginDAO.queryUserLoginByUserId(dbSnsInfo.getUserId());
	}
	
	public void operateBindQqLogin(UserSnsEntity userSnsEntity)throws DataBaseException{
		String userId = userSnsEntity.getUserId();
		if(StringUtils.isBlank(userId)){
			return;
		}
		UserSnsEntity snsEntity = userSnsDAO.queryUserSnsByUserId(userId);
		if(snsEntity == null){
			userSnsEntity.setUpdateTime(new Date());
			userSnsDAO.createUserSns(userSnsEntity);
		}else{
			userSnsEntity.setUpdateTime(new Date());
			userSnsDAO.updateUserQqLoginInfo(userSnsEntity);
		}
	}

	@Override
	public void updateUserPwd(UserLoginEntity userLoginEntity) {
		userLoginDAO.updateUserPwd(userLoginEntity);
	}
	
}

