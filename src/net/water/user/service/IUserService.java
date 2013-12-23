package net.water.user.service;
import java.util.List;

import net.kuakao.core.exception.DataBaseException;
import net.water.login.entity.UserLoginEntity;
import net.water.user.dto.UserBaseDto;
import net.water.user.entity.UserBaseEntity;

import org.springframework.ui.Model;


public interface IUserService {
	
	/**
	 * 用户基本信息(分页)查询
	 * @param userBaseDto 用户基本信息Dto
	 * @return 用户基本信息列表
	 * @throws Exception
	 */
	public List<UserBaseEntity> queryUserBaseByPage(UserBaseDto userBaseDto, Model model) throws DataBaseException;
	
	/**
	 * 查询单个用户基本信息对象
	 * @param userBaseDto 用户基本信息Dto
	 * @return 用户基本信息
	 * @throws Exception
	 */
	public UserBaseEntity getUserBaseById(UserBaseDto userBaseDto, Model model) throws DataBaseException;
	

	/**
	 * 修改用户登录密码
	 * @param userLoginEntity
	 * @throws DataBaseException
	 */
	public void updateUserPwd(UserLoginEntity userLoginEntity)throws DataBaseException;
	
	/**
	 * 更新用户基本信息
	 * @param userBaseEntity
	 * @param model
	 * @throws DataBaseException
	 */
	public void updateUserBase(UserBaseEntity userBaseEntity, Model model) throws DataBaseException;
	
	/**
	 * 删除用户
	 * @param userBaseDto
	 * @param model
	 * @throws DataBaseException
	 */
	public void destroyUser(UserBaseDto userBaseDto, Model model) throws DataBaseException;

	/**
	 * 注册新用户
	 * @param userLoginEntity type/email/upwd(/teamId)
	 * @param model
	 * @throws DataBaseException
	 */
	public void createRegisterUser(UserLoginEntity userLoginEntity, Model model) throws DataBaseException;
	
	/**
	 * (注册时)验证邮箱是否已存在
	 * @param email
	 * @return true存在false不存在
	 */
	public boolean validateEmailExit(String email);
}
