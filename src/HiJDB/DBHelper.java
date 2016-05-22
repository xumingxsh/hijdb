package HiJDB;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import HiJDB.Impl.DBHelperImpl;
import HiJUtil.HiCBO;
import HiJUtil.Generic.IEventRet8Param;

/**
 * ���ݿ���ظ�����
 * @author XuminRong
 *
 */
public final class DBHelper {
	/**
	 * ȡ��ResultSet�е�ֵ
	 * @param t
	 * @param set
	 * @param columnIndex
	 * @return
	 * @throws SQLException
	 */
	public static <T> T ReadValue(Class<T> t, ResultSet set, int columnIndex) throws SQLException {
		return DBHelperImpl.ReadValue(t, set, columnIndex);
	}
	

	/**
	 * ȡ��ResultSet�е�ֵ
	 * @param t
	 * @param set
	 * @param columns
	 * @param field
	 * @return
	 * @throws SQLException
	 */
	public static <T> T ReadValue(Class<T> t, ResultSet set, Map<String, Integer> columns, String field) throws SQLException {
		return DBHelperImpl.ReadValue(t, set, columns, field);
	}
	
	/**
	 * ȡ��������Ӧ��������
	 * @param set
	 * @return
	 * @throws SQLException
	 */
	public static Map<String, Integer> GetFieldIndex(ResultSet set) throws SQLException {
		return DBHelperImpl.GetFieldIndex(set);
	}
	
	/**
	 * ����ResultSetװ�ض���
	 * @param cls
	 * @param t
	 * @param set
	 * @param columns
	 * @return
	 * @throws SQLException
	 */
	public static <T> boolean FillObject(Class<T> cls, T t, ResultSet set, Map<String, Integer> columns) throws SQLException {
		return DBHelperImpl.FillObject(cls, t, set, columns);
	}

	/**
	 * ����ResultSetװ�ض���
	 * @param cls
	 * @param t
	 * @param set
	 * @return
	 * @throws SQLException
	 */
	public static <T> boolean FillObject(Class<T> cls, T t, ResultSet set) throws SQLException {
		return FillObject(cls, t, set);
	}
	public static <T> T CreateObject(Class<T> cls, ResultSet set) {
		return DBHelperImpl.CreateObject(cls,set);
	}
	
	/**
	 * ȡ��Set���б� 
	 * @param cls
	 * @param set
	 * @return
	 * @throws SQLException
	 */
	public static <T> List<T> GetResultsList(Class<T> cls, ResultSet set) throws SQLException {
		return DBHelperImpl.GetResultsList(cls,set);
	}

	/**
	 * ����ĳ�еĶ���
	 * @param cls
	 * @param set
	 * @param index
	 * @return
	 */
	public static <T> T GetResultData(Class<T> cls, ResultSet set, int index) {
		return DBHelperImpl.GetResultData(cls,set, index);
	}

	/**
	 * ����ĳ�еĶ���
	 * @param cls
	 * @param set
	 * @param index
	 * @param columns
	 * @return
	 */
	public static <T> T GetResultData(Class<T> cls, ResultSet set, int index, Map<String, Integer> columns) {
		if (set == null || cls  == null) {
			return null;
		}
		
		try
		{
			T t = cls.newInstance();
			if (!set.absolute(index)) {
				return null;
			}
			if (!FillObject(cls, t, set, columns)) {
				return null;
			}
			return t;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	/**
	 * ȡ�õ�һ������
	 * @param cls
	 * @param set
	 * @return
	 */
	public static <T> T GetFirst(Class<T> cls, ResultSet set) {
		return GetFirst(cls, set, null);
	}
	
	
	/**
	 * ȡ�õ�һ������
	 * @param cls
	 * @param set
	 * @param columns
	 * @return
	 */
	public static <T> T GetFirst(Class<T> cls, ResultSet set, Map<String, Integer> columns) {
		return GetResultData(cls, set, 0, columns);
	}
}
