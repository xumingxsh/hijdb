package HiJDB;

import java.sql.Connection;

/**
 * 数据库访问相关属性提供接口
 * @author XuminRong
 *
 */
public interface ICreator {
	/**
	 * 获得数据库驱动信息
	 * @return
	 */
	public String GetDriver();
	
	/**
	 * 创建数据库连接
	 * @param conn
	 * @param user
	 * @param pwd
	 * @return
	 */
	public Connection CreateConnection(String conn, String user, String pwd);

	
	/**
	 * 创建数据库连接
	 * @param conn
	 * @param user
	 * @param pwd
	 * @param isTrans
	 * @return
	 */
	public Connection CreateConnection(String conn, String user, String pwd, boolean isTrans);
	
	/**
	 * 关闭数据库连接
	 * @param conn
	 */
	public void CloseConnection(Connection conn);
	
	/**
	 * @param conn
	 * @param isTrans
	 */
	public void CloseConnection(Connection conn, boolean isTrans);
}
