package HiJCache.Extends;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public Map<String, SQLParamer> getParams() {
		if (params == null) {
			params = new HashMap<String, SQLParamer>();
		}
		return params;
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
			
			if (!params.containsKey(info.getText())) {
				return false;
			}
			info.setValue(params.get(info.getText()).getValue());
		}
		return true;
	}

	/**
	 * 参数数组
	 */
	private Map<String, SQLParamer> params;
	
	
	/**
	 * SQL语句
	 */
	private String sql;
	/**
	 * 计算查询总数的SQL语句
	 */
	private String countSQL;
	
	/**
	 * 数据库类型
	 */
	private String dbType;
	
	private List<SQLStr> sqlList;
}
