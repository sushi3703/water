package net.water.security.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.kuakao.core.base.dao.BaseDAO;
import net.kuakao.core.exception.DataBaseException;
import net.water.security.dao.ISecUrlDAO;
import net.water.security.dto.SecUrlDto;
import net.water.security.entity.SecUrlEntity;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository
public class SecUrlDAO extends BaseDAO implements ISecUrlDAO {
	
	public List<SecUrlEntity> querySecUrls(SecUrlDto secUrlDto) throws DataBaseException {
		String sql = "select url_id,url_name,url_method,url_path,app_type,app_menu,url_show,url_order from sec_url";
		StringBuffer where = new StringBuffer();
		List<Object> args = new ArrayList<Object>();
		SecUrlEntity secUrlEntity = secUrlDto.toSecUrlEntity();
		
		if(StringUtils.isNotBlank(secUrlDto.getUrlId())) {
			if(secUrlDto.getUrlId().indexOf(",") != -1) {
				where.append(" and url_id in (" + secUrlDto.getUrlId() + ")");
			} else {
				args.add(secUrlEntity.getUrlId());
				where.append(" and url_id=?");
			}
		}
		if(StringUtils.isNotBlank(secUrlDto.getUrlName())) {
			where.append(" and url_name like '%"+secUrlDto.getUrlName()+"%'");
		}
		if(StringUtils.isNotBlank(secUrlDto.getUrlMethod())) {
			args.add(secUrlEntity.getUrlMethod());
			where.append(" and url_method=?");
		}
		if(StringUtils.isNotBlank(secUrlDto.getUrlPath())) {
			args.add(secUrlEntity.getUrlPath());
			where.append(" and url_path=?");
		}
		if(StringUtils.isNotBlank(secUrlDto.getAppType())) {
			args.add(secUrlEntity.getAppType());
			where.append(" and app_type=?");
		}
		if(StringUtils.isNotBlank(secUrlDto.getAppMenu())) {
			args.add(secUrlEntity.getAppMenu());
			where.append(" and app_menu=?");
		}
		if(StringUtils.isNotBlank(secUrlDto.getUrlShow())) {
			args.add(secUrlEntity.getUrlShow());
			where.append(" and url_show=?");
		}
		
		String sqlStr = sql + where.toString().replaceFirst("and","where")+" order by app_type,app_menu,url_order";
		if(secUrlDto.getPerPage()==-1){
			return super.query(sqlStr, args.toArray(), new RowMapper<SecUrlEntity>() {
				public SecUrlEntity mapRow(ResultSet rs, int arg1) throws SQLException {
					SecUrlEntity secUrlEntity = new SecUrlEntity();
					secUrlEntity.setUrlId(rs.getInt("url_id"));
					secUrlEntity.setUrlName(rs.getString("url_name"));
					secUrlEntity.setUrlMethod(rs.getInt("url_method"));
					secUrlEntity.setUrlPath(rs.getString("url_path"));
					secUrlEntity.setAppType(rs.getInt("app_type"));
					secUrlEntity.setAppMenu(rs.getInt("app_menu"));
					secUrlEntity.setUrlShow(rs.getInt("url_show"));
					secUrlEntity.setUrlOrder(rs.getInt("url_order"));
					return secUrlEntity;
				}
			});	
		}else{
			return super.queryByPage(sqlStr, args.toArray(), new RowMapper<SecUrlEntity>() {
				public SecUrlEntity mapRow(ResultSet rs, int arg1) throws SQLException {
					SecUrlEntity secUrlEntity = new SecUrlEntity();
					secUrlEntity.setUrlId(rs.getInt("url_id"));
					secUrlEntity.setUrlName(rs.getString("url_name"));
					secUrlEntity.setUrlMethod(rs.getInt("url_method"));
					secUrlEntity.setUrlPath(rs.getString("url_path"));
					secUrlEntity.setAppType(rs.getInt("app_type"));
					secUrlEntity.setAppMenu(rs.getInt("app_menu"));
					secUrlEntity.setUrlShow(rs.getInt("url_show"));
					secUrlEntity.setUrlOrder(rs.getInt("url_order"));
					return secUrlEntity;
				}
			}, secUrlDto);
		}
		
	}

	
	
