package HiJDB.Impl;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import HiJDB.*;
import HiJUtil.HiLog;
import HiJUtil.HiTypeHelper;
import HiJUtil.Generic.IEvent;
import HiJUtil.Generic.IEvent8Param;
import HiJUtil.Generic.IEventRet8Param;

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
		connProxy.Init(conn_str, user, pwd, db_type, is_close_after_execute);
	}
	
	/**
	 * 关闭数据库连接
	 * @throws SQLException 
	 */
	public void Close() {
		connProxy.Close();
	}
	
	/**
	 * @param sql
	 * @param callback
	 * @return
	 * @throws SQLException
	 */
	public int ExecuteNoQuery(String sql, IEvent8Param<PreparedStatement> callback) throws SQLException {
	    Connection conn = connProxy.GetConnection();
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
	    	connProxy.CloseAfterExecute(conn);
	    }
	}
	
	/**
	 * @param t
	 * @param sql
	 * @param callback
	 * @return
	 * @throws SQLException
	 */
	public <T> T ExecuteScalar(Class<T> t, String sql, IEvent8Param<PreparedStatement> callback) throws SQLException{
	    Connection conn = connProxy.GetConnection();
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
	    	
	    	connProxy.CloseAfterExecute(conn);
	    }
	}
	
	
	/**
	 * @param sql
	 * @return
	 * @throws SQLException 
	 */
	public boolean  ExecuteQuery(String sql, IEventRet8Param<Boolean, ResultSet> setCallback, IEvent8Param<PreparedStatement> callback) throws SQLException{
	    Connection conn = connProxy.GetConnection();
	    if (conn == null) {
	    	return false;
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
	    		return false;
	    	}
	    	if (setCallback != null) {
	    		return setCallback.OnEvent(set);
	    	}
	        return false;
	    }
	    finally
	    {
	    	if (pstmt != null) {
	    		pstmt.close();
	    	}
	    	
	    	connProxy.CloseAfterExecute(conn);
	    }
	}
	
	/**
	 * @param evt
	 */
	public void OnTrans(IEvent evt) {
		
	}
		
	ConnectionProxy connProxy = new ConnectionProxy();
}
