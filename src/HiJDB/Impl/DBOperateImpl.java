package HiJDB.Impl;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import HiJDB.*;
import HiJUtil.HiLog;
import HiJUtil.HiTypeHelper;
import HiJUtil.Generic.IEvent;
import HiJUtil.Generic.IEvent8Param;

/**
 * @author Administrator
 *
 */
public class DBOperateImpl {
	
	/**
	 * @param conn_str 数据库连接字符串
	 * @param db_type 数据库类型,请参阅final变量
	 * @param is_close_after_execute
	 */
	public DBOperateImpl(String conn_str, String user, String pwd, int db_type, boolean is_close_after_execute){
		this.connStr = conn_str;
		this.db_type = db_type;
		this.user = user;
		this.pwd = pwd;
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
	 * 关闭数据库连接
	 * @throws SQLException 
	 */
	public void Close() {
		if (this.is_close_after_exe) {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				conn = null;
			}
		}
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
	    Connection conn = this.getConnection();
	    if (conn == null) {
	    	return -1;
	    }
	    PreparedStatement pstmt = null;
	    try
	    {
	    	pstmt = (PreparedStatement) conn.prepareStatement(sql);
	    	if (callback != null) {
	    		callback.OnEvent(pstmt);
	    	}
	        return pstmt.executeUpdate();
	    }
	    finally
	    {
	    	if (pstmt != null) {
	    		pstmt.close();
	    	}
	    	
	    	if (this.is_close_after_exe && this.creator != null) {
	    		this.creator.CloseConnection(conn);
	    	}
	    }
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
	    Connection conn = this.getConnection();
	    if (conn == null) {
	    	return HiTypeHelper.GetDefault(t);
	    }
	    PreparedStatement pstmt = null;
	    try
	    {
	    	pstmt = (PreparedStatement) conn.prepareStatement(sql);
	    	if (callback != null) {
	    		callback.OnEvent(pstmt);
	    	}
	    	ResultSet set = pstmt.executeQuery();
	    	if (set == null) {
	    		return HiTypeHelper.GetDefault(t);
	    	}
	    	if (!set.first()) {
	    		return HiTypeHelper.GetDefault(t);
	    	}
	        return DBHelper.ReadValue(t, set, 0);
	    }
	    finally
	    {
	    	if (pstmt != null) {
	    		pstmt.close();
	    	}
	    	
	    	if (this.is_close_after_exe && this.creator != null) {
	    		this.creator.CloseConnection(conn);
	    	}
	    }
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
	 * 初始化数据库操作对象,主要实现:
	 * 1) 注册driver
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
	
	private Connection getConnection() {
		if (!this.is_close_after_exe) {
			if (conn == null) {
				conn = CreateConnection(connStr, user, pwd);
			}
			return conn;
		} else {
			if (this.creator != null) {
				return this.creator.CreateConnection(connStr, user, pwd);
			} else {
				return null;
			}
		}
	}
	
	Connection conn = null;
	
	ICreator creator = null;
	boolean is_init_success = false;
	String connStr = "";
	String user = "";
	String pwd = "";
	int db_type = 1;
	boolean is_close_after_exe = true;	
	
	/**
	 * 添加要支持的数据库类型并提供创建类型
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
