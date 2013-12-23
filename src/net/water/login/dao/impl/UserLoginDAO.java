package net.water.login.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

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
		String sql = "insert into w_user_login(user_id,uname,email,upwd,type,team_id) values(?,?,?,?,?,?)";
		super.update(sql, new Object[]{userLoginEntity.getUserId(),userLoginEntity.getUname(),userLoginEntity.getEmail(),userLoginEntity.getUpwd(),userLoginEntity.getType(),userLoginEntity.getTeamId()});
	}

	@Override
	public UserLoginEntity queryUserLoginByEmail(String email) {
		String sql = "select user_id,uname,email,upwd,type,status,team_id from w_user_login where email=? limit 1";
		return super.queryForObject(sql, new Object[]{email}, new RowMapper<UserLoginEntity>() {
			public UserLoginEntity mapRow(ResultSet rs, int value) throws SQLException {
				UserLoginEntity entity = new UserLoginEntity();
				entity.setUserId(rs.getString("user_id"));
				entity.setUname(rs.getString("uname"));
				entity.setUpwd(rs.getString("upwd"));
				entity.setStatus(rs.getInt("status"));
				entity.setType(rs.getInt("type"));
				entity.setEmail(rs.getString("email"));
				entity.setTeamId(rs.getString("team_id"));
				return entity;
			}
		});
	}

	public UserLoginEntity queryUserLoginByUserId(String userId) {
		String sql = "select user_id,uname,email,type,status,team_id from w_user_login where user_id=? limit 1";
		return super.queryForObject(sql, new Object[]{userId}, new RowMapper<UserLoginEntity>() {
			public UserLoginEntity mapRow(ResultSet rs, int value) throws SQLException {
				UserLoginEntity entity = new UserLoginEntity();
				entity.setUserId(rs.getString("user_id"));
				entity.setUname(rs.getString("uname"));
				entity.setStatus(rs.getInt("status"));
				entity.setType(rs.getInt("type"));
				entity.setEmail(rs.getString("email"));
				entity.setTeamId(rs.getString("team_id"));
				return entity;
			}
		});
	}

	@Override
	public void updateUserPwd(UserLoginEntity userLoginEntity)
			throws DataBaseException {
		String sql = "update w_user_login set upwd=? where user_id=?";
		super.update(sql, new Object[]{userLoginEntity.getUpwd(),userLoginEntity.getUserId()});
	}
	

	public void updateUserStatus(String userId,int status) throws DataBaseException{
		String sql = "update w_user_login set status=? where user_id=?";
		super.update(sql, new Object[]{status,userId});
	}
	

	public void updateUserName(String userId,String uname) throws DataBaseException{
		String sql = "update w_user_login set uname=? where user_id=?";
		super.update(sql, new Object[]{uname,userId});
	}
	

	public void updateUserTeam(String userId,String teamId) throws DataBaseException{
		String sql = "update w_user_login set team_id=? where user_id=?";
		super.update(sql, new Object[]{teamId,userId});
	}
	
	
}

