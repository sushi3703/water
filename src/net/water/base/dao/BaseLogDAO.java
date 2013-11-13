package net.water.base.dao;

import java.sql.SQLException;
import java.util.Date;

import net.kuakao.core.base.util.UserUtils;
import net.kuakao.core.dao.impl.MysqlDAO;

public class BaseLogDAO extends MysqlDAO {

	/**
	 * 添加dao级别的日志记录
	 * @param daoName
	 * @param sqlValue SQL语句内容
	 * @param params 参数
	 * @param otherKey 其它关键字
	 * @throws SQLException
	 */
	public void insertDaoLog(String daoName,String sqlValue,Object[] params,String otherKey) throws SQLException {
		StringBuffer paramsStr = new StringBuffer("");
		if(params != null){
			for(Object o : params){
				if(!paramsStr.toString().equals(""))paramsStr.append(",");
				paramsStr.append(o);
			}
		}
		String doSql = "insert into base_log_dao(dao_name,sql,params,create_time,user_id,other_key) values(?,?,?,?,?,?)";
		super.update(doSql, new Object[]{daoName,sqlValue,paramsStr.toString(),new Date(),UserUtils.getLoginUserId(),otherKey });
	}
	
	/**
	 * 添加service级别的日志记录
	 * @param moduleName 模块
	 * @param serviceName 具体service的类名
	 * @param logName 记录的事件
	 * @param logContent 记录的内容
	 * @param otherKey 额外关键字
	 * @throws SQLException
	 */
	public void insertServiceLog(String moduleName,String serviceName,String logName,String logContent,String otherKey) throws SQLException {
		String doSql = "insert into base_service_dao(module_name,service_name,log_name,log_content,create_time,user_id,other_key) values(?,?,?,?,?,?,?)";
		super.update(doSql, new Object[]{moduleName,serviceName,logName,logContent,new Date(),UserUtils.getLoginUserId(),otherKey });
	}
	
	
}
