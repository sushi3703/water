package net.water.user.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.kuakao.core.base.dao.BaseDAO;
import net.kuakao.core.exception.DataBaseException;
import net.water.user.dao.IUserBaseDAO;
import net.water.user.dto.UserBaseDto;
import net.water.user.entity.UserBaseEntity;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository
public class UserBaseDAO extends BaseDAO implements IUserBaseDAO {
	
	
	public List<UserBaseEntity> queryUserBases(UserBaseDto userBaseDto) throws DataBaseException {
		String sql = "select b.user_id,b.create_time,b.department,b.job_title,b.qq,b.mobile,b.note,l.uname,l.email,l.type,l.team_id from w_user_base b,w_user_login l where b.user_id=l.user_id and l.status=1";
		StringBuffer where = new StringBuffer();
		List<Object> args = new ArrayList<Object>();
		UserBaseEntity userBaseEntity = userBaseDto.toUserBaseEntity();
		
		if(StringUtils.isNotBlank(userBaseDto.getUserId())) {
			args.add(userBaseEntity.getUserId());
			where.append(" and b.user_id=?");
		}
		if(StringUtils.isNotBlank(userBaseDto.getUname())){
			where.append(" and l.uname like '%"+userBaseDto.getUname()+"%'");
		}
		if(StringUtils.isNotBlank(userBaseDto.getEmail())){
			args.add(userBaseDto.getEmail());
			where.append(" and l.email=?");
		}
		if(StringUtils.isNotBlank(userBaseDto.getDepartment())) {
			args.add(userBaseEntity.getDepartment());
			where.append(" and b.department=?");
		}
		if(StringUtils.isNotBlank(userBaseDto.getQq())) {
			args.add(userBaseEntity.getQq());
			where.append(" and b.qq=?");
		}
		if(StringUtils.isNotBlank(userBaseDto.getMobile())) {
			args.add(userBaseEntity.getMobile());
			where.append(" and b.mobile=?");
		}
		if(StringUtils.isNotBlank(userBaseDto.getTeamId())){
			args.add(userBaseDto.getTeamId());
			where.append(" and l.team_id=?");
		}
		if(StringUtils.isNotBlank(userBaseDto.getUserType())){
			args.add(userBaseDto.getUserType());
			where.append(" and l.type=?");
		}
		
		String sqlSel = sql + where.toString() + " order by b.create_time";
		
		if(userBaseDto.getPerPage() == -1){
			return super.query(sqlSel, args.toArray(), new RowMapper<UserBaseEntity>() {

				public UserBaseEntity mapRow(ResultSet rs, int arg1) throws SQLException {
					UserBaseEntity userBaseEntity = new UserBaseEntity();
					userBaseEntity.setUserId(rs.getString("user_id"));
					userBaseEntity.setCreateTime(rs.getTimestamp("create_time"));
					userBaseEntity.setDepartment(rs.getString("department"));
					userBaseEntity.setJobTitle(rs.getString("job_title"));
					userBaseEntity.setQq(rs.getString("qq"));
					userBaseEntity.setMobile(rs.getString("mobile"));
					userBaseEntity.setNote(rs.getString("note"));
					userBaseEntity.setUname(rs.getString("uname"));
					userBaseEntity.setEmail(rs.getString("email"));
					userBaseEntity.setTeamId(rs.getString("team_id"));
					userBaseEntity.setType(rs.getInt("type"));
					return userBaseEntity;
				}
				
			});
		}else{
			return super.queryByPage(sqlSel, args.toArray(), new RowMapper<UserBaseEntity>() {

				public UserBaseEntity mapRow(ResultSet rs, int arg1) throws SQLException {
					UserBaseEntity userBaseEntity = new UserBaseEntity();
					userBaseEntity.setUserId(rs.getString("user_id"));
					userBaseEntity.setCreateTime(rs.getTimestamp("create_time"));
					userBaseEntity.setDepartment(rs.getString("department"));
					userBaseEntity.setJobTitle(rs.getString("job_title"));
					userBaseEntity.setQq(rs.getString("qq"));
					userBaseEntity.setMobile(rs.getString("mobile"));
					userBaseEntity.setNote(rs.getString("note"));
					userBaseEntity.setUname(rs.getString("uname"));
					userBaseEntity.setEmail(rs.getString("email"));
					userBaseEntity.setTeamId(rs.getString("team_id"));
					userBaseEntity.setType(rs.getInt("type"));
					return userBaseEntity;
				}
				
			}, userBaseDto);
		}
	}
	
