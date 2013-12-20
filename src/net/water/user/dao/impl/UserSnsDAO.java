package net.water.user.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import net.kuakao.core.base.dao.BaseDAO;
import net.kuakao.core.exception.DataBaseException;
import net.water.user.dao.IUserSnsDAO;
import net.water.user.entity.UserSnsEntity;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository
public class UserSnsDAO extends BaseDAO implements IUserSnsDAO {

	@Override
	public UserSnsEntity queryUserSnsByQqOpenId(UserSnsEntity userSnsEntity) {
		String sql = "select user_id,qq_username,qq_open_id,qq_access_token,update_time from w_user_sns where qq_open_id=? limit 1";
		return super.queryForObject(sql, new Object[]{userSnsEntity.getQqOpenId()}, new RowMapper<UserSnsEntity>() {
			public UserSnsEntity mapRow(ResultSet rs, int value) throws SQLException {
				UserSnsEntity entity = new UserSnsEntity();
				entity.setUserId(rs.getString("user_id"));
				entity.setQqUsername(rs.getString("qq_username"));
				entity.setQqOpenId(rs.getString("qq_open_id"));
				entity.setQqAccessToken(rs.getString("qq_access_token"));
				entity.setUpdateTime(rs.getTimestamp("update_time"));
				return entity;
			}
		});
	}
	
	public UserSnsEntity queryUserSnsByUserId(String userId){
		String sql = "select user_id,qq_username,qq_open_id,qq_access_token,update_time from w_user_sns where user_id=? limit 1";
		return super.queryForObject(sql, new Object[]{userId}, new RowMapper<UserSnsEntity>() {
			public UserSnsEntity mapRow(ResultSet rs, int value) throws SQLException {
				UserSnsEntity entity = new UserSnsEntity();
				entity.setUserId(rs.getString("user_id"));
				entity.setQqUsername(rs.getString("qq_username"));
				entity.setQqOpenId(rs.getString("qq_open_id"));
				entity.setQqAccessToken(rs.getString("qq_access_token"));
				entity.setUpdateTime(rs.getTimestamp("update_time"));
				return entity;
			}
		});
	}
	
	public void createUserSns(UserSnsEntity userSnsEntity) throws DataBaseException{
		String sql = "insert into w_user_sns(user_id,qq_open_id,qq_access_token,qq_username,update_time) values(?,?,?,?,?)";
		super.update(sql, new Object[]{userSnsEntity.getUserId(),userSnsEntity.getQqOpenId(),userSnsEntity.getQqAccessToken(),userSnsEntity.getQqUsername(),userSnsEntity.getUpdateTime()});
	}

	@Override
	public void updateUserQqLoginInfo(UserSnsEntity userSnsEntity)
			throws DataBaseException {
		String sql = "update w_user_sns set qq_open_id=?,qq_access_token=?,qq_username=?,update_time=? where user_id=?";
		super.update(sql, new Object[]{userSnsEntity.getQqOpenId(),userSnsEntity.getQqAccessToken(),userSnsEntity.getQqUsername(),userSnsEntity.getUpdateTime(),userSnsEntity.getUserId()});
	}
	
}

