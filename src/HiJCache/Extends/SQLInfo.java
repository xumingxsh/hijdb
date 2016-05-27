package HiJCache.Extends;

import java.util.List;

import HiJUtil.Generic.IEventRet8Param;

public class SQLInfo {
	public void setSQL(String sql){
		this.sql = sql;
		sqlList = ParseSQL.Parase(sql);
	}
	public String getSQL(){
		return sql;
	}
	public void setCountSQL(String sql) {
		countSQL = sql;
	}
	public String getCountSQL() {
		return countSQL;
	}
	public void setDBType(String dbType) {
		this.dbType = dbType;
	}
	public String getDbType() {
		return dbType;
	}
	
	public boolean InitParam() {
		if (sqlList == null) {
			return false;
		}
		for (int i = 0; i < sqlList.size(); i++) {
			SQLStr info = sqlList.get(i);
			if (!info.isParam()) {
				continue;
			}
		}
		return true;
	}
	
	
	/**
	 * SQL语句
	 */
	private String sql;
	/**
	 * 计算查询总数的SQL语句
	 */
	private String countSQL;
	
	public List<SQLStr> getSqlList() {
		return sqlList;
	}
	
	public String getSQL(IEventRet8Param<String, String> evt) {
		if (sqlList == null) {
			return sql;
		}
		return ParseSQL.CreateSQL(sqlList, evt);
	}

	/**
	 * 数据库类型
	 */
	private String dbType;
	
	private List<SQLStr> sqlList;
}
