package HiJProvider;

import java.util.List;

import HiJProvider.DBImpl.DBProvider;
import HiJUtil.Generic.IEventRet8Param;

public class DataProvider {
	public static <T> List<T> ExecuteQuery(Class<T> cls, String id) {
		return provider.ExecuteQuery(cls, id);		
	}
	
	public static <T> List<T> ExecuteQuery(Class<T> cls, String id, IEventRet8Param<String, String> evt) {
		return provider.ExecuteQuery(cls, id, evt);
	}
	static IProvider provider = new DBProvider();
}
