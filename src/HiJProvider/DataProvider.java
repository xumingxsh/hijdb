package HiJProvider;

import java.util.List;

import HiJProvider.DBImpl.DBProvider;
import HiJUtil.Generic.IEventRet8Param;

/**
 * ���ݽ����ӿ�
 * @author XuminRong
 *
 */
public class DataProvider {
	/**
	 * ���в�ѯ
	 * @param cls
	 * @param id
	 * @return
	 */
	public static <T> List<T> ExecuteQuery(Class<T> cls, String id) {
		return provider.ExecuteQuery(cls, id);		
	}
	
	/**
	 * ���в�ѯ
	 * @param cls
	 * @param id
	 * @param evt
	 * @return
	 */
	public static <T> List<T> ExecuteQuery(Class<T> cls, String id, IEventRet8Param<String, String> evt) {
		return provider.ExecuteQuery(cls, id, evt);
	}
		
	/**
	 * ��ѯһ������
	 * @param cls
	 * @param id
	 * @return
	 */
	public static <T> T ExecuteQuerySingle(Class<T> cls, String id) {
		return provider.ExecuteQuerySingle(cls, id);		
	}
	
	/**
	 * ��ѯһ������
	 * @param cls
	 * @param id
	 * @param evt
	 * @return
	 */
	public static <T> T ExecuteQuerySingle(Class<T> cls, String id, IEventRet8Param<String, String> evt) {
		return provider.ExecuteQuerySingle(cls, id, evt);		
	}
	
	/**
	 * ��ѯһ���ֶ�
	 * @param cls
	 * @param id
	 * @return
	 */
	public static <T> T ExecuteScalar(Class<T> cls, String id){
		return provider.ExecuteScalar(cls, id);		
	}
	
	/**
	 * ��ѯһ���ֶ�
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
