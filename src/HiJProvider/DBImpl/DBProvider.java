package HiJProvider.DBImpl;

import java.sql.SQLException;
import java.util.List;

import HiJCache.Extends.ParseSQL;
import HiJCache.Extends.SQLInfo;
import HiJCache.Extends.SQLProxy;
import HiJDB.DBOperate;
import HiJProvider.IProvider;
import HiJProvider.ProviderConfig;
import HiJUtil.HiTypeHelper;
import HiJUtil.Generic.IEventRet8Param;

public class DBProvider implements IProvider {
	
	DBOperate db = null;
	
	private DBOperate getDB()
	{
		if (db == null)
		{
			db = new DBOperate(ProviderConfig.getConnStr(), ProviderConfig.getUser(), ProviderConfig.getPwd(), ProviderConfig.getDbType());
		}
		return db;
	}

	@Override
	public <T> List<T> ExecuteQuery(Class<T> cls, String id) {
		if (getDB() == null) {
			return null;
		}
		
		return getDB().ExecuteQuery(cls, GetSql(id), null);
	}

	public <T> List<T> ExecuteQuery(Class<T> cls, String id, IEventRet8Param<String, String> evt) {
		if (getDB() == null) {
			return null;
		}
		return getDB().ExecuteQuery(cls, GetSql(id, evt), null);
	}

	public <T> T ExecuteQuerySingle(Class<T> cls, String id) {
		if (getDB() == null) {
			return null;
		}
		return getDB().ExecuteQuerySingle(cls, GetSql(id), null);		
	}
	public <T> T ExecuteQuerySingle(Class<T> cls, String id, IEventRet8Param<String, String> evt) {
		if (getDB() == null) {
			return null;
		}
		return getDB().ExecuteQuerySingle(cls, GetSql(id, evt), null);
	}

	public <T> T ExecuteScalar(Class<T> cls, String id) {
		if (getDB() == null) {
			return HiTypeHelper.GetDefault(cls);
		}
		try {
			return getDB().ExecuteScalar(cls, GetSql(id), null);
		} catch (SQLException e) {
			e.printStackTrace();
			return HiTypeHelper.GetDefault(cls);
		}
	}
	
	public <T> T ExecuteScalar(Class<T> cls, String id, IEventRet8Param<String, String> evt){
		if (getDB() == null) {
			return HiTypeHelper.GetDefault(cls);
		}
		try {
			return getDB().ExecuteScalar(cls, GetSql(id, evt), null);
		} catch (SQLException e) {
			e.printStackTrace();
			return HiTypeHelper.GetDefault(cls);
		}
	}

	private String GetSql(String id) {
		return GetSql(id, null);
	}
	private String GetSql(String id, IEventRet8Param<String, String> evt) {
		SQLInfo info = SQLProxy.GetValue(id); 
		if (info == null) {
			return null;
		}
		
		if (evt == null) {
			return info.getSQL();
		} else {
			return info.getSQL(evt);
		}
	}

}
