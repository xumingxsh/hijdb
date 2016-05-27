package HiJProvider.DBImpl;

import java.util.List;

import HiJCache.Extends.ParseSQL;
import HiJCache.Extends.SQLInfo;
import HiJCache.Extends.SQLProxy;
import HiJDB.DBOperate;
import HiJProvider.IProvider;
import HiJProvider.ProviderConfig;
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
		
		SQLInfo info = proxy.GetValue(id); 
		if (info == null) {
			return null;
		}
		
		return db.ExecuteQuery(cls, info.getSQL(), null);
	}

	public <T> List<T> ExecuteQuery(Class<T> cls, String id, IEventRet8Param<String, String> evt) {
		if (getDB() == null) {
			return null;
		}
		// TODO Auto-generated method stub
		SQLInfo info = proxy.GetValue(id); 
		if (info == null) {
			return null;
		}
		
		String sql = info.getSQL(evt);

		return db.ExecuteQuery(cls, sql, null);
	}

	static SQLProxy proxy = new SQLProxy();
}
