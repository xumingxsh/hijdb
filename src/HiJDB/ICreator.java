package HiJDB;

import java.sql.Connection;

/**
 * ���ݿ������������ṩ�ӿ�
 * @author XuminRong
 *
 */
public interface ICreator {
	/**
	 * ������ݿ�������Ϣ
	 * @return
	 */
	public String GetDriver();
	
	/**
	 * �������ݿ�����
	 * @param conn
	 * @param user
	 * @param pwd
	 * @return
	 */
	public Connection CreateConnection(String conn, String user, String pwd);

	
	/**
	 * �������ݿ�����
	 * @param conn
	 * @param user
	 * @param pwd
	 * @param isTrans
	 * @return
	 */
	public Connection CreateConnection(String conn, String user, String pwd, boolean isTrans);
	
	/**
	 * �ر����ݿ�����
	 * @param conn
	 */
	public void CloseConnection(Connection conn);
	
	/**
	 * @param conn
	 * @param isTrans
	 */
	public void CloseConnection(Connection conn, boolean isTrans);
}
