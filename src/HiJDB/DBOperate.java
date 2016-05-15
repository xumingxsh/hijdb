package HiJDB;

import java.sql.ResultSet;

import HiJDB.Impl.DBOperateImpl;
import HiJUtil.Generic.IEvent;;

/**
 * ���ݿ⽻����
 * @author XuminRong
 *
 * @version 1.0.0.0
 */
public final class DBOperate {	
	public static final int SQL_Server = 1;
	public static final int Oracle = 2;
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
	 */
	public int ExecuteNoQuery(String sql){
		return impl.ExecuteNoQuery(sql);
	}
	
	/**
	 * @param sql
	 * @return
	 */
	public Object ExecuteScalar(String sql){
		return null;
	}
	
	/**
	 * @param sql
	 * @return
	 */
	public ResultSet  ExecuteQuery(String sql){
		return impl.ExecuteQuery(sql);
	}
	
	/**
	 * @param evt
	 */
	public void OnTrans(IEvent evt) {
		impl.OnTrans(evt);
	}

	/**
	 * ���Ҫ֧�ֵ����ݿ����Ͳ��ṩ��������
	 * @param type
	 * @param creator
	 * @return
	 */
	public static <T> boolean AddDBCreator(int type, ICreator creator) {
		return DBOperateImpl.AddDBCreator(type, creator);
	}
	
	DBOperateImpl impl = null;
}
