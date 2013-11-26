package net.water.security.dao.impl;

import net.kuakao.core.base.dao.BaseDAO;
import net.kuakao.core.exception.DataBaseException;
import net.water.security.dao.ISecUserGroupDAO;
import net.water.security.entity.SecUserGroupEntity;

import org.springframework.stereotype.Repository;


@Repository
public class SecUserGroupDAO extends BaseDAO implements ISecUserGroupDAO {
	

	
	public void destroySecUserGroupByUserId(int userId) throws DataBaseException {
		String sql = "delete from w_sec_user_group where user_id = ? ";
		super.update(sql, new Object[]{userId});
	}
	
	
	
	public void createSecUserGroup(SecUserGroupEntity SecUserGroupEntity) throws DataBaseException {
		String sql = "insert into w_sec_user_group(user_id ,group_id ) values(? ,? )";
		super.update(sql, new Object[]{SecUserGroupEntity.getUserId() ,SecUserGroupEntity.getGroupId() });
	}

}
