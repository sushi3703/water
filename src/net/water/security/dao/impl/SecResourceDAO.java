package net.water.security.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import net.kuakao.core.base.dao.BaseDAO;
import net.kuakao.core.exception.DataBaseException;
import net.water.Constants;
import net.water.security.dao.ISecResourceDAO;
import net.water.security.dto.SecResourceDto;
import net.water.security.entity.SecResourceEntity;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository
public class SecResourceDAO extends BaseDAO implements ISecResourceDAO {
	
	
	public List<SecResourceEntity> querySecResources(SecResourceDto SecResourceDto) throws DataBaseException {
		String sql = "select res_id,res_name,app_type,app_menu,url_ids,status from sec_resource";
		StringBuffer where = new StringBuffer();
		List<Object> args = new ArrayList<Object>();
		SecResourceEntity SecResourceEntity = SecResourceDto.toSecResourceEntity();
		
		if(StringUtils.isNotBlank(SecResourceDto.getResId())) {
			args.add(SecResourceEntity.getResId());
			where.append(" and res_id=?");
		}
		if(StringUtils.isNotBlank(SecResourceDto.getResName())) {
			where.append(" and res_name like '%"+SecResourceEntity.getResName()+"%'");
		}
		if(StringUtils.isNotBlank(SecResourceDto.getAppType())) {
			args.add(SecResourceEntity.getAppType());
			where.append(" and app_type=?");
		}
		if(StringUtils.isNotBlank(SecResourceDto.getAppMenu())) {
			args.add(SecResourceEntity.getAppMenu());
			where.append(" and app_menu=?");
		}
		if(StringUtils.isNotBlank(SecResourceDto.getStatus())) {
			args.add(SecResourceEntity.getStatus());
			where.append(" and status=?");
		}
		
		String querySql = sql + where.toString().replaceFirst("and","where")+" order by res_id";
		
		if(SecResourceDto.getPerPage()==-1){
			return super.query(querySql, args.toArray(), new RowMapper<SecResourceEntity>() {
				public SecResourceEntity mapRow(ResultSet rs, int arg1) throws SQLException {
					SecResourceEntity SecResourceEntity = new SecResourceEntity();
					SecResourceEntity.setResId(rs.getInt("res_id"));
					SecResourceEntity.setResName(rs.getString("res_name"));
					SecResourceEntity.setAppType(rs.getInt("app_type"));
					SecResourceEntity.setAppMenu(rs.getInt("app_menu"));
					SecResourceEntity.setUrlIds(rs.getString("url_ids"));
					SecResourceEntity.setStatus(rs.getInt("status"));
					return SecResourceEntity;
				}
			});
		}else{
			return super.queryByPage(querySql, args.toArray(), new RowMapper<SecResourceEntity>() {
				public SecResourceEntity mapRow(ResultSet rs, int arg1) throws SQLException {
					SecResourceEntity SecResourceEntity = new SecResourceEntity();
					SecResourceEntity.setResId(rs.getInt("res_id"));
					SecResourceEntity.setResName(rs.getString("res_name"));
					SecResourceEntity.setAppType(rs.getInt("app_type"));
					SecResourceEntity.setAppMenu(rs.getInt("app_menu"));
					SecResourceEntity.setUrlIds(rs.getString("url_ids"));
					SecResourceEntity.setStatus(rs.getInt("status"));
					return SecResourceEntity;
				}
			}, SecResourceDto);
		}
	}
	
	
	public SecResourceEntity getSecResourceById(int resId) throws DataBaseException {
		String sql = "select res_id,res_name,app_type,app_menu,url_ids,status from sec_resource where res_id = ?  limit 1";
		return super.queryForObject(sql, new Object[]{resId }, new RowMapper<SecResourceEntity>() {
			public SecResourceEntity mapRow(ResultSet rs, int value) throws SQLException {
				SecResourceEntity SecResourceEntity = new SecResourceEntity();
				SecResourceEntity.setResId(rs.getInt("res_id"));
				SecResourceEntity.setResName(rs.getString("res_name"));
				SecResourceEntity.setAppType(rs.getInt("app_type"));
				SecResourceEntity.setAppMenu(rs.getInt("app_menu"));
				SecResourceEntity.setUrlIds(rs.getString("url_ids"));
				SecResourceEntity.setStatus(rs.getInt("status"));
				return SecResourceEntity;
			}
		});
	}

	
	public void destroySecResource(int resId) throws DataBaseException {
		String sql = "update sec_resource set status=? where res_id = ? ";
		super.update(sql, new Object[]{Constants.STATUS_DISABLE,resId});
	}
	
	
	
	public void createSecResource(SecResourceEntity SecResourceEntity) throws DataBaseException {
		String sql = "insert into sec_resource(res_name ,app_type ,app_menu ,url_ids ) values(? ,? ,? ,? )";
		super.update(sql, new Object[]{SecResourceEntity.getResName() ,SecResourceEntity.getAppType() ,SecResourceEntity.getAppMenu() ,SecResourceEntity.getUrlIds() });
	}
	
	
	
	public void updateSecResource(SecResourceEntity SecResourceEntity) throws DataBaseException {
		StringBuffer updateSql = new StringBuffer("update sec_resource set ");
		List<Object> args = new ArrayList<Object>();
		args.add(SecResourceEntity.getResName());
		updateSql.append("res_name=?").append(",");
		args.add(SecResourceEntity.getAppType());
		updateSql.append("app_type=?").append(",");
		args.add(SecResourceEntity.getAppMenu());
		updateSql.append("app_menu=?").append(",");
		args.add(SecResourceEntity.getUrlIds());
		updateSql.append("url_ids=?").append(",");
		
		args.add(SecResourceEntity.getResId());
			
		String sql = updateSql.substring(0, updateSql.length()-1) + " where res_id = ? "; 
		super.update(sql, args.toArray());
	}

}

