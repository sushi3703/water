package net.water.user.service;
import java.util.List;

import net.kuakao.core.exception.DataBaseException;
import net.water.user.dto.UserBaseDto;
import net.water.user.entity.UserBaseEntity;

import org.springframework.ui.Model;


public interface IUserBaseService {
	
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
	 * 保存或更新用户基本信息
	 * @param userBaseDto
	 * @throws Exception
	 */
	public void saveUserBase(UserBaseDto userBaseDto, Model model) throws DataBaseException;
	
	/**
	 * 删除用户
	 * @param userBaseDto
	 * @param model
	 * @throws DataBaseException
	 */
	public void destroyUser(UserBaseDto userBaseDto, Model model) throws DataBaseException;

}
