package HiJDB;

import java.sql.Connection;

/**
 * @author Administrator
 *
 */
public interface ICreator {
	public String GetDriver();
	public Connection CreateConnection(String conn, String user, String pwd);
	public void CloseConnection(Connection conn);
}
