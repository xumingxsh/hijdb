package HiJCache.Extends;

import java.util.ArrayList;
import java.util.List;

public class SQLInfo {
	public void setSQL(String sql){
		this.sql = sql;
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
	public List<SQLParamer> getParams() {
		if (params == null) {
			params = new ArrayList<SQLParamer>();
		}
		return params;
	}
	public void setDBType(String dbTYpe) {
		this.dbType = dbType;
	}
	public String getDBType() {
		return dbType;
	}
	/**
	 * ��������
	 */
	private List<SQLParamer> params;
	
	
	/**
	 * SQL���
	 */
	private String sql;
	/**
	 * �����ѯ������SQL���
	 */
	private String countSQL;
	
	/**
	 * ���ݿ�����
	 */
	private String dbType;
}
