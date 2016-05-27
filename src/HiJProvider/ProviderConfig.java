package HiJProvider;

import HiJCache.Extends.SQLProxy;
import HiJDB.DBOperate;
import HiJDB.ICreator;
import HiJUtil.Generic.IEventRet;

public class ProviderConfig {
	private static int dbType;
	public static int getDbType() {
		return dbType;
	}
	public static void setDbType(int dbType) {
		ProviderConfig.dbType = dbType;
	}
	public static String getConnStr() {
		return connStr;
	}
	public static void setConnStr(String connStr) {
		ProviderConfig.connStr = connStr;
	}
	public static String getUser() {
		return user;
	}
	public static void setUser(String user) {
		ProviderConfig.user = user;
	}
	public static String getPwd() {
		return pwd;
	}
	public static void setPwd(String pwd) {
		ProviderConfig.pwd = pwd;
	}
	
	public static void RegistDB(int type, IEventRet<ICreator> creator) {
		DBOperate.AddDBCreator(type, creator);
	}
	
	public static void setSQLFolder(String path) throws Exception {
		SQLProxy.LoadXMLsByFolder(path);
	}
	private static  String connStr;
	private static String user;
	private static String pwd;
}
