package net.water.user.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import net.kuakao.core.exception.DataBaseException;
import net.water.Constants;
import net.water.login.dao.IUserLoginDAO;
import net.water.login.entity.UserLoginEntity;
import net.water.tool.Md5;
import net.water.user.dao.IUserBaseDAO;
import net.water.user.dto.UserBaseDto;
import net.water.user.entity.UserBaseEntity;
import net.water.user.service.IUserService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

public class UserService implements IUserService {
	
	@Autowired
	private IUserBaseDAO userBaseDAO;
	
	@Autowired
	private IUserLoginDAO userLoginDAO;

	public List<UserBaseEntity> queryUserBaseByPage(UserBaseDto userBaseDto, Model model) throws DataBaseException {
		return userBaseDAO.queryUserBases(userBaseDto);
	}

	public UserBaseEntity getUserBaseById(UserBaseDto userBaseDto, Model model) throws DataBaseException {
			UserBaseEntity userBaseEntity = null;
			String userId = userBaseDto.getUserId();
			if(StringUtils.isNotBlank(userId)) {
				userBaseEntity = userBaseDAO.getUserBaseById(userId);
				if(userBaseEntity != null) {
					userBaseEntity.toUserBaseDto(userBaseDto);
				}
			}
			return userBaseEntity;
	}

	public void updateUserBase(UserBaseEntity userBaseEntity, Model model) throws DataBaseException {
		String userId = userBaseEntity.getUserId();
		if(StringUtils.isBlank(userId)) {
			throw new DataBaseException("请重新登录后再试");
		}
		//修改用户名
		UserLoginEntity loginInfo = userLoginDAO.queryUserLoginByUserId(userId);
		if(loginInfo == null){
			throw new DataBaseException("请重新登录后再试");
		}
		if(!loginInfo.getUname().equals(userBaseEntity.getUname())){
			userLoginDAO.updateUserName(userId, userBaseEntity.getUname());
		}
		//修改基本信息
		userBaseDAO.updateUserBase(userBaseEntity);
	}

	public void destroyUser(UserBaseDto userBaseDto, Model model) throws DataBaseException{
		String userId = userBaseDto.getUserId();
		if(StringUtils.isNotBlank(userId)) {
			userLoginDAO.updateUserStatus(userId, Constants.STATUS_DISABLE);
		}
	}

	public void createRegisterUser(UserLoginEntity userLoginEntity, Model model) throws DataBaseException{
		if(StringUtils.isBlank(userLoginEntity.getEmail()) || StringUtils.isBlank(userLoginEntity.getUpwd())){
			throw new DataBaseException("无法完成用户注册，因为邮箱或密码为空");
		}
		String userId = UUID.randomUUID().toString();
		//登录信息
		userLoginEntity.setUserId(userId);
		if(StringUtils.isBlank(userLoginEntity.getUname())){
			userLoginEntity.setUname(userLoginEntity.getEmail());
		}
		userLoginEntity.setUpwd(Md5.md5(userLoginEntity.getEmail()+userLoginEntity.getUpwd()));
		userLoginDAO.createUserLoginInfo(userLoginEntity);
		//基本信息
		UserBaseEntity userBaseEntity = new UserBaseEntity();
		userBaseEntity.setUserId(userId);
		userBaseEntity.setCreateTime(new Date());
		userBaseDAO.createUserBase(userBaseEntity);
		//
		userLoginEntity.setUpwd(null);
	}
	
	public boolean validateEmailExit(String email){
		return userLoginDAO.queryUserLoginByEmail(email)!=null;
	}

	@Override
	public void updateUserPwd(UserLoginEntity userLoginEntity)throws DataBaseException {
		userLoginEntity.setUpwd(Md5.md5(userLoginEntity.getEmail()+userLoginEntity.getUpwd()));
		userLoginDAO.updateUserPwd(userLoginEntity);
	}
	
}