	public List<UserBaseEntity> getAllBaseUser(UserBaseDto userBaseDto) throws DataBaseException {
		String sql = "select b.user_id,b.create_time,b.qq,b.mobile,b.note,l.uname,l.email,l.team_id,t.team_name from w_user_base b join w_user_login l on b.user_id=l.user_id left join w_team t on l.team_id=t.team_id where l.status=1";
		StringBuffer where = new StringBuffer();
		List<Object> args = new ArrayList<Object>();
		
		if(StringUtils.isNotBlank(userBaseDto.getUserId())) {
			args.add(userBaseDto.getUserId());
			where.append(" and b.user_id=?");
		}
		if(StringUtils.isNotBlank(userBaseDto.getUname())){
			where.append(" and l.uname like '%"+userBaseDto.getUname()+"%'");
		}
		if(StringUtils.isNotBlank(userBaseDto.getEmail())){
			args.add(userBaseDto.getEmail());
			where.append(" and l.email=?");
		}
		if(StringUtils.isNotBlank(userBaseDto.getQq())) {
			args.add(userBaseDto.getQq());
			where.append(" and b.qq=?");
		}
		if(StringUtils.isNotBlank(userBaseDto.getMobile())) {
			args.add(userBaseDto.getMobile());
			where.append(" and b.mobile=?");
		}
		if(StringUtils.isNotBlank(userBaseDto.getTeamId())){
			args.add(userBaseDto.getTeamId());
			where.append(" and l.team_id=?");
		}
		
		String sqlSel = sql + where.toString() + " order by b.create_time";
		
		return super.queryByPage(sqlSel, args.toArray(), new RowMapper<UserBaseEntity>() {

			public UserBaseEntity mapRow(ResultSet rs, int arg1) throws SQLException {
				UserBaseEntity userBaseEntity = new UserBaseEntity();
				userBaseEntity.setUserId(rs.getString("user_id"));
				userBaseEntity.setCreateTime(rs.getTimestamp("create_time"));
				userBaseEntity.setQq(rs.getString("qq"));
				userBaseEntity.setMobile(rs.getString("mobile"));
				userBaseEntity.setNote(rs.getString("note"));
				userBaseEntity.setUname(rs.getString("uname"));
				userBaseEntity.setEmail(rs.getString("email"));
				userBaseEntity.setTeamId(rs.getString("team_id"));
				userBaseEntity.setTeamName(rs.getString("team_name"));
				return userBaseEntity;
			}
			
		}, userBaseDto);
	}
	
	
	public UserBaseEntity getUserBaseById(String userId) throws DataBaseException {
		String sql = "select b.user_id,b.create_time,b.department,b.job_title,b.qq,b.mobile,b.note,l.uname,l.email,l.team_id,l.type from w_user_base b,w_user_login l where b.user_id=l.user_id and b.user_id = ?  limit 1";
		return super.queryForObject(sql, new Object[]{userId}, new RowMapper<UserBaseEntity>() {
			public UserBaseEntity mapRow(ResultSet rs, int value) throws SQLException {
				UserBaseEntity userBaseEntity = new UserBaseEntity();
				userBaseEntity.setUserId(rs.getString("user_id"));
				userBaseEntity.setCreateTime(rs.getTimestamp("create_time"));
				userBaseEntity.setDepartment(rs.getString("department"));
				userBaseEntity.setJobTitle(rs.getString("job_title"));
				userBaseEntity.setQq(rs.getString("qq"));
				userBaseEntity.setMobile(rs.getString("mobile"));
				userBaseEntity.setNote(rs.getString("note"));
				userBaseEntity.setUname(rs.getString("uname"));
				userBaseEntity.setEmail(rs.getString("email"));
				userBaseEntity.setTeamId(rs.getString("team_id"));
				userBaseEntity.setType(rs.getInt("type"));
				return userBaseEntity;
			}
		});
	}

	
	public void createUserBase(UserBaseEntity userBaseEntity) throws DataBaseException {
		String sql = "insert into w_user_base(user_id,create_time ,department ,job_title ,qq ,mobile ,note ) values(? ,? ,? ,? ,? ,? ,? )";
		super.update(sql, new Object[]{userBaseEntity.getUserId() ,userBaseEntity.getCreateTime() ,userBaseEntity.getDepartment() ,userBaseEntity.getJobTitle() ,userBaseEntity.getQq() ,userBaseEntity.getMobile() ,userBaseEntity.getNote() });
	}
	
	
	
	public void updateUserBase(UserBaseEntity userBaseEntity) throws DataBaseException {
		StringBuffer updateSql = new StringBuffer("update w_user_base set ");
		List<Object> args = new ArrayList<Object>();
		if(StringUtils.isNotBlank(userBaseEntity.getDepartment())){
			args.add(userBaseEntity.getDepartment());
			updateSql.append("department=?").append(",");	
		}
		if(StringUtils.isNotBlank(userBaseEntity.getJobTitle())){
			args.add(userBaseEntity.getJobTitle());
			updateSql.append("job_title=?").append(",");
		}
		if(StringUtils.isNotBlank(userBaseEntity.getQq())){
			args.add(userBaseEntity.getQq());
			updateSql.append("qq=?").append(",");
		}
		if(StringUtils.isNotBlank(userBaseEntity.getMobile())){
			args.add(userBaseEntity.getMobile());
			updateSql.append("mobile=?").append(",");
		}
		if(StringUtils.isNotBlank(userBaseEntity.getNote())){
			args.add(userBaseEntity.getNote());
			updateSql.append("note=?").append(",");
		}
		
		args.add(userBaseEntity.getUserId());
			
		String sql = updateSql.substring(0, updateSql.length()-1) + " where user_id = ? "; 
		super.update(sql, args.toArray());
	}

}