	public SecUrlEntity getSecUrlById(int urlId) throws DataBaseException {
		String sql = "select url_id,url_name,url_method,url_path,app_type,app_menu,url_show,url_order from sec_url where url_id = ?  limit 1";
		return this.queryForObject(sql, new Object[]{urlId }, new RowMapper<SecUrlEntity>() {
			public SecUrlEntity mapRow(ResultSet rs, int value) throws SQLException {
				SecUrlEntity secUrlEntity = new SecUrlEntity();
				secUrlEntity.setUrlId(rs.getInt("url_id"));
				secUrlEntity.setUrlName(rs.getString("url_name"));
				secUrlEntity.setUrlMethod(rs.getInt("url_method"));
				secUrlEntity.setUrlPath(rs.getString("url_path"));
				secUrlEntity.setAppType(rs.getInt("app_type"));
				secUrlEntity.setAppMenu(rs.getInt("app_menu"));
				secUrlEntity.setUrlShow(rs.getInt("url_show"));
				secUrlEntity.setUrlOrder(rs.getInt("url_order"));
				return secUrlEntity;
			}
		});
	}

	
	public void destroySecUrl(int urlId) throws DataBaseException {
		String sql = "delete from sec_url where url_id = ? ";
		super.update(sql, new Object[]{urlId});
	}
	
	
	
	public void createSecUrl(SecUrlEntity secUrlEntity) throws DataBaseException {
		String sql = "insert into sec_url(url_name,url_method,url_path,app_menu,url_show,url_order) values(? ,? ,? ,? ,? ,? )";
		super.update(sql, new Object[]{secUrlEntity.getUrlName() ,secUrlEntity.getUrlMethod() ,secUrlEntity.getUrlPath() ,secUrlEntity.getAppMenu() ,secUrlEntity.getUrlShow() ,secUrlEntity.getUrlOrder() });
	}
	
	
	
	public void updateSecUrl(SecUrlDto secUrlDto) throws DataBaseException {
		StringBuffer updateSql = new StringBuffer("update sec_url set ");
		List<Object> args = new ArrayList<Object>();
		SecUrlEntity secUrlEntity = secUrlDto.toSecUrlEntity();
		if(StringUtils.isNotBlank(secUrlDto.getUrlName())){
			args.add(secUrlEntity.getUrlName());
			updateSql.append("url_name=?").append(",");
		}
		if(StringUtils.isNotBlank(secUrlDto.getUrlMethod())){
			args.add(secUrlEntity.getUrlMethod());
			updateSql.append("url_method=?").append(",");
		}
		if(StringUtils.isNotBlank(secUrlDto.getUrlPath())){
			args.add(secUrlEntity.getUrlPath());
			updateSql.append("url_path=?").append(",");
		}
		if(StringUtils.isNotBlank(secUrlDto.getAppType())){
			args.add(secUrlEntity.getAppType());
			updateSql.append("app_type=?").append(",");
		}
		if(StringUtils.isNotBlank(secUrlDto.getAppMenu())){
			args.add(secUrlEntity.getAppMenu());
			updateSql.append("app_menu=?").append(",");
		}
		if(StringUtils.isNotBlank(secUrlDto.getUrlShow())){
			args.add(secUrlEntity.getUrlShow());
			updateSql.append("url_show=?").append(",");
		}
		if(StringUtils.isNotBlank(secUrlDto.getUrlOrder())){
			args.add(secUrlEntity.getUrlOrder());
			updateSql.append("url_order=?").append(",");
		}
		
		args.add(secUrlEntity.getUrlId());
			
		
		String sql = updateSql.substring(0, updateSql.length()-1) + " where url_id = ? "; 
		super.update(sql, args.toArray());
	}

}

