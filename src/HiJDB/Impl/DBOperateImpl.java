package HiJDB.Impl;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import HiJDB.*;
import HiJUtil.HiLog;
import HiJUtil.Generic.IEvent;

/**
 * @author Administrator
 *
 */
public class DBOperateImpl {
	
	/**
	 * @param conn_str ���ݿ������ַ���
	 * @param db_type ���ݿ�����,�����final����
	 * @param is_close_after_execute
	 */
	public DBOperateImpl(String conn_str, String user, String pwd, int db_type, boolean is_close_after_execute){
		this.connStr = conn_str;
		this.db_type = db_type;
		this.is_close_after_exe = is_close_after_execute;
		this.creator = GetCreator(db_type);
		is_init_success = InitDB(creator, db_type);
		if (is_init_success) {
			conn = CreateConnection(conn_str, user, pwd);
			if (conn == null) {
				is_init_success = false;
			}
		}
	}
	
	/**
	 * �ر����ݿ�����
	 */
	public void Close(){
		
	}
	
	/**
	 * @param sql
	 * @return
	 */
	public int ExecuteNoQuery(String sql){
		return 0;
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
		return null;
	}
	
	/**
	 * @param evt
	 */
	public void OnTrans(IEvent evt) {
		
	}
	
	/**
	 * @return
	 */
	/*private boolean Open(){
		if (!is_init_success) {
			return false;
		}
		return false;
	}*/
	
	/**
	 * @param creator
	 * @param db_type
	 */
	private static boolean InitDB(ICreator creator, int db_type){
		if (creator == null) {
			HiLog.Error(String.format(
					"database(%d) not support, please using DBOperate.AddDBCreator set",
					db_type));
			return false;
		}
		String driver = creator.GetDriver();
		if (driver == null) {
			return false;
		}
		if (forNames.containsKey(driver)) {
			return true;
		}
		
		try {
			Class.forName(driver);
			return true;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			HiLog.Error(String.format(
					"database(%d) driver name(%s) init failed, beacuse (%s) not found",
					db_type, driver, driver));
			return false;
		}
	}
	
	private static Connection CreateConnection(String conn_str, String user, String pwd) {

		try {
			return  (Connection) DriverManager.getConnection(conn_str, user, pwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			HiLog.Error(String.format(
					"connection string (%s) create connection failed, beacuse:%s",
					conn_str, e.getMessage()));
			return null;
		}
	}
	
	Connection conn = null;
	
	ICreator creator = null;
	boolean is_init_success = false;
	String connStr = "";
	int db_type = 1;
	boolean is_close_after_exe = true;	
	
	/**
	 * ����Ҫ֧�ֵ����ݿ����Ͳ��ṩ��������
	 *
	 * @param type
	 * @param creator
	 * @return
	 */
	public static boolean AddDBCreator(int type, ICreator creator) {
		if(creator == null || creators.containsKey(type)) {
			return false;
		}
		creators.put(type, creator);
		return true;
	}
	
	private static ICreator GetCreator(int type) {
		if(creators.containsKey(type)) {
			return null;
		}
		return creators.get(type);
	}

	static Map<Integer, ICreator> creators = new HashMap<Integer, ICreator>();
	static Map<String, String> forNames = new HashMap<String, String>();
}