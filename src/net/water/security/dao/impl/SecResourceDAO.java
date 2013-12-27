package net.water.security.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.kuakao.core.base.dao.BaseDAO;
import net.kuakao.core.exception.DataBaseException;
import net.water.security.dao.ISecResourceDAO;
import net.water.security.dto.SecResourceDto;
import net.water.security.entity.SecResourceEntity;
import net.water.security.entity.SecUserResourceEntity;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository
public class SecResourceDAO extends BaseDAO implements ISecResourceDAO {
	
	
	public List<SecResourceEntity> querySecResources(SecResourceDto secResourceDto) throws DataBaseException {
		String sql = "select res_id,res_name,app_type,app_menu,url_ids,base_res from w_sec_resource where status=1";
		StringBuffer where = new StringBuffer();
		List<Object> args = new ArrayList<Object>();
		SecResourceEntity secResourceEntity = secResourceDto.toSecResourceEntity();
		
		if(StringUtils.isNotBlank(secResourceDto.getResId())) {
			if(secResourceDto.getResId().indexOf(",")>-1){
				where.append(" and res_id in ("+secResourceDto.getResId()+")");
			}else{
				args.add(secResourceEntity.getResId());
				where.append(" and res_id=?");
			}
		}
		if(StringUtils.isNotBlank(secResourceDto.getResName())) {
			where.append(" and res_name like '%"+secResourceEntity.getResName()+"%'");
		}
		if(StringUtils.isNotBlank(secResourceDto.getAppType())) {
			args.add(secResourceEntity.getAppType());
			where.append(" and app_type=?");
		}
		if(StringUtils.isNotBlank(secResourceDto.getAppMenu())) {
			args.add(secResourceEntity.getAppMenu());
			where.append(" and app_menu=?");
		}
		
		String querySql = sql + where.toString() + " order by res_name";
		
		if(secResourceDto.getPerPage()==-1){
			return super.query(querySql, args.toArray(), new RowMapper<SecResourceEntity>() {
				public SecResourceEntity mapRow(ResultSet rs, int arg1) throws SQLException {
					SecResourceEntity secResourceEntity = new SecResourceEntity();
					secResourceEntity.setResId(rs.getString("res_id"));
					secResourceEntity.setResName(rs.getString("res_name"));
					secResourceEntity.setAppType(rs.getInt("app_type"));
					secResourceEntity.setAppMenu(rs.getInt("app_menu"));
					secResourceEntity.setUrlIds(rs.getString("url_ids"));
					secResourceEntity.setBaseRes(rs.getString("base_res"));
					return secResourceEntity;
				}
			});
		}else{
			return super.queryByPage(querySql, args.toArray(), new RowMapper<SecResourceEntity>() {
				public SecResourceEntity mapRow(ResultSet rs, int arg1) throws SQLException {
					SecResourceEntity secResourceEntity = new SecResourceEntity();
					secResourceEntity.setResId(rs.getString("res_id"));
					secResourceEntity.setResName(rs.getString("res_name"));
					secResourceEntity.setAppType(rs.getInt("app_type"));
					secResourceEntity.setAppMenu(rs.getInt("app_menu"));
					secResourceEntity.setUrlIds(rs.getString("url_ids"));
					secResourceEntity.setBaseRes(rs.getString("base_res"));
					return secResourceEntity;
				}
			}, secResourceDto);
		}
	}
	
	
	public SecResourceEntity getSecResourceById(String resId) throws DataBaseException {
		String sql = "select res_id,res_name,app_type,app_menu,url_ids,base_res from w_sec_resource where res_id = ? and status=1  limit 1";
		return super.queryForObject(sql, new Object[]{resId }, new RowMapper<SecResourceEntity>() {
			public SecResourceEntity mapRow(ResultSet rs, int value) throws SQLException {
				SecResourceEntity secResourceEntity = new SecResourceEntity();
				secResourceEntity.setResId(rs.getString("res_id"));
				secResourceEntity.setResName(rs.getString("res_name"));
				secResourceEntity.setAppType(rs.getInt("app_type"));
				secResourceEntity.setAppMenu(rs.getInt("app_menu"));
				secResourceEntity.setUrlIds(rs.getString("url_ids"));
				secResourceEntity.setBaseRes(rs.getString("base_res"));
				return secResourceEntity;
			}
		});
	}

	
	public void updateResourceStatus(String resId,int status) throws DataBaseException {
		String sql = "update w_sec_resource set status=? where res_id = ? ";
		super.update(sql, new Object[]{status,resId});
	}
	
	
	
	public void createSecResource(SecResourceEntity secResourceEntity) throws DataBaseException {
		String sql = "insert into w_sec_resource(res_id,res_name,app_menu,url_ids,base_res) values(? ,? ,? ,?,?)";
		super.update(sql, new Object[]{secResourceEntity.getResId(),secResourceEntity.getResName() ,secResourceEntity.getAppMenu() ,secResourceEntity.getUrlIds(),secResourceEntity.getBaseRes() });
	}
	
	
	
	public void updateSecResource(SecResourceEntity secResourceEntity) throws DataBaseException {
		StringBuffer updateSql = new StringBuffer("update w_sec_resource set ");
		List<Object> args = new ArrayList<Object>();
		if(StringUtils.isNotBlank(secResourceEntity.getResName())){
			args.add(secResourceEntity.getResName());
			updateSql.append("res_name=?").append(",");	
		}
		if(secResourceEntity.getAppMenu() != 0){
			args.add(secResourceEntity.getAppMenu());
			updateSql.append("app_menu=?").append(",");
		}
		if(StringUtils.isNotBlank(secResourceEntity.getUrlIds())){
			args.add(secResourceEntity.getUrlIds());
			updateSql.append("url_ids=?").append(",");
		}
		args.add(secResourceEntity.getBaseRes());
		updateSql.append("base_res=?").append(",");
		
		
		args.add(secResourceEntity.getResId());
			
		String sql = updateSql.substring(0, updateSql.length()-1) + " where res_id = ? "; 
		super.update(sql, args.toArray());
	}
	
	public List<SecResourceEntity> getResByUserId(String userId) throws DataBaseException{
		String sql = "select r.res_id,r.res_name,r.app_type,r.app_menu,r.url_ids,r.base_res from w_sec_resource r,w_sec_user_resource ur where r.res_id=ur.res_id and ur.user_id = ?";
		return super.query(sql, new Object[]{userId}, new RowMapper<SecResourceEntity>() {
			public SecResourceEntity mapRow(ResultSet rs, int arg1) throws SQLException {
				SecResourceEntity secResourceEntity = new SecResourceEntity();
				secResourceEntity.setResId(rs.getString("res_id"));
				secResourceEntity.setResName(rs.getString("res_name"));
				secResourceEntity.setAppType(rs.getInt("app_type"));
				secResourceEntity.setAppMenu(rs.getInt("app_menu"));
				secResourceEntity.setUrlIds(rs.getString("url_ids"));
				secResourceEntity.setBaseRes(rs.getString("base_res"));
				return secResourceEntity;
			}
		});
	}
	
	public void createUserRes(SecUserResourceEntity userResEntity) throws DataBaseException{
		String sql = "insert into w_sec_user_resource(user_id,res_id) values(? ,?)";
		super.update(sql, new Object[]{userResEntity.getUserId(),userResEntity.getResId() });		
	}
	
	public void destroyUserResByUserId(String userId) throws DataBaseException{
		String sql = "delete from w_sec_user_resource where user_id = ?";
		super.update(sql, new Object[]{userId});
	}
	
}

