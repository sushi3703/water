package net.water.user.service.impl;

import java.util.List;

import net.kuakao.core.exception.DataBaseException;
import net.water.Constants;
import net.water.login.dao.IUserLoginDAO;
import net.water.user.dao.IUserBaseDAO;
import net.water.user.dto.UserBaseDto;
import net.water.user.entity.UserBaseEntity;
import net.water.user.service.IUserBaseService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

public class UserBaseService implements IUserBaseService {
	
	@Autowired
	private IUserBaseDAO userBaseDAO;
	
	@Autowired
	private IUserLoginDAO userLoginDAO;

	public List<UserBaseEntity> queryUserBaseByPage(UserBaseDto userBaseDto, Model model) throws DataBaseException {
		return userBaseDAO.queryUserBases(userBaseDto);
	}

	public UserBaseEntity getUserBaseById(UserBaseDto userBaseDto, Model model) throws DataBaseException {
			UserBaseEntity userBaseEntity = null;
			String userIdStr = userBaseDto.getUserId();
			if(StringUtils.isNotBlank(userIdStr)) {
				userBaseEntity = userBaseDAO.getUserBaseById(Integer.parseInt(userIdStr));
				if(userBaseEntity != null) {
					try {
						userBaseEntity.toUserBaseDto(userBaseDto);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			return userBaseEntity;
	}

	public void saveUserBase(UserBaseDto userBaseDto, Model model) throws DataBaseException {
		UserBaseEntity userBaseEntity = userBaseDto.toUserBaseEntity();
		if(StringUtils.isNotBlank(userBaseDto.getUserId())) {
			userBaseDAO.updateUserBase(userBaseEntity);
		} else {
			userBaseDAO.createUserBase(userBaseEntity);
		}
	}

	public void destroyUser(UserBaseDto userBaseDto, Model model) throws DataBaseException{
		String userIdStr = userBaseDto.getUserId();
		if(StringUtils.isNotBlank(userIdStr)) {
			userLoginDAO.updateUserStatus(Integer.parseInt(userIdStr), Constants.STATUS_DISABLE);
		}
	}

}

