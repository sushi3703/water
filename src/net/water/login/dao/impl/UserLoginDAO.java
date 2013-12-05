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
		String sql = "insert into w_user_login(user_id,uname,email,upwd,type) values(?,?,?,?,?)";
		super.update(sql, new Object[]{userLoginEntity.getUserId(),userLoginEntity.getUname(),userLoginEntity.getEmail(),userLoginEntity.getUpwd(),userLoginEntity.getType()});
	}



	@Override
	public UserLoginEntity queryUserLoginByUname(UserLoginEntity userLoginEntity) {
		String sql = "select user_id,uname,email,upwd,type,status,qq_open_id,qq_access_token from w_user_login where uname=? and upwd=? limit 1";
		return super.queryForObject(sql, new Object[]{userLoginEntity.getUname(),userLoginEntity.getUpwd()}, new RowMapper<UserLoginEntity>() {
			public UserLoginEntity mapRow(ResultSet rs, int value) throws SQLException {
				UserLoginEntity entity = new UserLoginEntity();
				entity.setUserId(rs.getInt("user_id"));
				entity.setUname(rs.getString("uname"));
				entity.setStatus(rs.getInt("status"));
				entity.setType(rs.getInt("type"));
				entity.setEmail(rs.getString("email"));
				entity.setQqOpenId(rs.getString("qq_open_id"));
				entity.setQqAccessToken(rs.getString("qq_access_token"));
				return entity;
			}
		});
	}

	@Override
	public UserLoginEntity queryUserLoginByEmail(UserLoginEntity userLoginEntity) {
		String sql = "select user_id,uname,email,upwd,type,status,qq_open_id,qq_access_token from w_user_login where email=? and upwd=? limit 1";
		return super.queryForObject(sql, new Object[]{userLoginEntity.getEmail(),userLoginEntity.getUpwd()}, new RowMapper<UserLoginEntity>() {
			public UserLoginEntity mapRow(ResultSet rs, int value) throws SQLException {
				UserLoginEntity entity = new UserLoginEntity();
				entity.setUserId(rs.getInt("user_id"));
				entity.setUname(rs.getString("uname"));
				entity.setStatus(rs.getInt("status"));
				entity.setType(rs.getInt("type"));
				entity.setEmail(rs.getString("email"));
				entity.setQqOpenId(rs.getString("qq_open_id"));
				entity.setQqAccessToken(rs.getString("qq_access_token"));
				return entity;
			}
		});
	}

	public UserLoginEntity queryUserLoginByQqOpenId(UserLoginEntity userLoginEntity) {
		String sql = "select user_id,uname,email,type,status,qq_open_id,qq_access_token from w_user_login where qq_open_id=? limit 1";
		return super.queryForObject(sql, new Object[]{userLoginEntity.getQqOpenId()}, new RowMapper<UserLoginEntity>() {
			public UserLoginEntity mapRow(ResultSet rs, int value) throws SQLException {
				UserLoginEntity entity = new UserLoginEntity();
				entity.setUserId(rs.getInt("user_id"));
				entity.setUname(rs.getString("uname"));
				entity.setStatus(rs.getInt("status"));
				entity.setType(rs.getInt("type"));
				entity.setEmail(rs.getString("email"));
				entity.setQqOpenId(rs.getString("qq_open_id"));
				entity.setQqAccessToken(rs.getString("qq_access_token"));
				return entity;
			}
		});
	}

	public UserLoginEntity queryUserLoginByUserId(UserLoginEntity userLoginEntity) {
		String sql = "select user_id,uname,email,type,status,qq_open_id,qq_access_token from w_user_login where user_id=? limit 1";
		return super.queryForObject(sql, new Object[]{userLoginEntity.getUserId()}, new RowMapper<UserLoginEntity>() {
			public UserLoginEntity mapRow(ResultSet rs, int value) throws SQLException {
				UserLoginEntity entity = new UserLoginEntity();
				entity.setUserId(rs.getInt("user_id"));
				entity.setUname(rs.getString("uname"));
				entity.setStatus(rs.getInt("status"));
				entity.setType(rs.getInt("type"));
				entity.setEmail(rs.getString("email"));
				entity.setQqOpenId(rs.getString("qq_open_id"));
				entity.setQqAccessToken(rs.getString("qq_access_token"));
				return entity;
			}
		});
	}

	public List<Map<String,Object>> queryUsersByName(String uname){
		String sql = "select user_id,uname,email from w_user_login where status=1 and uname=?";
		return super.queryForList(sql, new Object[]{uname});
	}


	@Override
	public void updateUserPwd(UserLoginEntity userLoginEntity)
			throws DataBaseException {
		String sql = "update w_user_login set upwd=? where user_id=?";
		super.update(sql, new Object[]{userLoginEntity.getUpwd(),userLoginEntity.getUserId()});
	}
	

	public List<Map<String,Object>> queryUsersByEmail(String email){
		String sql = "select user_id,uname,email from w_user_login where status=1 and email=?";
		return super.queryForList(sql, new Object[]{email});
	}

	public void updateUserStatus(int userId,int status) throws DataBaseException{
		String sql = "update w_user_login set status=? where user_id=?";
		super.update(sql, new Object[]{status,userId});
	}
	
	public void updateUserQqLoginInfo(UserLoginEntity userLoginEntity) throws DataBaseException{
		String sql = "update w_user_login set qq_open_id=?,qq_access_token=? where user_id=?";
		super.update(sql, new Object[]{userLoginEntity.getQqOpenId(),userLoginEntity.getQqAccessToken(),userLoginEntity.getUserId()});
	}
}

