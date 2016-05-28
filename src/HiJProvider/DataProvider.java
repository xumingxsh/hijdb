package HiJProvider;

import java.util.List;

import HiJProvider.DBImpl.DBProvider;
import HiJUtil.Generic.IEventRet8Param;

/**
 * 数据交互接口
 * @author XuminRong
 *
 */
public class DataProvider {
	/**
	 * 队列查询
	 * @param cls
	 * @param id
	 * @return
	 */
	public static <T> List<T> ExecuteQuery(Class<T> cls, String id) {
		return provider.ExecuteQuery(cls, id);		
	}
	
	/**
	 * 队列查询
	 * @param cls
	 * @param id
	 * @param evt
	 * @return
	 */
	public static <T> List<T> ExecuteQuery(Class<T> cls, String id, IEventRet8Param<String, String> evt) {
		return provider.ExecuteQuery(cls, id, evt);
	}
		
	/**
	 * 查询一个对象
	 * @param cls
	 * @param id
	 * @return
	 */
	public static <T> T ExecuteQuerySingle(Class<T> cls, String id) {
		return provider.ExecuteQuerySingle(cls, id);		
	}
	
	/**
	 * 查询一个对象
	 * @param cls
	 * @param id
	 * @param evt
	 * @return
	 */
	public static <T> T ExecuteQuerySingle(Class<T> cls, String id, IEventRet8Param<String, String> evt) {
		return provider.ExecuteQuerySingle(cls, id, evt);		
	}
	
	/**
	 * 查询一个字段
	 * @param cls
	 * @param id
	 * @return
	 */
	public static <T> T ExecuteScalar(Class<T> cls, String id){
		return provider.ExecuteScalar(cls, id);		
	}
	
	/**
	 * 查询一个字段
	 * @param cls
	 * @param id
	 * @param evt
	 * @return
	 */
	public static <T> T ExecuteScalar(Class<T> cls, String id, IEventRet8Param<String, String> evt){
		return provider.ExecuteScalar(cls, id, evt);		
	}
	
	static IProvider provider = new DBProvider();
}
