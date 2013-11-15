package net.water.security.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.kuakao.core.base.dao.BaseDAO;
import net.kuakao.core.exception.DataBaseException;
import net.water.Constants;
import net.water.security.dao.ISecResGroupDAO;
import net.water.security.dto.SecResGroupDto;
import net.water.security.entity.SecResGroupEntity;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository
public class SecResGroupDAO extends BaseDAO implements ISecResGroupDAO {
	
	
	
	public List<SecResGroupEntity> querySecResGroups(SecResGroupDto secResGroupDto) throws DataBaseException {
		String sql = "select group_id,group_name,owner_id,res_ids from sec_res_group where status=1";
		StringBuffer where = new StringBuffer();
		List<Object> args = new ArrayList<Object>();
		SecResGroupEntity secResGroupEntity = secResGroupDto.toSecResGroupEntity();
		
		if(StringUtils.isNotBlank(secResGroupDto.getGroupId())) {
			args.add(secResGroupEntity.getGroupId());
			where.append(" and group_id=?");
		}
		if(StringUtils.isNotBlank(secResGroupDto.getGroupName())) {
			where.append(" and group_name like '%"+secResGroupEntity.getGroupName()+"%'");
		}
		if(StringUtils.isNotBlank(secResGroupDto.getOwnerId())) {
			args.add(secResGroupEntity.getOwnerId());
			where.append(" and owner_id=?");
		}

		String sqlStr = sql + " order by group_id";
		if(secResGroupDto.getPerPage() == -1){
			return super.query(sqlStr, args.toArray(), new RowMapper<SecResGroupEntity>() {
				public SecResGroupEntity mapRow(ResultSet rs, int arg1) throws SQLException {
					SecResGroupEntity secResGroupEntity = new SecResGroupEntity();
					secResGroupEntity.setGroupId(rs.getInt("group_id"));
					secResGroupEntity.setGroupName(rs.getString("group_name"));
					secResGroupEntity.setOwnerId(rs.getInt("owner_id"));
					secResGroupEntity.setResIds(rs.getString("res_ids"));
					return secResGroupEntity;
				}
			});	
		}else{
			return super.queryByPage(sqlStr, args.toArray(), new RowMapper<SecResGroupEntity>() {
				public SecResGroupEntity mapRow(ResultSet rs, int arg1) throws SQLException {
					SecResGroupEntity secResGroupEntity = new SecResGroupEntity();
					secResGroupEntity.setGroupId(rs.getInt("group_id"));
					secResGroupEntity.setGroupName(rs.getString("group_name"));
					secResGroupEntity.setOwnerId(rs.getInt("owner_id"));
					secResGroupEntity.setResIds(rs.getString("res_ids"));
					return secResGroupEntity;
				}
			},secResGroupDto);
		}
		
	}

	
	
	public SecResGroupEntity getSecResGroupById(int groupId) throws DataBaseException {
		String sql = "select group_id,group_name,owner_id,res_ids from sec_res_group where status=1 and group_id = ?  limit 1";
		return super.queryForObject(sql, new Object[]{groupId }, new RowMapper<SecResGroupEntity>() {
			public SecResGroupEntity mapRow(ResultSet rs, int value) throws SQLException {
				SecResGroupEntity secResGroupEntity = new SecResGroupEntity();
				secResGroupEntity.setGroupId(rs.getInt("group_id"));
				secResGroupEntity.setGroupName(rs.getString("group_name"));
				secResGroupEntity.setOwnerId(rs.getInt("owner_id"));
				secResGroupEntity.setResIds(rs.getString("res_ids"));
				return secResGroupEntity;
			}
		});
	}

	
	public void destroySecResGroup(int groupId) throws DataBaseException {
		String sql = "update sec_res_group set status=? where group_id = ? ";
		super.update(sql, new Object[]{Constants.STATUS_DISABLE,groupId});
	}
	
	
	
	public void createSecResGroup(SecResGroupEntity secResGroupEntity) throws DataBaseException {
		String sql = "insert into sec_res_group(group_name ,owner_id ,res_ids ) values(? ,? ,? )";
		super.update(sql, new Object[]{secResGroupEntity.getGroupName() ,secResGroupEntity.getOwnerId() ,secResGroupEntity.getResIds() });
	}
	
	
	
	public void updateSecResGroup(SecResGroupEntity secResGroupEntity) throws DataBaseException {
		StringBuffer updateSql = new StringBuffer("update sec_res_group set ");
		List<Object> args = new ArrayList<Object>();
		args.add(secResGroupEntity.getGroupName());
		updateSql.append("group_name=?").append(",");
		args.add(secResGroupEntity.getResIds());
		updateSql.append("res_ids=?").append(",");
		
		args.add(secResGroupEntity.getGroupId());
		
		String sql = updateSql.substring(0, updateSql.length()-1) + " where group_id = ? "; 
		super.update(sql, args.toArray());
	}

}

