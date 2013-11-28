package net.water.user.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

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
		String sql = "select b.user_id,b.create_user_id,b.create_time,b.create_ip,b.company,b.department,b.job_title,b.qq,b.mobile,b.tel,b.note,l.uname,l.email,l.type from w_user_base b,w_user_login l where b.user_id=l.user_id and l.status=1 and l.type>0";
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
			args.add(userBaseEntity.getEmail());
			where.append(" and l.email=?");
		}
		if(StringUtils.isNotBlank(userBaseDto.getCreateUserId())) {
			args.add(userBaseEntity.getCreateUserId());
			where.append(" and b.create_user_id=?");
		}
		if(StringUtils.isNotBlank(userBaseDto.getCompany())) {
			args.add(userBaseEntity.getCompany());
			where.append(" and b.company=?");
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
		if(StringUtils.isNotBlank(userBaseDto.getTel())) {
			args.add(userBaseEntity.getTel());
			where.append(" and b.tel=?");
		}
		
		if(userBaseDto.getPerPage() == -1){
			return super.query(sql + where.toString(), args.toArray(), new RowMapper<UserBaseEntity>() {

				public UserBaseEntity mapRow(ResultSet rs, int arg1) throws SQLException {
					UserBaseEntity userBaseEntity = new UserBaseEntity();
					userBaseEntity.setUserId(rs.getInt("user_id"));
					userBaseEntity.setCreateUserId(rs.getInt("create_user_id"));
					userBaseEntity.setCreateTime(rs.getTimestamp("create_time"));
					userBaseEntity.setCreateIp(rs.getString("create_ip"));
					userBaseEntity.setCompany(rs.getString("company"));
					userBaseEntity.setDepartment(rs.getString("department"));
					userBaseEntity.setJobTitle(rs.getString("job_title"));
					userBaseEntity.setQq(rs.getString("qq"));
					userBaseEntity.setMobile(rs.getString("mobile"));
					userBaseEntity.setTel(rs.getString("tel"));
					userBaseEntity.setNote(rs.getString("note"));
					userBaseEntity.setUname(rs.getString("uname"));
					userBaseEntity.setEmail(rs.getString("email"));
					return userBaseEntity;
				}
				
			});
		}else{
			return super.queryByPage(sql + where.toString().replaceFirst("and","where"), args.toArray(), new RowMapper<UserBaseEntity>() {

				public UserBaseEntity mapRow(ResultSet rs, int arg1) throws SQLException {
					UserBaseEntity userBaseEntity = new UserBaseEntity();
					userBaseEntity.setUserId(rs.getInt("user_id"));
					userBaseEntity.setCreateUserId(rs.getInt("create_user_id"));
					userBaseEntity.setCreateTime(rs.getTimestamp("create_time"));
					userBaseEntity.setCreateIp(rs.getString("create_ip"));
					userBaseEntity.setCompany(rs.getString("company"));
					userBaseEntity.setDepartment(rs.getString("department"));
					userBaseEntity.setJobTitle(rs.getString("job_title"));
					userBaseEntity.setQq(rs.getString("qq"));
					userBaseEntity.setMobile(rs.getString("mobile"));
					userBaseEntity.setTel(rs.getString("tel"));
					userBaseEntity.setNote(rs.getString("note"));
					userBaseEntity.setUname(rs.getString("uname"));
					userBaseEntity.setEmail(rs.getString("email"));
					return userBaseEntity;
				}
				
			}, userBaseDto);
		}
	}
	
	
	public UserBaseEntity getUserBaseById(int userId) throws DataBaseException {
		String sql = "select b.user_id,b.create_user_id,b.create_time,b.create_ip,b.company,b.department,b.job_title,b.qq,b.mobile,b.tel,b.note,l.uname,l.email from w_user_base b,w_user_login l where b.user_id=l.user_id and b.user_id = ?  limit 1";
		return super.queryForObject(sql, new Object[]{userId}, new RowMapper<UserBaseEntity>() {
			public UserBaseEntity mapRow(ResultSet rs, int value) throws SQLException {
				UserBaseEntity userBaseEntity = new UserBaseEntity();
				userBaseEntity.setUserId(rs.getInt("user_id"));
				userBaseEntity.setCreateUserId(rs.getInt("create_user_id"));
				userBaseEntity.setCreateTime(rs.getTimestamp("create_time"));
				userBaseEntity.setCreateIp(rs.getString("create_ip"));
				userBaseEntity.setCompany(rs.getString("company"));
				userBaseEntity.setDepartment(rs.getString("department"));
				userBaseEntity.setJobTitle(rs.getString("job_title"));
				userBaseEntity.setQq(rs.getString("qq"));
				userBaseEntity.setMobile(rs.getString("mobile"));
				userBaseEntity.setTel(rs.getString("tel"));
				userBaseEntity.setNote(rs.getString("note"));
				userBaseEntity.setUname(rs.getString("uname"));
				userBaseEntity.setEmail(rs.getString("email"));
				return userBaseEntity;
			}
		});
	}

	
	public void createUserBase(UserBaseEntity userBaseEntity) throws DataBaseException {
		String sql = "insert into w_user_base(create_user_id ,create_time ,create_ip ,company ,department ,job_title ,qq ,mobile ,tel ,note ) values(? ,? ,? ,? ,? ,? ,? ,? ,? ,? )";
		super.update(sql, new Object[]{userBaseEntity.getCreateUserId() ,userBaseEntity.getCreateTime() ,userBaseEntity.getCreateIp() ,userBaseEntity.getCompany() ,userBaseEntity.getDepartment() ,userBaseEntity.getJobTitle() ,userBaseEntity.getQq() ,userBaseEntity.getMobile() ,userBaseEntity.getTel() ,userBaseEntity.getNote() });
	}
	
	
	
	public void updateUserBase(UserBaseEntity userBaseEntity) throws DataBaseException {
		StringBuffer updateSql = new StringBuffer("update w_user_base set ");
		List<Object> args = new ArrayList<Object>();
		if(StringUtils.isNotBlank(userBaseEntity.getCompany())){
			args.add(userBaseEntity.getCompany());
			updateSql.append("company=?").append(",");
		}
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
		if(StringUtils.isNotBlank(userBaseEntity.getTel())){
			args.add(userBaseEntity.getTel());
			updateSql.append("tel=?").append(",");	
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

