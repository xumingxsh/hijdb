package HiJDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import HiJDB.Impl.ConnectionProxy;
import HiJDB.Impl.DBOperateImpl;
import HiJUtil.Generic.HiResult;
import HiJUtil.Generic.IEvent8Param;
import HiJUtil.Generic.IEventRet;
import HiJUtil.Generic.IEventRet8Param;

/**
 * ���ݿ⽻����
 * @author XuminRong
 *
 * @version 1.0.0.0
 */
public final class DBOperate {	
	/**
	 * SQLServer���ݿ�(����ѡ�����ݿ�����)
	 */
	public static final int SQL_Server = 1;
	
	/**
	 * Oracle���ݿ�(����ѡ�����ݿ�����)
	 */
	public static final int Oracle = 2;
	
	/**
	 * MySQL���ݿ�(����ѡ�����ݿ�����)
	 */
	public static final int MySQL = 3;
	
	/**
	 * @param conn_str ���ݿ������ַ���
	 * @param db_type ���ݿ�����,�����final����
	 * @param is_close_after_execute
	 */
	public DBOperate(String conn_str, String user, String pwd, int db_type, boolean is_close_after_execute){
		impl = new DBOperateImpl(conn_str, user, pwd, db_type, is_close_after_execute);
	}
	
	/**
	 * @param conn_str
	 * @param db_type
	 */
	public DBOperate(String conn_str, String user, String pwd, int db_type){
		impl = new DBOperateImpl(conn_str, user, pwd, db_type, true);
	}
	
	
	/**
	 * �ر����ݿ�����
	 */
	public void Close(){
		impl.Close();
	}
	
	/**
	 * @param sql
	 * @return
	 * @throws SQLException 
	 */
	public int ExecuteNoQuery(String sql) throws SQLException{
		return ExecuteNoQuery(sql, null);
	}
	
	/**
	 * @param sql
	 * @param callback
	 * @return
	 * @throws SQLException
	 */
	public int ExecuteNoQuery(String sql, IEvent8Param<PreparedStatement> callback) throws SQLException {
	    return impl.ExecuteNoQuery(sql, callback);
	}
	
	/**
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public Object ExecuteScalar(String sql) throws SQLException {
		return ExecuteScalar(sql, null);
	}
	
	/**
	 * @param sql
	 * @param callback
	 * @return
	 * @throws SQLException
	 */
	public Object ExecuteScalar(String sql, IEvent8Param<PreparedStatement> callback) throws SQLException {
		return ExecuteScalar(Object.class, sql, callback);
	}
	
	/**
	 * @param t
	 * @param sql
	 * @return
	 * @throws SQLException 
	 */
	public <T> T ExecuteScalar(Class<T> t, String sql) throws SQLException{
		return ExecuteScalar(t, sql, null);
	}
	
	/**
	 * @param t
	 * @param sql
	 * @param callback
	 * @return
	 * @throws SQLException
	 */
	public <T> T ExecuteScalar(Class<T> t, String sql, IEvent8Param<PreparedStatement> callback) throws SQLException{
		return impl.ExecuteScalar(t, sql, callback);
	}

	
	/**
	 * ִ�в�ѯ
	 * @param sql
	 * @param setCallback ��Ҫ�Խ�����еĴ���
	 * @param callback ��Ҫ������ز���
	 * @return
	 * @throws SQLException
	 */
	public boolean  ExecuteQuery(String sql, IEventRet8Param<Boolean, ResultSet> setCallback, IEvent8Param<PreparedStatement> callback) throws SQLException{
		return impl.ExecuteQuery(sql, setCallback, callback);
	}

	/**
	 * ���ض����б�
	 * @param cls
	 * @param sql
	 * @return
	 */
	public <T> List<T> ExecuteQuery(Class<T> cls, String sql) {
		return ExecuteQuery(cls, sql, null);
	}
		
	/**
	 * ���ض����б�
	 * @param cls
	 * @param sql
	 * @param callback
	 * @return
	 */
	public <T> List<T> ExecuteQuery(Class<T> cls, String sql, IEvent8Param<PreparedStatement> callback) {
		try {
			final HiResult<List<T>> result = new HiResult<List<T>>();
			result.Set(null);
			impl.ExecuteQuery(sql, new IEventRet8Param<Boolean, ResultSet>(){

				@Override
				public Boolean OnEvent(ResultSet v) {
					List<T> lst = null;
					try {
						lst = DBHelper.GetResultsList(cls, v);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return false;
					}
					result.Set(lst);
					return lst != null;
				}				
			}, callback);
			return result.Get();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(sql);
			return null;
		}
	}

	/**
	 * ���ص�һ�д����Ķ���
	 * @param cls
	 * @param sql
	 * @return
	 */
	public <T> T ExecuteQuerySingle(Class<T> cls, String sql) {
		return ExecuteQuerySingle(cls, sql, null);
	}
	
	/**
	 * ���ص�һ�д����Ķ���
	 * @param cls
	 * @param sql
	 * @param callback
	 * @return
	 */
	public <T> T ExecuteQuerySingle(Class<T> cls, String sql, IEvent8Param<PreparedStatement> callback) {
		try {
			final HiResult<T> result = new HiResult<T>();
			result.Set(null);
			impl.ExecuteQuery(sql, new IEventRet8Param<Boolean, ResultSet>(){

				@Override
				public Boolean OnEvent(ResultSet v) {
						T t = DBHelper.GetFirst(cls, v);
						result.Set(t);
						return true;
				}				
			}, callback);
			return result.Get();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(sql);
			return null;
		}
	}

	/**
	 * @param sql
	 * @param setCallback
	 * @return
	 * @throws SQLException 
	 */
	public boolean  ExecuteQuery(String sql, IEventRet8Param<Boolean, ResultSet> setCallback) throws SQLException{
		return ExecuteQuery(sql, setCallback, null);
	}
	
	/**
	 * @param evt
	 */
	public void OnTrans(IEventRet8Param<Boolean, Connection> evt) {
		impl.OnTrans(evt);
	}

	/**
	 * ���Ҫ֧�ֵ����ݿ����Ͳ��ṩ��������
	 * @param type
	 * @param creator
	 * @return
	 */
	public static boolean AddDBCreator(int type, IEventRet<ICreator> creator) {
		return ConnectionProxy.AddDBCreator(type, creator);
	}
	
	DBOperateImpl impl = null;
}
