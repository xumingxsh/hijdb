package HiJDB;

import java.sql.Connection;

/**
 * ���ݿ������������ṩ�ӿ�
 * @author Administrator
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
	 * �ر����ݿ�����
	 * @param conn
	 */
	public void CloseConnection(Connection conn);
}
