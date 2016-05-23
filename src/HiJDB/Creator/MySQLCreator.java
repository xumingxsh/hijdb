package HiJDB.Creator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import HiJDB.ICreator;
import HiJUtil.HiLog;

/**
 * MySQL的默认实现
 * @author Administrator
 *
 */
public class MySQLCreator implements ICreator {

	public MySQLCreator() {
		isCloseAfterExecute = false;
	}
	@Override
	public String GetDriver() {
		// TODO Auto-generated method stub
		return "com.mysql.jdbc.Driver";
	}

	@Override
	public Connection CreateConnection(String conn_str, String user, String pwd) {
		if (connection != null && !isCloseAfterExecute) {
			return connection;
		}

		try {
			Connection cnn =  (Connection) DriverManager.getConnection(conn_str, user, pwd);
			if (!isCloseAfterExecute) {
				connection = cnn;
			}
			return cnn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			HiLog.Error(String.format(
					"connection string (%s) create connection failed, beacuse:%s",
					conn_str, e.getMessage()));
			return null;
		}
	}

	@Override
	public Connection CreateConnection(String conn, String user, String pwd, boolean isTrans) {
		if (isTrans) {
			try {
				return  (Connection) DriverManager.getConnection(conn, user, pwd);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				HiLog.Error(String.format(
						"connection string (%s) create connection failed, beacuse:%s",
						conn, e.getMessage()));
				return null;
			}
		} else {
			return CreateConnection(conn, user, pwd);
		}
	}

	@Override
	public void CloseConnection(Connection conn) {
		CloseConnection(conn, false);		
	}

	@Override
	public void CloseConnection() {
		if (!isCloseAfterExecute && connection != null) {
			CloseConnection(connection, true);		
			connection = null;
		}
		
	}

	@Override
	public void CloseConnection(Connection conn, boolean isTrans) {
		if (!isCloseAfterExecute && !isTrans) {
			return;
		}
		if (conn == null) {
			return;
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conn = null;
	}

	private boolean isCloseAfterExecute;
	private Connection connection;

	public boolean isCloseAfterExecute() {
		return isCloseAfterExecute;
	}

	public void setCloseAfterExecute(boolean isCloseAfterExecute) {
		this.isCloseAfterExecute = isCloseAfterExecute;
	}
}
