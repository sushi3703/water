package net.water.login.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import net.kuakao.core.base.dao.BaseDAO;
import net.kuakao.core.exception.DataBaseException;
import net.water.login.dao.IUserLoginDAO;
import net.water.login.entity.UserLoginEntity;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository
public class UserLoginDAO extends BaseDAO implements IUserLoginDAO {

	@Override
	public void createUserLoginInfo(UserLoginEntity userLoginEntity)
			throws DataBaseException {
		String sql = "insert into user_login(uname,upwd,status,type,create_time) values(?,?,?,?,?)";
		super.update(sql, new Object[]{userLoginEntity.getUname(),userLoginEntity.getUpwd(),userLoginEntity.getStatus(),userLoginEntity.getType(),userLoginEntity.getCreateTime()});
	}



	@Override
	public UserLoginEntity queryUserLogin(UserLoginEntity userLoginEntity) {
		String sql = "select * from user_login where uname=? and upwd=? limit 1";
		return super.queryForObject(sql, new Object[]{userLoginEntity.getUname(),userLoginEntity.getUpwd()}, new RowMapper<UserLoginEntity>() {
			public UserLoginEntity mapRow(ResultSet rs, int value) throws SQLException {
				UserLoginEntity entity = new UserLoginEntity();
				entity.setId(rs.getInt("id"));
				entity.setUname(rs.getString("uname"));
				entity.setStatus(rs.getInt("status"));
				entity.setType(rs.getInt("type"));
				return entity;
			}
		});
	}

	public List<Map<String,Object>> queryUsersByName(String uname){
		String sql = "select id,uname from user_login where uname=?";
		return super.queryForList(sql, new Object[]{uname});
	}


	@Override
	public void updateUserPwd(UserLoginEntity userLoginEntity)
			throws DataBaseException {
		String sql = "update user_login set upwd=? where id=?";
		super.update(sql, new Object[]{userLoginEntity.getUpwd(),userLoginEntity.getId()});
	}
	


}

